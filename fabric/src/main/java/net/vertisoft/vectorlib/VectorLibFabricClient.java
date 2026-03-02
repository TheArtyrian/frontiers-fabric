package net.vertisoft.vectorlib;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.vertisoft.vectorlib.agnostic.networking.data.VectorPayloads;
import net.vertisoft.vectorlib.agnostic.networking.data.payloads.NetSyncPayload;

public class VectorLibFabricClient
{
    public static void bootstrap()
    {
        ClientPlayNetworking.registerGlobalReceiver(NetSyncPayload.ID, (payload, context) ->
                VectorPayloads.netsync(payload, context.player().level()));
    }
}
