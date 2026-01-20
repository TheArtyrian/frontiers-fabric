package net.artyrian.frontiers.definition.data.savedata;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StateSaveLoad extends SavedData
{
    // Globals
    public Boolean isInHardmode = false;
    private static final String HARDMODE_TAGNAME = "frontiersInHardmode";

    // Player Hashmap
    public HashMap<UUID, PlayerData> playerHash = new HashMap<>();
    //private static final String PLAYER_AVARICE_TAGNAME = "frontiersUsedAvariceTotem";

    // Bottle Messages List
    public List<ItemStack> bottleItems = new ArrayList<>();
    private static final String BOTTLE_ID_STRING = "ID";

    // All compound tag IDs
    private static final String PLAYERS_TAG = "players";
    private static final String BOTTLE_MESSAGES = "bottle_messages";

    @Override
    public CompoundTag save(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        nbt.putBoolean(HARDMODE_TAGNAME, isInHardmode);

        CompoundTag allPlayersNbt = new CompoundTag();
        playerHash.forEach((uuid, playerData) ->
        {
            CompoundTag playerNbt = new CompoundTag();

            //playerNbt.putBoolean(PLAYER_AVARICE_TAGNAME, playerData.avarice_totem);

            allPlayersNbt.put(uuid.toString(), playerNbt);
        });
        nbt.put(PLAYERS_TAG, allPlayersNbt);

        ListTag bottlesNbt = new ListTag();
        for (int i = 0; i < bottleItems.size(); i++)
        {
            ItemStack itemStack = bottleItems.get(i);
            if (!itemStack.isEmpty())
            {
                CompoundTag nbtCompound = new CompoundTag();
                nbtCompound.putByte(BOTTLE_ID_STRING, (byte)i);
                bottlesNbt.add(itemStack.save(registryLookup, nbtCompound));
            }
        }
        nbt.put(BOTTLE_MESSAGES, bottlesNbt);

        return nbt;
    }

    public static StateSaveLoad createFromNbt(CompoundTag tag, HolderLookup.Provider registryLookup)
    {
        StateSaveLoad state = new StateSaveLoad();
        state.isInHardmode = tag.getBoolean(HARDMODE_TAGNAME);

        CompoundTag allPlayersNbt = tag.getCompound(PLAYERS_TAG);
        allPlayersNbt.getAllKeys().forEach(key ->
        {
            PlayerData playerData = new PlayerData();

            //playerData.avarice_totem = allPlayersNbt.getCompound(key).getBoolean(PLAYER_AVARICE_TAGNAME);

            UUID uuid = UUID.fromString(key);
            state.playerHash.put(uuid, playerData);
        });

        ListTag bottlesNbt = tag.getList(BOTTLE_MESSAGES, Tag.TAG_COMPOUND);
        for (int i = 0; i < bottlesNbt.size(); i++)
        {
            CompoundTag nbtCompound = bottlesNbt.getCompound(i);
            int j = nbtCompound.getByte(BOTTLE_ID_STRING) & 255;
            if (j >= 0)
            {
                ItemStack stack = ItemStack.parse(registryLookup, nbtCompound).orElse(ItemStack.EMPTY);
                if (!stack.isEmpty())
                {
                    state.bottleItems.add(stack);
                }
            }
        }

        return state;
    }

    public static StateSaveLoad createNew()
    {
        StateSaveLoad state = new StateSaveLoad();
        state.isInHardmode = false;
        state.playerHash = new HashMap<>();
        state.bottleItems = new ArrayList<>();
        return state;
    }

    private static Factory<StateSaveLoad> type = new Factory<>(
            StateSaveLoad::createNew,
            StateSaveLoad::createFromNbt,
            null
    );

    // Gets server state.
    public static StateSaveLoad getServerState(MinecraftServer server)
    {
        DimensionDataStorage persistentStateManager = server.getLevel(Level.OVERWORLD).getDataStorage();

        StateSaveLoad state = persistentStateManager.computeIfAbsent(type, Frontiers.MOD_ID);
        state.setDirty();
        return state;
    }

    // Gets player state.
    public static PlayerData getPlayerState(LivingEntity player)
    {
        StateSaveLoad server_state = getServerState(player.level().getServer());

        return server_state.playerHash.computeIfAbsent(player.getUUID(), uuid -> new PlayerData());
    }
}
