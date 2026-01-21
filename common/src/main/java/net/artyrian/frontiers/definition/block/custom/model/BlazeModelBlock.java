package net.artyrian.frontiers.definition.block.custom.model;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.model.BlazeModelBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlazeModelBlock extends BaseEntityBlock implements EntityBlock
{
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final BooleanProperty MODEL_POWERED = ModBlockProperties.MODEL_POWERED;
    public static final MapCodec<BlazeModelBlock> CODEC = BlazeModelBlock.simpleCodec(BlazeModelBlock::new);

    private static final VoxelShape COLLIDER = Block.box(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    private static final VoxelShape VOXEL_SHAPE = Shapes.join(
            COLLIDER, Block.box(4.0, 1.0, 4.0, 12.0, 16.0, 12.0), BooleanOp.OR);

    public BlazeModelBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, 0));
        this.registerDefaultState(this.stateDefinition.any().setValue(MODEL_POWERED, false));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), BlazeModelBlockEntity::tick) : null;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return VOXEL_SHAPE; }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return COLLIDER; }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new BlazeModelBlockEntity(pos, state); }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        boolean is_receiving = ctx.getLevel().hasNeighborSignal(ctx.getClickedPos());
        return this.defaultBlockState()
                .setValue(ROTATION, RotationSegment.convertToSegment(ctx.getRotation() + 180.0F))
                .setValue(MODEL_POWERED, is_receiving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATION);
        builder.add(MODEL_POWERED);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) { return state.setValue(ROTATION, rotation.rotate(state.getValue(ROTATION), 16));}
    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) { return state.setValue(ROTATION, mirror.mirror(state.getValue(ROTATION), 16));}

    @Override
    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
    {
        if (!world.isClientSide)
        {
            boolean bl = state.getValue(MODEL_POWERED);
            if (bl != world.hasNeighborSignal(pos))
            {
                if (bl) world.scheduleTick(pos, this, 4);
                else world.setBlock(pos, state.cycle(MODEL_POWERED), Block.UPDATE_CLIENTS);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if (state.getValue(MODEL_POWERED) && !world.hasNeighborSignal(pos)) world.setBlock(pos, state.cycle(MODEL_POWERED), Block.UPDATE_CLIENTS);
    }
}
