package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.definition.recipe.special.CobaltShieldDecorationRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModRecipes
{
    // Recipe Types
    public static final Supplier<RecipeType<ArrowFletchingRecipe>> ARROW_FLETCHING = register("arrow_fletching");

    // Serializers
    public static final Supplier<RecipeSerializer<ArrowFletchingRecipe>> ARROW_FLETCHING_SERIALIZER = regSerializer(
            "arrow_fletching", new ArrowFletchingRecipe.Serializer(ArrowFletchingRecipe::new)
    );
    public static final Supplier<RecipeSerializer<CobaltShieldDecorationRecipe>> COBALT_SHIELD_DECORATION = regSerializer(
            "crafting_special_cobalt_shield_decoration", new SimpleCraftingRecipeSerializer<>(CobaltShieldDecorationRecipe::new)
    );
    // Unused
    //public static final RecipeSerializer<PaleTridentRecipe> PALE_TRIDENT_CRAFTING = regSerializer(
    //        "pale_trident_crafting", new SpecialRecipeSerializer<>(PaleTridentRecipe::new)
    //);

    // Registry methods
    public static <T extends Recipe<?>> Supplier<RecipeType<T>> register(final String id)
    {
        return VectorLib.REGISTRY.registerRecipeType(Frontiers.MOD_ID, id);
    }

    public static <T extends Recipe<?>> Supplier<RecipeSerializer<T>> regSerializer(String id, RecipeSerializer<T>  serializer)
    {
        return VectorLib.REGISTRY.registerRecipeSerializer(Frontiers.MOD_ID, id, serializer);
    }

    public static void registerRecipes()
    {
        // the one piece!!!!!! the one piece is real!!!!!!
    }
}
