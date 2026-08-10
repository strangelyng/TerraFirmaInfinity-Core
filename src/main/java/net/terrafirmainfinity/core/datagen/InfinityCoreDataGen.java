package net.terrafirmainfinity.core.datagen;

import com.tterrag.registrate.providers.ProviderType;
import net.terrafirmainfinity.core.datagen.lang.InfinityLangHandler;

import static net.terrafirmainfinity.core.InfinityCore.REGISTRATE;

public class InfinityCoreDataGen {
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, InfinityLangHandler::init);
    }
}
