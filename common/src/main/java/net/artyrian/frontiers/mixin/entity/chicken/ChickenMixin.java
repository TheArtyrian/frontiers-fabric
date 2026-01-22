package net.artyrian.frontiers.mixin.entity.chicken;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.entity.ai.chicken.ChickenMateGoal;
import net.artyrian.frontiers.entity.ai.creeper.CreeperNewRevengeGoal;
import net.artyrian.frontiers.entity.passive.GoldenChickenEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends AnimalEntityMixin
{
    @Unique
    private final Boolean GOLDEN_EGG = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.CHICKEN_GOLDEN_EGG, ModAttachmentTypes.CHICKEN_GOLDEN_EGG.initializer());

    @Unique
    public boolean frontiers$getGoldenEgg()
    {
        return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.CHICKEN_GOLDEN_EGG, ModAttachmentTypes.CHICKEN_GOLDEN_EGG.initializer());
    }
    @Unique
    public void frontiers$setGoldenEgg(boolean bool)
    {
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.CHICKEN_GOLDEN_EGG, bool);
    }

    @WrapOperation(method = "tickMovement", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/passive/ChickenEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;")
    )
    public ItemEntity eggDropIntercept(Chicken chicken, ItemLike itemConvertible, Operation<ItemEntity> original)
    {
        if (frontiers$getGoldenEgg())
        {
            frontiers$setGoldenEgg(false);
            if (this.getWorld().getRandom().nextIntBetweenInclusive(0, 3) == 0) return original.call(chicken, ModItem.GOLDEN_EGG);
        }
        return original.call(chicken, itemConvertible);
    }

    @Inject(method = "isBreedingItem", at = @At("HEAD"), cancellable = true)
    public void isAbleToMakeGoldenEgg(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        if (stack.is(ModTags.Items.GOLDEN_CHICKEN_FOOD))
        {
            Chicken self = ((Chicken)(Object)this);
            int i = self.getAge();
            boolean baby = self.isBaby();
            if (i == 0 && !baby) frontiers$setGoldenEgg(true);
            cir.setReturnValue(true);
        }
    }

    @ModifyReturnValue(method = "method_58366", at = @At("RETURN"))
    private static boolean frontiersCanAlsoFollowGoldenFood(boolean original, @Local(argsOnly = true) ItemStack stack)
    {
        return stack.is(ItemTags.CHICKEN_FOOD) || stack.is(ModTags.Items.GOLDEN_CHICKEN_FOOD);
    }

    /** Makes chickens able to mate with Golden Chickens */
    @ModifyArgs(
            method = "initGoals",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/AnimalMateGoal;<init>(Lnet/minecraft/entity/passive/AnimalEntity;D)V")),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V",
                    ordinal = 0)
    )
    private void frontiersGoldChickenMateGoal(Args args)
    {
        args.set(1, new ChickenMateGoal((Chicken)(Object)this, GoldenChickenEntity.class, 1.0));
    }

    @Override
    public void frontiersCanBreedWithHook(Animal other, CallbackInfoReturnable<Boolean> cir)
    {
        Chicken self = (Chicken)(Object)this;
        boolean returnVal;
        if (other == self)
        {
            returnVal = false;
        }
        else if (!(other instanceof Chicken) && !(other instanceof GoldenChickenEntity))
        {
            returnVal = false;
        }
        else
        {
            returnVal = this.isInLove() && other.isInLove();
        }
        cir.setReturnValue(returnVal);
    }
}