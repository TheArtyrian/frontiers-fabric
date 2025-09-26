package net.artyrian.frontiers.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.misc.CragsMonsterEntity;
import net.artyrian.frontiers.entity.misc.CragsStalkerEntity;
import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.artyrian.frontiers.entity.mob.JungleSpiderEntity;
import net.artyrian.frontiers.entity.projectile.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntity
{
    // Projectiles
    public static final EntityType<BallEntity> BALL = register(
            "ball",
            EntityType.Builder.<BallEntity>create(BallEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
    );
    public static final EntityType<WarpArrowEntity> WARP_ARROW = register(
            "warp_arrow",
            EntityType.Builder.<WarpArrowEntity>create(WarpArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );
    public static final EntityType<BouncyArrowEntity> BOUNCY_ARROW = register(
            "bouncy_arrow",
            EntityType.Builder.<BouncyArrowEntity>create(BouncyArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );
    public static final EntityType<SubzeroArrowEntity> SUBZERO_ARROW = register(
            "subzero_arrow",
            EntityType.Builder.<SubzeroArrowEntity>create(SubzeroArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );
    public static final EntityType<DynamiteArrowEntity> DYNAMITE_ARROW = register(
            "dynamite_arrow",
            EntityType.Builder.<DynamiteArrowEntity>create(DynamiteArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );
    public static final EntityType<PrismarineArrowEntity> PRISMARINE_ARROW = register(
            "prismarine_arrow",
            EntityType.Builder.<PrismarineArrowEntity>create(PrismarineArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );
    public static final EntityType<PaleTridentEntity> PALE_TRIDENT = register(
            "pale_trident",
            EntityType.Builder.<PaleTridentEntity>create(PaleTridentEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
    );

    // Mobs
    public static final EntityType<CrawlerEntity> CRAWLER = register(
            "crawler",
            EntityType.Builder.create(CrawlerEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6F, 1.7F)
                    .maxTrackingRange(8)
    );
    public static final EntityType<JungleSpiderEntity> JUNGLE_SPIDER = register(
            "jungle_spider",
            EntityType.Builder.create(JungleSpiderEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.6F, 0.4F)
                    .eyeHeight(0.36F)
                    .maxTrackingRange(8)
    );

    // Entities
    public static final EntityType<CragsStalkerEntity> CRAGS_STALKER = register(
            "crags_stalker",
            EntityType.Builder.<CragsStalkerEntity>create(CragsStalkerEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 2.0F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
    );
    public static final EntityType<CragsMonsterEntity> CRAGS_MONSTER = register(
            "crags_monster",
            EntityType.Builder.<CragsMonsterEntity>create(CragsMonsterEntity::new, SpawnGroup.MISC)
                    .dimensions(1F, 6F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(10)
    );


    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type)
    {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(Frontiers.MOD_ID, name), type.build(name));
    }

    // Registers mod entities. Just sends a log message.
    public static void registerModEntities()
    {
        //Frontiers.LOGGER.info("Registering Mod Entities for " + Frontiers.MOD_ID);
    }
}
