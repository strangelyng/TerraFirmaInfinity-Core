package net.terrafirmainfinity.core.common.data.materials;

import alexthw.eidolon_repraised.registries.Registry;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.HazardProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;
import com.rekindled.embers.RegistryManager;
import com.sammy.malum.registry.common.block.MalumBlocks;
import com.sammy.malum.registry.common.item.MalumItems;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static net.terrafirmainfinity.core.common.data.materials.InfinityMaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public final class InfinityMaterials {
    /**
     * New Elements
     */
    public static Material Malice;
    public static Material Hallow;
    public static Material Soul;

    public static Material Ember;

    public static Material Unknown;

    /**
     * Alloys
     */
    public static Material Pewter;

    /**
     * Mod Materials
     */
    public static Material AndesiteAlloy; // TODO: To Remove

    public static Material MalignantPewter;
    public static Material HallowedGold;
    public static Material SoulstainedSteel;

    public static Material Dawnstone;

    public static Material Fluix;

    /**
     * Misc
     */
    public static Material Cryolite;

    public static void register() {
        Malice = new Material.Builder(InfinityCore.id("malice"))
                .element(InfinityElements.Malice)
                .buildAndRegister();

        Hallow = new Material.Builder(InfinityCore.id("hallow"))
                .element(InfinityElements.Hallow)
                .buildAndRegister();

        Soul = new Material.Builder(InfinityCore.id("soul"))
                .element(InfinityElements.Soul)
                .buildAndRegister();

        Ember = new Material.Builder(InfinityCore.id("ember"))
                .gem()
                .gas(1300)
                .color(0xF76911).secondaryColor(0x960B0B).iconSet(MaterialIconSet.QUARTZ) // TODO: Custom Material Set
                .element(InfinityElements.Ember)
                .flags(PHOSPHORESCENT)
                .buildAndRegister();

        Unknown = new Material.Builder(InfinityCore.id("unknown"))
                .element(InfinityElements.Unknown)
                .buildAndRegister();

        Pewter = new Material.Builder(InfinityCore.id("pewter"))
                .ingot()
                .liquid(new FluidBuilder().temperature(500))
                .color(0xD8D1B2).secondaryColor(0xC7B59B).iconSet(MaterialIconSet.METALLIC)
                .components(Tin, 3, Lead, 2)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.WEAK_POISON)
                .buildAndRegister();

        AndesiteAlloy = new Material.Builder(InfinityCore.id("andesite_alloy")) // TODO: Consider Removing
                .ingot()
                .components(Andesite, 1, Iron, 1)
                .color(0xC7C8B8).secondaryColor(0x839689).iconSet(MaterialIconSet.DULL)
                .flags(GENERATE_PLATE, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();

        MalignantPewter = new Material.Builder(InfinityCore.id("malignant_pewter"))
                .ingot()
                .color(0xEBC8FA).secondaryColor(0x6E2361).iconSet(MaterialIconSet.METALLIC)
                .components(Tin, 3, Lead, 2, Malice, 1)
                .flags(GENERATE_PLATE)
                .hazard(HazardProperty.HazardTrigger.INHALATION, GTMedicalConditions.POISON)
                .buildAndRegister();

        HallowedGold = new Material.Builder(InfinityCore.id("hallowed_gold"))
                .ingot()
                .color(0xFFE475).secondaryColor(0xE03D14).iconSet(MaterialIconSet.BRIGHT)
                .components(Hallow, 1, Gold, 1)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_FINE_WIRE, GENERATE_RING)
                .buildAndRegister();

        SoulstainedSteel = new Material.Builder(InfinityCore.id("soulstained_steel"))
                .ingot()
                .color(0xEE8FFF).secondaryColor(0x593B7C).iconSet(MaterialIconSet.METALLIC)
                .components(BlackSteel, 1, Soul, 1)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_ROTOR)
                .buildAndRegister();

        Dawnstone = new Material.Builder(InfinityCore.id("dawnstone"))
                .ingot()
                .color(0xFFCB70).secondaryColor(0xB84E0D).iconSet(MaterialIconSet.BRIGHT)
                .components(Gold, 1, Copper, 1)
                .flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD, GENERATE_GEAR, GENERATE_SMALL_GEAR)
                .buildAndRegister();

        Fluix = new Material.Builder(InfinityCore.id("fluix"))
                .gem(1)
                .dust()
                .liquid()
                .color(0x8F5CCB).secondaryColor(0x252F5A).iconSet(MaterialIconSet.CERTUS)
                .flags(NO_SMELTING, CRYSTALLIZABLE, DISABLE_DECOMPOSITION)
                .components(Unknown, 1, Ruby, 1, CertusQuartz, 1)
                .buildAndRegister();

        Cryolite = new Material.Builder(InfinityCore.id("cryolite"))
                .gem()
                .liquid(new FluidBuilder().temperature(1285))
                .color(0xDEDCCD).secondaryColor(0xD3CCD1).iconSet(MaterialIconSet.EMERALD)
                .components(Sodium, 3, Aluminium, 1, Fluorine, 6)
                .buildAndRegister();
    }

    public static void modifyExistingMaterials() {
        // Add Properties
        Zirconium.setProperty(PropertyKey.INGOT, new IngotProperty());
    }

    public static void setIgnoredPrefixes() {
        // TFC Materials
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
