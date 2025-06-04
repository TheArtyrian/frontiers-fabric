package net.artyrian.frontiers.client.screen.monster_bakery;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.slot.Slot;

public class MonsterBakeFuelSlot extends Slot
{
    private final MonsterBakeryScreenHandler handler;

    public MonsterBakeFuelSlot(MonsterBakeryScreenHandler handler, Inventory inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack)
    {
        return this.handler.isFuel(stack);
    }
}
