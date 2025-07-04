package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.custom.tool.Unbreakable;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin
{
    @ModifyExpressionValue(method = "shouldCancelStripAttempt", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private static boolean checkOffhandables(boolean original, @Local PlayerEntity entity)
    {
        return original || entity.getOffHandStack().isIn(ModTags.Items.OFFHAND_PRIORITY_ITEM);
    }

    @WrapOperation(method = "useOnBlock", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;)V"))
    private void wrapForUnbreakables(
            ItemStack stack,
            int amount,
            LivingEntity entity,
            EquipmentSlot slot,
            Operation<Void> original,
            @Local(argsOnly = true)ItemUsageContext context)
    {
        EquipmentSlot slotHand = LivingEntity.getSlotForHand(context.getHand());
        if (stack.getItem() instanceof Unbreakable unbreakable && unbreakable.getBrokenItem() != null)
        {
            ItemStack stack2 = stack.damage(amount, unbreakable.getBrokenItem(), entity, slotHand);
            if (stack2 != stack) entity.setStackInHand(context.getHand(), stack2);
        }
        else
        {
            original.call(stack, amount, entity, slot);
        }
    }
}
