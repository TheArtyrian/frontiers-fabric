package net.artyrian.frontiers.mixin_intf.networking;

import net.artyrian.frontiers.definition.networking.packet.server.ServerboundCurseAltarPacket;

public interface ServerPlayIntf
{
    void frontiers$onCurseAltarPacket(ServerboundCurseAltarPacket packet);
}
