package net.artyrian.frontiers.mixin.worldgen.structure;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StructurePiece.class)
public abstract class StructurePieceMixin
{
    @Shadow protected abstract void generateBox(WorldGenLevel world, BoundingBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState outline, BlockState inside, boolean cantReplaceAir);
    @Shadow protected abstract void generateBox(WorldGenLevel world, BoundingBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean cantReplaceAir, RandomSource random, StructurePiece.BlockSelector randomizer);
    @Shadow protected abstract void generateBox(WorldGenLevel world, BoundingBox box, BoundingBox fillBox, BlockState outline, BlockState inside, boolean cantReplaceAir);
    @Shadow protected abstract void placeBlock(WorldGenLevel world, BlockState block, int x, int y, int z, BoundingBox box);
}
