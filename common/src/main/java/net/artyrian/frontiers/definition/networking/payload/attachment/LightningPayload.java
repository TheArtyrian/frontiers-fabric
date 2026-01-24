package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record LightningPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<LightningPayload> ID = new Type<>(ModNetworkConstants.LIGHTNING);
    public static final StreamCodec<RegistryFriendlyByteBuf, LightningPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            LightningPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            LightningPayload::nbt,
            LightningPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
