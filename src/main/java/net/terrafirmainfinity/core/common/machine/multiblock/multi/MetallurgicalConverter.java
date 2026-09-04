package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class MetallurgicalConverter {
    public static final MultiblockMachineDefinition METALLURGICAL_CONVERTER = InfinityRegistrate
            .multiblock("metallurgical_converter", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.METALLURGICAL_CONVERTER_RECIPE)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, MetallurgicalConverter::recipeModifier)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                    .slice(" C C ", " C C ", "     ", "     ", "     ", "     ", "     ")
                    .slice("FCCCF", "FF FF", "F   F", "FCCCF", " BBB ", " CCC ", "     ")
                    .slice(" CCC ", " FPF ", "  P  ", "CC#CC", "GB#BG", " C#C ", "  M  ")
                    .slice("FCCCF", "FFFFF", "F   F", "FCCCF", " BBB ", " CCC ", "     ")
                    .slice(" CSC ", " CCC ", "     ", "     ", "     ", "     ", "     ")
                    .where('S', controller(blocks(definition.get())))
                    .where('C', blocks(GTBlocks.CASING_STEEL_SOLID.get())
                            .and(autoAbilities(definition.getRecipeTypes()))
                            .and(autoAbilities(true, false, false)))
                    .where('F', frames(GTMaterials.Steel))
                    .where('P', blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                    .where('B', blocks(GTBlocks.FIREBOX_STEEL.get()))
                    .where('G', blocks(GTBlocks.CASING_STEEL_GEARBOX.get()))
                    .where('M', abilities(PartAbility.MUFFLER))
                    .where('#', MultiPredicate.AIR)
                    .where(' ', MultiPredicate.ANY)
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/blast_furnace"))
            .register();

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        int parallelAmount = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, 16);

        double parallelDuration = Math.min(1.0, ((float) parallelAmount) * 0.75);

        return ModifierFunction.builder().inputModifier(ContentModifier.multiplier(parallelAmount)).outputModifier(ContentModifier.multiplier(parallelAmount)).durationMultiplier(parallelDuration).parallels(parallelAmount).build();
    }

    public static void init() {}

}
