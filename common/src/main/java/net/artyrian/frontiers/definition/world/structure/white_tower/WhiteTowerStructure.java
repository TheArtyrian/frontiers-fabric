package net.artyrian.frontiers.definition.world.structure.white_tower;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.content.ModStructureType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
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
        return Optional.of(new Structure.GenerationStub(context.chunkPos().getWorldPosition().offset(0, -16, 0), collector -> addPieces(collector, context)));
    }

    private static void addPieces(StructurePiecesBuilder collector, Structure.GenerationContext context)
    {
        WorldgenRandom rando = context.random();
        BlockPos inputPos = context.chunkPos().getWorldPosition().offset(0, 64, 0);

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
    public StructureType<?> type() { return ModStructureType.WHITE_TOWER; }
}
