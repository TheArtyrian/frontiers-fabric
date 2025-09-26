package net.artyrian.frontiers.data.payloads;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record ChanceFoodItemPayload(ItemStack stack) implements CustomPayload
{
    public static final CustomPayload.Id<ChanceFoodItemPayload> ID = new CustomPayload.Id<>(ModNetworkConstants.CHANCE_FOOD_ITEM);
    public static final PacketCodec<RegistryByteBuf, ChanceFoodItemPayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, ChanceFoodItemPayload::stack,
            ChanceFoodItemPayload::new);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId()
    {
        return ID;
    }
}
