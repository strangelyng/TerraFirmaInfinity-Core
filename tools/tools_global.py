import re

# Rock Groups for Block Replacement Map
IGNEOUS_INTRUSIVE = ["granite", "diorite", "gabbro"]
IGNEOUS_EXTRUSIVE = ["rhyolite", "basalt", "andesite", "dacite", "tuff"]
ALL_IGNEOUS = IGNEOUS_INTRUSIVE + IGNEOUS_EXTRUSIVE

SEDIMENTARY = ["shale", "claystone", "limestone", "conglomerate", "dolomite", "chert", "chalk"]
METAMORPHIC = ["quartzite", "slate", "phyllite", "schist", "gneiss", "marble"]

ALL_ROCKS = ALL_IGNEOUS + SEDIMENTARY + METAMORPHIC
ALL_SANDS = ["brown", "white", "black", "red", "yellow", "green", "pink"]

CARBONATE_ROCKS = ["chalk", "limestone", "dolomite", "marble"]
KARST_ONLY = ["limestone", "dolomite"]

BIOME_LISTS = {
    "swamp": [
        "tfc:low_canyons",
        "tfc:lowlands",
        "tfc:salt_marsh"
    ],
    "montane": [
        "tfc:collisional_mountains",
        "tfc:extreme_doline_mountains",
        "tfc:glacially_carved_mountains",
        "tfc:glacially_carved_oceanic_mountains",
        "tfc:glaciated_mountains",
        "tfc:glaciated_oceanic_mountains",
        "tfc:ice_sheet_mountains",
        "tfc:ice_sheet_mountains_edge",
        "tfc:ice_sheet_oceanic_mountains",
        "tfc:ice_sheet_oceanic_mountains_edge",
        "tfc:mountain_lake",
        "tfc:mountains",
        "tfc:oceanic_mountain_lake",
        "tfc:oceanic_mountains",
        "tfc:old_mountain_lake",
        "tfc:old_mountains",
        "tfc:active_shield_volcano",
        "tfc:ancient_shield_volcano",
        "tfc:dormant_shield_volcano",
        "tfc:extinct_shield_volcano",
        "tfc:glacially_carved_volcanic_mountains",
        "tfc:glacially_carved_volcanic_oceanic_mountains",
        "tfc:glaciated_shield_volcano",
        "tfc:glaciated_volcanic_mountains",
        "tfc:glaciated_volcanic_oceanic_mountains",
        "tfc:ice_sheet_shield_volcano",
        "tfc:ice_sheet_volcanic_mountains",
        "tfc:ice_sheet_volcanic_oceanic_mountains",
        "tfc:oceanic_volcanic_arc",
        "tfc:old_shield_volcano_shore",
        "tfc:shield_volcano_shore",
        "tfc:sunken_shield_volcano",
        "tfc:volcanic_island",
        "tfc:volcanic_mountain_islands",
        "tfc:volcanic_mountain_lake",
        "tfc:volcanic_mountains",
        "tfc:volcanic_oceanic_mountain_lake",
        "tfc:volcanic_oceanic_mountains",
        "tfc:tower_karst_bay",
        "tfc:tower_karst_canyons",
        "tfc:tower_karst_highlands",
        "tfc:tower_karst_hills",
        "tfc:tower_karst_lake",
        "tfc:tower_karst_plains"
    ],
    "river": [
        "tfc:lake",
        "tfc:rift_lake",
        "tfc:river",
        "tfc:tower_karst_lake",
        "tfc:oceanic_mountain_lake",
        "tfc:volcanic_oceanic_mountain_lake",
        "tfc:meltwater_lake"
    ],
    "volcanic": [
        "tfc:active_shield_volcano",
        "tfc:ancient_shield_volcano",
        "tfc:dormant_shield_volcano",
        "tfc:extinct_shield_volcano",
        "tfc:glacially_carved_volcanic_mountains",
        "tfc:glacially_carved_volcanic_oceanic_mountains",
        "tfc:glaciated_shield_volcano",
        "tfc:glaciated_volcanic_mountains",
        "tfc:glaciated_volcanic_oceanic_mountains",
        "tfc:ice_sheet_shield_volcano",
        "tfc:ice_sheet_volcanic_mountains",
        "tfc:ice_sheet_volcanic_oceanic_mountains",
        "tfc:oceanic_volcanic_arc",
        "tfc:old_shield_volcano_shore",
        "tfc:shield_volcano_shore",
        "tfc:sunken_shield_volcano",
        "tfc:volcanic_island",
        "tfc:volcanic_mountain_islands",
        "tfc:volcanic_mountain_lake",
        "tfc:volcanic_mountains",
        "tfc:volcanic_oceanic_mountain_lake",
        "tfc:volcanic_oceanic_mountains"
    ],
    "collisional_mountains": [
        "tfc:collisional_mountains"
    ],
    "beach": [
        "tfc:guano_island",
        "tfc:shore",
        "tfc:tidal_flats",
        "tfc:sea_stacks",
        "tfc:terrace_upper",
        "tfc:terrace_lower",
        "tfc:setback_cliffs",
        "tfc:coastal_dunes",
        "tfc:rocky_shores",
        "tfc:embayments",
        "tfc:tower_karst_bay",
        "tfc:shield_volcano_shore",
        "tfc:old_shield_volcano_shore",
        "tfc:ice_sheet_oceanic",
        "tfc:ice_sheet_shore"
    ],
    "atoll": [
        "tfc:ocean_atolls",
        "tfc:deep_ocean_atolls",
        "tfc:guano_island"
    ]
}

def ore_block_to_material(block_id: str, include_namespace: bool):
    material = block_id.split(":")[-1]

    for s in ALL_SANDS:
        pattern = f"^{s}_(sandstone_)?"

        material = re.sub(pattern, "", material)

    for r in ALL_ROCKS:
        pattern = f"^{r}_"

        material = re.sub(pattern, "", material)

    material = material.replace("_ore", "")

    if include_namespace:
        if ":" in block_id and not block_id.startswith("gtceu:"):
            material = f"{block_id.split(':')[0]}:{material}"

    return material