package net.artyrian.frontiers.block.custom.models;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

// Thanks to Hecco / Bountiful Fares - Jack O Straw code used as reference point.
// Code: https://github.com/Heccology/Bountiful-Fares/blob/1.21/src/main/java/net/hecco/bountifulfares/block/custom/JackOStrawBlock.java
public class EntityModelBlock extends Block implements Waterloggable
{
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    // Voxel sizes.
    private static final VoxelShape VOXEL_UPPER = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 10.0, 12.0);
    private static final VoxelShape VOXEL_LOWER = VoxelShapes.combineAndSimplify(
            Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 1.0, 15.0),
            Block.createCuboidShape(4.0, 1.0, 4.0, 12.0, 16.0, 12.0),
            BooleanBiFunction.OR
    );

    public EntityModelBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(HALF, DoubleBlockHalf.LOWER)
                .with(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) return VOXEL_UPPER;
        else return VOXEL_LOWER;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        BlockState blockAbove = world.getBlockState(pos.up(1));
        if (blockAbove.isOf(Blocks.AIR) && state.get(HALF) == DoubleBlockHalf.LOWER)
        {
            world.setBlockState(pos.up(1), this.getDefaultState()
                    .with(FACING, state.get(FACING))
                    .with(HALF, DoubleBlockHalf.UPPER), 2);

        }
        if (blockAbove.isOf(Blocks.WATER) && state.get(HALF) == DoubleBlockHalf.LOWER)
        {
            world.setBlockState(pos.up(1), this.getDefaultState()
                    .with(FACING, state.get(FACING))
                    .with(HALF, DoubleBlockHalf.UPPER)
                    .with(WATERLOGGED, true), 2);
        }
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!world.isClient && player.isCreative())
        {
            onBreakInCreative(world, pos, state, player);
        }
        else if (world.getBlockState(pos.up()).isOf(this) && state.get(HALF) == DoubleBlockHalf.LOWER)
        {
            world.breakBlock(pos, true);
            world.breakBlock(pos.up(), false);
        }
        else if (world.getBlockState(pos.down()).isOf(this) && state.get(HALF) == DoubleBlockHalf.UPPER)
        {
            world.breakBlock(pos, true);
            world.breakBlock(pos.down(), false);
        }
        return super.onBreak(world, pos, state, player);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (state.get(HALF) == DoubleBlockHalf.LOWER)
        {
            return (blockState.isSideSolidFullSquare(world, blockPos, Direction.UP) || world.getBlockState(pos.down()).isOf(Blocks.FARMLAND)) && world.getBlockState(pos.up()).isOf(Blocks.AIR) || world.getBlockState(pos.up()).isOf(Blocks.WATER);
        }
        return blockState.isOf(this);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos)
    {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        Direction.Axis axis = direction.getAxis();
        if (axis== Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP) && (!neighborState.isOf(this) || neighborState.get(HALF) == doubleBlockHalf))
        {
            return Blocks.AIR.getDefaultState();
        }
        else
        {
            if (axis == Direction.Axis.Y && doubleBlockHalf == DoubleBlockHalf.LOWER == (direction == Direction.UP) && (!neighborState.isOf(this) || neighborState.get(HALF) == doubleBlockHalf))
            {
                return this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER);
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, HALF, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        if (state.get(WATERLOGGED))  return Fluids.WATER.getStill(false);
        return super.getFluidState(state);
    }

    private static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER)
        {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER)
            {
                BlockState blockState2 = blockState.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPos, blockState2, 35);
                world.syncWorldEvent(player, 2001, blockPos, Block.getRawIdFromState(blockState));
            }
        }

    }
}
