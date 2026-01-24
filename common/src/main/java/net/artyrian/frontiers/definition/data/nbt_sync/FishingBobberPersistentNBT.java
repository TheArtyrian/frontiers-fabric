package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.artyrian.frontiers.mixin_intf.BobberType;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class FishingBobberPersistentNBT
{
    public static final String BOBBER = "BobberType";
    public static final String ROD = "ParentRod";

    public static int setBobber(BobberIntf bob, BobberType bobberType)
    {
        CompoundTag compound = bob.frontiersArtyrian$getPersistentNbt();

        int val = bobberType.getID();
        compound.putInt(BOBBER, val);

        return val;
    }

    public static ItemStack setParentStack(BobberIntf bob, ItemStack stack, RegistryAccess access)
    {
        CompoundTag compound = bob.frontiersArtyrian$getPersistentNbt();

        compound.put(ROD, stack.save(access, new CompoundTag()));

        return stack;
    }
}
