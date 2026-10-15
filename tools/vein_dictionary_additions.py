from generate_vein_configs import VeinBuilder, ReplacementMapBuilder, make_vein_indicator
from tools_global import ALL_SOILS, ALL_ROCKS, ALL_SANDS, SEDIMENTARY, METAMORPHIC, IGNEOUS_INTRUSIVE

# Manual Vein Additions Imported After CSV Veins
VEIN_ADDITIONS = {
    "fossil_sand_cassiterite": VeinBuilder("fossil_sand_cassiterite", 140, 0.25, -20, -5, True)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().sand(["black", "brown", "red", "yellow"], {"cassiterite_sand": 100}).sandstone(["black", "brown", "red", "yellow"], {"cassiterite": 100}).build())
        .indicator(make_vein_indicator({"gtceu:cassiterite_sand_indicator": 1}, 14, 35, 1, 0))
        .build()
    # "normal_anthracite_graphite": VeinBuilder("normal_anthracite_graphite", 270, 0.3, -80, -20, True)
    #     .disc_vein(35, 3)
    #     .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["phyllite", "slate"], {}, {"tfinfinity:anthracite": 100}).sandstone(ALL_SANDS, {}, {"tfinfinity:anthracite": 100}).rock(IGNEOUS_INTRUSIVE + ["gneiss", "marble", "quartzite", "schist"], {"graphite": 100}).build())
    #     .build()
    #
    # Laterite Deposits
    # "bauxite_laterite": VeinBuilder("bauxite_laterite", 120, 0.4, -10, 1, True, True)
    #     .disc_vein(20, 5)
    #     .blocks(ReplacementMapBuilder().dirt(ALL_SOILS, {"tfinfinity:laterite/bauxite": 100}).grass(ALL_SOILS, {"tfinfinity:laterite/bauxite_grass": 100}).mud(ALL_SOILS, {"tfinfinity:laterite/bauxite": 100}).build())
    #     .placement({"type": "tfc:climate", "min_groundwater": 300, "min_temperature": 18})
    #     .build(),
    #
    # "garnierite_laterite": VeinBuilder("swamp_laterite", 120, 0.3, -20, -5, True, True)
    #     .disc_vein(25, 5)
    #     .blocks(ReplacementMapBuilder().dirt(ALL_SOILS, {"tfinfinity:laterite/garnierite": 100}).grass(ALL_SOILS, {"tfinfinity:laterite/garnierite_grass": 100}).mud(ALL_SOILS, {"tfinfinity:laterite/garnierite": 100}).build())
    #     .placement({"type": "tfc:climate", "min_groundwater": 150, "min_temperature": -13})
    #     .biome_filter("swamp")
    #     .build(),
    #
    # "limonite_laterite": VeinBuilder("swamp_laterite", 120, 0.5, -10, 1, True, True)
    #     .disc_vein(25, 5)
    #     .blocks(ReplacementMapBuilder().dirt(ALL_SOILS, {"tfinfinity:laterite/limonite": 100}).grass(ALL_SOILS, {"tfinfinity:laterite/limonite_grass": 100}).mud(ALL_SOILS, {"tfinfinity:laterite/limonite": 100}).build())
    #     .placement({"type": "tfc:climate", "min_groundwater": 150, "min_temperature": -13})
    #     .biome_filter("swamp")
    #     .build()
}