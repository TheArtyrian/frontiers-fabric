package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.property.FRBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;

public class LumenBlock extends Block
{
    public static final MapCodec<LumenBlock> CODEC = simpleCodec(LumenBlock::new);
    public static final IntegerProperty LUMEN_POWER = FRBlockProperties.LUMEN_POWER;

    @Override
    public MapCodec<LumenBlock> codec() {
        return CODEC;
    }

    public LumenBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(LUMEN_POWER, 0));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext con)
    {
        boolean is_receiving = con.getLevel().hasNeighborSignal(con.getClickedPos());
        if (is_receiving)
        {
            int power_lev = con.getLevel().getBestNeighborSignal(con.getClickedPos());
            int returnlev = (power_lev > 8) ? 2 : 1;
            return this.defaultBlockState().setValue(LUMEN_POWER, returnlev);
        }
        return this.defaultBlockState().setValue(LUMEN_POWER, 0);
    }

    @Override
    protected void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
    {
        if (!world.isClientSide)
        {
            int light_power = state.getValue(LUMEN_POWER);
            int conv_power = 0;

            if (world.hasNeighborSignal(pos))
            {
                int power_lev = world.getBestNeighborSignal(pos);
                conv_power = (power_lev > 8) ? 2 : 1;
            }

            if (light_power != conv_power)
            {
                if (conv_power == 0) world.scheduleTick(pos, this, 4);
                else world.setBlock(pos, state.setValue(LUMEN_POWER, conv_power), Block.UPDATE_CLIENTS);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if ((state.getValue(LUMEN_POWER) > 0) && !world.hasNeighborSignal(pos))
        {
            world.setBlock(pos, state.setValue(LUMEN_POWER, 0), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(LUMEN_POWER);
    }
}
