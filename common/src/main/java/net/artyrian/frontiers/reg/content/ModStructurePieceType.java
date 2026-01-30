package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.world.structure.white_tower.WhiteTowerGenerator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.vertisoft.vectorlib.VectorLib;

import java.util.Locale;
import java.util.function.Supplier;

public class ModStructurePieceType
{
    public static final Supplier<StructurePieceType> WHITE_TOWER_ENTRY = registerType("FNT_WTEntry", () -> WhiteTowerGenerator.Entry::new);
    public static final Supplier<StructurePieceType> WHITE_TOWER_PIECE = registerType("FNT_WTPiece", () -> WhiteTowerGenerator.Piece::new);
    public static final Supplier<StructurePieceType> WHITE_TOWER_BOTTOM = registerType("FNT_WTBottom", () -> WhiteTowerGenerator.Bottom::new);

    private static <T extends StructurePieceType> Supplier<T> register(String id, Supplier<T> type)
    {
        return VectorLib.REGISTRY.registerStructurePiece(Frontiers.MOD_ID, id.toLowerCase(Locale.ROOT), type);
    }

    private static <T extends StructurePieceType> Supplier<T> registerSimple(String id, Supplier<StructurePieceType.ContextlessType> type)
    {
        return (Supplier<T>)register(id, type);
    }

    private static <T extends StructurePieceType> Supplier<T> registerType(String id, Supplier<StructurePieceType.StructureTemplateType> type)
    {
        return (Supplier<T>)register(id, type);
    }

    public static void registerStrPieceType()
    {

    }
}
