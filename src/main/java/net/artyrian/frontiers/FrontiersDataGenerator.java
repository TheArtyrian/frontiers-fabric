package net.artyrian.frontiers;

import net.artyrian.frontiers.compat.farmersdelight.FDItem;
import net.artyrian.frontiers.compat.farmersdelight.FDItemTabs;
import net.artyrian.frontiers.compat.farmersdelight.FDRecipeProvider;
import net.artyrian.frontiers.datagen.*;
import net.artyrian.frontiers.world.ModConfiguredFeatures;
import net.artyrian.frontiers.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class FrontiersDataGenerator implements DataGeneratorEntrypoint
{
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator)
	{
		FabricDataGenerator.Pack pack = generator.createPack();

		// Add datagen files.
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModEntityTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModAdvancementProvider::new);
		pack.addProvider(ModWorldGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder)
	{
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
