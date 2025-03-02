package net.artyrian.frontiers.data.world;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

public class StateSaveLoad extends PersistentState
{
    public Boolean isInHardmode = false;
    private static final String TAGNAME = "frontiersInHardmode";

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        nbt.putBoolean(TAGNAME, isInHardmode);
        return nbt;
    }

    public static StateSaveLoad createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup)
    {
        StateSaveLoad state = new StateSaveLoad();
        state.isInHardmode = tag.getBoolean(TAGNAME);
        return state;
    }

    public static StateSaveLoad createNew()
    {
        StateSaveLoad state = new StateSaveLoad();
        state.isInHardmode = false;
        return state;
    }

    private static Type<StateSaveLoad> type = new Type<>(
            StateSaveLoad::createNew,
            StateSaveLoad::createFromNbt,
            null
    );

    public static StateSaveLoad getServerState(MinecraftServer server)
    {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        StateSaveLoad state = persistentStateManager.getOrCreate(type, Frontiers.MOD_ID);
        state.markDirty();
        return state;
    }
}
