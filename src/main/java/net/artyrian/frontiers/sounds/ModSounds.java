package net.artyrian.frontiers.sounds;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    public static final SoundEvent END_CRYSTAL_HIT = registerSoundEvent("end_crystal_hit");
    public static final SoundEvent END_CRYSTAL_WAIL = registerSoundEvent("end_crystal_wail");
    public static final SoundEvent END_CRYSTAL_EXPLODE = registerSoundEvent("end_crystal_explode");

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = Identifier.of(Frontiers.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds()
    {
        //Frontiers.LOGGER.info("Registering sounds for " + Frontiers.MOD_ID);
    }
}
