import ast
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
IGNEOUS_EXTRUSIVE = ["rhyolite", "basalt", "andesite", "dacite", "tuff"]  # Tuff is treated as igneous extrusive due to mineral similarity
ALL_IGNEOUS = IGNEOUS_INTRUSIVE + IGNEOUS_EXTRUSIVE

SEDIMENTARY = ["shale", "claystone", "limestone", "conglomerate", "dolomite", "chert", "chalk"]
METAMORPHIC = ["quartzite", "slate", "phyllite", "schist", "gneiss", "marble"]

CARBONATE_ROCKS = ["marble", "chalk", "limestone", "dolomite"]

ALL_ROCKS = ALL_IGNEOUS + SEDIMENTARY + METAMORPHIC
ALL_SANDS = ["brown", "white", "black", "red", "yellow", "green", "pink"]

# Specific rocks
MAFIC_ONLY = ["basalt", "gabbro"]
KARST_ONLY = ["limestone", "dolomite"]


#region HELPER FUNCTIONS
# Vein Functions
class VeinBuilder:
    def __init__(self, name: str, rarity: int, density: float, min_y: int, max_y: int, project: bool | None = None,
                 project_offset: bool | None = None):
        self.vein = {'type': "", 'config': {}, 'placement': []}

        vein_config = self.vein['config']

        vein_config['random_name'] = name
        vein_config['rarity'] = rarity
        vein_config['density'] = density
        vein_config['min_y'] = min_y
        vein_config['max_y'] = max_y

        if project is not None:
            vein_config['project'] = project
        if project_offset is not None:
            vein_config['project_offset'] = project_offset

    def cluster_vein(self, size: int):
        self.vein['type'] = "tfc:cluster_vein"
        self.vein['config']['size'] = size

        return self

    def disc_vein(self, size: int, height: int):
        self.vein['type'] = "tfc:disc_vein"
        self.vein['config']['size'] = size
        self.vein['config']['height'] = height

        return self

    def pipe_vein(self, height: int, radius: int, min_skew: int, max_skew: int, min_slant: int, max_slant: int, sign: float):
        self.vein['type'] = "tfc:pipe_vein"

        vein_config = self.vein['config']

        vein_config['height'] = height
        vein_config['radius'] = radius
        vein_config['min_skew'] = min_skew
        vein_config['max_skew'] = max_skew
        vein_config['min_slant'] = min_slant
        vein_config['max_slant'] = max_slant
        vein_config['sign'] = sign

        return self

    def near_lava(self):
        self.vein['config']['near_lava'] = True

        return self

    def blocks(self, blocks: list):
        self.vein['config']['blocks'] = blocks

        return self

    def indicator(self, indicator: dict):
        self.vein['config']['indicator'] = indicator

        return self

    def biome_filter(self, biome: str):
        self.vein['biome_filter'] = biome

        return self

    def placement(self, rules: dict):
        self.vein['placement'].append(rules)

        return self

    def build(self) -> dict:
        return self.vein


def make_rock_ore(material : str, rock : str):
    namespace = "gtceu"
    if ":" in material:
        split_str = material.split(":")
        namespace = split_str[0]
        material = split_str[1]

    return f"{namespace}:{rock}_{material}_ore"

def make_sand_ore(material : str, color : str):
    namespace = "gtceu"
    if ":" in material:
        split_str = material.split(":")
        namespace = split_str[0]
        material = split_str[1]

    return f"{namespace}:{color}_{material}_ore"

def make_sandstone_ore(material : str, color : str):
    namespace = "gtceu"
    if ":" in material:
        split_str = material.split(":")
        namespace = split_str[0]
        material = split_str[1]

    return f"{namespace}:{color}_sandstone_{material}_ore"

