package net.terrafirmainfinity.core.common.datagen;

import com.tterrag.registrate.providers.ProviderType;
import net.terrafirmainfinity.core.common.datagen.lang.InfinityLangHandler;

import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityCoreDatagen {
    public static void init() {
        InfinityRegistrate.addDataGenerator(ProviderType.LANG, InfinityLangHandler::init);
    }
}
