package net.artyrian.frontiers.recipe.special_crafting;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.recipe.ModRecipes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BannerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

// Doesn't work atm, I'll figure out later
public class PaleTridentRecipe extends SpecialCraftingRecipe
{
    public PaleTridentRecipe(CraftingRecipeCategory craftingRecipeCategory)
    {
        super(craftingRecipeCategory);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world)
    {
        if (input.getSize() > 9)
        {
            for (int i = 0; i < input.getSize(); i++)
            {
                ItemStack itemStack3 = input.getStackInSlot(i);
                if (!itemStack3.isEmpty() & itemStack3.isOf(ModItem.ELDER_GUARDIAN_SPINE))
                {
                    if ((input.getSize() - i) >= 9)
                    {
                        // TODO: This will break in anything bigger than 3x3!
                        //  I know a way that involves width+height checking but I need this out the door asap so do that later
                        boolean[] matchGrid = new boolean[]
                        {
                                input.getStackInSlot(i).isOf(ModItem.ELDER_GUARDIAN_SPINE),         // 1
                                input.getStackInSlot(i + 1).isOf(ModItem.ELDER_GUARDIAN_SPINE),     // 2
                                input.getStackInSlot(i + 2).isOf(ModItem.ELDER_GUARDIAN_SPINE),     // 3
                                input.getStackInSlot(i + 3).isOf(ModItem.PALE_PRISMARINE_SHARD),    // 4
                                input.getStackInSlot(i + 4).isOf(Items.TRIDENT),                    // 5
                                input.getStackInSlot(i + 5).isOf(ModItem.PALE_PRISMARINE_SHARD),    // 6
                                input.getStackInSlot(i + 6).isEmpty(),                              // 7
                                input.getStackInSlot(i + 7).isOf(ModItem.PALE_PRISMARINE_SHARD),    // 8
                                input.getStackInSlot(i + 8).isEmpty()                               // 9
                        };

                        boolean passe = true;
                        for (boolean b : matchGrid)
                        {
                            passe = b;
                            if (!passe) break;
                        }

                        if (passe) return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup lookup)
    {
        // Failsafe setup
        ItemStack returnItem = new ItemStack(ModItem.PALE_TRIDENT);

        for (int i = 0; i < input.getSize(); i++)
        {
            ItemStack itemStack3 = input.getStackInSlot(i);
            if (!itemStack3.isEmpty() & itemStack3.isOf(Items.TRIDENT))
            {
                returnItem = itemStack3.copyComponentsToNewStack(ModItem.PALE_TRIDENT, 1);
                break;
            }
        }

        return returnItem;
    }

    @Override
    public boolean fits(int width, int height) { return width * height >= 2; }
    @Override
    public boolean isIgnoredInRecipeBook() { return false; }
    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return null;
        //return ModRecipes.PALE_TRIDENT_CRAFTING;
    }
}
