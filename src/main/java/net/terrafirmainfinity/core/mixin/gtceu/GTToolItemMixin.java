package net.terrafirmainfinity.core.mixin.gtceu;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.tool.GTToolItem;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.Item;
import net.terrafirmainfinity.core.client.model.runtimegen.InfinityToolItemModelGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GTToolItem.class)
public class GTToolItemMixin {
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/client/model/runtimegen/ToolItemModelGenerator;add(Lnet/minecraft/world/item/Item;Lcom/gregtechceu/gtceu/api/item/tool/GTToolType;)V"))
    private void tfinfinity$injectToolItemModelGenerator(Item item, GTToolType toolType, Operation<Void> original, @Local Material material) {
        InfinityToolItemModelGenerator.add((GTToolItem) (Object) this, toolType, material.getMaterialIconSet());
    }
}
