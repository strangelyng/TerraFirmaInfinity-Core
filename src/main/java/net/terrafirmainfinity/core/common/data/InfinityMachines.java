package net.terrafirmainfinity.core.common.data;

import brachy.modularui.utils.FormattingUtil;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;
import com.gregtechceu.gtceu.common.item.QuantumTankMachineItem;
import com.gregtechceu.gtceu.common.machine.storage.QuantumChestMachine;
import com.gregtechceu.gtceu.common.machine.storage.QuantumTankMachine;
import com.gregtechceu.gtceu.common.mui.GTGuiTheme;
import com.gregtechceu.gtceu.common.mui.GTSingleblockMachinePanels;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidType;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityMachines {
    static {
        InfinityRegistrate.creativeModeTab(() -> InfinityCore.INFINITY_CREATIVE_TAB);
    }

    // Additional Bronze Containers
    public static final MachineDefinition BISMUTH_BRONZE_CRATE = GTMachineUtils.registerCrate(InfinityRegistrate, GTMaterials.BismuthBronze, 54, 9, "Bismuth Bronze Crate");
    public static final MachineDefinition BLACK_BRONZE_CRATE = GTMachineUtils.registerCrate(InfinityRegistrate, GTMaterials.BlackBronze, 54, 9, "Black Bronze Crate");

    public static final MachineDefinition BISMUTH_BRONZE_DRUM = GTMachineUtils.registerDrum(InfinityRegistrate, GTMaterials.BismuthBronze, (32 * FluidType.BUCKET_VOLUME), "Bismuth Bronze Drum");
    public static final MachineDefinition BLACK_BRONZE_DRUM = GTMachineUtils.registerDrum(InfinityRegistrate, GTMaterials.BlackBronze, (32 * FluidType.BUCKET_VOLUME), "Black Bronze Drum");

    // ULV Quantum Containers
    public static final MachineDefinition ULV_SUPER_CHEST = InfinityRegistrate.machine("ulv_super_chest",
            (holder) -> new QuantumChestMachine(holder, GTValues.ULV, 10000L))
            .langValue("Basic Super Chest")
            .blockProp(Block.Properties::dynamicShape)
            .rotationState(RotationState.ALL)
            .allowExtendedFacing(true)
            .model(GTMachineModels.createTieredHullMachineModel(GTCEu.id("block/machine/template/quantum/quantum_chest"))
                    .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createQuantumChestRender)))
            .hasBER(true)
            .tooltipBuilder(CHEST_TOOLTIPS)
            .tooltips(Component.translatable("gtceu.machine.quantum_chest.tooltip"),
                    Component.translatable("gtceu.universal.tooltip.item_storage_total",
                            FormattingUtil.formatNumbers(10000)))
            .tier(GTValues.ULV)
            .register();

    public static final MachineDefinition ULV_SUPER_TANK = registerULVSuperTank();

    private static MachineDefinition registerULVSuperTank() {
        long maxAmount = 500 * FluidType.BUCKET_VOLUME;
        MachineDefinition definition = InfinityRegistrate.machine("ulv_super_tank",
                        MachineDefinition::new, MetaMachineBlock::new, QuantumTankMachineItem::new, holder -> new QuantumTankMachine(holder, GTValues.ULV, maxAmount))
                .langValue("Basic Super Tank")
                .blockProp(Block.Properties::dynamicShape)
                .rotationState(RotationState.ALL)
                .allowExtendedFacing(true)
                .model(GTMachineModels.createTieredHullMachineModel(GTCEu.id("block/machine/template/quantum/quantum_tank"))
                        .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createQuantumTankRender)))
                .hasBER(true)
                .tooltipBuilder(TANK_TOOLTIPS)
                .tooltips(Component.translatable("gtceu.machine.quantum_tank.tooltip"),
                        Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity",
                                FormattingUtil.formatNumbers(maxAmount)))
                .tier(GTValues.ULV)
                .register();

        QuantumTankMachine.TANK_CAPACITY.put(definition, maxAmount);
        return definition;
    }

    // Custom Machines
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_ROASTER = registerSteamMachines(InfinityRegistrate, "steam_roaster",
            SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(InfinityRecipeTypes.ROASTER_RECIPE)
                    .ui(GTSingleblockMachinePanels.GENERAL_MACHINE)
                    .themeId(i -> i > 0 ? GTGuiTheme.STEEL.getId() : GTGuiTheme.BRONZE.getId())
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .addOutputLimit(FluidRecipeCapability.CAP, 0)
                    .modelProperty(GTMachineModelProperties.VENT_DIRECTION, RelativeDirection.BACK)
                    .workableSteamHullModel(pressure, InfinityCore.id("block/machines/roaster"))
                    .tooltipBuilder((stack, list) -> {
                        list.add(Component.translatable("tfinfinity.machine.roaster.tooltip"));
                    })
                    .register());

    public static final MachineDefinition[] ROASTER = registerTieredMachines(InfinityRegistrate, "roaster",
            SimpleTieredMachine::new, (tier, builder) -> builder
                    .langValue("%s Roaster %s".formatted(VLVH[tier], VLVT[tier]))
                    .rotationState(RotationState.NON_Y_AXIS)
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
