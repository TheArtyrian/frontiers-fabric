package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.reg.property.FRArmorBonus;
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

    @Inject(method = "doPostHurtEffects", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;doPostHurtEffects(Lnet/minecraft/world/entity/LivingEntity;)V",
            shift = At.Shift.AFTER),
            cancellable = true
    )
    private void deflectStray(LivingEntity target, CallbackInfo ci)
    {
        if (this.getOwner() instanceof Stray && FRArmorBonus.wearingSetOf(target, FRArmorBonus.FROSTITE))
        {
            ci.cancel();
        }
    }
}
