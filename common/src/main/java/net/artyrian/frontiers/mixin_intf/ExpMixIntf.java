package net.artyrian.frontiers.mixin_intf;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

public interface ExpMixIntf
{
    @Nullable
    public BlockPos frontiers$getXPBlockPos();

    public void frontiers$subtractCount();
}
