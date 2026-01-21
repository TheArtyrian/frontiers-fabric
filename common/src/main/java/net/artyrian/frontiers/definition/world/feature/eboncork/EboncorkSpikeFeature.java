package net.artyrian.frontiers.definition.world.feature.eboncork;

import com.mojang.serialization.Codec;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import java.util.function.Predicate;

public class EboncorkSpikeFeature extends Feature<NoneFeatureConfiguration>
{
    public EboncorkSpikeFeature(Codec<NoneFeatureConfiguration> codec) { super(codec); }

    @Override
    // i hope you guys like spaghetti, because i'm strega nona up in this fucking method
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        Predicate<BlockState> predicate = BlockState::isAir;
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        WorldGenLevel structWorld = context.level();

        boolean generate_up = false;
        boolean generate_down = false;
        int min_len = 8;
        int max_len = 16;
        int len_up = 0;
        int len_down = 0;

        // DON'T EVEN TRY IF THE ORIGIN ISN'T AIR!
        if (!structWorld.getBlockState(origin).isAir()) return false;

        BlockPos checkingPos;
        BlockPos[] checkingAround = new BlockPos[4];
        BlockState stateAt;
        boolean isAirAt;
        boolean isFluidAt;
        boolean[] isAirAround = new boolean[4];
        // Upwards check
        for (int i = 1; i <= max_len + 1; i++)
        {
            checkingPos = origin.above(i);
            stateAt = structWorld.getBlockState(checkingPos);
            isFluidAt = !structWorld.getFluidState(checkingPos).isEmpty();
            isAirAt = stateAt.isAir();

            if (isFluidAt)
            {
                generate_up = false;
                break;
            }

            if (len_up >= min_len)
            {
                checkingAround[0] = checkingPos.north();
                isAirAround[0] = structWorld.getBlockState(checkingAround[0]).isAir();
                checkingAround[1] = checkingPos.east();
                isAirAround[1] = structWorld.getBlockState(checkingAround[1]).isAir();
                checkingAround[2] = checkingPos.south();
                isAirAround[2] = structWorld.getBlockState(checkingAround[2]).isAir();
                checkingAround[3] = checkingPos.west();
                isAirAround[3] = structWorld.getBlockState(checkingAround[3]).isAir();

                if (isAirAt || isAirAround[0] || isAirAround[1] || isAirAround[2] || isAirAround[3])
                {
                    if (i == max_len + 1)
                    {
                        generate_up = false;
                        break;
                    }
                    else len_up++;
                }
                else break;
            }
            else
            {
                if (isAirAt)
                {
                    len_up++;
                    if (len_up >= min_len) generate_up = true;
                }
                else
                {
                    generate_up = false;
                    break;
                }
            }
        }

        // Downwards check
        for (int i = 1; i <= max_len + 1; i++)
        {
            checkingPos = origin.below(i);
            stateAt = structWorld.getBlockState(checkingPos);
            isFluidAt = !structWorld.getFluidState(checkingPos).isEmpty();
            isAirAt = stateAt.isAir();

            if (isFluidAt)
            {
                generate_down = false;
                break;
            }

            if (len_down >= min_len)
            {
                checkingAround[0] = checkingPos.north();
                isAirAround[0] = structWorld.getBlockState(checkingAround[0]).isAir();
                checkingAround[1] = checkingPos.east();
                isAirAround[1] = structWorld.getBlockState(checkingAround[1]).isAir();
                checkingAround[2] = checkingPos.south();
                isAirAround[2] = structWorld.getBlockState(checkingAround[2]).isAir();
                checkingAround[3] = checkingPos.west();
                isAirAround[3] = structWorld.getBlockState(checkingAround[3]).isAir();

                if (isAirAt || isAirAround[0] || isAirAround[1] || isAirAround[2] || isAirAround[3])
                {
                    if (i == max_len + 1)
                    {
                        generate_down = false;
                        break;
                    }
                    else len_down++;
                }
                else break;
            }
            else
            {
                if (isAirAt)
                {
                    len_down++;
                    if (len_down >= min_len) generate_down = true;
                }
                else
                {
                    generate_down = false;
                    break;
                }
            }
        }

