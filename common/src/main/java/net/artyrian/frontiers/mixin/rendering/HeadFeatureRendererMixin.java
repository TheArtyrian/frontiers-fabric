package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.rendering.armor.WitchHatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(CustomHeadLayer.class)
public abstract class HeadFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & HeadedModel>
{
    @Unique
    private final WitchHatModel frontiersArty_WitchHat = new WitchHatModel();
    @Shadow
    public static void translate(PoseStack matrices, boolean villager) { }

    @Inject(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/model/ModelPart;rotate(Lnet/minecraft/client/util/math/MatrixStack;)V",
            shift = At.Shift.AFTER),
            cancellable = true)
    private void frontiersCheckForHeadRenderStuff(
            PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider,
            int i,
            T livingEntity, float f, float g, float h, float j, float k, float l,
            CallbackInfo ci,
            @Local Item item)
    {
        if (item.equals(ModItem.WITCH_HAT))
        {
            boolean villager = livingEntity instanceof Villager || livingEntity instanceof ZombieVillager;

            matrixStack.scale(1.0F, 1.0F, 1.0F);
            matrixStack.translate(0.0F, -1.5, 0.0F);

            if (livingEntity instanceof ArmorStand) matrixStack.translate(0.0F, 0.2, 0.0F);
            else if (villager) matrixStack.translate(0.0F, -0.0625F, 0.0F);

            VertexConsumer consumer = vertexConsumerProvider.getBuffer(RenderType.entityTranslucent(WitchHatModel.TEXTURE));
            this.frontiersArty_WitchHat.renderToBuffer(matrixStack, consumer, i, OverlayTexture.NO_OVERLAY);
            matrixStack.popPose();

            ci.cancel();
        }
    }
}
