package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// Generates recipes for the entire project.
public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // Crafting recipes
    private void crafting(RecipeExporter exporter)
    {
        // Obsidian Smithing Upgrade
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .input('#', Items.DIAMOND)
                .input('C', Items.OBSIDIAN)
                .input('S', ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE)
                .criterion(hasItem(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE))
                .offerTo(exporter);
        // Obsidian Casing
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.OBSIDIAN_CASING)
                .input(Items.DIAMOND)
                .input(Items.DIAMOND)
                .input(Items.DIAMOND)
                .input(Items.DIAMOND)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .input(Items.OBSIDIAN)
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(exporter);
        // Cobalt Block (protip: offerReversibleCompactingRecipes sucks)
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBALT_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X', ModItem.COBALT_INGOT)
                .criterion(hasItem(ModItem.COBALT_INGOT), conditionsFromItem(ModItem.COBALT_INGOT))
                .offerTo(exporter);
        // 9 Cobalt Ingots from Block
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.COBALT_INGOT, 9)
                .input(ModBlocks.COBALT_BLOCK)
                .criterion(hasItem(ModBlocks.COBALT_BLOCK), conditionsFromItem(ModBlocks.COBALT_BLOCK))
                .offerTo(exporter);
        // Cobalt Fishing Rod
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItem.COBALT_FISHING_ROD)
                .pattern("  /")
                .pattern(" /S")
                .pattern("/ S")
                .input('/', ModItem.COBALT_INGOT)
                .input('S', Items.STRING)
                .criterion(hasItem(ModItem.COBALT_INGOT), conditionsFromItem(ModItem.COBALT_INGOT))
                .offerTo(exporter);
        // All cobalt tools.
        RecipeHelper.toolHelper(exporter, ModItem.COBALT_INGOT,
                ModItem.COBALT_AXE, ModItem.COBALT_SWORD, ModItem.COBALT_SHOVEL, ModItem.COBALT_PICKAXE, ModItem.COBALT_HOE
        );
        // Ancient Rose Bush
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.ANCIENT_ROSE_BUSH)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .input('$', ModBlocks.ANCIENT_ROSE)
                .input('X', Items.ROSE_BUSH)
                .criterion(hasItem(ModBlocks.ANCIENT_ROSE), conditionsFromItem(ModBlocks.ANCIENT_ROSE))
                .offerTo(exporter);
        // Violet Rose Bush
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.VIOLET_ROSE_BUSH)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .input('$', ModBlocks.VIOLET_ROSE)
                .input('X', Items.ROSE_BUSH)
                .criterion(hasItem(ModBlocks.VIOLET_ROSE), conditionsFromItem(ModBlocks.VIOLET_ROSE))
                .offerTo(exporter);
        // Cyan Dye from Ancient Rose Bush
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CYAN_DYE, 2)
                .input(ModBlocks.ANCIENT_ROSE_BUSH)
                .criterion(hasItem(ModBlocks.ANCIENT_ROSE_BUSH), conditionsFromItem(ModBlocks.ANCIENT_ROSE_BUSH))
                .group("cyan_dye")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose_bush"));
        // Cyan Dye from Ancient Rose
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CYAN_DYE)
                .input(ModBlocks.ANCIENT_ROSE)
                .criterion(hasItem(ModBlocks.ANCIENT_ROSE), conditionsFromItem(ModBlocks.ANCIENT_ROSE))
                .group("cyan_dye")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose"));
        // Purple Dye from Violet Rose Bush
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PURPLE_DYE, 2)
                .input(ModBlocks.VIOLET_ROSE_BUSH)
                .criterion(hasItem(ModBlocks.VIOLET_ROSE_BUSH), conditionsFromItem(ModBlocks.VIOLET_ROSE_BUSH))
                .group("purple_dye")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "purple_dye_from_violet_rose_bush"));
        // Purple Dye from Violet Rose
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PURPLE_DYE)
                .input(ModBlocks.VIOLET_ROSE)
                .criterion(hasItem(ModBlocks.VIOLET_ROSE), conditionsFromItem(ModBlocks.VIOLET_ROSE))
                .group("purple_dye")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "purple_dye_from_violet_rose"));
        // Red Dye from Rose
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.RED_DYE)
                .input(ModBlocks.ROSE)
                .criterion(hasItem(ModBlocks.ROSE), conditionsFromItem(ModBlocks.ROSE))
                .group("red_dye")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "red_dye_from_rose"));
        // Onyx Bones from Withered Essence
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.ONYX_BONE, 4)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .input('$', Items.BONE)
                .input('X', ModItem.WITHERED_ESSENCE)
                .criterion(hasItem(ModItem.WITHERED_ESSENCE), conditionsFromItem(ModItem.WITHERED_ESSENCE))
                .offerTo(exporter);
        // Necro Weave
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.NECRO_WEAVE)
                .pattern("X$ ")
                .pattern("$X ")
                .pattern("   ")
                .input('$', ItemTags.WOOL)
                .input('X', ModItem.ONYX_BONE)
                .criterion(hasItem(ModItem.ONYX_BONE), conditionsFromItem(ModItem.ONYX_BONE))
                .offerTo(exporter);
        // Mourning Gold Ingot
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.MOURNING_GOLD_INGOT, 2)
                .pattern("X$X")
                .pattern("XOX")
                .pattern("X$X")
                .input('$', Items.GOLD_INGOT)
                .input('X', Items.QUARTZ)
                .input('O', ModItem.ECTOPLASM)
                .criterion(hasItem(ModItem.ECTOPLASM), conditionsFromItem(ModItem.ECTOPLASM))
                .group("mourning_gold_ingot")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "mourning_gold_ingot"));
        // Necro Helmet
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.NECRO_WEAVE_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', ModItem.NECRO_WEAVE)
                .criterion(hasItem(ModItem.NECRO_WEAVE), conditionsFromItem(ModItem.NECRO_WEAVE))
                .offerTo(exporter);
        // Necro Chestplate
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.NECRO_WEAVE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItem.NECRO_WEAVE)
                .criterion(hasItem(ModItem.NECRO_WEAVE), conditionsFromItem(ModItem.NECRO_WEAVE))
                .offerTo(exporter);
        // Necro Leggings
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.NECRO_WEAVE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItem.NECRO_WEAVE)
                .criterion(hasItem(ModItem.NECRO_WEAVE), conditionsFromItem(ModItem.NECRO_WEAVE))
                .offerTo(exporter);
        // Necro Boots
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.NECRO_WEAVE_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItem.NECRO_WEAVE)
                .criterion(hasItem(ModItem.NECRO_WEAVE), conditionsFromItem(ModItem.NECRO_WEAVE))
                .offerTo(exporter);
    }

    // Furn recipes
    // One by one ecause offerSmelting has been nothing but horrible to work with
    private void smelting(RecipeExporter exporter)
    {
        // Cobalt Ores
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.RAW_COBALT), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 200)
                .group("cobalt_ingot")
                .criterion(hasItem(ModItem.RAW_COBALT), conditionsFromItem(ModItem.RAW_COBALT))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_raw_cobalt"));
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 200)
                .group("cobalt_ingot")
                .criterion(hasItem(ModBlocks.COBALT_ORE), conditionsFromItem(ModBlocks.COBALT_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_cobalt_ore"));
    }

    // Blast Furn recipes
    // One by one ecause offerBlasting has been nothing but horrible to work with
    private void blasting(RecipeExporter exporter)
    {
        // Cobalt Ores
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.RAW_COBALT), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 100)
                .group("cobalt_ingot")
                .criterion(hasItem(ModItem.RAW_COBALT), conditionsFromItem(ModItem.RAW_COBALT))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_raw_cobalt"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 100)
                .group("cobalt_ingot")
                .criterion(hasItem(ModBlocks.COBALT_ORE), conditionsFromItem(ModBlocks.COBALT_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_cobalt_ore"));
    }

    // Smoker recipes
    private void smoking(RecipeExporter exporter)
    {

    }

    // Campfire recipes
    private void campfire(RecipeExporter exporter)
    {

    }

    // Smithing recipes
    private void smithing(RecipeExporter exporter)
    {
        // Obsidian items.
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.DIAMOND_SWORD, RecipeCategory.COMBAT, ModItem.OBSIDIAN_SWORD);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.DIAMOND_AXE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_AXE);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_PICKAXE);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.DIAMOND_HOE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_HOE);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, ModItem.OBSIDIAN_SHOVEL);
    }

    // Stonecutting recipes
    private void stonecutting(RecipeExporter exporter)
    {

    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        crafting(exporter);             // Crafting (all types)
        smelting(exporter);             // Smelting
        blasting(exporter);             // Blasting
        campfire(exporter);             // Campfire cooking
        smoking(exporter);              // Smoking in Smoker
        smithing(exporter);             // Smithing (trim & upgrade)
        stonecutting(exporter);         // Stonecutting
    }
}
