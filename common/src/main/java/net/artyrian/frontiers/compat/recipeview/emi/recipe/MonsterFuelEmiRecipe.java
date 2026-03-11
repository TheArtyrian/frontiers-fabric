package net.artyrian.frontiers.compat.recipeview.emi.recipe;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.compat.recipeview.emi.FrontiersEMI;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class MonsterFuelEmiRecipe implements EmiRecipe
{
    public static final DecimalFormat TEXT_FORMAT = new DecimalFormat("#,###.##");

    private final EmiIngredient stack;
    private final int time;
    private final ResourceLocation id;

    public static final int TEX_W = 144;
    public static final int TEX_H = 18;

    public MonsterFuelEmiRecipe(Item item, int time)
    {
        ResourceLocation resc = BuiltInRegistries.ITEM.getKey(item);
        this.id = Frontiers.id(resc.getNamespace(), "/" + resc.getPath() + "_monsterfuel");
        this.stack = EmiIngredient.of(Ingredient.of(item));
        this.time = time;
    }

    @Override public EmiRecipeCategory getCategory() { return FrontiersEMI.BAKERY_FUEL; }
    @Override @Nullable public ResourceLocation getId() { return this.id; }
    @Override public List<EmiIngredient> getInputs() { return List.of(stack); }
    @Override public List<EmiStack> getOutputs() { return List.of(); }
    @Override public int getDisplayWidth() { return TEX_W; }
    @Override public int getDisplayHeight() { return TEX_H; }
    @Override public boolean supportsRecipeTree() { return false; }

    @Override
    public void addWidgets(WidgetHolder widg)
    {
        widg.addTexture(EmiTexture.EMPTY_FLAME, 1, 1);
        widg.addAnimatedTexture((Frontiers.DUNGEONS_DELIGHT_LOADED) ? FrontiersEMI.DD_FIRE : FrontiersEMI.FIRE, 1, 1, 1000 * time / 20, false, true, true);
        widg.addSlot(stack, 18, 0).recipeContext(this);
        widg.addText(Component.translatable("emi.fuel_time.frontiers.mobs",
                TEXT_FORMAT.format(this.time / (float)MonsterBakeryBlockEntity.MAX_INCUBATE_TIME)), 38, 5, -1, true);
    }
}
