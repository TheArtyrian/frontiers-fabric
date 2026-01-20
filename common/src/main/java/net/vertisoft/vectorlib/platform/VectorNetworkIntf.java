package net.vertisoft.vectorlib.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;

public interface VectorNetworkIntf
{
    default void sendToAllInChunk(ServerLevel level, BlockPos pos, CustomPacketPayload payload)
    {
        for (ServerPlayer player : level.getChunkSource().chunkMap.getPlayers(new ChunkPos(pos), false))
        {
            sendToPlayer(player, payload);
        }
    }

    void sendToServer(CustomPacketPayload payload);
    void sendToPlayer(ServerPlayer player, CustomPacketPayload payload);
}
