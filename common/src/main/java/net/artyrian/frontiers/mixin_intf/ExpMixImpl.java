package net.artyrian.frontiers.mixin_intf;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface ExpMixImpl
{
    @Nullable
    public BlockPos frontiers$getXPBlockPos();

    public void frontiers$subtractCount();
}
