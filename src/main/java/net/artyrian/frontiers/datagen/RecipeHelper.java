package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
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
import net.minecraft.util.Identifier;

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
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, getItemPath(result) + "_smithing"));
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

    // Creates a recipe for 4 provided armors using a single material.
    public static void armorHelper(RecipeExporter exporter, Item tool_material, Item helmet, Item chest, Item legs, Item boots)
    {
        // Necro Helmet
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, helmet)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', tool_material)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Necro Chestplate
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, chest)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', tool_material)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Necro Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, legs)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', tool_material)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
        // Necro Boots
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, boots)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', tool_material)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
    }

    // Intended for use with Farmer's Delight knives - creates a knife with the specific material,
    public static void knifeHelper(RecipeExporter exporter, Item tool_material, Item knife)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, knife)
                .pattern(" # ")
                .pattern(" S ")
                .pattern("   ")
                .input('#', tool_material)
                .input('S', Items.STICK)
                .criterion(hasItem(tool_material), conditionsFromItem(tool_material))
                .offerTo(exporter);
    }
}
