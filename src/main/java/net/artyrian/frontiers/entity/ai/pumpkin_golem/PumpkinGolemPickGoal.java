package net.artyrian.frontiers.entity.ai.pumpkin_golem;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Optional;

public class PumpkinGolemPickGoal extends MoveToTargetPosGoal
{
    private final PumpkinGolemEntity golem;
    private boolean hasTarget;

    public PumpkinGolemPickGoal(PumpkinGolemEntity golem, float speed, int range)
    {
        super(golem, speed, range);
        this.golem = golem;
    }

    @Override
    public boolean canStart()
    {
        if (this.golem.isGolemAsleep()) return false;

        if (this.cooldown <= 0) {
            if (!this.golem.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) { return false; }

            this.hasTarget = false;
        }
        return super.canStart();
    }

    @Override
    public boolean shouldContinue()
    {
        return this.hasTarget && !this.golem.isGolemAsleep() && super.shouldContinue();
    }

    @Override
    public void tick()
    {
        super.tick();
        this.golem
                .getLookControl()
                .lookAt(
                        this.targetPos.getX() + 0.5,
                        this.targetPos.getY() + 1.0,
                        this.targetPos.getZ() + 0.5,
                        10.0F,
                        (float)(this.golem.getMaxLookPitchChange())
                );

        if (this.hasReached() || this.targetPos.isWithinDistance(this.golem.getPos(), 2.0))
        {
            World world = this.golem.getWorld();
            BlockPos blockPos = this.targetPos.up();
            BlockState blockState = world.getBlockState(blockPos);
            Block block = blockState.getBlock();
            if (this.hasTarget)
            {
                BlockState defState = block.getDefaultState();

                world.breakBlock(blockPos, true, this.golem);
                if (!defState.isIn(ModTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT))
                {
                    world.setBlockState(blockPos, defState);
                }

                this.golem.setPickTicks();
            }

            this.hasTarget = false;
            this.cooldown = 10;
        }
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos)
    {
        BlockState blockState = world.getBlockState(pos.up());

        if (blockState.isIn(ModTags.Blocks.PUMPKIN_GOLEM_PICKABLE))
        {
            Block target = blockState.getBlock();

            if (target instanceof PlantBlock plant)
            {
                boolean canPlant = blockState.isIn(ModTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT) || plant.getDefaultState().canPlaceAt(world, pos.up());
                if (canPlant)
                {
                    boolean condCheck = doSpecialConditions(world, pos.up(), blockState);
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

    /** Checks if the crop can be planted in the space above the target position. */
    private static boolean doSpecialConditions(WorldView world, BlockPos pos, BlockState state)
    {
        Block target = state.getBlock();
        if (target instanceof CropBlock crop)
        {
            return crop.isMature(state);
        }
        else if (target instanceof NetherWartBlock)
        {
            Optional<Integer> opt = state.getOrEmpty(NetherWartBlock.AGE);
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
