package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.LumenBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

// A list of Bountiful Fares exclusive pack-in blocks.
public class BFBlock
{
    // Blocks added by Frontiers
    public static Block FELDSPAR_LUMEN = null;
    public static Block HOARY_WREATH = null;
    public static Block WALNUT_WREATH = null;
    public static Block APPLE_WREATH = null;
    public static Block ORANGE_WREATH = null;
    public static Block LEMON_WREATH = null;
    public static Block PLUM_WREATH = null;
    public static Block GOLDEN_WREATH = null;

    // Existing blocks (including compats)
    public static Block APPLEDOG_BLOCK = null;

    // Registers both the Block and Item to their respective Minecraft registry.
    private static Block registerBlock(String name, Block block)
    {
        doBlockItem(name, block, new Item.Settings());
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, Item.Settings settings)
    {
        doBlockItem(name, block, settings);
        return Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, name), block);
    }

    // Creates the block item - used alongside registerBlock() to make both in same method.
    private static Item doBlockItem(String name, Block block, Item.Settings settings)
    {
        return Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, name), new BlockItem(block, settings));
    }

    // Registers mod blocks. ALL LOGIC IS DONE IN HERE SINCE THIS IS ONLY CALLED WHEN BF IS ENABLED!
    public static void registerModBlocks()
    {
        FELDSPAR_LUMEN = registerBlock("feldspar_lumen",
                new LumenBlock(AbstractBlock.Settings.copy(ModBlocks.DIAMOND_LUMEN).mapColor(MapColor.TERRACOTTA_WHITE)));

        HOARY_WREATH = registerBlock("hoary_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES));
        WALNUT_WREATH = registerBlock("walnut_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES));
        APPLE_WREATH = registerBlock("apple_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES));
        ORANGE_WREATH = registerBlock("orange_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES));
        LEMON_WREATH = registerBlock("lemon_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES));
        PLUM_WREATH = registerBlock("plum_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES));
        GOLDEN_WREATH = registerBlock("golden_wreath", ModBlocks.createWreath(Blocks.OAK_LEAVES), new Item.Settings().rarity(Rarity.UNCOMMON));

        if (Frontiers.APPLEDOG_LOADED)
        {
            APPLEDOG_BLOCK = Registries.BLOCK.get(Identifier.of(Frontiers.APPLEDOG_ID, "appledog_block"));
        }
    }
}
