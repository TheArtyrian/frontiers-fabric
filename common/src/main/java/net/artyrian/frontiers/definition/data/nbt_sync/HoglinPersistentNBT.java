package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.minecraft.nbt.CompoundTag;

public class HoglinPersistentNBT
{
    public static final String TRUFFLE = "BredWithTruffle";

    public static boolean setTruffled(HoglinIntf hog, boolean val)
    {
        CompoundTag compound = hog.frontiersArtyrian$getPersistentNbt();

        compound.putBoolean(TRUFFLE, val);

        return val;
    }
}
