package net.terrafirmainfinity.core.common.data.material;

import alexthw.eidolon_repraised.registries.Registry;
import appeng.core.definitions.AEBlocks;
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
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.item.MalumItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.fluids.SimpleFluid;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.level.block.Blocks;

import java.util.HashMap;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.InfinityTagPrefix.*;

public class InfinityMaterials {
    // Integration Materials
    public static Material Dawnstone; // Embers
    public static Material Ember; // Embers

    public static Material Pewter; // Eidolon Repraised

    public static Material Fluix; // Applied Energistics 2

    public static Material HallowedGold; // Malum
    public static Material MalignantPewter; // Malum
    public static Material SoulstainedSteel; // Malum

    public static Material Kaolinite; // TFC
    public static Material PigIron; // TFC
    public static Material WeakSteel; // TFC
    public static Material WeakBlueSteel; // TFC
    public static Material WeakRedSteel; // TFC

    // Ore Materials
    public static Material Bismuthinite; // TFC
    public static Material Cryolite; // TFC

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
        InfinityOreMaterials.register();
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

        Lead.addFlags(MaterialFlags.GENERATE_FRAME);

        // Modify Properties
        IngotProperty ingotProp = WroughtIron.getProperty(PropertyKey.INGOT);
        ingotProp.setMacerateInto(Iron);
        WroughtIron.removeProperty(PropertyKey.FLUID);

        ingotProp = AnnealedCopper.getProperty(PropertyKey.INGOT);
        ingotProp.setMacerateInto(Copper);
        AnnealedCopper.removeProperty(PropertyKey.FLUID);

        BismuthBronze.removeProperty(PropertyKey.BLAST);
        BlackBronze.removeProperty(PropertyKey.BLAST);

        RoseGold.removeProperty(PropertyKey.BLAST);
        SterlingSilver.removeProperty(PropertyKey.BLAST);

        OreProperty oreProp = Ember.getProperty(PropertyKey.ORE);
        oreProp.setWashedIn(Mercury);
        oreProp.setOreByProducts(Sulfur, Sulfur, Ember, Gold);

        // Adjust Fluid Temperature of Steels
        Steel.removeProperty(PropertyKey.FLUID);
        BlackSteel.removeProperty(PropertyKey.FLUID);
        BlueSteel.removeProperty(PropertyKey.FLUID);
        RedSteel.removeProperty(PropertyKey.FLUID);

        addFluidToExisting(FluidStorageKeys.LIQUID, 1800, Steel);
        addFluidToExisting(FluidStorageKeys.LIQUID, 1800, BlueSteel);
        addFluidToExisting(FluidStorageKeys.LIQUID, 1800, RedSteel);
        addFluidToExisting(FluidStorageKeys.LIQUID, 1750, BlackSteel);

        // Set Steels Blast Temperature to Match Steel
        BlastProperty blackSteelProp = BlackSteel.getProperty(PropertyKey.BLAST);
        blackSteelProp.setBlastTemperature(1000);
        blackSteelProp.setEUtOverride(VA[MV]);
        blackSteelProp.setDurationOverride(1000);
        BlastProperty blueSteelProp = BlueSteel.getProperty(PropertyKey.BLAST);
        blueSteelProp.setBlastTemperature(1000);
        blueSteelProp.setEUtOverride(VA[MV]);
        BlastProperty redSteelProp = RedSteel.getProperty(PropertyKey.BLAST);
        redSteelProp.setBlastTemperature(1000);
        redSteelProp.setEUtOverride(VA[MV]);

        // Fluid Property
        FluidProperty fluidProp = Mercury.getProperty(PropertyKey.FLUID);
        fluidProp.getStorage().enqueueRegistration(FluidStorageKeys.GAS, new FluidBuilder().temperature(630));

        // Restrict Native Metals
        Aluminium.removeProperty(PropertyKey.ORE);
        Beryllium.removeProperty(PropertyKey.ORE);
        Cobalt.removeProperty(PropertyKey.ORE);
        Iron.removeProperty(PropertyKey.ORE);
//        Lead.removeProperty(PropertyKey.ORE);
        Lithium.removeProperty(PropertyKey.ORE);
        Molybdenum.removeProperty(PropertyKey.ORE);
        Neodymium.removeProperty(PropertyKey.ORE);
        Nickel.removeProperty(PropertyKey.ORE);
        Plutonium239.removeProperty(PropertyKey.ORE);
        Thorium.removeProperty(PropertyKey.ORE);
        Tin.removeProperty(PropertyKey.ORE);

