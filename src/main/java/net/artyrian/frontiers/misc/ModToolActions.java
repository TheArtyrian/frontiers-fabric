package net.artyrian.frontiers.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModToolActions
{
    public static final Map<Block, Block> LOGS = new LinkedHashMap<>();

    public static void execute()
    {
        LOGS.put(ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG, ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG);
        LOGS.put(ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG, ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG);

        LOGS.put(ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD, ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD);
        LOGS.put(ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD, ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD);
    }
}
