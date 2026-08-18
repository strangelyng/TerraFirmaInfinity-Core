package net.terrafirmainfinity.core.client;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.terrafirmainfinity.core.common.ui.InfinityGuiTextures;

public class InfinityCoreClient {
    public static void init(IEventBus modBus) {
        modBus.register(InfinityCoreClient.class);

        InfinityGuiTextures.init();
    }

    @SubscribeEvent
    private static void clientSetup(FMLClientSetupEvent event) {

    }
}
