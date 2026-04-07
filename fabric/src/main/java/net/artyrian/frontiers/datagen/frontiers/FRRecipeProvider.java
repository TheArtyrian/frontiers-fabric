package net.artyrian.frontiers.datagen.frontiers;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.datagen.bfares.BFRecipeProvider;
import net.artyrian.frontiers.datagen.fd.FDRecipeProvider;
import net.artyrian.frontiers.definition.recipe.fletching.FletchingRecipeBuilder;
import net.artyrian.frontiers.definition.recipe.special.CobaltShieldDecorationRecipe;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.content.FRTags;
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
public class FRRecipeProvider extends FabricRecipeProvider
{
    public FRRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // Crafting recipes
    private void crafting(RecipeOutput exporter)
    {
        // Obsidian Smithing Upgrade
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .pattern("#S#")
                .pattern("#C#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('C', Items.OBSIDIAN)
                .define('S', FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get())
                .unlockedBy(getHasName(FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get()), has(FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get()))
                .save(exporter);
        // Obsidian Casing
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRItems.OBSIDIAN_CASING.get())
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
        RecipeHelper.createReversible(exporter, FRBlocks.COBALT_BLOCK.get().asItem(), FRItems.COBALT_INGOT.get());
        // Cobalt Fishing Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FRItems.COBALT_FISHING_ROD.get())
                .pattern("  /")
                .pattern(" /S")
                .pattern("/ S")
                .define('/', FRItems.COBALT_INGOT.get())
                .define('S', Items.STRING)
                .unlockedBy(getHasName(FRItems.COBALT_INGOT.get()), has(FRItems.COBALT_INGOT.get()))
                .save(exporter);
        // Cobalt Shield
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FRItems.COBALT_SHIELD.get())
                .pattern("CGC")
                .pattern("CCC")
                .pattern(" C ")
                .define('C', FRItems.COBALT_INGOT.get())
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(FRItems.COBALT_INGOT.get()), has(FRItems.COBALT_INGOT.get()))
                .save(exporter);
        // Cobalt Grilles
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRBlocks.COBALT_GRILLES.get(), 24)
                .define('#', FRItems.COBALT_INGOT.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(FRItems.COBALT_INGOT.get()), has(FRItems.COBALT_INGOT.get()))
                .save(exporter);
        // All cobalt tools.
        RecipeHelper.toolHelper(exporter, FRItems.COBALT_INGOT.get(),
                FRItems.COBALT_AXE.get(), FRItems.COBALT_SWORD.get(), FRItems.COBALT_SHOVEL.get(), FRItems.COBALT_PICKAXE.get(), FRItems.COBALT_HOE.get()
        );
        // Ancient Rose Bush
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRBlocks.ANCIENT_ROSE_BUSH.get())
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', FRBlocks.ANCIENT_ROSE.get())
                .define('X', Items.ROSE_BUSH)
                .unlockedBy(getHasName(FRBlocks.ANCIENT_ROSE.get()), has(FRBlocks.ANCIENT_ROSE.get()))
                .save(exporter);
        // Violet Rose Bush
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRBlocks.VIOLET_ROSE_BUSH.get())
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', FRBlocks.VIOLET_ROSE.get())
                .define('X', Items.ROSE_BUSH)
                .unlockedBy(getHasName(FRBlocks.VIOLET_ROSE.get()), has(FRBlocks.VIOLET_ROSE.get()))
                .save(exporter);
        // Cyan Dye from Ancient Rose Bush
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_DYE, 2)
                .requires(FRBlocks.ANCIENT_ROSE_BUSH.get())
                .unlockedBy(getHasName(FRBlocks.ANCIENT_ROSE_BUSH.get()), has(FRBlocks.ANCIENT_ROSE_BUSH.get()))
                .group("cyan_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose_bush"));
        // Cyan Dye from Ancient Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.CYAN_DYE)
                .requires(FRBlocks.ANCIENT_ROSE.get())
                .unlockedBy(getHasName(FRBlocks.ANCIENT_ROSE.get()), has(FRBlocks.ANCIENT_ROSE.get()))
                .group("cyan_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cyan_dye_from_ancient_rose"));
        // Purple Dye from Violet Rose Bush
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE, 2)
                .requires(FRBlocks.VIOLET_ROSE_BUSH.get())
                .unlockedBy(getHasName(FRBlocks.VIOLET_ROSE_BUSH.get()), has(FRBlocks.VIOLET_ROSE_BUSH.get()))
                .group("purple_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "purple_dye_from_violet_rose_bush"));
        // Purple Dye from Violet Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE)
                .requires(FRBlocks.VIOLET_ROSE.get())
                .unlockedBy(getHasName(FRBlocks.VIOLET_ROSE.get()), has(FRBlocks.VIOLET_ROSE.get()))
                .group("purple_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "purple_dye_from_violet_rose"));
        // Red Dye from Rose
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.RED_DYE)
                .requires(FRBlocks.ROSE.get())
                .unlockedBy(getHasName(FRBlocks.ROSE.get()), has(FRBlocks.ROSE.get()))
                .group("red_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "red_dye_from_rose"));
        // Onyx Bones from Withered Essence
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.ONYX_BONE.get(), 4)
                .pattern(" $ ")
                .pattern("$X$")
                .pattern(" $ ")
                .define('$', Items.BONE)
                .define('X', FRItems.WITHERED_ESSENCE.get())
                .unlockedBy(getHasName(FRItems.WITHERED_ESSENCE.get()), has(FRItems.WITHERED_ESSENCE.get()))
                .save(exporter);
        // Necro Weave
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.NECRO_WEAVE.get())
                .pattern("X$ ")
                .pattern("$X ")
                .pattern("   ")
                .define('$', ItemTags.WOOL)
                .define('X', FRItems.ONYX_BONE.get())
                .group("necro_weave")
                .unlockedBy(getHasName(FRItems.ONYX_BONE.get()), has(FRItems.ONYX_BONE.get()))
                .save(exporter);
        // Necro Rug
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRBlocks.NECRO_RUG.get(), 4)
                .pattern("XX")
                .define('X', FRItems.NECRO_WEAVE.get())
                .unlockedBy(getHasName(FRItems.NECRO_WEAVE.get()), has(FRItems.NECRO_WEAVE.get()))
                .save(exporter);
        // Necro Weave Block <-> Necro Weave convertible
        RecipeHelper.createReversibleWithItemGroup(exporter, FRBlocks.NECRO_WEAVE_BLOCK.get().asItem(), FRItems.NECRO_WEAVE.get(), "necro_weave");
        // Mourning Gold Ingot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.MOURNING_GOLD_INGOT.get(), 2)
                .pattern("X$X")
                .pattern("XOX")
                .pattern("X$X")
                .define('$', Items.GOLD_INGOT)
                .define('X', Items.QUARTZ)
                .define('O', FRItems.ECTOPLASM.get())
                .unlockedBy(getHasName(FRItems.ECTOPLASM.get()), has(FRItems.ECTOPLASM.get()))
                .group("mourning_gold_ingot")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "mourning_gold_ingot"));
        // Rotcross/Necro Armor
        RecipeHelper.armorHelper(exporter, FRItems.NECRO_WEAVE.get(),
                FRItems.NECRO_WEAVE_HELMET.get(),
                FRItems.NECRO_WEAVE_CHESTPLATE.get(),
                FRItems.NECRO_WEAVE_LEGGINGS.get(),
                FRItems.NECRO_WEAVE_BOOTS.get()
        );
        // Black Emeralds
        RecipeHelper.createReversible(exporter, FRBlocks.BLACK_EMERALD_BLOCK.get().asItem(), FRItems.BLACK_EMERALD.get());
        // Mourning Gold Block <-> Ingot
        RecipeHelper.createReversibleWithItemGroup(exporter, FRBlocks.MOURNING_GOLD_BLOCK.get().asItem(), FRItems.MOURNING_GOLD_INGOT.get(), "mourning_gold_ingot");
        // Mourning Gold Armor
        RecipeHelper.armorHelper(exporter, FRItems.MOURNING_GOLD_INGOT.get(),
                FRItems.MOURNING_GOLD_HELMET.get(),
                FRItems.MOURNING_GOLD_CHESTPLATE.get(),
                FRItems.MOURNING_GOLD_LEGGINGS.get(),
                FRItems.MOURNING_GOLD_BOOTS.get()
        );
        // Mossy Tower Bricks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, FRBlocks.MOSSY_TOWER_BRICKS.get())
                .requires(FRBlocks.TOWER_BRICKS.get())
                .requires(Items.GLOW_LICHEN)
                .unlockedBy(getHasName(FRBlocks.TOWER_BRICKS.get()), has(FRBlocks.TOWER_BRICKS.get()))
                .save(exporter);
        // All mourning gold tools.
        RecipeHelper.toolHelper(exporter, FRItems.MOURNING_GOLD_INGOT.get(),
                FRItems.MOURNING_GOLD_AXE.get(), FRItems.MOURNING_GOLD_SWORD.get(), FRItems.MOURNING_GOLD_SHOVEL.get(), FRItems.MOURNING_GOLD_PICKAXE.get(), FRItems.MOURNING_GOLD_HOE.get()
        );
        // Marshmallow
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FRItems.MARSHMALLOW.get(), 4)
                .pattern(" X ")
                .pattern("OE ")
                .pattern("   ")
                .define('X', Items.SUGAR)
                .define('O', Items.EGG)
                .define('E', FRItems.ECTOPLASM.get())
                .unlockedBy(getHasName(FRItems.ECTOPLASM.get()), has(FRItems.ECTOPLASM.get()))
                .save(exporter);
        // Tome of Fangs
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.TOME_OF_FANGS.get())
                .pattern("GXG")
                .pattern("XBX")
                .pattern("GXG")
                .define('X', FRItems.INVOKE_SHARD.get())
                .define('B', Items.BOOK)
                .define('G', FRItems.RAVAGER_TOOTH.get())
                .unlockedBy(getHasName(FRItems.INVOKE_SHARD.get()), has(FRItems.INVOKE_SHARD.get()))
                .save(exporter);
        // All frostite tools.
        RecipeHelper.toolHelper(exporter, FRItems.FROSTITE_INGOT.get(),
                FRItems.FROSTITE_AXE.get(), FRItems.FROSTITE_SWORD.get(), FRItems.FROSTITE_SHOVEL.get(), FRItems.FROSTITE_PICKAXE.get(), FRItems.FROSTITE_HOE.get()
        );
        // Frostite Armor
        RecipeHelper.armorHelper(exporter, FRItems.FROSTITE_INGOT.get(),
                FRItems.FROSTITE_HELMET.get(),
                FRItems.FROSTITE_CHESTPLATE.get(),
                FRItems.FROSTITE_LEGGINGS.get(),
                FRItems.FROSTITE_BOOTS.get()
        );
        // Nacre Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.NACRE_BRICKS.get())
                .pattern("## ")
                .pattern("## ")
                .pattern("   ")
                .define('#', FRItems.NACRE_BRICK.get())
                .unlockedBy(getHasName(FRItems.NACRE_BRICK.get()), has(FRItems.NACRE_BRICK.get()))
                .save(exporter);
        // Levi Roll
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FRItems.LEVI_ROLL.get(), 2)
                .pattern("   ")
                .pattern("OwO")
                .pattern("   ")
                .define('w', FRItems.SHULKER_RESIDUE.get())
                .define('O', Items.WHEAT)
                .unlockedBy(getHasName(FRItems.SHULKER_RESIDUE.get()), has(FRItems.SHULKER_RESIDUE.get()))
                .save(exporter);
        // Cobalt Armor
        RecipeHelper.armorHelper(exporter, FRItems.COBALT_INGOT.get(),
                FRItems.COBALT_HELMET.get(),
                FRItems.COBALT_CHESTPLATE.get(),
                FRItems.COBALT_LEGGINGS.get(),
                FRItems.COBALT_BOOTS.get()
        );
        // Cursed Tablet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.CURSED_TABLET.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', FRItems.TABLET_FRAGMENT.get())
                .unlockedBy(getHasName(FRItems.TABLET_FRAGMENT.get()), has(FRItems.TABLET_FRAGMENT.get()))
                .save(exporter);
        // Light Blue Dye (Snow Dahlia)
        oneToOneConversionRecipe(exporter, Items.LIGHT_BLUE_DYE, FRBlocks.SNOW_DAHLIA.get(), "light_blue_dye");
        // Purple Dye (Fungal Daffodil)
        oneToOneConversionRecipe(exporter, Items.PURPLE_DYE, FRBlocks.FUNGAL_DAFFODIL.get(), "purple_dye");
        // Red Dye (Crimcone)
        oneToOneConversionRecipe(exporter, Items.RED_DYE, FRBlocks.CRIMCONE.get(), "red_dye");
        // Onyx Meal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRItems.ONYX_MEAL.get(), 3)
                .requires(FRItems.ONYX_BONE.get())
                .unlockedBy(getHasName(FRItems.ONYX_BONE.get()), has(FRItems.ONYX_BONE.get()))
                .group("onyx_meal")
                .save(exporter);
        // Black Dye from Oxyx Meal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.BLACK_DYE)
                .requires(FRItems.ONYX_MEAL.get())
                .unlockedBy(getHasName(FRItems.ONYX_MEAL.get()), has(FRItems.ONYX_MEAL.get()))
                .group("black_dye")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_dye_from_onyx_meal"));
        // Bait
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRItems.BAIT.get(), 2)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.SLIME_BALL)
                .unlockedBy(getHasName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(exporter);

        // Core Plate: Depths
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.DEPTHS_CORE_PLATE.get())
                .pattern(" X ")
                .pattern("XOX")
                .pattern(" X ")
                .define('X', Items.DIAMOND)
                .define('O', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(FRItems.UNFINISHED_CORE.get()), has(FRItems.UNFINISHED_CORE.get()))
                .save(exporter);
        // Core Plate: Frontal
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.FRONTAL_CORE_PLATE.get())
                .pattern(" X ")
                .pattern("EOE")
                .pattern(" X ")
                .define('X', FRItems.COBALT_INGOT.get())
                .define('E', FRItems.VERDINITE_INGOT.get())
                .define('O', FRItems.VIVULITE_INGOT.get())
                .unlockedBy(getHasName(FRItems.UNFINISHED_CORE.get()), has(FRItems.UNFINISHED_CORE.get()))
                .save(exporter);

        // Reactive Core
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.REACTIVE_CORE.get())
                .pattern(" A ")
                .pattern("BOC")
                .pattern(" D ")
                .define('A', FRItems.DEPTHS_CORE_PLATE.get())
                .define('B', FRItems.FRONTAL_CORE_PLATE.get())
                .define('C', FRItems.DEPTHS_CORE_PLATE.get())
                .define('D', FRItems.FRONTAL_CORE_PLATE.get())
                .define('O', FRItems.UNFINISHED_CORE.get())
                .unlockedBy(getHasName(FRItems.DEPTHS_CORE_PLATE.get()), has(FRItems.DEPTHS_CORE_PLATE.get()))
                .unlockedBy(getHasName(FRItems.FRONTAL_CORE_PLATE.get()), has(FRItems.FRONTAL_CORE_PLATE.get()))
                .save(exporter);

        // Strange Core
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRBlocks.STRANGE_CORE.get())
                .pattern("DXD")
                .pattern("DOD")
                .pattern("DXD")
                .define('D', Items.IRON_INGOT)
                .define('X', Items.DIAMOND)
                .define('O', FRItems.REACTIVE_CORE.get())
                .unlockedBy(getHasName(FRItems.REACTIVE_CORE.get()), has(FRItems.REACTIVE_CORE.get()))
                .save(exporter);

        // Copper Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.COPPER_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.COPPER_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                .save(exporter);
        // Iron Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.IRON_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.IRON_INGOT)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(exporter);
        // Diamond Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.DIAMOND_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', Items.DIAMOND)
                .define('S', Items.STRING)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(exporter);
        // Netherite Bow
        RecipeHelper.templateUpgrade(exporter, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT,
                FRItems.DIAMOND_BOW.get(), RecipeCategory.COMBAT, FRItems.NETHERITE_BOW.get());
        // Echo Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.ECHO_BOW.get())
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
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.VERDINITE_BOW.get())
                .pattern(" XS")
                .pattern("X S")
                .pattern(" XS")
                .define('X', FRItems.VERDINITE_INGOT.get())
                .define('S', Items.STRING)
                .unlockedBy(getHasName(FRItems.VERDINITE_INGOT.get()), has(FRItems.VERDINITE_INGOT.get()))
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
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.ONYX_BONE_BLOCK.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', FRItems.ONYX_MEAL.get())
                .unlockedBy(getHasName(FRItems.ONYX_MEAL.get()), has(FRItems.ONYX_MEAL.get()))
                .save(exporter);
        // 9 Onyx Meal from Onyx Bone Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRItems.ONYX_MEAL.get(), 9)
                .requires(FRBlocks.ONYX_BONE_BLOCK.get())
                .unlockedBy(getHasName(FRBlocks.ONYX_BONE_BLOCK.get()), has(FRBlocks.ONYX_BONE_BLOCK.get()))
                .group("onyx_meal")
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "onyx_meal_from_onyx_bone_block"));
        // Tower Key
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FRItems.TOWER_KEY.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', FRItems.TOWER_KEY_FRAGMENT.get())
                .unlockedBy(getHasName(FRItems.TOWER_KEY_FRAGMENT.get()), has(FRItems.TOWER_KEY_FRAGMENT.get()))
                .save(exporter);
        // Warped Wart Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.WARPED_WART_BLOCK)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', FRItems.WARPED_WART.get())
                .unlockedBy(getHasName(FRItems.WARPED_WART.get()), has(FRItems.WARPED_WART.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "warped_wart_block_from_warped_wart"));
        // Blue Nether Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.BLUE_NETHER_BRICKS.get(), 6)
                .pattern("NWN")
                .pattern("WNW")
                .pattern("NWN")
                .define('W', FRItems.WARPED_WART.get())
                .define('N', Items.NETHER_BRICK)
                .unlockedBy(getHasName(FRItems.WARPED_WART.get()), has(FRItems.WARPED_WART.get()))
                .save(exporter);
        // Purple Nether Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.PURPLE_NETHER_BRICKS.get(), 6)
                .pattern("NXN")
                .pattern("WNX")
                .pattern("NWN")
                .define('W', Items.NETHER_WART)
                .define('X', FRItems.WARPED_WART.get())
                .define('N', Items.NETHER_BRICK)
                .unlockedBy(getHasName(FRItems.WARPED_WART.get()), has(FRItems.WARPED_WART.get()))
                .save(exporter);
        // New Nether Brick Fences & Gates
        RecipeHelper.createBrickFence(exporter, FRBlocks.BLUE_NETHER_BRICKS.get(), Items.NETHER_BRICK, FRBlocks.BLUE_NETHER_BRICK_FENCE.get());
        RecipeHelper.createBrickFence(exporter, FRBlocks.PURPLE_NETHER_BRICKS.get(), Items.NETHER_BRICK, FRBlocks.PURPLE_NETHER_BRICK_FENCE.get());
        RecipeHelper.createBrickFence(exporter, Blocks.RED_NETHER_BRICKS, Items.NETHER_BRICK, FRBlocks.RED_NETHER_BRICK_FENCE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_FENCE_GATE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_FENCE_GATE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.RED_NETHER_BRICKS, FRBlocks.RED_NETHER_BRICK_FENCE_GATE.get());
        RecipeHelper.createFenceGate(exporter, Items.NETHER_BRICK, Blocks.NETHER_BRICKS, FRBlocks.NETHER_BRICK_FENCE_GATE.get());
        // Quicksand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.QUICKSAND.get(), 2)
                .pattern("EX")
                .pattern("XE")
                .define('E', Items.MUD)
                .define('X', Items.SAND)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(exporter);
        // Red Quicksand
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.RED_QUICKSAND.get(), 2)
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
                .define('X', FRItems.INVOKE_SHARD.get())
                .define('G', Items.GOLD_INGOT)
                .define('O', Items.EMERALD)
                .unlockedBy(getHasName(FRItems.INVOKE_SHARD.get()), has(FRItems.INVOKE_SHARD.get()))
                .save(exporter, Frontiers.id("totem_of_undying"));
        // Totem of Undying
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.TOTEM_OF_AVARICE.get())
                .pattern("X")
                .pattern("E")
                .pattern("#")
                .define('X', Items.DIAMOND)
                .define('E', FRItems.INVOKE_SHARD.get())
                .define('#', Items.IRON_INGOT)
                .unlockedBy(getHasName(FRItems.INVOKE_SHARD.get()), has(FRItems.INVOKE_SHARD.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "totem_of_avarice"));
        // Void Pearl
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FRItems.VOID_PEARL.get())
                .pattern("%%%")
                .pattern("%O%")
                .pattern("%%%")
                .define('%', FRItems.BRIMTAN_NUGGET.get())
                .define('O', Items.ENDER_EYE)
                .unlockedBy(getHasName(FRItems.BRIMTAN_NUGGET.get()), has(FRItems.BRIMTAN_NUGGET.get()))
                .save(exporter);
        // Lumens
        RecipeHelper.createLumen(exporter, Items.AMETHYST_SHARD, FRBlocks.AMETHYST_LUMEN.get(), FRItems.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.DIAMOND, FRBlocks.DIAMOND_LUMEN.get(), FRItems.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.EMERALD, FRBlocks.EMERALD_LUMEN.get(), FRItems.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.QUARTZ, FRBlocks.QUARTZ_LUMEN.get(), FRItems.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.REDSTONE, FRBlocks.REDSTONE_LUMEN.get(), FRItems.LIGHTNING_IN_A_BOTTLE.get());
        RecipeHelper.createLumen(exporter, Items.ECHO_SHARD, FRBlocks.ECHO_LUMEN.get(), Items.ECHO_SHARD);
        RecipeHelper.createLumen(exporter, FRItems.COBALT_INGOT.get(), FRBlocks.COBALT_LUMEN.get(), FRItems.COBALT_INGOT.get());
        RecipeHelper.createLumen(exporter, FRItems.FROSTITE_INGOT.get(), FRBlocks.FROSTITE_LUMEN.get(), FRItems.FROSTITE_INGOT.get());
        RecipeHelper.createLumen(exporter, FRItems.VERDINITE_INGOT.get(), FRBlocks.VERDINITE_LUMEN.get(), FRItems.VERDINITE_INGOT.get());
        RecipeHelper.createLumen(exporter, FRItems.VIVULITE_INGOT.get(), FRBlocks.VIVULITE_LUMEN.get(), FRItems.VIVULITE_INGOT.get());
        RecipeHelper.createLumen(exporter, FRItems.BRIMTAN_NUGGET.get(), FRBlocks.BRIMTAN_LUMEN.get(), FRItems.BRIMTAN_NUGGET.get());
        // All verdinite tools.
        RecipeHelper.toolHelper(exporter, FRItems.VERDINITE_INGOT.get(),
                FRItems.VERDINITE_AXE.get(), FRItems.VERDINITE_SWORD.get(), FRItems.VERDINITE_SHOVEL.get(), FRItems.VERDINITE_PICKAXE.get(), FRItems.VERDINITE_HOE.get()
        );
        // Verdinite Armor
        RecipeHelper.armorHelper(exporter, FRItems.VERDINITE_INGOT.get(),
                FRItems.VERDINITE_HELMET.get(),
                FRItems.VERDINITE_CHESTPLATE.get(),
                FRItems.VERDINITE_LEGGINGS.get(),
                FRItems.VERDINITE_BOOTS.get()
        );
        // All vivulite tools.
        RecipeHelper.toolHelper(exporter, FRItems.VIVULITE_INGOT.get(),
                FRItems.VIVULITE_AXE.get(), FRItems.VIVULITE_SWORD.get(), FRItems.VIVULITE_SHOVEL.get(), FRItems.VIVULITE_PICKAXE.get(), FRItems.VIVULITE_HOE.get()
        );
        // Vivulite Armor
        RecipeHelper.armorHelper(exporter, FRItems.VIVULITE_INGOT.get(),
                FRItems.VIVULITE_HELMET.get(),
                FRItems.VIVULITE_CHESTPLATE.get(),
                FRItems.VIVULITE_LEGGINGS.get(),
                FRItems.VIVULITE_BOOTS.get()
        );
        // Vivulite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, FRBlocks.VIVULITE_BLOCK.get().asItem(), FRItems.VIVULITE_INGOT.get());
        // Verdinite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, FRBlocks.VERDINITE_BLOCK.get().asItem(), FRItems.VERDINITE_INGOT.get());

        // Sea Glass
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.SEA_GLASS.get(), 2)
                .pattern(" P ")
                .pattern("PGP")
                .pattern(" P ")
                .define('P', Items.PRISMARINE_CRYSTALS)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.PRISMARINE_CRYSTALS), has(Items.PRISMARINE_CRYSTALS))
                .save(exporter);
        // Pale Prismarine
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.PALE_PRISMARINE.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE)
                .define('X', FRItems.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(FRItems.PALE_PRISMARINE_SHARD.get()), has(FRItems.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Pale Prismarine Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.PALE_PRISMARINE_BRICKS.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE_BRICKS)
                .define('X', FRItems.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(FRItems.PALE_PRISMARINE_SHARD.get()), has(FRItems.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Deep Pale Prismarine
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.DEEP_PALE_PRISMARINE.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', Items.DARK_PRISMARINE)
                .define('X', FRItems.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(FRItems.PALE_PRISMARINE_SHARD.get()), has(FRItems.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Pale Sea Glass
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.PALE_SEA_GLASS.get(), 8)
                .pattern("PPP")
                .pattern("PXP")
                .pattern("PPP")
                .define('P', FRBlocks.SEA_GLASS.get())
                .define('X', FRItems.PALE_PRISMARINE_SHARD.get())
                .unlockedBy(getHasName(FRItems.PALE_PRISMARINE_SHARD.get()), has(FRItems.PALE_PRISMARINE_SHARD.get()))
                .save(exporter);
        // Sea Glass Panes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.SEA_GLASS_PANE.get(), 16)
                .pattern("##")
                .define('#', FRBlocks.SEA_GLASS.get())
                .unlockedBy(getHasName(FRBlocks.SEA_GLASS.get()), has(FRBlocks.SEA_GLASS.get()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.PALE_SEA_GLASS_PANE.get(), 16)
                .pattern("##")
                .define('#', FRBlocks.PALE_SEA_GLASS.get())
                .unlockedBy(getHasName(FRBlocks.PALE_SEA_GLASS.get()), has(FRBlocks.PALE_SEA_GLASS.get()))
                .save(exporter);
        // Frostite Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, FRBlocks.FROSTITE_BLOCK.get().asItem(), FRItems.FROSTITE_INGOT.get());
        // Turtle Scute Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.TURTLE_SCUTE_BRICKS.get(), 8)
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XXX")
                .define('E', Items.TURTLE_SCUTE)
                .define('X', Items.BRICK)
                .unlockedBy(getHasName(Items.TURTLE_SCUTE), has(Items.TURTLE_SCUTE))
                .save(exporter);
        // Hielostone (from Blue ice)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRBlocks.HIELOSTONE.get())
                .pattern("XXX")
                .pattern("XEX")
                .pattern("XXX")
                .define('X', Items.STONE)
                .define('E', Items.BLUE_ICE)
                .unlockedBy(getHasName(Items.BLUE_ICE), has(Items.BLUE_ICE))
                .save(exporter, Frontiers.id("hielostone_from_blue_ice"));
        // Brimtan Ingot <-> Nugget convertible
        RecipeHelper.createReversible(exporter, FRItems.BRIMTAN_INGOT.get(), FRItems.BRIMTAN_NUGGET.get());
        // All brimtan shells.
        RecipeHelper.toolHelper(exporter, FRItems.BRIMTAN_INGOT.get(),
                FRItems.BRIMTAN_SHELL_AXE.get(),
                FRItems.BRIMTAN_SHELL_SWORD.get(),
                FRItems.BRIMTAN_SHELL_SHOVEL.get(),
                FRItems.BRIMTAN_SHELL_PICKAXE.get(),
                FRItems.BRIMTAN_SHELL_HOE.get()
        );
        // All balls
        RecipeHelper.doBallRecipes(exporter);
        // Vivulite Anvil
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FRBlocks.VIVULITE_ANVIL.get())
                .pattern("XXX")
                .pattern(" E ")
                .pattern("XXX")
                .define('E', FRItems.VIVULITE_INGOT.get())
                .define('X', FRBlocks.VIVULITE_BLOCK.get())
                .unlockedBy(getHasName(FRBlocks.VIVULITE_BLOCK.get()), has(FRBlocks.VIVULITE_BLOCK.get()))
                .save(exporter);
        // Beef Wellington
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FRBlocks.BEEF_WELLINGTON.get())
                .pattern("$$$")
                .pattern("$X$")
                .pattern("$I$")
                .define('$', Items.WHEAT)
                .define('X', FRItems.TRUFFLE.get())
                .define('I', Items.COOKED_BEEF)
                .unlockedBy(getHasName(FRItems.TRUFFLE.get()), has(FRItems.TRUFFLE.get()))
                .save(exporter);
        // Fruitcake
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FRBlocks.FRUITCAKE.get())
                .pattern("FWF")
                .pattern("F0F")
                .pattern("S$S")
                .define('$', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('0', Items.EGG)
                .define('W', Items.WHEAT)
                .define('F', FRTags.Items.FRUITCAKE_INGREDIENTS)
                .unlockedBy("foot_gummy", has(FRTags.Items.FRUITCAKE_INGREDIENTS))
                .save(exporter);
        // Truffle Potato Puff
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FRItems.TRUFFLE_POTATO_PUFF.get(), 6)
                .pattern(" X ")
                .pattern("$I$")
                .define('$', Items.WHEAT)
                .define('X', FRItems.TRUFFLE.get())
                .define('I', Items.BAKED_POTATO)
                .unlockedBy(getHasName(FRItems.TRUFFLE.get()), has(FRItems.TRUFFLE.get()))
                .save(exporter);
        // Truffle Oil
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FRItems.TRUFFLE_OIL.get(), 4)
                .requires(FRItems.TRUFFLE.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy(getHasName(FRItems.TRUFFLE.get()), has(FRItems.TRUFFLE.get()))
                .save(exporter);
        // Personal Chest
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRBlocks.PERSONAL_CHEST.get())
                .pattern("#S#")
                .pattern("#C#")
                .pattern("#E#")
                .define('#', Items.COBBLED_DEEPSLATE)
                .define('C', FRItems.LIGHTNING_IN_A_BOTTLE.get())
                .define('S', Items.GOLD_INGOT)
                .define('E', Items.ECHO_SHARD)
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .save(exporter);
        // Chest Key
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRItems.CHEST_KEY.get())
                .pattern(" ##")
                .pattern(" X#")
                .pattern("#  ")
                .define('#', Items.GOLD_INGOT)
                .define('X', Items.LAPIS_LAZULI)
                .unlockedBy(getHasName(FRBlocks.PERSONAL_CHEST.get()), has(FRBlocks.PERSONAL_CHEST.get()))
                .save(exporter);
        // Curse Altar
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRBlocks.CURSE_ALTAR.get())
                .pattern(" C ")
                .pattern("V#V")
                .pattern("L#L")
                .define('#', Items.SMOOTH_SANDSTONE)
                .define('C', FRItems.ECTOPLASM.get())
                .define('V', Items.DIAMOND)
                .define('L', Blocks.LAPIS_BLOCK)
                .unlockedBy(getHasName(FRItems.ECTOPLASM.get()), has(FRItems.ECTOPLASM.get()))
                .save(exporter);
        // Brimtan Block <-> Ingot convertible
        RecipeHelper.createReversible(exporter, FRBlocks.BRIMTAN_BLOCK.get().asItem(), FRItems.BRIMTAN_INGOT.get());

        // Trim templates
        RecipeHelper.doTemplateDupe(exporter, FRItems.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), Blocks.GRANITE.asItem());
        RecipeHelper.doTemplateDupe(exporter, FRItems.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), Blocks.SLIME_BLOCK.asItem());
        RecipeHelper.doTemplateDupe(exporter, FRItems.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get(), FRBlocks.ONYX_BONE_BLOCK.get().asItem());

        // Brimtan Armor Shells
        RecipeHelper.armorHelper(exporter, FRItems.BRIMTAN_INGOT.get(),
                FRItems.BRIMTAN_SHELL_HELMET.get(),
                FRItems.BRIMTAN_SHELL_CHESTPLATE.get(),
                FRItems.BRIMTAN_SHELL_LEGGINGS.get(),
                FRItems.BRIMTAN_SHELL_BOOTS.get()
        );
        // All Brimtan templates
        RecipeHelper.doBrimtanTemplateDupe(exporter, FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get());
        RecipeHelper.doBrimtanTemplateDupe(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get());

        // Brimmed Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), 4)
                .pattern(" B ")
                .pattern("B#B")
                .pattern(" B ")
                .define('B', FRBlocks.CRAGULSTANE_BRICKS.get())
                .define('#', FRItems.BRIMTAN_NUGGET.get())
                .unlockedBy(getHasName(FRItems.BRIMTAN_NUGGET.get()), has(FRItems.BRIMTAN_NUGGET.get()))
                .save(exporter);
        // Orange Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BXB")
                .pattern("BBB")
                .define('B', FRBlocks.CRAGULSTANE_BRICKS.get())
                .define('X', Items.LAVA_BUCKET)
                .unlockedBy(getHasName(FRBlocks.CRAGULSTANE_BRICKS.get()), has(FRBlocks.CRAGULSTANE_BRICKS.get()))
                .save(exporter);
        // Tyrian Cragulstane Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), 5)
                .pattern("B#B")
                .pattern("#BO")
                .pattern("BOB")
                .define('B', FRBlocks.CRAGULSTANE_BRICKS.get())
                .define('#', Items.NETHER_WART)
                .define('O', FRItems.WARPED_WART.get())
                .unlockedBy(getHasName(FRBlocks.CRAGULSTANE_BRICKS.get()), has(FRBlocks.CRAGULSTANE_BRICKS.get()))
                .save(exporter);
        // Pale Trident
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.PALE_TRIDENT.get())
                .pattern("XXX")
                .pattern("#T#")
                .pattern(" # ")
                .define('T', Items.TRIDENT)
                .define('#', FRItems.PALE_PRISMARINE_SHARD.get())
                .define('X', FRItems.ELDER_GUARDIAN_SPINE.get())
                .unlockedBy(getHasName(FRItems.ELDER_GUARDIAN_SPINE.get()), has(FRItems.ELDER_GUARDIAN_SPINE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "pale_trident"));

        // Glistering Melon <-> Slices convertible
        RecipeHelper.createReversible(exporter, FRBlocks.GLISTERING_MELON.get().asItem(), Items.GLISTERING_MELON_SLICE);
        // June o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.JUNE_O_LANTERN.get())
                .define('A', FRBlocks.CARVED_MELON.get())
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(FRBlocks.CARVED_MELON.get()))
                .save(exporter);
        // Glistering o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.GLISTERING_JUNE_O_LANTERN.get())
                .define('A', FRBlocks.CARVED_GLISTERING_MELON.get())
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(FRBlocks.CARVED_GLISTERING_MELON.get()))
                .save(exporter);
        // White Pumpkin
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, FRBlocks.WHITE_PUMPKIN.get())
                .define('X', FRItems.ECTOPLASM.get())
                .define('P', Blocks.CARVED_PUMPKIN)
                .pattern(" X ")
                .pattern("XPX")
                .pattern(" X ")
                .unlockedBy(getHasName(FRItems.ECTOPLASM.get()), has(FRItems.ECTOPLASM.get()))
                .save(exporter);
        // White Jack o Lantern
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.WHITE_JACK_O_LANTERN.get())
                .define('A', FRBlocks.WHITE_PUMPKIN.get())
                .define('B', Blocks.TORCH)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_base", has(FRBlocks.WHITE_PUMPKIN.get()))
                .save(exporter);
        // Slime Shoes
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.SLIME_SHOES.get())
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', FRItems.HARDENED_SLIME.get())
                .unlockedBy(getHasName(FRItems.HARDENED_SLIME.get()), has(FRItems.HARDENED_SLIME.get()))
                .save(exporter);

        // Spirit Candle
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRItems.SPIRIT_CANDLE.get(), 2)
                .pattern("   ")
                .pattern(" $ ")
                .pattern("#%#")
                .define('$', Items.STRING)
                .define('#', Items.HONEYCOMB)
                .define('%', FRItems.INCENSE.get())
                .unlockedBy(getHasName(FRItems.INCENSE.get()), has(FRItems.INCENSE.get()))
                .save(exporter);
        // Raw ore convertibles
        RecipeHelper.createReversible(exporter, FRBlocks.RAW_COBALT_BLOCK.get().asItem(), FRItems.RAW_COBALT.get());
        RecipeHelper.createReversible(exporter, FRBlocks.RAW_VERDINITE_BLOCK.get().asItem(), FRItems.RAW_VERDINITE.get());
        RecipeHelper.createReversible(exporter, FRBlocks.RAW_VIVULITE_BLOCK.get().asItem(), FRItems.RAW_VIVULITE.get());
        RecipeHelper.createReversible(exporter, FRBlocks.RAW_FROSTITE_BLOCK.get().asItem(), FRItems.RAW_FROSTITE.get());

        // Verdinite Horse Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.VERDINITE_HORSE_ARMOR.get())
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .define('H', FRItems.COBALT_HORSE_ARMOR.get())
                .define('#', FRItems.VERDINITE_INGOT.get())
                .unlockedBy(getHasName(FRItems.VERDINITE_INGOT.get()), has(FRItems.VERDINITE_INGOT.get()))
                .save(exporter);
        // Vivulite Horse Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, FRItems.VIVULITE_HORSE_ARMOR.get())
                .pattern("  #")
                .pattern("#H#")
                .pattern("###")
                .define('H', FRItems.VERDINITE_HORSE_ARMOR.get())
                .define('#', FRItems.VIVULITE_INGOT.get())
                .unlockedBy(getHasName(FRItems.VIVULITE_INGOT.get()), has(FRItems.VIVULITE_INGOT.get()))
                .save(exporter);

        // Phantasmic TNT
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, FRBlocks.PHANTASMIC_TNT.get())
                .pattern("#X#")
                .pattern("XSX")
                .pattern("#X#")
                .define('#', Items.GUNPOWDER)
                .define('X', ItemTags.SAND)
                .define('S', FRItems.SOUL.get())
                .unlockedBy(getHasName(FRItems.SOUL.get()), has(FRItems.SOUL.get()))
                .save(exporter);

        // Snow Melt
        ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, FRItems.SNOW_MELT.get(), 4)
                .requires(FRItems.FROST_BONE.get())
                .unlockedBy(getHasName(FRItems.FROST_BONE.get()), has(FRItems.FROST_BONE.get()))
                .save(exporter);
        // Message in a Bottle
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, FRItems.MESSAGE_IN_A_BOTTLE.get())
                .requires(Items.GLASS_BOTTLE)
                .requires(Items.PAPER)
                .requires(Items.INK_SAC)
                .unlockedBy(getHasName(Items.GLASS_BOTTLE), has(Items.GLASS_BOTTLE))
                .save(exporter);

        // Monster Bakery
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRBlocks.MONSTER_BAKERY.get())
                .pattern("#*#")
                .pattern("*&*")
                .pattern("#*#")
                .define('&', FRItems.INCENSE.get())
                .define('#', FRItems.SPAWNER_CHUNK.get())
                .define('*', Items.DIAMOND)
                .unlockedBy(getHasName(FRItems.SPAWNER_CHUNK.get()), has(FRItems.SPAWNER_CHUNK.get()))
                .save(exporter);

        // Phantom-Stitch Bed
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FRItems.PHANTOM_STITCH_BED.get())
                .pattern("###")
                .pattern("XXX")
                .define('#', Items.PHANTOM_MEMBRANE)
                .define('X', ItemTags.PLANKS)
                .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                .save(exporter);

        // Enchanting Magnet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, FRBlocks.ENCHANTING_MAGNET.get())
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
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, FRBlocks.ITEM_VACUUM.get(), 2)
                .pattern("#C#")
                .pattern("#R#")
                .pattern(" $ ")
                .define('#', Items.IRON_BARS)
                .define('$', Items.HOPPER)
                .define('R', Items.REDSTONE)
                .define('C', FRItems.SPAWNER_CHUNK.get())
                .unlockedBy(getHasName(FRItems.SPAWNER_CHUNK.get()), has(FRItems.SPAWNER_CHUNK.get()))
                .save(exporter);

        // Eboncork Blocks
        planksFromLogs(exporter, FRBlocks.EBONCORK_PLANKS.get(), FRTags.Items.EBONCORK_LOGS, 2);
        stairBuilder(FRBlocks.EBONCORK_STAIRS.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_stairs")
                .save(exporter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, FRBlocks.EBONCORK_SLAB.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_slab")
                .save(exporter);
        fenceBuilder(FRBlocks.EBONCORK_FENCE.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_fence")
                .save(exporter);
        fenceGateBuilder(FRBlocks.EBONCORK_FENCE_GATE.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(exporter);
        buttonBuilder(FRBlocks.EBONCORK_BUTTON.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_button")
                .save(exporter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, FRBlocks.EBONCORK_PRESSURE_PLATE.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(exporter);
        doorBuilder(FRBlocks.EBONCORK_DOOR.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_door")
                .save(exporter);
        trapdoorBuilder(FRBlocks.EBONCORK_TRAPDOOR.get(), Ingredient.of(FRBlocks.EBONCORK_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.EBONCORK_PLANKS.get()), has(FRBlocks.EBONCORK_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(exporter);

        // Blighted Birch Blocks
        woodFromLogs(exporter, FRBlocks.SULLEN_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.SULLEN_BLIGHTED_BIRCH_LOG.get());
        woodFromLogs(exporter, FRBlocks.RADIANT_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.RADIANT_BLIGHTED_BIRCH_LOG.get());
        woodFromLogs(exporter, FRBlocks.STRIPPED_BLIGHTED_BIRCH_WOOD.get(), FRBlocks.STRIPPED_BLIGHTED_BIRCH_LOG.get());
        planksFromLogs(exporter, FRBlocks.BLIGHTED_BIRCH_PLANKS.get(), FRTags.Items.BLIGHTED_BIRCH_LOGS, 4);

        stairBuilder(FRBlocks.BLIGHTED_BIRCH_STAIRS.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_stairs")
                .save(exporter);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, FRBlocks.BLIGHTED_BIRCH_SLAB.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_slab")
                .save(exporter);
        fenceBuilder(FRBlocks.BLIGHTED_BIRCH_FENCE.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_fence")
                .save(exporter);
        fenceGateBuilder(FRBlocks.BLIGHTED_BIRCH_FENCE_GATE.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_fence_gate")
                .save(exporter);
        buttonBuilder(FRBlocks.BLIGHTED_BIRCH_BUTTON.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_button")
                .save(exporter);
        pressurePlateBuilder(RecipeCategory.REDSTONE, FRBlocks.BLIGHTED_BIRCH_PRESSURE_PLATE.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_pressure_plate")
                .save(exporter);
        doorBuilder(FRBlocks.BLIGHTED_BIRCH_DOOR.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_door")
                .save(exporter);
        trapdoorBuilder(FRBlocks.BLIGHTED_BIRCH_TRAPDOOR.get(), Ingredient.of(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .unlockedBy(getHasName(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()), has(FRBlocks.BLIGHTED_BIRCH_PLANKS.get()))
                .group("wooden_trapdoor")
                .save(exporter);

        // Crusted Quicksands
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_QUICKSAND.get())
                .requires(FRBlocks.QUICKSAND.get())
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(FRBlocks.QUICKSAND.get()), has(FRBlocks.QUICKSAND.get()))
                .save(exporter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_RED_QUICKSAND.get())
                .requires(FRBlocks.RED_QUICKSAND.get())
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(FRBlocks.RED_QUICKSAND.get()), has(FRBlocks.RED_QUICKSAND.get()))
                .save(exporter);
        // Crusty Sand Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.CRUSTY_SAND_BRICKS.get(), 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', FRBlocks.CRUSTED_QUICKSAND.get())
                .unlockedBy(getHasName(FRBlocks.CRUSTED_QUICKSAND.get()), has(FRBlocks.CRUSTED_QUICKSAND.get()))
                .save(exporter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', FRBlocks.CRUSTED_RED_QUICKSAND.get())
                .unlockedBy(getHasName(FRBlocks.CRUSTED_RED_QUICKSAND.get()), has(FRBlocks.CRUSTED_RED_QUICKSAND.get()))
                .save(exporter);

        // Wreaths
        RecipeHelper.createWreath(exporter, Items.OAK_LEAVES, Items.POPPY, FRBlocks.OAK_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.DARK_OAK_LEAVES, Items.RED_MUSHROOM, FRBlocks.DARK_OAK_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.BIRCH_LEAVES, Items.DANDELION, FRBlocks.BIRCH_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.SPRUCE_LEAVES, Items.SWEET_BERRIES, FRBlocks.SPRUCE_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.JUNGLE_LEAVES, Items.COCOA_BEANS, FRBlocks.JUNGLE_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.ACACIA_LEAVES, Items.SHORT_GRASS, FRBlocks.ACACIA_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.MANGROVE_LEAVES, Items.BLUE_ORCHID, FRBlocks.MANGROVE_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.FLOWERING_AZALEA_LEAVES, Items.GLOW_BERRIES, FRBlocks.AZALEA_WREATH.get());
        RecipeHelper.createWreath(exporter, Items.CHERRY_LEAVES, Items.PINK_PETALS, FRBlocks.CHERRY_WREATH.get());
        RecipeHelper.createWreath(exporter, FRBlocks.BLIGHTED_BIRCH_LEAVES.get().asItem(), Items.SPIDER_EYE, FRBlocks.BLIGHTED_BIRCH_WREATH.get());

        // Misc storage block reversibles
        RecipeHelper.createReversible(exporter, FRBlocks.SUGAR_CANE_BLOCK.get().asItem(), Items.SUGAR_CANE);
        RecipeHelper.createReversible(exporter, FRBlocks.COCOA_BEAN_BLOCK.get().asItem(), Items.COCOA_BEANS);
        RecipeHelper.createReversible(exporter, FRBlocks.ROTTEN_FLESH_BLOCK.get().asItem(), Items.ROTTEN_FLESH);
        RecipeHelper.createReversibleSix(exporter, FRBlocks.EGG_PALLET.get().asItem(), Items.EGG);
        RecipeHelper.createReversibleSix(exporter, FRBlocks.GOLDEN_EGG_PALLET.get().asItem(), FRItems.GOLDEN_EGG.get());

        // TEMP APPLE OF ENLIGHTENMENT RECIPE!
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, FRItems.APPLE_OF_ENLIGHTENMENT.get())
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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.RAW_COBALT.get()), RecipeCategory.MISC, FRItems.COBALT_INGOT.get(), 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(FRItems.RAW_COBALT.get()), has(FRItems.RAW_COBALT.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_raw_cobalt"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.COBALT_ORE.get()), RecipeCategory.MISC, FRItems.COBALT_INGOT.get(), 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(FRBlocks.COBALT_ORE.get()), has(FRBlocks.COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_cobalt_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.DEEPSLATE_COBALT_ORE.get()), RecipeCategory.MISC, FRItems.COBALT_INGOT.get(), 1.4f, 200)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_COBALT_ORE.get()), has(FRBlocks.DEEPSLATE_COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_smelting_deepslate_cobalt_ore"));

        // Verdinite Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.RAW_VERDINITE.get()), RecipeCategory.MISC, FRItems.VERDINITE_INGOT.get(), 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(FRItems.RAW_VERDINITE.get()), has(FRItems.RAW_VERDINITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_raw_verdinite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.VERDINITE_ORE.get()), RecipeCategory.MISC, FRItems.VERDINITE_INGOT.get(), 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(FRBlocks.VERDINITE_ORE.get()), has(FRBlocks.VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_verdinite_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.DEEPSLATE_VERDINITE_ORE.get()), RecipeCategory.MISC, FRItems.VERDINITE_INGOT.get(), 1.4f, 200)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_VERDINITE_ORE.get()), has(FRBlocks.DEEPSLATE_VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_smelting_deepslate_verdinite_ore"));

        // Raw Frostite
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.RAW_FROSTITE.get()), RecipeCategory.MISC, FRItems.FROSTITE_INGOT.get(), 2.0f, 200)
                .group("frostite_ingot")
                .unlockedBy(getHasName(FRItems.RAW_FROSTITE.get()), has(FRItems.RAW_FROSTITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frostite_ingot_from_smelting_raw_frostite"));

        // Vivulite ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.RAW_VIVULITE.get()), RecipeCategory.MISC, FRItems.VIVULITE_INGOT.get(), 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(FRItems.RAW_VIVULITE.get()), has(FRItems.RAW_VIVULITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_raw_vivulite"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.VIVULITE_ORE.get()), RecipeCategory.MISC, FRItems.VIVULITE_INGOT.get(), 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(FRBlocks.VIVULITE_ORE.get()), has(FRBlocks.VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_vivulite_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.DEEPSLATE_VIVULITE_ORE.get()), RecipeCategory.MISC, FRItems.VIVULITE_INGOT.get(), 1.4f, 200)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_VIVULITE_ORE.get()), has(FRBlocks.DEEPSLATE_VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_smelting_deepslate_vivulite_ore"));

        // Black Emerald
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, FRItems.BLACK_EMERALD.get(), 1f, 200)
                .group("black_emerald")
                .unlockedBy(getHasName(FRBlocks.BLACK_EMERALD_ORE.get()), has(FRBlocks.BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_smelting_black_emerald_ore"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, FRItems.BLACK_EMERALD.get(), 1f, 200)
                .group("black_emerald")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), has(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_smelting_deepslate_black_emerald_ore"));

        // Brimtan Ores
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.BRIMTAN_CLUSTER.get()), RecipeCategory.MISC, FRItems.BRIMTAN_NUGGET.get(), 1.4f, 400)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(FRItems.BRIMTAN_CLUSTER.get()), has(FRItems.BRIMTAN_CLUSTER.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_cluster"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.BRIMTAN_ORE.get()), RecipeCategory.MISC, FRItems.BRIMTAN_NUGGET.get(), 1.4f, 400)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(FRBlocks.BRIMTAN_ORE.get()), has(FRBlocks.BRIMTAN_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_smelting_brimtan_ore"));

        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.MARSHMALLOW.get()), RecipeCategory.FOOD, FRItems.ROASTED_MARSHMALLOW.get(), 0.2f, 100)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(FRItems.MARSHMALLOW.get()), has(FRItems.MARSHMALLOW.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_smelting"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.GUARDIAN_SLICE.get()), RecipeCategory.FOOD, FRItems.COOKED_GUARDIAN_SLICE.get(), 0.35f, 200)
                .unlockedBy(getHasName(FRItems.GUARDIAN_SLICE.get()), has(FRItems.GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_smelting"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.ELDER_GUARDIAN_SLICE.get()), RecipeCategory.FOOD, FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), 0.35f, 200)
                .unlockedBy(getHasName(FRItems.ELDER_GUARDIAN_SLICE.get()), has(FRItems.ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smelting"));

        // Nacre Brick
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.SHULKER_RESIDUE.get()), RecipeCategory.MISC, FRItems.NACRE_BRICK.get(), 0.3f, 200)
                .group("nacre_brick")
                .unlockedBy(getHasName(FRItems.SHULKER_RESIDUE.get()), has(FRItems.SHULKER_RESIDUE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "nacre_brick_smelting"));

        // Hielostone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.COBBLEFROST.get()), RecipeCategory.MISC, FRBlocks.HIELOSTONE.get(), 0.1f, 200)
                .group("hielostone")
                .unlockedBy(getHasName(FRBlocks.COBBLEFROST.get()), has(FRBlocks.COBBLEFROST.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hielostone_smelting"));
        // Stone from smelting Hielostone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRBlocks.HIELOSTONE.get()), RecipeCategory.MISC, Blocks.STONE, 0.1f, 200)
                .group("stone")
                .unlockedBy(getHasName(FRBlocks.HIELOSTONE.get()), has(FRBlocks.HIELOSTONE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "stone_from_smelting_hielostone"));

        // Bone from Frost Bone
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.FROST_BONE.get()), RecipeCategory.MISC, Items.BONE, 0.3f, 200)
                .group("frost_bone")
                .unlockedBy(getHasName(FRItems.FROST_BONE.get()), has(FRItems.FROST_BONE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frost_bone_smelting"));

        // Cracked bricks
        RecipeHelper.createCrackedBrick(exporter, Blocks.RED_NETHER_BRICKS, FRBlocks.CRACKED_RED_NETHER_BRICKS.get(), "cracked_red_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.CRACKED_BLUE_NETHER_BRICKS.get(), "cracked_blue_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.CRACKED_PURPLE_NETHER_BRICKS.get(), "cracked_purple_nether_bricks");
        RecipeHelper.createCrackedBrick(exporter, FRBlocks.CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_CRAGULSTANE_BRICKS.get(), "cracked_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_BRIMMED_CRAGULSTANE_BRICKS.get(), "cracked_brimmed_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_ORANGE_CRAGULSTANE_BRICKS.get(), "cracked_orange_cragulstane_bricks");
        RecipeHelper.createCrackedBrick(exporter, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.CRACKED_TYRIAN_CRAGULSTANE_BRICKS.get(), "cracked_tyrian_cragulstane_bricks");
    }

    // Blast Furn recipes
    // One by one ecause offerBlasting has been nothing but horrible to work with
    private void blasting(RecipeOutput exporter)
    {
        // Cobalt Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRItems.RAW_COBALT.get()), RecipeCategory.MISC, FRItems.COBALT_INGOT.get(), 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(FRItems.RAW_COBALT.get()), has(FRItems.RAW_COBALT.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_raw_cobalt"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.COBALT_ORE.get()), RecipeCategory.MISC, FRItems.COBALT_INGOT.get(), 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(FRBlocks.COBALT_ORE.get()), has(FRBlocks.COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_cobalt_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.DEEPSLATE_COBALT_ORE.get()), RecipeCategory.MISC, FRItems.COBALT_INGOT.get(), 1.4f, 100)
                .group("cobalt_ingot")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_COBALT_ORE.get()), has(FRBlocks.DEEPSLATE_COBALT_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cobalt_ingot_from_blasting_deepslate_cobalt_ore"));

        // Verdinite Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRItems.RAW_VERDINITE.get()), RecipeCategory.MISC, FRItems.VERDINITE_INGOT.get(), 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(FRItems.RAW_VERDINITE.get()), has(FRItems.RAW_VERDINITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_raw_verdinite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.VERDINITE_ORE.get()), RecipeCategory.MISC, FRItems.VERDINITE_INGOT.get(), 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(FRBlocks.VERDINITE_ORE.get()), has(FRBlocks.VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_verdinite_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.DEEPSLATE_VERDINITE_ORE.get()), RecipeCategory.MISC, FRItems.VERDINITE_INGOT.get(), 1.4f, 100)
                .group("verdinite_ingot")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_VERDINITE_ORE.get()), has(FRBlocks.DEEPSLATE_VERDINITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "verdinite_ingot_from_blasting_deepslate_verdinite_ore"));

        // Raw Frostite
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRItems.RAW_FROSTITE.get()), RecipeCategory.MISC, FRItems.FROSTITE_INGOT.get(), 2.0f, 100)
                .group("frostite_ingot")
                .unlockedBy(getHasName(FRItems.RAW_FROSTITE.get()), has(FRItems.RAW_FROSTITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "frostite_ingot_from_blasting_raw_frostite"));

        // Vivulite Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRItems.RAW_VIVULITE.get()), RecipeCategory.MISC, FRItems.VIVULITE_INGOT.get(), 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(FRItems.RAW_VIVULITE.get()), has(FRItems.RAW_VIVULITE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_raw_vivulite"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.VIVULITE_ORE.get()), RecipeCategory.MISC, FRItems.VIVULITE_INGOT.get(), 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(FRBlocks.VIVULITE_ORE.get()), has(FRBlocks.VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_vivulite_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.DEEPSLATE_VIVULITE_ORE.get()), RecipeCategory.MISC, FRItems.VIVULITE_INGOT.get(), 1.4f, 100)
                .group("vivulite_ingot")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_VIVULITE_ORE.get()), has(FRBlocks.DEEPSLATE_VIVULITE_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "vivulite_ingot_from_blasting_deepslate_vivulite_ore"));

        // Black Emerald
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, FRItems.BLACK_EMERALD.get(), 1f, 100)
                .group("black_emerald")
                .unlockedBy(getHasName(FRBlocks.BLACK_EMERALD_ORE.get()), has(FRBlocks.BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_blasting_black_emerald_ore"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), RecipeCategory.MISC, FRItems.BLACK_EMERALD.get(), 1f, 100)
                .group("black_emerald")
                .unlockedBy(getHasName(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()), has(FRBlocks.DEEPSLATE_BLACK_EMERALD_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "black_emerald_from_blasting_deepslate_black_emerald_ore"));

        // Brimtan Ores
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRItems.BRIMTAN_CLUSTER.get()), RecipeCategory.MISC, FRItems.BRIMTAN_NUGGET.get(), 1.4f, 200)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(FRItems.BRIMTAN_CLUSTER.get()), has(FRItems.BRIMTAN_CLUSTER.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_cluster"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(FRBlocks.BRIMTAN_ORE.get()), RecipeCategory.MISC, FRItems.BRIMTAN_NUGGET.get(), 1.4f, 200)
                .group("brimtan_nugget")
                .unlockedBy(getHasName(FRBlocks.BRIMTAN_ORE.get()), has(FRBlocks.BRIMTAN_ORE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "brimtan_nugget_from_blasting_brimtan_ore"));
    }

    // Smoker recipes
    private void smoking(RecipeOutput exporter)
    {
        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(FRItems.MARSHMALLOW.get()), RecipeCategory.FOOD, FRItems.ROASTED_MARSHMALLOW.get(), 0.2f, 50)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(FRItems.MARSHMALLOW.get()), has(FRItems.MARSHMALLOW.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_smoking"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(FRItems.GUARDIAN_SLICE.get()), RecipeCategory.FOOD, FRItems.COOKED_GUARDIAN_SLICE.get(), 0.35f, 100)
                .unlockedBy(getHasName(FRItems.GUARDIAN_SLICE.get()), has(FRItems.GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_smoking"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(FRItems.ELDER_GUARDIAN_SLICE.get()), RecipeCategory.FOOD, FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), 0.35f, 100)
                .unlockedBy(getHasName(FRItems.ELDER_GUARDIAN_SLICE.get()), has(FRItems.ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_smoking"));
    }

    // Campfire recipes
    private void campfire(RecipeOutput exporter)
    {
        // Roasted Marshmallow
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(FRItems.MARSHMALLOW.get()), RecipeCategory.FOOD, FRItems.ROASTED_MARSHMALLOW.get(), 0.0f, 300)
                .group("roasted_marshmallow")
                .unlockedBy(getHasName(FRItems.MARSHMALLOW.get()), has(FRItems.MARSHMALLOW.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "roasted_marshmallow_campfire"));

        // Cooked Guardian Slice
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(FRItems.GUARDIAN_SLICE.get()), RecipeCategory.FOOD, FRItems.COOKED_GUARDIAN_SLICE.get(), 0.0f, 600)
                .unlockedBy(getHasName(FRItems.GUARDIAN_SLICE.get()), has(FRItems.GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_guardian_slice_campfire"));
        // Cooked Elder Guardian Slice
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(FRItems.ELDER_GUARDIAN_SLICE.get()), RecipeCategory.FOOD, FRItems.COOKED_ELDER_GUARDIAN_SLICE.get(), 0.0f, 600)
                .unlockedBy(getHasName(FRItems.ELDER_GUARDIAN_SLICE.get()), has(FRItems.ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "cooked_elder_guardian_slice_campfire"));
    }

    // Smithing recipes
    private void smithing(RecipeOutput exporter)
    {
        // TRIMS
        RecipeHelper.streamCustomTemplates().forEach(template -> trimSmithing(exporter, template.template(), template.id()));

        // Obsidian items.
        RecipeHelper.templateUpgrade(exporter, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.OBSIDIAN_CASING.get(),
                Items.GOLDEN_SWORD, RecipeCategory.COMBAT, FRItems.OBSIDIAN_SWORD.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.OBSIDIAN_CASING.get(),
                Items.GOLDEN_AXE, RecipeCategory.TOOLS, FRItems.OBSIDIAN_AXE.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.OBSIDIAN_CASING.get(),
                Items.GOLDEN_PICKAXE, RecipeCategory.TOOLS, FRItems.OBSIDIAN_PICKAXE.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.OBSIDIAN_CASING.get(),
                Items.GOLDEN_HOE, RecipeCategory.TOOLS, FRItems.OBSIDIAN_HOE.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.OBSIDIAN_CASING.get(),
                Items.GOLDEN_SHOVEL, RecipeCategory.TOOLS, FRItems.OBSIDIAN_SHOVEL.get());

        // Brimtan Items
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_SWORD.get(),
                FRItems.VIVULITE_SWORD.get(), RecipeCategory.COMBAT, FRItems.BRIMTAN_SWORD.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_SHOVEL.get(),
                FRItems.VIVULITE_SHOVEL.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_SHOVEL.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_PICKAXE.get(),
                FRItems.VIVULITE_PICKAXE.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_PICKAXE.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_AXE.get(),
                FRItems.VIVULITE_AXE.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_AXE.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_HOE.get(),
                FRItems.VIVULITE_HOE.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_HOE.get());

        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_HELMET_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_HELMET.get(),
                FRItems.VIVULITE_HELMET.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_HELMET.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_CHESTPLATE_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_CHESTPLATE.get(),
                FRItems.VIVULITE_CHESTPLATE.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_CHESTPLATE.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_LEGGINGS_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_LEGGINGS.get(),
                FRItems.VIVULITE_LEGGINGS.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_LEGGINGS.get());
        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_BOOTS_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.BRIMTAN_SHELL_BOOTS.get(),
                FRItems.VIVULITE_BOOTS.get(), RecipeCategory.TOOLS, FRItems.BRIMTAN_BOOTS.get());
    }

    // Stonecutting recipes
    private void stonecutting(RecipeOutput exporter)
    {
        // Stone-Like Bricks
        RecipeHelper.createStoneBrickRecipes(exporter, FRBlocks.CRAGULSTANE.get(), FRBlocks.CRAGULSTANE_BRICKS.get(), "cragulstane_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, FRBlocks.HIELOSTONE.get(), FRBlocks.HIELOSTONE_BRICKS.get(), "hielostone_bricks");
        RecipeHelper.createStoneBrickRecipes(exporter, FRBlocks.HIELOSTONE_BRICKS.get(), FRBlocks.HIELOSTONE_TILES.get(), "hielostone_tiles");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(FRBlocks.HIELOSTONE.get()), RecipeCategory.BUILDING_BLOCKS, FRBlocks.HIELOSTONE_TILES.get())
                .unlockedBy(getHasName(FRBlocks.HIELOSTONE.get()), has(FRBlocks.HIELOSTONE.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hielostone_tiles_from_stonecutting_hielostone"));

        // Stairs (also handles crafting table)
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_STAIRS.get(), "blue_nether_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_STAIRS.get(), "purple_nether_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.CRAGULSTANE_BRICKS.get(), FRBlocks.CRAGULSTANE_BRICK_STAIRS.get(), "cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_STAIRS.get(), "brimmed_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_STAIRS.get(), "orange_cragulstane_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_STAIRS.get(), "tyrian_cragulstane_brick_stairs");

        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.NACRE_BRICKS.get(), FRBlocks.NACRE_BRICK_STAIRS.get(), "nacre_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.TOWER_BRICKS.get(), FRBlocks.TOWER_BRICK_STAIRS.get(), "tower_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.MOSSY_TOWER_BRICKS.get(), FRBlocks.MOSSY_TOWER_BRICK_STAIRS.get(), "mossy_tower_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.PALE_PRISMARINE.get(), FRBlocks.PALE_PRISMARINE_STAIRS.get(), "pale_prismarine_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.PALE_PRISMARINE_BRICKS.get(), FRBlocks.PALE_PRISMARINE_BRICK_STAIRS.get(), "pale_prismarine_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.DEEP_PALE_PRISMARINE.get(), FRBlocks.DEEP_PALE_PRISMARINE_STAIRS.get(), "deep_pale_prismarine_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.TURTLE_SCUTE_BRICKS.get(), FRBlocks.TURTLE_SCUTE_BRICK_STAIRS.get(), "turtle_scute_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.HIELOSTONE.get(), FRBlocks.HIELOSTONE_STAIRS.get(), "hielostone_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.HIELOSTONE_BRICKS.get(), FRBlocks.HIELOSTONE_BRICK_STAIRS.get(), "hielostone_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.HIELOSTONE_TILES.get(), FRBlocks.HIELOSTONE_TILE_STAIRS.get(), "hielostone_tile_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.HIELOSTONE_PLATES.get(), FRBlocks.HIELOSTONE_PLATE_STAIRS.get(), "hielostone_plate_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.COBBLEFROST.get(), FRBlocks.COBBLEFROST_STAIRS.get(), "cobblefrost_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.CRUSTY_SAND_BRICKS.get(), FRBlocks.CRUSTY_SAND_BRICK_STAIRS.get(), "crusty_sand_brick_stairs");
        RecipeHelper.createStairsBothRecipes(exporter, FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_STAIRS.get(), "crusty_red_sand_brick_stairs");

        // Slabs (also handles crafting table)
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_SLAB.get(), "blue_nether_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(), "purple_nether_brick_slab");

        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.CRAGULSTANE_BRICKS.get(), FRBlocks.CRAGULSTANE_BRICK_SLAB.get(), "cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(), "brimmed_cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(), "orange_cragulstane_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(), "tyrian_cragulstane_brick_slab");

        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.NACRE_BRICKS.get(), FRBlocks.NACRE_BRICK_SLAB.get(), "nacre_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.TOWER_BRICKS.get(), FRBlocks.TOWER_BRICK_SLAB.get(), "tower_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.MOSSY_TOWER_BRICKS.get(), FRBlocks.MOSSY_TOWER_BRICK_SLAB.get(), "mossy_tower_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.PALE_PRISMARINE.get(), FRBlocks.PALE_PRISMARINE_SLAB.get(), "pale_prismarine_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.PALE_PRISMARINE_BRICKS.get(), FRBlocks.PALE_PRISMARINE_BRICK_SLAB.get(), "pale_prismarine_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.DEEP_PALE_PRISMARINE.get(), FRBlocks.DEEP_PALE_PRISMARINE_SLAB.get(), "deep_pale_prismarine_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.TURTLE_SCUTE_BRICKS.get(), FRBlocks.TURTLE_SCUTE_BRICK_SLAB.get(), "turtle_scute_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.HIELOSTONE.get(), FRBlocks.HIELOSTONE_SLAB.get(), "hielostone_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.HIELOSTONE_BRICKS.get(), FRBlocks.HIELOSTONE_BRICK_SLAB.get(), "hielostone_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.HIELOSTONE_TILES.get(), FRBlocks.HIELOSTONE_TILE_SLAB.get(), "hielostone_tile_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.HIELOSTONE_PLATES.get(), FRBlocks.HIELOSTONE_PLATE_SLAB.get(), "hielostone_plate_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.COBBLEFROST.get(), FRBlocks.COBBLEFROST_SLAB.get(), "cobblefrost_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.CRUSTY_SAND_BRICKS.get(), FRBlocks.CRUSTY_SAND_BRICK_SLAB.get(), "crusty_sand_brick_slab");
        RecipeHelper.createSlabBothRecipes(exporter, FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_SLAB.get(), "crusty_red_sand_brick_slab");

        // Walls (also handles crafting table)
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_WALL.get(), "blue_nether_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_WALL.get(), "purple_nether_brick_wall");

        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.CRAGULSTANE_BRICKS.get(), FRBlocks.CRAGULSTANE_BRICK_WALL.get(), "cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_WALL.get(), "brimmed_cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_WALL.get(), "orange_cragulstane_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_WALL.get(), "tyrian_cragulstane_brick_wall");

        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.NACRE_BRICKS.get(), FRBlocks.NACRE_BRICK_WALL.get(), "nacre_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.TOWER_BRICKS.get(), FRBlocks.TOWER_BRICK_WALL.get(), "tower_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.MOSSY_TOWER_BRICKS.get(), FRBlocks.MOSSY_TOWER_BRICK_WALL.get(), "mossy_tower_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.PALE_PRISMARINE.get(), FRBlocks.PALE_PRISMARINE_WALL.get(), "pale_prismarine_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.TURTLE_SCUTE_BRICKS.get(), FRBlocks.TURTLE_SCUTE_BRICK_WALL.get(), "turtle_scute_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.HIELOSTONE.get(), FRBlocks.HIELOSTONE_WALL.get(), "hielostone_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.HIELOSTONE_BRICKS.get(), FRBlocks.HIELOSTONE_BRICK_WALL.get(), "hielostone_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.HIELOSTONE_TILES.get(), FRBlocks.HIELOSTONE_TILE_WALL.get(), "hielostone_tile_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.HIELOSTONE_PLATES.get(), FRBlocks.HIELOSTONE_PLATE_WALL.get(), "hielostone_plate_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.COBBLEFROST.get(), FRBlocks.COBBLEFROST_WALL.get(), "cobblefrost_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.CRUSTY_SAND_BRICKS.get(), FRBlocks.CRUSTY_SAND_BRICK_WALL.get(), "crusty_sand_brick_wall");
        RecipeHelper.createWallBothRecipes(exporter, FRBlocks.CRUSTY_RED_SAND_BRICKS.get(), FRBlocks.CRUSTY_RED_SAND_BRICK_WALL.get(), "crusty_red_sand_brick_wall");

        // Chisels (also handles crafting table)
        RecipeHelper.createChiselRecipesMulti(exporter, FRBlocks.CRAGULSTANE.get(),
                FRBlocks.CRAGULSTANE_BRICKS.get(),
                FRBlocks.CRAGULSTANE_BRICK_SLAB.get(),
                FRBlocks.CHISELED_CRAGULSTANE_BRICKS.get(),
                "chiseled_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, FRBlocks.BRIMMED_CRAGULSTANE_BRICKS.get(), FRBlocks.BRIMMED_CRAGULSTANE_BRICK_SLAB.get(),
                FRBlocks.CHISELED_BRIMMED_CRAGULSTANE_BRICKS.get(),
                "chiseled_brimmed_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, FRBlocks.ORANGE_CRAGULSTANE_BRICKS.get(), FRBlocks.ORANGE_CRAGULSTANE_BRICK_SLAB.get(),
                FRBlocks.CHISELED_ORANGE_CRAGULSTANE_BRICKS.get(),
                "chiseled_orange_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, FRBlocks.TYRIAN_CRAGULSTANE_BRICKS.get(), FRBlocks.TYRIAN_CRAGULSTANE_BRICK_SLAB.get(),
                FRBlocks.CHISELED_TYRIAN_CRAGULSTANE_BRICKS.get(),
                "chiseled_tyrian_cragulstane_bricks");
        RecipeHelper.createChiselRecipes(exporter, Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICK_SLAB,
                FRBlocks.CHISELED_RED_NETHER_BRICKS.get(),
                "chiseled_red_nether_bricks");
        RecipeHelper.createChiselRecipes(exporter, FRBlocks.BLUE_NETHER_BRICKS.get(), FRBlocks.BLUE_NETHER_BRICK_SLAB.get(),
                FRBlocks.CHISELED_BLUE_NETHER_BRICKS.get(),
                "chiseled_blue_nether_bricks");
        RecipeHelper.createChiselRecipes(exporter, FRBlocks.PURPLE_NETHER_BRICKS.get(), FRBlocks.PURPLE_NETHER_BRICK_SLAB.get(),
                FRBlocks.CHISELED_PURPLE_NETHER_BRICKS.get(),
                "chiseled_purple_nether_bricks");
        RecipeHelper.createChiselRecipesMulti(exporter, FRBlocks.HIELOSTONE.get(),
                FRBlocks.HIELOSTONE_BRICKS.get(),
                FRBlocks.HIELOSTONE_BRICK_SLAB.get(),
                FRBlocks.HIELOSTONE_PLATES.get(),
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
                        FRItems.WARP_ARROW_ARROWHEAD.get(),
                        FRItems.WARP_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/warp_arrow.png")
                )
                .unlockedBy(getHasName(FRItems.WARP_ARROW_ARROWHEAD.get()), has(FRItems.WARP_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Bouncy Arrow
        FletchingRecipeBuilder.create(
                        FRItems.BOUNCY_ARROW_ARROWHEAD.get(),
                        FRItems.BOUNCY_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/bouncy_arrow.png")
                )
                .unlockedBy(getHasName(FRItems.BOUNCY_ARROW_ARROWHEAD.get()), has(FRItems.BOUNCY_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Subzero Arrow
        FletchingRecipeBuilder.create(
                        FRItems.SUBZERO_ARROW_ARROWHEAD.get(),
                        FRItems.SUBZERO_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/subzero_arrow.png")
                )
                .unlockedBy(getHasName(FRItems.SUBZERO_ARROW_ARROWHEAD.get()), has(FRItems.SUBZERO_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Spectral Arrow
        FletchingRecipeBuilder.create(
                        FRItems.SPECTRAL_ARROW_ARROWHEAD.get(),
                        Items.SPECTRAL_ARROW,
                        ResourceLocation.withDefaultNamespace("textures/entity/projectiles/spectral_arrow.png")
                )
                .unlockedBy(getHasName(FRItems.SPECTRAL_ARROW_ARROWHEAD.get()), has(FRItems.SPECTRAL_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Dynamite Arrow
        FletchingRecipeBuilder.create(
                        FRItems.DYNAMITE_ARROW_ARROWHEAD.get(),
                        FRItems.DYNAMITE_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/dynamite_arrow.png")
                )
                .unlockedBy(getHasName(FRItems.DYNAMITE_ARROW_ARROWHEAD.get()), has(FRItems.DYNAMITE_ARROW_ARROWHEAD.get()))
                .save(exporter);
        // Prismarine Arrow
        FletchingRecipeBuilder.create(
                        FRItems.PRISMARINE_ARROW_ARROWHEAD.get(),
                        FRItems.PRISMARINE_ARROW.get(),
                        ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/projectiles/prismarine_arrow.png")
                )
                .unlockedBy(getHasName(FRItems.PRISMARINE_ARROW_ARROWHEAD.get()), has(FRItems.PRISMARINE_ARROW_ARROWHEAD.get()))
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
