package net.artyrian.frontiers.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.projectile.BallEntity;
import net.artyrian.frontiers.entity.projectile.BouncyArrowEntity;
import net.artyrian.frontiers.entity.projectile.SubzeroArrowEntity;
import net.artyrian.frontiers.entity.projectile.WarpArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
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
    //public static final EntityType<CobaltBobber> COBALT_BOBBER = register(
    //        "cobalt_bobber",
    //        EntityType.Builder.<CobaltBobber>create(CobaltBobber::new, SpawnGroup.MISC)
    //                .disableSaving()
    //                .disableSummon()
    //                .dimensions(0.25F, 0.25F)
    //                .maxTrackingRange(4)
    //                .trackingTickInterval(5)
    //);


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
