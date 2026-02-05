package net.vertisoft.vectorlib;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.vertisoft.vectorlib.agnostic.networking.VectorPayloads;
import net.vertisoft.vectorlib.agnostic.networking.payloads.NetSyncPayload;

public class VectorLibFabricClient
{
    public static void bootstrap()
    {
        ClientPlayNetworking.registerGlobalReceiver(NetSyncPayload.ID, (payload, context) ->
                VectorPayloads.netsync(payload, context.player().level()));
    }
}
