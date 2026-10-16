/*
 * This class is adapted from TerraFirmaGreg-Core - Modern, which is licensed under LGPL-3.0
 * A copy of the license can be found here:
 * https://www.gnu.org/licenses/lgpl-3.0.en.html
 */

package net.terrafirmainfinity.core.common.event;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.terrafirmainfinity.core.InfinityCore;
import net.terrafirmainfinity.core.common.data.tag.InfinityItemTags;
import net.terrafirmainfinity.core.config.InfinityConfig;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

@EventBusSubscriber(modid = InfinityCore.MOD_ID)
public class OreProspectorEvent {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.@NotNull RightClickBlock event) {
        Player player = event.getEntity();
        Level level = player.level();
        ItemStack held = player.getItemInHand(event.getHand());

        if (level.isClientSide())
            return;

        boolean matchesTag = getWeakOreProspectorListHelper().stream().anyMatch(h -> held.is(h.getItemTag()))
                || getNormalOreProspectorListHelper().stream().anyMatch(h -> held.is(h.getItemTag()))
                || getAdvancedOreProspectorListHelper().stream().anyMatch(h -> held.is(h.getItemTag()));

        if (matchesTag) {
            BlockPos pos = event.getPos();
            BlockEntity blockEntity = level.getBlockEntity(pos);

            // Allow containers to be opened (chests, barrels, etc.)
            boolean isContainer = blockEntity instanceof MenuProvider;
            boolean isMachine = blockEntity instanceof MetaMachine;

            // Allow interactable entities (item frames, armor stands, tool racks, etc.)
            boolean hasEntityTarget = event.getEntity() != null
                    && player.pick(5.0D, 0.0F, false) instanceof EntityHitResult;

            // Determines which events should be canceled
            boolean shouldCancel = (!hasEntityTarget && !isMachine && !isContainer) || player.isCrouching();

            if (shouldCancel) {
                getWeakOreProspectorListHelper().forEach(h -> h.handleRightClick(event));
                getNormalOreProspectorListHelper().forEach(h -> h.handleRightClick(event));
                getAdvancedOreProspectorListHelper().forEach(h -> h.handleRightClick(event));

                event.setCanceled(true);
            }
        }
    }

    @Contract(" -> new")
    public static @NotNull @Unmodifiable List<WeakOreProspectorEventHelper> getWeakOreProspectorListHelper() {
        return List.of(
                new WeakOreProspectorEventHelper(
                        InfinityConfig.SERVER.copperPropickConfig.searchLength().get(),
                        InfinityConfig.SERVER.copperPropickConfig.searchWidth().get(),
                        InfinityConfig.SERVER.copperPropickConfig.searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_COPPER),
                new WeakOreProspectorEventHelper(
                        InfinityConfig.SERVER.bronzePropickConfig.searchLength().get(),
                        InfinityConfig.SERVER.bronzePropickConfig.searchWidth().get(),
                        InfinityConfig.SERVER.bronzePropickConfig.searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_BRONZE));
    }

    @Contract(" -> new")
    public static @NotNull @Unmodifiable List<NormalOreProspectorEventHelper> getNormalOreProspectorListHelper() {
        return List.of(
                new NormalOreProspectorEventHelper(
                        InfinityConfig.SERVER.wroughtIronPropickConfig.searchLength().get(),
                        InfinityConfig.SERVER.wroughtIronPropickConfig.searchWidth().get(),
                        InfinityConfig.SERVER.wroughtIronPropickConfig.searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_WROUGHT_IRON),
                new NormalOreProspectorEventHelper(
                        InfinityConfig.SERVER.steelPropickConfig.searchLength().get(),
                        InfinityConfig.SERVER.steelPropickConfig.searchWidth().get(),
                        InfinityConfig.SERVER.steelPropickConfig.searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_STEEL),
                new NormalOreProspectorEventHelper(
                        InfinityConfig.SERVER.blackSteelPropickConfig.searchLength().get(),
                        InfinityConfig.SERVER.blackSteelPropickConfig.searchWidth().get(),
                        InfinityConfig.SERVER.blackSteelPropickConfig.searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_BLACK_STEEL));
    }

    @Contract(" -> new")
    public static @NotNull @Unmodifiable List<AdvancedOreProspectorEventHelper> getAdvancedOreProspectorListHelper() {
        return List.of(
                new AdvancedOreProspectorEventHelper(
                        InfinityConfig.SERVER.blueSteelPropickConfig.inner().searchLength().get(),
                        InfinityConfig.SERVER.blueSteelPropickConfig.inner().searchWidth().get(),
                        InfinityConfig.SERVER.blueSteelPropickConfig.inner().searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_BLUE_STEEL,
                        InfinityConfig.SERVER.blueSteelPropickConfig.preciselyRenderVein().get()),
                new AdvancedOreProspectorEventHelper(
                        InfinityConfig.SERVER.redSteelPropickConfig.inner().searchLength().get(),
                        InfinityConfig.SERVER.redSteelPropickConfig.inner().searchWidth().get(),
                        InfinityConfig.SERVER.redSteelPropickConfig.inner().searchWidth().get(),
                        InfinityItemTags.ORE_PROSPECTORS_RED_STEEL,
                        InfinityConfig.SERVER.redSteelPropickConfig.preciselyRenderVein().get()));
    }
}
