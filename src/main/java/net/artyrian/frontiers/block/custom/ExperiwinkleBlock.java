package net.artyrian.frontiers.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class ExperiwinkleBlock extends FlowerBlock implements Fertilizable
{
    private static final IntProvider EXP_TO_DROP = ConstantIntProvider.create(5);

    public ExperiwinkleBlock(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings)
    {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    // Prevents mods that allow flowers to grow from bone mealing this, i.e Legacy4J
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state)
    {
        return false;
    }

    // Prevents mods that allow flowers to grow from bone mealing this, i.e Legacy4J
    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state)
    {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
    {

    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        super.onStacksDropped(state, world, pos, tool, dropExperience);
        if (dropExperience && !tool.isOf(Items.SHEARS))
        {
            this.dropExperienceWhenMined(world, pos, tool, EXP_TO_DROP);
        }
    }
}
