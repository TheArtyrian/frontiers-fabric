package net.artyrian.frontiers.client.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.slot.Slot;

public class DisplaySlot extends Slot
{
    public DisplaySlot(Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return false;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity)
    {
        return false;
    }
}
