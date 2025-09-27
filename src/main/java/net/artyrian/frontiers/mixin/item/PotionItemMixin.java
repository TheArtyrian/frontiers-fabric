package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public abstract class PotionItemMixin extends ItemMixinFrontiers
{
    @ModifyReturnValue(method = "getMaxUseTime", at = @At("RETURN"))
    private int frontiersReturnChange(int original, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) LivingEntity user)
    {
        if (!(user instanceof WitchEntity))
        {
            ItemStack hatStack = user.getEquippedStack(EquipmentSlot.HEAD);
            if (hatStack.isOf(ModItem.WITCH_HAT))
            {
                return (int)(original * 0.25);
            }
        }
        return original;
    }

    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void frontiersFinishUseInj(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir)
    {
        if (!world.isClient && !(user instanceof WitchEntity))
        {
            ItemStack hatStack = user.getEquippedStack(EquipmentSlot.HEAD);
            if (hatStack.isOf(ModItem.WITCH_HAT))
            {
                hatStack.damage(1, user, EquipmentSlot.HEAD);
            }
        }
    }
}
