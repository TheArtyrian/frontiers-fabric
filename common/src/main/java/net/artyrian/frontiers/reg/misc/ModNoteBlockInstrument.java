package net.artyrian.frontiers.reg.misc;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

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
    public static NoteBlockInstrument FRONTIERS_JESKOLA;
}
