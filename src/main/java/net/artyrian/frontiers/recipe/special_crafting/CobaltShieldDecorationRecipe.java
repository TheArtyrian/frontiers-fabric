package net.artyrian.frontiers.recipe.special_crafting;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.recipe.ModRecipes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

public class CobaltShieldDecorationRecipe extends SpecialCraftingRecipe
{
    public CobaltShieldDecorationRecipe(CraftingRecipeCategory craftingRecipeCategory)
    {
        super(craftingRecipeCategory);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for (int i = 0; i < input.getSize(); i++)
        {
            ItemStack itemStack3 = input.getStackInSlot(i);

            if (!itemStack3.isEmpty())
            {
                if (itemStack3.getItem() instanceof BannerItem)
                {
                    if (!itemStack2.isEmpty())
                    {
                        return false;
                    }

                    itemStack2 = itemStack3;
                }
                else
                {
                    if (!itemStack3.isOf(ModItem.COBALT_SHIELD))
                    {
                        return false;
                    }

                    if (!itemStack.isEmpty())
                    {
                        return false;
                    }

                    BannerPatternsComponent bannerPatternsComponent = itemStack3.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
                    if (!bannerPatternsComponent.layers().isEmpty())
                    {
                        return false;
                    }

                    itemStack = itemStack3;
                }
            }
        }

        return !itemStack.isEmpty() && !itemStack2.isEmpty();
    }

    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for (int i = 0; i < craftingRecipeInput.getSize(); i++)
        {
            ItemStack itemStack3 = craftingRecipeInput.getStackInSlot(i);
            if (!itemStack3.isEmpty())
            {
                if (itemStack3.getItem() instanceof BannerItem)
                {
                    itemStack = itemStack3;
                }
                else if (itemStack3.isOf(ModItem.COBALT_SHIELD))
                {
                    itemStack2 = itemStack3.copy();
                }
            }
        }

        if (itemStack2.isEmpty())
        {
            return itemStack2;
        }
        else
        {
            itemStack2.set(DataComponentTypes.BANNER_PATTERNS, itemStack.get(DataComponentTypes.BANNER_PATTERNS));
            itemStack2.set(DataComponentTypes.BASE_COLOR, ((BannerItem)itemStack.getItem()).getColor());
            return itemStack2;
        }
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.COBALT_SHIELD_DECORATION;
    }
}
