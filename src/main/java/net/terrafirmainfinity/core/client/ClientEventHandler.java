package net.terrafirmainfinity.core.client;

import net.dries007.tfc.client.TFCColors;
import net.dries007.tfc.common.blocks.soil.ConnectedGrassBlock;
import net.minecraft.client.color.block.BlockColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.InfinityBlocks;

@EventBusSubscriber(modid = InfinityCore.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public static void registerColorHandlerBlocks(RegisterColorHandlersEvent.Block event) {
        final BlockColor grassColor = (state, level, pos, tintIndex) -> TFCColors.getGrassColor(pos, tintIndex);
        final BlockColor grassBlockColor = (state, level, pos, tintIndex) -> state.getValue(ConnectedGrassBlock.SNOWY) || tintIndex != 1 ? -1 : grassColor.getColor(state, level, pos, tintIndex);

        event.register(grassBlockColor, InfinityBlocks.BAUXITE_GRASS.get());
        event.register(grassBlockColor, InfinityBlocks.GARNIERITE_GRASS.get());
        event.register(grassBlockColor, InfinityBlocks.LIMONITE_GRASS.get());
    }
}
