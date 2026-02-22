package net.vertisoft.vectorlib.platform;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class VectorPlatformFabric implements VectorPlatformIntf
{
    @Override
    public String getPlatformName() {
        return FABRIC;
    }

    @Override
    public boolean isModLoaded(String modId) { return FabricLoader.getInstance().isModLoaded(modId); }

    @Override
    public Path getConfigDirectory() { return FabricLoader.getInstance().getConfigDir(); }

    @Override
    public boolean isDevelopmentEnvironment()
    {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isClient() { return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT; }

    @Override
    public boolean isDatagen()
    {
        try
        {
            Class.forName("net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint");
            return System.getProperty("fabric-api.datagen") != null;
        }
        catch (ClassNotFoundException unused)
        {
            return false;
        }
    }
}
