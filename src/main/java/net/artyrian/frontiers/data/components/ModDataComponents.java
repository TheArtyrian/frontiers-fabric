package net.artyrian.frontiers.data.components;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.WrittenBookContentComponent;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents
{
    public static final ComponentType<BottleContentComponent> BOTTLE_CONTENT = register(
            "bottle_content", builder -> builder.codec(BottleContentComponent.CODEC).packetCodec(BottleContentComponent.PACKET_CODEC).cache()
    );

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator)
    {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Frontiers.MOD_ID, id), ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerComps()
    {

    }
}
