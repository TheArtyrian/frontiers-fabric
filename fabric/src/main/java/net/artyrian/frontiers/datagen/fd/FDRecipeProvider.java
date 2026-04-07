package net.artyrian.frontiers.datagen.fd;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.artyrian.frontiers.reg.content.FRItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.concurrent.CompletableFuture;

public abstract class FDRecipeProvider extends FabricRecipeProvider
{
    public FDRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    public static void crafting(RecipeOutput exporter)
    {
        RecipeHelper.knifeHelper(exporter, FRItems.COBALT_INGOT.get(), FDItem.COBALT_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, FRItems.MOURNING_GOLD_INGOT.get(), FDItem.MOURNING_GOLD_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, FRItems.FROSTITE_INGOT.get(), FDItem.FROSTITE_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, FRItems.VERDINITE_INGOT.get(), FDItem.VERDINITE_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, FRItems.VIVULITE_INGOT.get(), FDItem.VIVULITE_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, FRItems.BRIMTAN_INGOT.get(), FDItem.BRIMTAN_SHELL_KNIFE.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_QUICKSAND.get(), 2)
                .requires(FRBlocks.QUICKSAND.get())
                .requires(FRBlocks.QUICKSAND.get())
                .requires(FDItem.STRAW.get())
                .unlockedBy(getHasName(FRBlocks.QUICKSAND.get()), has(FRBlocks.QUICKSAND.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crusted_quicksand_with_straw"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_RED_QUICKSAND.get(), 2)
                .requires(FRBlocks.RED_QUICKSAND.get())
                .requires(FRBlocks.RED_QUICKSAND.get())
                .requires(FDItem.STRAW.get())
                .unlockedBy(getHasName(FRBlocks.RED_QUICKSAND.get()), has(FRBlocks.RED_QUICKSAND.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crusted_red_quicksand_with_straw"));
    }

    // Furn recipes
    public static void smelting(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(FRItems.GOLDEN_EGG.get()), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG.get(), 0.3f, 200)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(FRItems.GOLDEN_EGG.get()), has(FRItems.GOLDEN_EGG.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_smelting"));
    }

    // Blast Furn recipes
    public static void blasting(RecipeOutput exporter)
    {

    }

    // Smoker recipes
    public static void smoking(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(FRItems.GOLDEN_EGG.get()), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG.get(), 0.3f, 100)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(FRItems.GOLDEN_EGG.get()), has(FRItems.GOLDEN_EGG.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_smoking"));
    }

    // Campfire recipes
    public static void campfire(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(FRItems.GOLDEN_EGG.get()), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG.get(), 0.0f, 600)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(FRItems.GOLDEN_EGG.get()), has(FRItems.GOLDEN_EGG.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_campfire"));
    }

    // Smithing recipes
    public static void smithing(RecipeOutput exporter)
    {
        RecipeHelper.templateUpgrade(exporter, FRItems.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), FRItems.OBSIDIAN_CASING.get(),
                FDItem.GOLDEN_KNIFE.get(), RecipeCategory.COMBAT, FDItem.OBSIDIAN_KNIFE.get());

        RecipeHelper.templateUpgrade(exporter, FRItems.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FDItem.BRIMTAN_SHELL_KNIFE.get(),
                FDItem.VIVULITE_KNIFE.get(), RecipeCategory.COMBAT, FDItem.BRIMTAN_KNIFE.get());
    }

    // Stonecutting recipes
    public static void stonecutting(RecipeOutput exporter)
    {

    }
}
