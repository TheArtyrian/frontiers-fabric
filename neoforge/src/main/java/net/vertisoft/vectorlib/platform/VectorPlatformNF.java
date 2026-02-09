package net.vertisoft.vectorlib.platform;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.data.loading.DatagenModLoader;

import java.nio.file.Path;

public class VectorPlatformNF implements VectorPlatformIntf
{
    @Override
    public String getPlatformName() { return "NeoForge";}

    @Override
    public boolean isModLoaded(String modId)
    {
        return (ModList.get() != null) ? ModList.get().isLoaded(modId) : false;
    }

    @Override
    public boolean isDevelopmentEnvironment()
    {
        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isClient() { return FMLLoader.getDist().isClient(); }

    @Override
    public boolean isDatagen() { return DatagenModLoader.isRunningDataGen(); }

    @Override
    public Path getConfigDirectory() { return FMLPaths.CONFIGDIR.get(); }
}