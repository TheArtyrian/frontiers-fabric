package net.artyrian.frontiers.mixin;

import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.vertisoft.vectorlib.agnostic.util.VectorOpcode;
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

@Mixin(NoteBlockInstrument.class)
public abstract class NoteBlockMixinFabric
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static NoteBlockInstrument newNoteType(String internalName,
                                                    int ordinal,
                                                    String name,
                                                    Holder<SoundEvent> sound,
                                                    NoteBlockInstrument.Type type)
    {
        throw new AssertionError();
    }

    // Get note block field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    NoteBlockInstrument[] $VALUES;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = VectorOpcode.PUTSTATIC,
            target = "Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;$VALUES:[Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;",
            shift = At.Shift.AFTER))
    private static void addCustomNotes(CallbackInfo ci)
    {
        // Get note sound list.
        var notesounds = new ArrayList<>(Arrays.asList($VALUES));
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
        FRRegistries.NoteBlockInst.FRONTIERS_LOG_DRUM = frontiers_logdrum;
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
        FRRegistries.NoteBlockInst.FRONTIERS_ICE_BELL = frontiers_icebell;
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
        FRRegistries.NoteBlockInst.FRONTIERS_HARPSICHORD = frontiers_harpsichord;
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
        FRRegistries.NoteBlockInst.FRONTIERS_STEEL_DRUM = frontiers_steeldrum;
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
        FRRegistries.NoteBlockInst.FRONTIERS_ROBOLUNG = frontiers_robolung;
        notesounds.add(frontiers_robolung);
        i++;

        // Frontiers: Jeskola
        var frontiers_jeskola = newNoteType(
                "FRONTIERS_JESKOLA",
                last.ordinal() + i,
                "frontiers_jeskola",
                ModSounds.BLOCK_NOTE_BLOCK_JESKOLA,
                NoteBlockInstrument.Type.BASE_BLOCK
        );
        FRRegistries.NoteBlockInst.FRONTIERS_JESKOLA = frontiers_jeskola;
        notesounds.add(frontiers_jeskola);
        i++;

        // Inject.
        $VALUES = notesounds.toArray(new NoteBlockInstrument[0]);
    }
}
