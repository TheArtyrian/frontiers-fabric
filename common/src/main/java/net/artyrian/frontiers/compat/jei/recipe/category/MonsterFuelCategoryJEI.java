package net.artyrian.frontiers.compat.jei.recipe.category;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.artyrian.frontiers.compat.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.jei.recipe.custom.JEIMonsterFuelRecipe;
import net.minecraft.network.chat.Component;

public class MonsterFuelCategoryJEI implements IRecipeCategory<JEIMonsterFuelRecipe>
{
    private final IDrawable icon;
    private final IDrawable background;

    public MonsterFuelCategoryJEI(IGuiHelper helper)
    {

    }

    @Override public RecipeType<JEIMonsterFuelRecipe> getRecipeType() { return JEIRecipeType.BAKERY_FUEL; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.bakery_fuel"); }
}