        // Remove Other Ores
        Electrotine.removeProperty(PropertyKey.ORE);

        addFluidToExisting(FluidStorageKeys.LIQUID, SodiumHydroxide); // for Soda Lye
        addFluidToExisting(FluidStorageKeys.LIQUID, PotassiumHydroxide);
        addFluidToExisting(FluidStorageKeys.LIQUID, CalciumHydroxide);

        /// Remove Ignored ///

        // Vanilla Materials
        nugget.removeIgnored(Iron);
        block.removeIgnored(Iron);

        /// SET IGNORED ///

        // Create Materials
        ingot.setIgnored(Brass, () -> AllItems.BRASS_INGOT);
        plate.setIgnored(Brass, () -> AllItems.BRASS_SHEET);
        nugget.setIgnored(Brass, () -> AllItems.BRASS_NUGGET);
        block.setIgnored(Brass, () -> AllBlocks.BRASS_BLOCK);

//        plate.setIgnored(Copper, () -> AllItems.COPPER_SHEET);
        nugget.setIgnored(Copper, () -> AllItems.COPPER_NUGGET);

        ingot.setIgnored(Zinc, () -> AllItems.ZINC_INGOT);
        nugget.setIgnored(Zinc, () -> AllItems.ZINC_NUGGET);
        block.setIgnored(Zinc, () -> AllBlocks.ZINC_BLOCK);

        // Malum Materials
        ingot.setIgnored(HallowedGold, MalumItems.HALLOWED_GOLD_INGOT::get);
        nugget.setIgnored(HallowedGold, MalumItems.HALLOWED_GOLD_NUGGET::get);
        block.setIgnored(HallowedGold, MalumBlocks.BLOCK_OF_HALLOWED_GOLD::get);

