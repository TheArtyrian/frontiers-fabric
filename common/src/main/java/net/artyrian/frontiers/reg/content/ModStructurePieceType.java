package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.world.structure.white_tower.WhiteTowerGenerator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import java.util.Locale;

public class ModStructurePieceType
{
    public static final StructurePieceType WHITE_TOWER_ENTRY = registerType(WhiteTowerGenerator.Entry::new, "FNT_WTEntry");
    public static final StructurePieceType WHITE_TOWER_PIECE = registerType(WhiteTowerGenerator.Piece::new, "FNT_WTPiece");
    public static final StructurePieceType WHITE_TOWER_BOTTOM = registerType(WhiteTowerGenerator.Bottom::new, "FNT_WTBottom");

    private static StructurePieceType register(StructurePieceType type, String id)
    {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, id.toLowerCase(Locale.ROOT)), type);
    }

    private static StructurePieceType registerSimple(StructurePieceType.ContextlessType type, String id)
    {
        return register(type, id);
    }

    private static StructurePieceType registerType(StructurePieceType.StructureTemplateType type, String id)
    {
        return register(type, id);
    }

    public static void registerStrPieceType()
    {

    }
}
