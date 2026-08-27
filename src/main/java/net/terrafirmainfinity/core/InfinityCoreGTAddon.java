package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.chemical.material.ItemMaterialData;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.data.recipe.generated.*;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.terrafirmainfinity.core.common.data.material.InfinityMaterials;
import net.terrafirmainfinity.core.common.data.recipe.InfinityRecipeHandler;
import net.terrafirmainfinity.core.common.data.recipe.InfinityToolRecipeHandler;
import net.terrafirmainfinity.core.common.data.recipe.misc.InfinityMachineRecipes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.ingot;

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
        ItemMaterialData.registerMaterialEntry(() -> TFCItems.KAOLIN_CLAY, ingot, InfinityMaterials.Kaolinite);

        InfinityRecipeHandler.testRecipes(provider);
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
