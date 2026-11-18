package net.terrafirmainfinity.core.common.data.recipe.misc;

import net.minecraft.data.recipes.RecipeOutput;
import net.terrafirmainfinity.core.InfinityCore;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static net.terrafirmainfinity.core.common.data.InfinityRecipeTypes.LIQUID_FUEL_RECIPES;

public class InfinityFuelRecipes {

    public static void init(RecipeOutput provider) {
        // Liquid Fuel Recipes (for use in Fuel Hatches)
        LIQUID_FUEL_RECIPES.recipeBuilder(InfinityCore.id("crude_oil"))
                .inputFluids(RawOil.getFluid(64))
                .duration(15)
                .save(provider);

        LIQUID_FUEL_RECIPES.recipeBuilder(InfinityCore.id("diesel"))
                .inputFluids(Diesel.getFluid(1))
                .duration(15)
                .save(provider);

        LIQUID_FUEL_RECIPES.recipeBuilder(InfinityCore.id("natural_gas"))
                .inputFluids(NaturalGas.getFluid(8))
                .duration(5)
                .save(provider);

        LIQUID_FUEL_RECIPES.recipeBuilder(InfinityCore.id("coal_gas"))
                .inputFluids(CoalGas.getFluid(1))
                .duration(3)
                .save(provider);

        LIQUID_FUEL_RECIPES.recipeBuilder(InfinityCore.id("lpg"))
                .inputFluids(LPG.getFluid(1))
                .duration(10)
                .save(provider);
    }
}
