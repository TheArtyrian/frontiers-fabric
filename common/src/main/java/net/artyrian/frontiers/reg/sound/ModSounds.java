package net.artyrian.frontiers.reg.sound;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.block.SoundType;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModSounds
{
    public static final Supplier<SoundEvent> WITHER_DEFLECT_MACE = registerSoundEvent("entity.wither.deflect_mace");
    public static final Supplier<SoundEvent> END_CRYSTAL_HIT = registerSoundEvent("entity.end_crystal.hit");
    public static final Supplier<SoundEvent> END_CRYSTAL_WAIL = registerSoundEvent("entity.end_crystal.wail");
    public static final Supplier<SoundEvent> END_CRYSTAL_EXPLODE = registerSoundEvent("entity.end_crystal.explode");
    public static final Supplier<SoundEvent> SPELL_CAST_BASIC = registerSoundEvent("entity.player.spell_cast");
    public static final Supplier<SoundEvent> SPELL_CAST_FANGS = registerSoundEvent("entity.player.spell_cast_fangs");
    public static final Supplier<SoundEvent> CRAWLER_PRIMED = registerSoundEvent("entity.crawler.primed");
    public static final Supplier<SoundEvent> ECHO_BOW_SHOOT = registerSoundEvent("entity.arrow.shoot_echo");
    public static final Supplier<SoundEvent> VERDINITE_BOW_SHOOT = registerSoundEvent("entity.arrow.shoot_verdinite");
    public static final Supplier<SoundEvent> MANA_ORB_PICKUP = registerSoundEvent("entity.mana_orb.pickup");
    public static final Supplier<SoundEvent> VOID_PEARL_THROW = registerSoundEvent("item.void_pearl.use");
    public static final Supplier<SoundEvent> END_CRYSTAL_SHARD_USE = registerSoundEvent("item.end_crystal_shard.use");
    public static final Supplier<SoundEvent> CHEST_KEY_TAGGED = registerSoundEvent("item.chest_key.tag");
    public static final Supplier<SoundEvent> CHEST_KEY_USED = registerSoundEvent("item.chest_key.use");
    public static final Supplier<SoundEvent> ORE_WITHER = registerSoundEvent("block.ore.wither");
    public static final Supplier<SoundEvent> ENTITY_SHEARED = registerSoundEvent("entity.sheared_into_model");
    public static final Supplier<SoundEvent> BALL_THROW = registerSoundEvent("item.ball.use");
    public static final Supplier<SoundEvent> BAIT_THROW = registerSoundEvent("item.bait.use");
    public static final Supplier<SoundEvent> CRAGSMONSTER_BELLOW = registerSoundEvent("entity.cragsmonster.scream");
    public static final Supplier<SoundEvent> BEACON_BRIMTAN = registerSoundEvent("block.beacon.brimtan");
    public static final Supplier<SoundEvent> SLIME_BULB_PICK = registerSoundEvent("block.slime_bulb.pick");
    public static final Supplier<SoundEvent> SNOW_MELT_USE = registerSoundEvent("item.snow_melt.use");
    public static final Supplier<SoundEvent> EGG_CRACK = registerSoundEvent("item.golden_egg.use");
    public static final Supplier<SoundEvent> MESSAGE_BOTTLE_DEPOSIT = registerSoundEvent("item.bottled_message.splash");
    public static final Supplier<SoundEvent> ITEM_GENERIC_TAKE = registerSoundEvent("item.generic.take");

    // Entities
    public static final Supplier<SoundEvent> PUMPKIN_GOLEM_HURT = registerSoundEvent("entity.pumpkin_golem.hurt");
    public static final Supplier<SoundEvent> PUMPKIN_GOLEM_DEATH = registerSoundEvent("entity.pumpkin_golem.death");
    public static final Supplier<SoundEvent> PUMPKIN_GOLEM_PICK = registerSoundEvent("entity.pumpkin_golem.pick");
    public static final Supplier<SoundEvent> CROW_HURT = registerSoundEvent("entity.crow.hurt");
    public static final Supplier<SoundEvent> CROW_DEATH = registerSoundEvent("entity.crow.death");
    public static final Supplier<SoundEvent> CROW_IDLE = registerSoundEvent("entity.crow.ambient");
    public static final Supplier<SoundEvent> CROW_FLY = registerSoundEvent("entity.crow.fly");
    public static final Supplier<SoundEvent> BALL_BOUNCE = registerSoundEvent("entity.ball.bounce");

    // April Fool's
    public static final Supplier<SoundEvent> STEVE = registerSoundEvent("entity.player.steve");
    public static final Supplier<SoundEvent> APRIL_FOOLS_DEATH_SFX = registerSoundEvent("entity.player.april_fools");

    // Cragulstane SFX
    public static final Supplier<SoundEvent> BLOCK_CRAGULSTANE_BREAK = registerSoundEvent("block.cragulstane.break");
    public static final Supplier<SoundEvent> BLOCK_CRAGULSTANE_STEP = registerSoundEvent("block.cragulstane.step");
    public static final Supplier<SoundEvent> BLOCK_CRAGULSTANE_PLACE = registerSoundEvent("block.cragulstane.place");
    public static final Supplier<SoundEvent> BLOCK_CRAGULSTANE_HIT = registerSoundEvent("block.cragulstane.hit");
    public static final Supplier<SoundEvent> BLOCK_CRAGULSTANE_FALL = registerSoundEvent("block.cragulstane.fall");

    // Personal Chest
    public static final Supplier<SoundEvent> PERSONAL_CHEST_OPEN = registerSoundEvent("block.personal_chest.open");
    public static final Supplier<SoundEvent> PERSONAL_CHEST_CLOSE = registerSoundEvent("block.personal_chest.close");
    public static final Supplier<SoundEvent> PERSONAL_CHEST_LOCKED = registerSoundEvent("block.personal_chest.locked");

    public static final Supplier<SoundEvent> CURSE_ALTAR_USE = registerSoundEvent("block.curse_altar.use");
    public static final Supplier<SoundEvent> FLETCHING_TABLE_USE = registerSoundEvent("block.fletching_table.use");

    public static final Supplier<SoundEvent> STONE_FENCE_GATE_OPEN = registerSoundEvent("block.stone_fence_gate.open");
    public static final Supplier<SoundEvent> STONE_FENCE_GATE_CLOSE = registerSoundEvent("block.stone_fence_gate.close");

    public static final Supplier<SoundEvent> UI_TOAST_FRONTIERS = registerSoundEvent("ui.toast.frontier_reached");

    // Armors
    public static final Holder<SoundEvent> ARMOR_EQUIP_COBALT = registerSoundReference("item.armor.equip_cobalt");

    // Custom Head SFX
    public static final Supplier<SoundEvent> SKULL_FX_STEVE = registerSoundEvent("block.skull.steve");
    public static final Supplier<SoundEvent> SKULL_FX_ARTYRIAN = registerSoundEvent("block.skull.artyrian");
    public static final Supplier<SoundEvent> SKULL_FX_XENONA = registerSoundEvent("block.skull.xenona");
    public static final Supplier<SoundEvent> SKULL_FX_YURJEZICH = registerSoundEvent("block.skull.yurjezich");
    public static final Supplier<SoundEvent> SKULL_FX_KIRBYTG = registerSoundEvent("block.skull.kirbytg");
    public static final Supplier<SoundEvent> SKULL_FX_MAGIC = registerSoundEvent("block.skull.magic");
    public static final Supplier<SoundEvent> SKULL_FX_COURTJJESTER = registerSoundEvent("block.skull.courtjjester");
    public static final Supplier<SoundEvent> SKULL_FX_GOLDALIEN2016 = registerSoundEvent("block.skull.goldalien2016");
    public static final Supplier<SoundEvent> SKULL_FX_REDNALOKIN = registerSoundEvent("block.skull.rednalokin");
    public static final Supplier<SoundEvent> SKULL_FX_GREYL1ME = registerSoundEvent("block.skull.greyl1me");

    // Note Block SFX
    public static final Holder<SoundEvent> BLOCK_NOTE_BLOCK_LOG_DRUM = registerSoundReference("block.note_block.frontiers.logdrum");
    public static final Holder<SoundEvent> BLOCK_NOTE_BLOCK_ICE_BELL = registerSoundReference("block.note_block.frontiers.icebell");
    public static final Holder<SoundEvent> BLOCK_NOTE_BLOCK_HARPSICHORD = registerSoundReference("block.note_block.frontiers.harpsichord");
    public static final Holder<SoundEvent> BLOCK_NOTE_BLOCK_STEEL_DRUM = registerSoundReference("block.note_block.frontiers.steeldrum");
    public static final Holder<SoundEvent> BLOCK_NOTE_BLOCK_ROBOLUNG = registerSoundReference("block.note_block.frontiers.robolung");
    public static final Holder<SoundEvent> BLOCK_NOTE_BLOCK_JESKOLA = registerSoundReference("block.note_block.frontiers.jeskola");

    // Music
    public static final Holder<SoundEvent> BOSS_WITHER = registerSoundReference("music.wither");
    public static final Holder<SoundEvent> MUSIC_CRAGS = registerSoundReference("music.crags");

    // Music Discs + Registries
    public static final Supplier<SoundEvent> DISC_DIAPHRAGM = registerSoundEvent("disc.diaphragm");
    public static final ResourceKey<JukeboxSong> DISC_DIAPHRAGM_KEY = ResourceKey.create(Registries.JUKEBOX_SONG, Frontiers.id("diaphragm"));

    private static Supplier<SoundEvent> registerSoundEvent(String name)
    {
        return VectorLib.REGISTRY.register(Frontiers.MOD_ID, name, BuiltInRegistries.SOUND_EVENT, () ->
                SoundEvent.createVariableRangeEvent(Frontiers.id(name))
        );
    }

    private static Holder<SoundEvent> registerSoundReference(String name)
    {
        return VectorLib.REGISTRY.registerHolder(Frontiers.MOD_ID, name, BuiltInRegistries.SOUND_EVENT, () ->
                SoundEvent.createVariableRangeEvent(Frontiers.id(name))
        );
    }

    private static Holder.Reference<SoundEvent> registerSoundReferenceWithHold(String name)
    {
        return VectorLib.REGISTRY.registerHolderRef(Frontiers.MOD_ID, name, BuiltInRegistries.SOUND_EVENT, () ->
                SoundEvent.createVariableRangeEvent(Frontiers.id(name))
        );
    }

    public static void registerSounds()
    {

    }
}
