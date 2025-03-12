package net.artyrian.frontiers.mixin.entity.evoker_fangs;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_interfaces.FangsMixInterface;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EvokerFangsEntityRenderer;
import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(EvokerFangsEntityRenderer.class)
public abstract class EvokerFangsRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private EvokerFangsEntityModel<EvokerFangsEntity> model;
    @Shadow @Final private static Identifier TEXTURE;
    @Unique
    private static final Identifier TEXTURE_FRIENDLY = Identifier.of(Frontiers.MOD_ID,"textures/entity/illager/friendly_fangs.png");
    @Unique
    private static final Identifier TEXTURE_FLORIDA = Identifier.of(Frontiers.MOD_ID,"textures/entity/illager/florida_fangs.png");

    @Inject(method = "getTexture(Lnet/minecraft/entity/mob/EvokerFangsEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"), cancellable = true)
    public void getTexture(EvokerFangsEntity evokerFangsEntity, CallbackInfoReturnable<Identifier> cir)
    {
        boolean is_friend = ((FangsMixInterface)evokerFangsEntity).frontiers_1_21x$isFriendly();
        if (is_friend)
        {
            boolean florida = ((FangsMixInterface)evokerFangsEntity).frontiers_1_21x$isGator();
            if (florida) cir.setReturnValue(TEXTURE_FLORIDA);
            else cir.setReturnValue(TEXTURE_FRIENDLY);
        }
    }

    @ModifyVariable(method = "render(Lnet/minecraft/entity/mob/EvokerFangsEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer render_new_layer(VertexConsumer value, @Local EvokerFangsEntity evokerFangsEntity, @Local VertexConsumerProvider vertexConsumerProvider)
    {
        boolean is_friend = ((FangsMixInterface)evokerFangsEntity).frontiers_1_21x$isFriendly();
        if (is_friend)
        {
            boolean florida = ((FangsMixInterface)evokerFangsEntity).frontiers_1_21x$isGator();
            if (florida) return vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE_FLORIDA));
            else return vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE_FRIENDLY));
        }
        else return vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
    }
}
