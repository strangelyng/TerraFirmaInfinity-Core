import json
import csv
import os
from collections import defaultdict

CONFIGURED_DIR = "data/tfinfinity/worldgen/configured_feature/vein"
PLACED_DIR = "data/tfinfinity/worldgen/placed_feature/vein"

TAGS_DIR = "data/tfc/tags/worldgen/placed_feature/in_biome"
BIOME_VEINS_DIR = "data/tfc/tags/worldgen/placed_feature/in_biome/veins"

# Rock Groups for Block Replacement Map
IGNEOUS_INTRUSIVE = ["granite", "diorite", "gabbro"]
IGNEOUS_EXTRUSIVE = ["rhyolite", "basalt", "andesite", "dacite", "tuff"] # Tuff is treated as igneous extrusive due to mineral similarity
ALL_IGNEOUS = IGNEOUS_INTRUSIVE + IGNEOUS_EXTRUSIVE

SEDIMENTARY = ["shale", "claystone", "limestone", "conglomerate", "dolomite", "chert", "chalk"]
METAMORPHIC = ["quartzite", "slate", "phyllite", "schist", "gneiss", "marble"]

ALL_ROCKS = ALL_IGNEOUS + SEDIMENTARY + METAMORPHIC
ALL_SANDS = ["brown", "white", "black", "red", "yellow", "green", "pink"]

# Specific rocks
MAFIC_ONLY = ["basalt", "gabbro"]
KARST_ONLY = ["limestone", "dolomite"]

#region HELPER FUNCTIONS
# Vein Functions
def make_cluster_vein(name: str, rarity: int, density: float, size: int, min_y: int, max_y: int, 
                      project: bool, project_offset: bool, blocks: list, indicator: dict | None = None, rules: dict | None = None):
    vein_json = {
        "type": "tfc:cluster_vein",
        "config": {
            "rarity": rarity, "density": density, "size": size, "min_y": min_y, "max_y": max_y, 
            "project": project, "project_offset": project_offset,
            "random_name": name,
            "blocks": blocks
        }
    }

    if indicator is not None:
        vein_json.get("config")["indicator"] = indicator

    if rules is not None:
        vein_json.update(rules)

    return vein_json

def make_disc_vein(name: str, rarity: int, density: float, size: int, height: int, min_y: int, max_y: int, 
                   project: bool, project_offset: bool, blocks: list, indicator: dict | None = None, rules: dict | None = None):
    vein_json = {
        "type": "tfc:disc_vein",
        "config": {
            "rarity": rarity, "density": density, "size": size, "height": height, "min_y": min_y, "max_y": max_y, 
            "project": project, "project_offset": project_offset,
            "random_name": name,
            "blocks": blocks
        }
    }

    if indicator is not None:
        vein_json.get("config")["indicator"] = indicator

    if rules is not None:
        vein_json.update(rules)

    return vein_json

def make_pipe_vein(name: str, rarity: int, density: float, height: int, radius: int, min_y: int, max_y: int, 
                   min_skew: int, max_skew: int, min_slant: int, max_slant: int, sign: int,
                   project: bool, project_offset: bool, blocks: list, indicator: dict | None = None, rules: dict | None = None):
    vein_json = {
        "type": "tfc:pipe_vein",
        "config": {
            "rarity": rarity, "density": density, "height": height, "radius": radius, "min_y": min_y, "max_y": max_y, 
            "min_skew": min_skew, "max_skew": max_skew, "min_slant": min_slant, "max_slant": max_slant, "sign": sign,
            "project": project, "project_offset": project_offset,
            "random_name": name,
            "blocks": blocks
        }
    }

    if indicator is not None:
        vein_json.get("config")["indicator"] = indicator

    if rules is not None:
        vein_json.update(rules)

    return vein_json

