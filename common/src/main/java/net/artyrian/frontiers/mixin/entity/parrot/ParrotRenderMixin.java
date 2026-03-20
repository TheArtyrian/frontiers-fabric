package net.artyrian.frontiers.mixin.entity.parrot;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_intf.ParrotRenderIntf;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.ParrotRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Parrot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ParrotRenderer.class)
public abstract class ParrotRenderMixin extends EntityRenderMixin implements ParrotRenderIntf
{
    @Override
    public ResourceLocation frontiers$getTextureFromName(ResourceLocation og, String name)
    {
        if ("Kazooie".equals(name)) {
            return ParrotRenderIntf.KAZOOIE_TEXTURE;
        }
        else if ("Lovebirb".equals(name)) {
            return ParrotRenderIntf.LOVEBIRB_TEXTURE;
        }
        else if ("Keynis".equals(name)) {
            return ParrotRenderIntf.KEYNIS_TEXTURE;
        }
        return og;
    }

    @ModifyReturnValue(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Parrot;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"))
    private ResourceLocation getTexture(ResourceLocation original, @Local(argsOnly = true) Parrot parrotEntity)
    {
        return frontiers$getTextureFromName(original, ChatFormatting.stripFormatting(parrotEntity.getName().getString()));
    }
}
