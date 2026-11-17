package net.terrafirmainfinity.core.common.machine.multiblock.multi;

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
import com.gregtechceu.gtceu.api.registry.registrate.entry.MachineEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;
import net.terrafirmainfinity.core.common.machine.multiblock.WorkableFueledElectricMultiblockMachine;
import net.terrafirmainfinity.core.common.machine.part.InfinityPartAbility;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

/**
 * TODO: FuelInputHatch/FuelInputBus machine part for different types
 * Coal-fueled: Coal/Anthracite
 * Oil-fueled: Crude Oil/Diesel/etc.
 * Gas-fueled: Coal Gas/LPG/Natural Gas
 */
public class RotaryKiln {
    public static final MachineEntry<MultiblockMachineDefinition> ROTARY_KILN = InfinityRegistrate
            .multiblock("rotary_kiln", WorkableFueledElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.ROTARY_KILN_RECIPES)
            .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, RotaryKiln::parallelProcessing)
            .appearanceBlock(InfinityBlocks.CASING_STEEL_BRICKS)
            .pattern(definition -> {
                MultiPredicate casingPredicate = blocks(InfinityBlocks.CASING_STEEL_BRICKS.get());
                MultiPredicate maintenance = autoAbilities(true, false, false).setExactLimit(1);
                MultiPredicate energyPredicate = abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2);
                return MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                        .slice("F F", "OOO", "OOO", "OOO")
                        .slice("   ", "CCC", "C#C", "CCC")
                        .slice("   ", " B ", "B#B", " B ")
                        .slice("   ", " B ", "B#B", " B ")
                        .slice("   ", " B ", "B#B", " B ")
                        .slice("F F", "GGG", "G#G", "GGG")
                        .slice("   ", " B ", "B#B", " B ")
                        .slice("   ", " B ", "B#B", " B ")
                        .slice("   ", " B ", "B#B", " B ")
                        .slice("   ", "CCC", "C#C", "CCC")
                        .slice("F F", "III", "ISI", "III")
                        .where('S', controller(definition))
                        .where('F', frames(GTMaterials.Steel))
                        .where('I', casingPredicate
                                .and(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                                .and(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(2))
                                .and(abilities(InfinityPartAbility.FUEL_HATCH).setMaxGlobalLimited(1))
                                .and(maintenance)
                                .and(energyPredicate))
                        .where('O', casingPredicate
                                .and(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2))
                                .and(abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(2))
                                .and(abilities(InfinityPartAbility.FUEL_HATCH).setMaxGlobalLimited(1))
                                .and(maintenance)
                                .and(energyPredicate))
                        .where('C', blocks(GTBlocks.STEEL_HULL.get()))
                        .where('B', casingPredicate)
                        .where('G', blocks(GTBlocks.CASING_STEEL_GEARBOX.get()))
                        .where('#', air())
                        .build();
            })
            .workableCasingModel(InfinityCore.id("block/casings/solid/machine_casing_steel_plated_bricks"),
                    InfinityCore.id("block/machines/roaster"))
            .register();

    private static ModifierFunction parallelProcessing(MetaMachine machine, GTRecipe recipe) {
        int parallelAmount = ParallelLogic.getParallelAmountWithoutEU(machine, recipe, 4);

        int parallelMult = Math.max(0, parallelAmount - 1);

        double parallelDuration = 1.0d + (((float) parallelMult) * 0.5);

        return ModifierFunction.builder().inputModifier(ContentModifier.multiplier(parallelAmount)).tickInputModifier(ContentModifier.multiplier(1.0d / parallelAmount)).outputModifier(ContentModifier.multiplier(parallelAmount)).durationMultiplier(parallelDuration).parallels(parallelAmount).build();
    }

    public static void init() {}
}
