package net.terrafirmainfinity.core.integration.emi;

public class OreVeinDictionary {
    public static final OreVeinInfoRecipe[] RECIPES = {
            new OreVeinInfoRecipe("surface_bauxite_tropical", "minecraft:overworld",
                    120, 0.2, -20, -5, 18, 4, 0,
                    false, true, false, 35,
                    new String[] {"tfc:rock/raw/chalk", "tfc:rock/raw/chert", "tfc:rock/raw/claystone", "tfc:rock/raw/conglomerate", "tfc:rock/raw/dolomite", "tfc:rock/raw/limestone", "tfc:rock/raw/shale"},
                    new OreVeinInfoRecipe.WeightedBlock[] {new OreVeinInfoRecipe.WeightedBlock("bauxite", 75), new OreVeinInfoRecipe.WeightedBlock("hematite", 10), new OreVeinInfoRecipe.WeightedBlock("goethite", 5), new OreVeinInfoRecipe.WeightedBlock("bastnasite", 5)},
                    null, null,
                    300, null, 18, null,
                    null)
    };
}
