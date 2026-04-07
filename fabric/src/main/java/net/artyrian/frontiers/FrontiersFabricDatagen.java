package net.artyrian.frontiers;

import net.artyrian.frontiers.datagen.frontiers.*;
import net.artyrian.frontiers.datagen.frontiers.loot.FRChestLootTableProvider;
import net.artyrian.frontiers.datagen.frontiers.loot.FREntityLootTableProvider;
import net.artyrian.frontiers.datagen.frontiers.loot.FRLootTableProvider;
import net.artyrian.frontiers.datagen.frontiers.tag.*;
import net.artyrian.frontiers.reg.property.FRTrimMaterials;
import net.artyrian.frontiers.reg.property.FRTrimPatterns;
import net.artyrian.frontiers.reg.world.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class FrontiersFabricDatagen implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        this.frontiersDatapack(generator);
        this.fdDatapack(generator);
        this.bfDatapack(generator);
        this.ddyeDatapack(generator);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder)
    {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, FRFeaturesConfigured::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, FRFeaturesPlaced::bootstrap);

        registryBuilder.add(Registries.STRUCTURE, FRStructures::bootstrap);
        registryBuilder.add(Registries.STRUCTURE_SET, FRStructureSets::bootstrap);

        registryBuilder.add(Registries.DIMENSION_TYPE, FRDimension::bootstrapType);

        registryBuilder.add(Registries.TRIM_MATERIAL, FRTrimMaterials::bootstrap);
        registryBuilder.add(Registries.TRIM_PATTERN, FRTrimPatterns::bootstrap);
    }

    private void frontiersDatapack(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack frontiers = generator.createPack();

        frontiers.addProvider(FRBlockTagProvider::new);
        frontiers.addProvider(FRItemTagProvider::new);
        frontiers.addProvider(FREntityTagProvider::new);
        frontiers.addProvider(FREnchantTagProvider::new);
        frontiers.addProvider(FRBiomeTagProvider::new);
        frontiers.addProvider(FRStructureTagProvider::new);

        frontiers.addProvider(FRLootTableProvider::new);
        frontiers.addProvider(FRChestLootTableProvider::new);
        frontiers.addProvider(FREntityLootTableProvider::new);

        frontiers.addProvider(FRModelProvider::new);
        frontiers.addProvider(FRRecipeProvider::new);
        frontiers.addProvider(FRAdvancementProvider::new);
        frontiers.addProvider(FRWorldGenerator::new);
        frontiers.addProvider(FRTrimGenerator::new);

        frontiers.addProvider(FRSoundsJson::new);
        frontiers.addProvider(FRLangProviderEnglish::new);
    }

    private void fdDatapack(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack data = generator.createBuiltinResourcePack(Frontiers.id("farmersdelight_frnt"));
    }

    private void bfDatapack(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack data = generator.createBuiltinResourcePack(Frontiers.id("bountifulfares_frnt"));
    }

    private void ddyeDatapack(FabricDataGenerator generator)
    {
        FabricDataGenerator.Pack data = generator.createBuiltinResourcePack(Frontiers.id("delicatedyes_frnt"));
    }
}
