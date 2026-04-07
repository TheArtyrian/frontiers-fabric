package net.artyrian.frontiers.mixin.worldgen.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.WarpedWartBlock;
import net.artyrian.frontiers.mixin.worldgen.StructureMixin;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.core.BlockBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BastionPieces;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(JigsawStructure.class)
public abstract class JigsawStructMixin extends StructureMixin
{
    @Shadow @Final private Holder<StructureTemplatePool> startPool;

    @Override
    public void frontiers$postPlaceHook(WorldGenLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer pieces, CallbackInfo ci)
    {
        if (startPool.is(BastionPieces.START))
        {
            if (Frontiers.CONFIG.doBastionRework())
            {
                BlockBox box = new BlockBox(
                        new BlockPos(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ()),
                        new BlockPos(boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ())
                );

                box.forEach((pos) -> {
                    BlockState cache = level.getBlockState(pos);
                    if (cache.is(Blocks.NETHER_WART))
                    {
                        Optional<Integer> age = cache.getOptionalValue(NetherWartBlock.AGE);
                        age.ifPresent(axis ->
                                    level.setBlock(
                                            pos,
                                            FRBlocks.WARPED_WART.get().defaultBlockState().setValue(WarpedWartBlock.AGE, Math.clamp(
                                                age.get(),
                                                0,
                                                WarpedWartBlock.MAX_AGE)
                                            ),
                                            3)
                            );
                    }
                });
            }
        }
    }
}
