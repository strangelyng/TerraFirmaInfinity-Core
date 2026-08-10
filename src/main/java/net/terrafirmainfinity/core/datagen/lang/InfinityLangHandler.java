package net.terrafirmainfinity.core.datagen.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class InfinityLangHandler extends LangHandler {
    public static void init(RegistrateLangProvider provider) {
        replaceMaterialLang(provider, "andesite_alloy", "Andesite Composite");

        provider.add("tagprefix.poor_raw_ore", "Poor Raw %s");
        provider.add("tagprefix.rich_raw_ore", "Rich Raw %s");
    }

    public static void replaceMaterialLang(RegistrateLangProvider provider, String id, String name) {
        replace(provider, "material.tficore." + id, name);
    }
}
