package net.terrafirmainfinity.core.datagen.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.terrafirmainfinity.core.InfinityCore;

public class InfinityLangHandler extends LangHandler {
    private static final String[] INFINITY_RECIPE_TYPE_IDS = {
        "hpht_vacuum_press"
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
        replaceMaterialLang(provider, "andesite_alloy", "Andesite Composite");
        replaceMaterialLang(provider, "dawnstone", "Dawnsteel");
        replaceMaterialLang(provider, "zirconium_uhtc_composite", "Zirconium UHTC Composite");

        provider.add("tagprefix.poor_raw_ore", "Poor Raw %s");
        provider.add("tagprefix.rich_raw_ore", "Rich Raw %s");

        provider.add("tagprefix.ingot_hot", "Hot %s Ingot");
        provider.add("tagprefix.nugget_hot", "Hot %s Nugget");
        provider.add("tagprefix.plate_dense_hot", "Hot Dense %s Plate");
        provider.add("tagprefix.plate_double_hot", "Hot Double %s Plate");
        provider.add("tagprefix.plate_hot", "Hot %s Plate");
        provider.add("tagprefix.foil_hot", "Hot %s Foil");
        provider.add("tagprefix.rod_long_hot", "Hot Long %s Rod");
        provider.add("tagprefix.rod_hot", "Hot %s Rod");
        provider.add("tagprefix.bolt_hot", "Hot %s Bolt");
        provider.add("tagprefix.ring_hot", "Hot %s Ring");
        provider.add("tagprefix.gear_small_hot", "Hot Small %s Gear");
        provider.add("tagprefix.gear_hot", "Hot %s Gear");

        provider.add("tagprefix.ingot_powder_compact", "%s Ingot Powder Compact");
        provider.add("tagprefix.nugget_powder_compact", "%s Nugget Powder Compact");
        provider.add("tagprefix.plate_dense_powder_compact", "Dense %s Plate Powder Compact");
        provider.add("tagprefix.plate_double_powder_compact", "Double %s Plate Powder Compact");
        provider.add("tagprefix.plate_powder_compact", "%s Plate Powder Compact");
        provider.add("tagprefix.foil_powder_compact", "%s Foil Powder Compact");
        provider.add("tagprefix.rod_long_powder_compact", "Long %s Rod Powder Compact");
        provider.add("tagprefix.rod_powder_compact", "%s Rod Powder Compact");
        provider.add("tagprefix.bolt_powder_compact", "%s Bolt Powder Compact");
        provider.add("tagprefix.ring_powder_compact", "%s Ring Powder Compact");
        provider.add("tagprefix.gear_small_powder_compact", "Small %s Gear Powder Compact");
        provider.add("tagprefix.gear_powder_compact", "%s Gear Powder Compact");
        provider.add("tagprefix.tool_head_buzzsaw_powder_compact", "%s Buzzsaw Blade Powder Compact");
        provider.add("tagprefix.tool_head_screwdriver_powder_compact", "%s Screwdriver Tip Powder Compact");
        provider.add("tagprefix.tool_head_drill_powder_compact", "%s Drill Head Powder Compact");
        provider.add("tagprefix.tool_head_chainsaw_powder_compact", "%s Chainsaw Blade Powder Compact");
        provider.add("tagprefix.tool_head_wrench_powder_compact", "%s Wrench Tip Powder Compact");
        provider.add("tagprefix.tool_head_wire_cutter_powder_compact", "%s Wire Cutter Head Powder Compact");

        provider.add("tfi.machine.hpht_vacuum_press.tooltip", "§7Forming and sintering all-in-one");

        for (String id : INFINITY_RECIPE_TYPE_IDS) {
            String name = switch (id) {
                case "hpht_vacuum_press" -> "HPHT Vacuum Press";
                default -> toTitle(id);
            };
            provider.add("recipe_type." + InfinityCore.MOD_ID + "." + id, name);
        }
    }

    public static void replaceMaterialLang(RegistrateLangProvider provider, String id, String name) {
        replace(provider, "material." + InfinityCore.MOD_ID + "." + id, name);
    }
}
