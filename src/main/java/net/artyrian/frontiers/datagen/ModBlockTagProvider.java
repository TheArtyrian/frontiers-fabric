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
        getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_NETHER)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(BlockTags.INFINIBURN_END)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;

        // Mineables
        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
        ;
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
        ;
        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
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
                .add(ModBlocks.NACRE_BRICKS)
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
