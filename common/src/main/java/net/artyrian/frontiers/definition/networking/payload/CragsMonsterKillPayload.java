package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record CragsMonsterKillPayload(boolean bool) implements CustomPacketPayload
{
    public static final Type<CragsMonsterKillPayload> ID = new Type<>(ModNetworkConstants.CRAGS_MONSTER_KILL_PACKET);
    public static final StreamCodec<RegistryFriendlyByteBuf, CragsMonsterKillPayload> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, CragsMonsterKillPayload::bool, CragsMonsterKillPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}