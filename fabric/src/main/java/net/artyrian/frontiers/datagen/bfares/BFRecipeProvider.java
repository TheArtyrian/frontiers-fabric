package net.artyrian.frontiers.datagen.bfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFBlock;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class BFRecipeProvider extends FabricRecipeProvider
{
    public BFRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
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
    }

    public static void crafting(RecipeOutput exporter)
    {
        // Lumens
        RecipeHelper.createLumen(exporter, BFItem.FELDSPAR.get(), BFBlock.FELDSPAR_LUMEN.get(), FRItems.LIGHTNING_IN_A_BOTTLE.get());

        // Wreaths
        RecipeHelper.createWreath(exporter, BFBlock.WALNUT_LEAVES.get().asItem(), BFItem.WALNUT.get(), BFBlock.WALNUT_WREATH.get());
        RecipeHelper.createWreath(exporter, BFBlock.HOARY_LEAVES.get().asItem(), BFItem.LAPISBERRIES.get(), BFBlock.HOARY_WREATH.get());
        RecipeHelper.createWreath(exporter, BFBlock.APPLE_LEAVES.get().asItem(), BFBlock.FLOWERING_APPLE_LEAVES.get().asItem(), BFBlock.APPLE_WREATH.get());
        RecipeHelper.createWreath(exporter, BFBlock.ORANGE_LEAVES.get().asItem(), BFBlock.FLOWERING_ORANGE_LEAVES.get().asItem(), BFBlock.ORANGE_WREATH.get());
        RecipeHelper.createWreath(exporter, BFBlock.LEMON_LEAVES.get().asItem(), BFBlock.FLOWERING_LEMON_LEAVES.get().asItem(), BFBlock.LEMON_WREATH.get());
        RecipeHelper.createWreath(exporter, BFBlock.PLUM_LEAVES.get().asItem(), BFBlock.FLOWERING_PLUM_LEAVES.get().asItem(), BFBlock.PLUM_WREATH.get());
        RecipeHelper.createWreath(exporter, BFBlock.GOLDEN_APPLE_LEAVES.get().asItem(), Items.GOLD_NUGGET, BFBlock.GOLDEN_WREATH.get());

        // Crusted Quicksand
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_QUICKSAND.get())
                .requires(FRBlocks.QUICKSAND.get())
                .requires(BFItem.COCONUT_COIR.get())
                .unlockedBy(getHasName(FRBlocks.QUICKSAND.get()), has(FRBlocks.QUICKSAND.get()))
                .save(exporter, Frontiers.id("crusted_quicksand_with_coir"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_RED_QUICKSAND.get())
                .requires(FRBlocks.RED_QUICKSAND.get())
                .requires(BFItem.COCONUT_COIR.get())
                .unlockedBy(getHasName(FRBlocks.RED_QUICKSAND.get()), has(FRBlocks.RED_QUICKSAND.get()))
                .save(exporter, Frontiers.id("crusted_red_quicksand_with_coir"));

        // Food
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BFItem.BREADED_GUARDIAN.get())
                .requires(FRItems.COOKED_GUARDIAN_SLICE.get())
                .requires(BFItem.FLOUR.get())
                .requires(BFItem.CITRUS_ESSENCE.get())
                .requires(Items.DRIED_KELP)
                .unlockedBy(getHasName(FRItems.COOKED_GUARDIAN_SLICE.get()), has(FRItems.COOKED_GUARDIAN_SLICE.get()))
                .save(exporter, Frontiers.id("breaded_guardian"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BFItem.BREADED_GUARDIAN.get(), 2)
                .requires(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get())
                .requires(BFItem.FLOUR.get())
                .requires(BFItem.CITRUS_ESSENCE.get())
                .requires(Items.DRIED_KELP)
                .unlockedBy(getHasName(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get()), has(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, Frontiers.id("breaded_guardian_from_elder"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BFItem.GUARDIAN_SOUP.get())
                .requires(FRItems.COOKED_GUARDIAN_SLICE.get())
                .requires(BFItem.LEMON.get())
                .requires(BFItem.PICKLED_SPONGEKIN.get())
                .requires(Items.DRIED_KELP)
                .requires(Items.BOWL)
                .unlockedBy(getHasName(FRItems.COOKED_GUARDIAN_SLICE.get()), has(FRItems.COOKED_GUARDIAN_SLICE.get()))
                .save(exporter, Frontiers.id("guardian_soup"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BFItem.ELDEN_BOWL.get())
                .requires(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get())
                .requires(BFItem.CITRUS_ESSENCE.get())
                .requires(BFItem.WALNUT.get())
                .requires(BFItem.ELDERBERRIES.get())
                .requires(BFItem.PICKLED_SPONGEKIN.get())
                .requires(Items.BOWL)
                .unlockedBy(getHasName(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get()), has(FRItems.COOKED_ELDER_GUARDIAN_SLICE.get()))
                .save(exporter, Frontiers.id("elden_bowl"));
    }

    // Furn recipes
    public static void smelting(RecipeOutput exporter)
    {

    }

    // Blast Furn recipes
    public static void blasting(RecipeOutput exporter)
    {

    }

    // Smoker recipes
    public static void smoking(RecipeOutput exporter)
    {

    }

    // Campfire recipes
    public static void campfire(RecipeOutput exporter)
    {

    }

    // Smithing recipes
    public static void smithing(RecipeOutput exporter)
    {

    }

    // Stonecutting recipes
    public static void stonecutting(RecipeOutput exporter)
    {

    }
}
