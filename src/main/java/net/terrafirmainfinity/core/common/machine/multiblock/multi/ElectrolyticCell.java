package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

// TODO: Disable GCYM LARGE_ELECTROLYZER
public class ElectrolyticCell {
    public static final MultiblockMachineDefinition ELECTROLYTIC_CELL = InfinityRegistrate
            .multiblock("electrolytic_cell", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(InfinityRecipeTypes.ELECTROLYTIC_CELL_RECIPE)
            .recipeModifiers(GTRecipeModifiers.OC_PERFECT_SUBTICK, GTRecipeModifiers.BATCH_MODE)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                    .slice("CCCCC", "CCCCC", "CCCCC")
                    .slice("CCCCC", "CPPPC", "CPPPC")
                    .slice("CCCCC", "CPPPC", "CPPPC")
                    .slice("CCCCC", "CCSCC", "CCCCC")
                    .where('S', controller(blocks(definition.get())))
                    .where('C', blocks(GTBlocks.CASING_STEEL_SOLID.get()).setMinGlobalLimited(33)
                            .and(autoAbilities(definition.getRecipeTypes()))
                            .and(autoAbilities(true, false, false)))
                    .where('P', blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                GTCEu.id("block/machines/electrolyzer"))
            .register();

    public static void init() {}
}