# Block Replacement Functions
class ReplacementMapBuilder:
    def __init__(self):
        self.blocks = []

    def rock(self, rock_list: list, ore_weights: dict, block_weights: dict | None = None):
        for rock in rock_list:
            target = f"tfc:rock/raw/{rock}"
            with_entry = [
                {"weight": weight, "block": make_rock_ore(ore, rock)}
                for ore, weight in (ore_weights or {}).items()
            ] + [
                {"weight": weight, "block": f"{block}"}
                for block, weight in (block_weights or {}).items()
            ]
            self.blocks.append({"replace": [target], "with": with_entry})
        return self

    def sand(self, sand_colors: list, ore_weights: dict, block_weights: dict | None = None):
        for color in sand_colors:
            target = f"tfc:sand/{color}"
            with_entry = [
                {"weight": weight, "block": make_sand_ore(ore, color)}
                for ore, weight in (ore_weights or {}).items()
            ] + [
                 {"weight": weight, "block": f"{block}"}
                for block, weight in (block_weights or {}).items()
             ]
            self.blocks.append({"replace": [target], "with": with_entry})
        return self

    def sandstone(self, sand_colors: list, ore_weights: dict, block_weights: dict | None = None):
        for color in sand_colors:
            target = f"tfc:raw_sandstone/{color}"
            with_entry = [
                {"weight": weight, "block": make_sandstone_ore(ore, color)}
                for ore, weight in (ore_weights or {}).items()
            ] + [
                 {"weight": weight, "block": f"{block}"}
                 for block, weight in (block_weights or {}).items()
             ]
            self.blocks.append({"replace": [target], "with": with_entry})
        return self

    def build(self) -> list:
        return self.blocks


# Vein Indicator Functions
def make_vein_indicator(blocks: dict, indicator_rarity: int, indicator_depth: int, indicator_underground_rarity: int,
                        indicator_underground_count: int):
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
    return make_vein_indicator(blocks, 24, 35, 1, 0)


def make_deep_vein_indicator(blocks: dict):
    return make_vein_indicator(blocks, 0, 35, 1, 5)


def build_placed_feature_json(vein_key, placement_modifiers):
    feature_id = f"tfinfinity:vein/{vein_key}"

    placement = placement_modifiers or []

    return {
        "feature": feature_id,
        "placement": placement
    }


