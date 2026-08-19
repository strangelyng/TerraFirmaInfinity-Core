package net.terrafirmainfinity.core.common.data.recipe;

import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.minecraft.data.recipes.RecipeOutput;
import net.terrafirmainfinity.core.InfinityCore;

import static net.terrafirmainfinity.core.common.data.InfinityRecipeTypes.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class InfinityRecipeHandler {
    public static void testRecipes(RecipeOutput provider) {
        ROASTER_RECIPE.recipeBuilder(InfinityCore.id("chalcocite"))
                .inputItems(dust, Chalcocite, 2)
                .outputItems(dust, CupricOxide, 2) // Should be CuprousOxide
                .outputFluids(SulfurDioxide.getFluid(1000))
                .save(provider);

        GTRecipeTypes.ELECTROLYZER_RECIPES.recipeBuilder(InfinityCore.id("water_electrolysis"))
                .notConsumable(rod, Nickel)
                .notConsumable(rod, Iron)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(2000))
                .outputFluids(Oxygen.getFluid(1000))
                .EUt(VA[LV])
                .duration(1500)
                .save(provider);

        ELECTROLYTIC_CELL_RECIPE.recipeBuilder(InfinityCore.id("water_electrolysis"))
                .notConsumable(rod, Nickel)
                .notConsumable(rod, Iron)
                .notConsumableFluid(SodiumHydroxide.getFluid(50))
                .inputFluids(Water.getFluid(1000))
                .outputFluids(Hydrogen.getFluid(2000))
                .outputFluids(Oxygen.getFluid(1000))
                .EUt(VA[LV])
                .duration(400)
                .save(provider);

        GRAVITY_SEPARATOR_RECIPE.recipeBuilder(InfinityCore.id("crushed_hematite_ore"))
                .inputItems(crushed, Hematite)
                .inputFluids(Water.getFluid(100))
                .outputItems(crushedPurified, Hematite)
                .chancedOutput(dust, Magnetite, 3333)
                .chancedOutput(dust, Stone, 5000)
                .outputFluids(Water.getFluid(75))
                .duration(100)
                .save(provider);
    }
}
