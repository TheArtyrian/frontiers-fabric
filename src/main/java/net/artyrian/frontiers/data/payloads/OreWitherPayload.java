package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.block.Block;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record OreWitherPayload(BlockPos pos) implements CustomPayload
{
    public static final CustomPayload.Id<OreWitherPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.ORE_WITHER_PACKET);
    public static final PacketCodec<RegistryByteBuf, OreWitherPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, OreWitherPayload::pos, OreWitherPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
