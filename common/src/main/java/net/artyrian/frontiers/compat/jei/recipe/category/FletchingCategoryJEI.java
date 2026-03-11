package net.artyrian.frontiers.compat.jei.recipe.category;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.artyrian.frontiers.compat.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.minecraft.network.chat.Component;

public class FletchingCategoryJEI implements IRecipeCategory<ArrowFletchingRecipe>
{
    public FletchingCategoryJEI(IGuiHelper helper)
    {

    }

    @Override public RecipeType<ArrowFletchingRecipe> getRecipeType() { return JEIRecipeType.FLETCHING; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.fletching"); }
}
