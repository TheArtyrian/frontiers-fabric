package net.artyrian.frontiers.exclusive.networking;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.definition.networking.payload.attachment.*;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
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
                            ModNetworkConstants.ToServer.bottleMessageWrite(payload, (ServerPlayer) ctx.player());
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
                            ModNetworkConstants.ToClient.witherHardmodeSet(payload, Minecraft.getInstance());
                        });
                    }
            );

            // Ore Wither
            reg.playToClient(
                    OreWitherPayload.ID,
                    OreWitherPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.witherOre(payload, ctx.player().level());
                        });
                    }
            );

            // Avarice Totem
            reg.playToClient(
                    PlayerAvariceTotemPayload.ID,
                    PlayerAvariceTotemPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.avariceTotem(payload, (LocalPlayer)ctx.player());
                        });
                    }
            );

            // Sanity
            reg.playToClient(
                    SanitySyncPayload.ID,
                    SanitySyncPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.sanitySync(payload, ctx.player());
                        });
                    }
            );

            // Crags Monster Kill
            reg.playToClient(
                    CragsMonsterKillPayload.ID,
                    CragsMonsterKillPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.cragsMonsterKillPlayer(payload, (LocalPlayer) ctx.player());
                        });
                    }
            );

            // Despawn stalker sync
            reg.playToClient(
                    CragsStalkerDespawnPayload.ID,
                    CragsStalkerDespawnPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.despawnCragsStalker(payload, ctx.player().level());
                        });
                    }
            );

            // Chance-vary Food Item
            reg.playToClient(
                    ChanceFoodItemPayload.ID,
                    ChanceFoodItemPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.chanceFoodItem(payload, (LocalPlayer) ctx.player());
                        });
                    }
            );

            // Item Vacuum Empty Stack
            reg.playToClient(
                    ItemVacuumEmptyPayload.ID,
                    ItemVacuumEmptyPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.emptyItemVacuum(payload, ctx.player().level());
                        });
                    }
            );

            // Item Vacuum Sync Stack
            reg.playToClient(
                    ItemVacuumStackSyncPayload.ID,
                    ItemVacuumStackSyncPayload.CODEC,
                    (payload, ctx) -> {
                        ctx.enqueueWork(() -> {
                            ModNetworkConstants.ToClient.syncItemVacuumStack(payload, ctx.player().level());
                        });
                    }
            );

            // Entity Syncs
            reg.playToClient(OcelotPayload.ID, OcelotPayload.CODEC, (payload, ctx) -> ctx.enqueueWork(() -> ModNetworkConstants.ToClient.syncOcelot(payload, ctx.player().level())));
        }
    }
}
