package net.artyrian.frontiers.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModTrimGenerator extends FabricDynamicRegistryProvider
{
    public ModTrimGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries)
    {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.TRIM_MATERIAL));
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.TRIM_PATTERN));
    }

    @Override
    public String getName()
    {
        return "Trim Registries";
    }
}
