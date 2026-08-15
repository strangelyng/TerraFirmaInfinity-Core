package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import net.terrafirmainfinity.core.common.data.materials.InfinityMaterialFlags;
import net.terrafirmainfinity.core.common.data.materials.InfinityMaterialIconType;

import java.util.function.Predicate;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.Conditions.*;

public class InfinityTagPrefix {
    public static TagPrefix doubleIngot;
    public static TagPrefix crystal;

    public static TagPrefix poorRawOre;
    public static TagPrefix richRawOre;

    // Hot Materials
    public static TagPrefix nuggetHot;
    public static TagPrefix plateDenseHot;
    public static TagPrefix plateDoubleHot;
    public static TagPrefix plateHot;
    public static TagPrefix foilHot;
    public static TagPrefix rodLongHot;
    public static TagPrefix rodHot;
    public static TagPrefix boltHot;
    public static TagPrefix ringHot;
    public static TagPrefix gearSmallHot;
    public static TagPrefix gearHot;

    // Green Compacts
    public static TagPrefix ingotPowderCompact;
    public static TagPrefix nuggetPowderCompact;
    public static TagPrefix plateDensePowderCompact;
    public static TagPrefix plateDoublePowderCompact;
    public static TagPrefix platePowderCompact;
    public static TagPrefix foilPowderCompact;
    public static TagPrefix rodLongPowderCompact;
    public static TagPrefix rodPowderCompact;
    public static TagPrefix boltPowderCompact;
    public static TagPrefix ringPowderCompact;
    public static TagPrefix gearSmallPowderCompact;
    public static TagPrefix gearPowderCompact;

    public static TagPrefix toolHeadBuzzsawPowderCompact;
    public static TagPrefix toolHeadScrewdriverPowderCompact;
    public static TagPrefix toolHeadDrillPowderCompact;
    public static TagPrefix toolHeadChainsawPowderCompact;
    public static TagPrefix toolHeadWrenchPowderCompact;
    public static TagPrefix toolHeadWireCutterPowderCompact;

