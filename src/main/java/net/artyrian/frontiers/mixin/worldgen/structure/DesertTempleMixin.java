package net.artyrian.frontiers.mixin.worldgen.structure;

import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.structure.DesertTempleGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DesertTempleGenerator.class)
public abstract class DesertTempleMixin extends StructurePieceMixin
{
    @ModifyConstant(method = "<init>(Lnet/minecraft/util/math/random/Random;II)V", constant = @Constant(intValue = 15))
    private static int modDep(int og) { return 48; }

    @Inject(method = "generate", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/structure/DesertTempleGenerator;generateBasement(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/util/math/BlockBox;)V",
            shift = At.Shift.AFTER)
    )
    private void addNewFrontiersGen(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci)
    {
        int action1 = random.nextInt(100);
        int action2 = random.nextInt(100);
        boolean doBoss = (action1 >= 90);
        boolean doCatacombs = (action2 >= 60);

        if (doBoss) this.generateFrontiersUniquePyramidBossAltar(world, chunkBox);
        if (doCatacombs) this.generateFrontiersUniqueCatacombs(world, chunkBox);
    }

    @Unique
    private void generateFrontiersUniquePyramidBossAltar(StructureWorldAccess world, BlockBox chunkBox)
    {
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 10, 4, 7, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 10, 4, 8, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 9, 4, 9, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 11, 4, 9, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 8, 4, 10, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 12, 4, 10, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 7, 4, 10, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 13, 4, 10, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 9, 4, 11, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 11, 4, 11, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 10, 4, 12, chunkBox);
        this.addBlock(world, Blocks.ORANGE_TERRACOTTA.getDefaultState(), 10, 4, 13, chunkBox);
        this.addBlock(world, Blocks.BLUE_TERRACOTTA.getDefaultState(), 10, 4, 10, chunkBox);

        this.addBlock(world, Blocks.DIRT.getDefaultState(), 10, 5, 10, chunkBox);
    }

    @Unique
    private void generateFrontiersUniqueCatacombs(StructureWorldAccess world, BlockBox chunkBox)
    {
        chunkBox = chunkBox.expand(64);

        BlockState SANDSTONE = Blocks.SANDSTONE.getDefaultState();
        BlockState SANDSTONE_STAIRS = Blocks.SANDSTONE_STAIRS.getDefaultState();
        BlockState CUT_SANDSTONE = Blocks.CUT_SANDSTONE.getDefaultState();
        BlockState CHISELED_SANDSTONE = Blocks.CHISELED_SANDSTONE.getDefaultState();
        BlockState AIR = Blocks.AIR.getDefaultState();
        BlockState SAND = Blocks.SAND.getDefaultState();

        BlockPos pos = new BlockPos(13, 0, 4);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        // Stairs Down
        this.addBlock(world, SANDSTONE, 13, y, 4, chunkBox);
        this.addBlock(world, SAND, 14, y, 4, chunkBox);
        this.addBlock(world, SAND, 15, y, 4, chunkBox);
        this.addBlock(world, SAND, 16, y, 4, chunkBox);
        y--;
        this.addBlock(world, SANDSTONE_STAIRS.rotate(BlockRotation.COUNTERCLOCKWISE_90), 14, y, 4, chunkBox);
        this.addBlock(world, SAND, 15, y, 4, chunkBox);
        this.addBlock(world, SAND, 16, y, 4, chunkBox);
        this.addBlock(world, AIR, 17, y, 4, chunkBox);
        y--;
        this.addBlock(world, SANDSTONE_STAIRS.rotate(BlockRotation.COUNTERCLOCKWISE_90), 15, y, 4, chunkBox);
        this.addBlock(world, SAND, 16, y, 4, chunkBox);
        this.addBlock(world, AIR, 17, y, 4, chunkBox);
        this.addBlock(world, AIR, 18, y, 4, chunkBox);
        y--;
        this.addBlock(world, SANDSTONE_STAIRS.rotate(BlockRotation.COUNTERCLOCKWISE_90), 16, y, 4, chunkBox);
        this.addBlock(world, AIR, 17, y, 4, chunkBox);
        this.addBlock(world, AIR, 18, y, 4, chunkBox);
        this.addBlock(world, AIR, 19, y, 4, chunkBox);
        y--;
        this.addBlock(world, SANDSTONE_STAIRS.rotate(BlockRotation.COUNTERCLOCKWISE_90), 17, y, 4, chunkBox);
        this.addBlock(world, AIR, 18, y, 4, chunkBox);
        this.addBlock(world, AIR, 19, y, 4, chunkBox);
        this.addBlock(world, AIR, 20, y, 4, chunkBox);

        // Roofing tiles
        this.addBlock(world, AIR, 20, y, 4, chunkBox);
        this.addBlock(world, SANDSTONE, 21, y, 4, chunkBox);
        this.addBlock(world, SANDSTONE, 21, y, 3, chunkBox);
        this.addBlock(world, SANDSTONE, 21, y, 2, chunkBox);
        y--;

        // Shaft inwards
        for (int i = y; i >= (y - 18); i--)
        {
            // Wall Front + Back
            for (int j = 5; j > 0; j--)
            {
                this.addBlock(world, SANDSTONE, 18, i, j, chunkBox);
                this.addBlock(world, SANDSTONE, 22, i, j, chunkBox);
            }

            // Middle
            for (int j = 19; j < 22; j++)
            {
                this.addBlock(world, SANDSTONE, j, i, 5, chunkBox);
                this.addBlock(world, AIR, j, i, 4, chunkBox);
                this.addBlock(world, (j == 20) ? SANDSTONE : AIR, j, i, 3, chunkBox);
                this.addBlock(world, AIR, j, i, 2, chunkBox);
                this.addBlock(world, SANDSTONE, j, i, 1, chunkBox);
            }
        }
        this.addBlock(world, SANDSTONE_STAIRS.rotate(BlockRotation.COUNTERCLOCKWISE_90), 18, y, 4, chunkBox);
        this.frontiersGenerateFoyer(world, chunkBox, y - 19);
    }

    @Unique
    private void frontiersGenerateFoyer(StructureWorldAccess world, BlockBox chunkBox, int y_start)
    {
        BlockState SANDSTONE = Blocks.SANDSTONE.getDefaultState();
        BlockState ORANGE_TERRACOTTA = Blocks.ORANGE_TERRACOTTA.getDefaultState();
        BlockState BLUE_TERRACOTTA = Blocks.BLUE_TERRACOTTA.getDefaultState();
        BlockState CUT_SANDSTONE = Blocks.CUT_SANDSTONE.getDefaultState();
        BlockState CHISELED_SANDSTONE = Blocks.CHISELED_SANDSTONE.getDefaultState();
        BlockState SANDSTONE_STAIRS = Blocks.SANDSTONE_STAIRS.getDefaultState();
        BlockState AIR = Blocks.AIR.getDefaultState();
        BlockState SAND = Blocks.SAND.getDefaultState();
        int y = y_start;

        // Rim Front + Back
        for (int j =  6; j > -1; j--)
        {
            this.addBlock(world, SANDSTONE, 17, y, j, chunkBox);
            this.addBlock(world, SANDSTONE, 23, y, j, chunkBox);
        }

        // Rim Middle
        for (int j = 18; j < 23; j++)
        {
            this.addBlock(world, SANDSTONE, j, y, 6, chunkBox);
            this.addBlock(world, AIR, j, y, 5, chunkBox);
            this.addBlock(world, AIR, j, y, 4, chunkBox);
            this.addBlock(world, (j == 20) ? SANDSTONE : AIR, j, y, 3, chunkBox);
            this.addBlock(world, AIR, j, y, 2, chunkBox);
            this.addBlock(world, AIR, j, y, 1, chunkBox);
            this.addBlock(world, SANDSTONE, j, y, 0, chunkBox);
        }
        y--;

        // Inner box
        for (int i = -20; i > -28; i--)
        {
            // Box Front + Back
            for (int j = 7; j > -2; j--)
            {
                boolean ignore = (i < -23 && i > -27) && (j <= 4 && j >= 2);

                this.addBlock(world, (ignore) ? AIR : SANDSTONE, 16, y, j, chunkBox);
                this.addBlock(world, (ignore) ? AIR : SANDSTONE, 24, y, j, chunkBox);
            }

            // Box Middle - also places Sandstones
            for (int j = 17; j < 24; j++)
            {
                boolean ignore = (i < -23 && i > -27) && (j >= 19 && j <= 21);
                boolean floor = (i == -27);

                this.addBlock(world, ignore ? AIR : SANDSTONE, j, y, 7, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 6, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 5, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 4, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 3, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 2, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 1, chunkBox);
                this.addBlock(world, floor ? SANDSTONE : AIR, j, y, 0, chunkBox);
                this.addBlock(world, ignore ? AIR : SANDSTONE, j, y, -1, chunkBox);
            }
            y--;
        }

        // Decor (bottom)
        int bottom = y + 1;
        int top = bottom + 4;

        // Desert Rose (Bottom)
        this.addBlock(world, BLUE_TERRACOTTA, 20, bottom, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 19, bottom, 2, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 21, bottom, 2, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 19, bottom, 4, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 21, bottom, 4, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 18, bottom, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 17, bottom, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 22, bottom, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 23, bottom, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 20, bottom, 5, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 20, bottom, 6, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 20, bottom, 1, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 20, bottom, 0, chunkBox);

        // Desert Rose + Wraps (Top)
        this.addBlock(world, BLUE_TERRACOTTA, 20, top, 3, chunkBox);
        this.addBlock(world, SANDSTONE, 20, top + 1, 3, chunkBox);
        this.addBlock(world, SANDSTONE, 20, top + 2, 3, chunkBox);
        this.addBlock(world, SANDSTONE, 20, top + 3, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 19, top, 2, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 21, top, 2, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 19, top, 4, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 21, top, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 20, top, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 20, top, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, top, 3, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, top, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 18, top, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 22, top, 3, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 20, top, 5, chunkBox);
        this.addBlock(world, ORANGE_TERRACOTTA, 20, top, 1, chunkBox);

        // Corner 1
        this.addBlock(world, CUT_SANDSTONE, 17, top, 3, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 17, top + 1, 3, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 17, top + 2, 3, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 17, top + 3, 3, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 17, bottom + 1, 2, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 17, bottom + 2, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 17, bottom + 3, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 17, top, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 18, top, 2, chunkBox);

        // Corner 1 - Stairwell 1
        this.addBlock(world, SANDSTONE, 17, bottom + 1, 1, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 2, 1, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 3, 1, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 1, 0, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 2, 0, chunkBox);
        this.addBlock(world, SANDSTONE, 18, bottom + 1, 0, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.NORTH), 17, top, 1, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.WEST), 17, bottom + 3, 0, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.SOUTH), 18, bottom + 2, 0, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.SOUTH), 18, bottom + 1, 1, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 17, bottom + 1, 4, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 17, bottom + 2, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 17, bottom + 3, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 17, top, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 18, top, 4, chunkBox);

        // Corner 1 - Stairwell 2
        this.addBlock(world, SANDSTONE, 17, bottom + 1, 5, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 2, 5, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 3, 5, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 1, 6, chunkBox);
        this.addBlock(world, SANDSTONE, 17, bottom + 2, 6, chunkBox);
        this.addBlock(world, SANDSTONE, 18, bottom + 1, 6, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.SOUTH), 17, top, 5, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.WEST), 17, bottom + 3, 6, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.NORTH), 18, bottom + 2, 6, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.NORTH), 18, bottom + 1, 5, chunkBox);
        // Corner 2
        this.addBlock(world, CUT_SANDSTONE, 23, top, 3, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 23, top + 1, 3, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 23, top + 2, 3, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 23, top + 3, 3, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 23, bottom + 1, 2, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 23, bottom + 2, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 23, bottom + 3, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 23, top, 2, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 22, top, 2, chunkBox);

        // Corner 2 - Stiarwell 1
        this.addBlock(world, SANDSTONE, 23, bottom + 1, 1, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 2, 1, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 3, 1, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 1, 0, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 2, 0, chunkBox);
        this.addBlock(world, SANDSTONE, 22, bottom + 1, 0, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.NORTH), 23, top, 1, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.EAST), 23, bottom + 3, 0, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.SOUTH), 22, bottom + 2, 0, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.SOUTH), 22, bottom + 1, 1, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 23, bottom + 1, 4, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 23, bottom + 2, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 23, bottom + 3, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 23, top, 4, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 22, top, 4, chunkBox);

        // Corner 2 - Stairwell 2
        this.addBlock(world, SANDSTONE, 23, bottom + 1, 5, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 2, 5, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 3, 5, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 1, 6, chunkBox);
        this.addBlock(world, SANDSTONE, 23, bottom + 2, 6, chunkBox);
        this.addBlock(world, SANDSTONE, 22, bottom + 1, 6, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.SOUTH), 23, top, 5, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.EAST), 23, bottom + 3, 6, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.NORTH), 22, bottom + 2, 6, chunkBox);
        this.addBlock(world, SANDSTONE_STAIRS.with(StairsBlock.FACING, Direction.NORTH), 22, bottom + 1, 5, chunkBox);

        // Corner 3
        this.addBlock(world, CUT_SANDSTONE, 20, top, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 20, top + 1, 6, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 20, top + 2, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 20, top + 3, 6, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 19, bottom + 1, 6, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 19, bottom + 2, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, bottom + 3, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, top, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, top, 5, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 21, bottom + 1, 6, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 21, bottom + 2, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, bottom + 3, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, top, 6, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, top, 5, chunkBox);
        // Corner 4
        this.addBlock(world, CUT_SANDSTONE, 20, top, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 20, top + 1, 0, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 20, top + 2, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 20, top + 3, 0, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 19, bottom + 1, 0, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 19, bottom + 2, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, bottom + 3, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, top, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 19, top, 1, chunkBox);

        this.addBlock(world, CUT_SANDSTONE, 21, bottom + 1, 0, chunkBox);
        this.addBlock(world, CHISELED_SANDSTONE, 21, bottom + 2, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, bottom + 3, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, top, 0, chunkBox);
        this.addBlock(world, CUT_SANDSTONE, 21, top, 1, chunkBox);
    }
}
