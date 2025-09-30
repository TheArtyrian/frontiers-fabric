package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface ExpMixImpl
{
    @Nullable
    public BlockPos frontiers$getXPBlockPos();

    public void frontiers$subtractCount();
}