# Block Replacement Functions
def make_rock_replacements(rock_list, ore_weights):
    blocks = []
    for rock in rock_list:
        target = f"tfc:rock/raw/{rock}"
        with_entry = [
            {"weight": weight, "block": f"gtceu:{rock}_{ore}_ore"}
            for ore, weight in ore_weights.items()
        ]
        blocks.append({"replace": [target], "with": with_entry})
    return blocks

def make_sand_replacements(sand_colors, ore_weights):
    blocks = []
    for color in sand_colors:
        target = f"tfc:sand/{color}"
        with_entry = [
            {"weight": weight, "block": f"gtceu:{color}_{ore}_ore"}
            for ore, weight in ore_weights.items()
        ]
        blocks.append({"replace": [target], "with": with_entry})
    return blocks

def make_sandstone_replacements(sand_colors, ore_weights):
    blocks = []
    for color in sand_colors:
        target = f"tfc:raw_sandstone/{color}"
        with_entry = [
            {"weight": weight, "block": f"gtceu:{color}_sandstone_{ore}_ore"}
            for ore, weight in ore_weights.items()
        ]
        blocks.append({"replace": [target], "with": with_entry})
    return blocks

# Vein Indicator Functions
def make_vein_indicator(blocks: dict, indicator_rarity: int, indicator_depth: int, indicator_underground_rarity: int, indicator_underground_count: int):
    indicator = {
        "rarity": indicator_rarity, 
        "depth": indicator_depth, 
        "underground_rarity": indicator_underground_rarity, 
        "underground_count": indicator_underground_count,
        "blocks": []
    }

    for block, weight in blocks.items():
        block_entry = {"weight": weight, "block": f"{block}"}
        indicator.get("blocks").append(block_entry)
    return indicator

def make_surface_vein_indicator(blocks: dict):
    return make_vein_indicator(blocks, 14, 35, 1, 0)
    
def make_normal_vein_indicator(blocks: dict):
    return make_vein_indicator(blocks, 25, 35, 1, 0)
    
def make_deep_vein_indicator(blocks: dict):
    return make_vein_indicator(blocks, 0, 35, 1, 5)
    
def build_placed_feature_json(vein_key, placement_modifiers):
    feature_id = f"tfinfinity:vein/{vein_key}"
    
    placement = placement_modifiers or []
    
    return {
        "feature": feature_id,
        "placement": placement
    }

