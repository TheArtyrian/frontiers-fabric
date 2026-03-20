package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.misc.ManaOrbEntity;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.misc.ModAttribute;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin
{
    @Shadow public abstract AttributeInstance getAttribute(Holder<Attribute> attribute);
    @Shadow private void refreshDirtyAttributes() { }
    @Shadow public abstract AttributeMap getAttributes();
    @Shadow public abstract double getAttributeValue(Holder<Attribute> attribute);
    @Shadow public abstract float getHealth();
    @Shadow public abstract float getMaxHealth();
    @Shadow public abstract void setHealth(float health);
    @Shadow public abstract @Nullable LivingEntity getLastHurtByMob();
    @Shadow public abstract void hurtCurrentlyUsedShield(float amount);
    @Shadow public abstract boolean hasEffect(Holder<MobEffect> effect);
    @Shadow public abstract void remove(Entity.RemovalReason reason);
    @Shadow public abstract Brain<?> getBrain();
    @Shadow public abstract InteractionHand getUsedItemHand();
    @Shadow public abstract ItemStack getItemInHand(InteractionHand hand);
    @Shadow public static EquipmentSlot getSlotForHand(InteractionHand hand)
    {
        return null;
    }
    @Shadow protected abstract float getSoundVolume();
    @Shadow public abstract float getVoicePitch();
    @Shadow public abstract void makeSound(@Nullable SoundEvent sound);

    @Shadow protected ItemStack useItem;
    @Shadow private @Nullable LivingEntity lastHurtByMob;
    @Shadow public abstract void stopUsingItem();
    @Shadow public abstract EntityDimensions getDimensions(Pose pose);
    @Shadow public abstract boolean isDeadOrDying();
    @Shadow public abstract CombatTracker getCombatTracker();
    @Shadow public abstract @NotNull ItemStack getWeaponItem();
    @Shadow public abstract void heal(float amount);
    @Shadow protected abstract int getBaseExperienceReward();
    @Shadow public abstract boolean addEffect(MobEffectInstance effect);
    @Shadow public abstract boolean addEffect(MobEffectInstance effect, @Nullable Entity source);
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot slot);

    /**
     * Changes amount of XP drop based on Allurement level.
     */
    @ModifyReturnValue(method = "getExperienceReward", at = @At("RETURN"))
    public int addExtraExperienceEffectCheck(int original, @Local(argsOnly = true) ServerLevel world, @Local(argsOnly = true) @Nullable Entity attacker)
    {
        if (attacker instanceof Player player && player.hasEffect(ModStatusEffects.ALLUREMENT))
        {
            LivingEntity self = (LivingEntity) (Object) this;
            if (
                    !(self instanceof EnderDragon) &&
                    !(self instanceof WitherBoss) &&
                    !(self instanceof Player) &&
                    !(self instanceof Warden)
            )
            {
                int addition = (int) Math.round(this.getBaseExperienceReward() * 0.4) * (player.getEffect(ModStatusEffects.ALLUREMENT).getAmplifier() + 1);
                return original + addition;
            }
        }
        return original;
    }

    @Inject(method = "aiStep", at = @At("TAIL"))
    private void frontiersTickMovementMixin(CallbackInfo ci)
    {
        LivingEntity me = (LivingEntity)(Object)this;
        if (me instanceof Witch) return;

        ItemStack stack = this.getItemBySlot(EquipmentSlot.HEAD);
        boolean valid = (me instanceof Player) ? !this.isSpectator() : !this.isDeadOrDying();
        if (valid && stack.is(ModItem.WITCH_HAT.get()) && this.random.nextFloat() < 7.5E-4F)
        {
            VectorEventSync.Entity.fireEvent(this.level(), me, FRLevelEvents.Entity.WITCH_HAT_SPARKLE, 0);
        }
    }

    @Inject(method = "take", at = @At("TAIL"))
    private void frontiersPickupIntercept(Entity item, int count, CallbackInfo ci)
    {
        if (!item.isRemoved() && !this.level().isClientSide && (item instanceof ManaOrbEntity))
        {
            ((ServerLevel)this.level()).getChunkSource().broadcast(item, new ClientboundTakeItemEntityPacket(item.getId(), this.getId(), count));
        }
    }

    @WrapOperation(method = "dropExperience", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V")
    )
    private void frontiers$wrapForManaDrops(ServerLevel level, Vec3 position, int amount, Operation<Void> original, @Local(argsOnly = true) Entity entity)
    {
        original.call(level, position, amount);

        if (entity instanceof Player player && MethodToolbox.canCollectMana(player))
        {
            ManaOrbEntity.award(level, position, Math.round((float)amount / 2.0F));
        }
    }

    @WrapOperation(method = "hurt", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/LivingEntity;blockUsingShield(Lnet/minecraft/world/entity/LivingEntity;)V")
    )
    private void frontiers$takeShieldHitWrap(LivingEntity instance, LivingEntity attacker, Operation<Void> original)
    {
        if (this.useItem.is(ModItem.COBALT_SHIELD.get()))
        {
            frontiersTakeCobaltShieldHit(attacker);
        }
        else
        {
            original.call(instance, attacker);
        }
    }

    @Unique
    public void frontiersTakeCobaltShieldHit(LivingEntity attacker)
    {
        attacker.knockback(0.5, this.getX() - attacker.getX(), this.getZ() - attacker.getZ());
    }

    /////////////////////////////////////////////////////////
    // HOOKS
    /////////////////////////////////////////////////////////

    @Inject(method = "hurt", at = @At("TAIL"))
    public void damageHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {

    }

    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;makeSound(Lnet/minecraft/sounds/SoundEvent;)V"))
    public void deathSoundHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {

    }

    @Inject(method = "handleDamageEvent", at = @At("TAIL"))
    public void onDamagedHook(DamageSource damageSource, CallbackInfo ci)
    {

    }

    @Inject(method = "die", at = @At("HEAD"))
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {

    }

    @Inject(method = "playHurtSound", at = @At("TAIL"))
    public void hurtSoundHook(DamageSource damageSource, CallbackInfo ci)
    {

    }

    @Inject(method = "dropCustomDeathLoot", at = @At("TAIL"))
    public void dropEquipmentHook(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {

    }

    @Inject(method = "remove", at = @At("TAIL"))
    public void removeHook(Entity.RemovalReason reason, CallbackInfo ci)
    {

    }
}
