package net.terrafirmainfinity.core.common.data.material;

import alexthw.eidolon_repraised.registries.Registry;
import appeng.core.definitions.AEItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.common.data.GTFluids;
import com.rekindled.embers.RegistryManager;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.dries007.tfc.common.fluids.SimpleFluid;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;

import java.util.HashMap;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.InfinityTagPrefix.*;

public class InfinityMaterials {
    // Integration Materials
    public static Material Dawnstone; // Embers
    public static Material Pewter; // Eidolon Repraised
    public static Material Fluix; // Applied Energistics 2

    public static Material WeakSteel; // TFC
    public static Material WeakBlueSteel; // TFC
    public static Material WeakRedSteel; // TFC

    // Rock Materials
    public static Material FelsicIgneous; // Granite, Rhyolite
    public static Material IntermediateIgneous; // Diorite, Tuff, Andesite, Dacite
    public static Material MaficIgneous; // Gabbro, Basalt
    public static Material ClasticSedimentary; // Shale, Claystone, Conglomerate
    public static Material CarbonateSedimentary; // Limestone, Dolomite, Chalk
    public static Material SiliceousSedimentary; // Chert
    public static Material Metamorphic; // Quartzite, Slate, Phyllite, Schist, Gneiss, Marble


    public static void init() {
        InfinityIntegrationMaterials.register();
    }

    public static void modifyMaterials() {
        // Add Properties
        Hafnium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());

        Hafnium.setProperty(PropertyKey.BLAST, new BlastProperty(2506, BlastProperty.GasTier.MID, GTValues.VA[EV], 1500, GTValues.VA[HV], 360));
        Zirconium.setProperty(PropertyKey.BLAST, new BlastProperty(2125, BlastProperty.GasTier.MID, GTValues.VA[EV], 1300, GTValues.VA[HV], 300));

        // Add Ores
        Borax.setProperty(PropertyKey.ORE, new OreProperty());

