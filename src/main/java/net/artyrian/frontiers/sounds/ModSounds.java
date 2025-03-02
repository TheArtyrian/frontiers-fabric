package net.artyrian.frontiers.sounds;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    public static final SoundEvent END_CRYSTAL_HIT = registerSoundEvent("entity.end_crystal.hit");
    public static final SoundEvent END_CRYSTAL_WAIL = registerSoundEvent("entity.end_crystal.wail");
    public static final SoundEvent END_CRYSTAL_EXPLODE = registerSoundEvent("entity.end_crystal.explode");
    public static final SoundEvent SPELL_CAST_BASIC = registerSoundEvent("entity.player.spell_cast");
    public static final SoundEvent SPELL_CAST_FANGS = registerSoundEvent("entity.player.spell_cast_fangs");
    public static final SoundEvent VERDINITE_BOW_SHOOT = registerSoundEvent("entity.arrow.shoot_verdinite");
    public static final SoundEvent VOID_PEARL_THROW = registerSoundEvent("item.void_pearl.use");

    public static final RegistryEntry<SoundEvent> ARMOR_EQUIP_COBALT = registerSoundReference("item.armor.equip_cobalt");

    public static final SoundEvent UI_TOAST_FRONTIERS = registerSoundEvent("ui.toast.frontier_reached");

    public static final SoundEvent SKULL_FX_STEVE = registerSoundEvent("block.skull.steve");
    public static final SoundEvent SKULL_FX_ARTYRIAN = registerSoundEvent("block.skull.artyrian");
    public static final SoundEvent SKULL_FX_XENONA = registerSoundEvent("block.skull.xenona");
    public static final SoundEvent SKULL_FX_YURJEZICH = registerSoundEvent("block.skull.yurjezich");
    public static final SoundEvent SKULL_FX_KIRBYTG = registerSoundEvent("block.skull.kirbytg");
    public static final SoundEvent SKULL_FX_MAGIC = registerSoundEvent("block.skull.magic");
    public static final SoundEvent SKULL_FX_COURTJJESTER = registerSoundEvent("block.skull.courtjjester");
    public static final SoundEvent SKULL_FX_GOLDALIEN2016 = registerSoundEvent("block.skull.goldalien2016");
    public static final SoundEvent SKULL_FX_REDNALOKIN = registerSoundEvent("block.skull.rednalokin");
    public static final SoundEvent SKULL_FX_GREYL1ME = registerSoundEvent("block.skull.greyl1me");

    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = Identifier.of(Frontiers.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerSoundReference(String name)
    {
        Identifier id = Identifier.of(Frontiers.MOD_ID, name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds()
    {
        //Frontiers.LOGGER.info("Registering sounds for " + Frontiers.MOD_ID);
    }
}
