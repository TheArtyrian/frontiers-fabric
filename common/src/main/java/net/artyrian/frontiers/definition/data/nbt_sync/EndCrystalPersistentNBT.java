package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.mixin_intf.EndCrystalIntf;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class EndCrystalPersistentNBT
{
    public static final String FRIENDLY = "IsFriendly";
    public static final String HITS = "HitsTaken";
    public static final String BEAMPOS = "GoodBeamPos";

    public static boolean setFriendly(EndCrystalIntf crystal, boolean val)
    {
        CompoundTag compound = crystal.frontiersArtyrian$getPersistentNbt();

        compound.putBoolean(FRIENDLY, val);

        return val;
    }

    public static int setHits(EndCrystalIntf crystal, int hits)
    {
        CompoundTag compound = crystal.frontiersArtyrian$getPersistentNbt();

        compound.putInt(HITS, hits);

        return hits;
    }

    public static BlockPos setBeamPos(EndCrystalIntf crystal, BlockPos pos)
    {
        CompoundTag compound = crystal.frontiersArtyrian$getPersistentNbt();

        CompoundTag targ = new CompoundTag();
        targ.putInt("x", pos.getX());
        targ.putInt("y", pos.getY());
        targ.putInt("z", pos.getZ());
        compound.put(BEAMPOS, targ);

        return pos;
    }
}
