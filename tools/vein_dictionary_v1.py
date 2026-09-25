from generate_vein_configs import VeinBuilder, ReplacementMapBuilder, make_surface_vein_indicator, make_normal_vein_indicator, make_deep_vein_indicator
from generate_vein_configs import IGNEOUS_EXTRUSIVE, IGNEOUS_INTRUSIVE, ALL_IGNEOUS, METAMORPHIC, SEDIMENTARY, CARBONATE_ROCKS, KARST_ONLY, ALL_SANDS


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
    "deep_apatite": VeinBuilder("deep_apatite", 240, 0.3, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"apatite": 4, "tricalcium_phosphate": 3, "pyrochlore": 1}).build())
    .indicator(make_deep_vein_indicator({"gtceu:apatite_indicator": 1}))
    .build(),

    "deep_bismuth": VeinBuilder("deep_bismuth", 230, 0.25, -50, 20)
    .pipe_vein(60, 10, 7, 20, 2, 5, 0)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tfinfinity:bismuthinite": 9, "silver": 8, "gold": 8}).build())
    .indicator(make_deep_vein_indicator({"tfc:ore/small_bismuthinite": 5, "tfc:ore/small_native_silver": 2, "tfc:ore/small_native_gold": 2}))
    .biome_filter("montane")
    .build(),

    "deep_copper_sulfide": VeinBuilder("deep_copper_sulfide", 80, 0.3, -50, 20)
    .disc_vein(25, 5)
    .near_lava()
    .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"sulfur": 8, "bornite": 1, "chalcocite": 1}).build())
    .indicator(make_deep_vein_indicator({"gtceu:sulfur_indicator": 5, "gtceu:bornite_indicator": 1}))
    .build(),

    "deep_galena": VeinBuilder("deep_galena", 250, 0.25, -50, 20)
    .cluster_vein(25)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"galena": 6, "sphalerite": 3, "tfinfinity:acanthite": 2, "silver": 2}).build())
    .indicator(make_deep_vein_indicator({"gtceu:galena_indicator": 3, "tfc:ore/small_sphalerite": 2, "tfc:ore/small_native_silver": 1}))
    .build(),

    "deep_graphite": VeinBuilder("deep_graphite", 120, 0.35, -30, 50)
    .disc_vein(15, 4)
    .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"graphite": 100}).build())
    .indicator(make_deep_vein_indicator({"gtceu:graphite_indicator": 1}))
    .build(),

    "deep_kyanite": VeinBuilder("deep_kyanite", 240, 0.2, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE + ["gneiss", "schist"], {"mica": 5, "kyanite": 5, "talc": 3}).build())
    .build(),

    "deep_lapis": VeinBuilder("deep_lapis", 250, 0.35, -50, 20)
    .pipe_vein(60, 10, 7, 20, 2, 5, 0)
    .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"lazurite": 5, "sodalite": 4, "lapis": 3, "calcite": 3, "pyrite": 2}).build())
    .build(),

    "deep_magnesiochromite": VeinBuilder("deep_magnesiochromite", 240, 0.25, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(["gabbro", "basalt"], {"chromite": 3, "magnesite": 3, "olivine": 2}).build())
    .build(),

    "deep_nb_hf_zr_th": VeinBuilder("deep_nb_hf_zr_th", 330, 0.2, -50, 20)
    .cluster_vein(25)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tfinfinity:thorite": 3, "tfinfinity:columbite": 3, "tfinfinity:zircon": 2, "tfinfinity:hafnon": 1}).build())
    .build(),

    "deep_niobium_tantalum": VeinBuilder("deep_niobium_tantalum", 270, 0.2, -50, 20)
    .cluster_vein(25)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tantalite": 4, "tfinfinity:columbite": 3, "cassiterite": 2}).build())
    .build(),

    "deep_pge_deposit": VeinBuilder("deep_pge_deposit", 400, 0.2, -70, -10)
    .disc_vein(20, 6)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"cooperite": 3, "tfinfinity:sperrylite": 3, "platinum": 2, "palladium": 2}).build())
    .build(),

    "deep_pge_sulfides": VeinBuilder("deep_pge_sulfides", 300, 0.2, -50, 20)
    .cluster_vein(25)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"pentlandite": 5, "chalcopyrite": 2, "pyrite": 2, "tfinfinity:sperrylite": 1}).build())
    .build(),

    "deep_pitchblende": VeinBuilder("deep_pitchblende", 270, 0.2, -50, 20)
    .cluster_vein(25)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"pitchblende": 6, "uraninite": 4, "amethyst": 3, "tfinfinity:thorite": 2}).build())
    .build(),

    "deep_pollucite": VeinBuilder("deep_pollucite", 240, 0.2, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"mica": 5, "pollucite": 5, "cassiterite": 3}).build())
    .build(),

    "deep_ruby": VeinBuilder("deep_ruby", 210, 0.2, -50, 10)
    .cluster_vein(20)
    .blocks(ReplacementMapBuilder().rock(["marble"], {"ruby": 100}).build())
    .build(),

    "deep_saltpeter_evaporite": VeinBuilder("deep_saltpeter_evaporite", 240, 0.2, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"saltpeter": 3, "alunite": 2}).build())
    .placement({"type": "tfc:climate", "max_groundwater": 150, "min_temperature": 18})
    .build(),

    "deep_sapphire": VeinBuilder("deep_sapphire", 230, 0.2, -50, 20)
    .pipe_vein(60, 10, 7, 20, 2, 5, 0)
    .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS, {"sapphire": 8, "green_sapphire": 7, "pyrope": 4, "almandine": 3}).build())
    .build(),

    "deep_scheelite": VeinBuilder("deep_scheelite", 300, 0.3, -50, 20)
    .cluster_vein(25)
    .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"scheelite": 3, "tfinfinity:wolframite": 3, "lepidolite": 1}).build())
    .build(),

    "deep_sulfide_molybdate": VeinBuilder("deep_sulfide_molybdate", 270, 0.25, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"wulfenite": 10, "molybdenite": 7, "powellite": 8}).build())
    .biome_filter("volcano")
    .build(),

    "deep_sulfur": VeinBuilder("deep_sulfur", 60, 0.6, -60, -40)
    .disc_vein(30, 10)
    .near_lava()
    .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + METAMORPHIC, {"sulfur": 1}).build())
    .build(),

    "deep_thorianite": VeinBuilder("deep_thorianite", 270, 0.2, -50, 20)
    .cluster_vein(20)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tfinfinity:thorianite": 6, "uraninite": 4, "emerald": 3, "tfinfinity:thorite": 2}).build())
    .build(),

    "deep_tin_tungsten": VeinBuilder("deep_tin_tungsten", 210, 0.25, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"cassiterite": 4, "tfinfinity:arsenopyrite": 3, "tfinfinity:wolframite": 2}).build())
    .biome_filter("volcano")
    .build(),

    "deep_topaz": VeinBuilder("deep_topaz", 250, 0.2, -50, 20)
    .cluster_vein(30)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"blue_topaz": 6, "topaz": 5, "chalcocite": 3, "bornite": 1}).build())
    .build(),

    "deep_vanadium_magnetite": VeinBuilder("deep_vanadium_magnetite", 230, 0.3, -50, 20)
    .cluster_vein(35)
    .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"magnetite": 12, "vanadium_magnetite": 4, "gold": 4, "ilmenite": 2}).build())
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