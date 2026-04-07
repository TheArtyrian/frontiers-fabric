package net.artyrian.frontiers.mixin.entity.iron_golem;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.reg.content.FRTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IronGolem.class)
public abstract class IronGolemMixin extends MobEntityMixin
{
    /**
     * Prevents Iron Golems from attacking this entity if in the tag / if it's a tame Hoglin.
     */
    @WrapOperation(method = "doPush", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/IronGolem;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void frontiersPreventPushTargetIfTagged(IronGolem instance, LivingEntity livingEntity, Operation<Void> original)
    {
        boolean tameHog = (livingEntity instanceof HoglinIntf hog && hog.frontiers_1_21x$isTruffled());
        if (!tameHog && !livingEntity.getType().is(FRTags.EntityTypes.IRON_GOLEM_NO_TARGET))
        {
            original.call(instance, livingEntity);
        }
    }

    @Inject(method = "canAttackType", at = @At("HEAD"), cancellable = true)
    private void frontiersStopCanTarget(EntityType<?> type, CallbackInfoReturnable<Boolean> cir)
    {
        if (type.is(FRTags.EntityTypes.IRON_GOLEM_NO_TARGET))
        {
            cir.setReturnValue(false);
        }
    }
}
