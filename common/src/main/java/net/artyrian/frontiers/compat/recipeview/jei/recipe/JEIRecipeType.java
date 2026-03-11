package net.artyrian.frontiers.compat.recipeview.jei.recipe;

import mezz.jei.api.recipe.RecipeType;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterBakeryRecipe;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterFuelRecipe;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;

public class JEIRecipeType
{
    public static final RecipeType<ArrowFletchingRecipe> FLETCHING = RecipeType.create(Frontiers.MOD_ID, "arrow_fletching", ArrowFletchingRecipe.class);
    public static final RecipeType<JEIMonsterBakeryRecipe> MONSTER_BAKERY = RecipeType.create(Frontiers.MOD_ID, "monster_bakery", JEIMonsterBakeryRecipe.class);
    public static final RecipeType<JEIMonsterFuelRecipe> BAKERY_FUEL = RecipeType.create(Frontiers.MOD_ID, "bakery_fuel", JEIMonsterFuelRecipe.class);

    public static void initialize()
    {

    }
}
