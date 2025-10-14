package net.artyrian.frontiers.mixin.entity.iron_golem;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.ai.creeper.CreeperNewRevengeGoal;
import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.HoglinMixInterface;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.IronGolemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemMixin extends MobEntityMixin
{
    /**
     * Prevents Iron Golems from attacking Crawlers / tamed Hoglins in their target goal.
     */
    @ModifyArgs(
            method = "initGoals",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/ActiveTargetGoal;<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;IZZLjava/util/function/Predicate;)V")),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V",
                    ordinal = 1)
    )
    private void redirRevGoal(Args args)
    {
        if (args.get(1) instanceof ActiveTargetGoal<?> goal)
        {
            args.set(1,
                    new ActiveTargetGoal<>(
                            (IronGolemEntity) (Object) this,
                            MobEntity.class,
                            5,
                            false,
                            false,
                            entity -> {
                                return entity instanceof Monster
                                        && !(entity instanceof CreeperEntity)
                                        && !entity.getType().isIn(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET)
                                        && !(entity instanceof HoglinMixInterface hog && hog.frontiers_1_21x$isTruffled());
                            }
                            ));
        }
    }

    /**
     * Prevents Iron Golems from attacking this entity if in the tag / if it's a tame Hoglin.
     */
    @WrapOperation(method = "pushAway", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/IronGolemEntity;setTarget(Lnet/minecraft/entity/LivingEntity;)V"))
    private void frontiersPreventPushTargetIfTagged(IronGolemEntity instance, LivingEntity livingEntity, Operation<Void> original)
    {
        boolean tameHog = (livingEntity instanceof HoglinMixInterface hog && hog.frontiers_1_21x$isTruffled());
        if (!tameHog && !livingEntity.getType().isIn(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET))
        {
            original.call(instance, livingEntity);
        }
    }

    @Inject(method = "canTarget", at = @At("HEAD"), cancellable = true)
    private void frontiersStopCanTarget(EntityType<?> type, CallbackInfoReturnable<Boolean> cir)
    {
        if (type.isIn(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET))
        {
            cir.setReturnValue(false);
        }
    }
}
