package net.terrafirmainfinity.core.common.datagen;

import com.tterrag.registrate.providers.ProviderType;
import net.terrafirmainfinity.core.common.datagen.lang.InfinityLangHandler;

import static net.terrafirmainfinity.core.InfinityCore.REGISTRATE;

public class InfinityCoreDatagen {
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, InfinityLangHandler::init);
    }
}