#region ORE VEIN DICTIONARY
VEIN_DICT = {
    # Surface deposits
    "surface_bismuthinite": make_cluster_vein("surface_bismuthinite", 80, 0.25, 15, -20, 5, True, False,
                                              make_rock_replacements(SEDIMENTARY, {"bismuthinite": 100}),
                                              make_surface_vein_indicator({"tfc:ore/small_bismuthinite": 1})),

    "surface_coal": make_disc_vein("surface_coal", 90, 0.35, 15, 8, -20, 5, True, False,
                                   make_rock_replacements(SEDIMENTARY, {"coal": 100}),
                                   make_surface_vein_indicator({"gtceu:coal_indicator": 1})),

    "surface_gold": make_cluster_vein("surface_gold", 100, 0.2, 15, -20, 5, True, False,
                                      make_rock_replacements(ALL_IGNEOUS, {"gold": 100}),
                                      make_surface_vein_indicator({"tfc:ore/small_native_gold": 1})),

    "surface_hematite": make_cluster_vein("surface_hematite", 90, 0.25, 15, -20, 5, True, False,
                                          make_rock_replacements(IGNEOUS_EXTRUSIVE, {"hematite": 100}),
                                          make_surface_vein_indicator({"tfc:ore/small_hematite": 1})),

    "surface_magnetite": make_cluster_vein("surface_magnetite", 90, 0.2, 15, -20, 5, True, False,
                                           make_rock_replacements(SEDIMENTARY, {"magnetite": 100}),
                                           make_surface_vein_indicator({"tfc:ore/small_magnetite": 1})),

    "surface_malachite": make_cluster_vein("surface_malachite", 80, 0.2, 15, -20, 5, True, False,
                                           make_rock_replacements(SEDIMENTARY, {"malachite": 100}),
                                           make_surface_vein_indicator({"tfc:ore/small_malachite": 1})),

    "surface_copper": make_cluster_vein("surface_copper", 70, 0.25, 15, -20, -5, True, False,
                                        make_rock_replacements(IGNEOUS_EXTRUSIVE, {"copper": 100}),
                                        make_surface_vein_indicator({"tfc:ore/small_native_copper": 1})),

    "surface_bog_iron": make_disc_vein("surface_bog_iron", 90, 0.2, 20, 5, -30, -5, True, False,
                                       make_rock_replacements(SEDIMENTARY, {"yellow_limonite": 75, "goethite": 15, "garnierite": 15}),
                                       make_surface_vein_indicator({"tfc:ore/small_limonite": 3, "gtceu:goethite_indicator": 1}),
                                       {
                                           "placement": [
                                               {"type": "tfc:climate", "min_temperature": -13, "min_groundwater": 150}
                                           ],
                                           "biome_filter": "swamp"} # Must be added to biome defs
                                       ),

    "surface_mica": make_cluster_vein("surface_mica", 120, 0.15, 10, -20, -5, True, False,
                                      make_rock_replacements(["gneiss", "schist"] + IGNEOUS_INTRUSIVE, {"mica": 1}),
                                      make_surface_vein_indicator({"gtceu:mica_indicator": 1})),

    "surface_saltpeter": make_disc_vein("surface_saltpeter", 90, 0.25, 20, 5, -20, -5, True, False,
                                        make_rock_replacements(SEDIMENTARY, {"saltpeter": 100}),
                                        make_surface_vein_indicator({"gtceu:saltpeter_indicator": 1}),
                                        {"biome_filter": "atoll"}), # Must be added to biome defs

    "surface_sphalerite": make_cluster_vein("surface_sphalerite", 80, 0.25, 15, -20, -5, True, False,
                                            make_rock_replacements(ALL_IGNEOUS + SEDIMENTARY, {"sphalerite": 100}),
                                            make_surface_vein_indicator({"tfc:ore/small_sphalerite": 1})),

    "surface_tetrahedrite": make_cluster_vein("surface_tetrahedrite", 80, 0.2, 15, -20, -5, True, False,
                                              make_rock_replacements(METAMORPHIC, {"tetrahedrite": 100}),
                                              make_surface_vein_indicator({"tfc:ore/small_tetrahedrite": 1})),

    "surface_tricalcium_phosphate": make_cluster_vein("surface_tricalcium_phosphate", 90, 0.2, 15, -20, -5, True, False,
                                                      make_rock_replacements(SEDIMENTARY, {"tricalcium_phosphate": 100}),
                                                      make_surface_vein_indicator({"gtceu:tricalcium_phosphate_indicator": 1})),

    "surface_bauxite_karst": make_disc_vein("surface_bauxite_karst", 160, 0.2, 18, 4, -20, -5, True, False,
                                            make_rock_replacements(KARST_ONLY, {"bauxite": 75, "hematite": 10, "goethite": 5, "bastnasite": 5}),
                                            make_surface_vein_indicator({"gtceu:bauxite_indicator": 5, "tfc:ore/small_hematite": 1}),
                                            {
                                                "placement": [
                                                    {"type": "tfc:climate", "min_temperature": 8}
                                                ]
                                            }),

    "surface_bauxite_tropical": make_disc_vein("surface_bauxite_tropical", 120, 0.2, 18, 4, -20, -5, True, False,
                                               make_rock_replacements(SEDIMENTARY, {"bauxite": 80, "hematite": 15, "goethite": 5}),
                                               make_surface_vein_indicator({"gtceu:bauxite_indicator": 5, "tfc:ore/small_hematite": 1}),
                                               {
                                                   "placement": [
                                                       {"type": "tfc:climate", "min_temperature": 18, "min_groundwater": 300}
                                                   ]
                                               }),

    "massive_tropical_bauxite": make_disc_vein("massive_tropical_bauxite", 240, 0.25, 30, 6, -40, -5, True, True,
                                               make_rock_replacements(SEDIMENTARY, {"bauxite": 70, "hematite": 20, "goethite": 10}),
                                               make_surface_vein_indicator({"gtceu:bauxite_indicator": 7, "tfc:ore/small_hematite": 3}),
                                               {
                                                   "placement": [
                                                       {"type": "tfc:climate", "min_temperature": 18, "min_groundwater": 300}
                                                   ]
                                               }),
            

    # Sand deposits
    "sand_basaltic": make_disc_vein("sand_basaltic", 190, 0.25, 30, 3, -5, 1, True, False,
                                    make_sand_replacements(
                                        ["black", "green", "pink", "red"],
                                        {"basaltic_mineral_sand": 2, "glauconite_sand": 1}),
                                    make_surface_vein_indicator({"gtceu:basaltic_mineral_sand_indicator": 1})),

    "sand_garnet": make_disc_vein("sand_garnet", 270, 0.25, 30, 3, -5, 1, True, False,
                                  make_sand_replacements(ALL_SANDS, {"garnet_sand": 7, "glauconite_sand": 3}),
                                  make_surface_vein_indicator({"gtceu:garnet_sand_indicator": 1})),

    "sand_granitic": make_disc_vein("sand_granitic", 190, 0.25, 30, 3, -5, 1, True, False,
                                    make_sand_replacements(
                                        ["brown", "pink", "white", "yellow"], 
                                        {"granitic_mineral_sand": 8, "cassiterite_sand": 4, "zircon": 3, "monazite": 3}),
                                    make_surface_vein_indicator({"gtceu:granitic_mineral_sand_indicator": 1})),

    "sand_cassiterite": make_disc_vein("sand_cassiterite", 270, 0.2, 15, 4, -5, 1, True, False,
                                       make_sand_replacements(ALL_SANDS, {"cassiterite_sand": 100}),
                                       make_surface_vein_indicator({"gtceu:cassiterite_sand_indicator": 1})),

    # Evaporite deposits

    # High deposits
    "high_cassiterite": make_cluster_vein("high_cassiterite", 90, 0.25, 15, -30, 1, True, False,
                                          make_rock_replacements(IGNEOUS_EXTRUSIVE, {"cassiterite": 100}),
                                          make_surface_vein_indicator({"tfc:ore/small_cassiterite": 1}),
                                          {"biome_filter": "montane"}),

    # Normal deposits
    "normal_spodumene": make_cluster_vein("normal_spodumene", 230, 0.25, 25, 10, 70, False, False,
                                          make_rock_replacements(IGNEOUS_INTRUSIVE, {"spodumene": 4, "petalite": 3, "emerald": 2}),
                                          make_normal_vein_indicator({"gtceu:spodumene_indicator": 2, "gtceu:petalite_indicator": 1}),
                                          {
                                              "placement": [
                                                  {"type": "tfc:volcano", "distance": 0.5},
                                                  {"type": "minecraft:heightmap", "heightmap": "WORLD_SURFACE_WG"}
                                              ]
                                          }),

    # Deep deposits
    "deep_bismuth": make_pipe_vein("deep_bismuth", 160, 0.25, 60, 10, -50, 20, 7, 20, 2, 5, 0, False, False,
                                   make_rock_replacements(IGNEOUS_INTRUSIVE, {"bismuthinite": 9, "silver": 8, "gold": 8}),
                                   None,
                                   {"biome_filter": "montane"})
}

