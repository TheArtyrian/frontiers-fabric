package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.misc.ModBlockProperties;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModelHelper
{
    /** Registers a lumen-type model for the given block. */
    public static void registerLumen(Block type, BlockStateModelGenerator generator)
    {
        Identifier identifier = TexturedModel.CUBE_ALL.upload(type, generator.modelCollector);
        Identifier identifier2 = generator.createSubModel(type, "_power_1", Models.CUBE_ALL, TextureMap::all);
        Identifier identifier3 = generator.createSubModel(type, "_power_2", Models.CUBE_ALL, TextureMap::all);
        generator.blockStateCollector
                .accept(VariantsBlockStateSupplier.create(type)
                        .coordinate(BlockStateVariantMap.create(ModBlockProperties.LUMEN_POWER)
                                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, identifier3))
                        )
                );
    }
}
