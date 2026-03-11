package net.artyrian.frontiers.compat.recipeview.jei.recipe.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.gui.placement.HorizontalAlignment;
import mezz.jei.api.gui.placement.IPlaceable;
import mezz.jei.api.gui.placement.VerticalAlignment;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.gui.widgets.ITextWidget;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.vanilla.IJeiFuelingRecipe;
import mezz.jei.common.Internal;
import mezz.jei.common.gui.elements.DrawableAnimated;
import mezz.jei.common.gui.elements.DrawableCombined;
import mezz.jei.common.gui.elements.OffsetDrawable;
import mezz.jei.common.gui.textures.Textures;
import mezz.jei.library.util.RecipeUtil;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.recipeview.FRRecViewCom;
import net.artyrian.frontiers.compat.recipeview.jei.misc.BakeryRichTip;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterBakeryRecipe;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

public class MonsterBakeryCategoryJEI implements IRecipeCategory<JEIMonsterBakeryRecipe>
{
    private final IDrawableStatic flame;
    private final IDrawableStatic flame_dead;
    private final IDrawableStatic bg;

    private final IDrawable icon;

    public MonsterBakeryCategoryJEI(IGuiHelper helper, IDrawableStatic flame, IDrawableStatic flame_dead)
    {
        this.flame = flame;
        this.flame_dead = flame_dead;
        this.bg = helper.createDrawable(FRRecViewCom.WIDGET_SHEET, FRRecViewCom.CAGE_OUTPUT_XY[0], FRRecViewCom.CAGE_OUTPUT_XY[1], FRRecViewCom.CAGE_OUTPUT_DIM[0], FRRecViewCom.CAGE_OUTPUT_DIM[1]);;
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.MONSTER_BAKERY.get()));
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
        //int cookTime = Math.clamp(recipe.getCookTime(), 1, MonsterBakeryBlockEntity.MAX_INCUBATE_TIME);
//
        //builder.addAnimatedRecipeArrow(cookTime).setPosition(26, 17);
        //builder.addAnimatedRecipeFlame(300).setPosition(1, 20);
        //this.addExperience(builder, recipeHolder);
        //this.addCookTime(builder, recipeHolder);
    }

    private BakeryRichTip setTip(String id) { return new BakeryRichTip(Component.translatable(id).withStyle(ChatFormatting.GREEN)); }

    @Override public RecipeType<JEIMonsterBakeryRecipe> getRecipeType() { return JEIRecipeType.MONSTER_BAKERY; }
    @Override @Nullable public IDrawable getIcon() { return icon; }
    @Override @Nullable public ResourceLocation getRegistryName(JEIMonsterBakeryRecipe recipe) { return null; }

    @Override public int getWidth() { return 82; }
    @Override public int getHeight() { return 54; }
    // Uses EMI name to make my life easier
    @Override public Component getTitle() { return Component.translatable("emi.category.frontiers.monster_bakery"); }
}
