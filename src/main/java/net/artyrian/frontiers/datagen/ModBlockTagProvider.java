package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider
{
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
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
                .add(ModBlocks.GLOWING_OBSIDIAN)
                .add(ModBlocks.CRAGULSTANE)
                .add(ModBlocks.BRIMTAN_ORE)
                .add(ModBlocks.BRIMTAN_BLOCK)

                .add(ModBlocks.CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_CRAGULSTANE_BRICKS)

                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS)

                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICKS)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS)

                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .addTag(ModTags.Blocks.VIVULITE_ORES)
                .add(ModBlocks.VIVULITE_BLOCK)
                .add(ModBlocks.VIVULITE_ANVIL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_BRIMTAN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VIVULITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .add(ModBlocks.STRANGE_CORE)
                .add(ModBlocks.TOWER_BRICKS)
                .add(ModBlocks.MOSSY_TOWER_BRICKS)
                .addTag(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_BLOCK)
                .add(ModBlocks.VERDINITE_BLOCK)
                .addTag(ModTags.Blocks.VERDINITE_ORES)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.COBALT_ORE)
                .add(ModBlocks.DEEPSLATE_COBALT_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.VERDINITE_ORES)
                .add(ModBlocks.VERDINITE_ORE)
                .add(ModBlocks.DEEPSLATE_VERDINITE_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.VIVULITE_ORES)
                .add(ModBlocks.VIVULITE_ORE)
                .add(ModBlocks.DEEPSLATE_VIVULITE_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.COBALT_BLOCK)
                .addTag(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER)
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD)
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD)
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INFINIBURN_CRAGS)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.CONDUIT_BASE_BLOCKS)
                .add(Blocks.PRISMARINE)
                .add(Blocks.PRISMARINE_BRICKS)
                .add(Blocks.DARK_PRISMARINE)
                .add(ModBlocks.SEA_GLASS)
                .add(ModBlocks.PALE_PRISMARINE)
                .add(ModBlocks.PALE_PRISMARINE_BRICKS)
                .add(ModBlocks.DEEP_PALE_PRISMARINE)
                .add(ModBlocks.PALE_SEA_GLASS)
                .add(Blocks.SEA_LANTERN)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ONLY_DROP_IN_HARDMODE)
                .addTag(ModTags.Blocks.COBALT_ORES)
                .addTag(ModTags.Blocks.VERDINITE_ORES)
                .addTag(ModTags.Blocks.VIVULITE_ORES)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.LUMENS)
                .add(ModBlocks.DIAMOND_LUMEN)
                .add(ModBlocks.QUARTZ_LUMEN)
                .add(ModBlocks.REDSTONE_LUMEN)
                .add(ModBlocks.EMERALD_LUMEN)
                .add(ModBlocks.AMETHYST_LUMEN)
                .add(ModBlocks.COBALT_LUMEN)
                .add(ModBlocks.FROSTITE_LUMEN)
                .add(ModBlocks.VERDINITE_LUMEN)
                .add(ModBlocks.VIVULITE_LUMEN)
                .add(ModBlocks.BRIMTAN_LUMEN)
                .add(ModBlocks.ECHO_LUMEN)

                // Compat items
                .add(BFBlock.FELDSPAR_LUMEN)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.STONE_FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE)
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE)
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.ENTITY_MODELS)
            .add(ModBlocks.CREEPER_MODEL)
            .add(ModBlocks.SKELETON_MODEL)
            .add(ModBlocks.STRAY_MODEL)
            .add(ModBlocks.BOGGED_MODEL)
            .add(ModBlocks.BLAZE_MODEL)
            .add(ModBlocks.WITHER_SKELETON_MODEL)
            .add(ModBlocks.ENDERMAN_MODEL)
            .add(ModBlocks.SLIME_MODEL)
            .add(ModBlocks.MAGMA_CUBE_MODEL)
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
                .add(ModBlocks.CRAGS_PORTAL)
        ;
    }

    // Vanilla tags.
    private void vanillaBlockTag()
    {
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.COBALT_BLOCK)
                .add(ModBlocks.FROSTITE_BLOCK)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
                .add(ModBlocks.VIVULITE_BLOCK)
                .add(ModBlocks.VERDINITE_BLOCK)
                .add(ModBlocks.BRIMTAN_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.ANCIENT_ROSE_CROP)
        ;
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(ModBlocks.ANCIENT_ROSE)
                .add(ModBlocks.ROSE)
                .add(ModBlocks.VIOLET_ROSE)
                .add(ModBlocks.SNOW_DAHLIA)
                .add(ModBlocks.FUNGAL_DAFFODIL)
                .add(ModBlocks.CRIMCONE)
        ;
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
                .add(ModBlocks.ANCIENT_ROSE_BUSH)
                .add(ModBlocks.VIOLET_ROSE_BUSH)
        ;
        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE)
                .add(ModBlocks.GLOWING_OBSIDIAN)
                .add(ModBlocks.CRAGS_PORTAL)
                .add(ModBlocks.PERSONAL_CHEST)
        ;
        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.GLOWING_OBSIDIAN)
                .add(ModBlocks.CRAGS_PORTAL)
                .add(ModBlocks.PERSONAL_CHEST)
        ;
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(ModBlocks.ANCIENT_ROSE_CROP)
                .add(ModBlocks.ANCIENT_ROSE)
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_NETHER)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_END)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(BlockTags.SCULK_REPLACEABLE)
                .add(ModBlocks.QUICKSAND)
                .add(ModBlocks.RED_QUICKSAND)
                .add(ModBlocks.CRAGULSTANE)
        ;
        getOrCreateTagBuilder(BlockTags.IMPERMEABLE)
                .add(ModBlocks.SEA_GLASS)
                .add(ModBlocks.PALE_SEA_GLASS)
        ;
        getOrCreateTagBuilder(BlockTags.PORTALS)
                .add(ModBlocks.CRAGS_PORTAL)
        ;
        getOrCreateTagBuilder(BlockTags.INVALID_SPAWN_INSIDE)
                .add(ModBlocks.CRAGS_PORTAL)
        ;
        getOrCreateTagBuilder(BlockTags.HOGLIN_REPELLENTS)
                .add(ModBlocks.CRAGS_PORTAL)
        ;
        getOrCreateTagBuilder(BlockTags.PIGLIN_REPELLENTS)
                .add(ModBlocks.CRAGS_PORTAL)
        ;

        // Common categories
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB)
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB)
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.NACRE_BRICK_SLAB)
                .add(ModBlocks.PALE_PRISMARINE_SLAB)
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB)
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB)
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB)

                .add(ModBlocks.HIELOSTONE_SLAB)
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB)
                .add(ModBlocks.HIELOSTONE_TILE_SLAB)
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB)
                .add(ModBlocks.COBBLEFROST_SLAB)
        ;
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS)
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS)
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.NACRE_BRICK_STAIRS)
                .add(ModBlocks.PALE_PRISMARINE_STAIRS)
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS)
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS)
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS)

                .add(ModBlocks.HIELOSTONE_STAIRS)
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS)
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS)
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS)
                .add(ModBlocks.COBBLEFROST_STAIRS)
        ;
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL)
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL)
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.NACRE_BRICK_WALL)
                .add(ModBlocks.PALE_PRISMARINE_WALL)
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL)

                .add(ModBlocks.HIELOSTONE_WALL)
                .add(ModBlocks.HIELOSTONE_BRICK_WALL)
                .add(ModBlocks.HIELOSTONE_TILE_WALL)
                .add(ModBlocks.HIELOSTONE_PLATE_WALL)
                .add(ModBlocks.COBBLEFROST_WALL)
        ;
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE)
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE)
        ;
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE)
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE_GATE)
                .add(ModBlocks.NETHER_BRICK_FENCE_GATE)
        ;
        getOrCreateTagBuilder(BlockTags.ANVIL)
                .add(ModBlocks.VIVULITE_ANVIL)
        ;

        // Needs tools
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
                .add(ModBlocks.HIELOSTONE)
                .add(ModBlocks.HIELOSTONE_BRICKS)
                .add(ModBlocks.HIELOSTONE_TILES)
                .add(ModBlocks.HIELOSTONE_PLATES)
                .add(ModBlocks.COBBLEFROST)
                .add(ModBlocks.CURSE_ALTAR)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
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
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
        ;
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.WARPED_WART)
                .addTag(ModTags.Blocks.ENTITY_MODELS)

                .add(ModBlocks.GLISTERING_MELON)
                .add(ModBlocks.CARVED_MELON)
                .add(ModBlocks.CARVED_GLISTERING_MELON)
                .add(ModBlocks.JUNE_O_LANTERN)
                .add(ModBlocks.GLISTERING_JUNE_O_LANTERN)
                .add(ModBlocks.WHITE_PUMPKIN)
                .add(ModBlocks.WHITE_JACK_O_LANTERN)
        ;
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.QUICKSAND)
                .add(ModBlocks.RED_QUICKSAND)
        ;
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COBALT_BLOCK)
                .addTag(ModTags.Blocks.COBALT_ORES)
                .addTag(ModTags.Blocks.VERDINITE_ORES)
                .add(ModBlocks.VIVULITE_BLOCK)
                .add(ModBlocks.VERDINITE_BLOCK)
                .addTag(ModTags.Blocks.VIVULITE_ORES)
                .add(ModBlocks.GLOWING_OBSIDIAN)
                .add(ModBlocks.STRANGE_CORE)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
                .add(ModBlocks.TOWER_BRICKS)
                .add(ModBlocks.MOSSY_TOWER_BRICKS)
                .addTag(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_BLOCK)
                .add(ModBlocks.ONYX_BONE_BLOCK)
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER)
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD)
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD)
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD)

                .add(ModBlocks.PERSONAL_CHEST)
                .add(ModBlocks.CURSE_ALTAR)

                .add(ModBlocks.HIELOSTONE)
                .add(ModBlocks.HIELOSTONE_STAIRS)
                .add(ModBlocks.HIELOSTONE_SLAB)
                .add(ModBlocks.HIELOSTONE_WALL)
                .add(ModBlocks.HIELOSTONE_BRICKS)
                .add(ModBlocks.HIELOSTONE_BRICK_STAIRS)
                .add(ModBlocks.HIELOSTONE_BRICK_SLAB)
                .add(ModBlocks.HIELOSTONE_BRICK_WALL)
                .add(ModBlocks.HIELOSTONE_TILES)
                .add(ModBlocks.HIELOSTONE_TILE_STAIRS)
                .add(ModBlocks.HIELOSTONE_TILE_SLAB)
                .add(ModBlocks.HIELOSTONE_TILE_WALL)
                .add(ModBlocks.HIELOSTONE_PLATES)
                .add(ModBlocks.HIELOSTONE_PLATE_STAIRS)
                .add(ModBlocks.HIELOSTONE_PLATE_SLAB)
                .add(ModBlocks.HIELOSTONE_PLATE_WALL)
                .add(ModBlocks.COBBLEFROST)
                .add(ModBlocks.COBBLEFROST_STAIRS)
                .add(ModBlocks.COBBLEFROST_SLAB)
                .add(ModBlocks.COBBLEFROST_WALL)

                .add(ModBlocks.NACRE_BRICKS)
                .add(ModBlocks.NACRE_BRICK_STAIRS)
                .add(ModBlocks.NACRE_BRICK_SLAB)
                .add(ModBlocks.NACRE_BRICK_WALL)

                .add(ModBlocks.TURTLE_SCUTE_BRICKS)
                .add(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS)
                .add(ModBlocks.TURTLE_SCUTE_BRICK_SLAB)
                .add(ModBlocks.TURTLE_SCUTE_BRICK_WALL)

                .add(ModBlocks.CRAGULSTANE)
                .add(ModBlocks.BRIMTAN_ORE)
                .add(ModBlocks.BRIMTAN_BLOCK)
                .add(ModBlocks.CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.CHISELED_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_CRAGULSTANE_BRICKS)

                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICKS)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS)

                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICKS)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS)

                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICKS)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS)

                .add(ModBlocks.BLUE_NETHER_BRICKS)
                .add(ModBlocks.CRACKED_BLUE_NETHER_BRICKS)
                .add(ModBlocks.CHISELED_BLUE_NETHER_BRICKS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE)

                .add(ModBlocks.PURPLE_NETHER_BRICKS)
                .add(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS)
                .add(ModBlocks.CHISELED_PURPLE_NETHER_BRICKS)
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS)
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB)
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL)
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE)

                .add(ModBlocks.CRACKED_RED_NETHER_BRICKS)
                .add(ModBlocks.CHISELED_RED_NETHER_BRICKS)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE)

                .add(ModBlocks.PALE_PRISMARINE)
                .add(ModBlocks.PALE_PRISMARINE_STAIRS)
                .add(ModBlocks.PALE_PRISMARINE_SLAB)
                .add(ModBlocks.PALE_PRISMARINE_WALL)
                .add(ModBlocks.PALE_PRISMARINE_BRICKS)
                .add(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS)
                .add(ModBlocks.PALE_PRISMARINE_BRICK_SLAB)
                .add(ModBlocks.DEEP_PALE_PRISMARINE)
                .add(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS)
                .add(ModBlocks.DEEP_PALE_PRISMARINE_SLAB)

                .addTag(ModTags.Blocks.STONE_FENCE_GATES)
        ;
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.GLISTERING_MELON)
                .add(ModBlocks.CARVED_MELON)
                .add(ModBlocks.CARVED_GLISTERING_MELON)
                .add(ModBlocks.JUNE_O_LANTERN)
                .add(ModBlocks.GLISTERING_JUNE_O_LANTERN)
                .add(ModBlocks.WHITE_PUMPKIN)
                .add(ModBlocks.WHITE_JACK_O_LANTERN)

                .add(ModBlocks.WARPED_WART)
        ;
    }

    // Fabric tags.
    private void fabricBlockTag()
    {

    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
        modBlockTag();
        vanillaBlockTag();
        fabricBlockTag();
    }
}
