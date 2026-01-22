package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.ModItemTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

public class FDItemTabs
{
    public static final ResourceKey<CreativeModeTab> FARMERS_DELIGHT_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "farmersdelight")
            );

    // Farmer's Delight Tab
    public static void tabFD(FabricItemGroupEntries tab)
    {
        tab.addAfter(FDItem.GOLDEN_KNIFE, FDItem.MOURNING_GOLD_KNIFE);
        tab.addAfter(FDItem.NETHERITE_KNIFE, FDItem.OBSIDIAN_KNIFE);
        tab.addAfter(FDItem.OBSIDIAN_KNIFE, FDItem.COBALT_KNIFE);
        tab.addAfter(FDItem.COBALT_KNIFE, FDItem.VERDINITE_KNIFE);
        tab.addAfter(FDItem.VERDINITE_KNIFE, FDItem.FROSTITE_KNIFE);
        tab.addAfter(FDItem.FROSTITE_KNIFE, FDItem.VIVULITE_KNIFE);
        tab.addAfter(FDItem.VIVULITE_KNIFE, FDItem.BRIMTAN_KNIFE);

        tab.addAfter(FDItem.FRIED_EGG, FDItem.FRIED_GOLDEN_EGG);

        tab.addAfter(FDItem.PASTA_WITH_MUTTON_CHOP, FDItem.TRUFFLE_PASTA);
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients(FabricItemGroupEntries tab)
    {
        tab.addAfter(ModItem.BRIMTAN_SHELL_HOE, FDItem.BRIMTAN_SHELL_KNIFE);
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        // Shout in log.
        //Frontiers.LOGGER.info("Registering FD compat-exclusive item tab entries for " + Frontiers.MOD_ID);

        // Add items to their respective tabs.
        ItemGroupEvents.modifyEntriesEvent(FARMERS_DELIGHT_TAB).register(FDItemTabs::tabFD);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(FDItemTabs::tabIngredients);
    }
}
