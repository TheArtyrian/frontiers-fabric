package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItemTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class FDItemTabs
{
    public static final RegistryKey<ItemGroup> FARMERS_DELIGHT_TAB =
            RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Frontiers.FARMERS_DELIGHT_ID, "farmersdelight")
            );

    // Vanilla tab - Building Blocks.
    public static void tabFD(FabricItemGroupEntries tab)
    {
        tab.addAfter(FDItem.GOLDEN_KNIFE, FDItem.MOURNING_GOLD_KNIFE);
        tab.addAfter(FDItem.NETHERITE_KNIFE, FDItem.OBSIDIAN_KNIFE);
        tab.addAfter(FDItem.OBSIDIAN_KNIFE, FDItem.COBALT_KNIFE);
        tab.addAfter(FDItem.COBALT_KNIFE, FDItem.VERDINITE_KNIFE);
        tab.addAfter(FDItem.VERDINITE_KNIFE, FDItem.FROSTITE_KNIFE);
        tab.addAfter(FDItem.FROSTITE_KNIFE, FDItem.VIVULITE_KNIFE);
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        // Shout in log.
        //Frontiers.LOGGER.info("Registering FD compat-exclusive item tab entries for " + Frontiers.MOD_ID);

        // Add items to their respective tabs.
        ItemGroupEvents.modifyEntriesEvent(FARMERS_DELIGHT_TAB).register(FDItemTabs::tabFD);
    }
}
