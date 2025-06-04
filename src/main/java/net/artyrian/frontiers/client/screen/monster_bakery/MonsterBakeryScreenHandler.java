package net.artyrian.frontiers.client.screen.monster_bakery;

import net.artyrian.frontiers.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.client.screen.DisplaySlot;
import net.artyrian.frontiers.client.screen.ModScreenHandlers;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MonsterBakeryScreenHandler extends ScreenHandler
{
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;

    public MonsterBakeryScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(5));
    }

    public MonsterBakeryScreenHandler(
            int syncId,
            PlayerInventory playerInventory,
            Inventory inventory,
            PropertyDelegate delegate)
    {
        super(ModScreenHandlers.MONSTER_BAKERY, syncId);
        checkSize(inventory, 3);
        this.inventory = inventory;
        this.world = playerInventory.player.getWorld();
        this.propertyDelegate = delegate;

        this.addSlot(new Slot(inventory, 0, 56, 17));
        this.addSlot(new MonsterBakeFuelSlot(this, inventory, 1, 56, 53));
        this.addSlot(new DisplaySlot(inventory, 2, 116, 35));

        // Test code
        //this.slots.get(2).setStack(new ItemStack(Items.CREEPER_SPAWN_EGG));
        //this.slots.get(2).markDirty();

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

        this.addProperties(delegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot targetslot = this.slots.get(slot);

        if (targetslot != null & targetslot.hasStack() && slot != 2)
        {
            ItemStack targStack = targetslot.getStack();
            if (slot != 1 && slot != 0)
            {
                if (this.isRecipeItem(targStack))
                {
                    if (!this.insertItem(targStack, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (this.isFuel(targStack))
                {
                    if (!this.insertItem(targStack, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slot >= 3 && slot < 30)
                {
                    if (!this.insertItem(targStack, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (slot >= 30 && slot < 39 && !this.insertItem(targStack, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(targStack, 3, 39, false))
            {
                return ItemStack.EMPTY;
            }

            if (targStack.isEmpty()) targetslot.setStack(ItemStack.EMPTY);
            else targetslot.markDirty();

            if (targStack.getCount() == itemStack.getCount()) return ItemStack.EMPTY;

            targetslot.onTakeItem(player, targStack);
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) { return this.inventory.canPlayerUse(player); }
    public boolean isActive() { return this.propertyDelegate.get(0) > 0; }
    public float getSpawnTime() { return this.propertyDelegate.get(4); }

    protected boolean isFuel(ItemStack itemStack) { return MonsterBakeryBlockEntity.isFuel(itemStack); }
    protected boolean isRecipeItem(ItemStack itemStack) { return MonsterBakeryBlockEntity.isRecipeItem(itemStack); }

    public float getIncProgress()
    {
        int incTime = this.propertyDelegate.get(2);
        int incTimeTotal = this.propertyDelegate.get(3);
        return (incTime != 0 && incTimeTotal != 0) ? MathHelper.clamp((float)incTime / (float)incTimeTotal, 0.0F, 1.0F) : 0.0F;
    }

    public float getFuelProgress()
    {
        int cookTime = this.propertyDelegate.get(0);
        int maxCookTime = this.propertyDelegate.get(1);
        if (maxCookTime == 0)
        {
            maxCookTime = 200;
        }

        return MathHelper.clamp((float)cookTime / (float)maxCookTime, 0.0F, 1.0F);
    }

    public boolean hasItemCooking()
    {
        return !this.slots.get(2).getStack().isEmpty();
    }

    public float getSpawnChance()
    {
        return ((float)this.propertyDelegate.get(4) / 100.0F);
    }
}
