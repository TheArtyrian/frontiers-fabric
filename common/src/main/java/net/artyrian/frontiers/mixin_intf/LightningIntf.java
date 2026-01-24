package net.artyrian.frontiers.mixin_intf;

import net.minecraft.nbt.CompoundTag;

public interface LightningIntf
{
    boolean frontiers_1_21x$isChanneled();
    void frontiers_1_21x$setChanneled(boolean value);

    CompoundTag frontiersArtyrian$getPersistentNbt();
    void frontiersArtyrian$syncNbt(CompoundTag nbt);
}
