package net.terrafirmainfinity.core.common.data.tag;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class InfinityTagsHandler {
    public static void initBlockTags(RegistrateTagsProvider<Block> provider) {
        InfinityBlockTags.init(provider);
    }

    public static void initItemTags(RegistrateTagsProvider<Item> provider) {
        InfinityItemTags.init(provider);
    }
}
