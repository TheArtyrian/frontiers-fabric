package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import java.util.UUID;

public record SanitySyncPayload(UUID player_id, int sanity, int sanitytick) implements CustomPacketPayload
{
    public static final Type<SanitySyncPayload> ID = new Type<>(ModNetworkConstants.SANITY_SYNC_PACKET);
    public static final StreamCodec<RegistryFriendlyByteBuf, SanitySyncPayload> CODEC =
            StreamCodec.composite(
                    UUIDUtil.STREAM_CODEC, SanitySyncPayload::player_id,
                    ByteBufCodecs.INT, SanitySyncPayload::sanity,
                    ByteBufCodecs.INT, SanitySyncPayload::sanitytick,
                    SanitySyncPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
