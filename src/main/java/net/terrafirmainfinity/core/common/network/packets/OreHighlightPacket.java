/*
 * This class is adapted from TerraFirmaGreg-Core - Modern, which is licensed under LGPL-3.0
 * A copy of the license can be found here:
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 */

package net.terrafirmainfinity.core.common.network.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.client.renderer.OreHighlightRenderer;

import java.util.List;

public record OreHighlightPacket(List<BlockPos> positions) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OreHighlightPacket> TYPE = new CustomPacketPayload.Type<>(InfinityCore.id("ore_highlight"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OreHighlightPacket> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC.apply(ByteBufCodecs.list()), OreHighlightPacket::positions,
            OreHighlightPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> OreHighlightRenderer.addHighlights(this.positions()));
    }
}
