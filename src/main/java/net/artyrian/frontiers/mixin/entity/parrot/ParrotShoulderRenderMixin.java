package net.artyrian.frontiers.mixin.entity.parrot;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.ParrotRenderMixInterface;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.client.render.entity.feature.ShoulderParrotFeatureRenderer;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Debug(export = true)
@Mixin(value = ShoulderParrotFeatureRenderer.class, priority = 500)
public abstract class ParrotShoulderRenderMixin<T extends PlayerEntity>
{
    @Shadow
    @Final
    private ParrotEntityModel model;

    /** This code re-implements the Supplementaries shoulder party parrot behavior, since doing a mixin of a mixin is beyond feasible.
     *
     * The original code can be found at:
     * <a href="https://github.com/MehVahdJukaar/Supplementaries/blob/1.21/common/src/main/java/net/mehvahdjukaar/supplementaries/mixins/ParrotLayerMixin.java">...</a>
     * */
    @Inject(method = "renderShoulderParrot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/nbt/NbtCompound;getString(Ljava/lang/String;)Ljava/lang/String;",
                    shift = At.Shift.BEFORE
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void doRenderParrotSuppFixPleaseGodHelpMe(
            MatrixStack matrixStack, VertexConsumerProvider buffer, int packedLight, T livingEntity, float limbAngle, float limbDistance, float headYaw, float headPitch, boolean leftShoulder, CallbackInfo ci, NbtCompound nbtCompound
    )
    {
        if (Frontiers.SUPPLEMENTARIES_LOADED)
        {
            if (nbtCompound.getBoolean("record_playing"))
            {
                EntityType.get(nbtCompound.getString("id")).filter((entityType) -> entityType == EntityType.PARROT).ifPresent((entityType) ->
                {
                    matrixStack.push();
                    matrixStack.translate(leftShoulder ? 0.4000000059604645 : -0.4000000059604645, livingEntity.isInSneakingPose() ? -1.2999999523162842 : -1.5, 0.0);
                    ParrotEntity.Variant variant = ParrotEntity.Variant.byIndex(nbtCompound.getInt("Variant"));

                    // Buffer reimplement for Frontiers because mixing into mixins is like cutting yourself with rusty knives
                    String name = nbtCompound.getString("CustomName");
                    VertexConsumer vertexConsumer;
                    if ("\"Kazooie\"".equals(name)) { vertexConsumer = buffer.getBuffer(this.model.getLayer(ParrotRenderMixInterface.KAZOOIE_TEXTURE)); }
                    else if ("\"Lovebirb\"".equals(name)) { vertexConsumer = buffer.getBuffer(this.model.getLayer(ParrotRenderMixInterface.LOVEBIRB_TEXTURE));}
                    else vertexConsumer = buffer.getBuffer(this.model.getLayer(ParrotEntityRenderer.getTexture(variant)));

                    frontiersSuppReimpHELP(this.model, matrixStack, vertexConsumer, packedLight, OverlayTexture.DEFAULT_UV, limbAngle, limbDistance, headYaw, headPitch, livingEntity.age, 0.0F);
                    matrixStack.pop();
                });
                if (Frontiers.LOADER.isModLoaded("cpm"))
                {
                    matrixStack.pop();
                }
                ci.cancel();
            }
        }
    }

    @ModifyVariable(method = "method_17958", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer mixingIntoSynthesisWowHelp(
            VertexConsumer vertexConsumer,
            @Local NbtCompound nbtCompound,
            @Local ParrotEntity.Variant variant,
            @Local(argsOnly = true) VertexConsumerProvider vertexConsumers
    )
    {
        String name = nbtCompound.getString("CustomName");

        // I HATE HOW THIS WORKS JESUS AAAAAAAAAA
        if ("\"Kazooie\"".equals(name)) return vertexConsumers.getBuffer(this.model.getLayer(ParrotRenderMixInterface.KAZOOIE_TEXTURE));
        else if ("\"Lovebirb\"".equals(name)) return vertexConsumers.getBuffer(this.model.getLayer(ParrotRenderMixInterface.LOVEBIRB_TEXTURE));

        return vertexConsumer;
    }

    @Unique
    private static void frontiersSuppReimpHELP(ParrotEntityModel model, MatrixStack poseStack, VertexConsumer buffer,
                                                      int packedLight, int packedOverlay, float limbSwing, float limbSwingAmount,
                                                      float netHeadYaw, float headPitch, int tickCount, float bob)
    {
        model.animateModel(ParrotEntityModel.Pose.PARTY);
        model.setAngles(ParrotEntityModel.Pose.PARTY, tickCount, limbSwing, limbSwingAmount, bob, netHeadYaw, headPitch);
        model.getPart().render(poseStack, buffer, packedLight, packedOverlay);
    }
}
