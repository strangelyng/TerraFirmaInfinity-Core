package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import net.minecraft.network.chat.Component;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static com.gregtechceu.gtceu.common.mui.GTSingleblockMachinePanels.*;
import static net.terrafirmainfinity.core.InfinityCore.REGISTRATE;

public class InfinityMachines {
    public static final MachineDefinition[] HPHT_VACUUM_PRESS = registerTieredMachines(REGISTRATE, "hpht_vacuum_press",
            (holder, tier) -> new SimpleTieredMachine(holder, tier, defaultTankSizeFunction),
            (tier, builder) -> builder.recipeType(InfinityRecipeTypes.HPHT_VACUUM_PRESS_RECIPE)
                    .ui(GENERAL_MACHINE)
                    .tooltipBuilder((stack, list) -> {
                        list.add(Component.translatable("tfi.machine.hpht_vacuum_press.tooltip"));
                    })
                    .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, InfinityRecipeTypes.HPHT_VACUUM_PRESS_RECIPE,
                            defaultTankSizeFunction.applyAsInt(tier), true))
                    .workableTieredHullModel(InfinityCore.id("block/overlay/machine/hpht_vacuum_press"))
                    .register(),
            ELECTRIC_TIERS);

    public static void init() {}
}
