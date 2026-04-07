package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.vertisoft.vectorlib.agnostic.util.VectorItemTab;

import java.util.ArrayList;
import java.util.List;

public class BFItemTabs
{
    public static final ResourceKey<CreativeModeTab> BOUNTIFUL_FARES_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, Frontiers.id(Frontiers.BOUNTIFUL_FARES_ID, "bountiful_fares"));

    private static final List<VectorItemTab> ALL_TABS = new ArrayList<>();

    private static final VectorItemTab BOUNTIFULFARES = new VectorItemTab(BOUNTIFUL_FARES_TAB, ALL_TABS);
    private static final VectorItemTab NATURAL = new VectorItemTab(VectorItemTab.VanillaTab.NATURAL, ALL_TABS);
    private static final VectorItemTab FUNCTIONAL = new VectorItemTab(VectorItemTab.VanillaTab.FUNCTIONAL, ALL_TABS);
    private static final VectorItemTab REDSTONE = new VectorItemTab(VectorItemTab.VanillaTab.REDSTONE, ALL_TABS);
    private static final VectorItemTab FOOD_AND_DRINK = new VectorItemTab(VectorItemTab.VanillaTab.FOOD, ALL_TABS);

    // Bountiful Fares Tab
    public static void tabBF()
    {
        BOUNTIFULFARES.addAfter(BFItem.COCONUT_CRUSTED_COD.get(), BFItem.BREADED_GUARDIAN.get());
        BOUNTIFULFARES.addAfter(BFItem.BREADED_GUARDIAN.get(), BFItem.GUARDIAN_SOUP.get());
        BOUNTIFULFARES.addAfter(BFItem.GUARDIAN_SOUP.get(), BFItem.ELDEN_BOWL.get());
        BOUNTIFULFARES.addAfter(BFItem.COCONUT_MILK_BOTTLE.get(), BFItem.MELON_SPRITZER_BOTTLE.get());
        BOUNTIFULFARES.addAfter(BFItem.MELON_SPRITZER_BOTTLE.get(), BFItem.GLISTERING_SPRITZER_BOTTLE.get());
    }

    // Vanilla tab - Functional Blocks.
    public static void tabFunctional()
    {
        FUNCTIONAL.addAfter(FRBlocks.BRIMTAN_LUMEN.get(), BFBlock.FELDSPAR_LUMEN.get());
    }

    // Vanilla tab - Redstone Blocks.
    public static void tabRedstone()
    {
        REDSTONE.addAfter(FRBlocks.BRIMTAN_LUMEN.get(), BFBlock.FELDSPAR_LUMEN.get());
    }

    // Vanilla tab - Natural Blocks.
    public static void tabNatural()
    {
        NATURAL.addAfter(FRBlocks.AZALEA_WREATH.get(), BFBlock.APPLE_WREATH.get());
        NATURAL.addAfter(BFBlock.APPLE_WREATH.get(), BFBlock.ORANGE_WREATH.get());
        NATURAL.addAfter(BFBlock.ORANGE_WREATH.get(), BFBlock.LEMON_WREATH.get());
        NATURAL.addAfter(BFBlock.LEMON_WREATH.get(), BFBlock.PLUM_WREATH.get());
        NATURAL.addAfter(BFBlock.PLUM_WREATH.get(), BFBlock.GOLDEN_WREATH.get());
        NATURAL.addAfter(BFBlock.GOLDEN_WREATH.get(), BFBlock.WALNUT_WREATH.get());
        NATURAL.addAfter(BFBlock.WALNUT_WREATH.get(), BFBlock.HOARY_WREATH.get());
    }

    // Vanilla tab - Food & Drinks.
    public static void tabFood()
    {
        FOOD_AND_DRINK.addAfter(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), BFItem.BREADED_GUARDIAN.get());

        FOOD_AND_DRINK.addAfter(Items.RABBIT_STEW, BFItem.GUARDIAN_SOUP.get());
        FOOD_AND_DRINK.addAfter(BFItem.GUARDIAN_SOUP.get(), BFItem.ELDEN_BOWL.get());

        FOOD_AND_DRINK.addAfter(Items.GLISTERING_MELON_SLICE, BFItem.MELON_SPRITZER_BOTTLE.get());
        FOOD_AND_DRINK.addAfter(BFItem.MELON_SPRITZER_BOTTLE.get(), BFItem.GLISTERING_SPRITZER_BOTTLE.get());
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        tabBF();
        tabNatural();
        tabFunctional();
        tabRedstone();
        tabFood();

        for (VectorItemTab tab : ALL_TABS)
        {
            tab.build();
        }
    }
}
