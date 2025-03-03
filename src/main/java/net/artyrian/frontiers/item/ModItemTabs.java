package net.artyrian.frontiers.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

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

        tab.addAfter(Items.FISHING_ROD, ModItem.COBALT_FISHING_ROD);

        tab.addAfter(Items.ENDER_PEARL, ModItem.VOID_PEARL);
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

        tab.addAfter(Items.GOLDEN_AXE, ModItem.MOURNING_GOLD_AXE);
        tab.addAfter(Items.NETHERITE_AXE, ModItem.OBSIDIAN_AXE);
        tab.addAfter(ModItem.OBSIDIAN_AXE, ModItem.COBALT_AXE);
        tab.addAfter(ModItem.COBALT_AXE, ModItem.VERDINITE_AXE);
        tab.addAfter(ModItem.VERDINITE_AXE, ModItem.FROSTITE_AXE);
        tab.addAfter(ModItem.FROSTITE_AXE, ModItem.VIVULITE_AXE);

        tab.addAfter(Items.DIAMOND_BOOTS, ModItem.NECRO_WEAVE_HELMET);
        tab.addAfter(ModItem.NECRO_WEAVE_HELMET, ModItem.NECRO_WEAVE_CHESTPLATE);
        tab.addAfter(ModItem.NECRO_WEAVE_CHESTPLATE, ModItem.NECRO_WEAVE_LEGGINGS);
        tab.addAfter(ModItem.NECRO_WEAVE_LEGGINGS, ModItem.NECRO_WEAVE_BOOTS);

        tab.addAfter(Items.NETHERITE_BOOTS, ModItem.COBALT_HELMET);
        tab.addAfter(ModItem.COBALT_HELMET, ModItem.COBALT_CHESTPLATE);
        tab.addAfter(ModItem.COBALT_CHESTPLATE, ModItem.COBALT_LEGGINGS);
        tab.addAfter(ModItem.COBALT_LEGGINGS, ModItem.COBALT_BOOTS);
        tab.addAfter(ModItem.COBALT_BOOTS, ModItem.FROSTITE_HELMET);
        tab.addAfter(ModItem.FROSTITE_HELMET, ModItem.FROSTITE_CHESTPLATE);
        tab.addAfter(ModItem.FROSTITE_CHESTPLATE, ModItem.FROSTITE_LEGGINGS);
        tab.addAfter(ModItem.FROSTITE_LEGGINGS, ModItem.FROSTITE_BOOTS);

        tab.addAfter(Items.TURTLE_HELMET, ModItem.PLATE_HELMET);
        tab.addAfter(ModItem.PLATE_HELMET, ModItem.PLATE_CHESTPLATE);

        tab.addAfter(Items.CROSSBOW, ModItem.VERDINITE_BOW);
        tab.addAfter(ModItem.VERDINITE_BOW, ModItem.TOME_OF_FANGS);
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

        tab.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.TABLET_FRAGMENT);
        tab.addAfter(ModItem.TABLET_FRAGMENT, ModItem.CURSED_TABLET);
        tab.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE);
        tab.addAfter(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.GLOWING_OBSIDIAN_UPGRADE_SMITHING_TEMPLATE);

        tab.addAfter(Items.NETHER_STAR, ModItem.WITHERED_ESSENCE);
        tab.addAfter(ModItem.WITHERED_ESSENCE, ModItem.HEART_OF_THE_WARDEN);
        tab.addAfter(Items.SHULKER_SHELL, ModItem.SHULKER_RESIDUE);
        tab.addAfter(Items.NETHER_BRICK, ModItem.NACRE_BRICK);

        tab.addAfter(Items.NETHER_WART, ModItem.WARPED_WART);

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

        tab.addAfter(Blocks.BEDROCK, ModBlocks.AESTHENOSTONE);
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.PUMPKIN_PIE, ModItem.LEVI_ROLL);
        tab.addAfter(ModItem.LEVI_ROLL, ModItem.MARSHMALLOW);
        tab.addAfter(ModItem.MARSHMALLOW, ModItem.ROASTED_MARSHMALLOW);

        tab.addAfter(Items.DRIED_KELP, ModItem.TRUFFLE);

        tab.addAfter(Items.ENCHANTED_GOLDEN_APPLE, ModItem.APPLE_OF_ENLIGHTENMENT);
    }

    // Vanilla tab - Building Blocks.
    public static void tabBuilding(FabricItemGroupEntries tab)
    {
        tab.addAfter(Blocks.DIAMOND_BLOCK, ModBlocks.MOURNING_GOLD_BLOCK);
        tab.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.COBALT_BLOCK);

        tab.addAfter(Blocks.DARK_PRISMARINE_SLAB, ModBlocks.TOWER_BRICKS);
        tab.addAfter(ModBlocks.TOWER_BRICKS, ModBlocks.MOSSY_TOWER_BRICKS);

        tab.addAfter(Blocks.RED_NETHER_BRICK_WALL, ModBlocks.RED_NETHER_BRICK_FENCE);
        tab.addAfter(ModBlocks.RED_NETHER_BRICK_FENCE, ModBlocks.BLUE_NETHER_BRICKS);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_STAIRS, ModBlocks.BLUE_NETHER_BRICK_SLAB);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_SLAB, ModBlocks.BLUE_NETHER_BRICK_WALL);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_WALL, ModBlocks.BLUE_NETHER_BRICK_FENCE);
        tab.addAfter(ModBlocks.BLUE_NETHER_BRICK_FENCE, ModBlocks.PURPLE_NETHER_BRICKS);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_STAIRS);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_STAIRS, ModBlocks.PURPLE_NETHER_BRICK_SLAB);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_SLAB, ModBlocks.PURPLE_NETHER_BRICK_WALL);
        tab.addAfter(ModBlocks.PURPLE_NETHER_BRICK_WALL, ModBlocks.PURPLE_NETHER_BRICK_FENCE);

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
