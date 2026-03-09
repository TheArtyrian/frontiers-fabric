package net.artyrian.frontiers.definition.menu.monster_bakery;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class MonsterBakeFuelSlot extends Slot
{
    private final MonsterBakeryMenu handler;

    public MonsterBakeFuelSlot(MonsterBakeryMenu handler, Container inventory, int index, int x, int y)
    {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    @Override
    public boolean mayPlace(ItemStack stack)
    {
        return this.handler.isFuel(stack);
    }
}
