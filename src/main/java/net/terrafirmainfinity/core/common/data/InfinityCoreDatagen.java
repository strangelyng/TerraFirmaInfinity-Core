package net.terrafirmainfinity.core.common.data;

import com.tterrag.registrate.providers.ProviderType;
import net.terrafirmainfinity.core.common.data.lang.InfinityLangHandler;
import net.terrafirmainfinity.core.common.data.tag.InfinityTagsHandler;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityCoreDatagen {
    public static void init() {
        InfinityRegistrate.addDataGenerator(ProviderType.BLOCK_TAGS, InfinityTagsHandler::initBlockTags);
        InfinityRegistrate.addDataGenerator(ProviderType.ITEM_TAGS, InfinityTagsHandler::initItemTags);
        InfinityRegistrate.addDataGenerator(ProviderType.LANG, InfinityLangHandler::init);
    }
}
