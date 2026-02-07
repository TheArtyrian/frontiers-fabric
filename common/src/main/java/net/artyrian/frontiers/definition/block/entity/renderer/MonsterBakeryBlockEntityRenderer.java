package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SpawnerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.AABB;
import net.vertisoft.vectorlib.agnostic.neoforge_stitching.VectorIBlockIntf;

public class MonsterBakeryBlockEntityRenderer implements BlockEntityRenderer<MonsterBakeryBlockEntity>, VectorIBlockIntf<MonsterBakeryBlockEntity>
{
    private final EntityRenderDispatcher entityRenderDispatcher;

    public MonsterBakeryBlockEntityRenderer(BlockEntityRendererProvider.Context ctx)
    {
        this.entityRenderDispatcher = ctx.getEntityRenderer();
    }

    @Override
    public void render(MonsterBakeryBlockEntity spawner, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        Level world = spawner.getLevel();
        if (world != null)
        {
            Entity entity = spawner.getRenderedEntity();
            if (entity != null)
            {
                SpawnerRenderer.renderEntityInSpawner(tickDelta, matrices, vertexConsumers, light, entity, this.entityRenderDispatcher, spawner.getLastRot(), spawner.getRot());
            }
        }
    }

    @Override
    public AABB getVectorLibIntfRenderBox(MonsterBakeryBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB((double)pos.getX() - 1.0, (double)pos.getY() - 1.0, (double)pos.getZ() - 1.0, (double)pos.getX() + 2.0, (double)pos.getY() + 2.0, (double)pos.getZ() + 2.0);
    }
}
