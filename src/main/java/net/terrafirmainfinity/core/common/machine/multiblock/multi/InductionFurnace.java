package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.simibubi.create.AllBlocks;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

// ADD STEAM BOILER HEAT LOGIC, REQUIRE DISTILLED WATER FOR COOLING OR COILS WILL BE DAMAGED, ADDING WATER TO HOT BOILER WITH EXPLODE
public class InductionFurnace {
    public static final MultiblockMachineDefinition INDUCTION_FURNACE = InfinityRegistrate
            .multiblock("induction_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.INDUCTION_FURNACE_RECIPE)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, GTRecipeModifiers.BATCH_MODE)
            .appearanceBlock(GTBlocks.CASING_STEEL_SOLID)
            .pattern(definition -> MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                    .slice(" CCC ", " G G ", " CCC ")
                    .slice("CCCCC", "GXXXG", "CCCCC")
                    .slice("CCCCC", " XUX ", "CCCCC")
                    .slice("CCCCC", "GXXXG", "CCCCC")
                    .slice(" CSC ", " G G ", " CCC ")
                    .where('S', controller(blocks(definition.get())))
                    .where('C', blocks(GTBlocks.CASING_STEEL_SOLID.get()).setMinGlobalLimited(33)
                            .and(autoAbilities(definition.getRecipeTypes()))
                            .and(autoAbilities(true, false, false)))
                    .where('G', blocks(AllBlocks.METAL_GIRDER.get()))
                    .where('X', heatingCoils()) // TODO: Placeholder, Induction Furnace Coil Casing?
                    .where('U', blocks(TFCBlocks.CRUCIBLE))
                    .where(' ', MultiPredicate.ANY)
                    .build())
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_solid_steel"),
                    GTCEu.id("block/multiblock/blast_furnace"))
            .register();

    public static void init() {}
}
