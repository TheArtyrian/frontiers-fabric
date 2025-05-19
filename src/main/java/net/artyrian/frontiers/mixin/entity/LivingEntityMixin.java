package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModAttribute;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin
{
    @Shadow
    public abstract EntityAttributeInstance getAttributeInstance(RegistryEntry<EntityAttribute> attribute);

    @Shadow
    private void updateAttributes() {}

    @Shadow public abstract AttributeContainer getAttributes();
    @Shadow public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);
    @Shadow public abstract float getHealth();
    @Shadow public abstract float getMaxHealth();
    @Shadow public abstract void setHealth(float health);
    @Shadow public abstract @Nullable LivingEntity getAttacker();
    @Shadow public abstract void damageShield(float amount);
    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);
    @Shadow public abstract void remove(Entity.RemovalReason reason);
    @Shadow public abstract Brain<?> getBrain();
    @Shadow public abstract Hand getActiveHand();
    @Shadow public abstract ItemStack getStackInHand(Hand hand);

    @Shadow
    public static EquipmentSlot getSlotForHand(Hand hand)
    {
        return null;
    }

    @Shadow protected abstract float getSoundVolume();
    @Shadow public abstract float getSoundPitch();

    @Shadow public abstract void playSound(@Nullable SoundEvent sound);

    @Shadow protected ItemStack activeItemStack;

    @Shadow private @Nullable LivingEntity attacker;

    @Shadow public abstract void clearActiveItem();

    @Shadow public abstract EntityDimensions getDimensions(EntityPose pose);

    @Shadow public abstract boolean isDead();

    @Shadow public abstract DamageTracker getDamageTracker();

    @Shadow public abstract @NotNull ItemStack getWeaponStack();

    @Shadow public abstract void heal(float amount);

    @Inject(method = "updateAttribute", at = @At("HEAD"), cancellable = true)
    private void updateAttribute(RegistryEntry<EntityAttribute> attribute, CallbackInfo ci)
    {
        if (attribute.matchesId(Identifier.of(Frontiers.MOD_ID, "player.eaten_apple")))
        {
            boolean isActive = (this.getAttributeInstance(ModAttribute.PLAYER_EATEN_APPLE).getBaseValue() > 0.0);
            boolean hasMod = (this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).hasModifier(ModAttribute.APPLE_HEALTH.id()));

            if (isActive)
            {
                if (!hasMod) this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(ModAttribute.APPLE_HEALTH);
            }
            else if (hasMod)
            {
                this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).removeModifier(ModAttribute.APPLE_HEALTH);
                float f = this.getMaxHealth();
                if (this.getHealth() > f) {
                    this.setHealth(f);
                }
            }

            ci.cancel();
        }
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
