package net.vertisoft.vectorlib.agnostic.networking.eventsync;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Allows definition of client-side "level events" completely seperate of Vanilla.<p>
 * To gain a basic understanding of a level event in Minecraft, here's a few examples:
 * <ul>
 * <li>Monster Spawner spawning effects (flames & smoke)
 * <li>Bone Meal growth particles
 * <li>Copper wax on/off</li>
 * </ul>
 * In short, level events sum up to performing common effects on clients, and can be called from either client or server.
 * This class allows for defining custom ones. There's several kinds:
 * <ul>
 * <li> {@code Local}: Fires off to players who can see it nearby - good for particles & sfx
 * <li> {@code Dual}: Fires off to nearby players, but takes in 2 positions - good for advanced effects
 * <li> {@code Entity}: Fires off to nearby players, but uses an entity as the target - good for effects that require entity data
 * <li> {@code Global}: Fires off to ALL connected players - good for boss summon sfx and important sound-related events
 * </ul>
 */
public class VectorEventSync
{
    private static final String WTF = "The provided LevelAccessor doesn't extend VectorLevelAccess?????";

    public static class Local
    {
        public static final Map<String, List<VecEventAlias>> EVENT_MAPDEX = new HashMap<>();
        public static final Map<ResourceLocation, Integer> MAP_FREEZER = new HashMap<>();

        public static EventData register(ResourceLocation mapper, VecEventAlias event)
        {
            if (!EVENT_MAPDEX.containsKey(mapper.getNamespace()))
            {
                EVENT_MAPDEX.put(mapper.getNamespace(), new ArrayList<>());
            }
            List<VecEventAlias> list = EVENT_MAPDEX.get(mapper.getNamespace());
            int ordinal;

            if (!MAP_FREEZER.containsKey(mapper))
            {
                list.add(event);
                ordinal = list.indexOf(event);
                MAP_FREEZER.put(mapper, ordinal);
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync local list already contains event that maps to %s", mapper));

            return new EventData(mapper, ordinal);
        }

        public static void fireEvent(LevelAccessor level, BlockPos pos, EventData event, int data)
        {
            VectorEventSync.Local.fireEvent(null, level, pos, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, BlockPos pos, EventData event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPDEX.containsKey(event.getMod()))
                    {
                        vecacc.vectorLib$fireEvent(player, event.getMod(), event.getId(), pos, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("Namespace %1s doesn't exist for %2s VectorEvent", event.getMod(), "local"));
                    }
                }
                else throw new IllegalArgumentException(WTF);
            }
            catch (IllegalArgumentException exc)
            {
                VectorLib.LOGGER.error("Couldn't fire local VectorEvent, see below", exc);
            }
        }

