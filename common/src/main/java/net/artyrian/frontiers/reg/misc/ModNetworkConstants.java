package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.definition.networking.payload.BottleMessageWritePayload;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.item.ItemStack;

public class ModNetworkConstants
{
    // Payload Packets
    public static final ResourceLocation WITHER_HARDMODE = Frontiers.id("wither_hardmode");
    public static final ResourceLocation ORE_WITHER_PACKET = Frontiers.id("ore_wither_packet");
    public static final ResourceLocation PLAYER_AVARICE_PACKET = Frontiers.id("player_avarice_packet");
    public static final ResourceLocation SANITY_SYNC_PACKET = Frontiers.id("sanity_sync_packet");
    public static final ResourceLocation CRAGS_STALKER_DESPAWN_PACKET = Frontiers.id("crags_stalker_despawn_packet");
    public static final ResourceLocation CRAGS_MONSTER_KILL_PACKET = Frontiers.id("crags_monster_kill_packet");
    public static final ResourceLocation MESSAGE_BOTTLE = Frontiers.id("message_bottle");
    public static final ResourceLocation CHANCE_FOOD_ITEM = Frontiers.id("chance_food_item");
    public static final ResourceLocation ITEM_VACUUM_EMPTY = Frontiers.id("item_vacuum_empty");
    public static final ResourceLocation ITEM_VACUUM_SYNC = Frontiers.id("item_vacuum_sync");

    // Basic S2C Packets
    public static final PacketType<ItemBlockPickupS2CPacket> PICKUP_TO_BLOCK = doS2CPacket("frontiers_pickup_to_block");
    public static final PacketType<ManaOrbSpawnS2CPacket> SPAWN_MANA_ORB = doS2CPacket("frontiers_spawn_mana_orb");
    public static final PacketType<BossBarMusicS2CPacket> UPDATE_BOSSBAR_MUSIC = doS2CPacket("frontiers_update_bossbar_music");

    private static <T extends Packet<ClientGamePacketListener>> PacketType<T> doS2CPacket(String id)
    {
        return new PacketType<>(PacketFlow.CLIENTBOUND, ResourceLocation.withDefaultNamespace(id));
    }

    public static class Client
    {
        public static void bottleMessageWrite(BottleMessageWritePayload payload, ServerPlayer player)
        {
            int slot = payload.slot();
            String text = payload.text();

            ItemStack signed = new ItemStack(ModItem.BOTTLED_MESSAGE.get());
            signed.set(ModDataComponents.BOTTLE_CONTENT.get(), new BottleContentComponent(Filterable.passThrough(text)));

            player.getInventory().setItem(slot, signed);
        }
    }

    public static class Server
    {

    }
}
