package net.artyrian.frontiers.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WreathBlock extends Block implements Equipment
{
    public static final MapCodec<WreathBlock> CODEC = createCodec(WreathBlock::new);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH,
                    Block.createCuboidShape(1.5, 3.0, 11.0, 10.5, 13.0, 16.0),
                    Direction.SOUTH,
                    Block.createCuboidShape(5.5, 3.0, 0.0, 10.5, 13.0, 5.0),
                    Direction.WEST,
                    Block.createCuboidShape(11.0, 3.0, 5.5, 16.0, 13.0, 10.5),
                    Direction.EAST,
                    Block.createCuboidShape(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)
            )
    );

    @Override
    public MapCodec<WreathBlock> getCodec() {
        return CODEC;
    }

    public WreathBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        Map<Direction, VoxelShape> x = Maps.newEnumMap(
                ImmutableMap.of(
                        Direction.NORTH,
                        Block.createCuboidShape(2.5, 2.5, 12.0, 13.5, 13.5, 16.0),
                        Direction.SOUTH,
                        Block.createCuboidShape(2.5, 2.5, 0.0, 13.5, 13.5, 4.0),
                        Direction.WEST,
                        Block.createCuboidShape(12.0, 2.5, 2.5, 16.0, 13.5, 13.5),
                        Direction.EAST,
                        Block.createCuboidShape(0.0, 2.5, 2.5, 4.0, 13.5, 13.5)
                ));

        return x.get(state.get(FACING));
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        Direction face = state.get(FACING);
        BlockPos blockPos = pos.offset(face.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return (blockState.isIn(BlockTags.DOORS)) ? VoxelShapes.empty() : super.getCollisionShape(state, world, pos, context);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        Direction face = state.get(FACING);
        BlockPos blockPos = pos.offset(face.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return (blockState.isSideSolidFullSquare(world, blockPos, face) || blockState.getBlock() instanceof LeavesBlock);
    }

    @Nullable @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();

        for (Direction direction : directions)
        {
            if (direction.getAxis().isHorizontal())
            {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) return blockState;
            }
        }

        return null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos)
    {
        Direction face = state.get(FACING);
        BlockPos blockPos = pos.offset(face.getOpposite());
        BlockState behind = world.getBlockState(blockPos);
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) && !behind.isIn(BlockTags.DOORS) ? Blocks.AIR.getDefaultState() : state;
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation)
    {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror)
    {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
