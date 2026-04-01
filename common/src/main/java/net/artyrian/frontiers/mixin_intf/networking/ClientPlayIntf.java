package net.artyrian.frontiers.mixin_intf.networking;

import net.artyrian.frontiers.definition.networking.packet.client.ClientboundBossBarMusicPacket;
import net.artyrian.frontiers.definition.networking.packet.client.ClientboundItemToBlockPacket;
import net.artyrian.frontiers.definition.networking.packet.client.ClientboundManaOrbPacket;

public interface ClientPlayIntf
{
    void frontiers$onItemToBlockPickupAnim(ClientboundItemToBlockPacket packet);
    void frontiers$onManaOrbSpawn(ClientboundManaOrbPacket packet);
    void frontiers$onBossBarUpdateMusic(ClientboundBossBarMusicPacket packet);
}
