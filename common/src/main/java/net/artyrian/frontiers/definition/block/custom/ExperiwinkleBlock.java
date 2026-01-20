package net.artyrian.frontiers.definition.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ExperiwinkleBlock extends FlowerBlock implements BonemealableBlock
{
    private static final IntProvider EXP_TO_DROP = ConstantInt.of(5);

    public ExperiwinkleBlock(Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings)
    {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    // Prevents mods that allow flowers to grow from bone mealing this, i.e Legacy4J
    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state)
    {
        return false;
    }

    // Prevents mods that allow flowers to grow from bone mealing this, i.e Legacy4J
    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state)
    {
        return false;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state)
    {

    }

    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        super.spawnAfterBreak(state, world, pos, tool, dropExperience);
        if (dropExperience && !tool.is(Items.SHEARS))
        {
            this.tryDropExperience(world, pos, tool, EXP_TO_DROP);
        }
    }
}
