package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.recipe.fletching.FletchingRecipeBuilder;
import net.artyrian.frontiers.definition.recipe.special.CobaltShieldDecorationRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import java.util.concurrent.CompletableFuture;

// Generates recipes for the entire project.
public class ModRecipeProvider extends FabricRecipeProvider
{
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // Crafting recipes
    private void crafting(RecipeOutput exporter)
    {
        // Obsidian Smithing Upgrade
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('C', Items.OBSIDIAN)
                .define('S', ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE)
                .unlockedBy(getHasName(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE), has(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE))
                .save(exporter);
        // Obsidian Casing
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.OBSIDIAN_CASING)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(Items.OBSIDIAN)
                .requires(Items.OBSIDIAN)
                .requires(Items.OBSIDIAN)
                .requires(Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(exporter);
        // Cobalt Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.COBALT_BLOCK.asItem(), ModItem.COBALT_INGOT);
        // Cobalt Fishing Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.COBALT_FISHING_ROD)
                .pattern("  /")
                .pattern(" /S")
                .pattern("/ S")
                .define('/', ModItem.COBALT_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(ModItem.COBALT_INGOT), has(ModItem.COBALT_INGOT))
                .save(exporter);
        // Cobalt Shield
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.COBALT_SHIELD)
                .pattern("CGC")
                .pattern("CCC")
                .pattern(" C ")
                .define('C', ModItem.COBALT_INGOT)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModItem.COBALT_INGOT), has(ModItem.COBALT_INGOT))
                .save(exporter);
        // Cobalt Grilles
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.COBALT_GRILLES, 24)
                .define('#', ModItem.COBALT_INGOT)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(ModItem.COBALT_INGOT), has(ModItem.COBALT_INGOT))
                .save(exporter);
        // All cobalt tools.
        RecipeHelper.toolHelper(exporter, ModItem.COBALT_INGOT,
                ModItem.COBALT_AXE, ModItem.COBALT_SWORD, ModItem.COBALT_SHOVEL, ModItem.COBALT_PICKAXE, ModItem.COBALT_HOE
        );
        // Ancient Rose Bush
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ANCIENT_ROSE_BUSH)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', ModBlocks.ANCIENT_ROSE)
                .define('X', Items.ROSE_BUSH)
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ROSE), has(ModBlocks.ANCIENT_ROSE))
                .save(exporter);
        // Violet Rose Bush
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.VIOLET_ROSE_BUSH)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', ModBlocks.VIOLET_ROSE)
                .define('X', Items.ROSE_BUSH)
                .unlockedBy(getHasName(ModBlocks.VIOLET_ROSE), has(ModBlocks.VIOLET_ROSE))
                .save(exporter);
        // Cyan Dye from Ancient Rose Bush
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_DYE, 2)
                .requires(ModBlocks.ANCIENT_ROSE_BUSH)
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ROSE_BUSH), has(ModBlocks.ANCIENT_ROSE_BUSH))
                .group("cyan_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose_bush"));
        // Cyan Dye from Ancient Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_DYE)
                .requires(ModBlocks.ANCIENT_ROSE)
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ROSE), has(ModBlocks.ANCIENT_ROSE))
                .group("cyan_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose"));
        // Purple Dye from Violet Rose Bush
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE, 2)
                .requires(ModBlocks.VIOLET_ROSE_BUSH)
                .unlockedBy(getHasName(ModBlocks.VIOLET_ROSE_BUSH), has(ModBlocks.VIOLET_ROSE_BUSH))
                .group("purple_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "purple_dye_from_violet_rose_bush"));
        // Purple Dye from Violet Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE)
                .requires(ModBlocks.VIOLET_ROSE)
                .unlockedBy(getHasName(ModBlocks.VIOLET_ROSE), has(ModBlocks.VIOLET_ROSE))
                .group("purple_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "purple_dye_from_violet_rose"));
        // Red Dye from Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE)
                .requires(ModBlocks.ROSE)
                .unlockedBy(getHasName(ModBlocks.ROSE), has(ModBlocks.ROSE))
                .group("red_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "red_dye_from_rose"));
        // Onyx Bones from Withered Essence
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.ONYX_BONE, 4)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', Items.BONE)
                .define('X', ModItem.WITHERED_ESSENCE)
                .unlockedBy(getHasName(ModItem.WITHERED_ESSENCE), has(ModItem.WITHERED_ESSENCE))
                .save(exporter);
        // Necro Weave
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.NECRO_WEAVE)
                .pattern("X$ ")
                .pattern("$X ")
                .pattern("   ")
                .define('$', ItemTags.WOOL)
                .define('X', ModItem.ONYX_BONE)
                .unlockedBy(getHasName(ModItem.ONYX_BONE), has(ModItem.ONYX_BONE))
                .save(exporter);
        // Necro Rug
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.NECRO_RUG, 4)
                .pattern("XX")
                .define('X', ModItem.NECRO_WEAVE)
                .unlockedBy(getHasName(ModItem.NECRO_WEAVE), has(ModItem.NECRO_WEAVE))
                .save(exporter);
        // Necro Weave Block <-> Necro Weave convertible
        RecipeHelper.createReversible(exporter, ModBlocks.NECRO_WEAVE_BLOCK.asItem(), ModItem.NECRO_WEAVE);
        // Mourning Gold Ingot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.MOURNING_GOLD_INGOT, 2)
                .pattern("X$X")
                .pattern("XOX")
                .pattern("X$X")
                .define('$', Items.GOLD_INGOT)
                .define('X', Items.QUARTZ)
                .define('O', ModItem.ECTOPLASM)
                .unlockedBy(getHasName(ModItem.ECTOPLASM), has(ModItem.ECTOPLASM))
                .group("mourning_gold_ingot")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "mourning_gold_ingot"));
        // Rotcross/Necro Armor
        RecipeHelper.armorHelper(exporter, ModItem.NECRO_WEAVE,
                ModItem.NECRO_WEAVE_HELMET,
                ModItem.NECRO_WEAVE_CHESTPLATE,
                ModItem.NECRO_WEAVE_LEGGINGS,
                ModItem.NECRO_WEAVE_BOOTS
        );
        // Black Emeralds
        RecipeHelper.createReversible(exporter, ModBlocks.BLACK_EMERALD_BLOCK.asItem(), ModItem.BLACK_EMERALD);
        // Diamond Chunk (UNUSED)
        //ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.DIAMOND_CHUNK, 4)
        //        .input(Items.DIAMOND)
        //        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
        //        .group("diamond_chunk")
        //        .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "diamond_chunk_from_gem"));
        // Mourning Gold Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOURNING_GOLD_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.MOURNING_GOLD_INGOT)
                .unlockedBy(getHasName(ModItem.MOURNING_GOLD_INGOT), has(ModItem.MOURNING_GOLD_INGOT))
                .save(exporter);
        // 9 Mourning Gold Ingots from Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.MOURNING_GOLD_INGOT, 9)
                .requires(ModBlocks.MOURNING_GOLD_BLOCK)
                .unlockedBy(getHasName(ModBlocks.MOURNING_GOLD_BLOCK), has(ModBlocks.MOURNING_GOLD_BLOCK))
                .group("mourning_gold_ingot")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "mourning_gold_ingot_from_block"));
        // Mourning Gold Armor
        RecipeHelper.armorHelper(exporter, ModItem.MOURNING_GOLD_INGOT,
                ModItem.MOURNING_GOLD_HELMET,
                ModItem.MOURNING_GOLD_CHESTPLATE,
                ModItem.MOURNING_GOLD_LEGGINGS,
                ModItem.MOURNING_GOLD_BOOTS
        );
        // Mossy Tower Bricks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_TOWER_BRICKS)
                .requires(ModBlocks.TOWER_BRICKS)
                .requires(Items.GLOW_LICHEN)
                .unlockedBy(getHasName(ModBlocks.TOWER_BRICKS), has(ModBlocks.TOWER_BRICKS))
                .save(exporter);
        // All mourning gold tools.
        RecipeHelper.toolHelper(exporter, ModItem.MOURNING_GOLD_INGOT,
                ModItem.MOURNING_GOLD_AXE, ModItem.MOURNING_GOLD_SWORD, ModItem.MOURNING_GOLD_SHOVEL, ModItem.MOURNING_GOLD_PICKAXE, ModItem.MOURNING_GOLD_HOE
        );
        // Marshmallow
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.MARSHMALLOW, 4)
                .pattern(" X ")
                .pattern("OE ")
                .pattern("   ")
                .define('X', Items.SUGAR)
                .define('O', Items.EGG)
                .define('E', ModItem.ECTOPLASM)
                .unlockedBy(getHasName(ModItem.ECTOPLASM), has(ModItem.ECTOPLASM))
                .save(exporter);
        // Tome of Fangs
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.TOME_OF_FANGS)
                .pattern("GXG")
                .pattern("XBX")
                .pattern("GXG")
                .define('X', ModItem.INVOKE_SHARD)
                .define('B', Items.BOOK)
                .define('G', ModItem.RAVAGER_TOOTH)
                .unlockedBy(getHasName(ModItem.INVOKE_SHARD), has(ModItem.INVOKE_SHARD))
                .save(exporter);
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NACRE_BRICKS)
                .pattern("## ")
                .pattern("## ")
                .pattern("   ")
                .define('#', ModItem.NACRE_BRICK)
                .unlockedBy(getHasName(ModItem.NACRE_BRICK), has(ModItem.NACRE_BRICK))
                .save(exporter);
        // Levi Roll
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.LEVI_ROLL, 2)
                .pattern("   ")
                .pattern("OwO")
                .pattern("   ")
                .define('w', ModItem.SHULKER_RESIDUE)
                .define('O', Items.WHEAT)
                .unlockedBy(getHasName(ModItem.SHULKER_RESIDUE), has(ModItem.SHULKER_RESIDUE))
                .save(exporter);
        // Cobalt Armor
        RecipeHelper.armorHelper(exporter, ModItem.COBALT_INGOT,
                ModItem.COBALT_HELMET,
                ModItem.COBALT_CHESTPLATE,
                ModItem.COBALT_LEGGINGS,
                ModItem.COBALT_BOOTS
        );
        // Cursed Tablet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CURSED_TABLET)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItem.TABLET_FRAGMENT)
                .unlockedBy(getHasName(ModItem.TABLET_FRAGMENT), has(ModItem.TABLET_FRAGMENT))
                .save(exporter);
        // Light Blue Dye (Snow Dahlia)
        oneToOneConversionRecipe(exporter, Items.LIGHT_BLUE_DYE, ModBlocks.SNOW_DAHLIA, "light_blue_dye");
        // Purple Dye (Fungal Daffodil)
        oneToOneConversionRecipe(exporter, Items.PURPLE_DYE, ModBlocks.FUNGAL_DAFFODIL, "purple_dye");
        // Red Dye (Crimcone)
        oneToOneConversionRecipe(exporter, Items.RED_DYE, ModBlocks.CRIMCONE, "red_dye");
        // Onyx Meal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.ONYX_MEAL, 3)
                .requires(ModItem.ONYX_BONE)
                .unlockedBy(getHasName(ModItem.ONYX_BONE), has(ModItem.ONYX_BONE))
                .group("onyx_meal")
                .save(exporter);
        // Black Dye from Oxyx Meal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLACK_DYE)
                .requires(ModItem.ONYX_MEAL)
                .unlockedBy(getHasName(ModItem.ONYX_MEAL), has(ModItem.ONYX_MEAL))
                .group("black_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_dye_from_onyx_meal"));
        // Bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.BAIT, 2)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.SLIME_BALL)
                .unlockedBy(getHasName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(exporter);

        // Core Plate: Depths
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.DEPTHS_CORE_PLATE)
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', Items.DIAMOND)
                .define('O', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItem.UNFINISHED_CORE), has(ModItem.UNFINISHED_CORE))
                .save(exporter);
        // Core Plate: Frontal
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.FRONTAL_CORE_PLATE)
                .pattern(" X ")
                .pattern("EOE")
                .pattern(" X ")
                .define('X', ModItem.COBALT_INGOT)
                .define('E', ModItem.VERDINITE_INGOT)
                .define('O', ModItem.VIVULITE_INGOT)
                .unlockedBy(getHasName(ModItem.UNFINISHED_CORE), has(ModItem.UNFINISHED_CORE))
                .save(exporter);

        // Reactive Core
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.REACTIVE_CORE)
                .pattern(" A ")
                .pattern("BOC")
                .pattern(" D ")
                .define('A', ModItem.DEPTHS_CORE_PLATE)
                .define('B', ModItem.FRONTAL_CORE_PLATE)
                .define('C', ModItem.DEPTHS_CORE_PLATE)
                .define('D', ModItem.FRONTAL_CORE_PLATE)
                .define('O', ModItem.UNFINISHED_CORE)
                .unlockedBy(getHasName(ModItem.DEPTHS_CORE_PLATE), has(ModItem.DEPTHS_CORE_PLATE))
                .unlockedBy(getHasName(ModItem.FRONTAL_CORE_PLATE), has(ModItem.FRONTAL_CORE_PLATE))
                .save(exporter);

        // Strange Core
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STRANGE_CORE)
                .pattern("DXD")
                .pattern("DOD")
                .pattern("DXD")
                .define('D', Items.IRON_INGOT)
                .define('X', Items.DIAMOND)
                .define('O', ModItem.REACTIVE_CORE)
                .unlockedBy(getHasName(ModItem.REACTIVE_CORE), has(ModItem.REACTIVE_CORE))
                .save(exporter);

        // Copper Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.COPPER_BOW)
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.COPPER_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(exporter);
        // Iron Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.IRON_BOW)
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.IRON_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(exporter);
        // Diamond Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.DIAMOND_BOW)
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.DIAMOND)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(exporter);
        // Netherite Bow
        RecipeHelper.templateUpgrade(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT,
                ModItem.DIAMOND_BOW, RecipeCategory.COMBAT, ModItem.NETHERITE_BOW);
        // Echo Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.ECHO_BOW)
                .pattern(" /S")
                .pattern("X-S")
                .pattern(" /S")
                .define('/', Items.AMETHYST_SHARD)
                .define('X', Items.ECHO_SHARD)
                .define('S', Items.STRING)
                .define('-', Items.GOLD_NUGGET)
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .save(exporter);
        // Verdinite Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.VERDINITE_BOW)
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', ModItem.VERDINITE_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(ModItem.VERDINITE_INGOT), has(ModItem.VERDINITE_INGOT))
                .save(exporter);
        // Reinforced Shears
        /*ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.REINFORCED_SHEARS)
                .pattern("-V")
                .pattern("O-")
                .input('V', Items.SHEARS)
                .input('-', Items.GOLD_NUGGET)
                .input('O', Items.DIAMOND)
                .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter);*/

        // Onyx Bone Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ONYX_BONE_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.ONYX_MEAL)
                .unlockedBy(getHasName(ModItem.ONYX_MEAL), has(ModItem.ONYX_MEAL))
                .save(exporter);
        // 9 Onyx Meal from Onyx Bone Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.ONYX_MEAL, 9)
                .requires(ModBlocks.ONYX_BONE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ONYX_BONE_BLOCK), has(ModBlocks.ONYX_BONE_BLOCK))
                .group("onyx_meal")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "onyx_meal_from_onyx_bone_block"));
        // Tower Key
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.TOWER_KEY)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.TOWER_KEY_FRAGMENT)
                .unlockedBy(getHasName(ModItem.TOWER_KEY_FRAGMENT), has(ModItem.TOWER_KEY_FRAGMENT))
                .save(exporter);
        // Warped Wart Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.WARPED_WART)
                .unlockedBy(getHasName(ModItem.WARPED_WART), has(ModItem.WARPED_WART))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "warped_wart_block_from_warped_wart"));
        // Blue Nether Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICKS)
                .pattern("NW")
                .pattern("WN")
                .define('W', ModItem.WARPED_WART)
                .define('N', Items.NETHER_BRICK)
                .unlockedBy(getHasName(ModItem.WARPED_WART), has(ModItem.WARPED_WART))
                .save(exporter);
        // Purple Nether Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_NETHER_BRICKS)
                .pattern("NW")
                .pattern("XN")
                .define('W', Items.NETHER_WART)
                .define('X', ModItem.WARPED_WART)
                .define('N', Items.NETHER_BRICK)
                .unlockedBy(getHasName(ModItem.WARPED_WART), has(ModItem.WARPED_WART))
                .save(exporter);
        // New Nether Brick Fences & Gates
        RecipeHelper.createBrickFence(exporter, ModBlocks.BLUE_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.BLUE_NETHER_BRICK_FENCE);
        RecipeHelper.createBrickFence(exporter, ModBlocks.PURPLE_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.PURPLE_NETHER_BRICK_FENCE);
        RecipeHelper.createBrickFence(exporter, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.RED_NETHER_BRICK_FENCE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.RED_NETHER_BRICKS, ModBlocks.RED_NETHER_BRICK_FENCE_GATE);
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.NETHER_BRICKS, ModBlocks.NETHER_BRICK_FENCE_GATE);
        // Quicksand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUICKSAND, 2)
                .pattern("EX")
                .pattern("XE")
                .define('E', Items.MUD)
                .define('X', Items.SAND)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(exporter);
        // Red Quicksand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_QUICKSAND, 2)
                .pattern("EX")
                .pattern("XE")
                .define('E', Items.MUD)
                .define('X', Items.RED_SAND)
                .unlockedBy(getHasName(Items.RED_SAND), has(Items.RED_SAND))
                .save(exporter);
        // Totem of Undying
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Items.TOTEM_OF_UNDYING)
                .pattern("OXO")
                .pattern("XXX")
                .pattern("GXG")
                .define('X', ModItem.INVOKE_SHARD)
                .define('G', Items.GOLD_INGOT)
                .define('O', Items.EMERALD)
                .unlockedBy(getHasName(ModItem.INVOKE_SHARD), has(ModItem.INVOKE_SHARD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "totem_of_undying"));
        // Totem of Undying
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.TOTEM_OF_AVARICE)
                .pattern("X")
                .pattern("E")
                .pattern("#")
                .define('X', Items.DIAMOND)
                .define('E', ModItem.INVOKE_SHARD)
                .define('#', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItem.INVOKE_SHARD), has(ModItem.INVOKE_SHARD))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "totem_of_avarice"));
        // Void Pearl
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItem.VOID_PEARL)
                .requires(Items.BLAZE_POWDER)
                .requires(Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(exporter);
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_GLASS, 2)
                .pattern(" P ")
                .pattern("PGP")
                .pattern(" P ")
                .define('P', Items.PRISMARINE_CRYSTALS)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.PRISMARINE_CRYSTALS), has(Items.PRISMARINE_CRYSTALS))
                .save(exporter);
        // Pale Prismarine
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_PRISMARINE, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE)
                .define('X', ModItem.PALE_PRISMARINE_SHARD)
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD), has(ModItem.PALE_PRISMARINE_SHARD))
                .save(exporter);
        // Pale Prismarine Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_PRISMARINE_BRICKS, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE_BRICKS)
                .define('X', ModItem.PALE_PRISMARINE_SHARD)
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD), has(ModItem.PALE_PRISMARINE_SHARD))
                .save(exporter);
        // Deep Pale Prismarine
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEP_PALE_PRISMARINE, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.DARK_PRISMARINE)
                .define('X', ModItem.PALE_PRISMARINE_SHARD)
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD), has(ModItem.PALE_PRISMARINE_SHARD))
                .save(exporter);
        // Pale Sea Glass
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_SEA_GLASS, 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', ModBlocks.SEA_GLASS)
                .define('X', ModItem.PALE_PRISMARINE_SHARD)
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD), has(ModItem.PALE_PRISMARINE_SHARD))
                .save(exporter);
        // Sea Glass Panes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_GLASS_PANE, 16)
                .pattern("##")
                .define('#', ModBlocks.SEA_GLASS)
                .unlockedBy(getHasName(ModBlocks.SEA_GLASS), has(ModBlocks.SEA_GLASS))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_SEA_GLASS_PANE, 16)
                .pattern("##")
                .define('#', ModBlocks.PALE_SEA_GLASS)
                .unlockedBy(getHasName(ModBlocks.PALE_SEA_GLASS), has(ModBlocks.PALE_SEA_GLASS))
                .save(exporter);
        // Frostite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.FROSTITE_BLOCK.asItem(), ModItem.FROSTITE_INGOT);
        // Turtle Scute Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TURTLE_SCUTE_BRICKS, 8)
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XXX")
                .define('E', Items.TURTLE_SCUTE)
                .define('X', Items.BRICK)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE))
                .save(exporter);
        // Hielostone (from Blue ice)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.HIELOSTONE)
                .requires(Items.BLUE_ICE)
                .requires(Items.STONE)
                .unlockedBy(getHasName(Items.BLUE_ICE), has(Items.BLUE_ICE))
                .save(exporter);
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
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.VIVULITE_ANVIL)
                .pattern("XXX")
                .pattern(" E ")
                .pattern("XXX")
                .define('E', ModItem.VIVULITE_INGOT)
                .define('X', ModBlocks.VIVULITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.VIVULITE_BLOCK), has(ModBlocks.VIVULITE_BLOCK))
                .save(exporter);
        // Beef Wellington
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.BEEF_WELLINGTON)
                .pattern("$$$")
                .pattern("$X$")
                .pattern("$I$")
                .define('$', Items.WHEAT)
                .define('X', ModItem.TRUFFLE)
                .define('I', Items.COOKED_BEEF)
                .unlockedBy(getHasName(ModItem.TRUFFLE), has(ModItem.TRUFFLE))
                .save(exporter);
        // Fruitcake
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.FRUITCAKE)
                .pattern("FWF")
                .pattern("F0F")
                .pattern("S$S")
                .define('$', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('0', Items.EGG)
                .define('W', Items.WHEAT)
                .define('F', ModTags.Items.FRUITCAKE_INGREDIENTS)
                .unlockedBy("foot_gummy", has(ModTags.Items.FRUITCAKE_INGREDIENTS))
                .save(exporter);
        // Truffle Potato Puff
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.TRUFFLE_POTATO_PUFF, 6)
                .pattern(" X ")
                .pattern("$I$")
                .define('$', Items.WHEAT)
                .define('X', ModItem.TRUFFLE)
                .define('I', Items.BAKED_POTATO)
                .unlockedBy(getHasName(ModItem.TRUFFLE), has(ModItem.TRUFFLE))
                .save(exporter);
        // Truffle Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItem.TRUFFLE_OIL, 4)
                .requires(ModItem.TRUFFLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(ModItem.TRUFFLE), has(ModItem.TRUFFLE))
                .save(exporter);
        // Personal Chest
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PERSONAL_CHEST)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("#E#")
                .define('#', Items.COBBLED_DEEPSLATE)
                .define('C', ModItem.LIGHTNING_IN_A_BOTTLE)
                .define('S', Items.GOLD_INGOT)
                .define('E', Items.ECHO_SHARD)
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .save(exporter);
        // Chest Key
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CHEST_KEY)
                .pattern(" ##")
                .pattern(" X#")
                .pattern("#  ")
                .define('#', Items.GOLD_INGOT)
                .define('X', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(ModBlocks.PERSONAL_CHEST), has(ModBlocks.PERSONAL_CHEST))
                .save(exporter);
        // Curse Altar
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CURSE_ALTAR)
                .pattern(" C ")
                .pattern("V#V")
                .pattern("L#L")
                .define('#', Items.SMOOTH_SANDSTONE)
                .define('C', ModItem.CURSED_TABLET)
                .define('V', ModItem.VOID_PEARL)
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(ModItem.CURSED_TABLET), has(ModItem.CURSED_TABLET))
                .save(exporter);
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, 4)
                .pattern(" B ")
                .pattern("B#B")
                .pattern(" B ")
                .define('B', ModBlocks.CRAGULSTANE_BRICKS)
                .define('#', ModItem.BRIMTAN_NUGGET)
                .unlockedBy(getHasName(ModItem.BRIMTAN_NUGGET), has(ModItem.BRIMTAN_NUGGET))
                .save(exporter);
        // Orange Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, 8)
                .pattern("BBB")
                .pattern("BXB")
                .pattern("BBB")
                .define('B', ModBlocks.CRAGULSTANE_BRICKS)
                .define('X', Items.LAVA_BUCKET)
                .unlockedBy(getHasName(ModBlocks.CRAGULSTANE_BRICKS), has(ModBlocks.CRAGULSTANE_BRICKS))
                .save(exporter);
        // Tyrian Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, 5)
                .pattern("B#B")
                .pattern("#BO")
                .pattern("BOB")
                .define('B', ModBlocks.CRAGULSTANE_BRICKS)
                .define('#', Items.NETHER_WART)
                .define('O', ModItem.WARPED_WART)
                .unlockedBy(getHasName(ModBlocks.CRAGULSTANE_BRICKS), has(ModBlocks.CRAGULSTANE_BRICKS))
                .save(exporter);
        // Pale Trident
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.PALE_TRIDENT)
                .pattern("XXX")
                .pattern("#T#")
                .pattern(" # ")
                .define('T', Items.TRIDENT)
                .define('#', ModItem.PALE_PRISMARINE_SHARD)
                .define('X', ModItem.ELDER_GUARDIAN_SPINE)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SPINE), has(ModItem.ELDER_GUARDIAN_SPINE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "pale_trident"));

        // Glistering Melon <-> Slices convertible
        RecipeHelper.createReversible(exporter, ModBlocks.GLISTERING_MELON.asItem(), Items.GLISTERING_MELON_SLICE);
        // June o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNE_O_LANTERN)
                .define('A', ModBlocks.CARVED_MELON)
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(ModBlocks.CARVED_MELON))
                .save(exporter);
        // Glistering o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLISTERING_JUNE_O_LANTERN)
                .define('A', ModBlocks.CARVED_GLISTERING_MELON)
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(ModBlocks.CARVED_GLISTERING_MELON))
                .save(exporter);
        // White Pumpkin
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.WHITE_PUMPKIN)
                .define('X', ModItem.ECTOPLASM)
                .define('P', Blocks.CARVED_PUMPKIN)
                .pattern(" X ")
                .pattern("XPX")
                .pattern(" X ")
                .unlockedBy(getHasName(ModItem.ECTOPLASM), has(ModItem.ECTOPLASM))
                .save(exporter);
        // White Jack o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_JACK_O_LANTERN)
                .define('A', ModBlocks.WHITE_PUMPKIN)
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(ModBlocks.WHITE_PUMPKIN))
                .save(exporter);
        // Slime Shoes
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.SLIME_SHOES)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItem.HARDENED_SLIME)
                .unlockedBy(getHasName(ModItem.HARDENED_SLIME), has(ModItem.HARDENED_SLIME))
                .save(exporter);

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
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItem.SPIRIT_CANDLE, 2)
                .pattern("   ")
                .pattern(" $ ")
                .pattern("#%#")
                .define('$', Items.STRING)
                .define('#', Items.HONEYCOMB)
                .define('%', ModItem.INCENSE)
                .unlockedBy(getHasName(ModItem.INCENSE), has(ModItem.INCENSE))
                .save(exporter);
        // Raw ore convertibles
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_COBALT_BLOCK.asItem(), ModItem.RAW_COBALT);
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_VERDINITE_BLOCK.asItem(), ModItem.RAW_VERDINITE);
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_VIVULITE_BLOCK.asItem(), ModItem.RAW_VIVULITE);
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_FROSTITE_BLOCK.asItem(), ModItem.RAW_FROSTITE);

        // Verdinite Horse Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.VERDINITE_HORSE_ARMOR)
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .define('H', ModItem.COBALT_HORSE_ARMOR)
                .define('#', ModItem.VERDINITE_INGOT)
                .unlockedBy(getHasName(ModItem.VERDINITE_INGOT), has(ModItem.VERDINITE_INGOT))
                .save(exporter);
        // Vivulite Horse Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.VIVULITE_HORSE_ARMOR)
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .define('H', ModItem.VERDINITE_HORSE_ARMOR)
                .define('#', ModItem.VIVULITE_INGOT)
                .unlockedBy(getHasName(ModItem.VIVULITE_INGOT), has(ModItem.VIVULITE_INGOT))
                .save(exporter);

        // Phantasmic TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PHANTASMIC_TNT)
                .pattern("#X#")
                .pattern("XSX")
                .pattern("#X#")
                .define('#', Items.GUNPOWDER)
                .define('X', ItemTags.SAND)
                .define('S', ModItem.SOUL)
                .unlockedBy(getHasName(ModItem.SOUL), has(ModItem.SOUL))
                .save(exporter);

        // Snow Melt
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItem.SNOW_MELT, 2)
                .requires(ModItem.FROST_BONE)
                .requires(Items.LEATHER)
                .unlockedBy(getHasName(ModItem.FROST_BONE), has(ModItem.FROST_BONE))
                .save(exporter);
        // Message in a Bottle
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItem.MESSAGE_IN_A_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.PAPER)
                .requires(Items.INK_SAC)
                .unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                .save(exporter);

        // Monster Bakery
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MONSTER_BAKERY)
                .pattern("#*#")
                .pattern("*&*")
                .pattern("#*#")
                .define('&', ModItem.INCENSE)
                .define('#', ModItem.SPAWNER_CHUNK)
                .define('*', Items.DIAMOND)
                .unlockedBy(getHasName(ModItem.SPAWNER_CHUNK), has(ModItem.SPAWNER_CHUNK))
                .save(exporter);

        // Phantom-Stitch Bed
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItem.PHANTOM_STITCH_BED)
                .pattern("###")
                .pattern("XXX")
                .define('#', Items.PHANTOM_MEMBRANE)
                .define('X', ItemTags.PLANKS)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(exporter);

        // Enchanting Magnet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENCHANTING_MAGNET)
                .pattern("#@#")
                .pattern("#X#")
                .pattern("#$#")
                .define('#', Items.GLASS)
                .define('$', Items.EMERALD_BLOCK)
                .define('X', Items.NETHER_STAR)
                .define('@', Items.EXPERIENCE_BOTTLE)
                .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                .save(exporter);

        // Item Vacuum
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ITEM_VACUUM)
                .pattern("#C#")
                .pattern("# #")
                .pattern(" $ ")
                .define('#', Items.IRON_BARS)
                .define('$', Items.HOPPER)
                .define('C', ModItem.SPAWNER_CHUNK)
                .unlockedBy(getHasName(ModItem.SPAWNER_CHUNK), has(ModItem.SPAWNER_CHUNK))
                .save(exporter);

        // Eboncork Blocks
        planksFromLogs(exporter, ModBlocks.EBONCORK_PLANKS, ModTags.Items.EBONCORK_LOGS, 1);
        stairBuilder(ModBlocks.EBONCORK_STAIRS, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_stairs")
                .save(exporter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EBONCORK_SLAB, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_slab")
                .save(exporter);
        fenceBuilder(ModBlocks.EBONCORK_FENCE, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_fence")
                .save(exporter);
        fenceGateBuilder(ModBlocks.EBONCORK_FENCE_GATE, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_fence_gate")
                .save(exporter);
        buttonBuilder(ModBlocks.EBONCORK_BUTTON, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_button")
                .save(exporter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.EBONCORK_PRESSURE_PLATE, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_pressure_plate")
                .save(exporter);
        doorBuilder(ModBlocks.EBONCORK_DOOR, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_door")
                .save(exporter);
        trapdoorBuilder(ModBlocks.EBONCORK_TRAPDOOR, Ingredient.of(ModBlocks.EBONCORK_PLANKS))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS), has(ModBlocks.EBONCORK_PLANKS))
                .group("wooden_trapdoor")
                .save(exporter);


        // Blighted Birch Blocks
        woodFromLogs(exporter, ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD, ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG);
        woodFromLogs(exporter, ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD, ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG);
        woodFromLogs(exporter, ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD, ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG);
        planksFromLogs(exporter, ModBlocks.BLIGHTED_BIRCH_PLANKS, ModTags.Items.BLIGHTED_BIRCH_LOGS, 4);

        stairBuilder(ModBlocks.BLIGHTED_BIRCH_STAIRS, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_stairs")
                .save(exporter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLIGHTED_BIRCH_SLAB, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_slab")
                .save(exporter);
        fenceBuilder(ModBlocks.BLIGHTED_BIRCH_FENCE, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_fence")
                .save(exporter);
        fenceGateBuilder(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_fence_gate")
                .save(exporter);
        buttonBuilder(ModBlocks.BLIGHTED_BIRCH_BUTTON, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_button")
                .save(exporter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_pressure_plate")
                .save(exporter);
        doorBuilder(ModBlocks.BLIGHTED_BIRCH_DOOR, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_door")
                .save(exporter);
        trapdoorBuilder(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR, Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS), has(ModBlocks.BLIGHTED_BIRCH_PLANKS))
                .group("wooden_trapdoor")
                .save(exporter);

        // Wreaths
        RecipeHelper.createWreath(exporter, Items.OAK_LEAVES, Items.POPPY, ModBlocks.OAK_WREATH);
        RecipeHelper.createWreath(exporter, Items.DARK_OAK_LEAVES, Items.RED_MUSHROOM, ModBlocks.DARK_OAK_WREATH);
        RecipeHelper.createWreath(exporter, Items.BIRCH_LEAVES, Items.DANDELION, ModBlocks.BIRCH_WREATH);
        RecipeHelper.createWreath(exporter, Items.SPRUCE_LEAVES, Items.SWEET_BERRIES, ModBlocks.SPRUCE_WREATH);
        RecipeHelper.createWreath(exporter, Items.JUNGLE_LEAVES, Items.COCOA_BEANS, ModBlocks.JUNGLE_WREATH);
        RecipeHelper.createWreath(exporter, Items.ACACIA_LEAVES, Items.SHORT_GRASS, ModBlocks.ACACIA_WREATH);
        RecipeHelper.createWreath(exporter, Items.MANGROVE_LEAVES, Items.BLUE_ORCHID, ModBlocks.MANGROVE_WREATH);
        RecipeHelper.createWreath(exporter, Items.FLOWERING_AZALEA_LEAVES, Items.GLOW_BERRIES, ModBlocks.AZALEA_WREATH);
        RecipeHelper.createWreath(exporter, Items.CHERRY_LEAVES, Items.PINK_PETALS, ModBlocks.CHERRY_WREATH);
        RecipeHelper.createWreath(exporter, ModBlocks.BLIGHTED_BIRCH_LEAVES.asItem(), Items.SPIDER_EYE, ModBlocks.BLIGHTED_BIRCH_WREATH);

        // Sugar Cane Block <-> Cane convertible
        RecipeHelper.createReversible(exporter, ModBlocks.SUGAR_CANE_BLOCK.asItem(), Items.SUGAR_CANE);

        // Cocoa Bean Block <-> Beans convertible
        RecipeHelper.createReversible(exporter, ModBlocks.COCOA_BEAN_BLOCK.asItem(), Items.COCOA_BEANS);

        // TEMP APPLE OF ENLIGHTENMENT RECIPE!
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.APPLE_OF_ENLIGHTENMENT)
                .pattern("AAA")
                .pattern("A#A")
                .pattern("AAA")
                .define('#', Items.ENCHANTED_GOLDEN_APPLE)
                .define('A', Items.AMETHYST_SHARD)
                .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                .save(exporter);

        // COMPLEX (BUILT-IN) CRAFTING RECIPES
        SpecialRecipeBuilder
                .special(CobaltShieldDecorationRecipe::new)
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_shield_decoration"));
        //ComplexRecipeJsonBuilder
        //        .create(PaleTridentRecipe::new)
        //        .offerTo(exporter, Identifier.of(Frontiers.MOD_ID, "pale_trident"));

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
    private void smelting(RecipeOutput exporter)
    {
        // Cobalt Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_COBALT), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModItem.RAW_COBALT), has(ModItem.RAW_COBALT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_raw_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.COBALT_ORE), has(ModBlocks.COBALT_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_cobalt_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_COBALT_ORE), has(ModBlocks.DEEPSLATE_COBALT_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_deepslate_cobalt_ore"));

        // Verdinite Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_VERDINITE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VERDINITE), has(ModItem.RAW_VERDINITE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_raw_verdinite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.VERDINITE_ORE), has(ModBlocks.VERDINITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_verdinite_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VERDINITE_ORE), has(ModBlocks.DEEPSLATE_VERDINITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_deepslate_verdinite_ore"));

        // Raw Frostite
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_FROSTITE), RecipeCategory.MISC, ModItem.FROSTITE_INGOT, 2.0f, 200)
                .group("frostite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_FROSTITE), has(ModItem.RAW_FROSTITE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frostite_ingot_from_smelting_raw_frostite"));

        // Vivulite ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_VIVULITE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VIVULITE), has(ModItem.RAW_VIVULITE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_raw_vivulite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.VIVULITE_ORE), has(ModBlocks.VIVULITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_vivulite_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VIVULITE_ORE), has(ModBlocks.DEEPSLATE_VIVULITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_deepslate_vivulite_ore"));

        // Black Emerald
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BLACK_EMERALD_ORE), RecipeCategory.MISC, ModItem.BLACK_EMERALD, 1f, 200)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.BLACK_EMERALD_ORE), has(ModBlocks.BLACK_EMERALD_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_smelting_black_emerald_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE), RecipeCategory.MISC, ModItem.BLACK_EMERALD, 1f, 200)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE), has(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_smelting_deepslate_black_emerald_ore"));

        // Brimtan Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.BRIMTAN_CLUSTER), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 400)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModItem.BRIMTAN_CLUSTER), has(ModItem.BRIMTAN_CLUSTER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_cluster"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BRIMTAN_ORE), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 400)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModBlocks.BRIMTAN_ORE), has(ModBlocks.BRIMTAN_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_ore"));

        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.2f, 100)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(ModItem.MARSHMALLOW), has(ModItem.MARSHMALLOW))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_smelting"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE, 0.35f, 200)
                .unlockedBy(getHasName(ModItem.GUARDIAN_SLICE), has(ModItem.GUARDIAN_SLICE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_smelting"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.ELDER_GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE, 0.35f, 200)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SLICE), has(ModItem.ELDER_GUARDIAN_SLICE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smelting"));

        // Nacre Brick
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.SHULKER_RESIDUE), RecipeCategory.MISC, ModItem.NACRE_BRICK, 0.3f, 200)
                .group("nacre_brick")
                .unlockedBy(getHasName(ModItem.SHULKER_RESIDUE), has(ModItem.SHULKER_RESIDUE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "nacre_brick_smelting"));

        // Hielostone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.COBBLEFROST), RecipeCategory.MISC, ModBlocks.HIELOSTONE, 0.1f, 200)
                .group("hielostone")
                .unlockedBy(getHasName(ModBlocks.COBBLEFROST), has(ModBlocks.COBBLEFROST))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hielostone_smelting"));
        // Stone from smelting Hielostone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.HIELOSTONE), RecipeCategory.MISC, Blocks.STONE, 0.1f, 200)
                .group("stone")
                .unlockedBy(getHasName(ModBlocks.HIELOSTONE), has(ModBlocks.HIELOSTONE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "stone_from_smelting_hielostone"));

        // Bone from Frost Bone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.FROST_BONE), RecipeCategory.MISC, Items.BONE, 0.3f, 200)
                .group("frost_bone")
                .unlockedBy(getHasName(ModItem.FROST_BONE), has(ModItem.FROST_BONE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frost_bone_smelting"));

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
    private void blasting(RecipeOutput exporter)
    {
        // Cobalt Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_COBALT), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModItem.RAW_COBALT), has(ModItem.RAW_COBALT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_raw_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.COBALT_ORE), has(ModBlocks.COBALT_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_cobalt_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_COBALT_ORE), RecipeCategory.MISC, ModItem.COBALT_INGOT, 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_COBALT_ORE), has(ModBlocks.DEEPSLATE_COBALT_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_deepslate_cobalt_ore"));

        // Verdinite Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_VERDINITE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VERDINITE), has(ModItem.RAW_VERDINITE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_raw_verdinite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.VERDINITE_ORE), has(ModBlocks.VERDINITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_verdinite_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_VERDINITE_ORE), RecipeCategory.MISC, ModItem.VERDINITE_INGOT, 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VERDINITE_ORE), has(ModBlocks.DEEPSLATE_VERDINITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_deepslate_verdinite_ore"));

        // Raw Frostite
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_FROSTITE), RecipeCategory.MISC, ModItem.FROSTITE_INGOT, 2.0f, 100)
                .group("frostite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_FROSTITE), has(ModItem.RAW_FROSTITE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frostite_ingot_from_blasting_raw_frostite"));

        // Vivulite Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_VIVULITE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VIVULITE), has(ModItem.RAW_VIVULITE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_raw_vivulite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.VIVULITE_ORE), has(ModBlocks.VIVULITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_vivulite_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_VIVULITE_ORE), RecipeCategory.MISC, ModItem.VIVULITE_INGOT, 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VIVULITE_ORE), has(ModBlocks.DEEPSLATE_VIVULITE_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_deepslate_vivulite_ore"));

        // Black Emerald
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.BLACK_EMERALD_ORE), RecipeCategory.MISC, ModItem.BLACK_EMERALD, 1f, 100)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.BLACK_EMERALD_ORE), has(ModBlocks.BLACK_EMERALD_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_blasting_black_emerald_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE), RecipeCategory.MISC, ModItem.BLACK_EMERALD, 1f, 100)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE), has(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_blasting_deepslate_black_emerald_ore"));

        // Brimtan Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.BRIMTAN_CLUSTER), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 200)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModItem.BRIMTAN_CLUSTER), has(ModItem.BRIMTAN_CLUSTER))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_cluster"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.BRIMTAN_ORE), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET, 1.4f, 200)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModBlocks.BRIMTAN_ORE), has(ModBlocks.BRIMTAN_ORE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_ore"));
    }

    // Smoker recipes
    private void smoking(RecipeOutput exporter)
    {
        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.2f, 50)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(ModItem.MARSHMALLOW), has(ModItem.MARSHMALLOW))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_smoking"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE, 0.35f, 100)
                .unlockedBy(getHasName(ModItem.GUARDIAN_SLICE), has(ModItem.GUARDIAN_SLICE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_smoking"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.ELDER_GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE, 0.35f, 100)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SLICE), has(ModItem.ELDER_GUARDIAN_SLICE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smoking"));
    }

    // Campfire recipes
    private void campfire(RecipeOutput exporter)
    {
        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.MARSHMALLOW), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW, 0.0f, 300)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(ModItem.MARSHMALLOW), has(ModItem.MARSHMALLOW))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_campfire"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE, 0.0f, 600)
                .unlockedBy(getHasName(ModItem.GUARDIAN_SLICE), has(ModItem.GUARDIAN_SLICE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_campfire"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.ELDER_GUARDIAN_SLICE), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE, 0.0f, 600)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SLICE), has(ModItem.ELDER_GUARDIAN_SLICE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_campfire"));
    }

    // Smithing recipes
    private void smithing(RecipeOutput exporter)
    {
        // TRIMS
        RecipeHelper.streamCustomTemplates().forEach(template -> trimSmithing(exporter, template.template(), template.id()));

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
    private void stonecutting(RecipeOutput exporter)
    {
        // Stone-Like Bricks
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.CRAGULSTANE, ModBlocks.CRAGULSTANE_BRICKS, "cragulstane_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.HIELOSTONE, ModBlocks.HIELOSTONE_BRICKS, "hielostone_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS, ModBlocks.HIELOSTONE_TILES, "hielostone_tiles");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HIELOSTONE), RecipeCategory.BUILDING_BLOCKS, ModBlocks.HIELOSTONE_TILES)
                .unlockedBy(getHasName(ModBlocks.HIELOSTONE), has(ModBlocks.HIELOSTONE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hielostone_tiles_from_stonecutting_hielostone"));

        // Stairs (also handles crafting table)
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS, ModBlocks.BLUE_NETHER_BRICK_STAIRS, "blue_nether_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS, ModBlocks.PURPLE_NETHER_BRICK_STAIRS, "purple_nether_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS, ModBlocks.CRAGULSTANE_BRICK_STAIRS, "cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS, "brimmed_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS, ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS, "orange_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS, "tyrian_cragulstane_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.NACRE_BRICKS, ModBlocks.NACRE_BRICK_STAIRS, "nacre_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TOWER_BRICKS, ModBlocks.TOWER_BRICK_STAIRS, "tower_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.MOSSY_TOWER_BRICKS, ModBlocks.MOSSY_TOWER_BRICK_STAIRS, "mossy_tower_brick_stairs");
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
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.TOWER_BRICKS, ModBlocks.TOWER_BRICK_SLAB, "tower_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.MOSSY_TOWER_BRICKS, ModBlocks.MOSSY_TOWER_BRICK_SLAB, "mossy_tower_brick_slab");
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
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.TOWER_BRICKS, ModBlocks.TOWER_BRICK_WALL, "tower_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.MOSSY_TOWER_BRICKS, ModBlocks.MOSSY_TOWER_BRICK_WALL, "mossy_tower_brick_wall");
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
    private void fletching(RecipeOutput exporter)
    {
        // Vanilla Arrow
        FletchingRecipeBuilder.create(
                        Items.FLINT,
                        Items.ARROW,
                        ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png")
                )
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                .save(exporter);
        // Warp Arrow
        FletchingRecipeBuilder.create(
                        ModItem.WARP_ARROW_ARROWHEAD,
                        ModItem.WARP_ARROW,
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/warp_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.WARP_ARROW_ARROWHEAD), has(ModItem.WARP_ARROW_ARROWHEAD))
                .save(exporter);
        // Bouncy Arrow
        FletchingRecipeBuilder.create(
                        ModItem.BOUNCY_ARROW_ARROWHEAD,
                        ModItem.BOUNCY_ARROW,
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/bouncy_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.BOUNCY_ARROW_ARROWHEAD), has(ModItem.BOUNCY_ARROW_ARROWHEAD))
                .save(exporter);
        // Subzero Arrow
        FletchingRecipeBuilder.create(
                        ModItem.SUBZERO_ARROW_ARROWHEAD,
                        ModItem.SUBZERO_ARROW,
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/subzero_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.SUBZERO_ARROW_ARROWHEAD), has(ModItem.SUBZERO_ARROW_ARROWHEAD))
                .save(exporter);
        // Spectral Arrow
        FletchingRecipeBuilder.create(
                        ModItem.SPECTRAL_ARROW_ARROWHEAD,
                        Items.SPECTRAL_ARROW,
                        ResourceLocation.withDefaultNamespace("textures/entity/projectiles/spectral_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.SPECTRAL_ARROW_ARROWHEAD), has(ModItem.SPECTRAL_ARROW_ARROWHEAD))
                .save(exporter);
        // Dynamite Arrow
        FletchingRecipeBuilder.create(
                        ModItem.DYNAMITE_ARROW_ARROWHEAD,
                        ModItem.DYNAMITE_ARROW,
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/dynamite_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.DYNAMITE_ARROW_ARROWHEAD), has(ModItem.DYNAMITE_ARROW_ARROWHEAD))
                .save(exporter);
        // Prismarine Arrow
        FletchingRecipeBuilder.create(
                        ModItem.PRISMARINE_ARROW_ARROWHEAD,
                        ModItem.PRISMARINE_ARROW,
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/prismarine_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.PRISMARINE_ARROW_ARROWHEAD), has(ModItem.PRISMARINE_ARROW_ARROWHEAD))
                .save(exporter);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter)
    {
        crafting(exporter);             // Crafting (all types)
        smelting(exporter);             // Smelting
        blasting(exporter);             // Blasting
        campfire(exporter);             // Campfire cooking
        smoking(exporter);              // Smoking in Smoker
        smithing(exporter);             // Smithing (trim & upgrade)
        stonecutting(exporter);         // Stonecutting

        fletching(exporter);            // FRONTIERS RECIPES: Fletching

        // INTEG: Farmer's Delight
        FDRecipeProvider.crafting(exporter);             // Crafting (all types)
        FDRecipeProvider.smelting(exporter);             // Smelting
        FDRecipeProvider.blasting(exporter);             // Blasting
        FDRecipeProvider.campfire(exporter);             // Campfire cooking
        FDRecipeProvider.smoking(exporter);              // Smoking in Smoker
        FDRecipeProvider.smithing(exporter);             // Smithing (trim & upgrade)
        FDRecipeProvider.stonecutting(exporter);         // Stonecutting

        // INTEG: Bountiful Fares
        BFRecipeProvider.crafting(exporter);             // Crafting (all types)
        BFRecipeProvider.smelting(exporter);             // Smelting
        BFRecipeProvider.blasting(exporter);             // Blasting
        BFRecipeProvider.campfire(exporter);             // Campfire cooking
        BFRecipeProvider.smoking(exporter);              // Smoking in Smoker
        BFRecipeProvider.smithing(exporter);             // Smithing (trim & upgrade)
        BFRecipeProvider.stonecutting(exporter);         // Stonecutting
    }
}
