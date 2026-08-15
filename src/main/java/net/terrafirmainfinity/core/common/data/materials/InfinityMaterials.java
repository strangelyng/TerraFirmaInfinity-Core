package net.terrafirmainfinity.core.common.data.materials;

import alexthw.eidolon_repraised.registries.Registry;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialStack;
import com.rekindled.embers.RegistryManager;
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.item.MalumItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public final class InfinityMaterials {
     public static Material Acanthite;
     public static Material Alumina;
     public static Material Anatase;
     public static Material AndesiteAlloy; // TODO: To Remove
     public static Material Arsenopyrite;
     public static Material Bismuthinite; // TFC
     public static Material BoricAcid;
     public static Material BoronCarbide;
     public static Material BoronTrioxide;
     public static Material Cryolite;
     public static Material Dawnstone;
     public static Material Ember;
     public static Material Fluix;
     public static Material Fluorapatite;
     public static Material Fluorite;
     public static Material Hafnon;
     public static Material HafniumCarbonitride;
     public static Material Hallow;
     public static Material HallowedGold;
     public static Material Malice;
     public static Material MalignantPewter;
     public static Material Manganin;
     public static Material Millerite;
     public static Material Pewter;
     public static Material Siderite; // ???
     public static Material SiliconCarbide;
     public static Material Smithsonite;
     public static Material Soul;
     public static Material SoulstainedSteel;
     public static Material Sylvite; // TFC
     public static Material Thorianite;
     public static Material Thorite;
     public static Material Unknown;
     public static Material Vanadinite;
     public static Material Witherite;
     public static Material Wolframite;
     public static Material Zircon;
     public static Material Zirconia;
     public static Material ZirconiumCarbide;
     public static Material ZirconiumDiboride;
     public static Material ZirconiumTetrachloride;
     public static Material ZirconiumUHTCComposite;

    public static void init() {
        InfinityElementMaterials.register();
        InfinityFirstDegreeMaterials.register();
        InfinitySecondDegreeMaterials.register();
        InfinityThirdDegreeMaterials.register();
    }

    public static void modifyExistingMaterials() {
        // Add Properties
        Hafnium.setProperty(PropertyKey.INGOT, new IngotProperty());
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());

        BlastProperty blastProp = new BlastProperty();
        blastProp.setBlastTemperature(2125);
        blastProp.setGasTier(BlastProperty.GasTier.MID);
        blastProp.setEUtOverride(VA[HV]);
        blastProp.setDurationOverride(1500);
        blastProp.setVacuumEUtOverride(VA[HV]);

        Zirconium.setProperty(PropertyKey.BLAST, blastProp);

        Borax.setProperty(PropertyKey.ORE, new OreProperty());

        BlackSteel.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);

        TungstenCarbide.addFlags(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS, MaterialFlags.NO_WORKING);

        // Modify Components
        Ruby.setComponents(new MaterialStack(Chromium, 1), new MaterialStack(Alumina, 1));
        Sapphire.setComponents(new MaterialStack(Alumina, 1));
        GreenSapphire.setComponents(new MaterialStack(Alumina, 1));

        // Restrict Native Metals
        Aluminium.removeProperty(PropertyKey.ORE);
        Cobalt.removeProperty(PropertyKey.ORE);
        Lithium.removeProperty(PropertyKey.ORE);
        Molybdenum.removeProperty(PropertyKey.ORE);
        Neodymium.removeProperty(PropertyKey.ORE);
        Nickel.removeProperty(PropertyKey.ORE);
        Plutonium239.removeProperty(PropertyKey.ORE);
        Thorium.removeProperty(PropertyKey.ORE);
    }

    public static void setIgnoredPrefixes() {
        // TFC Materials
        ingot.setIgnored(Iron, // Iron to Cast Iron
                () -> TFCItems.METAL_ITEMS.get(Metal.CAST_IRON).get(Metal.ItemType.INGOT).get());

        ingot.setIgnored(BlackSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLACK_STEEL).get(Metal.ItemType.INGOT).get());
        ingot.setIgnored(BlueSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.INGOT).get());
        ingot.setIgnored(RedSteel,
                () -> TFCItems.METAL_ITEMS.get(Metal.RED_STEEL).get(Metal.ItemType.INGOT).get());

        // Create Materials
        ingot.setIgnored(AndesiteAlloy, () -> AllItems.ANDESITE_ALLOY); // TODO: TO REMOVE
        ingot.setIgnored(Brass, () -> AllItems.BRASS_INGOT);
        ingot.setIgnored(Zinc, () -> AllItems.ZINC_INGOT);

        nugget.setIgnored(Brass, () -> AllItems.BRASS_NUGGET);
        nugget.setIgnored(Copper, () -> AllItems.COPPER_NUGGET);
        nugget.setIgnored(Zinc, () -> AllItems.ZINC_INGOT);

        plate.setIgnored(Brass, () -> AllItems.BRASS_SHEET);
        plate.setIgnored(Copper, () -> AllItems.COPPER_SHEET);
        plate.setIgnored(Gold, () -> AllItems.GOLDEN_SHEET);

        block.setIgnored(Brass, () -> AllBlocks.BRASS_BLOCK);
        block.setIgnored(Zinc, () -> AllBlocks.ZINC_BLOCK);

        // Malum Materials
        ingot.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_INGOT);
        nugget.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_NUGGET);
        plate.setIgnored(MalignantPewter, MalumItems.MALIGNANT_PEWTER_PLATING);
        block.setIgnored(MalignantPewter, MalumBlocks.BLOCK_OF_MALIGNANT_PEWTER);

        ingot.setIgnored(HallowedGold, MalumItems.HALLOWED_GOLD_INGOT);
        nugget.setIgnored(HallowedGold, MalumItems.HALLOWED_GOLD_NUGGET);
        block.setIgnored(HallowedGold, MalumBlocks.BLOCK_OF_HALLOWED_GOLD);

        ingot.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_INGOT);
        nugget.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_NUGGET);
        plate.setIgnored(SoulstainedSteel, MalumItems.SOUL_STAINED_STEEL_PLATING);
        block.setIgnored(SoulstainedSteel, MalumBlocks.BLOCK_OF_SOUL_STAINED_STEEL);

        // Eidolon Materials
        ingot.setIgnored(Pewter, Registry.PEWTER_INGOT);
        nugget.setIgnored(Pewter, Registry.PEWTER_NUGGET);
        block.setIgnored(Pewter, Registry.PEWTER_BLOCK);

        // Embers Materials
        ingot.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_INGOT);
        nugget.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_NUGGET);
        plate.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_PLATE);
        block.setIgnored(Dawnstone, RegistryManager.DAWNSTONE_BLOCK);

        gem.setIgnored(Ember, RegistryManager.EMBER_CRYSTAL);
    }

    public static void postInit() {
        modifyExistingMaterials();
        setIgnoredPrefixes();
    }
}
