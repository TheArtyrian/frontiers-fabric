package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.artyrian.frontiers.data.components.BottleContentComponent;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record BottleMessageWritePayload(int slot, String text) implements CustomPayload
{
    public static final CustomPayload.Id<BottleMessageWritePayload> ID = new CustomPayload.Id<>(ModNetworkConstants.MESSAGE_BOTTLE);
    public static final PacketCodec<RegistryByteBuf, BottleMessageWritePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER,
            BottleMessageWritePayload::slot,
            PacketCodecs.string(BottleContentComponent.MAX_TEXT),
            BottleMessageWritePayload::text,
            BottleMessageWritePayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
