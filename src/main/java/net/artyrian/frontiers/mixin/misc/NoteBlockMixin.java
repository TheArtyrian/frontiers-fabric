package net.artyrian.frontiers.mixin.misc;

import net.artyrian.frontiers.misc.ModNoteBlockInstrument;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

// Mixes in custom rarities from net.artyrian.frontiers.misc.ModNoteBlockInstrument.
// ID OF NOTEBLOCK FIELD: field_12652
@Mixin(NoteBlockInstrument.class)
public abstract class NoteBlockMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static NoteBlockInstrument newNoteType(String internalName,
                                                    int ordinal,
                                                    String name,
                                                    RegistryEntry<SoundEvent> sound,
                                                    NoteBlockInstrument.Type type)
    {
        throw new AssertionError();
    }

    // Get note block field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    NoteBlockInstrument[] field_12652;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/block/enums/NoteBlockInstrument;field_12652:[Lnet/minecraft/block/enums/NoteBlockInstrument;",
            shift = At.Shift.AFTER))
    private static void addCustomNotes(CallbackInfo ci)
    {
        // Get note sound list.
        var notesounds = new ArrayList<>(Arrays.asList(field_12652));
        var last = notesounds.get(notesounds.size() - 1);
        var i = 1;

        // Frontiers: Log Drum
        var frontiers_logdrum = newNoteType(
                "FRONTIERS_LOG_DRUM",
                last.ordinal() + i,
                "frontiers_log_drum",
                ModSounds.BLOCK_NOTE_BLOCK_LOG_DRUM,
                NoteBlockInstrument.Type.BASE_BLOCK
        );
        ModNoteBlockInstrument.FRONTIERS_LOG_DRUM = frontiers_logdrum;
        notesounds.add(frontiers_logdrum);
        i++;

        // Frontiers: Ice Bell
        var frontiers_icebell = newNoteType(
                "FRONTIERS_ICE_BELL",
                last.ordinal() + i,
                "frontiers_ice_bell",
                ModSounds.BLOCK_NOTE_BLOCK_ICE_BELL,
                NoteBlockInstrument.Type.BASE_BLOCK
        );
        ModNoteBlockInstrument.FRONTIERS_ICE_BELL = frontiers_icebell;
        notesounds.add(frontiers_icebell);
        i++;

        // Frontiers: Harpsichord
        var frontiers_harpsichord = newNoteType(
                "FRONTIERS_HARPSICHORD",
                last.ordinal() + i,
                "frontiers_harpsichord",
                ModSounds.BLOCK_NOTE_BLOCK_HARPSICHORD,
                NoteBlockInstrument.Type.BASE_BLOCK
        );
        ModNoteBlockInstrument.FRONTIERS_HARPSICHORD = frontiers_harpsichord;
        notesounds.add(frontiers_harpsichord);
        i++;

        // Frontiers: Steel Drum
        var frontiers_steeldrum = newNoteType(
                "FRONTIERS_STEEL_DRUM",
                last.ordinal() + i,
                "frontiers_steel_drum",
                ModSounds.BLOCK_NOTE_BLOCK_STEEL_DRUM,
                NoteBlockInstrument.Type.BASE_BLOCK
        );
        ModNoteBlockInstrument.FRONTIERS_STEEL_DRUM = frontiers_steeldrum;
        notesounds.add(frontiers_steeldrum);
        i++;

        // Frontiers: RoboLung
        var frontiers_robolung = newNoteType(
                "FRONTIERS_ROBOLUNG",
                last.ordinal() + i,
                "frontiers_robolung",
                ModSounds.BLOCK_NOTE_BLOCK_ROBOLUNG,
                NoteBlockInstrument.Type.BASE_BLOCK
        );
        ModNoteBlockInstrument.FRONTIERS_ROBOLUNG = frontiers_robolung;
        notesounds.add(frontiers_robolung);
        i++;

        // Inject.
        field_12652 = notesounds.toArray(new NoteBlockInstrument[0]);
    }
}
