package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record OreWitherPayload(BlockPos pos) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<OreWitherPayload> ID = new CustomPacketPayload.Type<>(ModNetworkConstants.ORE_WITHER_PACKET);
    public static final StreamCodec<RegistryFriendlyByteBuf, OreWitherPayload> CODEC = StreamCodec.composite(BlockPos.STREAM_CODEC, OreWitherPayload::pos, OreWitherPayload::new);

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
