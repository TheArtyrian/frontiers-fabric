package net.vertisoft.vectorlib;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.vertisoft.vectorlib.agnostic.networking.data.payloads.NetSyncPayload;

public class VectorLibFabric
{
    public static void bootstrap()
    {
        PayloadTypeRegistry.playS2C().register(NetSyncPayload.ID, NetSyncPayload.CODEC);
    }
}
