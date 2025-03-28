package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.misc.ModDamageType;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlMixin extends EntityMixin
{
    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private DamageSource changeToWarp(DamageSource source)
    {
        return ModDamageType.of(this.getWorld(), ModDamageType.ENDER_PEARL_WARP);
    }
}
