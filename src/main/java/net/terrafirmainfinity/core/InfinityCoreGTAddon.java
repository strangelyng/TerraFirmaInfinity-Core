package net.terrafirmainfinity.core;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.terrafirmainfinity.core.common.data.InfinityMachineRecipes;
import net.terrafirmainfinity.core.common.data.materials.InfinityMaterialFlags;
import net.terrafirmainfinity.core.datagen.InfinityMaterialRecipeHandlers;

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
        for (Material material : GTRegistries.MATERIALS) {
            InfinityMaterialRecipeHandlers.init(provider, material);
        }
        InfinityMachineRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        for (Material material : GTRegistries.MATERIALS) {
            if (material.hasFlag(InfinityMaterialFlags.GENERATE_POWDER_COMPACTS)) {
                // Remove Default Blasting Recipes for Powder Compact Materials
                if (material.hasProperty(PropertyKey.INGOT) && material.hasProperty(PropertyKey.BLAST)) {
                    consumer.accept(ResourceLocation.fromNamespaceAndPath(GTCEu.MOD_ID, "electric_blast_furnace/blast_" + material.getName()));
                    consumer.accept(ResourceLocation.fromNamespaceAndPath(GTCEu.MOD_ID, "electric_blast_furnace/blast_" + material.getName() + "_gas"));
                }
            }
        }
    }
}
