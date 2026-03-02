package net.vertisoft.vectorlib.agnostic.networking.eventsync;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/** Allows definition of client-side "level events" completely seperate of Vanilla.
 *<p>
 * To gain a basic understanding of a level event in Minecraft, here's a few examples:
 * <p>- Monster Spawner spawning effects (flames & smoke)
 * <p>- Bone Meal growth particles
 * <p>- Copper wax on/off
 *<p>
 * In short, level events sum up to performing common effects on clients, and can be called from either client or server.
 * This class allows for defining custom ones.
 */
public class VectorEventSync
{
    public static final List<VecEventAlias> EVENT_LIST = new ArrayList<>();

    public static VectorEvent registerEvent(VecEventAlias event)
    {
        EVENT_LIST.add(event);
        VectorLib.LOGGER.info(String.valueOf(EVENT_LIST.indexOf(event)));
        return new VectorEvent(EVENT_LIST.indexOf(event));
    }

    public static void fireEvent(LevelAccessor level, BlockPos pos, VectorEvent event, int data)
    {
        VectorEventSync.fireEvent(null, level, pos, event, data);
    }

    public static void fireEvent(@Nullable Player player, LevelAccessor level, BlockPos pos, VectorEvent event, int data)
    {
        try
        {
            if (level instanceof VectorLevelAccess vecacc)
            {
                vecacc.vectorLib$fireEvent(player, event.index(), pos, data);
            }
            else throw new IllegalArgumentException("The provided LevelAccessor doesn't extend VectorLevelAccess?????");
        }
        catch (IllegalArgumentException exc)
        {
            VectorLib.LOGGER.error("Couldn't fire VectorEvent, see below", exc);
        }
    }

    public record VectorEvent(int index) {}

    @FunctionalInterface
    public interface VecEventAlias
    {
        void execute(Level level, BlockPos pos, int data);
    }
}
