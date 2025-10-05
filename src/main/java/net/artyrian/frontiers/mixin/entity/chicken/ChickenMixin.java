package net.artyrian.frontiers.mixin.entity.chicken;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

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
            if (this.getWorld().getRandom().nextFloat() >= 0.50F) return original.call(chicken, ModItem.GOLDEN_EGG);
        }
        return original.call(chicken, itemConvertible);
    }

    @ModifyReturnValue(method = "isBreedingItem", at = @At("RETURN"))
    public boolean isAbleToMakeGoldenEgg(boolean original, @Local(argsOnly = true) ItemStack stack)
    {
        if (stack.isIn(ModTags.Items.GOLDEN_CHICKEN_FOOD))
        {
            frontiers$setGoldenEgg(true);
        }
        return original || stack.isIn(ModTags.Items.GOLDEN_CHICKEN_FOOD);
    }
}