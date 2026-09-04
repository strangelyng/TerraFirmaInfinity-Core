package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.terrafirmainfinity.core.client.InfinityCoreClient;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.InfinityItems;
import net.terrafirmainfinity.core.common.data.*;
import net.terrafirmainfinity.core.common.data.material.InfinityElements;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterialIconSet;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterialFlags;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterials;
import net.terrafirmainfinity.core.common.datagen.InfinityCoreDatagen;
import net.terrafirmainfinity.core.common.machine.InfinityMulti;
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

    public static final String MOD_ID = "tfinfinity";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static GTRegistrate InfinityRegistrate = GTRegistrate.create(MOD_ID);

    public static RegistryEntry<CreativeModeTab, CreativeModeTab> INFINITY_CREATIVE_TAB = InfinityRegistrate.defaultCreativeTab(
            InfinityCore.MOD_ID,
            builder -> builder
                    .title(InfinityRegistrate
                            .addLang("itemGroup", InfinityCore.id("creative_tab"),
                                    "TerraFirmaInfinity (Coremod)"))
                    .icon(InfinityItems.STEAM_MOTOR::asStack)
                    .build())
            .register();

    public InfinityCore(IEventBus modBus, ModContainer modContainer) {
        InfinityCoreDatagen.init();
        modBus.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, InfinityConfig.SPEC);
        InfinityRegistrate.registerEventListeners(modBus);

        if (FMLEnvironment.dist.isClient()) {
            InfinityCoreClient.init(modBus);
        }
    }

    /**
     * GT Registration order: (elements -> material icon sets -> material icon types -> materials -> tag prefixes
     * -> sound entries -> blocks, fluids -> recipe capabilitiess, conditions, types -> tools, data components,
     * -> items -> machines -> ingredient types).
     * See {@link com.gregtechceu.gtceu.common.CommonProxy}
     */
    @SubscribeEvent
    public void onRegisterEvent(RegisterEvent event) {
        if (didRunRegistration) return;
        didRunRegistration = true;

        InfinityElements.init();
        InfinityMaterialIconSet.init();
        InfinityMaterialIconType.init();
        InfinityMaterialFlags.init();
        InfinityMaterials.init();
        InfinityTagPrefix.init();

        InfinityCovers.init();

        InfinityBlocks.init();
        InfinityItems.init();

        InfinityRecipeTypes.init();

        InfinityToolBehaviors.init();

        InfinityMulti.init();
        InfinityMachines.init();
    }

    /**
     * For modifying existing materials
     */
    @SubscribeEvent
    public void modifyExistingMaterials(PostMaterialEvent event) {
        InfinityMaterials.modifyMaterials();
    }

    @SubscribeEvent
    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    @SubscribeEvent
    public void onLoadComplete(FMLLoadCompleteEvent event) {

    }

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {

    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
