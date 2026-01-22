package net.artyrian.frontiers.compat.farmersdelight;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
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
        RecipeHelper.knifeHelper(exporter, ModItem.COBALT_INGOT, FDItem.COBALT_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.MOURNING_GOLD_INGOT, FDItem.MOURNING_GOLD_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.FROSTITE_INGOT, FDItem.FROSTITE_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.VERDINITE_INGOT, FDItem.VERDINITE_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.VIVULITE_INGOT, FDItem.VIVULITE_KNIFE);
        RecipeHelper.knifeHelper(exporter, ModItem.BRIMTAN_INGOT, FDItem.BRIMTAN_SHELL_KNIFE);
    }

    // Furn recipes
    public static void smelting(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItem.GOLDEN_EGG), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG, 0.3f, 200)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(ModItem.GOLDEN_EGG), has(ModItem.GOLDEN_EGG))
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
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItem.GOLDEN_EGG), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG, 0.3f, 100)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(ModItem.GOLDEN_EGG), has(ModItem.GOLDEN_EGG))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_smoking"));
    }

    // Campfire recipes
    public static void campfire(RecipeOutput exporter)
    {
        // Fried Golden Egg
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItem.GOLDEN_EGG), RecipeCategory.FOOD, FDItem.FRIED_GOLDEN_EGG, 0.0f, 600)
                .group("fried_golden_egg")
                .unlockedBy(getHasName(ModItem.GOLDEN_EGG), has(ModItem.GOLDEN_EGG))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "fried_golden_egg_campfire"));
    }

    // Smithing recipes
    public static void smithing(RecipeOutput exporter)
    {
        RecipeHelper.templateUpgrade(exporter, ModItem.OBSIDIAN_UPGRADE_SMITHING_TEMPLATE, ModItem.OBSIDIAN_CASING,
                FDItem.GOLDEN_KNIFE, RecipeCategory.COMBAT, FDItem.OBSIDIAN_KNIFE);

        RecipeHelper.templateUpgrade(exporter, ModItem.BRIMTAN_TOOL_UPGRADE_SMITHING_TEMPLATE, FDItem.BRIMTAN_SHELL_KNIFE,
                FDItem.VIVULITE_KNIFE, RecipeCategory.COMBAT, FDItem.BRIMTAN_KNIFE);
    }

    // Stonecutting recipes
    public static void stonecutting(RecipeOutput exporter)
    {

    }
}
