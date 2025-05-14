package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.nbt.NbtCompound;

public interface PlayerMixInterface
{
    boolean frontiers_1_21x$usedUpgradeApple();
    boolean frontiers_1_21x$usedAvariceTotem();
    boolean frontiers_1_21x$killedByCragsMonster();

    int frontiers_1_21x$getSanity();
    int frontiers_1_21x$getSanityTick();

    NbtCompound frontiersArtyrian$getPersistentNbt();
}
