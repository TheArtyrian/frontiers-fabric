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
        getOrCreateTagBuilder(ModTags.Blocks.INCORRECT_FOR_COBALT_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_COBALT_TOOL)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.COBALT_ORES)
                .add(ModBlocks.COBALT_ORE)
        ;
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.COBALT_BLOCK)
                .add(ModBlocks.COBALT_ORE)
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
        ;
        getOrCreateTagBuilder(BlockTags.TALL_FLOWERS)
                .add(ModBlocks.ANCIENT_ROSE_BUSH)
                .add(ModBlocks.VIOLET_ROSE_BUSH)
        ;

        // Needs tools
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.STRANGE_CORE)
                .add(ModBlocks.GLOWING_OBSIDIAN)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
        ;

        // Incorrect tools.
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
        ;
        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ModTags.Blocks.NEEDS_NETHERITE_TOOL)
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
                .add(ModBlocks.COBALT_ORE)
                .add(ModBlocks.GLOWING_OBSIDIAN)
                .add(ModBlocks.STRANGE_CORE)
                .add(ModBlocks.MOURNING_GOLD_BLOCK)
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
