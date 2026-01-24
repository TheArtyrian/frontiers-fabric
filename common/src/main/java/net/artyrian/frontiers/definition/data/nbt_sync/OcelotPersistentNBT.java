package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.EndCrystalIntf;
import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class OcelotPersistentNBT
{
    public static final String TAME_FLAG = "TameableFlags";
    public static final String OWNER = "Owner";
    public static final String COLLAR = "CollarColor";

    public static UUID setOwner(OcelotMixIntf ocelot, UUID val)
    {
        CompoundTag compound = ocelot.frontiersArtyrian$getPersistentNbt();

        compound.putUUID(OWNER, val);

        return val;
    }

    public static byte setCollarColor(OcelotMixIntf ocelot, byte bit)
    {
        CompoundTag compound = ocelot.frontiersArtyrian$getPersistentNbt();

        compound.putByte(COLLAR, bit);

        return bit;
    }

    public static byte setTameFlags(OcelotMixIntf ocelot, byte bit)
    {
        CompoundTag compound = ocelot.frontiersArtyrian$getPersistentNbt();

        compound.putByte(TAME_FLAG, bit);

        return bit;
    }
}
