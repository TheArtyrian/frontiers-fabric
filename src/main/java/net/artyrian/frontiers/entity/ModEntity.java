package net.artyrian.frontiers.entity;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntity
{
    // "Projectiles"
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
