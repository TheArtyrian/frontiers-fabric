package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record HoglinPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<HoglinPayload> ID = new Type<>(ModNetworkConstants.HOGLIN);
    public static final StreamCodec<RegistryFriendlyByteBuf, HoglinPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            HoglinPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            HoglinPayload::nbt,
            HoglinPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