        // Proper genstep
        if (generate_down || generate_up)
        {
            if (generate_up && generate_down) generate_up = false;

            int baseGenStart = (generate_up) ? len_up - random.nextIntBetweenInclusive(0, 2) : len_down - random.nextIntBetweenInclusive(0, 2);
            int[] yPosSides = new int[]{
                    random.nextIntBetweenInclusive(2, 5),
                    random.nextIntBetweenInclusive(2, 5),
                    random.nextIntBetweenInclusive(2, 5),
                    random.nextIntBetweenInclusive(2, 5),
                    baseGenStart - random.nextIntBetweenInclusive(0, 2),
                    baseGenStart - random.nextIntBetweenInclusive(0, 2),
                    baseGenStart - random.nextIntBetweenInclusive(0, 2),
                    baseGenStart - random.nextIntBetweenInclusive(0, 2)
            };


            if (generate_up)
            {
                for (int i = 0; i <= len_up; i++)
                {
                    checkingPos = origin.above(i);
                    this.safeSetBlock(structWorld, checkingPos, ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[0]) this.safeSetBlock(structWorld, checkingPos.north(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[1]) this.safeSetBlock(structWorld, checkingPos.south(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[2]) this.safeSetBlock(structWorld, checkingPos.east(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[3]) this.safeSetBlock(structWorld, checkingPos.west(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[4]) this.safeSetBlock(structWorld, checkingPos.offset(1, 0, 1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[5]) this.safeSetBlock(structWorld, checkingPos.offset(1, 0, -1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[6]) this.safeSetBlock(structWorld, checkingPos.offset(-1, 0, -1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[7]) this.safeSetBlock(structWorld, checkingPos.offset(-1, 0, 1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);

                    if (i >= baseGenStart)
                    {
                        this.createRing(ModBlocks.EBONCORK.get().defaultBlockState(), structWorld, checkingPos, predicate, random, false, false);
                    }

                    if (i == len_up - 2) this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, checkingPos, predicate, random, true, false);
                    if (i == len_up - 1) this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, checkingPos, predicate, random, true, true);
                    if (i == len_up) this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, checkingPos, predicate, random, false, true);
                }

                this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, origin.above(len_up + 1), predicate, random, false, true);

                return true;
            }
            else
            {
                for (int i = 0; i <= len_down; i++)
                {
                    checkingPos = origin.below(i);
                    this.safeSetBlock(structWorld, checkingPos, ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[0]) this.safeSetBlock(structWorld, checkingPos.north(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[1]) this.safeSetBlock(structWorld, checkingPos.south(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[2]) this.safeSetBlock(structWorld, checkingPos.east(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[3]) this.safeSetBlock(structWorld, checkingPos.west(), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[4]) this.safeSetBlock(structWorld, checkingPos.offset(1, 0, 1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[5]) this.safeSetBlock(structWorld, checkingPos.offset(1, 0, -1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[6]) this.safeSetBlock(structWorld, checkingPos.offset(-1, 0, -1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);
                    if (i >= yPosSides[7]) this.safeSetBlock(structWorld, checkingPos.offset(-1, 0, 1), ModBlocks.EBONCORK.get().defaultBlockState(), predicate);

                    if (i >= baseGenStart)
                    {
                        this.createRing(ModBlocks.EBONCORK.get().defaultBlockState(), structWorld, checkingPos, predicate, random, false, false);
                    }

                    if (i == len_down - 2) this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, checkingPos, predicate, random, true, false);
                    if (i == len_down - 1) this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, checkingPos, predicate, random, true, true);
                    if (i == len_down) this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, checkingPos, predicate, random, false, true);
                }

                this.createRing(ModBlocks.CRAGULSTANE.get().defaultBlockState(), structWorld, origin.below(len_down + 1), predicate, random, false, true);

                return true;
            }
        }
        return false;
    }

    private void createRing(BlockState block, WorldGenLevel world, BlockPos pos, Predicate<BlockState> predicate, RandomSource random, boolean decay, boolean big)
    {
        boolean sm_corner1 = !decay || big || random.nextBoolean();
        boolean sm_corner2 = !decay || big || random.nextBoolean();
        boolean sm_corner3 = !decay || big || random.nextBoolean();
        boolean sm_corner4 = !decay || big || random.nextBoolean();

        // 3x3 ring
        this.safeSetBlock(world, pos, block, predicate);
        this.safeSetBlock(world, pos.north(1), block, predicate);
        this.safeSetBlock(world, pos.north(2), block, predicate);
        this.safeSetBlock(world, pos.east(1), block, predicate);
        this.safeSetBlock(world, pos.east(2), block, predicate);
        this.safeSetBlock(world, pos.south(1), block, predicate);
        this.safeSetBlock(world, pos.south(2), block, predicate);
        this.safeSetBlock(world, pos.west(1), block, predicate);
        this.safeSetBlock(world, pos.west(2), block, predicate);
        if (sm_corner1) this.safeSetBlock(world, pos.offset(1, 0, 1), block, predicate);
        if (sm_corner2) this.safeSetBlock(world, pos.offset(1, 0, -1), block, predicate);
        if (sm_corner3) this.safeSetBlock(world, pos.offset(-1, 0, -1), block, predicate);
        if (sm_corner4) this.safeSetBlock(world, pos.offset(-1, 0, 1), block, predicate);

        if (big)
        {
            boolean[] horribleFuckingArray = new boolean[]
            {
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean()),
                    (!decay || random.nextBoolean())
            };

            // Guaranteed
            this.safeSetBlock(world, pos.north(3), block, predicate);
            this.safeSetBlock(world, pos.east(3), block, predicate);
            this.safeSetBlock(world, pos.south(3), block, predicate);
            this.safeSetBlock(world, pos.west(3), block, predicate);
            this.safeSetBlock(world, pos.offset(2, 0, 1), block, predicate);
            this.safeSetBlock(world, pos.offset(2, 0, -1), block, predicate);
            this.safeSetBlock(world, pos.offset(-2, 0, 1), block, predicate);
            this.safeSetBlock(world, pos.offset(-2, 0, -1), block, predicate);
            this.safeSetBlock(world, pos.offset(1, 0, 2), block, predicate);
            this.safeSetBlock(world, pos.offset(1, 0, -2), block, predicate);
            this.safeSetBlock(world, pos.offset(-1, 0, 2), block, predicate);
            this.safeSetBlock(world, pos.offset(-1, 0, -2), block, predicate);

            // Decayables
            if (horribleFuckingArray[0]) this.safeSetBlock(world, pos.offset(3, 0, 1), block, predicate);
            if (horribleFuckingArray[1]) this.safeSetBlock(world, pos.offset(3, 0, -1), block, predicate);
            if (horribleFuckingArray[2]) this.safeSetBlock(world, pos.offset(-3, 0, 1), block, predicate);
            if (horribleFuckingArray[3]) this.safeSetBlock(world, pos.offset(-3, 0, -1), block, predicate);

            if (horribleFuckingArray[4]) this.safeSetBlock(world, pos.offset(1, 0, 3), block, predicate);
            if (horribleFuckingArray[5]) this.safeSetBlock(world, pos.offset(-1, 0, 3), block, predicate);
            if (horribleFuckingArray[6]) this.safeSetBlock(world, pos.offset(1, 0, -3), block, predicate);
            if (horribleFuckingArray[7]) this.safeSetBlock(world, pos.offset(-1, 0, -3), block, predicate);

            if (horribleFuckingArray[8]) this.safeSetBlock(world, pos.offset(-2, 0, 2), block, predicate);
            if (horribleFuckingArray[9]) this.safeSetBlock(world, pos.offset(-2, 0, -2), block, predicate);
            if (horribleFuckingArray[10]) this.safeSetBlock(world, pos.offset(2, 0, 2), block, predicate);
            if (horribleFuckingArray[11]) this.safeSetBlock(world, pos.offset(2, 0, -2), block, predicate);
        }
    }
}
