package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.simibubi.create.AllBlocks;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;
import net.terrafirmainfinity.core.common.machine.multiblock.BasicWorkableMachine;

import static net.terrafirmainfinity.core.InfinityCore.REGISTRATE;
import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;

public class SpiralSeparator {
    public static final MultiblockMachineDefinition SPIRAL_SEPARATOR = REGISTRATE
            .multiblock("spiral_separator", BasicWorkableMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.SPIRAL_SEPARATOR_RECIPE)
            .addOutputLimit(ItemRecipeCapability.CAP, 3)
            .addOutputLimit(FluidRecipeCapability.CAP, 2)
            .recipeModifiers(SpiralSeparator::recipeModifier)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                    .slice("CCC", "GFG", "GFG", "GFG", "G#G", "XXX")
                    .slice("CCC", "FPF", "FPF", "FPF", "#P#", "XXX")
                    .slice("CSC", "GFG", "GFG", "GFG", "G#G", "XXX")
                    .where('S', controller(blocks(definition.get())))
                    .where('C', blocks(GTBlocks.CASING_STEEL_SOLID.get())
                            .or(abilities(PartAbility.EXPORT_ITEMS, PartAbility.EXPORT_FLUIDS)))
                    .where('X', blocks(GTBlocks.CASING_STEEL_SOLID.get())
                            .or(abilities(PartAbility.IMPORT_ITEMS, PartAbility.IMPORT_FLUIDS)))
                    .where('G', blocks(AllBlocks.METAL_GIRDER.get()))
                    .where('F', frames(GTMaterials.Steel))
                    .where('P', blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                    .where('#', MultiPredicate.AIR)
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/ore_washer"))
            .register();

    public static ModifierFunction recipeModifier(MetaMachine machine, GTRecipe recipe) {
        int parallelAmount = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, 8);

        double parallelDuration = Math.min(1.0, ((float) parallelAmount) * 0.5);

        return ModifierFunction.builder().inputModifier(ContentModifier.multiplier(parallelAmount)).outputModifier(ContentModifier.multiplier(parallelAmount)).durationMultiplier(parallelDuration).parallels(parallelAmount).build();
    }

    public static void init() {}
}
