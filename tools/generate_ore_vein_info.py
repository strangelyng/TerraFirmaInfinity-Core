import os
import json
from tools_global import ALL_ROCKS, ALL_SANDS, BIOME_LISTS, ore_block_to_material

def write_to_ore_vein_info_recipes(vein_dict: dict):
    pathdir = os.path.join("../src/main/java/net/terrafirmainfinity/core/integration/emi/OreVeinDictionary.java")

    if not os.path.exists(pathdir):
        print(f"Error: Target file '{pathdir}' not found.")
        return
    else:
        print(f"Writing vein info recipes to '{pathdir}")

    with open(pathdir, mode="w", encoding="utf-8") as file:
        file.write("package net.terrafirmainfinity.core.integration.emi;" + "\n")

        file.write("\n")

        file.write("public class OreVeinDictionary {" + "\n")

        file.write("    public static final OreVeinInfoRecipe[] RECIPES = {" + "\n")

        for vein_key, vein_data in vein_dict.items():
            vein_config = vein_data.get("config", {})

            rarity = vein_config.get("rarity", 0)
            density = vein_config.get("density", 0)
            min_y = vein_config.get("min_y", 0)
            max_y = vein_config.get("max_y", 0)
            size = vein_config.get("size", 0)
            height = vein_config.get("height", 0)
            radius = vein_config.get("radius", 0)
            near_lava = vein_config.get("near_lava", "false")
            project = vein_config.get("project", "false")
            project_offset = vein_config.get("project_offset", "false")

            indicator_dict = vein_config.get("indicator", {})
            if indicator_dict:
                indicator_depth = indicator_dict.get("depth", 0)
            else:
                indicator_depth = 0

            file.write(f"            new OreVeinInfoRecipe(\"{vein_key}\", \"minecraft:overworld\"," + "\n")
            file.write(f"                    {rarity}, {density}, {min_y}, {max_y}, {size}, {height}, {radius}," + "\n")
            file.write(f"                    {str(near_lava).lower()}, {str(project).lower()}, {str(project_offset).lower()}, {indicator_depth}," + "\n")

            blocks_str = []
            ore_weights_str = []

            material_list = []

            blocks = vein_config.get("blocks", [])
            for block_entry in blocks:
                replace_targets = block_entry.get("replace", [])
                with_entries = block_entry.get("with", [])

                for target in replace_targets:
                    blocks_str.append(target)

                for entry in with_entries:
                    block_id = entry.get("block", "")
                    weight = entry.get("weight", 100)

                    if "ore" in block_id and ("sandstone" in block_id or any(f"{r}" in block_id for r in ALL_ROCKS) or any(f"{s}" in block_id for s in ALL_SANDS)): # LMAO
                        material = ore_block_to_material(block_id, False)

                        if material not in material_list:
                            material_list.append(material)
                            ore_weights_str.append(f"new OreVeinInfoRecipe.WeightedBlock(\"{material}\", {weight})")

            blocks_str = str(blocks_str).replace("[", "").replace("]", "").replace("'", "\"")

            ore_weights_str = ", ".join(ore_weights_str)

            file.write(f"                    new String[] {{{blocks_str}}}," + "\n")

            file.write(f"                    new OreVeinInfoRecipe.WeightedBlock[] {{{ore_weights_str}}}," + "\n")

            biome_filter = vein_data.get("biome_filter")

            biome_list = BIOME_LISTS.get(biome_filter)

            biome_list = str(biome_list).replace("[", "").replace("]", "").replace("'", "\"")

            if biome_filter:
                file.write(f"                    \"{biome_filter}\", new String[] {{{biome_list}}}," + "\n")
            else:
                file.write(f"                    null, null," + "\n")

            placements = vein_data.get("placement", [])

            has_climate_rules = False

            for placement in placements:
                if placement.get("type") != "tfc:climate":
                    continue
                else:
                    min_rainfall = placement.get("min_groundwater", "null")
                    max_rainfall = placement.get("max_groundwater", "null")
                    min_temperature = placement.get("min_temperature", "null")
                    max_temperature = placement.get("max_temperature", "null")

                    file.write(f"                    {min_rainfall}, {max_rainfall}, {min_temperature}, {max_temperature}," + "\n")

                    has_climate_rules = True

                    break

            if not has_climate_rules:
                file.write(f"                    null, null, null, null," + "\n")

            file.write(f"                    null)," + "\n")

            file.write("\n")

        file.write("    };" + "\n")

        file.write("}" + "\n")

    make_lang(vein_dict)

def make_lang(vein_dict: dict):
    lang_dir = "lang"
    lang_file = "vein_lang.json"

    lang_path = os.path.join(lang_dir, lang_file)

    if not os.path.exists(lang_dir):
        os.makedirs(lang_dir, exist_ok=True)

    print(f"Writing vein lang to '{lang_path}")

    with open(lang_path, "w", encoding="utf-8") as file:
        lang_obj = {}

        for vein_key, vein_data in vein_dict.items():
            lang_key = "tfinfinity.ore_vein." + vein_key

            vein_name = vein_key.replace("_", " ").title()

            lang_obj[lang_key] = vein_name

        json.dump(lang_obj, file, indent=4)