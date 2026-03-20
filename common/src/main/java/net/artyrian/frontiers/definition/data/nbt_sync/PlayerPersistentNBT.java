package net.artyrian.frontiers.definition.data.nbt_sync;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.networking.payload.BuffSyncPayload;
import net.artyrian.frontiers.definition.networking.payload.PlayerAvariceTotemPayload;
import net.artyrian.frontiers.mixin_intf.PlayerIntf;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.vertisoft.vectorlib.VectorLib;
import org.jetbrains.annotations.Nullable;

public class PlayerPersistentNBT
{
    public static final String ID = "FrontiersPersistentUserdata";

    public static final String TOTEM = "totem";
    public static final String USED_HP_APPLE = "used_hp_apple";
    public static final String SANITY = "sanity";
    public static final String SANITY_TICK = "sanity_tick";
    public static final String CRAGSMONSTER = "cragsmonster_kill";

    public static CompoundTag getPlayerNBT(Player player) { return ((PlayerIntf)player).frontiersArtyrian$getPersistentNbt(); }

    public static CompoundTag init()
    {
        CompoundTag starter = new CompoundTag();

        starter.putBoolean(USED_HP_APPLE, false);
        starter.putInt(SANITY_TICK, 0);
        starter.putInt(SANITY, 20);

        return starter;
    }

    public static void handleRespawn(ServerPlayer oldPl, ServerPlayer newPl)
    {
        CompoundTag oldTag = PlayerPersistentNBT.getPlayerNBT(oldPl);
        if (oldTag != null)
        {
            CompoundTag newTag = PlayerPersistentNBT.getPlayerNBT(newPl);
            if (oldTag.contains(USED_HP_APPLE, ByteTag.TAG_BYTE))
            {
                newTag.putBoolean(USED_HP_APPLE, oldTag.getBoolean(USED_HP_APPLE));
                PlayerPersistentNBT.Buffs.updatePlayer(newPl, newPl.level());
            }
        }
    }

    public static int fallback(CompoundTag tag, String contains, int fallback)
    {
        if (tag != null && tag.contains(contains, ByteTag.TAG_INT)) return tag.getInt(contains);
        return fallback;
    }

    public static boolean fallback(CompoundTag tag, String contains, boolean fallback)
    {
        if (tag != null && tag.contains(contains, ByteTag.TAG_BYTE)) return tag.getBoolean(contains);
        return fallback;
    }

    public static class Buffs
    {
        public static void appleBuff(Item type, ItemStack stack, Level level, LivingEntity user)
        {
            if (!level.isClientSide() && user instanceof ServerPlayer player)
            {
                CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
                player.awardStat(Stats.ITEM_USED.get(type));

                CompoundTag tag = PlayerPersistentNBT.getPlayerNBT(player);
                tag.putBoolean(PlayerPersistentNBT.USED_HP_APPLE, true);

                PlayerPersistentNBT.Buffs.updatePlayer(player, level);
            }
        }

        public static void updatePlayer(Player player, @Nullable Level level)
        {
            if (level != null && !level.isClientSide() && player instanceof ServerPlayer serverPlayer)
            {
                ((PlayerIntf)player).frontiersArtyrian$checkBuffsStatus();

                MinecraftServer server = level.getServer();
                if (server != null)
                {
                    CompoundTag tag = getPlayerNBT(player);
                    VectorLib.NETWORK.sendToPlayer(serverPlayer, new BuffSyncPayload(tag.getBoolean(USED_HP_APPLE)));
                }
            }
        }
    }

    public static class AvariceTotem
    {
        public static boolean setTotemStatus(PlayerIntf player, boolean used_totem)
        {
            CompoundTag compound = player.frontiersArtyrian$getPersistentNbt();
            compound.putBoolean(TOTEM, used_totem);

            return used_totem;
        }
    }

    public static class Sanity
    {
        /** Adds to the sanity NBT. */
        public static int addSanity(PlayerIntf player, int amount)
        {
            CompoundTag compound = player.frontiersArtyrian$getPersistentNbt();
            int sanity = compound.getInt(SANITY);

            sanity = Math.clamp(sanity + amount, 0, 20);

            compound.putInt(SANITY, sanity);

            return sanity;
        }

        /** Subtracts from the sanity NBT. */
        public static int removeSanity(PlayerIntf player, int amount)
        {
            return addSanity(player, -amount);
        }

        /** Adds to the sanity tick NBT. */
        public static int addSanityTick(PlayerIntf player, int amount)
        {
            CompoundTag compound = player.frontiersArtyrian$getPersistentNbt();
            int sanity = compound.getInt(SANITY_TICK);

            sanity = Math.clamp(sanity + amount, 0, 1200);

            compound.putInt(SANITY_TICK, sanity);

            return sanity;
        }

        /** Subtracts from the sanity tick NBT. */
        public static int removeSanityTick(PlayerIntf player, int amount) { return addSanityTick(player, -amount); }

        public static int resetSanityTick(PlayerIntf player, boolean subtracting)
        {
            CompoundTag compound = player.frontiersArtyrian$getPersistentNbt();

            int setup = (subtracting) ? 1200 : 0;
            compound.putInt(SANITY_TICK, setup);

            return setup;
        }

        public static boolean setCragsMonsterKill(Player player)
        {
            CompoundTag compound = ((PlayerIntf)player).frontiersArtyrian$getPersistentNbt();
            compound.putBoolean(CRAGSMONSTER, true);

            return true;
        }
    }
}
