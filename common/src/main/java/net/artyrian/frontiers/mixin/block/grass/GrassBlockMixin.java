package net.artyrian.frontiers.mixin.block.grass;

import net.artyrian.frontiers.definition.block.intf.OnyxMealableBlock;
import net.artyrian.frontiers.reg.content.FRTags;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.content.FRParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GrassBlock.class)
public class GrassBlockMixin implements OnyxMealableBlock
{
    @Override
    public void createOnyxMealFRParticles(LevelAccessor level, BlockPos pos, int amount)
    {
        ParticleUtils.spawnParticles(level, pos.above(), amount * 3, 3.0, 1.0, false, FRParticles.WITHER_GLINT.get());
    }

    @Override public boolean isValidOnyxMealFRTarget(LevelReader reader, BlockPos pos, BlockState state) { return reader.getBlockState(pos.above()).isAir(); }

    @Override
    public void performOnyxMealFRAction(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
    {
        goto_start:
        for (int i = 0; i < 128; i++)
        {
            BlockPos blockPos2 = pos.above();
            for (int j = 0; j < i / 16; j++)
            {
                blockPos2 = blockPos2.offset(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                if (!level.getBlockState(blockPos2.below()).is(Blocks.GRASS_BLOCK) || level.getBlockState(blockPos2).isCollisionShapeFullBlock(level, blockPos2))
                {
                    continue goto_start;
                }
            }

            if (level.getBlockState(blockPos2).is(FRTags.Blocks.ONYX_MEAL_DECAYABLE))
            {
                level.destroyBlock(blockPos2, true);
                VectorEventSync.Local.fireEvent(level, blockPos2, FRLevelEvents.Local.ONYX_MEAL, 2);
            }
        }
    }
}
