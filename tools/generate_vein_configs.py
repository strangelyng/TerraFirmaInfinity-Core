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
class ReplacementMap:
    def __init__(self):
        self.blocks = []

class ReplacementMapBuilder:
    def __init__(self):
        self.replacement_map = ReplacementMap()

    def rock(self, rock_list, ore_weights, block_weights = None):
        for rock in rock_list:
            target = f"tfc:rock/raw/{rock}"
            with_entry = [
                {"weight": weight, "block": make_rock_ore(ore, rock)}
                for ore, weight in (ore_weights or {}).items()
            ] + [
                {"weight": weight, "block": f"{block}"}
                for block, weight in (block_weights or {}).items()
            ]
            self.replacement_map.blocks.append({"replace": [target], "with": with_entry})
        return self

    def sand(self, sand_colors, ore_weights, block_weights = None):
        for color in sand_colors:
            target = f"tfc:sand/{color}"
            with_entry = [
                {"weight": weight, "block": make_sand_ore(ore, color)}
                for ore, weight in (ore_weights or {}).items()
            ] + [
                 {"weight": weight, "block": f"{block}"}
                for block, weight in (block_weights or {}).items()
             ]
            self.replacement_map.blocks.append({"replace": [target], "with": with_entry})
        return self

    def sandstone(self, sand_colors, ore_weights, block_weights = None):
        for color in sand_colors:
            target = f"tfc:raw_sandstone/{color}"
            with_entry = [
                {"weight": weight, "block": make_sandstone_ore(ore, color)}
                for ore, weight in (ore_weights or {}).items()
            ] + [
                 {"weight": weight, "block": f"{block}"}
                 for block, weight in (block_weights or {}).items()
             ]
            self.replacement_map.blocks.append({"replace": [target], "with": with_entry})
        return self

    def build(self) -> list:
        return self.replacement_map.blocks


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
    "surface_bauxite_karst": VeinBuilder("surface_bauxite_karst", 160, 0.2, -20, -5, True)
        .disc_vein(18, 4)
        .blocks(ReplacementMapBuilder().rock(KARST_ONLY, {"bauxite": 75, "hematite": 10, "goethite": 5, "bastnasite": 5}).build())
        .indicator(make_surface_vein_indicator({"gtceu:bauxite_indicator": 5, "tfc:ore/small_hematite": 1}))
        .placement({"type": "tfc:climate", "min_temperature": 8, "max_temperature": 18, "min_groundwater": 150})
        .build(),

    "surface_bauxite_tropical": VeinBuilder("surface_bauxite_tropical", 120, 0.2, -20, -5, True)
        .disc_vein(18, 4)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"bauxite": 80, "hematite": 15, "goethite": 5}).build())
        .indicator(make_surface_vein_indicator({"gtceu:bauxite_indicator": 5, "tfc:ore/small_hematite": 1}))
        .placement({"type": "tfc:climate", "min_temperature": 18, "min_groundwater": 300})
        .build(),

    "surface_bog_iron": VeinBuilder("surface_bog_iron", 90, 0.2, -30, -5, True)
        .disc_vein(20, 5)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"yellow_limonite": 80, "goethite": 15, "garnierite": 5}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_limonite": 3, "gtceu:goethite_indicator": 1}))
        .placement({"type": "tfc:climate", "min_temperature": -13, "min_groundwater": 150})
        .biome_filter("swamp")
        .build(),

    "surface_bismuthinite": VeinBuilder("surface_bismuthinite", 80, 0.25, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"tfinfinity:bismuthinite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_bismuthinite": 1}))
        .build(),

    "surface_coal": VeinBuilder("surface_bismuthinite", 90, 0.35, -20, -5, True)
        .disc_vein(15, 8)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"coal": 100}).sandstone(ALL_SANDS, {"coal": 100}).build())
        .indicator(make_surface_vein_indicator({"gtceu:coal_indicator": 1}))
        .build(),

    "surface_copper": VeinBuilder("surface_copper", 70, 0.25, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"copper": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_native_copper": 1}))
        .build(),

    "surface_gold": VeinBuilder("surface_gold", 100, 0.2, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS, {"gold": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_native_gold": 1}))
        .build(),

    "surface_hematite": VeinBuilder("surface_hematite", 90, 0.25, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"hematite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_hematite": 1}))
        .build(),

    "surface_magnetite": VeinBuilder("surface_magnetite", 90, 0.2, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"magnetite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_magnetite": 1}))
        .build(),

    "surface_malachite": VeinBuilder("surface_malachite", 80, 0.2, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(CARBONATE_ROCKS, {"malachite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_malachite": 1}))
        .build(),

    "surface_mica": VeinBuilder("surface_mica", 120, 0.15, -20, -5, True)
        .cluster_vein(10)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE + ["gneiss", "schist"], {"mica": 100}).build())
        .indicator(make_surface_vein_indicator({"gtceu:mica_indicator": 1}))
        .build(),

    "surface_saltpeter": VeinBuilder("surface_saltpeter", 90, 0.25, -20, -5, True)
        .disc_vein(20, 5)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"saltpeter": 100}).build())
        .indicator(make_surface_vein_indicator({"gtceu:saltpeter_indicator": 1}))
        .biome_filter("atoll")
        .build(),

    "surface_sphalerite": VeinBuilder("surface_sphalerite", 80, 0.25, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + SEDIMENTARY, {"sphalerite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_sphalerite": 1}))
        .build(),

    "surface_tetrahedrite": VeinBuilder("surface_tetrahedrite", 80, 0.2, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"tetrahedrite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_tetrahedrite": 1}))
        .build(),

    "surface_tricalcium_phosphate": VeinBuilder("surface_tricalcium_phosphate", 90, 0.2, -20, -5, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"tricalcium_phosphate": 100}).build())
        .indicator(make_surface_vein_indicator({"gtceu:tricalcium_phosphate_indicator": 1}))
        .build(),

    # Sand deposits
    "sand_basaltic": VeinBuilder("sand_basaltic", 190, 0.25, -5, 1, True)
        .disc_vein(25, 3)
        .blocks(ReplacementMapBuilder().sand(["black", "green", "pink", "red"], {"basaltic_mineral_sand": 2, "glauconite_sand": 1}).build())
        .indicator(make_surface_vein_indicator({"gtceu:basaltic_mineral_sand_indicator": 1}))
        .biome_filter("beach")
        .build(),

    "sand_cassiterite": VeinBuilder("sand_basaltic", 270, 0.2, -5, 1, True)
        .disc_vein(20, 4)
        .blocks(ReplacementMapBuilder().sand(ALL_SANDS, {"cassiterite_sand": 100}).build())
        .indicator(make_surface_vein_indicator({"gtceu:cassiterite_sand_indicator": 1}))
        .biome_filter("beach")
        .build(),

    "sand_garnet": VeinBuilder("sand_garnet", 270, 0.25, -5, 1, True)
        .disc_vein(25, 3)
        .blocks(ReplacementMapBuilder().sand(ALL_SANDS, {"garnet_sand": 7, "glauconite_sand": 3}).build())
        .indicator(make_surface_vein_indicator({"gtceu:garnet_sand_indicator": 1}))
        .biome_filter("beach")
        .build(),

    "sand_granitic": VeinBuilder("sand_granitic", 190, 0.25, -5, 1, True)
        .disc_vein(25, 3)
        .blocks(ReplacementMapBuilder().sand(["brown", "pink", "white", "yellow"], {"granitic_mineral_sand": 8, "cassiterite_sand": 4, "tfinfinity:zircon": 3, "monazite": 3}).build())
        .indicator(make_surface_vein_indicator({"gtceu:granitic_mineral_sand_indicator": 1}))
        .biome_filter("beach")
        .build(),

    # Evaporite deposits
    "halite_gypsum_evaporite": VeinBuilder("halite_gypsum_evaporite", 85, 0.2, -20, 1, True)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().sand(ALL_SANDS, {"salt": 3, "gypsum": 2}).sandstone(ALL_SANDS, {"salt": 3, "gypsum": 2}).build())
        .indicator(make_surface_vein_indicator({"gtceu:salt_indicator": 3, "gtceu:gypsum_indicator": 2}))
        .placement({"type": "tfc:climate", "max_groundwater": 150, "min_temperature": 18})
        .build(),

    "palygorskite_basin": VeinBuilder("palygorskite_basin", 95, 0.2, -20, 1, True)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().sand(ALL_SANDS, {"fullers_earth": 8, "glauconite_sand": 3}).sandstone(ALL_SANDS, {"fullers_earth": 8, "glauconite_sand": 3}).build())
        .indicator(make_surface_vein_indicator({"gtceu:fullers_earth_indicator": 8, "gtceu:glauconite_sand_indicator": 3}))
        .placement({"type": "tfc:climate", "max_groundwater": 150, "min_temperature": 18})
        .build(),

    "sylvite_borax_evaporite": VeinBuilder("sylvite_borax_evaporite", 85, 0.2, -20, 1, True)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().sand(ALL_SANDS, {"rock_salt": 7, "borax": 3}).sandstone(ALL_SANDS, {"rock_salt": 7, "borax": 3}).build())
        .indicator(make_surface_vein_indicator({"gtceu:rock_salt_indicator": 3, "gtceu:borax_indicator": 2}))
        .placement({"type": "tfc:climate", "max_groundwater": 150, "min_temperature": 18})
        .build(),

    "trona_borax_basin": VeinBuilder("trona_borax_basin", 120, 0.2, -20, 1, True)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().sand(ALL_SANDS, {"trona": 4, "borax": 3}).sandstone(ALL_SANDS, {"trona": 4, "borax": 3}).build())
        .indicator(make_surface_vein_indicator({"gtceu:trona_indicator": 4, "gtceu:borax_indicator": 3}))
        .placement({"type": "tfc:climate", "max_groundwater": 150, "min_temperature": 18})
        .build(),

    # High deposits
    "high_cassiterite": VeinBuilder("high_cassiterite", 90, 0.25, -30, 1, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"cassiterite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_cassiterite": 1}))
        .biome_filter("montane")
        .build(),

    "high_galena": VeinBuilder("high_galena", 90, 0.25, -30, 1, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + METAMORPHIC, {"galena": 6, "tfinfinity:acanthite": 3, "silver": 1}).build())
        .indicator(make_surface_vein_indicator({"gtceu:galena_indicator": 6, "tfc:ore/small_native_silver": 2}))
        .biome_filter("montane")
        .build(),

    "high_malachite": VeinBuilder("high_malachite", 90, 0.25, -30, 1, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(CARBONATE_ROCKS, {"malachite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_malachite": 1}))
        .biome_filter("montane")
        .build(),

    "high_silver": VeinBuilder("high_silver", 90, 0.25, -30, 1, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(["diorite", "granite"], {"silver": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_native_silver": 1}))
        .biome_filter("montane")
        .build(),

    "high_sulfur": VeinBuilder("high_silver", 30, 0.9, 100, 170, False)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + METAMORPHIC, {"sulfur": 100}).build())
        .indicator(make_surface_vein_indicator({"gtceu:sulfur_indicator": 1}))
        .biome_filter("volcano")
        .build(),

    "high_tetrahedrite": VeinBuilder("high_tetrahedrite", 90, 0.25, -30, 1, True)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"tetrahedrite": 100}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_tetrahedrite": 1}))
        .biome_filter("montane")
        .build(),

    # Normal deposits
    "normal_asbestos": VeinBuilder("normal_asbestos", 160, 0.25, 10, 70)
        .disc_vein(20, 10)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE + METAMORPHIC, {"asbestos": 100}).build())
        .indicator(make_normal_vein_indicator({"gtceu:asbestos_indicator": 1}))
        .biome_filter("volcano")
        .build(),

    "normal_banded_iron": VeinBuilder("normal_banded_iron", 180, 0.2, 30, 80)
        .disc_vein(20, 10)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"hematite": 5, "magnetite": 4}).build())
        .indicator(make_normal_vein_indicator({"tfc:ore/small_hematite": 5, "tfc:ore/small_magnetite": 4}))
        .build(),

    "normal_copper_nickel": VeinBuilder("normal_copper_nickel", 200, 0.25, 10, 70)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(KARST_ONLY, {"chalcopyrite": 3, "tfinfinity:millerite": 2}).build())
        .indicator(make_normal_vein_indicator({"gtceu:chalcopyrite_indicator": 3, "tfinfinity:millerite_indicator": 2}))
        .build(),

    "normal_copper_sulfide": VeinBuilder("normal_copper_sulfide", 180, 0.3, 10, 70)
        .cluster_vein(25)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + METAMORPHIC + IGNEOUS_INTRUSIVE, {"chalcopyrite": 3, "pyrite": 2, "copper": 2}).build())
        .indicator(make_normal_vein_indicator({"gtceu:chalcopyrite_indicator": 1}))
        .build(),

    "normal_diatomite_karst": VeinBuilder("normal_diatomite_karst", 200, 0.3, 10, 70)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(KARST_ONLY, {"diatomite": 30, "gypsum": 12, "sulfur": 8}).build())
        .indicator(make_normal_vein_indicator({"gtceu:diatomite_indicator": 3, "gtceu:gypsum_indicator": 1}))
        .build(),

    "normal_quartz_gold": VeinBuilder("normal_quartz_gold", 160, 0.25, 10, 70)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(METAMORPHIC + IGNEOUS_EXTRUSIVE, {"gold": 5, "quartzite": 4, "tfinfinity:arsenopyrite": 3}).build())
        .indicator(make_normal_vein_indicator({"tfc:ore/small_native_gold": 3, "gtceu:quartzite_indicator": 2}))
        .build(),

    "normal_mica": VeinBuilder("normal_mica", 200, 0.23, 10, 70)
        .disc_vein(20, 10)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["gneiss", "schist"], {"mica": 7, "kyanite": 5}).build())
        .indicator(make_normal_vein_indicator({"gtceu:mica_indicator": 3, "gtceu:kyanite_indicator": 5}))
        .build(),

    "normal_nickel_cobalt": VeinBuilder("normal_nickel_cobalt", 160, 0.3, 10, 70)
        .cluster_vein(25)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"garnierite": 3, "cobaltite": 2, "pentlandite": 2}).build())
        .indicator(make_normal_vein_indicator({"tfc:ore/small_garnierite": 3, "gtceu:cobaltite_indicator": 2, "gtceu:pentlandite_indicator": 2}))
        .build(),

    "normal_quartz_barite": VeinBuilder("normal_quartz_barite", 180, 0.2, 10, 70)
        .disc_vein(20, 10)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + IGNEOUS_INTRUSIVE, {"quartzite": 3, "barite": 1}).build())
        .indicator(make_normal_vein_indicator({"gtceu:quartzite_indicator": 3}))
        .build(),

    "normal_red_garnet": VeinBuilder("normal_red_garnet", 160, 0.3, 10, 70)
        .disc_vein(15, 10)
        .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"red_garnet": 3, "pyrope": 3, "spessartine": 8, "almandine": 5}).build())
        .indicator(make_normal_vein_indicator({"gtceu:red_garnet_indicator": 1}))
        .build(),

    "normal_sodium_borate": VeinBuilder("normal_sodium_borate", 200, 0.2, 30, 80)
        .disc_vein(20, 10)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"salt": 6, "rock_salt": 3, "borax": 1}).build())
        .indicator(make_normal_vein_indicator({"gtceu:salt_indicator": 2, "gtceu:rock_salt_indicator": 1}))
        .placement({"type": "tfc:climate", "max_groundwater": 200})
        .build(),

    "normal_silver_sulfide": VeinBuilder("normal_silver_sulfide", 180, 0.3, 10, 70)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS, {"tfinfinity:acanthite": 7, "galena": 3, "silver": 1}).build())
        .indicator(make_normal_vein_indicator({"tfinfinity:acanthite_indicator": 3, "gtceu:galena_indicator": 1}))
        .build(),

    "normal_spodumene": VeinBuilder("normal_spodumene", 230, 0.25, 10, 70)
        .cluster_vein(25)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"spodumene": 4, "tfinfinity:petalite": 3, "emerald": 2}).build())
        .indicator(make_normal_vein_indicator({"gtceu:spodumene_indicator": 3, "tfinfinity:petalite_indicator": 1}))
        .placement({"type": "tfc:volcano", "distance": 0.5})
        .placement({"type": "minecraft:heightmap", "heightmap": "WORLD_SURFACE_WG"})
        .build(),

    "normal_sulfides": VeinBuilder("normal_sulfides", 200, 0.2, 10, 70)
        .disc_vein(20, 10)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + CARBONATE_ROCKS, {"bornite": 4, "sulfur": 3, "stibnite": 2}).build())
        .indicator(make_normal_vein_indicator({"gtceu:bornite_indicator": 3, "gtceu:sulfur_indicator": 2, "gtceu:stibnite_indicator": 1}))
        .build(),

    "normal_yellow_garnet": VeinBuilder("normal_yellow_garnet", 160, 0.3, 10, 70)
        .disc_vein(15, 10)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"yellow_garnet": 4, "andradite": 5, "grossular": 8, "uvarovite": 3}).build())
        .indicator(make_normal_vein_indicator({"gtceu:yellow_garnet_indicator": 1}))
        .build(),

    "normal_zeolite": VeinBuilder("normal_zeolite", 190, 0.25, 10, 70)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(["basalt"], {"zeolite": 5, "tfinfinity:fluorite": 3, "barite": 2}).build())
        .indicator(make_normal_vein_indicator({"gtceu:zeolite_indicator": 3, "tfinfinity:fluorite_indicator": 1}))
        .build(),

    "normal_zinc_lead_sulfide": VeinBuilder("normal_zinc_lead_sulfide", 180, 0.2, 10, 70)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + SEDIMENTARY, {"sphalerite": 5, "galena": 3, "pyrite": 2}).build())
        .indicator(make_normal_vein_indicator({"tfc:ore/small_sphalerite": 3, "gtceu:galena_indicator": 1}))
        .build(),

    # Deep deposits
    "deep_bismuth": VeinBuilder("deep_bismuth", 180, 0.25, -50, 20)
        .pipe_vein(60, 10, 7, 20, 2, 5, 0)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tfinfinity:bismuthinite": 9, "silver": 8, "gold": 8}).build())
        .biome_filter("montane")
        .build(),

    # Special deposits
    "bog_iron_manganese": VeinBuilder("bog_iron_manganese", 130, 0.2, -5, 1, True)
        .disc_vein(20, 5)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"pyrolusite": 4, "yellow_limonite": 2, "goethite": 1}).build())
        .indicator(make_surface_vein_indicator({"tfc:ore/small_limonite": 5, "gtceu:goethite_indicator": 2, "gtceu:pyrolusite_indicator": 1}))
        .placement({"type": "tfc:climate", "min_temperature": -13, "min_groundwater": 150})
        .biome_filter("swamp")
        .build(),

    "ivittut_cryolite": VeinBuilder("ivittut_cryolite", 270, 0.35, -50, 70)
        .cluster_vein(40)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tfinfinity:cryolite": 7, "tfinfinity:fluorite": 3, "blue_topaz": 2, "galena": 1}).build())
        .indicator(make_normal_vein_indicator({"tfinfinity:cryolite_indicator": 3, "tfinfinity:fluorite_indicator": 1, "gtceu:galena_indicator": 1}))
        .biome_filter("ice_sheets")
        .build(),

    "kimberlite_pipe": VeinBuilder("kimberlite_pipe", 240, 0.25, -64, 120)
        .pipe_vein(80, 10, 7, 20, 2, 5, 0)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"diamond": 4, "ilmenite": 3}).build())
        .biome_filter("volcano")
        .build(),

    "massive_bauxite_tropical": VeinBuilder("massive_bauxite_tropical", 270, 0.25, -40, -5, True, True)
        .disc_vein(30, 6)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY, {"bauxite": 7, "hematite": 2, "goethite": 1}).build())
        .indicator(make_surface_vein_indicator({"gtceu:bauxite_indicator": 7, "tfc:ore/small_hematite": 3}))
        .placement({"type": "tfc:climate", "min_temperature": 18, "min_groundwater": 300})
        .build(),

    "porphyry_copper": VeinBuilder("porphyry_copper", 140, 0.3, -64, 120)
        .pipe_vein(80, 10, 7, 20, 2, 5, 0)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"chalcopyrite": 8, "bornite": 6, "pyrite": 4, "molybdenite": 3}).build())
        .biome_filter("volcano")
        .build(),

    "tuff_bentonite_deposit": VeinBuilder("tuff_bentonite_deposit", 120, 0.2, -50, 70)
        .disc_vein(20, 4)
        .blocks(ReplacementMapBuilder().rock(["tuff"], {"bentonite": 100}).build())
        .build(),

    "volcanogenic_massive_sulfide": VeinBuilder("volcanogenic_massive_sulfide", 270, 0.3, -50, 70)
        .cluster_vein(40)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"galena": 1, "sphalerite": 2, "chalcopyrite": 2, "pyrite": 2}).build())
        .indicator(make_deep_vein_indicator({"gtceu:chalcopyrite_indicator": 1, "tfc:ore/small_sphalerite": 1, "gtceu:pyrite_indicator": 1}))
        .biome_filter("volcano")
        .build()
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
