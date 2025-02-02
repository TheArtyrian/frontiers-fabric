package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDRecipeProvider;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.StonecuttingRecipe;
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, 2)
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
        // Rotcross/Necro Armor
        RecipeHelper.armorHelper(exporter, ModItem.NECRO_WEAVE,
                ModItem.NECRO_WEAVE_HELMET,
                ModItem.NECRO_WEAVE_CHESTPLATE,
                ModItem.NECRO_WEAVE_LEGGINGS,
                ModItem.NECRO_WEAVE_BOOTS
        );
        // Mourning Gold Block
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOURNING_GOLD_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X', ModItem.MOURNING_GOLD_INGOT)
                .criterion(hasItem(ModItem.MOURNING_GOLD_INGOT), conditionsFromItem(ModItem.MOURNING_GOLD_INGOT))
                .offerTo(exporter);
        // 9 Mourning Gold Ingots from Block
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.MOURNING_GOLD_INGOT, 9)
                .input(ModBlocks.MOURNING_GOLD_BLOCK)
                .criterion(hasItem(ModBlocks.MOURNING_GOLD_BLOCK), conditionsFromItem(ModBlocks.MOURNING_GOLD_BLOCK))
                .group("mourning_gold_ingot")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "mourning_gold_ingot_from_block"));
        // Tower Bricks
        //ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TOWER_BRICKS, 2)
        //        .pattern("## ")
        //        .pattern("## ")
        //        .pattern("   ")
        //        .input('#', Items.CALCITE)
        //        .criterion(hasItem(Items.CALCITE), conditionsFromItem(Items.CALCITE))
        //        .offerTo(exporter);
        // Mossy Tower Bricks
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_TOWER_BRICKS)
                .input(ModBlocks.TOWER_BRICKS)
                .input(Items.GLOW_LICHEN)
                .criterion(hasItem(ModBlocks.TOWER_BRICKS), conditionsFromItem(ModBlocks.TOWER_BRICKS))
                .offerTo(exporter);
        // All mourning gold tools.
        RecipeHelper.toolHelper(exporter, ModItem.MOURNING_GOLD_INGOT,
                ModItem.MOURNING_GOLD_AXE, ModItem.MOURNING_GOLD_SWORD, ModItem.MOURNING_GOLD_SHOVEL, ModItem.MOURNING_GOLD_PICKAXE, ModItem.MOURNING_GOLD_HOE
        );
        // Marshmallow
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItem.MARSHMALLOW, 2)
                .pattern(" X ")
                .pattern("OE ")
                .pattern("   ")
                .input('X', Items.SUGAR)
                .input('O', Items.EGG)
                .input('E', ModItem.ECTOPLASM)
                .criterion(hasItem(ModItem.ECTOPLASM), conditionsFromItem(ModItem.ECTOPLASM))
                .offerTo(exporter);
        // Tome of Fangs
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.TOME_OF_FANGS)
                .pattern("GXG")
                .pattern("XBX")
                .pattern("GXG")
                .input('X', ModItem.INVOKE_SHARD)
                .input('B', Items.BOOK)
                .input('G', ModItem.RAVAGER_TOOTH)
                .criterion(hasItem(ModItem.INVOKE_SHARD), conditionsFromItem(ModItem.INVOKE_SHARD))
                .offerTo(exporter);
        // All frostite tools.
        RecipeHelper.toolHelper(exporter, ModItem.FROSTITE_INGOT,
                ModItem.FROSTITE_AXE, ModItem.FROSTITE_SWORD, ModItem.FROSTITE_SHOVEL, ModItem.FROSTITE_PICKAXE, ModItem.FROSTITE_HOE
        );
        // Frostite Armor
        RecipeHelper.armorHelper(exporter, ModItem.FROSTITE_INGOT,
                ModItem.FROSTITE_HELMET,
                ModItem.FROSTITE_CHESTPLATE,
                ModItem.FROSTITE_LEGGINGS,
                ModItem.FROSTITE_BOOTS
        );
        // Nacre Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NACRE_BRICKS)
                .pattern("## ")
                .pattern("## ")
                .pattern("   ")
                .input('#', ModItem.NACRE_BRICK)
                .criterion(hasItem(ModItem.NACRE_BRICK), conditionsFromItem(ModItem.NACRE_BRICK))
                .offerTo(exporter);
        // Levi Roll
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItem.LEVI_ROLL, 2)
                .pattern("   ")
                .pattern("OwO")
                .pattern("   ")
                .input('w', ModItem.SHULKER_RESIDUE)
                .input('O', Items.WHEAT)
                .criterion(hasItem(ModItem.SHULKER_RESIDUE), conditionsFromItem(ModItem.SHULKER_RESIDUE))
                .offerTo(exporter);
        // Cobalt Armor
        RecipeHelper.armorHelper(exporter, ModItem.COBALT_INGOT,
                ModItem.COBALT_HELMET,
                ModItem.COBALT_CHESTPLATE,
                ModItem.COBALT_LEGGINGS,
                ModItem.COBALT_BOOTS
        );
        // Cursed Tablet
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.CURSED_TABLET)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItem.TABLET_FRAGMENT)
                .criterion(hasItem(ModItem.TABLET_FRAGMENT), conditionsFromItem(ModItem.TABLET_FRAGMENT))
                .offerTo(exporter);

        // End Crystal (moved out of generated --> resources)
        //ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.END_CRYSTAL)
        //        .input('T', Items.ENDER_PEARL)
        //        .input('E', ModItem.END_CRYSTAL_SHARD)
        //        .input('G', Blocks.GLASS)
        //        .pattern("GGG")
        //        .pattern("GEG")
        //        .pattern("GTG")
        //        .criterion("has_end_shard", conditionsFromItem(ModItem.END_CRYSTAL_SHARD))
        //        .offerTo(exporter, "minecraft:recipe/end_crystal");
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

        // Verdinite Ores
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.RAW_VERDINITE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .criterion(hasItem(ModItem.RAW_VERDINITE), conditionsFromItem(ModItem.RAW_VERDINITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_raw_verdinite"));

        // Raw Frostite
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.RAW_FROSTITE), RecipeCategory.MISC, ModItem.FROSTITE_INGOT, 2.0f, 200)
                .group("frostite_ingot")
                .criterion(hasItem(ModItem.RAW_FROSTITE), conditionsFromItem(ModItem.RAW_FROSTITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "frostite_ingot_from_smelting_raw_frostite"));

        // Roasted Marshmallow
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.2f, 100)
                .group("roasted_marshmallow")
                .criterion(hasItem(ModItem.MARSHMALLOW), conditionsFromItem(ModItem.MARSHMALLOW))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "roasted_marshmallow_smelting"));
        // Nacre Brick
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.SHULKER_RESIDUE), RecipeCategory.MISC, ModItem.NACRE_BRICK, 0.3f, 200)
                .group("nacre_brick")
                .criterion(hasItem(ModItem.SHULKER_RESIDUE), conditionsFromItem(ModItem.SHULKER_RESIDUE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "nacre_brick_smelting"));
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

        // Verdinite Ores
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.RAW_VERDINITE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .criterion(hasItem(ModItem.RAW_VERDINITE), conditionsFromItem(ModItem.RAW_VERDINITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_raw_verdinite"));

        // Raw Frostite
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.RAW_FROSTITE), RecipeCategory.MISC, ModItem.FROSTITE_INGOT, 2.0f, 100)
                .group("frostite_ingot")
                .criterion(hasItem(ModItem.RAW_FROSTITE), conditionsFromItem(ModItem.RAW_FROSTITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "frostite_ingot_from_blasting_raw_frostite"));
    }

    // Smoker recipes
    private void smoking(RecipeExporter exporter)
    {
        // Roasted Marshmallow
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.2f, 50)
                .group("roasted_marshmallow")
                .criterion(hasItem(ModItem.MARSHMALLOW), conditionsFromItem(ModItem.MARSHMALLOW))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "roasted_marshmallow_smoking"));
    }

    // Campfire recipes
    private void campfire(RecipeExporter exporter)
    {
        // Roasted Marshmallow
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.0f, 300)
                .group("roasted_marshmallow")
                .criterion(hasItem(ModItem.MARSHMALLOW), conditionsFromItem(ModItem.MARSHMALLOW))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "roasted_marshmallow_campfire"));
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
        //StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Items.CALCITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.TOWER_BRICKS)
        //        .criterion(hasItem(Items.CALCITE), conditionsFromItem(Items.CALCITE))
        //        .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "calcite_tower_bricks_from_stonecutting"));
    }

    // Stonecutting recipes
    private void fd_recipes(RecipeExporter exporter)
    {
        // Crafting
        // Cobalt Knife
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, FDItem.COBALT_KNIFE)
                .pattern(" X ")
                .pattern(" / ")
                .pattern("   ")
                .input('X', ModItem.COBALT_INGOT)
                .input('/', Items.STICK)
                .criterion(hasItem(ModItem.COBALT_INGOT), conditionsFromItem(ModItem.COBALT_INGOT))
                .offerTo(exporter);

        // Smithing
        // Obsidian Knife
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                FDItem.DIAMOND_KNIFE, RecipeCategory.COMBAT, FDItem.OBSIDIAN_KNIFE);
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

        // COMPAT: Farmer's Delight
        FDRecipeProvider.crafting(exporter);             // Crafting (all types)
        FDRecipeProvider.smelting(exporter);             // Smelting
        FDRecipeProvider.blasting(exporter);             // Blasting
        FDRecipeProvider.campfire(exporter);             // Campfire cooking
        FDRecipeProvider.smoking(exporter);              // Smoking in Smoker
        FDRecipeProvider.smithing(exporter);             // Smithing (trim & upgrade)
        FDRecipeProvider.stonecutting(exporter);         // Stonecutting
    }
}
