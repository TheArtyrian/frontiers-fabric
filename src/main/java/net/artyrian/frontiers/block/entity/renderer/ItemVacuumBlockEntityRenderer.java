package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.block.entity.EnchantingMagnetBlockEntity;
import net.artyrian.frontiers.block.entity.ItemVacuumBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.VaultBlockEntityRenderer;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ItemVacuumBlockEntityRenderer implements BlockEntityRenderer<ItemVacuumBlockEntity>
{
    private final ItemRenderer itemRenderer;
    private final Random random = Random.create();

    public ItemVacuumBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ItemVacuumBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        ItemStack stack = entity.getStack();
        World world = entity.getWorld();
        if (!stack.isEmpty() && world != null)
        {
            ItemStack stackWithOne = stack.copyWithCount(1);
            this.random.setSeed(ItemEntityRenderer.getSeed(stack));
            VaultBlockEntityRenderer.renderDisplayItem(
                    tickDelta,
                    world,
                    matrices,
                    vertexConsumers,
                    light,
                    stackWithOne,
                    this.itemRenderer,
                    (float) entity.getLastRot() * 10,
                    (float) entity.getRot() * 10,
                    this.random
            );
        }
    }

}
