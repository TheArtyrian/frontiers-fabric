package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

public class FRLevelEvents
{
    public static class Local
    {
        public static final VectorEventSync.EventData ORE_WITHER = VectorEventSync.Local.register(Frontiers.id("ore_wither_away"));
        public static final VectorEventSync.EventData TOWER_SPAWNER_SPAWN = VectorEventSync.Local.register(Frontiers.id("tower_spawner_spawn"));
        public static final VectorEventSync.EventData TOWER_SPAWNER_TINY_POOF = VectorEventSync.Local.register(Frontiers.id("tower_spawner_homunculus_poof"));
        public static final VectorEventSync.EventData TOWER_SPAWNER_ENRAGE = VectorEventSync.Local.register(Frontiers.id("tower_spawner_enrage"));
        public static final VectorEventSync.EventData ONYX_MEAL = VectorEventSync.Local.register(Frontiers.id("onyx_meal_grow"));
        public static final VectorEventSync.EventData SNOW_MELT = VectorEventSync.Local.register(Frontiers.id("snow_melt_use"));
        public static final VectorEventSync.EventData SNOW_MELT_GLISTEN = VectorEventSync.Local.register(Frontiers.id("snow_melt_glisten"));
        public static final VectorEventSync.EventData CURSED_TABLET = VectorEventSync.Local.register(Frontiers.id("cursed_tablet"));
        public static final VectorEventSync.EventData BREWING_STAND_FILL = VectorEventSync.Local.register(Frontiers.id("brewing_stand_fill"));
        public static final VectorEventSync.EventData FURNACES_LIGHT = VectorEventSync.Local.register(Frontiers.id("furnaces_light"));
        public static final VectorEventSync.EventData CRAGS_TELEPORT = VectorEventSync.Local.register(Frontiers.id("crags_teleport"));
        public static final VectorEventSync.EventData MANA_GUI_EFFECT = VectorEventSync.Local.register(Frontiers.id("mana_gui_effect"));
        public static final VectorEventSync.EventData FLAME_PARTICLE_FLARE = VectorEventSync.Local.register(Frontiers.id("flame_particle_flare"));
        public static final VectorEventSync.EventData MONSTER_BAKERY_LIGHT = VectorEventSync.Local.register(Frontiers.id("monster_bakery_light"));

        private static void register()
        {

        }
    }

    public static class Dual
    {
        public static final VectorEventSync.EventData SPIRIT_CANDLE_DETER = VectorEventSync.Dual.register(Frontiers.id("spirit_candle_deter"));
        public static final VectorEventSync.EventData TOWER_SPAWNER_FLAMETRAIL = VectorEventSync.Dual.register(Frontiers.id("tower_spawner_flametrail"));
        public static final VectorEventSync.EventData CRAGS_STALKER_DESPAWN = VectorEventSync.Dual.register(Frontiers.id("crags_stalker_despawn"));
        public static final VectorEventSync.EventData VOID_OR_ENDER_EYE_SMASH = VectorEventSync.Dual.register(Frontiers.id("void_or_ender_eye_smash"));
        public static final VectorEventSync.EventData END_CRYSTAL_SHARD = VectorEventSync.Dual.register(Frontiers.id("end_crystal_shard"));

        private static void register()
        {

        }
    }

    public static class Entity
    {
        public static final VectorEventSync.EventData TOWER_ENTITY_POOF = VectorEventSync.Entity.register(Frontiers.id("tower_entity_poof"));
        public static final VectorEventSync.EventData WITCH_HAT_SPARKLE = VectorEventSync.Entity.register(Frontiers.id("witch_hat_sparkle"));
        public static final VectorEventSync.EventData HOGLIN_TAME = VectorEventSync.Entity.register(Frontiers.id("hoglin_tame"));
        public static final VectorEventSync.EventData TOGGLE_PUMPKIN_GOLEM = VectorEventSync.Entity.register(Frontiers.id("toggle_pumpkin_golem"));

        private static void register()
        {

        }
    }

    public static class Global
    {
        private static void register()
        {

        }
    }

    public static void register()
    {
        Local.register();
        Dual.register();
        Entity.register();
        Global.register();
    }
}
