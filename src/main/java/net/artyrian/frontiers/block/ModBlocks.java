package net.artyrian.frontiers.block;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;

// Registers all mod blocks (and their items) to Minecraft registries.
public class ModBlocks
{
    // BLOCK LIST. Gets lengthy.

    // Cobalt ore (drops Experience)
    public static final Block COBALT_ORE = registerBlock("cobalt_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(0, 3),
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE)
            )
    );
    // Block of Cobalt
    public static final Block COBALT_BLOCK = registerBlock("cobalt_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)
            )
    );
    // Glowing Obsidian
    public static final Block GLOWING_OBSIDIAN = registerBlock("glowing_obsidian",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.OBSIDIAN)
                            .mapColor(DyeColor.RED)
                            .luminance(state -> 12)
            )
    );
    // Strange Core (registered directly to change rarity).
    public static final Block STRANGE_CORE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "strange_core"), new Block(
            AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .mapColor(DyeColor.BLUE)
                    .luminance(state -> 12)
    ));
    private static final Item STRANGE_CORE_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "strange_core"), new BlockItem(STRANGE_CORE, new Item.Settings().rarity(Rarity.RARE)));;

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

    // Registers mod blocks. Just sends a log message.
    public static void registerModBlocks()
    {
        Frontiers.LOGGER.info("Registering Mod Blocks for " + Frontiers.MOD_ID);
    }
}
