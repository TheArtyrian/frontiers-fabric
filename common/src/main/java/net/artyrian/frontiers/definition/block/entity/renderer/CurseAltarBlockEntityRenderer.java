package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.CurseAltarBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.phys.AABB;
import net.vertisoft.vectorlib.agnostic.neoforge_stitching.VectorIBlockIntf;

public class CurseAltarBlockEntityRenderer implements BlockEntityRenderer<CurseAltarBlockEntity>, VectorIBlockIntf<CurseAltarBlockEntity>
{
    private static final int INV_VISIBLE = FastColor.ARGB32.color(Mth.floor(0.0F), -1);

    static final ResourceLocation SIGIL_TEXTURE_BOTTOM = Frontiers.id("textures/entity/curse_altar_sigil_bottom.png");
    static final ResourceLocation SIGIL_TEXTURE_TOP = Frontiers.id("textures/entity/curse_altar_sigil_top.png");
    static final ResourceLocation SIGIL_TEXTURE_BOTTOM_GLOW = Frontiers.id("textures/entity/curse_altar_sigil_glow_bottom.png");
    static final ResourceLocation SIGIL_TEXTURE_TOP_GLOW = Frontiers.id("textures/entity/curse_altar_sigil_glow_top.png");

    static final ResourceLocation TABLET_TEXTURE = Frontiers.id("textures/entity/curse_altar_tablet.png");
    static final ResourceLocation GLOW_TEXTURE = Frontiers.id("textures/entity/curse_altar_tablet_glow.png");

    private final ModelPart tablet;
    private final ModelPart overlay;

