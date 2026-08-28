package net.terrafirmainfinity.core.common;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.models.GTModels;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.data.InfinityTagPrefix;

import java.util.Locale;

import static net.terrafirmainfinity.core.InfinityCore.REGISTRATE;

public class InfinityBlocks {
    public static final BlockEntry<Block> HERMETIC_CASING_ULV = REGISTRATE
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
    public static final BlockEntry<Block> CASING_LEAD_BRICKS = REGISTRATE
            .block("dwarven_machine_casing", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
            .addLayer(() -> RenderType::solid)
            .exBlockstate(GTModels.cubeAllModel(InfinityCore.id("block/casings/solid/machine_casing_lead_plated_bricks")))
            .tag(CustomTags.MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH)
            .item(BlockItem::new)
            .build().register();

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
