package net.terrafirmainfinity.core.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.utils.GTUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.terrafirmainfinity.core.common.machine.part.FuelBusPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelHatchPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelPartMachine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorkableFueledElectricMultiblockMachine extends WorkableElectricMultiblockMachine {
    @SaveField
    protected FuelPartMachine fuelInput;

    protected TickableSubscription burnTimeSubs;

    @SaveField private Item fuelItem;
    @SaveField private Fluid fuelFluid;
    @SaveField private int fuelDuration;
    @SaveField private int fuelConsumption;
    @SaveField private int burnTime = 0;

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
        this.burnTime = 0;
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
}
