package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record ItemVacuumEmptyPayload(BlockPos pos) implements CustomPayload
{
    public static final CustomPayload.Id<ItemVacuumEmptyPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.ITEM_VACUUM_EMPTY);
    public static final PacketCodec<RegistryByteBuf, ItemVacuumEmptyPayload> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC,
            ItemVacuumEmptyPayload::pos,
            ItemVacuumEmptyPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
