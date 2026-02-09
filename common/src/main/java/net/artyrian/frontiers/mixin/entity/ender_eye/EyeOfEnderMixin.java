package net.artyrian.frontiers.mixin.entity.ender_eye;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EyeOfEnder.class)
public class EyeOfEnderMixin
{
    @ModifyReturnValue(method = "getDefaultItem", at = @At("RETURN"))
    private ItemStack frontiers$changeToVoidPearl(ItemStack original)
    {
        return new ItemStack(ModItem.VOID_PEARL.get());
    }
}
