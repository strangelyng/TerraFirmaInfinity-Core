package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.api.blockentity.BlockEntityCreationInfo;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import com.gregtechceu.gtceu.api.machine.trait.recipe.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.ActionResult;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import net.terrafirmainfinity.core.common.machine.part.ElectrodeHolderPartMachine;
import org.jetbrains.annotations.Nullable;

public class ElectrodeMachine extends WorkableElectricMultiblockMachine {
    public ElectrodeMachine(BlockEntityCreationInfo info) {
        super(info, new ElectrodeMachineRecipeLogic());
    }

    private @Nullable ElectrodeHolderPartMachine getElectrodeHolder() {
        for (MultiblockPartMachine part : getParts()) {
            if (part instanceof ElectrodeHolderPartMachine electrodeHolder) {
                return electrodeHolder;
            }
        }
        return null;
    }

    public boolean hasElectrodeHolder() {
        var electrodeHolder = getElectrodeHolder();
        return electrodeHolder != null && electrodeHolder.hasElectrode();
    }

    public static class ElectrodeMachineRecipeLogic extends RecipeLogic {
        public ElectrodeMachineRecipeLogic() {
            super();
        }

        @Override
        public ElectrodeMachine getMachine() {
            return (ElectrodeMachine) super.getMachine();
        }

        // Check that electrode is still present removed before re-running a cached recipe
        @Override
        protected ActionResult checkRecipe(GTRecipe recipe) {
            if (!getMachine().hasElectrodeHolder()) {
                return ActionResult.FAIL_NO_REASON; // Add appropriate component message
            }
            return super.checkRecipe(recipe);
        }
    }
}
