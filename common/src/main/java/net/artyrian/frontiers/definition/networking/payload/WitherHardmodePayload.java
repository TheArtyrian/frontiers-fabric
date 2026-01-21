package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record WitherHardmodePayload(boolean bool) implements CustomPacketPayload
{
    public static final Type<WitherHardmodePayload> ID = new Type<>(ModNetworkConstants.WITHER_HARDMODE);
    public static final StreamCodec<RegistryFriendlyByteBuf, WitherHardmodePayload> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, WitherHardmodePayload::bool, WitherHardmodePayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
