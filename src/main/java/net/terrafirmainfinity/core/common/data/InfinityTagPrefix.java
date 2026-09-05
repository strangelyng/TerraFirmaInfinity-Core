package net.terrafirmainfinity.core.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.OreBlock;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.terrafirmainfinity.core.common.data.item.InfinityToolTypes;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterialFlags;

import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.Conditions.*;

public class InfinityTagPrefix {
    /** TFC Items */
    public static final TagPrefix ingotDouble = new TagPrefix(GTCEu.id("ingot_double"))
            .idPattern("%s_double_ingot")
            .defaultTagPath("double_ingots/%s")
            .unformattedTagPath("double_ingots")
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Double Ingot")
            .materialAmount(GTValues.M*2)
            .materialIconType(MaterialIconType.ingotDouble)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasIngotProperty.and(
                    mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_DOUBLE_INGOT)
            ));

    public static final TagPrefix powder = new TagPrefix(GTCEu.id("powder"))
            .idPattern("%s_powder")
            .defaultTagPath("powders/%s")
            .unformattedTagPath("powders")
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Powder")
            .materialAmount(GTValues.M / 4)
            .materialIconType(InfinityMaterialIconType.powder)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty);

    public static final TagPrefix oreSmall = new TagPrefix(GTCEu.id("small_ore"))
            .materialAmount(GTValues.M / 9)
            .unificationEnabled(true);

    public static final TagPrefix poorRawOre = new TagPrefix(GTCEu.id("poor_raw_ore"))
            .idPattern("poor_raw_%s")
            .defaultTagPath("poor_raw_materials/%s")
            .unformattedTagPath("poor_raw_materials")
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("Poor Raw %s")
            .materialIconType(InfinityMaterialIconType.poorRawOre)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty);

    public static final TagPrefix richRawOre = new TagPrefix(GTCEu.id("rich_raw_ore"))
            .idPattern("rich_raw_%s")
            .defaultTagPath("rich_raw_materials/%s")
            .unformattedTagPath("rich_raw_materials")
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("Rich Raw %s")
            .materialIconType(InfinityMaterialIconType.richRawOre)
            .unificationEnabled(true)
            .generateItem(true)
            .generationCondition(hasOreProperty);

    /** TFC Tool Heads */
    public static final TagPrefix toolHeadPickaxe = new TagPrefix(GTCEu.id("pickaxe_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Pickaxe Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadPickaxe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.PICKAXE)));

    // TODO: Propick

    public static final TagPrefix toolHeadAxe = new TagPrefix(GTCEu.id("axe_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Axe Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadAxe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.AXE)));

    public static final TagPrefix toolHeadShovel = new TagPrefix(GTCEu.id("shovel_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Shovel Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadShovel)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SHOVEL)));

    public static final TagPrefix toolHeadHoe = new TagPrefix(GTCEu.id("hoe_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Hoe Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadHoe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.HOE)));

    public static final TagPrefix toolHeadChisel = new TagPrefix(GTCEu.id("chisel_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Chisel Head")
            .materialAmount(GTValues.M)
            .materialIconType(InfinityMaterialIconType.toolHeadChisel)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(InfinityToolTypes.CHISEL)));

    public static final TagPrefix toolHeadHammer = new TagPrefix(GTCEu.id("hammer_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Hammer Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadHammer)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.HARD_HAMMER)));

    public static final TagPrefix toolHeadSaw = new TagPrefix(GTCEu.id("saw_blade"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Saw Blade")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadSaw)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasNoCraftingToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SAW)));

    public static final TagPrefix toolHeadKnife = new TagPrefix(GTCEu.id("knife_blade"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Knife Blade")
            .materialAmount(GTValues.M)
            .materialIconType(InfinityMaterialIconType.toolHeadKnife)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.KNIFE)));

    public static final TagPrefix toolHeadScythe = new TagPrefix(GTCEu.id("scythe_blade"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Scythe Blade")
            .materialAmount(GTValues.M*2)
            .materialIconType(MaterialIconType.toolHeadScythe)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SCYTHE)));

    // TODO: Javelin

    public static final TagPrefix toolHeadSword = new TagPrefix(GTCEu.id("sword_blade"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Sword Blade")
            .materialAmount(GTValues.M*2)
            .materialIconType(MaterialIconType.toolHeadSword)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SWORD)));

    public static final TagPrefix toolHeadMace = new TagPrefix(GTCEu.id("mace_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Mace Head")
            .materialAmount(GTValues.M*2)
            .materialIconType(InfinityMaterialIconType.toolHeadMace)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(InfinityToolTypes.MACE)));

    // TODO: Fish Hook

    /** Missing GT Tool Heads */
    public static final TagPrefix toolHeadFile = new TagPrefix(GTCEu.id("file_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s File Head")
            .materialAmount(GTValues.M)
            .materialIconType(MaterialIconType.toolHeadFile)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasNoCraftingToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.FILE)));

    public static final TagPrefix toolHeadMiningHammer = new TagPrefix(GTCEu.id("mining_hammer_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Mining Hammer Head")
            .materialAmount(GTValues.M*2)
            .materialIconType(InfinityMaterialIconType.toolHeadMiningHammer)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.MINING_HAMMER)));

    public static final TagPrefix toolHeadSpade = new TagPrefix(GTCEu.id("spade_head"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Spade Head")
            .materialAmount(GTValues.M*2)
            .materialIconType(InfinityMaterialIconType.toolHeadSpade)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.SPADE)));

    public static final TagPrefix toolHeadButcheryKnife = new TagPrefix(GTCEu.id("butchery_knife_blade"))
            .itemTable(() -> GTMaterialItems.MATERIAL_ITEMS)
            .langValue("%s Butchery Knife Blade")
            .materialAmount(GTValues.M)
            .materialIconType(InfinityMaterialIconType.toolHeadButcheryKnife)
            .unificationEnabled(true)
            .enableRecycling()
            .generateItem(true)
            .generationCondition(hasToolProperty.and(
                    mat -> mat.getProperty(PropertyKey.TOOL).hasType(GTToolType.BUTCHERY_KNIFE)));

    // Additional TFC Items

    public static final TagPrefix anvil = new TagPrefix(GTCEu.id("anvil"))
            .materialAmount(GTValues.M * 14)
            .unificationEnabled(true);

    public static final TagPrefix bars = new TagPrefix(GTCEu.id("bars"))
            .materialAmount(GTValues.M / 4)
            .unificationEnabled(true);

    public static final TagPrefix bell = new TagPrefix(GTCEu.id("bell"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix chain = new TagPrefix(GTCEu.id("chain"))
            .defaultTagPath("chains/%s")
            .unformattedTagPath("chains")
            .materialAmount(GTValues.M / 16)
            .unificationEnabled(true);

    public static final TagPrefix lamp = new TagPrefix(GTCEu.id("lamp"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix lampUnfinished = new TagPrefix(GTCEu.id("lamp_unfinished"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix trapdoor = new TagPrefix(GTCEu.id("trapdoor"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix grate = new TagPrefix(GTCEu.id("grate"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix grateExposed = new TagPrefix(GTCEu.id("exposed_grate"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix grateWeathered = new TagPrefix(GTCEu.id("weathered_grate"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix grateOxidized = new TagPrefix(GTCEu.id("oxidized_grate"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix blockPlated = new TagPrefix(GTCEu.id("plated_block"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix blockPlatedExposed = new TagPrefix(GTCEu.id("exposed_plated_block"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix blockPlatedWeathered = new TagPrefix(GTCEu.id("weathered_plated_block"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix blockPlatedOxidized = new TagPrefix(GTCEu.id("oxidized_plated_block"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static final TagPrefix slabPlated = new TagPrefix(GTCEu.id("plated_slab"))
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true);

    public static final TagPrefix slabPlatedExposed = new TagPrefix(GTCEu.id("exposed_plated_slab"))
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true);

    public static final TagPrefix slabPlatedWeathered = new TagPrefix(GTCEu.id("weathered_plated_slab"))
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true);

    public static final TagPrefix slabPlatedOxidized = new TagPrefix(GTCEu.id("oxidized_plated_slab"))
            .materialAmount(GTValues.M / 2)
            .unificationEnabled(true);

    public static final TagPrefix stairsPlated = new TagPrefix(GTCEu.id("plated_stairs"))
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true);

    public static final TagPrefix stairsPlatedExposed = new TagPrefix(GTCEu.id("exposed_plated_stairs"))
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true);

    public static final TagPrefix stairsPlatedWeathered = new TagPrefix(GTCEu.id("weathered_plated_stairs"))
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true);

    public static final TagPrefix stairsPlatedOxidized = new TagPrefix(GTCEu.id("oxidized_plated_stairs"))
            .materialAmount((GTValues.M * 3) / 4)
            .unificationEnabled(true);


    /** Embers Items */
    public static final TagPrefix aspectus = new TagPrefix(GTCEu.id("aspectus"))
            .materialAmount(GTValues.M)
            .unificationEnabled(true);

    public static void modifyExistingToolHeadPrefixes() {
        TagPrefix.toolHeadBuzzSaw.materialAmount(GTValues.M*2);
        TagPrefix.toolHeadScrewdriver.materialAmount(GTValues.M);
        TagPrefix.toolHeadWrench.materialAmount(GTValues.M*2);
        TagPrefix.toolHeadWireCutter.materialAmount(GTValues.M*2);

        TagPrefix.toolHeadWrench.secondaryMaterials().clear();
        TagPrefix.toolHeadWireCutter.secondaryMaterials().clear();

        TagPrefix.toolHeadBuzzSaw.generationCondition(TagPrefix.toolHeadBuzzSaw.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_BUZZSAW_BLADE)));
        TagPrefix.toolHeadChainsaw.generationCondition(TagPrefix.toolHeadChainsaw.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_CHAINSAW_HEAD)));
        TagPrefix.toolHeadDrill.generationCondition(TagPrefix.toolHeadDrill.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_DRILL_HEAD)));
        TagPrefix.toolHeadScrewdriver.generationCondition(TagPrefix.toolHeadScrewdriver.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_SCREWDRIVER_HEAD)));
        TagPrefix.toolHeadWrench.generationCondition(TagPrefix.toolHeadWrench.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_WRENCH_HEAD)));
        TagPrefix.toolHeadWireCutter.generationCondition(TagPrefix.toolHeadWireCutter.generationCondition().or(mat -> mat.hasFlag(InfinityMaterialFlags.GENERATE_WIRE_CUTTER_HEAD)));
    }

    // TFC Stone Types
    public static TagPrefix oreGabbro;
    public static TagPrefix oreShale;
    public static TagPrefix oreClaystone;
    public static TagPrefix oreLimestone;
    public static TagPrefix oreConglomerate;
    public static TagPrefix oreDolomite;
    public static TagPrefix oreChert;
    public static TagPrefix oreChalk;
    public static TagPrefix oreRhyolite;
    public static TagPrefix oreDacite;
    public static TagPrefix oreQuartzite;
    public static TagPrefix oreSlate;
    public static TagPrefix orePhyllite;
    public static TagPrefix oreSchist;
    public static TagPrefix oreGneiss;

    public static void modifyExistingOres() {
        // Remove Unwanted Ore TagPrefixes
        TagPrefix.ORES.remove(TagPrefix.ore); // Vanilla Stone
        TagPrefix.ORES.remove(TagPrefix.oreRedGranite);
        TagPrefix.ORES.remove(TagPrefix.oreDeepslate);
        TagPrefix.ORES.remove(TagPrefix.oreSand);
        TagPrefix.ORES.remove(TagPrefix.oreRedSand);
        TagPrefix.ORES.remove(TagPrefix.oreGravel);
        TagPrefix.ORES.remove(TagPrefix.oreNetherrack);
        TagPrefix.ORES.remove(TagPrefix.oreBlackstone);
        TagPrefix.ORES.remove(TagPrefix.oreEndstone);

        // Convert existing ores to TFC Rocks
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
    }

    public static void init() {
        modifyExistingToolHeadPrefixes();
        modifyExistingOres();
        createTFCOres();
    }

    /**
     * This supplier ensures that the block has been registered before the TagPrefix attempts to register the ore
     */
    public static Supplier<BlockState> blockStateSupplier(ResourceLocation resLoc) {
        return () -> BuiltInRegistries.BLOCK.get(resLoc).defaultBlockState();
    }

    private static ResourceLocation getTFCRawRock(Rock rock) {
        return ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "rock/raw/" + rock.getSerializedName());
    }

    private static TagPrefix createTFCOreTagPrefix(Rock rock) {
        String name = rock.getSerializedName();

        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        ResourceLocation resLoc = getTFCRawRock(rock);

        return new TagPrefix(name)
                .langValue(formattedName + " %s Ore")
                .registerOre(blockStateSupplier(resLoc), () -> GTMaterials.Stone,
                        BlockBehaviour.Properties.of()
                                .mapColor(rock.color())
                                .requiresCorrectToolForDrops()
                                .strength(rock.category().hardness(6.5f), 10F),
                        ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "block/" + resLoc.getPath()),
                        false, false, true)
                .defaultTagPath("ores/%s")
                .prefixOnlyTagPath("ores_in_ground/%s")
                .unformattedTagPath("ores")
                .materialIconType(MaterialIconType.ore)
                .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .unificationEnabled(true)
                .blockConstructor(OreBlock::new)
                .generationCondition(hasOreProperty);
    }

    private static void convertOreToTFCRock(TagPrefix original, Rock rock) {
        ResourceLocation resLoc = getTFCRawRock(rock);

        original.registerOre(blockStateSupplier(resLoc), () -> GTMaterials.Stone,
                BlockBehaviour.Properties.of()
                        .mapColor(rock.color())
                        .requiresCorrectToolForDrops()
                        .strength(rock.category().hardness(6.5f), 10F),
                ResourceLocation.fromNamespaceAndPath(resLoc.getNamespace(), "block/" + resLoc.getPath()),
                false, false, true);
    }
}