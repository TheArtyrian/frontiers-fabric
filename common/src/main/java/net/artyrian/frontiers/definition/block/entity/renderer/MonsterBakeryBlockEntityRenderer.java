package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SpawnerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class MonsterBakeryBlockEntityRenderer implements BlockEntityRenderer<MonsterBakeryBlockEntity>
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
}
