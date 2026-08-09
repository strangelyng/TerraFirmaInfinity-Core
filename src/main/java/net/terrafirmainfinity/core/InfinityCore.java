package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(InfinityCore.MOD_ID)
public class InfinityCore {
    public static final String MOD_ID = "tficore";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static GTRegistrate INFINITY_REGISTRATE = GTRegistrate.create(MOD_ID);

    public InfinityCore(IEventBus bus, ModContainer mod) {
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        mod.registerConfig(ModConfig.Type.COMMON, InfinityConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
