package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.reg.content.FRTags;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorMixinFabric
{
    /** Check NeoForge package for an equivalent! */
    @ModifyVariable(method = "renderArmorPiece", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int makeArmorGlow(int original, @Local(argsOnly = true) LivingEntity entity, @Local(argsOnly = true) EquipmentSlot armorSlot)
    {
        ItemStack stack = entity.getItemBySlot(armorSlot);
        if (stack.is(FRTags.Items.GLOWING_BRIMTAN_ITEMS))
        {
            return 15728880;
        }
        return original;
    }
}
