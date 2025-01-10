package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_interfaces.BobberMixInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Debug(export = true)
@Mixin(FishingBobberEntityRenderer.class)
public abstract class FishingRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private static Identifier TEXTURE;
    @Unique private static final Identifier TEXTURE_COBALT = Identifier.of(Frontiers.MOD_ID, "textures/entity/cobalt_fishing_hook.png");
    @Unique private static final RenderLayer LAYER_COBALT = RenderLayer.getEntityCutout(TEXTURE_COBALT);

    @Shadow
    private static float percentage(int value, int max)
    {
        return 0.0f;
    }

    @Shadow @Final private static RenderLayer LAYER;

    @Unique
    private static void renderFishingLineColor(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd, int line_color)
    {
        float f = x * segmentStart;
        float g = y * (segmentStart * segmentStart + segmentStart) * 0.5F + 0.25F;
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5F + 0.25F - g;
        float k = z * segmentEnd - h;
        float l = MathHelper.sqrt(i * i + j * j + k * k);
        i /= l;
        j /= l;
        k /= l;
        buffer.vertex(matrices, f, g, h).color(line_color).normal(matrices, i, j, k);
    }
    @Unique
    private Vec3d getHandPosItemCheck(PlayerEntity player, float f, float tickDelta, Item checkItem)
    {
        int i = player.getMainArm() == Arm.RIGHT ? 1 : -1;
        ItemStack itemStack = player.getMainHandStack();
        if (!itemStack.isOf(checkItem)) {
            i = -i;
        }
        if (this.dispatcher.gameOptions.getPerspective().isFirstPerson() && player == MinecraftClient.getInstance().player) {
            double m = 960.0 / (double)this.dispatcher.gameOptions.getFov().getValue().intValue();
            Vec3d vec3d = this.dispatcher.camera.getProjection().getPosition((float)i * 0.525F, -0.1F).multiply(m).rotateY(f * 0.5F).rotateX(-f * 0.7F);
            return player.getCameraPosVec(tickDelta).add(vec3d);
        } else {
            float g = MathHelper.lerp(tickDelta, player.prevBodyYaw, player.bodyYaw) * (float) (Math.PI / 180.0);
            double d = (double)MathHelper.sin(g);
            double e = (double)MathHelper.cos(g);
            float h = player.getScale();
            double j = (double)i * 0.35 * (double)h;
            double k = 0.8 * (double)h;
            float l = player.isInSneakingPose() ? -0.1875F : 0.0F;
            return player.getCameraPosVec(tickDelta).add(-e * j - d * k, (double)l - 0.45 * (double)h, -d * j + e * k);
        }
    }
    @Unique
    private RenderLayer getLayer(FishingBobberEntity fishingBobberEntity)
    {
        int level = ((BobberMixInterface)fishingBobberEntity).getBobberLevel();
        return switch (level)
        {
            case 0 -> LAYER;
            case 1 -> LAYER_COBALT;
            default -> LAYER; /*/noinspection DuplicateBranchesInSwitch/*/
        };
    }

    @Redirect(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V")
    )
    private void new_matrices(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd, @Local(argsOnly = true) FishingBobberEntity fishingBobberEntity)
    {
        int newLineColor = ((BobberMixInterface)fishingBobberEntity).getLineColor();
        renderFishingLineColor(x, y, z, buffer, matrices, segmentStart, segmentEnd, newLineColor);
    }
    //@Inject(
    //      method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
    //      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V"),
    //      locals = LocalCapture.CAPTURE_FAILHARD
    //      )
    //private void redo_matrice(FishingBobberEntity fishingBobberEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci, PlayerEntity playerEntity, MatrixStack.Entry entry, VertexConsumer vertexConsumer, float h, float j, Vec3d vec3d, Vec3d vec3d2, float k, float l, float m, VertexConsumer vertexConsumer2, MatrixStack.Entry entry2, int n, int o)
    //{
    //    int newLineColor = ((BobberMixInterface)fishingBobberEntity).getLineColor();
    //    renderFishingLineColor(k, l, m, vertexConsumer2, entry2, percentage(o, 16), percentage(o + 1, 16), newLineColor);
    //}

    @Redirect(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;getHandPos(Lnet/minecraft/entity/player/PlayerEntity;FF)Lnet/minecraft/util/math/Vec3d;")
    )
    private Vec3d newHandPosCheck(FishingBobberEntityRenderer instance, PlayerEntity player, float f, float tickDelta, @Local(argsOnly = true) FishingBobberEntity fishingBobberEntity)
    {
        Item new_item_check = ((BobberMixInterface)fishingBobberEntity).getParentItem();
        return getHandPosItemCheck(player, f, tickDelta, new_item_check);
    }

    @Inject(method = "getTexture(Lnet/minecraft/entity/projectile/FishingBobberEntity;)Lnet/minecraft/util/Identifier;", at = @At(value = "RETURN"), cancellable = true)
    private void newTex(FishingBobberEntity fishingBobberEntity, CallbackInfoReturnable<Identifier> cir)
    {
        int level = ((BobberMixInterface)fishingBobberEntity).getBobberLevel();
        switch (level)
        {
            case 0: cir.setReturnValue(TEXTURE);
            case 1: cir.setReturnValue(TEXTURE_COBALT);
            default: cir.setReturnValue(cir.getReturnValue());
        }
    }

    @ModifyVariable(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "STORE"),
            name = "vertexConsumer"
    )
    private VertexConsumer lol(VertexConsumer value, @Local(argsOnly = true) FishingBobberEntity fishingBobberEntity, @Local(argsOnly = true) VertexConsumerProvider vertexConsumerProvider)
    {
        return vertexConsumerProvider.getBuffer(getLayer(fishingBobberEntity));
    }
}
