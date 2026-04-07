package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.dyemods.DyeModDummyItem;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/** A small package of common recipe archetypes, useful for fast recipe offerings. */
public class RecipeHelper extends ModRecipeProvider
{
    public RecipeHelper(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    /** Template Upgrade automation - provide template, material, item, the category and output. */
    public static void templateUpgrade(RecipeOutput exporter, Item template, Item upgrade_mat, Item input, RecipeCategory category, Item result)
    {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(template), Ingredient.of(input), Ingredient.of(upgrade_mat), category, result
                )
                .unlocks(getHasName(upgrade_mat), has(upgrade_mat))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, getItemName(result) + "_smithing"));
    }

    /** Creates a recipe for 5 provided tools using a single material. */
    public static void toolHelper(RecipeOutput exporter, Item tool_material, Item axe, Item sword, Item shovel, Item pickaxe, Item hoe)
    {
        // Axe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("## ")
                .pattern("#S ")
                .pattern(" S ")
                .define('#', tool_material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Sword
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sword)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" S ")
                .define('#', tool_material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Shovel
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', tool_material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', tool_material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("## ")
                .pattern(" S ")
                .pattern(" S ")
                .define('#', tool_material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
    }

    /** Creates a recipe for 4 provided armors using a single material. */
    public static void armorHelper(RecipeOutput exporter, Item tool_material, Item helmet, Item chest, Item legs, Item boots)
    {
        // Necro Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .define('#', tool_material)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Necro Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chest)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', tool_material)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Necro Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, legs)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', tool_material)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
        // Necro Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .define('#', tool_material)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
    }

    /** Intended for use with Farmer's Delight knives - creates a knife with the specific material. */
    public static void knifeHelper(RecipeOutput exporter, Item tool_material, Item knife)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, knife)
                .pattern(" # ")
                .pattern(" S ")
                .pattern("   ")
                .define('#', tool_material)
                .define('S', Items.STICK)
                .unlockedBy(getHasName(tool_material), has(tool_material))
                .save(exporter);
    }

    /** Creates both a crafting table and stonecutter recipe for a stairs item.
     <p>(either vanilla has implementation for this that i didn't see or it just freaking sucks lol)*/
    public static void createStairsBothRecipes(RecipeOutput exporter, Block material, Block output, String name)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X  ")
                .pattern("XX ")
                .pattern("XXX")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, output)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a wall item. */
    public static void createWallBothRecipes(RecipeOutput exporter, Block material, Block output, String name)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 6)
                .pattern("XXX")
                .pattern("XXX")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.MISC, output)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a slab item. */
    public static void createSlabBothRecipes(RecipeOutput exporter, Block material, Block output, String name)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 6)
                .pattern("XXX")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, output, 2)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a stone brick-like item. */
    public static void createStoneBrickRecipes(RecipeOutput exporter, Block material, Block output, String name)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("XX")
                .pattern("XX")
                .define('X', material)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), RecipeCategory.BUILDING_BLOCKS, output)
                .unlockedBy(getHasName(material), has(material))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates both a crafting table and stonecutter recipe for a chiseled block. */
    public static void createChiselRecipes(RecipeOutput exporter, Block base, Block slab, Block output, String name)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X")
                .pattern("X")
                .define('X', slab)
                .unlockedBy(getHasName(slab), has(slab))
                .save(exporter);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), RecipeCategory.BUILDING_BLOCKS, output)
                .unlockedBy(getHasName(base), has(base))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting"));
    }

    /** Creates all possible recipes for a chiseled stone block - meant for bricks made from stones. */
    public static void createChiselRecipesMulti(RecipeOutput exporter, Block base, Block brick, Block slab, Block output, String name)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 4)
                .pattern("X")
                .pattern("X")
                .define('X', slab)
                .unlockedBy(getHasName(slab), has(slab))
                .save(exporter);

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), RecipeCategory.BUILDING_BLOCKS, output)
                .unlockedBy(getHasName(base), has(base))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting_stone"));

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(brick), RecipeCategory.BUILDING_BLOCKS, output)
                .unlockedBy(getHasName(brick), has(brick))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, name + "_from_stonecutting_bricks"));
    }

    /** Create Nether Brick-like fence recipes. */
    public static void createBrickFence(RecipeOutput exporter, Block sides, Item middle, Block output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output, 6)
                .pattern("XDX")
                .pattern("XDX")
                .define('X', sides)
                .define('D', middle)
                .unlockedBy(getHasName(sides), has(sides))
                .save(exporter);
    }

    /** Create fence gate recipes. */
    public static void createFenceGate(RecipeOutput exporter, Item sides, Block middle, Block output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, output, 1)
                .pattern("XDX")
                .pattern("XDX")
                .define('X', sides)
                .define('D', middle)
                .unlockedBy(getHasName(middle), has(middle))
                .save(exporter);
    }

    /** Fast method for making cracked brick recipes. */
    public static void createCrackedBrick(RecipeOutput exporter, Block input, Block output, String group)
    {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, output, 0.1f, 200)
                .group(group)
                .unlockedBy(getHasName(input), has(input))
                .save(exporter);
    }

    /** Fast method for making lumen recipes - all fall within the same group.
     * <p>"looking_for" is the item needed to discover the recipe - allows for lumens of Hardmode kind to be found later. */
    public static void createLumen(RecipeOutput exporter, Item material, Block output, Item looking_for)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, output, 2)
                .group("ore_lumen")
                .pattern(" I ")
                .pattern("RXR")
                .pattern(" L ")
                .define('I', material)
                .define('R', Items.REDSTONE)
                .define('X', Items.GLOWSTONE)
                .define('L', ModItem.LIGHTNING_IN_A_BOTTLE.get())
                .unlockedBy(getHasName(looking_for), has(looking_for))
                .save(exporter);
    }

    /** Fast method for making wreaths. */
    public static void createWreath(RecipeOutput exporter, Item leaves, Item decor, Block output)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, output)
                .group("wreaths")
                .pattern("OIO")
                .pattern("I I")
                .pattern("OIO")
                .define('I', leaves)
                .define('O', decor)
                .unlockedBy(getHasName(leaves), has(leaves))
                .save(exporter);
    }

    public static void createReversibleSix(RecipeOutput exporter, Item block, Item ingot)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(exporter, Frontiers.id(getId(block) + "_from_" + getId(ingot)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 6)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(exporter, Frontiers.id(getId(ingot) + "_from_" + getId(block)));
    }

    public static void createReversible(RecipeOutput exporter, Item block, Item ingot)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(exporter, Frontiers.id(getId(block) + "_from_" + getId(ingot)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .save(exporter, Frontiers.id(getId(ingot) + "_from_" + getId(block)));
    }

    public static void createReversibleWithItemGroup(RecipeOutput exporter, Item block, Item ingot, String group)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ingot)
                .unlockedBy(getHasName(ingot), has(ingot))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,
                        getId(block) + "_from_" + getId(ingot)));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ingot, 9)
                .requires(block)
                .unlockedBy(getHasName(block), has(block))
                .group(group)
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,
                        getId(ingot) + "_from_" + getId(block)));
    }

    /** Helper method for making ball recipes. */
    public static void doBallRecipes(RecipeOutput exporter)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.BALL.get())
                .pattern("S  ")
                .pattern("W  ")
                .pattern("X  ")
                .define('S', Items.STRING)
                .define('X', Items.RABBIT_HIDE)
                .define('W', ItemTags.WOOL)
                .unlockedBy(getHasName(Items.RABBIT_HIDE), has(Items.RABBIT_HIDE))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItem.BOUNCY_BALL.get())
                .pattern(" O ")
                .pattern("OWO")
                .pattern(" O ")
                .define('O', Items.SLIME_BALL)
                .define('W', ModItem.BALL.get())
                .unlockedBy(getHasName(ModItem.BALL.get()), has(ModItem.BALL.get()))
                .save(exporter);

        // Vanilla Dyes
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.WHITE_DYE, ModItem.COLOR_BALLS.get(DyeColor.WHITE).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.LIGHT_GRAY_DYE, ModItem.COLOR_BALLS.get(DyeColor.LIGHT_GRAY).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.GRAY_DYE, ModItem.COLOR_BALLS.get(DyeColor.GRAY).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.BLACK_DYE, ModItem.COLOR_BALLS.get(DyeColor.BLACK).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.RED_DYE, ModItem.COLOR_BALLS.get(DyeColor.RED).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.ORANGE_DYE, ModItem.COLOR_BALLS.get(DyeColor.ORANGE).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.YELLOW_DYE, ModItem.COLOR_BALLS.get(DyeColor.YELLOW).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.LIME_DYE, ModItem.COLOR_BALLS.get(DyeColor.LIME).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.GREEN_DYE, ModItem.COLOR_BALLS.get(DyeColor.GREEN).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.LIGHT_BLUE_DYE, ModItem.COLOR_BALLS.get(DyeColor.LIGHT_BLUE).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.BLUE_DYE, ModItem.COLOR_BALLS.get(DyeColor.BLUE).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.CYAN_DYE, ModItem.COLOR_BALLS.get(DyeColor.CYAN).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.MAGENTA_DYE, ModItem.COLOR_BALLS.get(DyeColor.MAGENTA).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.PINK_DYE, ModItem.COLOR_BALLS.get(DyeColor.PINK).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.PURPLE_DYE, ModItem.COLOR_BALLS.get(DyeColor.PURPLE).get(), "color_balls");
        RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), Items.BROWN_DYE, ModItem.COLOR_BALLS.get(DyeColor.BROWN).get(), "color_balls");

        if (Frontiers.DOING_DATAGEN)
        {
            // Delicate Dyes
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.CORAL_DYE.get(), DyeModDummyItem.CORAL_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.CANARY_DYE.get(), DyeModDummyItem.CANARY_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.WASABI_DYE.get(), DyeModDummyItem.WASABI_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.SACRAMENTO_DYE.get(), DyeModDummyItem.SACRAMENTO_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.SKY_DYE.get(), DyeModDummyItem.SKY_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.BLURPLE_DYE.get(), DyeModDummyItem.BLURPLE_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.SANGRIA_DYE.get(), DyeModDummyItem.SANGRIA_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.ROSE_DYE.get(), DyeModDummyItem.ROSE_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.UMBER_DYE.get(), DyeModDummyItem.UMBER_BALL.get(), "color_balls");
            RecipeHelper.createDyedItem(exporter, RecipeCategory.MISC, ModItem.BALL.get(), DyeModDummyItem.LAVENDER_DYE.get(), DyeModDummyItem.LAVENDER_BALL.get(), "color_balls");
        }
    }

    /** Stream custom templates. */
    public static Stream<VanillaRecipeProvider.TrimTemplate> streamCustomTemplates()
    {
        return Stream.of(
                        ModItem.PULSE_ARMOR_TRIM_SMITHING_TEMPLATE.get(),
                        ModItem.SLUDGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(),
                        ModItem.PHOTON_ARMOR_TRIM_SMITHING_TEMPLATE.get()
                )
                .map(template -> new VanillaRecipeProvider.TrimTemplate(template, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,
                        getItemName(template) + "_smithing_trim")));
    }

    /** Intended for duplicating smithing templates.*/
    public static void doTemplateDupe(RecipeOutput exporter, Item template, Item block)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, template, 2)
                .pattern("X#X")
                .pattern("XSX")
                .pattern("XXX")
                .define('#', template)
                .define('S', block)
                .define('X', Items.DIAMOND)
                .unlockedBy(getHasName(template), has(template))
                .save(exporter);
    }

    /** Intended for the Brimtan smithing templates.*/
    public static void doBrimtanTemplateDupe(RecipeOutput exporter, Item template)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, template, 2)
                .pattern("X#X")
                .pattern("XSX")
                .pattern("XXX")
                .define('#', template)
                .define('S', ModBlocks.GLOWING_OBSIDIAN.get())
                .define('X', ModItem.COBALT_INGOT.get())
                .group("brimtan_templates")
                .unlockedBy(getHasName(template), has(template))
                .save(exporter);
    }

    /** Helper for creating dyed items. */
    public static void createDyedItem(RecipeOutput exporter, RecipeCategory category, Item input, Item dye, Item output, String group)
    {
        ShapelessRecipeBuilder.shapeless(category, output)
                .group(group)
                .requires(input)
                .requires(dye)
                .unlockedBy(getHasName(dye), has(dye))
                .save(exporter);
    }

    public static String getId(Item item)
    {
        ResourceLocation x = BuiltInRegistries.ITEM.getKey(item.asItem());
        return x.getPath();
    }
}
