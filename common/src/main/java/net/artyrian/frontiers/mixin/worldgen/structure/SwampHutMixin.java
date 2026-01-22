package net.artyrian.frontiers.mixin.worldgen.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.SwampHutPiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwampHutPiece.class)
public abstract class SwampHutMixin extends StructurePieceMixin
{
    @Inject(method = "generate", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/structure/SwampHutGenerator;spawnCat(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/BlockBox;)V")
    )
    private void injectOhSoSpookyCheck(WorldGenLevel world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci)
    {
        boolean can_replace = (Frontiers.EVENTS.IS_HALLOWEEN) || (random.nextFloat() <= 0.20);
        if (can_replace) this.addBlock(world, ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.defaultBlockState(), 1, 3, 5, chunkBox);
    }
}
