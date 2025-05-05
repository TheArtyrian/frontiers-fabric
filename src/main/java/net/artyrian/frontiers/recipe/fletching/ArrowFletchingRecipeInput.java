package net.artyrian.frontiers.recipe.fletching;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ArrowFletchingRecipeInput(ItemStack head, ItemStack stick, ItemStack feather) implements RecipeInput
{
    public ArrowFletchingRecipeInput(ItemStack head, ItemStack stick, ItemStack feather)
    {
        this.head = head;
        this.stick = stick;
        this.feather = feather;
    }

    public ItemStack getStackInSlot(int slot)
    {
        ItemStack theBronJames;
        switch (slot) {
            case 0 -> theBronJames = this.head;
            case 1 -> theBronJames = this.stick;
            case 2 -> theBronJames = this.feather;
            default -> throw new IllegalArgumentException("[FRONTIERS] Hey! Provided fletching recipe does not contain slot " + slot);
        }

        return theBronJames;
    }

    public int getSize() {
        return 3;
    }

    public boolean isEmpty() { return this.head.isEmpty() && this.stick.isEmpty() && this.feather.isEmpty(); }

    public ItemStack head() {
        return this.head;
    }

    public ItemStack stick() {
        return this.stick;
    }

    public ItemStack feather() {
        return this.feather;
    }
}
