from generate_vein_configs import VeinBuilder, ReplacementMapBuilder, make_vein_indicator
from generate_vein_configs import IGNEOUS_EXTRUSIVE, IGNEOUS_INTRUSIVE, ALL_IGNEOUS, METAMORPHIC, SEDIMENTARY, CARBONATE_ROCKS


VEIN_DICT = {
    # Base TFC Vein Recreations
    ## Surface
    "surface_bismuthinite": VeinBuilder("surface_bismuthinite", 48, 0.3, 40, 100)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"tfinfinity:bismuthinite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_bismuthinite": 1}, 14, 35, 1, 0))
        .build(),

    "surface_hematite": VeinBuilder("surface_hematite", 45, 0.4, 10, 90)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"hematite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_hematite": 1}, 24, 35, 1, 0))
        .build(),

    "surface_limonite": VeinBuilder("surface_limonite", 90, 0.4, 10, 90)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"yellow_limonite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_limonite": 1}, 24, 35, 1, 0))
        .build(),

    "surface_magnetite": VeinBuilder("surface_magnetite", 90, 0.4, 10, 90)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"magnetite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_magnetite": 1}, 24, 35, 1, 0))
        .build(),

    "surface_malachite": VeinBuilder("surface_malachite", 48, 0.25, 40, 100)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(CARBONATE_ROCKS, {"malachite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_malachite": 1}, 14, 35, 1, 0))
        .build(),

    "surface_native_copper": VeinBuilder("surface_native_copper", 36, 0.25, 40, 100)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"copper": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_native_copper": 1}, 14, 35, 1, 0))
        .build(),

    "surface_sphalerite": VeinBuilder("surface_sphalerite", 40, 0.3, 40, 100)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"sphalerite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_sphalerite": 1}, 12, 35, 1, 0))
        .build(),

    ## Montane
    "montane_bismuth": VeinBuilder("montane_bismuth", 24, 0.3, 100, 220)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"tfinfinity:bismuthinite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_bismuthinite": 1}, 14, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_cassiterite": VeinBuilder("montane_cassiterite", 2, 0.4, 80, 300)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"cassiterite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_cassiterite": 1}, 12, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_cinnabar": VeinBuilder("montane_cinnabar", 14, 0.6, 120, 280)
        .cluster_vein(14)
        .blocks(ReplacementMapBuilder().rock(["quartzite", "phyllite", "gneiss", "schist"], {"cinnabar": 100}).build())
        .biome_filter("montane")
        .build(),

    "montane_hematite": VeinBuilder("montane_hematite", 25, 0.4, 90, 250)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"hematite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_hematite": 1}, 24, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_limonite": VeinBuilder("montane_limonite", 45, 0.4, 90, 250)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"yellow_limonite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_limonite": 1}, 24, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_magnetite": VeinBuilder("montane_magnetite", 45, 0.4, 90, 250)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"magnetite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_magnetite": 1}, 24, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_malachite": VeinBuilder("montane_malachite", 11, 0.25, 40, 300)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(CARBONATE_ROCKS, {"malachite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_malachite": 1}, 14, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_native_copper": VeinBuilder("montane_native_copper", 16, 0.25, 100, 300)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"copper": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_native_copper": 1}, 14, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_native_silver": VeinBuilder("montane_native_silver", 7, 0.2, 90, 280)
        .cluster_vein(10)
        .blocks(ReplacementMapBuilder().rock(["granite", "diorite"], {"silver": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_native_silver": 1}, 12, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_sphalerite": VeinBuilder("montane_sphalerite", 20, 0.3, 100, 220)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_EXTRUSIVE, {"sphalerite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_sphalerite": 1}, 12, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    "montane_tetrahedrite": VeinBuilder("montane_tetrahedrite", 3, 0.25, 90, 270)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"tetrahedrite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_tetrahedrite": 1}, 8, 35, 1, 0))
        .biome_filter("montane")
        .build(),

    ## Normal
    "normal_bismuthinite": VeinBuilder("normal_bismuthinite", 45, 0.6, -80, 20)
        .cluster_vein(40)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"tfinfinity:bismuthinite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_bismuthinite": 1}, 0, 35, 1, 4))
        .build(),

    "normal_cinnabar": VeinBuilder("normal_cinnabar", 14, 0.6, -70, 10)
        .cluster_vein(18)
        .blocks(ReplacementMapBuilder().rock(["quartzite", "phyllite", "gneiss", "schist"], {"cinnabar": 100}).build())
        .build(),

    "normal_garnierite": VeinBuilder("normal_garnierite", 35, 0.3, -80, 0)
        .cluster_vein(18)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"garnierite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_garnierite": 1}, 12, 35, 1, 0))
        .build(),

    "normal_malachite": VeinBuilder("normal_malachite", 45, 0.5, -30, 70)
        .cluster_vein(30)
        .blocks(ReplacementMapBuilder().rock(CARBONATE_ROCKS, {"malachite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_malachite": 1}, 25, 35, 1, 0))
        .build(),

    "normal_native_gold": VeinBuilder("normal_native_gold", 90, 0.25, 0, 70)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS, {"gold": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_native_gold": 1}, 40, 35, 1, 0))
        .build(),

    "normal_native_silver": VeinBuilder("normal_native_silver", 25, 0.6, -80, 20)
        .cluster_vein(25)
        .blocks(ReplacementMapBuilder().rock(["granite", "diorite", "gneiss", "schist"], {"silver": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_native_silver": 1}, 0, 35, 1, 9))
        .build(),

    "normal_sphalerite": VeinBuilder("normal_sphalerite", 45, 0.6, -80, 20)
        .cluster_vein(40)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"sphalerite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_sphalerite": 1}, 0, 35, 1, 5))
        .build(),

    "normal_tetrahedrite": VeinBuilder("normal_tetrahedrite", 40, 0.5, -30, 70)
        .cluster_vein(30)
        .blocks(ReplacementMapBuilder().rock(METAMORPHIC, {"tetrahedrite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_tetrahedrite": 1}, 25, 35, 1, 0))
        .build(),

    ## Deep / Rich
    "deep_ruby": VeinBuilder("deep_ruby", 80, 0.2, -70, 10)
        .cluster_vein(22)
        .blocks(ReplacementMapBuilder().rock(["marble"], {"ruby": 100}).build())
        .build(),

    "rich_native_gold": VeinBuilder("rich_native_gold", 50, 0.5, -80, 20)
        .cluster_vein(40)
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE, {"gold": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_native_gold": 1}, 0, 35, 1, 4))
        .build(),

    ## Misc
    "fake_native_gold": VeinBuilder("fake_native_gold", 16, 0.35, -50, 70)
        .cluster_vein(15)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS, {"pyrite": 100}).build())
        .build(),

    "gabbro_garnierite": VeinBuilder("gabbro_garnierite", 20, 0.6, -80, 0)
        .cluster_vein(30)
        .blocks(ReplacementMapBuilder().rock(["gabbro"], {"garnierite": 100}).build())
        .indicator(make_vein_indicator({"tfc:ore/small_garnierite": 1}, 0, 35, 1, 7))
        .build(),

    "borax": VeinBuilder("borax", 40, 0.2, 40, 100)
        .disc_vein(23, 3)
        .blocks(ReplacementMapBuilder().rock(["claystone", "limestone", "shale"], {"borax": 100}).build())
        .build(),

    "bituminous_coal": VeinBuilder("bituminous_coal", 210, 0.9, -35, -12, True, True)
        .disc_vein(50, 3)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"coal": 100}).build())
        .build(),

    "cryolite": VeinBuilder("cryolite", 16, 0.7, -70, -10)
        .cluster_vein(18)
        .blocks(ReplacementMapBuilder().rock(["granite", "diorite"], {"tfinfinity:cryolite": 100}).build())
        .build(),

    "graphite": VeinBuilder("graphite", 20, 0.4, -30, 60)
        .cluster_vein(20)
        .blocks(ReplacementMapBuilder().rock(["gneiss", "marble", "quartzite", "schist"], {"graphite": 100}).build())
        .build(),

    "gypsum": VeinBuilder("gypsum", 70, 0.3, 40, 100)
        .disc_vein(25, 5)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"graphite": 100}).build())
        .build(),

    "halite": VeinBuilder("halite", 110, 0.85, -45, -12, True, True)
        .disc_vein(35, 4)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"salt": 100}).build())
        .build(),

    "saltpeter": VeinBuilder("saltpeter", 110, 0.4, 40, 100)
        .disc_vein(35, 5)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + ["tuff"], {"saltpeter": 100}).build())
        .build(),

    "sulfur": VeinBuilder("sulfur", 4, 0.25, -64, -45)
        .disc_vein(18, 5)
        .near_lava()
        .blocks(ReplacementMapBuilder().rock(IGNEOUS_INTRUSIVE + METAMORPHIC, {"sulfur": 100}).build())
        .build(),

    "sylvite": VeinBuilder("sylvite", 60, 0.35, 40, 100)
        .disc_vein(35, 5)
        .blocks(ReplacementMapBuilder().rock(["shale", "claystone", "chert"], {"rock_salt": 100}).build())
        .build(),

    "tuff_sulfur": VeinBuilder("tuff_sulfur", 2, 0.45, 40, 200)
        .disc_vein(18, 4)
        .blocks(ReplacementMapBuilder().rock(["tuff"], {"sulfur": 100}).build())
        .build(),

    ## Gems
    "diamond": VeinBuilder("diamond", 30, 0.15, -64, 100)
        .pipe_vein(60, 5, 5, 13, 0, 2, 0)
        .blocks(ReplacementMapBuilder().rock(["gabbro"], {"diamond": 100}).build())
        .build(),


    "emerald": VeinBuilder("emerald", 80, 0.15, -64, 100)
        .pipe_vein(60, 5, 5, 13, 0, 2, 0)
        .blocks(ReplacementMapBuilder().rock(["granite", "diorite"], {"emerald": 100}).build())
        .build(),


    "lapis_lazuli": VeinBuilder("lapis_lazuli", 30, 0.12, -20, 80)
        .cluster_vein(30)
        .blocks(ReplacementMapBuilder().rock(["limestone", "marble"], {"lapis": 100}).build())
        .build(),



    ## Collisional Mountains
    "ruby_marble_belt": VeinBuilder("ruby_marble_belt", 16, 1, -40, -4, True, True)
        .disc_vein(50, 5)
        .blocks(ReplacementMapBuilder().rock(ALL_IGNEOUS + METAMORPHIC, {"ruby": 6}, {"tfc:rock/raw/marble": 94}).build())
        .biome_filter("collisional_mountains")
        .build(),

    ## River
    "amethyst": VeinBuilder("amethyst", 25, 0.2, 40, 60)
        .disc_vein(8, 4)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + METAMORPHIC + ["tuff"], {"amethyst": 100}).build())
        .biome_filter("river")
        .build(),

    "opal": VeinBuilder("opal", 25, 0.2, 40, 60)
        .disc_vein(8, 4)
        .blocks(ReplacementMapBuilder().rock(SEDIMENTARY + IGNEOUS_EXTRUSIVE + ["tuff"], {"opal": 100}).build())
        .biome_filter("river")
        .build()
}