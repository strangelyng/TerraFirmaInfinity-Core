package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.*;
import net.minecraft.core.Direction;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static com.gregtechceu.gtceu.api.multiblock.Predicates.autoAbilities;
import static com.gregtechceu.gtceu.api.multiblock.Predicates.blocks;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class RotaryKiln {
    public static final MultiblockMachineDefinition ROTARY_KILN = InfinityRegistrate
            .multiblock("rotary_kiln", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.ROTARY_KILN_RECIPE)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, GTRecipeModifiers.BATCH_MODE)
            .appearanceBlock(InfinityBlocks.CASING_STEEL_BRICKS)
            .partAppearance((controller, part, side) -> {
                // TODO: Fix this?
                Direction facing = controller.getFrontFacing();
                Direction.Axis axis = facing.getAxis();

                int difference = controller.getBlockPos().relative(facing.getOpposite()).get(axis) - part.getBlockPos().get(axis);

                return Math.abs(difference) == 5 ? GTBlocks.MACHINE_CASING_LV.getDefaultState() : InfinityBlocks.CASING_STEEL_BRICKS.getDefaultState();

                // Sort of does what it's supposed to? It affects the correct blocks, but doesn't use the desired texture.
            })
            .pattern(definition -> MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                    .slice("F F", "CCC", "CCC", "CCC")
                    .slice("   ", "HHH", "H#H", "HHH")
                    .slice("   ", " B ", "B#B", " B ")
                    .slice("   ", " B ", "B#B", " B ")
                    .slice("   ", " B ", "B#B", " B ")
                    .slice("F F", "GGG", "G#G", "GGG")
                    .slice("   ", " B ", "B#B", " B ")
                    .slice("   ", " B ", "B#B", " B ")
                    .slice("   ", " B ", "B#B", " B ")
                    .slice("   ", "HHH", "H#H", "HHH")
                    .slice("F F", "CCC", "CSC", "CCC")
                    .where('S', controller(blocks(definition.get())))
                    .where('C', blocks(InfinityBlocks.CASING_STEEL_BRICKS.get())
                            .and(autoAbilities(new GTRecipeType[]{InfinityRecipeTypes.ROTARY_KILN_RECIPE}, false, false, true, true, true, true))
                            .and(autoAbilities(true, false, false)))
                    .where('B', blocks(InfinityBlocks.CASING_STEEL_BRICKS.get()))
                    .where('F', frames(GTMaterials.Steel))
                    .where('H', blocks(GTBlocks.STEEL_HULL.get()))
                    .where('G', blocks(GTBlocks.CASING_STEEL_GEARBOX.get())
                            .and(abilities(PartAbility.INPUT_ENERGY).setExactLimit(1)))
                    .where('#', MultiPredicate.AIR)
                    .where(' ', MultiPredicate.ANY)
                    .build())
            .workableCasingModel(InfinityCore.id("block/casings/solid/machine_casing_steel_plated_bricks"),
                    InfinityCore.id("block/machines/roaster"))
            .register();

    public static void init() {}
}
