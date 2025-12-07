package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Optional;

public class GrowableFlowerBlock extends ExtendedFlowerBlock implements Fertilizable {
    private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;

    public GrowableFlowerBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Block blocktype, RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings) {
        super(blocktype, stewEffect, effectLengthInSeconds, settings);
        this.featureKey = featureKey;
    }

    public boolean trySpawningHugeVariant(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        Optional<? extends RegistryEntry<ConfiguredFeature<?, ?>>> optional = world.getRegistryManager()
                .get(RegistryKeys.CONFIGURED_FEATURE)
                .getEntry(this.featureKey);
        if (optional.isEmpty()) {
            return false;
        } else {
            world.removeBlock(pos, false);
            if (((ConfiguredFeature)((RegistryEntry)optional.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
                return true;
            } else {
                world.setBlockState(pos, state, Block.NOTIFY_ALL);
                return false;
            }
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)random.nextFloat() < 0.4;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.trySpawningHugeVariant(world, pos, state, random);
    }
}
