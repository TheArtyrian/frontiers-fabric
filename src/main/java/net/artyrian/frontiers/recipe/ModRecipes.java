package net.artyrian.frontiers.recipe;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.recipe.special_crafting.CobaltShieldDecorationRecipe;
import net.artyrian.frontiers.recipe.special_crafting.PaleTridentRecipe;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes
{
    // Recipe Types
    public static final RecipeType<ArrowFletchingRecipe> ARROW_FLETCHING = register("arrow_fletching");

    // Serializers
    public static final RecipeSerializer<ArrowFletchingRecipe> ARROW_FLETCHING_SERIALIZER = regSerializer(
            "arrow_fletching", new ArrowFletchingRecipe.Serializer(ArrowFletchingRecipe::new)
    );
    public static final RecipeSerializer<CobaltShieldDecorationRecipe> COBALT_SHIELD_DECORATION = regSerializer(
            "crafting_special_cobalt_shield_decoration", new SpecialRecipeSerializer<>(CobaltShieldDecorationRecipe::new)
    );
    // Unused
    //public static final RecipeSerializer<PaleTridentRecipe> PALE_TRIDENT_CRAFTING = regSerializer(
    //        "pale_trident_crafting", new SpecialRecipeSerializer<>(PaleTridentRecipe::new)
    //);

    // Registry methods
    public static <T extends Recipe<?>> RecipeType<T> register(final String id)
    {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(Frontiers.MOD_ID, id), new RecipeType<T>()
        {
            public String toString()
            {
                return id;
            }
        });
    }
    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S regSerializer(String id, S serializer)
    {
        return Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Frontiers.MOD_ID, id), serializer);
    }

    public static void registerRecipes()
    {
        // the one piece!!!!!! the one piece is real!!!!!!
    }
}
