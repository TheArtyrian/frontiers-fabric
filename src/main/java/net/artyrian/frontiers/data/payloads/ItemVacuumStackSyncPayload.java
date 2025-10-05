package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record ItemVacuumStackSyncPayload(BlockPos pos, ItemStack stack) implements CustomPayload
{
    public static final CustomPayload.Id<ItemVacuumStackSyncPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.ITEM_VACUUM_SYNC);
    public static final PacketCodec<RegistryByteBuf, ItemVacuumStackSyncPayload> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC,
            ItemVacuumStackSyncPayload::pos,
            ItemStack.PACKET_CODEC,
            ItemVacuumStackSyncPayload::stack,
            ItemVacuumStackSyncPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
