package net.artyrian.frontiers.block;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.*;
import net.artyrian.frontiers.block.custom.models.*;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.misc.ModNoteBlockInstrument;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockPredicatesChecker;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.ToIntFunction;

// Registers all mod blocks (and their items) to Minecraft registries.
public class ModBlocks
{
    // BLOCK LIST. Gets lengthy.

    // Cobalt ore
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new HardmodeLockedExpBlock(
                    UniformIntProvider.create(0, 3),
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
            )
    );
    // Deepslate Cobalt ore
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new HardmodeLockedExpBlock(
                    UniformIntProvider.create(0, 3),
                    AbstractBlock.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
            )
    );
    // Block of Cobalt
    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.BLUE)
            )
    );
    // Verdinite ore
    public static final Block VERDINITE_ORE = registerBlock("verdinite_ore",
            new HardmodeLockedExpBlock(
                    UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
            )
    );
    // Deepslate Verdinite ore
    public static final Block DEEPSLATE_VERDINITE_ORE = registerBlock("deepslate_verdinite_ore",
            new HardmodeLockedExpBlock(
                    UniformIntProvider.create(2, 5),
                    AbstractBlock.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
            )
    );
    // Frostite Ore
    public static final Block FROSTITE_ORE = registerBlock("frostite_ore",
            new FrostiteOreBlock(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)
                            .mapColor(MapColor.PALE_PURPLE)
                            .sounds(BlockSoundGroup.GLASS)
                            .slipperiness(0.98F)
                            .ticksRandomly()
                            .nonOpaque()
            )
    );
    // Block of Frostite
    public static final Block FROSTITE_BLOCK = registerBlock("frostite_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)
                            .mapColor(MapColor.PALE_PURPLE)
                            .instrument(ModNoteBlockInstrument.FRONTIERS_ICE_BELL)
                            .slipperiness(0.98F)
            )
    );
    // Vivulite ore
    public static final Block VIVULITE_ORE = registerBlock("vivulite_ore",
            new HardmodeLockedExpBlock(
                    UniformIntProvider.create(3, 7),
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
            )
    );
    // Deepslate Vivulite ore
    public static final Block DEEPSLATE_VIVULITE_ORE = registerBlock("deepslate_vivulite_ore",
            new HardmodeLockedExpBlock(
                    UniformIntProvider.create(3, 7),
                    AbstractBlock.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
            )
    );
    // Block of Mourning Gold
    public static final Block MOURNING_GOLD_BLOCK = registerBlock("mourning_gold_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).instrument(ModNoteBlockInstrument.FRONTIERS_HARPSICHORD).mapColor(DyeColor.LIGHT_GRAY)
            )
    );
    // Glowing Obsidian
    public static final Block GLOWING_OBSIDIAN = registerBlock("glowing_obsidian",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.OBSIDIAN)
                            .mapColor(DyeColor.RED)
                            .luminance(state -> 12)
                            .requiresTool()
            )
    );
    // Strange Core (registered directly to change rarity).
    public static final Block STRANGE_CORE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "strange_core"), new NetherReactorBlockLol(
            AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .mapColor(DyeColor.BLUE)
                    .hardness(10.0F)
                    .luminance(strangeCoreLightHelper(5, 15))
                    .requiresTool()
    ));
    private static final Item STRANGE_CORE_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "strange_core"), new BlockItem(STRANGE_CORE, new Item.Settings().rarity(Rarity.RARE)));

    // Ancient Rose Seed (registered directly).
    public static final Block ANCIENT_ROSE_CROP = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "ancient_rose_crop"),
            new AncientRoseCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).mapColor(DyeColor.CYAN)
            )
    );
    // Ancient Rose
    public static final Block ANCIENT_ROSE = registerBlock("ancient_rose",
            new RoseFlowerBlock(ModBlocks.VIOLET_ROSE, StatusEffects.HUNGER, 20,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Potted Ancient Rose
    public static final Block POTTED_ANCIENT_ROSE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_ancient_rose"),
            new FlowerPotBlock(ANCIENT_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Ancient Rose Bush (registered directly to change rarity)
    public static final Block ANCIENT_ROSE_BUSH = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"), new TallFlowerBlock(
            (AbstractBlock.Settings.copy(Blocks.ROSE_BUSH))
    ));
    private static final Item ANCIENT_ROSE_BUSH_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"), new BlockItem(ANCIENT_ROSE_BUSH, new Item.Settings().rarity(Rarity.UNCOMMON)));

    // Rose
    public static final Block ROSE = registerBlock("rose",
            new RoseFlowerBlock(ModBlocks.VIOLET_ROSE, StatusEffects.HUNGER, 8,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Potted Rose
    public static final Block POTTED_ROSE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_rose"),
            new FlowerPotBlock(ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );

    // Violet Rose
    public static final Block VIOLET_ROSE = registerBlock("violet_rose",
            new FlowerBlock(StatusEffects.HUNGER, 20,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Potted Violet Rose
    public static final Block POTTED_VIOLET_ROSE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_violet_rose"),
            new FlowerPotBlock(VIOLET_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Violet Rose Bush (registered directly to change rarity)
    public static final Block VIOLET_ROSE_BUSH = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"), new TallFlowerBlock(
            (AbstractBlock.Settings.copy(Blocks.ROSE_BUSH))
    ));
    private static final Item VIOLET_ROSE_BUSH_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"), new BlockItem(VIOLET_ROSE_BUSH, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Tower Bricks
    public static final Block TOWER_BRICKS = registerBlock("tower_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool().strength(70.0F, 800.0F).pistonBehavior(PistonBehavior.BLOCK)
            )
    );
    // Mossy Tower Bricks
    public static final Block MOSSY_TOWER_BRICKS = registerBlock("mossy_tower_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool().strength(70.0F, 800.0F).pistonBehavior(PistonBehavior.BLOCK)
            )
    );
    // Nacre Brick family
    public static final Block NACRE_BRICKS = registerBlock("nacre_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.BRICKS).sounds(BlockSoundGroup.CALCITE).mapColor(MapColor.PALE_YELLOW)
            )
    );
    public static final Block NACRE_BRICK_STAIRS = registerBlock("nacre_brick_stairs", doStairs(NACRE_BRICKS));
    public static final Block NACRE_BRICK_SLAB = registerBlock("nacre_brick_slab", doSlab(NACRE_BRICKS));
    public static final Block NACRE_BRICK_WALL = registerBlock("nacre_brick_wall", doWall(NACRE_BRICKS));
    // Snow Dahlia
    public static final Block SNOW_DAHLIA = registerBlock("snow_dahlia",
            new FlowerBlock(StatusEffects.SLOWNESS, 4,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Fungal Daffodil
    public static final Block FUNGAL_DAFFODIL = registerBlock("fungal_daffodil",
            new SpecialPlaceableFlowerBlock(Blocks.MYCELIUM, StatusEffects.NAUSEA, 6,
                    AbstractBlock.Settings.copy(Blocks.POPPY).mapColor(MapColor.PALE_PURPLE).nonOpaque().noCollision())
    );
    // Crimcone
    public static final Block CRIMCONE = registerBlock("crimcone",
            new SpecialPlaceableFlowerBlock(Blocks.CRIMSON_NYLIUM, StatusEffects.MINING_FATIGUE, 10,
                    AbstractBlock.Settings.copy(Blocks.CRIMSON_FUNGUS).nonOpaque().noCollision())
    );
    // Potted Snow Dahlia
    public static final Block POTTED_SNOW_DAHLIA = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_snow_dahlia"),
            new FlowerPotBlock(SNOW_DAHLIA, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Potted Fungal Daffodil
    public static final Block POTTED_FUNGAL_DAFFODIL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_fungal_daffodil"),
            new FlowerPotBlock(FUNGAL_DAFFODIL, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Potted Crimcone
    public static final Block POTTED_CRIMCONE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_crimcone"),
            new FlowerPotBlock(CRIMCONE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Cragulstane
    public static final Block CRAGULSTANE = registerBlock("cragulstane",
            new Block(AbstractBlock.Settings.create()
                    .strength(10.0F, 800.0F)
                    .mapColor(MapColor.DULL_RED)
                    .requiresTool()
                    .instrument(ModNoteBlockInstrument.FRONTIERS_ROBOLUNG)
                    .sounds(ModBlockSoundGroups.CRAGULSTANE)
            )
    );
    // Cragulstane Brick family
    public static final Block CRAGULSTANE_BRICKS = registerBlock("cragulstane_bricks",
            new Block(AbstractBlock.Settings.copy(CRAGULSTANE)
            )
    );
    public static final Block CRAGULSTANE_BRICK_STAIRS = registerBlock("cragulstane_brick_stairs", doStairs(CRAGULSTANE_BRICKS));
    public static final Block CRAGULSTANE_BRICK_SLAB = registerBlock("cragulstane_brick_slab", doSlab(CRAGULSTANE_BRICKS));
    public static final Block CRAGULSTANE_BRICK_WALL = registerBlock("cragulstane_brick_wall", doWall(CRAGULSTANE_BRICKS));
    public static final Block CHISELED_CRAGULSTANE_BRICKS = registerBlock("chiseled_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRAGULSTANE)));
    // Aesthenostone
    public static final Block AESTHENOSTONE = registerBlock("aesthenostone",
            new CoreBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .luminance(state -> 3)
                    .emissiveLighting(Blocks::always)
            )
    );
    // Onyx Bone Block
    public static final Block ONYX_BONE_BLOCK = registerBlock("onyx_bone_block",
            new PillarBlock(
                    AbstractBlock.Settings.copy(Blocks.BONE_BLOCK).mapColor(MapColor.DEEPSLATE_GRAY)
            )
    );
    // All Corrupted Amethyst Clusters
    public static final Block CORRUPTED_AMETHYST_CLUSTER = registerBlock("corrupted_amethyst_cluster",
            new AmethystClusterBlock(7.0F, 3.0F, AbstractBlock.Settings.copy(Blocks.AMETHYST_CLUSTER))
    );
    public static final Block LARGE_CORRUPTED_AMETHYST_BUD = registerBlock("large_corrupted_amethyst_bud",
            new AmethystClusterBlock(5.0F, 3.0F, AbstractBlock.Settings.copy(CORRUPTED_AMETHYST_CLUSTER).sounds(BlockSoundGroup.MEDIUM_AMETHYST_BUD).luminance(state -> 4))
    );
    public static final Block MEDIUM_CORRUPTED_AMETHYST_BUD = registerBlock("medium_corrupted_amethyst_bud",
            new AmethystClusterBlock(4.0F, 3.0F, AbstractBlock.Settings.copy(CORRUPTED_AMETHYST_CLUSTER).sounds(BlockSoundGroup.LARGE_AMETHYST_BUD).luminance(state -> 2))
    );
    public static final Block SMALL_CORRUPTED_AMETHYST_BUD = registerBlock("small_corrupted_amethyst_bud",
            new AmethystClusterBlock(3.0F, 4.0F, AbstractBlock.Settings.copy(CORRUPTED_AMETHYST_CLUSTER).sounds(BlockSoundGroup.SMALL_AMETHYST_BUD).luminance(state -> 1))
    );
    // Quicksands
    public static final Block QUICKSAND = registerBlock("quicksand",
            new QuicksandBlock(
                    AbstractBlock.Settings.copy(Blocks.SAND)
                            .mapColor(MapColor.PALE_YELLOW)
                            .solid()
                            .noCollision()
                            .requiresTool()
                            .strength(1.0F)
                            .blockVision(Blocks::always)
            )
    );
    public static final Block RED_QUICKSAND = registerBlock("red_quicksand",
            new QuicksandBlock(AbstractBlock.Settings.copy(QUICKSAND).mapColor(MapColor.TERRACOTTA_ORANGE)));
    // Warped Wart
    public static final Block WARPED_WART = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "warped_wart"),
            new WarpedWartBlock(AbstractBlock.Settings.copy(Blocks.NETHER_WART).mapColor(MapColor.LIGHT_BLUE)));
    // Blue Nether Bricks (& co)
    public static final Block BLUE_NETHER_BRICKS = registerBlock("blue_nether_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.DARK_AQUA)
            )
    );
    public static final Block CRACKED_BLUE_NETHER_BRICKS = registerBlock("cracked_blue_nether_bricks", new Block(AbstractBlock.Settings.copy(BLUE_NETHER_BRICKS)));
    public static final Block CHISELED_BLUE_NETHER_BRICKS = registerBlock("chiseled_blue_nether_bricks", new Block(AbstractBlock.Settings.copy(BLUE_NETHER_BRICKS)));
    public static final Block BLUE_NETHER_BRICK_STAIRS = registerBlock("blue_nether_brick_stairs", doStairs(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_SLAB = registerBlock("blue_nether_brick_slab", doSlab(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_WALL = registerBlock("blue_nether_brick_wall", doWall(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_FENCE = registerBlock("blue_nether_brick_fence", doFence(BLUE_NETHER_BRICKS));
    public static final Block BLUE_NETHER_BRICK_FENCE_GATE = registerBlock("blue_nether_brick_fence_gate", doStoneGate(BLUE_NETHER_BRICKS));
    // Purple Nether Bricks (& co)
    public static final Block PURPLE_NETHER_BRICKS = registerBlock("purple_nether_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.PALE_PURPLE)
            )
    );
    public static final Block CRACKED_PURPLE_NETHER_BRICKS = registerBlock("cracked_purple_nether_bricks", new Block(AbstractBlock.Settings.copy(PURPLE_NETHER_BRICKS)));
    public static final Block CHISELED_PURPLE_NETHER_BRICKS = registerBlock("chiseled_purple_nether_bricks", new Block(AbstractBlock.Settings.copy(PURPLE_NETHER_BRICKS)));
    public static final Block PURPLE_NETHER_BRICK_STAIRS = registerBlock("purple_nether_brick_stairs", doStairs(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_SLAB = registerBlock("purple_nether_brick_slab", doSlab(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_WALL = registerBlock("purple_nether_brick_wall", doWall(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_FENCE = registerBlock("purple_nether_brick_fence", doFence(PURPLE_NETHER_BRICKS));
    public static final Block PURPLE_NETHER_BRICK_FENCE_GATE = registerBlock("purple_nether_brick_fence_gate", doStoneGate(PURPLE_NETHER_BRICKS));
    // Red Nether Brick Extras (because why tf not)
    public static final Block CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks", new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));
    public static final Block CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks", new Block(AbstractBlock.Settings.copy(Blocks.RED_NETHER_BRICKS)));
    public static final Block RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence", doFence(Blocks.RED_NETHER_BRICKS));
    public static final Block RED_NETHER_BRICK_FENCE_GATE = registerBlock("red_nether_brick_fence_gate", doStoneGate(Blocks.RED_NETHER_BRICKS));
    // Nether Brick Extras
    public static final Block NETHER_BRICK_FENCE_GATE = registerBlock("nether_brick_fence_gate", doStoneGate(Blocks.NETHER_BRICKS));

    // Lumens
    public static final Block DIAMOND_LUMEN = registerBlock("diamond_lumen",
            new LumenBlock(AbstractBlock.Settings.create()
                    .luminance(lumenLight(8, 15)).strength(0.3F).sounds(BlockSoundGroup.GLASS).
                    instrument(NoteBlockInstrument.PLING).allowsSpawning(Blocks::never).mapColor(MapColor.DIAMOND_BLUE)));
    public static final Block REDSTONE_LUMEN = registerBlock("redstone_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).luminance(lumenLight(4, 9)).mapColor(MapColor.RED)));
    public static final Block AMETHYST_LUMEN = registerBlock("amethyst_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.PURPLE)));
    public static final Block EMERALD_LUMEN = registerBlock("emerald_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.EMERALD_GREEN)));
    public static final Block QUARTZ_LUMEN = registerBlock("quartz_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.OFF_WHITE)));
    public static final Block COBALT_LUMEN = registerBlock("cobalt_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.BLUE)));
    public static final Block VERDINITE_LUMEN = registerBlock("verdinite_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.LIME)));
    public static final Block FROSTITE_LUMEN = registerBlock("frostite_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.PALE_PURPLE)));
    public static final Block VIVULITE_LUMEN = registerBlock("vivulite_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.RED)));
    public static final Block ECHO_LUMEN = registerBlock("echo_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).luminance(lumenLight(4, 9)).mapColor(MapColor.DARK_AQUA)));

    // Pale Prismarines
    public static final Block PALE_PRISMARINE = registerBlock("pale_prismarine",
            new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Block PALE_PRISMARINE_STAIRS = registerBlock("pale_prismarine_stairs", doStairs(PALE_PRISMARINE));
    public static final Block PALE_PRISMARINE_SLAB = registerBlock("pale_prismarine_slab", doSlab(PALE_PRISMARINE));
    public static final Block PALE_PRISMARINE_WALL = registerBlock("pale_prismarine_wall", doWall(PALE_PRISMARINE));

    public static final Block PALE_PRISMARINE_BRICKS = registerBlock("pale_prismarine_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Block PALE_PRISMARINE_BRICK_STAIRS = registerBlock("pale_prismarine_brick_stairs", doStairs(PALE_PRISMARINE_BRICKS));
    public static final Block PALE_PRISMARINE_BRICK_SLAB = registerBlock("pale_prismarine_brick_slab", doSlab(PALE_PRISMARINE_BRICKS));

    public static final Block DEEP_PALE_PRISMARINE = registerBlock("deep_pale_prismarine",
            new Block(AbstractBlock.Settings.copy(Blocks.DARK_PRISMARINE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Block DEEP_PALE_PRISMARINE_STAIRS = registerBlock("deep_pale_prismarine_stairs", doStairs(DEEP_PALE_PRISMARINE));
    public static final Block DEEP_PALE_PRISMARINE_SLAB = registerBlock("deep_pale_prismarine_slab", doSlab(DEEP_PALE_PRISMARINE));

    // Sea Glasses
    public static final Block SEA_GLASS = registerBlock("sea_glass",
            new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS)
                    .mapColor(MapColor.DIAMOND_BLUE)
                    .luminance(state -> 3)
                    .emissiveLighting(Blocks::always)
                    .instrument(ModNoteBlockInstrument.FRONTIERS_STEEL_DRUM)
            ));
    public static final Block SEA_GLASS_PANE = registerBlock("sea_glass_pane",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.GLASS_PANE)
                    .mapColor(MapColor.DIAMOND_BLUE)
                    .luminance(state -> 3)
                    .emissiveLighting(Blocks::always)
                    .instrument(ModNoteBlockInstrument.FRONTIERS_STEEL_DRUM)
            ));
    public static final Block PALE_SEA_GLASS = registerBlock("pale_sea_glass",
            new TransparentBlock(AbstractBlock.Settings.copy(SEA_GLASS).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Block PALE_SEA_GLASS_PANE = registerBlock("pale_sea_glass_pane",
            new PaneBlock(AbstractBlock.Settings.copy(SEA_GLASS_PANE).mapColor(MapColor.TERRACOTTA_BROWN)));

    // Turtle Scute Brick family
    public static final Block TURTLE_SCUTE_BRICKS = registerBlock("turtle_scute_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)
                    .instrument(ModNoteBlockInstrument.FRONTIERS_LOG_DRUM)
                    .mapColor(MapColor.PALE_GREEN)));
    public static final Block TURTLE_SCUTE_BRICK_STAIRS = registerBlock("turtle_scute_brick_stairs", doStairs(TURTLE_SCUTE_BRICKS));
    public static final Block TURTLE_SCUTE_BRICK_SLAB = registerBlock("turtle_scute_brick_slab", doSlab(TURTLE_SCUTE_BRICKS));
    public static final Block TURTLE_SCUTE_BRICK_WALL = registerBlock("turtle_scute_brick_wall", doWall(TURTLE_SCUTE_BRICKS));

    // Mob Models (registered in parts for item rarities)
    // Creeper
    public static final Block CREEPER_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "creeper_model"),
            new CreeperModelBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.OAK_TAN)
                    .strength(2.0F, 5.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .nonOpaque())
            );
    private static final Item CREEPER_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "creeper_model"),
            new BlockItem(CREEPER_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Skeleton
    public static final Block SKELETON_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "skeleton_model"), new SkeletonModelBlock(
            AbstractBlock.Settings.copy(CREEPER_MODEL)));
    private static final Item SKELETON_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "skeleton_model"),
            new BlockItem(SKELETON_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Stray
    public static final Block STRAY_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "stray_model"), new StrayModelBlock(
            AbstractBlock.Settings.copy(SKELETON_MODEL)));
    private static final Item STRAY_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "stray_model"),
            new BlockItem(STRAY_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Bogged
    public static final Block BOGGED_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "bogged_model"), new BoggedModelBlock(
            AbstractBlock.Settings.copy(SKELETON_MODEL)));
    private static final Item BOGGED_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "bogged_model"),
            new BlockItem(BOGGED_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Blaze
    public static final Block BLAZE_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "blaze_model"), new BlazeModelBlock(
            AbstractBlock.Settings.copy(CREEPER_MODEL).luminance(blazeModelLight(0, 12))));
    private static final Item BLAZE_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "blaze_model"),
            new BlockItem(BLAZE_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Wither Skeleton
    public static final Block WITHER_SKELETON_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "wither_skeleton_model"), new WitherSkeletonModelBlock(
            AbstractBlock.Settings.copy(CREEPER_MODEL)));
    private static final Item WITHER_SKELETON_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "wither_skeleton_model"),
            new BlockItem(WITHER_SKELETON_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Enderman
    public static final Block ENDERMAN_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "enderman_model"), new EndermanModelBlock(
            AbstractBlock.Settings.copy(CREEPER_MODEL)));
    private static final Item ENDERMAN_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "enderman_model"),
            new BlockItem(ENDERMAN_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Slime
    public static final Block SLIME_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "slime_model"), new SlimeModelBlock(
            AbstractBlock.Settings.copy(CREEPER_MODEL)));
    private static final Item SLIME_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "slime_model"),
            new BlockItem(SLIME_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Magma Cube
    public static final Block MAGMA_CUBE_MODEL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "magma_cube_model"), new MagmaCubeModelBlock(
            AbstractBlock.Settings.copy(CREEPER_MODEL)));
    private static final Item MAGMA_CUBE_MODEL_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "magma_cube_model"),
            new BlockItem(MAGMA_CUBE_MODEL, new Item.Settings().rarity(Rarity.UNCOMMON)));


    // #############################################################################
    // Helpers

    // Get light based on the Mod Property ACTIVE_POWER.
    public static ToIntFunction<BlockState> strangeCoreLightHelper(int litLevel1, int litLevel2)
    {

        return state -> switch (state.get(ModBlockProperties.ACTIVE_POWER))
        {
            case 0 -> litLevel1;
            case 1 -> litLevel2;
            case 2 -> 0;
            default -> litLevel1;
        };
    }

    // Does lighting for Lumen blocks.
    public static ToIntFunction<BlockState> lumenLight(int power1, int power2)
    {
        return state -> switch (state.get(ModBlockProperties.LUMEN_POWER))
        {
            case 0 -> 0;
            case 1 -> power1;
            case 2 -> power2;
            default -> 0;
        };
    }

    // Does lighting for Blaze Model.
    public static ToIntFunction<BlockState> blazeModelLight(int power1, int power2)
    {
        return state -> (state.get(ModBlockProperties.MODEL_POWERED)) ? power2 : power1;
    }

    // Helper class for common blocks
    public static Block doStairs(Block type) { return new StairsBlock(type.getDefaultState(), AbstractBlock.Settings.copy(type)); }
    public static Block doSlab(Block type) { return new SlabBlock(AbstractBlock.Settings.copy(type)); }
    public static Block doFence(Block type) { return new FenceBlock(AbstractBlock.Settings.copy(type)); }
    public static Block doWall(Block type) { return new WallBlock(AbstractBlock.Settings.copy(type).solid()); }
    public static Block doStoneGate(Block type) { return new StoneFenceGateBlock(AbstractBlock.Settings.copy(type)); }

    // #############################################################################

    // Registers both the Block and Item to their respective Minecraft registry.
    private static Block registerBlock(String name, Block block)
    {
        doBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    // Creates the block item - used alongside registerBlock() to make both in same method.
    private static Item doBlockItem(String name, Block block)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    // Registers mod blocks. Just sends a log message.
    public static void registerModBlocks()
    {
        //Frontiers.LOGGER.info("Registering Mod Blocks for " + Frontiers.MOD_ID);
    }
}
