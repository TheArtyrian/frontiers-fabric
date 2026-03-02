package net.vertisoft.vectorlib.mixin_intf.event;

import net.minecraft.core.BlockPos;

public interface VectorLevelRenderer
{
    void vectorLib$runGameEvent(int type, BlockPos pos, int data);
}
