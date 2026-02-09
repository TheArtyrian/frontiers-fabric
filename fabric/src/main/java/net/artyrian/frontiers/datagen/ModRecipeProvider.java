package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.datagen.compat.BFRecipeProvider;
import net.artyrian.frontiers.datagen.compat.FDRecipeProvider;
import net.artyrian.frontiers.definition.recipe.fletching.FletchingRecipeBuilder;
import net.artyrian.frontiers.definition.recipe.special.CobaltShieldDecorationRecipe;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('C', Items.OBSIDIAN)
                .define('S', ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get())
                .unlockedBy(getHasName(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get()), has(ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(exporter);
        // Obsidian Casing
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.OBSIDIAN_CASING.get())
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
        RecipeHelper.createReversible(exporter, ModBlocks.COBALT_BLOCK.get().asItem(), ModItem.COBALT_INGOT.get());
        // Cobalt Fishing Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.COBALT_FISHING_ROD.get())
                .pattern("  /")
                .pattern(" /S")
                .pattern("/ S")
                .define('/', ModItem.COBALT_INGOT.get())
                .define('S', Items.STRING)
                .unlockedBy(getHasName(ModItem.COBALT_INGOT.get()), has(ModItem.COBALT_INGOT.get()))
                .save(exporter);
        // Cobalt Shield
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.COBALT_SHIELD.get())
                .pattern("CGC")
                .pattern("CCC")
                .pattern(" C ")
                .define('C', ModItem.COBALT_INGOT.get())
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(ModItem.COBALT_INGOT.get()), has(ModItem.COBALT_INGOT.get()))
                .save(exporter);
        // Cobalt Grilles
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.COBALT_GRILLES.get(), 24)
                .define('#', ModItem.COBALT_INGOT.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(ModItem.COBALT_INGOT.get()), has(ModItem.COBALT_INGOT.get()))
                .save(exporter);
        // All cobalt tools.
        RecipeHelper.toolHelper(exporter, ModItem.COBALT_INGOT.get(),
                ModItem.COBALT_AXE.get(), ModItem.COBALT_SWORD.get(), ModItem.COBALT_SHOVEL.get(), ModItem.COBALT_PICKAXE.get(), ModItem.COBALT_HOE.get()
        );
        // Ancient Rose Bush
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ANCIENT_ROSE_BUSH.get())
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', ModBlocks.ANCIENT_ROSE.get())
                .define('X', Items.ROSE_BUSH)
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ROSE.get()), has(ModBlocks.ANCIENT_ROSE.get()))
                .save(exporter);
        // Violet Rose Bush
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.VIOLET_ROSE_BUSH.get())
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', ModBlocks.VIOLET_ROSE.get())
                .define('X', Items.ROSE_BUSH)
                .unlockedBy(getHasName(ModBlocks.VIOLET_ROSE.get()), has(ModBlocks.VIOLET_ROSE.get()))
                .save(exporter);
        // Cyan Dye from Ancient Rose Bush
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_DYE, 2)
                .requires(ModBlocks.ANCIENT_ROSE_BUSH.get())
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ROSE_BUSH.get()), has(ModBlocks.ANCIENT_ROSE_BUSH.get()))
                .group("cyan_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose_bush"));
        // Cyan Dye from Ancient Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_DYE)
                .requires(ModBlocks.ANCIENT_ROSE.get())
                .unlockedBy(getHasName(ModBlocks.ANCIENT_ROSE.get()), has(ModBlocks.ANCIENT_ROSE.get()))
                .group("cyan_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose"));
        // Purple Dye from Violet Rose Bush
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE, 2)
                .requires(ModBlocks.VIOLET_ROSE_BUSH.get())
                .unlockedBy(getHasName(ModBlocks.VIOLET_ROSE_BUSH.get()), has(ModBlocks.VIOLET_ROSE_BUSH.get()))
                .group("purple_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "purple_dye_from_violet_rose_bush"));
        // Purple Dye from Violet Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE)
                .requires(ModBlocks.VIOLET_ROSE.get())
                .unlockedBy(getHasName(ModBlocks.VIOLET_ROSE.get()), has(ModBlocks.VIOLET_ROSE.get()))
                .group("purple_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "purple_dye_from_violet_rose"));
        // Red Dye from Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE)
                .requires(ModBlocks.ROSE.get())
                .unlockedBy(getHasName(ModBlocks.ROSE.get()), has(ModBlocks.ROSE.get()))
                .group("red_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "red_dye_from_rose"));
        // Onyx Bones from Withered Essence
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.ONYX_BONE.get(), 4)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', Items.BONE)
                .define('X', ModItem.WITHERED_ESSENCE.get())
                .unlockedBy(getHasName(ModItem.WITHERED_ESSENCE.get()), has(ModItem.WITHERED_ESSENCE.get()))
                .save(exporter);
        // Necro Weave
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.NECRO_WEAVE.get())
                .pattern("X$ ")
                .pattern("$X ")
                .pattern("   ")
                .define('$', ItemTags.WOOL)
                .define('X', ModItem.ONYX_BONE.get())
                .group("necro_weave")
                .unlockedBy(getHasName(ModItem.ONYX_BONE.get()), has(ModItem.ONYX_BONE.get()))
                .save(exporter);
        // Necro Rug
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.NECRO_RUG.get(), 4)
                .pattern("XX")
                .define('X', ModItem.NECRO_WEAVE.get())
                .unlockedBy(getHasName(ModItem.NECRO_WEAVE.get()), has(ModItem.NECRO_WEAVE.get()))
                .save(exporter);
        // Necro Weave Block <-> Necro Weave convertible
        RecipeHelper.createReversibleWithItemGroup(exporter, ModBlocks.NECRO_WEAVE_BLOCK.get().asItem(), ModItem.NECRO_WEAVE.get(), "necro_weave");
        // Mourning Gold Ingot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.MOURNING_GOLD_INGOT.get(), 2)
                .pattern("X$X")
                .pattern("XOX")
                .pattern("X$X")
                .define('$', Items.GOLD_INGOT)
                .define('X', Items.QUARTZ)
                .define('O', ModItem.ECTOPLASM.get())
                .unlockedBy(getHasName(ModItem.ECTOPLASM.get()), has(ModItem.ECTOPLASM.get()))
                .group("mourning_gold_ingot")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "mourning_gold_ingot"));
        // Rotcross/Necro Armor
        RecipeHelper.armorHelper(exporter, ModItem.NECRO_WEAVE.get(),
                ModItem.NECRO_WEAVE_HELMET.get(),
                ModItem.NECRO_WEAVE_CHESTPLATE.get(),
                ModItem.NECRO_WEAVE_LEGGINGS.get(),
                ModItem.NECRO_WEAVE_BOOTS.get()
        );
        // Black Emeralds
        RecipeHelper.createReversible(exporter, ModBlocks.BLACK_EMERALD_BLOCK.get().asItem(), ModItem.BLACK_EMERALD.get());
        // Mourning Gold Block <-> Ingot
        RecipeHelper.createReversibleWithItemGroup(exporter, ModBlocks.MOURNING_GOLD_BLOCK.get().asItem(), ModItem.MOURNING_GOLD_INGOT.get(), "mourning_gold_ingot");
        // Mourning Gold Armor
        RecipeHelper.armorHelper(exporter, ModItem.MOURNING_GOLD_INGOT.get(),
                ModItem.MOURNING_GOLD_HELMET.get(),
                ModItem.MOURNING_GOLD_CHESTPLATE.get(),
                ModItem.MOURNING_GOLD_LEGGINGS.get(),
                ModItem.MOURNING_GOLD_BOOTS.get()
        );
        // Mossy Tower Bricks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_TOWER_BRICKS.get())
                .requires(ModBlocks.TOWER_BRICKS.get())
                .requires(Items.GLOW_LICHEN)
                .unlockedBy(getHasName(ModBlocks.TOWER_BRICKS.get()), has(ModBlocks.TOWER_BRICKS.get()))
                .save(exporter);
        // All mourning gold tools.
        RecipeHelper.toolHelper(exporter, ModItem.MOURNING_GOLD_INGOT.get(),
                ModItem.MOURNING_GOLD_AXE.get(), ModItem.MOURNING_GOLD_SWORD.get(), ModItem.MOURNING_GOLD_SHOVEL.get(), ModItem.MOURNING_GOLD_PICKAXE.get(), ModItem.MOURNING_GOLD_HOE.get()
        );
        // Marshmallow
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.MARSHMALLOW.get(), 4)
                .pattern(" X ")
                .pattern("OE ")
                .pattern("   ")
                .define('X', Items.SUGAR)
                .define('O', Items.EGG)
                .define('E', ModItem.ECTOPLASM.get())
                .unlockedBy(getHasName(ModItem.ECTOPLASM.get()), has(ModItem.ECTOPLASM.get()))
                .save(exporter);
        // Tome of Fangs
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.TOME_OF_FANGS.get())
                .pattern("GXG")
                .pattern("XBX")
                .pattern("GXG")
                .define('X', ModItem.INVOKE_SHARD.get())
                .define('B', Items.BOOK)
                .define('G', ModItem.RAVAGER_TOOTH.get())
                .unlockedBy(getHasName(ModItem.INVOKE_SHARD.get()), has(ModItem.INVOKE_SHARD.get()))
                .save(exporter);
        // All frostite tools.
        RecipeHelper.toolHelper(exporter, ModItem.FROSTITE_INGOT.get(),
                ModItem.FROSTITE_AXE.get(), ModItem.FROSTITE_SWORD.get(), ModItem.FROSTITE_SHOVEL.get(), ModItem.FROSTITE_PICKAXE.get(), ModItem.FROSTITE_HOE.get()
        );
        // Frostite Armor
        RecipeHelper.armorHelper(exporter, ModItem.FROSTITE_INGOT.get(),
                ModItem.FROSTITE_HELMET.get(),
                ModItem.FROSTITE_CHESTPLATE.get(),
                ModItem.FROSTITE_LEGGINGS.get(),
                ModItem.FROSTITE_BOOTS.get()
        );
        // Nacre Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.NACRE_BRICKS.get())
                .pattern("## ")
                .pattern("## ")
                .pattern("   ")
                .define('#', ModItem.NACRE_BRICK.get())
                .unlockedBy(getHasName(ModItem.NACRE_BRICK.get()), has(ModItem.NACRE_BRICK.get()))
                .save(exporter);
        // Levi Roll
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.LEVI_ROLL.get(), 2)
                .pattern("   ")
                .pattern("OwO")
                .pattern("   ")
                .define('w', ModItem.SHULKER_RESIDUE.get())
                .define('O', Items.WHEAT)
                .unlockedBy(getHasName(ModItem.SHULKER_RESIDUE.get()), has(ModItem.SHULKER_RESIDUE.get()))
                .save(exporter);
        // Cobalt Armor
        RecipeHelper.armorHelper(exporter, ModItem.COBALT_INGOT.get(),
                ModItem.COBALT_HELMET.get(),
                ModItem.COBALT_CHESTPLATE.get(),
                ModItem.COBALT_LEGGINGS.get(),
                ModItem.COBALT_BOOTS.get()
        );
        // Cursed Tablet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CURSED_TABLET.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItem.TABLET_FRAGMENT.get())
                .unlockedBy(getHasName(ModItem.TABLET_FRAGMENT.get()), has(ModItem.TABLET_FRAGMENT.get()))
                .save(exporter);
        // Light Blue Dye (Snow Dahlia)
        oneToOneConversionRecipe(exporter, Items.LIGHT_BLUE_DYE, ModBlocks.SNOW_DAHLIA.get(), "light_blue_dye");
        // Purple Dye (Fungal Daffodil)
        oneToOneConversionRecipe(exporter, Items.PURPLE_DYE, ModBlocks.FUNGAL_DAFFODIL.get(), "purple_dye");
        // Red Dye (Crimcone)
        oneToOneConversionRecipe(exporter, Items.RED_DYE, ModBlocks.CRIMCONE.get(), "red_dye");
        // Onyx Meal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.ONYX_MEAL.get(), 3)
                .requires(ModItem.ONYX_BONE.get())
                .unlockedBy(getHasName(ModItem.ONYX_BONE.get()), has(ModItem.ONYX_BONE.get()))
                .group("onyx_meal")
                .save(exporter);
        // Black Dye from Oxyx Meal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLACK_DYE)
                .requires(ModItem.ONYX_MEAL.get())
                .unlockedBy(getHasName(ModItem.ONYX_MEAL.get()), has(ModItem.ONYX_MEAL.get()))
                .group("black_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_dye_from_onyx_meal"));
        // Bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.BAIT.get(), 2)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.SLIME_BALL)
                .unlockedBy(getHasName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(exporter);

        // Core Plate: Depths
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.DEPTHS_CORE_PLATE.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', Items.DIAMOND)
                .define('O', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(ModItem.UNFINISHED_CORE.get()), has(ModItem.UNFINISHED_CORE.get()))
                .save(exporter);
        // Core Plate: Frontal
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.FRONTAL_CORE_PLATE.get())
                .pattern(" X ")
                .pattern("EOE")
                .pattern(" X ")
                .define('X', ModItem.COBALT_INGOT.get())
                .define('E', ModItem.VERDINITE_INGOT.get())
                .define('O', ModItem.VIVULITE_INGOT.get())
                .unlockedBy(getHasName(ModItem.UNFINISHED_CORE.get()), has(ModItem.UNFINISHED_CORE.get()))
                .save(exporter);

        // Reactive Core
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.REACTIVE_CORE.get())
                .pattern(" A ")
                .pattern("BOC")
                .pattern(" D ")
                .define('A', ModItem.DEPTHS_CORE_PLATE.get())
                .define('B', ModItem.FRONTAL_CORE_PLATE.get())
                .define('C', ModItem.DEPTHS_CORE_PLATE.get())
                .define('D', ModItem.FRONTAL_CORE_PLATE.get())
                .define('O', ModItem.UNFINISHED_CORE.get())
                .unlockedBy(getHasName(ModItem.DEPTHS_CORE_PLATE.get()), has(ModItem.DEPTHS_CORE_PLATE.get()))
                .unlockedBy(getHasName(ModItem.FRONTAL_CORE_PLATE.get()), has(ModItem.FRONTAL_CORE_PLATE.get()))
                .save(exporter);

        // Strange Core
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STRANGE_CORE.get())
                .pattern("DXD")
                .pattern("DOD")
                .pattern("DXD")
                .define('D', Items.IRON_INGOT)
                .define('X', Items.DIAMOND)
                .define('O', ModItem.REACTIVE_CORE.get())
                .unlockedBy(getHasName(ModItem.REACTIVE_CORE.get()), has(ModItem.REACTIVE_CORE.get()))
                .save(exporter);

        // Copper Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.COPPER_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.COPPER_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(exporter);
        // Iron Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.IRON_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.IRON_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(exporter);
        // Diamond Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.DIAMOND_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.DIAMOND)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(exporter);
        // Netherite Bow
        RecipeHelper.templateUpgrade(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT,
                ModItem.DIAMOND_BOW.get(), RecipeCategory.COMBAT, ModItem.NETHERITE_BOW.get());
        // Echo Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.ECHO_BOW.get())
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
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.VERDINITE_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', ModItem.VERDINITE_INGOT.get())
                .define('S', Items.STRING)
                .unlockedBy(getHasName(ModItem.VERDINITE_INGOT.get()), has(ModItem.VERDINITE_INGOT.get()))
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ONYX_BONE_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.ONYX_MEAL.get())
                .unlockedBy(getHasName(ModItem.ONYX_MEAL.get()), has(ModItem.ONYX_MEAL.get()))
                .save(exporter);
        // 9 Onyx Meal from Onyx Bone Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItem.ONYX_MEAL.get(), 9)
                .requires(ModBlocks.ONYX_BONE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ONYX_BONE_BLOCK.get()), has(ModBlocks.ONYX_BONE_BLOCK.get()))
                .group("onyx_meal")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "onyx_meal_from_onyx_bone_block"));
        // Tower Key
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.TOWER_KEY.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.TOWER_KEY_FRAGMENT.get())
                .unlockedBy(getHasName(ModItem.TOWER_KEY_FRAGMENT.get()), has(ModItem.TOWER_KEY_FRAGMENT.get()))
                .save(exporter);
        // Warped Wart Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ModItem.WARPED_WART.get())
                .unlockedBy(getHasName(ModItem.WARPED_WART.get()), has(ModItem.WARPED_WART.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "warped_wart_block_from_warped_wart"));
        // Blue Nether Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_NETHER_BRICKS.get(), 6)
                .pattern("NWN")
                .pattern("WNW")
                .pattern("NWN")
                .define('W', ModItem.WARPED_WART.get())
                .define('N', Items.NETHER_BRICK)
                .unlockedBy(getHasName(ModItem.WARPED_WART.get()), has(ModItem.WARPED_WART.get()))
                .save(exporter);
        // Purple Nether Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_NETHER_BRICKS.get(), 6)
                .pattern("NXN")
                .pattern("WNX")
                .pattern("NWN")
                .define('W', Items.NETHER_WART)
                .define('X', ModItem.WARPED_WART.get())
                .define('N', Items.NETHER_BRICK)
                .unlockedBy(getHasName(ModItem.WARPED_WART.get()), has(ModItem.WARPED_WART.get()))
                .save(exporter);
        // New Nether Brick Fences & Gates
        RecipeHelper.createBrickFence(exporter, ModBlocks.BLUE_NETHER_BRICKS.get(), Items.NETHER_BRICK, ModBlocks.BLUE_NETHER_BRICK_FENCE.get());
        RecipeHelper.createBrickFence(exporter, ModBlocks.PURPLE_NETHER_BRICKS.get(), Items.NETHER_BRICK, ModBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        RecipeHelper.createBrickFence(exporter, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK, ModBlocks.RED_NETHER_BRICK_FENCE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.RED_NETHER_BRICKS, ModBlocks.RED_NETHER_BRICK_FENCE_GATE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.NETHER_BRICKS, ModBlocks.NETHER_BRICK_FENCE_GATE.get());
        // Quicksand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QUICKSAND.get(), 2)
                .pattern("EX")
                .pattern("XE")
                .define('E', Items.MUD)
                .define('X', Items.SAND)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(exporter);
        // Red Quicksand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_QUICKSAND.get(), 2)
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
                .define('X', ModItem.INVOKE_SHARD.get())
                .define('G', Items.GOLD_INGOT)
                .define('O', Items.EMERALD)
                .unlockedBy(getHasName(ModItem.INVOKE_SHARD.get()), has(ModItem.INVOKE_SHARD.get()))
                .save(exporter, Frontiers.id("totem_of_undying"));
        // Totem of Undying
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.TOTEM_OF_AVARICE.get())
                .pattern("X")
                .pattern("E")
                .pattern("#")
                .define('X', Items.DIAMOND)
                .define('E', ModItem.INVOKE_SHARD.get())
                .define('#', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItem.INVOKE_SHARD.get()), has(ModItem.INVOKE_SHARD.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "totem_of_avarice"));
        // Void Pearl
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItem.VOID_PEARL.get())
                .pattern("%%%")
                .pattern("%O%")
                .pattern("%%%")
                .define('%', ModItem.BRIMTAN_NUGGET.get())
                .define('O', Items.ENDER_EYE)
                .unlockedBy(getHasName(ModItem.BRIMTAN_NUGGET.get()), has(ModItem.BRIMTAN_NUGGET.get()))
                .save(exporter);
        // Lumens
        RecipeHelper.createLumen(exporter, Items.AMETHYST_SHARD, ModBlocks.AMETHYST_LUMEN.get(), ModItem.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.DIAMOND, ModBlocks.DIAMOND_LUMEN.get(), ModItem.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.EMERALD, ModBlocks.EMERALD_LUMEN.get(), ModItem.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.QUARTZ, ModBlocks.QUARTZ_LUMEN.get(), ModItem.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.REDSTONE, ModBlocks.REDSTONE_LUMEN.get(), ModItem.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.ECHO_SHARD, ModBlocks.ECHO_LUMEN.get(), Items.ECHO_SHARD);
        RecipeHelper.createLumen(exporter, ModItem.COBALT_INGOT.get(), ModBlocks.COBALT_LUMEN.get(), ModItem.COBALT_INGOT.get());
        RecipeHelper.createLumen(exporter, ModItem.FROSTITE_INGOT.get(), ModBlocks.FROSTITE_LUMEN.get(), ModItem.FROSTITE_INGOT.get());
        RecipeHelper.createLumen(exporter, ModItem.VERDINITE_INGOT.get(), ModBlocks.VERDINITE_LUMEN.get(), ModItem.VERDINITE_INGOT.get());
        RecipeHelper.createLumen(exporter, ModItem.VIVULITE_INGOT.get(), ModBlocks.VIVULITE_LUMEN.get(), ModItem.VIVULITE_INGOT.get());
        RecipeHelper.createLumen(exporter, ModItem.BRIMTAN_NUGGET.get(), ModBlocks.BRIMTAN_LUMEN.get(), ModItem.BRIMTAN_NUGGET.get());
        // All verdinite tools.
        RecipeHelper.toolHelper(exporter, ModItem.VERDINITE_INGOT.get(),
                ModItem.VERDINITE_AXE.get(), ModItem.VERDINITE_SWORD.get(), ModItem.VERDINITE_SHOVEL.get(), ModItem.VERDINITE_PICKAXE.get(), ModItem.VERDINITE_HOE.get()
        );
        // Verdinite Armor
        RecipeHelper.armorHelper(exporter, ModItem.VERDINITE_INGOT.get(),
                ModItem.VERDINITE_HELMET.get(),
                ModItem.VERDINITE_CHESTPLATE.get(),
                ModItem.VERDINITE_LEGGINGS.get(),
                ModItem.VERDINITE_BOOTS.get()
        );
        // All vivulite tools.
        RecipeHelper.toolHelper(exporter, ModItem.VIVULITE_INGOT.get(),
                ModItem.VIVULITE_AXE.get(), ModItem.VIVULITE_SWORD.get(), ModItem.VIVULITE_SHOVEL.get(), ModItem.VIVULITE_PICKAXE.get(), ModItem.VIVULITE_HOE.get()
        );
        // Vivulite Armor
        RecipeHelper.armorHelper(exporter, ModItem.VIVULITE_INGOT.get(),
                ModItem.VIVULITE_HELMET.get(),
                ModItem.VIVULITE_CHESTPLATE.get(),
                ModItem.VIVULITE_LEGGINGS.get(),
                ModItem.VIVULITE_BOOTS.get()
        );
        // Vivulite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.VIVULITE_BLOCK.get().asItem(), ModItem.VIVULITE_INGOT.get());
        // Verdinite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.VERDINITE_BLOCK.get().asItem(), ModItem.VERDINITE_INGOT.get());

        // Sea Glass
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_GLASS.get(), 2)
                .pattern(" P ")
                .pattern("PGP")
                .pattern(" P ")
                .define('P', Items.PRISMARINE_CRYSTALS)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.PRISMARINE_CRYSTALS), has(Items.PRISMARINE_CRYSTALS))
                .save(exporter);
        // Pale Prismarine
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_PRISMARINE.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE)
                .define('X', ModItem.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD.get()), has(ModItem.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Pale Prismarine Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_PRISMARINE_BRICKS.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE_BRICKS)
                .define('X', ModItem.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD.get()), has(ModItem.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Deep Pale Prismarine
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEEP_PALE_PRISMARINE.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.DARK_PRISMARINE)
                .define('X', ModItem.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD.get()), has(ModItem.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Pale Sea Glass
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_SEA_GLASS.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', ModBlocks.SEA_GLASS.get())
                .define('X', ModItem.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(ModItem.PALE_PRISMARINE_SHARD.get()), has(ModItem.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Sea Glass Panes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEA_GLASS_PANE.get(), 16)
                .pattern("##")
                .define('#', ModBlocks.SEA_GLASS.get())
                .unlockedBy(getHasName(ModBlocks.SEA_GLASS.get()), has(ModBlocks.SEA_GLASS.get()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALE_SEA_GLASS_PANE.get(), 16)
                .pattern("##")
                .define('#', ModBlocks.PALE_SEA_GLASS.get())
                .unlockedBy(getHasName(ModBlocks.PALE_SEA_GLASS.get()), has(ModBlocks.PALE_SEA_GLASS.get()))
                .save(exporter);
        // Frostite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.FROSTITE_BLOCK.get().asItem(), ModItem.FROSTITE_INGOT.get());
        // Turtle Scute Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TURTLE_SCUTE_BRICKS.get(), 8)
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XXX")
                .define('E', Items.TURTLE_SCUTE)
                .define('X', Items.BRICK)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE))
                .save(exporter);
        // Hielostone (from Blue ice)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.HIELOSTONE.get())
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XXX")
                .define('X', Items.STONE)
                .define('E', Items.BLUE_ICE)
                .unlockedBy(getHasName(Items.BLUE_ICE), has(Items.BLUE_ICE))
                .save(exporter, Frontiers.id("hielostone_from_blue_ice"));
        // Brimtan Ingot <-> Nugget convertible
        RecipeHelper.createReversible(exporter, ModItem.BRIMTAN_INGOT.get(), ModItem.BRIMTAN_NUGGET.get());
        // All brimtan shells.
        RecipeHelper.toolHelper(exporter, ModItem.BRIMTAN_INGOT.get(),
                ModItem.BRIMTAN_SHELL_AXE.get(),
                ModItem.BRIMTAN_SHELL_SWORD.get(),
                ModItem.BRIMTAN_SHELL_SHOVEL.get(),
                ModItem.BRIMTAN_SHELL_PICKAXE.get(),
                ModItem.BRIMTAN_SHELL_HOE.get()
        );
        // All balls
        RecipeHelper.doBallRecipes(exporter);
        // Vivulite Anvil
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.VIVULITE_ANVIL.get())
                .pattern("XXX")
                .pattern(" E ")
                .pattern("XXX")
                .define('E', ModItem.VIVULITE_INGOT.get())
                .define('X', ModBlocks.VIVULITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.VIVULITE_BLOCK.get()), has(ModBlocks.VIVULITE_BLOCK.get()))
                .save(exporter);
        // Beef Wellington
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.BEEF_WELLINGTON.get())
                .pattern("$$$")
                .pattern("$X$")
                .pattern("$I$")
                .define('$', Items.WHEAT)
                .define('X', ModItem.TRUFFLE.get())
                .define('I', Items.COOKED_BEEF)
                .unlockedBy(getHasName(ModItem.TRUFFLE.get()), has(ModItem.TRUFFLE.get()))
                .save(exporter);
        // Fruitcake
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModBlocks.FRUITCAKE.get())
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
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.TRUFFLE_POTATO_PUFF.get(), 6)
                .pattern(" X ")
                .pattern("$I$")
                .define('$', Items.WHEAT)
                .define('X', ModItem.TRUFFLE.get())
                .define('I', Items.BAKED_POTATO)
                .unlockedBy(getHasName(ModItem.TRUFFLE.get()), has(ModItem.TRUFFLE.get()))
                .save(exporter);
        // Truffle Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItem.TRUFFLE_OIL.get(), 4)
                .requires(ModItem.TRUFFLE.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(ModItem.TRUFFLE.get()), has(ModItem.TRUFFLE.get()))
                .save(exporter);
        // Personal Chest
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PERSONAL_CHEST.get())
                .pattern("#S#")
                .pattern("#C#")
                .pattern("#E#")
                .define('#', Items.COBBLED_DEEPSLATE)
                .define('C', ModItem.LIGHTNING_IN_A_BOTTLE.get())
                .define('S', Items.GOLD_INGOT)
                .define('E', Items.ECHO_SHARD)
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .save(exporter);
        // Chest Key
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.CHEST_KEY.get())
                .pattern(" ##")
                .pattern(" X#")
                .pattern("#  ")
                .define('#', Items.GOLD_INGOT)
                .define('X', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(ModBlocks.PERSONAL_CHEST.get()), has(ModBlocks.PERSONAL_CHEST.get()))
                .save(exporter);
        // Curse Altar
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CURSE_ALTAR.get())
                .pattern(" C ")
                .pattern("V#V")
                .pattern("L#L")
                .define('#', Items.SMOOTH_SANDSTONE)
                .define('C', ModItem.CURSED_TABLET.get())
                .define('V', ModItem.VOID_PEARL.get())
                .define('L', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(ModItem.CURSED_TABLET.get()), has(ModItem.CURSED_TABLET.get()))
                .save(exporter);
        // Brimtan Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, ModBlocks.BRIMTAN_BLOCK.get().asItem(), ModItem.BRIMTAN_INGOT.get());

        // Trim templates
        RecipeHelper.doTemplateDupe(exporter, ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), Blocks.GRANITE.asItem());
        RecipeHelper.doTemplateDupe(exporter, ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), Blocks.SLIME_BLOCK.asItem());
        RecipeHelper.doTemplateDupe(exporter, ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModBlocks.ONYX_BONE_BLOCK.get().asItem());

        // Brimtan Armor Shells
        RecipeHelper.armorHelper(exporter, ModItem.BRIMTAN_INGOT.get(),
                ModItem.BRIMTAN_SHELL_HELMET.get(),
                ModItem.BRIMTAN_SHELL_CHESTPLATE.get(),
                ModItem.BRIMTAN_SHELL_LEGGINGS.get(),
                ModItem.BRIMTAN_SHELL_BOOTS.get()
        );
        // All Brimtan templates
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get());

        // Brimmed Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), 4)
                .pattern(" B ")
                .pattern("B#B")
                .pattern(" B ")
                .define('B', ModBlocks.CRAGULSTANE_BRICKS.get())
                .define('#', ModItem.BRIMTAN_NUGGET.get())
                .unlockedBy(getHasName(ModItem.BRIMTAN_NUGGET.get()), has(ModItem.BRIMTAN_NUGGET.get()))
                .save(exporter);
        // Orange Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BXB")
                .pattern("BBB")
                .define('B', ModBlocks.CRAGULSTANE_BRICKS.get())
                .define('X', Items.LAVA_BUCKET)
                .unlockedBy(getHasName(ModBlocks.CRAGULSTANE_BRICKS.get()), has(ModBlocks.CRAGULSTANE_BRICKS.get()))
                .save(exporter);
        // Tyrian Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), 5)
                .pattern("B#B")
                .pattern("#BO")
                .pattern("BOB")
                .define('B', ModBlocks.CRAGULSTANE_BRICKS.get())
                .define('#', Items.NETHER_WART)
                .define('O', ModItem.WARPED_WART.get())
                .unlockedBy(getHasName(ModBlocks.CRAGULSTANE_BRICKS.get()), has(ModBlocks.CRAGULSTANE_BRICKS.get()))
                .save(exporter);
        // Pale Trident
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.PALE_TRIDENT.get())
                .pattern("XXX")
                .pattern("#T#")
                .pattern(" # ")
                .define('T', Items.TRIDENT)
                .define('#', ModItem.PALE_PRISMARINE_SHARD.get())
                .define('X', ModItem.ELDER_GUARDIAN_SPINE.get())
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SPINE.get()), has(ModItem.ELDER_GUARDIAN_SPINE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "pale_trident"));

        // Glistering Melon <-> Slices convertible
        RecipeHelper.createReversible(exporter, ModBlocks.GLISTERING_MELON.get().asItem(), Items.GLISTERING_MELON_SLICE);
        // June o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNE_O_LANTERN.get())
                .define('A', ModBlocks.CARVED_MELON.get())
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(ModBlocks.CARVED_MELON.get()))
                .save(exporter);
        // Glistering o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLISTERING_JUNE_O_LANTERN.get())
                .define('A', ModBlocks.CARVED_GLISTERING_MELON.get())
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(ModBlocks.CARVED_GLISTERING_MELON.get()))
                .save(exporter);
        // White Pumpkin
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModBlocks.WHITE_PUMPKIN.get())
                .define('X', ModItem.ECTOPLASM.get())
                .define('P', Blocks.CARVED_PUMPKIN)
                .pattern(" X ")
                .pattern("XPX")
                .pattern(" X ")
                .unlockedBy(getHasName(ModItem.ECTOPLASM.get()), has(ModItem.ECTOPLASM.get()))
                .save(exporter);
        // White Jack o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_JACK_O_LANTERN.get())
                .define('A', ModBlocks.WHITE_PUMPKIN.get())
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(ModBlocks.WHITE_PUMPKIN.get()))
                .save(exporter);
        // Slime Shoes
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.SLIME_SHOES.get())
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', ModItem.HARDENED_SLIME.get())
                .unlockedBy(getHasName(ModItem.HARDENED_SLIME.get()), has(ModItem.HARDENED_SLIME.get()))
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
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItem.SPIRIT_CANDLE.get(), 2)
                .pattern("   ")
                .pattern(" $ ")
                .pattern("#%#")
                .define('$', Items.STRING)
                .define('#', Items.HONEYCOMB)
                .define('%', ModItem.INCENSE.get())
                .unlockedBy(getHasName(ModItem.INCENSE.get()), has(ModItem.INCENSE.get()))
                .save(exporter);
        // Raw ore convertibles
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_COBALT_BLOCK.get().asItem(), ModItem.RAW_COBALT.get());
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_VERDINITE_BLOCK.get().asItem(), ModItem.RAW_VERDINITE.get());
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_VIVULITE_BLOCK.get().asItem(), ModItem.RAW_VIVULITE.get());
        RecipeHelper.createReversible(exporter, ModBlocks.RAW_FROSTITE_BLOCK.get().asItem(), ModItem.RAW_FROSTITE.get());

        // Verdinite Horse Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.VERDINITE_HORSE_ARMOR.get())
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .define('H', ModItem.COBALT_HORSE_ARMOR.get())
                .define('#', ModItem.VERDINITE_INGOT.get())
                .unlockedBy(getHasName(ModItem.VERDINITE_INGOT.get()), has(ModItem.VERDINITE_INGOT.get()))
                .save(exporter);
        // Vivulite Horse Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItem.VIVULITE_HORSE_ARMOR.get())
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .define('H', ModItem.VERDINITE_HORSE_ARMOR.get())
                .define('#', ModItem.VIVULITE_INGOT.get())
                .unlockedBy(getHasName(ModItem.VIVULITE_INGOT.get()), has(ModItem.VIVULITE_INGOT.get()))
                .save(exporter);

        // Phantasmic TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.PHANTASMIC_TNT.get())
                .pattern("#X#")
                .pattern("XSX")
                .pattern("#X#")
                .define('#', Items.GUNPOWDER)
                .define('X', ItemTags.SAND)
                .define('S', ModItem.SOUL.get())
                .unlockedBy(getHasName(ModItem.SOUL.get()), has(ModItem.SOUL.get()))
                .save(exporter);

        // Snow Melt
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItem.SNOW_MELT.get(), 2)
                .requires(ModItem.FROST_BONE.get())
                .requires(Items.LEATHER)
                .unlockedBy(getHasName(ModItem.FROST_BONE.get()), has(ModItem.FROST_BONE.get()))
                .save(exporter);
        // Message in a Bottle
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, ModItem.MESSAGE_IN_A_BOTTLE.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.PAPER)
                .requires(Items.INK_SAC)
                .unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                .save(exporter);

        // Monster Bakery
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MONSTER_BAKERY.get())
                .pattern("#*#")
                .pattern("*&*")
                .pattern("#*#")
                .define('&', ModItem.INCENSE.get())
                .define('#', ModItem.SPAWNER_CHUNK.get())
                .define('*', Items.DIAMOND)
                .unlockedBy(getHasName(ModItem.SPAWNER_CHUNK.get()), has(ModItem.SPAWNER_CHUNK.get()))
                .save(exporter);

        // Phantom-Stitch Bed
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItem.PHANTOM_STITCH_BED.get())
                .pattern("###")
                .pattern("XXX")
                .define('#', Items.PHANTOM_MEMBRANE)
                .define('X', ItemTags.PLANKS)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(exporter);

        // Enchanting Magnet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENCHANTING_MAGNET.get())
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
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ITEM_VACUUM.get())
                .pattern("#C#")
                .pattern("# #")
                .pattern(" $ ")
                .define('#', Items.IRON_BARS)
                .define('$', Items.HOPPER)
                .define('C', ModItem.SPAWNER_CHUNK.get())
                .unlockedBy(getHasName(ModItem.SPAWNER_CHUNK.get()), has(ModItem.SPAWNER_CHUNK.get()))
                .save(exporter);

        // Eboncork Blocks
        planksFromLogs(exporter, ModBlocks.EBONCORK_PLANKS.get(), ModTags.Items.EBONCORK_LOGS, 2);
        stairBuilder(ModBlocks.EBONCORK_STAIRS.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_stairs")
                .save(exporter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EBONCORK_SLAB.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_slab")
                .save(exporter);
        fenceBuilder(ModBlocks.EBONCORK_FENCE.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_fence")
                .save(exporter);
        fenceGateBuilder(ModBlocks.EBONCORK_FENCE_GATE.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(exporter);
        buttonBuilder(ModBlocks.EBONCORK_BUTTON.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_button")
                .save(exporter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.EBONCORK_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(exporter);
        doorBuilder(ModBlocks.EBONCORK_DOOR.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_door")
                .save(exporter);
        trapdoorBuilder(ModBlocks.EBONCORK_TRAPDOOR.get(), Ingredient.of(ModBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.EBONCORK_PLANKS.get()), has(ModBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(exporter);


        // Blighted Birch Blocks
        woodFromLogs(exporter, ModBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());
        woodFromLogs(exporter, ModBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        woodFromLogs(exporter, ModBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), ModBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
        planksFromLogs(exporter, ModBlocks.BLIGHTED_BIRCH_PLANKS.get(), ModTags.Items.BLIGHTED_BIRCH_LOGS, 4);

        stairBuilder(ModBlocks.BLIGHTED_BIRCH_STAIRS.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_stairs")
                .save(exporter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLIGHTED_BIRCH_SLAB.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_slab")
                .save(exporter);
        fenceBuilder(ModBlocks.BLIGHTED_BIRCH_FENCE.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_fence")
                .save(exporter);
        fenceGateBuilder(ModBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(exporter);
        buttonBuilder(ModBlocks.BLIGHTED_BIRCH_BUTTON.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_button")
                .save(exporter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(exporter);
        doorBuilder(ModBlocks.BLIGHTED_BIRCH_DOOR.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_door")
                .save(exporter);
        trapdoorBuilder(ModBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), Ingredient.of(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(ModBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(exporter);

        // Wreaths
        RecipeHelper.createWreath(exporter, Items.OAK_LEAVES, Items.POPPY, ModBlocks.OAK_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.DARK_OAK_LEAVES, Items.RED_MUSHROOM, ModBlocks.DARK_OAK_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.BIRCH_LEAVES, Items.DANDELION, ModBlocks.BIRCH_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.SPRUCE_LEAVES, Items.SWEET_BERRIES, ModBlocks.SPRUCE_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.JUNGLE_LEAVES, Items.COCOA_BEANS, ModBlocks.JUNGLE_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.ACACIA_LEAVES, Items.SHORT_GRASS, ModBlocks.ACACIA_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.MANGROVE_LEAVES, Items.BLUE_ORCHID, ModBlocks.MANGROVE_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.FLOWERING_AZALEA_LEAVES, Items.GLOW_BERRIES, ModBlocks.AZALEA_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.CHERRY_LEAVES, Items.PINK_PETALS, ModBlocks.CHERRY_WREATH.get());
        RecipeHelper.createWreath(exporter, ModBlocks.BLIGHTED_BIRCH_LEAVES.get().asItem(), Items.SPIDER_EYE, ModBlocks.BLIGHTED_BIRCH_WREATH.get());

        // Sugar Cane Block <-> Cane convertible
        RecipeHelper.createReversible(exporter, ModBlocks.SUGAR_CANE_BLOCK.get().asItem(), Items.SUGAR_CANE);

        // Cocoa Bean Block <-> Beans convertible
        RecipeHelper.createReversible(exporter, ModBlocks.COCOA_BEAN_BLOCK.get().asItem(), Items.COCOA_BEANS);

        // TEMP APPLE OF ENLIGHTENMENT RECIPE!
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItem.APPLE_OF_ENLIGHTENMENT.get())
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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_COBALT.get()), RecipeCategory.MISC, ModItem.COBALT_INGOT.get(), 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModItem.RAW_COBALT.get()), has(ModItem.RAW_COBALT.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_raw_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.COBALT_ORE.get()), RecipeCategory.MISC, ModItem.COBALT_INGOT.get(), 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.COBALT_ORE.get()), has(ModBlocks.COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_cobalt_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_COBALT_ORE.get()), RecipeCategory.MISC, ModItem.COBALT_INGOT.get(), 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_COBALT_ORE.get()), has(ModBlocks.DEEPSLATE_COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_deepslate_cobalt_ore"));

        // Verdinite Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_VERDINITE.get()), RecipeCategory.MISC, ModItem.VERDINITE_INGOT.get(), 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VERDINITE.get()), has(ModItem.RAW_VERDINITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_raw_verdinite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.VERDINITE_ORE.get()), RecipeCategory.MISC, ModItem.VERDINITE_INGOT.get(), 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.VERDINITE_ORE.get()), has(ModBlocks.VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_verdinite_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_VERDINITE_ORE.get()), RecipeCategory.MISC, ModItem.VERDINITE_INGOT.get(), 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VERDINITE_ORE.get()), has(ModBlocks.DEEPSLATE_VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_deepslate_verdinite_ore"));

        // Raw Frostite
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_FROSTITE.get()), RecipeCategory.MISC, ModItem.FROSTITE_INGOT.get(), 2.0f, 200)
                .group("frostite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_FROSTITE.get()), has(ModItem.RAW_FROSTITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frostite_ingot_from_smelting_raw_frostite"));

        // Vivulite ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.RAW_VIVULITE.get()), RecipeCategory.MISC, ModItem.VIVULITE_INGOT.get(), 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VIVULITE.get()), has(ModItem.RAW_VIVULITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_raw_vivulite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.VIVULITE_ORE.get()), RecipeCategory.MISC, ModItem.VIVULITE_INGOT.get(), 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.VIVULITE_ORE.get()), has(ModBlocks.VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_vivulite_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_VIVULITE_ORE.get()), RecipeCategory.MISC, ModItem.VIVULITE_INGOT.get(), 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VIVULITE_ORE.get()), has(ModBlocks.DEEPSLATE_VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_deepslate_vivulite_ore"));

        // Black Emerald
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, ModItem.BLACK_EMERALD.get(), 1f, 200)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.BLACK_EMERALD_ORE.get()), has(ModBlocks.BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_smelting_black_emerald_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, ModItem.BLACK_EMERALD.get(), 1f, 200)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), has(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_smelting_deepslate_black_emerald_ore"));

        // Brimtan Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.BRIMTAN_CLUSTER.get()), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET.get(), 1.4f, 400)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModItem.BRIMTAN_CLUSTER.get()), has(ModItem.BRIMTAN_CLUSTER.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_cluster"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.BRIMTAN_ORE.get()), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET.get(), 1.4f, 400)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModBlocks.BRIMTAN_ORE.get()), has(ModBlocks.BRIMTAN_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_ore"));

        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.MARSHMALLOW.get()), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW.get(), 0.2f, 100)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(ModItem.MARSHMALLOW.get()), has(ModItem.MARSHMALLOW.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_smelting"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.GUARDIAN_SLICE.get()), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItem.GUARDIAN_SLICE.get()), has(ModItem.GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_smelting"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.ELDER_GUARDIAN_SLICE.get()), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE.get(), 0.35f, 200)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SLICE.get()), has(ModItem.ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smelting"));

        // Nacre Brick
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.SHULKER_RESIDUE.get()), RecipeCategory.MISC, ModItem.NACRE_BRICK.get(), 0.3f, 200)
                .group("nacre_brick")
                .unlockedBy(getHasName(ModItem.SHULKER_RESIDUE.get()), has(ModItem.SHULKER_RESIDUE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "nacre_brick_smelting"));

        // Hielostone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.COBBLEFROST.get()), RecipeCategory.MISC, ModBlocks.HIELOSTONE.get(), 0.1f, 200)
                .group("hielostone")
                .unlockedBy(getHasName(ModBlocks.COBBLEFROST.get()), has(ModBlocks.COBBLEFROST.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hielostone_smelting"));
        // Stone from smelting Hielostone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.HIELOSTONE.get()), RecipeCategory.MISC, Blocks.STONE, 0.1f, 200)
                .group("stone")
                .unlockedBy(getHasName(ModBlocks.HIELOSTONE.get()), has(ModBlocks.HIELOSTONE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "stone_from_smelting_hielostone"));

        // Bone from Frost Bone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.FROST_BONE.get()), RecipeCategory.MISC, Items.BONE, 0.3f, 200)
                .group("frost_bone")
                .unlockedBy(getHasName(ModItem.FROST_BONE.get()), has(ModItem.FROST_BONE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frost_bone_smelting"));

        // Cracked bricks
        RecipeHelper.createCrackedBrick(exporter, Blocks.RED_NETHER_BRICKS, ModBlocks.CRACKED_RED_NETHER_BRICKS.get(), "cracked_red_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), "cracked_blue_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), "cracked_purple_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), "cracked_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), "cracked_brimmed_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), "cracked_orange_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get(), "cracked_tyrian_cragulstane_bricks");
    }

    // Blast Furn recipes
    // One by one ecause offerBlasting has been nothing but horrible to work with
    private void blasting(RecipeOutput exporter)
    {
        // Cobalt Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_COBALT.get()), RecipeCategory.MISC, ModItem.COBALT_INGOT.get(), 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModItem.RAW_COBALT.get()), has(ModItem.RAW_COBALT.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_raw_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.COBALT_ORE.get()), RecipeCategory.MISC, ModItem.COBALT_INGOT.get(), 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.COBALT_ORE.get()), has(ModBlocks.COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_cobalt_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_COBALT_ORE.get()), RecipeCategory.MISC, ModItem.COBALT_INGOT.get(), 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_COBALT_ORE.get()), has(ModBlocks.DEEPSLATE_COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_deepslate_cobalt_ore"));

        // Verdinite Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_VERDINITE.get()), RecipeCategory.MISC, ModItem.VERDINITE_INGOT.get(), 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VERDINITE.get()), has(ModItem.RAW_VERDINITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_raw_verdinite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.VERDINITE_ORE.get()), RecipeCategory.MISC, ModItem.VERDINITE_INGOT.get(), 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.VERDINITE_ORE.get()), has(ModBlocks.VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_verdinite_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_VERDINITE_ORE.get()), RecipeCategory.MISC, ModItem.VERDINITE_INGOT.get(), 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VERDINITE_ORE.get()), has(ModBlocks.DEEPSLATE_VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_deepslate_verdinite_ore"));

        // Raw Frostite
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_FROSTITE.get()), RecipeCategory.MISC, ModItem.FROSTITE_INGOT.get(), 2.0f, 100)
                .group("frostite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_FROSTITE.get()), has(ModItem.RAW_FROSTITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frostite_ingot_from_blasting_raw_frostite"));

        // Vivulite Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.RAW_VIVULITE.get()), RecipeCategory.MISC, ModItem.VIVULITE_INGOT.get(), 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModItem.RAW_VIVULITE.get()), has(ModItem.RAW_VIVULITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_raw_vivulite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.VIVULITE_ORE.get()), RecipeCategory.MISC, ModItem.VIVULITE_INGOT.get(), 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.VIVULITE_ORE.get()), has(ModBlocks.VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_vivulite_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_VIVULITE_ORE.get()), RecipeCategory.MISC, ModItem.VIVULITE_INGOT.get(), 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_VIVULITE_ORE.get()), has(ModBlocks.DEEPSLATE_VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_deepslate_vivulite_ore"));

        // Black Emerald
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, ModItem.BLACK_EMERALD.get(), 1f, 100)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.BLACK_EMERALD_ORE.get()), has(ModBlocks.BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_blasting_black_emerald_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, ModItem.BLACK_EMERALD.get(), 1f, 100)
                .group("black_emerald")
                .unlockedBy(getHasName(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), has(ModBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_blasting_deepslate_black_emerald_ore"));

        // Brimtan Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModItem.BRIMTAN_CLUSTER.get()), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET.get(), 1.4f, 200)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModItem.BRIMTAN_CLUSTER.get()), has(ModItem.BRIMTAN_CLUSTER.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_cluster"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.BRIMTAN_ORE.get()), RecipeCategory.MISC, ModItem.BRIMTAN_NUGGET.get(), 1.4f, 200)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(ModBlocks.BRIMTAN_ORE.get()), has(ModBlocks.BRIMTAN_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_ore"));
    }

    // Smoker recipes
    private void smoking(RecipeOutput exporter)
    {
        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.MARSHMALLOW.get()), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW.get(), 0.2f, 50)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(ModItem.MARSHMALLOW.get()), has(ModItem.MARSHMALLOW.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_smoking"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.GUARDIAN_SLICE.get()), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItem.GUARDIAN_SLICE.get()), has(ModItem.GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_smoking"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.ELDER_GUARDIAN_SLICE.get()), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE.get(), 0.35f, 100)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SLICE.get()), has(ModItem.ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smoking"));
    }

    // Campfire recipes
    private void campfire(RecipeOutput exporter)
    {
        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.MARSHMALLOW.get()), RecipeCategory.FOOD, ModItem.ROASTED_MARSHMALLOW.get(), 0.0f, 300)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(ModItem.MARSHMALLOW.get()), has(ModItem.MARSHMALLOW.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_campfire"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.GUARDIAN_SLICE.get()), RecipeCategory.FOOD, ModItem.COOKED_GUARDIAN_SLICE.get(), 0.0f, 600)
                .unlockedBy(getHasName(ModItem.GUARDIAN_SLICE.get()), has(ModItem.GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_campfire"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.ELDER_GUARDIAN_SLICE.get()), RecipeCategory.FOOD, ModItem.COOKED_ELDER_GUARDIAN_SLICE.get(), 0.0f, 600)
                .unlockedBy(getHasName(ModItem.ELDER_GUARDIAN_SLICE.get()), has(ModItem.ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_campfire"));
    }

    // Smithing recipes
    private void smithing(RecipeOutput exporter)
    {
        // TRIMS
        RecipeHelper.streamCustomTemplates().forEach(template -> trimSmithing(exporter, template.template(), template.id()));

        // Obsidian items.
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.OBSIDIAN_CASING.get(),
                Items.GOLDEN_SWORD, RecipeCategory.COMBAT, ModItem.OBSIDIAN_SWORD.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.OBSIDIAN_CASING.get(),
                Items.GOLDEN_AXE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_AXE.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.OBSIDIAN_CASING.get(),
                Items.GOLDEN_PICKAXE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_PICKAXE.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.OBSIDIAN_CASING.get(),
                Items.GOLDEN_HOE, RecipeCategory.TOOLS, ModItem.OBSIDIAN_HOE.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.OBSIDIAN_CASING.get(),
                Items.GOLDEN_SHOVEL, RecipeCategory.TOOLS, ModItem.OBSIDIAN_SHOVEL.get());

        // Brimtan Items
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_SWORD.get(),
                ModItem.VIVULITE_SWORD.get(), RecipeCategory.COMBAT, ModItem.BRIMTAN_SWORD.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_SHOVEL.get(),
                ModItem.VIVULITE_SHOVEL.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_SHOVEL.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_PICKAXE.get(),
                ModItem.VIVULITE_PICKAXE.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_PICKAXE.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_AXE.get(),
                ModItem.VIVULITE_AXE.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_AXE.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_HOE.get(),
                ModItem.VIVULITE_HOE.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_HOE.get());

        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_HELMET.get(),
                ModItem.VIVULITE_HELMET.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_HELMET.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_CHESTPLATE.get(),
                ModItem.VIVULITE_CHESTPLATE.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_CHESTPLATE.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_LEGGINGS.get(),
                ModItem.VIVULITE_LEGGINGS.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_LEGGINGS.get());
        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.BRIMTAN_SHELL_BOOTS.get(),
                ModItem.VIVULITE_BOOTS.get(), RecipeCategory.TOOLS, ModItem.BRIMTAN_BOOTS.get());
    }

    // Stonecutting recipes
    private void stonecutting(RecipeOutput exporter)
    {
        // Stone-Like Bricks
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.CRAGULSTANE.get(), ModBlocks.CRAGULSTANE_BRICKS.get(), "cragulstane_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.HIELOSTONE.get(), ModBlocks.HIELOSTONE_BRICKS.get(), "hielostone_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS.get(), ModBlocks.HIELOSTONE_TILES.get(), "hielostone_tiles");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.HIELOSTONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.HIELOSTONE_TILES.get())
                .unlockedBy(getHasName(ModBlocks.HIELOSTONE.get()), has(ModBlocks.HIELOSTONE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hielostone_tiles_from_stonecutting_hielostone"));

        // Stairs (also handles crafting table)
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_STAIRS.get(), "blue_nether_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), "purple_nether_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS.get(), ModBlocks.CRAGULSTANE_BRICK_STAIRS.get(), "cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), "brimmed_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), "orange_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), "tyrian_cragulstane_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.NACRE_BRICKS.get(), ModBlocks.NACRE_BRICK_STAIRS.get(), "nacre_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TOWER_BRICKS.get(), ModBlocks.TOWER_BRICK_STAIRS.get(), "tower_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.MOSSY_TOWER_BRICKS.get(), ModBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), "mossy_tower_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PALE_PRISMARINE.get(), ModBlocks.PALE_PRISMARINE_STAIRS.get(), "pale_prismarine_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.PALE_PRISMARINE_BRICKS.get(), ModBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), "pale_prismarine_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.DEEP_PALE_PRISMARINE.get(), ModBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), "deep_pale_prismarine_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.TURTLE_SCUTE_BRICKS.get(), ModBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), "turtle_scute_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE.get(), ModBlocks.HIELOSTONE_STAIRS.get(), "hielostone_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS.get(), ModBlocks.HIELOSTONE_BRICK_STAIRS.get(), "hielostone_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE_TILES.get(), ModBlocks.HIELOSTONE_TILE_STAIRS.get(), "hielostone_tile_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.HIELOSTONE_PLATES.get(), ModBlocks.HIELOSTONE_PLATE_STAIRS.get(), "hielostone_plate_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, ModBlocks.COBBLEFROST.get(), ModBlocks.COBBLEFROST_STAIRS.get(), "cobblefrost_stairs");

        // Slabs (also handles crafting table)
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_SLAB.get(), "blue_nether_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICK_SLAB.get(), "purple_nether_brick_slab");

        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS.get(), ModBlocks.CRAGULSTANE_BRICK_SLAB.get(), "cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), "brimmed_cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), "orange_cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), "tyrian_cragulstane_brick_slab");

        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.NACRE_BRICKS.get(), ModBlocks.NACRE_BRICK_SLAB.get(), "nacre_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.TOWER_BRICKS.get(), ModBlocks.TOWER_BRICK_SLAB.get(), "tower_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.MOSSY_TOWER_BRICKS.get(), ModBlocks.MOSSY_TOWER_BRICK_SLAB.get(), "mossy_tower_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.PALE_PRISMARINE.get(), ModBlocks.PALE_PRISMARINE_SLAB.get(), "pale_prismarine_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.PALE_PRISMARINE_BRICKS.get(), ModBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), "pale_prismarine_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.DEEP_PALE_PRISMARINE.get(), ModBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), "deep_pale_prismarine_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.TURTLE_SCUTE_BRICKS.get(), ModBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), "turtle_scute_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE.get(), ModBlocks.HIELOSTONE_SLAB.get(), "hielostone_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS.get(), ModBlocks.HIELOSTONE_BRICK_SLAB.get(), "hielostone_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE_TILES.get(), ModBlocks.HIELOSTONE_TILE_SLAB.get(), "hielostone_tile_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.HIELOSTONE_PLATES.get(), ModBlocks.HIELOSTONE_PLATE_SLAB.get(), "hielostone_plate_slab");
        RecipeHelper.createSlabBothRecipes(exporter, ModBlocks.COBBLEFROST.get(), ModBlocks.COBBLEFROST_SLAB.get(), "cobblefrost_slab");

        // Walls (also handles crafting table)
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_WALL.get(), "blue_nether_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICK_WALL.get(), "purple_nether_brick_wall");

        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.CRAGULSTANE_BRICKS.get(), ModBlocks.CRAGULSTANE_BRICK_WALL.get(), "cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), "brimmed_cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), "orange_cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), "tyrian_cragulstane_brick_wall");

        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.NACRE_BRICKS.get(), ModBlocks.NACRE_BRICK_WALL.get(), "nacre_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.TOWER_BRICKS.get(), ModBlocks.TOWER_BRICK_WALL.get(), "tower_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.MOSSY_TOWER_BRICKS.get(), ModBlocks.MOSSY_TOWER_BRICK_WALL.get(), "mossy_tower_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.PALE_PRISMARINE.get(), ModBlocks.PALE_PRISMARINE_WALL.get(), "pale_prismarine_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.TURTLE_SCUTE_BRICKS.get(), ModBlocks.TURTLE_SCUTE_BRICK_WALL.get(), "turtle_scute_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE.get(), ModBlocks.HIELOSTONE_WALL.get(), "hielostone_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE_BRICKS.get(), ModBlocks.HIELOSTONE_BRICK_WALL.get(), "hielostone_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE_TILES.get(), ModBlocks.HIELOSTONE_TILE_WALL.get(), "hielostone_tile_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.HIELOSTONE_PLATES.get(), ModBlocks.HIELOSTONE_PLATE_WALL.get(), "hielostone_plate_wall");
        RecipeHelper.createWallBothRecipes(exporter, ModBlocks.COBBLEFROST.get(), ModBlocks.COBBLEFROST_WALL.get(), "cobblefrost_wall");

        // Chisels (also handles crafting table)
        RecipeHelper.createChiselRecipesMulti(exporter, ModBlocks.CRAGULSTANE.get(),
                ModBlocks.CRAGULSTANE_BRICKS.get(),
                ModBlocks.CRAGULSTANE_BRICK_SLAB.get(),
                ModBlocks.CHISELED_CRAGULSTANE_BRICKS.get(),
                "chiseled_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), ModBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(),
                ModBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(),
                "chiseled_brimmed_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), ModBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(),
                ModBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(),
                "chiseled_orange_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), ModBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(),
                ModBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(),
                "chiseled_tyrian_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB,
                ModBlocks.CHISELED_RED_NETHER_BRICKS.get(),
                "chiseled_red_nether_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.BLUE_NETHER_BRICKS.get(), ModBlocks.BLUE_NETHER_BRICK_SLAB.get(),
                ModBlocks.CHISELED_BLUE_NETHER_BRICKS.get(),
                "chiseled_blue_nether_bricks");
        RecipeHelper.createChiselRecipes(exporter, ModBlocks.PURPLE_NETHER_BRICKS.get(), ModBlocks.PURPLE_NETHER_BRICK_SLAB.get(),
                ModBlocks.CHISELED_PURPLE_NETHER_BRICKS.get(),
                "chiseled_purple_nether_bricks");
        RecipeHelper.createChiselRecipesMulti(exporter, ModBlocks.HIELOSTONE.get(),
                ModBlocks.HIELOSTONE_BRICKS.get(),
                ModBlocks.HIELOSTONE_BRICK_SLAB.get(),
                ModBlocks.HIELOSTONE_PLATES.get(),
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
                        ModItem.WARP_ARROW_ARROWHEAD.get(),
                        ModItem.WARP_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/warp_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.WARP_ARROW_ARROWHEAD.get()), has(ModItem.WARP_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Bouncy Arrow
        FletchingRecipeBuilder.create(
                        ModItem.BOUNCY_ARROW_ARROWHEAD.get(),
                        ModItem.BOUNCY_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/bouncy_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.BOUNCY_ARROW_ARROWHEAD.get()), has(ModItem.BOUNCY_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Subzero Arrow
        FletchingRecipeBuilder.create(
                        ModItem.SUBZERO_ARROW_ARROWHEAD.get(),
                        ModItem.SUBZERO_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/subzero_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.SUBZERO_ARROW_ARROWHEAD.get()), has(ModItem.SUBZERO_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Spectral Arrow
        FletchingRecipeBuilder.create(
                        ModItem.SPECTRAL_ARROW_ARROWHEAD.get(),
                        Items.SPECTRAL_ARROW,
                        ResourceLocation.withDefaultNamespace("textures/entity/projectiles/spectral_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.SPECTRAL_ARROW_ARROWHEAD.get()), has(ModItem.SPECTRAL_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Dynamite Arrow
        FletchingRecipeBuilder.create(
                        ModItem.DYNAMITE_ARROW_ARROWHEAD.get(),
                        ModItem.DYNAMITE_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/dynamite_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.DYNAMITE_ARROW_ARROWHEAD.get()), has(ModItem.DYNAMITE_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Prismarine Arrow
        FletchingRecipeBuilder.create(
                        ModItem.PRISMARINE_ARROW_ARROWHEAD.get(),
                        ModItem.PRISMARINE_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/prismarine_arrow.png")
                )
                .unlockedBy(getHasName(ModItem.PRISMARINE_ARROW_ARROWHEAD.get()), has(ModItem.PRISMARINE_ARROW_ARROWHEAD.get()))
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
