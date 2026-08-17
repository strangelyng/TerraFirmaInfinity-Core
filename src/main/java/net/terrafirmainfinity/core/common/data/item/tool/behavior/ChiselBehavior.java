package net.terrafirmainfinity.core.common.data.item.tool.behavior;

import com.gregtechceu.gtceu.api.item.tool.ToolHelper;
import com.gregtechceu.gtceu.api.item.tool.behavior.IToolBehavior;
import com.gregtechceu.gtceu.api.item.tool.behavior.ToolBehaviorType;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.dries007.tfc.common.TFCTags;
import net.dries007.tfc.common.player.IPlayerInfo;
import net.dries007.tfc.common.recipes.ChiselRecipe;
import net.dries007.tfc.common.recipes.CollapseRecipe;
import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.advancements.TFCAdvancements;
import net.minecraft.core.BlockPos;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.terrafirmainfinity.core.common.data.InfinityToolBehaviors;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ChiselBehavior implements IToolBehavior<ChiselBehavior> {

    public static final ChiselBehavior INSTANCE = new ChiselBehavior();
    public static final Codec<ChiselBehavior> CODEC = Codec.unit(INSTANCE);
    public static final StreamCodec<ByteBuf, ChiselBehavior> STREAM_CODEC = StreamCodec
            .unit(INSTANCE);

    protected ChiselBehavior() {/**/}

    /* This code is based on TerraFirmaCraft which is licensed under the EUPL, Version 1.2
     * You may obtain a copy of the Licence at:
     * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
     */
    @Override
    public @NotNull InteractionResult onItemUse(UseOnContext context) {
        final Player player = context.getPlayer();
        if (player != null)
        {
            final Level level = context.getLevel();
            final BlockPos pos = context.getClickedPos();
            final BlockState state = level.getBlockState(pos);
            final Either<BlockState, InteractionResult> result = ChiselRecipe.computeResult(player, state, new BlockHitResult(context.getClickLocation(), context.getClickedFace(), pos, context.isInside()), true);
            return result.map(resultState -> {
                player.playSound(resultState.getSoundType(level, pos, player).getHitSound(), 1f, 1f);

                ItemStack held = player.getMainHandItem();
                if (!level.isClientSide)
                {
                    if (TFCConfig.SERVER.enableChiselsStartCollapses.get())
                    {
                        if (Helpers.isBlock(state, TFCTags.Blocks.CAN_TRIGGER_COLLAPSE) && CollapseRecipe.tryTriggerCollapse(level, pos))
                        {
                            return InteractionResult.CONSUME; // Abort chiseling
                        }
                    }

                    final ChiselRecipe recipeUsed = ChiselRecipe.getRecipe(state, IPlayerInfo.get(player).chiselMode());
                    if (recipeUsed != null)
                    {
                        ItemStack extraDrop = recipeUsed.getItemOutput(held);
                        if (!extraDrop.isEmpty())
                        {
                            ItemHandlerHelper.giveItemToPlayer(player, extraDrop);
                        }
                    }
                }

                level.setBlockAndUpdate(pos, resultState);
                if (player instanceof ServerPlayer serverPlayer)
                {
                    TFCAdvancements.CHISELED.trigger(serverPlayer, resultState);
                }

                ToolHelper.damageItem(held, player);
                player.getCooldowns().addCooldown(held.getItem(), 10);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }, Function.identity());
        }
        return InteractionResult.PASS;
    }

    @Override
    public ToolBehaviorType<ChiselBehavior> getType() {
        return InfinityToolBehaviors.CHISEL;
    }
}
