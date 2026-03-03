package net.vertisoft.vectorlib.agnostic.networking.eventsync;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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
        // Regular Events
        public static final List<VecEventAlias> EVENT_LIST = new ArrayList<>();
        private static final Map<ResourceLocation, Integer> EVENT_MAPPING = new HashMap<>();

        public static ResourceLocation register(ResourceLocation mapper, VecEventAlias event)
        {
            EVENT_LIST.add(event);
            if (!EVENT_MAPPING.containsKey(mapper))
            {
                EVENT_MAPPING.put(mapper, EVENT_LIST.indexOf(event));
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync list already contains event that maps to %s", mapper.toString()));

            return mapper;
        }

        public static void fireEvent(LevelAccessor level, BlockPos pos, ResourceLocation event, int data)
        {
            VectorEventSync.Local.fireEvent(null, level, pos, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, BlockPos pos, ResourceLocation event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPPING.containsKey(event))
                    {
                        vecacc.vectorLib$fireEvent(player, EVENT_MAPPING.get(event), pos, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("ResourceLocation %s has no correlation to a VectorEvent", event));
                    }
                }
                else throw new IllegalArgumentException(WTF);
            }
            catch (IllegalArgumentException exc)
            {
                VectorLib.LOGGER.error("Couldn't fire VectorEvent, see below", exc);
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
        public static final List<VecDualEventAlias> EVENT_LIST = new ArrayList<>();
        private static final Map<ResourceLocation, Integer> EVENT_MAPPING = new HashMap<>();

        public static ResourceLocation register(ResourceLocation mapper, VecDualEventAlias event)
        {
            EVENT_LIST.add(event);
            if (!EVENT_MAPPING.containsKey(mapper))
            {
                EVENT_MAPPING.put(mapper, EVENT_LIST.indexOf(event));
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync dual-pos list already contains event that maps to %s", mapper.toString()));

            return mapper;
        }

        public static void fireEvent(LevelAccessor level, Vec3 pos1, Vec3 pos2, ResourceLocation event, int data)
        {
            VectorEventSync.Dual.fireEvent(null, level, pos1, pos2, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, Vec3 pos1, Vec3 pos2, ResourceLocation event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPPING.containsKey(event))
                    {
                        vecacc.vectorLib$fireDual(player, EVENT_MAPPING.get(event), pos1, pos2, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("ResourceLocation %s has no correlation to a dual-pos VectorEvent", event));
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
        public static final List<VecEntityEventAlias> EVENT_LIST = new ArrayList<>();
        private static final Map<ResourceLocation, Integer> EVENT_MAPPING = new HashMap<>();

        public static ResourceLocation register(ResourceLocation mapper, VecEntityEventAlias event)
        {
            EVENT_LIST.add(event);
            if (!EVENT_MAPPING.containsKey(mapper))
            {
                EVENT_MAPPING.put(mapper, EVENT_LIST.indexOf(event));
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync entity list already contains event that maps to %s", mapper.toString()));

            return mapper;
        }

        public static void fireEvent(LevelAccessor level, net.minecraft.world.entity.Entity entity, ResourceLocation event, int data)
        {
            VectorEventSync.Entity.fireEvent(null, level, entity, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, net.minecraft.world.entity.Entity entity, ResourceLocation event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPPING.containsKey(event))
                    {
                        vecacc.vectorLib$fireEntity(player, EVENT_MAPPING.get(event), entity, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("ResourceLocation %s has no correlation to an entity VectorEvent", event));
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
        public static final List<VecGlobalEventAlias> EVENT_LIST = new ArrayList<>();
        private static final Map<ResourceLocation, Integer> EVENT_MAPPING = new HashMap<>();

        public static ResourceLocation register(ResourceLocation mapper, VecGlobalEventAlias event)
        {
            EVENT_LIST.add(event);
            if (!EVENT_MAPPING.containsKey(mapper))
            {
                EVENT_MAPPING.put(mapper, EVENT_LIST.indexOf(event));
            }
            else throw new IllegalArgumentException(String.format("VectorEventSync global list already contains event that maps to %s", mapper.toString()));

            return mapper;
        }

        public static void fireEvent(LevelAccessor level, BlockPos pos, ResourceLocation event, int data)
        {
            VectorEventSync.Global.fireEvent(null, level, pos, event, data);
        }

        public static void fireEvent(@Nullable Player player, LevelAccessor level, BlockPos pos, ResourceLocation event, int data)
        {
            try
            {
                if (level instanceof VectorLevelAccess vecacc)
                {
                    if (EVENT_MAPPING.containsKey(event))
                    {
                        vecacc.vectorLib$fireGlobal(player, EVENT_MAPPING.get(event), pos, data);
                    }
                    else
                    {
                        throw new IllegalArgumentException(String.format("ResourceLocation %s has no correlation to a global VectorEvent", event));
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
}
