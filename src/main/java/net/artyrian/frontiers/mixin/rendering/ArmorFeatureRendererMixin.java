package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin
{
    @ModifyVariable(method = "renderArmor", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int makeArmorGlow(int original, @Local(argsOnly = true) LivingEntity entity, @Local(argsOnly = true) EquipmentSlot armorSlot)
    {
        ItemStack stack = entity.getEquippedStack(armorSlot);
        if (stack.isIn(ModTags.Items.GLOWING_BRIMTAN_ITEMS))
        {
            return 15728880;
        }
        return original;
    }
}
