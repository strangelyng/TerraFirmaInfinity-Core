package net.terrafirmainfinity.core.common.machine.multiblock;

import brachy.modularui.api.drawable.Text;
import brachy.modularui.api.widget.IWidget;
import brachy.modularui.value.sync.BooleanSyncValue;
import brachy.modularui.value.sync.IntSyncValue;
import brachy.modularui.value.sync.PanelSyncManager;
import brachy.modularui.widgets.TextWidget;
import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.terrafirmainfinity.core.common.machine.part.FuelBusPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelHatchPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelPartMachine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

// TODO: Fix burnTime reset on reload
public class WorkableFueledElectricMultiblockMachine extends WorkableElectricMultiblockMachine {
    @Nullable
    @SaveField
    protected FuelPartMachine fuelInput;

    protected TickableSubscription burnTimeSubs;

    @Nullable
    @SaveField
    private Item fuelItem;

    @Nullable
    @SaveField
    private Fluid fuelFluid;

    @SaveField
    private int fuelDuration = 0;

    @SaveField
    private int fuelConsumption = 0;

    @SaveField
    private int burnTime = 0;

    @SaveField private boolean liquidFueled = false;

    public WorkableFueledElectricMultiblockMachine(BlockEntityCreationInfo info) {
        super(info);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.fuelInput = null;
        this.burnTime = 0;
    }

    @Override
    public void formStructure(@NotNull String substructureName) {
        super.formStructure(substructureName);
        this.fuelInput = getFuelInput();
        liquidFueled = this.fuelInput instanceof FuelHatchPartMachine;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        scheduleForNextServerTick(this::updateBurnTimeSubscription);
    }

    @Override
    public void onUnload() {
        super.onUnload();
        if (burnTimeSubs != null) {
            burnTimeSubs.unsubscribe();
            burnTimeSubs = null;
        }
    }

    @Override
    public void onPartUnload() {
        super.onPartUnload();
        this.fuelInput = null;
//        this.burnTime = 0;
    }

    protected void updateBurnTimeSubscription() {
        if (burnTime > 0) {
            burnTimeSubs = subscribeServerTick(burnTimeSubs, this::updateBurnTime);
        } else if (burnTimeSubs != null) {
            burnTimeSubs.unsubscribe();
            burnTimeSubs = null;
        }
    }

    protected void updateBurnTime() {
        burnTime--;
        updateBurnTimeSubscription();
    }

    protected void addBurnTime(int fuelDuration) {
        burnTime += fuelDuration;
        updateBurnTimeSubscription();
    }

    private @Nullable FuelPartMachine getFuelInput() {
        for (MultiblockPartMachine part : getParts()) {
            if (part instanceof FuelPartMachine fuelPart) {
                return fuelPart;
            }
        }
        return null;
    }

