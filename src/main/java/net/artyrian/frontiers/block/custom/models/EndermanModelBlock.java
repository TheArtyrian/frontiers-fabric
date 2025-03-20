package net.artyrian.frontiers.block.custom.models;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.block.entity.entity_model.EndermanModelBlockEntity;
import net.artyrian.frontiers.block.entity.entity_model.SkeletonModelBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EndermanModelBlock extends BlockWithEntity implements BlockEntityProvider
{
    public static final IntProperty ROTATION = Properties.ROTATION;
    public static final MapCodec<EndermanModelBlock> CODEC = EndermanModelBlock.createCodec(EndermanModelBlock::new);

    private static final VoxelShape COLLIDER = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    private static final VoxelShape VOXEL_SHAPE = VoxelShapes.combineAndSimplify(
            COLLIDER, Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 16.0, 12.0), BooleanBiFunction.OR);

    public EndermanModelBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ROTATION, 0));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient ? validateTicker(type, ModBlockEntities.ENDERMAN_MODEL_BLOCKENTITY, EndermanModelBlockEntity::tick) : null;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return VOXEL_SHAPE; }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return COLLIDER; }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec()
    {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new EndermanModelBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState().with(ROTATION, RotationPropertyHelper.fromYaw(ctx.getPlayerYaw() + 180.0F));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) { return state.with(ROTATION, rotation.rotate(state.get(ROTATION), 16));}
    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) { return state.with(ROTATION, mirror.mirror(state.get(ROTATION), 16));}
}