#region Load Configs from CSV
def load_configs_from_csv(csv_filepath: str):
    loaded_veins = {}

    def parse_weights(dist_str: str) -> tuple[dict, dict]:
        ore_weights = {}
        block_weights = {}
        if not dist_str or not dist_str.strip():
            return ore_weights, block_weights

        # Double pipes ("||") are used in the Distribution column to separate ore weights and block weights
        if "||" in dist_str:
            ore_entries, block_entries = dist_str.split("||", 1)
        else:
            ore_entries, block_entries = dist_str, ""

        def process_weights(entries_str: str) -> dict:
            weighted_list = {}
            segments = entries_str.split(" / ")
            for segment in segments:

                segment = segment.strip()
                if not segment:
                    continue

                parts = segment.rsplit(maxsplit=1)

                if len(parts) == 2 and parts[1].isdigit():

                    block = parts[0].strip()
                    weight = parts[1]
                    weighted_list[block] = int(weight)
                else:
                    block = segment.strip()
                    weighted_list[block] = 100
            return weighted_list

        ore_weights = process_weights(ore_entries)
        block_weights = process_weights(block_entries)

        return ore_weights, block_weights

    with open(csv_filepath, mode='r', encoding='utf-8') as file:
        # Skip extra heading lines in exported sheet from Google Sheets
        for _ in range(2):
            file.readline()

        reader = csv.DictReader(file)

        for row_num, row in enumerate(reader, start=3):
            vein_id = row.get("Vein ID")
            if not vein_id:
                print(f"Skipping row {row_num}: Missing 'Vein ID'")
                continue
            vein_type = row.get("Type", "")
            if not vein_type:
                print(f"Skipping row {row_num}: {vein_id} has no 'Type'")
                continue
            distribution = row.get("Distribution", "")
            if not distribution:
                print(f"Skipping row {row_num}: {vein_id} has no 'Distribution'")
                continue

            rarity = int(row.get("Rarity") or 0)
            density = float(row.get("Density") or 0.0)
            min_y = int(row.get("Min Y") or 0)
            max_y = int(row.get("Max Y") or 0)
            projected = bool(row.get("Projected?") or False)
            project_offset = bool(row.get("Project Offset?") or False)
            near_lava = bool(row.get("Near Lava?") or False)
            biome = row.get("Biome", "")

            builder = VeinBuilder(vein_id, rarity, density, min_y, max_y, projected, project_offset)

            match vein_type:
                case "cluster_vein":
                    size = int(row.get("Size") or 0)
                    builder.cluster_vein(size)
                case "disc_vein":
                    size = int(row.get("Size") or 0)
                    height = int(row.get("Height") or 0)
                    builder.disc_vein(size, height)
                case "pipe_vein":
                    height = int(row.get("Height") or 0)
                    radius = int(row.get("Radius") or 0)

                    skew = row.get("Skew").split(",", 1)
                    min_skew = int(skew[0].strip() or 0)
                    max_skew = int(skew[1].strip() or 0)

                    slant = row.get("Slant").split(",", 1)
                    min_slant = int(slant[0].strip() or 0)
                    max_slant = int(slant[1].strip() or 0)

                    sign = float(row.get("Sign") or 0.0)
                    builder.pipe_vein(height, radius, min_skew, max_skew, min_slant, max_slant, sign)
                case _:
                    print(f"Skipping row {row_num}: Invalid Type '{vein_type}")
                    continue

            if near_lava:
                builder.near_lava()

            if biome:
                builder.biome_filter(biome)

            ore_weights, block_weights = parse_weights(distribution)

            map_builder = ReplacementMapBuilder()
            for header, value in row.items():

                header = header.lower()

                if not value or not value.strip():
                    continue

                if value:
                    if header in ALL_ROCKS:
                        map_builder.rock([header], ore_weights, block_weights)
                    elif header in ALL_SANDS:
                        if value == "sand":
                            map_builder.sand([header], ore_weights, block_weights)
                        elif value == "sandstone":
                            map_builder.sandstone([header], ore_weights, block_weights)
                        else:
                            map_builder.sand([header], ore_weights, block_weights)
                            map_builder.sandstone([header], ore_weights, block_weights)

            builder.blocks(map_builder.build())

            placement_exp = row.get("Placement", "")
            if placement_exp and placement_exp not in ("[]", ""):
                try:
                    placements = json.loads(f"[{placement_exp}]")
                    for placement in placements:
                        builder.placement(placement)
                except Exception as e:
                    print(f"Error parsing 'Placement' on row {row_num}: {e}")

            indicator_exp = row.get("Indicator", "")
            if indicator_exp and indicator_exp not in ("[]", ""):
                ind_vars = indicator_exp.split(",")

                ind_blocks = ast.literal_eval(ind_vars[0]) or {}
                ind_rarity = int(ind_vars[1]) or 0
                ind_depth = int(ind_vars[2]) or 0
                ind_under_rarity = int(ind_vars[3]) or 0
                ind_under_depth = int(ind_vars[4]) or 0

                try:
                    builder.indicator(make_vein_indicator(ind_blocks, ind_rarity, ind_depth, ind_under_rarity, ind_under_depth))
                except Exception as e:
                    print(f"Error parsing 'Indicator' on row {row_num}: {e}")

            loaded_veins[vein_id] = builder.build()

    return loaded_veins

#region MAIN
def main():
    csv_filepath = "veins_input.csv"

    if not os.path.exists(csv_filepath):
        print(f"Error: Target file '{csv_filepath}' not found.")
        return

    print(f"Parsing configs from '{csv_filepath}'...")
    vein_dict = load_configs_from_csv(csv_filepath)

    #region MAKE FEATURES
    os.makedirs(CONFIGURED_DIR, exist_ok=True)
    os.makedirs(PLACED_DIR, exist_ok=True)
    os.makedirs(TAGS_DIR, exist_ok=True)
    os.makedirs(BIOME_VEINS_DIR, exist_ok=True)

    configured_count = 0
    placed_count = 0

    biome_groups = defaultdict(list)

    for vein_key, data in vein_dict.items():
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


    # Select base TFC veins to be added to tag list
    default_veins = [
        "tfc:geode",
        "tfc:vein/gabbro_dike",
        "tfc:vein/diorite_dike",
        "tfc:vein/granite_dike",
        "tfc:vein/gravel"
    ]

    for default_vein in default_veins:
        biome_groups['veins'].append(default_vein)


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

    print(f"\nSuccessfully generated {configured_count} configured features and {placed_count} placed features for {len(biome_groups)} biome{"" if len(biome_groups) < 2 else "s"}!")


if __name__ == "__main__":
    main()
