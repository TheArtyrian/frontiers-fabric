package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownEgg.class)
public abstract class EggMixin extends ProjectileMixin
{
    @Inject(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;sendEntityStatus(Lnet/minecraft/entity/Entity;B)V", shift = At.Shift.AFTER))
    private void frontiersCrackCrackCrackTheEggIntoTheBowl(HitResult hitResult, CallbackInfo ci)
    {
        this.playSound(ModSounds.EGG_CRACK,0.8F, 1.0F);
    }
}
