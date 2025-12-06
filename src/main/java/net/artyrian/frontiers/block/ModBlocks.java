package net.artyrian.frontiers.block;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.*;
import net.artyrian.frontiers.block.custom.models.*;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.misc.ModBlockset;
import net.artyrian.frontiers.misc.ModNoteBlockInstrument;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import net.artyrian.frontiers.world.gen.ModSaplingGen;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Direction;
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
    // Block of Raw Cobalt
    public static final Block RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.DIAMOND_BLUE)
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
    // Block of Verdinite
    public static final Block VERDINITE_BLOCK = registerBlock("verdinite_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.LIME)
            )
    );
    // Block of Raw Verdinite
    public static final Block RAW_VERDINITE_BLOCK = registerBlock("raw_verdinite_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.TERRACOTTA_LIME)
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
    // Block of Raw Verdinite
    public static final Block RAW_FROSTITE_BLOCK = registerBlock("raw_frostite_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK)
                            .mapColor(MapColor.LIGHT_BLUE_GRAY)
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
    // Block of Vivulite
    public static final Block VIVULITE_BLOCK = registerBlock("vivulite_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.RED)
            )
    );
    // Block of Raw Vivulite
    public static final Block RAW_VIVULITE_BLOCK = registerBlock("raw_vivulite_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.MAGENTA)
            )
    );
    // Block of Mourning Gold
    public static final Block MOURNING_GOLD_BLOCK = registerBlock("mourning_gold_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).instrument(ModNoteBlockInstrument.FRONTIERS_HARPSICHORD).mapColor(DyeColor.LIGHT_GRAY)
            )
    );
    // Block of Necro Weave
    public static final Block NECRO_WEAVE_BLOCK = registerBlock("necro_weave_block",
            new NecroWeaveBlock(
                    AbstractBlock.Settings.create()
                            .instrument(NoteBlockInstrument.GUITAR)
                            .sounds(BlockSoundGroup.WOOL)
                            .strength(2.0F, 4.0F)
                            .mapColor(DyeColor.CYAN)
            )
    );
    public static final Block NECRO_RUG = registerBlock("necro_rug",
            new NecroCarpetBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.CYAN)
                            .strength(0.1F)
                            .sounds(BlockSoundGroup.WOOL)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    // Glowing Obsidian
    public static final Block GLOWING_OBSIDIAN = registerBlock("glowing_obsidian",
            new UnbreakableInDimensionBlock(
                    ModDimension.CRAGS_LEVEL_KEY,
                    AbstractBlock.Settings.copy(Blocks.OBSIDIAN)
                            .mapColor(DyeColor.RED)
                            .luminance(state -> 12)
                            .requiresTool()
            )
    );
    // Strange Core
    public static final Block STRANGE_CORE = registerBlock("strange_core", new NetherReactorBlockLol(
            AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .mapColor(DyeColor.BLUE)
                    .hardness(10.0F)
                    .luminance(strangeCoreLightHelper(5, 15))
                    .requiresTool()),
            new Item.Settings().rarity(Rarity.RARE)
    );
    // Enchanting Magnet
    public static final Block ENCHANTING_MAGNET = registerBlock("enchanting_magnet",
            new EnchantingMagnetBlock(AbstractBlock.Settings.copy(Blocks.BEACON).mapColor(MapColor.EMERALD_GREEN).luminance(state -> 4)),
            new Item.Settings().rarity(Rarity.RARE)
    );
    // Item Vacuum
    public static final Block ITEM_VACUUM = registerBlock("item_vacuum",
            new ItemVacuumBlock(AbstractBlock.Settings.copy(Blocks.SPAWNER).strength(3.0F, 5.0F).nonOpaque()),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );

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
    // Ancient Rose Bush
    public static final Block ANCIENT_ROSE_BUSH = registerBlock("ancient_rose_bush",
            new TallFlowerBlock((AbstractBlock.Settings.copy(Blocks.ROSE_BUSH))),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );

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
    // Violet Rose Bush
    public static final Block VIOLET_ROSE_BUSH = registerBlock("violet_rose_bush",
            new TallFlowerBlock((AbstractBlock.Settings.copy(Blocks.ROSE_BUSH))),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Tower Bricks family
    public static final Block TOWER_BRICKS = registerBlock("tower_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.CALCITE)
                            .requiresTool()
                            .strength(70.0F, 800.0F)
                            .pistonBehavior(PistonBehavior.BLOCK)
                            .instrument(ModNoteBlockInstrument.FRONTIERS_JESKOLA)
            )
    );
    public static final Block TOWER_BRICK_STAIRS = registerBlock("tower_brick_stairs", doStairs(TOWER_BRICKS));
    public static final Block TOWER_BRICK_SLAB = registerBlock("tower_brick_slab", doSlab(TOWER_BRICKS));
    public static final Block TOWER_BRICK_WALL = registerBlock("tower_brick_wall", doWall(TOWER_BRICKS));
    public static final Block MOSSY_TOWER_BRICKS = registerBlock("mossy_tower_bricks", new Block(AbstractBlock.Settings.copy(TOWER_BRICKS)));
    public static final Block MOSSY_TOWER_BRICK_STAIRS = registerBlock("mossy_tower_brick_stairs", doStairs(MOSSY_TOWER_BRICKS));
    public static final Block MOSSY_TOWER_BRICK_SLAB = registerBlock("mossy_tower_brick_slab", doSlab(MOSSY_TOWER_BRICKS));
    public static final Block MOSSY_TOWER_BRICK_WALL = registerBlock("mossy_tower_brick_wall", doWall(MOSSY_TOWER_BRICKS));
    // Tower Watcher
    public static final Block TOWER_WATCHER = registerBlock("tower_watcher",
            new TowerWatcherBlock(AbstractBlock.Settings.copy(TOWER_BRICKS)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
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
    // Fungal Daffodil Block
    public static final Block FUNGAL_DAFFODIL_BLOCK = registerBlock(
            "fungal_daffodil_block",
            new MushroomBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED).instrument(NoteBlockInstrument.BASS).slipperiness(0.9f).strength(0.2f).sounds(BlockSoundGroup.WOOD).burnable())
    );
    // Crimcone
    public static final Block CRIMCONE = registerBlock("crimcone",
            new SpecialPlaceableFlowerBlock(Blocks.CRIMSON_NYLIUM, StatusEffects.MINING_FATIGUE, 10,
                    AbstractBlock.Settings.copy(Blocks.CRIMSON_FUNGUS).nonOpaque().noCollision())
    );
    // Experiwinkle
    public static final Block EXPERIWINKLE = registerBlock("experiwinkle",
            new ExperiwinkleBlock(StatusEffects.LUCK, 15,
                    AbstractBlock.Settings.copy(Blocks.POPPY)
                            .nonOpaque()
                            .noCollision()
                            .luminance(state -> 3)
                            .emissiveLighting(Blocks::always)
            ),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Experiwinkle Crop
    public static final Block EXPERIWINKLE_CROP = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "experiwinkle_crop"),
            new ExperiwinkleCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).mapColor(DyeColor.LIME)
                    .luminance(state -> state.get(ExperiwinkleCropBlock.AGE) + 1)
                    .emissiveLighting(Blocks::always)
            )
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
    // Potted Experiwinkle
    public static final Block POTTED_EXPERIWINKLE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_experiwinkle"),
            new FlowerPotBlock(EXPERIWINKLE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY)
                    .nonOpaque()
                    .luminance(state -> 3)
                    .emissiveLighting(Blocks::always)
            )
    );
    // Sugar Cane Block
    public static final Block SUGAR_CANE_BLOCK = registerBlock(
            "sugar_cane_block",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.HAY_BLOCK)
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.5F)
                    .sounds(BlockSoundGroup.GRASS)
            )
    );
    // Cocoa Bean Block
    public static final Block COCOA_BEAN_BLOCK = registerBlock(
            "cocoa_bean_block",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .strength(0.5F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD)
            )
    );
    // Slime Trail
    public static final Block SLIME_TRAIL = registerBlock(
            "slime_trail",
            new SlimeTrailBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LIME)
                            .solid()
                            .noCollision()
                            .strength(0.2F)
                            .slipperiness(0.6F)
                            .sounds(BlockSoundGroup.SLIME)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    // Slime Bulb
    public static final Block SLIME_BULB = registerBlock(
            "slime_bulb",
            new SlimeBulbBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LIME)
                            .solid()
                            .ticksRandomly()
                            .noCollision()
                            .strength(2.0F)
                            .sounds(BlockSoundGroup.SLIME)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    // Cragulstane
    public static final Block CRAGULSTANE = registerBlock("cragulstane",
            new Block(AbstractBlock.Settings.create()
                    .strength(10.0F, 800.0F)
                    .mapColor(MapColor.DULL_RED)
                    .requiresTool()
                    .instrument(ModNoteBlockInstrument.FRONTIERS_ROBOLUNG)
                    .sounds(ModBlockSoundGroups.CRAGULSTANE)
                    .allowsSpawning((state, world, pos, entityType) -> entityType == ModEntity.CRAGS_STALKER)
            )
    );
    // Brimtan Ore
    public static final Block BRIMTAN_ORE = registerBlock("brimtan_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(4, 8),
                    AbstractBlock.Settings.copy(CRAGULSTANE)
            ),
            new Item.Settings().fireproof()
    );
    // Block of Brimtan
    public static final Block BRIMTAN_BLOCK = registerBlock("brimtan_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)
                            .mapColor(DyeColor.ORANGE)
                            .requiresTool()
                            .strength(60.0F, 1200.0F)
                            .sounds(BlockSoundGroup.NETHERITE)
            ),
            new Item.Settings().fireproof()
    );
    // Cobalt Grilles
    public static final Block COBALT_GRILLES = registerBlock("cobalt_grilles",
            new PaneBlock(AbstractBlock.Settings.copy(Blocks.IRON_BARS))
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
    public static final Block CRACKED_CRAGULSTANE_BRICKS = registerBlock("cracked_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRAGULSTANE)));
    // Brimmed Cragulstane Brick family
    public static final Block BRIMMED_CRAGULSTANE_BRICKS = registerBlock("brimmed_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRAGULSTANE_BRICKS)));
    public static final Block BRIMMED_CRAGULSTANE_BRICK_STAIRS = registerBlock("brimmed_cragulstane_brick_stairs", doStairs(BRIMMED_CRAGULSTANE_BRICKS));
    public static final Block BRIMMED_CRAGULSTANE_BRICK_SLAB = registerBlock("brimmed_cragulstane_brick_slab", doSlab(BRIMMED_CRAGULSTANE_BRICKS));
    public static final Block BRIMMED_CRAGULSTANE_BRICK_WALL = registerBlock("brimmed_cragulstane_brick_wall", doWall(BRIMMED_CRAGULSTANE_BRICKS));
    public static final Block CHISELED_BRIMMED_CRAGULSTANE_BRICKS = registerBlock("chiseled_brimmed_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CHISELED_CRAGULSTANE_BRICKS)));
    public static final Block CRACKED_BRIMMED_CRAGULSTANE_BRICKS = registerBlock("cracked_brimmed_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRACKED_CRAGULSTANE_BRICKS)));
    // Orange Cragulstane Brick family
    public static final Block ORANGE_CRAGULSTANE_BRICKS = registerBlock("orange_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRAGULSTANE_BRICKS)));
    public static final Block ORANGE_CRAGULSTANE_BRICK_STAIRS = registerBlock("orange_cragulstane_brick_stairs", doStairs(ORANGE_CRAGULSTANE_BRICKS));
    public static final Block ORANGE_CRAGULSTANE_BRICK_SLAB = registerBlock("orange_cragulstane_brick_slab", doSlab(ORANGE_CRAGULSTANE_BRICKS));
    public static final Block ORANGE_CRAGULSTANE_BRICK_WALL = registerBlock("orange_cragulstane_brick_wall", doWall(ORANGE_CRAGULSTANE_BRICKS));
    public static final Block CHISELED_ORANGE_CRAGULSTANE_BRICKS = registerBlock("chiseled_orange_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CHISELED_CRAGULSTANE_BRICKS)));
    public static final Block CRACKED_ORANGE_CRAGULSTANE_BRICKS = registerBlock("cracked_orange_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRACKED_CRAGULSTANE_BRICKS)));
    // Tyrian Cragulstane Brick family
    public static final Block TYRIAN_CRAGULSTANE_BRICKS = registerBlock("tyrian_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRAGULSTANE_BRICKS)));
    public static final Block TYRIAN_CRAGULSTANE_BRICK_STAIRS = registerBlock("tyrian_cragulstane_brick_stairs", doStairs(TYRIAN_CRAGULSTANE_BRICKS));
    public static final Block TYRIAN_CRAGULSTANE_BRICK_SLAB = registerBlock("tyrian_cragulstane_brick_slab", doSlab(TYRIAN_CRAGULSTANE_BRICKS));
    public static final Block TYRIAN_CRAGULSTANE_BRICK_WALL = registerBlock("tyrian_cragulstane_brick_wall", doWall(TYRIAN_CRAGULSTANE_BRICKS));
    public static final Block CHISELED_TYRIAN_CRAGULSTANE_BRICKS = registerBlock("chiseled_tyrian_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CHISELED_CRAGULSTANE_BRICKS)));
    public static final Block CRACKED_TYRIAN_CRAGULSTANE_BRICKS = registerBlock("cracked_tyrian_cragulstane_bricks", new Block(AbstractBlock.Settings.copy(CRACKED_CRAGULSTANE_BRICKS)));
    // Aesthenostone
    public static final Block AESTHENOSTONE = registerBlock("aesthenostone",
            new CoreBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .luminance(state -> 3)
                    .emissiveLighting(Blocks::always)
            )
    );
    // Eboncork Blocks
    public static final Block EBONCORK = registerBlock("eboncork",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DIRT_BROWN)
                    .strength(3.0F)
                    .sounds(BlockSoundGroup.NETHER_WOOD))
    );
    public static final Block EBONCORK_PLANKS = registerBlock("eboncork_planks",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DIRT_BROWN)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(3.0F, 4.0F)
                            .sounds(BlockSoundGroup.NETHER_WOOD)
            )
    );
    public static final Block EBONCORK_SLAB = registerBlock("eboncork_slab", doSlab(EBONCORK_PLANKS));
    public static final Block EBONCORK_STAIRS = registerBlock("eboncork_stairs", doStairs(EBONCORK_PLANKS));
    public static final Block EBONCORK_FENCE = registerBlock("eboncork_fence", doFence(EBONCORK_PLANKS));
    public static final Block EBONCORK_FENCE_GATE = registerBlock("eboncork_fence_gate", doWoodGate(ModBlockset.WoodSet.EBONCORK, EBONCORK_PLANKS));
    public static final Block EBONCORK_BUTTON = registerBlock("eboncork_button", Blocks.createWoodenButtonBlock(ModBlockset.BlockSet.EBONCORK));
    public static final Block EBONCORK_PRESSURE_PLATE = registerBlock(
            "eboncork_pressure_plate",
            new PressurePlateBlock(
                    ModBlockset.BlockSet.EBONCORK,
                    AbstractBlock.Settings.create()
                            .mapColor(EBONCORK_PLANKS.getDefaultMapColor())
                            .solid()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollision()
                            .strength(0.5F)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block EBONCORK_DOOR = registerBlockNoItem(
            "eboncork_door",
            new DoorBlock(
                    ModBlockset.BlockSet.EBONCORK,
                    AbstractBlock.Settings.create()
                            .mapColor(EBONCORK_PLANKS.getDefaultMapColor())
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(3.0F, 4.0F)
                            .nonOpaque()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block EBONCORK_TRAPDOOR = registerBlock(
            "eboncork_trapdoor",
            new TrapdoorBlock(
                    ModBlockset.BlockSet.EBONCORK,
                    AbstractBlock.Settings.create()
                            .mapColor(EBONCORK_PLANKS.getDefaultMapColor())
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(3.0F, 4.0F)
                            .nonOpaque()
                            .allowsSpawning(Blocks::never)
            )
    );

    // Blighted Birch Blocks
    public static final Block RADIANT_BLIGHTED_BIRCH_LOG = registerBlock("radiant_blighted_birch_log", createBlightedLog(
            false, "frontiers:sullen_blighted_birch_log"));
    public static final Block RADIANT_BLIGHTED_BIRCH_WOOD = registerBlock("radiant_blighted_birch_wood", createBlightedWood());
    public static final Block SULLEN_BLIGHTED_BIRCH_LOG = registerBlock("sullen_blighted_birch_log", createBlightedLog(
            true, "frontiers:radiant_blighted_birch_log"));
    public static final Block SULLEN_BLIGHTED_BIRCH_WOOD = registerBlock("sullen_blighted_birch_wood", createBlightedWood());
    public static final Block STRIPPED_BLIGHTED_BIRCH_LOG = registerBlock("stripped_blighted_birch_log",
            Blocks.createLogBlock(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_PURPLE));
    public static final Block STRIPPED_BLIGHTED_BIRCH_WOOD = registerBlock("stripped_blighted_birch_wood",
            new PillarBlock(
                    AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()
            )
    );
    public static final Block BLIGHTED_BIRCH_LEAVES = registerBlock("blighted_birch_leaves", Blocks.createLeavesBlock(BlockSoundGroup.GRASS));
    public static final Block BLIGHTED_BIRCH_PLANKS = registerBlock("blighted_birch_planks",
            new Block(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.TERRACOTTA_PURPLE)
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(2.0F, 3.0F)
                            .sounds(BlockSoundGroup.WOOD)
            )
    );
    public static final Block BLIGHTED_BIRCH_SLAB = registerBlock("blighted_birch_slab", doSlab(BLIGHTED_BIRCH_PLANKS));
    public static final Block BLIGHTED_BIRCH_STAIRS = registerBlock("blighted_birch_stairs", doStairs(BLIGHTED_BIRCH_PLANKS));
    public static final Block BLIGHTED_BIRCH_FENCE = registerBlock("blighted_birch_fence", doFence(BLIGHTED_BIRCH_PLANKS));
    public static final Block BLIGHTED_BIRCH_FENCE_GATE = registerBlock("blighted_birch_fence_gate", doWoodGate(ModBlockset.WoodSet.BLIGHTED_BIRCH, BLIGHTED_BIRCH_PLANKS));
    public static final Block BLIGHTED_BIRCH_BUTTON = registerBlock("blighted_birch_button", Blocks.createWoodenButtonBlock(ModBlockset.BlockSet.BLIGHTED_BIRCH));
    public static final Block BLIGHTED_BIRCH_PRESSURE_PLATE = registerBlock(
            "blighted_birch_pressure_plate",
            new PressurePlateBlock(
                    ModBlockset.BlockSet.BLIGHTED_BIRCH,
                    AbstractBlock.Settings.create()
                            .mapColor(BLIGHTED_BIRCH_PLANKS.getDefaultMapColor())
                            .solid()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollision()
                            .strength(0.5F)
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block BLIGHTED_BIRCH_DOOR = registerBlockNoItem(
            "blighted_birch_door",
            new DoorBlock(
                    ModBlockset.BlockSet.BLIGHTED_BIRCH,
                    AbstractBlock.Settings.create()
                            .mapColor(BLIGHTED_BIRCH_PLANKS.getDefaultMapColor())
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(3.0F)
                            .nonOpaque()
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block BLIGHTED_BIRCH_TRAPDOOR = registerBlock(
            "blighted_birch_trapdoor",
            new TrapdoorBlock(
                    ModBlockset.BlockSet.BLIGHTED_BIRCH,
                    AbstractBlock.Settings.create()
                            .mapColor(BLIGHTED_BIRCH_PLANKS.getDefaultMapColor())
                            .instrument(NoteBlockInstrument.BASS)
                            .strength(3.0F)
                            .nonOpaque()
                            .burnable()
                            .allowsSpawning(Blocks::never)
            )
    );

    // Blighted Birch Sapling + pot
    public static final Block BLIGHTED_BIRCH_SAPLING = registerBlock(
            "blighted_birch_sapling",
            new SaplingBlock(
                    ModSaplingGen.BLIGHTED_BIRCH,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.TERRACOTTA_PURPLE)
                            .noCollision()
                            .ticksRandomly()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block POTTED_BLIGHTED_BIRCH_SAPLING = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_blighted_birch_sapling"),
            new FlowerPotBlock(BLIGHTED_BIRCH_SAPLING, AbstractBlock.Settings.copy(Blocks.POTTED_BIRCH_SAPLING).nonOpaque())
    );

    // Wreaths
    public static final Block OAK_WREATH = registerBlock("oak_wreath", createWreath(Blocks.OAK_LEAVES));
    public static final Block DARK_OAK_WREATH = registerBlock("dark_oak_wreath", createWreath(Blocks.DARK_OAK_LEAVES));
    public static final Block BIRCH_WREATH = registerBlock("birch_wreath", createWreath(Blocks.BIRCH_LEAVES));
    public static final Block SPRUCE_WREATH = registerBlock("spruce_wreath", createWreath(Blocks.SPRUCE_LEAVES));
    public static final Block JUNGLE_WREATH = registerBlock("jungle_wreath", createWreath(Blocks.JUNGLE_LEAVES));
    public static final Block ACACIA_WREATH = registerBlock("acacia_wreath", createWreath(Blocks.ACACIA_LEAVES));
    public static final Block MANGROVE_WREATH = registerBlock("mangrove_wreath", createWreath(Blocks.MANGROVE_LEAVES));
    public static final Block AZALEA_WREATH = registerBlock("azalea_wreath", createWreathExt(AbstractBlock.Settings.copy(Blocks.FLOWERING_AZALEA_LEAVES).luminance(block -> 14)));
    public static final Block CHERRY_WREATH = registerBlock("cherry_wreath", createWreath(Blocks.CHERRY_LEAVES));
    public static final Block BLIGHTED_BIRCH_WREATH = registerBlock("blighted_birch_wreath", createWreath(BLIGHTED_BIRCH_LEAVES));

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
    public static final Block BRIMTAN_LUMEN = registerBlock("brimtan_lumen",
            new LumenBlock(AbstractBlock.Settings.copy(DIAMOND_LUMEN).mapColor(MapColor.ORANGE)),
            new Item.Settings().fireproof()
    );
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

    // Mob Models
    // Creeper
    public static final Block CREEPER_MODEL = registerBlock("creeper_model",
            new CreeperModelBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.OAK_TAN)
                    .strength(2.0F, 5.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .nonOpaque()),
            new Item.Settings().rarity(Rarity.UNCOMMON)
            );
    // Skeleton
    public static final Block SKELETON_MODEL = registerBlock("skeleton_model",
            new SkeletonModelBlock(AbstractBlock.Settings.copy(CREEPER_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Stray
    public static final Block STRAY_MODEL = registerBlock("stray_model",
            new StrayModelBlock(AbstractBlock.Settings.copy(SKELETON_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Bogged
    public static final Block BOGGED_MODEL = registerBlock("bogged_model",
            new BoggedModelBlock(AbstractBlock.Settings.copy(SKELETON_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Blaze
    public static final Block BLAZE_MODEL = registerBlock("blaze_model",
            new BlazeModelBlock(AbstractBlock.Settings.copy(CREEPER_MODEL).luminance(blazeModelLight(0, 12))),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Wither Skeleton
    public static final Block WITHER_SKELETON_MODEL = registerBlock("wither_skeleton_model",
            new WitherSkeletonModelBlock(AbstractBlock.Settings.copy(CREEPER_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Enderman
    public static final Block ENDERMAN_MODEL = registerBlock("enderman_model",
            new EndermanModelBlock(AbstractBlock.Settings.copy(CREEPER_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Slime
    public static final Block SLIME_MODEL = registerBlock("slime_model",
            new SlimeModelBlock(AbstractBlock.Settings.copy(CREEPER_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );
    // Magma Cube
    public static final Block MAGMA_CUBE_MODEL = registerBlock("magma_cube_model",
            new MagmaCubeModelBlock(AbstractBlock.Settings.copy(CREEPER_MODEL)),
            new Item.Settings().rarity(Rarity.UNCOMMON)
    );

    // Hielostone
    public static final Block HIELOSTONE = registerBlock("hielostone",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE)
                    .strength(2.0F, 5.0F)
                    .mapColor(MapColor.PALE_PURPLE)
            )
    );
    public static final Block HIELOSTONE_STAIRS = registerBlock("hielostone_stairs", doStairs(HIELOSTONE));
    public static final Block HIELOSTONE_SLAB = registerBlock("hielostone_slab", doSlab(HIELOSTONE));
    public static final Block HIELOSTONE_WALL = registerBlock("hielostone_wall", doWall(HIELOSTONE));
    // Hielostone Tiles
    public static final Block HIELOSTONE_TILES = registerBlock("hielostone_tiles", new Block(AbstractBlock.Settings.copy(HIELOSTONE)));
    public static final Block HIELOSTONE_TILE_STAIRS = registerBlock("hielostone_tile_stairs", doStairs(HIELOSTONE_TILES));
    public static final Block HIELOSTONE_TILE_SLAB = registerBlock("hielostone_tile_slab", doSlab(HIELOSTONE_TILES));
    public static final Block HIELOSTONE_TILE_WALL = registerBlock("hielostone_tile_wall", doWall(HIELOSTONE_TILES));
    // Hielostone Bricks
    public static final Block HIELOSTONE_BRICKS = registerBlock("hielostone_bricks", new Block(AbstractBlock.Settings.copy(HIELOSTONE)));
    public static final Block HIELOSTONE_BRICK_STAIRS = registerBlock("hielostone_brick_stairs", doStairs(HIELOSTONE_BRICKS));
    public static final Block HIELOSTONE_BRICK_SLAB = registerBlock("hielostone_brick_slab", doSlab(HIELOSTONE_BRICKS));
    public static final Block HIELOSTONE_BRICK_WALL = registerBlock("hielostone_brick_wall", doWall(HIELOSTONE_BRICKS));
    // Hielostone Plates
    public static final Block HIELOSTONE_PLATES = registerBlock("hielostone_plates", new Block(AbstractBlock.Settings.copy(HIELOSTONE)));
    public static final Block HIELOSTONE_PLATE_STAIRS = registerBlock("hielostone_plate_stairs", doStairs(HIELOSTONE_PLATES));
    public static final Block HIELOSTONE_PLATE_SLAB = registerBlock("hielostone_plate_slab", doSlab(HIELOSTONE_PLATES));
    public static final Block HIELOSTONE_PLATE_WALL = registerBlock("hielostone_plate_wall", doWall(HIELOSTONE_PLATES));
    // Cobblefrost
    public static final Block COBBLEFROST = registerBlock("cobblefrost",
            new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)
                    .strength(2.5F, 5.0F)
                    .mapColor(MapColor.PALE_PURPLE)
            )
    );
    public static final Block COBBLEFROST_STAIRS = registerBlock("cobblefrost_stairs", doStairs(COBBLEFROST));
    public static final Block COBBLEFROST_SLAB = registerBlock("cobblefrost_slab", doSlab(COBBLEFROST));
    public static final Block COBBLEFROST_WALL = registerBlock("cobblefrost_wall", doWall(COBBLEFROST));
    // Vivulite Anvil
    public static final Block VIVULITE_ANVIL = registerBlock("vivulite_anvil", new AnvilBlock(
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.RED)
                    .requiresTool()
                    .strength(7.0F, 1200.0F)
                    .sounds(BlockSoundGroup.ANVIL)
                    .pistonBehavior(PistonBehavior.BLOCK)),
            new Item.Settings().rarity(Rarity.RARE)
    );

    // Crags Portal
    public static final Block CRAGS_PORTAL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "crags_portal"),
            new CragsPortalBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.DULL_RED)
                            .noCollision()
                            .solid()
                            .luminance(state -> 5)
                            .strength(-1.0F, 3600000.0F)
                            .dropsNothing()
                            .pistonBehavior(PistonBehavior.BLOCK)
            )
    );

    // Beef Wellington
    public static final Block BEEF_WELLINGTON = registerBlock("beef_wellington", new BeefWellingtonBlock(AbstractBlock.Settings.copy(Blocks.CAKE)),
            new Item.Settings().maxCount(1));
    // Fruitcake
    public static final Block FRUITCAKE = registerBlock("fruitcake", new FruitcakeBlock(AbstractBlock.Settings.copy(Blocks.CAKE)),
            new Item.Settings().maxCount(1));

    // Personal Chest
    public static final Block PERSONAL_CHEST = registerBlock("personal_chest",
            new PersonalChestBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sounds(BlockSoundGroup.DEEPSLATE)
                    .requiresTool()
                    .strength(3.5F, 600.0F),
                    () -> ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY
            )
    );

    // Phantasmic TNT
    public static final Block PHANTASMIC_TNT = registerBlock("phantasmic_tnt",
            new TntBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.BRIGHT_TEAL)
                            .breakInstantly()
                            .sounds(BlockSoundGroup.GRASS)
                            .burnable()
                            .solidBlock(Blocks::never)
            )
    );

    // Curse Altar
    public static final Block CURSE_ALTAR = registerBlock("curse_altar",
            new CurseAltarBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LAPIS_BLUE)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .luminance(state -> 5)
                            .strength(4.0F, 1000.0F)
            )
    );

    // Melon-related blocks
    public static final Block GLISTERING_MELON = registerBlock("glistering_melon",
            new GlisteringMelonBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.GOLD)
                            .strength(1.5F)
                            .sounds(BlockSoundGroup.METAL)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block CARVED_MELON = registerBlock("carved_melon",
            new WearableFruitBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LIME)
                            .strength(1.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .allowsSpawning(Blocks::always)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block JUNE_O_LANTERN = registerBlock("june_o_lantern",
            new CarvedFruitBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.LIME)
                            .strength(1.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .luminance(state -> 15)
                            .allowsSpawning(Blocks::always)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block CARVED_GLISTERING_MELON = registerBlock("carved_glistering_melon",
            new WearableFruitBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.GOLD)
                            .strength(1.5F)
                            .sounds(BlockSoundGroup.METAL)
                            .allowsSpawning(Blocks::always)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block GLISTERING_JUNE_O_LANTERN = registerBlock("glistering_june_o_lantern",
            new CarvedFruitBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.GOLD)
                            .strength(1.5F)
                            .sounds(BlockSoundGroup.METAL)
                            .luminance(state -> 15)
                            .allowsSpawning(Blocks::always)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block WHITE_PUMPKIN = registerBlock("white_pumpkin",
            new WearableFruitBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE_GRAY)
                            .strength(1.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .allowsSpawning(Blocks::always)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block WHITE_JACK_O_LANTERN = registerBlock("white_jack_o_lantern",
            new CarvedFruitBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.WHITE_GRAY)
                            .strength(1.0F)
                            .sounds(BlockSoundGroup.WOOD)
                            .luminance(state -> 15)
                            .allowsSpawning(Blocks::always)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    // Spirit Candle
    public static final Block SPIRIT_CANDLE = registerBlockNoItem("spirit_candle",
            new SpiritCandleBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PALE_PURPLE)
                            .nonOpaque()
                            .strength(0.2F)
                            .sounds(BlockSoundGroup.CANDLE)
                            .luminance(SpiritCandleBlock.STATE_TO_LUMINANCE)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    // Monster Bakery
    public static final Block MONSTER_BAKERY = registerBlock("monster_bakery",
            new MonsterBakeryBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.STONE_GRAY)
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .requiresTool()
                            .strength(5.0F)
                            .sounds(BlockSoundGroup.METAL)
                            .nonOpaque()
            )
    );
    // Phantom-Stitch Bed
    public static final Block PHANTOM_STITCH_BED = registerBlockNoItem("phantom_stitch_bed",
            new PhantomBedBlock(
                    AbstractBlock.Settings.create()
                            .mapColor(state -> state.get(BedBlock.PART) == BedPart.FOOT ? MapColor.TERRACOTTA_PURPLE : MapColor.WHITE_GRAY)
                            .sounds(BlockSoundGroup.WOOD)
                            .strength(0.2F)
                            .nonOpaque()
                            .burnable()
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

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

    // Creates a blighted log block
    public static Block createBlightedLog(boolean day_switch, String target_block)
    {
        return new TimeSwitchLogBlock(
                day_switch,
                target_block,
                AbstractBlock.Settings.create()
                    .mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_PURPLE : MapColor.GOLD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()
        );
    }

    // Creates a blighted wood block
    public static Block createBlightedWood()
    {
        return new PillarBlock(AbstractBlock.Settings.create()
                .mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()
        );
    }

    // Creates a wreath
    public static Block createWreath(Block original)
    {
        return createWreathExt(AbstractBlock.Settings.copy(original));
    }

    public static Block createWreathExt(AbstractBlock.Settings settings)
    {
        return new WreathBlock(settings);
    }

    // Helper classes for common blocks
    public static Block doStairs(Block type) { return new StairsBlock(type.getDefaultState(), AbstractBlock.Settings.copy(type)); }
    public static Block doSlab(Block type) { return new SlabBlock(AbstractBlock.Settings.copy(type)); }
    public static Block doFence(Block type) { return new FenceBlock(AbstractBlock.Settings.copy(type)); }
    public static Block doWall(Block type) { return new WallBlock(AbstractBlock.Settings.copy(type).solid()); }
    public static Block doStoneGate(Block type) { return new StoneFenceGateBlock(AbstractBlock.Settings.copy(type)); }
    public static Block doWoodGate(WoodType wood, Block type) { return new FenceGateBlock(wood, AbstractBlock.Settings.copy(type)); }

    // #############################################################################

    /** Registers both the Block and Item to their respective Minecraft registry.*/
    private static Block registerBlock(String name, Block block)
    {
        doBlockItem(name, block, new Item.Settings());
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    /** Registers both the Block and Item to their respective Minecraft registry. Allows custom item definition. */
    private static Block registerBlock(String name, Block block, Item.Settings settings)
    {
        doBlockItem(name, block, settings);
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    /** Registers ONLY the Block to the respective Minecraft registry. Items like this will be registered in the ModItem class. */
    private static Block registerBlockNoItem(String name, Block block)
    {
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    /** Creates the block item - used alongside registerBlock() to make both in same method.*/
    private static Item doBlockItem(String name, Block block, Item.Settings settings)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), new BlockItem(block, settings));
    }

    /** Registers mod blocks. Just sends a log message. */
    public static void registerModBlocks()
    {
        //Frontiers.LOGGER.info("Registering Mod Blocks for " + Frontiers.MOD_ID);
    }
}
