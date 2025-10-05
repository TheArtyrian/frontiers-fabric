package net.artyrian.frontiers.mixin_interfaces;

import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.data.packets.ManaOrbSpawnS2CPacket;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;

public interface ClientPlayNetImpl
{
    void frontiers$onItemToBlockPickupAnim(ItemBlockPickupS2CPacket packet);
    void frontiers$onManaOrbSpawn(ManaOrbSpawnS2CPacket packet);
}
