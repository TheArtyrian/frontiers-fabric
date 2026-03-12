package net.artyrian.frontiers.compat.recipeview.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.placement.IPlaceable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.common.Internal;
import mezz.jei.common.gui.elements.DrawableAnimated;
import mezz.jei.common.gui.elements.DrawableCombined;
import mezz.jei.common.gui.elements.OffsetDrawable;
import mezz.jei.common.gui.textures.Textures;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.recipeview.FRRecViewCom;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.FRRecipesJEI;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.category.FletchingCategoryJEI;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.category.MonsterBakeryCategoryJEI;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.category.MonsterFuelCategoryJEI;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.vertisoft.vectorlib.agnostic.VectorSystems;

import java.util.ArrayList;
import java.util.List;

public class FrontiersJEI
{
    public static final ResourceLocation ID = Frontiers.id("jei_plugin");

    public static void registerRecipes(IRecipeRegistration registration)
    {
        FRRecipesJEI recipes = new FRRecipesJEI();

        // Recipes
        registration.addRecipes(JEIRecipeType.FLETCHING, recipes.fletching());
        registration.addRecipes(JEIRecipeType.MONSTER_BAKERY, recipes.monsterBakery());
        registration.addRecipes(JEIRecipeType.BAKERY_FUEL, recipes.bakeryFuel());

        // Info
        doIngredientInfo(registration);
    }

    public static void registerCategories(IRecipeCategoryRegistration registration)
    {
        JEIRecipeType.initialize();

        Textures textures = Internal.getTextures();
        IGuiHelper guiHelp = registration.getJeiHelpers().getGuiHelper();

        int v = (Frontiers.DUNGEONS_DELIGHT_LOADED) ? 46 : 32;
        IDrawableStatic flame = flameDrawable(guiHelp, 0, v);
        IDrawableStatic flame_dead = textures.getFlameEmptyIcon();

        registration.addRecipeCategories(new FletchingCategoryJEI(guiHelp));
        registration.addRecipeCategories(new MonsterBakeryCategoryJEI(guiHelp, flame, flame_dead));
        registration.addRecipeCategories(new MonsterFuelCategoryJEI(guiHelp, flame, flame_dead));
    }

    public static void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(Items.FLETCHING_TABLE), JEIRecipeType.FLETCHING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.MONSTER_BAKERY.get()), JEIRecipeType.MONSTER_BAKERY);
    }

    private static void doIngredientInfo(IRecipeRegistration registration)
    {
        Language langInst = Language.getInstance();
        List<String> doneAlready = new ArrayList<>();

        for (Item item : BuiltInRegistries.ITEM)
        {
            if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(Frontiers.MOD_ID))
            {
                String id = VectorSystems.JEI_PREFIX + item.getDescriptionId();
                if (langInst.has(id) && !doneAlready.contains(id))
                {
                    doneAlready.add(id);
                    registration.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM_STACK, Component.translatable(id));
                }
            }
        }

        for (Block block : BuiltInRegistries.BLOCK)
        {
            if (BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Frontiers.MOD_ID))
            {
                String id = VectorSystems.JEI_PREFIX + block.getDescriptionId();
                if (langInst.has(id) && !doneAlready.contains(id))
                {
                    doneAlready.add(id);
                    registration.addIngredientInfo(new ItemStack(block), VanillaTypes.ITEM_STACK, Component.translatable(id));
                }
            }
        }
    }

    private static IDrawableStatic flameDrawable(IGuiHelper helper, int u, int v) { return helper.createDrawable(FRRecViewCom.WIDGET_SHEET, u, v, FRRecViewCom.FLAME_DIM[0], FRRecViewCom.FLAME_DIM[1]); }

    public static void addSpecialFlame(IRecipeExtrasBuilder builder, int cookTime, IDrawableStatic flame, IDrawableStatic flameDead, int offX, int offY)
    {
        IDrawableAnimated animatedFill = new DrawableAnimated(flame, cookTime, IDrawableAnimated.StartDirection.TOP, true);
        IDrawable drawableCombined = new DrawableCombined(flameDead, animatedFill);
        OffsetDrawable flame2 = new OffsetDrawable(drawableCombined, 0, 0).setPosition(offX, offY);
        builder.addDrawable(flame2);
    }

    public static void addSpecialArrow(IRecipeExtrasBuilder builder, int time, IDrawableStatic front, IDrawableStatic back, int offX, int offY)
    {
        IDrawable animatedFill = new DrawableAnimated(front, time, IDrawableAnimated.StartDirection.LEFT, false);
        IDrawable drawableCombined = new DrawableCombined(back, animatedFill);
        OffsetDrawable arrow = new OffsetDrawable(drawableCombined, 0, 0).setPosition(offX, offY);
        builder.addDrawable(arrow);
    }
}
