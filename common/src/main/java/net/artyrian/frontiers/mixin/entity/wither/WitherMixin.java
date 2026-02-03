package net.artyrian.frontiers.mixin.entity.wither;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.definition.networking.payload.WitherHardmodePayload;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.artyrian.frontiers.mixin_intf.bossbar.BossBarImpl;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.FRMusic;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.ModArmorBonus;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.vertisoft.vectorlib.VectorLib;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(WitherBoss.class)
public abstract class WitherMixin extends LivingEntityMixin
{
    @Shadow private int destroyBlocksTick;
    @Shadow @Final private ServerBossEvent bossEvent;

    /** Makes the boss bar play wither music */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void frontiersInitBossBarSpec(EntityType entityType, Level world, CallbackInfo ci)
    {
        this.bossEvent.setPlayBossMusic(true);
        ((BossBarImpl)this.bossEvent).frontiers_1_21x$setBossBarMusic(FRMusic.WITHER);
    }

    @Override
    public void removeHook(Entity.RemovalReason reason, CallbackInfo ci)
    {
        if (!this.level().isClientSide && reason == Entity.RemovalReason.KILLED)
        {
            for (ServerPlayer targeter : VectorLib.NETWORK.getAllTrackingChunk((ServerLevel) this.level(), this.blockPosition(), false))
            {
                ModCriteria.ENTITY_KILLED_NEARBY.get().trigger(targeter, this.getType());
            }
        }
    }

    @Override
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {
        if (!level().isClientSide)
        {
            Player player;

            if (damageSource.getEntity() instanceof Player) player = (Player)damageSource.getEntity();
            else if (damageSource.getEntity() != null) player = level().getNearestPlayer(damageSource.getEntity(), 256.0);
            else player = null;

            if (player instanceof Player)
            {
                // Get MC server
                MinecraftServer server = level().getServer();

                // Do NBT test state.
                StateSaveLoad serverState = StateSaveLoad.getServerState(server);
                serverState.isInHardmode = true;

                // Send a packet to the server signifying Hardmode
                //PacketByteBuf data = PacketByteBufs.create();

                if (server != null)
                {
                    ServerPlayer playerEntity = server.getPlayerList().getPlayer(player.getUUID());
                    VectorLib.NETWORK.sendToPlayer(playerEntity, new WitherHardmodePayload(true));
                }
                else Frontiers.LOGGER.error("[Frontiers (FATAL)] Could not set hardmode! Server was not retraceable from ServerPlayer.");
            }
            else Frontiers.LOGGER.error("[Frontiers (FATAL)] Could not set hardmode! Player not found either as attacker or within range.");
        }
    }

    @ModifyExpressionValue(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/wither/WitherBoss;isPowered()Z"))
    private boolean overrideArrowBlock(boolean original, @Local(argsOnly = true) DamageSource source)
    {
        if (original)
        {
            Entity entity = source.getDirectEntity();
            if (entity instanceof AbstractArrow || entity instanceof WindCharge)
            {
                Entity owner = ((Projectile) entity).getOwner();
                if (owner instanceof LivingEntity e && ModArmorBonus.wearingSetOf(e, ModArmorBonus.NECRO))
                {
                    return false;
                }
            }
            // Make several people mad challege
            else if (source.getWeaponItem() != null && source.getWeaponItem().is(Items.MACE))
            {
                return true;
            }
        }
        return original;
    }

    @Inject(method = "hurt", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/damagesource/DamageSource;getDirectEntity()Lnet/minecraft/world/entity/Entity;",
            shift = At.Shift.BEFORE),
            cancellable = true
    )
    private void maceBlockerWithArmor(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        if (source.getEntity() != null && source.getWeaponItem() != null && source.getWeaponItem().is(Items.MACE))
        {
            Entity arbiter = source.getEntity();

            if (arbiter instanceof LivingEntity)
            {
                Level world = this.level();

                double Xer = Math.signum(arbiter.getDeltaMovement().x());
                double Zer = Math.signum(arbiter.getDeltaMovement().z());
                arbiter.push(Xer * -1.0, 1.0F, Zer * -1.0);

                // Apply weakness
                if (!this.level().isClientSide)
                {
                    ((LivingEntity) arbiter).addEffect(
                            new MobEffectInstance(MobEffects.WEAKNESS, 200, 1, false, true)
                    );

                    arbiter.fallDistance = 0.0F;
                }

                world.playSound(
                        source.getEntity(), this.blockPosition(), ModSounds.WITHER_DEFLECT_MACE.get(), SoundSource.HOSTILE,
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

            this.destroyBlocksTick = 1;
            cir.setReturnValue(false);
        }
    }

    @ModifyVariable(method = "hurt", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/damagesource/DamageSource;getEntity()Lnet/minecraft/world/entity/Entity;",
            shift = At.Shift.AFTER,
            ordinal = 1),
            argsOnly = true
    )
    private float maceCorrector(float value, @Local(argsOnly = true) DamageSource source)
    {
        if (source.getEntity() != null && source.getWeaponItem() != null && source.getWeaponItem().is(Items.MACE))
        {
            return Math.min(value, 30.0F);
        }
        return value;
    }

    // Screw you Mojang for making this thing still despawn on Bedcock, like it's been almost 10 fucking years please just port the wither fight over
    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    private void attemptPhotonTrimDrop(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci, @Local ItemEntity star)
    {
        // Try to make star permanent
        if (star != null) star.setUnlimitedLifetime();

        // Drop photon
        float dropFloat = this.getRandom().nextFloat();
        if (dropFloat >= 0.80F && causedByPlayer)
        {
            ItemEntity template = this.spawnAtLocation(ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get());
            if (template != null) template.setUnlimitedLifetime();
        }
    }
}
