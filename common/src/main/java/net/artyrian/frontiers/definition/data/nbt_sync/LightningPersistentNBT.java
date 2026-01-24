package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.LightningIntf;
import net.minecraft.nbt.CompoundTag;

public class LightningPersistentNBT
{
    public static final String CHANNELED = "IsChanneled";

    public static boolean setChanneled(LightningIntf bolt, boolean val)
    {
        CompoundTag compound = bolt.frontiersArtyrian$getPersistentNbt();

        compound.putBoolean(CHANNELED, val);

        return val;
    }
}
