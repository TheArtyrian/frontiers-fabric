package net.artyrian.frontiers.definition.world.feature.mushroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeFungalDaffodilFeature extends AbstractHugeMushroomFeature {
    public HugeFungalDaffodilFeature(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected int getTreeHeight(RandomSource random) {
        int i = random.nextInt(3) + 4;
        if (random.nextInt(12) == 0) {
            i *= 2;
        }

        return i + 3;
    }

    @Override
    protected void makeCap(LevelAccessor world, RandomSource random, BlockPos start, int y, BlockPos.MutableBlockPos mutable, HugeMushroomFeatureConfiguration config) {
        // Generate round cap
        BlockState shroomBlock = config.capProvider.getState(random, start);
        BlockState skinnedShroomBlock = config.capProvider.getState(random, start)
                .setValue(HugeMushroomBlock.UP, false)
                .setValue(HugeMushroomBlock.DOWN, false)
                .setValue(HugeMushroomBlock.WEST, false)
                .setValue(HugeMushroomBlock.EAST, false)
                .setValue(HugeMushroomBlock.NORTH, false)
                .setValue(HugeMushroomBlock.SOUTH, false);
        BlockPos.MutableBlockPos ogMutable = mutable.mutable();
        for (int i = y - 2; i <= y; i++) {
            int size = (y - i) + 1;
            for (int x = -size; x <= size; x++) {
                int spacing = Math.abs(x);
                for (int z = -size; z <= size; z++) {
                    if (Math.abs(z) <= size - spacing) {
                        mutable.setWithOffset(start, x, i, z);
                        this.setBlock(world, mutable, shroomBlock);
                    }
                }
            }
        }
        // Manual bits
        BlockState stemBlock = config.stemProvider.getState(random, start).setValue(HugeMushroomBlock.DOWN, true);
        mutable.setWithOffset(start, 0, y, 0);
        this.setBlock(world, mutable.offset(0, -2, -1), stemBlock);
        this.setBlock(world, mutable.offset(0, -2, 1), stemBlock);
        this.setBlock(world, mutable.offset(-1, -2, 0), stemBlock);
        this.setBlock(world, mutable.offset(1, -2, 0), stemBlock);

        this.setBlock(world, mutable.offset(-1, -3, -1), skinnedShroomBlock);
        this.setBlock(world, mutable.offset(-1, -3, 1), skinnedShroomBlock);
        this.setBlock(world, mutable.offset(1, -3, -1), skinnedShroomBlock);
        this.setBlock(world, mutable.offset(1, -3, 1), skinnedShroomBlock);

        // Tails

        BlockState facingShroomBlock = config.capProvider.getState(random, start).setValue(HugeMushroomBlock.DOWN, true);

        // Wes(🦎)t
        facingShroomBlock = facingShroomBlock
                .setValue(HugeMushroomBlock.WEST, false)
                .setValue(HugeMushroomBlock.EAST, true)
                .setValue(HugeMushroomBlock.NORTH, true)
                .setValue(HugeMushroomBlock.SOUTH, true);
        this.setBlock(world, mutable.offset(4, -3, 0), facingShroomBlock);
        this.setBlock(world, mutable.offset(3, -3, 0), facingShroomBlock);

        // East
        facingShroomBlock = facingShroomBlock
                .setValue(HugeMushroomBlock.WEST, true)
                .setValue(HugeMushroomBlock.EAST, false)
                .setValue(HugeMushroomBlock.NORTH, true)
                .setValue(HugeMushroomBlock.SOUTH, true);
        this.setBlock(world, mutable.offset(-4, -3, 0), facingShroomBlock);
        this.setBlock(world, mutable.offset(-3, -3, 0), facingShroomBlock);

        // North
        facingShroomBlock = facingShroomBlock
                .setValue(HugeMushroomBlock.WEST, true)
                .setValue(HugeMushroomBlock.EAST, true)
                .setValue(HugeMushroomBlock.NORTH, true)
                .setValue(HugeMushroomBlock.SOUTH, false);
        this.setBlock(world, mutable.offset(0, -3, -4), facingShroomBlock);
        this.setBlock(world, mutable.offset(0, -3, -3), facingShroomBlock);

        // South
        facingShroomBlock = facingShroomBlock
                .setValue(HugeMushroomBlock.WEST, true)
                .setValue(HugeMushroomBlock.EAST, true)
                .setValue(HugeMushroomBlock.NORTH, false)
                .setValue(HugeMushroomBlock.SOUTH, true);
        this.setBlock(world, mutable.offset(0, -3, 4), facingShroomBlock);
        this.setBlock(world, mutable.offset(0, -3, 3), facingShroomBlock);

        // Finally, the PointyBits(tm)
        facingShroomBlock = facingShroomBlock
                .setValue(HugeMushroomBlock.DOWN, true)
                .setValue(HugeMushroomBlock.WEST, true)
                .setValue(HugeMushroomBlock.EAST, true)
                .setValue(HugeMushroomBlock.NORTH, true)
                .setValue(HugeMushroomBlock.SOUTH, true);

        this.setBlock(world, mutable.offset(-5, -2, 0), facingShroomBlock);
        this.setBlock(world, mutable.offset(0, -2, -5), facingShroomBlock);
        this.setBlock(world, mutable.offset(5, -2, 0), facingShroomBlock);
        this.setBlock(world, mutable.offset(0, -2, 5), facingShroomBlock);
    }

    @Override
    protected int getTreeRadiusForHeight(int i, int j, int capSize, int y) {
        int k = 0;
        if (y < j && y >= j - 3) {
            k = capSize;
        } else if (y == j) {
            k = capSize;
        }

        return k;
    }
}
