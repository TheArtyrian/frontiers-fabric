package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
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
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_VERDINITE_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .add(ModBlocks.CRAGULSTANE)
                .add(ModBlocks.CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CHISELED_CRAGULSTANE_BRICKS)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .add(ModBlocks.STRANGE_CORE)
                .add(ModBlocks.TOWER_BRICKS)
                .add(ModBlocks.MOSSY_TOWER_BRICKS)
                .add(ModBlocks.FROSTITE_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.COBALT_ORE)
                .add(ModBlocks.DEEPSLATE_COBALT_ORE)
        ;getOrCreateTagBuilder(ModTags.Blocks.FROSTITE_ORES)
                .add(ModBlocks.FROSTITE_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.GLOWING_OBSIDIAN)
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

    }

    // Vanilla tags.
    private void vanillaBlockTag()
    {
        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.COBALT_BLOCK)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
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
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.ANCIENT_ROSE_BUSH)
                .add(ModBlocks.VIOLET_ROSE_BUSH)
        ;
        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.GLOWING_OBSIDIAN)
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

        // Common categories
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.BLUE_NETHER_BRICK_SLAB)
                .add(ModBlocks.PURPLE_NETHER_BRICK_SLAB)
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.NACRE_BRICK_SLAB)
        ;
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.BLUE_NETHER_BRICK_STAIRS)
                .add(ModBlocks.PURPLE_NETHER_BRICK_STAIRS)
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.NACRE_BRICK_STAIRS)
        ;
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.BLUE_NETHER_BRICK_WALL)
                .add(ModBlocks.PURPLE_NETHER_BRICK_WALL)
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.NACRE_BRICK_WALL)
        ;
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.BLUE_NETHER_BRICK_FENCE)
                .add(ModBlocks.PURPLE_NETHER_BRICK_FENCE)
                .add(ModBlocks.RED_NETHER_BRICK_FENCE)
        ;

        // Needs tools
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
        ;

        // Incorrect tools.
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_VERDINITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;

        // Mineables
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
        ;
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.WARPED_WART)
        ;
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.QUICKSAND)
                .add(ModBlocks.RED_QUICKSAND)
        ;
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.COBALT_BLOCK)
                .addTag(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.GLOWING_OBSIDIAN)
                .add(ModBlocks.STRANGE_CORE)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
                .add(ModBlocks.TOWER_BRICKS)
                .add(ModBlocks.MOSSY_TOWER_BRICKS)
                .add(ModBlocks.FROSTITE_ORE)
                .add(ModBlocks.ONYX_BONE_BLOCK)
                .add(ModBlocks.CORRUPTED_AMETHYST_CLUSTER)
                .add(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD)
                .add(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD)
                .add(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD)

                .add(ModBlocks.NACRE_BRICKS)
                .add(ModBlocks.NACRE_BRICK_STAIRS)
                .add(ModBlocks.NACRE_BRICK_SLAB)
                .add(ModBlocks.NACRE_BRICK_WALL)

                .add(ModBlocks.CRAGULSTANE)
                .add(ModBlocks.CRAGULSTANE_BRICKS)
                .add(ModBlocks.CRAGULSTANE_BRICK_STAIRS)
                .add(ModBlocks.CRAGULSTANE_BRICK_SLAB)
                .add(ModBlocks.CRAGULSTANE_BRICK_WALL)
                .add(ModBlocks.CHISELED_CRAGULSTANE_BRICKS)

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
        ;
        getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT)
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
