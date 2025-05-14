package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.util.math.BlockPos;

public interface EndCrystalMixInterface
{
    public int frontiers_1_21x$getCrackSpin();
    public float frontiers_1_21x$getCrackFloat();

    public float frontiers_1_21x$getBeamLen();
    public int frontiers_1_21x$getRays();

    public boolean frontiers_1_21x$isFriendly();
    public void frontiers_1_21x$setFriendly(boolean friend);

    public BlockPos frontiers$getGoodBeamPos();
    public void frontiers$setGoodBeamPos(BlockPos pos);
}
