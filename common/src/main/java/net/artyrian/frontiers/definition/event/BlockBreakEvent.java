package net.artyrian.frontiers.definition.event;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.definition.networking.payload.OreWitherPayload;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.VectorLib;
import org.jetbrains.annotations.Nullable;

public class BlockBreakEvent
{
    public static boolean oreWitherAway(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity entity)
    {
        if (state.is(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
        {
            MinecraftServer server = world.getServer();
            StateSaveLoad loader = StateSaveLoad.getServerState(server);
            boolean hard = loader.isInHardmode;

            // TODO: Replace with VectorEvent equivalent
            if (!hard)
            {
                VectorLib.NETWORK.sendToAllInChunk((ServerLevel) world, pos, new OreWitherPayload(pos));
            }
        }
        return true;
    }
}
