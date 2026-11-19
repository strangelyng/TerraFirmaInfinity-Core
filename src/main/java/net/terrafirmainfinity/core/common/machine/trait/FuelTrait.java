package net.terrafirmainfinity.core.common.machine.trait;

import brachy.modularui.api.drawable.Text;
import brachy.modularui.value.sync.BooleanSyncValue;
import brachy.modularui.value.sync.IntSyncValue;
import brachy.modularui.value.sync.PanelSyncManager;
import brachy.modularui.widgets.TextWidget;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.trait.feature.IRecipeLogicModifierTrait;
import com.gregtechceu.gtceu.api.machine.trait.multiblock.MultiblockMachineTrait;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.terrafirmainfinity.core.common.machine.multiblock.WorkableFueledElectricMultiblockMachine;
import net.terrafirmainfinity.core.common.machine.multiblock.multi.IFueledMultiblock;
import net.terrafirmainfinity.core.common.machine.part.FuelHatchPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelPartMachine;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FuelTrait extends MultiblockMachineTrait implements IRecipeLogicModifierTrait {
    @Nullable
    protected FuelPartMachine<?> fuelInput;

    @SaveField
    private int burnTime = 0;

    @SaveField
    private int fuelDuration = 0;

    @SaveField
    private int fuelConsumption = 0;

    @SaveField
    private boolean liquidFueled = false;

    @SaveField
    @Nullable
    protected Fluid fuelFluid;

    @SaveField
    @Nullable
    protected Item fuelItem;

    @Nullable
    protected TickableSubscription burnTimeSubs;

    @Override
    protected List<Class<?>> validMachineClasses() {
        return List.of(IFueledMultiblock.class);
    }

    @Override
    public WorkableFueledElectricMultiblockMachine getMachine() {
        return (WorkableFueledElectricMultiblockMachine) super.getMachine();
    }

    @Override
    public void onStructureInvalid(String substructureName) {
        super.onStructureInvalid(substructureName);
        resetBasicInfo();
    }

    @Override
    public void onStructureFormed(String substructureName) {
        super.onStructureFormed(substructureName);
        setFuelInput(getMachine().getFuelInput());
    }

    @Override
    public void onMachineLoad() {
        super.onMachineLoad();
        checkBurnTimeSubscription();
    }

    @Override
    public void onMachineUnload() {
        super.onMachineUnload();
        if (burnTimeSubs != null) {
            burnTimeSubs.unsubscribe();
            burnTimeSubs = null;
        }
    }

    public void resetBasicInfo() {
        setFuelInput(null);
        fuelItem = null;
        fuelFluid = null;
        fuelDuration = 0;
        fuelConsumption = 0;
    }

    public void checkBurnTimeSubscription() {
        if (burnTime > 0) {
            burnTimeSubs = subscribeServerTick(burnTimeSubs, this::updateBurnTime);
        } else if (burnTimeSubs != null) {
            burnTimeSubs.unsubscribe();
            burnTimeSubs = null;
        }
    }

    public void updateBurnTime() {
        burnTime--;
        checkBurnTimeSubscription();
    }

    public void addBurnTime(int fuelDuration) {
        burnTime += fuelDuration;
        checkBurnTimeSubscription();
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        assert fuelInput != null;

        if (recipe == null) return false;

        if (burnTime > 0) {
            return true;
        } else if (fuelInput.isReadyForRecipe()) {
            refreshFuelData();

            if (fuelInput.consumeFuel(fuelConsumption, true)) {
                addBurnTime(fuelDuration);
                fuelInput.consumeFuel(fuelConsumption, false);
                return true;
            } else {
                RecipeLogic.putFailureReason(this, recipe, Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onWorking() {
        assert fuelInput != null;

        if (burnTime <= 0) {
            if (!fuelInput.isReadyForRecipe()) {
                getMachine().recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
            } else {
                compareCachedFuels();
                if (fuelDuration < 1 || !fuelInput.consumeFuel(fuelConsumption, true)) {
                    getMachine().recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
                    return true;
                }
                addBurnTime(fuelDuration);
                fuelInput.consumeFuel(fuelConsumption, false);
            }
        }
        return true;
    }

    public void compareCachedFuels() {
        if (liquidFueled) {
            if (fuelFluid != fuelInput.getCachedFuel()) {
                refreshFuelData();
            }
        } else if (fuelItem != fuelInput.getCachedFuel()) {
            refreshFuelData();
        }
    }

    public void refreshFuelData() {
        assert fuelInput != null;

        int fuelTier = Math.max(0, getMachine().getTier() - 1);

        double multiplier = ((double) GTValues.V[fuelTier]) / 8.0d;

        if (liquidFueled) {
            fuelFluid = (Fluid) fuelInput.getFuel();
            fuelDuration = fuelInput.getCachedFuelDuration();
            fuelConsumption = (int) (fuelInput.getCachedFuelConsumption() * multiplier);
        } else {
            fuelItem = (Item) fuelInput.getFuel();

            double newDuration = fuelInput.getCachedFuelDuration() / multiplier;
            fuelDuration = newDuration < 1 ? 0 : (int) newDuration;
            fuelConsumption = fuelInput.getCachedFuelConsumption();
        }
    }

    public void setFuelInput(@Nullable FuelPartMachine<?> fuelInput) {
        this.fuelInput = fuelInput;
        this.liquidFueled = fuelInput instanceof FuelHatchPartMachine;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public int getFuelDuration() {
        return fuelDuration;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public boolean isLiquidFueled() {
        return liquidFueled;
    }

    public static TextWidget<?> addBurnTimeLine(FuelTrait fuelTrait, PanelSyncManager syncManager) {
        BooleanSyncValue liquidFueled = syncManager.getOrCreateSyncHandler("liquidFueled", BooleanSyncValue.class,
                () -> new BooleanSyncValue(fuelTrait::isLiquidFueled));
        IntSyncValue burnTime = syncManager.getOrCreateSyncHandler("burnTime", IntSyncValue.class,
                () -> new IntSyncValue(fuelTrait::getBurnTime));
        return Text.dynamic(() -> {
                    Component time = Component.literal(FormattingUtil.formatTime(burnTime.getIntValue()))
                            .withStyle(ChatFormatting.YELLOW);
                    return Component.translatable("tfinfinity.multiblock.burn_time", time)
                            .withStyle(ChatFormatting.GRAY);
                }).asWidget()
                .setEnabledIf(widget -> !liquidFueled.getBoolValue());
    }

    public static TextWidget<?> addFuelRateLine(FuelTrait fuelTrait, PanelSyncManager syncManager) {
        BooleanSyncValue liquidFueled = syncManager.getOrCreateSyncHandler("liquidFueled", BooleanSyncValue.class,
                () -> new BooleanSyncValue(fuelTrait::isLiquidFueled));
        IntSyncValue fuelDuration = syncManager.getOrCreateSyncHandler("fuelDuration", IntSyncValue.class,
                () -> new IntSyncValue(fuelTrait::getFuelDuration));
        IntSyncValue fuelConsumption = syncManager.getOrCreateSyncHandler("fuelConsumption", IntSyncValue.class,
                () -> new IntSyncValue(fuelTrait::getFuelConsumption));
        return Text.dynamic(() -> {
                    Component amount = Component.literal(FormattingUtil.formatNumbers(fuelConsumption.getIntValue()))
                            .withStyle(ChatFormatting.WHITE);
                    Component rate = Component.literal(FormattingUtil.formatNumbers(fuelDuration.getIntValue()))
                            .withStyle(ChatFormatting.WHITE);

                    String type = liquidFueled.getBoolValue() ? "hatch" : "bus";

                    return Component.translatable("tfinfinity.multiblock.fuel_rate." + type, amount, rate)
                            .withStyle(ChatFormatting.GRAY);
                }).asWidget()
                .setEnabledIf(widget -> (fuelConsumption.getIntValue() > 0 && fuelDuration.getIntValue() > 0));
    }
}