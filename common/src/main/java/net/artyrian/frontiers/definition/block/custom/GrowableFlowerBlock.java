package net.artyrian.frontiers.definition.block.custom;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class GrowableFlowerBlock extends ExtendedFlowerBlock implements BonemealableBlock
{
    private final ResourceKey<ConfiguredFeature<?, ?>> featureKey;

    public GrowableFlowerBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, Block blocktype, Holder<MobEffect> stewEffect, float effectLengthInSeconds, Properties settings) {
        super(blocktype, stewEffect, effectLengthInSeconds, settings);
        this.featureKey = featureKey;
    }

    public boolean trySpawningHugeVariant(ServerLevel world, BlockPos pos, BlockState state, RandomSource random)
    {
        Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = world.registryAccess()
                .registryOrThrow(Registries.CONFIGURED_FEATURE)
                .getHolder(this.featureKey);
        if (optional.isEmpty())
        {
            return false;
        }
        else
        {
            world.removeBlock(pos, false);
            if (((ConfiguredFeature)((Holder)optional.get()).value()).place(world, world.getChunkSource().getGenerator(), random, pos))
            {
                return true;
            }
            else
            {
                world.setBlock(pos, state, Block.UPDATE_ALL);
                return false;
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return (double)random.nextFloat() < 0.4;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        this.trySpawningHugeVariant(world, pos, state, random);
    }
}
