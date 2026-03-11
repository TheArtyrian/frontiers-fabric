package net.artyrian.frontiers.compat.recipeview.jei.misc;

import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import net.minecraft.network.chat.Component;

public class BakeryRichTip implements IRecipeSlotRichTooltipCallback
{
    private final Component display;

    public BakeryRichTip(Component comp)
    {
        this.display = comp;
    }

    @Override
    public void onRichTooltip(IRecipeSlotView view, ITooltipBuilder builder)
    {
        builder.clear();
        builder.add(this.display);
    }
}
