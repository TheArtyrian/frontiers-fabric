package net.artyrian.frontiers.mixin.entity.parrot;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModParrotVariant;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_interfaces.ParrotRenderMixInterface;
import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ParrotEntityRenderer.class)
public abstract class ParrotRenderMixin extends EntityRenderMixin implements ParrotRenderMixInterface
{
    @Override
    public Identifier frontiers$getTextureFromName(Identifier og, String name)
    {
        if ("Kazooie".equals(name)) {
            return ParrotRenderMixInterface.KAZOOIE_TEXTURE;
        }
        else if ("Lovebirb".equals(name)) {
            return ParrotRenderMixInterface.LOVEBIRB_TEXTURE;
        }
        else if ("Keynis".equals(name)) {
            return ParrotRenderMixInterface.KEYNIS_TEXTURE;
        }
        return og;
    }

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/passive/ParrotEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private Identifier getTexture(Identifier original, @Local(argsOnly = true) ParrotEntity parrotEntity)
    {
        return frontiers$getTextureFromName(original, Formatting.strip(parrotEntity.getName().getString()));
    }
}
