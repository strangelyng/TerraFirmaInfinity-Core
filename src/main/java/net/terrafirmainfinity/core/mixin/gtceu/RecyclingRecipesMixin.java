package net.terrafirmainfinity.core.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlag;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.data.recipe.misc.RecyclingRecipes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RecyclingRecipes.class)
public class RecyclingRecipesMixin {

    /*
     * Always check Material.macerateInto field, even for non-magnetic materials
     */
    @WrapOperation(method = "finalizeOutputs", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/api/data/chemical/material/Material;hasFlag(Lcom/gregtechceu/gtceu/api/data/chemical/material/info/MaterialFlag;)Z"))
    private static boolean tfinfinity$finalizeOutputs(Material material, MaterialFlag flag, Operation<Boolean> original) {
        return material.hasProperty(PropertyKey.INGOT);
    }
}
