package net.artyrian.frontiers.block;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.AncientRoseCropBlock;
import net.artyrian.frontiers.block.custom.FrostiteOreBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
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
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).mapColor(DyeColor.BLUE)
            )
    );
    // Frostite Ore
    public static final Block FROSTITE_ORE = registerBlock("frostite_ore",
            new FrostiteOreBlock(
                    AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK)
                            .mapColor(MapColor.PALE_PURPLE)
                            .sounds(BlockSoundGroup.GLASS)
                            .slipperiness(0.98F)
                            .nonOpaque()
                            .ticksRandomly()
            )
    );
    // Block of Mourning Gold
    public static final Block MOURNING_GOLD_BLOCK = registerBlock("mourning_gold_block",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).mapColor(DyeColor.LIGHT_GRAY)
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
    private static final Item STRANGE_CORE_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "strange_core"), new BlockItem(STRANGE_CORE, new Item.Settings().rarity(Rarity.RARE)));

    // Ancient Rose Seed (registered directly).
    public static final Block ANCIENT_ROSE_CROP = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "ancient_rose_crop"),
            new AncientRoseCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).mapColor(DyeColor.CYAN)
            )
    );
    // Ancient Rose
    public static final Block ANCIENT_ROSE = registerBlock("ancient_rose",
            new FlowerBlock(StatusEffects.HUNGER, 20,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Potted Ancient Rose
    public static final Block POTTED_ANCIENT_ROSE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_ancient_rose"),
            new FlowerPotBlock(ANCIENT_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Ancient Rose Bush (registered directly to change rarity)
    public static final Block ANCIENT_ROSE_BUSH = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"), new TallFlowerBlock(
            (AbstractBlock.Settings.copy(Blocks.ROSE_BUSH))
    ));
    private static final Item ANCIENT_ROSE_BUSH_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "ancient_rose_bush"), new BlockItem(ANCIENT_ROSE_BUSH, new Item.Settings().rarity(Rarity.UNCOMMON)));

    // Rose
    public static final Block ROSE = registerBlock("rose",
            new FlowerBlock(StatusEffects.HUNGER, 8,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Potted Rose
    public static final Block POTTED_ROSE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_rose"),
            new FlowerPotBlock(ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );

    // Violet Rose
    public static final Block VIOLET_ROSE = registerBlock("violet_rose",
            new FlowerBlock(StatusEffects.HUNGER, 20,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Potted Violet Rose
    public static final Block POTTED_VIOLET_ROSE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_violet_rose"),
            new FlowerPotBlock(VIOLET_ROSE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Violet Rose Bush (registered directly to change rarity)
    public static final Block VIOLET_ROSE_BUSH = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"), new TallFlowerBlock(
            (AbstractBlock.Settings.copy(Blocks.ROSE_BUSH))
    ));
    private static final Item VIOLET_ROSE_BUSH_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "violet_rose_bush"), new BlockItem(VIOLET_ROSE_BUSH, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // Tower Bricks
    public static final Block TOWER_BRICKS = registerBlock("tower_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool().strength(70.0F, 800.0F).pistonBehavior(PistonBehavior.BLOCK)
            )
    );
    // Mossy Tower Bricks
    public static final Block MOSSY_TOWER_BRICKS = registerBlock("mossy_tower_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.CALCITE).requiresTool().strength(70.0F, 800.0F).pistonBehavior(PistonBehavior.BLOCK)
            )
    );

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
