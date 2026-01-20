package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import java.util.Optional;

public class TimeSwitchLogBlock extends RotatedPillarBlock
{
    public static final BooleanProperty CAN_SWITCH = BooleanProperty.create("can_switch");
    public static final MapCodec<TimeSwitchLogBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                        Codec.BOOL.fieldOf("switch_during_day").forGetter(block -> block.switch_during_day),
                        Codec.STRING.fieldOf("swapped_block").forGetter(block -> block.swapped_block),
                        propertiesCodec()
                    )
                    .apply(instance, TimeSwitchLogBlock::new)
    );

    private final boolean switch_during_day;
    private final String swapped_block;

    public TimeSwitchLogBlock(boolean switch_during_day, String target_block, Properties settings)
    {
        super(settings);
        this.switch_during_day = switch_during_day;
        this.swapped_block = target_block;

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(AXIS, Direction.Axis.Y)
                        .setValue(CAN_SWITCH, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(AXIS, CAN_SWITCH); }

    @Override
    public MapCodec<TimeSwitchLogBlock> codec() {
        return CODEC;
    }

    @Override
    protected void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify)
    {
        world.scheduleTick(pos, this, 20);
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        Optional<Boolean> switcher = state.getOptionalValue(CAN_SWITCH);
        boolean not_fixed_time = !world.dimensionType().hasFixedTime();
        if (switcher.isPresent() && switcher.get() && not_fixed_time)
        {
            boolean is_target_time = (this.switch_during_day) ? world.isDay() : world.isNight();
            if (is_target_time)
            {
                Optional<Block> blockToGo = getSwitchBlock();
                blockToGo.ifPresent(block -> world.setBlockAndUpdate(pos, block.defaultBlockState()
                        .setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS))
                        .setValue(CAN_SWITCH, true)
                ));
            }
            world.scheduleTick(pos, this, 20);
        }
    }

    public boolean canSwitchDuringDay() { return this.switch_during_day; }
    public Optional<Block> getSwitchBlock() { return BuiltInRegistries.BLOCK.getOptional(ResourceLocation.parse(this.swapped_block)); }
}
