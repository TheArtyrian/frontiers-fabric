package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record OcelotPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<OcelotPayload> ID = new Type<>(ModNetworkConstants.OCELOT);
    public static final StreamCodec<RegistryFriendlyByteBuf, OcelotPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            OcelotPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            OcelotPayload::nbt,
            OcelotPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
