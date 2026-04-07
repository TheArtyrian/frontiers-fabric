package net.artyrian.frontiers.reg.content;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.misc.CragsMonsterEntity;
import net.artyrian.frontiers.definition.entity.types.misc.CragsStalkerEntity;
import net.artyrian.frontiers.definition.entity.types.misc.ManaOrbEntity;
import net.artyrian.frontiers.definition.entity.types.mob.CrawlerEntity;
import net.artyrian.frontiers.definition.entity.types.mob.JungleSpiderEntity;
import net.artyrian.frontiers.definition.entity.types.passive.CrowEntity;
import net.artyrian.frontiers.definition.entity.types.passive.GoldenChickenEntity;
import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.definition.entity.types.projectile.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class FREntity
{
    // Projectiles
    public static final Supplier<EntityType<BallEntity>> BALL = register("ball", () ->
            EntityType.Builder.<BallEntity>of(BallEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("ball")
    );
    public static final Supplier<EntityType<FruitcakeEntity>> FRUITCAKE = register("fruitcake", () ->
            EntityType.Builder.<FruitcakeEntity>of(FruitcakeEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("fruitcake")
    );
    public static final Supplier<EntityType<BaitEntity>> BAIT = register("bait", () ->
            EntityType.Builder.<BaitEntity>of(BaitEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("bait")
    );
    public static final Supplier<EntityType<GoldenEggEntity>> GOLDEN_EGG = register("golden_egg", () ->
            EntityType.Builder.<GoldenEggEntity>of(GoldenEggEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("golden_egg")
    );
    public static final Supplier<EntityType<WarpArrowEntity>> WARP_ARROW = register("warp_arrow", () ->
            EntityType.Builder.<WarpArrowEntity>of(WarpArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("warp_arrow")
    );
    public static final Supplier<EntityType<BouncyArrowEntity>> BOUNCY_ARROW = register("bouncy_arrow", () ->
            EntityType.Builder.<BouncyArrowEntity>of(BouncyArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("bouncy_arrow")
    );
    public static final Supplier<EntityType<SubzeroArrowEntity>> SUBZERO_ARROW = register("subzero_arrow", () ->
            EntityType.Builder.<SubzeroArrowEntity>of(SubzeroArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("subzero_arrow")
    );
    public static final Supplier<EntityType<DynamiteArrowEntity>> DYNAMITE_ARROW = register("dynamite_arrow", () ->
            EntityType.Builder.<DynamiteArrowEntity>of(DynamiteArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("dynamite_arrow")
    );
    public static final Supplier<EntityType<PrismarineArrowEntity>> PRISMARINE_ARROW = register("prismarine_arrow", () ->
            EntityType.Builder.<PrismarineArrowEntity>of(PrismarineArrowEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("prismarine_arrow")
    );
    public static final Supplier<EntityType<PaleTridentEntity>> PALE_TRIDENT = register("pale_trident", () ->
            EntityType.Builder.<PaleTridentEntity>of(PaleTridentEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .eyeHeight(0.13F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("pale_trident")
    );
    public static final Supplier<EntityType<ManaBottleEntity>> MANA_BOTTLE = register("mana_bottle", () ->
            EntityType.Builder.<ManaBottleEntity>of(ManaBottleEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("mana_bottle")
    );

    // Mobs
    public static final Supplier<EntityType<CrawlerEntity>> CRAWLER = register("crawler", () ->
            EntityType.Builder.of(CrawlerEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.7F)
                    .clientTrackingRange(8)
                    .build("crawler")
    );
    public static final Supplier<EntityType<JungleSpiderEntity>> JUNGLE_SPIDER = register("jungle_spider", () ->
            EntityType.Builder.of(JungleSpiderEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 0.4F)
                    .eyeHeight(0.36F)
                    .clientTrackingRange(8)
                    .build("jungle_spider")
    );
    public static final Supplier<EntityType<PumpkinGolemEntity>> PUMPKIN_GOLEM = register("pumpkin_golem", () ->
            EntityType.Builder.of(PumpkinGolemEntity::new, MobCategory.MISC)
                    .sized(1.0F, 1.0F)
                    .eyeHeight(0.6F)
                    .clientTrackingRange(10)
                    .build("pumpkin_golem")
    );
    public static final Supplier<EntityType<CrowEntity>> CROW = register("crow", () ->
            EntityType.Builder.of(CrowEntity::new, MobCategory.AMBIENT)
                    .sized(0.5F, 0.6F)
                    .eyeHeight(0.4F)
                    .clientTrackingRange(5)
                    .build("crow")
    );
    public static final Supplier<EntityType<GoldenChickenEntity>> GOLDEN_CHICKEN = register("golden_chicken", () ->
            EntityType.Builder.of(GoldenChickenEntity::new, MobCategory.CREATURE)
                    .sized(0.4F, 0.7F)
                    .eyeHeight(0.644F)
                    .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                    .clientTrackingRange(10)
                    .build("golden_chicken")
    );

    // Entities
    public static final Supplier<EntityType<ManaOrbEntity>> MANA_ORB = register("mana_orb", () ->
            EntityType.Builder.<ManaOrbEntity>of(ManaOrbEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(6)
                    .updateInterval(20)
                    .build("mana_orb")
    );
    public static final Supplier<EntityType<CragsStalkerEntity>> CRAGS_STALKER = register("crags_stalker", () ->
            EntityType.Builder.<CragsStalkerEntity>of(CragsStalkerEntity::new, MobCategory.MISC)
                    .sized(0.5F, 2.0F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("crags_stalker")
    );
    public static final Supplier<EntityType<CragsMonsterEntity>> CRAGS_MONSTER = register("crags_monster", () ->
            EntityType.Builder.<CragsMonsterEntity>of(CragsMonsterEntity::new, MobCategory.MISC)
                    .sized(1F, 6F)
                    .clientTrackingRange(8)
                    .updateInterval(10)
                    .build("crags_monster")
    );

    private static <T extends Entity> Supplier<EntityType<T>> register(String name, Supplier<EntityType<T>> type)
    {
        return VectorLib.REGISTRY.registerEntityType(Frontiers.MOD_ID, name, type);
    }

    // Registers mod entities. Just sends a log message.
    public static void registerModEntities()
    {
        //Frontiers.LOGGER.info("Registering Mod Entities for " + Frontiers.MOD_ID);
    }
}
