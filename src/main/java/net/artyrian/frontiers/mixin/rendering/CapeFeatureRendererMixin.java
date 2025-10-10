package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(CapeFeatureRenderer.class)
public abstract class CapeFeatureRendererMixin
{
    @Unique
    private final List<String> FRONTIERS$CUTOUT_UUIDS = List.of(
            Frontiers.CONTRIB_IDS.get("Courtjjester")
    );

    // Special method that makes cool transparent capes render as they should
    @WrapOperation(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;",
                    ordinal = 0
            )
    )
    private VertexConsumer frontiersGetSpecialBuff(
            VertexConsumerProvider instance,
            RenderLayer renderLayer,
            Operation<VertexConsumer> original,
            @Local(argsOnly = true) AbstractClientPlayerEntity abstractClientPlayerEntity,
            @Local SkinTextures tex)
    {
        if (FRONTIERS$CUTOUT_UUIDS.contains(abstractClientPlayerEntity.getUuidAsString()))
        {
            return original.call(instance, RenderLayer.getEntityCutoutNoCull(tex.capeTexture()));
        }
        else
        {
            return original.call(instance, renderLayer);
        }
    }
}
