package net.artyrian.frontiers.mixin_intf;

import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;

/** Interface for accessing ocelot taming reimplementation */
public interface OcelotMixIntf
{
    LivingEntity frontiers$getOwner();
    void frontiers$setOwner(Player player);
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

    boolean frontiers$cannotFollowOwner();
    boolean frontiers$shouldTryTeleportToOwner();
    void frontiers$tryTeleportToOwner();
}
