package net.artyrian.frontiers.definition.world.feature.slime_trail;

import com.mojang.serialization.Codec;
import net.artyrian.frontiers.definition.block.custom.SlimeBulbBlock;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import java.util.function.Predicate;

public class SlimeTrailFeature extends Feature<NoneFeatureConfiguration>
{
    BlockState CAVE_AIR = Blocks.CAVE_AIR.defaultBlockState();

    public SlimeTrailFeature(Codec<NoneFeatureConfiguration> codec) { super(codec); }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos pos = context.origin();
        WorldGenLevel structworld = context.level();
        ChunkPos chunkPos = new ChunkPos(pos);
        RandomSource contextRand = context.random();

        RandomSource random = WorldgenRandom.seedSlimeChunk(chunkPos.x, chunkPos.z, structworld.getSeed(), 987234911L);
        Predicate<BlockState> predicate = blockState -> { return blockState.isAir(); };

        boolean is_in_slime_chunk = random.nextInt(10) == 0;
        if (is_in_slime_chunk && pos.getY() < 40 && contextRand.nextIntBetweenInclusive(0, 2) == 0)
        {
            BlockPos truePos = pos;
            boolean passed = false;
            for (int i = 0; i < 12; i++)
            {
                BlockPos down = truePos.below();
                boolean top_solid = structworld.getBlockState(down).isRedstoneConductor(structworld, down);
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
                int xxEnd = contextRand.nextIntBetweenInclusive(1, 3) * -1;
                int zzEnd = contextRand.nextIntBetweenInclusive(1, 3) * -1;

                for (int xx = contextRand.nextIntBetweenInclusive(1, 3); xx >= xxEnd; xx--)
                {
                    for (int yy = 1; yy >= -1; yy--)
                    {
                        for (int zz = contextRand.nextIntBetweenInclusive(1, 3); zz >= zzEnd; zz--)
                        {
                            checkingPos = truePos.offset(xx, yy, zz);
                            BlockState blockstate = structworld.getBlockState(checkingPos);

                            boolean canPlace = (xx != xxEnd && zz != zzEnd) || contextRand.nextIntBetweenInclusive(0, 2) == 0;

                            // This is very bad ignore this
                            if (blockstate.isAir() && canPlace)
                            {
                                boolean solidU = structworld.getBlockState(checkingPos.above()).isRedstoneConductor(structworld, checkingPos.above()) && !structworld.getBlockState(checkingPos.above()).is(FRBlocks.SLIME_TRAIL.get());
                                boolean solidD = structworld.getBlockState(checkingPos.below()).isRedstoneConductor(structworld, checkingPos.below()) && !structworld.getBlockState(checkingPos.below()).is(FRBlocks.SLIME_TRAIL.get());
                                boolean solidN = structworld.getBlockState(checkingPos.north()).isRedstoneConductor(structworld, checkingPos.north()) && !structworld.getBlockState(checkingPos.north()).is(FRBlocks.SLIME_TRAIL.get());
                                boolean solidE = structworld.getBlockState(checkingPos.east()).isRedstoneConductor(structworld, checkingPos.east()) && !structworld.getBlockState(checkingPos.east()).is(FRBlocks.SLIME_TRAIL.get());
                                boolean solidS = structworld.getBlockState(checkingPos.south()).isRedstoneConductor(structworld, checkingPos.south()) && !structworld.getBlockState(checkingPos.south()).is(FRBlocks.SLIME_TRAIL.get());
                                boolean solidW = structworld.getBlockState(checkingPos.west()).isRedstoneConductor(structworld, checkingPos.west()) && !structworld.getBlockState(checkingPos.west()).is(FRBlocks.SLIME_TRAIL.get());
                                boolean watery = structworld.getBlockState(checkingPos).getFluidState().is(Fluids.WATER);

                                if (solidU || solidD || solidN || solidE || solidS || solidW)
                                {
                                    BlockState base = FRBlocks.SLIME_TRAIL.get().defaultBlockState()
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.UP), solidU)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.DOWN), solidD)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.NORTH), solidN)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.EAST), solidE)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.SOUTH), solidS)
                                            .setValue(MultifaceBlock.getFaceProperty(Direction.WEST), solidW)
                                            .setValue(BlockStateProperties.WATERLOGGED, watery);

                                    this.safeSetBlock(structworld, checkingPos, base, predicate);
                                }
                            }
                        }
                    }
                }

                boolean generate_bulb = contextRand.nextIntBetweenInclusive(0, 2) == 0;
                if (generate_bulb && structworld.getBlockState(truePos.below()).isRedstoneConductor(structworld, truePos.below()))
                {
                    BlockState def = FRBlocks.SLIME_BULB.get().defaultBlockState();
                    def = def.setValue(SlimeBulbBlock.AGE, 1);
                    this.setBlock(structworld, truePos, def);
                }

                return true;
            }
            return false;
        }
        return false;
    }
}
