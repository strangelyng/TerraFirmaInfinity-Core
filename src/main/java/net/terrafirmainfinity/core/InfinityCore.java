package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.terrafirmainfinity.core.common.data.materials.InfinityElements;
import net.terrafirmainfinity.core.common.data.materials.InfinityMaterials;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(InfinityCore.MOD_ID)
public class InfinityCore {
    private static boolean didRunRegistration = false;

    public static final String MOD_ID = "tficore";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static GTRegistrate INFINITY_REGISTRATE = GTRegistrate.create(MOD_ID);

    public InfinityCore(IEventBus bus, ModContainer mod) {
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.register(this);
        INFINITY_REGISTRATE.registerEventListeners(bus);

        mod.registerConfig(ModConfig.Type.COMMON, InfinityConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onRegisterEvent(RegisterEvent event) {
        if (didRunRegistration) return;
        didRunRegistration = true;
        InfinityElements.init();
        InfinityMaterials.register();
    }

    @SubscribeEvent
    public void onPostMaterialEvent(PostMaterialEvent event) {
        InfinityMaterials.postInit();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
