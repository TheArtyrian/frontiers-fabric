package net.artyrian.frontiers.compat.recipeview.jei.recipe.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public class JEIMonsterFuelRecipe
{
    private final Ingredient item;
    private final int time;
    private final ResourceLocation id;

    public JEIMonsterFuelRecipe(Item item, int time)
    {
        ResourceLocation resc = BuiltInRegistries.ITEM.getKey(item);
        this.id = Frontiers.id(resc.getNamespace(), "/" + resc.getPath() + "_monsterfuel");
        this.item = Ingredient.of(item);
        this.time = time;
    }

    public Ingredient getItem() { return this.item; }
    public int getTime() { return this.time; }
}
