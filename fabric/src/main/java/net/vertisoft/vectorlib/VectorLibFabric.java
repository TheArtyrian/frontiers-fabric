package net.vertisoft.vectorlib;

import net.artyrian.frontiers.reg.misc.FRTrade;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.multiplayer.ClientLevel;
import net.vertisoft.vectorlib.agnostic.networking.data.payloads.NetSyncPayload;
import net.vertisoft.vectorlib.agnostic.util.VectorTrade;

public class VectorLibFabric
{
    public static void bootstrap()
    {
        PayloadTypeRegistry.playS2C().register(NetSyncPayload.ID, NetSyncPayload.CODEC);

        doVillagerTrades();
    }

    private static void doVillagerTrades()
    {
        for (VectorTrade trade : VectorTrade.getTrades())
        {
            if (trade.isWandering())
            {
                TradeOfferHelper.registerWanderingTraderOffers(trade.isRareWandering() ? 2 : 1, factories -> factories.add(trade.getTrade()));
            }
            else
            {
                TradeOfferHelper.registerVillagerOffers(trade.getJob(), trade.getLvl(), factories -> factories.add(trade.getTrade()));
            }
        }
    }
}
