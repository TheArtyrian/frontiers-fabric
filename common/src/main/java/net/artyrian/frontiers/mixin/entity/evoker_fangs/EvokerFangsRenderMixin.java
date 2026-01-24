package net.artyrian.frontiers.mixin.entity.evoker_fangs;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_intf.EvoFangsIntf;
import net.minecraft.client.model.EvokerFangsModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EvokerFangsRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.EvokerFangs;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(EvokerFangsRenderer.class)
public abstract class EvokerFangsRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private EvokerFangsModel<EvokerFangs> model;
    @Shadow @Final private static ResourceLocation TEXTURE_LOCATION;
    @Unique
    private static final ResourceLocation TEXTURE_FRIENDLY = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/entity/illager/friendly_fangs.png");
    @Unique
    private static final ResourceLocation TEXTURE_FLORIDA = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/entity/illager/florida_fangs.png");

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/projectile/EvokerFangs;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void getTexture(EvokerFangs evokerFangsEntity, CallbackInfoReturnable<ResourceLocation> cir)
    {
        boolean is_friend = ((EvoFangsIntf)evokerFangsEntity).frontiers_1_21x$isFriendly();
        if (is_friend)
        {
            boolean florida = ((EvoFangsIntf)evokerFangsEntity).frontiers_1_21x$isGator();
            if (florida) cir.setReturnValue(TEXTURE_FLORIDA);
            else cir.setReturnValue(TEXTURE_FRIENDLY);
        }
    }

    @ModifyVariable(method = "render(Lnet/minecraft/world/entity/projectile/EvokerFangs;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer render_new_layer(VertexConsumer value, @Local EvokerFangs evokerFangsEntity, @Local MultiBufferSource vertexConsumerProvider)
    {
        boolean is_friend = ((EvoFangsIntf)evokerFangsEntity).frontiers_1_21x$isFriendly();
        if (is_friend)
        {
            boolean florida = ((EvoFangsIntf)evokerFangsEntity).frontiers_1_21x$isGator();
            if (florida) return vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE_FLORIDA));
            else return vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE_FRIENDLY));
        }
        else return vertexConsumerProvider.getBuffer(this.model.renderType(TEXTURE_LOCATION));
    }
}
