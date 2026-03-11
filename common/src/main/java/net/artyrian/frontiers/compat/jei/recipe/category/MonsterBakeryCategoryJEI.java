package net.artyrian.frontiers.compat.jei.recipe.category;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.artyrian.frontiers.compat.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.jei.recipe.custom.JEIMonsterBakeryRecipe;
import net.minecraft.network.chat.Component;

public class MonsterBakeryCategoryJEI implements IRecipeCategory<JEIMonsterBakeryRecipe>
{
    public MonsterBakeryCategoryJEI(IGuiHelper helper)
    {

    }

    @Override public RecipeType<JEIMonsterBakeryRecipe> getRecipeType() { return JEIRecipeType.MONSTER_BAKERY; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.monster_bakery"); }
}
