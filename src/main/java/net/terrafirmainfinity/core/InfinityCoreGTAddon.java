package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.terrafirmainfinity.core.common.data.recipe.InfinityRecipeHandler;
import net.terrafirmainfinity.core.common.data.recipe.InfinityToolRecipeHandler;
import net.terrafirmainfinity.core.common.data.recipe.misc.InfinityMachineRecipes;

import java.util.function.Consumer;

@GTAddon(InfinityCore.MOD_ID)
public class InfinityCoreGTAddon implements IGTAddon
{
    @Override
    public GTRegistrate getRegistrate()
    {
        return InfinityCore.InfinityRegistrate;
    }

    @Override
    public void addRecipes(RecipeOutput provider) {
        // TODO: Figure out how to do this now
        // ItemMaterialData.registerMaterialEntry(() -> TFCItems.KAOLIN_CLAY, ingot, InfinityMaterials.Kaolinite);

        InfinityRecipeHandler.testRecipes(provider);
        InfinityRecipeHandler.landslideRecipes(provider);
        InfinityMachineRecipes.init(provider);

        for (Material material : GTRegistries.MATERIALS) {
            if (material.hasFlag(MaterialFlags.DISABLE_MATERIAL_RECIPES)) {
                continue;
            }

            InfinityToolRecipeHandler.run(provider, material);
        }
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {

    }
}
