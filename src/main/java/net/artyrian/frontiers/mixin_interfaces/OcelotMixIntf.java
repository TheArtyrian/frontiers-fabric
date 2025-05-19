package net.artyrian.frontiers.mixin_interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface OcelotMixIntf
{
    LivingEntity frontiers$getOwner();
    void frontiers$setOwner(PlayerEntity player);
    boolean frontiers$isOwner(LivingEntity player);

    UUID frontiers$getOcelotOwnerID();
    void frontiers$setOcelotOwnerID(@Nullable UUID uuid);

    boolean frontiers$isTamed();
    void frontiers$setTamed(boolean tamed, boolean updateAttributes);

    boolean frontiers$isSitting();
    void frontiers$setSitting(boolean sitting);

    boolean frontiers$isInSittingPose();
    void frontiers$setInSittingPose(boolean sitting);

    DyeColor frontiers$getCollarColor();
    void frontiers$setCollarColor(DyeColor color);
}
