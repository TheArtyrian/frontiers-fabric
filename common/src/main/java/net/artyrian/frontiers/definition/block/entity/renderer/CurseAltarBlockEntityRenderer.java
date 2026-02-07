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
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.phys.AABB;
import net.vertisoft.vectorlib.agnostic.neoforge_stitching.VectorIBlockIntf;

public class CurseAltarBlockEntityRenderer implements BlockEntityRenderer<CurseAltarBlockEntity>, VectorIBlockIntf<CurseAltarBlockEntity>
{
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
        matrices.translate(0.5F, 0.75F, 0.5F);

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
        int colornew = FastColor.ARGB32.lerp(entity.tabletGlow, FastColor.ARGB32.color(Mth.floor(0.0F), -1), -1);
        this.tablet.render(matrices, vertexConsumer, 255, overlay, colornew);

        matrices.popPose();
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
}
