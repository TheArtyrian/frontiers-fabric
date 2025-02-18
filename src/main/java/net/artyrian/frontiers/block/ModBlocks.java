package net.artyrian.frontiers.block;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.*;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.sounds.ModBlockSoundGroups;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.ToIntFunction;

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
    // Deepslate Cobalt ore (drops Experience)
    public static final Block DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            new ExperienceDroppingBlock(
                    UniformIntProvider.create(0, 3),
                    AbstractBlock.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
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
                            .requiresTool()
            )
    );
    // Strange Core (registered directly to change rarity).
    public static final Block STRANGE_CORE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "strange_core"), new NetherReactorBlockLol(
            AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK)
                    .mapColor(DyeColor.BLUE)
                    .hardness(10.0F)
                    .luminance(strangeCoreLightHelper(5, 15))
                    .requiresTool()
    ));
    private static final Item STRANGE_CORE_ITEM = Registry.register(Registries.ITEM, Identifier.of(Frontiers.MOD_ID, "strange_core"), new BlockItem(STRANGE_CORE, new Item.Settings().rarity(Rarity.RARE)));

    // Ancient Rose Seed (registered directly).
    public static final Block ANCIENT_ROSE_CROP = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "ancient_rose_crop"),
            new AncientRoseCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).mapColor(DyeColor.CYAN)
            )
    );
    // Ancient Rose
    public static final Block ANCIENT_ROSE = registerBlock("ancient_rose",
            new RoseFlowerBlock(ModBlocks.VIOLET_ROSE, StatusEffects.HUNGER, 20,
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
            new RoseFlowerBlock(ModBlocks.VIOLET_ROSE, StatusEffects.HUNGER, 8,
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
    // Nacre Bricks
    public static final Block NACRE_BRICKS = registerBlock("nacre_bricks",
            new Block(
                    AbstractBlock.Settings.copy(Blocks.BRICKS).sounds(BlockSoundGroup.CALCITE).mapColor(MapColor.PALE_YELLOW)
            )
    );
    // Snow Dahlia
    public static final Block SNOW_DAHLIA = registerBlock("snow_dahlia",
            new FlowerBlock(StatusEffects.SLOWNESS, 4,
                    AbstractBlock.Settings.copy(Blocks.POPPY).nonOpaque().noCollision())
    );
    // Fungal Daffodil
    public static final Block FUNGAL_DAFFODIL = registerBlock("fungal_daffodil",
            new SpecialPlaceableFlowerBlock(Blocks.MYCELIUM, StatusEffects.NAUSEA, 6,
                    AbstractBlock.Settings.copy(Blocks.POPPY).mapColor(MapColor.PALE_PURPLE).nonOpaque().noCollision())
    );
    // Crimcone
    public static final Block CRIMCONE = registerBlock("crimcone",
            new SpecialPlaceableFlowerBlock(Blocks.CRIMSON_NYLIUM, StatusEffects.MINING_FATIGUE, 10,
                    AbstractBlock.Settings.copy(Blocks.CRIMSON_FUNGUS).nonOpaque().noCollision())
    );
    // Potted Snow Dahlia
    public static final Block POTTED_SNOW_DAHLIA = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_snow_dahlia"),
            new FlowerPotBlock(SNOW_DAHLIA, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Potted Fungal Daffodil
    public static final Block POTTED_FUNGAL_DAFFODIL = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_fungal_daffodil"),
            new FlowerPotBlock(FUNGAL_DAFFODIL, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Potted Crimcone
    public static final Block POTTED_CRIMCONE = Registry.register(Registries.BLOCK, Identifier.of(Frontiers.MOD_ID, "potted_crimcone"),
            new FlowerPotBlock(CRIMCONE, AbstractBlock.Settings.copy(Blocks.POTTED_POPPY).nonOpaque())
    );
    // Cragulstane
    public static final Block CRAGULSTANE = registerBlock("cragulstane",
            new Block(AbstractBlock.Settings.create()
                    .strength(10.0F, 800.0F)
                    .mapColor(MapColor.DULL_RED)
                    .requiresTool()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sounds(ModBlockSoundGroups.CRAGULSTANE)
            )
    );

    // Aesthenostone
    public static final Block AESTHENOSTONE = registerBlock("aesthenostone",
            new CoreBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .luminance(state -> 3)
                    .emissiveLighting(Blocks::always)
            )
    );
    // #############################################################################

    // Get light based on the Mod Property ACTIVE_POWER.
    public static ToIntFunction<BlockState> strangeCoreLightHelper(int litLevel1, int litLevel2) {

        return state -> switch (state.get(ModBlockProperties.ACTIVE_POWER))
        {
            case 0 -> litLevel1;
            case 1 -> litLevel2;
            case 2 -> 0;
            default -> litLevel1;
        };
    }

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
        //Frontiers.LOGGER.info("Registering Mod Blocks for " + Frontiers.MOD_ID);
    }
}
