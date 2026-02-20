package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.vertisoft.vectorlib.exclusive.datagen.VectorLangGen;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FRLangProviderEnglish extends VectorLangGen
{
    private static final String L4J_FOOD = "§f\uD83C\uDF56§r";
    private static final String L4J_ARMOR = "§f\uD83D\uDC58§r";

    public FRLangProviderEnglish(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(Frontiers.MOD_ID, dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        doItemGeneral(provider, builder);
        doItemArmor(provider, builder);
        doItemTool(provider, builder);
        doItemMisc(provider, builder);
        doBlock(provider, builder);
        doEntity(provider, builder);
        doPainting(provider, builder);
        doEffectsAndEtc(provider, builder);
        doAdv(provider, builder);
        doEnchantments(provider, builder);
        doDmg(provider, builder);
        doContainers(provider, builder);
        doSubtitles(provider, builder);
        doBiomes(provider, builder);
        doTags(provider, builder);
        doMisc(provider, builder);

        doCompatLang(provider, builder);
    }

    private void doBlock(HolderLookup.Provider provider, TranslationBuilder builder)
    {

    }

    private void doItemGeneral(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String smithing_template = "Smithing Template";
        String music_disc = "Music Disc";

        addItemWithDesc(builder, ModItem.RAW_COBALT.get(), "Raw Cobalt",
                "Can be smelted in a furnace to create a cobalt ingot.",
                "A chunk of cobalt that can be smelted into an ingot"
        );
        addItemWithDesc(builder, ModItem.COBALT_INGOT.get(), "Cobalt Ingot",
                null,
                "A shimmering blue metal used to create powerful equipment"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_INGOT.get(), "Brimtan Ingot",
                "A hot-to-the-touch ingot which can be used to craft tools made from this material. Created by smelting ore in a furnace.",
                "A burning hot metal used to create otherworldly equipment"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_CLUSTER.get(), "Brimtan Cluster",
                "Can be smelted in a furnace to create a brimtan nugget.",
                "A warped chunk of brimtan that can be smelted into nuggets"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_NUGGET.get(), "Brimtan Nugget",
                "Created by smelting Brimtan Clusters in a furnace. Can be crafted into a Brimtan Ingot.",
                "A piece of a brimtan ingot"
        );
        addItemWithDesc(builder, ModItem.RAW_FROSTITE.get(), "Raw Frostite",
                "Obtained from melted frostite ore. Can be smelted in a furnace to create a frostite ingot.",
                "A chunk of frostite that can be smelted into an ingot"
        );
        addItemWithDesc(builder, ModItem.FROSTITE_INGOT.get(), "Frostite Ingot",
                "A freezing-cold ingot obtained through a tedious process. Created by smelting ore in a furnace.",
                "A beautiful icy metal used to create frosty equipment"
        );
        addItemWithDesc(builder, ModItem.CURSED_TABLET.get(), "Cursed Tablet",
                "Used to craft a Curse Altar, or can be used with a Curse Altar to remove all curse enchantments on a single item.",
                "Lifts curses from items at a Curse Altar"
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_CASING.get(), "Obsidian Casing",
                "Crafted with Obsidian and Diamonds. Can be used to upgrade Golden tools in a Smithing Table.",
                "An amalgam comprised of obsidian and diamond, useful for encasing golden tools"
        );
        addItemWithDesc(builder, ModItem.COBALT_FISHING_ROD.get(), "Cobalt Fishing Rod",
                "Used to catch a wider variety of fish and items.",
                "\"Water is blue, that means fish LOVE the color blue!\" - Artyrian, probably"
        );
        addItemWithDesc(builder, ModItem.COBALT_SHIELD.get(), "Cobalt Shield",
                "Stronger than a regular shield, has less disable time when hit with an axe, and knocks melee attacks further back.",
                "A stronger variant of shield; knocks back attackers and recovers faster when hit with an axe"
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with Golden tools and an Obsidian Casing to give the gear an Obsidian tip.",
                "An obsidian tablet that can tip golden tools with obsidian"
        );
        addItemWithDesc(builder, ModItem.HEART_OF_THE_WARDEN.get(), "Heart of the Warden",
                "Dropped by the Warden. Using it on a Sculk Catalyst allows for the collection of a very exotic ore.",
                "Proof of the indomitable human spirit at work - that even the worst horrors can be overcome"
        );
        addItemWithDesc(builder, ModItem.SHULKER_RESIDUE.get(), "Shulker Residue",
                "Collected by smashing a Shulker's bullet. Can be used to make potions, bricks and other goods.",
                "Basically glorified shulker spit; can be turned into bricks and other goods"
        );
        addItemWithDesc(builder, ModItem.WITHERED_ESSENCE.get(), "Withered Essence",
                "Dropped by the Wither, used in crafting a variety of useful items.",
                "A foggy substance from the Wither that makes you uneasy just looking at"
        );
        addItemWithDesc(builder, ModItem.ONYX_BONE.get(), "Onyx Bone",
                "Collected by killing a Wither skeleton. Can be used to craft necro weave.",
                "\"Because bones & coal made absolutely zero sense\""
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE.get(), "Necro Weave",
                "Crafted from Onyx Bones and Wool. Can be crafted into armor.",
                "#literallyterraria"
        );
        addItemWithDesc(builder, ModItem.ANCIENT_ROSE_SEED.get(), "Ancient Rose Seed",
                "Grows into an Ancient Rose and is obtained by letting a Sniffer sniff it out of the ground.",
                "When planted it will grow into an ancient rose"
        );
        addItemWithDesc(builder, ModItem.ECTOPLASM.get(), "Ectoplasm",
                "Dropped by Ghasts when they die. Mainly used to craft Mourning gold ingots.",
                "A sticky substance used to craft mourning gold ingots"
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_INGOT.get(), "Mourning Gold Ingot",
                "Crafted with Gold Ingots, Ectoplasm and Quartz. Can be used to make unique tools.",
                "A spectral metal used to make equipment"
        );
        addItemWithDesc(builder, ModItem.BLACK_EMERALD.get(), "Black Emerald",
                "A rare variant of Emerald. Can be used to craft rare tools.",
                "\"She is the most powerful emerald\""
        );
        addItemWithDesc(builder, ModItem.MARSHMALLOW.get(), "Marshmallow",
                "Can be consumed extremely fast. Restores 0.5 §f\uD83C\uDF56§r, or can be cooked on a campfire (or furnace, if you hate fun).",
                "How many can you fit in your mouth?"
        );
        addItemWithDesc(builder, ModItem.ROASTED_MARSHMALLOW.get(), "Roasted Marshmallow",
                "Restores 1 §f\uD83C\uDF56§r. Can be consumed extremely fast.",
                "And for once, it didn't get set on fire and burn to a crisp!"
        );
        addItemWithDesc(builder, ModItem.INVOKE_SHARD.get(), "Invoke Shard",
                "A magical shard that can be crafted into Totems of Undying & other powerful items.",
                "A magical shard dropped by Evokers, useful in creating totems and tomes"
        );
        addItemWithDesc(builder, ModItem.NACRE_BRICK.get(), "Nacre Brick",
                "Baked from Shulker residue in a furnace.",
                "A shiny, slick brick that can be put together to create a block"
        );
        addItemWithDesc(builder, ModItem.LEVI_ROLL.get(), "Levi Roll",
                "Restores 1.5 " + L4J_FOOD + ". Eating this can cause you to momentarily levitate.",
                "A snack so good it'll sweep you off your feet...literally!"
        );
        addItemWithDesc(builder, ModItem.FRUITCAKE_SLICE.get(), "Slice of Fruitcake",
                "Restores 3 " + L4J_FOOD + ". Using while full will throw it, dealing 6 damage to any mob.",
                "A rich cake often filled with fruit and nuts; fun to throw!"
        );
        addItemWithDesc(builder, ModItem.POMEGRANATE.get(), "Pomegranate",
                "Restores 0.5 " + L4J_FOOD + ". Has a chance to not be consumed on use.",
                "A shiny fruit with delicious seeds, has a chance to not be eaten when used"
        );
        addItemWithDesc(builder, ModItem.TRUFFLE.get(), "Truffle",
                "A very rare mushroom obtained by letting a Sniffer sniff it out of Mycelium. Can also pacify Hoglins, preventing them from becoming Zoglins.",
                "A delicious delicacy - can be turned into food or fed to Hoglins"
        );
        addItemWithDesc(builder, ModItem.TRUFFLE_OIL.get(), "Truffle Oil",
                "Restores 0.5 " + L4J_FOOD + ", but is extremely saturating. Drinking this can cause Hunger, however",
                "A savory, highly saturating vial of oil from truffles"
        );
        addItemWithDesc(builder, ModItem.TRUFFLE_POTATO_PUFF.get(), "Truffle Potato Puff",
                "Restores 2 " + L4J_FOOD + " and provides moderate saturation. Can be eaten more quickly than most other foods.",
                "Artyrian can tell you stories about his old job with this one!"
        );
        addItemWithDesc(builder, ModItem.RAW_VERDINITE.get(), "Raw Verdinite",
                "Can be smelted in a furnace to create a verdinite ingot.",
                "Despite its looks, it's not actually radioactive"
        );
        addItemWithDesc(builder, ModItem.VERDINITE_INGOT.get(), "Verdinite Ingot",
                null,
                "A swirly green-blue metal used to create even stronger equipment"
        );
        addItemWithDesc(builder, ModItem.TABLET_FRAGMENT.get(), "Tablet Fragment",
                "Can be used to craft a Cursed Tablet.",
                "A remnant of a magical tablet"
        );
        addItemWithDesc(builder, ModItem.APPLE_OF_ENLIGHTENMENT.get(), "Apple of Enlightenment",
                "An extremely rare food item. Permanently adds 2 §f\uD83D\uDC96§r to your maximum health when first eaten, changing your health's appearance (§f\uD83D\uDC97§r).",
                "Increases your max health by 4 - let's see EarthBound do that one"
        );
        addItemWithDesc(builder, ModItem.LIGHTNING_IN_A_BOTTLE.get(), "Lightning in a Bottle",
                "Created when lightning strikes a brewing stand with an attached lightning rod, as long as it contains glass bottles. Can be thrown or used in crafting.",
                "...seriously?! It's an IDIOM! You weren't supposed to actually go and DO IT!!!"
        );
        addItemWithDesc(builder, ModItem.PURIFIED_END_CRYSTAL.get(), "Purified End Crystal",
                "A variant of the End Crystal that can be used to further power up Enchanting Tables. Does not explode when hit, instead dropping itself.",
                "Can be placed in corners surrounding your enchanting table to power it up"
        );
        addItemWithDesc(builder, ModItem.END_CRYSTAL_SHARD.get(), "End Crystal Shard",
                "Dropped by destroyed End Crystals. Can be used in crafting, or used to gain Quick Flight.",
                "Can be smashed to gain temporary flight powers"
        );
        addItemWithDesc(builder, ModItem.RAVAGER_TOOTH.get(), "Ravager Tooth",
                "Dropped by Ravagers. Can be used in crafting.",
                "\"Dude, you knocked its teeth out!\""
        );
        addItemWithDesc(builder, ModItem.INCENSE.get(), "Incense",
                "The pure essence of anger, dropped by Vexes. Can be used in crafting many spirit-based items.",
                "Did you know this is synonymous with \"vex\"? Cool I know"
        );
        addItemWithDesc(builder, ModItem.ONYX_MEAL.get(), "Onyx Meal",
                "Used to instantly grow Nether Wart and Warped Wart, and will destroy nearby grasses when used on Grass Blocks. Can be used to craft Black Dye.",
                "Crushed onyx bones that can kill tall grass and grow nether wart"
        );
        addItemWithDesc(builder, ModItem.PITCH_INGOT.get(), "Pitch Ingot",
                "A strange ingot which can be used to craft tools made from this material. Created by smelting ore in a furnace.",
                "A weird, sculky ingot used to craft special equipment"
        );
        addItemWithDesc(builder, ModItem.TOWER_KEY_FRAGMENT.get(), "Key Fragment",
                "Can be used to craft a Tower Key.",
                "A broken piece of a special key"
        );
        addItemWithDesc(builder, ModItem.TOWER_KEY.get(), "Tower Key",
                "Can be used at a Tower Vault to unlock it.",
                "Unlocks special vaults located inside the Tower"
        );
        addItemWithDesc(builder, ModItem.WARPED_WART.get(), "Warped Wart",
                "Used in advanced potion brewing. This can be found naturally growing in Bastion Remnants. It can also be planted on Soul Sand.",
                "A rare fungus that grows on soul sand, a common ingredient for advanced potion brewing"
        );
        addItemWithDesc(builder, ModItem.VIVULITE_INGOT.get(), "Vivulite Ingot",
                null,
                "An iridescent red-violet metal used to create really powerful equipment"
        );
        addItemWithDesc(builder, ModItem.RAW_VIVULITE.get(), "Raw Vivulite",
                "Can be smelted in a furnace to create a vivulite ingot.",
                "A chunk of vivulite that can be smelted into an ingot"
        );
        addItemWithDesc(builder, ModItem.VOID_PEARL.get(), "Void's Eye",
                "When thrown, will show the direction to an End Portal. When twelve of these are placed in the End Portal Frames, the End Portal will be activated.",
                "Can be used to track a stronghold or to fill an end portal frame"
                // "Consumed on use, allowing access to a secure personal storage."
                // "Opens a rift into personal storage when used"
        );
        addItemWithDesc(builder, ModItem.UNFINISHED_CORE.get(), "Unfinished Core",
                "Found in Bastion Remnants. Must be combined with 4 unique Core Plates to make a Reactive Core.",
                "Can be brought to completion with four unique plates"
        );
        addItemWithDesc(builder, ModItem.REACTIVE_CORE.get(), "Reactive Core",
                "Crafted from an Unfinished Core and the 4 unique Core Plates. Used to craft a Strange Core.",
                "A fully-assembled core, used for crafting a special block"
        );
        addItemWithDesc(builder, ModItem.DEPTHS_CORE_PLATE.get(), "Core Plate",
                "A Core Plate made from rare ores. Used in crafting a Reactive Core.",
                "Combine with 3 other plates & an unfinished core to complete it"
        );
        addItemWithDesc(builder, ModItem.FRONTAL_CORE_PLATE.get(), "Core Plate",
                "A Core Plate made from exotic ores. Used in crafting a Reactive Core.",
                "Combine with 3 other plates & an unfinished core to complete it"
        );
        addItemWithDesc(builder, ModItem.GUARDIAN_SLICE.get(), "Raw Guardian Slice",
                "Restores 1.5 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A raw piece of meat, or is it a fish?"
        );
        addItemWithDesc(builder, ModItem.ELDER_GUARDIAN_SLICE.get(), "Raw Elder Guardian Slice",
                "Restores 2 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A raw piece of meat, or is it a fish?"
        );
        addItemWithDesc(builder, ModItem.COOKED_GUARDIAN_SLICE.get(), "Cooked Guardian Slice",
                "Restores 3 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A searing piece of meat, tastes like sushi!"
        );
        addItemWithDesc(builder, ModItem.COOKED_ELDER_GUARDIAN_SLICE.get(), "Cooked Elder Guardian Slice",
                "Restores 4 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A searing piece of meat, tastes like sushi!"
        );
        addItemWithDesc(builder, ModItem.PALE_PRISMARINE_SHARD.get(), "Pale Prismarine Shard",
                "Dropped by Elder Guardians. Can be used in crafting alternate colors of Prismarine blocks.",
                "A sharp remnant of a rare stone"
        );
        addItemWithDesc(builder, ModItem.MUSIC_DISC_DIAPHRAGM.get(), music_disc,
                "Can be played in a jukebox.",
                "Can be inserted into a jukebox to play shrill tunes"
        );
        addItemWithDesc(builder, ModItem.TOTEM_OF_AVARICE.get(), "Totem of Avarice",
                "Dying with this in your inventory will allow you to keep your items. Consumed on use.",
                "Keep your items on death, but not your levels!"
        );
        addItemWithDesc(builder, ModItem.VOID_DIAMOND.get(), "Void Diamond",
                "Use these to create some of the strongest, most powerful weapons and armor in the world.",
                "A precious gemstone infused with dark energy, useful for creating legendary equipment"
        );
        addItemWithDesc(builder, ModItem.CHEST_KEY.get(), "Chest Key",
                "Can be used on a Personal Chest that you own to give its assigned player access to it.",
                "Use on a player to link them, then use on your personal chest to give that player access"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with a Vivulite Helmet and the correct Brimtan Shell to make a Brimtan Helmet.",
                "A glowing obsidian tablet that can upgrade vivulite helmets to brimtan"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with a Vivulite Chestplate and the correct Brimtan Shell to make a Brimtan Chestplate.",
                "A glowing obsidian tablet that can upgrade vivulite chestplates to brimtan"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with Vivulite Leggings and the correct Brimtan Shell to make Brimtan Leggings.",
                "A glowing obsidian tablet that can upgrade vivulite leggings to brimtan"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with Vivulite Boots and the correct Brimtan Shell to make Brimtan Boots.",
                "A glowing obsidian tablet that can upgrade vivulite boots to brimtan"
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with a Vivulite Tool and the correct Brimtan Shell to make a Brimtan Tool.",
                "A glowing obsidian tablet that can upgrade vivulite tools to brimtan"
        );
        addItemWithDesc(builder, ModItem.PALE_TRIDENT.get(), "Pale Trident",
                "A more powerful Trident crafted from Elder Guardian Spines.",
                "\"Upgrades, people, upgrades!\""
        );
        addItemWithDesc(builder, ModItem.ELDER_GUARDIAN_SPINE.get(), "Elder Guardian Spine",
                "Can be used to craft a Pale Trident.",
                "Possibly one of the most pointless items ever made"
        );
        addItemWithDesc(builder, ModItem.EXPERIWINKLE_BULB.get(), "Experiwinkle Bulb",
                "Can be planted on farmland to grow an Experiwinkle. It cannot be bonemealed.",
                "When planted it will grow into an experiwinkle"
        );
        addItemWithDesc(builder, ModItem.FROST_BONE.get(), "Frost Bone",
                "Collected by killing a Stray. Can be crafted into snow melt.",
                "Only the finest of item bloat"
        );
        addItemWithDesc(builder, ModItem.SNOW_MELT.get(), "Snow Melt",
                "Will melt snow layers in a small radius.",
                "Melts nearby snow when placed on a block"
        );
        addItemWithDesc(builder, ModItem.MESSAGE_IN_A_BOTTLE.get(), "Message in a Bottle",
                "Can hold a small amount of text.",
                "It's like that one song by The Police!"
        );
        addItemWithDesc(builder, ModItem.BOTTLED_MESSAGE.get(), "Bottled Message",
                "A note from an anonymous source. Throwing it into open water in an Ocean biome will allow it to be fished up in any Ocean biome.",
                "An anonymous note in a bottle...I wonder what it says?"
        );
        addItemWithDesc(builder, ModItem.MANA_BOTTLE.get(), "Bottle o' Magicks",
                "When thrown, it drops Mana Orbs which increase your mana meter when collected.",
                "A glowing bottle that contains a small amount of mana"
        );
        addItemWithDesc(builder, ModItem.SPAWNER_CHUNK.get(), "Spawner Chunk",
                "A piece of a Monster Spawner. Can be used to craft a Monster Bakery.",
                "A piece from a spawner, used to craft monster bakeries"
        );
        addItemWithDesc(builder, ModItem.GOLDEN_EGG.get(), "Golden Egg",
                "Laid by chickens when fed a Golden Nugget. Can be consumed to earn extra Experience from mobs.",
                "When thrown it effects all nearby with allurement - there is a chance to spawn golden chickens"
        );
        addItemWithDesc(builder, ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), smithing_template,
                null,
                "A granite tablet that can be used to trim armor"
        );
        addItemWithDesc(builder, ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), smithing_template,
                null,
                "A slime tablet that can be used to trim armor"
        );
        addItemWithDesc(builder, ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), smithing_template,
                null,
                "An onyx bone tablet that can be used to trim armor"
        );
        addItemWithDesc(builder, ModItem.SOUL.get(), "Soul",
                "A important resource dropped by stronger enemies.",
                "A special material dropped by powerful enemies"
        );
        addItemWithDesc(builder, ModItem.HARDENED_SLIME.get(), "Hardened Slime",
                "Crystallized slime found rarely underground. Can be used to craft Slime Shoes.",
                "A crystal made of slime, used to craft a bouncy set of boots"
        );
        addItemWithDesc(builder, ModItem.BAIT.get(), "Bait",
                "Can be thrown into water to drastically increase the chances of catching fish in that area.",
                "It used to be believable, but can still attract fish when thrown in water"
        );
    }

    private void doItemArmor(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String shell = "Brimtan Shell";

        // Necro Weave
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_HELMET.get(), "Rotcross Helm",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_CHESTPLATE.get(), "Rotcross Suit Top",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_LEGGINGS.get(), "Rotcross Suit Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_BOOTS.get(), "Rotcross Treads",
                "",
                ""
        );
        // Mourning Gold
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_HELMET.get(), "Mourning Gold Helmet",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_CHESTPLATE.get(), "Mourning Gold Chestplate",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_LEGGINGS.get(), "Mourning Gold Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_BOOTS.get(), "Mourning Gold Boots",
                "",
                ""
        );
        // Cobalt
        addItemWithDesc(builder, ModItem.COBALT_HELMET.get(), "Cobalt Helmet",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_CHESTPLATE.get(), "Cobalt Chestplate",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_LEGGINGS.get(), "Cobalt Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_BOOTS.get(), "Cobalt Boots",
                "",
                ""
        );
        // Frostite
        addItemWithDesc(builder, ModItem.FROSTITE_HELMET.get(), "Frostite Helmet",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_CHESTPLATE.get(), "Frostite Chestplate",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_LEGGINGS.get(), "Frostite Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_BOOTS.get(), "Frostite Boots",
                "",
                ""
        );
        // Plate
        addItemWithDesc(builder, ModItem.PLATE_HELMET.get(), "Plate Helm",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.PLATE_CHESTPLATE.get(), "Plate Chestpiece",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.PLATE_LEGGINGS.get(), "Plate Legpiece",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.PLATE_BOOTS.get(), "Plate Boots",
                "",
                ""
        );
        // Verdinite
        addItemWithDesc(builder, ModItem.VERDINITE_HELMET.get(), "Verdinite Helmet",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_CHESTPLATE.get(), "Verdinite Chestplate",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_LEGGINGS.get(), "Verdinite Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_BOOTS.get(), "Verdinite Boots",
                "",
                ""
        );
        // Vivulite
        addItemWithDesc(builder, ModItem.VIVULITE_HELMET.get(), "Vivulite Helmet",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_CHESTPLATE.get(), "Vivulite Chestplate",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_LEGGINGS.get(), "Vivulite Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_BOOTS.get(), "Vivulite Boots",
                "",
                ""
        );
        // Brimtan
        addItemWithDesc(builder, ModItem.BRIMTAN_HELMET.get(), "Brimtan Helmet",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_CHESTPLATE.get(), "Brimtan Chestplate",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_LEGGINGS.get(), "Brimtan Leggings",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_BOOTS.get(), "Brimtan Boots",
                "",
                ""
        );
        // Brimtan Shells
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_HELMET.get(), shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_CHESTPLATE.get(), shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_LEGGINGS.get(), shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_BOOTS.get(), shell,
                "",
                ""
        );
        // Miscs
        addItemWithDesc(builder, ModItem.WITCH_HAT.get(), "Witch Hat",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.SLIME_SHOES.get(), "Slime Shoes",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_HORSE_ARMOR.get(), "Cobalt Horse Armor",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_HORSE_ARMOR.get(), "Verdinite Horse Armor",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_HORSE_ARMOR.get(), "Vivulite Horse Armor",
                "",
                ""
        );
    }

    private void doItemTool(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String brimtan_shell = "Brimtan Shell";
        String arrowhead = "Arrowhead";
        String arrowhead_yt = "Can be used at a fletching table to make its respective specialty arrow";
        String arrow_prefix_yt = "A projectile that can be shot from some weapons";
        String ball_yt = "Here, kid, have a ball!";

        // Mourning Gold
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_PICKAXE.get(), "Mourning Gold Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_AXE.get(), "Mourning Gold Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_SWORD.get(), "Mourning Gold Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_SHOVEL.get(), "Mourning Gold Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_HOE.get(), "Mourning Gold Hoe",
                "",
                ""
        );
        // Obsidian
        addItemWithDesc(builder, ModItem.OBSIDIAN_PICKAXE.get(), "Obsidian-Tipped Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_PICKAXE_BROKEN.get(), "Broken Obsidian-Tipped Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_AXE.get(), "Obsidian-Tipped Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_AXE_BROKEN.get(), "Broken Obsidian-Tipped Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SWORD.get(), "Obsidian-Tipped Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SWORD_BROKEN.get(), "Broken Obsidian-Tipped Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SHOVEL.get(), "Obsidian-Tipped Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SHOVEL_BROKEN.get(), "Broken Obsidian-Tipped Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_HOE.get(), "Obsidian-Tipped Hoe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_HOE_BROKEN.get(), "Broken Obsidian-Tipped Hoe",
                "",
                ""
        );
        // Cobalt
        addItemWithDesc(builder, ModItem.COBALT_PICKAXE.get(), "Cobalt Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_AXE.get(), "Cobalt Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_SWORD.get(), "Cobalt Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_SHOVEL.get(), "Cobalt Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.COBALT_HOE.get(), "Cobalt Hoe",
                "",
                ""
        );
        // Frostite
        addItemWithDesc(builder, ModItem.FROSTITE_PICKAXE.get(), "Frostite Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_AXE.get(), "Frostite Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_SWORD.get(), "Frostite Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_SHOVEL.get(), "Frostite Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.FROSTITE_HOE.get(), "Frostite Hoe",
                "",
                ""
        );
        // Verdinite
        addItemWithDesc(builder, ModItem.VERDINITE_PICKAXE.get(), "Verdinite Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_AXE.get(), "Verdinite Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_SWORD.get(), "Verdinite Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_SHOVEL.get(), "Verdinite Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_HOE.get(), "Verdinite Hoe",
                "",
                ""
        );
        // Vivulite
        addItemWithDesc(builder, ModItem.VIVULITE_PICKAXE.get(), "Vivulite Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_AXE.get(), "Vivulite Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_SWORD.get(), "Vivulite Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_SHOVEL.get(), "Vivulite Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VIVULITE_HOE.get(), "Vivulite Hoe",
                "",
                ""
        );
        // Brimtan
        addItemWithDesc(builder, ModItem.BRIMTAN_PICKAXE.get(), "Brimtan Pickaxe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_PICKAXE.get(), brimtan_shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_AXE.get(), "Brimtan Axe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_AXE.get(), brimtan_shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SWORD.get(), "Brimtan Sword",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_SWORD.get(), brimtan_shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHOVEL.get(), "Brimtan Shovel",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_SHOVEL.get(), brimtan_shell,
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_HOE.get(), "Brimtan Hoe",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_HOE.get(), brimtan_shell,
                "",
                ""
        );

        // Bows
        addItemWithDesc(builder, ModItem.COPPER_BOW.get(), "Copper Bow",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.IRON_BOW.get(), "Iron Bow",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.DIAMOND_BOW.get(), "Diamond Bow",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.NETHERITE_BOW.get(), "Netherite Bow",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.ECHO_BOW.get(), "Echo Bow",
                "",
                ""
        );
        addItemWithDesc(builder, ModItem.VERDINITE_BOW.get(), "Verdinite Bow",
                "",
                ""
        );
        // Tomes
        addItemWithDesc(builder, ModItem.TOME_OF_FANGS.get(), "Tome of Fangs",
                "",
                ""
        );
        // Arrowheads
        addItemWithDesc(builder, ModItem.WARP_ARROW.get(), "Warp Arrow",
                "",
                arrow_prefix_yt + " to teleport the shooter to where it lands"
        );
        addItemWithDesc(builder, ModItem.WARP_ARROW_ARROWHEAD.get(), arrowhead,
                "",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.SUBZERO_ARROW.get(), "Subzero Arrow",
                "",
                arrow_prefix_yt + " to freeze the target"
        );
        addItemWithDesc(builder, ModItem.SUBZERO_ARROW_ARROWHEAD.get(), arrowhead,
                "",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.BOUNCY_ARROW.get(), "Bouncy Arrow",
                "",
                arrow_prefix_yt + " and bounces on impact"
        );
        addItemWithDesc(builder, ModItem.BOUNCY_ARROW_ARROWHEAD.get(), arrowhead,
                "",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.DYNAMITE_ARROW.get(), "Dynamite Arrow",
                "",
                arrow_prefix_yt + " and explodes on impact"
        );
        addItemWithDesc(builder, ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), arrowhead,
                "",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.PRISMARINE_ARROW.get(), "Prismarine Arrow",
                "",
                arrow_prefix_yt + "; travels quickly underwater"
        );
        addItemWithDesc(builder, ModItem.PRISMARINE_ARROW_ARROWHEAD.get(), arrowhead,
                "",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), arrowhead,
                "",
                arrowhead_yt
        );
        // Spawn Eggs
        addItemWithDesc(builder, ModItem.CRAWLER_SPAWN_EGG.get(), "Crawler Spawn Egg",
                "",
                "A deadlier version of a creeper with bigger explosions and more health"
        );
        addItemWithDesc(builder, ModItem.JUNGLE_SPIDER_SPAWN_EGG.get(), "Jungle Spider Spawn Egg",
                "",
                "A tiny monster that moves fast and inflicts weakness"
        );
        addItemWithDesc(builder, ModItem.PUMPKIN_GOLEM_SPAWN_EGG.get(), "Pumpkin Golem Spawn Egg",
                "",
                "A mischievous golem that picks and replants most crops it comes across"
        );
        addItemWithDesc(builder, ModItem.CROW_SPAWN_EGG.get(), "Crow Spawn Egg",
                "",
                "A noisy, intelligent bird that's often attracted to shiny things"
        );
        addItemWithDesc(builder, ModItem.GOLDEN_CHICKEN_SPAWN_EGG.get(), "Golden Chicken Spawn Egg",
                "",
                "A golden variant of chicken that rarely lays golden eggs"
        );
        // Balls
        addItemWithDesc(builder, ModItem.BALL.get(), "Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.BOUNCY_BALL.get(), "Bouncy Ball",
                "",
                "Now with built-in bounce action!"
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.WHITE).get(), "White Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), "Light Gray Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.GRAY).get(), "Gray Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.BLACK).get(), "Black Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.BROWN).get(), "Brown Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.RED).get(), "Red Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.ORANGE).get(), "Orange Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.YELLOW).get(), "Yellow Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.LIME).get(), "Lime Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.GREEN).get(), "Green Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.CYAN).get(), "Cyan Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), "Light Blue Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.BLUE).get(), "Blue Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.PURPLE).get(), "Purple Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.MAGENTA).get(), "Magenta Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.PINK).get(), "White Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.coral_ball", "Coral Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.canary_ball", "Canary Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.wasabi_ball", "Wasabi Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.sacramento_ball", "Sacramento Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.sky_ball", "Sky Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.blurple_ball", "Blurple Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.sangria_ball", "Sangria Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.rose_ball", "Rose Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.umber_ball", "Umber Ball",
                null,
                ball_yt
        );
        addItemDescRaw(builder, "item.frontiers.lavender_ball", "Lavender Ball",
                null,
                ball_yt
        );
    }

    private void doItemMisc(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String additions = "additions_slot_description";
        String applies = "applies_to";
        String base = "base_slot_description";
        String ingredients = "ingredients";
        String desc = "desc";

        // Obsidian Template
        addTemplateUpgrade(builder, Frontiers.MOD_ID, "obsidian_upgrade", "Obsidian Upgrade",
                "Add Obsidian Casing",
                "Golden Tools",
                "Add golden weapon or tool",
                "Obsidian Casing"
        );
        // Brimtan Helmet Template
        addTemplateUpgrade(builder, Frontiers.MOD_ID, "brimtan_helmet_upgrade", "Brimtan Upgrade (Helmet)",
                "Add Brimtan Shell (Helmet)",
                "Vivulite Helmet",
                "Add Vivulite Helmet",
                "Brimtan Shell (Helmet)"
        );
        // Brimtan Chest Template
        addTemplateUpgrade(builder, Frontiers.MOD_ID, "brimtan_chestplate_upgrade", "Brimtan Upgrade (Chestplate)",
                "Add Brimtan Shell (Chestplate)",
                "Vivulite Chestplate",
                "Add Vivulite Chestplate",
                "Brimtan Shell (Chestplate)"
        );
        // Brimtan Leggings Template
        addTemplateUpgrade(builder, Frontiers.MOD_ID, "brimtan_leggings_upgrade", "Brimtan Upgrade (Leggings)",
                "Add Brimtan Shell (Leggings)",
                "Vivulite Leggings",
                "Add Vivulite Leggings",
                "Brimtan Shell (Leggings)"
        );
        // Brimtan Boots Template
        addTemplateUpgrade(builder, Frontiers.MOD_ID, "brimtan_boots_upgrade", "Brimtan Upgrade (Boots)",
                "Add Brimtan Shell (Boots)",
                "Vivulite Boots",
                "Add Vivulite Boots",
                "Brimtan Shell (Boots)"
        );
        // Brimtan Tool Template
        addTemplateUpgrade(builder, Frontiers.MOD_ID, "brimtan_tool_upgrade", "Brimtan Upgrade (Tool)",
                "Add matching Brimtan Shell",
                "Vivulite Tools",
                "Add Vivulite Tool",
                "Brimtan Shell"
        );
        // Descriptions
        addItemExtra(builder, ModItem.MUSIC_DISC_DIAPHRAGM.get(), desc, "Artyrian - Diaphragm");
        addItemExtra(builder, ModItem.TABLET_FRAGMENT.get(), desc, "Cursed Tablet");
        addItemExtra(builder, ModItem.TOWER_KEY_FRAGMENT.get(), desc, "Tower Key");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_PICKAXE.get(), desc, "Brimtan Pickaxe");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_AXE.get(), desc, "Brimtan Axe");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_SWORD.get(), desc, "Brimtan Sword");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_SHOVEL.get(), desc, "Brimtan Shovel");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_HOE.get(), desc, "Brimtan Hoe");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_HELMET.get(), desc, "Brimtan Helmet");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_CHESTPLATE.get(), desc, "Brimtan Chestplate");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_LEGGINGS.get(), desc, "Brimtan Leggings");
        addItemExtra(builder, ModItem.BRIMTAN_SHELL_BOOTS.get(), desc, "Brimtan Boots");
        // Cobalt Shield Colors
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "black", "Black Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "blue", "Blue Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "brown", "Brown Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "cyan", "Cyan Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "gray", "Gray Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "green", "Green Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "light_blue", "Light Blue Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "light_gray", "Light Gray Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "lime", "Lime Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "magenta", "Magenta Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "orange", "Orange Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "pink", "Pink Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "purple", "Purple Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "red", "Red Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "white", "White Cobalt Shield");
        addItemExtra(builder, ModItem.COBALT_SHIELD.get(), "yellow", "Yellow Cobalt Shield");
        // Trim Patterns
        addRaw(builder, "trim_pattern.frontiers.pulse", "Pulse Armor Trim");
        addRaw(builder, "trim_pattern.frontiers.sludge", "Sludge Armor Trim");
        addRaw(builder, "trim_pattern.frontiers.photon", "Photon Armor Trim");
        // Trim Materials
        addRaw(builder, "trim_material.frontiers.cobalt", "Cobalt Material");
        addRaw(builder, "trim_material.frontiers.verdinite", "Verdinite Material");
        addRaw(builder, "trim_material.frontiers.vivulite", "Vivulite Material");
        addRaw(builder, "trim_material.frontiers.frostite", "Frostite Material");
        addRaw(builder, "trim_material.frontiers.mourning_gold", "Mourning Gold Material");
        addRaw(builder, "trim_material.frontiers.brimtan", "Brimtan Material");
        // Arrowhead Desc.
        addRaw(builder, "arrowhead.frontiers.subzero", "Subzero");
        addRaw(builder, "arrowhead.frontiers.warp", "Warp");
        addRaw(builder, "arrowhead.frontiers.bouncy", "Bouncy");
        addRaw(builder, "arrowhead.frontiers.spectral", "Spectral");
        addRaw(builder, "arrowhead.frontiers.dynamite", "Dynamite");
        addRaw(builder, "arrowhead.frontiers.prismarine", "Prismarine");
        // Core Plate Names
        addRaw(builder, "core_plate_type.frontiers.depths", "Type: Depths");
        addRaw(builder, "core_plate_type.frontiers.frontal", "Type: Frontal");
        addRaw(builder, "core_plate_type.frontiers.3", "Type: Trek");
        addRaw(builder, "core_plate_type.frontiers.4", "Type: Alpha");
        // Item additional name data
        addItemExtra(builder, ModItem.CHEST_KEY.get(), "named", "Assigned Chest Key");
        addRaw(builder, "item.frontiers.core_plate.header", "Combine with:");
        addRaw(builder, "item.frontiers.arrowhead.header", "Creates:");
        addRaw(builder, "item.frontiers.arrowhead.footer", "%1$s (x6)");
    }

    private void doEntity(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addEntity(builder, ModEntity.SUBZERO_ARROW.get(), "Subzero Arrow");
        addEntity(builder, ModEntity.WARP_ARROW.get(), "Warp Arrow");
        addEntity(builder, ModEntity.DYNAMITE_ARROW.get(), "Dynamite Arrow");
        addEntity(builder, ModEntity.BOUNCY_ARROW.get(), "Bouncy Arrow");
        addEntity(builder, ModEntity.PRISMARINE_ARROW.get(), "Prismarine Arrow");

        addEntity(builder, ModEntity.CRAGS_STALKER.get(), "§f§kMANIFEST§r");
        addEntity(builder, ModEntity.CRAGS_MONSTER.get(), "§f§kENTITY§r");

        addEntity(builder, ModEntity.BALL.get(), "Ball");
        addEntity(builder, ModEntity.FRUITCAKE.get(), "Fruitcake");
        addEntity(builder, ModEntity.MANA_ORB.get(), "Mana Orb");
        addEntity(builder, ModEntity.MANA_BOTTLE.get(), "Bottle o' Magicks");
        addEntity(builder, ModEntity.GOLDEN_EGG.get(), "Golden Egg");

        addEntity(builder, ModEntity.CRAWLER.get(), "Crawler");
        addEntity(builder, ModEntity.JUNGLE_SPIDER.get(), "Jungle Spider");
        addEntity(builder, ModEntity.PUMPKIN_GOLEM.get(), "Pumpkin Golem");
        addEntity(builder, ModEntity.CROW.get(), "Crow");
        addEntity(builder, ModEntity.GOLDEN_CHICKEN.get(), "Golden Chicken");

        // Ball Displays
        String baller = ModEntity.BALL.get().getDescriptionId();
        addRaw(builder, baller + ".caught", "%1$s caught the %2$s!");
        addRaw(builder, baller + ".hit", "%1$s hit the %2$s!");
        addRaw(builder, baller + ".intercept", "%1$s intercepted the %2$s!");
        addRaw(builder, baller + ".stopped", "%1$s stopped the %2$s!");
        addRaw(builder, baller + ".got_hit", "%1$s got hit with the %2$s!");
        addRaw(builder, baller + ".dropped", "%1$s dropped the %2$s!");
        addRaw(builder, baller + ".hit_ground", "%1$s hit the ground!");
        addRaw(builder, baller + ".dispenser", "%1$s dispensed!");
    }

    private void doPainting(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String its_me = "Artyrian";

        addPainting(builder, Frontiers.MOD_ID, "the_sea_of_silence", "The Sea of Silence (Expanded)", "R. Teare (Artyrian)");
        addPainting(builder, Frontiers.MOD_ID, "the_orb", "The Orb", its_me);
        addPainting(builder, Frontiers.MOD_ID, "the_mansion", "The Mansion", its_me);
        addPainting(builder, Frontiers.MOD_ID, "watching", "Watching", its_me);
        addPainting(builder, Frontiers.MOD_ID, "this_thing", "This Thing", its_me);
        addPainting(builder, Frontiers.MOD_ID, "awful", "Awful", "Adapted by Artyrian");
    }

    private void doEffectsAndEtc(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        // Items
        addPotionItems(builder, Frontiers.MOD_ID, "levitation",
                "Potion of Levitation", "Splash Potion of Levitation", "Lingering Potion of Levitation", "Arrow of Levitation");
        addPotionItems(builder, Frontiers.MOD_ID, "long_levitation",
                "Potion of Levitation", "Splash Potion of Levitation", "Lingering Potion of Levitation", "Arrow of Levitation");
        addPotionItems(builder, Frontiers.MOD_ID, "strong_levitation",
                "Potion of Levitation", "Splash Potion of Levitation", "Lingering Potion of Levitation", "Arrow of Levitation");
        addPotionItems(builder, Frontiers.MOD_ID, "debonair",
                "Debonair Potion", "Debonair Splash Potion", "Debonair Lingering Potion", "Tipped Arrow");
        addPotionItems(builder, Frontiers.MOD_ID, "gluttony",
                "Gluttunous Potion", "Gluttunous Splash Potion", "Gluttunous Lingering Potion", "Tipped Arrow");
        addPotionItems(builder, Frontiers.MOD_ID, "interesting_health",
                "Interesting Potion", "Interesting Splash Potion", "Interesting Lingering Potion", "Tipped Arrow");
        addPotionItems(builder, Frontiers.MOD_ID, "interesting_harm",
                "Interesting Potion", "Interesting Splash Potion", "Interesting Lingering Potion", "Tipped Arrow");
        addPotionItems(builder, Frontiers.MOD_ID, "life_boost",
                "Potion of Life Boost", "Splash Potion of Life Boost", "Lingering Potion of Life Boost", "Arrow of Life Boost");
        addPotionItems(builder, Frontiers.MOD_ID, "turbo_regeneration",
                "Potion of Regeneration", "Splash Potion of Regeneration", "Lingering Potion of Regeneration", "Arrow of Regeneration");
        addPotionItems(builder, Frontiers.MOD_ID, "decay",
                "Potion of Decay", "Splash Potion of Decay", "Lingering Potion of Decay", "Arrow of Decay");
        addPotionItems(builder, Frontiers.MOD_ID, "magma_vision",
                "Potion of Magma Vision", "Splash Potion of Magma Vision", "Lingering Potion of Magma Vision", "Arrow of Magma Vision");
        addPotionItems(builder, Frontiers.MOD_ID, "long_magma_vision",
                "Potion of Magma Vision", "Splash Potion of Magma Vision", "Lingering Potion of Magma Vision", "Arrow of Magma Vision");

        // Effects
        addEffect(builder, ModStatusEffects.QUICK_FLIGHT, "Quick Flight");
        addEffect(builder, ModStatusEffects.STORM_POISONING, "Storm Poisoning");
        addEffect(builder, ModStatusEffects.MAGMA_VISION, "Magma Vision");
        addEffect(builder, ModStatusEffects.ALLUREMENT, "Allurement");
        addEffect(builder, ModStatusEffects.WELL_RESTED, "Well-Rested");
    }

    private void doAdv(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String adventure = "adventure";
        String husbandry = "husbandry";
        String nether = "nether";

        addAdv(builder, Frontiers.MOD_ID, "root", "Frontiers", "An adventure for the ages");
        addAdv(builder, Frontiers.MOD_ID, "smelt_cobalt", "Deep Azure", "Smelt a Cobalt Ingot");
        addAdv(builder, Frontiers.MOD_ID, "loot_tower", "Breaking the Tower", "Reach the loot room of the mysterious White Tower");
        addAdv(builder, Frontiers.MOD_ID, "smelt_frostite", "Stay Frosty", "Smelt a Frostite Ingot at the end of a long extraction process");
        addAdv(builder, Frontiers.MOD_ID, "eat_hpapple", "An Apple a Day", "Eat an Apple of Enlightenment");
        addAdv(builder, Frontiers.MOD_ID, "get_a_model", "Lifelike Replica", "Obtain a model");
        addAdv(builder, Frontiers.MOD_ID, "get_all_models", "Overworldian Psycho", "Obtain every model");
        addAdv(builder, Frontiers.MOD_ID, "full_vivulite_armor", "Cover Me in Scarlet", "Get a full suit of Vivulite armor");
        addAdv(builder, Frontiers.MOD_ID, "full_brimtan_armor", "Through the Fire and Flames", "Obtain a full suit of Brimtan armor");
        addAdv(builder, Frontiers.MOD_ID, "obtain_vivulite_anvil", "Stronger Than Steel", "Craft an indestructible Vivulite Anvil");
        addAdv(builder, Frontiers.MOD_ID, "break_curse", "Returning the Slab", "Purify a cursed item on a Curse Altar");
        addAdv(builder, Frontiers.MOD_ID, "obtain_glowing_obsidian", "Glow Up", "Transmute Obsidian into Glowing Obsidian");
        addAdv(builder, Frontiers.MOD_ID, "enter_crags", "Deeper Than Deep", "Enter the Crags, a dimension that was sealed away for good reasons...");
        addAdv(builder, Frontiers.MOD_ID, "purify_crystal", "The Purity Within", "Purify an End Crystal");

        addAdv(builder, adventure, "kill_warden", "Defiance", "Despite all odds, kill a Warden");
        addAdv(builder, adventure, "hit_ball_twenty", "We Ball", "Hit a ball 20 times in a row without letting it hit the ground");
        addAdv(builder, adventure, "sleep_in_phantom_bed", "A Good Night's Sleep", "Sleep in a super-cozy Phantom-Stitch Bed");
        addAdv(builder, adventure, "throw_fruitcake", "It's You!", "Kill a mob with a Fruitcake");

        addAdv(builder, husbandry, "get_violet_rose", "Imaginary Technique", "Obtain a Violet Rose. Is it technically a violet?");
        addAdv(builder, husbandry, "find_truffle", "Quite a Fungi", "Obtain a Truffle from a Sniffer by letting it dig on Mycelium");
        addAdv(builder, husbandry, "feed_truffle_to_hoglin", "Pigging Out", "Tame a Hoglin with a Truffle, making it immune to zombification");
        addAdv(builder, husbandry, "obtain_brimtan_hoe", "Playing a Losing Game", "Craft a Brimtan Hoe, even though it'll never fill the void in your soul");
        addAdv(builder, husbandry, "catch_bottled_message", "I'll Send an SOS to the World", "Catch a Message in a Bottle, yeah");
        addAdv(builder, husbandry, "summon_pumpkin_golem", "Jack o' All Trades", "Summon a Pumpkin Golem by placing a Spirit Candle inside a Carved Pumpkin");

        addAdv(builder, nether, "brew_lightning", "It's Just an Idiom", "Catch Lightning in a Bottle using a jury-rigged Brewing Stand");
        addAdv(builder, nether, "kill_wither", "The Beginning.", "Defeat the Wither and break its curse on the world");
        addAdv(builder, nether, "brimtan_beacon", "Sizzling Hot Beacon", "Use Brimtan Blocks to push a Beacon's range to its limits");
        addAdv(builder, nether, "use_enchanting_magnet", "Fountain of Knowledge", "Collect Experience Orbs in an Enchanting Magnet, then collect them with a Glass Bottle");
    }

    private void doEnchantments(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addRaw(builder, "enchantment.frontiers.brittle_curse", "Curse of Brittleness");
        addRaw(builder, "enchantment.frontiers.exp_curse", "Curse of Inexperience");
    }

    private void doContainers(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        Map<String, String> bakery = Map.of(
                "output", "Chance to spawn: %1$s",
                "error", "Unknown Entity!"
        );
        Map<String, String> bottled_message = Map.of(
                "write", "Finish",
                "title", "Write Message"
        );
        Map<String, String> curse_altar = Map.of(
                "uncurse", "Purify",
                "levelcount", "Requires %1$s levels!"
        );

        addContainer(builder, Frontiers.MOD_ID, "curse_altar", "Purify", curse_altar);
        addContainer(builder, Frontiers.MOD_ID, "fletching", "Create Arrows", null);
        addContainer(builder, Frontiers.MOD_ID, "personal_chest", "Personal Chest", null);
        addContainer(builder, Frontiers.MOD_ID, "bottled_message", null, bottled_message);
        addContainer(builder, Frontiers.MOD_ID, "monster_bakery", "Monster Bakery", bakery);
    }

    private void doSubtitles(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addRaw(builder, "sounds.frontiers.end_crystal_hit", "End Crystal cracks");
        addRaw(builder, "sounds.frontiers.end_crystal_wail", "End Crystal wails");
        addRaw(builder, "sounds.frontiers.end_crystal_explode", "End Crystal shatters");
        addRaw(builder, "sounds.frontiers.spell_cast_fangs", "Attack prepared");
        addRaw(builder, "sounds.frontiers.spell_cast", "Spell cast");
        addRaw(builder, "sounds.frontiers.verdinite_bow", "Arrow fired");
        addRaw(builder, "sounds.frontiers.void_pearl.use", "Eye of Ender smashed");
        addRaw(builder, "sounds.frontiers.end_crystal_shard.use", "End Crystal Shard shatters");
        addRaw(builder, "sounds.frontiers.ball.use", "Ball flies");
        addRaw(builder, "sounds.frontiers.bait.use", "Bait flies");
        addRaw(builder, "sounds.frontiers.block.ore.wither", "Ore withers away");
        addRaw(builder, "sounds.frontiers.equip.cobalt", "Cobalt armor clunks");
        addRaw(builder, "sounds.frontiers.wither_deflect_mace", "Mace deflected");
        addRaw(builder, "sounds.frontiers.snow_melt.use", "Snow Melt crinkles");
        addRaw(builder, "sounds.frontiers.bottled_message.splash", "Bottled Message deposits");
        addRaw(builder, "sounds.frontiers.echo_bow", "Arrow fired");
        addRaw(builder, "sounds.frontiers.generic.item.take", "Item taken");

        addRaw(builder, "sounds.frontiers.crow.hurt", "Crow hurts");
        addRaw(builder, "sounds.frontiers.crow.death", "Crow dies");
        addRaw(builder, "sounds.frontiers.crow.ambient", "Crow caws");
        addRaw(builder, "sounds.frontiers.crow.fly", "Crow flutters");

        addRaw(builder, "sounds.frontiers.ball.bounce", "Ball bounces");

        addRaw(builder, "sounds.frontiers.pumpkin_golem.hurt", "Pumpkin Golem hurts");
        addRaw(builder, "sounds.frontiers.pumpkin_golem.death", "Pumpkin Golem dies");
        addRaw(builder, "sounds.frontiers.pumpkin_golem.pick", "Pumpkin Golem picks");

        addRaw(builder, "sounds.frontiers.block.skull.steve", "Male hurts");
        addRaw(builder, "sounds.frontiers.block.skull.artyrian", "Madman rambles");
        addRaw(builder, "sounds.frontiers.block.skull.xenona", "Incomprehensible entity sounds");

        addRaw(builder, "subtitles.item.shears.shear_entity_for_model", "Shears cut");
        addRaw(builder, "subtitles.block.personal_chest.locked", "Personal Chest locks");
        addRaw(builder, "subtitles.block.slime_bulb.pick", "Hardened Slime pops");
        addRaw(builder, "subtitles.block.curse_altar.use", "Curse Altar used");
        addRaw(builder, "subtitles.block.beacon.brimtan", "Beacon drones");
        addRaw(builder, "subtitles.block.fletching_table.use", "Fletching Table used");
        addRaw(builder, "subtitles.item.chest_key.tag", "Chest Key tagged");
        addRaw(builder, "subtitles.item.chest_key.use", "Personal Chest accepts");
        addRaw(builder, "subtitles.item.golden_egg.use", "Egg cracks");
        addRaw(builder, "subtitles.entity.cragsmonster", "§f§kENTITY§r bellows");
        addRaw(builder, "subtitles.entity.mana_orb.pickup", "Mana gained");
    }

    private void doDmg(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addRaw(builder, "death.fell.accident.water", "%1$s failed the high dive");

        addDmgType(builder, "coreTouch", "%1$s reached the center of the world", "%1$s was pushed into the core by %2$s");
        addDmgType(builder, "quicksand", "%1$s suffocated in quicksand", "%1$s was forced into quicksand by %2$s");
        addDmgType(builder, "stormPoisoning", "%1$s succumbed to the End Storm", "%1$s was trapped in the End Storm by %2$s");
        addDmgType(builder, "evokerFangs", "%1$s was torn apart by fangs", "%1$s was torn apart by fangs while fighting %2$s");
        addDmgType(builder, "enderPearlWarp", "%1$s experienced warp torsion", "%1$s experienced warp torsion trying to escape %2$s");
        addDmgType(builder, "insanity", "%1$s was §f§k§lMURDERED§r by §f§k§lARTYRIAN_LOL§r", "%1$s was §f§k§lAMOGUS§r into §f§k§lARTYRIAN_LOL§r while trying to harm %2$s");
        addDmgType(builder, "ball", "%1$s took a fastball to the face", "%1$s was knocked out of the park by %2$s");
        addDmgType(builder, "appledogged", "%1$s got Appledog'd", "%1$s got Appledog'd while fighting %2$s");
    }

    private void doBiomes(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addRaw(builder, "biome.frontiers.crags_plains", "Crags Plains");
    }

    private void doTags(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addRaw(builder, "tag.item.frontiers.offhand_priority_item", "Offhand Priority Items");
        addRaw(builder, "tag.item.frontiers.stone_fence_gates", "Stone Fence Gates");
        addRaw(builder, "tag.item.frontiers.entity_models", "Entity Models");
        addRaw(builder, "tag.item.frontiers.deflects_balls", "Deflects Balls");
        addRaw(builder, "tag.item.frontiers.evertree_boostable", "Evertree Boostable Crops");
        addRaw(builder, "tag.item.frontiers.balls", "Balls");
        addRaw(builder, "tag.item.frontiers.lumens", "Lumens");
        addRaw(builder, "tag.item.frontiers.golden_chicken_food", "Golden Chicken Food");
        addRaw(builder, "tag.item.frontiers.glowing_brimtan_items", "Glowing Brimtan Items");
        addRaw(builder, "tag.item.frontiers.item_vacuum_hearts", "Creates Heart Particles in Item Vacuum");
        addRaw(builder, "tag.item.frontiers.item_vacuum_soul_fire", "Creates Soul Fire Particles in Item Vacuum");
        addRaw(builder, "tag.item.frontiers.eboncork_logs", "Eboncork Logs");
        addRaw(builder, "tag.item.frontiers.blighted_birch_logs", "Blighted Birch Logs");
        addRaw(builder, "tag.item.frontiers.fruitcake_ingredients", "Fruitcake Ingredients");
    }

    private void doMisc(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addStat(builder, Frontiers.MOD_ID, "open_personalchest", "Personal Chests Opened");
        addStat(builder, Frontiers.MOD_ID, "remove_curse", "Cursed Items Cleansed");
        addStat(builder, Frontiers.MOD_ID, "interact_with_fletching_table", "Interactions with Fletching Table");
        addStat(builder, Frontiers.MOD_ID, "interact_with_monster_bakery", "Interactions with Monster Bakery");
        addStat(builder, Frontiers.MOD_ID, "hit_ball", "Balls Hit");

        addRaw(builder, "tooltip.ranged.damage", "%s× Damage Multiplier");
        addRaw(builder, "tooltip.ranged.speed", "%s× Charge Speed");

        addRaw(builder, "deathScreen.frontiers.consumedTotem", "Totem of Avarice consumed and inventory kept.");

        addRaw(builder, "advancements.toast.frontier_adv", "Frontier Reached!");
        addRaw(builder, "chat.type.advancement.frontier_adv", "%s has reached the frontier %s");

        addRaw(builder, "block.frontiers.strange_core.incorrect", "Not the correct pattern!");
        addRaw(builder, "block.frontiers.strange_core.incorrect_funny", "...maybe try the Nether equivalents of these blocks?");
        addRaw(builder, "block.frontiers.strange_core.not_overworld", "This cannot be activated in this dimension.");
        addRaw(builder, "block.frontiers.strange_core.active", "Active!");
    }

    private void doCompatLang(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String desc = "desc";

        // FD
        addItemWithDesc(builder, FDItem.MOURNING_GOLD_KNIFE.get(), "Mourning Gold Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.COBALT_KNIFE.get(), "Cobalt Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.OBSIDIAN_KNIFE.get(), "Obsidian-Tipped Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.OBSIDIAN_KNIFE_BROKEN.get(), "Broken Obsidian-Tipped Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.VERDINITE_KNIFE.get(), "Verdinite Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.FROSTITE_KNIFE.get(), "Frostite Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.VIVULITE_KNIFE.get(), "Vivulite Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.BRIMTAN_KNIFE.get(), "Brimtan Knife",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.BRIMTAN_SHELL_KNIFE.get(), "Brimtan Shell",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.TRUFFLE_PASTA.get(), "Truffle Pasta",
                "",
                ""
        );
        addItemWithDesc(builder, FDItem.FRIED_GOLDEN_EGG.get(), "Fried Golden Egg",
                "",
                ""
        );
        addItemExtra(builder, FDItem.BRIMTAN_SHELL_KNIFE.get(), desc, "Brimtan Knife");

        // BF
        addBlockWithDesc(builder, BFBlock.FELDSPAR_LUMEN.get(),"Feldspar Lumen",
                "",
                ""
        );
        addItemWithDesc(builder, BFItem.GUARDIAN_SOUP.get(),"Guardian Soup",
                "",
                ""
        );
        addItemWithDesc(builder, BFItem.ELDEN_BOWL.get(),"Elden Bowl",
                "",
                ""
        );
        addItemWithDesc(builder, BFItem.BREADED_GUARDIAN.get(),"Breaded Guardian",
                "",
                ""
        );
        addItemWithDesc(builder, BFItem.MELON_SPRITZER_BOTTLE.get(),"Melon Spritzer Bottle",
                "",
                ""
        );
        addItemWithDesc(builder, BFItem.GLISTERING_SPRITZER_BOTTLE.get(),"Glistering Spritzer Bottle",
                "",
                ""
        );
    }
}
