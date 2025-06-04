package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.MobSpawnerBlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.World;

public class MonsterBakeryBlockEntityRenderer implements BlockEntityRenderer<MonsterBakeryBlockEntity>
{
    private final EntityRenderDispatcher entityRenderDispatcher;

    public MonsterBakeryBlockEntityRenderer(BlockEntityRendererFactory.Context ctx)
    {
        this.entityRenderDispatcher = ctx.getEntityRenderDispatcher();
    }

    @Override
    public void render(MonsterBakeryBlockEntity spawner, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        World world = spawner.getWorld();
        if (world != null)
        {
            Entity entity = spawner.getRenderedEntity();
            if (entity != null)
            {
                MobSpawnerBlockEntityRenderer.render(tickDelta, matrices, vertexConsumers, light, entity, this.entityRenderDispatcher, spawner.getLastRot(), spawner.getRot());
            }
        }
    }
}
