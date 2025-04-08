package net.artyrian.frontiers;

import net.artyrian.frontiers.datagen.*;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.item.trim.ModTrimMaterials;
import net.artyrian.frontiers.item.trim.ModTrimPatterns;
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
		pack.addProvider(ModTrimGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder)
	{
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.DIMENSION_TYPE, ModDimension::bootstrapType);

		registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
		// registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, ModTrimPatterns::bootstrap);
	}
}
