package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.item.ModItem;
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, FDItem.COBALT_KNIFE)
                .pattern(" X ")
                .pattern(" / ")
                .pattern("   ")
                .input('X', ModItem.COBALT_INGOT)
                .input('/', Items.STICK)
                .criterion(hasItem(ModItem.COBALT_INGOT), conditionsFromItem(ModItem.COBALT_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, FDItem.MOURNING_GOLD_KNIFE)
                .pattern(" X ")
                .pattern(" / ")
                .pattern("   ")
                .input('X', ModItem.MOURNING_GOLD_INGOT)
                .input('/', Items.STICK)
                .criterion(hasItem(ModItem.MOURNING_GOLD_INGOT), conditionsFromItem(ModItem.MOURNING_GOLD_INGOT))
                .offerTo(exporter);
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
                FDItem.DIAMOND_KNIFE, RecipeCategory.COMBAT, FDItem.OBSIDIAN_KNIFE);
    }

    // Stonecutting recipes
    public static void stonecutting(RecipeExporter exporter)
    {

    }
}
