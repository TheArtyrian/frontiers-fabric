package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
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
}
