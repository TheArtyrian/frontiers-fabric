package net.artyrian.frontiers.data.components;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.EnchantingMagnetBlockEntity;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.WrittenBookContentComponent;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.UnaryOperator;

public class ModDataComponents
{
    public static final ComponentType<BottleContentComponent> BOTTLE_CONTENT = register(
            "bottle_content", builder -> builder.codec(BottleContentComponent.CODEC).packetCodec(BottleContentComponent.PACKET_CODEC).cache()
    );
    public static final ComponentType<Integer> EXP_AMOUNT = register(
            "exp_amount", builder -> builder.codec(Codecs.rangedInt(0, EnchantingMagnetBlockEntity.MAX_EXP)).packetCodec(PacketCodecs.VAR_INT)
    );

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator)
    {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Frontiers.MOD_ID, id), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerComps()
    {

    }
}
