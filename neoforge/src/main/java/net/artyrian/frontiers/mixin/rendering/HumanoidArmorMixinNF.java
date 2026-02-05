package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.reg.content.ModTags;
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
public abstract class HumanoidArmorMixinNF
{
    /** Check Fabric package for an equivalent! */
    @ModifyVariable(method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int makeArmorGlow(int original, @Local(argsOnly = true) LivingEntity entity, @Local(argsOnly = true) EquipmentSlot armorSlot)
    {
        ItemStack stack = entity.getItemBySlot(armorSlot);
        if (stack.is(ModTags.Items.GLOWING_BRIMTAN_ITEMS))
        {
            return 15728880;
        }
        return original;
    }
}
