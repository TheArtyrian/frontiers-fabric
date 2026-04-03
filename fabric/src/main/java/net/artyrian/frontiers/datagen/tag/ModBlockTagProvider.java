package net.artyrian.frontiers.datagen.tag;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // Mod tags.
    private void modBlockTag()
    {
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_BRIMTAN_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
                .add(ModBlocks.CRAGULSTANE.get())
                .add(ModBlocks.BRIMTAN_ORE.get())
                .add(ModBlocks.BRIMTAN_BLOCK.get())

                .add(ModBlocks.CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .addTag(ModTags.Blocks.VIVULITE_ORES)
                .add(ModBlocks.VIVULITE_BLOCK.get())
                .add(ModBlocks.RAW_VIVULITE_BLOCK.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .add(ModBlocks.STRANGE_CORE.get())
                .addTag(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_BLOCK.get())
                .add(ModBlocks.RAW_FROSTITE_BLOCK.get())
                .add(ModBlocks.VERDINITE_BLOCK.get())
                .add(ModBlocks.RAW_VERDINITE_BLOCK.get())
                .addTag(ModTags.Blocks.VERDINITE_ORES)

                .add(ModBlocks.TOWER_BRICKS.get())
                .add(ModBlocks.TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.TOWER_BRICK_SLAB.get())
                .add(ModBlocks.TOWER_BRICK_WALL.get())

                .add(ModBlocks.MOSSY_TOWER_BRICKS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_WALL.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.VERDINITE_ORES)
                .add(ModBlocks.VERDINITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_VERDINITE_ORE.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_ORE.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.VIVULITE_ORES)
                .add(ModBlocks.VIVULITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_VIVULITE_ORE.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.BLACK_EMERALD_ORES)
                .add(ModBlocks.BLACK_EMERALD_ORE.get())
                .add(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.RAW_COBALT_BLOCK.get())
                .addTag(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get())
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INFINIBURN_CRAGS)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.CONDUIT_BASE_BLOCKS)
                .add(Blocks.PRISMARINE)
                .add(Blocks.PRISMARINE_BRICKS)
                .add(Blocks.DARK_PRISMARINE)
                .add(ModBlocks.SEA_GLASS.get())
                .add(ModBlocks.PALE_PRISMARINE.get())
                .add(ModBlocks.PALE_PRISMARINE_BRICKS.get())
                .add(ModBlocks.DEEP_PALE_PRISMARINE.get())
                .add(ModBlocks.PALE_SEA_GLASS.get())
                .add(Blocks.SEA_LANTERN)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ONLY_DROP_IN_HARDMODE)
                .addTag(ModTags.Blocks.COBALT_ORES)
                .addTag(ModTags.Blocks.VERDINITE_ORES)
                .addTag(ModTags.Blocks.VIVULITE_ORES)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.LUMENS)
                .add(ModBlocks.DIAMOND_LUMEN.get())
                .add(ModBlocks.QUARTZ_LUMEN.get())
                .add(ModBlocks.REDSTONE_LUMEN.get())
                .add(ModBlocks.EMERALD_LUMEN.get())
                .add(ModBlocks.AMETHYST_LUMEN.get())
                .add(ModBlocks.COBALT_LUMEN.get())
                .add(ModBlocks.FROSTITE_LUMEN.get())
                .add(ModBlocks.VERDINITE_LUMEN.get())
                .add(ModBlocks.VIVULITE_LUMEN.get())
                .add(ModBlocks.BRIMTAN_LUMEN.get())
                .add(ModBlocks.ECHO_LUMEN.get())

                // Compat items
                .addOptional(Frontiers.id("feldspar_lumen"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.STONE_FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ENTITY_MODELS)
            .add(ModBlocks.CREEPER_MODEL.get())
            .add(ModBlocks.SKELETON_MODEL.get())
            .add(ModBlocks.STRAY_MODEL.get())
            .add(ModBlocks.BOGGED_MODEL.get())
            .add(ModBlocks.BLAZE_MODEL.get())
            .add(ModBlocks.WITHER_SKELETON_MODEL.get())
            .add(ModBlocks.ENDERMAN_MODEL.get())
            .add(ModBlocks.SLIME_MODEL.get())
            .add(ModBlocks.MAGMA_CUBE_MODEL.get())
            .add(ModBlocks.PHANTOM_MODEL.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT)
                // Farmer's Delight
                .addOptional(Frontiers.id(Frontiers.FARMERS_DELIGHT_ID, "rice_panicles"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.CROW_CAN_SPAWN_ON)
                .add(Blocks.GRASS_BLOCK, Blocks.AIR)
                .addTag(BlockTags.LEAVES)
                .addTag(BlockTags.LOGS)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.PUMPKIN_GOLEM_PICKABLE)
                // No-replant tag
                .addTag(ModTags.Blocks.PUMPKIN_GOLEM_NO_REPLANT)

                // Vanilla
                .add(Blocks.WHEAT)
                .add(Blocks.POTATOES)
                .add(Blocks.BEETROOTS)
                .add(Blocks.CARROTS)
                .add(Blocks.NETHER_WART)

                // Frontiers
                .add(ModBlocks.WARPED_WART.get())
                .add(ModBlocks.EXPERIWINKLE.get())

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
        getOrCreateTagBuilder(ModTags.Blocks.ONYX_MEAL_DECAYABLE)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.FERN)
                .add(Blocks.LARGE_FERN)
                .add(Blocks.DEAD_BUSH)
                .add(Blocks.VINE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.PREVENTS_FLUID_FLOW)
                .add(Blocks.NETHER_PORTAL)
                .add(Blocks.END_PORTAL)
                .add(Blocks.END_GATEWAY)
                .add(Blocks.STRUCTURE_VOID)
                .add(ModBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.EBONCORK_LOGS)
                .add(ModBlocks.EBONCORK.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.BLIGHTED_BIRCH_LOGS)
                .add(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get())
                .add(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get())
                .add(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get())
                .add(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get())
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get())
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get())
        ;
        getOrCreateTagBuilder(ModTags.Blocks.WREATHS)
                .add(ModBlocks.OAK_WREATH.get())
                .add(ModBlocks.DARK_OAK_WREATH.get())
                .add(ModBlocks.BIRCH_WREATH.get())
                .add(ModBlocks.SPRUCE_WREATH.get())
                .add(ModBlocks.JUNGLE_WREATH.get())
                .add(ModBlocks.ACACIA_WREATH.get())
                .add(ModBlocks.MANGROVE_WREATH.get())
                .add(ModBlocks.AZALEA_WREATH.get())
                .add(ModBlocks.CHERRY_WREATH.get())
                .add(ModBlocks.BLIGHTED_BIRCH_WREATH.get())

                .addOptional(Frontiers.id("hoary_wreath"))
                .addOptional(Frontiers.id("walnut_wreath"))
                .addOptional(Frontiers.id("apple_wreath"))
                .addOptional(Frontiers.id("orange_wreath"))
                .addOptional(Frontiers.id("lemon_wreath"))
                .addOptional(Frontiers.id("plum_wreath"))
                .addOptional(Frontiers.id("golden_wreath"))
        ;
        getOrCreateTagBuilder(ModTags.Blocks.TOWER_WATCHABLES)
                .add(ModBlocks.TOWER_SPAWNER.get())
                .add(ModBlocks.TOWER_HEART.get())
                .add(ModBlocks.TOWER_KEY_VAULT.get())
                .add(ModBlocks.TOWER_TREASURE_VAULT.get())
                .add(ModBlocks.TOWER_WATCHER.get())

                .add(ModBlocks.TOWER_BRICKS.get())
                .add(ModBlocks.TOWER_BRICK_SLAB.get())
                .add(ModBlocks.TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.TOWER_BRICK_WALL.get())

                .add(ModBlocks.MOSSY_TOWER_BRICKS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_WALL.get())
        ;
    }

    // Vanilla tags.
    private void vanillaBlockTag()
    {
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.FROSTITE_BLOCK.get())
                .add(ModBlocks.MOURNING_GOLD_BLOCK.get())
                .add(ModBlocks.VIVULITE_BLOCK.get())
                .add(ModBlocks.VERDINITE_BLOCK.get())
                .add(ModBlocks.BRIMTAN_BLOCK.get())
                .add(ModBlocks.BLACK_EMERALD_BLOCK.get())
        ;
        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.ANCIENT_ROSE_CROP.get())
                .add(ModBlocks.EXPERIWINKLE_CROP.get())
        ;
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.ANCIENT_ROSE.get())
                .add(ModBlocks.ROSE.get())
                .add(ModBlocks.VIOLET_ROSE.get())
                .add(ModBlocks.SNOW_DAHLIA.get())
                .add(ModBlocks.FUNGAL_DAFFODIL.get())
                .add(ModBlocks.CRIMCONE.get())
                .add(ModBlocks.EXPERIWINKLE.get())
        ;
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
                .add(ModBlocks.ANCIENT_ROSE_BUSH.get())
                .add(ModBlocks.VIOLET_ROSE_BUSH.get())
        ;
        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
                .add(ModBlocks.CRAGS_PORTAL.get())
                .add(ModBlocks.PERSONAL_CHEST.get())
        ;
        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
                .add(ModBlocks.CRAGS_PORTAL.get())
                .add(ModBlocks.PERSONAL_CHEST.get())
                .add(ModBlocks.COBALT_GRILLES.get())
        ;
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(ModBlocks.ANCIENT_ROSE_CROP.get())
                .add(ModBlocks.ANCIENT_ROSE.get())
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_NETHER)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_END)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
                .add(ModBlocks.QUICKSAND.get())
                .add(ModBlocks.RED_QUICKSAND.get())
                .add(ModBlocks.CRAGULSTANE.get())
        ;
        getOrCreateTagBuilder(BlockTags.IMPERMEABLE)
                .add(ModBlocks.SEA_GLASS.get())
                .add(ModBlocks.PALE_SEA_GLASS.get())
        ;
        getOrCreateTagBuilder(BlockTags.PORTALS)
                .add(ModBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(BlockTags.BEDS)
                .add(ModBlocks.PHANTOM_STITCH_BED.get())
        ;
        getOrCreateTagBuilder(BlockTags.INVALID_SPAWN_INSIDE)
                .add(ModBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(BlockTags.HOGLIN_REPELLENTS)
                .add(ModBlocks.CRAGS_PORTAL.get())
        ;
        getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS)
                .add(ModBlocks.CRAGS_PORTAL.get())
        ;

        // Slabs
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.NACRE_BRICK_SLAB.get())
                .add(ModBlocks.TOWER_BRICK_SLAB.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(ModBlocks.PALE_PRISMARINE_SLAB.get())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get())

                .add(ModBlocks.HIELOSTONE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB.get())
                .add(ModBlocks.HIELOSTONE_TILE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB.get())
                .add(ModBlocks.COBBLEFROST_SLAB.get())
                .add(ModBlocks.CRUSTY_SAND_BRICK_SLAB.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get())

                .add(ModBlocks.GOLDEN_EGG_PALLET.get())
                .add(ModBlocks.EGG_PALLET.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.EBONCORK_SLAB.get())
                .add(ModBlocks.BLIGHTED_BIRCH_SLAB.get())
        ;
        // Stairs
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.NACRE_BRICK_STAIRS.get())
                .add(ModBlocks.TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.PALE_PRISMARINE_STAIRS.get())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get())

                .add(ModBlocks.HIELOSTONE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS.get())
                .add(ModBlocks.COBBLEFROST_STAIRS.get())
                .add(ModBlocks.CRUSTY_SAND_BRICK_STAIRS.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.EBONCORK_STAIRS.get())
                .add(ModBlocks.BLIGHTED_BIRCH_STAIRS.get())
        ;
        // Walls
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.NACRE_BRICK_WALL.get())
                .add(ModBlocks.TOWER_BRICK_WALL.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_WALL.get())
                .add(ModBlocks.PALE_PRISMARINE_WALL.get())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL.get())

                .add(ModBlocks.HIELOSTONE_WALL.get())
                .add(ModBlocks.HIELOSTONE_BRICK_WALL.get())
                .add(ModBlocks.HIELOSTONE_TILE_WALL.get())
                .add(ModBlocks.HIELOSTONE_PLATE_WALL.get())
                .add(ModBlocks.COBBLEFROST_WALL.get())
                .add(ModBlocks.CRUSTY_SAND_BRICK_WALL.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICK_WALL.get())
        ;
        // Fences
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE.get())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.EBONCORK_FENCE.get())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE.get())
        ;
        // Fence Gates
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get())
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE.get())

                .add(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get())
                .add(ModBlocks.EBONCORK_FENCE_GATE.get())
        ;
        // Pressure Plates + Buttons
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.EBONCORK_PRESSURE_PLATE.get())
                .add(ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get())
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.EBONCORK_BUTTON.get())
                .add(ModBlocks.BLIGHTED_BIRCH_BUTTON.get())
        ;
        getOrCreateTagBuilder(BlockTags.ANVIL)
                .add(ModBlocks.VIVULITE_ANVIL.get())
        ;
        // Doors + Trapdoors
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.EBONCORK_DOOR.get())
                .add(ModBlocks.BLIGHTED_BIRCH_DOOR.get());
        ;
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.EBONCORK_TRAPDOOR.get())
                .add(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get());
        ;

        // Needs tools
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .addTag(ModTags.Blocks.BLACK_EMERALD_ORES)
                .add(ModBlocks.BLACK_EMERALD_BLOCK.get())
                .add(ModBlocks.MOURNING_GOLD_BLOCK.get())

                .add(ModBlocks.COBBLEFROST.get())
                .add(ModBlocks.COBBLEFROST_STAIRS.get())
                .add(ModBlocks.COBBLEFROST_SLAB.get())
                .add(ModBlocks.COBBLEFROST_WALL.get())

                .add(ModBlocks.CURSE_ALTAR.get())
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.HIELOSTONE.get())
                .add(ModBlocks.HIELOSTONE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_WALL.get())

                .add(ModBlocks.HIELOSTONE_BRICKS.get())
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB.get())
                .add(ModBlocks.HIELOSTONE_BRICK_WALL.get())

                .add(ModBlocks.HIELOSTONE_TILES.get())
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_TILE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_TILE_WALL.get())

                .add(ModBlocks.HIELOSTONE_PLATES.get())
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_PLATE_WALL.get())
        ;

        // Incorrect tools.
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;

        // Mineables
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.SUGAR_CANE_BLOCK.get())
                .add(ModBlocks.BLIGHTED_BIRCH_LEAVES.get())
                .addTag(ModTags.Blocks.WREATHS)
        ;
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.WARPED_WART.get())
                .addTag(ModTags.Blocks.ENTITY_MODELS)

                .add(ModBlocks.COCOA_BEAN_BLOCK.get())
                .add(ModBlocks.GLISTERING_MELON.get())
                .add(ModBlocks.CARVED_MELON.get())
                .add(ModBlocks.CARVED_GLISTERING_MELON.get())
                .add(ModBlocks.JUNE_O_LANTERN.get())
                .add(ModBlocks.GLISTERING_JUNE_O_LANTERN.get())
                .add(ModBlocks.WHITE_PUMPKIN.get())
                .add(ModBlocks.WHITE_JACK_O_LANTERN.get())
                .add(ModBlocks.FUNGAL_DAFFODIL_BLOCK.get())

                .add(ModBlocks.EBONCORK.get())
                .add(ModBlocks.EBONCORK_PLANKS.get())
                .add(ModBlocks.EBONCORK_STAIRS.get())
                .add(ModBlocks.EBONCORK_SLAB.get())
                .add(ModBlocks.EBONCORK_FENCE.get())
                .add(ModBlocks.EBONCORK_FENCE_GATE.get())

                .add(ModBlocks.BLIGHTED_BIRCH_PLANKS.get())
                .add(ModBlocks.BLIGHTED_BIRCH_STAIRS.get())
                .add(ModBlocks.BLIGHTED_BIRCH_SLAB.get())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE.get())
                .add(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get())
                .addTag(ModTags.Blocks.BLIGHTED_BIRCH_LOGS)

                .add(ModBlocks.ROTTEN_FLESH_BLOCK.get())
                .add(ModBlocks.EGG_PALLET.get())
                .add(ModBlocks.GOLDEN_EGG_PALLET.get())
        ;
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.QUICKSAND.get())
                .add(ModBlocks.RED_QUICKSAND.get())
                .add(ModBlocks.SLIME_TRAIL.get())
                .add(ModBlocks.CRUSTED_QUICKSAND.get())
                .add(ModBlocks.CRUSTED_RED_QUICKSAND.get())
        ;
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.RAW_COBALT_BLOCK.get())
                .addTag(ModTags.Blocks.COBALT_ORES)
                .addTag(ModTags.Blocks.VERDINITE_ORES)
                .add(ModBlocks.VIVULITE_BLOCK.get())
                .add(ModBlocks.RAW_VIVULITE_BLOCK.get())
                .add(ModBlocks.VERDINITE_BLOCK.get())
                .add(ModBlocks.RAW_VERDINITE_BLOCK.get())
                .addTag(ModTags.Blocks.VIVULITE_ORES)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
                .add(ModBlocks.STRANGE_CORE.get())
                .add(ModBlocks.BLACK_EMERALD_BLOCK.get())
                .addTag(ModTags.Blocks.BLACK_EMERALD_ORES)
                .add(ModBlocks.MOURNING_GOLD_BLOCK.get())
                .addTag(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_BLOCK.get())
                .add(ModBlocks.RAW_FROSTITE_BLOCK.get())
                .add(ModBlocks.ONYX_BONE_BLOCK.get())
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get())
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.COBALT_GRILLES.get())

                .add(ModBlocks.PERSONAL_CHEST.get())
                .add(ModBlocks.CURSE_ALTAR.get())
                .add(ModBlocks.MONSTER_BAKERY.get())
                .add(ModBlocks.ITEM_VACUUM.get())

                .add(ModBlocks.TOWER_WATCHER.get())
                .add(ModBlocks.TOWER_HEART.get())
                .add(ModBlocks.TOWER_SPAWNER.get())
                .add(ModBlocks.TOWER_KEY_VAULT.get())
                .add(ModBlocks.TOWER_TREASURE_VAULT.get())

                .add(ModBlocks.HIELOSTONE.get())
                .add(ModBlocks.HIELOSTONE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_WALL.get())
                .add(ModBlocks.HIELOSTONE_BRICKS.get())
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB.get())
                .add(ModBlocks.HIELOSTONE_BRICK_WALL.get())
                .add(ModBlocks.HIELOSTONE_TILES.get())
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_TILE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_TILE_WALL.get())
                .add(ModBlocks.HIELOSTONE_PLATES.get())
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS.get())
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB.get())
                .add(ModBlocks.HIELOSTONE_PLATE_WALL.get())
                .add(ModBlocks.COBBLEFROST.get())
                .add(ModBlocks.COBBLEFROST_STAIRS.get())
                .add(ModBlocks.COBBLEFROST_SLAB.get())
                .add(ModBlocks.COBBLEFROST_WALL.get())

                .add(ModBlocks.NACRE_BRICKS.get())
                .add(ModBlocks.NACRE_BRICK_STAIRS.get())
                .add(ModBlocks.NACRE_BRICK_SLAB.get())
                .add(ModBlocks.NACRE_BRICK_WALL.get())

                .add(ModBlocks.TOWER_BRICKS.get())
                .add(ModBlocks.TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.TOWER_BRICK_SLAB.get())
                .add(ModBlocks.TOWER_BRICK_WALL.get())

                .add(ModBlocks.MOSSY_TOWER_BRICKS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_SLAB.get())
                .add(ModBlocks.MOSSY_TOWER_BRICK_WALL.get())

                .add(ModBlocks.TURTLE_SCUTE_BRICKS.get())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get())
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL.get())

                .add(ModBlocks.CRAGULSTANE.get())
                .add(ModBlocks.BRIMTAN_ORE.get())
                .add(ModBlocks.BRIMTAN_BLOCK.get())
                .add(ModBlocks.CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get())
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get())
                .add(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get())
                .add(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get())

                .add(ModBlocks.BLUE_NETHER_BRICKS.get())
                .add(ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get())
                .add(ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL.get())
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE.get())

                .add(ModBlocks.PURPLE_NETHER_BRICKS.get())
                .add(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get())
                .add(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL.get())
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE.get())

                .add(ModBlocks.CRACKED_RED_NETHER_BRICKS.get())
                .add(ModBlocks.CHISELED_RED_NETHER_BRICKS.get())
                .add(ModBlocks.RED_NETHER_BRICK_FENCE.get())

                .add(ModBlocks.PALE_PRISMARINE.get())
                .add(ModBlocks.PALE_PRISMARINE_STAIRS.get())
                .add(ModBlocks.PALE_PRISMARINE_SLAB.get())
                .add(ModBlocks.PALE_PRISMARINE_WALL.get())
                .add(ModBlocks.PALE_PRISMARINE_BRICKS.get())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get())
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get())
                .add(ModBlocks.DEEP_PALE_PRISMARINE.get())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get())
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get())

                .add(ModBlocks.CRUSTED_QUICKSAND.get())
                .add(ModBlocks.CRUSTED_RED_QUICKSAND.get())
                .add(ModBlocks.CRUSTY_SAND_BRICKS.get())
                .add(ModBlocks.CRUSTY_SAND_BRICK_STAIRS.get())
                .add(ModBlocks.CRUSTY_SAND_BRICK_SLAB.get())
                .add(ModBlocks.CRUSTY_SAND_BRICK_WALL.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICKS.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get())
                .add(ModBlocks.CRUSTY_RED_SAND_BRICK_WALL.get())

                .addTag(ModTags.Blocks.STONE_FENCE_GATES)
        ;
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.GLISTERING_MELON.get())
                .add(ModBlocks.CARVED_MELON.get())
                .add(ModBlocks.CARVED_GLISTERING_MELON.get())
                .add(ModBlocks.JUNE_O_LANTERN.get())
                .add(ModBlocks.GLISTERING_JUNE_O_LANTERN.get())
                .add(ModBlocks.WHITE_PUMPKIN.get())
                .add(ModBlocks.WHITE_JACK_O_LANTERN.get())

                .add(ModBlocks.WARPED_WART.get())
        ;

        // Extra
        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.EBONCORK_PLANKS.get())
                .add(ModBlocks.BLIGHTED_BIRCH_PLANKS.get())
        ;
        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(ModBlocks.EBONCORK.get())
        ;
        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.BLIGHTED_BIRCH_LOGS)
        ;
        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.BLIGHTED_BIRCH_SAPLING.get())
        ;
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.BLIGHTED_BIRCH_LEAVES.get())
        ;
        getOrCreateTagBuilder(BlockTags.SMELTS_TO_GLASS)
                .add(ModBlocks.QUICKSAND.get())
                .add(ModBlocks.RED_QUICKSAND.get())
        ;
        getOrCreateTagBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
        ;
        getOrCreateTagBuilder(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
                .add(ModBlocks.SLIME_TRAIL.get())
        ;
        getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN)
                .add(ModBlocks.BLACK_EMERALD_ORE.get())
        ;
        getOrCreateTagBuilder(BlockTags.FEATURES_CANNOT_REPLACE)
                .add(ModBlocks.AESTHENOSTONE.get())
        ;
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_CRIMCONE.get())
                .add(ModBlocks.POTTED_FUNGAL_DAFFODIL.get())
                .add(ModBlocks.POTTED_ANCIENT_ROSE.get())
                .add(ModBlocks.POTTED_BLIGHTED_BIRCH_SAPLING.get())
                .add(ModBlocks.POTTED_ROSE.get())
                .add(ModBlocks.POTTED_VIOLET_ROSE.get())
                .add(ModBlocks.POTTED_SNOW_DAHLIA.get())
                .add(ModBlocks.POTTED_EXPERIWINKLE.get())
        ;
    }

    // Common tags.
    private void commonBlockTag()
    {
        getOrCreateTagBuilder(ConventionalBlockTags.STONES)
                .add(ModBlocks.HIELOSTONE.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.COBBLESTONES)
                .add(ModBlocks.COBBLEFROST.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.OBSIDIANS)
                .add(ModBlocks.GLOWING_OBSIDIAN.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.ORES)
                .add(ModBlocks.COBALT_ORE.get())
                .add(ModBlocks.DEEPSLATE_COBALT_ORE.get())
                .add(ModBlocks.VERDINITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_VERDINITE_ORE.get())
                .add(ModBlocks.FROSTITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_VIVULITE_ORE.get())
                .add(ModBlocks.VIVULITE_ORE.get())
                .add(ModBlocks.BRIMTAN_ORE.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.CHESTS)
                .add(ModBlocks.PERSONAL_CHEST.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.GLASS_BLOCKS)
                .add(ModBlocks.SEA_GLASS.get())
                .add(ModBlocks.PALE_SEA_GLASS.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.GLASS_PANES)
                .add(ModBlocks.SEA_GLASS_PANE.get())
                .add(ModBlocks.PALE_SEA_GLASS_PANE.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.BUDS)
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD.get())
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.CLUSTERS)
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
                .add(ModBlocks.BRIMTAN_BLOCK.get())
                .add(ModBlocks.BLACK_EMERALD_BLOCK.get())
                .add(ModBlocks.COBALT_BLOCK.get())
                .add(ModBlocks.FROSTITE_BLOCK.get())
                .add(ModBlocks.COCOA_BEAN_BLOCK.get())
                .add(ModBlocks.ROTTEN_FLESH_BLOCK.get())
                .add(ModBlocks.MOURNING_GOLD_BLOCK.get())
                .add(ModBlocks.NECRO_WEAVE_BLOCK.get())
                .add(ModBlocks.RAW_COBALT_BLOCK.get())
                .add(ModBlocks.RAW_FROSTITE_BLOCK.get())
                .add(ModBlocks.RAW_VERDINITE_BLOCK.get())
                .add(ModBlocks.RAW_VIVULITE_BLOCK.get())
                .add(ModBlocks.SUGAR_CANE_BLOCK.get())
                .add(ModBlocks.VERDINITE_BLOCK.get())
                .add(ModBlocks.VIVULITE_BLOCK.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_LOGS)
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get())
        ;
        getOrCreateTagBuilder(ConventionalBlockTags.STRIPPED_WOODS)
                .add(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get())
        ;
        // Custom C
        getOrCreateTagBuilder(ModTags.Blocks.C_RAW_BLOCKS)
                .add(ModBlocks.RAW_COBALT_BLOCK.get())
                .add(ModBlocks.RAW_FROSTITE_BLOCK.get())
                .add(ModBlocks.RAW_VERDINITE_BLOCK.get())
                .add(ModBlocks.RAW_VIVULITE_BLOCK.get())
        ;
    }

    private void extraBlockTag()
    {
        getOrCreateTagBuilder(ModTags.Blocks.QUARK_SIMPLE_HARVEST_NOGO)
                .add(ModBlocks.ANCIENT_ROSE.get())
                .add(ModBlocks.ANCIENT_ROSE_CROP.get())
                .add(ModBlocks.EXPERIWINKLE.get())
                .add(ModBlocks.EXPERIWINKLE_CROP.get())
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
