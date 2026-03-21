package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record ManaSyncPayload(UUID player_id, int points, int level, int to_next) implements CustomPacketPayload
{
    public static final Type<ManaSyncPayload> ID = new Type<>(ModNetworkConstants.MANA_SYNC_PACKET);
    public static final StreamCodec<RegistryFriendlyByteBuf, ManaSyncPayload> CODEC =
            StreamCodec.composite(
                    UUIDUtil.STREAM_CODEC, ManaSyncPayload::player_id,
                    ByteBufCodecs.INT, ManaSyncPayload::points,
                    ByteBufCodecs.INT, ManaSyncPayload::level,
                    ByteBufCodecs.INT, ManaSyncPayload::to_next,
                    ManaSyncPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
