package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SanityTickSyncPayload(int integer) implements CustomPayload
{
    public static final CustomPayload.Id<SanityTickSyncPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.SANITY_TICK_SYNC_PACKET);
    public static final PacketCodec<RegistryByteBuf, SanityTickSyncPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, SanityTickSyncPayload::integer, SanityTickSyncPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
