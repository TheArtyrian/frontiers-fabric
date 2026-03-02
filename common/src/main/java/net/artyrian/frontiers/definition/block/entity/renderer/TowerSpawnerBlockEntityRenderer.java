package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.TowerSpawnerBlockEntity;
import net.artyrian.frontiers.definition.block.entity.data.TowerSpawner;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;

public class TowerSpawnerBlockEntityRenderer implements BlockEntityRenderer<TowerSpawnerBlockEntity>
{
    private final EntityRenderDispatcher entityRenderer;

    public TowerSpawnerBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.entityRenderer = context.getEntityRenderer();
    }

    @Override
    public void render(TowerSpawnerBlockEntity spawner, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packLight, int packOverlay)
    {
        Level level = spawner.getLevel();
        if (level != null)
        {
            TowerSpawner data = spawner.getSpawner();
            Entity entity = data.getOrCreateDisplayable(level, spawner.getBlockPos());
            if (entity != null && !data.isDefeated())
            {
                render(partialTick, poseStack, multiBufferSource, packLight, packOverlay, entity, this.entityRenderer, data.getRotLast(), data.getRot(), data.getRise());
            }
        }
    }

    public static void render(
            float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, Entity entity, EntityRenderDispatcher entityRenderer, double oSpin, double spin, double rise
    )
    {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.0F, 0.5F);

        float f = 0.53125F;
        float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());

        if ((double)f1 > 1.0) f /= f1;

        poseStack.translate(0.0F, 0.4F + rise, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees((float) Mth.lerp(partialTick, oSpin, spin) * 10.0F));
        poseStack.translate(0.0F, -0.2F, 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(-30.0F));
        poseStack.scale(f, f, f);
        entityRenderer.render(entity, 0.0, 0.0, 0.0, 0.0F, partialTick, poseStack, buffer, packedLight);
        poseStack.popPose();
    }
}
