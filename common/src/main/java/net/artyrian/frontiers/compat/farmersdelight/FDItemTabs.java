package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.vertisoft.vectorlib.agnostic.util.VectorItemTab;

import java.util.ArrayList;
import java.util.List;

public class FDItemTabs
{
    public static final ResourceKey<CreativeModeTab> FARMERS_DELIGHT_TAB =
            ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(Frontiers.FARMERS_DELIGHT_ID, "farmersdelight")
    );

    private static final List<VectorItemTab> ALL_TABS = new ArrayList<>();

    private static final VectorItemTab FARMERSDELIGHT = VectorItemTab.create(FARMERS_DELIGHT_TAB, Frontiers.FARMERS_DELIGHT_LOADED, ALL_TABS);
    private static final VectorItemTab INGREDIENTS = VectorItemTab.create(VectorItemTab.VanillaTab.INGREDIENTS, ALL_TABS);

    // Farmer's Delight Tab
    public static void tabFD()
    {
        FARMERSDELIGHT.addAfter(FDItem.GOLDEN_KNIFE.get(), FDItem.MOURNING_GOLD_KNIFE.get());
        FARMERSDELIGHT.addAfter(FDItem.NETHERITE_KNIFE.get(), FDItem.OBSIDIAN_KNIFE.get());
        FARMERSDELIGHT.addAfter(FDItem.OBSIDIAN_KNIFE.get(), FDItem.COBALT_KNIFE.get());
        FARMERSDELIGHT.addAfter(FDItem.COBALT_KNIFE.get(), FDItem.VERDINITE_KNIFE.get());
        FARMERSDELIGHT.addAfter(FDItem.VERDINITE_KNIFE.get(), FDItem.FROSTITE_KNIFE.get());
        FARMERSDELIGHT.addAfter(FDItem.FROSTITE_KNIFE.get(), FDItem.VIVULITE_KNIFE.get());
        FARMERSDELIGHT.addAfter(FDItem.VIVULITE_KNIFE.get(), FDItem.BRIMTAN_KNIFE.get());

        FARMERSDELIGHT.addAfter(FDItem.FRIED_EGG.get(), FDItem.FRIED_GOLDEN_EGG.get());

        FARMERSDELIGHT.addAfter(FDItem.PASTA_WITH_MUTTON_CHOP.get(), FDItem.TRUFFLE_PASTA.get());
    }

    // Vanilla tab - Ingredients.
    public static void tabIngredients()
    {
        INGREDIENTS.addAfter(FRItems.BRIMTAN_SHELL_HOE.get(), FDItem.BRIMTAN_SHELL_KNIFE.get());
    }

    // Registers the creative tabs for all modded items/blocks.
    public static void registerModItemTabs()
    {
        tabFD();
        tabIngredients();

        for (VectorItemTab tab : ALL_TABS)
        {
            tab.build();
        }
    }
}
