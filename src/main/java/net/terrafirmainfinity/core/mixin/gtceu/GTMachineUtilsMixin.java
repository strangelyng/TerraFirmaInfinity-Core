package net.terrafirmainfinity.core.mixin.gtceu;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.item.QuantumTankMachineItem;
import com.gregtechceu.gtceu.common.machine.storage.QuantumChestMachine;
import com.gregtechceu.gtceu.common.machine.storage.QuantumTankMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.LVT;
import static com.gregtechceu.gtceu.api.GTValues.MAX;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createTieredHullMachineModel;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;

@Mixin(GTMachineUtils.class)
public class GTMachineUtilsMixin {
    /**
     * @author Strangelyng
     * @reason Not my favorite, but there doesn't seem to be a builtin method to modify default capacity of super chests
     */
    @Overwrite
    public static MachineDefinition[] registerQuantumChests(GTRegistrate registrate, String name, int... tiers) {
        return registerTieredMachines(registrate, name,
                (holder, tier) -> new QuantumChestMachine(holder, tier,
                        tier == MAX ? Long.MAX_VALUE : 1000000 * (long) Math.pow(2, tier - 1)),
                (tier, builder) -> builder
                        .langValue(toEnglishName(name) + " " + LVT[tier])
                        .blockProp(Block.Properties::dynamicShape)
                        .rotationState(RotationState.ALL)
                        .allowExtendedFacing(true)
                        .model(createTieredHullMachineModel(GTCEu.id("block/machine/template/quantum/quantum_chest"))
                                .andThen(
                                        b -> b.addDynamicRenderer(DynamicRenderHelper::createQuantumChestRender)))
                        .hasBER(true)
                        .tooltipBuilder(CHEST_TOOLTIPS)
                        .tooltips(Component.translatable("gtceu.machine.quantum_chest.tooltip"),
                                Component.translatable("gtceu.universal.tooltip.item_storage_total",
                                        FormattingUtil.formatNumbers(tier == MAX ? Long.MAX_VALUE : 1000000 * (long) Math.pow(2, tier - 1))))
                        .register(),
                tiers);
    }

    /**
     * @author Strangelyng
     * @reason Not my favorite, but there doesn't seem to be a builtin method to modify default capacity of super tanks
     */
    @Overwrite
    public static MachineDefinition[] registerQuantumTanks(GTRegistrate registrate, String name, int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            long maxAmount = 1000 * FluidType.BUCKET_VOLUME * (long) Math.pow(2, tier - 1);
            var register = registrate.machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name, MachineDefinition::new, MetaMachineBlock::new, QuantumTankMachineItem::new, holder -> new QuantumTankMachine(holder, tier, maxAmount)).langValue(toEnglishName(name) + " " + LVT[tier]).blockProp(Block.Properties::dynamicShape).rotationState(RotationState.ALL).allowExtendedFacing(true).model(createTieredHullMachineModel(GTCEu.id("block/machine/template/quantum/quantum_tank")).andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createQuantumTankRender))).hasBER(true).tooltipBuilder(TANK_TOOLTIPS).tooltips(Component.translatable("gtceu.machine.quantum_tank.tooltip"), Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", FormattingUtil.formatNumbers(maxAmount))).tier(tier).register();
            QuantumTankMachine.TANK_CAPACITY.put(register, maxAmount);
            definitions[tier] = register;
        }
        return definitions;
    }
}
