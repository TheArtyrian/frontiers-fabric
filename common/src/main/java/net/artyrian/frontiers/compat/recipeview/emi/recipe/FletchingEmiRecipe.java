package net.artyrian.frontiers.compat.recipeview.emi.recipe;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.artyrian.frontiers.compat.recipeview.FRRecViewCom;
import net.artyrian.frontiers.compat.recipeview.emi.FrontiersEMI;
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

    public FletchingEmiRecipe(RecipeHolder<ArrowFletchingRecipe> recipe)
    {
        super(FrontiersEMI.FLETCHING, recipe.id(), FRRecViewCom.FLETCH_W, FRRecViewCom.FLETCH_H);

        this.head = EmiIngredient.of(recipe.value().getHead());
        this.stick = EmiIngredient.of(recipe.value().getStick());
        this.feather = EmiIngredient.of(recipe.value().getFeather());
        this.output = EmiStack.of(recipe.value().getOutput());
    }

    @Override public EmiRecipeCategory getCategory() { return FrontiersEMI.FLETCHING; }
    @Override public ResourceLocation getId() { return id; }

    @Override public List<EmiIngredient> getInputs() { return List.of(head, stick, feather); }
    @Override public List<EmiStack> getOutputs() { return List.of(output); }

    @Override public int getDisplayWidth() { return FRRecViewCom.FLETCH_W; }
    @Override public int getDisplayHeight() { return FRRecViewCom.FLETCH_H; }

    @Override
    public void addWidgets(WidgetHolder widg)
    {
        widg.addTexture(FRRecViewCom.FLETCHING_SHEET, 0, 0, FRRecViewCom.FLETCH_W, FRRecViewCom.FLETCH_H, 0, 0, FRRecViewCom.FLETCH_W, FRRecViewCom.FLETCH_H, FRRecViewCom.FLETCH_TEX_DIMSQ, FRRecViewCom.FLETCH_TEX_DIMSQ);

        // Inputs
        widg.addSlot(head, FRRecViewCom.FLETCH_ISLOT[0], FRRecViewCom.FLETCH_ISLOT[1]);
        widg.addSlot(stick, FRRecViewCom.FLETCH_ISLOT[2], FRRecViewCom.FLETCH_ISLOT[3]);
        widg.addSlot(feather, FRRecViewCom.FLETCH_ISLOT[4], FRRecViewCom.FLETCH_ISLOT[5]);

        // Outputs
        SlotWidget ioWid = new SlotWidget(output, FRRecViewCom.FLETCH_OSLOT[0], FRRecViewCom.FLETCH_OSLOT[1]);
        ioWid.drawBack(false);
        widg.add(ioWid).recipeContext(this);
    }
}