        @FunctionalInterface
        public interface VecEventAlias
        {
            void execute(Level level, BlockPos pos, int data);
        }
    }

    public static class Dual
    {
        public static final Map<String, List<VecDualEventAlias>> EVENT_MAPDEX = new HashMap<>();
        public static final Map<ResourceLocation, Integer> MAP_FREEZER = new HashMap<>();

        public static EventData register(ResourceLocation mapper, VecDualEventAlias event)
        {
            if (!EVENT_MAPDEX.containsKey(mapper.getNamespace()))
            {
                EVENT_MAPDEX.put(mapper.getNamespace(), new ArrayList<>());
            }
            List<VecDualEventAlias> list = EVENT_MAPDEX.get(mapper.getNamespace());
            int ordinal;

            if (!MAP_FREEZER.containsKey(mapper))
            {
                list.add(event);
                ordinal = list.indexOf(event);
                MAP_FREEZER.put(mapper, ordinal);
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync dual-pos list already contains event that maps to %s", mapper));

            return new EventData(mapper, ordinal);
        }

        public static void fireEvent(LevelAccessor level, Vec3 pos1, Vec3 pos2, EventData event, int data)
        {
            VectorEventSync.Dual.fireEvent(null, level, pos1, pos2, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, Vec3 pos1, Vec3 pos2, EventData event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPDEX.containsKey(event.getMod()))
                    {
                        vecacc.vectorLib$fireDual(player, event.getMod(), event.getId(), pos1, pos2, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("Namespace %1s doesn't exist for %2s VectorEvent", event.getMod(), "dual-pos"));
                    }
                }
                else throw new IllegalArgumentException(WTF);
            }
            catch (IllegalArgumentException exc)
            {
                VectorLib.LOGGER.error("Couldn't fire dual-pos VectorEvent, see below", exc);
            }
        }

        @FunctionalInterface
        public interface VecDualEventAlias
        {
            void execute(Level level, Vec3 pos1, Vec3 pos2, int data);
        }
    }

    public static class Entity
    {
        public static final Map<String, List<VecEntityEventAlias>> EVENT_MAPDEX = new HashMap<>();
        public static final Map<ResourceLocation, Integer> MAP_FREEZER = new HashMap<>();

        public static EventData register(ResourceLocation mapper, VecEntityEventAlias event)
        {
            if (!EVENT_MAPDEX.containsKey(mapper.getNamespace()))
            {
                EVENT_MAPDEX.put(mapper.getNamespace(), new ArrayList<>());
            }
            List<VecEntityEventAlias> list = EVENT_MAPDEX.get(mapper.getNamespace());
            int ordinal;

            if (!MAP_FREEZER.containsKey(mapper))
            {
                list.add(event);
                ordinal = list.indexOf(event);
                MAP_FREEZER.put(mapper, ordinal);
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync entity list already contains event that maps to %s", mapper));

            return new EventData(mapper, ordinal);
        }

        public static void fireEvent(LevelAccessor level, net.minecraft.world.entity.Entity entity, EventData event, int data)
        {
            VectorEventSync.Entity.fireEvent(null, level, entity, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, net.minecraft.world.entity.Entity entity, EventData event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPDEX.containsKey(event.getMod()))
                    {
                        vecacc.vectorLib$fireEntity(player, event.getMod(), event.getId(), entity, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("Namespace %1s doesn't exist for %2s VectorEvent", event.getMod(), "entity"));
                    }
                }
                else throw new IllegalArgumentException(WTF);
            }
            catch (IllegalArgumentException exc)
            {
                VectorLib.LOGGER.error("Couldn't fire entity VectorEvent, see below", exc);
            }
        }

        @FunctionalInterface
        public interface VecEntityEventAlias
        {
            void execute(Level level, net.minecraft.world.entity.Entity entity, int data);
        }
    }

    public static class Global
    {
        public static final Map<String, List<VecGlobalEventAlias>> EVENT_MAPDEX = new HashMap<>();
        public static final Map<ResourceLocation, Integer> MAP_FREEZER = new HashMap<>();

        public static EventData register(ResourceLocation mapper, VecGlobalEventAlias event)
        {
            if (!EVENT_MAPDEX.containsKey(mapper.getNamespace()))
            {
                EVENT_MAPDEX.put(mapper.getNamespace(), new ArrayList<>());
            }
            List<VecGlobalEventAlias> list = EVENT_MAPDEX.get(mapper.getNamespace());
            int ordinal;

            if (!MAP_FREEZER.containsKey(mapper))
            {
                list.add(event);
                ordinal = list.indexOf(event);
                MAP_FREEZER.put(mapper, ordinal);
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync global list already contains event that maps to %s", mapper));

            return new EventData(mapper, ordinal);
        }

        public static void fireEvent(LevelAccessor level, BlockPos pos, EventData event, int data)
        {
            VectorEventSync.Global.fireEvent(null, level, pos, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, BlockPos pos, EventData event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPDEX.containsKey(event.getMod()))
                    {
                        vecacc.vectorLib$fireGlobal(player, event.getMod(), event.getId(), pos, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("Namespace %1s doesn't exist for %2s VectorEvent", event.getMod(), "global"));
                    }
                }
                else throw new IllegalArgumentException(WTF);
            }
            catch (IllegalArgumentException exc)
            {
                VectorLib.LOGGER.error("Couldn't fire global VectorEvent, see below", exc);
            }
        }

        @FunctionalInterface
        public interface VecGlobalEventAlias
        {
            void execute(Level level, Vec3 pos, int data);
        }
    }

    public static class EventData
    {
        private final ResourceLocation location;
        private final int id;

        public EventData(ResourceLocation location, int id)
        {
            this.location = location;
            this.id = id;
        }

        public int getId() { return id; }
        public String getMod() { return location.getNamespace(); }
        public String getName() { return location.getPath(); }
    }
}
