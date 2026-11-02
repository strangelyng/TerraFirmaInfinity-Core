package net.terrafirmainfinity.core.common.data.recipe;

import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.common.recipes.LandslideRecipe;
import net.dries007.tfc.common.recipes.ingredients.BlockIngredient;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.block.Blocks;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterials;

import static net.terrafirmainfinity.core.common.data.InfinityRecipeTypes.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.api.GTValues.*;
import static net.terrafirmainfinity.core.common.data.material.InfinityMaterials.*;

public class InfinityRecipeHandler {
    public static void testRecipes(RecipeOutput provider) {
        ROASTER_RECIPES.recipeBuilder(InfinityCore.id("purified_chalcopyrite"))
                .inputItems(crushedPurified, Chalcopyrite, 4)
                .inputItems(dust, SiliconDioxide, 3)
                .inputFluids(Oxygen.getFluid(3000))
                .outputItems(dust, CupricOxide, 2)
                .outputItems(dust, Ferrosilite, 5)
                .outputFluids(SulfurDioxide.getFluid(2000))
                .EUt(VA[LV])
                .duration(200)
                .save(provider);

        LOOM_RECIPES.recipeBuilder(InfinityCore.id("white_wool"))
                .inputItems(TFCItems.WOOL_CLOTH::get, 4)
                .outputItems(Blocks.WHITE_WOOL, 8)
                .duration(25*4)
                .EUt(VA[ULV])
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

//        ELECTROLYTIC_CELL_RECIPE.recipeBuilder(InfinityCore.id("water_electrolysis"))
//                .notConsumable(rod, Nickel)
//                .notConsumable(rod, Iron)
//                .notConsumableFluid(SodiumHydroxide.getFluid(50))
//                .inputFluids(Water.getFluid(1000))
//                .outputFluids(Hydrogen.getFluid(2000))
//                .outputFluids(Oxygen.getFluid(1000))
//                .EUt(VA[LV])
//                .duration(400)
//                .save(provider);

        SPIRAL_SEPARATOR_RECIPES.recipeBuilder(InfinityCore.id("crushed_hematite_ore"))
                .inputItems(crushed, Hematite)
                .inputFluids(Water.getFluid(100))
                .outputItems(crushedPurified, Hematite)
                .chancedOutput(dust, Magnetite, 3333)
                .chancedOutput(dust, Stone, 5000)
                .outputFluids(Water.getFluid(75))
                .duration(200)
                .save(provider);

        METALLURGICAL_CONVERTER_RECIPES.recipeBuilder(InfinityCore.id("pig_iron_to_steel_air"))
                .inputItems(ingot, InfinityMaterials.PigIron)
                .inputFluids(Air.getFluid(200))
                .outputItems(ingot, Steel)
                .EUt(VA[MV])
                .duration(600)
                .save(provider);

        METALLURGICAL_CONVERTER_RECIPES.recipeBuilder(InfinityCore.id("pig_iron_to_steel"))
                .inputItems(ingot, InfinityMaterials.PigIron)
                .inputFluids(Oxygen.getFluid(200))
                .outputItems(ingot, Steel)
                .EUt(VA[MV])
                .duration(400)
                .save(provider);

//        FLASH_SMELTING_RECIPE.recipeBuilder(InfinityCore.id("purified_chalcopyrite"))
//                .inputItems(crushedPurified, Chalcopyrite)
//                .inputItems(InfinityTagPrefix.powder, CalciumCarbonate) // Flux (use tag?)
//                .inputFluids(Oxygen.getFluid(200))
//                .outputFluids(Copper.getFluid(144)) // Copper Matte
//                .outputFluids(Lava.getFluid(100)) // Slag
//                .outputFluids(SulfurDioxide.getFluid(100)) // Off-Gas
//                .EUt(VA[MV])
//                .duration(200)
//                .save(provider);

        ADVANCED_ARC_FURNACE_RECIPES.recipeBuilder(InfinityCore.id("stainless_steel"))
                .inputItems(dust, Iron, 6)
                .inputItems(dust, Chromium, 1)
                .inputItems(dust, Manganese, 1)
                .inputItems(dust, Nickel, 1)
                .outputFluids(StainlessSteel.getFluid(144*9))
                .EUt(VA[HV])
                .duration(1100)
                .save(provider);

        FLUIDIZED_BR_RECIPES.recipeBuilder(InfinityCore.id("coal_gasification"))
                .inputItems(dust, Coal, 16)
                .inputFluids(Steam.getFluid(4000))
                .outputItems(dust, Coke, 12)
                .outputFluids(Syngas.getFluid(8000))
                .EUt(96)
                .duration(320)
                .save(provider);
    }

    public static void landslideRecipes(RecipeOutput provider) {
        provider.accept(InfinityCore.id("collapse/laterite/bauxite_grass"),
                new LandslideRecipe(
                        BlockIngredient.of(InfinityBlocks.BAUXITE_GRASS.get()),
                        InfinityBlocks.BAUXITE_SOIL.get().defaultBlockState()
                ), null);

        provider.accept(InfinityCore.id("collapse/laterite/garnierite_grass"),
                new LandslideRecipe(
                        BlockIngredient.of(InfinityBlocks.GARNIERITE_GRASS.get()),
                        InfinityBlocks.GARNIERITE_SOIL.get().defaultBlockState()
                ), null);

        provider.accept(InfinityCore.id("collapse/laterite/limonite_grass"),
                new LandslideRecipe(
                        BlockIngredient.of(InfinityBlocks.LIMONITE_GRASS.get()),
                        InfinityBlocks.LIMONITE_SOIL.get().defaultBlockState()
                ), null);
    }
}
