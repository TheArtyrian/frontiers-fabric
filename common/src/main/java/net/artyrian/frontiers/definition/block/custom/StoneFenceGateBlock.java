package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.function.BiConsumer;

public class StoneFenceGateBlock extends HorizontalDirectionalBlock
{
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty IN_WALL = BlockStateProperties.IN_WALL;
    protected static final VoxelShape Z_AXIS_SHAPE = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape X_AXIS_SHAPE = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);
    protected static final VoxelShape IN_WALL_Z_AXIS_SHAPE = Block.box(0.0, 0.0, 6.0, 16.0, 13.0, 10.0);
    protected static final VoxelShape IN_WALL_X_AXIS_SHAPE = Block.box(6.0, 0.0, 0.0, 10.0, 13.0, 16.0);
    protected static final VoxelShape Z_AXIS_COLLISION_SHAPE = Block.box(0.0, 0.0, 6.0, 16.0, 24.0, 10.0);
    protected static final VoxelShape X_AXIS_COLLISION_SHAPE = Block.box(6.0, 0.0, 0.0, 10.0, 24.0, 16.0);
    protected static final VoxelShape Z_AXIS_SIDES_SHAPE = Block.box(0.0, 5.0, 6.0, 16.0, 24.0, 10.0);
    protected static final VoxelShape X_AXIS_SIDES_SHAPE = Block.box(6.0, 5.0, 0.0, 10.0, 24.0, 16.0);
    protected static final VoxelShape Z_AXIS_CULL_SHAPE = Shapes.or(
            Block.box(0.0, 5.0, 7.0, 2.0, 16.0, 9.0), Block.box(14.0, 5.0, 7.0, 16.0, 16.0, 9.0)
    );
    protected static final VoxelShape X_AXIS_CULL_SHAPE = Shapes.or(
            Block.box(7.0, 5.0, 0.0, 9.0, 16.0, 2.0), Block.box(7.0, 5.0, 14.0, 9.0, 16.0, 16.0)
    );
    protected static final VoxelShape IN_WALL_Z_AXIS_CULL_SHAPE = Shapes.or(
            Block.box(0.0, 2.0, 7.0, 2.0, 13.0, 9.0), Block.box(14.0, 2.0, 7.0, 16.0, 13.0, 9.0)
    );
    protected static final VoxelShape IN_WALL_X_AXIS_CULL_SHAPE = Shapes.or(
            Block.box(7.0, 2.0, 0.0, 9.0, 13.0, 2.0), Block.box(7.0, 2.0, 14.0, 9.0, 13.0, 16.0)
    );

    private final SoundEvent open = FRSounds.STONE_FENCE_GATE_OPEN.get();
    private final SoundEvent close = FRSounds.STONE_FENCE_GATE_CLOSE.get();

    public static final MapCodec<StoneFenceGateBlock> CODEC = simpleCodec(StoneFenceGateBlock::new);

    @Override
    public MapCodec<StoneFenceGateBlock> codec() {
        return CODEC;
    }

    public StoneFenceGateBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any().setValue(OPEN, false).setValue(POWERED, false).setValue(IN_WALL, false)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (state.getValue(IN_WALL))
        {
            return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? IN_WALL_X_AXIS_SHAPE : IN_WALL_Z_AXIS_SHAPE;
        }
        else
        {
            return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
        }
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos)
    {
        Direction.Axis axis = direction.getAxis();
        if ((state.getValue(FACING)).getClockWise().getAxis() != axis)
        {
            return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
        }
        else
        {
            boolean bl = this.isWall(neighborState) || this.isWall(world.getBlockState(pos.relative(direction.getOpposite())));
            return state.setValue(IN_WALL, bl);
        }
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) {
        if (state.getValue(OPEN))
        {
            return Shapes.empty();
        }
        else
        {
            return (state.getValue(FACING)).getAxis() == Direction.Axis.Z ? Z_AXIS_SIDES_SHAPE : X_AXIS_SIDES_SHAPE;
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (state.getValue(OPEN))
        {
            return Shapes.empty();
        }
        else
        {
            return (state.getValue(FACING)).getAxis() == Direction.Axis.Z ? Z_AXIS_COLLISION_SHAPE : X_AXIS_COLLISION_SHAPE;
        }
    }

    @Override
    protected VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos)
    {
        if (state.getValue(IN_WALL))
        {
            return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? IN_WALL_X_AXIS_CULL_SHAPE : IN_WALL_Z_AXIS_CULL_SHAPE;
        }
        else
        {
            return (state.getValue(FACING)).getAxis() == Direction.Axis.X ? X_AXIS_CULL_SHAPE : Z_AXIS_CULL_SHAPE;
        }
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type)
    {
        return switch (type)
        {
            case LAND -> state.getValue(OPEN);
            case WATER -> false;
            case AIR -> state.getValue(OPEN);
            default -> false;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        Level world = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        boolean bl = world.hasNeighborSignal(blockPos);
        Direction direction = ctx.getHorizontalDirection();
        Direction.Axis axis = direction.getAxis();
        boolean bl2 = axis == Direction.Axis.Z && (this.isWall(world.getBlockState(blockPos.west())) || this.isWall(world.getBlockState(blockPos.east())))
                || axis == Direction.Axis.X && (this.isWall(world.getBlockState(blockPos.north())) || this.isWall(world.getBlockState(blockPos.south())));
        return this.defaultBlockState().setValue(FACING, direction).setValue(OPEN, bl).setValue(POWERED, bl).setValue(IN_WALL, bl2);
    }

    private boolean isWall(BlockState state) {
        return state.is(BlockTags.WALLS);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        if (state.getValue(OPEN))
        {
            state = state.setValue(OPEN, false);
            world.setBlock(pos, state, Block.UPDATE_CLIENTS | Block.UPDATE_IMMEDIATE);
        }
        else
        {
            Direction direction = player.getDirection();
            if (state.getValue(FACING) == direction.getOpposite())
            {
                state = state.setValue(FACING, direction);
            }

            state = state.setValue(OPEN, true);
            world.setBlock(pos, state, Block.UPDATE_CLIENTS | Block.UPDATE_IMMEDIATE);
        }

        boolean bl = state.getValue(OPEN);
        world.playSound(
                player, pos, bl ? this.open : this.close, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F
        );
        world.gameEvent(player, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    @Override
    protected void onExplosionHit(BlockState state, Level world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger)
    {
        if (explosion.canTriggerBlocks() && !(Boolean)state.getValue(POWERED))
        {
            boolean bl = state.getValue(OPEN);
            world.setBlockAndUpdate(pos, state.setValue(OPEN, !bl));
            world.playSound(
                    null, pos, bl ? this.close : this.open, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F
            );
            world.gameEvent(bl ? GameEvent.BLOCK_CLOSE : GameEvent.BLOCK_OPEN, pos, GameEvent.Context.of(state));
        }

        super.onExplosionHit(state, world, pos, explosion, stackMerger);
    }

    @Override
    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
    {
        if (!world.isClientSide)
        {
            boolean bl = world.hasNeighborSignal(pos);
            if (state.getValue(POWERED) != bl)
            {
                world.setBlock(pos, state.setValue(POWERED, bl).setValue(OPEN, bl), Block.UPDATE_CLIENTS);
                if (state.getValue(OPEN) != bl)
                {
                    world.playSound(
                            null, pos, bl ? this.open : this.close, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.1F + 0.9F
                    );
                    world.gameEvent(null, bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, OPEN, POWERED, IN_WALL);
    }

    public static boolean canWallConnect(BlockState state, Direction side)
    {
        return (state.getValue(FACING)).getAxis() == side.getClockWise().getAxis();
    }
}
