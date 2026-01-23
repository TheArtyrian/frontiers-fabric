package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.reg.misc.ModDamageType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ThrownEnderpearl.class)
public abstract class EnderPearlMixin extends EntityMixin
{
    @ModifyArg(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ThrownEnderpearl;damageSources()Lnet/minecraft/world/damagesource/DamageSources;"))
    private DamageSource changeToWarp(DamageSource source)
    {
        return ModDamageType.of(this.level(), ModDamageType.ENDER_PEARL_WARP);
    }
}
