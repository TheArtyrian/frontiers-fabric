package net.artyrian.frontiers.definition.entity.ai.pumpkin_golem;

import net.artyrian.frontiers.definition.block.custom.ExperiwinkleBlock;
import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Optional;

public class PumpkinGolemPickGoal extends MoveToBlockGoal
{
    private final PumpkinGolemEntity golem;
    private boolean hasTarget;

    public PumpkinGolemPickGoal(PumpkinGolemEntity golem, float speed, int range)
    {
        super(golem, speed, range);
        this.golem = golem;
    }

    @Override
    public boolean canUse()
    {
        if (this.golem.isGolemAsleep()) return false;

        if (this.nextStartTick <= 0) {
            if (!this.golem.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) { return false; }

            this.hasTarget = false;
        }
        return super.canUse();
    }

    @Override
    public boolean canContinueToUse()
    {
        return this.hasTarget && !this.golem.isGolemAsleep() && super.canContinueToUse();
    }

    @Override
    public void tick()
    {
        super.tick();
        this.golem
                .getLookControl()
                .setLookAt(
                        this.blockPos.getX() + 0.5,
                        this.blockPos.getY() + 1.0,
                        this.blockPos.getZ() + 0.5,
                        10.0F,
                        (float)(this.golem.getMaxHeadXRot())
                );

        if (this.isReachedTarget() || this.blockPos.above().closerToCenterThan(this.golem.position(), 2.0))
        {
            Level world = this.golem.level();
            BlockPos blockPos = this.blockPos.above();
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();
            if (this.hasTarget)
            {
                BlockState defState = getHardcodedStateToGet(block);

                world.destroyBlock(blockPos, true, this.golem);
                if (!defState.is(FRTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT))
                {
                    world.setBlockAndUpdate(blockPos, defState);
                }

                this.golem.setPickTicks();
            }

            this.hasTarget = false;
            this.nextStartTick = 10;
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader world, BlockPos pos)
    {
        BlockState blockState = world.getBlockState(pos.above());

        if (blockState.is(FRTags.Blocks.PUMPKIN_GOLEM_PICKABLE))
        {
            Block target = blockState.getBlock();

            if (target instanceof BushBlock plant)
            {
                boolean canPlant = blockState.is(FRTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT) || plant.defaultBlockState().canSurvive(world, pos.above());
                if (canPlant)
                {
                    boolean condCheck = doSpecialConditions(world, pos.above(), blockState);
                    if (condCheck)
                    {
                        this.hasTarget = true;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static BlockState getHardcodedStateToGet(Block target)
    {
        if (target instanceof ExperiwinkleBlock) return FRBlocks.EXPERIWINKLE_CROP.get().defaultBlockState();
        return target.defaultBlockState();
    }

    /** Checks if the crop can be planted in the space above the target position. */
    private static boolean doSpecialConditions(LevelReader world, BlockPos pos, BlockState state)
    {
        Block target = state.getBlock();
        if (target instanceof CropBlock crop)
        {
            return crop.isMaxAge(state);
        }
        else if (target instanceof ExperiwinkleBlock)
        {
            return FRBlocks.EXPERIWINKLE_CROP.get().defaultBlockState().canSurvive(world, pos);
        }
        else if (target instanceof NetherWartBlock)
        {
            Optional<Integer> opt = state.getOptionalValue(NetherWartBlock.AGE);
            if (opt.isPresent())
            {
                int opt2 = opt.get();
                return (opt2 >= NetherWartBlock.MAX_AGE);
            }
            return false;
        }
        // Returns false as a failsafe to prevent the mod from doing weird shit with modded crops.
        return false;
    }
}
