package net.terrafirmainfinity.core.common.machine.multiblock.multi;

import brachy.modularui.api.drawable.Text;
import brachy.modularui.value.sync.BooleanSyncValue;
import brachy.modularui.value.sync.IntSyncValue;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.multiblock.MultiPredicate;
import com.gregtechceu.gtceu.api.multiblock.pattern.MultiblockPatternBuilder;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.api.registry.registrate.entry.MachineEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.network.chat.Component;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.data.InfinityRecipeTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

import static com.gregtechceu.gtceu.api.multiblock.Predicates.*;
import static com.gregtechceu.gtceu.api.recipe.OverclockingLogic.NON_PERFECT_OVERCLOCK_SUBTICK;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class ElectricRotaryKiln {
    public static final MachineEntry<MultiblockMachineDefinition> ELECTRIC_ROTARY_KILN = InfinityRegistrate
            .multiblock("electric_rotary_kiln", CoilWorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(InfinityRecipeTypes.ELECTRIC_ROTARY_KILN_RECIPES)
            .recipeModifiers(ElectricRotaryKiln::electricRotaryKilnOC)
            .appearanceBlock(InfinityBlocks.CASING_STEEL_BRICKS)
            .pattern(definition -> {
                MultiPredicate casingPredicate = blocks(InfinityBlocks.CASING_STEEL_BRICKS.get());
                MultiPredicate maintenance = autoAbilities(true, false, false).setExactLimit(1);
                MultiPredicate energyPredicate = abilities(PartAbility.INPUT_ENERGY).setMaxGlobalLimited(2);
                return MultiblockPatternBuilder.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                        .slice("F F", "OOO", "OOO", "OOO")
                        .slice("   ", "CCC", "CPC", "CCC")
                        .slice("   ", "BXB", "XPX", "BXB")
                        .slice("   ", "BXB", "XPX", "BXB")
                        .slice("   ", "BXB", "XPX", "BXB")
                        .slice("F F", "GGG", "GPG", "GGG")
                        .slice("   ", "BXB", "XPX", "BXB")
                        .slice("   ", "BXB", "XPX", "BXB")
                        .slice("   ", "BXB", "XPX", "BXB")
                        .slice("   ", "CCC", "CPC", "CCC")
                        .slice("F F", "III", "ISI", "III")
                        .where('S', controller(definition))
                        .where('F', frames(GTMaterials.Steel))
                        .where('I', casingPredicate
                                .and(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(2))
                                .and(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(2))
                                .and(maintenance)
                                .and(energyPredicate))
                        .where('O', casingPredicate
                                .and(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(2))
                                .and(abilities(PartAbility.EXPORT_FLUIDS).setMaxGlobalLimited(2))
                                .and(maintenance)
                                .and(energyPredicate))
                        .where('C', blocks(GTBlocks.STEEL_HULL.get()))
                        .where('B', casingPredicate)
                        .where('G', blocks(GTBlocks.CASING_STEEL_GEARBOX.get()))
                        .where('#', air())
                        .where('P', blocks(InfinityBlocks.CASING_STAINLESS_STEEL_PIPE.get()))
                        .where('X', heatingCoils())
                        .build();
            })
            .workableCasingModel(InfinityCore.id("block/casings/solid/machine_casing_steel_plated_bricks"),
                    GTCEu.id("block/multiblock/blast_furnace"))
            .tooltips(Component.translatable("tfinfinity.machine.electric_rotary_kiln.tooltip.0"),
                    Component.translatable("tfinfinity.machine.electric_rotary_kiln.tooltip.1"))
            .additionalDisplay((controller, syncManager) -> {
                if (!(controller instanceof CoilWorkableElectricMultiblockMachine coilMachine))
                    return Collections.emptyList();
                BooleanSyncValue isFormed = syncManager.getOrCreateSyncHandler("isFormed", BooleanSyncValue.class,
                        () -> new BooleanSyncValue(controller::isFormed));
                IntSyncValue coilTier = syncManager.getOrCreateSyncHandler("coilTier", IntSyncValue.class,
                        () -> new IntSyncValue(() -> coilMachine.getCoilTier()));

                return Collections.singletonList(Text
                        .dynamic(() -> Component.translatable("gtceu.multiblock.pyrolyse_oven.speed",
                                coilTier.getIntValue() == 0 ? 75 : 50 * (coilTier.getIntValue() + 1)))
                        .asWidget().setEnabledIf(w -> isFormed.getBoolValue()));
            })
            .register();

    public static @NotNull ModifierFunction electricRotaryKilnOC(@NotNull MetaMachine machine,
                                                                  @NotNull GTRecipe recipe) {
        if (!(machine instanceof CoilWorkableElectricMultiblockMachine coilMachine)) {
            return RecipeModifier.nullWrongType(CoilWorkableElectricMultiblockMachine.class, machine);
        }
        if (RecipeHelper.getRecipeEUtTier(recipe) > coilMachine.getTier()) return ModifierFunction.NULL;

        int tier = coilMachine.getCoilTier();
        double durationMultiplier = (tier == 0) ? (4.0 / 3.0) : (2.0 / (tier + 1)); // 75% speed with cupro coils
        var durationModifier = ModifierFunction.builder()
                .durationMultiplier(durationMultiplier)
                .build();

        var oc = NON_PERFECT_OVERCLOCK_SUBTICK.getModifier(machine, recipe, coilMachine.getOverclockVoltage());
        return durationModifier.andThen(oc);
    }

    public static void init() {}
}
