package net.artyrian.frontiers.definition.networking.payload;

import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;

public record ChanceFoodItemPayload(ItemStack stack) implements CustomPacketPayload
{
    public static final Type<ChanceFoodItemPayload> ID = new Type<>(ModNetworkConstants.CHANCE_FOOD_ITEM);
    public static final StreamCodec<RegistryFriendlyByteBuf, ChanceFoodItemPayload> CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, ChanceFoodItemPayload::stack,
            ChanceFoodItemPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return ID;
    }
}
