package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownEgg.class)
public abstract class EggMixin extends ProjectileMixin
{
    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V", shift = At.Shift.AFTER))
    private void frontiersCrackCrackCrackTheEggIntoTheBowl(HitResult hitResult, CallbackInfo ci)
    {
        this.playSound(ModSounds.EGG_CRACK.get(),0.8F, 1.0F);
    }
}
