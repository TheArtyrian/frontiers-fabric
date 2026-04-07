package net.artyrian.frontiers.definition.menu.monster_bakery;

import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.definition.menu.DisplaySlot;
import net.artyrian.frontiers.reg.content.FRMenus;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MonsterBakeryMenu extends AbstractContainerMenu
{
    private final Container inventory;
    private final ContainerData propertyDelegate;
    protected final Level world;

    public MonsterBakeryMenu(int syncId, Inventory playerInventory)
    {
        this(syncId, playerInventory, new SimpleContainer(3), new SimpleContainerData(5));
    }

    public MonsterBakeryMenu(
            int syncId,
            Inventory playerInventory,
            Container inventory,
            ContainerData delegate)
    {
        super(FRMenus.MONSTER_BAKERY.get(), syncId);
        checkContainerSize(inventory, 3);
        this.inventory = inventory;
        this.world = playerInventory.player.level();
        this.propertyDelegate = delegate;

        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new MonsterBakeFuelSlot(this, inventory, 1, 56, 53));
        this.addSlot(new DisplaySlot(inventory, 2, 116, 35));

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addDataSlots(delegate);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot targetslot = this.slots.get(slot);

        if (targetslot != null & targetslot.hasItem() && slot != 2)
        {
            ItemStack targStack = targetslot.getItem();
            if (slot != 1 && slot != 0)
            {
                if (this.isRecipeItem(targStack))
                {
                    if (!this.moveItemStackTo(targStack, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (this.isFuel(targStack))
                {
                    if (!this.moveItemStackTo(targStack, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slot >= 3 && slot < 30)
                {
                    if (!this.moveItemStackTo(targStack, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slot >= 30 && slot < 39 && !this.moveItemStackTo(targStack, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.moveItemStackTo(targStack, 3, 39, false))
            {
                return ItemStack.EMPTY;
            }

            if (targStack.isEmpty()) targetslot.setByPlayer(ItemStack.EMPTY);
            else targetslot.setChanged();

            if (targStack.getCount() == itemStack.getCount()) return ItemStack.EMPTY;

            targetslot.onTake(player, targStack);
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) { return this.inventory.stillValid(player); }
    public boolean isActive() { return this.propertyDelegate.get(0) > 0; }
    public float getSpawnTime() { return this.propertyDelegate.get(4); }

    protected boolean isFuel(ItemStack itemStack) { return MonsterBakeryBlockEntity.isFuel(itemStack); }
    protected boolean isRecipeItem(ItemStack itemStack) { return MonsterBakeryBlockEntity.isRecipeItem(itemStack); }

    public float getIncProgress()
    {
        int incTime = this.propertyDelegate.get(2);
        int incTimeTotal = this.propertyDelegate.get(3);
        return (incTime != 0 && incTimeTotal != 0) ? Mth.clamp((float)incTime / (float)incTimeTotal, 0.0F, 1.0F) : 0.0F;
    }

    public float getFuelProgress()
    {
        int cookTime = this.propertyDelegate.get(0);
        int maxCookTime = this.propertyDelegate.get(1);
        if (maxCookTime == 0)
        {
            maxCookTime = 200;
        }

        return Mth.clamp((float)cookTime / (float)maxCookTime, 0.0F, 1.0F);
    }

    public boolean hasItemCooking()
    {
        return !this.slots.get(2).getItem().isEmpty();
    }

    public float getSpawnChance()
    {
        return ((float)this.propertyDelegate.get(4) / 100.0F);
    }
}
