package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ItemVacuumEmptyPayload(BlockPos pos) implements CustomPacketPayload
{
    public static final Type<ItemVacuumEmptyPayload> ID = new Type<>(ModNetworkConstants.ITEM_VACUUM_EMPTY);
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemVacuumEmptyPayload> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ItemVacuumEmptyPayload::pos,
            ItemVacuumEmptyPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
