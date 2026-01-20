package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.EnchantingMagnetBlockEntity;
import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.vertisoft.vectorlib.VectorLib;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ModDataComponents
{
    public static final Supplier<DataComponentType<BottleContentComponent>> BOTTLE_CONTENT = register(
            "bottle_content", builder -> builder.persistent(BottleContentComponent.CODEC).networkSynchronized(BottleContentComponent.PACKET_CODEC).cacheEncoding()
    );
    public static final Supplier<DataComponentType<Integer>> EXP_AMOUNT = register(
            "exp_amount", builder -> builder.persistent(ExtraCodecs.intRange(0, EnchantingMagnetBlockEntity.MAX_EXP)).networkSynchronized(ByteBufCodecs.VAR_INT)
    );

    private static <T> Supplier<DataComponentType<T>> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator)
    {
        return VectorLib.REGISTRY.registerComponentType(Frontiers.MOD_ID, id, builderOperator);
    }

    public static void registerComps()
    {

    }
}
