package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.EvoFangsIntf;
import net.minecraft.nbt.CompoundTag;

public class EvokerFangsPersistentNBT
{
    public static final String FRIENDLY = "IsFriendly";
    public static final String GATOR = "UseGatorFrontiersTex";

    public static boolean setFriendly(EvoFangsIntf evo, boolean val)
    {
        CompoundTag compound = evo.frontiersArtyrian$getPersistentNbt();

        compound.putBoolean(FRIENDLY, val);

        return val;
    }

    public static boolean setGator(EvoFangsIntf evo, boolean val)
    {
        CompoundTag compound = evo.frontiersArtyrian$getPersistentNbt();

        compound.putBoolean(GATOR, val);

        return val;
    }
}
