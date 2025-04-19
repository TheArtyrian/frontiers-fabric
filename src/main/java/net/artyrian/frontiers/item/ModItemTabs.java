package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.GoatHornItem;
import net.minecraft.item.Instruments;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

// Registers both items (blocks too) to Creative tabs.
public class ModItemTabs
{
    // Vanilla tab - Tools & Utilities.
    public static void tabTools(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.GOLDEN_HOE, ModItem.MOURNING_GOLD_SHOVEL);
        tab.addAfter(ModItem.MOURNING_GOLD_SHOVEL, ModItem.MOURNING_GOLD_PICKAXE);
        tab.addAfter(ModItem.MOURNING_GOLD_PICKAXE, ModItem.MOURNING_GOLD_AXE);
        tab.addAfter(ModItem.MOURNING_GOLD_AXE, ModItem.MOURNING_GOLD_HOE);

        tab.addAfter(Items.NETHERITE_HOE, ModItem.OBSIDIAN_SHOVEL);
        tab.addAfter(ModItem.OBSIDIAN_SHOVEL, ModItem.OBSIDIAN_PICKAXE);
        tab.addAfter(ModItem.OBSIDIAN_PICKAXE, ModItem.OBSIDIAN_AXE);
        tab.addAfter(ModItem.OBSIDIAN_AXE, ModItem.OBSIDIAN_HOE);

        tab.addAfter(ModItem.OBSIDIAN_HOE, ModItem.COBALT_SHOVEL);
        tab.addAfter(ModItem.COBALT_SHOVEL, ModItem.COBALT_PICKAXE);
        tab.addAfter(ModItem.COBALT_PICKAXE, ModItem.COBALT_AXE);
        tab.addAfter(ModItem.COBALT_AXE, ModItem.COBALT_HOE);

        tab.addAfter(ModItem.COBALT_HOE, ModItem.VERDINITE_SHOVEL);
        tab.addAfter(ModItem.VERDINITE_SHOVEL, ModItem.VERDINITE_PICKAXE);
        tab.addAfter(ModItem.VERDINITE_PICKAXE, ModItem.VERDINITE_AXE);
        tab.addAfter(ModItem.VERDINITE_AXE, ModItem.VERDINITE_HOE);

        tab.addAfter(ModItem.VERDINITE_HOE, ModItem.FROSTITE_SHOVEL);
        tab.addAfter(ModItem.FROSTITE_SHOVEL, ModItem.FROSTITE_PICKAXE);
        tab.addAfter(ModItem.FROSTITE_PICKAXE, ModItem.FROSTITE_AXE);
        tab.addAfter(ModItem.FROSTITE_AXE, ModItem.FROSTITE_HOE);

        tab.addAfter(ModItem.FROSTITE_HOE, ModItem.VIVULITE_SHOVEL);
        tab.addAfter(ModItem.VIVULITE_SHOVEL, ModItem.VIVULITE_PICKAXE);
        tab.addAfter(ModItem.VIVULITE_PICKAXE, ModItem.VIVULITE_AXE);
        tab.addAfter(ModItem.VIVULITE_AXE, ModItem.VIVULITE_HOE);

        tab.addAfter(ModItem.VIVULITE_HOE, ModItem.BRIMTAN_SHOVEL);
        tab.addAfter(ModItem.BRIMTAN_SHOVEL, ModItem.BRIMTAN_PICKAXE);
        tab.addAfter(ModItem.BRIMTAN_PICKAXE, ModItem.BRIMTAN_AXE);
        tab.addAfter(ModItem.BRIMTAN_AXE, ModItem.BRIMTAN_HOE);

        tab.addAfter(Items.FISHING_ROD, ModItem.COBALT_FISHING_ROD);

        tab.addAfter(Items.ENDER_PEARL, ModItem.VOID_PEARL);

