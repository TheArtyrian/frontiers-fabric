package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class FDRecipeProvider extends FabricRecipeProvider
{
    public FDRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    public static void crafting(RecipeExporter exporter)
    {
        RecipeHelper.knifeHelper(exporter, ModItem.COBALT_INGOT, FDItem.COBALT_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.MOURNING_GOLD_INGOT, FDItem.MOURNING_GOLD_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.FROSTITE_INGOT, FDItem.FROSTITE_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.VERDINITE_INGOT, FDItem.VERDINITE_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.VIVULITE_INGOT, FDItem.VIVULITE_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.BRIMTAN_INGOT, FDItem.BRIMTAN_SHELL_KNIFE);
    }

    // Furn recipes
    public static void smelting(RecipeExporter exporter)
    {

    }

    // Blast Furn recipes
    public static void blasting(RecipeExporter exporter)
    {

    }

    // Smoker recipes
    public static void smoking(RecipeExporter exporter)
    {

    }

    // Campfire recipes
    public static void campfire(RecipeExporter exporter)
    {

    }

    // Smithing recipes
    public static void smithing(RecipeExporter exporter)
    {
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                FDItem.GOLDEN_KNIFE, RecipeCategory.COMBAT, FDItem.OBSIDIAN_KNIFE);

        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, FDItem.BRIMTAN_SHELL_KNIFE,
                FDItem.VIVULITE_KNIFE, RecipeCategory.COMBAT, FDItem.BRIMTAN_KNIFE);
    }

    // Stonecutting recipes
    public static void stonecutting(RecipeExporter exporter)
    {

    }
}
