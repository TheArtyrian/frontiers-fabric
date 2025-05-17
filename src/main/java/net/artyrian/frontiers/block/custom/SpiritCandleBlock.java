package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.function.ToIntFunction;

public class SpiritCandleBlock extends Block
{
    public static final MapCodec<SpiritCandleBlock> CODEC = createCodec(SpiritCandleBlock::new);
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.get(LIT) ? 6 : 0;
    private static final VoxelShape SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 7.0, 10.0);

    public SpiritCandleBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(LIT, false)
                        .with(WATERLOGGED, false)
        );
    }

    @Override
    protected MapCodec<SpiritCandleBlock> getCodec() { return CODEC; }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(LIT, WATERLOGGED); }
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);}
    protected boolean isNotLit(BlockState state) { return !(Boolean)state.get(WATERLOGGED); }
}
