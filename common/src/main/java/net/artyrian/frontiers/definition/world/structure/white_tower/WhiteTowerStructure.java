package net.artyrian.frontiers.definition.world.structure.white_tower;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModStructureType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Objects;
import java.util.Optional;

public class WhiteTowerStructure extends Structure
{
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 15;
    private static final int BASIC_HEIGHT = 6;
    private static final int XZ_SIZE = 25;
    public static final MapCodec<WhiteTowerStructure> CODEC = simpleCodec(WhiteTowerStructure::new);

    public WhiteTowerStructure(Structure.StructureSettings config)
    {
        super(config);
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context)
    {
        BlockPos prePos = context.chunkPos().getWorldPosition();
        int y = context.chunkGenerator()
                .getFirstOccupiedHeight(prePos.getX(), prePos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
        y = Math.clamp(y - (BASIC_HEIGHT * 8), 32, 160);
        BlockPos truePos = new BlockPos(prePos.getX(), y, prePos.getZ());

        return Optional.of(new Structure.GenerationStub(truePos, collector -> this.addPieces(collector, context, truePos)));
    }

    @Override
    public void afterPlace(WorldGenLevel level, StructureManager structureManager, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, PiecesContainer pieces)
    {
        super.afterPlace(level, structureManager, chunkGenerator, random, boundingBox, chunkPos, pieces);
    }

    private void addPieces(StructurePiecesBuilder collector, Structure.GenerationContext context, BlockPos inputPos)
    {
        Objects.requireNonNull(collector);
        WorldgenRandom rando = context.random();

        collector.addPiece(new WhiteTowerGenerator.Bottom(context.structureTemplateManager(), inputPos, Rotation.NONE, Mirror.NONE));
        inputPos = inputPos.offset(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.CLOCKWISE_180, Mirror.NONE));
        inputPos = inputPos.offset(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.NONE, Mirror.NONE));
        inputPos = inputPos.offset(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.CLOCKWISE_180, Mirror.NONE));
        inputPos = inputPos.offset(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.NONE, Mirror.NONE));
        inputPos = inputPos.offset(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.CLOCKWISE_180, Mirror.NONE));
        inputPos = inputPos.offset(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.NONE, Mirror.NONE));
        inputPos = inputPos.offset(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.CLOCKWISE_180, Mirror.NONE));
        inputPos = inputPos.offset(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.NONE, Mirror.NONE));
        inputPos = inputPos.offset(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextIntBetweenInclusive(MIN_VAL, MAX_VAL)), inputPos, Rotation.CLOCKWISE_180, Mirror.NONE));
        inputPos = inputPos.offset(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Entry(
                context.structureTemplateManager(), inputPos, Rotation.NONE, Mirror.NONE));
    }

    @Override
    public StructureType<?> type() { return ModStructureType.WHITE_TOWER.get(); }
}
