package net.artyrian.frontiers.misc;

import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.client.gui.hud.InGameHud;

// Loads Note Block types
public class ModNoteBlockInstrument
{
    static
    {
        NoteBlockInstrument.values();        // Ensure class is loaded.
    }

    public static NoteBlockInstrument FRONTIERS_LOG_DRUM;
    public static NoteBlockInstrument FRONTIERS_ICE_BELL;
    public static NoteBlockInstrument FRONTIERS_HARPSICHORD;
    public static NoteBlockInstrument FRONTIERS_STEEL_DRUM;
    public static NoteBlockInstrument FRONTIERS_ROBOLUNG;
}
