package net.artyrian.frontiers.datagen.bfares;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.bountifulfares.BFItem;
import net.artyrian.frontiers.reg.content.FRBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public abstract class BFRecipeProvider extends FabricRecipeProvider
{
    public BFRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    // "FabricDataGen is acting funky. - Earthbound, 1997"
    public static void crafting(RecipeOutput exporter)
    {
        //RecipeHelper.createLumen(exporter, BFItem.FELDSPAR.get(), BFBlock.FELDSPAR_LUMEN.get(), ModItem.LIGHTNING_IN_A_BOTTLE.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_QUICKSAND.get())
                .requires(FRBlocks.QUICKSAND.get())
                .requires(BFItem.COCONUT_COIR.get())
                .unlockedBy(getHasName(FRBlocks.QUICKSAND.get()), has(FRBlocks.QUICKSAND.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crusted_quicksand_with_coir"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, FRBlocks.CRUSTED_RED_QUICKSAND.get())
                .requires(FRBlocks.RED_QUICKSAND.get())
                .requires(BFItem.COCONUT_COIR.get())
                .unlockedBy(getHasName(FRBlocks.RED_QUICKSAND.get()), has(FRBlocks.RED_QUICKSAND.get()))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "crusted_red_quicksand_with_coir"));
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
