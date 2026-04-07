package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BuffSyncPayload(boolean hp_apple) implements CustomPacketPayload
{
    public static final Type<BuffSyncPayload> ID = new Type<>(FRNetworking.SYNC_PLAYER_BUFFS);
    public static final StreamCodec<RegistryFriendlyByteBuf, BuffSyncPayload> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, BuffSyncPayload::hp_apple, BuffSyncPayload::new);

    @Override public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}