    private static final Predicate<Material> hotTagCondition = hasBlastProperty.and(
                    mat -> mat.getProperty(PropertyKey.BLAST).getBlastTemperature() > 1750).and(
                        mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS));

    public static void init() {
        doubleIngot = new TagPrefix("double_ingot")
                .defaultTagPath("double_ingots/%s")
                .unformattedTagPath("double_ingots")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 2)
                .materialIconType(MaterialIconType.ingotDouble)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasFlag(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT));

        crystal = new TagPrefix("crystal")
                .defaultTagPath("crystals/%s")
                .unformattedTagPath("crystals")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(InfinityMaterialIconType.crystal)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(material -> material.hasFlag(InfinityMaterialFlags.GENERATE_CRYSTAL));

        poorRawOre = new TagPrefix("poor_raw_ore")
                .idPattern("poor_raw_%s")
                .defaultTagPath("poor_raw_materials/%s")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .unformattedTagPath("poor_raw_materials")
                .materialIconType(InfinityMaterialIconType.poorRawOre)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasOreProperty);

        richRawOre = new TagPrefix("rich_raw_ore")
                .idPattern("rich_raw_%s")
                .defaultTagPath("rich_raw_materials/%s")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .unformattedTagPath("rich_raw_materials")
                .materialIconType(InfinityMaterialIconType.richRawOre)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasOreProperty);

        nuggetHot = new TagPrefix("nugget_hot")
                .idPattern("hot_%s_nugget")
                .defaultTagPath("hot_nuggets/%s")
                .unformattedTagPath("hot_nuggets")
                .materialAmount(GTValues.M / 9)
                .materialIconType(InfinityMaterialIconType.nuggetHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasIngotProperty.and(hotTagCondition));

        plateDenseHot = new TagPrefix("plate_dense_hot")
                .idPattern("hot_dense_%s_plate")
                .defaultTagPath("hot_dense_plates/%s")
                .unformattedTagPath("hot_dense_plates")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 9)
                .materialIconType(InfinityMaterialIconType.plateDenseHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_DENSE)));

        plateDoubleHot = new TagPrefix("plate_double_hot")
                .idPattern("hot_double_%s_plate")
                .defaultTagPath("hot_double_plates/%s")
                .unformattedTagPath("hot_double_plates")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 2)
                .materialIconType(InfinityMaterialIconType.plateDoubleHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_PLATE)));

        plateHot = new TagPrefix("plate_hot")
                .idPattern("hot_%s_plate")
                .defaultTagPath("hot_plates/%s")
                .unformattedTagPath("hot_plates")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(InfinityMaterialIconType.plateHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_PLATE)));

        foilHot = new TagPrefix("foil_hot")
                .idPattern("hot_%s_foil")
                .defaultTagPath("hot_foils/%s")
                .unformattedTagPath("hot_foils")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 4)
                .materialIconType(InfinityMaterialIconType.foilHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_FOIL)));

        rodLongHot = new TagPrefix("rod_long_hot")
                .idPattern("hot_long_%s_rod")
                .defaultTagPath("hot_long_rods/%s")
                .unformattedTagPath("hot_long_rods")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(InfinityMaterialIconType.rodLongHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_LONG_ROD)));

        rodHot = new TagPrefix("rod_hot")
                .idPattern("hot_%s_rod")
                .defaultTagPath("hot_rods/%s")
                .unformattedTagPath("hot_rods")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(InfinityMaterialIconType.rodHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_ROD)));

        boltHot = new TagPrefix("bolt_hot")
                .idPattern("hot_%s_bolt")
                .defaultTagPath("hot_bolts/%s")
                .unformattedTagPath("hot_bolts")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 8)
                .materialIconType(InfinityMaterialIconType.boltHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW)));

        ringHot = new TagPrefix("ring_hot")
                .idPattern("hot_%s_ring")
                .defaultTagPath("hot_rings/%s")
                .unformattedTagPath("hot_rings")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 4)
                .materialIconType(InfinityMaterialIconType.ringHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_RING)));

        gearSmallHot = new TagPrefix("gear_small_hot")
                .idPattern("hot_small_%s_gear")
                .defaultTagPath("hot_small_gears/%s")
                .unformattedTagPath("hot_small_gears")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(InfinityMaterialIconType.gearSmallHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_SMALL_GEAR)));

        gearHot = new TagPrefix("gear_hot")
                .idPattern("hot_%s_gear")
                .defaultTagPath("hot_gears/%s")
                .unformattedTagPath("hot_gears")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 4)
                .materialIconType(InfinityMaterialIconType.gearHot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(hotTagCondition).and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_GEAR)));

        ingotPowderCompact = new TagPrefix("ingot_powder_compact")
                .idPattern("%s_ingot_powder_compact")
                .defaultTagPath("green_compacts/ingots/%s")
                .unformattedTagPath("green_compacts/ingots")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(MaterialIconType.ingot)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasIngotProperty.and(material ->
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        nuggetPowderCompact = new TagPrefix("nugget_powder_compact")
                .idPattern("%s_nugget_powder_compact")
                .defaultTagPath("green_compacts/nuggets/%s")
                .unformattedTagPath("green_compacts/nuggets")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 9)
                .materialIconType(MaterialIconType.nugget)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasIngotProperty.and(material ->
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        plateDensePowderCompact = new TagPrefix("plate_dense_powder_compact")
                .idPattern("dense_%s_plate_powder_compact")
                .defaultTagPath("green_compacts/dense_plates/%s")
                .unformattedTagPath("green_compacts/dense_plates")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 9)
                .materialIconType(MaterialIconType.plateDense)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                            material.hasFlag(MaterialFlags.GENERATE_DENSE) &&
                                    material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        plateDoublePowderCompact = new TagPrefix("plate_double_powder_compact")
                .idPattern("double_%s_plate_powder_compact")
                .defaultTagPath("green_compacts/double_plates/%s")
                .unformattedTagPath("green_compacts/double_plates")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 2)
                .materialIconType(MaterialIconType.plateDouble)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_PLATE) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        platePowderCompact = new TagPrefix("plate_powder_compact")
                .idPattern("%s_plate_powder_compact")
                .defaultTagPath("green_compacts/plates/%s")
                .unformattedTagPath("green_compacts/plates")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(MaterialIconType.plate)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_PLATE) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        foilPowderCompact = new TagPrefix("foil_powder_compact")
                .idPattern("%s_foil_powder_compact")
                .defaultTagPath("green_compacts/foils/%s")
                .unformattedTagPath("green_compacts/foils")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 4)
                .materialIconType(MaterialIconType.foil)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_FOIL) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        rodLongPowderCompact = new TagPrefix("rod_long_powder_compact")
                .idPattern("long_%s_rod_powder_compact")
                .defaultTagPath("green_compacts/long_rods/%s")
                .unformattedTagPath("green_compacts/long_rods")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(MaterialIconType.rodLong)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_LONG_ROD) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        rodPowderCompact = new TagPrefix("rod_powder_compact")
                .idPattern("%s_rod_powder_compact")
                .defaultTagPath("green_compacts/rods/%s")
                .unformattedTagPath("green_compacts/rods")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 2)
                .materialIconType(MaterialIconType.rod)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_ROD) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        boltPowderCompact = new TagPrefix("bolt_powder_compact")
                .idPattern("%s_bolt_powder_compact")
                .defaultTagPath("green_compacts/bolts/%s")
                .unformattedTagPath("green_compacts/bolts")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 8)
                .materialIconType(MaterialIconType.bolt)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_BOLT_SCREW) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        ringPowderCompact = new TagPrefix("ring_powder_compact")
                .idPattern("%s_ring_powder_compact")
                .defaultTagPath("green_compacts/rings/%s")
                .unformattedTagPath("green_compacts/rings")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M / 4)
                .materialIconType(MaterialIconType.ring)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_RING) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        gearSmallPowderCompact = new TagPrefix("gear_small_powder_compact")
                .idPattern("small_%s_gear_powder_compact")
                .defaultTagPath("green_compacts/small_gears/%s")
                .unformattedTagPath("green_compacts/small_gears")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(MaterialIconType.gearSmall)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_SMALL_GEAR) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        gearPowderCompact = new TagPrefix("gear_powder_compact")
                .idPattern("%s_gear_powder_compact")
                .defaultTagPath("green_compacts/gears/%s")
                .unformattedTagPath("green_compacts/gears")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 4)
                .materialIconType(MaterialIconType.gear)
                .unificationEnabled(true)
                .generateItem(true)
                .generationCondition(hasDustProperty.and(material ->
                        material.hasFlag(MaterialFlags.GENERATE_GEAR) &&
                                material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)));

        toolHeadBuzzsawPowderCompact = new TagPrefix("tool_head_buzzsaw_powder_compact")
                .idPattern("%s_buzzsaw_blade_powder_compact")
                .defaultTagPath("green_compacts/buzzsaw_blades/%s")
                .unformattedTagPath("green_compacts/buzzsaw_blades")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 4)
                .materialIconType(MaterialIconType.toolHeadBuzzSaw)
                .unificationEnabled(true)
                .generationCondition(hasDustProperty.and(hasToolProperty.and(
                        material -> material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)).and(
                            material -> material.getProperty(PropertyKey.TOOL).hasType(GTToolType.BUZZSAW_LV))));

        toolHeadScrewdriverPowderCompact = new TagPrefix("tool_head_screwdriver_powder_compact")
                .idPattern("%s_screwdriver_tip_powder_compact")
                .defaultTagPath("green_compacts/screwdriver_tips/%s")
                .unformattedTagPath("green_compacts/screwdriver_tips")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M)
                .materialIconType(MaterialIconType.toolHeadScrewdriver)
                .unificationEnabled(true)
                .generationCondition(hasDustProperty.and(hasToolProperty.and(
                        material -> material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)).and(
                            material -> material.getProperty(PropertyKey.TOOL).hasType(GTToolType.SCREWDRIVER_LV))));

        toolHeadDrillPowderCompact = new TagPrefix("tool_head_drill_powder_compact")
                .idPattern("%s_drill_head_powder_compact")
                .defaultTagPath("green_compacts/drill_heads/%s")
                .unformattedTagPath("green_compacts/drill_heads")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 4)
                .materialIconType(MaterialIconType.toolHeadDrill)
                .unificationEnabled(true)
                .generationCondition(hasDustProperty.and(hasToolProperty.and(
                        material -> material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)).and(
                            material -> material.getProperty(PropertyKey.TOOL).hasType(GTToolType.DRILL_LV))));

        toolHeadChainsawPowderCompact = new TagPrefix("tool_head_chainsaw_powder_compact")
                .idPattern("%s_chainsaw_head_powder_compact")
                .defaultTagPath("green_compacts/chainsaw_heads/%s")
                .unformattedTagPath("green_compacts/chainsaw_heads")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 2)
                .materialIconType(MaterialIconType.toolHeadChainsaw)
                .unificationEnabled(true)
                .generationCondition(hasDustProperty.and(hasToolProperty.and(
                        material -> material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)).and(
                            material -> material.getProperty(PropertyKey.TOOL).hasType(GTToolType.CHAINSAW_LV))));

        toolHeadWrenchPowderCompact = new TagPrefix("tool_head_wrench_powder_compact")
                .idPattern("%s_wrench_tip_powder_compact")
                .defaultTagPath("green_compacts/wrench_tips/%s")
                .unformattedTagPath("green_compacts/wrench_tips")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 4)
                .materialIconType(MaterialIconType.toolHeadWrench)
                .unificationEnabled(true)
                .generationCondition(hasDustProperty.and(hasToolProperty.and(
                        material -> material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)).and(
                            material -> material.getProperty(PropertyKey.TOOL).hasType(GTToolType.WRENCH_LV))));

        toolHeadWireCutterPowderCompact = new TagPrefix("tool_head_wire_cutter_powder_compact")
                .idPattern("%s_wire_cutter_head_powder_compact")
                .defaultTagPath("green_compacts/wire_cutter_heads/%s")
                .unformattedTagPath("green_compacts/wire_cutter_heads")
                .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
                .materialAmount(GTValues.M * 4)
                .materialIconType(MaterialIconType.toolHeadWireCutter)
                .unificationEnabled(true)
                .generationCondition(hasDustProperty.and(hasToolProperty.and(
                        material -> material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)).and(
                                material -> material.getProperty(PropertyKey.TOOL).hasType(GTToolType.WIRE_CUTTER_LV))));

        // Modify existing for tool heads
        // Special thanks to TFG for this workaround (prevents crash on startup due to missing inputs)
        TagPrefix.toolHeadBuzzSaw.generationCondition(TagPrefix.toolHeadBuzzSaw.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_BUZZSAW_BLADE)));
        TagPrefix.toolHeadChainsaw.generationCondition(TagPrefix.toolHeadChainsaw.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_CHAINSAW_HEAD)));
        TagPrefix.toolHeadDrill.generationCondition(TagPrefix.toolHeadDrill.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_DRILL_HEAD)));
        TagPrefix.toolHeadScrewdriver.generationCondition(TagPrefix.toolHeadScrewdriver.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_SCREWDRIVER_HEAD)));
        TagPrefix.toolHeadWrench.generationCondition(TagPrefix.toolHeadWrench.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_WRENCH_HEAD)));
        TagPrefix.toolHeadWireCutter.generationCondition(TagPrefix.toolHeadWireCutter.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_WIRE_CUTTER_HEAD)));
    }
}
