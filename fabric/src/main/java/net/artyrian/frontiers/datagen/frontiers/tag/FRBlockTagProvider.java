package net.artyrian.frontiers.datagen.frontiers.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class FRBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public FRBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // Mod tags.
    private void modBlockTag()
    {
        getOrCreateTagBuilder(FRTags.Blocks.INCORRECT_FOR_BRIMTAN_TOOL)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.INCORRECT_FOR_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
                .add(FRBlocks.CRAGULSTANE.get())
                .add(FRBlocks.BRIMTAN_ORE.get())
                .add(FRBlocks.BRIMTAN_BLOCK.get())

                .add(FRBlocks.CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .addTag(FRTags.Blocks.VIVULITE_ORES)
                .add(FRBlocks.VIVULITE_BLOCK.get())
                .add(FRBlocks.RAW_VIVULITE_BLOCK.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.INCORRECT_FOR_COBALT_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.NEEDS_COBALT_TOOL)
                .add(FRBlocks.STRANGE_CORE.get())
                .addTag(FRTags.Blocks.FROSTITE_ORES)
                .add(FRBlocks.FROSTITE_BLOCK.get())
                .add(FRBlocks.RAW_FROSTITE_BLOCK.get())
                .add(FRBlocks.VERDINITE_BLOCK.get())
                .add(FRBlocks.RAW_VERDINITE_BLOCK.get())
                .addTag(FRTags.Blocks.VERDINITE_ORES)

                .add(FRBlocks.TOWER_BRICKS.get())
                .add(FRBlocks.TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.TOWER_BRICK_SLAB.get())
                .add(FRBlocks.TOWER_BRICK_WALL.get())

                .add(FRBlocks.MOSSY_TOWER_BRICKS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_WALL.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.COBALT_ORES)
                .add(FRBlocks.COBALT_ORE.get())
                .add(FRBlocks.DEEPSLATE_COBALT_ORE.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.VERDINITE_ORES)
                .add(FRBlocks.VERDINITE_ORE.get())
                .add(FRBlocks.DEEPSLATE_VERDINITE_ORE.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.FROSTITE_ORES)
                .add(FRBlocks.FROSTITE_ORE.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.VIVULITE_ORES)
                .add(FRBlocks.VIVULITE_ORE.get())
                .add(FRBlocks.DEEPSLATE_VIVULITE_ORE.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.BLACK_EMERALD_ORES)
                .add(FRBlocks.BLACK_EMERALD_ORE.get())
                .add(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(FRBlocks.COBALT_BLOCK.get())
                .add(FRBlocks.RAW_COBALT_BLOCK.get())
                .addTag(FRTags.Blocks.COBALT_ORES)
                .add(FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get())
                .add(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.INFINIBURN_CRAGS)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.CONDUIT_BASE_BLOCKS)
                .add(Blocks.PRISMARINE)
                .add(Blocks.PRISMARINE_BRICKS)
                .add(Blocks.DARK_PRISMARINE)
                .add(FRBlocks.SEA_GLASS.get())
                .add(FRBlocks.PALE_PRISMARINE.get())
                .add(FRBlocks.PALE_PRISMARINE_BRICKS.get())
                .add(FRBlocks.DEEP_PALE_PRISMARINE.get())
                .add(FRBlocks.PALE_SEA_GLASS.get())
                .add(Blocks.SEA_LANTERN)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.ONLY_DROP_IN_HARDMODE)
                .addTag(FRTags.Blocks.COBALT_ORES)
                .addTag(FRTags.Blocks.VERDINITE_ORES)
                .addTag(FRTags.Blocks.VIVULITE_ORES)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.LUMENS)
                .add(FRBlocks.DIAMOND_LUMEN.get())
                .add(FRBlocks.QUARTZ_LUMEN.get())
                .add(FRBlocks.REDSTONE_LUMEN.get())
                .add(FRBlocks.EMERALD_LUMEN.get())
                .add(FRBlocks.AMETHYST_LUMEN.get())
                .add(FRBlocks.COBALT_LUMEN.get())
                .add(FRBlocks.FROSTITE_LUMEN.get())
                .add(FRBlocks.VERDINITE_LUMEN.get())
                .add(FRBlocks.VIVULITE_LUMEN.get())
                .add(FRBlocks.BRIMTAN_LUMEN.get())
                .add(FRBlocks.ECHO_LUMEN.get())

                // Compat items
                .addOptional(Frontiers.id("feldspar_lumen"))
        ;
        getOrCreateTagBuilder(FRTags.Blocks.STONE_FENCE_GATES)
                .add(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get())
                .add(FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get())
                .add(FRBlocks.NETHER_BRICK_FENCE_GATE.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.ENTITY_MODELS)
            .add(FRBlocks.CREEPER_MODEL.get())
            .add(FRBlocks.SKELETON_MODEL.get())
            .add(FRBlocks.STRAY_MODEL.get())
            .add(FRBlocks.BOGGED_MODEL.get())
            .add(FRBlocks.BLAZE_MODEL.get())
            .add(FRBlocks.WITHER_SKELETON_MODEL.get())
            .add(FRBlocks.ENDERMAN_MODEL.get())
            .add(FRBlocks.SLIME_MODEL.get())
            .add(FRBlocks.MAGMA_CUBE_MODEL.get())
            .add(FRBlocks.PHANTOM_MODEL.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT)
                // Farmer's Delight
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "rice_panicles"))
        ;
        getOrCreateTagBuilder(FRTags.Blocks.CROW_CAN_SPAWN_ON)
                .add(Blocks.GRASS_BLOCK, Blocks.AIR)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.LOGS)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.PUMPKIN_GOLEM_PICKABLE)
                // No-replant tag
                .addTag(FRTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT)

                // Vanilla
                .add(Blocks.WHEAT)
                .add(Blocks.POTATOES)
                .add(Blocks.BEETROOTS)
                .add(Blocks.CARROTS)
                .add(Blocks.NETHER_WART)

                // Frontiers
                .add(FRBlocks.WARPED_WART.get())
                .add(FRBlocks.EXPERIWINKLE.get())

                // Farmer's Delight
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "cabbages"))
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "onions"))

                // Rustic Delight
                .addOptional(Frontiers.id("rusticdelight", "cotton"))
                .addOptional(Frontiers.id("rusticdelight", "bell_peppers"))
                .addOptional(Frontiers.id("rusticdelight", "coffee"))

                // Supplementaries
                .addOptional(Frontiers.id(Frontiers.SUPPLEMENTARIES_ID, "flax"))

                // Bountiful Fares
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "leeks"))
                .addOptional(Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "maize_crop"))
        ;
        getOrCreateTagBuilder(FRTags.Blocks.ONYX_MEAL_DECAYABLE)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.FERN)
                .add(Blocks.LARGE_FERN)
                .add(Blocks.DEAD_BUSH)
                .add(Blocks.VINE)
        ;
        getOrCreateTagBuilder(FRTags.Blocks.PREVENTS_FLUID_FLOW)
                .add(Blocks.NETHER_PORTAL)
                .add(Blocks.END_PORTAL)
                .add(Blocks.END_GATEWAY)
                .add(Blocks.STRUCTURE_VOID)
                .add(FRBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.EBONCORK_LOGS)
                .add(FRBlocks.EBONCORK.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.BLIGHTED_BIRCH_LOGS)
                .add(FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get())
                .add(FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get())
                .add(FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get())
                .add(FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get())
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get())
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get())
        ;
        getOrCreateTagBuilder(FRTags.Blocks.WREATHS)
                .add(FRBlocks.OAK_WREATH.get())
                .add(FRBlocks.DARK_OAK_WREATH.get())
                .add(FRBlocks.BIRCH_WREATH.get())
                .add(FRBlocks.SPRUCE_WREATH.get())
                .add(FRBlocks.JUNGLE_WREATH.get())
                .add(FRBlocks.ACACIA_WREATH.get())
                .add(FRBlocks.MANGROVE_WREATH.get())
                .add(FRBlocks.AZALEA_WREATH.get())
                .add(FRBlocks.CHERRY_WREATH.get())
                .add(FRBlocks.BLIGHTED_BIRCH_WREATH.get())

                .addOptional(Frontiers.id("hoary_wreath"))
                .addOptional(Frontiers.id("walnut_wreath"))
                .addOptional(Frontiers.id("apple_wreath"))
                .addOptional(Frontiers.id("orange_wreath"))
                .addOptional(Frontiers.id("lemon_wreath"))
                .addOptional(Frontiers.id("plum_wreath"))
                .addOptional(Frontiers.id("golden_wreath"))
        ;
        getOrCreateTagBuilder(FRTags.Blocks.TOWER_WATCHABLES)
                .add(FRBlocks.TOWER_SPAWNER.get())
                .add(FRBlocks.TOWER_HEART.get())
                .add(FRBlocks.TOWER_KEY_VAULT.get())
                .add(FRBlocks.TOWER_TREASURE_VAULT.get())
                .add(FRBlocks.TOWER_WATCHER.get())

                .add(FRBlocks.TOWER_BRICKS.get())
                .add(FRBlocks.TOWER_BRICK_SLAB.get())
                .add(FRBlocks.TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.TOWER_BRICK_WALL.get())

                .add(FRBlocks.MOSSY_TOWER_BRICKS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_WALL.get())
        ;
    }

    // Vanilla tags.
    private void vanillaBlockTag()
    {
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(FRBlocks.COBALT_BLOCK.get())
                .add(FRBlocks.FROSTITE_BLOCK.get())
                .add(FRBlocks.MOURNING_GOLD_BLOCK.get())
                .add(FRBlocks.VIVULITE_BLOCK.get())
                .add(FRBlocks.VERDINITE_BLOCK.get())
                .add(FRBlocks.BRIMTAN_BLOCK.get())
                .add(FRBlocks.BLACK_EMERALD_BLOCK.get())
        ;
        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(FRBlocks.ANCIENT_ROSE_CROP.get())
                .add(FRBlocks.EXPERIWINKLE_CROP.get())
        ;
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(FRBlocks.ANCIENT_ROSE.get())
                .add(FRBlocks.ROSE.get())
                .add(FRBlocks.VIOLET_ROSE.get())
                .add(FRBlocks.SNOW_DAHLIA.get())
                .add(FRBlocks.FUNGAL_DAFFODIL.get())
                .add(FRBlocks.CRIMCONE.get())
                .add(FRBlocks.EXPERIWINKLE.get())
        ;
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
                .add(FRBlocks.ANCIENT_ROSE_BUSH.get())
                .add(FRBlocks.VIOLET_ROSE_BUSH.get())
        ;
        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
                .add(FRBlocks.CRAGS_PORTAL.get())
                .add(FRBlocks.PERSONAL_CHEST.get())
        ;
        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
                .add(FRBlocks.CRAGS_PORTAL.get())
                .add(FRBlocks.PERSONAL_CHEST.get())
                .add(FRBlocks.COBALT_GRILLES.get())
        ;
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(FRBlocks.ANCIENT_ROSE_CROP.get())
                .add(FRBlocks.ANCIENT_ROSE.get())
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_NETHER)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_END)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
                .add(FRBlocks.QUICKSAND.get())
                .add(FRBlocks.RED_QUICKSAND.get())
                .add(FRBlocks.CRAGULSTANE.get())
        ;
        getOrCreateTagBuilder(BlockTags.IMPERMEABLE)
                .add(FRBlocks.SEA_GLASS.get())
                .add(FRBlocks.PALE_SEA_GLASS.get())
        ;
        getOrCreateTagBuilder(BlockTags.PORTALS)
                .add(FRBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(BlockTags.BEDS)
                .add(FRBlocks.PHANTOM_STITCH_BED.get())
        ;
        getOrCreateTagBuilder(BlockTags.INVALID_SPAWN_INSIDE)
                .add(FRBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(BlockTags.HOGLIN_REPELLENTS)
                .add(FRBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS)
                .add(FRBlocks.CRAGS_PORTAL.get())
        ;

        // Slabs
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(FRBlocks.BLUE_NETHER_BRICK_SLAB.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.NACRE_BRICK_SLAB.get())
                .add(FRBlocks.TOWER_BRICK_SLAB.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(FRBlocks.PALE_PRISMARINE_SLAB.get())
                .add(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get())
                .add(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get())

                .add(FRBlocks.HIELOSTONE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_BRICK_SLAB.get())
                .add(FRBlocks.HIELOSTONE_TILE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_PLATE_SLAB.get())
                .add(FRBlocks.COBBLEFROST_SLAB.get())
                .add(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get())

                .add(FRBlocks.GOLDEN_EGG_PALLET.get())
                .add(FRBlocks.EGG_PALLET.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(FRBlocks.EBONCORK_SLAB.get())
                .add(FRBlocks.BLIGHTED_BIRCH_SLAB.get())
        ;
        // Stairs
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.NACRE_BRICK_STAIRS.get())
                .add(FRBlocks.TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.PALE_PRISMARINE_STAIRS.get())
                .add(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get())
                .add(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get())

                .add(FRBlocks.HIELOSTONE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_BRICK_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_TILE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_PLATE_STAIRS.get())
                .add(FRBlocks.COBBLEFROST_STAIRS.get())
                .add(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(FRBlocks.EBONCORK_STAIRS.get())
                .add(FRBlocks.BLIGHTED_BIRCH_STAIRS.get())
        ;
        // Walls
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(FRBlocks.BLUE_NETHER_BRICK_WALL.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_WALL.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.NACRE_BRICK_WALL.get())
                .add(FRBlocks.TOWER_BRICK_WALL.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_WALL.get())
                .add(FRBlocks.PALE_PRISMARINE_WALL.get())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_WALL.get())

                .add(FRBlocks.HIELOSTONE_WALL.get())
                .add(FRBlocks.HIELOSTONE_BRICK_WALL.get())
                .add(FRBlocks.HIELOSTONE_TILE_WALL.get())
                .add(FRBlocks.HIELOSTONE_PLATE_WALL.get())
                .add(FRBlocks.COBBLEFROST_WALL.get())
                .add(FRBlocks.CRUSTY_SAND_BRICK_WALL.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get())
        ;
        // Fences
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(FRBlocks.BLUE_NETHER_BRICK_FENCE.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get())
                .add(FRBlocks.RED_NETHER_BRICK_FENCE.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(FRBlocks.EBONCORK_FENCE.get())
                .add(FRBlocks.BLIGHTED_BIRCH_FENCE.get())
        ;
        // Fence Gates
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get())
                .add(FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get())
                .add(FRBlocks.NETHER_BRICK_FENCE_GATE.get())

                .add(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get())
                .add(FRBlocks.EBONCORK_FENCE_GATE.get())
        ;
        // Pressure Plates + Buttons
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(FRBlocks.EBONCORK_PRESSURE_PLATE.get())
                .add(FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(FRBlocks.EBONCORK_BUTTON.get())
                .add(FRBlocks.BLIGHTED_BIRCH_BUTTON.get())
        ;
        getOrCreateTagBuilder(BlockTags.ANVIL)
                .add(FRBlocks.VIVULITE_ANVIL.get())
        ;
        // Doors + Trapdoors
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(FRBlocks.EBONCORK_DOOR.get())
                .add(FRBlocks.BLIGHTED_BIRCH_DOOR.get());
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(FRBlocks.EBONCORK_TRAPDOOR.get())
                .add(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());
        ;

        // Needs tools
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .addTag(FRTags.Blocks.BLACK_EMERALD_ORES)
                .add(FRBlocks.BLACK_EMERALD_BLOCK.get())
                .add(FRBlocks.MOURNING_GOLD_BLOCK.get())

                .add(FRBlocks.COBBLEFROST.get())
                .add(FRBlocks.COBBLEFROST_STAIRS.get())
                .add(FRBlocks.COBBLEFROST_SLAB.get())
                .add(FRBlocks.COBBLEFROST_WALL.get())

                .add(FRBlocks.CURSE_ALTAR.get())
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(FRBlocks.HIELOSTONE.get())
                .add(FRBlocks.HIELOSTONE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_WALL.get())

                .add(FRBlocks.HIELOSTONE_BRICKS.get())
                .add(FRBlocks.HIELOSTONE_BRICK_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_BRICK_SLAB.get())
                .add(FRBlocks.HIELOSTONE_BRICK_WALL.get())

                .add(FRBlocks.HIELOSTONE_TILES.get())
                .add(FRBlocks.HIELOSTONE_TILE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_TILE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_TILE_WALL.get())

                .add(FRBlocks.HIELOSTONE_PLATES.get())
                .add(FRBlocks.HIELOSTONE_PLATE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_PLATE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_PLATE_WALL.get())
        ;

        // Incorrect tools.
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_COBALT_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(FRTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;

        // Mineables
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(FRBlocks.SUGAR_CANE_BLOCK.get())
                .add(FRBlocks.BLIGHTED_BIRCH_LEAVES.get())
                .addTag(FRTags.Blocks.WREATHS)
        ;
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(FRBlocks.WARPED_WART.get())
                .addTag(FRTags.Blocks.ENTITY_MODELS)

                .add(FRBlocks.COCOA_BEAN_BLOCK.get())
                .add(FRBlocks.GLISTERING_MELON.get())
                .add(FRBlocks.CARVED_MELON.get())
                .add(FRBlocks.CARVED_GLISTERING_MELON.get())
                .add(FRBlocks.JUNE_O_LANTERN.get())
                .add(FRBlocks.GLISTERING_JUNE_O_LANTERN.get())
                .add(FRBlocks.WHITE_PUMPKIN.get())
                .add(FRBlocks.WHITE_JACK_O_LANTERN.get())
                .add(FRBlocks.FUNGAL_DAFFODIL_BLOCK.get())

                .add(FRBlocks.EBONCORK.get())
                .add(FRBlocks.EBONCORK_PLANKS.get())
                .add(FRBlocks.EBONCORK_STAIRS.get())
                .add(FRBlocks.EBONCORK_SLAB.get())
                .add(FRBlocks.EBONCORK_FENCE.get())
                .add(FRBlocks.EBONCORK_FENCE_GATE.get())

                .add(FRBlocks.BLIGHTED_BIRCH_PLANKS.get())
                .add(FRBlocks.BLIGHTED_BIRCH_STAIRS.get())
                .add(FRBlocks.BLIGHTED_BIRCH_SLAB.get())
                .add(FRBlocks.BLIGHTED_BIRCH_FENCE.get())
                .add(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get())
                .addTag(FRTags.Blocks.BLIGHTED_BIRCH_LOGS)

                .add(FRBlocks.ROTTEN_FLESH_BLOCK.get())
                .add(FRBlocks.EGG_PALLET.get())
                .add(FRBlocks.GOLDEN_EGG_PALLET.get())
        ;
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(FRBlocks.QUICKSAND.get())
                .add(FRBlocks.RED_QUICKSAND.get())
                .add(FRBlocks.SLIME_TRAIL.get())
                .add(FRBlocks.CRUSTED_QUICKSAND.get())
                .add(FRBlocks.CRUSTED_RED_QUICKSAND.get())
        ;
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(FRBlocks.COBALT_BLOCK.get())
                .add(FRBlocks.RAW_COBALT_BLOCK.get())
                .addTag(FRTags.Blocks.COBALT_ORES)
                .addTag(FRTags.Blocks.VERDINITE_ORES)
                .add(FRBlocks.VIVULITE_BLOCK.get())
                .add(FRBlocks.RAW_VIVULITE_BLOCK.get())
                .add(FRBlocks.VERDINITE_BLOCK.get())
                .add(FRBlocks.RAW_VERDINITE_BLOCK.get())
                .addTag(FRTags.Blocks.VIVULITE_ORES)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
                .add(FRBlocks.STRANGE_CORE.get())
                .add(FRBlocks.BLACK_EMERALD_BLOCK.get())
                .addTag(FRTags.Blocks.BLACK_EMERALD_ORES)
                .add(FRBlocks.MOURNING_GOLD_BLOCK.get())
                .addTag(FRTags.Blocks.FROSTITE_ORES)
                .add(FRBlocks.FROSTITE_BLOCK.get())
                .add(FRBlocks.RAW_FROSTITE_BLOCK.get())
                .add(FRBlocks.ONYX_BONE_BLOCK.get())
                .add(FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get())
                .add(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.COBALT_GRILLES.get())

                .add(FRBlocks.PERSONAL_CHEST.get())
                .add(FRBlocks.CURSE_ALTAR.get())
                .add(FRBlocks.MONSTER_BAKERY.get())
                .add(FRBlocks.ITEM_VACUUM.get())

                .add(FRBlocks.TOWER_WATCHER.get())
                .add(FRBlocks.TOWER_HEART.get())
                .add(FRBlocks.TOWER_SPAWNER.get())
                .add(FRBlocks.TOWER_KEY_VAULT.get())
                .add(FRBlocks.TOWER_TREASURE_VAULT.get())

                .add(FRBlocks.HIELOSTONE.get())
                .add(FRBlocks.HIELOSTONE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_WALL.get())
                .add(FRBlocks.HIELOSTONE_BRICKS.get())
                .add(FRBlocks.HIELOSTONE_BRICK_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_BRICK_SLAB.get())
                .add(FRBlocks.HIELOSTONE_BRICK_WALL.get())
                .add(FRBlocks.HIELOSTONE_TILES.get())
                .add(FRBlocks.HIELOSTONE_TILE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_TILE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_TILE_WALL.get())
                .add(FRBlocks.HIELOSTONE_PLATES.get())
                .add(FRBlocks.HIELOSTONE_PLATE_STAIRS.get())
                .add(FRBlocks.HIELOSTONE_PLATE_SLAB.get())
                .add(FRBlocks.HIELOSTONE_PLATE_WALL.get())
                .add(FRBlocks.COBBLEFROST.get())
                .add(FRBlocks.COBBLEFROST_STAIRS.get())
                .add(FRBlocks.COBBLEFROST_SLAB.get())
                .add(FRBlocks.COBBLEFROST_WALL.get())

                .add(FRBlocks.NACRE_BRICKS.get())
                .add(FRBlocks.NACRE_BRICK_STAIRS.get())
                .add(FRBlocks.NACRE_BRICK_SLAB.get())
                .add(FRBlocks.NACRE_BRICK_WALL.get())

                .add(FRBlocks.TOWER_BRICKS.get())
                .add(FRBlocks.TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.TOWER_BRICK_SLAB.get())
                .add(FRBlocks.TOWER_BRICK_WALL.get())

                .add(FRBlocks.MOSSY_TOWER_BRICKS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(FRBlocks.MOSSY_TOWER_BRICK_WALL.get())

                .add(FRBlocks.TURTLE_SCUTE_BRICKS.get())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get())
                .add(FRBlocks.TURTLE_SCUTE_BRICK_WALL.get())

                .add(FRBlocks.CRAGULSTANE.get())
                .add(FRBlocks.BRIMTAN_ORE.get())
                .add(FRBlocks.BRIMTAN_BLOCK.get())
                .add(FRBlocks.CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get())
                .add(FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get())
                .add(FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get())

                .add(FRBlocks.BLUE_NETHER_BRICKS.get())
                .add(FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get())
                .add(FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get())
                .add(FRBlocks.BLUE_NETHER_BRICK_STAIRS.get())
                .add(FRBlocks.BLUE_NETHER_BRICK_SLAB.get())
                .add(FRBlocks.BLUE_NETHER_BRICK_WALL.get())
                .add(FRBlocks.BLUE_NETHER_BRICK_FENCE.get())

                .add(FRBlocks.PURPLE_NETHER_BRICKS.get())
                .add(FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get())
                .add(FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_SLAB.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_WALL.get())
                .add(FRBlocks.PURPLE_NETHER_BRICK_FENCE.get())

                .add(FRBlocks.CRACKED_RED_NETHER_BRICKS.get())
                .add(FRBlocks.CHISELED_RED_NETHER_BRICKS.get())
                .add(FRBlocks.RED_NETHER_BRICK_FENCE.get())

                .add(FRBlocks.PALE_PRISMARINE.get())
                .add(FRBlocks.PALE_PRISMARINE_STAIRS.get())
                .add(FRBlocks.PALE_PRISMARINE_SLAB.get())
                .add(FRBlocks.PALE_PRISMARINE_WALL.get())
                .add(FRBlocks.PALE_PRISMARINE_BRICKS.get())
                .add(FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get())
                .add(FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get())
                .add(FRBlocks.DEEP_PALE_PRISMARINE.get())
                .add(FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get())
                .add(FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get())

                .add(FRBlocks.CRUSTED_QUICKSAND.get())
                .add(FRBlocks.CRUSTED_RED_QUICKSAND.get())
                .add(FRBlocks.CRUSTY_SAND_BRICKS.get())
                .add(FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get())
                .add(FRBlocks.CRUSTY_SAND_BRICK_SLAB.get())
                .add(FRBlocks.CRUSTY_SAND_BRICK_WALL.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICKS.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get())
                .add(FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get())

                .addTag(FRTags.Blocks.STONE_FENCE_GATES)
        ;
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(FRBlocks.GLISTERING_MELON.get())
                .add(FRBlocks.CARVED_MELON.get())
                .add(FRBlocks.CARVED_GLISTERING_MELON.get())
                .add(FRBlocks.JUNE_O_LANTERN.get())
                .add(FRBlocks.GLISTERING_JUNE_O_LANTERN.get())
                .add(FRBlocks.WHITE_PUMPKIN.get())
                .add(FRBlocks.WHITE_JACK_O_LANTERN.get())

                .add(FRBlocks.WARPED_WART.get())
        ;

        // Extra
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(FRBlocks.EBONCORK_PLANKS.get())
                .add(FRBlocks.BLIGHTED_BIRCH_PLANKS.get())
        ;
        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(FRBlocks.EBONCORK.get())
        ;
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(FRTags.Blocks.BLIGHTED_BIRCH_LOGS)
        ;
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(FRBlocks.BLIGHTED_BIRCH_SAPLING.get())
        ;
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(FRBlocks.BLIGHTED_BIRCH_LEAVES.get())
        ;
        getOrCreateTagBuilder(BlockTags.SMELTS_TO_GLASS)
                .add(FRBlocks.QUICKSAND.get())
                .add(FRBlocks.RED_QUICKSAND.get())
        ;
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
        ;
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
                .add(FRBlocks.SLIME_TRAIL.get())
        ;
        getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN)
                .add(FRBlocks.BLACK_EMERALD_ORE.get())
        ;
        getOrCreateTagBuilder(BlockTags.FEATURES_CANNOT_REPLACE)
                .add(FRBlocks.AESTHENOSTONE.get())
        ;
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(FRBlocks.POTTED_CRIMCONE.get())
                .add(FRBlocks.POTTED_FUNGAL_DAFFODIL.get())
                .add(FRBlocks.POTTED_ANCIENT_ROSE.get())
                .add(FRBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get())
                .add(FRBlocks.POTTED_ROSE.get())
                .add(FRBlocks.POTTED_VIOLET_ROSE.get())
                .add(FRBlocks.POTTED_SNOW_DAHLIA.get())
                .add(FRBlocks.POTTED_EXPERIWINKLE.get())
        ;
    }

    // Common tags.
    private void commonBlockTag()
    {
        getOrCreateTagBuilder(ConventionalBlockTags.STONES)
                .add(FRBlocks.HIELOSTONE.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES)
                .add(FRBlocks.COBBLEFROST.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.OBSIDIANS)
                .add(FRBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.ORES)
                .add(FRBlocks.COBALT_ORE.get())
                .add(FRBlocks.DEEPSLATE_COBALT_ORE.get())
                .add(FRBlocks.VERDINITE_ORE.get())
                .add(FRBlocks.DEEPSLATE_VERDINITE_ORE.get())
                .add(FRBlocks.FROSTITE_ORE.get())
                .add(FRBlocks.DEEPSLATE_VIVULITE_ORE.get())
                .add(FRBlocks.VIVULITE_ORE.get())
                .add(FRBlocks.BRIMTAN_ORE.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.CHESTS)
                .add(FRBlocks.PERSONAL_CHEST.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.GLASS_BLOCKS)
                .add(FRBlocks.SEA_GLASS.get())
                .add(FRBlocks.PALE_SEA_GLASS.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.GLASS_PANES)
                .add(FRBlocks.SEA_GLASS_PANE.get())
                .add(FRBlocks.PALE_SEA_GLASS_PANE.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.BUDS)
                .add(FRBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get())
                .add(FRBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.CLUSTERS)
                .add(FRBlocks.CORRUPTED_AMETHYST_CLUSTER.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
                .add(FRBlocks.BRIMTAN_BLOCK.get())
                .add(FRBlocks.BLACK_EMERALD_BLOCK.get())
                .add(FRBlocks.COBALT_BLOCK.get())
                .add(FRBlocks.FROSTITE_BLOCK.get())
                .add(FRBlocks.COCOA_BEAN_BLOCK.get())
                .add(FRBlocks.ROTTEN_FLESH_BLOCK.get())
                .add(FRBlocks.MOURNING_GOLD_BLOCK.get())
                .add(FRBlocks.NECRO_WEAVE_BLOCK.get())
                .add(FRBlocks.RAW_COBALT_BLOCK.get())
                .add(FRBlocks.RAW_FROSTITE_BLOCK.get())
                .add(FRBlocks.RAW_VERDINITE_BLOCK.get())
                .add(FRBlocks.RAW_VIVULITE_BLOCK.get())
                .add(FRBlocks.SUGAR_CANE_BLOCK.get())
                .add(FRBlocks.VERDINITE_BLOCK.get())
                .add(FRBlocks.VIVULITE_BLOCK.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_LOGS)
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_WOODS)
                .add(FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get())
        ;
        // Custom C
        getOrCreateTagBuilder(FRTags.Blocks.C_RAW_BLOCKS)
                .add(FRBlocks.RAW_COBALT_BLOCK.get())
                .add(FRBlocks.RAW_FROSTITE_BLOCK.get())
                .add(FRBlocks.RAW_VERDINITE_BLOCK.get())
                .add(FRBlocks.RAW_VIVULITE_BLOCK.get())
        ;
    }

    private void extraBlockTag()
    {
        getOrCreateTagBuilder(FRTags.Blocks.QUARK_SIMPLE_HARVEST_NOGO)
                .add(FRBlocks.ANCIENT_ROSE.get())
                .add(FRBlocks.ANCIENT_ROSE_CROP.get())
                .add(FRBlocks.EXPERIWINKLE.get())
                .add(FRBlocks.EXPERIWINKLE_CROP.get())
        ;
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup)
    {
        modBlockTag();
        vanillaBlockTag();
        commonBlockTag();
        extraBlockTag();
    }
}
