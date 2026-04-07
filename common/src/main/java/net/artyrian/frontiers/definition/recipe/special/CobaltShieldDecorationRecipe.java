package net.artyrian.frontiers.definition.recipe.special;

import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.property.FRRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

public class CobaltShieldDecorationRecipe extends CustomRecipe
{
    public CobaltShieldDecorationRecipe(CraftingBookCategory craftingRecipeCategory)
    {
        super(craftingRecipeCategory);
    }

    @Override
    public boolean matches(CraftingInput input, Level world)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for (int i = 0; i < input.size(); i++)
        {
            ItemStack itemStack3 = input.getItem(i);

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
                    if (!itemStack3.is(FRItems.COBALT_SHIELD.get()))
                    {
                        return false;
                    }

                    if (!itemStack.isEmpty())
                    {
                        return false;
                    }

                    BannerPatternLayers bannerPatternsComponent = itemStack3.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
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

    public ItemStack assemble(CraftingInput craftingRecipeInput, HolderLookup.Provider wrapperLookup)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;

        for (int i = 0; i < craftingRecipeInput.size(); i++)
        {
            ItemStack itemStack3 = craftingRecipeInput.getItem(i);
            if (!itemStack3.isEmpty())
            {
                if (itemStack3.getItem() instanceof BannerItem)
                {
                    itemStack = itemStack3;
                }
                else if (itemStack3.is(FRItems.COBALT_SHIELD.get()))
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
            itemStack2.set(DataComponents.BANNER_PATTERNS, itemStack.get(DataComponents.BANNER_PATTERNS));
            itemStack2.set(DataComponents.BASE_COLOR, ((BannerItem)itemStack.getItem()).getColor());
            return itemStack2;
        }
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return FRRecipes.COBALT_SHIELD_DECORATION.get();
    }
}
