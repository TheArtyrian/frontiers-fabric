package net.artyrian.frontiers.world.structure.white_tower;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.world.structure.ModStructureType;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.ChunkRandom;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class WhiteTowerStructure extends Structure
{
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 15;
    private static final int BASIC_HEIGHT = 6;
    private static final int XZ_SIZE = 25;
    public static final MapCodec<WhiteTowerStructure> CODEC = createCodec(WhiteTowerStructure::new);

    public WhiteTowerStructure(Structure.Config config)
    {
        super(config);
    }

    @Override
    public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context)
    {
        return Optional.of(new Structure.StructurePosition(context.chunkPos().getStartPos().add(0, -16, 0), collector -> addPieces(collector, context)));
    }

    private static void addPieces(StructurePiecesCollector collector, Structure.Context context)
    {
        ChunkRandom rando = context.random();
        BlockPos inputPos = context.chunkPos().getStartPos().add(0, 64, 0);

        collector.addPiece(new WhiteTowerGenerator.Bottom(context.structureTemplateManager(), inputPos, BlockRotation.NONE, BlockMirror.NONE));
        inputPos = inputPos.add(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.CLOCKWISE_180, BlockMirror.NONE));
        inputPos = inputPos.add(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.NONE, BlockMirror.NONE));
        inputPos = inputPos.add(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.CLOCKWISE_180, BlockMirror.NONE));
        inputPos = inputPos.add(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.NONE, BlockMirror.NONE));
        inputPos = inputPos.add(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.CLOCKWISE_180, BlockMirror.NONE));
        inputPos = inputPos.add(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.NONE, BlockMirror.NONE));
        inputPos = inputPos.add(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.CLOCKWISE_180, BlockMirror.NONE));
        inputPos = inputPos.add(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.NONE, BlockMirror.NONE));
        inputPos = inputPos.add(XZ_SIZE, BASIC_HEIGHT, XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Piece(
                context.structureTemplateManager(), "preset" + String.valueOf(rando.nextBetween(MIN_VAL, MAX_VAL)), inputPos, BlockRotation.CLOCKWISE_180, BlockMirror.NONE));
        inputPos = inputPos.add(-XZ_SIZE, BASIC_HEIGHT, -XZ_SIZE);
        collector.addPiece(new WhiteTowerGenerator.Entry(
                context.structureTemplateManager(), inputPos, BlockRotation.NONE, BlockMirror.NONE));
    }

    @Override
    public StructureType<?> getType() { return ModStructureType.WHITE_TOWER; }
}
