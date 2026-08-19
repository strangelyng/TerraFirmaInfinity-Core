package net.terrafirmainfinity.core.common.data.recipe.misc;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.simibubi.create.AllBlocks;
import net.minecraft.data.recipes.RecipeOutput;
import net.terrafirmainfinity.core.common.InfinityBlocks;
import net.terrafirmainfinity.core.common.data.InfinityMachines;

public class InfinityMachineRecipes {
    public static void init(RecipeOutput provider) {
        // Crates & Drums
        VanillaRecipeHelper.addShapedRecipe(provider, true, "bismuth_bronze_crate", InfinityMachines.BISMUTH_BRONZE_CRATE.asStack(),
                "RPR", "PhP", "RPR",
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.BismuthBronze),
                'R', new MaterialEntry(TagPrefix.rodLong, GTMaterials.BismuthBronze));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "black_bronze_crate", InfinityMachines.BLACK_BRONZE_CRATE.asStack(),
                "RPR", "PhP", "RPR",
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.BlackBronze),
                'R', new MaterialEntry(TagPrefix.rodLong, GTMaterials.BlackBronze));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "bismuth_bronze_drum", InfinityMachines.BISMUTH_BRONZE_DRUM.asStack(),
                " h ", "PRP", "PRP",
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.BismuthBronze),
                'R', new MaterialEntry(TagPrefix.rodLong, GTMaterials.BismuthBronze));

        VanillaRecipeHelper.addShapedRecipe(provider, true, "black_bronze_drum", InfinityMachines.BLACK_BRONZE_DRUM.asStack(),
                " h ", "PRP", "PRP",
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.BlackBronze),
                'R', new MaterialEntry(TagPrefix.rodLong, GTMaterials.BlackBronze));

        // Super Chests & Tanks
        VanillaRecipeHelper.addShapedRecipe(provider, true, "super_chest_ulv", InfinityMachines.ULV_SUPER_CHEST.asStack(),
                "CPC", "PFP", "CPC",
                'C', CustomTags.ULV_CIRCUITS,
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.WroughtIron),
                'F', GTMachines.BRONZE_CRATE.asStack());

        VanillaRecipeHelper.addShapedRecipe(provider, true, "super_tank_ulv", InfinityMachines.ULV_SUPER_TANK.asStack(),
                "CPC", "PHP", "CFC",
                'C', CustomTags.ULV_CIRCUITS,
                'P', new MaterialEntry(TagPrefix.plate, GTMaterials.WroughtIron),
                'F', AllBlocks.MECHANICAL_PUMP.asStack(),
                'H', InfinityBlocks.HERMETIC_CASING_ULV.asStack());
    }
}