    @Override
    public boolean beforeWorking(@Nullable GTRecipe recipe) {
        if (!super.beforeWorking(recipe)) {
            return false;
        }
        if (recipe == null) {
            return false;
        }
        if (burnTime > 0) {
            return true;
        } else if (fuelInput.isReadyForRecipe()) {
            refreshFuelData(recipe);

            if (fuelInput.consumeFuel(fuelConsumption, true)) {
                addBurnTime(fuelDuration);
                fuelInput.consumeFuel(fuelConsumption, false);
                return true;
            }  else {
                RecipeLogic.putFailureReason(this, recipe, Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onWorking() {
        GTRecipe recipe = recipeLogic.getLastUnrolledRecipe();
        assert recipe != null;

        boolean value = super.onWorking();

        if (burnTime <= 0) {
            if (!fuelInput.isReadyForRecipe() || !fuelInput.consumeFuel(fuelConsumption, true)) {
                recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
            } else {
                if (fuelInput instanceof FuelHatchPartMachine fuelHatch) {
                    if (fuelFluid != fuelHatch.getCachedFuelFluid()) {
                        refreshFuelData(recipe);
                    }
                } else if (fuelInput instanceof FuelBusPartMachine fuelBus) {
                    if (fuelItem != fuelBus.getCachedFuelItem()) {
                        refreshFuelData(recipe);
                    }
                }
                addBurnTime(fuelDuration);
                fuelInput.consumeFuel(fuelConsumption, false);
            }
        }
        return value;
    }

    public void refreshFuelData(GTRecipe recipe) {
        int tier = GTUtil.getTierByVoltage(recipe.getInputEUt().voltage());

        double multiplier = tier > 0 ? tier : 0.5;

        // Fluid Fuels
        if (fuelInput instanceof FuelHatchPartMachine fuelHatch) {
            fuelFluid = fuelHatch.getFuelFluidStack().getFluid();
            fuelDuration = (int) ((double) fuelHatch.getCachedFuelDuration() / multiplier);
            fuelConsumption = (int) ((double) fuelHatch.getCachedFuelConsumption() * multiplier);
        }

        // Item Fuels
        if (fuelInput instanceof FuelBusPartMachine fuelBus) {
            fuelItem = fuelBus.getFuelItemStack().getItem();
            fuelDuration = (int) ((double) fuelBus.getCachedFuelDuration() / multiplier);
            fuelConsumption = (int) ((double) fuelBus.getCachedFuelConsumption() * multiplier); // TODO: Do I want this
        }
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

    @Override
    public List<IWidget> getWidgetsForDisplay(PanelSyncManager syncManager) {
        BooleanSyncValue liquidFueled = new BooleanSyncValue(this::isLiquidFueled);
        syncManager.syncValue("liquidFueled", liquidFueled);
        IntSyncValue burnTime = new IntSyncValue(this::getBurnTime);
        syncManager.syncValue("burnTime", burnTime);
        IntSyncValue fuelDuration = new IntSyncValue(this::getFuelDuration);
        syncManager.syncValue("fuelDuration", fuelDuration);
        IntSyncValue fuelConsumption = new IntSyncValue(this::getFuelConsumption);
        syncManager.syncValue("fuelConsumption", fuelConsumption);
        List<IWidget> widgets = new ArrayList<>();
        widgets.add(addBurnTimeLine(this, syncManager));
        widgets.add(addFuelRateLine(this, syncManager));
        widgets.addAll(super.getWidgetsForDisplay(syncManager));
        return widgets;
    }

    private static TextWidget<?> addBurnTimeLine(WorkableFueledElectricMultiblockMachine fueledMachine,
                                                PanelSyncManager syncManager) {
        BooleanSyncValue liquidFueled = syncManager.getOrCreateSyncHandler("liquidFueled", BooleanSyncValue.class,
                () -> new BooleanSyncValue(fueledMachine::isLiquidFueled));
        IntSyncValue burnTime = syncManager.getOrCreateSyncHandler("burnTime", IntSyncValue.class,
                () -> new IntSyncValue(fueledMachine::getBurnTime));
        return Text.dynamic(() -> {
                    Component time = Component.literal(FormattingUtil.formatTime(burnTime.getIntValue()))
                            .withStyle(ChatFormatting.YELLOW);
                    return Component.translatable("tfinfinity.multiblock.burn_time", time)
                            .withStyle(ChatFormatting.GRAY);
                }).asWidget()
                .setEnabledIf(widget -> !liquidFueled.getBoolValue() && burnTime.getIntValue() > 0);
    }

    private static TextWidget<?> addFuelRateLine(WorkableFueledElectricMultiblockMachine fueledMachine,
                                                PanelSyncManager syncManager) {
        BooleanSyncValue liquidFueled = syncManager.getOrCreateSyncHandler("liquidFueled", BooleanSyncValue.class,
                () -> new BooleanSyncValue(fueledMachine::isLiquidFueled));
        IntSyncValue fuelDuration = syncManager.getOrCreateSyncHandler("fuelDuration", IntSyncValue.class,
                () -> new IntSyncValue(fueledMachine::getFuelDuration));
        IntSyncValue fuelConsumption = syncManager.getOrCreateSyncHandler("fuelConsumption", IntSyncValue.class,
                () -> new IntSyncValue(fueledMachine::getFuelConsumption));
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
