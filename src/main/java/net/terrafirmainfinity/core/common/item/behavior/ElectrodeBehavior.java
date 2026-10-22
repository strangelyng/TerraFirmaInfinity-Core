package net.terrafirmainfinity.core.common.item.behavior;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.component.IMaterialPartItem;
import com.gregtechceu.gtceu.api.item.component.ISubItemHandler;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.terrafirmainfinity.core.common.data.InfinityPropertyKeys;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElectrodeBehavior implements IMaterialPartItem, ISubItemHandler {

    @Override
    public void fillItemCategory(Item item, CreativeModeTab category, NonNullList<ItemStack> items) {
        for (Material material : GTRegistries.MATERIALS) {
            if (!material.hasProperty(InfinityPropertyKeys.ELECTRODE)) {
                continue;
            }

            var electrodeStack = new ItemStack(item);
            var behavior = ElectrodeBehavior.getBehavior(electrodeStack);
            if (behavior != null) {
                behavior.setPartMaterial(electrodeStack, material);
                items.add(electrodeStack);
            }
        }
    }

    public int getElectrodeEfficiency(ItemStack stack) {
        var property = getPartMaterial(stack).getProperty(InfinityPropertyKeys.ELECTRODE);
        return property == null ? -1 : property.getEfficiency();
    }

    @Override
    public int getPartMaxDurability(ItemStack itemStack) {
        var property = getPartMaterial(itemStack).getProperty(InfinityPropertyKeys.ELECTRODE);
        return property == null ? -1 : property.getDurability();
    }

    public int getElectrodeDurabilityPercent(ItemStack itemStack) {
        return 100 - 100 * getPartDamage(itemStack) / getPartMaxDurability(itemStack);
    }

    public void applyElectrodeDamage(ItemStack itemStack, int damageApplied) {
        int electrodeDurability = getPartMaxDurability(itemStack);
        int resultDamage = getPartDamage(itemStack) + damageApplied;
        if (resultDamage >= electrodeDurability) {
            itemStack.shrink(1);
        } else {
            setPartDamage(itemStack, resultDamage);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        IMaterialPartItem.super.appendHoverText(stack, context, tooltipComponents, isAdvanced);
        tooltipComponents
                .add(Component.translatable("metaitem.tool.tooltip.electrode.efficiency", getElectrodeEfficiency(stack)));
    }

    @Nullable
    public static ElectrodeBehavior getBehavior(@NotNull ItemStack itemStack) {
        if (itemStack.getItem() instanceof IComponentItem componentItem) {
            for (var component : componentItem.getComponents()) {
                if (component instanceof ElectrodeBehavior behavior) {
                    return behavior;
                }
            }
        }
        return null;
    }
}
