package net.artyrian.frontiers.mixin.entity.fishing;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(FishingHookRenderer.class)
public abstract class FishingRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private static ResourceLocation TEXTURE_LOCATION;
    @Shadow @Final private static RenderType RENDER_TYPE;
    @Shadow private static float fraction(int value, int max)
    {
        return 0.0f;
    }
    @Shadow protected abstract Vec3 getPlayerHandPos(Player player, float f, float tickDelta);

    @Unique private static final float line_correction_float = 0.10F;
    @Unique private final ModelPart FISH_BOBBER_3D = frontiersCreateBobberMeta();
    @Unique private static final ResourceLocation TEXTURE_3D = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/fishhook/3d_fishing_hook.png");
    @Unique private static final ResourceLocation TEXTURE_COBALT = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/fishhook/cobalt_fishing_hook.png");
    @Unique private static final ResourceLocation TEXTURE_COBALT_3D = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/fishhook/3d_cobalt_fishing_hook.png");
    @Unique private static final RenderType LAYER_3D = RenderType.entityCutout(TEXTURE_3D);
    @Unique private static final RenderType LAYER_COBALT = RenderType.entityCutout(TEXTURE_COBALT);
    @Unique private static final RenderType LAYER_COBALT_3D = RenderType.entityCutout(TEXTURE_COBALT_3D);

    /** A redo of the vanilla fishing line rendering code - renders the fishing line in a unique color. */
    @Unique
    private static void renderFishingLineColor(float x, float y, float z, VertexConsumer buffer, PoseStack.Pose matrices, float segmentStart, float segmentEnd, int line_color)
    {
        float adder = (Frontiers.CONFIG.do3DFishBobbers() && Minecraft.useFancyGraphics()) ? line_correction_float : 0.25F;

        float f = x * segmentStart;
        float g = y * (segmentStart * segmentStart + segmentStart) * 0.5F + adder;
        float h = z * segmentStart;
        float i = x * segmentEnd - f;
        float j = y * (segmentEnd * segmentEnd + segmentEnd) * 0.5F + adder - g;

        float k = z * segmentEnd - h;
        float l = Mth.sqrt(i * i + j * j + k * k);
        i /= l;
        j /= l;
        k /= l;

        buffer.addVertex(matrices, f, g, h).setColor(line_color).setNormal(matrices, i, j, k);
    }
    /** A wrap for hand checking code in fishing rods - checks if the player is holding a given item. */
    @Unique
    private Vec3 getHandPosItemCheck(Player player, float f, float tickDelta, Item checkItem)
    {
        int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
        ItemStack itemStack = player.getMainHandItem();
        if (!itemStack.is(checkItem))
        {
            i = -i;
        }
        if (this.dispatcher.options.getCameraType().isFirstPerson() && player == Minecraft.getInstance().player) {
            double m = 960.0 / (double)this.dispatcher.options.fov().get().intValue();
            Vec3 vec3d = this.dispatcher.camera.getNearPlane().getPointOnPlane((float)i * 0.525F, -0.1F).scale(m).yRot(f * 0.5F).xRot(-f * 0.7F);
            return player.getEyePosition(tickDelta).add(vec3d);
        } else {
            float g = Mth.lerp(tickDelta, player.yBodyRotO, player.yBodyRot) * (float) (Math.PI / 180.0);
            double d = (double)Mth.sin(g);
            double e = (double)Mth.cos(g);
            float h = player.getScale();
            double j = (double)i * 0.35 * (double)h;
            double k = 0.8 * (double)h;
            float l = player.isCrouching() ? -0.1875F : 0.0F;
            return player.getEyePosition(tickDelta).add(-e * j - d * k, (double)l - 0.45 * (double)h, -d * j + e * k);
        }
    }
    /** Gets the render layer for the fishing bobber. */
    @Unique
    private RenderType frontiersGetLayer(FishingHook fishingBobberEntity)
    {
        int level = ((BobberIntf)fishingBobberEntity).frontiers_1_21x$getBobberLevel();
        boolean bobber3D = Frontiers.CONFIG.do3DFishBobbers() && Minecraft.useFancyGraphics();
        return switch (level)
        {
            case 0 -> (bobber3D) ? LAYER_3D : RENDER_TYPE;
            case 1 -> (bobber3D) ? LAYER_COBALT_3D : LAYER_COBALT;
            default -> (bobber3D) ? LAYER_3D : RENDER_TYPE; /*/noinspection DuplicateBranchesInSwitch/*/
        };
    }
    /** Creates the fishing bobber 3D model. Recreated from the Bedrock model in Blockbench, is 1:1 with the original. */
    @Unique
    private static ModelPart frontiersCreateBobberMeta()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition fishmain = modelPartData.addOrReplaceChild("fishmain", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 3).addBox(0.0F, 1.5F, -2.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(6, 3).addBox(0.0F, -4.5F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube1 = fishmain.addOrReplaceChild("cube1", CubeListBuilder.create().texOffs(6, 3).addBox(0.0F, -3.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPart finalBox = LayerDefinition.create(modelData, 16, 16).bakeRoot();
        float halfpi = ((float) Math.PI / 2);
        finalBox.offsetRotation(new Vector3f(0, 0, (float) Math.PI));
        return finalBox;
    }

    /** Handles rendering the bobber in 3D.*/
    @Inject(
            method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void redirectTo3DRenderIfProper(
            FishingHook fishingBobberEntity,
            float f, float g,
            PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider,
            int i,
            CallbackInfo ci)
    {
        if (Frontiers.CONFIG.do3DFishBobbers() && Minecraft.useFancyGraphics())
        {
            Player playerEntity = fishingBobberEntity.getPlayerOwner();
            if (playerEntity != null)
            {
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(frontiersGetLayer(fishingBobberEntity));
                this.FISH_BOBBER_3D.render(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, -1);

                matrixStack.pushPose();

                float h = playerEntity.getAttackAnim(g);
                float j = Mth.sin(Mth.sqrt(h) * (float) Math.PI);

                Item new_item_check = ((BobberIntf)fishingBobberEntity).frontiers_1_21x$getParentItemStack().getItem();
                Vec3 vec3d = this.getHandPosItemCheck(playerEntity, j, g, new_item_check);

                Vec3 vec3d2 = fishingBobberEntity.getPosition(g).add(0.0, line_correction_float, 0.0);
                float k = (float)(vec3d.x - vec3d2.x);
                float l = (float)(vec3d.y - vec3d2.y);
                float m = (float)(vec3d.z - vec3d2.z);

                VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderType.lineStrip());
                PoseStack.Pose entry2 = matrixStack.last();
                int newLineColor = ((BobberIntf)fishingBobberEntity).frontiers_1_21x$getLineColor();

                for (int o = 0; o <= 16; o++)
                {
                    renderFishingLineColor(k, l, m, vertexConsumer2, entry2, fraction(o, 16), fraction(o + 1, 16), newLineColor);
                }

                matrixStack.popPose();

                // This is a fix matching Iris shaders' line connect fix
                vertexConsumer2.addVertex(0, 0, 0).setColor(newLineColor).setNormal(0, 0, 0);

                super.render(fishingBobberEntity, f, g, matrixStack, vertexConsumerProvider, i);
            }
            ci.cancel();
        }
    }

    /** Redirects the fishing line code to use the Frontiers version. This is an instance where redirect is more or less necessary. */
    @Redirect(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;renderFishingLine(FFFLnet/minecraft/client/render/VertexConsumer;Lnet/minecraft/client/util/math/MatrixStack$Entry;FF)V")
    )
    private void new_matrices(float x, float y, float z, VertexConsumer buffer, PoseStack.Pose matrices, float segmentStart, float segmentEnd, @Local(argsOnly = true) FishingHook fishingBobberEntity)
    {
        int newLineColor = ((BobberIntf)fishingBobberEntity).frontiers_1_21x$getLineColor();
        renderFishingLineColor(x, y, z, buffer, matrices, segmentStart, segmentEnd, newLineColor);
    }

    /** Redirects the hand pos check. May rewrite. */
    @Redirect(
            method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/FishingBobberEntityRenderer;getHandPos(Lnet/minecraft/entity/player/PlayerEntity;FF)Lnet/minecraft/util/math/Vec3d;")
    )
    private Vec3 newHandPosCheck(FishingHookRenderer instance, Player player, float f, float tickDelta, @Local(argsOnly = true) FishingHook fishingBobberEntity)
    {
        Item new_item_check = ((BobberIntf)fishingBobberEntity).frontiers_1_21x$getParentItemStack().getItem();
        return getHandPosItemCheck(player, f, tickDelta, new_item_check);
    }

    /** Gets the texture for the fishing bobber. */
    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/projectile/FishingHook;)Lnet/minecraft/resources/ResourceLocation;", at = @At(value = "RETURN"), cancellable = true)
    private void newTex(FishingHook fishingBobberEntity, CallbackInfoReturnable<ResourceLocation> cir)
    {
        int level = ((BobberIntf)fishingBobberEntity).frontiers_1_21x$getBobberLevel();
        switch (level)
        {
            case 0:
            {
                if (Frontiers.CONFIG.do3DFishBobbers() && Minecraft.useFancyGraphics()) cir.setReturnValue(TEXTURE_3D);
                else cir.setReturnValue(TEXTURE_LOCATION);
            }
            case 1:
            {
                if (Frontiers.CONFIG.do3DFishBobbers() && Minecraft.useFancyGraphics()) cir.setReturnValue(TEXTURE_COBALT_3D);
                else cir.setReturnValue(TEXTURE_COBALT);
            }
            default:
            {
                if (Frontiers.CONFIG.do3DFishBobbers() && Minecraft.useFancyGraphics()) cir.setReturnValue(TEXTURE_3D);
                else cir.setReturnValue(cir.getReturnValue());
            }
        }
    }

    /** Changes the render layer for the fishing bobber */
    @ModifyVariable(method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer render_new_layer(VertexConsumer value, @Local FishingHook fishingBobberEntity, @Local MultiBufferSource vertexConsumerProvider)
    {
        RenderType returner = frontiersGetLayer(fishingBobberEntity);
        return vertexConsumerProvider.getBuffer(returner);
    }
}
