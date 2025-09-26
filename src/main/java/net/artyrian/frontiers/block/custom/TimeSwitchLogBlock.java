package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Optional;

public class TimeSwitchLogBlock extends PillarBlock
{
    public static final BooleanProperty CAN_SWITCH = BooleanProperty.of("can_switch");
    public static final MapCodec<TimeSwitchLogBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                        Codec.BOOL.fieldOf("switch_during_day").forGetter(block -> block.switch_during_day),
                        Codec.STRING.fieldOf("swapped_block").forGetter(block -> block.swapped_block),
                        createSettingsCodec()
                    )
                    .apply(instance, TimeSwitchLogBlock::new)
    );

    private final boolean switch_during_day;
    private final String swapped_block;

    public TimeSwitchLogBlock(boolean switch_during_day, String target_block, Settings settings)
    {
        super(settings);
        this.switch_during_day = switch_during_day;
        this.swapped_block = target_block;

        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(AXIS, Direction.Axis.Y)
                        .with(CAN_SWITCH, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(AXIS, CAN_SWITCH); }

    @Override
    public MapCodec<TimeSwitchLogBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
    {
        world.scheduleBlockTick(pos, this, 20);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        Optional<Boolean> switcher = state.getOrEmpty(CAN_SWITCH);
        boolean not_fixed_time = !world.getDimension().hasFixedTime();
        if (switcher.isPresent() && switcher.get() && not_fixed_time)
        {
            boolean is_target_time = (this.switch_during_day) ? world.isDay() : world.isNight();
            if (is_target_time)
            {
                Optional<Block> blockToGo = getSwitchBlock();
                blockToGo.ifPresent(block -> world.setBlockState(pos, block.getDefaultState()
                        .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                        .with(CAN_SWITCH, true)
                ));
            }
            world.scheduleBlockTick(pos, this, 20);
        }
    }

    public boolean canSwitchDuringDay() { return this.switch_during_day; }
    public Optional<Block> getSwitchBlock() { return Registries.BLOCK.getOrEmpty(Identifier.of(this.swapped_block)); }
}