        tab.addAfter(GoatHornItem.getStackForInstrument(Items.GOAT_HORN,
                Registries.INSTRUMENT.getEntry(Instruments.DREAM_GOAT_HORN).get()), ModItem.BALL);
        tab.addAfter(ModItem.BALL, ModItem.WHITE_BALL);
        tab.addAfter(ModItem.WHITE_BALL, ModItem.LIGHT_GRAY_BALL);
        tab.addAfter(ModItem.LIGHT_GRAY_BALL, ModItem.GRAY_BALL);
        tab.addAfter(ModItem.GRAY_BALL, ModItem.BLACK_BALL);
        tab.addAfter(ModItem.BLACK_BALL, ModItem.BROWN_BALL);
        tab.addAfter(ModItem.BROWN_BALL, ModItem.RED_BALL);
        tab.addAfter(ModItem.RED_BALL, ModItem.ORANGE_BALL);
        tab.addAfter(ModItem.ORANGE_BALL, ModItem.YELLOW_BALL);
        tab.addAfter(ModItem.YELLOW_BALL, ModItem.LIME_BALL);
        tab.addAfter(ModItem.LIME_BALL, ModItem.GREEN_BALL);
        tab.addAfter(ModItem.GREEN_BALL, ModItem.CYAN_BALL);
        tab.addAfter(ModItem.CYAN_BALL, ModItem.LIGHT_BLUE_BALL);
        tab.addAfter(ModItem.LIGHT_BLUE_BALL, ModItem.BLUE_BALL);
        tab.addAfter(ModItem.BLUE_BALL, ModItem.PURPLE_BALL);
        tab.addAfter(ModItem.PURPLE_BALL, ModItem.MAGENTA_BALL);
        tab.addAfter(ModItem.MAGENTA_BALL, ModItem.PINK_BALL);

