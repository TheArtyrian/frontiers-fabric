package net.artyrian.frontiers.datagen.compat;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
        RecipeHelper.knifeHelper(exporter, ModItem.COBALT_INGOT.get(), FDItem.COBALT_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, ModItem.MOURNING_GOLD_INGOT.get(), FDItem.MOURNING_GOLD_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, ModItem.FROSTITE_INGOT.get(), FDItem.FROSTITE_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, ModItem.VERDINITE_INGOT.get(), FDItem.VERDINITE_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, ModItem.VIVULITE_INGOT.get(), FDItem.VIVULITE_KNIFE.get());
        RecipeHelper.knifeHelper(exporter, ModItem.BRIMTAN_INGOT.get(), FDItem.BRIMTAN_SHELL_KNIFE.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CRUSTED_QUICKSAND.get(), 2)
                .requires(ModBlocks.QUICKSAND.get())
                .requires(ModBlocks.QUICKSAND.get())
                .requires(FDItem.STRAW.get())
                .unlockedBy(getHasName(ModBlocks.QUICKSAND.get()), has(ModBlocks.QUICKSAND.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crusted_quicksand_with_straw"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CRUSTED_RED_QUICKSAND.get(), 2)
                .requires(ModBlocks.RED_QUICKSAND.get())
                .requires(ModBlocks.RED_QUICKSAND.get())
                .requires(FDItem.STRAW.get())
                .unlockedBy(getHasName(ModBlocks.RED_QUICKSAND.get()), has(ModBlocks.RED_QUICKSAND.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crusted_red_quicksand_with_straw"));
    }

    // Furn recipes
    public static void smelting(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.GOLDEN_EGG.get()), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG.get(), 0.3f, 200)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(ModItem.GOLDEN_EGG.get()), has(ModItem.GOLDEN_EGG.get()))
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
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.GOLDEN_EGG.get()), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG.get(), 0.3f, 100)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(ModItem.GOLDEN_EGG.get()), has(ModItem.GOLDEN_EGG.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_smoking"));
    }

    // Campfire recipes
    public static void campfire(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.GOLDEN_EGG.get()), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG.get(), 0.0f, 600)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(ModItem.GOLDEN_EGG.get()), has(ModItem.GOLDEN_EGG.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_campfire"));
    }

    // Smithing recipes
    public static void smithing(RecipeOutput exporter)
    {
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE.get(), ModItem.OBSIDIAN_CASING.get(),
                FDItem.GOLDEN_KNIFE.get(), RecipeCategory.COMBAT, FDItem.OBSIDIAN_KNIFE.get());

        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE.get(), FDItem.BRIMTAN_SHELL_KNIFE.get(),
                FDItem.VIVULITE_KNIFE.get(), RecipeCategory.COMBAT, FDItem.BRIMTAN_KNIFE.get());
    }

    // Stonecutting recipes
    public static void stonecutting(RecipeOutput exporter)
    {

    }
}
