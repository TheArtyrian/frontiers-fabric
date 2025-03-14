package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record PlayerAvariceTotemPayload(boolean bool) implements CustomPayload
{
    public static final CustomPayload.Id<PlayerAvariceTotemPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.PLAYER_AVARICE_PACKET);
    public static final PacketCodec<RegistryByteBuf, PlayerAvariceTotemPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, PlayerAvariceTotemPayload::bool, PlayerAvariceTotemPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
