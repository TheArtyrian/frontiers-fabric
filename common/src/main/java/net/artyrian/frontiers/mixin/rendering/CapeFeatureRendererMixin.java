package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.resources.PlayerSkin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Mixin(CapeLayer.class)
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
            MultiBufferSource instance,
            RenderType renderLayer,
            Operation<VertexConsumer> original,
            @Local(argsOnly = true) AbstractClientPlayer abstractClientPlayerEntity,
            @Local PlayerSkin tex)
    {
        if (FRONTIERS$CUTOUT_UUIDS.contains(abstractClientPlayerEntity.getStringUUID()))
        {
            return original.call(instance, RenderType.entityCutoutNoCull(tex.capeTexture()));
        }
        else
        {
            return original.call(instance, renderLayer);
        }
    }
}
