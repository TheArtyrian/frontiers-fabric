package net.artyrian.frontiers.definition.block.custom.model;

import net.artyrian.frontiers.definition.block.entity.model.BlazeModelBlockEntity;
import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.artyrian.frontiers.reg.property.FRBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.jetbrains.annotations.Nullable;

public class BlazeModelBlock extends EntityModelBlock implements EntityBlock
{
    public static final BooleanProperty MODEL_POWERED = FRBlockProperties.MODEL_POWERED;

    public BlazeModelBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(MODEL_POWERED, false));
    }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, FRBlockEntities.BLAZE_MODEL_BLOCKENTITY.get(), BlazeModelBlockEntity::tick) : null;
    }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new BlazeModelBlockEntity(pos, state); }

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
        super.createBlockStateDefinition(builder);
        builder.add(MODEL_POWERED);
    }

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
