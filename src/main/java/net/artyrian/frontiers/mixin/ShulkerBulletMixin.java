package net.artyrian.frontiers.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerBulletEntity.class)
public class ShulkerBulletMixin
{
    @Inject(method="damage", at = @At("HEAD"), cancellable = true)
    public void dropShulkScum(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {

    }

}
