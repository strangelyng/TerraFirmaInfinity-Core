package net.terrafirmainfinity.core.common.data.material;

import alexthw.eidolon_repraised.registries.Registry;
import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import com.eerussianguy.beneath.common.items.BeneathItems;
import com.eerussianguy.firmalife.common.blocks.FLBlocks;
import com.eerussianguy.firmalife.common.items.FLItems;
import com.eerussianguy.firmalife.common.util.FLMetal;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.DeferredMaterialStack;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.registry.registrate.entry.MaterialRegistryEntry;
import com.gregtechceu.gtceu.common.data.GTFluids;
import com.rekindled.embers.RegistryManager;
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.item.MalumItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Ore;
import net.dries007.tfc.common.items.Powder;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.level.block.Blocks;
import net.terrafirmainfinity.core.common.data.InfinityPropertyKeys;
import net.terrafirmainfinity.core.common.data.material.property.ElectrodeProperty;

import java.util.HashMap;
import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.InfinityTagPrefix.*;

public class InfinityMaterials {
    // Integration Materials
    public static MaterialRegistryEntry Dawnstone; // Embers
    public static MaterialRegistryEntry Ember; // Embers

    public static MaterialRegistryEntry Pewter; // Eidolon Repraised

    public static MaterialRegistryEntry Fluix; // Applied Energistics 2

    public static MaterialRegistryEntry HallowedGold; // Malum
    public static MaterialRegistryEntry MalignantPewter; // Malum
    public static MaterialRegistryEntry SoulstainedSteel; // Malum

    public static MaterialRegistryEntry Kaolinite; // TFC
    public static MaterialRegistryEntry PigIron; // TFC
    public static MaterialRegistryEntry WeakSteel; // TFC
    public static MaterialRegistryEntry WeakBlueSteel; // TFC
    public static MaterialRegistryEntry WeakRedSteel; // TFC

    // Ore Materials
    public static MaterialRegistryEntry Bismuthinite; // TFC
    public static MaterialRegistryEntry Cryolite; // TFC

    public static MaterialRegistryEntry Acanthite; // Silver Sulfide
    public static MaterialRegistryEntry Arsenopyrite; // Iron Arsenic Sulfide
    public static MaterialRegistryEntry Columbite; // Niobium Oxide
    public static MaterialRegistryEntry Fluorite; // Calcium Fluoride
    public static MaterialRegistryEntry Hafnon; // Hafnium Silicate
    public static MaterialRegistryEntry Millerite; // Nickel Sulfide
    public static MaterialRegistryEntry Petalite; // Lithium-Aluminium Silicate
    public static MaterialRegistryEntry Thorianite; // Thorium Oxide
    public static MaterialRegistryEntry Thorite; // Thorium Silicate
    public static MaterialRegistryEntry Vanadinite; // Lead Chlorovanadate
    public static MaterialRegistryEntry Wolframite; // Tungsten Oxide
    public static MaterialRegistryEntry Zircon; // Zirconium Silicate

    public static MaterialRegistryEntry Sperrylite; // Platinum Arsenide

    public static MaterialRegistryEntry Lignite;
    public static MaterialRegistryEntry Anthracite;

    // Compounds
    public static MaterialRegistryEntry Alumina; // Al2O3
    public static MaterialRegistryEntry Beryllia; // BeO
    public static MaterialRegistryEntry ChromiumOxide; // Cr2O3
    public static MaterialRegistryEntry HydrogenChloride; // HCl
    public static MaterialRegistryEntry HydrogenFluoride; // HF

    // Alloys
    public static MaterialRegistryEntry Ferrosilicon; // FeSi3
    public static MaterialRegistryEntry Ferrovanadium; // V3Fe2

