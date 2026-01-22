package net.artyrian.frontiers.mixin.worldgen.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
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
    @Inject(method = "postProcess", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/levelgen/structure/structures/SwampHutPiece;spawnCat(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;)V")
    )
    private void injectOhSoSpookyCheck(WorldGenLevel world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox chunkBox, ChunkPos chunkPos, BlockPos pivot, CallbackInfo ci)
    {
        boolean can_replace = (Frontiers.EVENTS.IS_HALLOWEEN) || (random.nextFloat() <= 0.20);
        if (can_replace) this.placeBlock(world, ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get().defaultBlockState(), 1, 3, 5, chunkBox);
    }
}
