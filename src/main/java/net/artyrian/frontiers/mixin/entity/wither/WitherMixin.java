package net.artyrian.frontiers.mixin.entity.wither;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.item.armor.ModArmorBonus;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(WitherEntity.class)
public abstract class WitherMixin extends LivingEntityMixin
{
    @Shadow private int blockBreakingCooldown;

    @Override
    public void removeHook(Entity.RemovalReason reason, CallbackInfo ci)
    {
        if (!this.getWorld().isClient && reason == Entity.RemovalReason.KILLED)
        {
            for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) this.getWorld(), this.getBlockPos()))
            {
                ModCriteria.ENTITY_KILLED_NEARBY.trigger(targeter, this.getType());
            }
        }
    }

    @Override
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {
        if (!getWorld().isClient)
        {
            PlayerEntity player;

            if (damageSource.getAttacker() instanceof PlayerEntity) player = (PlayerEntity)damageSource.getAttacker();
            else if (damageSource.getAttacker() != null) player = getWorld().getClosestPlayer(damageSource.getAttacker(), 256.0);
            else player = null;

            if (player instanceof PlayerEntity)
            {
                // Get MC server
                MinecraftServer server = getWorld().getServer();

                // Do NBT test state.
                StateSaveLoad serverState = StateSaveLoad.getServerState(server);
                serverState.isInHardmode = true;

                // Send a packet to the server signifying Hardmode
                //PacketByteBuf data = PacketByteBufs.create();

                if (server != null)
                {
                    ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
                    server.execute(() ->
                    {
                        ServerPlayNetworking.send(playerEntity, new WitherHardmodePayload(true));
                    });
                }
                else Frontiers.LOGGER.error("[Frontiers (FATAL)] Could not set hardmode! Server was not retraceable from ServerPlayer.");
            }
            else Frontiers.LOGGER.error("[Frontiers (FATAL)] Could not set hardmode! Player not found either as attacker or within range.");
        }
    }

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/boss/WitherEntity;shouldRenderOverlay()Z"))
    private boolean overrideArrowBlock(boolean original, @Local(argsOnly = true) DamageSource source)
    {
        if (original)
        {
            Entity entity = source.getSource();
            if (entity instanceof PersistentProjectileEntity || entity instanceof WindChargeEntity)
            {
                Entity owner = ((ProjectileEntity) entity).getOwner();
                if (owner instanceof LivingEntity e && ModArmorBonus.wearingSetOf(e, ModArmorBonus.NECRO))
                {
                    return false;
                }
            }
            // Make several people mad challege
            else if (source.getWeaponStack() != null && source.getWeaponStack().isOf(Items.MACE))
            {
                return true;
            }
        }
        return original;
    }

    @Inject(method = "damage", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/damage/DamageSource;getSource()Lnet/minecraft/entity/Entity;",
            shift = At.Shift.BEFORE),
            cancellable = true
    )
    private void maceBlockerWithArmor(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (source.getAttacker() != null && source.getWeaponStack() != null && source.getWeaponStack().isOf(Items.MACE))
        {
            Entity arbiter = source.getAttacker();

            if (arbiter instanceof LivingEntity)
            {
                World world = this.getWorld();

                double Xer = Math.signum(arbiter.getVelocity().getX());
                double Zer = Math.signum(arbiter.getVelocity().getZ());
                arbiter.addVelocity(Xer * -1.0, 1.0F, Zer * -1.0);

                // Apply weakness
                if (!this.getWorld().isClient)
                {
                    ((LivingEntity) arbiter).addStatusEffect(
                            new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 1, false, true)
                    );

                    arbiter.fallDistance = 0.0F;
                }

                world.playSound(
                        source.getAttacker(), this.getBlockPos(), ModSounds.WITHER_DEFLECT_MACE, SoundCategory.HOSTILE,
                        3.0F,
                        1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
                );

                world
                        .addParticle(
                                ParticleTypes.EXPLOSION_EMITTER,
                                this.getX(),
                                this.getY() + 1.0D,
                                this.getZ(),
                                ((double)world.random.nextFloat() - 0.5) * 0.8,
                                ((double)world.random.nextFloat() - 0.5) * 0.8,
                                ((double)world.random.nextFloat() - 0.5) * 0.8
                        );
                for (int i = 0; i < 8; i++) {
                    world
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX(),
                                    this.getY() + 1.0D,
                                    this.getZ(),
                                    ((double)world.random.nextFloat() - 0.5) * 0.7,
                                    ((double)world.random.nextFloat() - 0.5) * 0.7,
                                    ((double)world.random.nextFloat() - 0.5) * 0.7
                            );

                    world
                            .addParticle(
                                    ParticleTypes.WHITE_SMOKE,
                                    this.getX(),
                                    this.getY() + 1.0D,
                                    this.getZ(),
                                    ((double)world.random.nextFloat() - 0.5) * 0.8,
                                    ((double)world.random.nextFloat() - 0.5) * 0.8,
                                    ((double)world.random.nextFloat() - 0.5) * 0.8
                            );
                }
            }

            this.blockBreakingCooldown = 1;
            cir.setReturnValue(false);
        }
    }

    @ModifyVariable(method = "damage", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/damage/DamageSource;getAttacker()Lnet/minecraft/entity/Entity;",
            shift = At.Shift.AFTER,
            ordinal = 1),
            argsOnly = true
    )
    private float maceCorrector(float value, @Local(argsOnly = true) DamageSource source)
    {
        if (source.getAttacker() != null && source.getWeaponStack() != null && source.getWeaponStack().isOf(Items.MACE))
        {
            return Math.min(value, 30.0F);
        }
        return value;
    }
}
