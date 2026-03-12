package net.artyrian.frontiers.compat.recipeview.jei.recipe.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class JEIMonsterFuelRecipe
{
    private final Ingredient item;
    private final int time;

    public JEIMonsterFuelRecipe(Item item, int time)
    {
        this.item = Ingredient.of(item);
        this.time = time;
    }

    public Ingredient getItem() { return this.item; }
    public int getTime() { return this.time; }
}
