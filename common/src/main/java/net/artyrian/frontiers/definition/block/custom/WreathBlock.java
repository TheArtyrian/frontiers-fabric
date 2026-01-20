package net.artyrian.frontiers.definition.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WreathBlock extends Block implements Equipable
{
    public static final MapCodec<WreathBlock> CODEC = simpleCodec(WreathBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH,
                    Block.box(1.5, 3.0, 11.0, 10.5, 13.0, 16.0),
                    Direction.SOUTH,
                    Block.box(5.5, 3.0, 0.0, 10.5, 13.0, 5.0),
                    Direction.WEST,
                    Block.box(11.0, 3.0, 5.5, 16.0, 13.0, 10.5),
                    Direction.EAST,
                    Block.box(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)
            )
    );

    @Override
    public MapCodec<WreathBlock> codec() {
        return CODEC;
    }

    public WreathBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        Map<Direction, VoxelShape> x = Maps.newEnumMap(
                ImmutableMap.of(
                        Direction.NORTH,
                        Block.box(2.5, 2.5, 12.0, 13.5, 13.5, 16.0),
                        Direction.SOUTH,
                        Block.box(2.5, 2.5, 0.0, 13.5, 13.5, 4.0),
                        Direction.WEST,
                        Block.box(12.0, 2.5, 2.5, 16.0, 13.5, 13.5),
                        Direction.EAST,
                        Block.box(0.0, 2.5, 2.5, 4.0, 13.5, 13.5)
                ));

        return x.get(state.getValue(FACING));
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        Direction face = state.getValue(FACING);
        BlockPos blockPos = pos.relative(face.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return (blockState.is(BlockTags.DOORS)) ? Shapes.empty() : super.getCollisionShape(state, world, pos, context);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        Direction face = state.getValue(FACING);
        BlockPos blockPos = pos.relative(face.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return (blockState.isFaceSturdy(world, blockPos, face) || blockState.getBlock() instanceof LeavesBlock);
    }

    @Nullable @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        BlockState blockState = this.defaultBlockState();
        LevelReader worldView = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        Direction[] directions = ctx.getNearestLookingDirections();

        for (Direction direction : directions)
        {
            if (direction.getAxis().isHorizontal())
            {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.setValue(FACING, direction2);
                if (blockState.canSurvive(worldView, blockPos)) return blockState;
            }
        }

        return null;
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos)
    {
        Direction face = state.getValue(FACING);
        BlockPos blockPos = pos.relative(face.getOpposite());
        BlockState behind = world.getBlockState(blockPos);
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(world, pos) && !behind.is(BlockTags.DOORS) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation)
    {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror)
    {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
