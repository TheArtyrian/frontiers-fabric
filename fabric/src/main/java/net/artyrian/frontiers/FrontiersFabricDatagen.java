package net.artyrian.frontiers;

import net.artyrian.frontiers.datagen.*;
import net.artyrian.frontiers.datagen.loot.*;
import net.artyrian.frontiers.datagen.tag.*;
import net.artyrian.frontiers.reg.content.ModStructure;
import net.artyrian.frontiers.reg.content.ModStructureSets;
import net.artyrian.frontiers.reg.misc.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class FrontiersFabricDatagen implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Add datagen files.
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModEntityTagProvider::new);
        pack.addProvider(ModEnchantTagProvider::new);
        pack.addProvider(ModBiomeTagProvider::new);

        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModChestLootTableProvider::new);
        pack.addProvider(ModGiftLootTableProvider::new);
        pack.addProvider(ModEntityLootTableProvider::new);
        pack.addProvider(FRLootModTableProvider.Modify::new);
        pack.addProvider(FRLootModTableProvider.Replace::new);

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
        pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModTrimGenerator::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder)
    {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);

        registryBuilder.add(Registries.STRUCTURE, ModStructure::bootstrap);
        registryBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);

        registryBuilder.add(Registries.DIMENSION_TYPE, ModDimension::bootstrapType);

        registryBuilder.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
        registryBuilder.add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap);
    }
}
