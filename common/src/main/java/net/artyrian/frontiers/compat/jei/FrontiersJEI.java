package net.artyrian.frontiers.compat.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.jei.recipe.FRRecipesJEI;
import net.artyrian.frontiers.compat.jei.recipe.JEIRecipeType;
import net.artyrian.frontiers.compat.jei.recipe.category.FletchingCategoryJEI;
import net.artyrian.frontiers.compat.jei.recipe.category.MonsterBakeryCategoryJEI;
import net.artyrian.frontiers.compat.jei.recipe.category.MonsterFuelCategoryJEI;
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
        registration.addRecipeCategories(new FletchingCategoryJEI(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MonsterBakeryCategoryJEI(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new MonsterFuelCategoryJEI(registration.getJeiHelpers().getGuiHelper()));
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

    public static final ResourceLocation ID = Frontiers.id("jei_plugin");
}
