package net.artyrian.frontiers.exclusive.world;

import net.artyrian.frontiers.definition.entity.types.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.types.passive.CrowEntity;
import net.artyrian.frontiers.reg.content.FREntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

public class EntitySpawnsNF
{
    // Happens in Fabric at FabricEntitySpawning.addSpawns()
    public static void reg(RegisterSpawnPlacementsEvent event)
    {
        event.register(FREntity.JUNGLE_SPIDER.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JungleSpiderEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);

        event.register(FREntity.CROW.get(), SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING, CrowEntity::canSpawn, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
