package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.vertisoft.vectorlib.exclusive.datagen.VectorLangGen;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FRLangProviderEnglish extends VectorLangGen
{
    private static final String BRIMTAN_SHELL = "Brimtan Shell";

    private static final String L4J_FOOD = "§f\uD83C\uDF56§r";
    private static final String L4J_ARMOR = "§f\uD83D\uDC58§r";

    private static final String L4J_WREATH_PRE = "A wreath made with ";
    private static final String L4J_WREATH_POST = " leaves. Can be used as decoration, and won't have collision when placed on doors.";
    private static final String YAP_WREATH_PRE = "A pretty little wreath made with ";
    private static final String YAP_WREATH_POST = " leaves";

    private static final String YAP_BRIMTAN_SHELL = "A shell made of Brimtan, can be clad onto the respective Vivulite tool with the right template";
    private static final String YAP_LUMEN = "A source of light that gets brighter with more redstone power";
    private static final String YAP_LUMEN_DIM = ", dimmer than most other lumens";
    private static final String YAP_BROKE_OBSID = "A broken obsidian tool, can be repaired with obsidian at an anvil";

    private static final String mournDesc = "A lustrous";
    private static final String cobaltDesc = "A deep blue";
    private static final String frostiteDesc = "A frosty";
    private static final String plateDesc = "A clunky";
    private static final String verdiniteDesc = "An iridescent";
    private static final String vivuliteDesc = "A beautiful scarlet";
    private static final String brimtanDesc = "A glowing";

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
        doExtraTips(provider, builder);

        doRecipeViewer(provider, builder);
        doCompatLang(provider, builder);
    }

    private void doBlock(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String fenceTip = "Too high to jump over";
        String gateTip = "Can be opened, connects with fences and walls";
        String woodButtonTip = "Can be pushed by players, arrows, and tridents, stays pushed for 1.5 seconds";
        String woodPlateTip = "Produces a redstone signal when ANY entity makes contact with it";
        String doorTip = "Make yourself feel at home";
        String trapdoorTip = "Commonly used for everything BUT traps";

        addBlockWithDesc(builder, ModBlocks.COBALT_ORE.get(), "Cobalt Ore",
                "Can be mined with a Netherite pickaxe or better once the Wither has been defeated, otherwise it disintegrates. Can be smelted in a furnace to produce cobalt ingots.",
                "Forms in jungles under rare circumstances, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, ModBlocks.COBALT_BLOCK.get(), "Block of Cobalt",
                "A compact way of storing Cobalt.",
                "A deep blue block constructed from a collection of cobalt"
        );
        addBlockWithDesc(builder, ModBlocks.DEEPSLATE_COBALT_ORE.get(), "Deepslate Cobalt Ore",
                "Can be mined with a Netherite pickaxe or better once the Wither has been defeated, otherwise it disintegrates. Can be smelted in a furnace to produce cobalt ingots.",
                "Forms in jungles in all parts of the deepslate layer, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, ModBlocks.FROSTITE_ORE.get(), "Frostite Ore",
                "Can be mined with a cobalt pickaxe or better - but only when enchanted with Silk Touch. Must be melted by a light source to collect the raw frostite within.",
                "Forms in ice spikes, must be melted by light sources to drop ore"
        );
        addBlockWithDesc(builder, ModBlocks.FROSTITE_BLOCK.get(), "Block of Frostite",
                "A compact way of storing Frostite.",
                "A frosty block constructed from a collection of frostite"
        );
        addBlockWithDesc(builder, ModBlocks.GLOWING_OBSIDIAN.get(), "Glowing Obsidian",
                "A special glowing variant of Obsidian obtained when a Strange Core converts Obsidian. Creates a Crags portal in the Nether, or can be used as decoration.",
                "Obsidian mutated by a powerful core, used to make a portal to a terrifying place"
        );
        addBlockWithDesc(builder, ModBlocks.STRANGE_CORE.get(), "Strange Core",
                "A core with the ability to terraform its surroundings. Part of a structure - 5 blackstone in a plus shape, 4 Mourning Gold blocks in the corners of the plus, 4 blackstone above those, the core in the center, then another blackstone plus on top.",
                "Surround with blackstone and mourning gold blocks in a familiar pattern to activate"
        );
        addBlockWithDesc(builder, ModBlocks.ENCHANTING_MAGNET.get(), "Enchanting Magnet",
                "Picks up nearby Experience Orbs and stores a fraction of their value. Stored experience can be collected with Glass Bottles.",
                "Attracts and stores experience orbs, which can then be withdrawn with glass bottles",
                "Drags any nearby Experience Orbs to it, and picks them up on contact. The experience can then be harvested with Glass Bottles."
                        +"\n\nDrops 50% of its stored experience when mined, unless mined with Silk Touch; doing so will store the experience in the item."
        );
        addBlockWithDesc(builder, ModBlocks.ITEM_VACUUM.get(), "Item Vacuum",
                "Picks up nearby items within a small radius. Placing an item in an Item Frame above it will make it only pick up items of that kind.",
                "Sucks up nearby items; put an item in an item frame on top of one to make an item sorter!"
        );
        addBlockWithDesc(builder, ModBlocks.ANCIENT_ROSE.get(), "Ancient Rose",
                "An ultra-rare blue flower, grown when an Ancient Rose Seed is planted. Can be used as decoration or to craft a bush.",
                "Looking at this flower, a feeling of nostalgia washes over you..."
        );
        addBlockWithDesc(builder, ModBlocks.ANCIENT_ROSE_BUSH.get(), "Ancient Rose Bush",
                "A tall blue flower crafted from 4 Ancient Roses and a Rose Bush.",
                "A rose bush modified with ancient roses"
        );
        addBlockWithDesc(builder, ModBlocks.ROSE.get(), "Rose",
                "A rare red flower obtained by shearing Rose Bushes. Can be used to craft red dye.",
                "Not to be confused with the one from Delicate Dyes"
        );
        addBlockWithDesc(builder, ModBlocks.VIOLET_ROSE.get(), "Violet Rose",
                "A violet flower, grown when bonemealing a Rose or Ancient Rose nearby one another. Can be used as decoration or to craft a bush.",
                "\"I alone am the honored one.\""
        );
        addBlockWithDesc(builder, ModBlocks.VIOLET_ROSE_BUSH.get(), "Violet Rose Bush",
                "A tall purple flower crafted from 4 Violet Roses and a Rose Bush.",
                "A rose bush modified with violet roses"
        );
        addBlockWithDesc(builder, ModBlocks.MOURNING_GOLD_BLOCK.get(), "Block of Mourning Gold",
                "A compact way of storing Mourning Gold.",
                "A lustrous block constructed from a collection of mourning gold"
        );
        addBlockWithDesc(builder, ModBlocks.BLACK_EMERALD_BLOCK.get(), "Block of Black Emerald",
                "A compact way of storing Black Emeralds.",
                "The most powerful block constructed from a collection of black emeralds"
        );
        addBlockWithDesc(builder, ModBlocks.BLACK_EMERALD_ORE.get(), "Black Emerald Ore",
                "Can be mined with an Iron pickaxe or better to collect Black Emeralds.",
                "Forms at the top of the stone layer, and commonly in mountains"
        );
        addBlockWithDesc(builder, ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get(), "Deepslate Black Emerald Ore",
                "Can be mined with an Iron pickaxe or better to collect Black Emeralds.",
                "Rarely forms in the top layer of the deepslate layer and in mountains"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_BRICKS.get(), "Tower Bricks",
                "An extremely strong brick found in the mysterious white tower.",
                "Bricks from a living tower...and for some reason, they feel warm."
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_BRICK_STAIRS.get(), "Tower Brick Stairs",
                null,
                "Sleek stairs constructed from tower bricks"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_BRICK_SLAB.get(), "Tower Brick Slab",
                null,
                "Sleek slabs constructed from tower bricks"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_BRICK_WALL.get(), "Tower Brick Wall",
                "A wall made of Tower Bricks.",
                "A sleek wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, ModBlocks.MOSSY_TOWER_BRICKS.get(), "Mossy Tower Bricks",
                "An extremely strong moss-covered brick found in the mysterious white tower.",
                "Mossy bricks from a living tower"
        );
        addBlockWithDesc(builder, ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), "Mossy Tower Brick Stairs",
                null,
                "Sleek stairs constructed from tower bricks"
        );
        addBlockWithDesc(builder, ModBlocks.MOSSY_TOWER_BRICK_SLAB.get(), "Mossy Tower Brick Slab",
                null,
                "Sleek slabs constructed from tower bricks"
        );
        addBlockWithDesc(builder, ModBlocks.MOSSY_TOWER_BRICK_WALL.get(), "Mossy Tower Brick Wall",
                "A wall made of Mossy Tower Bricks.",
                "A sleek wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, ModBlocks.NACRE_BRICKS.get(), "Nacre Bricks",
                "Baked from Shulker residue in a furnace.",
                "Shulker residue that has been smelted into bricks, still feels warm"
        );
        addBlockWithDesc(builder, ModBlocks.NACRE_BRICK_STAIRS.get(), "Nacre Brick Stairs",
                null,
                "Slick stairs constructed from bricks"
        );
        addBlockWithDesc(builder, ModBlocks.NACRE_BRICK_SLAB.get(), "Nacre Brick Slab",
                null,
                "Slick slabs constructed from bricks"
        );
        addBlockWithDesc(builder, ModBlocks.NACRE_BRICK_WALL.get(), "Nacre Brick Wall",
                "A wall made of Nacre Bricks.",
                "A slick wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, ModBlocks.SNOW_DAHLIA.get(), "Snow Dahlia",
                "A rare icy flower that can be used to craft light blue dye.",
                "An icy flower that spawns on frozen riverbanks"
        );
        addBlockWithDesc(builder, ModBlocks.FUNGAL_DAFFODIL.get(), "Fungal Daffodil",
                "An uncommon purple fungus that can be used to craft purple dye. Only grows on Mycelium.",
                "Don't even THINK about eating this."
        );
        addBlockWithDesc(builder, ModBlocks.FUNGAL_DAFFODIL_BLOCK.get(), "Fungal Daffodil Block",
                "Can be used as decoration. Causes players and mobs to bounce when they jump on it.",
                "A weird block from a large fungal daffodil's head"
        );
        addBlockWithDesc(builder, ModBlocks.CRIMCONE.get(), "Crimcone",
                "A rare red flower from the Nether that can be used to craft red dye. Only grows on Crimson Nylium.",
                "Look too quickly and you'd mistake this for a fungus!"
        );
        addBlockWithDesc(builder, ModBlocks.EXPERIWINKLE.get(), "Experiwinkle",
                "An extremely rare glowing flower found in Flower Fields and Meadows. Can be grown to harvest some experience.",
                "Not interested in experience farming? That's ok, too!"
        );
        addBlockWithDesc(builder, ModBlocks.CRAGULSTANE.get(), "Cragulstane",
                "A strong, warped stone that generates in the Crags. Can be used for construction.",
                "It feels strange, like flesh and stone put together"
        );
        String chiselCrag = "Cragulstane that has been finely chiseled";
        String crackedCrag = "Cragulstane bricks that took a beating";
        String cragStairs = "Fleshy stairs constructed from cragulstane bricks";
        String cragSlab = "Fleshy slabs constructed from cragulstane bricks";
        String cragWall = "A fleshy wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, ModBlocks.CRAGULSTANE_BRICKS.get(), "Cragulstane Bricks",
                "Crafted from Cragulstane. Can be used for construction.",
                "Cragulstane compacted into bricks"
        );
        addBlockWithDesc(builder, ModBlocks.CRAGULSTANE_BRICK_STAIRS.get(), "Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, ModBlocks.CRAGULSTANE_BRICK_SLAB.get(), "Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, ModBlocks.CRAGULSTANE_BRICK_WALL.get(), "Cragulstane Brick Wall",
                "A wall made of Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), "Cracked Cragulstane Bricks",
                "Created by smelting Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get(), "Chiseled Cragulstane Bricks",
                "Crafted from Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), "Orange Cragulstane Bricks",
                "Crafted from Cragulstane and a Lava Bucket. Can be used for construction.",
                "Cragulstane compacted into bricks (but orange)"
        );
        addBlockWithDesc(builder, ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), "Orange Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), "Orange Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), "Orange Cragulstane Brick Wall",
                "A wall made of Orange Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), "Cracked Orange Cragulstane Bricks",
                "Created by smelting Orange Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(), "Chiseled Orange Cragulstane Bricks",
                "Crafted from Orange Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), "Tyrian Cragulstane Bricks",
                "Crafted from Cragulstane Bricks and both types of Nether Wart. Can be used for construction.",
                "Cragulstane compacted into bricks (but purple)"
        );
        addBlockWithDesc(builder, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), "Tyrian Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), "Tyrian Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), "Tyrian Cragulstane Brick Wall",
                "A wall made of Tyrian Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get(), "Cracked Tyrian Cragulstane Bricks",
                "Created by smelting Tyrian Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(), "Chiseled Tyrian Cragulstane Bricks",
                "Crafted from Tyrian Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), "Brimmed Cragulstane Bricks",
                "Crafted from Cragulstane and Brimtan Ingots. Can be used for construction.",
                "Cragulstane compacted into bricks (but cooler)"
        );
        addBlockWithDesc(builder, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), "Brimmed Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), "Brimmed Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), "Brimmed Cragulstane Brick Wall",
                "A wall made of Brimmed Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), "Cracked Brimmed Cragulstane Bricks",
                "Created by smelting Brimmed Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(), "Chiseled Brimmed Cragulstane Bricks",
                "Crafted from Brimmed Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, ModBlocks.AESTHENOSTONE.get(), "Aesthenostone",
                "The literal core of the world. Cannot be broken, and inflicts extreme damage when stood on.",
                "\"When you've reached the lowest of lows, the only way to go is up...\""
        );
        addBlockWithDesc(builder, ModBlocks.ONYX_BONE_BLOCK.get(), "Onyx Bone Block",
                "Crafted from Onyx Bone Meal, or found in the Nether in fossils. Can be used as decoration.",
                "Looks like someone didn't drink enough milk"
        );
        addBlockWithDesc(builder, ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), "Small Corrupted Amethyst Bud",
                "Will grow into a Medium Corrupted Amethyst Bud. Can also be used as decoration.",
                "A small cluster of corrupted amethyst"
        );
        addBlockWithDesc(builder, ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), "Medium Corrupted Amethyst Bud",
                "Will grow into a Large Corrupted Amethyst Bud. Can also be used as decoration.",
                "A medium cluster of corrupted amethyst"
        );
        addBlockWithDesc(builder, ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), "Large Corrupted Amethyst Bud",
                "Will grow into a Corrupted Amethyst Cluster that can be mined for End Crystal Shards. Can also be used as decoration.",
                "A large cluster of corrupted amethyst"
        );
        addBlockWithDesc(builder, ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get(), "Corrupted Amethyst Cluster",
                "Can be mined for End Crystal Shards or used as decoration.",
                "A cluster of corrupted amethyst, can be harvested for some end crystal shards"
        );
        addBlockWithDesc(builder, ModBlocks.QUICKSAND.get(), "Quicksand",
                "Slows your movement on contact. Being submerged in it will suffocate you.",
                "Slowly traps and suffocates entities, sneaking will prevent sinking"
        );
        addBlockWithDesc(builder, ModBlocks.RED_QUICKSAND.get(), "Red Quicksand",
                "Slows your movement on contact. Being submerged in it will suffocate you.",
                "This just exists for some reason"
        );
        addBlockWithDesc(builder, ModBlocks.SUGAR_CANE_BLOCK.get(), "Sugar Cane Block",
                "A compact way of storing Sugar Cane.",
                "Sugar cane compressed into a block"
        );
        addBlockWithDesc(builder, ModBlocks.COCOA_BEAN_BLOCK.get(), "Cocoa Bean Block",
                "A compact way of storing Cocoa Beans.",
                "Cocoa beans compressed into a block"
        );
        addBlockWithDesc(builder, ModBlocks.ROTTEN_FLESH_BLOCK.get(), "Rotten Flesh Block",
                "A compact way of storing Rotten Flesh.",
                "Top 10 building materials to get the police called on you with"
        );
        String netherCracked = " nether bricks that took a beating";
        String netherChis = " nether bricks that has been finely chiseled";
        String netherStairs = "Rough stairs constructed from ";
        String netherSlab = "Rough slabs constructed from ";
        String netherWall = "A rough wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, ModBlocks.BLUE_NETHER_BRICKS.get(), "Blue Nether Bricks",
                "Crafted from Warped Wart and Nether Brick. Can be used as decoration.",
                "Nether bricks stained with warped wart to appear blue"
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), "Chiseled Blue Nether Bricks",
                "Crafted with Blue Nether Brick Slabs. Can be used for construction.",
                "Blue" + netherChis
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), "Cracked Blue Nether Bricks",
                "Created by smelting Blue Nether Bricks in a furnace. Can be used for construction.",
                "Blue" + netherCracked
        );
        addBlockWithDesc(builder, ModBlocks.BLUE_NETHER_BRICK_STAIRS.get(), "Blue Nether Brick Stairs",
                null,
                netherStairs + "blue nether bricks"
        );
        addBlockWithDesc(builder, ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), "Blue Nether Brick Slab",
                null,
                netherSlab + "blue nether bricks"
        );
        addBlockWithDesc(builder, ModBlocks.BLUE_NETHER_BRICK_WALL.get(), "Blue Nether Brick Wall",
                "A wall made of Blue Nether Bricks.",
                netherWall
        );
        addBlockWithDesc(builder, ModBlocks.BLUE_NETHER_BRICK_FENCE.get(), "Blue Nether Brick Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), "Blue Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, ModBlocks.PURPLE_NETHER_BRICKS.get(), "Purple Nether Bricks",
                "Crafted from both kinds of Wart and Nether Brick. Can be used as decoration.",
                "Nether bricks stained with warts to appear purple"
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_PURPLE_NETHER_BRICKS.get(), "Chiseled Purple Nether Bricks",
                "Crafted with Purple Nether Brick Slabs. Can be used for construction.",
                "Purple" + netherChis
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), "Cracked Purple Nether Bricks",
                "Created by smelting Purple Nether Bricks in a furnace. Can be used for construction.",
                "Purple" + netherCracked
        );
        addBlockWithDesc(builder, ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), "Purple Nether Brick Stairs",
                null,
                netherStairs + "purple nether bricks"
        );
        addBlockWithDesc(builder, ModBlocks.PURPLE_NETHER_BRICK_SLAB.get(), "Purple Nether Brick Slab",
                null,
                netherSlab + "purple nether bricks"
        );
        addBlockWithDesc(builder, ModBlocks.PURPLE_NETHER_BRICK_WALL.get(), "Purple Nether Brick Wall",
                "A wall made of Purple Nether Bricks.",
                netherWall
        );
        addBlockWithDesc(builder, ModBlocks.PURPLE_NETHER_BRICK_FENCE.get(), "Purple Nether Brick Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get(), "Purple Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, ModBlocks.CHISELED_RED_NETHER_BRICKS.get(), "Chiseled Red Nether Bricks",
                "Crafted with Red Nether Brick Slabs. Can be used for construction.",
                "Red" + netherChis
        );
        addBlockWithDesc(builder, ModBlocks.CRACKED_RED_NETHER_BRICKS.get(), "Cracked Red Nether Bricks",
                "Created by smelting Red Nether Bricks in a furnace. Can be used for construction.",
                "Red" + netherCracked
        );
        addBlockWithDesc(builder, ModBlocks.RED_NETHER_BRICK_FENCE.get(), "Red Nether Brick Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), "Red Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, ModBlocks.NETHER_BRICK_FENCE_GATE.get(), "Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, ModBlocks.AMETHYST_LUMEN.get(), "Amethyst Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.COBALT_LUMEN.get(), "Cobalt Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.DIAMOND_LUMEN.get(), "Diamond Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.EMERALD_LUMEN.get(), "Emerald Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.FROSTITE_LUMEN.get(), "Frostite Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.QUARTZ_LUMEN.get(), "Quartz Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.REDSTONE_LUMEN.get(), "Redstone Lumen",
                "Emits different levels of light based on the power provided. Emits much weaker light than most lumens.",
                YAP_LUMEN + YAP_LUMEN_DIM
        );
        addBlockWithDesc(builder, ModBlocks.VERDINITE_LUMEN.get(), "Verdinite Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.VIVULITE_LUMEN.get(), "Vivulite Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.BRIMTAN_LUMEN.get(), "Brimtan Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, ModBlocks.ECHO_LUMEN.get(), "Echo Lumen",
                "Emits different levels of light based on the power provided. Emits much weaker light than most lumens.",
                YAP_LUMEN + YAP_LUMEN_DIM
        );
        addBlockWithDesc(builder, ModBlocks.VERDINITE_ORE.get(), "Verdinite Ore",
                "Can be mined with a cobalt pickaxe or better, then smelted in a furnace to produce verdinite ingots.",
                "Forms under rare circumstances, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, ModBlocks.VERDINITE_BLOCK.get(), "Block of Verdinite",
                "A compact way of storing Verdinite.",
                "A bright green block constructed from a collection of verdinite"
        );
        addBlockWithDesc(builder, ModBlocks.DEEPSLATE_VERDINITE_ORE.get(), "Deepslate Verdinite Ore",
                "Can be mined with a cobalt pickaxe or better, then smelted in a furnace to produce verdinite ingots.",
                "Found near the bottom of the deepslate layer, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, ModBlocks.VIVULITE_ORE.get(), "Vivulite Ore",
                "Can be mined with a verdinite pickaxe or better, then smelted in a furnace to produce vivulite ingots.",
                "Forms under rare circumstances, never spawns exposed to air"
        );
        addBlockWithDesc(builder, ModBlocks.VIVULITE_BLOCK.get(), "Block of Vivulite",
                "A compact way of storing Vivulite.",
                "A beautiful scarlet block constructed from a collection of vivulite"
        );
        addBlockWithDesc(builder, ModBlocks.DEEPSLATE_VIVULITE_ORE.get(), "Deepslate Vivulite Ore",
                "Can be mined with a verdinite pickaxe or better, then smelted in a furnace to produce vivulite ingots.",
                "Forms near bedrock in the Overworld, never spawns exposed to air"
        );
        String paleStairs = "Regal stairs constructed from ";
        String paleSlab = "Regal slabs constructed from ";
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE_BRICKS.get(), "Pale Prismarine Bricks",
                "A rare variant of Prismarine Bricks crafted using Prismarine Bricks and a Pale Prismarine Shard.",
                "Pale prismarine compacted into bricks"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), "Pale Prismarine Brick Slab",
                null,
                paleSlab + "pale prismarine bricks"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), "Pale Prismarine Brick Stairs",
                null,
                paleStairs + "pale prismarine bricks"
        );
        addBlockWithDesc(builder, ModBlocks.DEEP_PALE_PRISMARINE.get(), "Deep Pale Prismarine",
                "A rare variant of Dark Prismarine crafted using Dark Prismarine and a Pale Prismarine Shard.",
                "Dark prismarine with an elderly touch"
        );
        addBlockWithDesc(builder, ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), "Deep Pale Prismarine Slab",
                null,
                paleSlab + "deep pale prismarine"
        );
        addBlockWithDesc(builder, ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), "Deep Pale Prismarine Stairs",
                null,
                paleStairs + "deep pale prismarine"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE.get(), "Pale Prismarine",
                "A rare variant of Prismarine crafted using Prismarine and a Pale Prismarine Shard.",
                "Prismarine infused with material from elder guardians"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE_SLAB.get(), "Pale Prismarine Slab",
                null,
                paleSlab + "pale prismarine"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE_STAIRS.get(), "Pale Prismarine Stairs",
                null,
                paleStairs + "pale prismarine"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_PRISMARINE_WALL.get(), "Pale Prismarine Wall",
                "A wall made of Pale Prismarine.",
                "A regal wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, ModBlocks.TURTLE_SCUTE_BRICKS.get(), "Turtle Scute Bricks",
                "Crafted from Brick and Turtle Scutes. Can be used for decoration.",
                "No turtles were harmed in the making of this block"
        );
        addBlockWithDesc(builder, ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), "Turtle Scute Brick Slab",
                null,
                "Bumpy slabs constructed from turtle scute bricks"
        );
        addBlockWithDesc(builder, ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), "Turtle Scute Brick Stairs",
                null,
                "Bumpy stairs constructed from turtle scute bricks"
        );
        addBlockWithDesc(builder, ModBlocks.TURTLE_SCUTE_BRICK_WALL.get(), "Turtle Scute Brick Wall",
                "A wall made of Turtle Scute Bricks.",
                "A bumpy wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, ModBlocks.SEA_GLASS.get(), "Sea Glass",
                "A semi-transparent block that emits low light and lets light through. Can be crafted from a Glass Block and Prismarine Crystals.",
                "A glass block infused with prismarine crystals to make it glow"
        );
        addBlockWithDesc(builder, ModBlocks.SEA_GLASS_PANE.get(), "Sea Glass Pane",
                "Can be used as an alternative to Sea Glass.",
                "Sea glass that has been cut into panes"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_SEA_GLASS.get(), "Pale Sea Glass",
                "A rare variant of Sea Glass crafted using Sea Glass and a Pale Prismarine Shard.",
                "A glass block infused with prismarine crystals to make it glow"
        );
        addBlockWithDesc(builder, ModBlocks.PALE_SEA_GLASS_PANE.get(), "Pale Sea Glass Pane",
                "Can be used as an alternative to Pale Sea Glass.",
                "Pale sea glass that has been cut into panes"
        );
        addBlockWithDesc(builder, ModBlocks.CREEPER_MODEL.get(), "Creeper Model",
                "A taxidermized Creeper. Can be used as decoration.",
                "Behold the anatomy of the Creepus Explodus, without the risk of being blown to pieces!"
        );
        addBlockWithDesc(builder, ModBlocks.SKELETON_MODEL.get(), "Skeleton Model",
                "A taxidermized Skeleton. Can be used as decoration.",
                "A model of a skeleton, perfect for putting on display"
        );
        addBlockWithDesc(builder, ModBlocks.STRAY_MODEL.get(), "Stray Model",
                "A taxidermized Stray. Can be used as decoration.",
                "A model of a stray, can be sheared to examine the bones"
        );
        addBlockWithDesc(builder, ModBlocks.BOGGED_MODEL.get(), "Bogged Model",
                "A taxidermized Bogged. Can be used as decoration.",
                "A model of a bogged, perfect for putting on display"
        );
        addBlockWithDesc(builder, ModBlocks.BLAZE_MODEL.get(), "Blaze Model",
                "A taxidermized Blaze. Can be used as decoration.",
                "A model of a blaze, comes alive with redstone power"
        );
        addBlockWithDesc(builder, ModBlocks.WITHER_SKELETON_MODEL.get(), "Wither Skeleton Model",
                "A taxidermized Wither Skeleton. Can be used as decoration.",
                "It's not a skull, but you gotta admit this is FAR cooler"
        );
        addBlockWithDesc(builder, ModBlocks.ENDERMAN_MODEL.get(), "Enderman Model",
                "A taxidermized Enderman. Can be used as decoration.",
                "Getting this must've been a difficult task, huh?"
        );
        addBlockWithDesc(builder, ModBlocks.SLIME_MODEL.get(), "Slime Model",
                "A taxidermized Slime. Can be used as decoration.",
                "A model of a slime, perfect for putting on display"
        );
        addBlockWithDesc(builder, ModBlocks.MAGMA_CUBE_MODEL.get(), "Magma Cube Model",
                "A taxidermized Magma Cube. Can be used as decoration.",
                "A model of a magma cube, perfect for putting on display"
        );
        addBlockWithDesc(builder, ModBlocks.PHANTOM_MODEL.get(), "Phantom Model",
                "A taxidermized Phantom. Can be used as decoration.",
                "LITERALLY NO OTHER MONSTER DESERVED A FATE THIS GRUESOME, THIS IS WHAT PHANTOMS DESERVE"
        );
        String hieloStairs = "Frigid stairs constructed from ";
        String hieloSlab = "Frigid slabs constructed from ";
        String hieloWall = "A frigid wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE.get(), "Hielostone",
                "Found in cold biomes. Can be mined with a pickaxe to collect cobblefrost.",
                "A rock infused with permafrost, commonly found in colder biomes"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_STAIRS.get(), "Hielostone Stairs",
                null,
                hieloStairs + "hielostone"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_SLAB.get(), "Hielostone Slab",
                null,
                hieloSlab + "hielostone"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_WALL.get(), "Hielostone Wall",
                "A wall made of Hielostone.",
                hieloWall
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_BRICKS.get(), "Hielostone Bricks",
                "Crafted with Hielostone. Can be used for construction and as decoration.",
                "Hielostone compacted into bricks"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_BRICK_STAIRS.get(), "Hielostone Brick Stairs",
                null,
                hieloStairs + "hielostone bricks"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_BRICK_SLAB.get(), "Hielostone Brick Slab",
                null,
                hieloSlab + "hielostone bricks"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_BRICK_WALL.get(), "Hielostone Brick Wall",
                "A wall made of Hielostone Bricks.",
                hieloWall
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_TILES.get(), "Hielostone Tiles",
                "Crafted with Hielostone Bricks. Can be used for construction and as decoration.",
                "Hielostone compacted into tiles"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_TILE_STAIRS.get(), "Hielostone Tile Stairs",
                null,
                hieloStairs + "hielostone tiles"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_TILE_SLAB.get(), "Hielostone Tile Slab",
                null,
                hieloSlab + "hielostone tiles"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_TILE_WALL.get(), "Hielostone Tile Wall",
                "A wall made of Hielostone Tiles.",
                hieloWall
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_PLATES.get(), "Hielostone Plates",
                "Crafted with Hielostone Brick Slabs. Can be used for construction and as decoration.",
                "Hielostone compacted into plates"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_PLATE_STAIRS.get(), "Hielostone Plate Stairs",
                null,
                hieloStairs + "hielostone plates"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_PLATE_SLAB.get(), "Hielostone Plate Slab",
                null,
                hieloSlab + "hielostone plates"
        );
        addBlockWithDesc(builder, ModBlocks.HIELOSTONE_PLATE_WALL.get(), "Hielostone Plate Wall",
                "A wall made of Hielostone Plates.",
                hieloWall
        );
        addBlockWithDesc(builder, ModBlocks.COBBLEFROST.get(), "Cobblefrost",
                "Mined from hielostone using a pickaxe. Can be used to construct a furnace or stone tools.",
                "A rough block that would look nice in colder builds"
        );
        addBlockWithDesc(builder, ModBlocks.COBBLEFROST_STAIRS.get(), "Cobblefrost Stairs",
                null,
                hieloStairs + "cobblefrost"
        );
        addBlockWithDesc(builder, ModBlocks.COBBLEFROST_SLAB.get(), "Cobblefrost Slab",
                null,
                hieloSlab + "cobblefrost"
        );
        addBlockWithDesc(builder, ModBlocks.COBBLEFROST_WALL.get(), "Cobblefrost Wall",
                "A wall made of Cobblefrost.",
                hieloWall
        );
        addBlockWithDesc(builder, ModBlocks.BRIMTAN_ORE.get(), "Brimtan Ore",
                "Can be mined with a vivulite pickaxe or better to collect brimtan clusters.",
                "Found all over the crags in small batches"
        );
        addBlockWithDesc(builder, ModBlocks.BRIMTAN_BLOCK.get(), "Block of Brimtan",
                "A compact way of storing Brimtan.",
                "A molten block constructed from a collection of brimtan"
        );
        addBlockWithDesc(builder, ModBlocks.VIVULITE_ANVIL.get(), "Vivulite Anvil",
                "Can be used to repair weapons, tools and armor. Does not break no matter how many times it's used.",
                "Acts like an anvil, but will never break no matter how much you use it",
                "Can repair and enchant items just as a normal Anvil can, with one huge bonus: it will never break no matter how much it's used!"
        );
        addBlockWithDesc(builder, ModBlocks.BEEF_WELLINGTON.get(), "Beef Wellington",
                "Restores 3 " + L4J_FOOD + ". Can be used 7 times.",
                "I know a famous british chef who would KILL for this"
        );
        addBlockWithDesc(builder, ModBlocks.FRUITCAKE.get(), "Fruitcake",
                "Can be used 7 times, dropping a slice of fruitcake on each use",
                "A delicious holiday treat to share with (or throw at) friends"
        );
        addBlockWithDesc(builder, ModBlocks.CRAGS_PORTAL.get(), "Crags Portal",
                null,
                null
        );
        addBlockWithDesc(builder, ModBlocks.PERSONAL_CHEST.get(), "Personal Chest",
                "Stores blocks and items inside. Can only be accessed by the player who placed it.",
                "Can be used to store items, only the owner and allowed users can access the contents",
                "Can store items like a normal Chest, but can only be accessed by the person who placed it. Additional players can be given access using a Chest Key."
        );
        addBlockWithDesc(builder, ModBlocks.CURSE_ALTAR.get(), "Curse Altar",
                "Can remove both enchantments and curses from a desired item, so long as it's been charged with a Cursed Tablet.",
                "\"For the low cost of 30 levels, you can remove curses from your items!\"",
                "Can be used to remove both specific enchantments AND curses from a desired item. Must be charged with a Cursed Tablet first.\n\nEach use will wear down the tablet until it breaks, in which case you'll need to find another."
        );
        addBlockWithDesc(builder, ModBlocks.GLISTERING_MELON.get(), "Glistering Melon",
                "Can be crafted from Glistering Melon Slices.",
                "A large melon coated in gold"
        );
        addBlockWithDesc(builder, ModBlocks.CARVED_MELON.get(), "Carved Melon",
                "Can be worn as a helmet or crafted with a torch to create a June-O-Lantern.",
                "\"The people of this town love Halloween so much, they celebrate it twice a year. And wouldn't you know it, it's today!\""
        );
        addBlockWithDesc(builder, ModBlocks.CARVED_GLISTERING_MELON.get(), "Carved Glistering Melon",
                "Can be worn as a helmet or crafted with a torch to create a Glistering June-O-Lantern.",
                "Are you doing this just to flex at this point?"
        );
        addBlockWithDesc(builder, ModBlocks.JUNE_O_LANTERN.get(), "June o'Lantern",
                "Used to create brighter light than torches. Melts snow/ice and can be used underwater.",
                "If you don't collect 500 pieces of candy before this goes out, the Trickster will eat you!"
        );
        addBlockWithDesc(builder, ModBlocks.GLISTERING_JUNE_O_LANTERN.get(), "Glistering June o'Lantern",
                "Used to create brighter light than torches. Melts snow/ice and can be used underwater.",
                "Flaunt your wealth on those dastardly trick-or-treaters"
        );
        addBlockWithDesc(builder, ModBlocks.WHITE_PUMPKIN.get(), "White Pumpkin",
                "Can be worn like a Carved Pumpkin without obstructing your view.",
                "Prevents endermen from getting mad without blocking your view"
        );
        addBlockWithDesc(builder, ModBlocks.WHITE_JACK_O_LANTERN.get(), "White Jack o'Lantern",
                "Used to create brighter light than torches. Melts snow/ice and can be used underwater.",
                "\"Oh boy, we're really in story mode now!\""
        );
        addBlockWithDesc(builder, ModBlocks.SPIRIT_CANDLE.get(), "Spirit Candle",
                "Weakens any undead mobs in a small radius and deters endermen when lit.",
                "Weakens undead mobs and keeps endermen away",
                "When placed and lit, any undead mobs that come into the vicinity will be significantly weakened. Additionally, any Endermen that try teleporting nearby will be stopped."
        );
        addBlockWithDesc(builder, ModBlocks.RAW_COBALT_BLOCK.get(), "Block of Raw Cobalt",
                "A compact way of storing Raw Cobalt.",
                "Raw cobalt compressed into a block"
        );
        addBlockWithDesc(builder, ModBlocks.RAW_VERDINITE_BLOCK.get(), "Block of Raw Verdinite",
                "A compact way of storing Raw Verdinite.",
                "Raw verdinite compressed into a block"
        );
        addBlockWithDesc(builder, ModBlocks.RAW_VIVULITE_BLOCK.get(), "Block of Raw Vivulite",
                "A compact way of storing Raw Vivulite.",
                "Raw vivulite compressed into a block"
        );
        addBlockWithDesc(builder, ModBlocks.RAW_FROSTITE_BLOCK.get(), "Block of Raw Frostite",
                "A compact way of storing Raw Frostite.",
                "Raw frostite compressed into a block"
        );
        addBlockWithDesc(builder, ModBlocks.MONSTER_BAKERY.get(), "Monster Bakery",
                "Can be used to spawn mobs. Requires an item pertaining to a specific mob, plus fuel. The spawn chance increases with each item.",
                "Will spawn mobs with the right materials, fuel, and matching spawning conditions for said mob",
                "Can be used to spawn a small selection of mobs. Requires an item pertaining to a specific mob (i.e. Rotten Flesh or Blaze Rods), plus fuel. You can find all of the recipes and fuels in their respective viewer tabs."
                    + "\n\nWhen an item finishes \"baking\", it will attempt to spawn a mob nearby based on the spawn percentage. The conditions must match those of the mob's spawning conditions. "
                    + "If it fails, the chance increases until it succeeds - in which case the spawn chance resets."
        );
        addBlockWithDesc(builder, ModBlocks.PHANTOM_STITCH_BED.get(), "Phantom-Stitch Bed",
                "Fully heals the player upon waking up, and provides a small Absorption & Slow Falling bonus.",
                "The key to keeping those pesky phantoms away",
                "Sleeping in it will fully heal you, provide small buffs and provide you with Well-Rested, which repels Phantoms!"
        );
        addBlockWithDesc(builder, ModBlocks.PHANTASMIC_TNT.get(), "Phantasmic TNT",
                "Used to cause stronger explosions than TNT.",
                "TNT but worse"
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK.get(), "Eboncork",
                "A wood-like substance found in spike formations in The Crags. Can be crafted into planks.",
                "A spongy piece of wood-like substance from a spike in the Crags"
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_PLANKS.get(), "Eboncork Planks",
                "Used as a building material and can be crafted into many things. Can be crafted from Eboncork.",
                "Fine planks constructed from eboncork"
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_STAIRS.get(), "Eboncork Stairs",
                null,
                "Fine wooden stairs constructed from eboncork"
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_SLAB.get(), "Eboncork Slab",
                null,
                "Fine wooden slabs constructed from eboncork"
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_FENCE.get(), "Eboncork Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_FENCE_GATE.get(), "Eboncork Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_BUTTON.get(), "Eboncork Button",
                null,
                woodButtonTip
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_PRESSURE_PLATE.get(), "Eboncork Pressure Plate",
                "A sensitive Eboncork pressure plate that can be activated by applying almost any amount of pressure.",
                woodPlateTip
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_DOOR.get(), "Eboncork Door",
                null,
                doorTip
        );
        addBlockWithDesc(builder, ModBlocks.EBONCORK_TRAPDOOR.get(), "Eboncork Trapdoor",
                null,
                trapdoorTip
        );
        addBlockWithDesc(builder, ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), "Radiant Blighted Birch Log",
                null,
                "A sturdy log from a blighted birch tree during the day"
        );
        addBlockWithDesc(builder, ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), "Radiant Blighted Birch Wood",
                null,
                "A sturdy piece of wood from a radiant blighted birch log"
        );
        addBlockWithDesc(builder, ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), "Sullen Blighted Birch Log",
                null,
                "A sturdy log from a blighted birch tree during the night"
        );
        addBlockWithDesc(builder, ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), "Sullen Blighted Birch Wood",
                null,
                "A sturdy piece of wood from a sullen blighted birch log"
        );
        addBlockWithDesc(builder, ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), "Stripped Blighted Birch Log",
                "A Blighted Birch log that has had the bark removed with an axe.",
                "A blighted birch log that has been stripped- by accident?"
        );
        addBlockWithDesc(builder, ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), "Stripped Blighted Birch Wood",
                "Blighted Birch wood that has had the bark removed with an axe.",
                "A blighted birch wood that has been stripped- by accident?"
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_LEAVES.get(), "Blighted Birch Leaves",
                null,
                "Foliage from a blighted birch tree"
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_SAPLING.get(), "Blighted Birch Sapling",
                null,
                "A young plant that will grow into a blighted birch tree, mainly found in swamp huts",
                "Found in Witch Huts with a 1/3 chance of replacing the Red Mushroom pot...unless it's Halloween, when it will always replace it."
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_PLANKS.get(), "Blighted Birch Planks",
                null,
                "Fine planks constructed from blighted birch"
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_STAIRS.get(), "Blighted Birch Stairs",
                null,
                "Fine wooden stairs constructed from blighted birch"
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_SLAB.get(), "Blighted Birch Slab",
                null,
                "Fine wooden slabs constructed from blighted birch"
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_FENCE.get(), "Blighted Birch Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), "Blighted Birch Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_BUTTON.get(), "Blighted Birch Button",
                null,
                woodButtonTip
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), "Blighted Birch Pressure Plate",
                "A sensitive Blighted Birch pressure plate that can be activated by applying almost any amount of pressure.",
                woodPlateTip
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_DOOR.get(), "Blighted Birch Door",
                null,
                doorTip
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), "Blighted Birch Trapdoor",
                null,
                trapdoorTip
        );
        addBlockWithDesc(builder, ModBlocks.SLIME_TRAIL.get(), "Slime Trail",
                "Generates underground where Slimes can spawn. Can be used as decoration.",
                "Slimes might spawn nearby these"
        );
        addBlockWithDesc(builder, ModBlocks.SLIME_BULB.get(), "Slime Bulb",
                "A rare block that will slowly grow a single Hardened Slime. Does not drop itself when destroyed.",
                "A strange orifice that grows a single hardened slime crystal"
        );
        addBlockWithDesc(builder, ModBlocks.NECRO_WEAVE_BLOCK.get(), "Block of Necro Weave",
                "A compact way of storing Necro Weave. Also cushions falls far better than Hay Bale.",
                "A block constructed from a collection of necro weave, also good for breaking falls!"
        );
        addBlockWithDesc(builder, ModBlocks.NECRO_RUG.get(), "Necro Rug",
                "Crafted from Necro Weave. Sneaking while walking on it allows you to phase through it.",
                "Can be phased through by sneaking on it"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_WATCHER.get(), "Tower Watcher",
                "A part of the white tower, it inflicts any intruders with several debuffs.",
                "Like the immune system of the tower, protects it from intruders"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_SPAWNER.get(), "Tower Spawner",
                "A part of the white tower, it spawns monsters into the world.",
                "Spawns monsters to stop intruders"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_KEY_VAULT.get(), "Tower Key Vault",
                "Interacting will provide you with a single piece of a Tower Key. Will only drop one per person.",
                "Drops a single piece of a tower key when interacted with"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_TREASURE_VAULT.get(), "Tower Treasure Vault",
                "Unlocked with a Tower Key, after which it can be picked up. Will periodically ask for a specific item; giving it this item will cause it to drop rare loot in return.",
                "Will give you exclusive items in exchange for an item it desires"
        );
        addBlockWithDesc(builder, ModBlocks.TOWER_HEART.get(), "Tower Heart",
                "The core of the White Tower - destroying it will permanently disable the tower.",
                "The very core of the White Tower"
        );
        addBlockWithDesc(builder, ModBlocks.COBALT_GRILLES.get(), "Cobalt Grilles",
                "A blue alternative to Iron Bars.",
                "You thought copper bars were lazy?"
        );

        addBlockWithDesc(builder, ModBlocks.OAK_WREATH.get(), "Oak Wreath",
                L4J_WREATH_PRE + "Oak" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "oak" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.BIRCH_WREATH.get(), "Birch Wreath",
                L4J_WREATH_PRE + "Birch" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "birch" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.SPRUCE_WREATH.get(), "Spruce Wreath",
                L4J_WREATH_PRE + "Spruce" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "spruce" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.JUNGLE_WREATH.get(), "Jungle Wreath",
                L4J_WREATH_PRE + "Jungle" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "jungle" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.ACACIA_WREATH.get(), "Acacia Wreath",
                L4J_WREATH_PRE + "Acacia" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "acacia" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.DARK_OAK_WREATH.get(), "Dark Oak Wreath",
                L4J_WREATH_PRE + "Dark Oak" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "dark oak" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.MANGROVE_WREATH.get(), "Mangrove Wreath",
                L4J_WREATH_PRE + "Mangrove" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "mangrove" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.CHERRY_WREATH.get(), "Cherry Wreath",
                L4J_WREATH_PRE + "Cherry" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "cherry" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.AZALEA_WREATH.get(), "Azalea Wreath",
                L4J_WREATH_PRE + "Azalea" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "azalea" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, ModBlocks.BLIGHTED_BIRCH_WREATH.get(), "Blighted Birch Wreath",
                L4J_WREATH_PRE + "Blighted Birch" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "blighted birch" + YAP_WREATH_POST
        );
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
                "Can be placed onto a Curse Altar to enable the removal of desired enchantments and curses, for an experience price.",
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
                "Can be placed in corners surrounding your enchanting table to power it up",
                "A secret(?) item obtained by purifying an End Crystal on a Curse Altar. Placing 4 around the corners of your bookshelf-adorned Enchanting Table will allow it to grant treasure enchants (such as Mending)."
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
                "Did you know the name of this item is synonymous with \"vex\"? Cool, I know"
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
                "Can be inserted into a jukebox to play melancholic tunes"
        );
        addItemWithDesc(builder, ModItem.TOTEM_OF_AVARICE.get(), "Totem of Avarice",
                "Dying with this in your inventory will allow you to keep your items, but breaks it in the process.",
                "Keep your items on death...but not your levels!"
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
                "Can be used to craft a Pale Trident. (Most likely will be removed in a future version.)",
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
                "Will melt snow layers in a small radius. Any affected blocks won't be covered by snowfall again unless covered with a solid block.",
                "Melts nearby snow when placed on a block; affected blocks can't be snowed on again unless covered"
        );
        addItemWithDesc(builder, ModItem.MESSAGE_IN_A_BOTTLE.get(), "Message in a Bottle",
                "Can hold a small amount of text.",
                "Write some text on it, then throw it into an ocean or river"
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
        String yapHelm = " helmet to protect the user's head";
        String yapChest = " helmet to protect the user's body";
        String yapLegs = " helmet to protect the user's legs";
        String yapBoots = " helmet to protect the user's feet";

        // Necro Weave
        String necroDesd = "A spooky";
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_HELMET.get(), "Rotcross Helm",
                "Gives the user 1 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapHelm
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_CHESTPLATE.get(), "Rotcross Suit Top",
                "Gives the user 3 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapChest
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_LEGGINGS.get(), "Rotcross Suit Leggings",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapLegs
        );
        addItemWithDesc(builder, ModItem.NECRO_WEAVE_BOOTS.get(), "Rotcross Treads",
                "Gives the user 1 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapBoots
        );
        // Mourning Gold
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_HELMET.get(), "Mourning Gold Helmet",
                "Gives the user 1 " + L4J_ARMOR + " when worn.",
                mournDesc + yapHelm
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_CHESTPLATE.get(), "Mourning Gold Chestplate",
                "Gives the user 3 " + L4J_ARMOR + " when worn.",
                mournDesc + yapChest
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_LEGGINGS.get(), "Mourning Gold Leggings",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn.",
                mournDesc + yapLegs
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_BOOTS.get(), "Mourning Gold Boots",
                "Gives the user 1 " + L4J_ARMOR + " when worn.",
                mournDesc + yapBoots
        );
        // Cobalt
        addItemWithDesc(builder, ModItem.COBALT_HELMET.get(), "Cobalt Helmet",
                "Gives the user 2 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapHelm
        );
        addItemWithDesc(builder, ModItem.COBALT_CHESTPLATE.get(), "Cobalt Chestplate",
                "Gives the user 4.5 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapChest
        );
        addItemWithDesc(builder, ModItem.COBALT_LEGGINGS.get(), "Cobalt Leggings",
                "Gives the user 3.5 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapLegs
        );
        addItemWithDesc(builder, ModItem.COBALT_BOOTS.get(), "Cobalt Boots",
                "Gives the user 2 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapBoots
        );
        // Frostite
        addItemWithDesc(builder, ModItem.FROSTITE_HELMET.get(), "Frostite Helmet",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapHelm
        );
        addItemWithDesc(builder, ModItem.FROSTITE_CHESTPLATE.get(), "Frostite Chestplate",
                "Gives the user 5 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapChest
        );
        addItemWithDesc(builder, ModItem.FROSTITE_LEGGINGS.get(), "Frostite Leggings",
                "Gives the user 4 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapLegs
        );
        addItemWithDesc(builder, ModItem.FROSTITE_BOOTS.get(), "Frostite Boots",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapBoots
        );
        // Plate
        addItemWithDesc(builder, ModItem.PLATE_HELMET.get(), "Plate Helm",
                "Gives the user 1 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " helmet that provides some benefits"
        );
        addItemWithDesc(builder, ModItem.PLATE_CHESTPLATE.get(), "Plate Chestpiece",
                "Gives the user 2 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " chestplate that provides some benefits"
        );
        addItemWithDesc(builder, ModItem.PLATE_LEGGINGS.get(), "Plate Legpiece",
                "Gives the user 1.5 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " pair of boots that provides some benefits"
        );
        addItemWithDesc(builder, ModItem.PLATE_BOOTS.get(), "Plate Boots",
                "Gives the user 0.5 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " pair of boots that provides some benefits"
        );
        // Verdinite
        addItemWithDesc(builder, ModItem.VERDINITE_HELMET.get(), "Verdinite Helmet",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapHelm
        );
        addItemWithDesc(builder, ModItem.VERDINITE_CHESTPLATE.get(), "Verdinite Chestplate",
                "Gives the user 5 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapChest
        );
        addItemWithDesc(builder, ModItem.VERDINITE_LEGGINGS.get(), "Verdinite Leggings",
                "Gives the user 4 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapLegs
        );
        addItemWithDesc(builder, ModItem.VERDINITE_BOOTS.get(), "Verdinite Boots",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapBoots
        );
        // Vivulite
        addItemWithDesc(builder, ModItem.VIVULITE_HELMET.get(), "Vivulite Helmet",
                "Gives the user 3 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapHelm
        );
        addItemWithDesc(builder, ModItem.VIVULITE_CHESTPLATE.get(), "Vivulite Chestplate",
                "Gives the user 5.5 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapChest
        );
        addItemWithDesc(builder, ModItem.VIVULITE_LEGGINGS.get(), "Vivulite Leggings",
                "Gives the user 4.5 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapLegs
        );
        addItemWithDesc(builder, ModItem.VIVULITE_BOOTS.get(), "Vivulite Boots",
                "Gives the user 3 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapBoots
        );
        // Brimtan
        addItemWithDesc(builder, ModItem.BRIMTAN_HELMET.get(), "Brimtan Helmet",
                "Gives the user 3.5 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapHelm
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_CHESTPLATE.get(), "Brimtan Chestplate",
                "Gives the user 6 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapChest
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_LEGGINGS.get(), "Brimtan Leggings",
                "Gives the user 5 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapLegs
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_BOOTS.get(), "Brimtan Boots",
                "Gives the user 3.5 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapBoots
        );
        // Brimtan Shells
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_HELMET.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Helmet on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_CHESTPLATE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Chestplate on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_LEGGINGS.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Leggings on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_BOOTS.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Boots on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        // Miscs
        addItemWithDesc(builder, ModItem.WITCH_HAT.get(), "Witch Hat",
                "Protects the wearer from most types of magic, and allows near-instant potion consumption.",
                "Lets you drink potions super fast and protects you from magic attacks",
                "Wearing this defends you from most magic attacks, with the added bonus of near-instant potion drinking."
        );
        addItemWithDesc(builder, ModItem.SLIME_SHOES.get(), "Slime Shoes",
                "Gives the user 0.5 " + L4J_ARMOR + " when worn, and causes the wearer to bounce when hitting the ground.",
                "A pair of bouncy shoes that lets you fall from greater heights and bounce around"
        );
        addItemWithDesc(builder, ModItem.COBALT_HORSE_ARMOR.get(), "Cobalt Horse Armor",
                "A special type of Armor that can be equipped to a horse. Provides 7 " + L4J_ARMOR + ".",
                "A deep blue armor to protect a horse with"
        );
        addItemWithDesc(builder, ModItem.VERDINITE_HORSE_ARMOR.get(), "Verdinite Horse Armor",
                "A special type of Armor that can be equipped to a horse. Provides 8.5 " + L4J_ARMOR + ".",
                "A bright green armor to protect a horse with"
        );
        addItemWithDesc(builder, ModItem.VIVULITE_HORSE_ARMOR.get(), "Vivulite Horse Armor",
                "A special type of Armor that can be equipped to a horse. Provides 10 " + L4J_ARMOR + ".",
                "An iridescent scarlet armor to protect a horse with"
        );
    }

    private void doItemTool(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String arrowhead = "Arrowhead";
        String arrowhead_yt = "Can be used at a fletching table to make its respective specialty arrow";
        String arrow_prefix_yt = "A projectile that can be shot from some weapons";
        String ball_yt = "Here, kid, have a ball!";

        String sword = " blade, time to strike!";
        String shovel = " tool, time to dig!";
        String axe = " tool, time to chop!";
        String pickaxe = " tool, time to mine!";
        String hoe = " tool, time to farm!";

        String obsDesc = "A fragile";

        // Mourning Gold
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_PICKAXE.get(), "Mourning Gold Pickaxe",
                null,
                mournDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_AXE.get(), "Mourning Gold Axe",
                null,
                mournDesc + axe
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_SWORD.get(), "Mourning Gold Sword",
                null,
                mournDesc + sword
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_SHOVEL.get(), "Mourning Gold Shovel",
                null,
                mournDesc + shovel
        );
        addItemWithDesc(builder, ModItem.MOURNING_GOLD_HOE.get(), "Mourning Gold Hoe",
                null,
                mournDesc + hoe
        );
        // Obsidian
        addItemWithDesc(builder, ModItem.OBSIDIAN_PICKAXE.get(), "Obsidian-Tipped Pickaxe",
                null,
                obsDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_PICKAXE_BROKEN.get(), "Broken Obsidian-Tipped Pickaxe",
                "A broken Obsidian Pickaxe. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_AXE.get(), "Obsidian-Tipped Axe",
                null,
                obsDesc + axe
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_AXE_BROKEN.get(), "Broken Obsidian-Tipped Axe",
                "A broken Obsidian Axe. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SWORD.get(), "Obsidian-Tipped Sword",
                null,
                obsDesc + sword
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SWORD_BROKEN.get(), "Broken Obsidian-Tipped Sword",
                "A broken Obsidian Sword. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SHOVEL.get(), "Obsidian-Tipped Shovel",
                null,
                obsDesc + shovel
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_SHOVEL_BROKEN.get(), "Broken Obsidian-Tipped Shovel",
                "A broken Obsidian Shovel. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_HOE.get(), "Obsidian-Tipped Hoe",
                null,
                obsDesc + hoe
        );
        addItemWithDesc(builder, ModItem.OBSIDIAN_HOE_BROKEN.get(), "Broken Obsidian-Tipped Hoe",
                "A broken Obsidian Hoe. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        // Cobalt
        addItemWithDesc(builder, ModItem.COBALT_PICKAXE.get(), "Cobalt Pickaxe",
                null,
                cobaltDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.COBALT_AXE.get(), "Cobalt Axe",
                null,
                cobaltDesc + axe
        );
        addItemWithDesc(builder, ModItem.COBALT_SWORD.get(), "Cobalt Sword",
                null,
                cobaltDesc + sword
        );
        addItemWithDesc(builder, ModItem.COBALT_SHOVEL.get(), "Cobalt Shovel",
                null,
                cobaltDesc + shovel
        );
        addItemWithDesc(builder, ModItem.COBALT_HOE.get(), "Cobalt Hoe",
                null,
                cobaltDesc + hoe
        );
        // Frostite
        addItemWithDesc(builder, ModItem.FROSTITE_PICKAXE.get(), "Frostite Pickaxe",
                null,
                frostiteDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.FROSTITE_AXE.get(), "Frostite Axe",
                null,
                frostiteDesc + axe
        );
        addItemWithDesc(builder, ModItem.FROSTITE_SWORD.get(), "Frostite Sword",
                null,
                frostiteDesc + sword
        );
        addItemWithDesc(builder, ModItem.FROSTITE_SHOVEL.get(), "Frostite Shovel",
                null,
                frostiteDesc + shovel
        );
        addItemWithDesc(builder, ModItem.FROSTITE_HOE.get(), "Frostite Hoe",
                null,
                frostiteDesc + hoe
        );
        // Verdinite
        addItemWithDesc(builder, ModItem.VERDINITE_PICKAXE.get(), "Verdinite Pickaxe",
                null,
                verdiniteDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.VERDINITE_AXE.get(), "Verdinite Axe",
                null,
                verdiniteDesc + axe
        );
        addItemWithDesc(builder, ModItem.VERDINITE_SWORD.get(), "Verdinite Sword",
                null,
                verdiniteDesc + sword
        );
        addItemWithDesc(builder, ModItem.VERDINITE_SHOVEL.get(), "Verdinite Shovel",
                null,
                verdiniteDesc + shovel
        );
        addItemWithDesc(builder, ModItem.VERDINITE_HOE.get(), "Verdinite Hoe",
                null,
                verdiniteDesc + hoe
        );
        // Vivulite
        addItemWithDesc(builder, ModItem.VIVULITE_PICKAXE.get(), "Vivulite Pickaxe",
                null,
                vivuliteDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.VIVULITE_AXE.get(), "Vivulite Axe",
                null,
                vivuliteDesc + axe
        );
        addItemWithDesc(builder, ModItem.VIVULITE_SWORD.get(), "Vivulite Sword",
                null,
                vivuliteDesc + sword
        );
        addItemWithDesc(builder, ModItem.VIVULITE_SHOVEL.get(), "Vivulite Shovel",
                null,
                vivuliteDesc + shovel
        );
        addItemWithDesc(builder, ModItem.VIVULITE_HOE.get(), "Vivulite Hoe",
                null,
                vivuliteDesc + hoe
        );
        // Brimtan
        addItemWithDesc(builder, ModItem.BRIMTAN_PICKAXE.get(), "Brimtan Pickaxe",
                null,
                brimtanDesc + pickaxe
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_PICKAXE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Pickaxe on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_AXE.get(), "Brimtan Axe",
                null,
                brimtanDesc + axe
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_AXE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Axe on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SWORD.get(), "Brimtan Sword",
                null,
                brimtanDesc + sword
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_SWORD.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Sword on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHOVEL.get(), "Brimtan Shovel",
                null,
                brimtanDesc + shovel
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_SHOVEL.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Shovel on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_HOE.get(), "Brimtan Hoe",
                null,
                brimtanDesc + hoe
        );
        addItemWithDesc(builder, ModItem.BRIMTAN_SHELL_HOE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Hoe on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );

        // Bows
        addItemWithDesc(builder, ModItem.COPPER_BOW.get(), "Copper Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, ModItem.IRON_BOW.get(), "Iron Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, ModItem.DIAMOND_BOW.get(), "Diamond Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, ModItem.NETHERITE_BOW.get(), "Netherite Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, ModItem.ECHO_BOW.get(), "Echo Bow",
                "A faster version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance. Charges faster than other bows"
        );
        addItemWithDesc(builder, ModItem.VERDINITE_BOW.get(), "Verdinite Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        // Tomes
        addItemWithDesc(builder, ModItem.TOME_OF_FANGS.get(), "Tome of Fangs",
                "A book that summons a line of Evoker Fangs in the direction you're facing. Using it at your feet summons a circle of fangs instead.",
                "Summons a stream of evoker fangs wherever you use it"
        );
        // Arrowheads
        addItemWithDesc(builder, ModItem.WARP_ARROW.get(), "Warp Arrow",
                "Teleports the user to wherever it lands, at the cost of some health.",
                arrow_prefix_yt + " to teleport the shooter to where it lands"
        );
        addItemWithDesc(builder, ModItem.WARP_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Warp Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.SUBZERO_ARROW.get(), "Subzero Arrow",
                "Immediately freezes the victim as if they were standing in Powder Snow.",
                arrow_prefix_yt + " to freeze the target"
        );
        addItemWithDesc(builder, ModItem.SUBZERO_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Subzero Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.BOUNCY_ARROW.get(), "Bouncy Arrow",
                "Ricochets off surfaces and targets.",
                arrow_prefix_yt + " and bounces on impact"
        );
        addItemWithDesc(builder, ModItem.BOUNCY_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Bouncy Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.DYNAMITE_ARROW.get(), "Dynamite Arrow",
                "Makes a small explosion on impact.",
                arrow_prefix_yt + " and explodes on impact"
        );
        addItemWithDesc(builder, ModItem.DYNAMITE_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Dynamite Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.PRISMARINE_ARROW.get(), "Prismarine Arrow",
                "Travels much faster through water.",
                arrow_prefix_yt + "; travels quickly underwater"
        );
        addItemWithDesc(builder, ModItem.PRISMARINE_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Prismarine Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, ModItem.SPECTRAL_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Spectral Arrows.",
                arrowhead_yt
        );
        // Spawn Eggs
        addItemWithDesc(builder, ModItem.CRAWLER_SPAWN_EGG.get(), "Crawler Spawn Egg",
                null,
                "A deadlier version of a creeper with bigger explosions and more health"
        );
        addItemWithDesc(builder, ModItem.JUNGLE_SPIDER_SPAWN_EGG.get(), "Jungle Spider Spawn Egg",
                null,
                "A tiny monster that moves fast and inflicts weakness"
        );
        addItemWithDesc(builder, ModItem.PUMPKIN_GOLEM_SPAWN_EGG.get(), "Pumpkin Golem Spawn Egg",
                null,
                "A mischievous golem that picks and replants most crops it comes across"
        );
        addItemWithDesc(builder, ModItem.CROW_SPAWN_EGG.get(), "Crow Spawn Egg",
                null,
                "A noisy, intelligent bird that's often attracted to shiny things"
        );
        addItemWithDesc(builder, ModItem.GOLDEN_CHICKEN_SPAWN_EGG.get(), "Golden Chicken Spawn Egg",
                null,
                "A golden variant of chicken that rarely lays golden eggs"
        );
        // Balls
        addItemWithDesc(builder, ModItem.BALL.get(), "Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, ModItem.BOUNCY_BALL.get(), "Bouncy Ball",
                "A recreational item that can be thrown around. Bounces off of blocks up to 4 times.",
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
        addItemWithDesc(builder, ModItem.COLOR_BALLS.get(DyeColor.PINK).get(), "Pink Ball",
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
        addAdv(builder, Frontiers.MOD_ID, "enter_tower", "Breaking the Tower", "Enter the challenging White Tower");
        addAdv(builder, Frontiers.MOD_ID, "enrage_tower_spawner", "Explosive Temperament", "Enrage a Tower Spawner with an explosion");
        addAdv(builder, Frontiers.MOD_ID, "kill_tower", "Slay the Spire", "Defeat the White Tower by smashing its heart");
        addAdv(builder, Frontiers.MOD_ID, "smelt_frostite", "Stay Frosty", "Smelt a Frostite Ingot at the end of a long extraction process");
        addAdv(builder, Frontiers.MOD_ID, "eat_hpapple", "An Apple a Day", "Eat an Apple of Enlightenment");
        addAdv(builder, Frontiers.MOD_ID, "get_a_model", "Lifelike Replica", "Obtain a model");
        addAdv(builder, Frontiers.MOD_ID, "get_all_models", "Overworldian Psycho", "Obtain every model");
        addAdv(builder, Frontiers.MOD_ID, "full_vivulite_armor", "Cover Me in Scarlet", "Get a full suit of Vivulite armor");
        addAdv(builder, Frontiers.MOD_ID, "full_brimtan_armor", "Through the Fire and Flames", "Obtain a full suit of Brimtan armor");
        addAdv(builder, Frontiers.MOD_ID, "obtain_vivulite_anvil", "Stronger Than Steel", "Craft an indestructible Vivulite Anvil");
        addAdv(builder, Frontiers.MOD_ID, "break_curse", "Returning the Slab", "Remove an enchantment or curse on a Curse Altar");
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
        addRaw(builder, "sounds.frontiers.onyx_meal.use", "Onyx Meal withers");
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
        addRaw(builder, "subtitles.block.curse_altar.tablet", "Cursed Tablet activated");
        addRaw(builder, "subtitles.block.curse_altar.use", "Curse Altar used");
        addRaw(builder, "subtitles.block.beacon.brimtan", "Beacon drones");
        addRaw(builder, "subtitles.block.fletching_table.use", "Fletching Table used");
        addRaw(builder, "subtitles.block.tower_spawner.enrage", "Tower Spawner enrages");
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
        addRaw(builder, "tag.item.frontiers.wreaths", "Wreaths");
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

        // "block.frontiers.evertree.tip" : "Enhances production of a chosen crop within a large area.",
        // "block.frontiers.silverfish_model.tip" : "A taxidermized Silverfish. Can be used as decoration.",
        // "item.frontiers.everseed.tip": "Found in Jungle Temples. Grows into an Evertree.",
    }

    private void doExtraTips(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        // YAPPING

        // LEGACY4J
        addRaw(builder, "item.frontiers.ball.tip",
                "A recreational item that can be thrown around.");
        addRaw(builder, "block.frontiers.lumen.tip",
                "Emits different levels of light based on the power provided.");
        addRaw(builder, "item.minecraft.override_end_crystal.tip",
                "Heals the Ender Dragon, and can be used to respawn it. Can take three hits before exploding. Placing one near Budding Amethyst will alter what it produces.");
        addRaw(builder, "item.minecraft.override_prismarine_shard.tip",
                "Dropped by Guardians. Can be used in crafting Prismarine and Sea Lanterns, or repairing Tridents on an Anvil.");
    }

    private void doCompatLang(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String desc = "desc";

        // FD
        addItemWithDesc(builder, FDItem.MOURNING_GOLD_KNIFE.get(), "Mourning Gold Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.COBALT_KNIFE.get(), "Cobalt Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.OBSIDIAN_KNIFE.get(), "Obsidian-Tipped Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.OBSIDIAN_KNIFE_BROKEN.get(), "Broken Obsidian-Tipped Knife",
                "A broken Obsidian Knife. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, FDItem.VERDINITE_KNIFE.get(), "Verdinite Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.FROSTITE_KNIFE.get(), "Frostite Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.VIVULITE_KNIFE.get(), "Vivulite Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.BRIMTAN_KNIFE.get(), "Brimtan Knife",
                null,
                null
        );
        addItemWithDesc(builder, FDItem.BRIMTAN_SHELL_KNIFE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Knife on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FDItem.TRUFFLE_PASTA.get(), "Truffle Pasta",
                "Restores 8 " + L4J_FOOD + " and provides Nourishment for a good amount of time.",
                null
        );
        addItemWithDesc(builder, FDItem.FRIED_GOLDEN_EGG.get(), "Fried Golden Egg",
                "Restores 2.5 " + L4J_FOOD + " and provides Allurement for a short time.",
                null
        );
        addItemExtra(builder, FDItem.BRIMTAN_SHELL_KNIFE.get(), desc, "Brimtan Knife");

        // BF
        addBlockWithDesc(builder, BFBlock.FELDSPAR_LUMEN.get(),"Feldspar Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, BFBlock.HOARY_WREATH.get(),"Hoary Wreath",
                L4J_WREATH_PRE + "Hoary" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "hoary" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.WALNUT_WREATH.get(),"Walnut Wreath",
                L4J_WREATH_PRE + "Walnut" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "walnut" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.APPLE_WREATH.get(),"Apple Wreath",
                L4J_WREATH_PRE + "Apple" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "apple" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.LEMON_WREATH.get(),"Lemon Wreath",
                L4J_WREATH_PRE + "Lemon" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "lemon" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.PLUM_WREATH.get(),"Plum Wreath",
                L4J_WREATH_PRE + "Plum" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "plum" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.ORANGE_WREATH.get(),"Orange Wreath",
                L4J_WREATH_PRE + "Orange" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "orange" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, BFBlock.GOLDEN_WREATH.get(),"Golden Wreath",
                L4J_WREATH_PRE + "Golden" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "golden" + YAP_WREATH_POST
        );
        addItemWithDesc(builder, BFItem.GUARDIAN_SOUP.get(),"Guardian Soup",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.ELDEN_BOWL.get(),"Elden Bowl",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.BREADED_GUARDIAN.get(),"Breaded Guardian",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.MELON_SPRITZER_BOTTLE.get(),"Melon Spritzer Bottle",
                null,
                null
        );
        addItemWithDesc(builder, BFItem.GLISTERING_SPRITZER_BOTTLE.get(),"Glistering Spritzer Bottle",
                null,
                null
        );
    }

    private void doRecipeViewer(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        // JEI
        addRaw(builder, "jei.category.frontiers.bakery_fuel.single", "1 mob attempt");
        addRaw(builder, "jei.category.frontiers.bakery_fuel.multi", "%s mob attempts");

        // EMI
        addRaw(builder, "emi.fuel_time.frontiers.mobs", "%s mob attempt(s)");
        addRaw(builder, "emi.fuel_time.frontiers.bakery_chance", "+%s%% / item");

        addRaw(builder, "emi.category.frontiers.fletching", "Fletching");
        addRaw(builder, "emi.category.frontiers.monster_bakery", "Monster Bakery");
        addRaw(builder, "emi.category.frontiers.bakery_fuel", "Fuel (Monster Bakery)");
    }
}
