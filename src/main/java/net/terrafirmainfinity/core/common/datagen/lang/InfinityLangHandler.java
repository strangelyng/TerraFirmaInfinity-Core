package net.terrafirmainfinity.core.common.datagen.lang;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.data.InfinityTagPrefix;

import java.util.Locale;

public class InfinityLangHandler extends LangHandler {
    private static final String[] INFINITY_RECIPE_TYPE_IDS = {
            "roaster", "electrolytic_cell", "gravity_separator"
    };

    private static String toTitle(String snakeCase) {
        StringBuilder out = new StringBuilder();
        for (String part : snakeCase.split("_")) {
            if (part.isEmpty()) continue;
            if (out.length() > 0) out.append(' ');
            out.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return out.toString();
    }

    public static void init(RegistrateLangProvider provider) {
        // Materials
//        replaceMaterialLang(provider, "dawnstone", "Aurichalcum");

        // Tag Prefixes
        addTagPrefixLang(provider, InfinityTagPrefix.ingotDouble);

        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadPickaxe);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadAxe);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadShovel);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadHoe);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadChisel);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadHammer);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadSaw);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadKnife);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadScythe);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadSword);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadMace);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadMiningHammer);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadFile);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadSpade);
        addTagPrefixLang(provider, InfinityTagPrefix.toolHeadButcheryKnife);

        addTagPrefixLang(provider, InfinityTagPrefix.oreChalk);
        addTagPrefixLang(provider, InfinityTagPrefix.oreChert);
        addTagPrefixLang(provider, InfinityTagPrefix.oreConglomerate);
        addTagPrefixLang(provider, InfinityTagPrefix.oreClaystone);
        addTagPrefixLang(provider, InfinityTagPrefix.oreDacite);
        addTagPrefixLang(provider, InfinityTagPrefix.oreDolomite);
        addTagPrefixLang(provider, InfinityTagPrefix.oreGabbro);
        addTagPrefixLang(provider, InfinityTagPrefix.oreGneiss);
        addTagPrefixLang(provider, InfinityTagPrefix.oreLimestone);
        addTagPrefixLang(provider, InfinityTagPrefix.orePhyllite);
        addTagPrefixLang(provider, InfinityTagPrefix.oreQuartzite);
        addTagPrefixLang(provider, InfinityTagPrefix.oreRhyolite);
        addTagPrefixLang(provider, InfinityTagPrefix.oreSchist);
        addTagPrefixLang(provider, InfinityTagPrefix.oreShale);
        addTagPrefixLang(provider, InfinityTagPrefix.oreSlate);

        addToolClassLang(provider, "Chisel");
        addToolClassLang(provider, "Mace");

        // Items
        replace(provider, "item.gtceu.tool.chisel", "%s Chisel");
        replace(provider, "item.gtceu.tool.mace", "%s Mace");

        // Machines & Recipe Types
        provider.add("tfinfinity.machine.roaster.tooltip", "§7Sulfide roast, anyone?");

        for (String id : INFINITY_RECIPE_TYPE_IDS) {
            String name = switch (id) {
                case "roaster" -> "Ore Roasting";
                case "gravity_separator" -> "Gravity Separation";
                case "electrolytic_cell" -> "Advanced Electrolysis";
                default -> toTitle(id);
            };
            provider.add("recipe_type." + InfinityCore.MOD_ID + "." + id, name);
        }
    }

    public static void addToolClassLang(RegistrateLangProvider provider, String toolClass) {
        provider.add("gtceu.tool.class." + toolClass.toLowerCase(Locale.ROOT).replace(' ', '_'), toolClass);
    }

    public static void addTagPrefixLang(RegistrateLangProvider provider, TagPrefix prefix) {
        provider.add("tagprefix." + prefix.name(), prefix.langValue());
    }

    public static void replaceMaterialLang(RegistrateLangProvider provider, String id, String name) {
        replace(provider, "material." + InfinityCore.MOD_ID + "." + id, name);
    }
}
