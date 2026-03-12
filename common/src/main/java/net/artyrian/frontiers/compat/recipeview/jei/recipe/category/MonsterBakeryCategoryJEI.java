package net.artyrian.frontiers.compat.recipeview.jei.recipe.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.gui.widgets.ITextWidget;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.artyrian.frontiers.compat.recipeview.FRRecViewCom;
import net.artyrian.frontiers.compat.recipeview.jei.FrontiersJEI;
import net.artyrian.frontiers.compat.recipeview.jei.misc.BakeryRichTip;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterBakeryRecipe;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MonsterBakeryCategoryJEI implements IRecipeCategory<JEIMonsterBakeryRecipe>
{
    private final IDrawable icon;

    private final IDrawableStatic flame;
    private final IDrawableStatic flame_dead;
    private final IDrawableStatic arrow_bg;
    private final IDrawableStatic arrow;
    private final IDrawableStatic bg;

    public MonsterBakeryCategoryJEI(IGuiHelper helper, IDrawableStatic flame, IDrawableStatic flame_dead)
    {
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MONSTER_BAKERY.get()));
        this.flame = flame;
        this.flame_dead = flame_dead;

        this.arrow_bg = helper.createDrawable(FRRecViewCom.WIDGET_SHEET,
                FRRecViewCom.ARROW_OFF_XY[0], FRRecViewCom.ARROW_OFF_XY[1], FRRecViewCom.ARROW_DIM[0], FRRecViewCom.ARROW_DIM[1]);
        this.arrow = helper.createDrawable(FRRecViewCom.WIDGET_SHEET,
                FRRecViewCom.ARROW_ON_XY[0], FRRecViewCom.ARROW_ON_XY[1], FRRecViewCom.ARROW_DIM[0], FRRecViewCom.ARROW_DIM[1]);
        this.bg = helper.createDrawable(FRRecViewCom.WIDGET_SHEET,
                FRRecViewCom.CAGE_OUTPUT_XY[0], FRRecViewCom.CAGE_OUTPUT_XY[1], FRRecViewCom.CAGE_OUTPUT_DIM[0], FRRecViewCom.CAGE_OUTPUT_DIM[1]);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, JEIMonsterBakeryRecipe recipe, IFocusGroup focuses)
    {
        builder.addInputSlot(1, 1).setStandardSlotBackground().addIngredients(recipe.getItem());
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 1, 37).setStandardSlotBackground();
        builder.addOutputSlot(61, 19).setBackground(this.bg, -5, -5).addItemStack(new ItemStack(recipe.getEgg()))
                .addRichTooltipCallback(setTip(recipe.getEntityName()));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, JEIMonsterBakeryRecipe recipe, IFocusGroup focuses)
    {
        int cookTime = Math.max(1, recipe.getCookTime());

        FrontiersJEI.addSpecialArrow(builder, cookTime, this.arrow, this.arrow_bg, 26, 17);
        FrontiersJEI.addSpecialFlame(builder, 300, this.flame, this.flame_dead, 1, 20);
        this.addPercent(builder, recipe.getPercent());
        this.addTime(builder, cookTime);
    }

    private void addPercent(IRecipeExtrasBuilder builder, int percent)
    {
        if (percent <= 0) return;

        Component str = Component.translatable("emi.fuel_time.frontiers.bakery_chance", percent);
        builder.addText(str, this.getWidth() - 20, 10)
                .setPosition(0, 0, this.getWidth(), this.getHeight(), HorizontalAlignment.RIGHT, VerticalAlignment.TOP)
                .setTextAlignment(HorizontalAlignment.RIGHT)
                .setColor(FRRecViewCom.JEI_TEXT_GRAY);
    }

    private void addTime(IRecipeExtrasBuilder builder, int time)
    {
        int cookTimeSeconds = (time / 20);
        Component timeString = Component.translatable("gui.jei.category.smelting.time.seconds", cookTimeSeconds);
        builder.addText(timeString, this.getWidth() - 20, 10)
                .setPosition(0, 0, this.getWidth(), this.getHeight(), HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM)
                .setTextAlignment(HorizontalAlignment.RIGHT)
                .setTextAlignment(VerticalAlignment.BOTTOM)
                .setColor(FRRecViewCom.JEI_TEXT_GRAY);
    }

    private BakeryRichTip setTip(String id) { return new BakeryRichTip(FRRecViewCom.bakeryEntityText(id)); }

    @Override public RecipeType<JEIMonsterBakeryRecipe> getRecipeType() { return JEIRecipeType.MONSTER_BAKERY; }
    @Override @Nullable public IDrawable getIcon() { return icon; }
    @Override @Nullable public ResourceLocation getRegistryName(JEIMonsterBakeryRecipe recipe) { return null; }

    @Override public int getWidth() { return 82; }
    @Override public int getHeight() { return 54; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.monster_bakery"); }
}
