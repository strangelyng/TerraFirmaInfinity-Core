package net.terrafirmainfinity.core.client;

import com.gregtechceu.gtceu.data.pack.event.RegisterDynamicResourcesEvent;
import net.dries007.tfc.client.TFCColors;
import net.dries007.tfc.common.blocks.soil.ConnectedGrassBlock;
import net.minecraft.client.color.block.BlockColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.client.model.runtimegen.InfinityToolItemModelGenerator;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.InfinityParticles;
import net.terrafirmainfinity.core.common.particle.OreProspectorProvider;
import net.terrafirmainfinity.core.common.particle.OreProspectorVeinProvider;

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

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(InfinityParticles.ORE_PROSPECTOR.get(), OreProspectorProvider::new);
        event.registerSpriteSet(InfinityParticles.ORE_PROSPECTOR_VEIN.get(), OreProspectorVeinProvider::new);
    }

    @SubscribeEvent
    public static void registerDynamicAssets(RegisterDynamicResourcesEvent event) {
        InfinityToolItemModelGenerator.reinitModels();
    }
}