    // Rock Materials
    public static MaterialRegistryEntry FelsicIgneous; // Granite, Rhyolite
    public static MaterialRegistryEntry IntermediateIgneous; // Diorite, Tuff, Andesite, Dacite
    public static MaterialRegistryEntry MaficIgneous; // Gabbro, Basalt
    public static MaterialRegistryEntry ClasticSedimentary; // Shale, Claystone, Conglomerate
    public static MaterialRegistryEntry CarbonateSedimentary; // Limestone, Dolomite, Chalk
    public static MaterialRegistryEntry SiliceousSedimentary; // Chert
    public static MaterialRegistryEntry Metamorphic; // Quartzite, Slate, Phyllite, Schist, Gneiss, Marble

    public static void init() {
        InfinityCompoundMaterials.register();
        InfinityIntegrationMaterials.register();
        InfinityOreMaterials.register();
        InfinityAlloyMaterials.register();
    }

    public static void modifyMaterials() {
        /* Add Properties */
        Hafnium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());

        Hafnium.setProperty(PropertyKey.BLAST, new BlastProperty(2506, BlastProperty.GasTier.MID, GTValues.VA[EV], 1500, GTValues.VA[HV], 360));
        Zirconium.setProperty(PropertyKey.BLAST, new BlastProperty(2125, BlastProperty.GasTier.MID, GTValues.VA[EV], 1300, GTValues.VA[HV], 300));

        // Ore Properties
        Andradite.setProperty(PropertyKey.ORE, new OreProperty(3, 1));
        Borax.setProperty(PropertyKey.ORE, new OreProperty(2, 1));
        Uvarovite.setProperty(PropertyKey.ORE, new OreProperty(3, 1));

        Borax.setProperty(PropertyKey.GEM, new GemProperty());

        // Add Flags
        Bismuth.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT, MaterialFlags.GENERATE_PLATE, MaterialFlags.GENERATE_ROD);
        BismuthBronze.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        BlackBronze.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Bronze.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Brass.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Copper.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Gold.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Nickel.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT, MaterialFlags.GENERATE_ROD);
        RoseGold.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Silver.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Tin.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Zinc.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        SterlingSilver.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        WroughtIron.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Iron.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        Steel.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        BlackSteel.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT, MaterialFlags.GENERATE_BOLT_SCREW);
        BlueSteel.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);
        RedSteel.value().addFlags(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT);

        Carbon.value().addFlags(MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD);
        Graphite.value().addFlags(MaterialFlags.GENERATE_ROD, MaterialFlags.GENERATE_LONG_ROD);

        Carbon.setProperty(InfinityPropertyKeys.ELECTRODE, new ElectrodeProperty(100, 256));
        Graphite.setProperty(InfinityPropertyKeys.ELECTRODE, new ElectrodeProperty(120, 512));

        Lead.value().addFlags(MaterialFlags.GENERATE_FRAME);

        // Modify Components (deferred-material-registration)
        Alunite.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Potassium, 1),
                new DeferredMaterialStack(Aluminium, 3),
                new DeferredMaterialStack(Sulfur, 2),
                new DeferredMaterialStack(Hydrogen, 6),
                new DeferredMaterialStack(Oxygen, 14))
        );
        Alunite.get().setFormula("KAl3S2H6O14", true);

        Sodalite.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Sodium, 4),
                new DeferredMaterialStack(Aluminium, 3),
                new DeferredMaterialStack(Silicon, 3),
                new DeferredMaterialStack(Oxygen, 12),
                new DeferredMaterialStack(Chlorine, 1))
        );
        Sodalite.get().setFormula("Na4(Al3Si3O12)Cl", true);

        Lazurite.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Sodium, 3),
                new DeferredMaterialStack(Calcium, 1),
                new DeferredMaterialStack(Aluminium, 3),
                new DeferredMaterialStack(Silicon, 3),
                new DeferredMaterialStack(Oxygen, 12),
                new DeferredMaterialStack(Sulfur, 1))
        );
        Lazurite.get().setFormula("(Na3Ca)Al3Si3O12S", true);

        Lepidolite.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Potassium, 1),
                new DeferredMaterialStack(Lithium, 2),
                new DeferredMaterialStack(Aluminium, 1),
                new DeferredMaterialStack(Silicon, 4),
                new DeferredMaterialStack(Oxygen, 10),
                new DeferredMaterialStack(Fluorine, 2))
        );
        Lepidolite.get().setFormula("KLi2Al(Si4O10)F2", true);

        Ruby.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Alumina, 1),
                new DeferredMaterialStack(ChromiumOxide, 1))
        );
        Ruby.get().setFormula("(Al2O3)(Cr2O3)", true);

        Sapphire.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Alumina, 1))
        );
        Sapphire.get().setFormula("(Al2O3)", true);

        GreenSapphire.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Alumina, 1))
        );
        GreenSapphire.get().setFormula("(Al2O3)", true);

        AllylChloride.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Carbon, 2),
                new DeferredMaterialStack(Methane, 1),
                new DeferredMaterialStack(HydrogenChloride, 1))
        );
        AllylChloride.get().setFormula("C3H5Cl", true);

        AmmoniumChloride.getMaterialInfo().setComponents(List.of(
                new DeferredMaterialStack(Ammonia, 1),
                new DeferredMaterialStack(HydrogenChloride, 1))
        );
        AmmoniumChloride.get().setFormula("NH4Cl", true);

        /* Modify Properties */
        IngotProperty ingotProp = WroughtIron.getProperty(PropertyKey.INGOT);
        ingotProp.setMacerateInto(Iron);
