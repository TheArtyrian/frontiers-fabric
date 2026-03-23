package net.vertisoft.vectorlib;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.vertisoft.vectorlib.agnostic.networking.data.VectorPayloads;
import net.vertisoft.vectorlib.agnostic.networking.data.payloads.NetSyncPayload;
import net.vertisoft.vectorlib.agnostic.util.VectorTrade;
import net.vertisoft.vectorlib.exclusive.NFLootModSet;

public class VectorLibNF
{
    public static void bootstrap(IEventBus eventBus)
    {
        NFLootModSet.REGISTRY.register(eventBus);
    }

    public static void villagerTrades(VillagerTradesEvent event)
    {
        for (VectorTrade trade : VectorTrade.getTrades())
        {
            if (!trade.isWandering())
            {
                if (event.getType() == trade.getJob()) event.getTrades().get(trade.getLvl()).add(trade.getTrade());
            }
        }
    }

    public static void wanderingTrades(WandererTradesEvent event)
    {
        for (VectorTrade trade : VectorTrade.getTrades())
        {
            if (trade.isWandering())
            {
                if (trade.isRareWandering()) event.getRareTrades().add(trade.getTrade());
                else event.getGenericTrades().add(trade.getTrade());
            }
        }
    }

    public static void payloadSetup(PayloadRegistrar event)
    {
        // Client
        event.playToClient(NetSyncPayload.ID, NetSyncPayload.CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> VectorPayloads.netsync(payload, ctx.player().level())));
    }
}
