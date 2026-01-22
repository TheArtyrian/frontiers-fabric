package net.vertisoft.vectorlib.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;

import java.util.List;

public interface VectorNetworkIntf
{
    default void sendToAllInChunk(ServerLevel level, BlockPos pos, CustomPacketPayload payload)
    {
        for (ServerPlayer player : getAllTrackingChunk(level, pos,false))
        {
            sendToPlayer(player, payload);
        }
    }

    default List<ServerPlayer> getAllTrackingChunk(ServerLevel level, BlockPos pos, boolean bounds)
    {
        return level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), bounds);
    }

    void sendToServer(CustomPacketPayload payload);
    void sendToPlayer(ServerPlayer player, CustomPacketPayload payload);
}
