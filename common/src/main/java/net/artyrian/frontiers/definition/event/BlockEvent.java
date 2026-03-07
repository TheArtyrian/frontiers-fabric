package net.artyrian.frontiers.definition.event;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.Nullable;

public class BlockEvent
{
    public static boolean oreWitherAway(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity)
    {
        if (state.is(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
        {
            MinecraftServer server = world.getServer();
            StateSaveLoad loader = StateSaveLoad.getServerState(server);
            boolean hard = loader.isInHardmode;

            if (!hard) VectorEventSync.Local.fireEvent(world, pos, FRLevelEvents.Local.ORE_WITHER, 0);
        }
        return true;
    }
}
