package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.advancement.criterion.BeaconBrimtanCriterion;
import net.artyrian.frontiers.definition.advancement.criterion.CurseAltarCriterion;
import net.artyrian.frontiers.definition.advancement.criterion.EnrageTowerSpawnerCriterion;
import net.artyrian.frontiers.definition.advancement.criterion.EntityKilledNearbyCriterion;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;

public class ModCriteria
{
    public static final Supplier<CurseAltarCriterion> USED_CURSE_ALTAR = register("used_curse_altar", CurseAltarCriterion::new);
    public static final Supplier<EntityKilledNearbyCriterion> ENTITY_KILLED_NEARBY = register("entity_killed_nearby", EntityKilledNearbyCriterion::new);
    public static final Supplier<BeaconBrimtanCriterion> BEACON_POWERED_WITH_BRIMTAN = register("beacon_powered_with_brimtan", BeaconBrimtanCriterion::new);
    public static final Supplier<EnrageTowerSpawnerCriterion> ENRAGE_TOWER_SPAWNER = register("enrage_tower_spawner", EnrageTowerSpawnerCriterion::new);
    public static final Supplier<PlayerTrigger> SLEPT_ON_PHANTOM_BED = register("slept_on_phantom_bed", PlayerTrigger::new);

    public static <T extends CriterionTrigger<?>> Supplier<T> register(String id, Supplier<T> criterion)
    {
        return VectorLib.REGISTRY.registerAdvCriteria(Frontiers.MOD_ID, id, criterion);
    }

    public static void registerCriterion()
    {
        // RETURN THE SLAB, OR SUFFER MY CURSE
    }
}
