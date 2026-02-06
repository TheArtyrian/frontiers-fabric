package net.vertisoft.vectorlib;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.vertisoft.vectorlib.agnostic.networking.VectorPayloads;
import net.vertisoft.vectorlib.agnostic.networking.payloads.NetSyncPayload;
import net.vertisoft.vectorlib.exclusive.NFLootModSet;

public class VectorLibNF
{
    public static void bootstrap(IEventBus eventBus)
    {
        NFLootModSet.REGISTRY.register(eventBus);
    }

    public static void payloadSetup(PayloadRegistrar reg)
    {
        reg.playToClient(NetSyncPayload.ID, NetSyncPayload.CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> VectorPayloads.netsync(payload, ctx.player().level())));
    }
}
