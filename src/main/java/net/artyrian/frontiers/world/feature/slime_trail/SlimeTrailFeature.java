package net.artyrian.frontiers.world.feature.slime_trail;

import com.mojang.serialization.Codec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.SlimeBulbBlock;
import net.artyrian.frontiers.block.custom.SlimeTrailBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Predicate;

public class SlimeTrailFeature extends Feature<DefaultFeatureConfig>
{
    BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();

    public SlimeTrailFeature(Codec<DefaultFeatureConfig> codec) { super(codec); }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context)
    {
        BlockPos pos = context.getOrigin();
        StructureWorldAccess structworld = context.getWorld();
        ChunkPos chunkPos = new ChunkPos(pos);
        Random contextRand = context.getRandom();

        Random random = ChunkRandom.getSlimeRandom(chunkPos.x, chunkPos.z, structworld.getSeed(), 987234911L);
        Predicate<BlockState> predicate = blockState -> { return blockState.isAir(); };

        boolean is_in_slime_chunk = random.nextInt(10) == 0;
        if (is_in_slime_chunk && pos.getY() < 40 && contextRand.nextBetween(0, 2) == 0)
        {
            BlockPos truePos = pos;
            boolean passed = false;
            for (int i = 0; i < 12; i++)
            {
                BlockPos down = truePos.down();
                boolean top_solid = structworld.getBlockState(down).isSolidBlock(structworld, down);
                if (structworld.getBlockState(truePos).isAir() && top_solid)
                {
                    passed = true;
                    break;
                }
                else
                {
                    truePos = down;
                }
            }

            if (passed)
            {
                // Slime trail placement based on my contributions to Dungeon's Delight :3
                BlockPos checkingPos;
                int xxEnd = contextRand.nextBetween(1, 3) * -1;
                int zzEnd = contextRand.nextBetween(1, 3) * -1;

                for (int xx = contextRand.nextBetween(1, 3); xx >= xxEnd; xx--)
                {
                    for (int yy = 1; yy >= -1; yy--)
                    {
                        for (int zz = contextRand.nextBetween(1, 3); zz >= zzEnd; zz--)
                        {
                            checkingPos = truePos.add(xx, yy, zz);
                            BlockState blockstate = structworld.getBlockState(checkingPos);

                            boolean canPlace = (xx != xxEnd && zz != zzEnd) || contextRand.nextBetween(0, 2) == 0;

                            // This is very bad ignore this
                            if (blockstate.isAir() && canPlace)
                            {
                                boolean solidU = structworld.getBlockState(checkingPos.up()).isSolidBlock(structworld, checkingPos.up()) && !structworld.getBlockState(checkingPos.up()).isOf(ModBlocks.SLIME_TRAIL);
                                boolean solidD = structworld.getBlockState(checkingPos.down()).isSolidBlock(structworld, checkingPos.down()) && !structworld.getBlockState(checkingPos.down()).isOf(ModBlocks.SLIME_TRAIL);
                                boolean solidN = structworld.getBlockState(checkingPos.north()).isSolidBlock(structworld, checkingPos.north()) && !structworld.getBlockState(checkingPos.north()).isOf(ModBlocks.SLIME_TRAIL);
                                boolean solidE = structworld.getBlockState(checkingPos.east()).isSolidBlock(structworld, checkingPos.east()) && !structworld.getBlockState(checkingPos.east()).isOf(ModBlocks.SLIME_TRAIL);
                                boolean solidS = structworld.getBlockState(checkingPos.south()).isSolidBlock(structworld, checkingPos.south()) && !structworld.getBlockState(checkingPos.south()).isOf(ModBlocks.SLIME_TRAIL);
                                boolean solidW = structworld.getBlockState(checkingPos.west()).isSolidBlock(structworld, checkingPos.west()) && !structworld.getBlockState(checkingPos.west()).isOf(ModBlocks.SLIME_TRAIL);
                                boolean watery = structworld.getBlockState(checkingPos).getFluidState().isOf(Fluids.WATER);

                                if (solidU || solidD || solidN || solidE || solidS || solidW)
                                {
                                    BlockState base = ModBlocks.SLIME_TRAIL.getDefaultState()
                                            .with(MultifaceGrowthBlock.getProperty(Direction.UP), solidU)
                                            .with(MultifaceGrowthBlock.getProperty(Direction.DOWN), solidD)
                                            .with(MultifaceGrowthBlock.getProperty(Direction.NORTH), solidN)
                                            .with(MultifaceGrowthBlock.getProperty(Direction.EAST), solidE)
                                            .with(MultifaceGrowthBlock.getProperty(Direction.SOUTH), solidS)
                                            .with(MultifaceGrowthBlock.getProperty(Direction.WEST), solidW)
                                            .with(Properties.WATERLOGGED, watery);

                                    this.setBlockStateIf(structworld, checkingPos, base, predicate);
                                }
                            }
                        }
                    }
                }

                boolean generate_bulb = contextRand.nextBetween(0, 2) == 0;
                if (generate_bulb && structworld.getBlockState(truePos.down()).isSolidBlock(structworld, truePos.down()))
                {
                    BlockState def = ModBlocks.SLIME_BULB.getDefaultState();
                    def = def.with(SlimeBulbBlock.AGE, 1);
                    this.setBlockState(structworld, truePos, def);
                }

                return true;
            }
            return false;
        }
        return false;
    }
}
