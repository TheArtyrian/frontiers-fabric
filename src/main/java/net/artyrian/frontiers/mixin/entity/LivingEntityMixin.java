package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModAttribute;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
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
    @Shadow
    public abstract EntityAttributeInstance getAttributeInstance(RegistryEntry<EntityAttribute> attribute);

    @Shadow
    private void updateAttributes()
    {
    }

    @Shadow
    public abstract AttributeContainer getAttributes();

    @Shadow
    public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @Shadow
    public abstract float getHealth();

    @Shadow
    public abstract float getMaxHealth();

    @Shadow
    public abstract void setHealth(float health);

    @Shadow
    public abstract @Nullable LivingEntity getAttacker();

    @Shadow
    public abstract void damageShield(float amount);

    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Shadow
    public abstract void remove(Entity.RemovalReason reason);

    @Shadow
    public abstract Brain<?> getBrain();

    @Shadow
    public abstract Hand getActiveHand();

    @Shadow
    public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    public static EquipmentSlot getSlotForHand(Hand hand)
    {
        return null;
    }

    @Shadow
    protected abstract float getSoundVolume();

    @Shadow
    public abstract float getSoundPitch();

    @Shadow
    public abstract void playSound(@Nullable SoundEvent sound);

    @Shadow
    protected ItemStack activeItemStack;

    @Shadow
    private @Nullable LivingEntity attacker;

    @Shadow
    public abstract void clearActiveItem();

    @Shadow
    public abstract EntityDimensions getDimensions(EntityPose pose);

    @Shadow
    public abstract boolean isDead();

    @Shadow
    public abstract DamageTracker getDamageTracker();

    @Shadow
    public abstract @NotNull ItemStack getWeaponStack();

    @Shadow
    public abstract void heal(float amount);

    @Shadow
    protected abstract int getXpToDrop();

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow
    public abstract boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Inject(method = "updateAttribute", at = @At("HEAD"), cancellable = true)
    private void updateAttribute(RegistryEntry<EntityAttribute> attribute, CallbackInfo ci)
    {
        if (attribute.matchesId(Identifier.of(Frontiers.MOD_ID, "player.eaten_apple")))
        {
            boolean isActive = (this.getAttributeInstance(ModAttribute.PLAYER_EATEN_APPLE).getBaseValue() > 0.0);
            boolean hasMod = (this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id()));

            if (isActive)
            {
                if (!hasMod)
                    this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(ModAttribute.APPLE_HEALTH);
            } else if (hasMod)
            {
                this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).removeModifier(ModAttribute.APPLE_HEALTH);
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
    public int addExtraExperienceEffectCheck(int original, @Local(argsOnly = true) ServerWorld world, @Local(argsOnly = true) @Nullable Entity attacker)
    {
        if (attacker instanceof PlayerEntity player && player.hasStatusEffect(ModStatusEffects.ALLUREMENT))
        {
            LivingEntity self = (LivingEntity) (Object) this;
            if (
                    !(self instanceof EnderDragonEntity) &&
                            !(self instanceof WitherEntity) &&
                            !(self instanceof PlayerEntity) &&
                            !(self instanceof WardenEntity)
            )
            {
                int addition = (int) Math.round(this.getXpToDrop() * 0.4) * (player.getStatusEffect(ModStatusEffects.ALLUREMENT).getAmplifier() + 1);
                return original + addition;
            }
        }
        return original;
    }

    @Inject(method = "tickMovement", at = @At("TAIL"))
    private void frontiersTickMovementMixin(CallbackInfo ci)
    {
        if (!((LivingEntity)(Object)this instanceof WitchEntity))
        {
            ItemStack stack = this.getEquippedStack(EquipmentSlot.HEAD);
            if (stack.isOf(ModItem.WITCH_HAT) && this.random.nextFloat() < 7.5E-4F) {
                this.getWorld().sendEntityStatus((LivingEntity)(Object)this, (byte)123);
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

    @ModifyVariable(method = "modifyAppliedDamage", at = @At(value = "HEAD", ordinal = 0), argsOnly = true)
    private float frontiersRunBitchHatCheck(float value, @Local(argsOnly = true) DamageSource source)
    {
        if (!((LivingEntity)(Object)this instanceof WitchEntity) && !source.isIn(DamageTypeTags.BYPASSES_EFFECTS) && !this.hasStatusEffect(StatusEffects.RESISTANCE))
        {
            ItemStack stack = this.getEquippedStack(EquipmentSlot.HEAD);
            if (stack.isOf(ModItem.WITCH_HAT) && source.isIn(DamageTypeTags.WITCH_RESISTANT_TO))
            {
                stack.damage(3, (LivingEntity)(Object)this, EquipmentSlot.HEAD);
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
    public void dropEquipmentHook(ServerWorld world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
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
        if (this.activeItemStack.isOf(ModItem.COBALT_SHIELD))
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
        attacker.takeKnockback(0.5, this.getX() - attacker.getX(), this.getZ() - attacker.getZ());
    }
}
