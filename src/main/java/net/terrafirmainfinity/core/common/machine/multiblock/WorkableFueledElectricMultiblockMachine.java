package net.terrafirmainfinity.core.common.machine.multiblock;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.sync_system.annotations.SaveField;
import com.gregtechceu.gtceu.utils.GTUtil;
import net.minecraft.network.chat.Component;
import net.terrafirmainfinity.core.common.machine.part.FuelHatchPartMachine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WorkableFueledElectricMultiblockMachine extends WorkableElectricMultiblockMachine {
    @SaveField
    protected FuelHatchPartMachine fuelHatch;

    @SaveField private int fuelDuration;
    @SaveField private int fuelConsumption;

    public WorkableFueledElectricMultiblockMachine(BlockEntityCreationInfo info) {
        super(info);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        this.fuelHatch = null;
    }

    @Override
    public void formStructure(@NotNull String substructureName) {
        super.formStructure(substructureName);
        this.fuelHatch = getFuelPart();
    }

    @Override
    public void onPartUnload() {
        super.onPartUnload();
        this.fuelHatch = null;
    }

    private @Nullable FuelHatchPartMachine getFuelPart() {
        for (MultiblockPartMachine part : getParts()) {
            if (part instanceof FuelHatchPartMachine fuelHatch) {
                return fuelHatch;
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
        if (fuelHatch.isReadyForRecipe()) {
            int tier = GTUtil.getTierByVoltage(recipe.getInputEUt().voltage());

            double multiplier = tier > 0 ? tier : 0.5;

            fuelDuration = (int) ((double) fuelHatch.getCachedFuelDuration() / multiplier);
            fuelConsumption = (int) ((double) fuelHatch.getCachedFuelConsumption() * multiplier);
            return true;
        } else {
            RecipeLogic.putFailureReason(this, recipe, Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
            return false;
        }
    }

    @Override
    public boolean onWorking() {
        if (!fuelHatch.isReadyForRecipe() || !fuelHatch.consumeFuel(fuelConsumption, true)) {
            recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));
        } else {
            if (getOffsetTimer() % fuelDuration == 0) {
                fuelHatch.consumeFuel(fuelConsumption, false);
            }
        }
        return super.onWorking();
    }
}
