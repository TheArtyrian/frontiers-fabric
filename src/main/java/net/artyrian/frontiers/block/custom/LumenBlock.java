package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LumenBlock extends Block
{
    public static final MapCodec<LumenBlock> CODEC = createCodec(LumenBlock::new);
    public static final IntProperty LUMEN_POWER = ModBlockProperties.LUMEN_POWER;

    @Override
    public MapCodec<LumenBlock> getCodec() {
        return CODEC;
    }

    public LumenBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LUMEN_POWER, 0));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext con) {
        boolean is_receiving = con.getWorld().isReceivingRedstonePower(con.getBlockPos());
        if (is_receiving)
        {
            int power_lev = con.getWorld().getReceivedRedstonePower(con.getBlockPos());
            int returnlev = (power_lev > 8) ? 2 : 1;
            return this.getDefaultState().with(LUMEN_POWER, returnlev);
        }
        return this.getDefaultState().with(LUMEN_POWER, 0);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
    {
        if (!world.isClient)
        {
            int light_power = state.get(LUMEN_POWER);
            int conv_power = 0;

            if (world.isReceivingRedstonePower(pos))
            {
                int power_lev = world.getReceivedRedstonePower(pos);
                conv_power = (power_lev > 8) ? 2 : 1;
            }

            if (light_power != conv_power)
            {
                if (conv_power == 0) world.scheduleBlockTick(pos, this, 4);
                else world.setBlockState(pos, state.with(LUMEN_POWER, conv_power), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((state.get(LUMEN_POWER) > 0) && !world.isReceivingRedstonePower(pos))
        {
            world.setBlockState(pos, state.with(LUMEN_POWER, 0), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LUMEN_POWER);
    }
}
