package net.artyrian.frontiers.mixin_intf;

import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public interface PlayerIntf
{
    CompoundTag frontiersArtyrian$getPersistentNbt();
    // CRAGS           ////////////////////////////////////////////////////////////

    int frontiers_1_21x$getSanity();
    int frontiers_1_21x$getSanityTick();
    boolean frontiers_1_21x$killedByCragsMonster();

    // MANA            ////////////////////////////////////////////////////////////

    int frontiers_1_21x$getManaPts();
    int frontiers_1_21x$getManaLvl();
    int frontiers_1_21x$getManaForNext();
    void frontiers_1_21x$setManaPts(int pts);

    default void frontiers_1_21x$addMana(int amnt) { this.frontiers_1_21x$setManaPts(this.frontiers_1_21x$getManaPts() + amnt); }
    default void frontiers_1_21x$removeMana(int amnt) { this.frontiers_1_21x$addMana(-amnt); }
    default float frontiers_1_21x$getManaProg() { return (float)this.frontiers_1_21x$getManaPts() / (float)this.frontiers_1_21x$getManaForNext(); }
    default boolean frontiers_1_21x$atMaxMana()
    {
        return this.frontiers_1_21x$getManaPts() >= this.frontiers_1_21x$getManaForNext() && this.frontiers_1_21x$getManaLvl() >= PlayerPersistentNBT.Mana.MAX_LEVEL;
    }

    // UPGRADES        ////////////////////////////////////////////////////////////

    boolean frontiers_1_21x$usedUpgradeApple();
    void frontiersArtyrian$checkBuffsStatus();

    // MISC           ////////////////////////////////////////////////////////////

    boolean frontiers_1_21x$usedAvariceTotem();
    void frontiers$openBottleScreen(ItemStack stack, InteractionHand hand);
}