        // Add Flags
        Bismuth.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        BismuthBronze.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        BlackBronze.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Bronze.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Brass.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Copper.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Gold.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Nickel.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        RoseGold.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Silver.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Tin.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Zinc.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        SterlingSilver.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        WroughtIron.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Iron.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Steel.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        BlackSteel.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT, MaterialFlags.GENERATE_BOLT_SCREW);
        BlueSteel.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        RedSteel.addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);

        Carbon.addFlags(MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD);
        Graphite.addFlags(MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD);

        // Modify Properties
        BismuthBronze.removeProperty(PropertyKey.BLAST);
        BlackBronze.removeProperty(PropertyKey.BLAST);

        RoseGold.removeProperty(PropertyKey.BLAST);
        SterlingSilver.removeProperty(PropertyKey.BLAST);

        BlackSteel.getProperty(PropertyKey.BLAST).setBlastTemperature(1485);
        BlueSteel.getProperty(PropertyKey.BLAST).setBlastTemperature(1540);
        RedSteel.getProperty(PropertyKey.BLAST).setBlastTemperature(1540);

        // Restrict Native Metals
        Aluminium.removeProperty(PropertyKey.ORE);
        Beryllium.removeProperty(PropertyKey.ORE);
        Cobalt.removeProperty(PropertyKey.ORE);
        Lithium.removeProperty(PropertyKey.ORE);
        Molybdenum.removeProperty(PropertyKey.ORE);
        Neodymium.removeProperty(PropertyKey.ORE);
        Nickel.removeProperty(PropertyKey.ORE);
        Plutonium239.removeProperty(PropertyKey.ORE);
        Thorium.removeProperty(PropertyKey.ORE);

        addFluidToExisting(FluidStorageKeys.LIQUID, SodiumHydroxide);

        /// SET IGNORED ///

        // Create Materials
        ingot.setIgnored(Brass, () -> AllItems.BRASS_INGOT);
        plate.setIgnored(Brass, () -> AllItems.BRASS_SHEET);
        nugget.setIgnored(Brass, () -> AllItems.BRASS_NUGGET);
        block.setIgnored(Brass, () -> AllBlocks.BRASS_BLOCK);

        plate.setIgnored(Copper, () -> AllItems.COPPER_SHEET);
        nugget.setIgnored(Copper, () -> AllItems.COPPER_NUGGET);

        ingot.setIgnored(Zinc, () -> AllItems.ZINC_INGOT);
        nugget.setIgnored(Zinc, () -> AllItems.ZINC_NUGGET);
        block.setIgnored(Zinc, () -> AllBlocks.ZINC_BLOCK);

        // TFC Materials
        GTFluids.handleNonMaterialFluids(SodiumHydroxide, () -> TFCFluids.SIMPLE_FLUIDS.get(SimpleFluid.LYE).source().get());

        ingot.setIgnored(Iron, // Iron to Cast Iron
                () -> TFCItems.METAL_ITEMS.get(Metal.CAST_IRON).get(Metal.ItemType.INGOT));

        ingot.setIgnored(WeakSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.WEAK_STEEL).get(Metal.ItemType.INGOT));
        ingot.setIgnored(BlackSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLACK_STEEL).get(Metal.ItemType.INGOT));

        ingot.setIgnored(BlueSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.WEAK_BLUE_STEEL).get(Metal.ItemType.INGOT));
        ingot.setIgnored(BlueSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.INGOT));

        ingot.setIgnored(WeakRedSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.WEAK_RED_STEEL).get(Metal.ItemType.INGOT));
        ingot.setIgnored(RedSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.RED_STEEL).get(Metal.ItemType.INGOT));

        // TFC Tool Materials
        var tfcMetals = new HashMap<Material, Metal>();
        tfcMetals.put(Copper, Metal.COPPER);
        tfcMetals.put(BismuthBronze, Metal.BISMUTH_BRONZE);
        tfcMetals.put(Bronze, Metal.BRONZE);
        tfcMetals.put(BlackBronze, Metal.BLACK_BRONZE);
        tfcMetals.put(WroughtIron, Metal.WROUGHT_IRON);
        tfcMetals.put(Steel, Metal.STEEL);
        tfcMetals.put(BlackSteel, Metal.BLACK_STEEL);
        tfcMetals.put(BlueSteel, Metal.BLUE_STEEL);
        tfcMetals.put(RedSteel, Metal.RED_STEEL);

        tfcMetals.forEach((material, metal) -> {
            var metalItems = TFCItems.METAL_ITEMS.get(metal);
            toolHeadPickaxe.setIgnored(material, () -> metalItems.get(Metal.ItemType.PICKAXE_HEAD));
            toolHeadAxe.setIgnored(material, () -> metalItems.get(Metal.ItemType.AXE_HEAD));
            toolHeadShovel.setIgnored(material, () -> metalItems.get(Metal.ItemType.SHOVEL_HEAD));
            toolHeadHoe.setIgnored(material, () -> metalItems.get(Metal.ItemType.HOE_HEAD));
            toolHeadChisel.setIgnored(material, () -> metalItems.get(Metal.ItemType.CHISEL_HEAD));
            toolHeadHammer.setIgnored(material, () -> metalItems.get(Metal.ItemType.HAMMER_HEAD));
            toolHeadSaw.setIgnored(material, () -> metalItems.get(Metal.ItemType.SAW_BLADE));
            toolHeadKnife.setIgnored(material, () -> metalItems.get(Metal.ItemType.KNIFE_BLADE));
            toolHeadScythe.setIgnored(material, () -> metalItems.get(Metal.ItemType.SCYTHE_BLADE));
            toolHeadSword.setIgnored(material, () -> metalItems.get(Metal.ItemType.SWORD_BLADE));
            toolHeadMace.setIgnored(material, () -> metalItems.get(Metal.ItemType.MACE_HEAD));
        });

        // TFC All Materials
        tfcMetals.put(Bismuth, Metal.BISMUTH);
        tfcMetals.put(Brass, Metal.BRASS);
        tfcMetals.put(Gold, Metal.GOLD);
        tfcMetals.put(Nickel, Metal.NICKEL);
        tfcMetals.put(RoseGold, Metal.ROSE_GOLD);
        tfcMetals.put(Silver, Metal.SILVER);
        tfcMetals.put(Tin, Metal.TIN);
        tfcMetals.put(Zinc, Metal.ZINC);
        tfcMetals.put(SterlingSilver, Metal.STERLING_SILVER);
        tfcMetals.put(Iron, Metal.CAST_IRON);

        tfcMetals.forEach((material, metal) -> {
            var metalItems = TFCItems.METAL_ITEMS.get(metal);
            ingotDouble.setIgnored(material, () -> metalItems.get(Metal.ItemType.DOUBLE_INGOT));
        });

        // Eidolon Materials
        ingot.setIgnored(Pewter, Registry.PEWTER_INGOT);
        nugget.setIgnored(Pewter, Registry.PEWTER_NUGGET);
        block.setIgnored(Pewter, Registry.PEWTER_BLOCK);

        // Embers Materials
        ingot.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_INGOT);
        plate.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_PLATE);
        nugget.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_NUGGET);
        block.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_BLOCK);
        GTFluids.handleNonMaterialFluids(Dawnstone, RegistryManager.MOLTEN_DAWNSTONE.FLUID::get);

        // AE2 Materials
        dust.setIgnored(CertusQuartz, () -> AEItems.CERTUS_QUARTZ_DUST);
        gem.setIgnored(CertusQuartz, () -> AEItems.CERTUS_QUARTZ_CRYSTAL);

        dust.setIgnored(Fluix, () -> AEItems.FLUIX_DUST);
        gem.setIgnored(Fluix, () -> AEItems.FLUIX_CRYSTAL);

        dust.setIgnored(EnderPearl, () -> AEItems.ENDER_DUST);
    }

    public static void addFluidToExisting(FluidStorageKey key, Material mat) {
        FluidProperty fluidProp = new FluidProperty();
        fluidProp.getStorage().enqueueRegistration(key, new FluidBuilder());
        mat.setProperty(PropertyKey.FLUID, fluidProp);
    }
}
