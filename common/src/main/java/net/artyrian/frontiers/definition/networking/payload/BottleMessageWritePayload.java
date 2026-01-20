package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BottleMessageWritePayload(int slot, String text) implements CustomPacketPayload
{
    public static final Type<BottleMessageWritePayload> ID = new Type<>(ModNetworkConstants.MESSAGE_BOTTLE);
    public static final StreamCodec<RegistryFriendlyByteBuf, BottleMessageWritePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            BottleMessageWritePayload::slot,
            ByteBufCodecs.stringUtf8(BottleContentComponent.MAX_TEXT),
            BottleMessageWritePayload::text,
            BottleMessageWritePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
