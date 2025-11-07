package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;

import java.util.HashMap;
import java.util.Map;

public class ModFlammable
{
    // Will be thrown into the FireBlock mixin
    public static Map<Block, int[]> LIST = new HashMap<>();

    public static void execute()
    {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        fireBlock.registerFlammableBlock(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG, 5, 5);
        fireBlock.registerFlammableBlock(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD, 5, 5);
        fireBlock.registerFlammableBlock(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG, 5, 5);
        fireBlock.registerFlammableBlock(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD, 5, 5);
        fireBlock.registerFlammableBlock(ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG, 5, 5);
        fireBlock.registerFlammableBlock(ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD, 5, 5);

        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_PLANKS, 5, 20);
        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_STAIRS, 5, 20);
        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_SLAB, 5, 20);
        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_FENCE, 5, 20);
        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE, 5, 20);

        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_LEAVES, 30, 60);

        fireBlock.registerFlammableBlock(ModBlocks.ROSE, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.ANCIENT_ROSE, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.VIOLET_ROSE, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.ANCIENT_ROSE_BUSH, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.VIOLET_ROSE_BUSH, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.SNOW_DAHLIA, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.EXPERIWINKLE, 60, 100);

        fireBlock.registerFlammableBlock(ModBlocks.PHANTASMIC_TNT, 15, 100);

        fireBlock.registerFlammableBlock(ModBlocks.SLIME_TRAIL, 60, 100);
        fireBlock.registerFlammableBlock(ModBlocks.SLIME_BULB, 60, 100);

        fireBlock.registerFlammableBlock(ModBlocks.OAK_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.DARK_OAK_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.BIRCH_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.SPRUCE_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.JUNGLE_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.ACACIA_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.MANGROVE_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.AZALEA_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.CHERRY_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(ModBlocks.BLIGHTED_BIRCH_WREATH, 30, 60);
    }

    public static void executeBF()
    {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;

        fireBlock.registerFlammableBlock(BFBlock.HOARY_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(BFBlock.WALNUT_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(BFBlock.APPLE_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(BFBlock.ORANGE_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(BFBlock.LEMON_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(BFBlock.PLUM_WREATH, 30, 60);
        fireBlock.registerFlammableBlock(BFBlock.GOLDEN_WREATH, 30, 60);
    }
}
