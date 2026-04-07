package net.artyrian.frontiers.exclusive.networking;

import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkingNF
{
    public static class ToServer
    {
        public static void register(PayloadRegistrar reg)
        {
            // Bottle Message write
            reg.playToServer(
                    BottleMessageWritePayload.ID,
                    BottleMessageWritePayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToServer.bottleMessageWrite(payload, (ServerPlayer) ctx.player());
                        });
                    }
            );
        }
    }

    public static class ToClient
    {
        public static void register(PayloadRegistrar reg)
        {
            // Wither Hardmode set
            reg.playToClient(
                    WitherHardmodePayload.ID,
                    WitherHardmodePayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.witherHardmodeSet(payload, Minecraft.getInstance());
                        });
                    }
            );

            // Avarice Totem
            reg.playToClient(
                    PlayerAvariceTotemPayload.ID,
                    PlayerAvariceTotemPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.avariceTotem(payload, (LocalPlayer)ctx.player());
                        });
                    }
            );

            // Crags Monster Kill
            reg.playToClient(
                    CragsMonsterKillPayload.ID,
                    CragsMonsterKillPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.cragsMonsterKillPlayer(payload, (LocalPlayer) ctx.player());
                        });
                    }
            );

            // Player Buffs
            reg.playToClient(
                    BuffSyncPayload.ID,
                    BuffSyncPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.syncPlayerBuffs(payload, (LocalPlayer)ctx.player());
                        });
                    }
            );

            // Sanity
            reg.playToClient(
                    SanitySyncPayload.ID,
                    SanitySyncPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.sanitySync(payload, ctx.player());
                        });
                    }
            );

            // Mana
            reg.playToClient(
                    ManaSyncPayload.ID,
                    ManaSyncPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.manaSync(payload, (LocalPlayer)ctx.player());
                        });
                    }
            );

            // Chance-vary Food Item
            reg.playToClient(
                    ChanceFoodItemPayload.ID,
                    ChanceFoodItemPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            FRNetworking.ToClient.chanceFoodItem(payload, (LocalPlayer) ctx.player());
                        });
                    }
            );
        }
    }
}
