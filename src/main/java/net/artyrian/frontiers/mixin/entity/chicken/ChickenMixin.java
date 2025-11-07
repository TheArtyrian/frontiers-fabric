package net.artyrian.frontiers.mixin.entity.chicken;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.entity.passive.GoldenChickenEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenEntity.class)
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
    public ItemEntity eggDropIntercept(ChickenEntity chicken, ItemConvertible itemConvertible, Operation<ItemEntity> original)
    {
        if (frontiers$getGoldenEgg())
        {
            frontiers$setGoldenEgg(false);
            if (this.getWorld().getRandom().nextBetween(0, 3) == 0) return original.call(chicken, ModItem.GOLDEN_EGG);
        }
        return original.call(chicken, itemConvertible);
    }

    @Inject(method = "isBreedingItem", at = @At("HEAD"), cancellable = true)
    public void isAbleToMakeGoldenEgg(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        if (stack.isIn(ModTags.Items.GOLDEN_CHICKEN_FOOD))
        {
            ChickenEntity self = ((ChickenEntity)(Object)this);
            int i = self.getBreedingAge();
            boolean baby = self.isBaby();
            if (i == 0 && !baby) frontiers$setGoldenEgg(true);
            cir.setReturnValue(true);
        }
    }

    @ModifyReturnValue(method = "method_58366", at = @At("RETURN"))
    private static boolean frontiersCanAlsoFollowGoldenFood(boolean original, @Local(argsOnly = true) ItemStack stack)
    {
        return original || stack.isIn(ModTags.Items.GOLDEN_CHICKEN_FOOD);
    }

    @Override
    public void frontiersCanBreedWithHook(AnimalEntity other, CallbackInfoReturnable<Boolean> cir)
    {
        ChickenEntity self = (ChickenEntity)(Object)this;
        boolean returnVal = false;
        if (other == self)
        {
            Frontiers.LOGGER.info("self");
            returnVal = false;
        }
        else if (!(other instanceof ChickenEntity) && !(other instanceof GoldenChickenEntity))
        {
            Frontiers.LOGGER.info("not gold or chick");
            returnVal = false;
        }
        else
        {
            Frontiers.LOGGER.info("yes");
            returnVal = this.isInLove() && other.isInLove();
        }
        cir.setReturnValue(returnVal);
    }
}