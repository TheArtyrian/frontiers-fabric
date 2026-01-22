package net.artyrian.frontiers.compat.bountifulfares;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.datagen.RecipeHelper;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
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
        //ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BFBlock.FELDSPAR_LUMEN, 2)
        //        .group("ore_lumen")
        //        .pattern(" I ")
        //        .pattern("RXR")
        //        .pattern(" L ")
        //        .input('I', BFItem.FELDSPAR)
        //        .input('R', Items.REDSTONE)
        //        .input('X', Items.GLOWSTONE)
        //        .input('L', ModItem.LIGHTNING_IN_A_BOTTLE)
        //        .criterion(hasItem(ModItem.LIGHTNING_IN_A_BOTTLE), conditionsFromItem(ModItem.LIGHTNING_IN_A_BOTTLE))
        //        .offerTo(exporter);
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
