package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
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

    public static final String MOD_ID = "tfi";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static GTRegistrate REGISTRATE = GTRegistrate.create(MOD_ID);

    public static RegistryEntry<CreativeModeTab, ? extends CreativeModeTab> INFINITY_CREATIVE_TAB = REGISTRATE
            .defaultCreativeTab(InfinityCore.MOD_ID,
                    builder -> builder
                            .displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(
                                    InfinityCore.MOD_ID, REGISTRATE))
                            .title(REGISTRATE
                                    .addLang("itemGroup", InfinityCore.id("creative_tab"),
                                            "TerraFirmaInfinity (Coremod)"))
                            .icon(Items.NETHERITE_INGOT::getDefaultInstance)
                            .build())
            .register();

    public InfinityCore(IEventBus bus, ModContainer mod) {
        bus.register(this);
        mod.registerConfig(ModConfig.Type.COMMON, InfinityConfig.SPEC);
        REGISTRATE.registerEventListeners(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onRegisterEvent(RegisterEvent event) {
        if (didRunRegistration) return;
        didRunRegistration = true;
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
