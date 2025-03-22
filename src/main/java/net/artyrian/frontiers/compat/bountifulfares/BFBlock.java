package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.LumenBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

// A list of Bountiful Fares exclusive pack-in blocks.
public class BFBlock
{
    // Blocks added by Frontiers
    public static Block FELDSPAR_LUMEN = null;

    // Existing blocks (including compats)
    public static Block APPLEDOG_BLOCK = null;

    // Registers both the Block and Item to their respective Minecraft registry.
    private static Block registerBlock(String name, Block block)
    {
        doBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    // Creates the block item - used alongside registerBlock() to make both in same method.
    private static Item doBlockItem(String name, Block block)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    // Registers mod blocks. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    public static void registerModBlocks()
    {
        FELDSPAR_LUMEN = registerBlock("feldspar_lumen",
                new LumenBlock(AbstractBlock.Settings.copy(ModBlocks.DIAMOND_LUMEN).mapColor(MapColor.TERRACOTTA_WHITE)));

        if (Frontiers.APPLEDOG_LOADED)
        {
            APPLEDOG_BLOCK = Registries.BLOCK.get(Identifier.of(Frontiers.APPLEDOG_ID, "appledog_block"));
        }
    }
}
