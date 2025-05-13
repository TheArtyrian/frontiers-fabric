package net.artyrian.frontiers.data;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.PlayerAvariceTotemPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class ModNetworkConstants
{
    public static final Identifier WITHER_HARDMODE = Identifier.of(Frontiers.MOD_ID, "wither_hardmode");
    public static final Identifier ORE_WITHER_PACKET = Identifier.of(Frontiers.MOD_ID, "ore_wither_packet");
    public static final Identifier PLAYER_AVARICE_PACKET = Identifier.of(Frontiers.MOD_ID, "player_avarice_packet");
    public static final Identifier SANITY_SYNC_PACKET = Identifier.of(Frontiers.MOD_ID, "sanity_sync_packet");
    public static final Identifier CRAGS_STALKER_DESPAWN_PACKET = Identifier.of(Frontiers.MOD_ID, "crags_stalker_despawn_packet");
    public static final Identifier CRAGS_MONSTER_KILL_PACKET = Identifier.of(Frontiers.MOD_ID, "crags_monster_kill_packet");

    public static void registerC2SPayloads()
    {

    }
}
