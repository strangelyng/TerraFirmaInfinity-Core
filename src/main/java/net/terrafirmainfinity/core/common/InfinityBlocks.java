package net.terrafirmainfinity.core.common;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.ConnectedGrassBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.data.InfinityTagPrefix;
import net.terrafirmainfinity.core.common.data.block.LateriteBlock;
import net.terrafirmainfinity.core.common.data.models.InfinityModels;
import net.terrafirmainfinity.core.util.InfinityUtil;

import java.util.Locale;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityBlocks {
    static {
        InfinityRegistrate.creativeModeTab(() -> InfinityCore.INFINITY_CREATIVE_TAB);
    }

    public static final BlockEntry<Block> HERMETIC_CASING_ULV = InfinityRegistrate
            .block("ulv_hermetic_casing", Block::new)
            .lang("Hermetic Casing")
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.isValidSpawn(((state, level, pos, entity) -> false)))
            .addLayer(() -> RenderType::cutoutMipped)
            .blockstate(GTModels.createHermeticCasingModel(GTValues.VN[GTValues.ULV].toLowerCase(Locale.ROOT)))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    // Machine Casing Blocks
    public static final BlockEntry<Block> CASING_STEEL_BRICKS = InfinityRegistrate
            .block("hp_steam_machine_casing", Block::new)
            .lang("High Pressure Steam Machine Casing")
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
            .addLayer(() -> RenderType::solid)
            .exBlockstate(GTModels.cubeAllModel(InfinityCore.id("block/casings/solid/machine_casing_steel_plated_bricks")))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    public static final BlockEntry<Block> CASING_LEAD_BRICKS = InfinityRegistrate
            .block("dwarven_machine_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
            .addLayer(() -> RenderType::solid)
            .exBlockstate(GTModels.cubeAllModel(InfinityCore.id("block/casings/solid/machine_casing_lead_plated_bricks")))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    public static final BlockEntry<Block> LEAD_BRICKS_HULL = InfinityRegistrate
            .block("lead_brick_casing", Block::new)
            .lang("Bricked Lead Casing")
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .blockstate(InfinityModels.createDwarvenCasingModel("bricked_lead"))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    public static final BlockEntry<Block> LEAD_HULL = InfinityRegistrate
            .block("lead_machine_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .blockstate(InfinityModels.createDwarvenCasingModel("lead"))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    public static final BlockEntry<Block> CASING_LEAD_PIPE = InfinityRegistrate
            .block("lead_pipe_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
            .addLayer(() -> RenderType::solid)
            .exBlockstate(GTModels.cubeAllModel(InfinityCore.id("block/casings/pipe/machine_casing_pipe_lead")))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    public static final BlockEntry<Block> CASING_STAINLESS_STEEL_PIPE = InfinityRegistrate
            .block("stainless_steel_pipe_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
            .addLayer(() -> RenderType::solid)
            .exBlockstate(GTModels.cubeAllModel(InfinityCore.id("block/casings/pipe/machine_casing_pipe_stainless_steel")))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build()
            .register();

    // Laterites
    public static final BlockEntry<LateriteBlock> BAUXITE_SOIL = createLateriteDirtBlock("bauxite", MapColor.TERRACOTTA_PINK);
    public static final BlockEntry<ConnectedGrassBlock> BAUXITE_GRASS = createLateriteGrassBlock("bauxite", BAUXITE_SOIL);

    public static final BlockEntry<LateriteBlock> GARNIERITE_SOIL = createLateriteDirtBlock("garnierite", MapColor.COLOR_GREEN);
    public static final BlockEntry<ConnectedGrassBlock> GARNIERITE_GRASS = createLateriteGrassBlock("garnierite", GARNIERITE_SOIL);

    public static final BlockEntry<LateriteBlock> LIMONITE_SOIL = createLateriteDirtBlock("limonite", MapColor.TERRACOTTA_YELLOW);
    public static final BlockEntry<ConnectedGrassBlock> LIMONITE_GRASS = createLateriteGrassBlock("limonite", LIMONITE_SOIL);

    private static BlockEntry<LateriteBlock> createLateriteDirtBlock(String material, MapColor color) {
        return InfinityRegistrate
                .block("laterite/" + material, p ->
                        new LateriteBlock(BlockBehaviour.Properties.of().mapColor(color).strength(3.0F).sound(SoundType.GRAVEL),
                                () -> BuiltInRegistries.BLOCK.get(InfinityCore.id("laterite/" + material + "_grass"))))
                .lang("Laterite " + InfinityUtil.capitalize(material))
                .blockstate((ctx, prov) ->
                        prov.simpleBlock(ctx.get(), prov.models().cubeAll("block/" + ctx.getName(),
                                InfinityCore.id("block/" + ctx.getName()))))
                .tag(BlockTags.MINEABLE_WITH_SHOVEL, TFCTags.Blocks.CAN_LANDSLIDE,
                        TFCTags.Blocks.GRASS_PLANTABLE_ON, TFCTags.Blocks.BUSH_PLANTABLE_ON, TFCTags.Blocks.DRY_PLANT_PLANTABLE_ON, TFCTags.Blocks.SPREADING_FRUIT_GROWS_ON)
                .item(BlockItem::new)
                .model((ctx, prov) ->
                        prov.withExistingParent("item/" + ctx.getName(), InfinityCore.id("block/" + ctx.getName())))
                .build().register();
    }

    private static BlockEntry<ConnectedGrassBlock> createLateriteGrassBlock(String material, BlockEntry<? extends Block> dirtBlock) {
        return InfinityRegistrate
                .block("laterite/" + material + "_grass", p ->
                        new ConnectedGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GRASS).randomTicks().strength(3.0F).sound(SoundType.GRASS), dirtBlock, null, null))
                .lang("Laterite " + InfinityUtil.capitalize(material) + " Grass")
                .blockstate(InfinityModels.createTFCConnectedGrassModel())
                .addLayer(() -> RenderType::cutoutMipped)
                .tag(BlockTags.MINEABLE_WITH_SHOVEL, TFCTags.Blocks.CAN_LANDSLIDE,
                        TFCTags.Blocks.GRASS_PLANTABLE_ON, TFCTags.Blocks.BUSH_PLANTABLE_ON, TFCTags.Blocks.DRY_PLANT_PLANTABLE_ON, TFCTags.Blocks.SPREADING_FRUIT_GROWS_ON)
                .loot((ctx, prov) -> ctx.dropOther(prov, dirtBlock))
                .item(BlockItem::new)
                .model((ctx, prov) ->
                        prov.withExistingParent("item/" + ctx.getName(), ResourceLocation.fromNamespaceAndPath(TerraFirmaCraft.MOD_ID, "item/grass_inv"))
                                .texture("block", InfinityCore.id("block/" + ctx.getName().replace("_grass", ""))))
                .build().register();
    }

    // Cobble Replacement
    public static void init() {
        reinitializeCobbleReplacements();
    }

    private static void reinitializeCobbleReplacements() {
        GTBlocks.COBBLE_BLOCKS.clear();

        GTBlocks.COBBLE_BLOCKS.put(TagPrefix.oreGranite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.GRANITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(TagPrefix.oreDiorite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.DIORITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(TagPrefix.oreAndesite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.ANDESITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(TagPrefix.oreMarble,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.MARBLE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(TagPrefix.oreTuff,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.TUFF).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(TagPrefix.oreBasalt,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.BASALT).get(Rock.BlockType.COBBLE).get().defaultBlockState());

        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreGabbro,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.GABBRO).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreShale,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.SHALE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreClaystone,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.CLAYSTONE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreLimestone,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.LIMESTONE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreConglomerate,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.CONGLOMERATE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreDolomite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.DOLOMITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreChert,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.CHERT).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreChalk,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.CHALK).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreRhyolite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.RHYOLITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreDacite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.DACITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreQuartzite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.QUARTZITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreSlate,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.SLATE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.orePhyllite,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.PHYLLITE).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreSchist,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.SCHIST).get(Rock.BlockType.COBBLE).get().defaultBlockState());
        GTBlocks.COBBLE_BLOCKS.put(InfinityTagPrefix.oreGneiss,
                () -> TFCBlocks.ROCK_BLOCKS.get(Rock.GNEISS).get(Rock.BlockType.COBBLE).get().defaultBlockState());
    }
}
