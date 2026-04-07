package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record PlayerAvariceTotemPayload(boolean bool) implements CustomPacketPayload
{
    public static final Type<PlayerAvariceTotemPayload> ID = new Type<>(FRNetworking.PLAYER_AVARICE_PACKET);
    public static final StreamCodec<RegistryFriendlyByteBuf, PlayerAvariceTotemPayload> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PlayerAvariceTotemPayload::bool, PlayerAvariceTotemPayload::new);

    @Override public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