        ingot.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_INGOT::get);
        plate.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_PLATING::get);
        nugget.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_NUGGET::get);
        block.setIgnored(MalignantPewter, MalumBlocks.BLOCK_OF_MALIGNANT_PEWTER::get);

        ingot.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_INGOT::get);
        plate.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_PLATING::get);
        nugget.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_NUGGET::get);
        block.setIgnored(SoulstainedSteel, MalumBlocks.BLOCK_OF_SOUL_STAINED_STEEL::get);

        // TFC Materials
        block.setIgnored(Kaolinite, () -> TFCBlocks.RED_KAOLIN_CLAY);
        block.setIgnored(Kaolinite, () -> TFCBlocks.PINK_KAOLIN_CLAY);
        block.setIgnored(Kaolinite, () -> TFCBlocks.WHITE_KAOLIN_CLAY);
        powder.setIgnored(Kaolinite, () -> TFCItems.POWDERS.get(Powder.KAOLINITE));

        block.modifyMaterialAmount(Kaolinite, 4);

        powder.setIgnored(CalciumCarbonate, () -> TFCItems.POWDERS.get(Powder.FLUX));
        powder.setIgnored(Quicklime, () -> TFCItems.POWDERS.get(Powder.LIME));
        powder.setIgnored(SodaAsh, () -> TFCItems.POWDERS.get(Powder.SODA_ASH));
        powder.setIgnored(PotassiumCarbonate, () -> TFCItems.POWDERS.get(Powder.WOOD_ASH));

        powder.setIgnored(Charcoal, () -> TFCItems.POWDERS.get(Powder.CHARCOAL));
        powder.setIgnored(Coke, () -> TFCItems.POWDERS.get(Powder.COKE));
        powder.setIgnored(Salt, () -> TFCItems.POWDERS.get(Powder.SALT));

        GTFluids.handleNonMaterialFluids(PotassiumHydroxide, () -> TFCFluids.SIMPLE_FLUIDS.get(SimpleFluid.LYE).source().get());
        GTFluids.handleNonMaterialFluids(CalciumHydroxide, () -> TFCFluids.SIMPLE_FLUIDS.get(SimpleFluid.LIMEWATER).source().get());

        ingot.removeIgnored(Iron);
        ingot.setIgnored(Iron, // Iron to Cast Iron
                () -> TFCItems.METAL_ITEMS.get(Metal.CAST_IRON).get(Metal.ItemType.INGOT));

        ingot.setIgnored(PigIron,
                () -> TFCItems.METAL_ITEMS.get(Metal.PIG_IRON).get(Metal.ItemType.INGOT));

        ingot.setIgnored(WeakSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.WEAK_STEEL).get(Metal.ItemType.INGOT));
        ingot.setIgnored(BlackSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLACK_STEEL).get(Metal.ItemType.INGOT));

        ingot.setIgnored(WeakBlueSteel,
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

            lampUnfinished.setIgnored(material, () -> metalItems.get(Metal.ItemType.UNFINISHED_LAMP));

            var metalBlocks = TFCBlocks.METALS.get(metal);
            anvil.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.ANVIL));
            bars.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.BARS));
            chain.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.CHAIN));
            grate.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.GRATE));
            lamp.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.LAMP));
            trapdoor.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.TRAPDOOR));
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

            var metalBlocks = TFCBlocks.METALS.get(metal);
            blockPlated.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.BLOCK));
            slabPlated.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.BLOCK_SLAB));
            stairsPlated.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.BLOCK_STAIRS));
        });

        var oxidizableMetals = new HashMap<Material, Metal>();
        oxidizableMetals.put(Bronze, Metal.BRONZE);
        oxidizableMetals.put(Copper, Metal.COPPER);
        oxidizableMetals.put(Steel, Metal.STEEL);
        oxidizableMetals.put(WroughtIron, Metal.WROUGHT_IRON);
        oxidizableMetals.forEach((material, metal) -> {
            var metalBlocks = TFCBlocks.METALS.get(metal);

            grateExposed.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.EXPOSED_GRATE));
            grateWeathered.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.WEATHERED_GRATE));
            grateOxidized.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.OXIDIZED_GRATE));
        });

        oxidizableMetals.put(Brass, Metal.BRASS);
        oxidizableMetals.put(Silver, Metal.SILVER);
        oxidizableMetals.put(SterlingSilver, Metal.STERLING_SILVER);

        oxidizableMetals.forEach((material, metal) -> {
            var metalBlocks = TFCBlocks.METALS.get(metal);

            blockPlatedExposed.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.EXPOSED_BLOCK));
            slabPlatedExposed.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.EXPOSED_BLOCK_SLAB));
            stairsPlatedExposed.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.EXPOSED_BLOCK_STAIRS));

            blockPlatedWeathered.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.WEATHERED_BLOCK));
            slabPlatedWeathered.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.WEATHERED_BLOCK_SLAB));
            stairsPlatedWeathered.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.WEATHERED_BLOCK_STAIRS));

            blockPlatedOxidized.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.OXIDIZED_BLOCK));
            slabPlatedOxidized.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.OXIDIZED_BLOCK_SLAB));
            stairsPlatedOxidized.setIgnored(material, () -> metalBlocks.get(Metal.BlockType.OXIDIZED_BLOCK_STAIRS));
        });

        bell.setIgnored(Brass, () -> TFCBlocks.BRASS_BELL);
        bell.setIgnored(Bronze, () -> TFCBlocks.BRONZE_BELL);
        bell.setIgnored(Gold, () -> Blocks.BELL);

        // TFC Graded Ores
        var tfcGradedOres = new HashMap<Material, Ore>();
        tfcGradedOres.put(Bismuthinite, Ore.BISMUTHINITE);
        tfcGradedOres.put(Cassiterite, Ore.CASSITERITE);
        tfcGradedOres.put(Garnierite, Ore.GARNIERITE);
        tfcGradedOres.put(Hematite, Ore.HEMATITE);
        tfcGradedOres.put(YellowLimonite, Ore.LIMONITE);
        tfcGradedOres.put(Magnetite, Ore.MAGNETITE);
        tfcGradedOres.put(Malachite, Ore.MALACHITE);
        tfcGradedOres.put(Copper, Ore.NATIVE_COPPER);
        tfcGradedOres.put(Gold, Ore.NATIVE_GOLD);
        tfcGradedOres.put(Silver, Ore.NATIVE_SILVER);
        tfcGradedOres.put(Sphalerite, Ore.SPHALERITE);
        tfcGradedOres.put(Tetrahedrite, Ore.TETRAHEDRITE);

        tfcGradedOres.forEach((material, ore) -> {
            oreSmall.setIgnored(material, () -> TFCBlocks.SMALL_ORES.get(ore));

            var oreItems = TFCItems.GRADED_ORES.get(ore);
            poorRawOre.setIgnored(material, () -> oreItems.get(Ore.Grade.POOR));
            rawOre.setIgnored(material, () -> oreItems.get(Ore.Grade.NORMAL));
            richRawOre.setIgnored(material, () -> oreItems.get(Ore.Grade.RICH));

            if (ore.hasPowder()) {
                powder.setIgnored(material, () -> TFCItems.ORE_POWDERS.get(ore));
            }
        });

        // TFC Ungraded Ores
        var tfcOres = new HashMap<Material, Ore>();
        tfcOres.put(Amethyst, Ore.AMETHYST);
        tfcOres.put(Borax, Ore.BORAX);
        tfcOres.put(Cinnabar, Ore.CINNABAR);
        tfcOres.put(Cryolite, Ore.CRYOLITE);
        tfcOres.put(Diamond, Ore.DIAMOND);
        tfcOres.put(Emerald, Ore.EMERALD);
        tfcOres.put(Graphite, Ore.GRAPHITE);
        tfcOres.put(Gypsum, Ore.GYPSUM);
        tfcOres.put(Salt, Ore.HALITE); // Halite = NaCl
        tfcOres.put(Lapis, Ore.LAPIS_LAZULI);
