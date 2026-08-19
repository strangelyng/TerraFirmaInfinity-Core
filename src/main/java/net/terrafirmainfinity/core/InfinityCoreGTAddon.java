package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.terrafirmainfinity.core.common.data.recipe.InfinityRecipeHandler;
import net.terrafirmainfinity.core.common.data.recipe.misc.InfinityMachineRecipes;

import java.util.function.Consumer;

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
        InfinityRecipeHandler.testRecipes(provider);
        InfinityMachineRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {

    }
}
