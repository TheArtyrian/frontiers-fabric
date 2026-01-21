package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.definition.entity.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.passive.CrowEntity;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class FabricEntitySpawning
{
    private static void addSpawns()
    {
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                MobCategory.MONSTER, ModEntity.JUNGLE_SPIDER.get(), 100, 4, 4);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
                MobCategory.AMBIENT, ModEntity.CROW.get(), 40, 1, 3);
    }

    private static void addRestrictions()
    {
        SpawnPlacements.register(ModEntity.JUNGLE_SPIDER.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JungleSpiderEntity::canSpawn);

        SpawnPlacements.register(ModEntity.CROW.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, CrowEntity::canSpawn);
    }

    public static void register()
    {
        addSpawns();
        addRestrictions();
    }
}
