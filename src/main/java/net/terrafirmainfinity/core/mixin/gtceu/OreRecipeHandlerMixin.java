package net.terrafirmainfinity.core.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.data.recipe.generated.OreRecipeHandler;
import net.minecraft.data.recipes.RecipeOutput;
import net.terrafirmainfinity.core.common.data.recipe.generated.InfinityOreRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreRecipeHandler.class)
public class OreRecipeHandlerMixin {

    /**
     * Replace OreRecipeHandler with TerraFirmaInfinity's own
     */
    @Inject(method = "run", at = @At(value = "HEAD"), cancellable = true)
    private static void tfinfinity$replaceOreProc(RecipeOutput provider, Material material, CallbackInfo ci) {
        InfinityOreRecipeHandler.run(provider, material);
        ci.cancel();
    }
}
