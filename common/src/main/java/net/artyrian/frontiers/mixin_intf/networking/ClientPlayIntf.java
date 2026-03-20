package net.artyrian.frontiers.mixin_intf.networking;

import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ManaOrbSpawnS2CPacket;

public interface ClientPlayIntf
{
    void frontiers$onItemToBlockPickupAnim(ItemBlockPickupS2CPacket packet);
    void frontiers$onManaOrbSpawn(ManaOrbSpawnS2CPacket packet);
    void frontiers$onBossBarUpdateMusic(BossBarMusicS2CPacket packet);
}
