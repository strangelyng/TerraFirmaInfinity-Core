package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.common.mui.GTGuiTheme;
import com.gregtechceu.gtceu.common.mui.GTSingleblockMachinePanels;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.chat.Component;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.GTValues.VLVH;
import static com.gregtechceu.gtceu.api.GTValues.VLVT;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static net.terrafirmainfinity.core.InfinityCore.REGISTRATE;

public class InfinityMachines {
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_ROASTER = registerSteamMachines(REGISTRATE, "steam_roaster",
            SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(InfinityRecipeTypes.ROASTER_RECIPE)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(FluidRecipeCapability.CAP, 0)
                    .themeId((i) -> i > 0 ? GTGuiTheme.STEEL.getId() : GTGuiTheme.BRONZE.getId())
                    .ui(GTSingleblockMachinePanels.GENERAL_MACHINE)
                    .modelProperty(GTMachineModelProperties.VENT_DIRECTION, RelativeDirection.BACK)
                    .workableSteamHullModel(pressure, InfinityCore.id("block/machines/roaster"))
                    .tooltipBuilder((stack, list) -> {
                        list.add(Component.translatable("tfinfinity.machine.roaster.tooltip"));
                    })
                    .register());

    public static final MachineDefinition[] ROASTER = registerTieredMachines(REGISTRATE, "roaster",
            SimpleTieredMachine::new, (tier, builder) -> builder
                    .langValue("%s Roaster %s".formatted(VLVH[tier], VLVT[tier]))
                    .recipeType(InfinityRecipeTypes.ROASTER_RECIPE)
                    .ui(GTSingleblockMachinePanels.GENERAL_MACHINE)
                    .workableTieredHullModel(InfinityCore.id("block/machines/roaster"))
                    .tooltipBuilder((stack, list) -> {
                        list.add(Component.translatable("tfinfinity.machine.roaster.tooltip"));
                    })
                    .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, InfinityRecipeTypes.ROASTER_RECIPE,
                            defaultTankSizeFunction.applyAsInt(tier), true))
                    .register(),
            ELECTRIC_TIERS);

    public static void init() {}
}
