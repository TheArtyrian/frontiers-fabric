package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingObsidianBlock extends UnbreakableInDimensionBlock
{
    public GlowingObsidianBlock(ResourceKey<Level> type, Properties settings) { super(type, settings); }
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) { if (level.random.nextFloat() > 0.8F) spawnParticles(level, pos); }

    // Based on redstone code as it seems the original was, but let's make it more unique shall we
    private static void spawnParticles(Level level, BlockPos pos)
    {
        double offset = 0.5625;
        RandomSource randomsource = level.random;

        for (Direction dir : Direction.values())
        {
            BlockPos blockpos = pos.relative(dir);
            if (!level.getBlockState(blockpos).isSolidRender(level, blockpos))
            {
                Direction.Axis axi = dir.getAxis();
                double xx = (axi == Direction.Axis.X) ? 0.5 + offset * (double)dir.getStepX() : randomsource.nextDouble();
                double yy = (axi == Direction.Axis.Y) ? 0.5 + offset * (double)dir.getStepY() : randomsource.nextDouble();
                double zz = (axi == Direction.Axis.Z) ? 0.5 + offset * (double)dir.getStepZ() : randomsource.nextDouble();
                level.addParticle(
                        ModParticle.GLOWING_OBSIDIAN,
                        (double)pos.getX() + xx,
                        (double)pos.getY() + yy,
                        (double)pos.getZ() + zz,
                        0.0,
                        0.0,
                        0.0
                );
            }
        }
    }
}
