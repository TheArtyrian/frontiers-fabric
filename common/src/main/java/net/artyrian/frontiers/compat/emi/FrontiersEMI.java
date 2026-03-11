package net.artyrian.frontiers.compat.emi;

import com.mojang.datafixers.util.Pair;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.EmiRecipeSorting;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.render.EmiRenderable;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.emi.recipe.FletchingEmiRecipe;
import net.artyrian.frontiers.compat.emi.recipe.MonsterBakeryEmiRecipe;
import net.artyrian.frontiers.compat.emi.recipe.MonsterFuelEmiRecipe;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.Map;

public class FrontiersEMI
{
    // Sprite Sheets
    public static final ResourceLocation FLETCH_SHEET = Frontiers.id("textures/gui/emi/fletching.png");
    public static final ResourceLocation WIDGETS = Frontiers.id("textures/gui/emi/widgets.png");

    // Tex
    public static final EmiTexture FIRE = new EmiTexture(FrontiersEMI.WIDGETS, 0, 0, 14, 14, 14, 14, 64, 64);
    public static final EmiTexture DD_FIRE = new EmiTexture(FrontiersEMI.WIDGETS, 14, 0, 14, 14, 14, 14, 64, 64);
    public static final EmiTexture BAKER_ARROW_EMPTY = new EmiTexture(FrontiersEMI.WIDGETS, 40, 0, 24, 16, 24, 16, 64, 64);
    public static final EmiTexture BAKER_ARROW_FULL = new EmiTexture(FrontiersEMI.WIDGETS, 40, 16, 24, 16, 24, 16, 64, 64);

    // Workstations
    public static final EmiStack FLETCHING_STATION = EmiStack.of(Items.FLETCHING_TABLE);
    public static final EmiStack BAKERY_STATION = EmiStack.of(ModBlocks.MONSTER_BAKERY.get());

    // Categories
    public static final EmiRecipeCategory FLETCHING =
            new EmiRecipeCategory(Frontiers.id("fletching"), FLETCHING_STATION, new EmiTexture(WIDGETS, 48, 48, 16, 16, 16, 16, 64, 64));
    public static final EmiRecipeCategory MONSTER_BAKERY =
            new EmiRecipeCategory(Frontiers.id("monster_bakery"), BAKERY_STATION,  new EmiTexture(WIDGETS, 32, 48, 16, 16, 16, 16, 64, 64));
    public static final EmiRecipeCategory BAKERY_FUEL;

    public static void register(EmiRegistry emiRegistry)
    {
        emiRegistry.addCategory(FLETCHING);
        emiRegistry.addCategory(MONSTER_BAKERY);
        emiRegistry.addCategory(BAKERY_FUEL);

        emiRegistry.addWorkstation(FLETCHING, FLETCHING_STATION);
        emiRegistry.addWorkstation(MONSTER_BAKERY, BAKERY_STATION);

        RecipeManager manager = emiRegistry.getRecipeManager();

        // Arrow Fletching
        for (RecipeHolder<ArrowFletchingRecipe> recipe : manager.getAllRecipesFor(ModRecipes.ARROW_FLETCHING.get()))
        {
            emiRegistry.addRecipe(new FletchingEmiRecipe(recipe));
        }

        // Monster Bakery
        createBakery(emiRegistry);

        // Monster Fuels
        createFuels(emiRegistry);
    }

    private static void createBakery(EmiRegistry emiRegistry)
    {
        Map<Item, Pair<EntityType<? extends LivingEntity>, Integer>> recipes = MonsterBakeryBlockEntity.defaultRecipes();
        for (Item itemHere : recipes.keySet())
        {
            Pair<EntityType<? extends LivingEntity>, Integer> packer = recipes.get(itemHere);
            emiRegistry.addRecipe(new MonsterBakeryEmiRecipe(itemHere, packer.getFirst(), packer.getSecond()));
        }
    }

    private static void createFuels(EmiRegistry emiRegistry)
    {
        Map<Item, Integer> fuelList = MonsterBakeryBlockEntity.defaultFuels();
        for (Item itemHere : fuelList.keySet())
        {
            emiRegistry.addRecipe(new MonsterFuelEmiRecipe(itemHere, fuelList.get(itemHere)));
        }
    }

    // Here for DD control
    static
    {
        EmiRenderable flame = (matrices, x, y, delta) ->
        {
            if (Frontiers.DUNGEONS_DELIGHT_LOADED) DD_FIRE.render(matrices, x + 1, y + 1, delta);
            else FIRE.render(matrices, x + 1, y + 1, delta);
        };

        BAKERY_FUEL = new EmiRecipeCategory(Frontiers.id("bakery_fuel"), flame, flame, EmiRecipeSorting.compareInputThenOutput());
    }
}
