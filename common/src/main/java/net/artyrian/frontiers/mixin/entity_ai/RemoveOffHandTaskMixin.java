package net.artyrian.frontiers.mixin.entity_ai;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.StopHoldingItemIfNoLongerAdmiring;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Debug(export = true)
@Mixin(StopHoldingItemIfNoLongerAdmiring.class)
public abstract class RemoveOffHandTaskMixin
{
    @ModifyExpressionValue(method = "method_47299", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z")
    )
    private static boolean checkOffhandables(boolean original, @Local(argsOnly = true) Piglin entity)
    {
        return original || entity.getOffhandItem().is(ModTags.Items.OFFHAND_PRIORITY_ITEM);
    }
}
