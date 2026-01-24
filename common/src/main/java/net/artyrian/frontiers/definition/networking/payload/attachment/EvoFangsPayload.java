package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record EvoFangsPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<EvoFangsPayload> ID = new Type<>(ModNetworkConstants.EVO_FANGS);
    public static final StreamCodec<RegistryFriendlyByteBuf, EvoFangsPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            EvoFangsPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            EvoFangsPayload::nbt,
            EvoFangsPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
