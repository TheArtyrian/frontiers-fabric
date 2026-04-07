package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin extends ItemMixinFrontiers
{
    @ModifyReturnValue(method = "getUseDuration", at = @At("RETURN"))
    private int frontiersReturnChange(int original, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) LivingEntity user)
    {
        if (!(user instanceof Witch))
        {
            ItemStack hatStack = user.getItemBySlot(EquipmentSlot.HEAD);
            if (hatStack.is(FRItems.WITCH_HAT.get()))
            {
                return (int)(original * 0.25);
            }
        }
        return original;
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void frontiersFinishUseInj(ItemStack stack, Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir)
    {
        if (!world.isClientSide && !(user instanceof Witch))
        {
            ItemStack hatStack = user.getItemBySlot(EquipmentSlot.HEAD);
            if (hatStack.is(FRItems.WITCH_HAT.get()))
            {
                hatStack.hurtAndBreak(1, user, EquipmentSlot.HEAD);
            }
        }
    }
}
