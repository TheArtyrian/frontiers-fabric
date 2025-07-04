package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.bountifulfares.BFRecipeProvider;
import net.artyrian.frontiers.compat.farmersdelight.FDRecipeProvider;
import net.artyrian.frontiers.datagen.recipe.FletchingRecipeBuilder;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.recipe.special_crafting.CobaltShieldDecorationRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.TippedArrowRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

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
        // Cobalt Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.COBALT_BLOCK.asItem(), ModItem.COBALT_INGOT);
        // Cobalt Fishing Rod
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItem.COBALT_FISHING_ROD)
                .pattern("  /")
                .pattern(" /S")
                .pattern("/ S")
                .input('/', ModItem.COBALT_INGOT)
                .input('S', Items.STRING)
                .criterion(hasItem(ModItem.COBALT_INGOT), conditionsFromItem(ModItem.COBALT_INGOT))
                .offerTo(exporter);
        // Cobalt Shield
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItem.COBALT_SHIELD)
                .pattern("CGC")
                .pattern("CCC")
                .pattern(" C ")
                .input('C', ModItem.COBALT_INGOT)
                .input('G', Items.GOLD_INGOT)
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
        // Mourning Gold Armor
        RecipeHelper.armorHelper(exporter, ModItem.MOURNING_GOLD_INGOT,
                ModItem.MOURNING_GOLD_HELMET,
                ModItem.MOURNING_GOLD_CHESTPLATE,
                ModItem.MOURNING_GOLD_LEGGINGS,
                ModItem.MOURNING_GOLD_BOOTS
        );
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
        // Light Blue Dye (Snow Dahlia)
        offerSingleOutputShapelessRecipe(exporter, Items.LIGHT_BLUE_DYE, ModBlocks.SNOW_DAHLIA, "light_blue_dye");
        // Purple Dye (Fungal Daffodil)
        offerSingleOutputShapelessRecipe(exporter, Items.PURPLE_DYE, ModBlocks.FUNGAL_DAFFODIL, "purple_dye");
        // Red Dye (Crimcone)
        offerSingleOutputShapelessRecipe(exporter, Items.RED_DYE, ModBlocks.CRIMCONE, "red_dye");
        // Onyx Meal
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.ONYX_MEAL, 3)
                .input(ModItem.ONYX_BONE)
                .criterion(hasItem(ModItem.ONYX_BONE), conditionsFromItem(ModItem.ONYX_BONE))
                .group("onyx_meal")
                .offerTo(exporter);
        // Black Dye from Oxyx Meal
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLACK_DYE)
                .input(ModItem.ONYX_MEAL)
                .criterion(hasItem(ModItem.ONYX_MEAL), conditionsFromItem(ModItem.ONYX_MEAL))
                .group("black_dye")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "black_dye_from_onyx_meal"));

        // Core Plate: Depths
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.DEPTHS_CORE_PLATE)
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .input('X', Items.DIAMOND)
                .input('O', Items.NETHERITE_INGOT)
                .criterion(hasItem(ModItem.UNFINISHED_CORE), conditionsFromItem(ModItem.UNFINISHED_CORE))
                .offerTo(exporter);
        // Core Plate: Frontal
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.FRONTAL_CORE_PLATE)
                .pattern(" X ")
                .pattern("EOE")
                .pattern(" X ")
                .input('X', ModItem.COBALT_INGOT)
                .input('E', ModItem.VERDINITE_INGOT)
                .input('O', ModItem.VIVULITE_INGOT)
                .criterion(hasItem(ModItem.UNFINISHED_CORE), conditionsFromItem(ModItem.UNFINISHED_CORE))
                .offerTo(exporter);

        // Reactive Core
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.REACTIVE_CORE)
                .pattern(" A ")
                .pattern("BOC")
                .pattern(" D ")
                .input('A', ModItem.DEPTHS_CORE_PLATE)
                .input('B', ModItem.FRONTAL_CORE_PLATE)
                .input('C', ModItem.DEPTHS_CORE_PLATE)
                .input('D', ModItem.FRONTAL_CORE_PLATE)
                .input('O', ModItem.UNFINISHED_CORE)
                .criterion(hasItem(ModItem.DEPTHS_CORE_PLATE), conditionsFromItem(ModItem.DEPTHS_CORE_PLATE))
                .criterion(hasItem(ModItem.FRONTAL_CORE_PLATE), conditionsFromItem(ModItem.FRONTAL_CORE_PLATE))
                .offerTo(exporter);

        // Strange Core
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModBlocks.STRANGE_CORE)
                .pattern("DXD")
                .pattern("DOD")
                .pattern("DXD")
                .input('D', Items.IRON_INGOT)
                .input('X', Items.DIAMOND)
                .input('O', ModItem.REACTIVE_CORE)
                .criterion(hasItem(ModItem.REACTIVE_CORE), conditionsFromItem(ModItem.REACTIVE_CORE))
                .offerTo(exporter);
        // Verdinite Bow
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.VERDINITE_BOW)
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .input('X', ModItem.VERDINITE_INGOT)
                .input('S', Items.STRING)
                .criterion(hasItem(ModItem.VERDINITE_INGOT), conditionsFromItem(ModItem.VERDINITE_INGOT))
                .offerTo(exporter);

        // Onyx Bone Block
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ONYX_BONE_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X', ModItem.ONYX_MEAL)
                .criterion(hasItem(ModItem.ONYX_MEAL), conditionsFromItem(ModItem.ONYX_MEAL))
                .offerTo(exporter);
        // 9 Onyx Meal from Onyx Bone Block
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.ONYX_MEAL, 9)
                .input(ModBlocks.ONYX_BONE_BLOCK)
                .criterion(hasItem(ModBlocks.ONYX_BONE_BLOCK), conditionsFromItem(ModBlocks.ONYX_BONE_BLOCK))
                .group("onyx_meal")
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "onyx_meal_from_onyx_bone_block"));
        // Tower Key
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItem.TOWER_KEY)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X', ModItem.TOWER_KEY_FRAGMENT)
                .criterion(hasItem(ModItem.TOWER_KEY_FRAGMENT), conditionsFromItem(ModItem.TOWER_KEY_FRAGMENT))
                .offerTo(exporter);
        // Warped Wart Block
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .input('X', ModItem.WARPED_WART)
                .criterion(hasItem(ModItem.WARPED_WART), conditionsFromItem(ModItem.WARPED_WART))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "warped_wart_block_from_warped_wart"));
        // Blue Nether Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICKS)
                .pattern("NW")
                .pattern("WN")
                .input('W', ModItem.WARPED_WART)
                .input('N', Items.NETHER_BRICK)
                .criterion(hasItem(ModItem.WARPED_WART), conditionsFromItem(ModItem.WARPED_WART))
                .offerTo(exporter);
        // Purple Nether Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_NETHER_BRICKS)
                .pattern("NW")
                .pattern("XN")
                .input('W', Items.NETHER_WART)
                .input('X', ModItem.WARPED_WART)
                .input('N', Items.NETHER_BRICK)
                .criterion(hasItem(ModItem.WARPED_WART), conditionsFromItem(ModItem.WARPED_WART))
                .offerTo(exporter);
        // New Nether Brick Fences & Gates
        RecipeHelper.createBrickFence(exporter, ModBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.BLUE_NETHER_BRICK_FENCE);
        RecipeHelper.createBrickFence(exporter, ModBlocks.PURPLE_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        RecipeHelper.createBrickFence(exporter, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.RED_NETHER_BRICK_FENCE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.RED_NETHER_BRICKS, ModBlocks.RED_NETHER_BRICK_FENCE_GATE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.NETHER_BRICKS, ModBlocks.NETHER_BRICK_FENCE_GATE);
        // Quicksand
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUICKSAND, 2)
                .pattern("EX")
                .pattern("XE")
                .input('E', Items.MUD)
                .input('X', Items.SAND)
                .criterion(hasItem(Items.MUD), conditionsFromItem(Items.MUD))
                .offerTo(exporter);
        // Red Quicksand
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_QUICKSAND, 2)
                .pattern("EX")
                .pattern("XE")
                .input('E', Items.MUD)
                .input('X', Items.RED_SAND)
                .criterion(hasItem(Items.RED_SAND), conditionsFromItem(Items.RED_SAND))
                .offerTo(exporter);
        // Totem of Undying
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.TOTEM_OF_UNDYING)
                .pattern("OXO")
                .pattern("XXX")
                .pattern("GXG")
                .input('X', ModItem.INVOKE_SHARD)
                .input('G', Items.GOLD_INGOT)
                .input('O', Items.EMERALD)
                .criterion(hasItem(ModItem.INVOKE_SHARD), conditionsFromItem(ModItem.INVOKE_SHARD))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "totem_of_undying"));
        // Totem of Undying
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.TOTEM_OF_AVARICE)
                .pattern("X")
                .pattern("E")
                .pattern("#")
                .input('X', Items.DIAMOND)
                .input('E', ModItem.INVOKE_SHARD)
                .input('#', Items.IRON_INGOT)
                .criterion(hasItem(ModItem.INVOKE_SHARD), conditionsFromItem(ModItem.INVOKE_SHARD))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "totem_of_avarice"));
        // Void Pearl
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItem.VOID_PEARL)
                .input(Items.BLAZE_POWDER)
                .input(Items.ENDER_PEARL)
                .criterion(hasItem(Items.ENDER_PEARL), conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter);
        // Lumens
        RecipeHelper.createLumen(exporter, Items.AMETHYST_SHARD, ModBlocks.AMETHYST_LUMEN, ModItem.LIGHTNING_IN_A_BOTTLE);
        RecipeHelper.createLumen(exporter, Items.DIAMOND, ModBlocks.DIAMOND_LUMEN, ModItem.LIGHTNING_IN_A_BOTTLE);
        RecipeHelper.createLumen(exporter, Items.EMERALD, ModBlocks.EMERALD_LUMEN, ModItem.LIGHTNING_IN_A_BOTTLE);
        RecipeHelper.createLumen(exporter, Items.QUARTZ, ModBlocks.QUARTZ_LUMEN, ModItem.LIGHTNING_IN_A_BOTTLE);
        RecipeHelper.createLumen(exporter, Items.REDSTONE, ModBlocks.REDSTONE_LUMEN, ModItem.LIGHTNING_IN_A_BOTTLE);
        RecipeHelper.createLumen(exporter, Items.ECHO_SHARD, ModBlocks.ECHO_LUMEN, Items.ECHO_SHARD);
        RecipeHelper.createLumen(exporter, ModItem.COBALT_INGOT, ModBlocks.COBALT_LUMEN, ModItem.COBALT_INGOT);
        RecipeHelper.createLumen(exporter, ModItem.FROSTITE_INGOT, ModBlocks.FROSTITE_LUMEN, ModItem.FROSTITE_INGOT);
        RecipeHelper.createLumen(exporter, ModItem.VERDINITE_INGOT, ModBlocks.VERDINITE_LUMEN, ModItem.VERDINITE_INGOT);
        RecipeHelper.createLumen(exporter, ModItem.VIVULITE_INGOT, ModBlocks.VIVULITE_LUMEN, ModItem.VIVULITE_INGOT);
        RecipeHelper.createLumen(exporter, ModItem.BRIMTAN_NUGGET, ModBlocks.BRIMTAN_LUMEN, ModItem.BRIMTAN_NUGGET);
        // All verdinite tools.
        RecipeHelper.toolHelper(exporter, ModItem.VERDINITE_INGOT,
                ModItem.VERDINITE_AXE, ModItem.VERDINITE_SWORD, ModItem.VERDINITE_SHOVEL, ModItem.VERDINITE_PICKAXE, ModItem.VERDINITE_HOE
        );
        // Verdinite Armor
        RecipeHelper.armorHelper(exporter, ModItem.VERDINITE_INGOT,
                ModItem.VERDINITE_HELMET,
                ModItem.VERDINITE_CHESTPLATE,
                ModItem.VERDINITE_LEGGINGS,
                ModItem.VERDINITE_BOOTS
        );
        // All vivulite tools.
        RecipeHelper.toolHelper(exporter, ModItem.VIVULITE_INGOT,
                ModItem.VIVULITE_AXE, ModItem.VIVULITE_SWORD, ModItem.VIVULITE_SHOVEL, ModItem.VIVULITE_PICKAXE, ModItem.VIVULITE_HOE
        );
        // Vivulite Armor
        RecipeHelper.armorHelper(exporter, ModItem.VIVULITE_INGOT,
                ModItem.VIVULITE_HELMET,
                ModItem.VIVULITE_CHESTPLATE,
                ModItem.VIVULITE_LEGGINGS,
                ModItem.VIVULITE_BOOTS
        );
        // Vivulite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.VIVULITE_BLOCK.asItem(), ModItem.VIVULITE_INGOT);
        // Verdinite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.VERDINITE_BLOCK.asItem(), ModItem.VERDINITE_INGOT);

        // Sea Glass
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_GLASS, 2)
                .pattern(" P ")
                .pattern("PGP")
                .pattern(" P ")
                .input('P', Items.PRISMARINE_CRYSTALS)
                .input('G', Items.GLASS)
                .criterion(hasItem(Items.PRISMARINE_CRYSTALS), conditionsFromItem(Items.PRISMARINE_CRYSTALS))
                .offerTo(exporter);
        // Pale Prismarine
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_PRISMARINE, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .input('P', Items.PRISMARINE)
                .input('X', ModItem.PALE_PRISMARINE_SHARD)
                .criterion(hasItem(ModItem.PALE_PRISMARINE_SHARD), conditionsFromItem(ModItem.PALE_PRISMARINE_SHARD))
                .offerTo(exporter);
        // Pale Prismarine Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_PRISMARINE_BRICKS, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .input('P', Items.PRISMARINE_BRICKS)
                .input('X', ModItem.PALE_PRISMARINE_SHARD)
                .criterion(hasItem(ModItem.PALE_PRISMARINE_SHARD), conditionsFromItem(ModItem.PALE_PRISMARINE_SHARD))
                .offerTo(exporter);
        // Deep Pale Prismarine
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEP_PALE_PRISMARINE, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .input('P', Items.DARK_PRISMARINE)
                .input('X', ModItem.PALE_PRISMARINE_SHARD)
                .criterion(hasItem(ModItem.PALE_PRISMARINE_SHARD), conditionsFromItem(ModItem.PALE_PRISMARINE_SHARD))
                .offerTo(exporter);
        // Pale Sea Glass
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_SEA_GLASS, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .input('P', ModBlocks.SEA_GLASS)
                .input('X', ModItem.PALE_PRISMARINE_SHARD)
                .criterion(hasItem(ModItem.PALE_PRISMARINE_SHARD), conditionsFromItem(ModItem.PALE_PRISMARINE_SHARD))
                .offerTo(exporter);
        // Sea Glass Panes
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_GLASS_PANE, 16)
                .pattern("##")
                .input('#', ModBlocks.SEA_GLASS)
                .criterion(hasItem(ModBlocks.SEA_GLASS), conditionsFromItem(ModBlocks.SEA_GLASS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_SEA_GLASS_PANE, 16)
                .pattern("##")
                .input('#', ModBlocks.PALE_SEA_GLASS)
                .criterion(hasItem(ModBlocks.PALE_SEA_GLASS), conditionsFromItem(ModBlocks.PALE_SEA_GLASS))
                .offerTo(exporter);
        // Frostite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.FROSTITE_BLOCK.asItem(), ModItem.FROSTITE_INGOT);
        // Turtle Scute Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TURTLE_SCUTE_BRICKS, 8)
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XXX")
                .input('E', Items.TURTLE_SCUTE)
                .input('X', Items.BRICK)
                .criterion(hasItem(Items.TURTLE_SCUTE), conditionsFromItem(Items.TURTLE_SCUTE))
                .offerTo(exporter);
        // Hielostone (from Blue ice)
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.HIELOSTONE)
                .input(Items.BLUE_ICE)
                .input(Items.STONE)
                .criterion(hasItem(Items.BLUE_ICE), conditionsFromItem(Items.BLUE_ICE))
                .offerTo(exporter);
        // Brimtan Ingot <-> Nugget convertible
        RecipeHelper.createReversible(exporter, ModItem.BRIMTAN_INGOT, ModItem.BRIMTAN_NUGGET);
        // All brimtan shells.
        RecipeHelper.toolHelper(exporter, ModItem.BRIMTAN_INGOT,
                ModItem.BRIMTAN_SHELL_AXE,
                ModItem.BRIMTAN_SHELL_SWORD,
                ModItem.BRIMTAN_SHELL_SHOVEL,
                ModItem.BRIMTAN_SHELL_PICKAXE,
                ModItem.BRIMTAN_SHELL_HOE
        );
        // All balls
        RecipeHelper.doBallRecipes(exporter);
        // Vivulite Anvil
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModBlocks.VIVULITE_ANVIL)
                .pattern("XXX")
                .pattern(" E ")
                .pattern("XXX")
                .input('E', ModItem.VIVULITE_INGOT)
                .input('X', ModBlocks.VIVULITE_BLOCK)
                .criterion(hasItem(ModBlocks.VIVULITE_BLOCK), conditionsFromItem(ModBlocks.VIVULITE_BLOCK))
                .offerTo(exporter);
        // Beef Wellington
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModBlocks.BEEF_WELLINGTON)
                .pattern("$$$")
                .pattern("$X$")
                .pattern("$I$")
                .input('$', Items.WHEAT)
                .input('X', ModItem.TRUFFLE)
                .input('I', Items.COOKED_BEEF)
                .criterion(hasItem(ModItem.TRUFFLE), conditionsFromItem(ModItem.TRUFFLE))
                .offerTo(exporter);
        // Truffle Potato Puff
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItem.TRUFFLE_POTATO_PUFF, 6)
                .pattern(" X ")
                .pattern("$I$")
                .input('$', Items.WHEAT)
                .input('X', ModItem.TRUFFLE)
                .input('I', Items.BAKED_POTATO)
                .criterion(hasItem(ModItem.TRUFFLE), conditionsFromItem(ModItem.TRUFFLE))
                .offerTo(exporter);
        // Truffle Oil
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItem.TRUFFLE_OIL, 4)
                .input(ModItem.TRUFFLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .criterion(hasItem(ModItem.TRUFFLE), conditionsFromItem(ModItem.TRUFFLE))
                .offerTo(exporter);
        // Personal Chest
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PERSONAL_CHEST)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("#E#")
                .input('#', Items.COBBLED_DEEPSLATE)
                .input('C', ModItem.LIGHTNING_IN_A_BOTTLE)
                .input('S', Items.GOLD_INGOT)
                .input('E', Items.ECHO_SHARD)
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .offerTo(exporter);
        // Chest Key
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.CHEST_KEY)
                .pattern(" ##")
                .pattern(" X#")
                .pattern("#  ")
                .input('#', Items.GOLD_INGOT)
                .input('X', Items.LAPIS_LAZULI)
                .criterion(hasItem(ModBlocks.PERSONAL_CHEST), conditionsFromItem(ModBlocks.PERSONAL_CHEST))
                .offerTo(exporter);
        // Curse Altar
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.CURSE_ALTAR)
                .pattern(" C ")
                .pattern("V#V")
                .pattern("L#L")
                .input('#', Items.SMOOTH_SANDSTONE)
                .input('C', ModItem.CURSED_TABLET)
                .input('V', ModItem.VOID_PEARL)
                .input('L', Items.LAPIS_LAZULI)
                .criterion(hasItem(ModItem.CURSED_TABLET), conditionsFromItem(ModItem.CURSED_TABLET))
                .offerTo(exporter);
        // Brimtan Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.BRIMTAN_BLOCK.asItem(), ModItem.BRIMTAN_INGOT);

        // Trim templates
        RecipeHelper.doTemplateDupe(exporter, ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE, Blocks.GRANITE.asItem());
        RecipeHelper.doTemplateDupe(exporter, ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE, Blocks.SLIME_BLOCK.asItem());
        RecipeHelper.doTemplateDupe(exporter, ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE, ModBlocks.ONYX_BONE_BLOCK.asItem());

        // Brimtan Armor Shells
        RecipeHelper.armorHelper(exporter, ModItem.BRIMTAN_INGOT,
                ModItem.BRIMTAN_SHELL_HELMET,
                ModItem.BRIMTAN_SHELL_CHESTPLATE,
                ModItem.BRIMTAN_SHELL_LEGGINGS,
                ModItem.BRIMTAN_SHELL_BOOTS
        );
        // All Brimtan templates
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE);
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE);
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE);
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE);
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE);

        // Brimmed Cragulstane Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, 5)
                .pattern("B#B")
                .pattern("#B#")
                .pattern("B#B")
                .input('B', ModBlocks.CRAGULSTANE_BRICKS)
                .input('#', ModItem.BRIMTAN_NUGGET)
                .criterion(hasItem(ModItem.BRIMTAN_NUGGET), conditionsFromItem(ModItem.BRIMTAN_NUGGET))
                .offerTo(exporter);
        // Orange Cragulstane Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, 8)
                .pattern("BBB")
                .pattern("BXB")
                .pattern("BBB")
                .input('B', ModBlocks.CRAGULSTANE_BRICKS)
                .input('X', Items.LAVA_BUCKET)
                .criterion(hasItem(ModBlocks.CRAGULSTANE_BRICKS), conditionsFromItem(ModBlocks.CRAGULSTANE_BRICKS))
                .offerTo(exporter);
        // Tyrian Cragulstane Bricks
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, 5)
                .pattern("B#B")
                .pattern("#BO")
                .pattern("BOB")
                .input('B', ModBlocks.CRAGULSTANE_BRICKS)
                .input('#', Items.NETHER_WART)
                .input('O', ModItem.WARPED_WART)
                .criterion(hasItem(ModBlocks.CRAGULSTANE_BRICKS), conditionsFromItem(ModBlocks.CRAGULSTANE_BRICKS))
                .offerTo(exporter);
        // Pale Trident
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.PALE_TRIDENT)
                .pattern("XXX")
                .pattern("#T#")
                .pattern(" # ")
                .input('T', Items.TRIDENT)
                .input('#', ModItem.PALE_PRISMARINE_SHARD)
                .input('X', ModItem.ELDER_GUARDIAN_SPINE)
                .criterion(hasItem(ModItem.ELDER_GUARDIAN_SPINE), conditionsFromItem(ModItem.ELDER_GUARDIAN_SPINE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "pale_trident"));

        // Glistering Melon <-> Slices convertible
        RecipeHelper.createReversible(exporter, ModBlocks.GLISTERING_MELON.asItem(), Items.GLISTERING_MELON_SLICE);
        // June o Lantern
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNE_O_LANTERN)
                .input('A', ModBlocks.CARVED_MELON)
                .input('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .criterion("has_base", conditionsFromItem(ModBlocks.CARVED_MELON))
                .offerTo(exporter);
        // Glistering o Lantern
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLISTERING_JUNE_O_LANTERN)
                .input('A', ModBlocks.CARVED_GLISTERING_MELON)
                .input('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .criterion("has_base", conditionsFromItem(ModBlocks.CARVED_GLISTERING_MELON))
                .offerTo(exporter);
        // White Pumpkin
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModBlocks.WHITE_PUMPKIN)
                .input('X', ModItem.ECTOPLASM)
                .input('P', Blocks.CARVED_PUMPKIN)
                .pattern(" X ")
                .pattern("XPX")
                .pattern(" X ")
                .criterion(hasItem(ModItem.ECTOPLASM), conditionsFromItem(ModItem.ECTOPLASM))
                .offerTo(exporter);
        // White Jack o Lantern
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_JACK_O_LANTERN)
                .input('A', ModBlocks.WHITE_PUMPKIN)
                .input('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .criterion("has_base", conditionsFromItem(ModBlocks.WHITE_PUMPKIN))
                .offerTo(exporter);

        // (UNUSED) Purified End Crystal
        //ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModItem.PURIFIED_END_CRYSTAL)
        //        .pattern(" A ")
        //        .pattern("A#A")
        //        .pattern(" A ")
        //        .input('#', Items.END_CRYSTAL)
        //        .input('A', Items.DIAMOND)
        //        .criterion(hasItem(Items.END_CRYSTAL), conditionsFromItem(Items.END_CRYSTAL))
        //        .offerTo(exporter);

        // Spirit Candle
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.SPIRIT_CANDLE)
                .pattern(" $ ")
                .pattern(" # ")
                .pattern("%#%")
                .input('$', Items.STRING)
                .input('#', Items.HONEYCOMB)
                .input('%', ModItem.INCENSE)
                .criterion(hasItem(ModItem.INCENSE), conditionsFromItem(ModItem.INCENSE))
                .offerTo(exporter);
        // Raw ore convertibles
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_COBALT_BLOCK.asItem(), ModItem.RAW_COBALT);
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_VERDINITE_BLOCK.asItem(), ModItem.RAW_VERDINITE);
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_VIVULITE_BLOCK.asItem(), ModItem.RAW_VIVULITE);
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_FROSTITE_BLOCK.asItem(), ModItem.RAW_FROSTITE);

        // Verdinite Horse Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.VERDINITE_HORSE_ARMOR)
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .input('H', ModItem.COBALT_HORSE_ARMOR)
                .input('#', ModItem.VERDINITE_INGOT)
                .criterion(hasItem(ModItem.VERDINITE_INGOT), conditionsFromItem(ModItem.VERDINITE_INGOT))
                .offerTo(exporter);
        // Vivulite Horse Armor
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.VIVULITE_HORSE_ARMOR)
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .input('H', ModItem.VERDINITE_HORSE_ARMOR)
                .input('#', ModItem.VIVULITE_INGOT)
                .criterion(hasItem(ModItem.VIVULITE_INGOT), conditionsFromItem(ModItem.VIVULITE_INGOT))
                .offerTo(exporter);

        // Snow Melt
        ShapelessRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.SNOW_MELT, 2)
                .input(ModItem.FROST_BONE)
                .input(Items.LEATHER)
                .criterion(hasItem(ModItem.FROST_BONE), conditionsFromItem(ModItem.FROST_BONE))
                .offerTo(exporter);
        // Message in a Bottle
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItem.MESSAGE_IN_A_BOTTLE)
                .input(Items.GLASS_BOTTLE)
                .input(Items.PAPER)
                .input(Items.INK_SAC)
                .criterion(hasItem(Items.GLASS_BOTTLE), conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(exporter);

        // Monster Bakery
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MONSTER_BAKERY)
                .pattern("#*#")
                .pattern("*&*")
                .pattern("#*#")
                .input('&', ModItem.INCENSE)
                .input('#', ModItem.SPAWNER_CHUNK)
                .input('*', Items.DIAMOND)
                .criterion(hasItem(ModItem.SPAWNER_CHUNK), conditionsFromItem(ModItem.SPAWNER_CHUNK))
                .offerTo(exporter);

        // Phantom-Stitch Bed
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModItem.PHANTOM_STITCH_BED)
                .pattern("###")
                .pattern("XXX")
                .input('#', Items.PHANTOM_MEMBRANE)
                .input('X', ItemTags.PLANKS)
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(exporter);

        // TEMP APPLE OF ENLIGHTENMENT RECIPE!
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItem.APPLE_OF_ENLIGHTENMENT)
                .pattern("AAA")
                .pattern("A#A")
                .pattern("AAA")
                .input('#', Items.ENCHANTED_GOLDEN_APPLE)
                .input('A', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.ENCHANTED_GOLDEN_APPLE), conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                .offerTo(exporter);

        // COMPLEX (BUILT-IN) CRAFTING RECIPES
        ComplexRecipeJsonBuilder
                .create(CobaltShieldDecorationRecipe::new)
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_shield_decoration"));

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
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.DEEPSLATE_COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 200)
                .group("cobalt_ingot")
                .criterion(hasItem(ModBlocks.DEEPSLATE_COBALT_ORE), conditionsFromItem(ModBlocks.DEEPSLATE_COBALT_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_deepslate_cobalt_ore"));

        // Verdinite Ores
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.RAW_VERDINITE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .criterion(hasItem(ModItem.RAW_VERDINITE), conditionsFromItem(ModItem.RAW_VERDINITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_raw_verdinite"));
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .criterion(hasItem(ModBlocks.VERDINITE_ORE), conditionsFromItem(ModBlocks.VERDINITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_verdinite_ore"));
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.DEEPSLATE_VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .criterion(hasItem(ModBlocks.DEEPSLATE_VERDINITE_ORE), conditionsFromItem(ModBlocks.DEEPSLATE_VERDINITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_deepslate_verdinite_ore"));

        // Raw Frostite
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.RAW_FROSTITE), RecipeCategory.MISC, ModItem.FROSTITE_INGOT, 2.0f, 200)
                .group("frostite_ingot")
                .criterion(hasItem(ModItem.RAW_FROSTITE), conditionsFromItem(ModItem.RAW_FROSTITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "frostite_ingot_from_smelting_raw_frostite"));

        // Vivulite ores
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.RAW_VIVULITE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 200)
                .group("vivulite_ingot")
                .criterion(hasItem(ModItem.RAW_VIVULITE), conditionsFromItem(ModItem.RAW_VIVULITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_raw_vivulite"));
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 200)
                .group("vivulite_ingot")
                .criterion(hasItem(ModBlocks.VIVULITE_ORE), conditionsFromItem(ModBlocks.VIVULITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_vivulite_ore"));
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.DEEPSLATE_VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 200)
                .group("vivulite_ingot")
                .criterion(hasItem(ModBlocks.DEEPSLATE_VIVULITE_ORE), conditionsFromItem(ModBlocks.DEEPSLATE_VIVULITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_deepslate_vivulite_ore"));

        // Brimtan Ores
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.BRIMTAN_CLUSTER), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 400)
                .group("brimtan_nugget")
                .criterion(hasItem(ModItem.BRIMTAN_CLUSTER), conditionsFromItem(ModItem.BRIMTAN_CLUSTER))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_cluster"));
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.BRIMTAN_ORE), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 400)
                .group("brimtan_nugget")
                .criterion(hasItem(ModBlocks.BRIMTAN_ORE), conditionsFromItem(ModBlocks.BRIMTAN_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_ore"));

        // Roasted Marshmallow
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.2f, 100)
                .group("roasted_marshmallow")
                .criterion(hasItem(ModItem.MARSHMALLOW), conditionsFromItem(ModItem.MARSHMALLOW))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "roasted_marshmallow_smelting"));

        // Cooked Guardian Slice
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE, 0.35f, 200)
                .criterion(hasItem(ModItem.GUARDIAN_SLICE), conditionsFromItem(ModItem.GUARDIAN_SLICE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cooked_guardian_slice_smelting"));
        // Cooked Elder Guardian Slice
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.ELDER_GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE, 0.35f, 200)
                .criterion(hasItem(ModItem.ELDER_GUARDIAN_SLICE), conditionsFromItem(ModItem.ELDER_GUARDIAN_SLICE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smelting"));

        // Nacre Brick
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.SHULKER_RESIDUE), RecipeCategory.MISC, ModItem.NACRE_BRICK, 0.3f, 200)
                .group("nacre_brick")
                .criterion(hasItem(ModItem.SHULKER_RESIDUE), conditionsFromItem(ModItem.SHULKER_RESIDUE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "nacre_brick_smelting"));

        // Hielostone
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.COBBLEFROST), RecipeCategory.MISC, ModBlocks.HIELOSTONE, 0.1f, 200)
                .group("hielostone")
                .criterion(hasItem(ModBlocks.COBBLEFROST), conditionsFromItem(ModBlocks.COBBLEFROST))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "hielostone_smelting"));
        // Stone from smelting Hielostone
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.HIELOSTONE), RecipeCategory.MISC, Blocks.STONE, 0.1f, 200)
                .group("stone")
                .criterion(hasItem(ModBlocks.HIELOSTONE), conditionsFromItem(ModBlocks.HIELOSTONE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "stone_from_smelting_hielostone"));

        // Bone from Frost Bone
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItem.FROST_BONE), RecipeCategory.MISC, Items.BONE, 0.3f, 200)
                .group("frost_bone")
                .criterion(hasItem(ModItem.FROST_BONE), conditionsFromItem(ModItem.FROST_BONE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "frost_bone_smelting"));

        // Cracked bricks
        RecipeHelper.createCrackedBrick(exporter, Blocks.RED_NETHER_BRICKS, ModBlocks.CRACKED_RED_NETHER_BRICKS, "cracked_red_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.CRACKED_BLUE_NETHER_BRICKS, "cracked_blue_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.CRACKED_PURPLE_NETHER_BRICKS, "cracked_purple_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.CRAGULSTANE_BRICKS, ModBlocks.CRACKED_CRAGULSTANE_BRICKS, "cracked_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS, "cracked_brimmed_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS, "cracked_orange_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS, "cracked_tyrian_cragulstane_bricks");
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
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.DEEPSLATE_COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 100)
                .group("cobalt_ingot")
                .criterion(hasItem(ModBlocks.DEEPSLATE_COBALT_ORE), conditionsFromItem(ModBlocks.DEEPSLATE_COBALT_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_deepslate_cobalt_ore"));

        // Verdinite Ores
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.RAW_VERDINITE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .criterion(hasItem(ModItem.RAW_VERDINITE), conditionsFromItem(ModItem.RAW_VERDINITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_raw_verdinite"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .criterion(hasItem(ModBlocks.VERDINITE_ORE), conditionsFromItem(ModBlocks.VERDINITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_verdinite_ore"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.DEEPSLATE_VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .criterion(hasItem(ModBlocks.DEEPSLATE_VERDINITE_ORE), conditionsFromItem(ModBlocks.DEEPSLATE_VERDINITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_deepslate_verdinite_ore"));

        // Raw Frostite
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.RAW_FROSTITE), RecipeCategory.MISC, ModItem.FROSTITE_INGOT, 2.0f, 100)
                .group("frostite_ingot")
                .criterion(hasItem(ModItem.RAW_FROSTITE), conditionsFromItem(ModItem.RAW_FROSTITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "frostite_ingot_from_blasting_raw_frostite"));

        // Vivulite Ores
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.RAW_VIVULITE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 100)
                .group("vivulite_ingot")
                .criterion(hasItem(ModItem.RAW_VIVULITE), conditionsFromItem(ModItem.RAW_VIVULITE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_raw_vivulite"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 100)
                .group("vivulite_ingot")
                .criterion(hasItem(ModBlocks.VIVULITE_ORE), conditionsFromItem(ModBlocks.VIVULITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_vivulite_ore"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.DEEPSLATE_VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 100)
                .group("vivulite_ingot")
                .criterion(hasItem(ModBlocks.DEEPSLATE_VIVULITE_ORE), conditionsFromItem(ModBlocks.DEEPSLATE_VIVULITE_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_deepslate_vivulite_ore"));

        // Brimtan Ores
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItem.BRIMTAN_CLUSTER), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 200)
                .group("brimtan_nugget")
                .criterion(hasItem(ModItem.BRIMTAN_CLUSTER), conditionsFromItem(ModItem.BRIMTAN_CLUSTER))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_cluster"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModBlocks.BRIMTAN_ORE), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 200)
                .group("brimtan_nugget")
                .criterion(hasItem(ModBlocks.BRIMTAN_ORE), conditionsFromItem(ModBlocks.BRIMTAN_ORE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_ore"));
    }

    // Smoker recipes
    private void smoking(RecipeExporter exporter)
    {
        // Roasted Marshmallow
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.2f, 50)
                .group("roasted_marshmallow")
                .criterion(hasItem(ModItem.MARSHMALLOW), conditionsFromItem(ModItem.MARSHMALLOW))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "roasted_marshmallow_smoking"));

        // Cooked Guardian Slice
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(ModItem.GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE, 0.35f, 100)
                .criterion(hasItem(ModItem.GUARDIAN_SLICE), conditionsFromItem(ModItem.GUARDIAN_SLICE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cooked_guardian_slice_smoking"));
        // Cooked Elder Guardian Slice
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(ModItem.ELDER_GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE, 0.35f, 100)
                .criterion(hasItem(ModItem.ELDER_GUARDIAN_SLICE), conditionsFromItem(ModItem.ELDER_GUARDIAN_SLICE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smoking"));
    }

    // Campfire recipes
    private void campfire(RecipeExporter exporter)
    {
        // Roasted Marshmallow
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.0f, 300)
                .group("roasted_marshmallow")
                .criterion(hasItem(ModItem.MARSHMALLOW), conditionsFromItem(ModItem.MARSHMALLOW))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "roasted_marshmallow_campfire"));

        // Cooked Guardian Slice
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(ModItem.GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE, 0.0f, 600)
                .criterion(hasItem(ModItem.GUARDIAN_SLICE), conditionsFromItem(ModItem.GUARDIAN_SLICE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cooked_guardian_slice_campfire"));
        // Cooked Elder Guardian Slice
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(ModItem.ELDER_GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE, 0.0f, 600)
                .criterion(hasItem(ModItem.ELDER_GUARDIAN_SLICE), conditionsFromItem(ModItem.ELDER_GUARDIAN_SLICE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "cooked_elder_guardian_slice_campfire"));
    }

    // Smithing recipes
    private void smithing(RecipeExporter exporter)
    {
        // TRIMS
        RecipeHelper.streamCustomTemplates().forEach(template -> offerSmithingTrimRecipe(exporter, template.template(), template.id()));

        // Obsidian items.
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.GOLDEN_SWORD, RecipeCategory.COMBAT, ModItem.OBSIDIAN_SWORD);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.GOLDEN_AXE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_AXE);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.GOLDEN_PICKAXE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_PICKAXE);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.GOLDEN_HOE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_HOE);
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                Items.GOLDEN_SHOVEL, RecipeCategory.TOOLS, ModItem.OBSIDIAN_SHOVEL);

        // Brimtan Items
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_SWORD,
                ModItem.VIVULITE_SWORD, RecipeCategory.COMBAT, ModItem.BRIMTAN_SWORD);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_SHOVEL,
                ModItem.VIVULITE_SHOVEL, RecipeCategory.TOOLS, ModItem.BRIMTAN_SHOVEL);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_PICKAXE,
                ModItem.VIVULITE_PICKAXE, RecipeCategory.TOOLS, ModItem.BRIMTAN_PICKAXE);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_AXE,
                ModItem.VIVULITE_AXE, RecipeCategory.TOOLS, ModItem.BRIMTAN_AXE);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_HOE,
                ModItem.VIVULITE_HOE, RecipeCategory.TOOLS, ModItem.BRIMTAN_HOE);

        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_HELMET,
                ModItem.VIVULITE_HELMET, RecipeCategory.TOOLS, ModItem.BRIMTAN_HELMET);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_CHESTPLATE,
                ModItem.VIVULITE_CHESTPLATE, RecipeCategory.TOOLS, ModItem.BRIMTAN_CHESTPLATE);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_LEGGINGS,
                ModItem.VIVULITE_LEGGINGS, RecipeCategory.TOOLS, ModItem.BRIMTAN_LEGGINGS);
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE, ModItem.BRIMTAN_SHELL_BOOTS,
                ModItem.VIVULITE_BOOTS, RecipeCategory.TOOLS, ModItem.BRIMTAN_BOOTS);
    }

    // Stonecutting recipes
    private void stonecutting(RecipeExporter exporter)
    {
        // Stone-Like Bricks
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.CRAGULSTANE, ModBlocks.CRAGULSTANE_BRICKS, "cragulstane_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.HIELOSTONE, ModBlocks.HIELOSTONE_BRICKS, "hielostone_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS, ModBlocks.HIELOSTONE_TILES, "hielostone_tiles");

        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(ModBlocks.HIELOSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.HIELOSTONE_TILES)
                .criterion(hasItem(ModBlocks.HIELOSTONE), conditionsFromItem(ModBlocks.HIELOSTONE))
                .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "hielostone_tiles_from_stonecutting_hielostone"));

        // Stairs (also handles crafting table)
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS, "blue_nether_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_STAIRS, "purple_nether_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS, ModBlocks.CRAGULSTANE_BRICK_STAIRS, "cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS, "brimmed_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS, "orange_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS, "tyrian_cragulstane_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.NACRE_BRICKS, ModBlocks.NACRE_BRICK_STAIRS, "nacre_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PALE_PRISMARINE, ModBlocks.PALE_PRISMARINE_STAIRS, "pale_prismarine_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PALE_PRISMARINE_BRICKS, ModBlocks.PALE_PRISMARINE_BRICK_STAIRS, "pale_prismarine_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.DEEP_PALE_PRISMARINE, ModBlocks.DEEP_PALE_PRISMARINE_STAIRS, "deep_pale_prismarine_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TURTLE_SCUTE_BRICKS, ModBlocks.TURTLE_SCUTE_BRICK_STAIRS, "turtle_scute_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE, ModBlocks.HIELOSTONE_STAIRS, "hielostone_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS, ModBlocks.HIELOSTONE_BRICK_STAIRS, "hielostone_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE_TILES, ModBlocks.HIELOSTONE_TILE_STAIRS, "hielostone_tile_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE_PLATES, ModBlocks.HIELOSTONE_PLATE_STAIRS, "hielostone_plate_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.COBBLEFROST, ModBlocks.COBBLEFROST_STAIRS, "cobblefrost_stairs");

        // Slabs (also handles crafting table)
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_SLAB, "blue_nether_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_SLAB, "purple_nether_brick_slab");

        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS, ModBlocks.CRAGULSTANE_BRICK_SLAB, "cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB, "brimmed_cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB, "orange_cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB, "tyrian_cragulstane_brick_slab");

        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.NACRE_BRICKS, ModBlocks.NACRE_BRICK_SLAB, "nacre_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.PALE_PRISMARINE, ModBlocks.PALE_PRISMARINE_SLAB, "pale_prismarine_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.PALE_PRISMARINE_BRICKS, ModBlocks.PALE_PRISMARINE_BRICK_SLAB, "pale_prismarine_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.DEEP_PALE_PRISMARINE, ModBlocks.DEEP_PALE_PRISMARINE_SLAB, "deep_pale_prismarine_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.TURTLE_SCUTE_BRICKS, ModBlocks.TURTLE_SCUTE_BRICK_SLAB, "turtle_scute_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE, ModBlocks.HIELOSTONE_SLAB, "hielostone_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS, ModBlocks.HIELOSTONE_BRICK_SLAB, "hielostone_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE_TILES, ModBlocks.HIELOSTONE_TILE_SLAB, "hielostone_tile_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE_PLATES, ModBlocks.HIELOSTONE_PLATE_SLAB, "hielostone_plate_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.COBBLEFROST, ModBlocks.COBBLEFROST_SLAB, "cobblefrost_slab");

        // Walls (also handles crafting table)
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_WALL, "blue_nether_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_WALL, "purple_nether_brick_wall");

        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS, ModBlocks.CRAGULSTANE_BRICK_WALL, "cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL, "brimmed_cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL, "orange_cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL, "tyrian_cragulstane_brick_wall");

        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.NACRE_BRICKS, ModBlocks.NACRE_BRICK_WALL, "nacre_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.PALE_PRISMARINE, ModBlocks.PALE_PRISMARINE_WALL, "pale_prismarine_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.TURTLE_SCUTE_BRICKS, ModBlocks.TURTLE_SCUTE_BRICK_WALL, "turtle_scute_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE, ModBlocks.HIELOSTONE_WALL, "hielostone_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS, ModBlocks.HIELOSTONE_BRICK_WALL, "hielostone_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE_TILES, ModBlocks.HIELOSTONE_TILE_WALL, "hielostone_tile_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE_PLATES, ModBlocks.HIELOSTONE_PLATE_WALL, "hielostone_plate_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.COBBLEFROST, ModBlocks.COBBLEFROST_WALL, "cobblefrost_wall");

        // Chisels (also handles crafting table)
        RecipeHelper.createChiselRecipesMulti(exporter, ModBlocks.CRAGULSTANE,
                ModBlocks.CRAGULSTANE_BRICKS,
                ModBlocks.CRAGULSTANE_BRICK_SLAB,
                ModBlocks.CHISELED_CRAGULSTANE_BRICKS,
                "chiseled_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB,
                ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS,
                "chiseled_brimmed_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB,
                ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS,
                "chiseled_orange_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB,
                ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS,
                "chiseled_tyrian_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB,
                ModBlocks.CHISELED_RED_NETHER_BRICKS,
                "chiseled_red_nether_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_SLAB,
                ModBlocks.CHISELED_BLUE_NETHER_BRICKS,
                "chiseled_blue_nether_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_SLAB,
                ModBlocks.CHISELED_PURPLE_NETHER_BRICKS,
                "chiseled_purple_nether_bricks");
        RecipeHelper.createChiselRecipesMulti(exporter, ModBlocks.HIELOSTONE,
                ModBlocks.HIELOSTONE_BRICKS,
                ModBlocks.HIELOSTONE_BRICK_SLAB,
                ModBlocks.HIELOSTONE_PLATES,
                "hielostone_plates");

        //StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(Items.CALCITE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.TOWER_BRICKS)
        //        .criterion(hasItem(Items.CALCITE), conditionsFromItem(Items.CALCITE))
        //        .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "calcite_tower_bricks_from_stonecutting"));
    }

    // FRONTIERS: Fletching recipes
    private void fletching(RecipeExporter exporter)
    {
        // Vanilla Arrow
        FletchingRecipeBuilder.create(
                        Items.FLINT,
                        Items.ARROW,
                        Identifier.ofVanilla("textures/entity/projectiles/arrow.png")
                )
                .criterion(hasItem(Items.FLINT), conditionsFromItem(Items.FLINT))
                .offerTo(exporter);
        // Warp Arrow
        FletchingRecipeBuilder.create(
                        ModItem.WARP_ARROW_ARROWHEAD,
                        ModItem.WARP_ARROW,
                        Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/warp_arrow.png")
                )
                .criterion(hasItem(ModItem.WARP_ARROW_ARROWHEAD), conditionsFromItem(ModItem.WARP_ARROW_ARROWHEAD))
                .offerTo(exporter);
        // Bouncy Arrow
        FletchingRecipeBuilder.create(
                        ModItem.BOUNCY_ARROW_ARROWHEAD,
                        ModItem.BOUNCY_ARROW,
                        Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/bouncy_arrow.png")
                )
                .criterion(hasItem(ModItem.BOUNCY_ARROW_ARROWHEAD), conditionsFromItem(ModItem.BOUNCY_ARROW_ARROWHEAD))
                .offerTo(exporter);
        // Subzero Arrow
        FletchingRecipeBuilder.create(
                        ModItem.SUBZERO_ARROW_ARROWHEAD,
                        ModItem.SUBZERO_ARROW,
                        Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/subzero_arrow.png")
                )
                .criterion(hasItem(ModItem.SUBZERO_ARROW_ARROWHEAD), conditionsFromItem(ModItem.SUBZERO_ARROW_ARROWHEAD))
                .offerTo(exporter);
        // Spectral Arrow
        FletchingRecipeBuilder.create(
                        ModItem.SPECTRAL_ARROW_ARROWHEAD,
                        Items.SPECTRAL_ARROW,
                        Identifier.ofVanilla("textures/entity/projectiles/spectral_arrow.png")
                )
                .criterion(hasItem(ModItem.SPECTRAL_ARROW_ARROWHEAD), conditionsFromItem(ModItem.SPECTRAL_ARROW_ARROWHEAD))
                .offerTo(exporter);
        // Dynamite Arrow
        FletchingRecipeBuilder.create(
                        ModItem.DYNAMITE_ARROW_ARROWHEAD,
                        ModItem.DYNAMITE_ARROW,
                        Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/dynamite_arrow.png")
                )
                .criterion(hasItem(ModItem.DYNAMITE_ARROW_ARROWHEAD), conditionsFromItem(ModItem.DYNAMITE_ARROW_ARROWHEAD))
                .offerTo(exporter);
        // Prismarine Arrow
        FletchingRecipeBuilder.create(
                        ModItem.PRISMARINE_ARROW_ARROWHEAD,
                        ModItem.PRISMARINE_ARROW,
                        Identifier.of(Frontiers.MOD_ID, "textures/entity/projectiles/prismarine_arrow.png")
                )
                .criterion(hasItem(ModItem.PRISMARINE_ARROW_ARROWHEAD), conditionsFromItem(ModItem.PRISMARINE_ARROW_ARROWHEAD))
                .offerTo(exporter);
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

        fletching(exporter);            // FRONTIERS RECIPES: Fletching

        // COMPAT: Farmer's Delight
        FDRecipeProvider.crafting(exporter);             // Crafting (all types)
        FDRecipeProvider.smelting(exporter);             // Smelting
        FDRecipeProvider.blasting(exporter);             // Blasting
        FDRecipeProvider.campfire(exporter);             // Campfire cooking
        FDRecipeProvider.smoking(exporter);              // Smoking in Smoker
        FDRecipeProvider.smithing(exporter);             // Smithing (trim & upgrade)
        FDRecipeProvider.stonecutting(exporter);         // Stonecutting

        // COMPAT: Bountiful Fares
        BFRecipeProvider.crafting(exporter);             // Crafting (all types)
        BFRecipeProvider.smelting(exporter);             // Smelting
        BFRecipeProvider.blasting(exporter);             // Blasting
        BFRecipeProvider.campfire(exporter);             // Campfire cooking
        BFRecipeProvider.smoking(exporter);              // Smoking in Smoker
        BFRecipeProvider.smithing(exporter);             // Smithing (trim & upgrade)
        BFRecipeProvider.stonecutting(exporter);         // Stonecutting
    }
}
