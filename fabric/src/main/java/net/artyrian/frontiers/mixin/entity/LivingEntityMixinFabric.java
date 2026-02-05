package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinFabric extends EntityMixin
{
    @ModifyReturnValue(method = "getDamageAfterMagicAbsorb", at = @At(value = "RETURN"))
    private float frontiersRunBitchHatCheck(float value, @Local(argsOnly = true) DamageSource source)
    {
        return MixinShortcuts.doWitchHatDamage((LivingEntity)(Object)this, value, source);
    }
}