#region MAIN
def main():
    # Export to CSV before placement modifiers are popped off
    fieldnames = [
        "Vein ID",
        "Type",
        "Blocks",
        "Min Y",
        "Max Y",
        "Projected?",
        "Rarity",
        "Density",
        "Size",
        "Height",
        "Radius",
        "Near Lava?",
        "Stones",
        "Placement",
        "Indicator"
    ]
    
    #region WRITE TO CSV
    # TODO: Improve formatting of Blocks and Placement Modifier, Add Biome Filter
    with open("veins.csv", mode="w", newline="", encoding="utf-8") as csv_file:
        writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
        writer.writeheader()
        
        for vein_key, vein_data in VEIN_DICT.items():
            vein_config = vein_data.get("config", {})
            
            blocks = vein_config.get("blocks", {})
            
            stones_string = ""
            ores_string = ""
            
            for index, block_entry in enumerate(blocks):
                stone_id = block_entry.get("replace", "")[0]
                
                if index == 0:
                    stones_string += stone_id
                else:
                    stones_string += f", {stone_id}"
                    
                if index == 0:
                    replacements = block_entry.get("with", [])
                    
                    for replacement_index, replacement_entry in enumerate(replacements):
                        replacement_id = replacement_entry.get("block", "").split("_", maxsplit=1)[1].rsplit("_", 1)[0]
                        replacement_weight = replacement_entry.get("weight", "")
                        
                        if replacement_index == 0:
                            ores_string += f"{replacement_weight} {replacement_id}"
                        else:
                            ores_string += f" / {replacement_weight} {replacement_id}"
            
            placement = vein_data.get("placement", "")
            
            row = {            
                "Vein ID": vein_key,
                "Type": vein_data.get("type", "").split(":")[1],
                "Blocks": ores_string,
                "Min Y": vein_config.get("min_y", ""),
                "Max Y": vein_config.get("max_y", ""),
                "Projected?": vein_config.get("project", False),
                "Rarity": vein_config.get("rarity", ""),
                "Density": vein_config.get("density", ""),
                "Size": vein_config.get("size", ""),
                "Height": vein_config.get("height", ""),
                "Radius": vein_config.get("radius", ""),
                "Near Lava?": vein_config.get("near_lava", False),
                # Format as strings for now
                "Stones": stones_string,
                "Placement": str(placement),
                "Indicator": str(vein_config.get("indicator", ""))
            }
            
            writer.writerow(row)
            
    print(f"Successfully wrote vein data entries to veins.csv")
    
    #region MAKE FEATURES
    os.makedirs(CONFIGURED_DIR, exist_ok=True)
    os.makedirs(PLACED_DIR, exist_ok=True)
    os.makedirs(TAGS_DIR, exist_ok=True)
    os.makedirs(BIOME_VEINS_DIR, exist_ok=True)
    
    configured_count = 0
    placed_count = 0

    biome_groups = defaultdict(list)
    
    for vein_key, data in VEIN_DICT.items():
        biome_type = data.pop("biome_filter", "veins")
        if not biome_type:
            biome_type = "veins"

        placement_modifiers = data.pop("placement", {})
        
        cf_filename = f"{vein_key}.json"
        cf_path = os.path.join(CONFIGURED_DIR, cf_filename)
        with open(cf_path, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=2)
        configured_count += 1
        
        pf_filename = f"{vein_key}.json"
        pf_path = os.path.join(PLACED_DIR, pf_filename)
        placed_json = build_placed_feature_json(vein_key, placement_modifiers)
        
        with open(pf_path, "w", encoding="utf-8") as f:
            json.dump(placed_json, f, indent=2)
        placed_count += 1
        
        placed_feature_id = f"tfinfinity:vein/{vein_key}"
        
        biome_groups[biome_type].append(placed_feature_id)
        
        print(f"Generated files for: {vein_key}")

    # Biome Tags
    for biome_name, feature_list in biome_groups.items():
        feature_list.sort()   

        tag_data = {
            "replace": True,
            "values": feature_list
        }

        if biome_name == "veins":
            tag_path = os.path.join(TAGS_DIR, "veins.json")
        else:
            tag_path = os.path.join(BIOME_VEINS_DIR, f"{biome_name}.json")

        with open(tag_path, "w", encoding="utf-8") as f:
            json.dump(tag_data, f, indent=2)
        
    print(f"\nSuccessfully generated {configured_count} configured features and {placed_count} placed features")

if __name__ == "__main__":
    main()