package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static net.terrafirmainfinity.core.common.data.InfinityRecipeTypes.ROASTER_RECIPE;

@GTAddon(InfinityCore.MOD_ID)
public class InfinityCoreGTAddon implements IGTAddon
{
    @Override
    public GTRegistrate getRegistrate()
    {
        return InfinityCore.REGISTRATE;
    }

    @Override
    public void gtInitComplete()
    {
        InfinityCore.LOGGER.info("InfinityCoreGTAddon has loaded!");
    }

    @Override
    public void addRecipes(RecipeOutput provider) {
        /* TEST RECIPES */
        ROASTER_RECIPE.recipeBuilder(InfinityCore.id("chalcocite"))
                .inputItems(TagPrefix.dust, GTMaterials.Chalcocite, 2)
                .outputItems(TagPrefix.dust, GTMaterials.CupricOxide, 2) // Should be CuprousOxide
                .outputFluids(GTMaterials.SulfurDioxide.getFluid(1000))
                .save(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {

    }
}
