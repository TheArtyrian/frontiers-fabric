package net.artyrian.frontiers.compat.recipeview.jei.recipe.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.artyrian.frontiers.compat.recipeview.FRRecViewCom;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class FletchingCategoryJEI implements IRecipeCategory<ArrowFletchingRecipe>
{
    private final IDrawable icon;
    private final IDrawable background;

    public FletchingCategoryJEI(IGuiHelper helper)
    {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Items.FLETCHING_TABLE));
        this.background = helper.createDrawable(FRRecViewCom.FLETCHING_SHEET, 0, 0, FRRecViewCom.FLETCH_W, FRRecViewCom.FLETCH_H);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ArrowFletchingRecipe recipe, IFocusGroup focusGroup)
    {
        builder.addSlot(RecipeIngredientRole.INPUT, FRRecViewCom.FLETCH_ISLOT[0] + 1, FRRecViewCom.FLETCH_ISLOT[1] + 1).addIngredients(recipe.getHead());
        builder.addSlot(RecipeIngredientRole.INPUT, FRRecViewCom.FLETCH_ISLOT[2] + 1, FRRecViewCom.FLETCH_ISLOT[3] + 1).addIngredients(recipe.getStick());
        builder.addSlot(RecipeIngredientRole.INPUT, FRRecViewCom.FLETCH_ISLOT[4] + 1, FRRecViewCom.FLETCH_ISLOT[5] + 1).addIngredients(recipe.getFeather());

        builder.addSlot(RecipeIngredientRole.OUTPUT, FRRecViewCom.FLETCH_OSLOT[0] + 1, FRRecViewCom.FLETCH_OSLOT[1] + 1).addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(ArrowFletchingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY)
    {
        // Idk why theyre deprecating getBackground but no shot am i taking risks
        this.background.draw(guiGraphics, 0, 0);
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }

    @Override public RecipeType<ArrowFletchingRecipe> getRecipeType() { return JEIRecipeType.FLETCHING; }
    @Override @Nullable public IDrawable getIcon() { return icon; }
    @Override public int getWidth() { return FRRecViewCom.FLETCH_W; }
    @Override public int getHeight() { return FRRecViewCom.FLETCH_H; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.fletching"); }
}
