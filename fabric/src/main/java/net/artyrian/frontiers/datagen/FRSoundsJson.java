package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.vertisoft.vectorlib.exclusive.datagen.VectorDatagen;
import net.vertisoft.vectorlib.exclusive.datagen.soundfile.VectorSoundsheetGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FRSoundsJson extends VectorSoundsheetGen
{
    private static final String MINECRAFT = ResourceLocation.DEFAULT_NAMESPACE;

    public FRSoundsJson(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(Frontiers.MOD_ID, dataOutput, registryLookup);
    }

    @Override
    public void generateSounds(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        registerEntitySFX(lookup, sounds);
        registerBlockSFX(lookup, sounds);
        registerItemSFX(lookup, sounds);
        registerArmorSFX(lookup, sounds);
        registerMiscSFX(lookup, sounds);
        registerHudSFX(lookup, sounds);
        registerNoteBlocks(lookup, sounds);
        registerMusicAndDiscs(lookup, sounds);
    }

    private void registerEntitySFX(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        // Wither
        sounds.addSound(ModSounds.WITHER_DEFLECT_MACE.get(), multiple(Frontiers.MOD_ID, "entity/wither/deflect", 3),
                VectorDatagen.Caption.of("sounds.frontiers.wither_deflect_mace", Map.ofEntries(VectorDatagen.Caption.englishUS("Mace deflected")))
        );
        // End Crystal
        sounds.addSound(ModSounds.END_CRYSTAL_HIT.get(), multiple(Frontiers.MOD_ID, "entity/end_crystal/end_crystal_hit_", 3),
                VectorDatagen.Caption.of("sounds.frontiers.end_crystal_hit", Map.ofEntries(VectorDatagen.Caption.englishUS("End Crystal cracks")))
        );
        sounds.addSound(ModSounds.END_CRYSTAL_WAIL.get(), addOne(Frontiers.MOD_ID, "entity/end_crystal/end_crystal_wail"),
                VectorDatagen.Caption.of("sounds.frontiers.end_crystal_wail", Map.ofEntries(VectorDatagen.Caption.englishUS("End Crystal wails")))
        );
        sounds.addSound(ModSounds.END_CRYSTAL_EXPLODE.get(), addOne(Frontiers.MOD_ID, "entity/end_crystal/end_crystal_explode"),
                VectorDatagen.Caption.of("sounds.frontiers.end_crystal_explode", Map.ofEntries(VectorDatagen.Caption.englishUS("End Crystal shatters")))
        );
        // Crow
        sounds.addSound(ModSounds.CROW_IDLE.get(), multiple(Frontiers.MOD_ID, "entity/crow/idle", 3),
                VectorDatagen.Caption.of("sounds.frontiers.crow.ambient", Map.ofEntries(VectorDatagen.Caption.englishUS("Crow caws")))
        );
        sounds.addSound(ModSounds.CROW_HURT.get(), multiple(Frontiers.MOD_ID, "entity/crow/hurt", 3),
                VectorDatagen.Caption.of("sounds.frontiers.crow.hurt", Map.ofEntries(VectorDatagen.Caption.englishUS("Crow hurts")))
        );
        sounds.addSound(ModSounds.CROW_DEATH.get(), addOne(Frontiers.MOD_ID, "entity/crow/die"),
                VectorDatagen.Caption.of("sounds.frontiers.crow.death", Map.ofEntries(VectorDatagen.Caption.englishUS("Crow dies")))
        );
        sounds.addSound(ModSounds.CROW_FLY.get(), multiple(MINECRAFT, "mob/parrot/fly", 8),
                VectorDatagen.Caption.of("sounds.frontiers.crow.fly", Map.ofEntries(VectorDatagen.Caption.englishUS("Crow flutters")))
        );
        // Pumpkin Golem
        sounds.addSound(ModSounds.PUMPKIN_GOLEM_HURT.get(), multiple(Frontiers.MOD_ID, "entity/pumpkin_golem/hurt", 3),
                VectorDatagen.Caption.of("sounds.frontiers.pumpkin_golem.hurt", Map.ofEntries(VectorDatagen.Caption.englishUS("Pumpkin Golem hurts")))
        );
        sounds.addSound(ModSounds.PUMPKIN_GOLEM_DEATH.get(), addOne(Frontiers.MOD_ID, "entity/pumpkin_golem/die"),
                VectorDatagen.Caption.of("sounds.frontiers.pumpkin_golem.death", Map.ofEntries(VectorDatagen.Caption.englishUS("Pumpkin Golem dies")))
        );
        sounds.addSound(ModSounds.PUMPKIN_GOLEM_PICK.get(), addOne(MINECRAFT, "mob/irongolem/throw"),
                VectorDatagen.Caption.of("sounds.frontiers.pumpkin_golem.pick", Map.ofEntries(VectorDatagen.Caption.englishUS("Pumpkin Golem picks")))
        );
        // Crags Monster
        sounds.addSound(ModSounds.CRAGSMONSTER_BELLOW.get(), addOne(Frontiers.MOD_ID, "entity/cragsmonster/scream"),
                VectorDatagen.Caption.of("subtitles.entity.cragsmonster", Map.ofEntries(VectorDatagen.Caption.englishUS("§f§kENTITY§r bellows")))
        );
        // Crawler
        sounds.addSound(ModSounds.CRAWLER_PRIMED.get(), addOne(Frontiers.MOD_ID, "entity/crawler/fuse"),
                VectorDatagen.Caption.of("subtitles.frontiers.crawler.primed", Map.ofEntries(VectorDatagen.Caption.englishUS("Crawler hisses")))
        );
    }

    private void registerItemSFX(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        String firedArrow = "Arrow fired";
        List<SoundDefinition> RANDOM_BOW = addOne(MINECRAFT, "random/bow");

        // Bows
        sounds.addSound(ModSounds.ECHO_BOW_SHOOT.get(), multiple(Frontiers.MOD_ID, "item/echo_bow/shoot_", 3),
                VectorDatagen.Caption.of("sounds.frontiers.echo_bow", Map.ofEntries(VectorDatagen.Caption.englishUS(firedArrow)))
        );
        sounds.addSound(ModSounds.VERDINITE_BOW_SHOOT.get(), addOne(Frontiers.MOD_ID, "item/verdinite_bow/shoot"),
                VectorDatagen.Caption.of("sounds.frontiers.verdinite_bow", Map.ofEntries(VectorDatagen.Caption.englishUS(firedArrow)))
        );
        // Spells
        sounds.addSound(ModSounds.SPELL_CAST_BASIC.get(), multiple(MINECRAFT, "mob/evocation_illager/cast", 2),
                VectorDatagen.Caption.of("sounds.frontiers.spell_cast", Map.ofEntries(VectorDatagen.Caption.englishUS("Spell cast")))
        );
        sounds.addSound(ModSounds.SPELL_CAST_FANGS.get(), multiple(MINECRAFT, "mob/evocation_illager/prepare_attack", 2),
                VectorDatagen.Caption.of("sounds.frontiers.spell_cast_fangs", Map.ofEntries(VectorDatagen.Caption.englishUS("Attack prepared")))
        );
        sounds.addSound(ModSounds.THUNDER_TOME_SMALL.get(), multiple(Frontiers.MOD_ID, "item/lightning_tome/bolt_small", 3),
                VectorDatagen.Caption.of("sounds.frontiers.spell_cast_bolt_small", Map.ofEntries(VectorDatagen.Caption.englishUS("Lightning spell crackles")))
        );
        sounds.addSound(ModSounds.THUNDER_TOME_HEAVY.get(), addOne(Frontiers.MOD_ID, "item/lightning_tome/bolt_heavy1"),
                VectorDatagen.Caption.of("sounds.frontiers.spell_cast_bolt_heavy", Map.ofEntries(VectorDatagen.Caption.englishUS("Lightning spell bursts")))
        );
        // Ball
        sounds.addSound(ModSounds.BALL_THROW.get(), RANDOM_BOW,
                VectorDatagen.Caption.of("sounds.frontiers.ball.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Ball flies")))
        );
        sounds.addSound(ModSounds.BALL_BOUNCE.get(), addOne(Frontiers.MOD_ID, "entity/ball/bounce"),
                VectorDatagen.Caption.of("sounds.frontiers.ball.bounce", Map.ofEntries(VectorDatagen.Caption.englishUS("Ball bounces")))
        );
        // Bait
        sounds.addSound(ModSounds.BAIT_THROW.get(), RANDOM_BOW,
                VectorDatagen.Caption.of("sounds.frontiers.bait.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Bait flies")))
        );
        // Ender Eye
        sounds.addSound(ModSounds.ENDER_EYE_SMASH.get(), addOne(Frontiers.MOD_ID, "item/ender_eye/use"),
                VectorDatagen.Caption.of("sounds.frontiers.ender_eye.use_frontiers", Map.ofEntries(VectorDatagen.Caption.englishUS("Eye of Ender smashed")))
        );
        // End Crystal Shard
        sounds.addSound(ModSounds.END_CRYSTAL_SHARD_USE.get(), addOne(Frontiers.MOD_ID, "item/end_crystal_shard/use"),
                VectorDatagen.Caption.of("sounds.frontiers.end_crystal_shard.use", Map.ofEntries(VectorDatagen.Caption.englishUS("End Crystal Shard shatters")))
        );
        // Snow Melt
        sounds.addSound(ModSounds.SNOW_MELT_USE.get(), meal(MINECRAFT, "item/bonemeal/bonemeal", 5),
                VectorDatagen.Caption.of("sounds.frontiers.snow_melt.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Snow Melt crinkles")))
        );
        // Onyx Meal
        sounds.addSound(ModSounds.ONYX_MEAL_USE.get(), meal(Frontiers.MOD_ID, "item/onyxmeal/meal", 5),
                VectorDatagen.Caption.of("sounds.frontiers.onyx_meal.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Onyx Meal withers")))
        );
        // Bottled Message
        sounds.addSound(ModSounds.MESSAGE_BOTTLE_DEPOSIT.get(), addOne(MINECRAFT, "random/splash"),
                VectorDatagen.Caption.of("sounds.frontiers.bottled_message.splash", Map.ofEntries(VectorDatagen.Caption.englishUS("Bottled Message deposits")))
        );
        // Chest Key
        sounds.addSound(ModSounds.CHEST_KEY_TAGGED.get(), addOne(Frontiers.MOD_ID, "item/chest_key/tagged"),
                VectorDatagen.Caption.of("subtitles.item.chest_key.tag", Map.ofEntries(VectorDatagen.Caption.englishUS("Chest Key tagged")))
        );
        sounds.addSound(ModSounds.CHEST_KEY_USED.get(), addOne(Frontiers.MOD_ID, "item/chest_key/used"),
                VectorDatagen.Caption.of("subtitles.item.chest_key.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Personal Chest accepts")))
        );
        // Egg
        sounds.addSound(ModSounds.EGG_CRACK.get(), List.of(
                    SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/turtle/egg/egg_crack1"), 1.2F),
                    SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/turtle/egg/egg_crack2"), 1.2F),
                    SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/turtle/egg/egg_crack3"), 1.2F),
                    SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/turtle/egg/egg_crack4"), 1.2F),
                    SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/turtle/egg/egg_crack5"), 1.2F)
                ),
                VectorDatagen.Caption.of("subtitles.item.egg.smash", Map.ofEntries(VectorDatagen.Caption.englishUS("Egg cracks")))
        );
    }

    private void registerArmorSFX(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        // Cobalt
        sounds.addSound(ModSounds.ARMOR_EQUIP_COBALT.value(), multiple(Frontiers.MOD_ID, "armor/cobalt/equip", 4),
                VectorDatagen.Caption.of("sounds.frontiers.equip.cobalt", Map.ofEntries(VectorDatagen.Caption.englishUS("Cobalt armor clunks")))
        );
    }

    private void registerBlockSFX(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        String genBreak = "subtitles.block.generic.break";
        String genHit = "subtitles.block.generic.hit";
        String genPlace = "subtitles.block.generic.place";
        String genStep = "subtitles.block.generic.footsteps";
        String chestOpen = "subtitles.block.chest.open";
        String chestClose = "subtitles.block.chest.close";
        String fenceGate = "subtitles.block.fence_gate.toggle";

        // Ore Withering
        sounds.addSound(ModSounds.ORE_WITHER.get(), addOne(Frontiers.MOD_ID, "block/ore_wither"),
                VectorDatagen.Caption.of("sounds.frontiers.block.ore.wither", Map.ofEntries(VectorDatagen.Caption.englishUS("Ore withers away")))
        );
        // Crags Portal
        sounds.addSound(ModSounds.CRAGS_TRAVEL.get(), addOne(Frontiers.MOD_ID, "block/crags_portal/teleport"),
                VectorDatagen.Caption.of("subtitles.block.crags_portal.travel_crags", Map.ofEntries(VectorDatagen.Caption.englishUS("Portal echoes")))
        );
        // Tower Spawner
        sounds.addSound(ModSounds.TOWER_SPAWNER_ENRAGE.get(), addOne(Frontiers.MOD_ID, "block/tower/enrage"),
                VectorDatagen.Caption.of("subtitles.block.tower_spawner.enrage", Map.ofEntries(VectorDatagen.Caption.englishUS("Tower Spawner enrages")))
        );
        // Fletching Table
        sounds.addSound(ModSounds.FLETCHING_TABLE_USE.get(), multiple(MINECRAFT, "block/fletching_table/fletching_table", 2),
                VectorDatagen.Caption.of("subtitles.block.fletching_table.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Fletching Table used")))
        );
        // Beacon
        sounds.addSound(ModSounds.BEACON_BRIMTAN.get(), addOne(Frontiers.MOD_ID, "block/beacon/brimtan_hum"),
                VectorDatagen.Caption.of("subtitles.block.beacon.brimtan", Map.ofEntries(VectorDatagen.Caption.englishUS("Beacon drones")))
        );
        // Slime Bulb
        sounds.addSound(ModSounds.SLIME_BULB_PICK.get(), multiple(Frontiers.MOD_ID, "block/slime_bulb/pick_", 2),
                VectorDatagen.Caption.of("subtitles.block.slime_bulb.pick", Map.ofEntries(VectorDatagen.Caption.englishUS("Hardened Slime pops")))
        );
        // Curse Altar
        sounds.addSound(ModSounds.CURSE_ALTAR_TABLET.get(), addOne(Frontiers.MOD_ID, "block/curse_altar/place"),
                VectorDatagen.Caption.of("subtitles.block.curse_altar.tablet", Map.ofEntries(VectorDatagen.Caption.englishUS("Cursed Tablet activated")))
        );
        sounds.addSound(ModSounds.CURSE_ALTAR_USE.get(), addOne(Frontiers.MOD_ID, "block/curse_altar/uncurse1"),
                VectorDatagen.Caption.of("subtitles.block.curse_altar.use", Map.ofEntries(VectorDatagen.Caption.englishUS("Curse Altar used")))
        );
        // Brewing Stand
        sounds.addSound(ModSounds.BREWING_STAND_FILL.get(), multiple(Frontiers.MOD_ID, "block/brewing_stand/fizz", 2),
                VectorDatagen.Caption.of("subtitles.block.brewing_stand.frontiers_blaze_charge", Map.ofEntries(VectorDatagen.Caption.englishUS("Brewing Stand fills up")))
        );
        // Personal Chest
        sounds.addSound(ModSounds.PERSONAL_CHEST_OPEN.get(), addOne(Frontiers.MOD_ID, "block/personal_chest/open"),
                VectorDatagen.Caption.ofExisting(chestOpen)
        );
        sounds.addSound(ModSounds.PERSONAL_CHEST_CLOSE.get(), multiple(Frontiers.MOD_ID, "block/personal_chest/close", 3),
                VectorDatagen.Caption.ofExisting(chestClose)
        );
        sounds.addSound(ModSounds.PERSONAL_CHEST_LOCKED.get(), addOne(Frontiers.MOD_ID, "block/personal_chest/locked"),
                VectorDatagen.Caption.of("subtitles.block.personal_chest.locked", Map.ofEntries(VectorDatagen.Caption.englishUS("Personal Chest locks")))
        );
        // Cragulstane
        List<SoundDefinition> cragulDig = multiple(Frontiers.MOD_ID, "block/dig/cragulstane", 4);
        List<SoundDefinition> cragulStep = multiple(Frontiers.MOD_ID, "block/step/cragulstane", 6);
        sounds.addSound(ModSounds.BLOCK_CRAGULSTANE_BREAK.get(), cragulDig, VectorDatagen.Caption.ofExisting(genBreak));
        sounds.addSound(ModSounds.BLOCK_CRAGULSTANE_FALL.get(), cragulStep, null);
        sounds.addSound(ModSounds.BLOCK_CRAGULSTANE_HIT.get(), cragulStep, VectorDatagen.Caption.ofExisting(genHit));
        sounds.addSound(ModSounds.BLOCK_CRAGULSTANE_PLACE.get(), cragulDig, VectorDatagen.Caption.ofExisting(genPlace));
        sounds.addSound(ModSounds.BLOCK_CRAGULSTANE_STEP.get(), cragulStep, VectorDatagen.Caption.ofExisting(genStep));
        // Stone Fence Gate
        sounds.addSound(ModSounds.STONE_FENCE_GATE_OPEN.get(), List.of(
                        SoundDefinition.ofVolume(ResourceLocation.withDefaultNamespace("block/iron_trapdoor/open1"), 0.9F),
                        SoundDefinition.ofVolume(ResourceLocation.withDefaultNamespace("block/iron_trapdoor/open2"), 0.9F)
                ),
                VectorDatagen.Caption.ofExisting(fenceGate)
        );
        sounds.addSound(ModSounds.STONE_FENCE_GATE_CLOSE.get(), List.of(
                    SoundDefinition.ofVolume(ResourceLocation.withDefaultNamespace("block/iron_trapdoor/close1"), 0.9F),
                    SoundDefinition.ofVolume(ResourceLocation.withDefaultNamespace("block/iron_trapdoor/close2"), 0.9F)
                ),
            VectorDatagen.Caption.ofExisting(fenceGate)
        );
        // Skulls
        sounds.addSound(ModSounds.SKULL_FX_STEVE.get(), addOne(Frontiers.MOD_ID, "block/heads/steve_old"),
                VectorDatagen.Caption.of("sounds.frontiers.block.skull.steve", Map.ofEntries(VectorDatagen.Caption.englishUS("Male hurts")))
        );
        sounds.addSound(ModSounds.SKULL_FX_ARTYRIAN.get(), multiple(Frontiers.MOD_ID, "block/heads/artyrian/artyrian_", 6),
                VectorDatagen.Caption.of("sounds.frontiers.block.skull.artyrian", Map.ofEntries(VectorDatagen.Caption.englishUS("Madman rambles")))
        );
        sounds.addSound(ModSounds.SKULL_FX_XENONA.get(), List.of(
                        SoundDefinition.of(Frontiers.id("block/heads/xenona/xenona_1")),
                        SoundDefinition.of(Frontiers.id("block/heads/xenona/xenona_2")),
                        SoundDefinition.of(Frontiers.id("block/heads/xenona/xenona_3")),
                        SoundDefinition.of(Frontiers.id("block/heads/xenona/xenona_4")),
                        SoundDefinition.of(Frontiers.id("block/heads/xenona/xenona_5")),
                        SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/sheep/say1"), 1.5F),
                        SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/sheep/say2"), 1.5F),
                        SoundDefinition.ofPitch(ResourceLocation.withDefaultNamespace("mob/sheep/say3"), 1.5F)
                ),
                VectorDatagen.Caption.of("sounds.frontiers.block.skull.xenona", Map.ofEntries(VectorDatagen.Caption.englishUS("Incomprehensible entity sounds")))
        );
    }

    private void registerHudSFX(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        // Advancement
        sounds.addSound(ModSounds.UI_TOAST_FRONTIERS.get(), List.of(SoundDefinition.ofVolume(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "entity/end_crystal/end_crystal_explode"), 0.8F)),
                null
        );
        // Mana
        sounds.addSound(ModSounds.PLAYER_MANA_UP.get(), addOne(Frontiers.MOD_ID, "random/mana_level"),
                VectorDatagen.Caption.of("subtitles.entity.player.manaupgrade", Map.ofEntries(VectorDatagen.Caption.englishUS("Player charges up")))
        );
    }

    private void registerMiscSFX(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        // Mana
        sounds.addSound(ModSounds.MANA_ORB_PICKUP.get(), addOne(Frontiers.MOD_ID, "random/mana"),
                VectorDatagen.Caption.of("subtitles.entity.mana_orb.pickup", Map.ofEntries(VectorDatagen.Caption.englishUS("Mana gained")))
        );
        // Generics
        sounds.addSound(ModSounds.ENTITY_SHEARED.get(), addOne(Frontiers.MOD_ID, "item/tools/shear_for_model"),
                VectorDatagen.Caption.of("subtitles.item.shears.shear_entity_for_model", Map.ofEntries(VectorDatagen.Caption.englishUS("Shears cut")))
        );
        sounds.addSound(ModSounds.ITEM_GENERIC_TAKE.get(), multiple(MINECRAFT, "item/armor/equip_generic", 6),
                VectorDatagen.Caption.of("sounds.frontiers.generic.item.take", Map.ofEntries(VectorDatagen.Caption.englishUS("Item taken")))
        );
        // April Fools
        sounds.addSound(ModSounds.APRIL_FOOLS_DEATH_SFX.get(), addAll(Frontiers.MOD_ID, List.of(
                        "hahafunny/aguanile", "hahafunny/airplane", "hahafunny/blyat", "hahafunny/cathade", "hahafunny/combine",
                        "hahafunny/flight1", "hahafunny/gunstarheroes", "hahafunny/icarly", "hahafunny/keemstar", "hahafunny/ohmygodwegafromhalflife",
                        "hahafunny/pelo", "hahafunny/scoobis", "hahafunny/steveinreallife", "hahafunny/tacobell", "hahafunny/thistaskagruelingone",
                        "hahafunny/toad", "hahafunny/fatherhelp", "hahafunny/flightdies2", "hahafunny/fortnite", "hahafunny/shulk_er",
                        "hahafunny/stayonthebike", "hahafunny/hl2", "hahafunny/holdup", "hahafunny/lol", "hahafunny/regular",
                        "hahafunny/yoda", "hahafunny/wonderfulidea", "hahafunny/baldi", "hahafunny/pipe", "hahafunny/urio"
                )),
                null
        );
        sounds.addSound(ModSounds.STEVE.get(), addOne(Frontiers.MOD_ID, "block/heads/steve_old"),
                null
        );
    }

    private void registerNoteBlocks(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        String noteBlock = "subtitles.block.note_block.note";

        sounds.addSound(ModSounds.BLOCK_NOTE_BLOCK_LOG_DRUM.value(), addOne(Frontiers.MOD_ID, "note/log"),
                VectorDatagen.Caption.ofExisting(noteBlock)
        );
        sounds.addSound(ModSounds.BLOCK_NOTE_BLOCK_ICE_BELL.value(), addOne(Frontiers.MOD_ID, "note/ice_bell"),
                VectorDatagen.Caption.ofExisting(noteBlock)
        );
        sounds.addSound(ModSounds.BLOCK_NOTE_BLOCK_HARPSICHORD.value(), addOne(Frontiers.MOD_ID, "note/harpsi"),
                VectorDatagen.Caption.ofExisting(noteBlock)
        );
        sounds.addSound(ModSounds.BLOCK_NOTE_BLOCK_STEEL_DRUM.value(), addOne(Frontiers.MOD_ID, "note/steel_drum"),
                VectorDatagen.Caption.ofExisting(noteBlock)
        );
        sounds.addSound(ModSounds.BLOCK_NOTE_BLOCK_ROBOLUNG.value(), addOne(Frontiers.MOD_ID, "note/robolung"),
                VectorDatagen.Caption.ofExisting(noteBlock)
        );
        sounds.addSound(ModSounds.BLOCK_NOTE_BLOCK_JESKOLA.value(), addOne(Frontiers.MOD_ID, "note/jeskola"),
                VectorDatagen.Caption.ofExisting(noteBlock)
        );
    }

    private void registerMusicAndDiscs(HolderLookup.Provider lookup, SoundsFactory sounds)
    {
        // Boss Music
        sounds.addSound(ModSounds.BOSS_WITHER.value(), List.of(SoundDefinition.of(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "music/boss/wither"), true)),
                null
        );
        // Dim Music
        sounds.addSound(ModSounds.MUSIC_CRAGS.value(), List.of(SoundDefinition.of(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "music/deeper"), true)),
                null
        );
        // Discs
        sounds.addSound(ModSounds.DISC_DIAPHRAGM.get(), List.of(SoundDefinition.of(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "disc/diaphragm"), true)),
                null
        );
    }

    private List<SoundDefinition> meal(String assets, String commonpath, int counts)
    {
        List<SoundDefinition> returnable = new ArrayList<>();
        for (int i = 0; i < counts; i++)
        {
            returnable.add(SoundDefinition.of(ResourceLocation.fromNamespaceAndPath(assets, commonpath + (i + 1))));
            returnable.add(SoundDefinition.ofPitch(ResourceLocation.fromNamespaceAndPath(assets, commonpath + (i + 1)), 0.9F));
            returnable.add(SoundDefinition.ofPitch(ResourceLocation.fromNamespaceAndPath(assets, commonpath + (i + 1)), 1.1F));
        }
        return returnable;
    }
}
