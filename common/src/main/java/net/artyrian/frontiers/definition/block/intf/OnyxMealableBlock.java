package net.artyrian.frontiers.definition.block.intf;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public interface OnyxMealableBlock
{
    void createOnyxMealFRParticles(LevelAccessor level, BlockPos pos, int amount);
    boolean isValidOnyxMealFRTarget(LevelReader reader, BlockPos pos, BlockState state);
    void performOnyxMealFRAction(ServerLevel level, RandomSource random, BlockPos pos, BlockState state);
}
