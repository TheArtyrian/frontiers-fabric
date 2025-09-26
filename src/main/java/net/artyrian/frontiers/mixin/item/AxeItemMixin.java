package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.custom.tool.Unbreakable;
import net.artyrian.frontiers.misc.ModToolActions;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
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
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;
import java.util.Optional;

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

    // Retroactively based on my additions to NexusLib
    @ModifyVariable(method = "tryStrip", at = @At("STORE"), ordinal = 0)
    private Optional<BlockState> frontiersStripInput(Optional<BlockState> value, @Local(argsOnly = true) BlockState state)
    {
        if (value.isEmpty())
        {
            Optional<BlockState> stripgrab = Optional.ofNullable(ModToolActions.LOGS.get(state.getBlock())).map((block) -> {
                return block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));
            });

            if (stripgrab.isPresent()) return stripgrab;
        }

        return value;
    }
}
