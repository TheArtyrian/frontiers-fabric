package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

// A small package of common recipe archetypes, useful for fast recipe offerings.
public class RecipeHelper extends ModRecipeProvider
{
    public RecipeHelper(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // Template Upgrade automation - provide template, material, item, the category and output.
    public static void templateUpgrade(RecipeExporter exporter, Item template, Item upgrade_mat, Item input, RecipeCategory category, Item result)
    {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template), Ingredient.ofItems(input), Ingredient.ofItems(upgrade_mat), category, result
                )
                .criterion(hasItem(upgrade_mat), conditionsFromItem(upgrade_mat))
                .offerTo(exporter, getItemPath(result) + "_smithing");
    }

    // Creates a recipe for 5 provided tools using a single material.
    public static void toolHelper(RecipeExporter exporter, Item tool_material, Item axe, Item sword, Item shovel, Item pickaxe, Item hoe)
    {
        // Axe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, axe)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .input('#', tool_material)
                .input('S', Items.STICK)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Sword
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, sword)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" S ")
                .input('#', tool_material)
                .input('S', Items.STICK)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Shovel
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, shovel)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', tool_material)
                .input('S', Items.STICK)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Pickaxe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, pickaxe)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', tool_material)
                .input('S', Items.STICK)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Hoe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, hoe)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .input('#', tool_material)
                .input('S', Items.STICK)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
    }
}
