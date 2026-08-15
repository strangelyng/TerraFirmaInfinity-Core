package net.terrafirmainfinity.core.mixin.gtceu;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.utils.ToolItemHelper;
import net.minecraft.world.item.ItemStack;
import net.terrafirmainfinity.core.InfinityCore;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ToolItemHelper.class)
public class ToolItemHelperMixin {
    @Shadow
    @Final
    public static Map<GTToolType, ItemStack> TOOL_CACHE;

    /**
     * @author Strangelyng
     * @reason Fixes a crash caused by disabled Neutronium tools
     */
    @Overwrite
    public static ItemStack getToolItem(GTToolType toolType) {
        return TOOL_CACHE.computeIfAbsent(toolType, type -> {
            if (type == GTToolType.SOFT_MALLET) {
                return GTMaterialItems.TOOL_ITEMS.get(GTMaterials.Rubber, type).asStack();
            }
            return GTMaterialItems.TOOL_ITEMS.get(GTMaterials.Steel, type).asStack(); // The original method gets GTMaterials.Neutronium here
        });
    }
}
