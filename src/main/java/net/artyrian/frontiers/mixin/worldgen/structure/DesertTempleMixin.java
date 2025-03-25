package net.artyrian.frontiers.mixin.worldgen.structure;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.DesertTempleGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DesertTempleGenerator.class)
public abstract class DesertTempleMixin extends StructurePieceMixin
{
    @Inject(method = "generate", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/structure/DesertTempleGenerator;generateBasement(Lnet/minecraft/world/StructureWorldAccess;Lnet/minecraft/util/math/BlockBox;)V",
            shift = At.Shift.AFTER)
    )
    private void addNewFrontiersGen(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci)
    {
        this.generateFrontiersUniqueCatacombs(world, chunkBox);
    }

    @Unique
    private void generateFrontiersUniqueCatacombs(StructureWorldAccess world, BlockBox chunkBox)
    {
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
                this.addBlock(world, SANDSTONE, 16, y, j, chunkBox);
                this.addBlock(world, SANDSTONE, 24, y, j, chunkBox);
            }

            // Box Middle - also places Sandstones
            for (int j = 17; j < 24; j++)
            {
                this.addBlock(world, SANDSTONE, j, y, 7, chunkBox);
                this.addBlock(world, AIR, j, y, 6, chunkBox);
                this.addBlock(world, AIR, j, y, 5, chunkBox);
                this.addBlock(world, AIR, j, y, 4, chunkBox);
                this.addBlock(world, AIR, j, y, 3, chunkBox);
                this.addBlock(world, AIR, j, y, 2, chunkBox);
                this.addBlock(world, AIR, j, y, 1, chunkBox);
                this.addBlock(world, AIR, j, y, 0, chunkBox);
                this.addBlock(world, SANDSTONE, j, y, -1, chunkBox);
            }
        }
    }
}
