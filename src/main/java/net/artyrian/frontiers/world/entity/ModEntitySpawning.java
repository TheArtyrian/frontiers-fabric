package net.artyrian.frontiers.world.entity;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.mob.JungleSpiderEntity;
import net.artyrian.frontiers.entity.passive.CrowEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawning
{
    private static void addSpawns()
    {
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                SpawnGroup.MONSTER, ModEntity.JUNGLE_SPIDER, 100, 4, 4);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                SpawnGroup.AMBIENT, ModEntity.CROW, 40, 1, 3);
    }

    private static void addRestrictions()
    {
        SpawnRestriction.register(ModEntity.JUNGLE_SPIDER,
                SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, JungleSpiderEntity::canSpawn);

        SpawnRestriction.register(ModEntity.CROW,
                SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING, CrowEntity::canSpawn);
    }

    public static void register()
    {
        addSpawns();
        addRestrictions();
    }
}
