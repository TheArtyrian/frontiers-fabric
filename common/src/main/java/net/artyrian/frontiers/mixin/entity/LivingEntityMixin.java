package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.misc.ModAttribute;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
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

    @Inject(method = "onAttributeUpdated", at = @At("HEAD"), cancellable = true)
    private void updateAttribute(Holder<Attribute> attribute, CallbackInfo ci)
    {
        if (attribute.is(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "player.eaten_apple")))
        {
            boolean isActive = (this.getAttributeInstance(ModAttribute.PLAYER_EATEN_APPLE).getBaseValue() > 0.0);
            boolean hasMod = (this.getAttributeInstance(Attributes.MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id()));

            if (isActive)
            {
                if (!hasMod)
                    this.getAttributeInstance(Attributes.MAX_HEALTH).addPermanentModifier(ModAttribute.APPLE_HEALTH);
            } else if (hasMod)
            {
                this.getAttributeInstance(Attributes.MAX_HEALTH).removeModifier(ModAttribute.APPLE_HEALTH);
                float f = this.getMaxHealth();
                if (this.getHealth() > f)
                {
                    this.setHealth(f);
                }
            }

            ci.cancel();
        }
    }

    /**
     * Changes amount of XP drop based on Allurement level.
     */
    @ModifyReturnValue(method = "getXpToDrop(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;)I", at = @At("RETURN"))
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
                int addition = (int) Math.round(this.getXpToDrop() * 0.4) * (player.getEffect(ModStatusEffects.ALLUREMENT).getAmplifier() + 1);
                return original + addition;
            }
        }
        return original;
    }

    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void frontiersTickMovementMixin(CallbackInfo ci)
    {
        if (!((LivingEntity)(Object)this instanceof Witch))
        {
            ItemStack stack = this.getEquippedStack(EquipmentSlot.HEAD);
            boolean valid = ((LivingEntity)(Object)this instanceof Player player) ? !this.isSpectator() : true;
            if (valid && stack.is(ModItem.WITCH_HAT) && this.random.nextFloat() < 7.5E-4F)
            {
                this.getWorld().broadcastEntityEvent((LivingEntity)(Object)this, (byte)123);
            }
        }
    }

    @Inject(method = "handleStatus", at = @At("HEAD"), cancellable = true)
    private void frontiersLivingEntityStatusMix(byte status, CallbackInfo ci)
    {
        if (status == 123)
        {
            for (int i = 0; i < this.random.nextInt(35) + 10; ++i)
            {
                this.getWorld().addParticle(ParticleTypes.WITCH,
                        this.getX() + this.random.nextGaussian() * 0.12999999523162842,
                        this.getBoundingBox().maxY + 0.5 + this.random.nextGaussian() * 0.12999999523162842,
                        this.getZ() + this.random.nextGaussian() * 0.12999999523162842,
                        0.0,
                        0.0,
                        0.0);
            }
            ci.cancel();
        }
    }

    @Inject(method = "sendPickup", at = @At("TAIL"))
    private void frontiersPickupIntercept(Entity item, int count, CallbackInfo ci)
    {
        if (!item.isRemoved() && !this.getWorld().isClientSide
                && (item instanceof ManaOrbEntity))
        {
            ((ServerLevel)this.getWorld()).getChunkSource().broadcast(item, new ClientboundTakeItemEntityPacket(item.getId(), this.getId(), count));
        }
    }

    @ModifyVariable(method = "modifyAppliedDamage", at = @At(value = "HEAD", ordinal = 0), argsOnly = true)
    private float frontiersRunBitchHatCheck(float value, @Local(argsOnly = true) DamageSource source)
    {
        if (!((LivingEntity)(Object)this instanceof Witch) && !source.is(DamageTypeTags.BYPASSES_EFFECTS) && !this.hasStatusEffect(MobEffects.DAMAGE_RESISTANCE))
        {
            ItemStack stack = this.getEquippedStack(EquipmentSlot.HEAD);
            if (stack.is(ModItem.WITCH_HAT) && source.is(DamageTypeTags.WITCH_RESISTANT_TO))
            {
                int valueToDmg = Math.clamp(Math.round(0.5 * value), 1, 20);
                stack.hurtAndBreak(valueToDmg, (LivingEntity)(Object)this, EquipmentSlot.HEAD);
                return value * 0.15F;
            }
        }
        return value;
    }

    @Inject(method = "damage", at = @At("TAIL"))
    public void damageHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {

    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;playSound(Lnet/minecraft/sound/SoundEvent;)V"))
    public void deathSoundHook(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {

    }

    @Inject(method = "onDamaged", at = @At("TAIL"))
    public void onDamagedHook(DamageSource damageSource, CallbackInfo ci)
    {

    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {

    }

    @Inject(method = "playHurtSound", at = @At("TAIL"))
    public void hurtSoundHook(DamageSource damageSource, CallbackInfo ci)
    {

    }

    @Inject(method = "dropEquipment", at = @At("TAIL"))
    public void dropEquipmentHook(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {

    }

    @Inject(method = "remove", at = @At("TAIL"))
    public void removeHook(Entity.RemovalReason reason, CallbackInfo ci)
    {

    }

    @WrapOperation(method = "damage", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;takeShieldHit(Lnet/minecraft/entity/LivingEntity;)V")
    )
    private void takeShieldHitWrap(LivingEntity instance, LivingEntity attacker, Operation<Void> original)
    {
        if (this.activeItemStack.is(ModItem.COBALT_SHIELD))
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
}
