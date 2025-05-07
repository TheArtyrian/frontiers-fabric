package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin
{
    @ModifyVariable(method = "renderBakedItemModel", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int modifyLight(int original, @Local(argsOnly = true) ItemStack stack)
    {
        if (!stack.isEmpty() && stack.isIn(ModTags.Items.GLOWING_BRIMTAN_ITEMS))
        {
            return 15728880;
        }
        return original;
    }
}
