package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static com.gregtechceu.gtceu.api.multiblock.Predicates.autoAbilities;
import static com.gregtechceu.gtceu.api.multiblock.Predicates.blocks;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class AdvancedArcFurnace {
    public static final MultiblockMachineDefinition ADVANCED_ARC_FURNACE = InfinityRegistrate
            .multiblock("advanced_arc_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.ADVANCED_ARC_FURNACE_RECIPE)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, GTRecipeModifiers.BATCH_MODE)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                    .slice(" CCC ", " CCC ", " FFF ", "     ")
                    .slice("CCCCC", "C#E#C", "F#E#F", " CEC ")
                    .slice("ECCCE", "E###E", "E###E", " CMC ")
                    .slice("CCCCC", "C###C", "F###F", " CCC ")
                    .slice(" CCC ", " CSC ", " FFF ", "     ")
                    .where('S', controller(blocks(definition.get())))
                    .where('C', blocks(GTBlocks.CASING_STEEL_SOLID.get()).setMinGlobalLimited(28)
                            .and(autoAbilities(definition.getRecipeTypes()))
                            .and(autoAbilities(true, false, false)))
                    .where('F', blocks(GTBlocks.FIREBOX_STEEL.get()))
                    .where('E', blocks(GCYMBlocks.ELECTROLYTIC_CELL.get())) // TODO: Placeholder, Electrode Casing?
                    .where('M', autoAbilities(false, true, false))
                    .where('#', MultiPredicate.AIR)
                    .where(' ', MultiPredicate.ANY)
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/machines/arc_furnace"))
            .register();

    public static void init() {}
}
