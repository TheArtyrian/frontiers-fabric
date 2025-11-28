package net.artyrian.frontiers.world.structure;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.world.structure.white_tower.WhiteTowerGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;

import java.util.Locale;

public class ModStructurePieceType
{
    public static final StructurePieceType WHITE_TOWER_ENTRY = registerType(WhiteTowerGenerator.Entry::new, "FNT_WTEntry");
    public static final StructurePieceType WHITE_TOWER_PIECE = registerType(WhiteTowerGenerator.Piece::new, "FNT_WTPiece");
    public static final StructurePieceType WHITE_TOWER_BOTTOM = registerType(WhiteTowerGenerator.Bottom::new, "FNT_WTBottom");

    private static StructurePieceType register(StructurePieceType type, String id)
    {
        return Registry.register(Registries.STRUCTURE_PIECE, Identifier.of(Frontiers.MOD_ID, id.toLowerCase(Locale.ROOT)), type);
    }

    private static StructurePieceType registerSimple(StructurePieceType.Simple type, String id)
    {
        return register(type, id);
    }

    private static StructurePieceType registerType(StructurePieceType.ManagerAware type, String id)
    {
        return register(type, id);
    }

    public static void registerStrPieceType()
    {

    }
}
