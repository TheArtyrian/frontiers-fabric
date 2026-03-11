package net.artyrian.frontiers.compat.emi.recipe;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.artyrian.frontiers.compat.emi.FrontiersEMI;
import net.artyrian.frontiers.definition.recipe.fletching.ArrowFletchingRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

public class FletchingEmiRecipe extends BasicEmiRecipe
{
    private final EmiIngredient head;
    private final EmiIngredient stick;
    private final EmiIngredient feather;
    private final EmiStack output;

    public static final int TEX_W = 82;
    public static final int TEX_H = 60;

    public FletchingEmiRecipe(RecipeHolder<ArrowFletchingRecipe> recipe)
    {
        super(FrontiersEMI.FLETCHING, recipe.id(), TEX_W, TEX_H);

        this.head = EmiIngredient.of(recipe.value().getHead());
        this.stick = EmiIngredient.of(recipe.value().getStick());
        this.feather = EmiIngredient.of(recipe.value().getFeather());
        this.output = EmiStack.of(recipe.value().getOutput());
    }

    @Override public EmiRecipeCategory getCategory() { return FrontiersEMI.FLETCHING; }
    @Override public ResourceLocation getId() { return id; }

    @Override public List<EmiIngredient> getInputs() { return List.of(head, stick, feather); }
    @Override public List<EmiStack> getOutputs() { return List.of(output); }

    @Override public int getDisplayWidth() { return TEX_W; }
    @Override public int getDisplayHeight() { return TEX_H; }

    @Override
    public void addWidgets(WidgetHolder widg)
    {
        widg.addTexture(FrontiersEMI.FLETCH_SHEET, 0, 0, TEX_W, TEX_H, 0, 0, TEX_W, TEX_H, 128, 128);

        // Inputs
        widg.addSlot(head, 1, 1);
        widg.addSlot(stick, 1, 21);
        widg.addSlot(feather, 1, 41);

        // Outputs
        SlotWidget ioWid = new SlotWidget(output, 59, 21);
        ioWid.drawBack(false);
        widg.add(ioWid).recipeContext(this);
    }
}
