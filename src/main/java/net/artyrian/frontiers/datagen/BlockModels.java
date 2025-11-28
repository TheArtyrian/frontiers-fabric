package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class BlockModels
{
    public static final TextureKey ANVIL_BODY = TextureKey.of("body");
    public static final Model TEMPLATE_VIVULITE_ANVIL = blockVanilla("template_anvil", TextureKey.TOP, ANVIL_BODY, TextureKey.PARTICLE);
    public static final TexturedModel.Factory TEMPLATE_VIV_ANVIL_FACTORY = TexturedModel.makeFactory(BlockModels::anvilBody, TEMPLATE_VIVULITE_ANVIL);

    /**
     Returns a block model from Vanilla.
     */
    private static Model blockVanilla(String parent, TextureKey... requiredTextureKeys)
    {
        return new Model(Optional.of(Identifier.ofVanilla("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    /**
     Returns a block model from Frontiers.
     */
    private static Model block(String parent, TextureKey... requiredTextureKeys)
    {
        return new Model(Optional.of(Identifier.of(Frontiers.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    /**
     Creates a new texture map for the Anvil.
     */
    public static TextureMap anvilBody(Block block) {
        return new TextureMap()
                .put(ANVIL_BODY, TextureMap.getId(block))
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.PARTICLE, TextureMap.getId(block));
    }

    /**
     Creates a block with a carpet
     */
    public static void registerCarpet(Block carpet, BlockStateModelGenerator modelGenerator)
    {
        Identifier identifier = TexturedModel.CARPET.get(carpet).upload(carpet, modelGenerator.modelCollector);
        modelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(carpet, identifier));
    }

    /**
     Registers a block using the Vivulite Anvil model.
     */
    public static void registerVivuliteAnvil(Block anvil, BlockStateModelGenerator modelGenerator) {
        Identifier identifier = BlockModels.TEMPLATE_VIV_ANVIL_FACTORY.upload(anvil, modelGenerator.modelCollector);
        modelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(anvil, identifier).coordinate(
                        BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates()));
    }

    /**
     Registers a block using the Vanilla cake model.
     */
    public static void registerCakeBlock(Block cake, Item cake_item, BlockStateModelGenerator generator)
    {
        generator.registerItemModel(cake_item);

        TextureMap cakemap = new TextureMap()
                .put(TextureKey.PARTICLE, TextureMap.getSubId(cake, "_side"))
                .put(TextureKey.BOTTOM, TextureMap.getSubId(cake, "_bottom"))
                .put(TextureKey.TOP, TextureMap.getSubId(cake, "_top"))
                .put(TextureKey.SIDE, TextureMap.getSubId(cake, "_side"))
                .put(TextureKey.SIDE, TextureMap.getSubId(cake, "_side"));

        generator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(cake)
                                .coordinate(
                                        BlockStateVariantMap.create(Properties.BITES)
                                                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(cake)))
                                                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_slice1")))
                                                .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_slice2")))
                                                .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_slice3")))
                                                .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_slice4")))
                                                .register(5, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_slice5")))
                                                .register(6, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(cake, "_slice6")))
                                )
                );
    }
}
