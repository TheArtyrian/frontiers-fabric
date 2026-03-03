package net.vertisoft.vectorlib.mixin_intf.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public interface VectorLevelRenderer
{
    void vectorLib$runGameEvent(int type, BlockPos pos, int data);
    void vectorLib$runDualEvent(int type, Vec3 pos1, Vec3 pos2, int data);
    void vectorLib$runEntityEvent(int type, Entity entity, int data);
    void vectorLib$runGlobalEvent(int type, BlockPos pos, int data);
}
