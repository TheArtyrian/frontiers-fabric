package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.ItemVacuumBlockEntity;
import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.mixin_intf.*;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.Filterable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.UUID;

public class ModNetworkConstants
{
    // Payload Packets
    public static final ResourceLocation WITHER_HARDMODE = Frontiers.id("wither_hardmode");
    public static final ResourceLocation SANITY_SYNC_PACKET = Frontiers.id("sanity_sync_packet");
    public static final ResourceLocation MANA_SYNC_PACKET = Frontiers.id("mana_sync_packet");
    public static final ResourceLocation CHANCE_FOOD_ITEM = Frontiers.id("chance_food_item");
    public static final ResourceLocation ITEM_VACUUM_EMPTY = Frontiers.id("item_vacuum_empty");
    public static final ResourceLocation ITEM_VACUUM_SYNC = Frontiers.id("item_vacuum_sync");
    // TODO?: Since these all utilize persistent playerdata and get called rarely, maybe it could be condensed into one? Seems fragile though, won't do for now
    public static final ResourceLocation PLAYER_AVARICE_PACKET = Frontiers.id("player_avarice_packet");
    public static final ResourceLocation CRAGS_MONSTER_KILL_PACKET = Frontiers.id("crags_monster_kill_packet");
    public static final ResourceLocation SYNC_PLAYER_BUFFS = Frontiers.id("sync_player_buffs_packet");

    public static final ResourceLocation MESSAGE_BOTTLE = Frontiers.id("message_bottle");

    // Basic S2C Packets
    public static final PacketType<ItemBlockPickupS2CPacket> PICKUP_TO_BLOCK = doS2CPacket("frontiers_pickup_to_block");
    public static final PacketType<ManaOrbSpawnS2CPacket> SPAWN_MANA_ORB = doS2CPacket("frontiers_spawn_mana_orb");
    public static final PacketType<BossBarMusicS2CPacket> UPDATE_BOSSBAR_MUSIC = doS2CPacket("frontiers_update_bossbar_music");

    private static <T extends Packet<ClientGamePacketListener>> PacketType<T> doS2CPacket(String id)
    {
        return new PacketType<>(PacketFlow.CLIENTBOUND, Frontiers.id(id));
    }

    public static class ToServer
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

    public static class ToClient
    {
        public static void witherHardmodeSet(WitherHardmodePayload payload, Minecraft client)
        {
            client.execute(() -> {
                if (payload.bool()) Frontiers.LOGGER.info("[Frontiers] Hardmode has been successfully set.");
            });
        }

        public static void avariceTotem(PlayerAvariceTotemPayload payload, LocalPlayer player)
        {
            boolean boolpayload = payload.bool();

            PlayerPersistentNBT.AvariceTotem.setTotemStatus(((PlayerIntf)player), boolpayload);
        }

        public static void sanitySync(SanitySyncPayload payload, Player reciever)
        {
            UUID uuid = payload.player_id();
            int sanity = payload.sanity();
            int sanitytick = payload.sanitytick();

            Player player = reciever.level().getPlayerByUUID(uuid);

            if (player != null)
            {
                CompoundTag compound = PlayerPersistentNBT.getPlayerNBT(player);
                compound.putInt(PlayerPersistentNBT.SANITY, sanity);
                compound.putInt(PlayerPersistentNBT.SANITY_TICK, sanitytick);
            }
            else
            {
                Frontiers.LOGGER.warn("[FRONTIERS]: Received sanity sync packet with an unknown player UUID of " + uuid + ", ignoring");
            }
        }

        public static void cragsMonsterKillPlayer(CragsMonsterKillPayload payload, LocalPlayer player)
        {
            CompoundTag compound = PlayerPersistentNBT.getPlayerNBT(player);
            compound.putBoolean(PlayerPersistentNBT.CRAGSMONSTER, payload.bool());
        }

        public static void syncPlayerBuffs(BuffSyncPayload payload, LocalPlayer player)
        {
            CompoundTag compound = PlayerPersistentNBT.getPlayerNBT(player);
            compound.putBoolean(PlayerPersistentNBT.USED_HP_APPLE, payload.hp_apple());
            ((PlayerIntf)player).frontiersArtyrian$checkBuffsStatus();
        }

        public static void manaSync(ManaSyncPayload payload, LocalPlayer player)
        {
            UUID uuid = payload.player_id();
            Player playerTarget = player.level().getPlayerByUUID(uuid);

            if (playerTarget != null)
            {
                CompoundTag compound = PlayerPersistentNBT.getPlayerNBT(playerTarget);
                compound.putInt(PlayerPersistentNBT.MANA_LEVEL, payload.level());
                compound.putInt(PlayerPersistentNBT.MANA_POINTS, payload.points());
                compound.putInt(PlayerPersistentNBT.MANA_TO_NEXT_LEVEL, payload.to_next());
            }
            else
            {
                Frontiers.LOGGER.warn("[FRONTIERS]: Received mana sync packet with an unknown player UUID of " + uuid + ", ignoring");
            }
        }

        public static void chanceFoodItem(ChanceFoodItemPayload payload, LocalPlayer player)
        {
            ItemStack stack = payload.stack();

            stack.consume(1, player);
        }
    }
}
