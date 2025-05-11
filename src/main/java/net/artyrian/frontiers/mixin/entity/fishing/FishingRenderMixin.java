package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_interfaces.BobberMixInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(FishingBobberEntityRenderer.class)
public abstract class FishingRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private static Identifier TEXTURE;
    @Shadow @Final private static RenderLayer LAYER;
    @Shadow private static float percentage(int value, int max)
    {
        return 0.0f;
    }
    @Shadow protected abstract Vec3d getHandPos(PlayerEntity player, float f, float tickDelta);

    @Unique private static final float line_correction_float = 0.10F;
    @Unique private final ModelPart FISH_BOBBER_3D = frontiersCreateBobberMeta();
    @Unique private static final Identifier TEXTURE_3D = Identifier.of(Frontiers.MOD_ID, "textures/entity/fishhook/3d_fishing_hook.png");
    @Unique private static final Identifier TEXTURE_COBALT = Identifier.of(Frontiers.MOD_ID, "textures/entity/fishhook/cobalt_fishing_hook.png");
    @Unique private static final Identifier TEXTURE_COBALT_3D = Identifier.of(Frontiers.MOD_ID, "textures/entity/fishhook/3d_cobalt_fishing_hook.png");
    @Unique private static final RenderLayer LAYER_3D = RenderLayer.getEntityCutout(TEXTURE_3D);
    @Unique private static final RenderLayer LAYER_COBALT = RenderLayer.getEntityCutout(TEXTURE_COBALT);
    @Unique private static final RenderLayer LAYER_COBALT_3D = RenderLayer.getEntityCutout(TEXTURE_COBALT_3D);

    /** A redo of the vanilla fishing line rendering code - renders the fishing line in a unique color. */
    @Unique
    private static void renderFishingLineColor(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd, int line_color)
    {
        float adder = (Frontiers.CONFIG.do3DFishBobbers()) ? line_correction_float : 0.25F;

        float f = x * segmentStart;
        float g = y * (segmentStart * segmentStart + segmentStart) * 0.5F + adder;
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5F + adder - g;

        float k = z * segmentEnd - h;
        float l = MathHelper.sqrt(i * i + j * j + k * k);
        i /= l;
        j /= l;
        k /= l;

        buffer.vertex(matrices, f, g, h).color(line_color).normal(matrices, i, j, k);
    }
    /** A wrap for hand checking code in fishing rods - checks if the player is holding a given item. */
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
    /** Gets the render layer for the fishing bobber. */
    @Unique
    private RenderLayer frontiersGetLayer(FishingBobberEntity fishingBobberEntity)
    {
        int level = ((BobberMixInterface)fishingBobberEntity).frontiers_1_21x$getBobberLevel();
        boolean bobber3D = Frontiers.CONFIG.do3DFishBobbers();
        return switch (level)
        {
            case 0 -> (bobber3D) ? LAYER_3D : LAYER;
            case 1 -> (bobber3D) ? LAYER_COBALT_3D : LAYER_COBALT;
            default -> (bobber3D) ? LAYER_3D : LAYER; /*/noinspection DuplicateBranchesInSwitch/*/
        };
    }
    /** Creates the fishing bobber 3D model. Recreated from the Bedrock model in Blockbench, is 1:1 with the original. */
    @Unique
    private static ModelPart frontiersCreateBobberMeta()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData fishmain = modelPartData.addChild("fishmain", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 3).cuboid(0.0F, 1.5F, -2.5F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F))
                .uv(6, 3).cuboid(0.0F, -4.5F, -1.5F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube1 = fishmain.addChild("cube1", ModelPartBuilder.create().uv(6, 3).cuboid(0.0F, -3.0F, -1.5F, 0.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPart finalBox = TexturedModelData.of(modelData, 16, 16).createModel();
        float halfpi = ((float) Math.PI / 2);
        finalBox.rotate(new Vector3f(0, 0, (float) Math.PI));
        return finalBox;
    }

    /** Handles rendering the bobber in 3D.*/
    @Inject(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/FishingBobberEntity;getPlayerOwner()Lnet/minecraft/entity/player/PlayerEntity;", shift = At.Shift.AFTER),
            cancellable = true
    )
    private void redirectTo3DRenderIfProper(
            FishingBobberEntity fishingBobberEntity,
            float f, float g,
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            CallbackInfo ci)
    {
        PlayerEntity playerEntity = fishingBobberEntity.getPlayerOwner();
        if (playerEntity != null && Frontiers.CONFIG.do3DFishBobbers())
        {
            matrixStack.push();
            matrixStack.push();

            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(frontiersGetLayer(fishingBobberEntity));
            this.FISH_BOBBER_3D.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, -1);

            matrixStack.pop();
            float h = playerEntity.getHandSwingProgress(g);
            float j = MathHelper.sin(MathHelper.sqrt(h) * (float) Math.PI);

            Item new_item_check = ((BobberMixInterface)fishingBobberEntity).frontiers_1_21x$getParentItemStack().getItem();
            Vec3d vec3d = this.getHandPosItemCheck(playerEntity, j, g, new_item_check);

            Vec3d vec3d2 = fishingBobberEntity.getLerpedPos(g).add(0.0, line_correction_float, 0.0);
            float k = (float)(vec3d.x - vec3d2.x);
            float l = (float)(vec3d.y - vec3d2.y);
            float m = (float)(vec3d.z - vec3d2.z);
            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getLineStrip());
            MatrixStack.Entry entry2 = matrixStack.peek();

            int newLineColor = ((BobberMixInterface)fishingBobberEntity).frontiers_1_21x$getLineColor();
            for (int o = 0; o <= 16; o++) {
                renderFishingLineColor(k, l, m, vertexConsumer2, entry2, percentage(o, 16), percentage(o + 1, 16), newLineColor);
            }

            matrixStack.pop();
            super.render(fishingBobberEntity, f, g, matrixStack, vertexConsumerProvider, i);
            ci.cancel();
        }
    }

    /** Redirects the fishing line code to use the Frontiers version. This is an instance where redirect is more or less necessary. */
    @Redirect(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V")
    )
    private void new_matrices(float x, float y, float z, VertexConsumer buffer, MatrixStack.Entry matrices, float segmentStart, float segmentEnd, @Local(argsOnly = true) FishingBobberEntity fishingBobberEntity)
    {
        int newLineColor = ((BobberMixInterface)fishingBobberEntity).frontiers_1_21x$getLineColor();
        renderFishingLineColor(x, y, z, buffer, matrices, segmentStart, segmentEnd, newLineColor);
    }

    /** Redirects the hand pos check. May rewrite. */
    @Redirect(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;getHandPos(Lnet/minecraft/entity/player/PlayerEntity;FF)Lnet/minecraft/util/math/Vec3d;")
    )
    private Vec3d newHandPosCheck(FishingBobberEntityRenderer instance, PlayerEntity player, float f, float tickDelta, @Local(argsOnly = true) FishingBobberEntity fishingBobberEntity)
    {
        Item new_item_check = ((BobberMixInterface)fishingBobberEntity).frontiers_1_21x$getParentItemStack().getItem();
        return getHandPosItemCheck(player, f, tickDelta, new_item_check);
    }

    /** Gets the texture for the fishing bobber. */
    @Inject(method = "getTexture(Lnet/minecraft/entity/projectile/FishingBobberEntity;)Lnet/minecraft/util/Identifier;", at = @At(value = "RETURN"), cancellable = true)
    private void newTex(FishingBobberEntity fishingBobberEntity, CallbackInfoReturnable<Identifier> cir)
    {
        int level = ((BobberMixInterface)fishingBobberEntity).frontiers_1_21x$getBobberLevel();
        switch (level)
        {
            case 0:
            {
                if (Frontiers.CONFIG.do3DFishBobbers()) cir.setReturnValue(TEXTURE_3D);
                else cir.setReturnValue(TEXTURE);
            }
            case 1:
            {
                if (Frontiers.CONFIG.do3DFishBobbers()) cir.setReturnValue(TEXTURE_COBALT_3D);
                else cir.setReturnValue(TEXTURE_COBALT);
            }
            default:
            {
                if (Frontiers.CONFIG.do3DFishBobbers()) cir.setReturnValue(TEXTURE_3D);
                else cir.setReturnValue(cir.getReturnValue());
            }
        }
    }

    /** Changes the render layer for the fishing bobber */
    @ModifyVariable(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer render_new_layer(VertexConsumer value, @Local FishingBobberEntity fishingBobberEntity, @Local VertexConsumerProvider vertexConsumerProvider)
    {
        RenderLayer returner = frontiersGetLayer(fishingBobberEntity);
        return vertexConsumerProvider.getBuffer(returner);
    }
}
