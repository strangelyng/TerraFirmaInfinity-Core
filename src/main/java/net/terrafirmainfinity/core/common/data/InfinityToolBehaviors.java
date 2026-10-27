package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.item.tool.behavior.IToolBehavior;
import com.gregtechceu.gtceu.api.item.tool.behavior.ToolBehaviorType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.mojang.serialization.Codec;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.terrafirmainfinity.core.common.data.item.tool.behavior.ChiselBehavior;
import net.terrafirmainfinity.core.common.data.item.tool.behavior.MaceBehavior;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityToolBehaviors {

    // TODO: Propick

    public static final RegistryEntry<ToolBehaviorType<?>, ToolBehaviorType<ChiselBehavior>> CHISEL = register("chisel", ChiselBehavior.CODEC, ChiselBehavior.STREAM_CODEC);

    // TODO: Javelin

    public static final RegistryEntry<ToolBehaviorType<?>, ToolBehaviorType<MaceBehavior>> MACE = register("mace", MaceBehavior.CODEC, MaceBehavior.STREAM_CODEC);

    public static void init() {}

    private static <T extends IToolBehavior<T>> RegistryEntry<ToolBehaviorType<?>, ToolBehaviorType<T>> register(String name,
                                                                                                                 Codec<T> codec,
                                                                                                                 StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {
        return InfinityRegistrate.simple(name, GTRegistries.Keys.TOOL_BEHAVIOR,
                () -> new ToolBehaviorType<>(codec, streamCodec));
    }
}
