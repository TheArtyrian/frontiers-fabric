package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.item.custom.tool.Unbreakable;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;
import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin
{
    @ModifyExpressionValue(method = "playerHasShieldUseIntent", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
    )
    private static boolean checkOffhandables(boolean original, @Local Player entity)
    {
        return original || entity.getOffhandItem().is(ModTags.Items.OFFHAND_PRIORITY_ITEM);
    }

    @WrapOperation(method = "useOn", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"))
    private void wrapForUnbreakables(
            ItemStack stack,
            int amount,
            LivingEntity entity,
            EquipmentSlot slot,
            Operation<Void> original,
            @Local(argsOnly = true)UseOnContext context)
    {
        EquipmentSlot slotHand = LivingEntity.getSlotForHand(context.getHand());
        if (stack.getItem() instanceof Unbreakable unbreakable && unbreakable.getBrokenItem() != null)
        {
            ItemStack stack2 = stack.hurtAndConvertOnBreak(amount, unbreakable.getBrokenItem(), entity, slotHand);
            if (stack2 != stack) entity.setItemInHand(context.getHand(), stack2);
        }
        else
        {
            original.call(stack, amount, entity, slot);
        }
    }
}
