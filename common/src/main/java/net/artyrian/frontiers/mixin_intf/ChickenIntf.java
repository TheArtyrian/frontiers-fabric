package net.artyrian.frontiers.mixin_intf;

import net.minecraft.nbt.CompoundTag;

public interface ChickenIntf
{
    CompoundTag frontiersArtyrian$getPersistentNbt();
    void frontiersArtyrian$syncNbt(CompoundTag nbt);
}
