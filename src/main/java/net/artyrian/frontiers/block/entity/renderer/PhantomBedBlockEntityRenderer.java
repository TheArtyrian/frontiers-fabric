package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.PhantomBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BedBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

public class PhantomBedBlockEntityRenderer implements BlockEntityRenderer<PhantomBedBlockEntity>
{
    private final ModelPart bedHead;
    private final ModelPart bedFoot;

    public PhantomBedBlockEntityRenderer(BlockEntityRendererFactory.Context ctx)
    {
        this.bedHead = ctx.getLayerModelPart(EntityModelLayers.BED_HEAD);
        this.bedFoot = ctx.getLayerModelPart(EntityModelLayers.BED_FOOT);
    }

    public static TexturedModelData getHeadTexturedModelData()
    {
        return BedBlockEntityRenderer.getHeadTexturedModelData();
    }

    public static TexturedModelData getFootTexturedModelData()
    {
        return BedBlockEntityRenderer.getFootTexturedModelData();
    }

    public void render(PhantomBedBlockEntity bedBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        SpriteIdentifier spriteIdentifier =
                new SpriteIdentifier(TexturedRenderLayers.BEDS_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/bed/phantom_stitch_bed"));

        World world = bedBlockEntity.getWorld();

        if (world != null)
        {
            BlockState blockState = bedBlockEntity.getCachedState();
            DoubleBlockProperties.PropertySource<? extends BedBlockEntity> propertySource = DoubleBlockProperties.toPropertySource(
                    BlockEntityType.BED,
                    BedBlock::getBedPart,
                    BedBlock::getOppositePartDirection,
                    ChestBlock.FACING,
                    blockState,
                    world,
                    bedBlockEntity.getPos(),
                    (worldx, pos) -> false
            );
            int k = propertySource.apply(new LightmapCoordinatesRetriever<>()).get(i);
            this.renderPart(
                    matrixStack,
                    vertexConsumerProvider,
                    blockState.get(BedBlock.PART) == BedPart.HEAD ? this.bedHead : this.bedFoot,
                    blockState.get(BedBlock.FACING),
                    spriteIdentifier,
                    k,
                    j,
                    false
            );
        }
        else
        {
            this.renderPart(matrixStack, vertexConsumerProvider, this.bedHead, Direction.SOUTH, spriteIdentifier, i, j, false);
            this.renderPart(matrixStack, vertexConsumerProvider, this.bedFoot, Direction.SOUTH, spriteIdentifier, i, j, true);
        }
    }

    private void renderPart(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            ModelPart part,
            Direction direction,
            SpriteIdentifier sprite,
            int light,
            int overlay,
            boolean isFoot
    )
    {
        matrices.push();
        matrices.translate(0.0F, 0.5625F, isFoot ? -1.0F : 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
        matrices.translate(0.5F, 0.5F, 0.5F);
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F + direction.asRotation()));
        matrices.translate(-0.5F, -0.5F, -0.5F);
        VertexConsumer vertexConsumer = sprite.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        part.render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }
}
