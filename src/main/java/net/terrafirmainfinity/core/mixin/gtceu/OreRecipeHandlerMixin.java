package net.terrafirmainfinity.core.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.data.recipe.generated.OreRecipeHandler;
import net.minecraft.data.recipes.RecipeOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreRecipeHandler.class)
public class OreRecipeHandlerMixin {

    /**
     * Cancels processing of ore-bearing stones as they clutter recipe viewers and the blocks themselves are unobtainable
     */
    @Inject(method = "processOre", at = @At(value = "HEAD"), cancellable = true)
    private static void tfinfinity$cancelProcessOre(RecipeOutput provider, TagPrefix orePrefix, OreProperty property, Material material, CallbackInfo ci) {
        ci.cancel();
    }
}
