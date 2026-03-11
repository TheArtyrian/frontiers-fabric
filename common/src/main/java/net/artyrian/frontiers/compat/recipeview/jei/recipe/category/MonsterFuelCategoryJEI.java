package net.artyrian.frontiers.compat.recipeview.jei.recipe.category;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.common.Internal;
import mezz.jei.common.gui.elements.DrawableAnimated;
import mezz.jei.common.gui.elements.DrawableCombined;
import mezz.jei.common.gui.elements.OffsetDrawable;
import mezz.jei.common.gui.textures.JeiSpriteUploader;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterFuelRecipe;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.text.NumberFormat;

public class MonsterFuelCategoryJEI implements IRecipeCategory<JEIMonsterFuelRecipe>
{
    private static final int WHATEVER_THIS_DOES_IG = 2000000000;
    private static final int COLOR_CONST = -8355712;

    private final IDrawableStatic flame;
    private final IDrawableStatic flame_dead;

    private final JeiSpriteUploader uploader;
    private final IDrawable icon;

    public MonsterFuelCategoryJEI(IGuiHelper helper, IDrawableStatic flame, IDrawableStatic flame_dead)
    {
        this.uploader = Internal.getTextures().getSpriteUploader();
        this.flame = flame;
        this.flame_dead = flame_dead;
        this.icon = flame;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, JEIMonsterFuelRecipe recipe, IFocusGroup iFocusGroup)
    {
        builder.addInputSlot(1, 17).setStandardSlotBackground().addIngredients(recipe.getItem());
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, JEIMonsterFuelRecipe recipe, IFocusGroup focuses)
    {
        int burnTime = recipe.getTime();
        Component smeltCountText = createBakeryComp(burnTime);
        OffsetDrawable fl = this.addSpecialFlame(burnTime).setPosition(1, 0);
        builder.addDrawable(fl);

        builder.addText(smeltCountText, this.getWidth() - 20, this.getHeight()).setPosition(20, 0)
                .setTextAlignment(HorizontalAlignment.CENTER).setTextAlignment(VerticalAlignment.CENTER).setColor(COLOR_CONST);
    }

    public OffsetDrawable addSpecialFlame(int cookTime)
    {
        IDrawableAnimated animatedFill = new DrawableAnimated(this.flame, cookTime, IDrawableAnimated.StartDirection.TOP, true);
        IDrawable drawableCombined = new DrawableCombined(this.flame_dead, animatedFill);
        return new OffsetDrawable(drawableCombined, 0, 0);
    }

    private static int getMaxWidth()
    {
        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = minecraft.font;
        Component maxSmeltCountText = createBakeryComp(WHATEVER_THIS_DOES_IG);
        int maxStringWidth = fontRenderer.width(maxSmeltCountText.getString());
        int textPadding = 20;
        return 18 + textPadding + maxStringWidth;
    }

    public static Component createBakeryComp(int burnTime)
    {
        if (burnTime == MonsterBakeryBlockEntity.MAX_INCUBATE_TIME)
        {
            return Component.translatable("jei.category.frontiers.bakery_fuel.single");
        }
        else
        {
            NumberFormat numberInstance = NumberFormat.getNumberInstance();
            numberInstance.setMaximumFractionDigits(2);
            String smeltCount = numberInstance.format(((float)burnTime / (float)MonsterBakeryBlockEntity.MAX_INCUBATE_TIME));
            return Component.translatable("jei.category.frontiers.bakery_fuel.multi", smeltCount);
        }
    }

    @Override public RecipeType<JEIMonsterFuelRecipe> getRecipeType() { return JEIRecipeType.BAKERY_FUEL; }
    @Override @Nullable public IDrawable getIcon() { return icon; }
    @Override @Nullable public ResourceLocation getRegistryName(JEIMonsterFuelRecipe recipe) { return null; }
    @Override public int getWidth() { return getMaxWidth(); }
    @Override public int getHeight() { return 34; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.bakery_fuel"); }
}
