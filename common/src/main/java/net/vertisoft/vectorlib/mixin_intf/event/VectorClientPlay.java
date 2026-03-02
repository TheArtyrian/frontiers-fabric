package net.vertisoft.vectorlib.mixin_intf.event;

import net.vertisoft.vectorlib.agnostic.networking.data.packets.VectorEventS2CPacket;

public interface VectorClientPlay
{
    void vectorLib$handleGameEvent(VectorEventS2CPacket packet);
}
