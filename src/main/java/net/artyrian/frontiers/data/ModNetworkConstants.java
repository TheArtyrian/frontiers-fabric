package net.artyrian.frontiers.data;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.components.BottleContentComponent;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.artyrian.frontiers.data.packets.BossBarMusicS2CPacket;
import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.data.packets.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.data.payloads.BottleMessageWritePayload;
import net.artyrian.frontiers.data.payloads.PlayerAvariceTotemPayload;
import net.artyrian.frontiers.item.ModItem;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.network.packet.s2c.play.TickStepS2CPacket;
import net.minecraft.network.state.PlayStateFactories;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.RawFilteredPair;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModNetworkConstants
{
    // Payload Packets
    public static final Identifier WITHER_HARDMODE = Identifier.of(Frontiers.MOD_ID, "wither_hardmode");
    public static final Identifier ORE_WITHER_PACKET = Identifier.of(Frontiers.MOD_ID, "ore_wither_packet");
    public static final Identifier PLAYER_AVARICE_PACKET = Identifier.of(Frontiers.MOD_ID, "player_avarice_packet");
    public static final Identifier SANITY_SYNC_PACKET = Identifier.of(Frontiers.MOD_ID, "sanity_sync_packet");
    public static final Identifier CRAGS_STALKER_DESPAWN_PACKET = Identifier.of(Frontiers.MOD_ID, "crags_stalker_despawn_packet");
    public static final Identifier CRAGS_MONSTER_KILL_PACKET = Identifier.of(Frontiers.MOD_ID, "crags_monster_kill_packet");
    public static final Identifier MESSAGE_BOTTLE = Identifier.of(Frontiers.MOD_ID, "message_bottle");
    public static final Identifier CHANCE_FOOD_ITEM = Identifier.of(Frontiers.MOD_ID, "chance_food_item");
    public static final Identifier ITEM_VACUUM_EMPTY = Identifier.of(Frontiers.MOD_ID, "item_vacuum_empty");
    public static final Identifier ITEM_VACUUM_SYNC = Identifier.of(Frontiers.MOD_ID, "item_vacuum_sync");

    // Basic S2C Packets
    public static final PacketType<ItemBlockPickupS2CPacket> PICKUP_TO_BLOCK = doS2CPacket("frontiers_pickup_to_block");
    public static final PacketType<ManaOrbSpawnS2CPacket> SPAWN_MANA_ORB = doS2CPacket("frontiers_spawn_mana_orb");
    public static final PacketType<BossBarMusicS2CPacket> UPDATE_BOSSBAR_MUSIC = doS2CPacket("frontiers_update_bossbar_music");

    private static <T extends Packet<ClientPlayPacketListener>> PacketType<T> doS2CPacket(String id)
    {
        return new PacketType<>(NetworkSide.CLIENTBOUND, Identifier.ofVanilla(id));
    }

    public static void registerC2SPayloads()
    {
        ServerPlayNetworking.registerGlobalReceiver(BottleMessageWritePayload.ID, ((payload, context) -> {
            ServerPlayerEntity player = context.player();

            int slot = payload.slot();
            String text = payload.text();

            ItemStack signed = new ItemStack(ModItem.BOTTLED_MESSAGE);
            signed.set(ModDataComponents.BOTTLE_CONTENT, new BottleContentComponent(RawFilteredPair.of(text)));

            player.getInventory().setStack(slot, signed);
        }));
    }
}
