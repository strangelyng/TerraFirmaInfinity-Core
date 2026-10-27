package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.data.tag.TagType;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.registry.registrate.entry.TagPrefixEntry;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.util.Helpers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.terrafirmainfinity.core.common.data.item.InfinityToolTypes;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterialFlags;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.Conditions.*;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityTagPrefix {
    public static final Predicate<Material> hasElectrodeProperty = mat -> mat.hasProperty(InfinityPropertyKeys.ELECTRODE);

    /** TFC Items */
    public static final TagPrefixEntry ingotDouble = InfinityRegistrate.tagPrefix("ingot_double")
            .idPattern("%s_double_ingot")
            .defaultTagPath("double_ingots/%s")
            .unformattedTagPath("double_ingots")
            .lang("%s Double Ingot")
            .materialAmount(GTValues.M*2)
            .materialIconType(MaterialIconType.ingotDouble)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasIngotProperty.and(
                    mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT)
            ))
            .register();

    public static final TagPrefixEntry powder = InfinityRegistrate.tagPrefix("powder")
            .idPattern("%s_powder")
            .defaultTagPath("powders/%s")
            .unformattedTagPath("powders")
            .lang("%s Powder")
            .materialAmount(GTValues.M / 4)
            .materialIconType(InfinityMaterialIconType.powder)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty)
            .register();

    public static final TagPrefixEntry oreSmall = InfinityRegistrate.tagPrefix("small_ore")
            .materialAmount(GTValues.M / 9)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry poorRawOre = InfinityRegistrate.tagPrefix("poor_raw_ore")
            .idPattern("poor_raw_%s")
            .defaultTagPath("poor_raw_materials/%s")
            .unformattedTagPath("poor_raw_materials")
            .lang("Poor Raw %s")
            .materialIconType(InfinityMaterialIconType.poorRawOre)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty)
            .register();

    public static final TagPrefixEntry richRawOre = InfinityRegistrate.tagPrefix("rich_raw_ore")
            .idPattern("rich_raw_%s")
            .defaultTagPath("rich_raw_materials/%s")
            .unformattedTagPath("rich_raw_materials")
            .lang("Rich Raw %s")
            .materialIconType(InfinityMaterialIconType.richRawOre)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty)
            .register();

    /** TFC Tool Heads */
    public static final TagPrefixEntry toolHeadPickaxe = InfinityRegistrate.tagPrefix("pickaxe_head")
            .lang("%s Pickaxe Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadPickaxe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.PICKAXE)))
            .register();

    // TODO: Propick

    public static final TagPrefixEntry toolHeadAxe = InfinityRegistrate.tagPrefix("axe_head")
            .lang("%s Axe Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadAxe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.AXE)))
            .register();

    public static final TagPrefixEntry toolHeadShovel = InfinityRegistrate.tagPrefix("shovel_head")
            .lang("%s Shovel Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadShovel)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SHOVEL)))
            .register();

    public static final TagPrefixEntry toolHeadHoe = InfinityRegistrate.tagPrefix("hoe_head")
            .lang("%s Hoe Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadHoe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.HOE)))
            .register();

    public static final TagPrefixEntry toolHeadChisel = InfinityRegistrate.tagPrefix("chisel_head")
            .lang("%s Chisel Head")
            .materialAmount(GTValues.M)
            .materialIconType(InfinityMaterialIconType.toolHeadChisel)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(InfinityToolTypes.CHISEL)))
            .register();

    public static final TagPrefixEntry toolHeadHammer = InfinityRegistrate.tagPrefix("hammer_head")
            .lang("%s Hammer Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadHammer)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.HARD_HAMMER)))
            .register();

    public static final TagPrefixEntry toolHeadSaw = InfinityRegistrate.tagPrefix("saw_blade")
            .lang("%s Saw Blade")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadSaw)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasNoCraftingToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SAW)))
            .register();

    public static final TagPrefixEntry toolHeadKnife = InfinityRegistrate.tagPrefix("knife_blade")
            .lang("%s Knife Blade")
            .materialAmount(GTValues.M)
            .materialIconType(InfinityMaterialIconType.toolHeadKnife)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.KNIFE)))
            .register();

    public static final TagPrefixEntry toolHeadScythe = InfinityRegistrate.tagPrefix("scythe_blade")
            .lang("%s Scythe Blade")
            .materialAmount(GTValues.M*2)
            .materialIconType(MaterialIconType.toolHeadScythe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SCYTHE)))
            .register();

    // TODO: Javelin

    public static final TagPrefixEntry toolHeadSword = InfinityRegistrate.tagPrefix("sword_blade")
            .lang("%s Sword Blade")
            .materialAmount(GTValues.M*2)
            .materialIconType(MaterialIconType.toolHeadSword)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SWORD)))
            .register();

    public static final TagPrefixEntry toolHeadMace = InfinityRegistrate.tagPrefix("mace_head")
            .lang("%s Mace Head")
            .materialAmount(GTValues.M*2)
            .materialIconType(InfinityMaterialIconType.toolHeadMace)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(InfinityToolTypes.MACE)))
            .register();

    // TODO: Fish Hook

    /** Missing GT Tool Heads */
    public static final TagPrefixEntry toolHeadFile = InfinityRegistrate.tagPrefix("file_head")
            .lang("%s File Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadFile)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasNoCraftingToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.FILE)))
            .register();

    public static final TagPrefixEntry toolHeadMiningHammer = InfinityRegistrate.tagPrefix("mining_hammer_head")
            .lang("%s Mining Hammer Head")
            .materialAmount(GTValues.M*2)
            .materialIconType(InfinityMaterialIconType.toolHeadMiningHammer)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.MINING_HAMMER)))
            .register();

    public static final TagPrefixEntry toolHeadSpade = InfinityRegistrate.tagPrefix("spade_head")
            .lang("%s Spade Head")
            .materialAmount(GTValues.M*2)
            .materialIconType(InfinityMaterialIconType.toolHeadSpade)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SPADE)))
            .register();

    public static final TagPrefixEntry toolHeadButcheryKnife = InfinityRegistrate.tagPrefix("butchery_knife_blade")
            .lang("%s Butchery Knife Blade")
            .materialAmount(GTValues.M)
            .materialIconType(InfinityMaterialIconType.toolHeadButcheryKnife)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.BUTCHERY_KNIFE)))
            .register();

    // Additional TFC Items

    public static final TagPrefixEntry anvil = InfinityRegistrate.tagPrefix("anvil")
            .materialAmount(GTValues.M * 14)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry bars = InfinityRegistrate.tagPrefix("bars")
            .materialAmount(GTValues.M / 4)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry bell = InfinityRegistrate.tagPrefix("bell")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry chain = InfinityRegistrate.tagPrefix("chain")
            .defaultTagPath("chains/%s")
            .unformattedTagPath("chains")
            .materialAmount(GTValues.M / 16)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry lamp = InfinityRegistrate.tagPrefix("lamp")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry lampUnfinished = InfinityRegistrate.tagPrefix("lamp_unfinished")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry trapdoor = InfinityRegistrate.tagPrefix("trapdoor")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry grate = InfinityRegistrate.tagPrefix("grate")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry grateExposed = InfinityRegistrate.tagPrefix("exposed_grate")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry grateWeathered = InfinityRegistrate.tagPrefix("weathered_grate")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry grateOxidized = InfinityRegistrate.tagPrefix("oxidized_grate")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry blockPlated = InfinityRegistrate.tagPrefix("plated_block")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry blockPlatedExposed = InfinityRegistrate.tagPrefix("exposed_plated_block")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry blockPlatedWeathered = InfinityRegistrate.tagPrefix("weathered_plated_block")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry blockPlatedOxidized = InfinityRegistrate.tagPrefix("oxidized_plated_block")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry slabPlated = InfinityRegistrate.tagPrefix("plated_slab")
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry slabPlatedExposed = InfinityRegistrate.tagPrefix("exposed_plated_slab")
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry slabPlatedWeathered = InfinityRegistrate.tagPrefix("weathered_plated_slab")
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry slabPlatedOxidized = InfinityRegistrate.tagPrefix("oxidized_plated_slab")
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry stairsPlated = InfinityRegistrate.tagPrefix("plated_stairs")
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry stairsPlatedExposed = InfinityRegistrate.tagPrefix("exposed_plated_stairs")
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry stairsPlatedWeathered = InfinityRegistrate.tagPrefix("weathered_plated_stairs")
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true)
            .register();

    public static final TagPrefixEntry stairsPlatedOxidized = InfinityRegistrate.tagPrefix("oxidized_plated_stairs")
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true)
            .register();

    /** Embers Items */
    public static final TagPrefixEntry aspectus = InfinityRegistrate.tagPrefix("aspectus")
            .materialAmount(GTValues.M)
            .unificationEnabled(true)
            .register();

    public static void modifyExistingTagPrefixes() {
        TagPrefix.toolHeadBuzzSaw.value().materialAmount(GTValues.M*2);
        TagPrefix.toolHeadScrewdriver.value().materialAmount(GTValues.M);
        TagPrefix.toolHeadWrench.value().materialAmount(GTValues.M*2);
        TagPrefix.toolHeadWireCutter.value().materialAmount(GTValues.M*2);

        TagPrefix.toolHeadWrench.value().secondaryMaterials().clear();
        TagPrefix.toolHeadWireCutter.value().secondaryMaterials().clear();

        TagPrefix.toolHeadBuzzSaw.value().generationCondition(TagPrefix.toolHeadBuzzSaw.value().generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_BUZZSAW_BLADE)));
        TagPrefix.toolHeadChainsaw.value().generationCondition(TagPrefix.toolHeadChainsaw.value().generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_CHAINSAW_HEAD)));
        TagPrefix.toolHeadDrill.value().generationCondition(TagPrefix.toolHeadDrill.value().generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_DRILL_HEAD)));
        TagPrefix.toolHeadScrewdriver.value().generationCondition(TagPrefix.toolHeadScrewdriver.value().generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_SCREWDRIVER_HEAD)));
        TagPrefix.toolHeadWrench.value().generationCondition(TagPrefix.toolHeadWrench.value().generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_WRENCH_HEAD)));
        TagPrefix.toolHeadWireCutter.value().generationCondition(TagPrefix.toolHeadWireCutter.value().generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_WIRE_CUTTER_HEAD)));

        // Add Tags to existing tag prefixes
        // This is bad and uses internal API
        // TODO: Find alternative
        TagPrefix.turbineBlade.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("turbine_blades/%s", false),
                        TagType.withNoFormatter("turbine_blades", false)));
        TagPrefix.pipeTinyFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("tiny_fluid_pipes/%s", false),
                        TagType.withNoFormatter("tiny_fluid_pipes", false)));
        TagPrefix.pipeSmallFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("small_fluid_pipes/%s", false),
                        TagType.withNoFormatter("small_fluid_pipes", false)));
        TagPrefix.pipeNormalFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("normal_fluid_pipes/%s", false),
                        TagType.withNoFormatter("normal_fluid_pipes", false)));
        TagPrefix.pipeLargeFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("large_fluid_pipes/%s", false),
                        TagType.withNoFormatter("large_fluid_pipes", false)));
        TagPrefix.pipeHugeFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("huge_fluid_pipes/%s", false),
                        TagType.withNoFormatter("huge_fluid_pipes", false)));
        TagPrefix.pipeQuadrupleFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("quadruple_fluid_pipes/%s", false),
                        TagType.withNoFormatter("quadruple_fluid_pipes", false)));
        TagPrefix.pipeNonupleFluid.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("nonuple_fluid_pipes/%s", false),
                        TagType.withNoFormatter("nonuple_fluid_pipes", false)));

        TagPrefix.turbineBlade.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("turbine_blades/%s", false),
                        TagType.withNoFormatter("turbine_blades", false)));
        TagPrefix.pipeSmallItem.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("small_item_pipes/%s", false),
                        TagType.withNoFormatter("small_item_pipes", false)));
        TagPrefix.pipeNormalItem.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("normal_item_pipes/%s", false),
                        TagType.withNoFormatter("normal_item_pipes", false)));
        TagPrefix.pipeLargeItem.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("large_item_pipes/%s", false),
                        TagType.withNoFormatter("large_item_pipes", false)));
        TagPrefix.pipeHugeItem.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("huge_item_pipes/%s", false),
                        TagType.withNoFormatter("huge_item_pipes", false)));
        TagPrefix.pipeSmallRestrictive.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("small_restrictive_pipes/%s", false),
                        TagType.withNoFormatter("small_restrictive_pipes", false)));
        TagPrefix.pipeNormalRestrictive.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("normal_restrictive_pipes/%s", false),
                        TagType.withNoFormatter("normal_restrictive_pipes", false)));
        TagPrefix.pipeLargeRestrictive.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("large_restrictive_pipes/%s", false),
                        TagType.withNoFormatter("large_restrictive_pipes", false)));
        TagPrefix.pipeHugeRestrictive.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("huge_restrictive_pipes/%s", false),
                        TagType.withNoFormatter("huge_restrictive_pipes", false)));

        TagPrefix.wireGtSingle.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("single_wires/%s", false),
                        TagType.withNoFormatter("single_wires", false)));
        TagPrefix.wireGtDouble.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("double_wires/%s", false),
                        TagType.withNoFormatter("double_wires", false)));
        TagPrefix.wireGtQuadruple.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("quadruple_wires/%s", false),
                        TagType.withNoFormatter("quadruple_wires", false)));
        TagPrefix.wireGtOctal.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("octal_wires/%s", false),
                        TagType.withNoFormatter("octal_wires", false)));
        TagPrefix.wireGtHex.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("hex_wires/%s", false),
                        TagType.withNoFormatter("hex_wires", false)));

        TagPrefix.cableGtSingle.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("single_cables/%s", false),
                        TagType.withNoFormatter("single_cables", false)));
        TagPrefix.cableGtDouble.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("double_cables/%s", false),
                        TagType.withNoFormatter("double_cables", false)));
        TagPrefix.cableGtQuadruple.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("quadruple_cables/%s", false),
                        TagType.withNoFormatter("quadruple_cables", false)));
        TagPrefix.cableGtOctal.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("octal_cables/%s", false),
                        TagType.withNoFormatter("octal_cables", false)));
        TagPrefix.cableGtHex.value().setTags(
                List.of(
                        TagType.withDefaultFormatter("hex_cables/%s", false),
                        TagType.withNoFormatter("hex_cables", false)));

        modifyExistingOres();
    }

    // TFC Stone Types
    public static TagPrefixEntry oreGabbro;
    public static TagPrefixEntry oreShale;
    public static TagPrefixEntry oreClaystone;
    public static TagPrefixEntry oreLimestone;
    public static TagPrefixEntry oreConglomerate;
    public static TagPrefixEntry oreDolomite;
    public static TagPrefixEntry oreChert;
    public static TagPrefixEntry oreChalk;
    public static TagPrefixEntry oreRhyolite;
    public static TagPrefixEntry oreDacite;
    public static TagPrefixEntry oreQuartzite;
    public static TagPrefixEntry oreSlate;
    public static TagPrefixEntry orePhyllite;
    public static TagPrefixEntry oreSchist;
    public static TagPrefixEntry oreGneiss;

    // TFC Sand Types
    public static TagPrefixEntry oreSandBrown;
    public static TagPrefixEntry oreSandWhite;
    public static TagPrefixEntry oreSandBlack;
    public static TagPrefixEntry oreSandRed;
    public static TagPrefixEntry oreSandYellow;
    public static TagPrefixEntry oreSandGreen;
    public static TagPrefixEntry oreSandPink;

    public static TagPrefixEntry oreSandstoneBrown;
    public static TagPrefixEntry oreSandstoneWhite;
    public static TagPrefixEntry oreSandstoneBlack;
    public static TagPrefixEntry oreSandstoneRed;
    public static TagPrefixEntry oreSandstoneYellow;
    public static TagPrefixEntry oreSandstoneGreen;
    public static TagPrefixEntry oreSandstonePink;

    public static void modifyExistingOres() {
        // Remove Unwanted Ore TagPrefixes
        TagPrefix.ORES.remove(TagPrefix.ore.value()); // Vanilla Stone
        TagPrefix.ORES.remove(TagPrefix.oreRedGranite.value());
        TagPrefix.ORES.remove(TagPrefix.oreDeepslate.value());
        TagPrefix.ORES.remove(TagPrefix.oreSand.value());
        TagPrefix.ORES.remove(TagPrefix.oreRedSand.value());
        TagPrefix.ORES.remove(TagPrefix.oreGravel.value());
        TagPrefix.ORES.remove(TagPrefix.oreNetherrack.value());
        TagPrefix.ORES.remove(TagPrefix.oreBlackstone.value());
        TagPrefix.ORES.remove(TagPrefix.oreEndstone.value());

        convertOreToTFCRock(TagPrefix.oreGranite, Rock.GRANITE);
        convertOreToTFCRock(TagPrefix.oreDiorite, Rock.DIORITE);
        convertOreToTFCRock(TagPrefix.oreAndesite, Rock.ANDESITE);
        convertOreToTFCRock(TagPrefix.oreMarble, Rock.MARBLE);
        convertOreToTFCRock(TagPrefix.oreTuff, Rock.TUFF);
        convertOreToTFCRock(TagPrefix.oreBasalt, Rock.BASALT);
    }

    public static void createTFCOres() {
        oreGabbro = createTFCOreTagPrefix(Rock.GABBRO);
        oreShale = createTFCOreTagPrefix(Rock.SHALE);
        oreClaystone = createTFCOreTagPrefix(Rock.CLAYSTONE);
        oreLimestone = createTFCOreTagPrefix(Rock.LIMESTONE);
        oreConglomerate = createTFCOreTagPrefix(Rock.CONGLOMERATE);
        oreDolomite = createTFCOreTagPrefix(Rock.DOLOMITE);
        oreChert = createTFCOreTagPrefix(Rock.CHERT);
        oreChalk = createTFCOreTagPrefix(Rock.CHALK);
        oreRhyolite = createTFCOreTagPrefix(Rock.RHYOLITE);
        oreDacite = createTFCOreTagPrefix(Rock.DACITE);
        oreQuartzite = createTFCOreTagPrefix(Rock.QUARTZITE);
        oreSlate = createTFCOreTagPrefix(Rock.SLATE);
        orePhyllite = createTFCOreTagPrefix(Rock.PHYLLITE);
        oreSchist = createTFCOreTagPrefix(Rock.SCHIST);
        oreGneiss = createTFCOreTagPrefix(Rock.GNEISS);

        oreSandBrown = createTFCSandOreTagPrefix(SandBlockType.BROWN);
        oreSandBlack = createTFCSandOreTagPrefix(SandBlockType.BLACK);
        oreSandWhite = createTFCSandOreTagPrefix(SandBlockType.WHITE);
        oreSandRed = createTFCSandOreTagPrefix(SandBlockType.RED);
        oreSandGreen = createTFCSandOreTagPrefix(SandBlockType.GREEN);
        oreSandYellow = createTFCSandOreTagPrefix(SandBlockType.YELLOW);
        oreSandPink = createTFCSandOreTagPrefix(SandBlockType.PINK);

        oreSandstoneBrown = createTFCSandstoneOreTagPrefix(SandBlockType.BROWN);
        oreSandstoneBlack = createTFCSandstoneOreTagPrefix(SandBlockType.BLACK);
        oreSandstoneWhite = createTFCSandstoneOreTagPrefix(SandBlockType.WHITE);
        oreSandstoneRed = createTFCSandstoneOreTagPrefix(SandBlockType.RED);
        oreSandstoneGreen = createTFCSandstoneOreTagPrefix(SandBlockType.GREEN);
        oreSandstoneYellow = createTFCSandstoneOreTagPrefix(SandBlockType.YELLOW);
        oreSandstonePink = createTFCSandstoneOreTagPrefix(SandBlockType.PINK);
    }

    public static void init() {
        createTFCOres();
    }

    private static Supplier<BlockState> blockStateSupplier(ResourceLocation resLoc) {
        return () -> BuiltInRegistries.BLOCK.get(resLoc).defaultBlockState();
    }

    private static ResourceLocation getTFCRawRock(Rock rock) {
        return Helpers.identifier("rock/raw/" + rock.getSerializedName());
    }

    private static TagPrefixEntry createTFCOreTagPrefix(Rock rock) {
        String name = rock.getSerializedName();

        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        ResourceLocation resLoc = getTFCRawRock(rock);

        return InfinityRegistrate.oreTagPrefix(name, BlockTags.MINEABLE_WITH_PICKAXE)
                .lang(formattedName + " %s Ore")
                .registerOre(blockStateSupplier(resLoc), GTMaterials.Stone,
                        BlockBehaviour.Properties.of()
                                .mapColor(rock.color())
                                .requiresCorrectToolForDrops()
                                .strength(rock.category().hardness(6.5f), 10F),
                        ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "block/" + resLoc.getPath()),
                        false, false, true)
                .generationCondition(hasOreProperty.and(mat -> !mat.hasFlag(InfinityMaterialFlags.GENERATE_SAND_ORES)))
                .register();
    }

    private static ResourceLocation getTFCSand(SandBlockType sand) {
        return Helpers.identifier("sand/" + sand.name().toLowerCase(Locale.ROOT));
    }

    // TODO: Stop these from generating if material does not have the GENERATE_SAND_ORES material flag
    private static TagPrefixEntry createTFCSandOreTagPrefix(SandBlockType sand) {
        String name = sand.name().toLowerCase(Locale.ROOT);

        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        ResourceLocation resLoc = getTFCSand(sand);

        return InfinityRegistrate.oreTagPrefix(name, BlockTags.MINEABLE_WITH_SHOVEL)
                .lang(formattedName + " %s Ore")
                .registerOre(blockStateSupplier(resLoc), GTMaterials.QuartzSand,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)
                                .mapColor(sand.getMaterialColor())
                                .requiresCorrectToolForDrops()
                                .strength(0.5F)
                                .sound(SoundType.SAND),
                        ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "block/" + resLoc.getPath()),
                        false, true, false)
                .generationCondition(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_SAND_ORES))
                .register(); // This doesn't do anything atm
    }

    private static ResourceLocation getTFCSandstone(SandBlockType sand) {
        return Helpers.identifier("raw_sandstone/" + sand.name().toLowerCase(Locale.ROOT));
    }

    private static TagPrefixEntry createTFCSandstoneOreTagPrefix(SandBlockType sand) {
        String name = sand.name().toLowerCase(Locale.ROOT);

        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        ResourceLocation resLoc = getTFCSandstone(sand);

        return InfinityRegistrate.oreTagPrefix(name + "_sandstone", BlockTags.MINEABLE_WITH_PICKAXE)
                .lang(formattedName + " Sandstone %s Ore")
                .registerOre(blockStateSupplier(resLoc), GTMaterials.Stone,
                        BlockBehaviour.Properties.of()
                                .mapColor(sand.getMaterialColor())
                                .requiresCorrectToolForDrops()
                                .strength(0.8F),
                        ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "block/" + resLoc.getPath()),
                        false, false, false)
                .generationCondition(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_SAND_ORES))
                .register(); // This doesn't do anything atm
    }

    private static void convertOreToTFCRock(TagPrefixEntry tagPrefixEntry, Rock rock) {
        TagPrefix.ORES.remove(tagPrefixEntry.value());

        ResourceLocation resLoc = getTFCRawRock(rock);

        TagPrefix.OreType tfcOreType = new TagPrefix.OreType(
                blockStateSupplier(resLoc),
                GTMaterials.Stone,
                () -> BlockBehaviour.Properties.of()
                        .mapColor(rock.color())
                        .requiresCorrectToolForDrops()
                        .strength(rock.category().hardness(6.5f), 10F),
                ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "block/" + resLoc.getPath()),
                false, false, false);

        TagPrefix.ORES.put(tagPrefixEntry.value(), tfcOreType);
    }
}