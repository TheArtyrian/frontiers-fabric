package net.artyrian.frontiers.datagen.frontiers;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FREntity;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.content.FRStatusEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.vertisoft.vectorlib.exclusive.datagen.VectorDatagen;
import net.vertisoft.vectorlib.exclusive.datagen.VectorLangGen;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FRLangProviderEnglish extends VectorLangGen
{
    private static final String BRIMTAN_SHELL = "Brimtan Shell";

    private static final String L4J_FOOD = "§f\uD83C\uDF56§r";
    private static final String L4J_ARMOR = "§f\uD83D\uDC58§r";

    public static final String L4J_WREATH_PRE = "A wreath made with ";
    public static final String L4J_WREATH_POST = " leaves. Can be used as decoration, and won't have collision when placed on doors.";
    public static final String YAP_WREATH_PRE = "A pretty little wreath made with ";
    public static final String YAP_WREATH_POST = " leaves";

    public static final String YAP_BRIMTAN_SHELL = "A shell made of Brimtan, can be clad onto the respective Vivulite tool with the right template";
    public static final String YAP_LUMEN = "A source of light that gets brighter with more redstone power";
    public static final String YAP_LUMEN_DIM = ", dimmer than most other lumens";
    public static final String YAP_BROKE_OBSID = "A broken obsidian tool, can be repaired with obsidian at an anvil";

    private static final String mournDesc = "A lustrous";
    private static final String cobaltDesc = "A deep blue";
    private static final String frostiteDesc = "A frosty";
    private static final String plateDesc = "A clunky";
    private static final String verdiniteDesc = "An iridescent";
    private static final String vivuliteDesc = "A beautiful scarlet";
    private static final String brimtanDesc = "A glowing";

    public FRLangProviderEnglish(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(Frontiers.MOD_ID, dataOutput, VectorDatagen.EN_US, registryLookup);
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
        doBiomes(provider, builder);
        doTags(provider, builder);
        doMisc(provider, builder);
        doExtraTips(provider, builder);

        doRecipeViewer(provider, builder);
        doCompatLang(provider, builder);

        doSubtitles(provider, builder);
    }

    private void doBlock(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String fenceTip = "Too high to jump over";
        String gateTip = "Can be opened, connects with fences and walls";
        String woodButtonTip = "Can be pushed by players, arrows, and tridents, stays pushed for 1.5 seconds";
        String woodPlateTip = "Produces a redstone signal when ANY entity makes contact with it";
        String doorTip = "Make yourself feel at home";
        String trapdoorTip = "Commonly used for everything BUT traps";

        addBlockWithDesc(builder, FRBlocks.COBALT_ORE.get(), "Cobalt Ore",
                "Can be mined with a Netherite pickaxe or better once the Wither has been defeated, otherwise it disintegrates. Can be smelted in a furnace to produce cobalt ingots.",
                "Forms in jungles under rare circumstances, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, FRBlocks.COBALT_BLOCK.get(), "Block of Cobalt",
                "A compact way of storing Cobalt.",
                "A deep blue block constructed from a collection of cobalt"
        );
        addBlockWithDesc(builder, FRBlocks.DEEPSLATE_COBALT_ORE.get(), "Deepslate Cobalt Ore",
                "Can be mined with a Netherite pickaxe or better once the Wither has been defeated, otherwise it disintegrates. Can be smelted in a furnace to produce cobalt ingots.",
                "Forms in jungles in all parts of the deepslate layer, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, FRBlocks.FROSTITE_ORE.get(), "Frostite Ore",
                "Can be mined with a cobalt pickaxe or better - but only when enchanted with Silk Touch. Must be melted by a light source to collect the raw frostite within.",
                "Forms in ice spikes, must be melted by light sources to drop ore"
        );
        addBlockWithDesc(builder, FRBlocks.FROSTITE_BLOCK.get(), "Block of Frostite",
                "A compact way of storing Frostite.",
                "A frosty block constructed from a collection of frostite"
        );
        addBlockWithDesc(builder, FRBlocks.GLOWING_OBSIDIAN.get(), "Glowing Obsidian",
                "A special glowing variant of Obsidian obtained when a Strange Core converts Obsidian. Creates a Crags portal in the Nether, or can be used as decoration.",
                "Obsidian mutated by a powerful core, used to make a portal to a terrifying place"
        );
        addBlockWithDesc(builder, FRBlocks.STRANGE_CORE.get(), "Strange Core",
                "A core with the ability to terraform its surroundings. Part of a structure - 5 blackstone in a plus shape, 4 Mourning Gold blocks in the corners of the plus, 4 blackstone above those, the core in the center, then another blackstone plus on top.",
                "Surround with blackstone and mourning gold blocks in a familiar pattern to activate"
        );
        addBlockWithDesc(builder, FRBlocks.ENCHANTING_MAGNET.get(), "Enchanting Magnet",
                "Picks up nearby Experience Orbs and stores a fraction of their value. Stored experience can be collected with Glass Bottles.",
                "Attracts and stores experience orbs, which can then be withdrawn with glass bottles",
                "Drags any nearby Experience Orbs to it, and picks them up on contact. The experience can then be harvested with Glass Bottles."
                        +"\n\nDrops 50% of its stored experience when mined, unless mined with Silk Touch; doing so will store the experience in the item."
        );
        addBlockWithDesc(builder, FRBlocks.ITEM_VACUUM.get(), "Item Vacuum",
                "Picks up nearby items within a small radius. Placing an item in an Item Frame above it will make it only pick up items of that kind.",
                "Sucks up nearby items; put an item in an item frame on top of one to make an item sorter!"
        );
        addBlockWithDesc(builder, FRBlocks.ANCIENT_ROSE.get(), "Ancient Rose",
                "An ultra-rare blue flower, grown when an Ancient Rose Seed is planted. Can be used as decoration or to craft a bush.",
                "Looking at this flower, a feeling of nostalgia washes over you..."
        );
        addBlockWithDesc(builder, FRBlocks.ANCIENT_ROSE_BUSH.get(), "Ancient Rose Bush",
                "A tall blue flower crafted from 4 Ancient Roses and a Rose Bush.",
                "A rose bush modified with ancient roses"
        );
        addBlockWithDesc(builder, FRBlocks.ROSE.get(), "Rose",
                "A rare red flower obtained by shearing Rose Bushes. Can be used to craft red dye.",
                "Not to be confused with the one from Delicate Dyes"
        );
        addBlockWithDesc(builder, FRBlocks.VIOLET_ROSE.get(), "Violet Rose",
                "A violet flower, grown when bonemealing a Rose or Ancient Rose nearby one another. Can be used as decoration or to craft a bush.",
                "\"I alone am the honored one.\""
        );
        addBlockWithDesc(builder, FRBlocks.VIOLET_ROSE_BUSH.get(), "Violet Rose Bush",
                "A tall purple flower crafted from 4 Violet Roses and a Rose Bush.",
                "A rose bush modified with violet roses"
        );
        addBlockWithDesc(builder, FRBlocks.MOURNING_GOLD_BLOCK.get(), "Block of Mourning Gold",
                "A compact way of storing Mourning Gold.",
                "A lustrous block constructed from a collection of mourning gold"
        );
        addBlockWithDesc(builder, FRBlocks.BLACK_EMERALD_BLOCK.get(), "Block of Black Emerald",
                "A compact way of storing Black Emeralds.",
                "The most powerful block constructed from a collection of black emeralds"
        );
        addBlockWithDesc(builder, FRBlocks.BLACK_EMERALD_ORE.get(), "Black Emerald Ore",
                "Can be mined with an Iron pickaxe or better to collect Black Emeralds.",
                "Forms at the top of the stone layer, and commonly in mountains"
        );
        addBlockWithDesc(builder, FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get(), "Deepslate Black Emerald Ore",
                "Can be mined with an Iron pickaxe or better to collect Black Emeralds.",
                "Rarely forms in the top layer of the deepslate layer and in mountains"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_BRICKS.get(), "Tower Bricks",
                "An extremely strong brick found in the mysterious white tower.",
                "Bricks from a living tower...and for some reason, they feel warm."
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_BRICK_STAIRS.get(), "Tower Brick Stairs",
                null,
                "Sleek stairs constructed from tower bricks"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_BRICK_SLAB.get(), "Tower Brick Slab",
                null,
                "Sleek slabs constructed from tower bricks"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_BRICK_WALL.get(), "Tower Brick Wall",
                "A wall made of Tower Bricks.",
                "A sleek wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, FRBlocks.MOSSY_TOWER_BRICKS.get(), "Mossy Tower Bricks",
                "An extremely strong moss-covered brick found in the mysterious white tower.",
                "Mossy bricks from a living tower"
        );
        addBlockWithDesc(builder, FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), "Mossy Tower Brick Stairs",
                null,
                "Sleek stairs constructed from tower bricks"
        );
        addBlockWithDesc(builder, FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(), "Mossy Tower Brick Slab",
                null,
                "Sleek slabs constructed from tower bricks"
        );
        addBlockWithDesc(builder, FRBlocks.MOSSY_TOWER_BRICK_WALL.get(), "Mossy Tower Brick Wall",
                "A wall made of Mossy Tower Bricks.",
                "A sleek wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, FRBlocks.NACRE_BRICKS.get(), "Nacre Bricks",
                "Baked from Shulker residue in a furnace.",
                "Shulker residue that has been smelted into bricks, still feels warm"
        );
        addBlockWithDesc(builder, FRBlocks.NACRE_BRICK_STAIRS.get(), "Nacre Brick Stairs",
                null,
                "Slick stairs constructed from bricks"
        );
        addBlockWithDesc(builder, FRBlocks.NACRE_BRICK_SLAB.get(), "Nacre Brick Slab",
                null,
                "Slick slabs constructed from bricks"
        );
        addBlockWithDesc(builder, FRBlocks.NACRE_BRICK_WALL.get(), "Nacre Brick Wall",
                "A wall made of Nacre Bricks.",
                "A slick wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, FRBlocks.SNOW_DAHLIA.get(), "Snow Dahlia",
                "A rare icy flower that can be used to craft light blue dye.",
                "An icy flower that spawns on frozen riverbanks"
        );
        addBlockWithDesc(builder, FRBlocks.FUNGAL_DAFFODIL.get(), "Fungal Daffodil",
                "An uncommon purple fungus that can be used to craft purple dye. Only grows on Mycelium.",
                "Don't even THINK about eating this."
        );
        addBlockWithDesc(builder, FRBlocks.FUNGAL_DAFFODIL_BLOCK.get(), "Fungal Daffodil Block",
                "Can be used as decoration. Causes players and mobs to bounce when they jump on it.",
                "A weird block from a large fungal daffodil's head"
        );
        addBlockWithDesc(builder, FRBlocks.CRIMCONE.get(), "Crimcone",
                "A rare red flower from the Nether that can be used to craft red dye. Only grows on Crimson Nylium.",
                "Look too quickly and you'd mistake this for a fungus!"
        );
        addBlockWithDesc(builder, FRBlocks.EXPERIWINKLE.get(), "Experiwinkle",
                "An extremely rare glowing flower found in Flower Fields and Meadows. Can be grown to harvest some experience.",
                "Not interested in experience farming? That's ok, too!"
        );
        addBlockWithDesc(builder, FRBlocks.CRAGULSTANE.get(), "Cragulstane",
                "A strong, warped stone that generates in the Crags. Can be used for construction.",
                "It feels strange, like flesh and stone put together"
        );
        String chiselCrag = "Cragulstane that has been finely chiseled";
        String crackedCrag = "Cragulstane bricks that took a beating";
        String cragStairs = "Fleshy stairs constructed from cragulstane bricks";
        String cragSlab = "Fleshy slabs constructed from cragulstane bricks";
        String cragWall = "A fleshy wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, FRBlocks.CRAGULSTANE_BRICKS.get(), "Cragulstane Bricks",
                "Crafted from Cragulstane. Can be used for construction.",
                "Cragulstane compacted into bricks"
        );
        addBlockWithDesc(builder, FRBlocks.CRAGULSTANE_BRICK_STAIRS.get(), "Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, FRBlocks.CRAGULSTANE_BRICK_SLAB.get(), "Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, FRBlocks.CRAGULSTANE_BRICK_WALL.get(), "Cragulstane Brick Wall",
                "A wall made of Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), "Cracked Cragulstane Bricks",
                "Created by smelting Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get(), "Chiseled Cragulstane Bricks",
                "Crafted from Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), "Orange Cragulstane Bricks",
                "Crafted from Cragulstane and a Lava Bucket. Can be used for construction.",
                "Cragulstane compacted into bricks (but orange)"
        );
        addBlockWithDesc(builder, FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), "Orange Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), "Orange Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), "Orange Cragulstane Brick Wall",
                "A wall made of Orange Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), "Cracked Orange Cragulstane Bricks",
                "Created by smelting Orange Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(), "Chiseled Orange Cragulstane Bricks",
                "Crafted from Orange Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), "Tyrian Cragulstane Bricks",
                "Crafted from Cragulstane Bricks and both types of Nether Wart. Can be used for construction.",
                "Cragulstane compacted into bricks (but purple)"
        );
        addBlockWithDesc(builder, FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), "Tyrian Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), "Tyrian Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), "Tyrian Cragulstane Brick Wall",
                "A wall made of Tyrian Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get(), "Cracked Tyrian Cragulstane Bricks",
                "Created by smelting Tyrian Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(), "Chiseled Tyrian Cragulstane Bricks",
                "Crafted from Tyrian Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), "Brimmed Cragulstane Bricks",
                "Crafted from Cragulstane and Brimtan Ingots. Can be used for construction.",
                "Cragulstane compacted into bricks (but cooler)"
        );
        addBlockWithDesc(builder, FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), "Brimmed Cragulstane Brick Stairs",
                null,
                cragStairs
        );
        addBlockWithDesc(builder, FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), "Brimmed Cragulstane Brick Slab",
                null,
                cragSlab
        );
        addBlockWithDesc(builder, FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), "Brimmed Cragulstane Brick Wall",
                "A wall made of Brimmed Cragulstane Bricks.",
                cragWall
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), "Cracked Brimmed Cragulstane Bricks",
                "Created by smelting Brimmed Cragulstane Bricks in a furnace. Can be used for construction.",
                crackedCrag
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(), "Chiseled Brimmed Cragulstane Bricks",
                "Crafted from Brimmed Cragulstane Brick Slabs. Can be used for construction.",
                chiselCrag
        );
        addBlockWithDesc(builder, FRBlocks.AESTHENOSTONE.get(), "Aesthenostone",
                "The literal core of the world. Cannot be broken, and inflicts extreme damage when stood on.",
                "\"When you've reached the lowest of lows, the only way to go is up...\""
        );
        addBlockWithDesc(builder, FRBlocks.ONYX_BONE_BLOCK.get(), "Onyx Bone Block",
                "Crafted from Onyx Bone Meal, or found in the Nether in fossils. Can be used as decoration.",
                "Looks like someone didn't drink enough milk"
        );
        addBlockWithDesc(builder, FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get(), "Small Corrupted Amethyst Bud",
                "Will grow into a Medium Corrupted Amethyst Bud. Can also be used as decoration.",
                "A small cluster of corrupted amethyst"
        );
        addBlockWithDesc(builder, FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get(), "Medium Corrupted Amethyst Bud",
                "Will grow into a Large Corrupted Amethyst Bud. Can also be used as decoration.",
                "A medium cluster of corrupted amethyst"
        );
        addBlockWithDesc(builder, FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get(), "Large Corrupted Amethyst Bud",
                "Will grow into a Corrupted Amethyst Cluster that can be mined for End Crystal Shards. Can also be used as decoration.",
                "A large cluster of corrupted amethyst"
        );
        addBlockWithDesc(builder, FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get(), "Corrupted Amethyst Cluster",
                "Can be mined for End Crystal Shards or used as decoration.",
                "A cluster of corrupted amethyst, can be harvested for some end crystal shards"
        );
        addBlockWithDesc(builder, FRBlocks.QUICKSAND.get(), "Quicksand",
                "Slows your movement on contact. Being submerged in it will suffocate you.",
                "Slowly traps and suffocates entities, sneaking will prevent sinking"
        );
        addBlockWithDesc(builder, FRBlocks.RED_QUICKSAND.get(), "Red Quicksand",
                "Slows your movement on contact. Being submerged in it will suffocate you.",
                "This just exists for some reason"
        );
        addBlockWithDesc(builder, FRBlocks.SUGAR_CANE_BLOCK.get(), "Sugar Cane Block",
                "A compact way of storing Sugar Cane.",
                "Sugar cane compressed into a block"
        );
        addBlockWithDesc(builder, FRBlocks.COCOA_BEAN_BLOCK.get(), "Cocoa Bean Block",
                "A compact way of storing Cocoa Beans.",
                "Cocoa beans compressed into a block"
        );
        addBlockWithDesc(builder, FRBlocks.ROTTEN_FLESH_BLOCK.get(), "Rotten Flesh Block",
                "A compact way of storing Rotten Flesh.",
                "Top 10 building materials to get the police called on you with"
        );
        addBlockWithDesc(builder, FRBlocks.EGG_PALLET.get(), "Pallet of Eggs",
                "A compact way of storing Eggs. Can be stacked on top of each other like slabs.",
                "Eggs (gently) compressed into a slab"
        );
        addBlockWithDesc(builder, FRBlocks.GOLDEN_EGG_PALLET.get(), "Pallet of Golden Eggs",
                "A compact way of storing Golden Eggs. Can be stacked on top of each other like slabs.",
                "Golden eggs (gently) compressed into a slab"
        );
        String netherCracked = " nether bricks that took a beating";
        String netherChis = " nether bricks that has been finely chiseled";
        String netherStairs = "Rough stairs constructed from ";
        String netherSlab = "Rough slabs constructed from ";
        String netherWall = "A rough wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, FRBlocks.BLUE_NETHER_BRICKS.get(), "Blue Nether Bricks",
                "Crafted from Warped Wart and Nether Brick. Can be used as decoration.",
                "Nether bricks stained with warped wart to appear blue"
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get(), "Chiseled Blue Nether Bricks",
                "Crafted with Blue Nether Brick Slabs. Can be used for construction.",
                "Blue" + netherChis
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), "Cracked Blue Nether Bricks",
                "Created by smelting Blue Nether Bricks in a furnace. Can be used for construction.",
                "Blue" + netherCracked
        );
        addBlockWithDesc(builder, FRBlocks.BLUE_NETHER_BRICK_STAIRS.get(), "Blue Nether Brick Stairs",
                null,
                netherStairs + "blue nether bricks"
        );
        addBlockWithDesc(builder, FRBlocks.BLUE_NETHER_BRICK_SLAB.get(), "Blue Nether Brick Slab",
                null,
                netherSlab + "blue nether bricks"
        );
        addBlockWithDesc(builder, FRBlocks.BLUE_NETHER_BRICK_WALL.get(), "Blue Nether Brick Wall",
                "A wall made of Blue Nether Bricks.",
                netherWall
        );
        addBlockWithDesc(builder, FRBlocks.BLUE_NETHER_BRICK_FENCE.get(), "Blue Nether Brick Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get(), "Blue Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, FRBlocks.PURPLE_NETHER_BRICKS.get(), "Purple Nether Bricks",
                "Crafted from both kinds of Wart and Nether Brick. Can be used as decoration.",
                "Nether bricks stained with warts to appear purple"
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get(), "Chiseled Purple Nether Bricks",
                "Crafted with Purple Nether Brick Slabs. Can be used for construction.",
                "Purple" + netherChis
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), "Cracked Purple Nether Bricks",
                "Created by smelting Purple Nether Bricks in a furnace. Can be used for construction.",
                "Purple" + netherCracked
        );
        addBlockWithDesc(builder, FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), "Purple Nether Brick Stairs",
                null,
                netherStairs + "purple nether bricks"
        );
        addBlockWithDesc(builder, FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(), "Purple Nether Brick Slab",
                null,
                netherSlab + "purple nether bricks"
        );
        addBlockWithDesc(builder, FRBlocks.PURPLE_NETHER_BRICK_WALL.get(), "Purple Nether Brick Wall",
                "A wall made of Purple Nether Bricks.",
                netherWall
        );
        addBlockWithDesc(builder, FRBlocks.PURPLE_NETHER_BRICK_FENCE.get(), "Purple Nether Brick Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get(), "Purple Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, FRBlocks.CHISELED_RED_NETHER_BRICKS.get(), "Chiseled Red Nether Bricks",
                "Crafted with Red Nether Brick Slabs. Can be used for construction.",
                "Red" + netherChis
        );
        addBlockWithDesc(builder, FRBlocks.CRACKED_RED_NETHER_BRICKS.get(), "Cracked Red Nether Bricks",
                "Created by smelting Red Nether Bricks in a furnace. Can be used for construction.",
                "Red" + netherCracked
        );
        addBlockWithDesc(builder, FRBlocks.RED_NETHER_BRICK_FENCE.get(), "Red Nether Brick Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get(), "Red Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, FRBlocks.NETHER_BRICK_FENCE_GATE.get(), "Nether Brick Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, FRBlocks.AMETHYST_LUMEN.get(), "Amethyst Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.COBALT_LUMEN.get(), "Cobalt Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.DIAMOND_LUMEN.get(), "Diamond Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.EMERALD_LUMEN.get(), "Emerald Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.FROSTITE_LUMEN.get(), "Frostite Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.QUARTZ_LUMEN.get(), "Quartz Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.REDSTONE_LUMEN.get(), "Redstone Lumen",
                "Emits different levels of light based on the power provided. Emits much weaker light than most lumens.",
                YAP_LUMEN + YAP_LUMEN_DIM
        );
        addBlockWithDesc(builder, FRBlocks.VERDINITE_LUMEN.get(), "Verdinite Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.VIVULITE_LUMEN.get(), "Vivulite Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.BRIMTAN_LUMEN.get(), "Brimtan Lumen",
                null,
                YAP_LUMEN
        );
        addBlockWithDesc(builder, FRBlocks.ECHO_LUMEN.get(), "Echo Lumen",
                "Emits different levels of light based on the power provided. Emits much weaker light than most lumens.",
                YAP_LUMEN + YAP_LUMEN_DIM
        );
        addBlockWithDesc(builder, FRBlocks.VERDINITE_ORE.get(), "Verdinite Ore",
                "Can be mined with a cobalt pickaxe or better, then smelted in a furnace to produce verdinite ingots.",
                "Forms under rare circumstances, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, FRBlocks.VERDINITE_BLOCK.get(), "Block of Verdinite",
                "A compact way of storing Verdinite.",
                "A bright green block constructed from a collection of verdinite"
        );
        addBlockWithDesc(builder, FRBlocks.DEEPSLATE_VERDINITE_ORE.get(), "Deepslate Verdinite Ore",
                "Can be mined with a cobalt pickaxe or better, then smelted in a furnace to produce verdinite ingots.",
                "Found near the bottom of the deepslate layer, can't be mined until a powerful entity is defeated"
        );
        addBlockWithDesc(builder, FRBlocks.VIVULITE_ORE.get(), "Vivulite Ore",
                "Can be mined with a verdinite pickaxe or better, then smelted in a furnace to produce vivulite ingots.",
                "Forms under rare circumstances, never spawns exposed to air"
        );
        addBlockWithDesc(builder, FRBlocks.VIVULITE_BLOCK.get(), "Block of Vivulite",
                "A compact way of storing Vivulite.",
                "A beautiful scarlet block constructed from a collection of vivulite"
        );
        addBlockWithDesc(builder, FRBlocks.DEEPSLATE_VIVULITE_ORE.get(), "Deepslate Vivulite Ore",
                "Can be mined with a verdinite pickaxe or better, then smelted in a furnace to produce vivulite ingots.",
                "Forms near bedrock in the Overworld, never spawns exposed to air"
        );
        String paleStairs = "Regal stairs constructed from ";
        String paleSlab = "Regal slabs constructed from ";
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE_BRICKS.get(), "Pale Prismarine Bricks",
                "A rare variant of Prismarine Bricks crafted using Prismarine Bricks and a Pale Prismarine Shard.",
                "Pale prismarine compacted into bricks"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), "Pale Prismarine Brick Slab",
                null,
                paleSlab + "pale prismarine bricks"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), "Pale Prismarine Brick Stairs",
                null,
                paleStairs + "pale prismarine bricks"
        );
        addBlockWithDesc(builder, FRBlocks.DEEP_PALE_PRISMARINE.get(), "Deep Pale Prismarine",
                "A rare variant of Dark Prismarine crafted using Dark Prismarine and a Pale Prismarine Shard.",
                "Dark prismarine with an elderly touch"
        );
        addBlockWithDesc(builder, FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), "Deep Pale Prismarine Slab",
                null,
                paleSlab + "deep pale prismarine"
        );
        addBlockWithDesc(builder, FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), "Deep Pale Prismarine Stairs",
                null,
                paleStairs + "deep pale prismarine"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE.get(), "Pale Prismarine",
                "A rare variant of Prismarine crafted using Prismarine and a Pale Prismarine Shard.",
                "Prismarine infused with material from elder guardians"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE_SLAB.get(), "Pale Prismarine Slab",
                null,
                paleSlab + "pale prismarine"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE_STAIRS.get(), "Pale Prismarine Stairs",
                null,
                paleStairs + "pale prismarine"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_PRISMARINE_WALL.get(), "Pale Prismarine Wall",
                "A wall made of Pale Prismarine.",
                "A regal wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, FRBlocks.TURTLE_SCUTE_BRICKS.get(), "Turtle Scute Bricks",
                "Crafted from Brick and Turtle Scutes. Can be used for decoration.",
                "No turtles were harmed in the making of this block"
        );
        addBlockWithDesc(builder, FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), "Turtle Scute Brick Slab",
                null,
                "Bumpy slabs constructed from turtle scute bricks"
        );
        addBlockWithDesc(builder, FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), "Turtle Scute Brick Stairs",
                null,
                "Bumpy stairs constructed from turtle scute bricks"
        );
        addBlockWithDesc(builder, FRBlocks.TURTLE_SCUTE_BRICK_WALL.get(), "Turtle Scute Brick Wall",
                "A wall made of Turtle Scute Bricks.",
                "A bumpy wall that can connect to other walls, too high to jump over"
        );
        addBlockWithDesc(builder, FRBlocks.SEA_GLASS.get(), "Sea Glass",
                "A semi-transparent block that emits low light and lets light through. Can be crafted from a Glass Block and Prismarine Crystals.",
                "A glass block infused with prismarine crystals to make it glow"
        );
        addBlockWithDesc(builder, FRBlocks.SEA_GLASS_PANE.get(), "Sea Glass Pane",
                "Can be used as an alternative to Sea Glass.",
                "Sea glass that has been cut into panes"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_SEA_GLASS.get(), "Pale Sea Glass",
                "A rare variant of Sea Glass crafted using Sea Glass and a Pale Prismarine Shard.",
                "A glass block infused with prismarine crystals to make it glow"
        );
        addBlockWithDesc(builder, FRBlocks.PALE_SEA_GLASS_PANE.get(), "Pale Sea Glass Pane",
                "Can be used as an alternative to Pale Sea Glass.",
                "Pale sea glass that has been cut into panes"
        );
        addBlockWithDesc(builder, FRBlocks.CREEPER_MODEL.get(), "Creeper Model",
                "A taxidermized Creeper. Can be used as decoration.",
                "Behold the anatomy of the Creepus Explodus, without the risk of being blown to pieces!"
        );
        addBlockWithDesc(builder, FRBlocks.SKELETON_MODEL.get(), "Skeleton Model",
                "A taxidermized Skeleton. Can be used as decoration.",
                "A model of a skeleton, perfect for putting on display"
        );
        addBlockWithDesc(builder, FRBlocks.STRAY_MODEL.get(), "Stray Model",
                "A taxidermized Stray. Can be used as decoration.",
                "A model of a stray, can be sheared to examine the bones"
        );
        addBlockWithDesc(builder, FRBlocks.BOGGED_MODEL.get(), "Bogged Model",
                "A taxidermized Bogged. Can be used as decoration.",
                "A model of a bogged, perfect for putting on display"
        );
        addBlockWithDesc(builder, FRBlocks.BLAZE_MODEL.get(), "Blaze Model",
                "A taxidermized Blaze. Can be used as decoration.",
                "A model of a blaze, comes alive with redstone power"
        );
        addBlockWithDesc(builder, FRBlocks.WITHER_SKELETON_MODEL.get(), "Wither Skeleton Model",
                "A taxidermized Wither Skeleton. Can be used as decoration.",
                "It's not a skull, but you gotta admit this is FAR cooler"
        );
        addBlockWithDesc(builder, FRBlocks.ENDERMAN_MODEL.get(), "Enderman Model",
                "A taxidermized Enderman. Can be used as decoration.",
                "Getting this must've been a difficult task, huh?"
        );
        addBlockWithDesc(builder, FRBlocks.SLIME_MODEL.get(), "Slime Model",
                "A taxidermized Slime. Can be used as decoration.",
                "A model of a slime, perfect for putting on display"
        );
        addBlockWithDesc(builder, FRBlocks.MAGMA_CUBE_MODEL.get(), "Magma Cube Model",
                "A taxidermized Magma Cube. Can be used as decoration.",
                "A model of a magma cube, perfect for putting on display"
        );
        addBlockWithDesc(builder, FRBlocks.PHANTOM_MODEL.get(), "Phantom Model",
                "A taxidermized Phantom. Can be used as decoration.",
                "LITERALLY NO OTHER MONSTER DESERVED A FATE THIS GRUESOME, THIS IS WHAT PHANTOMS DESERVE"
        );
        String hieloStairs = "Frigid stairs constructed from ";
        String hieloSlab = "Frigid slabs constructed from ";
        String hieloWall = "A frigid wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE.get(), "Hielostone",
                "Found in cold biomes. Can be mined with a pickaxe to collect cobblefrost.",
                "A rock infused with permafrost, commonly found in colder biomes"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_STAIRS.get(), "Hielostone Stairs",
                null,
                hieloStairs + "hielostone"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_SLAB.get(), "Hielostone Slab",
                null,
                hieloSlab + "hielostone"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_WALL.get(), "Hielostone Wall",
                "A wall made of Hielostone.",
                hieloWall
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_BRICKS.get(), "Hielostone Bricks",
                "Crafted with Hielostone. Can be used for construction and as decoration.",
                "Hielostone compacted into bricks"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_BRICK_STAIRS.get(), "Hielostone Brick Stairs",
                null,
                hieloStairs + "hielostone bricks"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_BRICK_SLAB.get(), "Hielostone Brick Slab",
                null,
                hieloSlab + "hielostone bricks"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_BRICK_WALL.get(), "Hielostone Brick Wall",
                "A wall made of Hielostone Bricks.",
                hieloWall
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_TILES.get(), "Hielostone Tiles",
                "Crafted with Hielostone Bricks. Can be used for construction and as decoration.",
                "Hielostone compacted into tiles"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_TILE_STAIRS.get(), "Hielostone Tile Stairs",
                null,
                hieloStairs + "hielostone tiles"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_TILE_SLAB.get(), "Hielostone Tile Slab",
                null,
                hieloSlab + "hielostone tiles"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_TILE_WALL.get(), "Hielostone Tile Wall",
                "A wall made of Hielostone Tiles.",
                hieloWall
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_PLATES.get(), "Hielostone Plates",
                "Crafted with Hielostone Brick Slabs. Can be used for construction and as decoration.",
                "Hielostone compacted into plates"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_PLATE_STAIRS.get(), "Hielostone Plate Stairs",
                null,
                hieloStairs + "hielostone plates"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_PLATE_SLAB.get(), "Hielostone Plate Slab",
                null,
                hieloSlab + "hielostone plates"
        );
        addBlockWithDesc(builder, FRBlocks.HIELOSTONE_PLATE_WALL.get(), "Hielostone Plate Wall",
                "A wall made of Hielostone Plates.",
                hieloWall
        );
        addBlockWithDesc(builder, FRBlocks.COBBLEFROST.get(), "Cobblefrost",
                "Mined from hielostone using a pickaxe. Can be used to construct a furnace or stone tools.",
                "A rough block that would look nice in colder builds"
        );
        addBlockWithDesc(builder, FRBlocks.COBBLEFROST_STAIRS.get(), "Cobblefrost Stairs",
                null,
                hieloStairs + "cobblefrost"
        );
        addBlockWithDesc(builder, FRBlocks.COBBLEFROST_SLAB.get(), "Cobblefrost Slab",
                null,
                hieloSlab + "cobblefrost"
        );
        addBlockWithDesc(builder, FRBlocks.COBBLEFROST_WALL.get(), "Cobblefrost Wall",
                "A wall made of Cobblefrost.",
                hieloWall
        );
        String quickStairs = "Shoddy stairs constructed from ";
        String quickSlab = "Shoddy slabs constructed from ";
        String quickWall = "A shoddy wall that can connect to other walls, too high to jump over";
        addBlockWithDesc(builder, FRBlocks.CRUSTED_QUICKSAND.get(), "Crusted Quicksand",
                "Crafted from Quicksand and Wheat. Can be used to craft Crusty Sand Bricks or be used for construction.",
                "Dried-out, compacted quicksand with a strange white color"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTED_RED_QUICKSAND.get(), "Crusted Red Quicksand",
                "Crafted from Red Quicksand and Wheat. Can be used to craft Crusty Red Sand Bricks or be used for construction.",
                "Dried-out, compacted quicksand with a strange red color"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_SAND_BRICKS.get(), "Crusty Sand Bricks",
                "Crafted from Crusted Quicksand. Can be used for construction.",
                "Bricks made from dried-out quicksand"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), "Crusty Red Sand Bricks",
                "Crafted from Crusted Red Quicksand. Can be used for construction.",
                "Bricks made from dried-out red quicksand"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get(), "Crusty Sand Brick Stairs",
                null,
                quickStairs + "crusted quicksand"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_SAND_BRICK_SLAB.get(), "Crusty Sand Brick Slab",
                null,
                quickSlab + "crusted quicksand"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_SAND_BRICK_WALL.get(), "Crusty Sand Brick Wall",
                "A wall made of crusted quicksand.",
                quickWall
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get(), "Crusty Red Sand Brick Stairs",
                null,
                quickStairs + "crusted red quicksand"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get(), "Crusty Red Sand Brick Slab",
                null,
                quickSlab + "crusted red quicksand"
        );
        addBlockWithDesc(builder, FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get(), "Crusty Red Sand Brick Wall",
                "A wall made of crusted red quicksand.",
                quickWall
        );
        addBlockWithDesc(builder, FRBlocks.BRIMTAN_ORE.get(), "Brimtan Ore",
                "Can be mined with a vivulite pickaxe or better to collect brimtan clusters.",
                "Found all over the crags in small batches"
        );
        addBlockWithDesc(builder, FRBlocks.BRIMTAN_BLOCK.get(), "Block of Brimtan",
                "A compact way of storing Brimtan.",
                "A molten block constructed from a collection of brimtan"
        );
        addBlockWithDesc(builder, FRBlocks.VIVULITE_ANVIL.get(), "Vivulite Anvil",
                "Can be used to repair weapons, tools and armor. Does not break no matter how many times it's used.",
                "Acts like an anvil, but will never break no matter how much you use it",
                "Can repair and enchant items just as a normal Anvil can, with one huge bonus: it will never break no matter how much it's used!"
        );
        addBlockWithDesc(builder, FRBlocks.BEEF_WELLINGTON.get(), "Beef Wellington",
                "Restores 3 " + L4J_FOOD + ". Can be used 7 times.",
                "I know a famous british chef who would KILL for this"
        );
        addBlockWithDesc(builder, FRBlocks.FRUITCAKE.get(), "Fruitcake",
                "Can be used 7 times, dropping a slice of fruitcake on each use",
                "A delicious holiday treat to share with (or throw at) friends"
        );
        addBlockWithDesc(builder, FRBlocks.CRAGS_PORTAL.get(), "Crags Portal",
                null,
                null
        );
        addBlockWithDesc(builder, FRBlocks.PERSONAL_CHEST.get(), "Personal Chest",
                "Stores blocks and items inside. Can only be accessed by the player who placed it.",
                "Can be used to store items, only the owner and allowed users can access the contents",
                "Can store items like a normal Chest, but can only be accessed by the person who placed it. Additional players can be given access using a Chest Key."
        );
        addBlockWithDesc(builder, FRBlocks.CURSE_ALTAR.get(), "Curse Altar",
                "Can remove both enchantments and curses from a desired item, so long as it's been charged with a Cursed Tablet.",
                "\"For the low cost of 30 levels, you can remove curses from your items!\"",
                "Can be used to remove both specific enchantments AND curses from a desired item. Must be charged with a Cursed Tablet first.\n\nEach use will wear down the tablet until it breaks, in which case you'll need to find another."
        );
        addBlockWithDesc(builder, FRBlocks.GLISTERING_MELON.get(), "Glistering Melon",
                "Can be crafted from Glistering Melon Slices.",
                "A large melon coated in gold"
        );
        addBlockWithDesc(builder, FRBlocks.CARVED_MELON.get(), "Carved Melon",
                "Can be worn as a helmet or crafted with a torch to create a June-O-Lantern.",
                "\"The people of this town love Halloween so much, they celebrate it twice a year. And wouldn't you know it, it's today!\""
        );
        addBlockWithDesc(builder, FRBlocks.CARVED_GLISTERING_MELON.get(), "Carved Glistering Melon",
                "Can be worn as a helmet or crafted with a torch to create a Glistering June-O-Lantern.",
                "Are you doing this just to flex at this point?"
        );
        addBlockWithDesc(builder, FRBlocks.JUNE_O_LANTERN.get(), "June o'Lantern",
                "Used to create brighter light than torches. Melts snow/ice and can be used underwater.",
                "If you don't collect 500 pieces of candy before this goes out, the Trickster will eat you!"
        );
        addBlockWithDesc(builder, FRBlocks.GLISTERING_JUNE_O_LANTERN.get(), "Glistering June o'Lantern",
                "Used to create brighter light than torches. Melts snow/ice and can be used underwater.",
                "Flaunt your wealth on those dastardly trick-or-treaters"
        );
        addBlockWithDesc(builder, FRBlocks.WHITE_PUMPKIN.get(), "White Pumpkin",
                "Can be worn like a Carved Pumpkin without obstructing your view.",
                "Prevents endermen from getting mad without blocking your view"
        );
        addBlockWithDesc(builder, FRBlocks.WHITE_JACK_O_LANTERN.get(), "White Jack o'Lantern",
                "Used to create brighter light than torches. Melts snow/ice and can be used underwater.",
                "\"Oh boy, we're really in story mode now!\""
        );
        addBlockWithDesc(builder, FRBlocks.SPIRIT_CANDLE.get(), "Spirit Candle",
                "Weakens any undead mobs in a small radius and deters endermen when lit.",
                "Weakens undead mobs and keeps endermen away",
                "When placed and lit, any undead mobs that come into the vicinity will be significantly weakened. Additionally, any Endermen that try teleporting nearby will be stopped."
        );
        addBlockWithDesc(builder, FRBlocks.RAW_COBALT_BLOCK.get(), "Block of Raw Cobalt",
                "A compact way of storing Raw Cobalt.",
                "Raw cobalt compressed into a block"
        );
        addBlockWithDesc(builder, FRBlocks.RAW_VERDINITE_BLOCK.get(), "Block of Raw Verdinite",
                "A compact way of storing Raw Verdinite.",
                "Raw verdinite compressed into a block"
        );
        addBlockWithDesc(builder, FRBlocks.RAW_VIVULITE_BLOCK.get(), "Block of Raw Vivulite",
                "A compact way of storing Raw Vivulite.",
                "Raw vivulite compressed into a block"
        );
        addBlockWithDesc(builder, FRBlocks.RAW_FROSTITE_BLOCK.get(), "Block of Raw Frostite",
                "A compact way of storing Raw Frostite.",
                "Raw frostite compressed into a block"
        );
        addBlockWithDesc(builder, FRBlocks.MONSTER_BAKERY.get(), "Monster Bakery",
                "Can be used to spawn mobs. Requires an item pertaining to a specific mob, plus fuel. The spawn chance increases with each item.",
                "Will spawn mobs with the right materials, fuel, and matching spawning conditions for said mob",
                "Can be used to spawn a small selection of mobs. Requires an item pertaining to a specific mob (i.e. Rotten Flesh or Blaze Rods), plus fuel. You can find all of the recipes and fuels in their respective viewer tabs."
                    + "\n\nWhen an item finishes \"baking\", it will attempt to spawn a mob nearby based on the spawn percentage. The conditions must match those of the mob's spawning conditions. "
                    + "If it fails, the chance increases until it succeeds - in which case the spawn chance resets."
        );
        addBlockWithDesc(builder, FRBlocks.PHANTOM_STITCH_BED.get(), "Phantom-Stitch Bed",
                "Fully heals the player upon waking up, and provides a small Absorption & Slow Falling bonus.",
                "The key to keeping those pesky phantoms away",
                "Sleeping in it will fully heal you, provide small buffs and provide you with Well-Rested, which repels Phantoms!"
        );
        addBlockWithDesc(builder, FRBlocks.PHANTASMIC_TNT.get(), "Phantasmic TNT",
                "Used to cause stronger explosions than TNT.",
                "TNT but worse"
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK.get(), "Eboncork",
                "A wood-like substance found in spike formations in The Crags. Can be crafted into planks.",
                "A spongy piece of wood-like substance from a spike in the Crags"
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_PLANKS.get(), "Eboncork Planks",
                "Used as a building material and can be crafted into many things. Can be crafted from Eboncork.",
                "Fine planks constructed from eboncork"
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_STAIRS.get(), "Eboncork Stairs",
                null,
                "Fine wooden stairs constructed from eboncork"
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_SLAB.get(), "Eboncork Slab",
                null,
                "Fine wooden slabs constructed from eboncork"
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_FENCE.get(), "Eboncork Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_FENCE_GATE.get(), "Eboncork Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_BUTTON.get(), "Eboncork Button",
                null,
                woodButtonTip
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_PRESSURE_PLATE.get(), "Eboncork Pressure Plate",
                "A sensitive Eboncork pressure plate that can be activated by applying almost any amount of pressure.",
                woodPlateTip
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_DOOR.get(), "Eboncork Door",
                null,
                doorTip
        );
        addBlockWithDesc(builder, FRBlocks.EBONCORK_TRAPDOOR.get(), "Eboncork Trapdoor",
                null,
                trapdoorTip
        );
        addBlockWithDesc(builder, FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get(), "Radiant Blighted Birch Log",
                null,
                "A sturdy log from a blighted birch tree during the day"
        );
        addBlockWithDesc(builder, FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), "Radiant Blighted Birch Wood",
                null,
                "A sturdy piece of wood from a radiant blighted birch log"
        );
        addBlockWithDesc(builder, FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get(), "Sullen Blighted Birch Log",
                null,
                "A sturdy log from a blighted birch tree during the night"
        );
        addBlockWithDesc(builder, FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), "Sullen Blighted Birch Wood",
                null,
                "A sturdy piece of wood from a sullen blighted birch log"
        );
        addBlockWithDesc(builder, FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get(), "Stripped Blighted Birch Log",
                "A Blighted Birch log that has had the bark removed with an axe.",
                "A blighted birch log that has been stripped- by accident?"
        );
        addBlockWithDesc(builder, FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), "Stripped Blighted Birch Wood",
                "Blighted Birch wood that has had the bark removed with an axe.",
                "A blighted birch wood that has been stripped- by accident?"
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_LEAVES.get(), "Blighted Birch Leaves",
                null,
                "Foliage from a blighted birch tree"
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_SAPLING.get(), "Blighted Birch Sapling",
                null,
                "A young plant that will grow into a blighted birch tree, mainly found in swamp huts",
                "Found in Witch Huts with a 1/3 chance of replacing the Red Mushroom pot...unless it's Halloween, when it will always replace it."
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_PLANKS.get(), "Blighted Birch Planks",
                null,
                "Fine planks constructed from blighted birch"
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_STAIRS.get(), "Blighted Birch Stairs",
                null,
                "Fine wooden stairs constructed from blighted birch"
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_SLAB.get(), "Blighted Birch Slab",
                null,
                "Fine wooden slabs constructed from blighted birch"
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_FENCE.get(), "Blighted Birch Fence",
                null,
                fenceTip
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), "Blighted Birch Fence Gate",
                null,
                gateTip
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_BUTTON.get(), "Blighted Birch Button",
                null,
                woodButtonTip
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), "Blighted Birch Pressure Plate",
                "A sensitive Blighted Birch pressure plate that can be activated by applying almost any amount of pressure.",
                woodPlateTip
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_DOOR.get(), "Blighted Birch Door",
                null,
                doorTip
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), "Blighted Birch Trapdoor",
                null,
                trapdoorTip
        );
        addBlockWithDesc(builder, FRBlocks.SLIME_TRAIL.get(), "Slime Trail",
                "Generates underground where Slimes can spawn. Can be used as decoration.",
                "Slimes might spawn nearby these"
        );
        addBlockWithDesc(builder, FRBlocks.SLIME_BULB.get(), "Slime Bulb",
                "A rare block that will slowly grow a single Hardened Slime. Does not drop itself when destroyed.",
                "A strange orifice that grows a single hardened slime crystal"
        );
        addBlockWithDesc(builder, FRBlocks.NECRO_WEAVE_BLOCK.get(), "Block of Necro Weave",
                "A compact way of storing Necro Weave. Also cushions falls far better than Hay Bale.",
                "A block constructed from a collection of necro weave, also good for breaking falls!"
        );
        addBlockWithDesc(builder, FRBlocks.NECRO_RUG.get(), "Necro Rug",
                "Crafted from Necro Weave. Sneaking while walking on it allows you to phase through it.",
                "Can be phased through by sneaking on it"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_WATCHER.get(), "Tower Watcher",
                "A part of the white tower, it inflicts any intruders with several debuffs.",
                "Like the immune system of the tower, protects it from intruders"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_SPAWNER.get(), "Tower Spawner",
                "A part of the white tower, it spawns monsters into the world.",
                "Spawns monsters to stop intruders"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_KEY_VAULT.get(), "Tower Key Vault",
                "Interacting will provide you with a single piece of a Tower Key. Will only drop one per person.",
                "Drops a single piece of a tower key when interacted with"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_TREASURE_VAULT.get(), "Tower Treasure Vault",
                "Unlocked with a Tower Key, after which it can be picked up. Will periodically ask for a specific item; giving it this item will cause it to drop rare loot in return.",
                "Will give you exclusive items in exchange for an item it desires"
        );
        addBlockWithDesc(builder, FRBlocks.TOWER_HEART.get(), "Tower Heart",
                "The core of the White Tower - destroying it will permanently disable the tower.",
                "The very core of the White Tower"
        );
        addBlockWithDesc(builder, FRBlocks.COBALT_GRILLES.get(), "Cobalt Grilles",
                "A blue alternative to Iron Bars.",
                "You thought copper bars were lazy?"
        );

        addBlockWithDesc(builder, FRBlocks.OAK_WREATH.get(), "Oak Wreath",
                L4J_WREATH_PRE + "Oak" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "oak" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.BIRCH_WREATH.get(), "Birch Wreath",
                L4J_WREATH_PRE + "Birch" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "birch" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.SPRUCE_WREATH.get(), "Spruce Wreath",
                L4J_WREATH_PRE + "Spruce" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "spruce" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.JUNGLE_WREATH.get(), "Jungle Wreath",
                L4J_WREATH_PRE + "Jungle" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "jungle" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.ACACIA_WREATH.get(), "Acacia Wreath",
                L4J_WREATH_PRE + "Acacia" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "acacia" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.DARK_OAK_WREATH.get(), "Dark Oak Wreath",
                L4J_WREATH_PRE + "Dark Oak" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "dark oak" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.MANGROVE_WREATH.get(), "Mangrove Wreath",
                L4J_WREATH_PRE + "Mangrove" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "mangrove" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.CHERRY_WREATH.get(), "Cherry Wreath",
                L4J_WREATH_PRE + "Cherry" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "cherry" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.AZALEA_WREATH.get(), "Azalea Wreath",
                L4J_WREATH_PRE + "Azalea" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "azalea" + YAP_WREATH_POST
        );
        addBlockWithDesc(builder, FRBlocks.BLIGHTED_BIRCH_WREATH.get(), "Blighted Birch Wreath",
                L4J_WREATH_PRE + "Blighted Birch" + L4J_WREATH_POST,
                YAP_WREATH_PRE + "blighted birch" + YAP_WREATH_POST
        );
    }

    private void doItemGeneral(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        String smithing_template = "Smithing Template";
        String music_disc = "Music Disc";

        addItemWithDesc(builder, FRItems.RAW_COBALT.get(), "Raw Cobalt",
                "Can be smelted in a furnace to create a cobalt ingot.",
                "A chunk of cobalt that can be smelted into an ingot"
        );
        addItemWithDesc(builder, FRItems.COBALT_INGOT.get(), "Cobalt Ingot",
                null,
                "A shimmering blue metal used to create powerful equipment"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_INGOT.get(), "Brimtan Ingot",
                "A hot-to-the-touch ingot which can be used to craft tools made from this material. Created by smelting ore in a furnace.",
                "A burning hot metal used to create otherworldly equipment"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_CLUSTER.get(), "Brimtan Cluster",
                "Can be smelted in a furnace to create a brimtan nugget.",
                "A warped chunk of brimtan that can be smelted into nuggets"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_NUGGET.get(), "Brimtan Nugget",
                "Created by smelting Brimtan Clusters in a furnace. Can be crafted into a Brimtan Ingot.",
                "A piece of a brimtan ingot"
        );
        addItemWithDesc(builder, FRItems.RAW_FROSTITE.get(), "Raw Frostite",
                "Obtained from melted frostite ore. Can be smelted in a furnace to create a frostite ingot.",
                "A chunk of frostite that can be smelted into an ingot"
        );
        addItemWithDesc(builder, FRItems.FROSTITE_INGOT.get(), "Frostite Ingot",
                "A freezing-cold ingot obtained through a tedious process. Created by smelting ore in a furnace.",
                "A beautiful icy metal used to create frosty equipment"
        );
        addItemWithDesc(builder, FRItems.CURSED_TABLET.get(), "Cursed Tablet",
                "Can be placed onto a Curse Altar to enable the removal of desired enchantments and curses, for an experience price.",
                "Lifts curses from items at a Curse Altar"
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_CASING.get(), "Obsidian Casing",
                "Crafted with Obsidian and Diamonds. Can be used to upgrade Golden tools in a Smithing Table.",
                "An amalgam comprised of obsidian and diamond, useful for encasing golden tools"
        );
        addItemWithDesc(builder, FRItems.COBALT_FISHING_ROD.get(), "Cobalt Fishing Rod",
                "Used to catch a wider variety of fish and items.",
                "\"Water is blue, that means fish LOVE the color blue!\" - Artyrian, probably"
        );
        addItemWithDesc(builder, FRItems.COBALT_SHIELD.get(), "Cobalt Shield",
                "Stronger than a regular shield, has less disable time when hit with an axe, and knocks melee attacks further back.",
                "A stronger variant of shield; knocks back attackers and recovers faster when hit with an axe"
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with Golden tools and an Obsidian Casing to give the gear an Obsidian tip.",
                "An obsidian tablet that can tip golden tools with obsidian"
        );
        addItemWithDesc(builder, FRItems.HEART_OF_THE_WARDEN.get(), "Heart of the Warden",
                "Dropped by the Warden. Using it on a Sculk Catalyst allows for the collection of a very exotic ore.",
                "Proof of the indomitable human spirit at work - that even the worst horrors can be overcome"
        );
        addItemWithDesc(builder, FRItems.SHULKER_RESIDUE.get(), "Shulker Residue",
                "Collected by smashing a Shulker's bullet. Can be used to make potions, bricks and other goods.",
                "Basically glorified shulker spit; can be turned into bricks and other goods"
        );
        addItemWithDesc(builder, FRItems.WITHERED_ESSENCE.get(), "Withered Essence",
                "Dropped by the Wither, used in crafting a variety of useful items.",
                "A foggy substance from the Wither that makes you uneasy just looking at"
        );
        addItemWithDesc(builder, FRItems.ONYX_BONE.get(), "Onyx Bone",
                "Collected by killing a Wither skeleton. Can be used to craft necro weave.",
                "\"Because bones & coal made absolutely zero sense\""
        );
        addItemWithDesc(builder, FRItems.NECRO_WEAVE.get(), "Necro Weave",
                "Crafted from Onyx Bones and Wool. Can be crafted into armor.",
                "#literallyterraria"
        );
        addItemWithDesc(builder, FRItems.ANCIENT_ROSE_SEED.get(), "Ancient Rose Seed",
                "Grows into an Ancient Rose and is obtained by letting a Sniffer sniff it out of the ground.",
                "When planted it will grow into an ancient rose"
        );
        addItemWithDesc(builder, FRItems.ECTOPLASM.get(), "Ectoplasm",
                "Dropped by Ghasts when they die. Mainly used to craft Mourning gold ingots.",
                "A sticky substance used to craft mourning gold ingots"
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_INGOT.get(), "Mourning Gold Ingot",
                "Crafted with Gold Ingots, Ectoplasm and Quartz. Can be used to make unique tools.",
                "A spectral metal used to make equipment"
        );
        addItemWithDesc(builder, FRItems.BLACK_EMERALD.get(), "Black Emerald",
                "A rare variant of Emerald. Can be used to craft rare tools.",
                "\"She is the most powerful emerald\""
        );
        addItemWithDesc(builder, FRItems.MARSHMALLOW.get(), "Marshmallow",
                "Can be consumed extremely fast. Restores 0.5 §f\uD83C\uDF56§r, or can be cooked on a campfire (or furnace, if you hate fun).",
                "How many can you fit in your mouth?"
        );
        addItemWithDesc(builder, FRItems.ROASTED_MARSHMALLOW.get(), "Roasted Marshmallow",
                "Restores 1 §f\uD83C\uDF56§r. Can be consumed extremely fast.",
                "And for once, it didn't get set on fire and burn to a crisp!"
        );
        addItemWithDesc(builder, FRItems.INVOKE_SHARD.get(), "Invoke Shard",
                "A magical shard that can be crafted into Totems of Undying & other powerful items.",
                "A magical shard dropped by Evokers, useful in creating totems and tomes"
        );
        addItemWithDesc(builder, FRItems.NACRE_BRICK.get(), "Nacre Brick",
                "Baked from Shulker residue in a furnace.",
                "A shiny, slick brick that can be put together to create a block"
        );
        addItemWithDesc(builder, FRItems.LEVI_ROLL.get(), "Levi Roll",
                "Restores 1.5 " + L4J_FOOD + ". Eating this can cause you to momentarily levitate.",
                "A snack so good it'll sweep you off your feet...literally!"
        );
        addItemWithDesc(builder, FRItems.FRUITCAKE_SLICE.get(), "Slice of Fruitcake",
                "Restores 3 " + L4J_FOOD + ". Using while full will throw it, dealing 6 damage to any mob.",
                "A rich cake often filled with fruit and nuts; fun to throw!"
        );
        addItemWithDesc(builder, FRItems.POMEGRANATE.get(), "Pomegranate",
                "Restores 0.5 " + L4J_FOOD + ". Has a chance to not be consumed on use.",
                "A shiny fruit with delicious seeds, has a chance to not be eaten when used"
        );
        addItemWithDesc(builder, FRItems.TRUFFLE.get(), "Truffle",
                "A very rare mushroom obtained by letting a Sniffer sniff it out of Mycelium. Can also pacify Hoglins, preventing them from becoming Zoglins.",
                "A delicious delicacy - can be turned into food or fed to Hoglins"
        );
        addItemWithDesc(builder, FRItems.TRUFFLE_OIL.get(), "Truffle Oil",
                "Restores 0.5 " + L4J_FOOD + ", but is extremely saturating. Drinking this can cause Hunger, however",
                "A savory, highly saturating vial of oil from truffles"
        );
        addItemWithDesc(builder, FRItems.TRUFFLE_POTATO_PUFF.get(), "Truffle Potato Puff",
                "Restores 2 " + L4J_FOOD + " and provides moderate saturation. Can be eaten more quickly than most other foods.",
                "Artyrian can tell you stories about his old job with this one!"
        );
        addItemWithDesc(builder, FRItems.RAW_VERDINITE.get(), "Raw Verdinite",
                "Can be smelted in a furnace to create a verdinite ingot.",
                "Despite its looks, it's not actually radioactive"
        );
        addItemWithDesc(builder, FRItems.VERDINITE_INGOT.get(), "Verdinite Ingot",
                null,
                "A swirly green-blue metal used to create even stronger equipment"
        );
        addItemWithDesc(builder, FRItems.TABLET_FRAGMENT.get(), "Tablet Fragment",
                "Can be used to craft a Cursed Tablet.",
                "A remnant of a magical tablet"
        );
        addItemWithDesc(builder, FRItems.APPLE_OF_ENLIGHTENMENT.get(), "Apple of Enlightenment",
                "An extremely rare food item. Permanently adds 2 §f\uD83D\uDC96§r to your maximum health when first eaten, changing your health's appearance (§f\uD83D\uDC97§r).",
                "Increases your max health by 4 - let's see EarthBound do that one"
        );
        addItemWithDesc(builder, FRItems.LIGHTNING_IN_A_BOTTLE.get(), "Lightning in a Bottle",
                "Created when lightning strikes a brewing stand with an attached lightning rod, as long as it contains glass bottles. Can be thrown or used in crafting.",
                "...seriously?! It's an IDIOM! You weren't supposed to actually go and DO IT!!!"
        );
        addItemWithDesc(builder, FRItems.PURIFIED_END_CRYSTAL.get(), "Purified End Crystal",
                "A variant of the End Crystal that can be used to further power up Enchanting Tables. Does not explode when hit, instead dropping itself.",
                "Can be placed in corners surrounding your enchanting table to power it up",
                "A secret(?) item obtained by purifying an End Crystal on a Curse Altar. Placing 4 around the corners of your bookshelf-adorned Enchanting Table will allow it to grant treasure enchants (such as Mending)."
        );
        addItemWithDesc(builder, FRItems.END_CRYSTAL_SHARD.get(), "End Crystal Shard",
                "Dropped by destroyed End Crystals. Can be used in crafting, or used to gain Quick Flight.",
                "Can be smashed to gain temporary flight powers"
        );
        addItemWithDesc(builder, FRItems.RAVAGER_TOOTH.get(), "Ravager Tooth",
                "Dropped by Ravagers. Can be used in crafting.",
                "\"Dude, you knocked its teeth out!\""
        );
        addItemWithDesc(builder, FRItems.INCENSE.get(), "Incense",
                "The pure essence of anger, dropped by Vexes. Can be used in crafting many spirit-based items.",
                "Did you know the name of this item is synonymous with \"vex\"? Cool, I know"
        );
        addItemWithDesc(builder, FRItems.ONYX_MEAL.get(), "Onyx Meal",
                "Used to instantly grow Nether Wart and Warped Wart, and will destroy nearby grasses when used on Grass Blocks. Can be used to craft Black Dye.",
                "Crushed onyx bones that can kill tall grass and grow nether wart"
        );
        addItemWithDesc(builder, FRItems.PITCH_INGOT.get(), "Pitch Ingot",
                "A strange ingot which can be used to craft tools made from this material. Created by smelting ore in a furnace.",
                "A weird, sculky ingot used to craft special equipment"
        );
        addItemWithDesc(builder, FRItems.TOWER_KEY_FRAGMENT.get(), "Key Fragment",
                "Can be used to craft a Tower Key.",
                "A broken piece of a special key"
        );
        addItemWithDesc(builder, FRItems.TOWER_KEY.get(), "Tower Key",
                "Can be used at a Tower Vault to unlock it.",
                "Unlocks special vaults located inside the Tower"
        );
        addItemWithDesc(builder, FRItems.WARPED_WART.get(), "Warped Wart",
                "Used in advanced potion brewing. This can be found naturally growing in Bastion Remnants. It can also be planted on Soul Sand.",
                "A rare fungus that grows on soul sand, a common ingredient for advanced potion brewing"
        );
        addItemWithDesc(builder, FRItems.VIVULITE_INGOT.get(), "Vivulite Ingot",
                null,
                "An iridescent red-violet metal used to create really powerful equipment"
        );
        addItemWithDesc(builder, FRItems.RAW_VIVULITE.get(), "Raw Vivulite",
                "Can be smelted in a furnace to create a vivulite ingot.",
                "A chunk of vivulite that can be smelted into an ingot"
        );
        addItemWithDesc(builder, FRItems.VOID_PEARL.get(), "Void's Eye",
                "When thrown, will show the direction to an End Portal. When twelve of these are placed in the End Portal Frames, the End Portal will be activated.",
                "Can be used to track a stronghold or to fill an end portal frame"
                // "Consumed on use, allowing access to a secure personal storage."
                // "Opens a rift into personal storage when used"
        );
        addItemWithDesc(builder, FRItems.UNFINISHED_CORE.get(), "Unfinished Core",
                "Found in Bastion Remnants. Must be combined with 4 unique Core Plates to make a Reactive Core.",
                "Can be brought to completion with four unique plates"
        );
        addItemWithDesc(builder, FRItems.REACTIVE_CORE.get(), "Reactive Core",
                "Crafted from an Unfinished Core and the 4 unique Core Plates. Used to craft a Strange Core.",
                "A fully-assembled core, used for crafting a special block"
        );
        addItemWithDesc(builder, FRItems.DEPTHS_CORE_PLATE.get(), "Core Plate",
                "A Core Plate made from rare ores. Used in crafting a Reactive Core.",
                "Combine with 3 other plates & an unfinished core to complete it"
        );
        addItemWithDesc(builder, FRItems.FRONTAL_CORE_PLATE.get(), "Core Plate",
                "A Core Plate made from exotic ores. Used in crafting a Reactive Core.",
                "Combine with 3 other plates & an unfinished core to complete it"
        );
        addItemWithDesc(builder, FRItems.GUARDIAN_SLICE.get(), "Raw Guardian Slice",
                "Restores 1.5 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A raw piece of meat, or is it a fish?"
        );
        addItemWithDesc(builder, FRItems.ELDER_GUARDIAN_SLICE.get(), "Raw Elder Guardian Slice",
                "Restores 2 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A raw piece of meat, or is it a fish?"
        );
        addItemWithDesc(builder, FRItems.COOKED_GUARDIAN_SLICE.get(), "Cooked Guardian Slice",
                "Restores 3 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A searing piece of meat, tastes like sushi!"
        );
        addItemWithDesc(builder, FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), "Cooked Elder Guardian Slice",
                "Restores 4 " + L4J_FOOD + ". Removes Mining Fatigue on consumption.",
                "A searing piece of meat, tastes like sushi!"
        );
        addItemWithDesc(builder, FRItems.PALE_PRISMARINE_SHARD.get(), "Pale Prismarine Shard",
                "Dropped by Elder Guardians. Can be used in crafting alternate colors of Prismarine blocks.",
                "A sharp remnant of a rare stone"
        );
        addItemWithDesc(builder, FRItems.MUSIC_DISC_DIAPHRAGM.get(), music_disc,
                "Can be played in a jukebox.",
                "Can be inserted into a jukebox to play melancholic tunes"
        );
        addItemWithDesc(builder, FRItems.TOTEM_OF_AVARICE.get(), "Totem of Avarice",
                "Dying with this in your inventory will allow you to keep your items, but breaks it in the process.",
                "Keep your items on death...but not your levels!"
        );
        addItemWithDesc(builder, FRItems.VOID_DIAMOND.get(), "Void Diamond",
                "Use these to create some of the strongest, most powerful weapons and armor in the world.",
                "A precious gemstone infused with dark energy, useful for creating legendary equipment"
        );
        addItemWithDesc(builder, FRItems.CHEST_KEY.get(), "Chest Key",
                "Can be used on a Personal Chest that you own to give its assigned player access to it.",
                "Use on a player to link them, then use on your personal chest to give that player access"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with a Vivulite Helmet and the correct Brimtan Shell to make a Brimtan Helmet.",
                "A glowing obsidian tablet that can upgrade vivulite helmets to brimtan"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with a Vivulite Chestplate and the correct Brimtan Shell to make a Brimtan Chestplate.",
                "A glowing obsidian tablet that can upgrade vivulite chestplates to brimtan"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with Vivulite Leggings and the correct Brimtan Shell to make Brimtan Leggings.",
                "A glowing obsidian tablet that can upgrade vivulite leggings to brimtan"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with Vivulite Boots and the correct Brimtan Shell to make Brimtan Boots.",
                "A glowing obsidian tablet that can upgrade vivulite boots to brimtan"
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), smithing_template,
                "Can be combined with a Vivulite Tool and the correct Brimtan Shell to make a Brimtan Tool.",
                "A glowing obsidian tablet that can upgrade vivulite tools to brimtan"
        );
        addItemWithDesc(builder, FRItems.PALE_TRIDENT.get(), "Pale Trident",
                "A more powerful Trident crafted from Elder Guardian Spines.",
                "\"Upgrades, people, upgrades!\""
        );
        addItemWithDesc(builder, FRItems.ELDER_GUARDIAN_SPINE.get(), "Elder Guardian Spine",
                "Can be used to craft a Pale Trident. (Most likely will be removed in a future version.)",
                "Possibly one of the most pointless items ever made"
        );
        addItemWithDesc(builder, FRItems.EXPERIWINKLE_BULB.get(), "Experiwinkle Bulb",
                "Can be planted on farmland to grow an Experiwinkle. It cannot be bonemealed.",
                "When planted it will grow into an experiwinkle"
        );
        addItemWithDesc(builder, FRItems.FROST_BONE.get(), "Frost Bone",
                "Collected by killing a Stray. Can be crafted into snow melt.",
                "Only the finest of item bloat"
        );
        addItemWithDesc(builder, FRItems.SNOW_MELT.get(), "Snow Melt",
                "Will melt snow layers in a small radius. Any affected blocks won't be covered by snowfall again unless covered with a solid block.",
                "Melts nearby snow when placed on a block; affected blocks can't be snowed on again unless covered"
        );
        addItemWithDesc(builder, FRItems.MESSAGE_IN_A_BOTTLE.get(), "Message in a Bottle",
                "Can hold a small amount of text.",
                "Write some text on it, then throw it into an ocean or river"
        );
        addItemWithDesc(builder, FRItems.BOTTLED_MESSAGE.get(), "Bottled Message",
                "A note from an anonymous source. Throwing it into open water in an Ocean biome will allow it to be fished up in any Ocean biome.",
                "An anonymous note in a bottle...I wonder what it says?"
        );
        addItemWithDesc(builder, FRItems.MANA_BOTTLE.get(), "Bottle o' Magicks",
                "When thrown, it drops Mana Orbs which increase your mana meter when collected.",
                "A glowing bottle that contains a small amount of mana"
        );
        addItemWithDesc(builder, FRItems.SPAWNER_CHUNK.get(), "Spawner Chunk",
                "A piece of a Monster Spawner. Can be used to craft a Monster Bakery.",
                "A piece from a spawner, used to craft monster bakeries"
        );
        addItemWithDesc(builder, FRItems.GOLDEN_EGG.get(), "Golden Egg",
                "Laid by chickens when fed a Golden Nugget. Can be consumed to earn extra Experience from mobs.",
                "When thrown it effects all nearby with allurement - there is a chance to spawn golden chickens"
        );
        addItemWithDesc(builder, FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), smithing_template,
                null,
                "A granite tablet that can be used to trim armor"
        );
        addItemWithDesc(builder, FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), smithing_template,
                null,
                "A slime tablet that can be used to trim armor"
        );
        addItemWithDesc(builder, FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), smithing_template,
                null,
                "An onyx bone tablet that can be used to trim armor"
        );
        addItemWithDesc(builder, FRItems.SOUL.get(), "Soul",
                "A important resource dropped by stronger enemies.",
                "A special material dropped by powerful enemies"
        );
        addItemWithDesc(builder, FRItems.HARDENED_SLIME.get(), "Hardened Slime",
                "Crystallized slime found rarely underground. Can be used to craft Slime Shoes.",
                "A crystal made of slime, used to craft a bouncy set of boots"
        );
        addItemWithDesc(builder, FRItems.BAIT.get(), "Bait",
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
        addItemWithDesc(builder, FRItems.NECRO_WEAVE_HELMET.get(), "Rotcross Helm",
                "Gives the user 1 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapHelm
        );
        addItemWithDesc(builder, FRItems.NECRO_WEAVE_CHESTPLATE.get(), "Rotcross Suit Top",
                "Gives the user 3 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapChest
        );
        addItemWithDesc(builder, FRItems.NECRO_WEAVE_LEGGINGS.get(), "Rotcross Suit Leggings",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapLegs
        );
        addItemWithDesc(builder, FRItems.NECRO_WEAVE_BOOTS.get(), "Rotcross Treads",
                "Gives the user 1 " + L4J_ARMOR + " when worn. Wearing a full set enhances the power of bows.",
                necroDesd + yapBoots
        );
        // Mourning Gold
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_HELMET.get(), "Mourning Gold Helmet",
                "Gives the user 1 " + L4J_ARMOR + " when worn.",
                mournDesc + yapHelm
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_CHESTPLATE.get(), "Mourning Gold Chestplate",
                "Gives the user 3 " + L4J_ARMOR + " when worn.",
                mournDesc + yapChest
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_LEGGINGS.get(), "Mourning Gold Leggings",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn.",
                mournDesc + yapLegs
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_BOOTS.get(), "Mourning Gold Boots",
                "Gives the user 1 " + L4J_ARMOR + " when worn.",
                mournDesc + yapBoots
        );
        // Cobalt
        addItemWithDesc(builder, FRItems.COBALT_HELMET.get(), "Cobalt Helmet",
                "Gives the user 2 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapHelm
        );
        addItemWithDesc(builder, FRItems.COBALT_CHESTPLATE.get(), "Cobalt Chestplate",
                "Gives the user 4.5 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapChest
        );
        addItemWithDesc(builder, FRItems.COBALT_LEGGINGS.get(), "Cobalt Leggings",
                "Gives the user 3.5 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapLegs
        );
        addItemWithDesc(builder, FRItems.COBALT_BOOTS.get(), "Cobalt Boots",
                "Gives the user 2 " + L4J_ARMOR + " when worn.",
                cobaltDesc + yapBoots
        );
        // Frostite
        addItemWithDesc(builder, FRItems.FROSTITE_HELMET.get(), "Frostite Helmet",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapHelm
        );
        addItemWithDesc(builder, FRItems.FROSTITE_CHESTPLATE.get(), "Frostite Chestplate",
                "Gives the user 5 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapChest
        );
        addItemWithDesc(builder, FRItems.FROSTITE_LEGGINGS.get(), "Frostite Leggings",
                "Gives the user 4 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapLegs
        );
        addItemWithDesc(builder, FRItems.FROSTITE_BOOTS.get(), "Frostite Boots",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn. Wearing a full set provides immunity against specific effects.",
                frostiteDesc + yapBoots
        );
        // Plate
        addItemWithDesc(builder, FRItems.PLATE_HELMET.get(), "Plate Helm",
                "Gives the user 1 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " helmet that provides some benefits"
        );
        addItemWithDesc(builder, FRItems.PLATE_CHESTPLATE.get(), "Plate Chestpiece",
                "Gives the user 2 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " chestplate that provides some benefits"
        );
        addItemWithDesc(builder, FRItems.PLATE_LEGGINGS.get(), "Plate Legpiece",
                "Gives the user 1.5 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " pair of boots that provides some benefits"
        );
        addItemWithDesc(builder, FRItems.PLATE_BOOTS.get(), "Plate Boots",
                "Gives the user 0.5 §f\uD83D\uDC96§r when worn, and increases mining capabilities.",
                plateDesc + " pair of boots that provides some benefits"
        );
        // Verdinite
        addItemWithDesc(builder, FRItems.VERDINITE_HELMET.get(), "Verdinite Helmet",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapHelm
        );
        addItemWithDesc(builder, FRItems.VERDINITE_CHESTPLATE.get(), "Verdinite Chestplate",
                "Gives the user 5 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapChest
        );
        addItemWithDesc(builder, FRItems.VERDINITE_LEGGINGS.get(), "Verdinite Leggings",
                "Gives the user 4 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapLegs
        );
        addItemWithDesc(builder, FRItems.VERDINITE_BOOTS.get(), "Verdinite Boots",
                "Gives the user 2.5 " + L4J_ARMOR + " when worn.",
                verdiniteDesc + yapBoots
        );
        // Vivulite
        addItemWithDesc(builder, FRItems.VIVULITE_HELMET.get(), "Vivulite Helmet",
                "Gives the user 3 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapHelm
        );
        addItemWithDesc(builder, FRItems.VIVULITE_CHESTPLATE.get(), "Vivulite Chestplate",
                "Gives the user 5.5 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapChest
        );
        addItemWithDesc(builder, FRItems.VIVULITE_LEGGINGS.get(), "Vivulite Leggings",
                "Gives the user 4.5 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapLegs
        );
        addItemWithDesc(builder, FRItems.VIVULITE_BOOTS.get(), "Vivulite Boots",
                "Gives the user 3 " + L4J_ARMOR + " when worn.",
                vivuliteDesc + yapBoots
        );
        // Brimtan
        addItemWithDesc(builder, FRItems.BRIMTAN_HELMET.get(), "Brimtan Helmet",
                "Gives the user 3.5 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapHelm
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_CHESTPLATE.get(), "Brimtan Chestplate",
                "Gives the user 6 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapChest
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_LEGGINGS.get(), "Brimtan Leggings",
                "Gives the user 5 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapLegs
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_BOOTS.get(), "Brimtan Boots",
                "Gives the user 3.5 " + L4J_ARMOR + " when worn, and glows in the dark.",
                brimtanDesc + yapBoots
        );
        // Brimtan Shells
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_HELMET.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Helmet on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_CHESTPLATE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Chestplate on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_LEGGINGS.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Leggings on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_BOOTS.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Boots on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        // Miscs
        addItemWithDesc(builder, FRItems.WITCH_HAT.get(), "Witch Hat",
                "Protects the wearer from most types of magic, and allows near-instant potion consumption.",
                "Lets you drink potions super fast and protects you from magic attacks",
                "Wearing this defends you from most magic attacks, with the added bonus of near-instant potion drinking."
        );
        addItemWithDesc(builder, FRItems.SLIME_SHOES.get(), "Slime Shoes",
                "Gives the user 0.5 " + L4J_ARMOR + " when worn, and causes the wearer to bounce when hitting the ground.",
                "A pair of bouncy shoes that lets you fall from greater heights and bounce around"
        );
        addItemWithDesc(builder, FRItems.COBALT_HORSE_ARMOR.get(), "Cobalt Horse Armor",
                "A special type of Armor that can be equipped to a horse. Provides 7 " + L4J_ARMOR + ".",
                "A deep blue armor to protect a horse with"
        );
        addItemWithDesc(builder, FRItems.VERDINITE_HORSE_ARMOR.get(), "Verdinite Horse Armor",
                "A special type of Armor that can be equipped to a horse. Provides 8.5 " + L4J_ARMOR + ".",
                "A bright green armor to protect a horse with"
        );
        addItemWithDesc(builder, FRItems.VIVULITE_HORSE_ARMOR.get(), "Vivulite Horse Armor",
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
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_PICKAXE.get(), "Mourning Gold Pickaxe",
                null,
                mournDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_AXE.get(), "Mourning Gold Axe",
                null,
                mournDesc + axe
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_SWORD.get(), "Mourning Gold Sword",
                null,
                mournDesc + sword
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_SHOVEL.get(), "Mourning Gold Shovel",
                null,
                mournDesc + shovel
        );
        addItemWithDesc(builder, FRItems.MOURNING_GOLD_HOE.get(), "Mourning Gold Hoe",
                null,
                mournDesc + hoe
        );
        // Obsidian
        addItemWithDesc(builder, FRItems.OBSIDIAN_PICKAXE.get(), "Obsidian-Tipped Pickaxe",
                null,
                obsDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_PICKAXE_BROKEN.get(), "Broken Obsidian-Tipped Pickaxe",
                "A broken Obsidian Pickaxe. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_AXE.get(), "Obsidian-Tipped Axe",
                null,
                obsDesc + axe
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_AXE_BROKEN.get(), "Broken Obsidian-Tipped Axe",
                "A broken Obsidian Axe. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_SWORD.get(), "Obsidian-Tipped Sword",
                null,
                obsDesc + sword
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_SWORD_BROKEN.get(), "Broken Obsidian-Tipped Sword",
                "A broken Obsidian Sword. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_SHOVEL.get(), "Obsidian-Tipped Shovel",
                null,
                obsDesc + shovel
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_SHOVEL_BROKEN.get(), "Broken Obsidian-Tipped Shovel",
                "A broken Obsidian Shovel. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_HOE.get(), "Obsidian-Tipped Hoe",
                null,
                obsDesc + hoe
        );
        addItemWithDesc(builder, FRItems.OBSIDIAN_HOE_BROKEN.get(), "Broken Obsidian-Tipped Hoe",
                "A broken Obsidian Hoe. Can be repaired with Obsidian on an Anvil.",
                YAP_BROKE_OBSID
        );
        // Cobalt
        addItemWithDesc(builder, FRItems.COBALT_PICKAXE.get(), "Cobalt Pickaxe",
                null,
                cobaltDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.COBALT_AXE.get(), "Cobalt Axe",
                null,
                cobaltDesc + axe
        );
        addItemWithDesc(builder, FRItems.COBALT_SWORD.get(), "Cobalt Sword",
                null,
                cobaltDesc + sword
        );
        addItemWithDesc(builder, FRItems.COBALT_SHOVEL.get(), "Cobalt Shovel",
                null,
                cobaltDesc + shovel
        );
        addItemWithDesc(builder, FRItems.COBALT_HOE.get(), "Cobalt Hoe",
                null,
                cobaltDesc + hoe
        );
        // Frostite
        addItemWithDesc(builder, FRItems.FROSTITE_PICKAXE.get(), "Frostite Pickaxe",
                null,
                frostiteDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.FROSTITE_AXE.get(), "Frostite Axe",
                null,
                frostiteDesc + axe
        );
        addItemWithDesc(builder, FRItems.FROSTITE_SWORD.get(), "Frostite Sword",
                null,
                frostiteDesc + sword
        );
        addItemWithDesc(builder, FRItems.FROSTITE_SHOVEL.get(), "Frostite Shovel",
                null,
                frostiteDesc + shovel
        );
        addItemWithDesc(builder, FRItems.FROSTITE_HOE.get(), "Frostite Hoe",
                null,
                frostiteDesc + hoe
        );
        // Verdinite
        addItemWithDesc(builder, FRItems.VERDINITE_PICKAXE.get(), "Verdinite Pickaxe",
                null,
                verdiniteDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.VERDINITE_AXE.get(), "Verdinite Axe",
                null,
                verdiniteDesc + axe
        );
        addItemWithDesc(builder, FRItems.VERDINITE_SWORD.get(), "Verdinite Sword",
                null,
                verdiniteDesc + sword
        );
        addItemWithDesc(builder, FRItems.VERDINITE_SHOVEL.get(), "Verdinite Shovel",
                null,
                verdiniteDesc + shovel
        );
        addItemWithDesc(builder, FRItems.VERDINITE_HOE.get(), "Verdinite Hoe",
                null,
                verdiniteDesc + hoe
        );
        // Vivulite
        addItemWithDesc(builder, FRItems.VIVULITE_PICKAXE.get(), "Vivulite Pickaxe",
                null,
                vivuliteDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.VIVULITE_AXE.get(), "Vivulite Axe",
                null,
                vivuliteDesc + axe
        );
        addItemWithDesc(builder, FRItems.VIVULITE_SWORD.get(), "Vivulite Sword",
                null,
                vivuliteDesc + sword
        );
        addItemWithDesc(builder, FRItems.VIVULITE_SHOVEL.get(), "Vivulite Shovel",
                null,
                vivuliteDesc + shovel
        );
        addItemWithDesc(builder, FRItems.VIVULITE_HOE.get(), "Vivulite Hoe",
                null,
                vivuliteDesc + hoe
        );
        // Brimtan
        addItemWithDesc(builder, FRItems.BRIMTAN_PICKAXE.get(), "Brimtan Pickaxe",
                null,
                brimtanDesc + pickaxe
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_PICKAXE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Pickaxe on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_AXE.get(), "Brimtan Axe",
                null,
                brimtanDesc + axe
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_AXE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Axe on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SWORD.get(), "Brimtan Sword",
                null,
                brimtanDesc + sword
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_SWORD.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Sword on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHOVEL.get(), "Brimtan Shovel",
                null,
                brimtanDesc + shovel
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_SHOVEL.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Shovel on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_HOE.get(), "Brimtan Hoe",
                null,
                brimtanDesc + hoe
        );
        addItemWithDesc(builder, FRItems.BRIMTAN_SHELL_HOE.get(), BRIMTAN_SHELL,
                "Can be combined with a Vivulute Hoe on a Smithing Table, provided you have the template.",
                YAP_BRIMTAN_SHELL
        );

        // Bows
        addItemWithDesc(builder, FRItems.COPPER_BOW.get(), "Copper Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, FRItems.IRON_BOW.get(), "Iron Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, FRItems.DIAMOND_BOW.get(), "Diamond Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, FRItems.NETHERITE_BOW.get(), "Netherite Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        addItemWithDesc(builder, FRItems.ECHO_BOW.get(), "Echo Bow",
                "A faster version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance. Charges faster than other bows"
        );
        addItemWithDesc(builder, FRItems.VERDINITE_BOW.get(), "Verdinite Bow",
                "A stronger version of a wooden Bow.",
                "Can be charged with arrows to deal damage from a distance"
        );
        // Tomes
        addItemWithDesc(builder, FRItems.TOME_OF_FANGS.get(), "Tome of Fangs",
                "A book that summons a line of Evoker Fangs in the direction you're facing. Using it at your feet summons a circle of fangs instead.",
                "Summons a stream of evoker fangs wherever you use it"
        );
        addItemWithDesc(builder, FRItems.THUNDERVAST_TOME.get(), "Thundervast Tome",
                "A book that channels the power of lightning. Allows shooting thunderbolts at a heavy mana cost - and when fully charged, allows you to unleash a terrible storm.",
                "YOUR LIVES ARE FORFEIT!!!!"
        );
        // Arrowheads
        addItemWithDesc(builder, FRItems.WARP_ARROW.get(), "Warp Arrow",
                "Teleports the user to wherever it lands, at the cost of some health.",
                arrow_prefix_yt + " to teleport the shooter to where it lands"
        );
        addItemWithDesc(builder, FRItems.WARP_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Warp Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, FRItems.SUBZERO_ARROW.get(), "Subzero Arrow",
                "Immediately freezes the victim as if they were standing in Powder Snow.",
                arrow_prefix_yt + " to freeze the target"
        );
        addItemWithDesc(builder, FRItems.SUBZERO_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Subzero Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, FRItems.BOUNCY_ARROW.get(), "Bouncy Arrow",
                "Ricochets off surfaces and targets.",
                arrow_prefix_yt + " and bounces on impact"
        );
        addItemWithDesc(builder, FRItems.BOUNCY_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Bouncy Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, FRItems.DYNAMITE_ARROW.get(), "Dynamite Arrow",
                "Makes a small explosion on impact.",
                arrow_prefix_yt + " and explodes on impact"
        );
        addItemWithDesc(builder, FRItems.DYNAMITE_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Dynamite Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, FRItems.PRISMARINE_ARROW.get(), "Prismarine Arrow",
                "Travels much faster through water.",
                arrow_prefix_yt + "; travels quickly underwater"
        );
        addItemWithDesc(builder, FRItems.PRISMARINE_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Prismarine Arrows.",
                arrowhead_yt
        );
        addItemWithDesc(builder, FRItems.SPECTRAL_ARROW_ARROWHEAD.get(), arrowhead,
                "Used on a Fletching Table to create Spectral Arrows.",
                arrowhead_yt
        );
        // Spawn Eggs
        addItemWithDesc(builder, FRItems.CRAWLER_SPAWN_EGG.get(), "Crawler Spawn Egg",
                null,
                "A deadlier version of a creeper with bigger explosions and more health"
        );
        addItemWithDesc(builder, FRItems.JUNGLE_SPIDER_SPAWN_EGG.get(), "Jungle Spider Spawn Egg",
                null,
                "A tiny monster that moves fast and inflicts weakness"
        );
        addItemWithDesc(builder, FRItems.PUMPKIN_GOLEM_SPAWN_EGG.get(), "Pumpkin Golem Spawn Egg",
                null,
                "A mischievous golem that picks and replants most crops it comes across"
        );
        addItemWithDesc(builder, FRItems.CROW_SPAWN_EGG.get(), "Crow Spawn Egg",
                null,
                "A noisy, intelligent bird that's often attracted to shiny things"
        );
        addItemWithDesc(builder, FRItems.GOLDEN_CHICKEN_SPAWN_EGG.get(), "Golden Chicken Spawn Egg",
                null,
                "A golden variant of chicken that rarely lays golden eggs"
        );
        // Balls
        addItemWithDesc(builder, FRItems.BALL.get(), "Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.BOUNCY_BALL.get(), "Bouncy Ball",
                "A recreational item that can be thrown around. Bounces off of blocks up to 4 times.",
                "Now with built-in bounce action!"
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.WHITE).get(), "White Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), "Light Gray Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.GRAY).get(), "Gray Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.BLACK).get(), "Black Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.BROWN).get(), "Brown Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.RED).get(), "Red Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.ORANGE).get(), "Orange Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.YELLOW).get(), "Yellow Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.LIME).get(), "Lime Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.GREEN).get(), "Green Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.CYAN).get(), "Cyan Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), "Light Blue Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.BLUE).get(), "Blue Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.PURPLE).get(), "Purple Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.MAGENTA).get(), "Magenta Ball",
                null,
                ball_yt
        );
        addItemWithDesc(builder, FRItems.COLOR_BALLS.get(DyeColor.PINK).get(), "Pink Ball",
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
        addItemExtra(builder, FRItems.MUSIC_DISC_DIAPHRAGM.get(), desc, "Artyrian - Diaphragm");
        addItemExtra(builder, FRItems.TABLET_FRAGMENT.get(), desc, "Cursed Tablet");
        addItemExtra(builder, FRItems.TOWER_KEY_FRAGMENT.get(), desc, "Tower Key");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_PICKAXE.get(), desc, "Brimtan Pickaxe");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_AXE.get(), desc, "Brimtan Axe");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_SWORD.get(), desc, "Brimtan Sword");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_SHOVEL.get(), desc, "Brimtan Shovel");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_HOE.get(), desc, "Brimtan Hoe");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_HELMET.get(), desc, "Brimtan Helmet");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_CHESTPLATE.get(), desc, "Brimtan Chestplate");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_LEGGINGS.get(), desc, "Brimtan Leggings");
        addItemExtra(builder, FRItems.BRIMTAN_SHELL_BOOTS.get(), desc, "Brimtan Boots");
        // Cobalt Shield Colors
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "black", "Black Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "blue", "Blue Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "brown", "Brown Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "cyan", "Cyan Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "gray", "Gray Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "green", "Green Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "light_blue", "Light Blue Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "light_gray", "Light Gray Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "lime", "Lime Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "magenta", "Magenta Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "orange", "Orange Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "pink", "Pink Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "purple", "Purple Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "red", "Red Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "white", "White Cobalt Shield");
        addItemExtra(builder, FRItems.COBALT_SHIELD.get(), "yellow", "Yellow Cobalt Shield");
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
        addItemExtra(builder, FRItems.CHEST_KEY.get(), "named", "Assigned Chest Key");
        addRaw(builder, "item.frontiers.core_plate.header", "Combine with:");
        addRaw(builder, "item.frontiers.arrowhead.header", "Creates:");
        addRaw(builder, "item.frontiers.arrowhead.footer", "%1$s (x6)");
    }

    private void doEntity(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addEntity(builder, FREntity.SUBZERO_ARROW.get(), "Subzero Arrow");
        addEntity(builder, FREntity.WARP_ARROW.get(), "Warp Arrow");
        addEntity(builder, FREntity.DYNAMITE_ARROW.get(), "Dynamite Arrow");
        addEntity(builder, FREntity.BOUNCY_ARROW.get(), "Bouncy Arrow");
        addEntity(builder, FREntity.PRISMARINE_ARROW.get(), "Prismarine Arrow");

        addEntity(builder, FREntity.CRAGS_STALKER.get(), "§f§kMANIFEST§r");
        addEntity(builder, FREntity.CRAGS_MONSTER.get(), "§f§kENTITY§r");

        addEntity(builder, FREntity.BALL.get(), "Ball");
        addEntity(builder, FREntity.FRUITCAKE.get(), "Fruitcake");
        addEntity(builder, FREntity.MANA_ORB.get(), "Mana Orb");
        addEntity(builder, FREntity.MANA_BOTTLE.get(), "Bottle o' Magicks");
        addEntity(builder, FREntity.GOLDEN_EGG.get(), "Golden Egg");

        addEntity(builder, FREntity.CRAWLER.get(), "Crawler");
        addEntity(builder, FREntity.JUNGLE_SPIDER.get(), "Jungle Spider");
        addEntity(builder, FREntity.PUMPKIN_GOLEM.get(), "Pumpkin Golem");
        addEntity(builder, FREntity.CROW.get(), "Crow");
        addEntity(builder, FREntity.GOLDEN_CHICKEN.get(), "Golden Chicken");

        // Ball Displays
        String baller = FREntity.BALL.get().getDescriptionId();
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
        addEffect(builder, FRStatusEffects.QUICK_FLIGHT, "Quick Flight");
        addEffect(builder, FRStatusEffects.STORM_POISONING, "Storm Poisoning");
        addEffect(builder, FRStatusEffects.MAGMA_VISION, "Magma Vision");
        addEffect(builder, FRStatusEffects.ALLUREMENT, "Allurement");
        addEffect(builder, FRStatusEffects.WELL_RESTED, "Well-Rested");
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
        addAdv(builder, Frontiers.MOD_ID, "break_curse", "Unnatural Selection", "Remove an enchantment or curse on a Curse Altar");
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

        addAdv(builder, nether, "brew_lightning", "Taking It Literally", "Catch Lightning in a Bottle using a jury-rigged Brewing Stand");
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
                "exclaim", "%s . . . !",
                "level_cost", "Level Requirement: %s",
                "charge_cost", "Charge Requirement: %s",
                "cooldown", "Please wait..."
        );

        addContainer(builder, Frontiers.MOD_ID, "curse_altar", "Purify", curse_altar);
        addContainer(builder, Frontiers.MOD_ID, "fletching", "Create Arrows", null);
        addContainer(builder, Frontiers.MOD_ID, "personal_chest", "Personal Chest", null);
        addContainer(builder, Frontiers.MOD_ID, "bottled_message", null, bottled_message);
        addContainer(builder, Frontiers.MOD_ID, "monster_bakery", "Monster Bakery", bakery);
    }

    private void doSubtitles(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        addSubtitles(builder, VectorDatagen.CAPTIONS);
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
        addRaw(builder, "tag.item.frontiers.tomes", "Tomes");
        addRaw(builder, "tag.item.frontiers.golden_chicken_food", "Golden Chicken Food");
        addRaw(builder, "tag.item.frontiers.glowing_brimtan_items", "Glowing Brimtan Items");
        addRaw(builder, "tag.item.frontiers.item_vacuum_hearts", "Creates Heart Particles in Item Vacuum");
        addRaw(builder, "tag.item.frontiers.item_vacuum_living_fire", "Creates Living Fire in Item Vacuum (Dungeon's Delight)");
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

        addRaw(builder, "filled_map.frontiers.tower", "Tower Explorer Map");

        addRaw(builder, "tooltip.ranged.damage", "%s× Damage Multiplier");
        addRaw(builder, "tooltip.ranged.speed", "%s× Charge Speed");

        addRaw(builder, "deathScreen.frontiers.consumedTotem", "Totem of Avarice consumed and inventory kept.");

        addRaw(builder, "advancements.toast.frontier_adv", "Frontier Reached!");
        addRaw(builder, "chat.type.advancement.frontier_adv", "%s has reached the frontier %s");

        addRaw(builder, "block.frontiers.strange_core.incorrect", "Not the correct pattern!");
        addRaw(builder, "block.frontiers.strange_core.incorrect_funny", "...maybe try the Nether equivalents of these blocks?");
        addRaw(builder, "block.frontiers.strange_core.not_overworld", "This cannot be activated in this dimension.");
        addRaw(builder, "block.frontiers.strange_core.active", "Active!");

        addRaw(builder, "ui.frontiers.aprilfools.xdddddddd", "Minceraft Infdev (Real)");
        addRaw(builder, "ui.frontiers.aprilfools.type0", "Minecraft Infdev (Real)");
        addRaw(builder, "ui.frontiers.aprilfools.type1", "Minecraft Alpha 1.2.0_01");
        addRaw(builder, "ui.frontiers.aprilfools.type2", "Minecraft Gamma 1.2.3.4.5.6.7");
        addRaw(builder, "ui.frontiers.aprilfools.type3", "Minecraft but Awesome");
        addRaw(builder, "ui.frontiers.aprilfools.type4", "Minecraft in 2013");
        addRaw(builder, "ui.frontiers.aprilfools.type5", "Minecraft but it's dank lol!!! xD");
        addRaw(builder, "ui.frontiers.aprilfools.type6", "Minecraft: Order of the Stone");
        addRaw(builder, "ui.frontiers.aprilfools.type7", "Cave Game Tech Test");
        addRaw(builder, "ui.frontiers.aprilfools.type8", "rd-132211");
        addRaw(builder, "ui.frontiers.aprilfools.type9", "Minecraft if it was good");
        addRaw(builder, "ui.frontiers.aprilfools.type10", "Minecraft if Mojang locked in");
        addRaw(builder, "ui.frontiers.aprilfools.type11", "Minecraft 2");
        addRaw(builder, "ui.frontiers.aprilfools.type12", "Minecraft: Trouble in Paradise");
        addRaw(builder, "ui.frontiers.aprilfools.type13", "Minecraft: Electric Boogaloo");
        addRaw(builder, "ui.frontiers.aprilfools.type14", "Fortnite.exe");
        addRaw(builder, "ui.frontiers.aprilfools.type15", "Terraria 1.4.5.5");
        addRaw(builder, "ui.frontiers.aprilfools.type16", "UNDERTALE 1.08 (C) TOBY FOX 2015 - 2017");
        addRaw(builder, "ui.frontiers.aprilfools.type17", "[LiveLeak]");
        addRaw(builder, "ui.frontiers.aprilfools.type18", "missingno");
        addRaw(builder, "ui.frontiers.aprilfools.type19", "Minecraft: Demo Edition");
        addRaw(builder, "ui.frontiers.aprilfools.type20", "Hypertrig.exe");
        addRaw(builder, "ui.frontiers.aprilfools.type21_1", "Minecraft Alpha 1.2.0_01    Unlicensed Copy :(");
        addRaw(builder, "ui.frontiers.aprilfools.type21_2", "(Or logged in from another location)");
        addRaw(builder, "ui.frontiers.aprilfools.type21_3", "Purchase at minecraft.net");
        addRaw(builder, "ui.frontiers.aprilfools.type22", "The Amazing Digital Circus");
        addRaw(builder, "ui.frontiers.aprilfools.type23", "Unregistered Hypercam 2");

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
    }

    private void doRecipeViewer(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        // JEI
        addRaw(builder, "jei.category.frontiers.bakery_fuel.single", "1 mob attempt");
        addRaw(builder, "jei.category.frontiers.bakery_fuel.multi", "%s mob attempts");
        addRaw(builder, "jei.category.frontiers.monster_bakery.chance", "+%s%%");

        // EMI
        addRaw(builder, "emi.fuel_time.frontiers.mobs", "%s mob attempt(s)");
        addRaw(builder, "emi.fuel_time.frontiers.bakery_chance", "+%s%% chance");

        addRaw(builder, "emi.category.frontiers.fletching", "Fletching");
        addRaw(builder, "emi.category.frontiers.monster_bakery", "Monster Bakery");
        addRaw(builder, "emi.category.frontiers.bakery_fuel", "Fuel (Monster Bakery)");
    }
}
