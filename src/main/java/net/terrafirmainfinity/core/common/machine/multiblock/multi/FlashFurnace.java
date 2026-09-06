package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;

import java.util.Comparator;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class FlashFurnace {
    /**
     * More Intuitive Slices
     *  return MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
     *      .slice(" BBBBB", " ICCCC", " I CCC", " I MMM", " I    ")
     *      .slice("BBBBBB", "IPPPPC", "IPICPC", "IPIMMM", "IPI   ")
     *      .slice(" BBBBB", " ICCCC", " I CSC", " I MMM", " I    ")
     */

    // TODO: Distillation Tower Style Recipe Logic for fluid output layers

    public static final MultiblockMachineDefinition FLASH_FURNACE = InfinityRegistrate
            .multiblock("flash_furnace", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.FLASH_SMELTING_RECIPE)
            .appearanceBlock(GTBlocks.CASING_INVAR_HEATPROOF)
            .pattern(definition -> {
                MultiPredicate casingPredicate = blocks(GTBlocks.CASING_INVAR_HEATPROOF.get());
                MultiPredicate exportPredicate = abilities(PartAbility.EXPORT_FLUIDS_1X);
                exportPredicate = exportPredicate.setMaxLayerLimited(1).setMinGlobalLimited(2);
                MultiPredicate maintenance = autoAbilities(true, false, false)
                        .setMaxGlobalLimited(1);
                MultiPredicate muffler = autoAbilities(false, true, false)
                        .setMaxGlobalLimited(1);
                MultiPredicate energyPredicate = abilities(PartAbility.INPUT_ENERGY);
                energyPredicate = energyPredicate.setMinGlobalLimited(1).setMaxGlobalLimited(2);
                return MultiblockPatternBuilder.start(RelativeDirection.UP, RelativeDirection.BACK, RelativeDirection.RIGHT)
                        .slice(" BBBBB", "BBBBBB", " BBBBB")
                        .slice(" ICCCC", "IPPPPC", " ICCCC")
                        .slice(" I CSC", "IPICPC", " I CCC")
                        .slice(" I MMM", "IPIMMM", " I MMM")
                        .slice(" I    ", "IPI   ", " I    ")
                        .where('S', controller(blocks(definition.get())))
                        .where('B', blocks(GTBlocks.FIREBOX_STEEL.get()))
                        .where('I', casingPredicate
                                .and(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(2))
                                .and(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2)))
                        .where('C', casingPredicate
                                .and(energyPredicate)
                                .and(exportPredicate)
                                .and(maintenance))
                        .where('M', casingPredicate.and(
                                maintenance.and(
                                    muffler.xor(
                                            exportPredicate
                                    ).setMinLayerLimited(1)
                                )))
                        .where('P', blocks(GTBlocks.CASING_STEEL_PIPE.get()))
                        .where(' ', MultiPredicate.ANY)
                        .build();
            })
            .partSorter(Comparator.comparingInt(p -> p.getBlockPos().getY()))
            .workableCasingModel(GTCEu.id("block/casings/solid/machine_casing_heatproof"),
                    GTCEu.id("block/multiblock/blast_furnace"))
            .register();

    public static void init() {}
}
