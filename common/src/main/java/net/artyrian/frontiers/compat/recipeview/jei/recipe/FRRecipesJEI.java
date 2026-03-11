package net.artyrian.frontiers.compat.recipeview.jei.recipe;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterBakeryRecipe;
import net.artyrian.frontiers.compat.recipeview.jei.recipe.custom.JEIMonsterFuelRecipe;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.reg.misc.ModRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<JEIMonsterBakeryRecipe> list = new ArrayList<>();

        Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> recipes = MonsterBakeryBlockEntity.defaultRecipes();
        for (Item itemHere : recipes.keySet())
        {
            Pair<EntityType<? extends LivingEntity>, Integer> packer = recipes.get(itemHere);
            list.add(new JEIMonsterBakeryRecipe(itemHere, packer.getFirst(), packer.getSecond()));
        }

        return list;
    }

    public List<JEIMonsterFuelRecipe> bakeryFuel()
    {
        List<JEIMonsterFuelRecipe> list = new ArrayList<>();

        Map<Item, Integer> fuelList = MonsterBakeryBlockEntity.defaultFuels();
        for (Item itemHere : fuelList.keySet())
        {
            list.add(new JEIMonsterFuelRecipe(itemHere, fuelList.get(itemHere)));
        }

        return list;
    }
}
