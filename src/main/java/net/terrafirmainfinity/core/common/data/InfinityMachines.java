package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.multiblock.util.RelativeDirection;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.entry.MachineEntry;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.gregtechceu.gtceu.common.machine.storage.QuantumChestMachine;
import com.gregtechceu.gtceu.common.machine.storage.QuantumTankMachine;
import com.gregtechceu.gtceu.common.mui.GTGuiTheme;
import com.gregtechceu.gtceu.common.mui.GTSingleblockMachinePanels;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.machine.part.ElectrodeHolderPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelBusPartMachine;
import net.terrafirmainfinity.core.common.machine.part.FuelHatchPartMachine;
import net.terrafirmainfinity.core.common.machine.part.InfinityPartAbility;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityMachines {
    static {
        InfinityRegistrate.creativeModeTab(() -> InfinityCore.INFINITY_CREATIVE_TAB);
    }

    // Additional Bronze Containers
    public static final MachineEntry<MachineDefinition> BISMUTH_BRONZE_CRATE = GTMachineUtils.registerCrate(InfinityRegistrate, GTMaterials.BismuthBronze, 54, 9, "Bismuth Bronze Crate");
    public static final MachineEntry<MachineDefinition> BLACK_BRONZE_CRATE = GTMachineUtils.registerCrate(InfinityRegistrate, GTMaterials.BlackBronze, 54, 9, "Black Bronze Crate");

    public static final MachineEntry<MachineDefinition> BISMUTH_BRONZE_DRUM = GTMachineUtils.registerDrum(InfinityRegistrate, GTMaterials.BismuthBronze, (32 * FluidType.BUCKET_VOLUME), "Bismuth Bronze Drum");
    public static final MachineEntry<MachineDefinition> BLACK_BRONZE_DRUM = GTMachineUtils.registerDrum(InfinityRegistrate, GTMaterials.BlackBronze, (32 * FluidType.BUCKET_VOLUME), "Black Bronze Drum");

    // ULV Quantum Containers
    public static final MachineEntry<MachineDefinition> ULV_SUPER_CHEST = InfinityRegistrate.machine("ulv_super_chest",
            (holder) -> new QuantumChestMachine(holder, GTValues.ULV, 10000L))
            .langValue("Basic Super Chest")
            .rotationState(RotationState.ALL)
            .allowExtendedFacing(true)
            .tooltipBuilder(CHEST_TOOLTIPS)
            .tooltips(Component.translatable("gtceu.machine.quantum_chest.tooltip"),
                    Component.translatable("gtceu.universal.tooltip.item_storage_total",
                            FormattingUtil.formatNumbers(10000)))
            .model(GTMachineModels.createTieredHullMachineModel(GTCEu.id("block/machine/template/quantum/quantum_chest"))
                    .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createQuantumChestRender)))
            .hasBER(true)
            .tier(GTValues.ULV)
            .register();

    public static final MachineEntry<MachineDefinition> ULV_SUPER_TANK = registerULVSuperTank();

    private static MachineEntry<MachineDefinition> registerULVSuperTank() {
        long maxAmount = 500 * FluidType.BUCKET_VOLUME;
        return InfinityRegistrate.machine("ulv_super_tank",
                        holder -> new QuantumTankMachine(holder, GTValues.ULV, maxAmount))
                .langValue("Basic Super Tank")
                .rotationState(RotationState.ALL)
                .allowExtendedFacing(true)
                .tooltipBuilder(TANK_TOOLTIPS)
                .tooltips(Component.translatable("gtceu.machine.quantum_tank.tooltip"),
                        Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity",
                                FormattingUtil.formatNumbers(maxAmount)))
                .model(GTMachineModels.createTieredHullMachineModel(GTCEu.id("block/machine/template/quantum/quantum_tank"))
                        .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createQuantumTankRender)))
                .hasBER(true)
                .tier(GTValues.ULV)
                .onRegister(d -> QuantumTankMachine.TANK_CAPACITY.put(d, maxAmount))
                .register();
    }

    // Custom Machines
    public static final Pair<MachineEntry<MachineDefinition>, MachineEntry<MachineDefinition>> STEAM_LOOM = registerSimpleSteamMachines("loom", InfinityRecipeTypes.LOOM_RECIPES);

    public static final Pair<MachineEntry<MachineDefinition>, MachineEntry<MachineDefinition>> STEAM_ROASTER = registerSimpleSteamMachines("roaster", InfinityRecipeTypes.ROASTER_RECIPES);

    public static final MachineEntry<MachineDefinition>[] ROASTER = new SimpleMachineBuilder(InfinityRegistrate, "roaster",
            InfinityRecipeTypes.ROASTER_RECIPES).tankScalingFunction(hvCappedTankSizeFunction).register();

    public static final MachineEntry<MachineDefinition>[] LOOM = new SimpleMachineBuilder(InfinityRegistrate, "loom",
            InfinityRecipeTypes.LOOM_RECIPES).register();

    // Machine Parts
    public static final MachineEntry<MachineDefinition>[] ELECTRODE_HOLDER = registerTieredMachines(InfinityRegistrate,
            "electrode_holder",
            ElectrodeHolderPartMachine::new,
            (tier, builder) -> builder
                    .langValue("%s Electrode Holder".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(InfinityPartAbility.ELECTRODE_HOLDER)
                    .modelProperty(IS_FORMED, false)
                    .overlayTieredHullModel(InfinityCore.id("block/machine/part/electrode_holder"))
                    .tooltips(LangHandler.getMultiLang("tfinfinity.machine.electrode_holder.tooltip"))
                    .tooltips(Component.translatable("gtceu.part_sharing.disabled"))
                    .register(),
            GTValues.tiersBetween(LV, GTCEuAPI.isHighTier() ? OpV : UV));

    public static final MachineEntry<MachineDefinition>[] FUEL_BUS = registerTieredMachines(InfinityRegistrate,
            "fuel_bus",
            FuelBusPartMachine::new,
            (tier, builder) -> builder
                .langValue("%s Fuel Bus".formatted(VNF[tier]))
                .rotationState(RotationState.ALL)
                .abilities(InfinityPartAbility.FUEL_BUS)
                .modelProperty(IS_FORMED, false)
                .overlayTieredHullModel(GTCEu.id("block/machine/part/item_passthrough_hatch"))
                .tooltips(Component.translatable("tfinfinity.machine.fuel_bus.tooltip"))
                .tooltips(Component.translatable("gtceu.universal.tooltip.item_storage_capacity",
                        (1 + Math.min(9, tier)) * (1 + Math.min(9, tier))))
                .tooltips(Component.translatable("gtceu.part_sharing.disabled"))
                .register(),
            GTValues.tiersBetween(ULV, GTCEuAPI.isHighTier() ? OpV : UV));

    // TODO: Update Texture
    public static final MachineEntry<MachineDefinition>[] FUEL_HATCH = registerTieredMachines(InfinityRegistrate,
            "fuel_hatch",
            FuelHatchPartMachine::new,
            (tier, builder) -> builder
                    .langValue("%s Fuel Hatch".formatted(VNF[tier]))
                    .rotationState(RotationState.ALL)
                    .abilities(InfinityPartAbility.FUEL_HATCH)
                    .modelProperty(IS_FORMED, false)
                    .overlayTieredHullModel(GTCEu.id("block/machine/part/fluid_passthrough_hatch"))
                    .tooltips(Component.translatable("tfinfinity.machine.fuel_hatch.tooltip"))
                    .tooltips(Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", FormattingUtil.formatNumbers(FluidHatchPartMachine.getTankCapacity(FuelHatchPartMachine.INITIAL_TANK_CAPACITY, tier))))
                    .tooltips(Component.translatable("gtceu.part_sharing.disabled"))
                    .register(),
            GTValues.tiersBetween(ULV, GTCEuAPI.isHighTier() ? OpV : UV));

    // Helper Methods
    private static Pair<MachineEntry<MachineDefinition>, MachineEntry<MachineDefinition>> registerSimpleSteamMachines(String name, DeferredHolder<RecipeType<?>, GTRecipeType> recipeType) {
        return registerSteamMachines(InfinityRegistrate, "steam_" + name, SimpleSteamMachine::new, (pressure, builder) -> builder.rotationState(RotationState.ALL).recipeType(recipeType).recipeModifier(SimpleSteamMachine::recipeModifier).themeId(i -> i > 0 ? GTGuiTheme.STEEL.getId() : GTGuiTheme.BRONZE.getId()).ui(GTSingleblockMachinePanels.GENERAL_MACHINE).modelProperty(GTMachineModelProperties.VENT_DIRECTION, RelativeDirection.BACK).workableSteamHullModel(pressure, InfinityCore.id("block/machines/" + name)).register());
    }

    public static void init() {}
}
