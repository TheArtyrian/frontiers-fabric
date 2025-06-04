package net.artyrian.frontiers.data.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.RawFilteredPair;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public record BottleContentComponent(RawFilteredPair<String> text)
{
    public static final BottleContentComponent DEFAULT = new BottleContentComponent(RawFilteredPair.of(""));
    public static final int MAX_TEXT = 32;
    public static final Codec<BottleContentComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            RawFilteredPair.createCodec(Codec.string(0, MAX_TEXT)).fieldOf("text").forGetter(BottleContentComponent::text)
                    )
                    .apply(instance, BottleContentComponent::new)
    );
    public static final PacketCodec<RegistryByteBuf, BottleContentComponent> PACKET_CODEC = PacketCodec.tuple(
            RawFilteredPair.createPacketCodec(PacketCodecs.string(MAX_TEXT)),
            BottleContentComponent::text,
            BottleContentComponent::new
    );

    public String getText()
    {
        return this.text.get(MinecraftClient.getInstance().shouldFilterText());
    }
}
