package net.artyrian.frontiers.mixin_intf;

import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.gen.Accessor;

public interface HoglinIntf
{
    boolean frontiers_1_21x$isTruffled();
    void frontiers_1_21x$setTruffled(boolean value);

    CompoundTag frontiersArtyrian$getPersistentNbt();
    void frontiersArtyrian$syncNbt(CompoundTag nbt);

    boolean frontiers$isImmuneToZombification();
}
