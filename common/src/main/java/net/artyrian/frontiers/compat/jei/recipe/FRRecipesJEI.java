package net.artyrian.frontiers.compat.jei.recipe;

import net.artyrian.frontiers.compat.jei.recipe.custom.JEIMonsterBakeryRecipe;
import net.artyrian.frontiers.compat.jei.recipe.custom.JEIMonsterFuelRecipe;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.reg.misc.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class FRRecipesJEI
{
    private final RecipeManager recipeMan;

    public FRRecipesJEI()
    {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel world = minecraft.level;

        if (world != null) this.recipeMan = world.getRecipeManager();
        else throw new NullPointerException("Minecraft level is null - are we running on server somehow or?");
    }

    public List<ArrowFletchingRecipe> fletching()
    {
        return this.recipeMan.getAllRecipesFor(ModRecipes.ARROW_FLETCHING.get()).stream().map(RecipeHolder::value).toList();
    }

    public List<JEIMonsterBakeryRecipe> monsterBakery()
    {
        return List.of();
    }

    public List<JEIMonsterFuelRecipe> bakeryFuel()
    {
        return List.of();
    }
}
