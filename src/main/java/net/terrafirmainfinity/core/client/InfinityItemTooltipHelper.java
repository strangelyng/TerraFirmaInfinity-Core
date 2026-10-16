package net.terrafirmainfinity.core.client;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.event.AdvancedOreProspectorEventHelper;
import net.terrafirmainfinity.core.common.event.NormalOreProspectorEventHelper;
import net.terrafirmainfinity.core.common.event.OreProspectorEvent;
import net.terrafirmainfinity.core.common.event.WeakOreProspectorEventHelper;

@EventBusSubscriber(modid = InfinityCore.MOD_ID, value = Dist.CLIENT)
public class InfinityItemTooltipHelper {
    @SubscribeEvent
    public static void addItemTooltips(ItemTooltipEvent event) {
        var tooltip = event.getToolTip();
        var stack = event.getItemStack();

        // Ore Prospectors
        for (WeakOreProspectorEventHelper helper : OreProspectorEvent.getWeakOreProspectorListHelper()) {
            if (stack.is(helper.getItemTag())) {
                tooltip.add(Component.translatable(
                        "tfinfinity.tooltip.ore_prospector_stats",
                        helper.getLength(),
                        (int) (helper.getHalfWidth() * 2),
                        (int) (helper.getHalfHeight() * 2)).withStyle(ChatFormatting.YELLOW));
                return;
            }
        }

        for (NormalOreProspectorEventHelper helper : OreProspectorEvent.getNormalOreProspectorListHelper()) {
            if (stack.is(helper.getItemTag())) {
                tooltip.add(Component.translatable(
                        "tfinfinity.tooltip.ore_prospector_stats",
                        helper.getLength(),
                        (int) (helper.getHalfWidth() * 2),
                        (int) (helper.getHalfHeight() * 2)).withStyle(ChatFormatting.YELLOW));

                tooltip.add(Component.translatable(
                        "tfinfinity.tooltip.ore_prospector_count").withStyle(ChatFormatting.YELLOW));
                return;
            }
        }

        for (AdvancedOreProspectorEventHelper helper : OreProspectorEvent.getAdvancedOreProspectorListHelper()) {
            if (stack.is(helper.getItemTag())) {
                String modeKey = helper.isCentersOnly() ? "tfinfinity.tooltip.ore_prospector_mode_vein" : "tfinfinity.tooltip.ore_prospector_mode_block";

                tooltip.add(Component.translatable(
                        "tfinfinity.tooltip.ore_prospector_stats",
                        helper.getLength(),
                        (int) (helper.getHalfWidth() * 2),
                        (int) (helper.getHalfHeight() * 2)).withStyle(ChatFormatting.YELLOW));

                tooltip.add(Component.translatable(
                        "tfinfinity.tooltip.ore_prospector_count").withStyle(ChatFormatting.YELLOW));
                tooltip.add(Component.translatable(
                        "tfinfinity.tooltip.ore_prospector_xray", Component.translatable(modeKey)
                ).withStyle(ChatFormatting.YELLOW));
                return;
            }
        }
    }
}
