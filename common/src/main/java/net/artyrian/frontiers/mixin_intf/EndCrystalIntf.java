package net.artyrian.frontiers.mixin_intf;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public interface EndCrystalIntf
{
    public int frontiers_1_21x$getCrackSpin();
    public float frontiers_1_21x$getCrackFloat();

    public float frontiers_1_21x$getBeamLen();
    public int frontiers_1_21x$getRays();

    public boolean frontiers_1_21x$isFriendly();
    public void frontiers_1_21x$setFriendly(boolean friend);

    public int frontiers_1_21x$getHitsTaken();
    public void frontiers_1_21x$setHitsTaken(int count);

    public BlockPos frontiers$getGoodBeamPos();
    public void frontiers$setGoodBeamPos(BlockPos pos);
}
