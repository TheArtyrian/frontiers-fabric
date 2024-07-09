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
        tab.addAfter(Items.NETHERITE_HOE, ModItem.OBSIDIAN_SHOVEL);
        tab.addAfter(ModItem.OBSIDIAN_SHOVEL, ModItem.OBSIDIAN_PICKAXE);
        tab.addAfter(ModItem.OBSIDIAN_PICKAXE, ModItem.OBSIDIAN_AXE);
        tab.addAfter(ModItem.OBSIDIAN_AXE, ModItem.OBSIDIAN_HOE);

        tab.addAfter(ModItem.OBSIDIAN_HOE, ModItem.COBALT_SHOVEL);
        tab.addAfter(ModItem.COBALT_SHOVEL, ModItem.COBALT_PICKAXE);
        tab.addAfter(ModItem.COBALT_PICKAXE, ModItem.COBALT_AXE);
        tab.addAfter(ModItem.COBALT_AXE, ModItem.COBALT_HOE);

        tab.addAfter(Items.FISHING_ROD, ModItem.COBALT_FISHING_ROD);
    }

    // Vanilla tab - Combat.
    public static void tabCombat(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.NETHERITE_SWORD, ModItem.OBSIDIAN_SWORD);
        tab.addAfter(ModItem.OBSIDIAN_SWORD, ModItem.COBALT_SWORD);

        tab.addAfter(Items.NETHERITE_AXE, ModItem.OBSIDIAN_AXE);
        tab.addAfter(ModItem.OBSIDIAN_AXE, ModItem.COBALT_AXE);
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients(FabricItemGroupEntries tab)
    {
        tab.addAfter(Items.NETHERITE_INGOT, ModItem.OBSIDIAN_CASING);
        tab.addAfter(ModItem.OBSIDIAN_CASING, ModItem.COBALT_INGOT);
        tab.addAfter(ModItem.COBALT_INGOT, ModItem.BRIMTAN_INGOT);
        tab.addAfter(ModItem.BRIMTAN_INGOT, ModItem.FROSTITE_INGOT);

        tab.addAfter(Items.RAW_GOLD, ModItem.RAW_COBALT);

        tab.addBefore(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.CURSED_TABLET);
        tab.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE);

        tab.addAfter(Items.NETHER_STAR, ModItem.WITHERED_ESSENCE);
        tab.addAfter(ModItem.WITHERED_ESSENCE, ModItem.HEART_OF_THE_WARDEN);
        tab.addAfter(Items.SHULKER_SHELL, ModItem.SHULKER_RESIDUE);
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural(FabricItemGroupEntries tab)
    {
        tab.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE, ModBlocks.COBALT_ORE);

        tab.addAfter(Blocks.OBSIDIAN, ModBlocks.GLOWING_OBSIDIAN);

        tab.addAfter(Blocks.POPPY, ModBlocks.ANCIENT_ROSE);
        tab.addAfter(Blocks.ROSE_BUSH, ModBlocks.ANCIENT_ROSE_BUSH);

        tab.addAfter(Items.PITCHER_POD, ModItem.ANCIENT_ROSE_SEED);
    }

    // Vanilla tab - Building Blocks.
    public static void tabBuilding(FabricItemGroupEntries tab)
    {
        tab.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.COBALT_BLOCK);
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional(FabricItemGroupEntries tab)
    {
        tab.addAfter(Blocks.BEACON, ModBlocks.STRANGE_CORE);
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        // Shout in log.
        Frontiers.LOGGER.info("Registering Creative Tab locations for " + Frontiers.MOD_ID);

        // Add items to their respective tabs.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItemTabs::tabTools);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItemTabs::tabCombat);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItemTabs::tabIngredients);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItemTabs::tabNatural);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModItemTabs::tabBuilding);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(ModItemTabs::tabFunctional);
    }
}
