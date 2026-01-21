package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record CragsStalkerDespawnPayload(double x, double y, double z) implements CustomPacketPayload
{
    public static final Type<CragsStalkerDespawnPayload> ID = new Type<>(ModNetworkConstants.CRAGS_STALKER_DESPAWN_PACKET);
    public static final StreamCodec<RegistryFriendlyByteBuf, CragsStalkerDespawnPayload> CODEC = StreamCodec.composite(
                ByteBufCodecs.DOUBLE, CragsStalkerDespawnPayload::x,
                ByteBufCodecs.DOUBLE, CragsStalkerDespawnPayload::y,
                ByteBufCodecs.DOUBLE, CragsStalkerDespawnPayload::z,
                CragsStalkerDespawnPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}