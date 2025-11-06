package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class SlimeTrailBlock extends MultifaceGrowthBlock implements Waterloggable
{
    public static final MapCodec<SlimeTrailBlock> CODEC = createCodec(SlimeTrailBlock::new);
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final LichenGrower grower = new LichenGrower(this);

    @Override
    public MapCodec<SlimeTrailBlock> getCodec() {
        return CODEC;
    }

    @Override
    public LichenGrower getGrower()
    {
        return grower;
    }

    public SlimeTrailBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    )
    {
        if (state.get(WATERLOGGED))
        {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos)
    {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected boolean canReplace(BlockState state, ItemPlacementContext context)
    {
        return !context.getStack().isOf(ModBlocks.SLIME_TRAIL.asItem()) || super.canReplace(state, context);
    }
}
