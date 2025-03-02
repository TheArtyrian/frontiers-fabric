package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record WitherHardmodePayload(boolean bool) implements CustomPayload
{
    public static final CustomPayload.Id<WitherHardmodePayload> ID = new CustomPayload.Id<>(ModNetworkConstants.WITHER_HARDMODE);
    public static final PacketCodec<RegistryByteBuf, WitherHardmodePayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, WitherHardmodePayload::bool, WitherHardmodePayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
