package net.artyrian.frontiers.definition.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.network.Filterable;

public record BottleContentComponent(Filterable<String> text)
{
    public static final BottleContentComponent DEFAULT = new BottleContentComponent(Filterable.passThrough(""));
    public static final int MAX_TEXT = 32;
    public static final Codec<BottleContentComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Filterable.codec(Codec.string(0, MAX_TEXT)).fieldOf("text").forGetter(BottleContentComponent::text)
                    )
                    .apply(instance, BottleContentComponent::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, BottleContentComponent> PACKET_CODEC = StreamCodec.composite(
            Filterable.streamCodec(ByteBufCodecs.stringUtf8(MAX_TEXT)),
            BottleContentComponent::text,
            BottleContentComponent::new
    );

    public String getText()
    {
        return this.text.get(Minecraft.getInstance().isTextFilteringEnabled());
    }
}
