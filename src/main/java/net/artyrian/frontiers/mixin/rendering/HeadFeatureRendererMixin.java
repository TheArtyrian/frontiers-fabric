package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.rendering.armor.WitchHatModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeadFeatureRenderer.class)
public abstract class HeadFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithHead>
{
    @Unique
    private final WitchHatModel frontiersArty_WitchHat = new WitchHatModel();
    @Shadow
    public static void translate(MatrixStack matrices, boolean villager) { }

    @Inject(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/model/ModelPart;rotate(Lnet/minecraft/client/util/math/MatrixStack;)V",
            shift = At.Shift.AFTER),
            cancellable = true)
    private void frontiersCheckForHeadRenderStuff(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            T livingEntity, float f, float g, float h, float j, float k, float l,
            CallbackInfo ci,
            @Local Item item,
            @Local(ordinal = 0) boolean villager)
    {
        if (item.equals(ModItem.WITCH_HAT))
        {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
            matrixStack.translate(0.0F, -(livingEntity.getHeight() + 0.1), 0.0F);
            if (livingEntity instanceof ArmorStandEntity) matrixStack.translate(0.0F, 0.2, 0.0F);

            //matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F));

            VertexConsumer consumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(WitchHatModel.TEXTURE));
            this.frontiersArty_WitchHat.render(matrixStack, consumer, i, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();

            ci.cancel();
        }
    }
}
