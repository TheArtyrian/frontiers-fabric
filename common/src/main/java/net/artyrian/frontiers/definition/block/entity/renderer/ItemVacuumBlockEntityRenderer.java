package net.artyrian.frontiers.definition.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.artyrian.frontiers.definition.block.entity.ItemVacuumBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.VaultRenderer;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemVacuumBlockEntityRenderer implements BlockEntityRenderer<ItemVacuumBlockEntity>
{
    private final ItemRenderer itemRenderer;
    private final RandomSource random = RandomSource.create();

    public ItemVacuumBlockEntityRenderer(BlockEntityRendererProvider.Context context)
    {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ItemVacuumBlockEntity entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
    {
        ItemStack stack = entity.getTheItem();
        Level world = entity.getLevel();
        if (!stack.isEmpty() && world != null)
        {
            ItemStack stackWithOne = stack.copyWithCount(1);
            this.random.setSeed(ItemEntityRenderer.getSeedForItemStack(stack));
            VaultRenderer.renderItemInside(
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
