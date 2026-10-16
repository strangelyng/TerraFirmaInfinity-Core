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
import net.terrafirmainfinity.core.client.renderer.OreHighlightVeinRenderer;

import java.util.List;

public record OreHighlightVeinPacket(List<BlockPos> veinCenters) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<OreHighlightVeinPacket> TYPE = new CustomPacketPayload.Type<>(InfinityCore.id("ore_highlight_vein"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OreHighlightVeinPacket> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC.apply(ByteBufCodecs.list()), OreHighlightVeinPacket::veinCenters,
            OreHighlightVeinPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> OreHighlightVeinRenderer.addVeinHighlights(this.veinCenters()));
    }
}