//        tfcOres.put(Lignite, Ore.LIGNITE);
        tfcOres.put(Opal, Ore.OPAL);
        tfcOres.put(Pyrite, Ore.PYRITE);
        tfcOres.put(Ruby, Ore.RUBY);
        tfcOres.put(Saltpeter, Ore.SALTPETER);
        tfcOres.put(Sapphire, Ore.SAPPHIRE);
        tfcOres.put(Sulfur, Ore.SULFUR);
        tfcOres.put(RockSalt, Ore.SYLVITE); // Sylvite = KCl
        tfcOres.put(Topaz, Ore.TOPAZ);

        tfcOres.forEach((material, ore) -> {
            rawOre.setIgnored(material, () -> TFCItems.ORES.get(ore));

            if (ore.hasPowder()) {
                powder.setIgnored(material, () -> TFCItems.ORE_POWDERS.get(ore));
            }
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

        gem.setIgnored(Ember, RegistryManager.EMBER_CRYSTAL);
        dust.setIgnored(Ember, RegistryManager.EMBER_GRIT);

        aspectus.setIgnored(Iron, RegistryManager.IRON_ASPECTUS);
        aspectus.setIgnored(Copper, RegistryManager.COPPER_ASPECTUS);
        aspectus.setIgnored(Lead, RegistryManager.LEAD_ASPECTUS);
        aspectus.setIgnored(Silver, RegistryManager.SILVER_ASPECTUS);
        aspectus.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_ASPECTUS);

        // AE2 Materials
        block.setIgnored(CertusQuartz, () -> AEBlocks.QUARTZ_BLOCK);
        block.modifyMaterialAmount(CertusQuartz, 4);
        dust.setIgnored(CertusQuartz, () -> AEItems.CERTUS_QUARTZ_DUST);
        gem.setIgnored(CertusQuartz, () -> AEItems.CERTUS_QUARTZ_CRYSTAL);

        block.setIgnored(Fluix, () -> AEBlocks.FLUIX_BLOCK);
        block.modifyMaterialAmount(Fluix, 4);
        dust.setIgnored(Fluix, () -> AEItems.FLUIX_DUST);
        gem.setIgnored(Fluix, () -> AEItems.FLUIX_CRYSTAL);

        dust.setIgnored(EnderPearl, () -> AEItems.ENDER_DUST);
    }

    public static void addFluidToExisting(FluidStorageKey key, Material mat) {
        FluidProperty fluidProp = new FluidProperty();
        fluidProp.getStorage().enqueueRegistration(key, new FluidBuilder());
        mat.setProperty(PropertyKey.FLUID, fluidProp);
    }

    public static void addFluidToExisting(FluidStorageKey key, int temp, Material mat) {
        FluidProperty fluidProp = new FluidProperty();
        fluidProp.getStorage().enqueueRegistration(key, new FluidBuilder().temperature(temp));
        mat.setProperty(PropertyKey.FLUID, fluidProp);
    }
}
