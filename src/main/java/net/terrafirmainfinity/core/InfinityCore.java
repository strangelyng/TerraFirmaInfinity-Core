package net.terrafirmainfinity.core;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(InfinityCore.MOD_ID)
public class InfinityCore
{
    public static final String MOD_ID = "tficore";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public InfinityCore(IEventBus bus, ModContainer mod) {
        bus.addListener(this::commonSetup);

        mod.registerConfig(ModConfig.Type.COMMON, InfinityConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }
}
