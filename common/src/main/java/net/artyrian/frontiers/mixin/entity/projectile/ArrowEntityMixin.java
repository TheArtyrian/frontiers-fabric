package net.artyrian.frontiers.mixin.entity.projectile;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.armor.ModArmorBonus;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Arrow.class)
public abstract class ArrowEntityMixin extends ProjectileMixin
{
    @Shadow protected abstract PotionContents getPotionContents();

    @Inject(method = "onHit", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;onHit(Lnet/minecraft/entity/LivingEntity;)V",
            shift = At.Shift.AFTER),
            cancellable = true
    )
    private void deflectStray(LivingEntity target, CallbackInfo ci)
    {
        if (this.getOwner() instanceof Stray && ModArmorBonus.wearingSetOf(target, ModArmorBonus.FROSTITE))
        {
            ci.cancel();
        }
    }
}