        tab.addAfter(Items.MUSIC_DISC_PIGSTEP, ModItem.MUSIC_DISC_DIAPHRAGM);
    }

    // Vanilla tab - Combat.
    public static void tabCombat(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.GOLDEN_SWORD, ModItem.MOURNING_GOLD_SWORD);
        tab.addAfter(Items.NETHERITE_SWORD, ModItem.OBSIDIAN_SWORD);
        tab.addAfter(ModItem.OBSIDIAN_SWORD, ModItem.COBALT_SWORD);
        tab.addAfter(ModItem.COBALT_SWORD, ModItem.VERDINITE_SWORD);
        tab.addAfter(ModItem.VERDINITE_SWORD, ModItem.FROSTITE_SWORD);
        tab.addAfter(ModItem.FROSTITE_SWORD, ModItem.VIVULITE_SWORD);
        tab.addAfter(ModItem.VIVULITE_SWORD, ModItem.BRIMTAN_SWORD);

        tab.addAfter(Items.GOLDEN_AXE, ModItem.MOURNING_GOLD_AXE);
        tab.addAfter(Items.NETHERITE_AXE, ModItem.OBSIDIAN_AXE);
        tab.addAfter(ModItem.OBSIDIAN_AXE, ModItem.COBALT_AXE);
        tab.addAfter(ModItem.COBALT_AXE, ModItem.VERDINITE_AXE);
        tab.addAfter(ModItem.VERDINITE_AXE, ModItem.FROSTITE_AXE);
        tab.addAfter(ModItem.FROSTITE_AXE, ModItem.VIVULITE_AXE);
        tab.addAfter(ModItem.VIVULITE_AXE, ModItem.BRIMTAN_AXE);

        tab.addAfter(Items.DIAMOND_BOOTS, ModItem.NECRO_WEAVE_HELMET);
        tab.addAfter(ModItem.NECRO_WEAVE_HELMET, ModItem.NECRO_WEAVE_CHESTPLATE);
        tab.addAfter(ModItem.NECRO_WEAVE_CHESTPLATE, ModItem.NECRO_WEAVE_LEGGINGS);
        tab.addAfter(ModItem.NECRO_WEAVE_LEGGINGS, ModItem.NECRO_WEAVE_BOOTS);

        tab.addAfter(Items.NETHERITE_BOOTS, ModItem.COBALT_HELMET);
        tab.addAfter(ModItem.COBALT_HELMET, ModItem.COBALT_CHESTPLATE);
        tab.addAfter(ModItem.COBALT_CHESTPLATE, ModItem.COBALT_LEGGINGS);
        tab.addAfter(ModItem.COBALT_LEGGINGS, ModItem.COBALT_BOOTS);

        tab.addAfter(ModItem.COBALT_BOOTS, ModItem.VERDINITE_HELMET);
        tab.addAfter(ModItem.VERDINITE_HELMET, ModItem.VERDINITE_CHESTPLATE);
        tab.addAfter(ModItem.VERDINITE_CHESTPLATE, ModItem.VERDINITE_LEGGINGS);
        tab.addAfter(ModItem.VERDINITE_LEGGINGS, ModItem.VERDINITE_BOOTS);

        tab.addAfter(ModItem.VERDINITE_BOOTS, ModItem.FROSTITE_HELMET);
        tab.addAfter(ModItem.FROSTITE_HELMET, ModItem.FROSTITE_CHESTPLATE);
        tab.addAfter(ModItem.FROSTITE_CHESTPLATE, ModItem.FROSTITE_LEGGINGS);
        tab.addAfter(ModItem.FROSTITE_LEGGINGS, ModItem.FROSTITE_BOOTS);

        tab.addAfter(ModItem.FROSTITE_BOOTS, ModItem.VIVULITE_HELMET);
        tab.addAfter(ModItem.VIVULITE_HELMET, ModItem.VIVULITE_CHESTPLATE);
        tab.addAfter(ModItem.VIVULITE_CHESTPLATE, ModItem.VIVULITE_LEGGINGS);
        tab.addAfter(ModItem.VIVULITE_LEGGINGS, ModItem.VIVULITE_BOOTS);

        tab.addAfter(Items.TURTLE_HELMET, ModItem.PLATE_HELMET);
        tab.addAfter(ModItem.PLATE_HELMET, ModItem.PLATE_CHESTPLATE);

        tab.addAfter(Items.ARROW, ModItem.SUBZERO_ARROW);
        tab.addAfter(ModItem.SUBZERO_ARROW, ModItem.BOUNCY_ARROW);
        tab.addAfter(ModItem.BOUNCY_ARROW, ModItem.WARP_ARROW);

        tab.addAfter(Items.CROSSBOW, ModItem.VERDINITE_BOW);
        tab.addAfter(ModItem.VERDINITE_BOW, ModItem.TOME_OF_FANGS);

        tab.addAfter(Items.TOTEM_OF_UNDYING, ModItem.TOTEM_OF_AVARICE);
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.NETHERITE_INGOT, ModItem.OBSIDIAN_CASING);
        tab.addAfter(ModItem.OBSIDIAN_CASING, ModItem.MOURNING_GOLD_INGOT);
        tab.addAfter(ModItem.MOURNING_GOLD_INGOT, ModItem.COBALT_INGOT);
        tab.addAfter(ModItem.COBALT_INGOT, ModItem.VERDINITE_INGOT);
        tab.addAfter(ModItem.VERDINITE_INGOT, ModItem.FROSTITE_INGOT);
        tab.addAfter(ModItem.FROSTITE_INGOT, ModItem.VIVULITE_INGOT);
        tab.addAfter(ModItem.VIVULITE_INGOT, ModItem.BRIMTAN_INGOT);
        tab.addAfter(ModItem.BRIMTAN_INGOT, ModItem.PITCH_INGOT);

        tab.addAfter(Items.RAW_GOLD, ModItem.RAW_COBALT);
        tab.addAfter(ModItem.RAW_COBALT, ModItem.RAW_VERDINITE);
        tab.addAfter(ModItem.RAW_VERDINITE, ModItem.RAW_FROSTITE);
        tab.addAfter(ModItem.RAW_FROSTITE, ModItem.RAW_VIVULITE);
        tab.addAfter(ModItem.RAW_VIVULITE, ModItem.BRIMTAN_CLUSTER);

        tab.addAfter(Items.GOLD_NUGGET, ModItem.BRIMTAN_NUGGET);

        tab.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.TABLET_FRAGMENT);
        tab.addAfter(ModItem.TABLET_FRAGMENT, ModItem.CURSED_TABLET);
        tab.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE);
        tab.addAfter(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.GLOWING_OBSIDIAN_UPGRADE_SMITHING_TEMPLATE);

        tab.addAfter(Items.NETHER_STAR, ModItem.WITHERED_ESSENCE);
        tab.addAfter(ModItem.WITHERED_ESSENCE, ModItem.HEART_OF_THE_WARDEN);
        tab.addAfter(Items.SHULKER_SHELL, ModItem.SHULKER_RESIDUE);
        tab.addAfter(Items.NETHER_BRICK, ModItem.NACRE_BRICK);

        tab.addAfter(Items.NETHER_WART, ModItem.WARPED_WART);

        tab.addAfter(Items.DIAMOND, ModItem.VOID_DIAMOND);

        tab.addAfter(Items.PRISMARINE_SHARD, ModItem.PALE_PRISMARINE_SHARD);

        tab.addAfter(Items.FLINT, ModItem.SPECTRAL_ARROW_ARROWHEAD);
        tab.addAfter(ModItem.SPECTRAL_ARROW_ARROWHEAD, ModItem.SUBZERO_ARROW_ARROWHEAD);
        tab.addAfter(ModItem.SUBZERO_ARROW_ARROWHEAD, ModItem.BOUNCY_ARROW_ARROWHEAD);
        tab.addAfter(ModItem.BOUNCY_ARROW_ARROWHEAD, ModItem.WARP_ARROW_ARROWHEAD);

        tab.addAfter(Items.BONE, ModItem.ONYX_BONE);
        tab.addAfter(ModItem.ONYX_BONE, ModItem.NECRO_WEAVE);
        tab.addAfter(Items.BONE_MEAL, ModItem.ONYX_MEAL);

        tab.addAfter(Items.ENDER_PEARL, ModItem.VOID_PEARL);

        tab.addAfter(Items.GHAST_TEAR, ModItem.ECTOPLASM);
        tab.addBefore(Items.PRISMARINE_SHARD, ModItem.INVOKE_SHARD);
        tab.addAfter(ModItem.INVOKE_SHARD, ModItem.INCENSE);
        tab.addAfter(ModItem.INCENSE, ModItem.RAVAGER_TOOTH);

        tab.addBefore(Items.DRAGON_BREATH, ModItem.LIGHTNING_IN_A_BOTTLE);

        tab.addAfter(Items.OMINOUS_TRIAL_KEY, ModItem.TOWER_KEY);
        tab.addAfter(ModItem.TOWER_KEY, ModItem.TOWER_KEY_FRAGMENT);
        tab.addAfter(ModItem.TOWER_KEY_FRAGMENT, ModItem.UNFINISHED_CORE);
        tab.addAfter(ModItem.UNFINISHED_CORE, ModItem.REACTIVE_CORE);
        tab.addAfter(ModItem.REACTIVE_CORE, ModItem.DEPTHS_CORE_PLATE);
        tab.addAfter(ModItem.DEPTHS_CORE_PLATE, ModItem.FRONTAL_CORE_PLATE);
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural(FabricItemGroupEntries tab)
    {
        tab.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, ModBlocks.COBALT_ORE);
        tab.addAfter(ModBlocks.COBALT_ORE, ModBlocks.DEEPSLATE_COBALT_ORE);
        tab.addAfter(ModBlocks.DEEPSLATE_COBALT_ORE, ModBlocks.VERDINITE_ORE);
        tab.addAfter(ModBlocks.VERDINITE_ORE, ModBlocks.DEEPSLATE_VERDINITE_ORE);
        tab.addAfter(ModBlocks.DEEPSLATE_VERDINITE_ORE, ModBlocks.VIVULITE_ORE);
        tab.addAfter(ModBlocks.VIVULITE_ORE, ModBlocks.DEEPSLATE_VIVULITE_ORE);
        tab.addAfter(ModBlocks.DEEPSLATE_VIVULITE_ORE, ModBlocks.FROSTITE_ORE);

        tab.addAfter(Blocks.OBSIDIAN, ModBlocks.GLOWING_OBSIDIAN);

        tab.addAfter(Blocks.TUFF, ModBlocks.HIELOSTONE);

        tab.addAfter(Blocks.POPPY, ModBlocks.ROSE);
        tab.addAfter(ModBlocks.ROSE, ModBlocks.ANCIENT_ROSE);
        tab.addAfter(ModBlocks.ANCIENT_ROSE, ModBlocks.VIOLET_ROSE);
        tab.addAfter(Blocks.ROSE_BUSH, ModBlocks.ANCIENT_ROSE_BUSH);
        tab.addAfter(ModBlocks.ANCIENT_ROSE_BUSH, ModBlocks.VIOLET_ROSE_BUSH);

        tab.addAfter(Blocks.BONE_BLOCK, ModBlocks.ONYX_BONE_BLOCK);

        tab.addAfter(Blocks.LILY_OF_THE_VALLEY, ModBlocks.SNOW_DAHLIA);
        tab.addAfter(ModBlocks.SNOW_DAHLIA, ModBlocks.FUNGAL_DAFFODIL);
        tab.addAfter(ModBlocks.FUNGAL_DAFFODIL, ModBlocks.CRIMCONE);

        tab.addAfter(Items.PITCHER_POD, ModItem.ANCIENT_ROSE_SEED);
        tab.addAfter(Items.NETHER_WART, ModItem.WARPED_WART);

        tab.addAfter(Blocks.AMETHYST_CLUSTER, ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD);
        tab.addAfter(ModBlocks.SMALL_CORRUPTED_AMETHYST_BUD, ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD);
        tab.addAfter(ModBlocks.MEDIUM_CORRUPTED_AMETHYST_BUD, ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD);
        tab.addAfter(ModBlocks.LARGE_CORRUPTED_AMETHYST_BUD, ModBlocks.CORRUPTED_AMETHYST_CLUSTER);

        tab.addAfter(Blocks.SAND, ModBlocks.QUICKSAND);
        tab.addAfter(Blocks.RED_SAND, ModBlocks.RED_QUICKSAND);

        tab.addBefore(Blocks.END_STONE, ModBlocks.CRAGULSTANE);

        tab.addAfter(Blocks.ANCIENT_DEBRIS, ModBlocks.BRIMTAN_ORE);

        tab.addAfter(Blocks.BEDROCK, ModBlocks.AESTHENOSTONE);
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.PUMPKIN_PIE, ModItem.LEVI_ROLL);
        tab.addAfter(ModItem.LEVI_ROLL, ModItem.MARSHMALLOW);
        tab.addAfter(ModItem.MARSHMALLOW, ModItem.ROASTED_MARSHMALLOW);

        tab.addAfter(Items.DRIED_KELP, ModItem.TRUFFLE);

        tab.addAfter(Items.PUFFERFISH, ModItem.GUARDIAN_SLICE);
        tab.addAfter(ModItem.GUARDIAN_SLICE, ModItem.COOKED_GUARDIAN_SLICE);
        tab.addAfter(ModItem.COOKED_GUARDIAN_SLICE, ModItem.ELDER_GUARDIAN_SLICE);
        tab.addAfter(ModItem.ELDER_GUARDIAN_SLICE, ModItem.COOKED_ELDER_GUARDIAN_SLICE);

        tab.addAfter(Items.ENCHANTED_GOLDEN_APPLE, ModItem.APPLE_OF_ENLIGHTENMENT);

        tab.addAfter(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE);
    }

    // Vanilla tab - Building Blocks.
    public static void tabBuilding(FabricItemGroupEntries tab)
    {
        tab.addAfter(Blocks.DIAMOND_BLOCK, ModBlocks.MOURNING_GOLD_BLOCK);
        tab.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.COBALT_BLOCK);
        tab.addAfter(ModBlocks.COBALT_BLOCK, ModBlocks.VERDINITE_BLOCK);
        tab.addAfter(ModBlocks.VERDINITE_BLOCK, ModBlocks.FROSTITE_BLOCK);
        tab.addAfter(ModBlocks.FROSTITE_BLOCK, ModBlocks.VIVULITE_BLOCK);

        tab.addBefore(Blocks.PRISMARINE, ModBlocks.SEA_GLASS);
        tab.addAfter(ModBlocks.SEA_GLASS, ModBlocks.SEA_GLASS_PANE);

        tab.addAfter(Blocks.DARK_PRISMARINE_SLAB, ModBlocks.PALE_SEA_GLASS);
        tab.addAfter(ModBlocks.PALE_SEA_GLASS, ModBlocks.PALE_SEA_GLASS_PANE);
        tab.addAfter(ModBlocks.PALE_SEA_GLASS_PANE, ModBlocks.PALE_PRISMARINE);
        tab.addAfter(ModBlocks.PALE_PRISMARINE, ModBlocks.PALE_PRISMARINE_STAIRS);
        tab.addAfter(ModBlocks.PALE_PRISMARINE_STAIRS, ModBlocks.PALE_PRISMARINE_SLAB);
        tab.addAfter(ModBlocks.PALE_PRISMARINE_SLAB, ModBlocks.PALE_PRISMARINE_WALL);
        tab.addAfter(ModBlocks.PALE_PRISMARINE_WALL, ModBlocks.PALE_PRISMARINE_BRICKS);
        tab.addAfter(ModBlocks.PALE_PRISMARINE_BRICKS, ModBlocks.PALE_PRISMARINE_BRICK_STAIRS);
        tab.addAfter(ModBlocks.PALE_PRISMARINE_BRICK_STAIRS, ModBlocks.PALE_PRISMARINE_BRICK_SLAB);
        tab.addAfter(ModBlocks.PALE_PRISMARINE_BRICK_SLAB, ModBlocks.DEEP_PALE_PRISMARINE);
        tab.addAfter(ModBlocks.DEEP_PALE_PRISMARINE, ModBlocks.DEEP_PALE_PRISMARINE_STAIRS);
        tab.addAfter(ModBlocks.DEEP_PALE_PRISMARINE_STAIRS, ModBlocks.DEEP_PALE_PRISMARINE_SLAB);

        tab.addAfter(ModBlocks.DEEP_PALE_PRISMARINE_SLAB, ModBlocks.TOWER_BRICKS);
        tab.addAfter(ModBlocks.TOWER_BRICKS, ModBlocks.MOSSY_TOWER_BRICKS);

        tab.addAfter(Blocks.NETHER_BRICK_FENCE, ModBlocks.NETHER_BRICK_FENCE_GATE);

        tab.addAfter(Blocks.RED_NETHER_BRICKS, ModBlocks.CRACKED_RED_NETHER_BRICKS);
        tab.addAfter(Blocks.RED_NETHER_BRICK_WALL, ModBlocks.RED_NETHER_BRICK_FENCE);
        tab.addAfter(ModBlocks.RED_NETHER_BRICK_FENCE, ModBlocks.RED_NETHER_BRICK_FENCE_GATE);
        tab.addAfter(ModBlocks.RED_NETHER_BRICK_FENCE_GATE, ModBlocks.CHISELED_RED_NETHER_BRICKS);

        tab.addAfter(ModBlocks.CHISELED_RED_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICKS);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.CRACKED_BLUE_NETHER_BRICKS);
        tab.addAfter(ModBlocks.CRACKED_BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_STAIRS, ModBlocks.BLUE_NETHER_BRICK_SLAB);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_SLAB, ModBlocks.BLUE_NETHER_BRICK_WALL);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_WALL, ModBlocks.BLUE_NETHER_BRICK_FENCE);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE, ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE, ModBlocks.CHISELED_BLUE_NETHER_BRICKS);

        tab.addAfter(ModBlocks.CHISELED_BLUE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICKS);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.CRACKED_PURPLE_NETHER_BRICKS);
        tab.addAfter(ModBlocks.CRACKED_PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_STAIRS);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_STAIRS, ModBlocks.PURPLE_NETHER_BRICK_SLAB);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_SLAB, ModBlocks.PURPLE_NETHER_BRICK_WALL);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_WALL, ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_FENCE, ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE, ModBlocks.CHISELED_PURPLE_NETHER_BRICKS);

        tab.addAfter(Blocks.PURPUR_SLAB, ModBlocks.NACRE_BRICKS);
        tab.addAfter(ModBlocks.NACRE_BRICKS, ModBlocks.NACRE_BRICK_STAIRS);
        tab.addAfter(ModBlocks.NACRE_BRICK_STAIRS, ModBlocks.NACRE_BRICK_SLAB);
        tab.addAfter(ModBlocks.NACRE_BRICK_SLAB, ModBlocks.NACRE_BRICK_WALL);

        tab.addBefore(Blocks.END_STONE, ModBlocks.CRAGULSTANE);
        tab.addAfter(ModBlocks.CRAGULSTANE, ModBlocks.CRAGULSTANE_BRICKS);
        tab.addAfter(ModBlocks.CRAGULSTANE_BRICKS, ModBlocks.CRAGULSTANE_BRICK_STAIRS);
        tab.addAfter(ModBlocks.CRAGULSTANE_BRICK_STAIRS, ModBlocks.CRAGULSTANE_BRICK_SLAB);
        tab.addAfter(ModBlocks.CRAGULSTANE_BRICK_SLAB, ModBlocks.CRAGULSTANE_BRICK_WALL);
        tab.addAfter(ModBlocks.CRAGULSTANE_BRICK_WALL, ModBlocks.CHISELED_CRAGULSTANE_BRICKS);

        tab.addBefore(Blocks.SEA_LANTERN, ModBlocks.TURTLE_SCUTE_BRICKS);
        tab.addAfter(ModBlocks.TURTLE_SCUTE_BRICKS, ModBlocks.TURTLE_SCUTE_BRICK_STAIRS);
        tab.addAfter(ModBlocks.TURTLE_SCUTE_BRICK_STAIRS, ModBlocks.TURTLE_SCUTE_BRICK_SLAB);
        tab.addAfter(ModBlocks.TURTLE_SCUTE_BRICK_SLAB, ModBlocks.TURTLE_SCUTE_BRICK_WALL);

        tab.addAfter(Blocks.CHISELED_TUFF_BRICKS, ModBlocks.HIELOSTONE);
        tab.addAfter(ModBlocks.HIELOSTONE, ModBlocks.HIELOSTONE_STAIRS);
        tab.addAfter(ModBlocks.HIELOSTONE_STAIRS, ModBlocks.HIELOSTONE_SLAB);
        tab.addAfter(ModBlocks.HIELOSTONE_SLAB, ModBlocks.HIELOSTONE_WALL);

        tab.addAfter(ModBlocks.HIELOSTONE_WALL, ModBlocks.HIELOSTONE_BRICKS);
        tab.addAfter(ModBlocks.HIELOSTONE_BRICKS, ModBlocks.HIELOSTONE_BRICK_STAIRS);
        tab.addAfter(ModBlocks.HIELOSTONE_BRICK_STAIRS, ModBlocks.HIELOSTONE_BRICK_SLAB);
        tab.addAfter(ModBlocks.HIELOSTONE_BRICK_SLAB, ModBlocks.HIELOSTONE_BRICK_WALL);

        tab.addAfter(ModBlocks.HIELOSTONE_BRICK_WALL, ModBlocks.HIELOSTONE_TILES);
        tab.addAfter(ModBlocks.HIELOSTONE_TILES, ModBlocks.HIELOSTONE_TILE_STAIRS);
        tab.addAfter(ModBlocks.HIELOSTONE_TILE_STAIRS, ModBlocks.HIELOSTONE_TILE_SLAB);
        tab.addAfter(ModBlocks.HIELOSTONE_TILE_SLAB, ModBlocks.HIELOSTONE_TILE_WALL);

        tab.addAfter(ModBlocks.HIELOSTONE_TILE_WALL, ModBlocks.HIELOSTONE_PLATES);
        tab.addAfter(ModBlocks.HIELOSTONE_PLATES, ModBlocks.HIELOSTONE_PLATE_STAIRS);
        tab.addAfter(ModBlocks.HIELOSTONE_PLATE_STAIRS, ModBlocks.HIELOSTONE_PLATE_SLAB);
        tab.addAfter(ModBlocks.HIELOSTONE_PLATE_SLAB, ModBlocks.HIELOSTONE_PLATE_WALL);

        tab.addAfter(ModBlocks.HIELOSTONE_PLATE_WALL, ModBlocks.COBBLEFROST);
        tab.addAfter(ModBlocks.COBBLEFROST, ModBlocks.COBBLEFROST_STAIRS);
        tab.addAfter(ModBlocks.COBBLEFROST_STAIRS, ModBlocks.COBBLEFROST_SLAB);
        tab.addAfter(ModBlocks.COBBLEFROST_SLAB, ModBlocks.COBBLEFROST_WALL);
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.END_CRYSTAL, ModItem.PURIFIED_END_CRYSTAL);
        tab.addAfter(Blocks.BEACON, ModBlocks.STRANGE_CORE);

        tab.addAfter(Items.REDSTONE_LAMP, ModBlocks.DIAMOND_LUMEN);
        tab.addAfter(ModBlocks.DIAMOND_LUMEN, ModBlocks.REDSTONE_LUMEN);
        tab.addAfter(ModBlocks.REDSTONE_LUMEN, ModBlocks.QUARTZ_LUMEN);
        tab.addAfter(ModBlocks.QUARTZ_LUMEN, ModBlocks.EMERALD_LUMEN);
        tab.addAfter(ModBlocks.EMERALD_LUMEN, ModBlocks.AMETHYST_LUMEN);
        tab.addAfter(ModBlocks.AMETHYST_LUMEN, ModBlocks.ECHO_LUMEN);
        tab.addAfter(ModBlocks.ECHO_LUMEN, ModBlocks.COBALT_LUMEN);
        tab.addAfter(ModBlocks.COBALT_LUMEN, ModBlocks.VERDINITE_LUMEN);
        tab.addAfter(ModBlocks.VERDINITE_LUMEN, ModBlocks.FROSTITE_LUMEN);
        tab.addAfter(ModBlocks.FROSTITE_LUMEN, ModBlocks.VIVULITE_LUMEN);
        tab.addAfter(ModBlocks.VIVULITE_LUMEN, ModBlocks.BRIMTAN_LUMEN);

        tab.addBefore(Items.SKELETON_SKULL, ModBlocks.CREEPER_MODEL);
        tab.addAfter(ModBlocks.CREEPER_MODEL, ModBlocks.SLIME_MODEL);
        tab.addAfter(ModBlocks.SLIME_MODEL, ModBlocks.SKELETON_MODEL);
        tab.addAfter(ModBlocks.SKELETON_MODEL, ModBlocks.STRAY_MODEL);
        tab.addAfter(ModBlocks.STRAY_MODEL, ModBlocks.BOGGED_MODEL);
        tab.addAfter(ModBlocks.BOGGED_MODEL, ModBlocks.WITHER_SKELETON_MODEL);
        tab.addAfter(ModBlocks.WITHER_SKELETON_MODEL, ModBlocks.BLAZE_MODEL);
        tab.addAfter(ModBlocks.BLAZE_MODEL, ModBlocks.MAGMA_CUBE_MODEL);
        tab.addAfter(ModBlocks.MAGMA_CUBE_MODEL, ModBlocks.ENDERMAN_MODEL);

        tab.addAfter(Blocks.DAMAGED_ANVIL, ModBlocks.VIVULITE_ANVIL);
    }

    // Vanilla tab - Redstone Blocks.
    public static void tabRedstone(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.LIGHTNING_ROD, ModBlocks.DIAMOND_LUMEN);
        tab.addAfter(ModBlocks.DIAMOND_LUMEN, ModBlocks.REDSTONE_LUMEN);
        tab.addAfter(ModBlocks.REDSTONE_LUMEN, ModBlocks.QUARTZ_LUMEN);
        tab.addAfter(ModBlocks.QUARTZ_LUMEN, ModBlocks.EMERALD_LUMEN);
        tab.addAfter(ModBlocks.EMERALD_LUMEN, ModBlocks.AMETHYST_LUMEN);
        tab.addAfter(ModBlocks.AMETHYST_LUMEN, ModBlocks.ECHO_LUMEN);
        tab.addAfter(ModBlocks.ECHO_LUMEN, ModBlocks.COBALT_LUMEN);
        tab.addAfter(ModBlocks.COBALT_LUMEN, ModBlocks.VERDINITE_LUMEN);
        tab.addAfter(ModBlocks.VERDINITE_LUMEN, ModBlocks.FROSTITE_LUMEN);
        tab.addAfter(ModBlocks.FROSTITE_LUMEN, ModBlocks.VIVULITE_LUMEN);
        tab.addAfter(ModBlocks.VIVULITE_LUMEN, ModBlocks.BRIMTAN_LUMEN);

        tab.addAfter(Items.ARMOR_STAND, ModBlocks.BLAZE_MODEL);
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        // Shout in log.
        //Frontiers.LOGGER.info("Registering Creative Tab locations for " + Frontiers.MOD_ID);

        // Add items to their respective tabs.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItemTabs::tabTools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItemTabs::tabCombat);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItemTabs::tabIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItemTabs::tabNatural);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItemTabs::tabBuilding);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItemTabs::tabFunctional);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(ModItemTabs::tabRedstone);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItemTabs::tabFood);
    }
}
