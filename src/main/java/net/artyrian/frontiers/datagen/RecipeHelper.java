package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

/** A small package of common recipe archetypes, useful for fast recipe offerings. */
public class RecipeHelper extends ModRecipeProvider
{
    public RecipeHelper(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    /** Template Upgrade automation - provide template, material, item, the category and output. */
    public static void templateUpgrade(RecipeExporter exporter, Item template, Item upgrade_mat, Item input, RecipeCategory category, Item result)
    {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItems(template), Ingredient.ofItems(input), Ingredient.ofItems(upgrade_mat), category, result
                )
                .criterion(hasItem(upgrade_mat), conditionsFromItem(upgrade_mat))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, getItemPath(result) + "_smithing"));
    }

    /** Creates a recipe for 5 provided tools using a single material. */
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

    /** Creates a recipe for 4 provided armors using a single material. */
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

    /** Intended for use with Farmer's Delight knives - creates a knife with the specific material. */
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

    /** Creates both a crafting table and stonecutter recipe for a stairs item.
     <p>(either vanilla has implementation for this that i didn't see or it just freaking sucks lol)*/
    public static void createStairsBothRecipes(RecipeExporter exporter, Block material, Block output, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X  ")
                .pattern("XX ")
                .pattern("XXX")
                .input('X', material)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(material), RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a wall item. */
    public static void createWallBothRecipes(RecipeExporter exporter, Block material, Block output, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 6)
                .pattern("XXX")
                .pattern("XXX")
                .input('X', material)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(material), RecipeCategory.MISC, output)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a slab item. */
    public static void createSlabBothRecipes(RecipeExporter exporter, Block material, Block output, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("XXX")
                .input('X', material)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(material), RecipeCategory.BUILDING_BLOCKS, output, 2)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a stone brick-like item. */
    public static void createStoneBrickRecipes(RecipeExporter exporter, Block material, Block output, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("XX")
                .pattern("XX")
                .input('X', material)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(material), RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a chiseled block. */
    public static void createChiselRecipes(RecipeExporter exporter, Block base, Block slab, Block output, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X")
                .pattern("X")
                .input('X', slab)
                .criterion(hasItem(slab), conditionsFromItem(slab))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(base), RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(base), conditionsFromItem(base))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates all possible recipes for a chiseled stone block - meant for bricks made from stones. */
    public static void createChiselRecipesMulti(RecipeExporter exporter, Block base, Block brick, Block slab, Block output, String name)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X")
                .pattern("X")
                .input('X', slab)
                .criterion(hasItem(slab), conditionsFromItem(slab))
                .offerTo(exporter);

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(base), RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(base), conditionsFromItem(base))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting_stone"));

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(brick), RecipeCategory.BUILDING_BLOCKS, output)
                .criterion(hasItem(brick), conditionsFromItem(brick))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, name + "_from_stonecutting_bricks"));
    }

    /** Create Nether Brick-like fence recipes. */
    public static void createBrickFence(RecipeExporter exporter, Block sides, Item middle, Block output)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 6)
                .pattern("XDX")
                .pattern("XDX")
                .input('X', sides)
                .input('D', middle)
                .criterion(hasItem(sides), conditionsFromItem(sides))
                .offerTo(exporter);
    }

    /** Fast method for making cracked brick recipes. */
    public static void createCrackedBrick(RecipeExporter exporter, Block input, Block output, String group)
    {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1f, 200)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter);
    }

    /** Fast method for making lumen recipes - all fall within the same group.
     * <p>"looking_for" is the item needed to discover the recipe - allows for lumens of Hardmode kind to be found later. */
    public static void createLumen(RecipeExporter exporter, Item material, Block output, Item looking_for)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .group("ore_lumen")
                .pattern(" I ")
                .pattern("RXR")
                .pattern(" L ")
                .input('I', material)
                .input('R', Items.REDSTONE)
                .input('X', Items.GLOWSTONE)
                .input('L', ModItem.LIGHTNING_IN_A_BOTTLE)
                .criterion(hasItem(looking_for), conditionsFromItem(looking_for))
                .offerTo(exporter);
    }
}
