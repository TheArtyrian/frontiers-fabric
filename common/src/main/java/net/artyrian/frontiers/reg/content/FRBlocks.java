package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.*;
import net.artyrian.frontiers.definition.block.custom.model.*;
import net.artyrian.frontiers.reg.misc.*;
import net.artyrian.frontiers.reg.property.FRBlockProperties;
import net.artyrian.frontiers.reg.property.FRBlocksets;
import net.artyrian.frontiers.reg.sound.FRBlockSFX;
import net.artyrian.frontiers.reg.world.FRDimension;
import net.artyrian.frontiers.reg.world.FRFeaturesConfigured;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

// Registers all mod blocks (and their items) to Minecraft registries.
public class FRBlocks
{
    // BLOCK LIST. Gets lengthy.

    // BLOCK FAMILIES
    // Tower
    public static final Supplier<Block> TOWER_BRICKS = registerBlock("tower_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).requiresCorrectToolForDrops().strength(70.0F, 800.0F).pushReaction(PushReaction.BLOCK).instrument(FRRegistries.NoteBlockInst.FRONTIERS_JESKOLA)));
    public static final Supplier<Block> TOWER_BRICK_STAIRS = registerBlock("tower_brick_stairs", () -> doStairs(TOWER_BRICKS.get()));
    public static final Supplier<Block> TOWER_BRICK_SLAB = registerBlock("tower_brick_slab", () -> doSlab(TOWER_BRICKS.get()));
    public static final Supplier<Block> TOWER_BRICK_WALL = registerBlock("tower_brick_wall", () -> doWall(TOWER_BRICKS.get()));
    public static final Supplier<Block> MOSSY_TOWER_BRICKS = registerBlock("mossy_tower_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TOWER_BRICKS.get())));
    public static final Supplier<Block> MOSSY_TOWER_BRICK_STAIRS = registerBlock("mossy_tower_brick_stairs", () -> doStairs(MOSSY_TOWER_BRICKS.get()));
    public static final Supplier<Block> MOSSY_TOWER_BRICK_SLAB = registerBlock("mossy_tower_brick_slab", () -> doSlab(MOSSY_TOWER_BRICKS.get()));
    public static final Supplier<Block> MOSSY_TOWER_BRICK_WALL = registerBlock("mossy_tower_brick_wall", () -> doWall(MOSSY_TOWER_BRICKS.get()));
    public static final Supplier<Block> TOWER_WATCHER = registerBlock("tower_watcher", () -> new TowerWatcherBlock(BlockBehaviour.Properties.ofFullCopy(TOWER_BRICKS.get())));
    public static final Supplier<Block> TOWER_SPAWNER = registerBlock("tower_spawner", () -> new TowerSpawnerBlock(BlockBehaviour.Properties.ofFullCopy(TOWER_BRICKS.get()).strength(70.0F, 1.0F).isViewBlocking(Blocks::never).noOcclusion().lightLevel(towerSpawnerLight()).emissiveRendering(FRBlocks::towerSpawnerEmis)));
    public static final Supplier<Block> TOWER_KEY_VAULT = registerBlock("tower_key_vault", () -> new TowerKeyVaultBlock(BlockBehaviour.Properties.ofFullCopy(TOWER_BRICKS.get()).isViewBlocking(Blocks::never).noOcclusion()));
    public static final Supplier<Block> TOWER_TREASURE_VAULT = registerBlock("tower_treasure_vault", () -> new TowerTreasureVaultBlock(BlockBehaviour.Properties.ofFullCopy(TOWER_BRICKS.get()).isViewBlocking(Blocks::never).noOcclusion()));
    public static final Supplier<Block> TOWER_HEART = registerBlock("tower_heart", () -> new TowerHeartBlock(BlockBehaviour.Properties.ofFullCopy(TOWER_BRICKS.get()).isViewBlocking(Blocks::never).noOcclusion().lightLevel(state -> 5).emissiveRendering(Blocks::always)));
    // Nacre
    public static final Supplier<Block> NACRE_BRICKS = registerBlock("nacre_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).sound(SoundType.CALCITE).mapColor(MapColor.SAND)));
    public static final Supplier<Block> NACRE_BRICK_STAIRS = registerBlock("nacre_brick_stairs", () -> doStairs(NACRE_BRICKS.get()));
    public static final Supplier<Block> NACRE_BRICK_SLAB = registerBlock("nacre_brick_slab", () -> doSlab(NACRE_BRICKS.get()));
    public static final Supplier<Block> NACRE_BRICK_WALL = registerBlock("nacre_brick_wall", () -> doWall(NACRE_BRICKS.get()));
    // Turtle Scute
    public static final Supplier<Block> TURTLE_SCUTE_BRICKS = registerBlock("turtle_scute_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).instrument(FRRegistries.NoteBlockInst.FRONTIERS_LOG_DRUM).mapColor(MapColor.GRASS)));
    public static final Supplier<Block> TURTLE_SCUTE_BRICK_STAIRS = registerBlock("turtle_scute_brick_stairs", () -> doStairs(TURTLE_SCUTE_BRICKS.get()));
    public static final Supplier<Block> TURTLE_SCUTE_BRICK_SLAB = registerBlock("turtle_scute_brick_slab", () -> doSlab(TURTLE_SCUTE_BRICKS.get()));
    public static final Supplier<Block> TURTLE_SCUTE_BRICK_WALL = registerBlock("turtle_scute_brick_wall", () -> doWall(TURTLE_SCUTE_BRICKS.get()));
    // Cragulstane
    public static final Supplier<Block> CRAGULSTANE = registerBlock("cragulstane", () -> new Block(BlockBehaviour.Properties.of().strength(10.0F, 800.0F).mapColor(MapColor.CRIMSON_NYLIUM).requiresCorrectToolForDrops().instrument(FRRegistries.NoteBlockInst.FRONTIERS_ROBOLUNG).sound(FRBlockSFX.CRAGULSTANE).isValidSpawn((state, world, pos, entityType) -> entityType == FREntity.CRAGS_STALKER.get())));
    public static final Supplier<Block> CRAGULSTANE_BRICKS = registerBlock("cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE.get())));
    public static final Supplier<Block> CRAGULSTANE_BRICK_STAIRS = registerBlock("cragulstane_brick_stairs", () -> doStairs(CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> CRAGULSTANE_BRICK_SLAB = registerBlock("cragulstane_brick_slab", () -> doSlab(CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> CRAGULSTANE_BRICK_WALL = registerBlock("cragulstane_brick_wall", () -> doWall(CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> CHISELED_CRAGULSTANE_BRICKS = registerBlock("chiseled_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE.get())));
    public static final Supplier<Block> CRACKED_CRAGULSTANE_BRICKS = registerBlock("cracked_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE.get())));
    // Brimmed Cragulstane
    public static final Supplier<Block> BRIMMED_CRAGULSTANE_BRICKS = registerBlock("brimmed_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE_BRICKS.get())));
    public static final Supplier<Block> BRIMMED_CRAGULSTANE_BRICK_STAIRS = registerBlock("brimmed_cragulstane_brick_stairs", () -> doStairs(BRIMMED_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> BRIMMED_CRAGULSTANE_BRICK_SLAB = registerBlock("brimmed_cragulstane_brick_slab", () -> doSlab(BRIMMED_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> BRIMMED_CRAGULSTANE_BRICK_WALL = registerBlock("brimmed_cragulstane_brick_wall", () -> doWall(BRIMMED_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> CHISELED_BRIMMED_CRAGULSTANE_BRICKS = registerBlock("chiseled_brimmed_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CHISELED_CRAGULSTANE_BRICKS.get())));
    public static final Supplier<Block> CRACKED_BRIMMED_CRAGULSTANE_BRICKS = registerBlock("cracked_brimmed_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRACKED_CRAGULSTANE_BRICKS.get())));
    // Orange Cragulstane
    public static final Supplier<Block> ORANGE_CRAGULSTANE_BRICKS = registerBlock("orange_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE_BRICKS.get())));
    public static final Supplier<Block> ORANGE_CRAGULSTANE_BRICK_STAIRS = registerBlock("orange_cragulstane_brick_stairs", () -> doStairs(ORANGE_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> ORANGE_CRAGULSTANE_BRICK_SLAB = registerBlock("orange_cragulstane_brick_slab", () -> doSlab(ORANGE_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> ORANGE_CRAGULSTANE_BRICK_WALL = registerBlock("orange_cragulstane_brick_wall", () -> doWall(ORANGE_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> CHISELED_ORANGE_CRAGULSTANE_BRICKS = registerBlock("chiseled_orange_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CHISELED_CRAGULSTANE_BRICKS.get())));
    public static final Supplier<Block> CRACKED_ORANGE_CRAGULSTANE_BRICKS = registerBlock("cracked_orange_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRACKED_CRAGULSTANE_BRICKS.get())));
    // Tyrian Cragulstane
    public static final Supplier<Block> TYRIAN_CRAGULSTANE_BRICKS = registerBlock("tyrian_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE_BRICKS.get())));
    public static final Supplier<Block> TYRIAN_CRAGULSTANE_BRICK_STAIRS = registerBlock("tyrian_cragulstane_brick_stairs", () -> doStairs(TYRIAN_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> TYRIAN_CRAGULSTANE_BRICK_SLAB = registerBlock("tyrian_cragulstane_brick_slab", () -> doSlab(TYRIAN_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> TYRIAN_CRAGULSTANE_BRICK_WALL = registerBlock("tyrian_cragulstane_brick_wall", () -> doWall(TYRIAN_CRAGULSTANE_BRICKS.get()));
    public static final Supplier<Block> CHISELED_TYRIAN_CRAGULSTANE_BRICKS = registerBlock("chiseled_tyrian_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CHISELED_CRAGULSTANE_BRICKS.get())));
    public static final Supplier<Block> CRACKED_TYRIAN_CRAGULSTANE_BRICKS = registerBlock("cracked_tyrian_cragulstane_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRACKED_CRAGULSTANE_BRICKS.get())));
    // Eboncork Blocks
    public static final Supplier<Block> EBONCORK = registerBlock("eboncork", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).strength(3.0F).sound(SoundType.NETHER_WOOD)));
    public static final Supplier<Block> EBONCORK_PLANKS = registerBlock("eboncork_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).instrument(NoteBlockInstrument.BASS).strength(3.0F, 4.0F).sound(SoundType.NETHER_WOOD)));
    public static final Supplier<Block> EBONCORK_SLAB = registerBlock("eboncork_slab", () -> doSlab(EBONCORK_PLANKS.get()));
    public static final Supplier<Block> EBONCORK_STAIRS = registerBlock("eboncork_stairs", () -> doStairs(EBONCORK_PLANKS.get()));
    public static final Supplier<Block> EBONCORK_FENCE = registerBlock("eboncork_fence", () -> doFence(EBONCORK_PLANKS.get()));
    public static final Supplier<Block> EBONCORK_FENCE_GATE = registerBlock("eboncork_fence_gate", () -> doWoodGate(FRBlocksets.WoodSet.EBONCORK, EBONCORK_PLANKS.get()));
    public static final Supplier<Block> EBONCORK_BUTTON = registerBlock("eboncork_button", () -> Blocks.woodenButton(FRBlocksets.BlockSet.EBONCORK));
    public static final Supplier<Block> EBONCORK_PRESSURE_PLATE = registerBlock("eboncork_pressure_plate", () -> new PressurePlateBlock(FRBlocksets.BlockSet.EBONCORK, BlockBehaviour.Properties.of().mapColor(EBONCORK_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> EBONCORK_DOOR = registerBlockNoItem("eboncork_door", () -> new DoorBlock(FRBlocksets.BlockSet.EBONCORK, BlockBehaviour.Properties.of().mapColor(EBONCORK_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F, 4.0F).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> EBONCORK_TRAPDOOR = registerBlock("eboncork_trapdoor", () -> new TrapDoorBlock(FRBlocksets.BlockSet.EBONCORK, BlockBehaviour.Properties.of().mapColor(EBONCORK_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F, 4.0F).noOcclusion().isValidSpawn(Blocks::never)));
    // Blighted Birch
    public static final Supplier<Block> RADIANT_BLIGHTED_BIRCH_LOG = registerBlock("radiant_blighted_birch_log", () -> createBlightedLog(false, "frontiers:sullen_blighted_birch_log"));
    public static final Supplier<Block> RADIANT_BLIGHTED_BIRCH_WOOD = registerBlock("radiant_blighted_birch_wood", () -> createBlightedWood());
    public static final Supplier<Block> SULLEN_BLIGHTED_BIRCH_LOG = registerBlock("sullen_blighted_birch_log", () -> createBlightedLog(true, "frontiers:radiant_blighted_birch_log"));
    public static final Supplier<Block> SULLEN_BLIGHTED_BIRCH_WOOD = registerBlock("sullen_blighted_birch_wood", () -> createBlightedWood());
    public static final Supplier<Block> STRIPPED_BLIGHTED_BIRCH_LOG = registerBlock("stripped_blighted_birch_log", () -> Blocks.log(MapColor.TERRACOTTA_PURPLE, MapColor.TERRACOTTA_PURPLE));
    public static final Supplier<Block> STRIPPED_BLIGHTED_BIRCH_WOOD = registerBlock("stripped_blighted_birch_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));
    public static final Supplier<Block> BLIGHTED_BIRCH_LEAVES = registerBlock("blighted_birch_leaves", () -> Blocks.leaves(SoundType.GRASS));
    public static final Supplier<Block> BLIGHTED_BIRCH_PLANKS = registerBlock("blighted_birch_planks", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> BLIGHTED_BIRCH_SLAB = registerBlock("blighted_birch_slab", () -> doSlab(BLIGHTED_BIRCH_PLANKS.get()));
    public static final Supplier<Block> BLIGHTED_BIRCH_STAIRS = registerBlock("blighted_birch_stairs", () -> doStairs(BLIGHTED_BIRCH_PLANKS.get()));
    public static final Supplier<Block> BLIGHTED_BIRCH_FENCE = registerBlock("blighted_birch_fence", () -> doFence(BLIGHTED_BIRCH_PLANKS.get()));
    public static final Supplier<Block> BLIGHTED_BIRCH_FENCE_GATE = registerBlock("blighted_birch_fence_gate", () -> doWoodGate(FRBlocksets.WoodSet.BLIGHTED_BIRCH, BLIGHTED_BIRCH_PLANKS.get()));
    public static final Supplier<Block> BLIGHTED_BIRCH_BUTTON = registerBlock("blighted_birch_button", () -> Blocks.woodenButton(FRBlocksets.BlockSet.BLIGHTED_BIRCH));
    public static final Supplier<Block> BLIGHTED_BIRCH_PRESSURE_PLATE = registerBlock("blighted_birch_pressure_plate", () -> new PressurePlateBlock(FRBlocksets.BlockSet.BLIGHTED_BIRCH, BlockBehaviour.Properties.of().mapColor(BLIGHTED_BIRCH_PLANKS.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(0.5F).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> BLIGHTED_BIRCH_DOOR = registerBlockNoItem("blighted_birch_door", () -> new DoorBlock(FRBlocksets.BlockSet.BLIGHTED_BIRCH, BlockBehaviour.Properties.of().mapColor(BLIGHTED_BIRCH_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> BLIGHTED_BIRCH_TRAPDOOR = registerBlock("blighted_birch_trapdoor", () -> new TrapDoorBlock(FRBlocksets.BlockSet.BLIGHTED_BIRCH, BlockBehaviour.Properties.of().mapColor(BLIGHTED_BIRCH_PLANKS.get().defaultMapColor()).instrument(NoteBlockInstrument.BASS).strength(3.0F).noOcclusion().ignitedByLava().isValidSpawn(Blocks::never)));
    // Nether Brick
    public static final Supplier<Block> NETHER_BRICK_FENCE_GATE = registerBlock("nether_brick_fence_gate", () -> doStoneGate(Blocks.NETHER_BRICKS));
    // Red Nether Brick
    public static final Supplier<Block> CRACKED_RED_NETHER_BRICKS = registerBlock("cracked_red_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)));
    public static final Supplier<Block> CHISELED_RED_NETHER_BRICKS = registerBlock("chiseled_red_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)));
    public static final Supplier<Block> RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence", () -> doFence(Blocks.RED_NETHER_BRICKS));
    public static final Supplier<Block> RED_NETHER_BRICK_FENCE_GATE = registerBlock("red_nether_brick_fence_gate", () -> doStoneGate(Blocks.RED_NETHER_BRICKS));
    // Blue Nether Brick
    public static final Supplier<Block> BLUE_NETHER_BRICKS = registerBlock("blue_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.WARPED_STEM)));
    public static final Supplier<Block> CRACKED_BLUE_NETHER_BRICKS = registerBlock("cracked_blue_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(BLUE_NETHER_BRICKS.get())));
    public static final Supplier<Block> CHISELED_BLUE_NETHER_BRICKS = registerBlock("chiseled_blue_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(BLUE_NETHER_BRICKS.get())));
    public static final Supplier<Block> BLUE_NETHER_BRICK_STAIRS = registerBlock("blue_nether_brick_stairs", () -> doStairs(BLUE_NETHER_BRICKS.get()));
    public static final Supplier<Block> BLUE_NETHER_BRICK_SLAB = registerBlock("blue_nether_brick_slab", () -> doSlab(BLUE_NETHER_BRICKS.get()));
    public static final Supplier<Block> BLUE_NETHER_BRICK_WALL = registerBlock("blue_nether_brick_wall", () -> doWall(BLUE_NETHER_BRICKS.get()));
    public static final Supplier<Block> BLUE_NETHER_BRICK_FENCE = registerBlock("blue_nether_brick_fence", () -> doFence(BLUE_NETHER_BRICKS.get()));
    public static final Supplier<Block> BLUE_NETHER_BRICK_FENCE_GATE = registerBlock("blue_nether_brick_fence_gate", () -> doStoneGate(BLUE_NETHER_BRICKS.get()));
    // Purple Nether Brick
    public static final Supplier<Block> PURPLE_NETHER_BRICKS = registerBlock("purple_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS).mapColor(MapColor.ICE)));
    public static final Supplier<Block> CRACKED_PURPLE_NETHER_BRICKS = registerBlock("cracked_purple_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(PURPLE_NETHER_BRICKS.get())));
    public static final Supplier<Block> CHISELED_PURPLE_NETHER_BRICKS = registerBlock("chiseled_purple_nether_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(PURPLE_NETHER_BRICKS.get())));
    public static final Supplier<Block> PURPLE_NETHER_BRICK_STAIRS = registerBlock("purple_nether_brick_stairs", () -> doStairs(PURPLE_NETHER_BRICKS.get()));
    public static final Supplier<Block> PURPLE_NETHER_BRICK_SLAB = registerBlock("purple_nether_brick_slab", () -> doSlab(PURPLE_NETHER_BRICKS.get()));
    public static final Supplier<Block> PURPLE_NETHER_BRICK_WALL = registerBlock("purple_nether_brick_wall", () -> doWall(PURPLE_NETHER_BRICKS.get()));
    public static final Supplier<Block> PURPLE_NETHER_BRICK_FENCE = registerBlock("purple_nether_brick_fence", () -> doFence(PURPLE_NETHER_BRICKS.get()));
    public static final Supplier<Block> PURPLE_NETHER_BRICK_FENCE_GATE = registerBlock("purple_nether_brick_fence_gate", () -> doStoneGate(PURPLE_NETHER_BRICKS.get()));
    // Pale Prismarine
    public static final Supplier<Block> PALE_PRISMARINE = registerBlock("pale_prismarine", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Supplier<Block> PALE_PRISMARINE_STAIRS = registerBlock("pale_prismarine_stairs", () -> doStairs(PALE_PRISMARINE.get()));
    public static final Supplier<Block> PALE_PRISMARINE_SLAB = registerBlock("pale_prismarine_slab", () -> doSlab(PALE_PRISMARINE.get()));
    public static final Supplier<Block> PALE_PRISMARINE_WALL = registerBlock("pale_prismarine_wall", () -> doWall(PALE_PRISMARINE.get()));
    public static final Supplier<Block> PALE_PRISMARINE_BRICKS = registerBlock("pale_prismarine_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Supplier<Block> PALE_PRISMARINE_BRICK_STAIRS = registerBlock("pale_prismarine_brick_stairs", () -> doStairs(PALE_PRISMARINE_BRICKS.get()));
    public static final Supplier<Block> PALE_PRISMARINE_BRICK_SLAB = registerBlock("pale_prismarine_brick_slab", () -> doSlab(PALE_PRISMARINE_BRICKS.get()));
    public static final Supplier<Block> DEEP_PALE_PRISMARINE = registerBlock("deep_pale_prismarine", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_PRISMARINE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Supplier<Block> DEEP_PALE_PRISMARINE_STAIRS = registerBlock("deep_pale_prismarine_stairs", () -> doStairs(DEEP_PALE_PRISMARINE.get()));
    public static final Supplier<Block> DEEP_PALE_PRISMARINE_SLAB = registerBlock("deep_pale_prismarine_slab", () -> doSlab(DEEP_PALE_PRISMARINE.get()));
    // Hielostone
    public static final Supplier<Block> HIELOSTONE = registerBlock("hielostone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2.0F, 5.0F).mapColor(MapColor.ICE)));
    public static final Supplier<Block> HIELOSTONE_STAIRS = registerBlock("hielostone_stairs", () -> doStairs(HIELOSTONE.get()));
    public static final Supplier<Block> HIELOSTONE_SLAB = registerBlock("hielostone_slab", () -> doSlab(HIELOSTONE.get()));
    public static final Supplier<Block> HIELOSTONE_WALL = registerBlock("hielostone_wall", () -> doWall(HIELOSTONE.get()));
    public static final Supplier<Block> COBBLEFROST = registerBlock("cobblefrost", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).strength(2.5F, 5.0F).mapColor(MapColor.ICE)));
    public static final Supplier<Block> COBBLEFROST_STAIRS = registerBlock("cobblefrost_stairs", () -> doStairs(COBBLEFROST.get()));
    public static final Supplier<Block> COBBLEFROST_SLAB = registerBlock("cobblefrost_slab", () -> doSlab(COBBLEFROST.get()));
    public static final Supplier<Block> COBBLEFROST_WALL = registerBlock("cobblefrost_wall", () -> doWall(COBBLEFROST.get()));
    public static final Supplier<Block> HIELOSTONE_TILES = registerBlock("hielostone_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIELOSTONE.get())));
    public static final Supplier<Block> HIELOSTONE_TILE_STAIRS = registerBlock("hielostone_tile_stairs", () -> doStairs(HIELOSTONE_TILES.get()));
    public static final Supplier<Block> HIELOSTONE_TILE_SLAB = registerBlock("hielostone_tile_slab", () -> doSlab(HIELOSTONE_TILES.get()));
    public static final Supplier<Block> HIELOSTONE_TILE_WALL = registerBlock("hielostone_tile_wall", () -> doWall(HIELOSTONE_TILES.get()));
    public static final Supplier<Block> HIELOSTONE_BRICKS = registerBlock("hielostone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIELOSTONE.get())));
    public static final Supplier<Block> HIELOSTONE_BRICK_STAIRS = registerBlock("hielostone_brick_stairs", () -> doStairs(HIELOSTONE_BRICKS.get()));
    public static final Supplier<Block> HIELOSTONE_BRICK_SLAB = registerBlock("hielostone_brick_slab", () -> doSlab(HIELOSTONE_BRICKS.get()));
    public static final Supplier<Block> HIELOSTONE_BRICK_WALL = registerBlock("hielostone_brick_wall", () -> doWall(HIELOSTONE_BRICKS.get()));
    public static final Supplier<Block> HIELOSTONE_PLATES = registerBlock("hielostone_plates", () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIELOSTONE.get())));
    public static final Supplier<Block> HIELOSTONE_PLATE_STAIRS = registerBlock("hielostone_plate_stairs", () -> doStairs(HIELOSTONE_PLATES.get()));
    public static final Supplier<Block> HIELOSTONE_PLATE_SLAB = registerBlock("hielostone_plate_slab", () -> doSlab(HIELOSTONE_PLATES.get()));
    public static final Supplier<Block> HIELOSTONE_PLATE_WALL = registerBlock("hielostone_plate_wall", () -> doWall(HIELOSTONE_PLATES.get()));
    // Quicksand + Crusted Quicksand
    public static final Supplier<Block> QUICKSAND = registerBlock("quicksand", () -> new QuicksandBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).mapColor(MapColor.SAND).forceSolidOn().noCollission().requiresCorrectToolForDrops().strength(1.0F).isViewBlocking(Blocks::always)));
    public static final Supplier<Block> CRUSTED_QUICKSAND = registerBlock("crusted_quicksand", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_MUD).mapColor(MapColor.SAND)));
    public static final Supplier<Block> CRUSTY_SAND_BRICKS = registerBlock("crusty_sand_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS).mapColor(MapColor.SAND)));
    public static final Supplier<Block> CRUSTY_SAND_BRICK_STAIRS = registerBlock("crusty_sand_brick_stairs", () -> doStairs(CRUSTY_SAND_BRICKS.get()));
    public static final Supplier<Block> CRUSTY_SAND_BRICK_SLAB = registerBlock("crusty_sand_brick_slab", () -> doSlab(CRUSTY_SAND_BRICKS.get()));
    public static final Supplier<Block> CRUSTY_SAND_BRICK_WALL = registerBlock("crusty_sand_brick_wall", () -> doWall(CRUSTY_SAND_BRICKS.get()));
    // Red Quicksand + Crusted Red Quicksand
    public static final Supplier<Block> RED_QUICKSAND = registerBlock("red_quicksand", () -> new QuicksandBlock(BlockBehaviour.Properties.ofFullCopy(QUICKSAND.get()).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Supplier<Block> CRUSTED_RED_QUICKSAND = registerBlock("crusted_red_quicksand", () -> new Block(BlockBehaviour.Properties.ofFullCopy(FRBlocks.CRUSTED_QUICKSAND.get()).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Supplier<Block> CRUSTY_RED_SAND_BRICKS = registerBlock("crusty_red_sand_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(FRBlocks.CRUSTY_SAND_BRICKS.get()).mapColor(MapColor.TERRACOTTA_ORANGE)));
    public static final Supplier<Block> CRUSTY_RED_SAND_BRICK_STAIRS = registerBlock("crusty_red_sand_brick_stairs", () -> doStairs(CRUSTY_RED_SAND_BRICKS.get()));
    public static final Supplier<Block> CRUSTY_RED_SAND_BRICK_SLAB = registerBlock("crusty_red_sand_brick_slab", () -> doSlab(CRUSTY_RED_SAND_BRICKS.get()));
    public static final Supplier<Block> CRUSTY_RED_SAND_BRICK_WALL = registerBlock("crusty_red_sand_brick_wall", () -> doWall(CRUSTY_RED_SAND_BRICKS.get()));

    // ORE BLOCK FAMILIES
    // Cobalt
    public static final Supplier<Block> COBALT_ORE = registerBlock("cobalt_ore", () -> new HardmodeLockedExpBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final Supplier<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore", () -> new HardmodeLockedExpBlock(UniformInt.of(0, 3), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
    public static final Supplier<Block> COBALT_BLOCK = registerBlock("cobalt_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.BLUE)));
    public static final Supplier<Block> RAW_COBALT_BLOCK = registerBlock("raw_cobalt_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.DIAMOND)));
    public static final Supplier<Block> COBALT_GRILLES = registerBlock("cobalt_grilles", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)));
    // Verdinite
    public static final Supplier<Block> VERDINITE_ORE = registerBlock("verdinite_ore", () -> new HardmodeLockedExpBlock(UniformInt.of(2, 5), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final Supplier<Block> DEEPSLATE_VERDINITE_ORE = registerBlock("deepslate_verdinite_ore", () -> new HardmodeLockedExpBlock(UniformInt.of(2, 5), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
    public static final Supplier<Block> VERDINITE_BLOCK = registerBlock("verdinite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.LIME)));
    public static final Supplier<Block> RAW_VERDINITE_BLOCK = registerBlock("raw_verdinite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN)));
    // Frostite
    public static final Supplier<Block> FROSTITE_ORE = registerBlock("frostite_ore", () -> new FrostiteOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.ICE).sound(SoundType.GLASS).friction(0.98F).randomTicks().noOcclusion()));
    public static final Supplier<Block> FROSTITE_BLOCK = registerBlock("frostite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.ICE).instrument(FRRegistries.NoteBlockInst.FRONTIERS_ICE_BELL).friction(0.98F)));
    public static final Supplier<Block> RAW_FROSTITE_BLOCK = registerBlock("raw_frostite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.CLAY).friction(0.98F)));
    // Vivulite
    public static final Supplier<Block> VIVULITE_ORE = registerBlock("vivulite_ore", () -> new HardmodeLockedExpBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));
    public static final Supplier<Block> DEEPSLATE_VIVULITE_ORE = registerBlock("deepslate_vivulite_ore", () -> new HardmodeLockedExpBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));
    public static final Supplier<Block> VIVULITE_BLOCK = registerBlock("vivulite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.RED)));
    public static final Supplier<Block> RAW_VIVULITE_BLOCK = registerBlock("raw_vivulite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).mapColor(MapColor.COLOR_MAGENTA)));
    // Brimtan
    public static final Supplier<Block> BRIMTAN_ORE = registerBlock("brimtan_ore", () -> new DropExperienceBlock(UniformInt.of(4, 8), BlockBehaviour.Properties.ofFullCopy(CRAGULSTANE.get())), new Item.Properties().fireResistant());
    public static final Supplier<Block> BRIMTAN_BLOCK = registerBlock("brimtan_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.ORANGE).requiresCorrectToolForDrops().strength(60.0F, 1200.0F).sound(SoundType.NETHERITE_BLOCK)), new Item.Properties().fireResistant());
    // Black Emerald
    public static final Supplier<Block> BLACK_EMERALD_ORE = registerBlock("black_emerald_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).mapColor(MapColor.WARPED_HYPHAE)));
    public static final Supplier<Block> DEEPSLATE_BLACK_EMERALD_ORE = registerBlock("deepslate_black_emerald_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_EMERALD_ORE).mapColor(MapColor.WARPED_HYPHAE)));
    public static final Supplier<Block> BLACK_EMERALD_BLOCK = registerBlock("black_emerald_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_BLOCK).instrument(FRRegistries.NoteBlockInst.FRONTIERS_HARPSICHORD).mapColor(MapColor.WARPED_HYPHAE)), new Item.Properties().rarity(Rarity.RARE));
    // Mourning Gold
    public static final Supplier<Block> MOURNING_GOLD_BLOCK = registerBlock("mourning_gold_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).instrument(FRRegistries.NoteBlockInst.FRONTIERS_HARPSICHORD).mapColor(DyeColor.LIGHT_GRAY)));
    // Necro Weave
    public static final Supplier<Block> NECRO_WEAVE_BLOCK = registerBlock("necro_weave_block", () -> new NecroWeaveBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.GUITAR).sound(SoundType.WOOL).strength(2.0F, 4.0F).mapColor(DyeColor.CYAN)));
    public static final Supplier<Block> NECRO_RUG = registerBlock("necro_rug", () -> new NecroCarpetBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.1F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY)));

    // CROPS
    // Ancient Rose
    public static final Supplier<Block> ANCIENT_ROSE_CROP = registerBlockNoItem( "ancient_rose_crop", () -> new AncientRoseCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).mapColor(DyeColor.CYAN)));
    public static final Supplier<Block> ANCIENT_ROSE = registerBlock("ancient_rose", () -> new RoseFlowerBlock(FRBlocks.VIOLET_ROSE, MobEffects.HUNGER, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).noOcclusion().noCollission()));
    public static final Supplier<Block> POTTED_ANCIENT_ROSE = registerBlockNoItem("potted_ancient_rose", () -> new FlowerPotBlock(ANCIENT_ROSE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final Supplier<Block> ANCIENT_ROSE_BUSH = registerBlock("ancient_rose_bush", () -> new TallFlowerBlock((BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH))), new Item.Properties().rarity(Rarity.UNCOMMON));
    // Rose
    public static final Supplier<Block> ROSE = registerBlock("rose", () -> new RoseFlowerBlock(FRBlocks.VIOLET_ROSE, MobEffects.HUNGER, 8, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).noOcclusion().noCollission()));
    public static final Supplier<Block> POTTED_ROSE = registerBlockNoItem("potted_rose", () -> new FlowerPotBlock(ROSE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    // Violet Rose
    public static final Supplier<Block> VIOLET_ROSE = registerBlock("violet_rose", () -> new FlowerBlock(MobEffects.HUNGER, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).noOcclusion().noCollission()));
    public static final Supplier<Block> POTTED_VIOLET_ROSE = registerBlockNoItem("potted_violet_rose", () -> new FlowerPotBlock(VIOLET_ROSE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final Supplier<Block> VIOLET_ROSE_BUSH = registerBlock("violet_rose_bush", () -> new TallFlowerBlock((BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH))), new Item.Properties().rarity(Rarity.UNCOMMON));
    // Crimcone
    public static final Supplier<Block> CRIMCONE = registerBlock("crimcone", () -> new ExtendedFlowerBlock(Blocks.CRIMSON_NYLIUM, MobEffects.DIG_SLOWDOWN, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FUNGUS).noOcclusion().noCollission()));
    public static final Supplier<Block> POTTED_CRIMCONE = registerBlockNoItem("potted_crimcone", () -> new FlowerPotBlock(CRIMCONE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    // Fungal Daffodil
    public static final Supplier<Block> FUNGAL_DAFFODIL = registerBlock("fungal_daffodil", () -> new GrowableFlowerBlock(FRFeaturesConfigured.HUGE_FUNGAL_DAFFODIL_KEY, Blocks.MYCELIUM, MobEffects.CONFUSION, 6, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).mapColor(MapColor.ICE).noOcclusion().noCollission()));
    public static final Supplier<Block> POTTED_FUNGAL_DAFFODIL = registerBlockNoItem("potted_fungal_daffodil", () -> new FlowerPotBlock(FUNGAL_DAFFODIL.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    public static final Supplier<Block> FUNGAL_DAFFODIL_BLOCK = registerBlock("fungal_daffodil_block", () -> new FungalDaffodilBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.BASS).friction(0.95f).strength(0.2f).sound(SoundType.SHROOMLIGHT).ignitedByLava()));
    // Snow Dahlia
    public static final Supplier<Block> SNOW_DAHLIA = registerBlock("snow_dahlia", () -> new FlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).noOcclusion().noCollission()));
    public static final Supplier<Block> POTTED_SNOW_DAHLIA = registerBlockNoItem("potted_snow_dahlia", () -> new FlowerPotBlock(SNOW_DAHLIA.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));
    // Experiwinkle (created by Yurjezich)
    public static final Supplier<Block> EXPERIWINKLE_CROP = registerBlockNoItem("experiwinkle_crop", () -> new ExperiwinkleCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).mapColor(DyeColor.LIME).lightLevel(state -> state.getValue(ExperiwinkleCropBlock.AGE) + 1).emissiveRendering(Blocks::always)));
    public static final Supplier<Block> EXPERIWINKLE = registerBlock("experiwinkle", () -> new ExperiwinkleBlock(MobEffects.LUCK, 15, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).noOcclusion().noCollission().lightLevel(state -> 3).emissiveRendering(Blocks::always)), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> POTTED_EXPERIWINKLE = registerBlockNoItem("potted_experiwinkle", () -> new FlowerPotBlock(EXPERIWINKLE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion().lightLevel(state -> 3).emissiveRendering(Blocks::always)));
    // Blighted Birch Sapling (created by jesterccore)
    public static final Supplier<Block> BLIGHTED_BIRCH_SAPLING = registerBlock("blighted_birch_sapling", () -> new SaplingBlock(FRRegistries.Sapling.BLIGHTED_BIRCH, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> POTTED_BLIGHTED_BIRCH_SAPLING = registerBlockNoItem("potted_blighted_birch_sapling", () -> new FlowerPotBlock(BLIGHTED_BIRCH_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_BIRCH_SAPLING).noOcclusion()));
    // Warped Wart
    public static final Supplier<Block> WARPED_WART = registerBlockNoItem("warped_wart", () -> new WarpedWartBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_WART).mapColor(MapColor.COLOR_LIGHT_BLUE)));

    // UTILITY BLOCKS
    public static final Supplier<Block> GLOWING_OBSIDIAN = registerBlockNoItem("glowing_obsidian", () -> new GlowingObsidianBlock(FRDimension.CRAGS_LEVEL_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).mapColor(DyeColor.RED).lightLevel(state -> 12).requiresCorrectToolForDrops()));
    public static final Supplier<Block> STRANGE_CORE = registerBlock("strange_core", () -> new NetherReactorBlockLol(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).mapColor(DyeColor.BLUE).destroyTime(10.0F).lightLevel(strangeCoreLightHelper(5, 15)).requiresCorrectToolForDrops()), new Item.Properties().rarity(Rarity.RARE));
    public static final Supplier<Block> ENCHANTING_MAGNET = registerBlock("enchanting_magnet", () -> new EnchantingMagnetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEACON).mapColor(MapColor.EMERALD).lightLevel(state -> 4)), new Item.Properties().rarity(Rarity.RARE));
    public static final Supplier<Block> ITEM_VACUUM = registerBlock("item_vacuum", () -> new ItemVacuumBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPAWNER).strength(3.0F, 5.0F).noOcclusion()), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> VIVULITE_ANVIL = registerBlock("vivulite_anvil", () -> new AnvilBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(7.0F, 1200.0F).sound(SoundType.ANVIL).pushReaction(PushReaction.BLOCK)), new Item.Properties().rarity(Rarity.RARE));
    public static final Supplier<Block> PERSONAL_CHEST = registerBlock("personal_chest", () -> new PersonalChestBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().strength(3.5F, 600.0F), () -> FRBlockEntities.PERSONAL_CHEST_BLOCKENTITY.get()));
    public static final Supplier<Block> CURSE_ALTAR = registerBlock("curse_altar", () -> new CurseAltarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.LAPIS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().lightLevel(state -> 5).strength(4.0F, 1000.0F)));
    public static final Supplier<Block> PHANTASMIC_TNT = registerBlock("phantasmic_tnt", () -> new TntBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WARPED_WART_BLOCK).instabreak().sound(SoundType.GRASS).ignitedByLava().isRedstoneConductor(Blocks::never)));
    public static final Supplier<Block> SPIRIT_CANDLE = registerBlockNoItem("spirit_candle", () -> new SpiritCandleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.ICE).noOcclusion().strength(0.2F).sound(SoundType.CANDLE).lightLevel(SpiritCandleBlock.STATE_TO_LUMINANCE).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> MONSTER_BAKERY = registerBlock("monster_bakery", () -> new MonsterBakeryBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion()));
    public static final Supplier<Block> PHANTOM_STITCH_BED = registerBlockNoItem("phantom_stitch_bed", () -> new PhantomBedBlock(BlockBehaviour.Properties.of().mapColor(state -> state.getValue(BedBlock.PART) == BedPart.FOOT ? MapColor.TERRACOTTA_PURPLE : MapColor.WOOL).sound(SoundType.WOOD).strength(0.2F).noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));

    // FOOD
    public static final Supplier<Block> BEEF_WELLINGTON = registerBlock("beef_wellington", () -> new BeefWellingtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)), new Item.Properties().stacksTo(1));
    public static final Supplier<Block> FRUITCAKE = registerBlock("fruitcake", () -> new FruitcakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)), new Item.Properties().stacksTo(1));

    // OTHER
    // Wreaths
    public static final Supplier<Block> OAK_WREATH = registerBlock("oak_wreath", () -> createWreath(Blocks.OAK_LEAVES));
    public static final Supplier<Block> DARK_OAK_WREATH = registerBlock("dark_oak_wreath", () -> createWreath(Blocks.DARK_OAK_LEAVES));
    public static final Supplier<Block> BIRCH_WREATH = registerBlock("birch_wreath", () -> createWreath(Blocks.BIRCH_LEAVES));
    public static final Supplier<Block> SPRUCE_WREATH = registerBlock("spruce_wreath", () -> createWreath(Blocks.SPRUCE_LEAVES));
    public static final Supplier<Block> JUNGLE_WREATH = registerBlock("jungle_wreath", () -> createWreath(Blocks.JUNGLE_LEAVES));
    public static final Supplier<Block> ACACIA_WREATH = registerBlock("acacia_wreath", () -> createWreath(Blocks.ACACIA_LEAVES));
    public static final Supplier<Block> MANGROVE_WREATH = registerBlock("mangrove_wreath", () -> createWreath(Blocks.MANGROVE_LEAVES));
    public static final Supplier<Block> AZALEA_WREATH = registerBlock("azalea_wreath", () -> createWreathExt(BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWERING_AZALEA_LEAVES).lightLevel(block -> 14)));
    public static final Supplier<Block> CHERRY_WREATH = registerBlock("cherry_wreath", () -> createWreath(Blocks.CHERRY_LEAVES));
    public static final Supplier<Block> BLIGHTED_BIRCH_WREATH = registerBlock("blighted_birch_wreath", () -> createWreath(BLIGHTED_BIRCH_LEAVES.get()));
    // Lumens
    public static final Supplier<Block> DIAMOND_LUMEN = registerBlock("diamond_lumen", () -> new LumenBlock(BlockBehaviour.Properties.of().lightLevel(lumenLight(8, 15)).strength(0.3F).sound(SoundType.GLASS).instrument(NoteBlockInstrument.PLING).isValidSpawn(Blocks::never).mapColor(MapColor.DIAMOND)));
    public static final Supplier<Block> REDSTONE_LUMEN = registerBlock("redstone_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).lightLevel(lumenLight(4, 9)).mapColor(MapColor.COLOR_RED)));
    public static final Supplier<Block> AMETHYST_LUMEN = registerBlock("amethyst_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.COLOR_PURPLE)));
    public static final Supplier<Block> EMERALD_LUMEN = registerBlock("emerald_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.EMERALD)));
    public static final Supplier<Block> QUARTZ_LUMEN = registerBlock("quartz_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.QUARTZ)));
    public static final Supplier<Block> COBALT_LUMEN = registerBlock("cobalt_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.COLOR_BLUE)));
    public static final Supplier<Block> VERDINITE_LUMEN = registerBlock("verdinite_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.COLOR_LIGHT_GREEN)));
    public static final Supplier<Block> FROSTITE_LUMEN = registerBlock("frostite_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.ICE)));
    public static final Supplier<Block> VIVULITE_LUMEN = registerBlock("vivulite_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.COLOR_RED)));
    public static final Supplier<Block> BRIMTAN_LUMEN = registerBlock("brimtan_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).mapColor(MapColor.COLOR_ORANGE)), new Item.Properties().fireResistant());
    public static final Supplier<Block> ECHO_LUMEN = registerBlock("echo_lumen", () -> new LumenBlock(BlockBehaviour.Properties.ofFullCopy(DIAMOND_LUMEN.get()).lightLevel(lumenLight(4, 9)).mapColor(MapColor.WARPED_STEM)));
    // Corrupted Amethyst Cluster
    public static final Supplier<Block> CORRUPTED_AMETHYST_CLUSTER = registerBlock("corrupted_amethyst_cluster", () -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)));
    public static final Supplier<Block> LARGE_CORRUPTED_AMETHYST_BUD = registerBlock("large_corrupted_amethyst_bud", () -> new AmethystClusterBlock(5.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(CORRUPTED_AMETHYST_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel(state -> 4)));
    public static final Supplier<Block> MEDIUM_CORRUPTED_AMETHYST_BUD = registerBlock("medium_corrupted_amethyst_bud", () -> new AmethystClusterBlock(4.0F, 3.0F, BlockBehaviour.Properties.ofFullCopy(CORRUPTED_AMETHYST_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel(state -> 2)));
    public static final Supplier<Block> SMALL_CORRUPTED_AMETHYST_BUD = registerBlock("small_corrupted_amethyst_bud", () -> new AmethystClusterBlock(3.0F, 4.0F, BlockBehaviour.Properties.ofFullCopy(CORRUPTED_AMETHYST_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel(state -> 1)));
    // Sea Glasses
    public static final Supplier<Block> SEA_GLASS = registerBlock("sea_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).mapColor(MapColor.DIAMOND).lightLevel(state -> 3).emissiveRendering(Blocks::always).instrument(FRRegistries.NoteBlockInst.FRONTIERS_STEEL_DRUM)));
    public static final Supplier<Block> SEA_GLASS_PANE = registerBlock("sea_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE).mapColor(MapColor.DIAMOND).lightLevel(state -> 3).emissiveRendering(Blocks::always).instrument(FRRegistries.NoteBlockInst.FRONTIERS_STEEL_DRUM)));
    public static final Supplier<Block> PALE_SEA_GLASS = registerBlock("pale_sea_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(SEA_GLASS.get()).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final Supplier<Block> PALE_SEA_GLASS_PANE = registerBlock("pale_sea_glass_pane", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(SEA_GLASS_PANE.get()).mapColor(MapColor.TERRACOTTA_BROWN)));
    // Mob Models
    public static final Supplier<Block> CREEPER_MODEL = registerBlock("creeper_model", () -> new CreeperModelBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 5.0F).sound(SoundType.WOOD).noOcclusion()), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> SKELETON_MODEL = registerBlock("skeleton_model", () -> new SkeletonModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> STRAY_MODEL = registerBlock("stray_model", () -> new StrayModelBlock(BlockBehaviour.Properties.ofFullCopy(SKELETON_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> BOGGED_MODEL = registerBlock("bogged_model", () -> new BoggedModelBlock(BlockBehaviour.Properties.ofFullCopy(SKELETON_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> BLAZE_MODEL = registerBlock("blaze_model", () -> new BlazeModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get()).lightLevel(blazeModelLight(0, 12))), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> WITHER_SKELETON_MODEL = registerBlock("wither_skeleton_model", () -> new WitherSkeletonModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> ENDERMAN_MODEL = registerBlock("enderman_model", () -> new EndermanModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> SLIME_MODEL = registerBlock("slime_model", () -> new SlimeModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> MAGMA_CUBE_MODEL = registerBlock("magma_cube_model", () -> new MagmaCubeModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Supplier<Block> PHANTOM_MODEL = registerBlock("phantom_model", () -> new PhantomModelBlock(BlockBehaviour.Properties.ofFullCopy(CREEPER_MODEL.get())), new Item.Properties().rarity(Rarity.UNCOMMON));
    // Pumpkin / Melon blocks
    public static final Supplier<Block> GLISTERING_MELON = registerBlock("glistering_melon", () -> new GlisteringMelonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(1.5F).sound(SoundType.METAL).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> CARVED_MELON = registerBlock("carved_melon", () -> new WearableFruitBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).isValidSpawn(Blocks::always).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> JUNE_O_LANTERN = registerBlock("june_o_lantern", () -> new CarvedFruitBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD).lightLevel(state -> 15).isValidSpawn(Blocks::always).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> CARVED_GLISTERING_MELON = registerBlock("carved_glistering_melon", () -> new WearableFruitBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(1.5F).sound(SoundType.METAL).isValidSpawn(Blocks::always).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> GLISTERING_JUNE_O_LANTERN = registerBlock("glistering_june_o_lantern", () -> new CarvedFruitBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).strength(1.5F).sound(SoundType.METAL).lightLevel(state -> 15).isValidSpawn(Blocks::always).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> WHITE_PUMPKIN = registerBlock("white_pumpkin", () -> new WearableFruitBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(1.0F).sound(SoundType.WOOD).isValidSpawn(Blocks::always).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> WHITE_JACK_O_LANTERN = registerBlock("white_jack_o_lantern", () -> new CarvedFruitBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).strength(1.0F).sound(SoundType.WOOD).lightLevel(state -> 15).isValidSpawn(Blocks::always).pushReaction(PushReaction.DESTROY)));
    // Misc
    public static final Supplier<Block> SUGAR_CANE_BLOCK = registerBlock("sugar_cane_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).mapColor(MapColor.PLANT).strength(0.5F).sound(SoundType.GRASS)));
    public static final Supplier<Block> COCOA_BEAN_BLOCK = registerBlock("cocoa_bean_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.5F, 3.0F).sound(SoundType.WOOD)));
    public static final Supplier<Block> ROTTEN_FLESH_BLOCK = registerBlock("rotten_flesh_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).strength(0.5F, 1.0F).sound(SoundType.HONEY_BLOCK)));
    public static final Supplier<Block> EGG_PALLET = registerBlock("egg_pallet", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final Supplier<Block> GOLDEN_EGG_PALLET = registerBlock("golden_egg_pallet", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor(MapColor.GOLD)));
    public static final Supplier<Block> AESTHENOSTONE = registerBlock("aesthenostone", () -> new CoreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).mapColor(MapColor.TERRACOTTA_ORANGE).lightLevel(state -> 3).emissiveRendering(Blocks::always)));
    public static final Supplier<Block> ONYX_BONE_BLOCK = registerBlock("onyx_bone_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK).mapColor(MapColor.DEEPSLATE)));
    public static final Supplier<Block> SLIME_TRAIL = registerBlock("slime_trail", () -> new SlimeTrailBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).forceSolidOn().noCollission().strength(0.2F).friction(0.6F).sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> SLIME_BULB = registerBlock("slime_bulb", () -> new SlimeBulbBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).forceSolidOn().randomTicks().noCollission().strength(2.0F).sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<Block> CRAGS_PORTAL = registerBlockNoItem("crags_portal", () -> new CragsPortalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_NYLIUM).noCollission().forceSolidOn().lightLevel(state -> 5).strength(-1.0F, 3600000.0F).noLootTable().pushReaction(PushReaction.BLOCK)));

    // #############################################################################
    // Helpers

    /** Get light based on the Mod Property ACTIVE_POWER. */
    public static ToIntFunction<BlockState> strangeCoreLightHelper(int litLevel1, int litLevel2)
    {
        return state -> switch (state.getValue(FRBlockProperties.ACTIVE_POWER))
        {
            case 0 -> litLevel1;
            case 1 -> litLevel2;
            case 2 -> 0;
            default -> litLevel1;
        };
    }

    /** Does lighting for Lumen blocks. */
    public static ToIntFunction<BlockState> lumenLight(int power1, int power2)
    {
        return state -> switch (state.getValue(FRBlockProperties.LUMEN_POWER))
        {
            case 0 -> 0;
            case 1 -> power1;
            case 2 -> power2;
            default -> 0;
        };
    }

    /** Does lighting for Blaze Model. */
    public static ToIntFunction<BlockState> blazeModelLight(int power1, int power2) { return state -> (state.getValue(FRBlockProperties.MODEL_POWERED)) ? power2 : power1; }

    /** Does lighting for Tower Spawner. */
    public static ToIntFunction<BlockState> towerSpawnerLight() { return state -> (state.getValue(FRBlockProperties.ENRAGED) && !state.getValue(FRBlockProperties.DEFEATED)) ? 5 : 0; }

    /** Does emissive lighting for Tower Spawner. */
    public static boolean towerSpawnerEmis(BlockState state, BlockGetter blockGetter, BlockPos pos) { return state.getValue(FRBlockProperties.ENRAGED) && !state.getValue(FRBlockProperties.DEFEATED); }


    /** Creates a blighted log block */
    public static Block createBlightedLog(boolean day_switch, String target_block)
    {
        return new TimeSwitchLogBlock(
                day_switch,
                target_block,
                BlockBehaviour.Properties.of()
                        .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_PURPLE : MapColor.GOLD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
        );
    }

    /** Creates a blighted wood block */
    public static Block createBlightedWood()
    {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()
        );
    }

    /** Creates a wreath */
    public static Block createWreath(Block original)
    {
        return createWreathExt(BlockBehaviour.Properties.ofFullCopy(original));
    }

    public static Block createWreathExt(BlockBehaviour.Properties settings)
    {
        return new WreathBlock(settings);
    }

    // Helper classes for common blocks
    public static Block doStairs(Block type) { return new StairBlock(type.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(type)); }
    public static Block doSlab(Block type) { return new SlabBlock(BlockBehaviour.Properties.ofFullCopy(type)); }
    public static Block doFence(Block type) { return new FenceBlock(BlockBehaviour.Properties.ofFullCopy(type)); }
    public static Block doWall(Block type) { return new WallBlock(BlockBehaviour.Properties.ofFullCopy(type).forceSolidOn()); }
    public static Block doStoneGate(Block type) { return new StoneFenceGateBlock(BlockBehaviour.Properties.ofFullCopy(type)); }
    public static Block doWoodGate(WoodType wood, Block type) { return new FenceGateBlock(wood, BlockBehaviour.Properties.ofFullCopy(type)); }

    // #############################################################################

    private static Supplier<Block> registerBlock(String name, Supplier<Block> block)
    {
        return registerBlock(name, block, true);
    }

    private static Supplier<Block> registerBlock(String name, Supplier<Block> block, boolean autoTab)
    {
        Supplier<Block> returnable = VectorLib.REGISTRY.registerBlock(Frontiers.MOD_ID, name, block);
        if (autoTab) FRItemTabs.FRONTIERS_ITEMS.add(returnable);
        return returnable;
    }

    private static Supplier<Block> registerBlock(String name, Supplier<Block> block, Item.Properties settings)
    {
        return registerBlock(name, block, settings, true);
    }

    private static Supplier<Block> registerBlock(String name, Supplier<Block> block, Item.Properties settings, boolean autoTab)
    {
        Supplier<Block> returnable = VectorLib.REGISTRY.registerBlock(Frontiers.MOD_ID, name, block, settings);
        if (autoTab)FRItemTabs.FRONTIERS_ITEMS.add(returnable);
        return returnable;
    }

    private static Supplier<Block> registerBlockNoItem(String name, Supplier<Block> block)
    {
        return VectorLib.REGISTRY.registerBlockNoItem(Frontiers.MOD_ID, name, block);
    }

    public static void registerModBlocks()
    {

    }
}
