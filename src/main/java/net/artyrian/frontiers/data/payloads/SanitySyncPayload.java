package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record SanitySyncPayload(UUID player_id, int sanity, int sanitytick) implements CustomPayload
{
    public static final CustomPayload.Id<SanitySyncPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.SANITY_SYNC_PACKET);
    public static final PacketCodec<RegistryByteBuf, SanitySyncPayload> CODEC =
            PacketCodec.tuple(
                    Uuids.PACKET_CODEC, SanitySyncPayload::player_id,
                    PacketCodecs.INTEGER, SanitySyncPayload::sanity,
                    PacketCodecs.INTEGER, SanitySyncPayload::sanitytick,
                    SanitySyncPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
