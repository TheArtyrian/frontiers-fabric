package net.vertisoft.vectorlib;

import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.vertisoft.vectorlib.agnostic.networking.VectorPayloads;
import net.vertisoft.vectorlib.agnostic.networking.payloads.NetSyncPayload;

public class VectorLibNF
{
    public static void payloadSetup(PayloadRegistrar reg)
    {
        reg.playToClient(NetSyncPayload.ID, NetSyncPayload.CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> VectorPayloads.netsync(payload, ctx.player().level())));
    }
}
