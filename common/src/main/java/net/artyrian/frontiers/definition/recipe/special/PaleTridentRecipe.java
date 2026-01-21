package net.artyrian.frontiers.definition.recipe.special;

import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

// Doesn't work atm, I'll figure out later
public class PaleTridentRecipe extends CustomRecipe
{
    public PaleTridentRecipe(CraftingBookCategory craftingRecipeCategory)
    {
        super(craftingRecipeCategory);
    }

    @Override
    public boolean matches(CraftingInput input, Level world)
    {
        if (input.size() > 9)
        {
            for (int i = 0; i < input.size(); i++)
            {
                ItemStack itemStack3 = input.getItem(i);
                if (!itemStack3.isEmpty() & itemStack3.is(ModItem.ELDER_GUARDIAN_SPINE.get()))
                {
                    if ((input.size() - i) >= 9)
                    {
                        // TODO: This will break in anything bigger than 3x3!
                        //  I know a way that involves width+height checking but I need this out the door asap so do that later
                        boolean[] matchGrid = new boolean[]
                        {
                                input.getItem(i).is(ModItem.ELDER_GUARDIAN_SPINE.get()),         // 1
                                input.getItem(i + 1).is(ModItem.ELDER_GUARDIAN_SPINE.get()),     // 2
                                input.getItem(i + 2).is(ModItem.ELDER_GUARDIAN_SPINE.get()),     // 3
                                input.getItem(i + 3).is(ModItem.PALE_PRISMARINE_SHARD.get()),    // 4
                                input.getItem(i + 4).is(Items.TRIDENT),                    // 5
                                input.getItem(i + 5).is(ModItem.PALE_PRISMARINE_SHARD.get()),    // 6
                                input.getItem(i + 6).isEmpty(),                              // 7
                                input.getItem(i + 7).is(ModItem.PALE_PRISMARINE_SHARD.get()),    // 8
                                input.getItem(i + 8).isEmpty()                               // 9
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
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider lookup)
    {
        // Failsafe setup
        ItemStack returnItem = new ItemStack(ModItem.PALE_TRIDENT.get());

        for (int i = 0; i < input.size(); i++)
        {
            ItemStack itemStack3 = input.getItem(i);
            if (!itemStack3.isEmpty() & itemStack3.is(Items.TRIDENT))
            {
                returnItem = itemStack3.transmuteCopy(ModItem.PALE_TRIDENT.get(), 1);
                break;
            }
        }

        return returnItem;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) { return width * height >= 2; }
    @Override
    public boolean isSpecial() { return false; }
    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return null;
        //return ModRecipes.PALE_TRIDENT_CRAFTING;
    }
}
