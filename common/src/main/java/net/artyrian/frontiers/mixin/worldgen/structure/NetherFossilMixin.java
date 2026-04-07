package net.artyrian.frontiers.mixin.worldgen.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.worldgen.StructureMixin;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.minecraft.core.BlockBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.structures.NetherFossilStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(NetherFossilStructure.class)
public abstract class NetherFossilMixin extends StructureMixin
{
    @Override
    public void frontiers$postPlaceHook(WorldGenLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer pieces, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doNetherFossilRework())
        {
            BlockBox box = new BlockBox(
                    new BlockPos(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ()),
                    new BlockPos(boundingBox.maxX(), boundingBox.maxY(), boundingBox.maxZ())
            );

            box.forEach((pos) -> {
                BlockState cache = level.getBlockState(pos);
                if (cache.is(Blocks.BONE_BLOCK))
                {
                    Optional<Direction.Axis> axi = cache.getOptionalValue(RotatedPillarBlock.AXIS);
                    axi.ifPresent(axis ->
                            level.setBlock(pos, FRBlocks.ONYX_BONE_BLOCK.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis), 3)
                    );
                }
            });
        }
    }
}
