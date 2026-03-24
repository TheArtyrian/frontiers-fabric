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
    }
}
