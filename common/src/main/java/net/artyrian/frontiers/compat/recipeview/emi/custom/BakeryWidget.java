package net.artyrian.frontiers.compat.recipeview.emi.custom;

import com.google.common.collect.Lists;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.widget.SlotWidget;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;

import java.util.List;

public class BakeryWidget extends SlotWidget
{
    public BakeryWidget(EmiIngredient stack, int x, int y)
    {
        super(stack, x, y);
    }

    @Override
    public List<ClientTooltipComponent> getTooltip(int mouseX, int mouseY)
    {
        List<ClientTooltipComponent> list = Lists.newArrayList();
        if (this.getStack().isEmpty())  return list;
        else
        {
            this.addSlotTooltip(list);
            return list;
        }
    }
}
