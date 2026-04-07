package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.reg.property.FRDamageType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrownEnderpearl.class)
public abstract class EnderPearlMixin extends EntityMixin
{
    @ModifyArg(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private DamageSource changeToWarp(DamageSource source)
    {
        return FRDamageType.of(this.level(), FRDamageType.ENDER_PEARL_WARP);
    }
}