    public CurseAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.tablet = getTexModel().bakeRoot();
        this.overlay = this.tablet.getChild("tabmain").getChild("overlay");
    }

    @Override
    // Made with Blockbench 4.12.4 and hand written into java because lol lmao
    // https://www.youtube.com/watch?v=n_n8toR4Bxo - artyrian
    public void render(CurseAltarBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        matrices.pushPose();
        matrices.translate(0.5F, 0.8F, 0.5F);

        boolean tabletPresent = entity.getCharges() > 0;

        int glowColor = FastColor.ARGB32.lerp(tabletPresent ? entity.tabletGlow : 0, INV_VISIBLE, -1);
        int glowColorInv = FastColor.ARGB32.lerp(tabletPresent ? entity.tabletGlow : 0, -1, INV_VISIBLE);

        float sigilH = entity.sigilRotation - entity.lastSigilRotation;
        float sigilDelta = entity.lastSigilRotation + sigilH * tickDelta;

        matrices.mulPose(Axis.XP.rotationDegrees(-90));
        matrices.mulPose(Axis.ZP.rotationDegrees(sigilDelta));
        matrices.scale(1.5F, 1.5F, 1.5F);

        PoseStack.Pose pose = matrices.last();

        if (entity.tabletGlow > 0.0F)
        {
            VertexConsumer sigilTexGlow1 = vertexConsumers.getBuffer(RenderType.entityTranslucent(SIGIL_TEXTURE_BOTTOM_GLOW));
            vertex(sigilTexGlow1, pose, 15728880, 0.0F, 0, 0, 1, glowColor);
            vertex(sigilTexGlow1, pose, 15728880, 1.0F, 0, 1, 1, glowColor);
            vertex(sigilTexGlow1, pose, 15728880, 1.0F, 1, 1, 0, glowColor);
            vertex(sigilTexGlow1, pose, 15728880, 0.0F, 1, 0, 0, glowColor);
        }

        VertexConsumer sigilTex1 = vertexConsumers.getBuffer(RenderType.entityTranslucent(SIGIL_TEXTURE_BOTTOM));
        vertex(sigilTex1, pose, 15728880, 0.0F, 0, 0, 1, glowColorInv);
        vertex(sigilTex1, pose, 15728880, 1.0F, 0, 1, 1, glowColorInv);
        vertex(sigilTex1, pose, 15728880, 1.0F, 1, 1, 0, glowColorInv);
        vertex(sigilTex1, pose, 15728880, 0.0F, 1, 0, 0, glowColorInv);

        matrices.mulPose(Axis.ZN.rotationDegrees(sigilDelta));
        matrices.mulPose(Axis.ZN.rotationDegrees(sigilDelta));
        matrices.translate(0.0F, 0.0F, 0.01F);

        if (entity.tabletGlow > 0.0F)
        {
            VertexConsumer sigilTexGlow2 = vertexConsumers.getBuffer(RenderType.entityTranslucent(SIGIL_TEXTURE_TOP_GLOW));
            vertex(sigilTexGlow2, pose, 15728880, 0.0F, 0, 0, 1, glowColor);
            vertex(sigilTexGlow2, pose, 15728880, 1.0F, 0, 1, 1, glowColor);
            vertex(sigilTexGlow2, pose, 15728880, 1.0F, 1, 1, 0, glowColor);
            vertex(sigilTexGlow2, pose, 15728880, 0.0F, 1, 0, 0, glowColor);
        }

        VertexConsumer sigilTex2 = vertexConsumers.getBuffer(RenderType.entityTranslucent(SIGIL_TEXTURE_TOP));
        vertex(sigilTex2, pose, 15728880, 0.0F, 0, 0, 1, glowColorInv);
        vertex(sigilTex2, pose, 15728880, 1.0F, 0, 1, 1, glowColorInv);
        vertex(sigilTex2, pose, 15728880, 1.0F, 1, 1, 0, glowColorInv);
        vertex(sigilTex2, pose, 15728880, 0.0F, 1, 0, 0, glowColorInv);

        matrices.popPose();

        // TODO: Replace `true` with chargesPresent > 0 or watevr
        if (tabletPresent)
        {
            matrices.pushPose();

            matrices.translate(0.5F, 0.76F, 0.5F);

            float g = (float)entity.ticks + tickDelta;
            matrices.translate(0.0F, 0.1F + Mth.sin(g * 0.1F) * 0.01F, 0.0F);
            float h = entity.tabletRotation - entity.lastTabletRotation;

            while (h >= (float) Math.PI) { h -= (float) (Math.PI * 2); }
            while (h < (float) -Math.PI) { h += (float) (Math.PI * 2); }

            float k = entity.lastTabletRotation + h * tickDelta;
            matrices.mulPose(Axis.YP.rotation(-k));
            matrices.mulPose(Axis.ZP.rotationDegrees(80.0F));

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutout(TABLET_TEXTURE));
            this.tablet.render(matrices, vertexConsumer, light, overlay, -1);
            vertexConsumer = vertexConsumers.getBuffer(RenderType.entityTranslucent(GLOW_TEXTURE));
            this.tablet.render(matrices, vertexConsumer, 255, overlay, glowColor);

            matrices.popPose();
        }
    }

    public static LayerDefinition getTexModel()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition tabmain = modelPartData.addOrReplaceChild("tabmain", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition cube = tabmain
                .addOrReplaceChild("cube",
                        CubeListBuilder.create()
                                .texOffs(0, 0)
                                .addBox(-4.0F, -3.0F, -6.0F, 8.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)),
                        PartPose.rotation(0.0F, (float) (Math.PI / 2), 1.5F));
        PartDefinition overlay = tabmain
                .addOrReplaceChild("overlay",
                        CubeListBuilder.create()
                                .texOffs(0, 18)
                                .addBox(-4.0F, -3.0F, -6.0F, 8.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)),
                        PartPose.rotation(0.0F, (float) (Math.PI / 2), 1.5F));
        return LayerDefinition.create(modelData, 48, 48);
    }

    @Override
    public AABB getVectorLibIntfRenderBox(CurseAltarBlockEntity blockEntity)
    {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.5, pos.getZ() + 1.0);
    }

    private static void vertex(VertexConsumer buffer, PoseStack.Pose matrix, int light, float x, int y, int u, int v, int color)
    {
        buffer.addVertex(matrix, x - 0.5F, (float)y - 0.5F, 0.0F)
                .setColor(color)
                .setUv((float)u, (float)v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(matrix, 0.0F, 0.0F, 1.0F);
    }
}
