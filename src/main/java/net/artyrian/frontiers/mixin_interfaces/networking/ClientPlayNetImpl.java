package net.artyrian.frontiers.mixin_interfaces.networking;

import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.data.packets.ManaOrbSpawnS2CPacket;

public interface ClientPlayNetImpl
{
    void frontiers$onItemToBlockPickupAnim(ItemBlockPickupS2CPacket packet);
    void frontiers$onManaOrbSpawn(ManaOrbSpawnS2CPacket packet);
    void frontiers$onBossBarUpdateMusic(BossBarMusicS2CPacket packet);
}
