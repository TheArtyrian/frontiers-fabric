package net.artyrian.frontiers.sounds;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    public static final SoundEvent WITHER_DEFLECT_MACE = registerSoundEvent("entity.wither.deflect_mace");
    public static final SoundEvent END_CRYSTAL_HIT = registerSoundEvent("entity.end_crystal.hit");
    public static final SoundEvent END_CRYSTAL_WAIL = registerSoundEvent("entity.end_crystal.wail");
    public static final SoundEvent END_CRYSTAL_EXPLODE = registerSoundEvent("entity.end_crystal.explode");
    public static final SoundEvent SPELL_CAST_BASIC = registerSoundEvent("entity.player.spell_cast");
    public static final SoundEvent SPELL_CAST_FANGS = registerSoundEvent("entity.player.spell_cast_fangs");
    public static final SoundEvent CRAWLER_PRIMED = registerSoundEvent("entity.crawler.primed");
    public static final SoundEvent VERDINITE_BOW_SHOOT = registerSoundEvent("entity.arrow.shoot_verdinite");
    public static final SoundEvent MANA_ORB_PICKUP = registerSoundEvent("entity.mana_orb.pickup");
    public static final SoundEvent VOID_PEARL_THROW = registerSoundEvent("item.void_pearl.use");
    public static final SoundEvent END_CRYSTAL_SHARD_USE = registerSoundEvent("item.end_crystal_shard.use");
    public static final SoundEvent CHEST_KEY_TAGGED = registerSoundEvent("item.chest_key.tag");
    public static final SoundEvent CHEST_KEY_USED = registerSoundEvent("item.chest_key.use");
    public static final SoundEvent ORE_WITHER = registerSoundEvent("block.ore.wither");
    public static final SoundEvent ENTITY_SHEARED = registerSoundEvent("entity.sheared_into_model");
    public static final SoundEvent BALL_THROW = registerSoundEvent("item.ball.use");
    public static final SoundEvent BAIT_THROW = registerSoundEvent("item.bait.use");
    public static final SoundEvent CRAGSMONSTER_BELLOW = registerSoundEvent("entity.cragsmonster.scream");
    public static final SoundEvent BEACON_BRIMTAN = registerSoundEvent("block.beacon.brimtan");
    public static final SoundEvent SLIME_BULB_PICK = registerSoundEvent("block.slime_bulb.pick");
    public static final SoundEvent SNOW_MELT_USE = registerSoundEvent("item.snow_melt.use");
    public static final SoundEvent EGG_CRACK = registerSoundEvent("item.golden_egg.use");
    public static final SoundEvent MESSAGE_BOTTLE_DEPOSIT = registerSoundEvent("item.bottled_message.splash");
    public static final SoundEvent ITEM_GENERIC_TAKE = registerSoundEvent("item.generic.take");

    // Entities
    public static final SoundEvent PUMPKIN_GOLEM_HURT = registerSoundEvent("entity.pumpkin_golem.hurt");
    public static final SoundEvent PUMPKIN_GOLEM_DEATH = registerSoundEvent("entity.pumpkin_golem.death");
    public static final SoundEvent PUMPKIN_GOLEM_PICK = registerSoundEvent("entity.pumpkin_golem.pick");
    public static final SoundEvent CROW_HURT = registerSoundEvent("entity.crow.hurt");
    public static final SoundEvent CROW_DEATH = registerSoundEvent("entity.crow.death");
    public static final SoundEvent CROW_IDLE = registerSoundEvent("entity.crow.ambient");
    public static final SoundEvent CROW_FLY = registerSoundEvent("entity.crow.fly");
    public static final SoundEvent BALL_BOUNCE = registerSoundEvent("entity.ball.bounce");

    // April Fool's
    public static final SoundEvent STEVE = registerSoundEvent("entity.player.steve");
    public static final SoundEvent APRIL_FOOLS_DEATH_SFX = registerSoundEvent("entity.player.april_fools");

    // Cragulstane SFX
    public static final SoundEvent BLOCK_CRAGULSTANE_BREAK = registerSoundEvent("block.cragulstane.break");
    public static final SoundEvent BLOCK_CRAGULSTANE_STEP = registerSoundEvent("block.cragulstane.step");
    public static final SoundEvent BLOCK_CRAGULSTANE_PLACE = registerSoundEvent("block.cragulstane.place");
    public static final SoundEvent BLOCK_CRAGULSTANE_HIT = registerSoundEvent("block.cragulstane.hit");
    public static final SoundEvent BLOCK_CRAGULSTANE_FALL = registerSoundEvent("block.cragulstane.fall");

    // Personal Chest
    public static final SoundEvent PERSONAL_CHEST_OPEN = registerSoundEvent("block.personal_chest.open");
    public static final SoundEvent PERSONAL_CHEST_CLOSE = registerSoundEvent("block.personal_chest.close");
    public static final SoundEvent PERSONAL_CHEST_LOCKED = registerSoundEvent("block.personal_chest.locked");

    public static final SoundEvent CURSE_ALTAR_USE = registerSoundEvent("block.curse_altar.use");
    public static final SoundEvent FLETCHING_TABLE_USE = registerSoundEvent("block.fletching_table.use");

    public static final SoundEvent STONE_FENCE_GATE_OPEN = registerSoundEvent("block.stone_fence_gate.open");
    public static final SoundEvent STONE_FENCE_GATE_CLOSE = registerSoundEvent("block.stone_fence_gate.close");

    public static final RegistryEntry<SoundEvent> ARMOR_EQUIP_COBALT = registerSoundReference("item.armor.equip_cobalt");

    public static final SoundEvent UI_TOAST_FRONTIERS = registerSoundEvent("ui.toast.frontier_reached");

    // Note Block SFX
    public static final RegistryEntry<SoundEvent> BLOCK_NOTE_BLOCK_LOG_DRUM = registerSoundReference("block.note_block.frontiers.logdrum");
    public static final RegistryEntry<SoundEvent> BLOCK_NOTE_BLOCK_ICE_BELL = registerSoundReference("block.note_block.frontiers.icebell");
    public static final RegistryEntry<SoundEvent> BLOCK_NOTE_BLOCK_HARPSICHORD = registerSoundReference("block.note_block.frontiers.harpsichord");
    public static final RegistryEntry<SoundEvent> BLOCK_NOTE_BLOCK_STEEL_DRUM = registerSoundReference("block.note_block.frontiers.steeldrum");
    public static final RegistryEntry<SoundEvent> BLOCK_NOTE_BLOCK_ROBOLUNG = registerSoundReference("block.note_block.frontiers.robolung");

    // Custom Head SFX
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

    // Music
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_CRAGS =
            registerSoundReference("music.crags");

    // Music Discs + Registries
    public static final SoundEvent DISC_DIAPHRAGM = registerSoundEvent("disc.diaphragm");
    public static final RegistryKey<JukeboxSong> DISC_DIAPHRAGM_KEY =
            RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Frontiers.MOD_ID, "diaphragm"));

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