//        WroughtIron.removeProperty(PropertyKey.FLUID); // TODO: Handle on KJS side

        ingotProp = AnnealedCopper.getProperty(PropertyKey.INGOT);
        ingotProp.setMacerateInto(Copper);
//        AnnealedCopper.removeProperty(PropertyKey.FLUID); // TODO: Handle on KJS side

        // Remove Blast Properties
        BismuthBronze.removeProperty(PropertyKey.BLAST);
        BismuthBronze.removeProperty(PropertyKey.ALLOY_BLAST);
        BlackBronze.removeProperty(PropertyKey.BLAST);
        BlackBronze.removeProperty(PropertyKey.ALLOY_BLAST);

        RoseGold.removeProperty(PropertyKey.BLAST);
        RoseGold.removeProperty(PropertyKey.ALLOY_BLAST);
        SterlingSilver.removeProperty(PropertyKey.BLAST);
        SterlingSilver.removeProperty(PropertyKey.ALLOY_BLAST);

        // Modify Ore Properties
        OreProperty oreProp = Quartzite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(NetherQuartz, Barite); // CertusQuartz to NetherQuartz

        oreProp = Molybdenite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Molybdenite, Sulfur, Quartzite); // Molybdenum to Molybdenite

        oreProp = Scheelite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Manganese, Molybdenite, Calcium); // Molybdenum to Molybdenite

        oreProp = Chromite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Iron, Magnesium, Chromite); // Chromium to Chromite

        oreProp = Ruby.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Chromite, GarnetRed, Chromite); // Chromium to Chromite

        oreProp = Coal.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Coal, Coal, Coal); // Remove Thorium

        oreProp = Uraninite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Uraninite, Thorite, Silver); // Thorium to Thorite

        oreProp = Pitchblende.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Thorianite, Uraninite, Lead); // Thorium to Thorianite

        oreProp = Monazite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Thorianite, Neodymium, RareEarth); // Thorium to Thorianite

        oreProp = Tantalite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Manganese, Columbite, Tantalite); // Niobium to Columbite, Tantalum to Tantalite

        oreProp = Pyrolusite.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Manganese, Tantalite, Columbite); // Niobium to Columbite

        oreProp = Pyrochlore.getProperty(PropertyKey.ORE);
        oreProp.setOreByProducts(Apatite, Calcium, Columbite); // Niobium to Columbite

        oreProp = Andradite.getProperty(PropertyKey.ORE); // New Ore Property
        oreProp.setOreByProducts(GarnetYellow, Calcium);

        oreProp = Borax.getProperty(PropertyKey.ORE); // New Ore Property
        oreProp.setOreByProducts(Salt, Salt, Trona);

        oreProp = Uvarovite.getProperty(PropertyKey.ORE); // New Ore Property
        oreProp.setOreByProducts(GarnetYellow, Calcium);

        // Adjust Fluid Temperature of Steels
        Steel.removeProperty(PropertyKey.FLUID);
        BlackSteel.removeProperty(PropertyKey.FLUID);
        BlueSteel.removeProperty(PropertyKey.FLUID);
        RedSteel.removeProperty(PropertyKey.FLUID);

        addFluidToExisting(FluidStorageKeys.LIQUID, 1800, Steel);
        addFluidToExisting(FluidStorageKeys.LIQUID, 1800, BlueSteel);
        addFluidToExisting(FluidStorageKeys.LIQUID, 1800, RedSteel);
        addFluidToExisting(FluidStorageKeys.LIQUID, 1750, BlackSteel);

        addFluidToExisting(FluidStorageKeys.LIQUID, 1074, Salt, "fluid.gtceu.salt");

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
        Lead.removeProperty(PropertyKey.ORE);
        Lithium.removeProperty(PropertyKey.ORE);
        Molybdenum.removeProperty(PropertyKey.ORE);
        Neodymium.removeProperty(PropertyKey.ORE);
        Nickel.removeProperty(PropertyKey.ORE);
        Plutonium239.removeProperty(PropertyKey.ORE);
        Thorium.removeProperty(PropertyKey.ORE);
        Tin.removeProperty(PropertyKey.ORE);

        // Remove Other Ores
        Electrotine.removeProperty(PropertyKey.ORE);
        Soapstone.removeProperty(PropertyKey.ORE); // A Metamorphic Rock Largely Composed of Talc... Just Use Talc
        Tungstate.removeProperty(PropertyKey.ORE);

        // Coal is not a conventional ore
        Coal.removeProperty(PropertyKey.ORE);

        CassiteriteSand.value().addFlags(InfinityMaterialFlags.GENERATE_SAND_ORES);
        GlauconiteSand.value().addFlags(InfinityMaterialFlags.GENERATE_SAND_ORES);
        Oilsands.value().addFlags(InfinityMaterialFlags.GENERATE_SAND_ORES);
        GraniticMineralSand.value().addFlags(InfinityMaterialFlags.GENERATE_SAND_ORES);
        GarnetSand.value().addFlags(InfinityMaterialFlags.GENERATE_SAND_ORES);
        BasalticMineralSand.value().addFlags(InfinityMaterialFlags.GENERATE_SAND_ORES);

        /// Remove Ignored ///

        // Vanilla Materials
        block.value().removeIgnored(Iron.value());
        rawOre.value().removeIgnored(Iron.value());
        nugget.value().removeIgnored(Iron.value());
        ingot.value().removeIgnored(Iron.value());

        rawOre.value().removeIgnored(Copper.value());

        rawOre.value().removeIgnored(Gold.value());

        /// SET IGNORED ///
        ingot.setIgnored(Kaolinite, () -> TFCItems.KAOLIN_CLAY);

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
        ingot.setIgnored(HallowedGold, MalumItems.HALLOWED_GOLD_INGOT);
        nugget.setIgnored(HallowedGold, MalumItems.HALLOWED_GOLD_NUGGET);
        block.setIgnored(HallowedGold, MalumBlocks.BLOCK_OF_HALLOWED_GOLD);

        ingot.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_INGOT);
        plate.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_PLATING);
        nugget.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_NUGGET);
        block.setIgnored(MalignantPewter, MalumBlocks.BLOCK_OF_MALIGNANT_PEWTER);

        ingot.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_INGOT);
        plate.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_PLATING);
        nugget.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_NUGGET);
        block.setIgnored(SoulstainedSteel, MalumBlocks.BLOCK_OF_SOUL_STAINED_STEEL);

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

        gemChipped.setIgnored(Lignite);
        gemFlawed.setIgnored(Lignite);
        gem.setIgnored(Lignite, () -> TFCItems.ORES.get(Ore.LIGNITE));
        gemFlawless.setIgnored(Lignite);
        gemExquisite.setIgnored(Lignite);

        gemChipped.setIgnored(Coal);
        gemFlawed.setIgnored(Coal);
        gem.setIgnored(Coal, () -> TFCItems.ORES.get(Ore.BITUMINOUS_COAL));
        gemFlawless.setIgnored(Coal);
        gemExquisite.setIgnored(Coal);

        gemChipped.setIgnored(Anthracite);
        gemFlawed.setIgnored(Anthracite);
        gem.setIgnored(Anthracite, () -> BeneathItems.CURSECOAL);
        gemFlawless.setIgnored(Anthracite);
        gemExquisite.setIgnored(Anthracite);

        ingot.value().removeIgnored(Iron.value());
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
        var tfcMetals = new HashMap<MaterialRegistryEntry, Metal>();
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

        var oxidizableMetals = new HashMap<MaterialRegistryEntry, Metal>();
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
        var tfcGradedOres = new HashMap<MaterialRegistryEntry, Ore>();
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
        var tfcOres = new HashMap<MaterialRegistryEntry, Ore>();
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

        // Firmalife
        oreSmall.setIgnored(Chromite, () -> FLBlocks.SMALL_CHROMITE);
        rawOre.setIgnored(Chromite, () -> FLItems.CHROMIUM_ORES.get(Ore.Grade.NORMAL));
        poorRawOre.setIgnored(Chromite, () -> FLItems.CHROMIUM_ORES.get(Ore.Grade.POOR));
        richRawOre.setIgnored(Chromite, () -> FLItems.CHROMIUM_ORES.get(Ore.Grade.RICH));

        blockPlated.setIgnored(Chromium, () -> FLBlocks.METALS.get(FLMetal.CHROMIUM).get(Metal.BlockType.BLOCK));
        slabPlated.setIgnored(Chromium, () -> FLBlocks.METALS.get(FLMetal.CHROMIUM).get(Metal.BlockType.BLOCK_SLAB));
        stairsPlated.setIgnored(Chromium, () -> FLBlocks.METALS.get(FLMetal.CHROMIUM).get(Metal.BlockType.BLOCK_STAIRS));

        blockPlated.setIgnored(StainlessSteel, () -> FLBlocks.METALS.get(FLMetal.STAINLESS_STEEL).get(Metal.BlockType.BLOCK));
        slabPlated.setIgnored(StainlessSteel, () -> FLBlocks.METALS.get(FLMetal.STAINLESS_STEEL).get(Metal.BlockType.BLOCK_SLAB));
        stairsPlated.setIgnored(StainlessSteel, () -> FLBlocks.METALS.get(FLMetal.STAINLESS_STEEL).get(Metal.BlockType.BLOCK_STAIRS));


        // Eidolon Materials
        ingot.setIgnored(Pewter, Registry.PEWTER_INGOT);
        nugget.setIgnored(Pewter, Registry.PEWTER_NUGGET);
        block.setIgnored(Pewter, Registry.PEWTER_BLOCK);

        // Embers Materials
        ingot.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_INGOT);
        plate.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_PLATE);
        nugget.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_NUGGET);
        block.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_BLOCK);
        GTFluids.handleNonMaterialFluids(Dawnstone.value(), RegistryManager.MOLTEN_DAWNSTONE.FLUID::get);

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

    public static void addFluidToExisting(FluidStorageKey key, int temp, MaterialRegistryEntry mat) {
        FluidProperty fluidProp = new FluidProperty();
        fluidProp.getStorage().enqueueRegistration(key, new FluidBuilder().temperature(temp));
        mat.setProperty(PropertyKey.FLUID, fluidProp);
    }

    public static void addFluidToExisting(FluidStorageKey key, int temp, MaterialRegistryEntry mat, String translationKey) {
        FluidProperty fluidProp = new FluidProperty();
        fluidProp.getStorage().enqueueRegistration(key, new FluidBuilder().temperature(temp).translation(translationKey));
        mat.setProperty(PropertyKey.FLUID, fluidProp);
    }
}
