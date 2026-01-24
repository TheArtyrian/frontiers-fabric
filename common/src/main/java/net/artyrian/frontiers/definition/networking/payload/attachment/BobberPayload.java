package net.artyrian.frontiers.definition.networking.payload.attachment;

import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.definition.networking.payload.BottleMessageWritePayload;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record BobberPayload(int id, CompoundTag nbt) implements CustomPacketPayload
{
    public static final Type<BobberPayload> ID = new Type<>(ModNetworkConstants.BOBBER);
    public static final StreamCodec<RegistryFriendlyByteBuf, BobberPayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            BobberPayload::id,
            ByteBufCodecs.fromCodec(CompoundTag.CODEC),
            BobberPayload::nbt,
            BobberPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
