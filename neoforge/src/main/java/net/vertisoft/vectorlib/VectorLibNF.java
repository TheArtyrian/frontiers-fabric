package net.vertisoft.vectorlib;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.vertisoft.vectorlib.agnostic.networking.data.VectorPayloads;
import net.vertisoft.vectorlib.agnostic.networking.data.payloads.NetSyncPayload;
import net.vertisoft.vectorlib.agnostic.util.VectorTrade;
import net.vertisoft.vectorlib.exclusive.NFLootModSet;
import net.vertisoft.vectorlib.platform.VectorRegNF;

import java.util.function.Supplier;

public class VectorLibNF
{
    public static void bootstrap(IEventBus eventBus)
    {
        NFLootModSet.REGISTRY.register(eventBus);
    }

    public static void villagerTrades(VillagerTradesEvent event)
    {
        for (Supplier<VectorTrade.Profession> trade : VectorRegNF.NF_TRADES_PROF)
        {
            VectorTrade.Profession prof = trade.get();

            if (event.getType() == prof.getJob()) event.getTrades().get(prof.getLvl()).add(prof.getTrade());
        }
    }

    public static void wanderingTrades(WandererTradesEvent event)
    {
        for (Supplier<VectorTrade.Wandering> trade : VectorRegNF.NF_TRADES_WAND)
        {
            VectorTrade.Wandering wand = trade.get();

            if (wand.isRare()) event.getRareTrades().add(wand.getTrade());
            else event.getGenericTrades().add(wand.getTrade());
        }
    }

    public static void payloadSetup(PayloadRegistrar event)
    {
        // Client
        event.playToClient(NetSyncPayload.ID, NetSyncPayload.CODEC, (payload, ctx) ->
                ctx.enqueueWork(() -> VectorPayloads.netsync(payload, ctx.player().level())));
    }
}
