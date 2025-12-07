package net.artyrian.frontiers.world.feature.huge_mushrooms;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class HugeFungalDaffodilFeature extends HugeMushroomFeature {
    public HugeFungalDaffodilFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected int getHeight(Random random) {
        int i = random.nextInt(3) + 4;
        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        return i + 3;
    }

    @Override
    protected void generateCap(WorldAccess world, Random random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        // Generate round cap
        BlockState shroomBlock = config.capProvider.get(random, start);
        BlockState skinnedShroomBlock = config.capProvider.get(random, start)
                .with(MushroomBlock.UP, false)
                .with(MushroomBlock.DOWN, false)
                .with(MushroomBlock.WEST, false)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, false);
        BlockPos.Mutable ogMutable = mutable.mutableCopy();
        for (int i = y - 2; i <= y; i++) {
            int size = (y - i) + 1;
            for (int x = -size; x <= size; x++) {
                int spacing = Math.abs(x);
                for (int z = -size; z <= size; z++) {
                    if (Math.abs(z) <= size - spacing) {
                        mutable.set(start, x, i, z);
                        this.setBlockState(world, mutable, shroomBlock);
                    }
                }
            }
        }
        // Manual bits
        BlockState stemBlock = config.stemProvider.get(random, start).with(MushroomBlock.DOWN, true);
        mutable.set(start, 0, y, 0);
        this.setBlockState(world, mutable.add(0, -2, -1), stemBlock);
        this.setBlockState(world, mutable.add(0, -2, 1), stemBlock);
        this.setBlockState(world, mutable.add(-1, -2, 0), stemBlock);
        this.setBlockState(world, mutable.add(1, -2, 0), stemBlock);

        this.setBlockState(world, mutable.add(-1, -3, -1), skinnedShroomBlock);
        this.setBlockState(world, mutable.add(-1, -3, 1), skinnedShroomBlock);
        this.setBlockState(world, mutable.add(1, -3, -1), skinnedShroomBlock);
        this.setBlockState(world, mutable.add(1, -3, 1), skinnedShroomBlock);

        // Tails

        BlockState facingShroomBlock = config.capProvider.get(random, start).with(MushroomBlock.DOWN, true);

        // Wes(🦎)t
        facingShroomBlock = facingShroomBlock
                .with(MushroomBlock.WEST, false)
                .with(MushroomBlock.EAST, true)
                .with(MushroomBlock.NORTH, true)
                .with(MushroomBlock.SOUTH, true);
        this.setBlockState(world, mutable.add(4, -3, 0), facingShroomBlock);
        this.setBlockState(world, mutable.add(3, -3, 0), facingShroomBlock);

        // East
        facingShroomBlock = facingShroomBlock
                .with(MushroomBlock.WEST, true)
                .with(MushroomBlock.EAST, false)
                .with(MushroomBlock.NORTH, true)
                .with(MushroomBlock.SOUTH, true);
        this.setBlockState(world, mutable.add(-4, -3, 0), facingShroomBlock);
        this.setBlockState(world, mutable.add(-3, -3, 0), facingShroomBlock);

        // North
        facingShroomBlock = facingShroomBlock
                .with(MushroomBlock.WEST, true)
                .with(MushroomBlock.EAST, true)
                .with(MushroomBlock.NORTH, true)
                .with(MushroomBlock.SOUTH, false);
        this.setBlockState(world, mutable.add(0, -3, -4), facingShroomBlock);
        this.setBlockState(world, mutable.add(0, -3, -3), facingShroomBlock);

        // South
        facingShroomBlock = facingShroomBlock
                .with(MushroomBlock.WEST, true)
                .with(MushroomBlock.EAST, true)
                .with(MushroomBlock.NORTH, false)
                .with(MushroomBlock.SOUTH, true);
        this.setBlockState(world, mutable.add(0, -3, 4), facingShroomBlock);
        this.setBlockState(world, mutable.add(0, -3, 3), facingShroomBlock);

        // Finally, the PointyBits(tm)
        facingShroomBlock = facingShroomBlock
                .with(MushroomBlock.DOWN, true)
                .with(MushroomBlock.WEST, true)
                .with(MushroomBlock.EAST, true)
                .with(MushroomBlock.NORTH, true)
                .with(MushroomBlock.SOUTH, true);

        this.setBlockState(world, mutable.add(-5, -2, 0), facingShroomBlock);
        this.setBlockState(world, mutable.add(0, -2, -5), facingShroomBlock);
        this.setBlockState(world, mutable.add(5, -2, 0), facingShroomBlock);
        this.setBlockState(world, mutable.add(0, -2, 5), facingShroomBlock);
    }

    @Override
    protected int getCapSize(int i, int j, int capSize, int y) {
        int k = 0;
        if (y < j && y >= j - 3) {
            k = capSize;
        } else if (y == j) {
            k = capSize;
        }

        return k;
    }
}
