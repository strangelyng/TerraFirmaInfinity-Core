from generate_vein_configs import VeinBuilder, ReplacementMapBuilder
from tools_global import ALL_SOILS

# Manual Vein Additions Imported After CSV Veins
VEIN_ADDITIONS = {
    # Laterite Deposits
    "bauxite_laterite": VeinBuilder("bauxite_laterite", 120, 0.4, -10, 1, True, True)
        .disc_vein(20, 5)
        .blocks(ReplacementMapBuilder().dirt(ALL_SOILS, {"tfinfinity:laterite/bauxite": 100}).grass(ALL_SOILS, {"tfinfinity:laterite/bauxite_grass": 100}).mud(ALL_SOILS, {"tfinfinity:laterite/bauxite": 100}).build())
        .placement({"type": "tfc:climate", "min_groundwater": 300, "min_temperature": 18})
        .build(),

    "garnierite_laterite": VeinBuilder("swamp_laterite", 120, 0.3, -20, -5, True, True)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().dirt(ALL_SOILS, {"tfinfinity:laterite/garnierite": 100}).grass(ALL_SOILS, {"tfinfinity:laterite/garnierite_grass": 100}).mud(ALL_SOILS, {"tfinfinity:laterite/garnierite": 100}).build())
        .placement({"type": "tfc:climate", "min_groundwater": 150, "min_temperature": -13})
        .biome_filter("swamp")
        .build(),

    "limonite_laterite": VeinBuilder("swamp_laterite", 120, 0.5, -10, 1, True, True)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().dirt(ALL_SOILS, {"tfinfinity:laterite/limonite": 100}).grass(ALL_SOILS, {"tfinfinity:laterite/limonite_grass": 100}).mud(ALL_SOILS, {"tfinfinity:laterite/limonite": 100}).build())
        .placement({"type": "tfc:climate", "min_groundwater": 150, "min_temperature": -13})
        .biome_filter("swamp")
        .build()
}