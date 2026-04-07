package net.artyrian.frontiers.exclusive.world.entity;

import net.artyrian.frontiers.definition.entity.types.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.types.passive.CrowEntity;
import net.artyrian.frontiers.reg.content.FREntity;
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
    // Happens in NeoForge EntitySpawnsNF
    private static void addSpawns()
    {
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_JUNGLE),
                MobCategory.MONSTER, FREntity.JUNGLE_SPIDER.get(), 100, 4, 4);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
                MobCategory.AMBIENT, FREntity.CROW.get(), 40, 1, 3);
    }

    private static void addRestrictions()
    {
        SpawnPlacements.register(FREntity.JUNGLE_SPIDER.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JungleSpiderEntity::canSpawn);

        SpawnPlacements.register(FREntity.CROW.get(),
                SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, CrowEntity::canSpawn);
    }

    public static void register()
    {
        addSpawns();
        addRestrictions();
    }
}
