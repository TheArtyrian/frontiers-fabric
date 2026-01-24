package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record ChickenPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<ChickenPayload> ID = new Type<>(ModNetworkConstants.CHICKEN);
    public static final StreamCodec<RegistryFriendlyByteBuf, ChickenPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ChickenPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            ChickenPayload::nbt,
            ChickenPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
