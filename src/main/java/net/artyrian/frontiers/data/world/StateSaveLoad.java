package net.artyrian.frontiers.data.world;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.player.PlayerData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StateSaveLoad extends PersistentState
{
    // Globals
    public Boolean isInHardmode = false;
    private static final String HARDMODE_TAGNAME = "frontiersInHardmode";

    // Player Hashmap
    public HashMap<UUID, PlayerData> playerHash = new HashMap<>();
    private static final String PLAYER_AVARICE_TAGNAME = "frontiersUsedAvariceTotem";

    // Bottle Messages List
    public List<ItemStack> bottleItems = new ArrayList<>();
    private static final String BOTTLE_ID_STRING = "ID";

    // All compound tag IDs
    private static final String PLAYERS_TAG = "players";
    private static final String BOTTLE_MESSAGES = "bottle_messages";

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        nbt.putBoolean(HARDMODE_TAGNAME, isInHardmode);

        NbtCompound allPlayersNbt = new NbtCompound();
        playerHash.forEach((uuid, playerData) ->
        {
            NbtCompound playerNbt = new NbtCompound();

            playerNbt.putBoolean(PLAYER_AVARICE_TAGNAME, playerData.avarice_totem);

            allPlayersNbt.put(uuid.toString(), playerNbt);
        });
        nbt.put(PLAYERS_TAG, allPlayersNbt);

        NbtList bottlesNbt = new NbtList();
        for (int i = 0; i < bottleItems.size(); i++)
        {
            ItemStack itemStack = bottleItems.get(i);
            if (!itemStack.isEmpty())
            {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putByte(BOTTLE_ID_STRING, (byte)i);
                bottlesNbt.add(itemStack.encode(registryLookup, nbtCompound));
            }
        }
        nbt.put(BOTTLE_MESSAGES, bottlesNbt);

        return nbt;
    }

    public static StateSaveLoad createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup)
    {
        StateSaveLoad state = new StateSaveLoad();
        state.isInHardmode = tag.getBoolean(HARDMODE_TAGNAME);

        NbtCompound allPlayersNbt = tag.getCompound(PLAYERS_TAG);
        allPlayersNbt.getKeys().forEach(key ->
        {
            PlayerData playerData = new PlayerData();

            playerData.avarice_totem = allPlayersNbt.getCompound(key).getBoolean(PLAYER_AVARICE_TAGNAME);

            UUID uuid = UUID.fromString(key);
            state.playerHash.put(uuid, playerData);
        });

        NbtList bottlesNbt = tag.getList(BOTTLE_MESSAGES, NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < bottlesNbt.size(); i++)
        {
            NbtCompound nbtCompound = bottlesNbt.getCompound(i);
            int j = nbtCompound.getByte(BOTTLE_ID_STRING) & 255;
            if (j >= 0)
            {
                ItemStack stack = ItemStack.fromNbt(registryLookup, nbtCompound).orElse(ItemStack.EMPTY);
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

    private static Type<StateSaveLoad> type = new Type<>(
            StateSaveLoad::createNew,
            StateSaveLoad::createFromNbt,
            null
    );

    // Gets server state.
    public static StateSaveLoad getServerState(MinecraftServer server)
    {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        StateSaveLoad state = persistentStateManager.getOrCreate(type, Frontiers.MOD_ID);
        state.markDirty();
        return state;
    }

    // Gets player state.
    public static PlayerData getPlayerState(LivingEntity player)
    {
        StateSaveLoad server_state = getServerState(player.getWorld().getServer());

        return server_state.playerHash.computeIfAbsent(player.getUuid(), uuid -> new PlayerData());
    }
}
