package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.ModItemTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;

public class BFItemTabs
{
    public static final ResourceKey<CreativeModeTab> BOUNTIFUL_FARES_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Frontiers.BOUNTIFUL_FARES_ID, "bountiful_fares")
            );

    // Bountiful Fares Tab
    public static void tabBF(FabricItemGroupEntries tab)
    {
        tab.addAfter(BFItem.COCONUT_CRUSTED_COD, BFItem.BREADED_GUARDIAN);
        tab.addAfter(BFItem.BREADED_GUARDIAN, BFItem.GUARDIAN_SOUP);
        tab.addAfter(BFItem.GUARDIAN_SOUP, BFItem.ELDEN_BOWL);
        tab.addAfter(BFItem.COCONUT_MILK_BOTTLE, BFItem.MELON_SPRITZER_BOTTLE);
        tab.addAfter(BFItem.MELON_SPRITZER_BOTTLE, BFItem.GLISTERING_SPRITZER_BOTTLE);
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional(FabricItemGroupEntries tab)
    {
        tab.addAfter(ModBlocks.BRIMTAN_LUMEN, BFBlock.FELDSPAR_LUMEN);
    }

    // Vanilla tab - Redstone Blocks.
    public static void tabRedstone(FabricItemGroupEntries tab)
    {
        tab.addAfter(ModBlocks.BRIMTAN_LUMEN, BFBlock.FELDSPAR_LUMEN);
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural(FabricItemGroupEntries tab)
    {
        tab.addAfter(ModBlocks.AZALEA_WREATH, BFBlock.APPLE_WREATH);
        tab.addAfter(BFBlock.APPLE_WREATH, BFBlock.ORANGE_WREATH);
        tab.addAfter(BFBlock.ORANGE_WREATH, BFBlock.LEMON_WREATH);
        tab.addAfter(BFBlock.LEMON_WREATH, BFBlock.PLUM_WREATH);
        tab.addAfter(BFBlock.PLUM_WREATH, BFBlock.GOLDEN_WREATH);
        tab.addAfter(BFBlock.GOLDEN_WREATH, BFBlock.WALNUT_WREATH);
        tab.addAfter(BFBlock.WALNUT_WREATH, BFBlock.HOARY_WREATH);
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood(FabricItemGroupEntries tab)
    {
        tab.addAfter(ModItem.COOKED_ELDER_GUARDIAN_SLICE, BFItem.BREADED_GUARDIAN);

        tab.addAfter(Items.RABBIT_STEW, BFItem.GUARDIAN_SOUP);
        tab.addAfter(BFItem.GUARDIAN_SOUP, BFItem.ELDEN_BOWL);

        tab.addAfter(Items.GLISTERING_MELON_SLICE, BFItem.MELON_SPRITZER_BOTTLE);
        tab.addAfter(BFItem.MELON_SPRITZER_BOTTLE, BFItem.GLISTERING_SPRITZER_BOTTLE);
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        // Shout in log.
        //Frontiers.LOGGER.info("Registering BF compat-exclusive item tab entries for " + Frontiers.MOD_ID);

        // Add items to their respective tabs.
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(BFItemTabs::tabFunctional);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(BFItemTabs::tabRedstone);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(BFItemTabs::tabFood);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(BFItemTabs::tabNatural);
        ItemGroupEvents.modifyEntriesEvent(BOUNTIFUL_FARES_TAB).register(BFItemTabs::tabBF);
    }
}
