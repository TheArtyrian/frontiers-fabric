package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.ChickenIntf;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.minecraft.nbt.CompoundTag;

public class ChickenPersistentNBT
{
    public static final String EGG = "golden_egg";

    /** Sets the Chicken's golden egg status. */
    public static boolean setGoldenEgg(ChickenIntf chicken, boolean val)
    {
        CompoundTag compound = chicken.frontiersArtyrian$getPersistentNbt();

        compound.putBoolean(EGG, val);

        return val;
    }
}
