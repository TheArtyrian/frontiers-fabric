package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record EndCrystalPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<EndCrystalPayload> ID = new Type<>(ModNetworkConstants.END_CRYSTAL);
    public static final StreamCodec<RegistryFriendlyByteBuf, EndCrystalPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            EndCrystalPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            EndCrystalPayload::nbt,
            EndCrystalPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
