package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SanitySyncPayload(int integer) implements CustomPayload
{
    public static final CustomPayload.Id<SanitySyncPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.SANITY_SYNC_PACKET);
    public static final PacketCodec<RegistryByteBuf, SanitySyncPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, SanitySyncPayload::integer, SanitySyncPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
