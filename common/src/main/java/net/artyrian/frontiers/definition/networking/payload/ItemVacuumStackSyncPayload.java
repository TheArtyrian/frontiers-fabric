package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;

public record ItemVacuumStackSyncPayload(BlockPos pos, ItemStack stack) implements CustomPacketPayload
{
    public static final Type<ItemVacuumStackSyncPayload> ID = new Type<>(ModNetworkConstants.ITEM_VACUUM_SYNC);
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemVacuumStackSyncPayload> CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            ItemVacuumStackSyncPayload::pos,
            ItemStack.STREAM_CODEC,
            ItemVacuumStackSyncPayload::stack,
            ItemVacuumStackSyncPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
