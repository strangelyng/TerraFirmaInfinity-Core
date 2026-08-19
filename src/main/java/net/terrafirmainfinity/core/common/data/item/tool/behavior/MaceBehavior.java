package net.terrafirmainfinity.core.common.data.item.tool.behavior;

import com.gregtechceu.gtceu.api.item.tool.behavior.IToolBehavior;
import com.gregtechceu.gtceu.api.item.tool.behavior.ToolBehaviorType;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.Direction;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.terrafirmainfinity.core.common.data.InfinityToolBehaviors;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class MaceBehavior implements IToolBehavior<MaceBehavior> {
    public static final MaceBehavior INSTANCE = new MaceBehavior();
    public static final Codec<MaceBehavior> CODEC = Codec.unit(INSTANCE);
    public static final StreamCodec<ByteBuf, MaceBehavior> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    /** Copied from vanilla MaceItem class with readability adjustments */
    @Override
    public void hitEntity(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        if (attacker instanceof ServerPlayer serverPlayer && MaceItem.canSmashAttack(serverPlayer)) {
            ServerLevel serverlevel = (ServerLevel) attacker.level();
            DamageSource damageSource = serverPlayer.damageSources().playerAttack(serverPlayer); // ADDITION

            if (serverPlayer.isIgnoringFallDamageFromCurrentImpulse() && serverPlayer.currentImpulseImpactPos != null) {
                if (serverPlayer.currentImpulseImpactPos.y > serverPlayer.position().y) {
                    serverPlayer.currentImpulseImpactPos = serverPlayer.position();
                }
            } else {
                serverPlayer.currentImpulseImpactPos = serverPlayer.position();
            }

            serverPlayer.setIgnoreFallDamageFromCurrentImpulse(true);
            serverPlayer.setDeltaMovement(serverPlayer.getDeltaMovement().with(Direction.Axis.Y, 0.01F));
            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
            if (target.onGround()) {
                serverPlayer.setSpawnExtraParticlesOnFall(true);
                SoundEvent soundevent = serverPlayer.fallDistance > 5.0F ? SoundEvents.MACE_SMASH_GROUND_HEAVY : SoundEvents.MACE_SMASH_GROUND;
                serverlevel.playSound(
                        null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), soundevent, serverPlayer.getSoundSource(), 1.0F, 1.0F
                );
            } else {
                serverlevel.playSound(
                        null, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), SoundEvents.MACE_SMASH_AIR, serverPlayer.getSoundSource(), 1.0F, 1.0F
                );
            }

            target.hurt(damageSource, getSmashAttackBonus(target, damageSource)); // ADDITION

            knockback(serverlevel, serverPlayer, target);
        }
    }

    // Doesn't seem to quite match vanilla, but does have an effect
    private float getSmashAttackBonus(Entity target, DamageSource damageSource) {
        if (damageSource.getDirectEntity() instanceof LivingEntity livingEntity) {
            if (!MaceItem.canSmashAttack(livingEntity)) {
                return 0.0F;
            } else {
                float fallDistance = livingEntity.fallDistance;
                float smashDamage;
                if (fallDistance <= 3.0F) {
                    smashDamage = 4.0F * fallDistance;
                } else if (fallDistance <= 8.0F) {
                    smashDamage = 12.0F + 2.0F * (fallDistance - 3.0F);
                } else {
                    smashDamage = 22.0F + fallDistance - 8.0F;
                }

                return livingEntity.level() instanceof ServerLevel serverlevel
                        ? smashDamage + EnchantmentHelper.modifyFallBasedDamage(serverlevel, livingEntity.getWeaponItem(), target, damageSource, 0.0F) * fallDistance
                        : smashDamage;
            }
        } else {
            return 0.0F;
        }
    }

    private static void knockback(Level level, Player player, Entity entity) {
        level.levelEvent(2013, entity.getOnPos(), 750);
        level.getEntitiesOfClass(LivingEntity.class, entity.getBoundingBox().inflate(3.5), knockbackPredicate(player, entity))
                .forEach(target -> {
                    Vec3 trajectory = target.position().subtract(entity.position());
                    double magnitude = getKnockbackPower(player, target, trajectory);
                    Vec3 velocity = trajectory.normalize().scale(magnitude);
                    if (magnitude > 0.0) {
                        target.push(velocity.x, 0.7F, velocity.z);
                        if (target instanceof ServerPlayer serverPlayer) {
                            serverPlayer.connection.send(new ClientboundSetEntityMotionPacket(serverPlayer));
                        }
                    }
                });
    }

    private static Predicate<LivingEntity> knockbackPredicate(Player player, Entity entity) {
        return target -> {
            if (target.isSpectator()) {
                return false;
            }

            if (target == player || target == entity) {
                return false;
            }

            if (player.isAlliedTo(target)) {
                return false;
            }

            if (target instanceof TamableAnimal tamableAnimal && tamableAnimal.isTame() && player.getUUID().equals(tamableAnimal.getOwnerUUID())) {
                return false;
            }

            if (target instanceof ArmorStand armorStand && armorStand.isMarker()) {
                return false;
            }

            boolean isInRange = entity.distanceToSqr(target) <= Math.pow(3.5, 2.0);
            return isInRange;
        };
    }

    private static double getKnockbackPower(Player player, LivingEntity entity, Vec3 entityPos) {
        return (3.5 - entityPos.length())
                * 0.7F
                * (double) (player.fallDistance > 5.0F ? 2 : 1)
                * (1.0 - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
    }

    @Override
    public ToolBehaviorType<MaceBehavior> getType() {
        return InfinityToolBehaviors.MACE;
    }
}
