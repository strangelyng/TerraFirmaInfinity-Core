package net.terrafirmainfinity.core.common;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.item.behavior.CoverPlaceBehavior;
import com.gregtechceu.gtceu.common.item.behavior.TooltipBehavior;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.data.InfinityCovers;

import static com.gregtechceu.gtceu.common.data.GTItems.attach;
import static net.terrafirmainfinity.core.InfinityCore.InfinityRegistrate;

public class InfinityItems {
    static {
        InfinityRegistrate.creativeModeTab(() -> InfinityCore.INFINITY_CREATIVE_TAB);
    }

    public static ItemEntry<ComponentItem> COVER_AIR_VENT = InfinityRegistrate
            .item("air_vent_cover", ComponentItem::new)
            .lang("Air Vent Cover")
            .onRegister(attach(new CoverPlaceBehavior(InfinityCovers.AIR_VENT)))
            .register();

    public static ItemEntry<Item> STEAM_MOTOR = InfinityRegistrate.item("steam_motor", Item::new)
            .lang("Steam Motor")
            .tag(CustomTags.ELECTRIC_MOTORS)
            .register();

    public static ItemEntry<Item> STEAM_PISTON = InfinityRegistrate.item("steam_piston", Item::new)
            .lang("Steam Piston")
            .tag(CustomTags.ELECTRIC_PISTONS)
            .register();

    public static ItemEntry<ComponentItem> STEAM_CONVEYOR = InfinityRegistrate
            .item("steam_conveyor", ComponentItem::new)
            .lang("Steam Conveyor")
            .onRegister(attach(new CoverPlaceBehavior(InfinityCovers.STEAM_CONVEYOR)))
            .onRegister(attach(new TooltipBehavior(lines -> {
                lines.add(Component.translatable("item.gtceu.conveyor.module.tooltip"));
                lines.add(Component.translatable("gtceu.universal.tooltip.item_transfer_rate",
                        4));
            })))
            .tag(CustomTags.CONVEYOR_MODULES)
            .register();

    public static ItemEntry<ComponentItem> STEAM_PUMP = InfinityRegistrate
            .item("steam_pump", ComponentItem::new)
            .lang("Steam Pump")
            .onRegister(attach(new CoverPlaceBehavior(InfinityCovers.STEAM_PUMP)))
            .onRegister(attach(new TooltipBehavior(lines -> {
                lines.add(Component.translatable("item.gtceu.electric.pump.tooltip"));
                lines.add(Component.translatable("gtceu.universal.tooltip.fluid_transfer_rate",
                        32));
            })))
            .tag(CustomTags.ELECTRIC_PUMPS)
            .register();

    public static void init() {}
}
