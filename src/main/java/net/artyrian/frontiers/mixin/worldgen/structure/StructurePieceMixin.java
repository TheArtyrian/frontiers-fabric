package net.artyrian.frontiers.mixin.worldgen.structure;

import net.minecraft.block.BlockState;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StructurePiece.class)
public abstract class StructurePieceMixin
{
    @Shadow protected abstract void fillWithOutline(StructureWorldAccess world, BlockBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, BlockState outline, BlockState inside, boolean cantReplaceAir);
    @Shadow protected abstract void fillWithOutline(StructureWorldAccess world, BlockBox box, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean cantReplaceAir, Random random, StructurePiece.BlockRandomizer randomizer);
    @Shadow protected abstract void fillWithOutline(StructureWorldAccess world, BlockBox box, BlockBox fillBox, BlockState outline, BlockState inside, boolean cantReplaceAir);
    @Shadow protected abstract void addBlock(StructureWorldAccess world, BlockState block, int x, int y, int z, BlockBox box);
}
