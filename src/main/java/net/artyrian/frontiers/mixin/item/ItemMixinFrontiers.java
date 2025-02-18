package net.artyrian.frontiers.mixin.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Item.class)
public abstract class ItemMixinFrontiers
{
    @ModifyReturnValue(method = "canRepair", at = @At("RETURN"))
    public boolean canRepair(boolean original, ItemStack stack, ItemStack ingredient)
    {
        return original;
    }
}
