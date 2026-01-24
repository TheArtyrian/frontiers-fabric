package net.artyrian.frontiers.mixin.entity.end_crystal;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_intf.EndCrystalIntf;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EndCrystalRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.client.renderer.entity.EndCrystalRenderer.getY;

@Debug(export = true)
@Mixin(EndCrystalRenderer.class)
public abstract class EndCrystalRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private static RenderType RENDER_TYPE;

    @Unique private static final ResourceLocation TEXTURE_CRACKED1 = Frontiers.id("textures/entity/end_crystal/end_crystal_damaged1.png");
    @Unique private static final RenderType LAYER_CRACKED1 = RenderType.entityCutoutNoCull(TEXTURE_CRACKED1);
    @Unique private static final ResourceLocation TEXTURE_CRACKED2 = Frontiers.id("textures/entity/end_crystal/end_crystal_damaged2.png");
    @Unique private static final RenderType LAYER_CRACKED2 = RenderType.entityCutoutNoCull(TEXTURE_CRACKED2);
    @Unique private static final ResourceLocation TEXTURE_FRIENDLY = Frontiers.id("textures/entity/end_crystal/friendly_end_crystal.png");
    @Unique private static final RenderType LAYER_FRIENDLY = RenderType.entityCutoutNoCull(TEXTURE_FRIENDLY);

    @Unique private static final ResourceLocation CRYSTAL_BEAM_TEXTURE_FRNT = ResourceLocation.withDefaultNamespace("textures/entity/end_crystal/end_crystal_beam.png");
    @Unique private static final RenderType CRYSTAL_BEAM_LAYER_FRNT = RenderType.entitySmoothCutout(CRYSTAL_BEAM_TEXTURE_FRNT);

    @Unique private static final float HF_SQRT = (float)(Math.sqrt(3.0) / 2.0);

    @Unique private boolean frontiers$CanProjectFriendlyBeams(Level world, BlockPos gotoPos, BlockPos thisPos)
    {
        return world != null && gotoPos != thisPos && world.getBlockState(gotoPos).is(Blocks.ENCHANTING_TABLE) && thisPos.closerThan(gotoPos, 6);
    }

    @Override
    protected int getBlockLightLevel(Entity entity, BlockPos blockPos)
    {
        int hit_amnt = ((EndCrystalIntf)entity).frontiers_1_21x$getHitsTaken();
        boolean is_friendly = ((EndCrystalIntf)entity).frontiers_1_21x$isFriendly();
        boolean showing_base = ((EndCrystal)entity).showsBottom();

        int block_level = entity.level().getBrightness(LightLayer.BLOCK, blockPos);

        if ((is_friendly && !showing_base) || hit_amnt == 1) return Math.max(8, block_level);
        else if (hit_amnt == 2) return Math.max(15, block_level);
        else return block_level;
    }

    @Unique
    private static void frontiers$RenderFriendlyBeam(
            float dx, float dy, float dz, float tickDelta, int age, PoseStack matrices, MultiBufferSource vertexConsumers, int light)
    {
        float f = Mth.sqrt(dx * dx + dz * dz);
        float g = Mth.sqrt(dx * dx + dy * dy + dz * dz);
        matrices.pushPose();
        matrices.translate(0.0F, 0.75F, 0.0F);
        matrices.mulPose(Axis.YP.rotation((float)(-Math.atan2(dz, dx)) - (float) (Math.PI / 2)));
        matrices.mulPose(Axis.XP.rotation((float)(-Math.atan2(f, dy)) - (float) (Math.PI / 2)));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(CRYSTAL_BEAM_LAYER_FRNT);
        float h = 0.0F - ((float)age + tickDelta) * -0.01F;
        float i = g / 32.0F - ((float)age + tickDelta) * -0.01F;
        int j = 8;
        float k = 0.0F;
        float l = 0.75F;
        float m = 0.0F;
        PoseStack.Pose entry = matrices.last();

        for (int n = 1; n <= 8; n++)
        {
            float o = Mth.sin((float)n * (float) (Math.PI * 2) / 8.0F) * 0.75F;
            float p = Mth.cos((float)n * (float) (Math.PI * 2) / 8.0F) * 0.75F;
            float q = (float)n / 8.0F;
            vertexConsumer.addVertex(entry, k * 0.2F, l * 0.2F, 0.0F)
                    .setColor(CommonColors.WHITE)
                    .setUv(m, h)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(light)
                    .setNormal(entry, 0.0F, -1.0F, 0.0F);
            vertexConsumer.addVertex(entry, k, l, g).setColor(0x62E4FF).setUv(m, i).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(entry, 0.0F, -1.0F, 0.0F);
            vertexConsumer.addVertex(entry, o, p, g).setColor(0x62E4FF).setUv(q, i).setOverlay(OverlayTexture.NO_OVERLAY).setLight(light).setNormal(entry, 0.0F, -1.0F, 0.0F);
            vertexConsumer.addVertex(entry, o * 0.2F, p * 0.2F, 0.0F)
                    .setColor(CommonColors.WHITE)
                    .setUv(q, h)
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setLight(light)
                    .setNormal(entry, 0.0F, -1.0F, 0.0F);
            k = o;
            l = p;
            m = q;
        }

        matrices.popPose();
    }

    // Renders rays. Totally not based on dragon code.
    @Unique private static void renderRays(PoseStack matrices, float amnt, float alpha_sub, float beam_len, int rays, VertexConsumer vCon)
    {
        matrices.pushPose();

        int colhelp = FastColor.ARGB32.colorFromFloat(1.0F - alpha_sub, 1.0F, 1.0F, 1.0F);
        // Note; based on dragon code, def color is 16711935.
        int color = 16711935;
        RandomSource random = RandomSource.create(432L);
        Quaternionf quatro = new Quaternionf();
        float spinadd = Math.min(amnt > 0.8F ? (amnt - 0.8F) / 0.2F : 0.0F, 1.0F);
        Vector3f v3f_1 = new Vector3f();
        Vector3f v3f_2 = new Vector3f();
        Vector3f v3f_3 = new Vector3f();
        Vector3f v3f_4 = new Vector3f();

        for (int x = 0; x < rays; x++)
        {
            quatro.rotationXYZ(random.nextFloat() * (float) (Math.PI * 2), random.nextFloat() * (float) (Math.PI * 2), random.nextFloat() * (float) (Math.PI * 2))
                    .rotateXYZ(
                            random.nextFloat() * (float) (Math.PI * 2),
                            random.nextFloat() * (float) (Math.PI * 2),
                            random.nextFloat() * (float) (Math.PI * 2) + amnt * (float) (Math.PI / 2)
                    );
            matrices.mulPose(quatro);

            float g = random.nextFloat() * 10.0F + 5.0F + (beam_len * 10.0F);
            float h = random.nextFloat() * 2.0F + 1.0F + (beam_len * 2.0F);
            v3f_2.set(-HF_SQRT * h, g, -0.5F * h);
            v3f_3.set(HF_SQRT * h, g, -0.5F * h);
            v3f_4.set(0.0F, g, h);

            PoseStack.Pose entry = matrices.last();
            vCon.addVertex(entry, v3f_1).setColor(colhelp);
            vCon.addVertex(entry, v3f_2).setColor(color);
            vCon.addVertex(entry, v3f_3).setColor(color);
            vCon.addVertex(entry, v3f_1).setColor(colhelp);
            vCon.addVertex(entry, v3f_3).setColor(color);
            vCon.addVertex(entry, v3f_3).setColor(color);
            vCon.addVertex(entry, v3f_1).setColor(colhelp);
            vCon.addVertex(entry, v3f_3).setColor(color);
            vCon.addVertex(entry, v3f_2).setColor(color);
        }

        matrices.popPose();
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;)Lnet/minecraft/resources/ResourceLocation;", at = @At("RETURN"), cancellable = true)
    public void getTexture(EndCrystal endCrystalEntity, CallbackInfoReturnable<ResourceLocation> cir)
    {
        int hit_amnt = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getHitsTaken();
        boolean is_friendly = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$isFriendly();

        if (is_friendly) cir.setReturnValue(TEXTURE_FRIENDLY);
        else if (hit_amnt == 1) cir.setReturnValue(TEXTURE_CRACKED1);
        else if (hit_amnt == 2) cir.setReturnValue(TEXTURE_CRACKED2);
    }
    @Inject(
            method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "TAIL")
    )
    private void doRays(EndCrystal endCrystalEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, CallbackInfo ci)
    {
        int hit_amnt = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getHitsTaken();
        boolean is_friendly = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$isFriendly();

        if (is_friendly)
        {
            BlockPos gotoPos = ((EndCrystalIntf)endCrystalEntity).frontiers$getGoodBeamPos();
            BlockPos thisPos = endCrystalEntity.blockPosition();
            Level world = endCrystalEntity.level();

            // youre witnessing a future day zero vulnerability-causing dev hard at work here
            if (this.frontiers$CanProjectFriendlyBeams(world, gotoPos, thisPos))
            {
                float ssX = (float)gotoPos.getX();
                float ssY = (float)gotoPos.getY() - 0.25F;
                float ssZ = (float)gotoPos.getZ();
                float bX = (float)((double)ssX - thisPos.getX());
                float bY = (float)((double)ssY - thisPos.getY());
                float bZ = (float)((double)ssZ - thisPos.getZ());

                matrixStack.pushPose();
                matrixStack.translate(bX, bY, bZ);
                frontiers$RenderFriendlyBeam(-bX, -bY + (EndCrystalRenderer.getY(endCrystalEntity, g) + 1.25F), -bZ, g, endCrystalEntity.tickCount, matrixStack, vertexConsumerProvider, i);
                matrixStack.popPose();
            }
        }
        else if (hit_amnt > 0)
        {
            int crack_spin = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getCrackSpin();
            float crack_float = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getCrackFloat();
            float beamlen = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getBeamLen();
            int rays = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getRays();

            float amnt_mat = ((float)crack_spin + g) / 20.0f;
            float offsety = getY(endCrystalEntity, g);

            matrixStack.pushPose();
            matrixStack.translate(0.0F, 1.5F + offsety / 2.0F, 0.0F);
            renderRays(matrixStack, amnt_mat, crack_float, beamlen, rays, vertexConsumerProvider.getBuffer(RenderType.dragonRays()));
            renderRays(matrixStack, amnt_mat, crack_float, beamlen, rays, vertexConsumerProvider.getBuffer(RenderType.dragonRaysDepth()));
            matrixStack.popPose();
        }
    }

    @ModifyVariable(method = "render(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer render_new_layer(VertexConsumer value, @Local EndCrystal endCrystalEntity, @Local MultiBufferSource vertexConsumerProvider)
    {
        int hit_amnt = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$getHitsTaken();
        boolean is_friendly = ((EndCrystalIntf)endCrystalEntity).frontiers_1_21x$isFriendly();

        if (is_friendly) return vertexConsumerProvider.getBuffer(LAYER_FRIENDLY);
        else if (hit_amnt == 1) return vertexConsumerProvider.getBuffer(LAYER_CRACKED1);
        else if (hit_amnt == 2) return vertexConsumerProvider.getBuffer(LAYER_CRACKED2);
        else return vertexConsumerProvider.getBuffer(RENDER_TYPE);
    }

    @ModifyReturnValue(method = "shouldRender(Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;Lnet/minecraft/client/renderer/culling/Frustum;DDD)Z", at = @At("RETURN"))
    private boolean shouldAlsoRenderWithBlockRays(boolean original, @Local(argsOnly = true) EndCrystal endCrystalEntity)
    {
        BlockPos gotoPos = ((EndCrystalIntf)endCrystalEntity).frontiers$getGoodBeamPos();
        BlockPos thisPos = endCrystalEntity.blockPosition();
        Level world = endCrystalEntity.level();

        return original || this.frontiers$CanProjectFriendlyBeams(world, gotoPos, thisPos);
    }
}
