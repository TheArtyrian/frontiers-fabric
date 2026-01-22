package net.artyrian.frontiers.mixin_intf;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public interface PlayerMixInterface
{
    boolean frontiers_1_21x$usedUpgradeApple();
    boolean frontiers_1_21x$usedAvariceTotem();
    boolean frontiers_1_21x$killedByCragsMonster();

    int frontiers_1_21x$getSanity();
    int frontiers_1_21x$getSanityTick();

    void frontiers$openBottleScreen(ItemStack stack, InteractionHand hand);

    CompoundTag frontiersArtyrian$getPersistentNbt();
}
