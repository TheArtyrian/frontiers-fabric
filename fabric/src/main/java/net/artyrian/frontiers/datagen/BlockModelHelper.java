package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import java.util.Optional;

public class BlockModelHelper
{
    public static final TextureSlot ANVIL_BODY = TextureSlot.create("body");
    public static final ModelTemplate TEMPLATE_VIVULITE_ANVIL = blockVanilla("template_anvil", TextureSlot.TOP, ANVIL_BODY, TextureSlot.PARTICLE);
    public static final TexturedModel.Provider TEMPLATE_VIV_ANVIL_FACTORY = TexturedModel.createDefault(BlockModelHelper::anvilBody, TEMPLATE_VIVULITE_ANVIL);

    /**
     Returns a block model from Vanilla.
     */
    private static ModelTemplate blockVanilla(String parent, TextureSlot... requiredTextureKeys)
    {
        return new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    /**
     Returns a block model from Frontiers.
     */
    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys)
    {
        return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    /**
     Creates a new texture map for the Anvil.
     */
    public static TextureMapping anvilBody(Block block) {
        return new TextureMapping()
                .put(ANVIL_BODY, TextureMapping.getBlockTexture(block))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block));
    }

    /**
     Creates a block with a carpet
     */
    public static void registerCarpet(Block carpet, BlockModelGenerators modelGenerator)
    {
        ResourceLocation identifier = TexturedModel.CARPET.get(carpet).create(carpet, modelGenerator.modelOutput);
        modelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(carpet, identifier));
    }

    /**
     Registers a block using the Vivulite Anvil model.
     */
    public static void registerVivuliteAnvil(Block anvil, BlockModelGenerators modelGenerator) {
        ResourceLocation identifier = BlockModelHelper.TEMPLATE_VIV_ANVIL_FACTORY.create(anvil, modelGenerator.modelOutput);
        modelGenerator.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(anvil, identifier).with(
                        BlockModelGenerators.createHorizontalFacingDispatchAlt()));
    }

    /**
     Registers a block using the Vanilla cake model.
     */
    public static void registerCakeBlock(Block cake, Item cake_item, BlockModelGenerators generator)
    {
        generator.createSimpleFlatItemModel(cake_item);

        TextureMapping cakemap = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(cake, "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(cake, "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(cake, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(cake, "_side"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(cake, "_side"));

        generator.blockStateOutput
                .accept(
                        MultiVariantGenerator.multiVariant(cake)
                                .with(
                                        PropertyDispatch.property(BlockStateProperties.BITES)
                                                .select(0, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake)))
                                                .select(1, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake, "_slice1")))
                                                .select(2, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake, "_slice2")))
                                                .select(3, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake, "_slice3")))
                                                .select(4, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake, "_slice4")))
                                                .select(5, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake, "_slice5")))
                                                .select(6, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(cake, "_slice6")))
                                )
                );
    }

    /**
     Registers a slab without a parent block.
     */
    public static void registerSoloSlab(Block slab, BlockModelGenerators generator)
    {
        TextureMapping map = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(slab, "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(slab))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(slab))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(slab, "_side"));

        ResourceLocation slabbottom = ModelTemplates.SLAB_BOTTOM.create(slab, map, generator.modelOutput);
        ResourceLocation slabtop = ModelTemplates.SLAB_TOP.createWithSuffix(slab, "_top", map, generator.modelOutput);
        ResourceLocation slabfull = ModelTemplates.CUBE_TOP.createWithSuffix(slab, "_full", map, generator.modelOutput);

        generator.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, slabbottom, slabtop, slabfull));
        generator.delegateItemModel(slab, slabbottom);
    }

    /**
     Registers a block using the Vanilla iron bars model.
     */
    public static void registerIronBarLike(Block bars, BlockModelGenerators generator)
    {
        ResourceLocation identifier = ModelLocationUtils.getModelLocation(bars, "_post_ends");
        ResourceLocation identifier2 = ModelLocationUtils.getModelLocation(bars, "_post");
        ResourceLocation identifier3 = ModelLocationUtils.getModelLocation(bars, "_cap");
        ResourceLocation identifier4 = ModelLocationUtils.getModelLocation(bars, "_cap_alt");
        ResourceLocation identifier5 = ModelLocationUtils.getModelLocation(bars, "_side");
        ResourceLocation identifier6 = ModelLocationUtils.getModelLocation(bars, "_side_alt");
        generator.blockStateOutput
                .accept(
                        MultiPartGenerator.multiPart(bars)
                                .with(Variant.variant().with(VariantProperties.MODEL, identifier))
                                .with(
                                        Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false),
                                        Variant.variant().with(VariantProperties.MODEL, identifier2)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.NORTH, true).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false),
                                        Variant.variant().with(VariantProperties.MODEL, identifier3)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, true).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, false),
                                        Variant.variant().with(VariantProperties.MODEL, identifier3).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, true).term(BlockStateProperties.WEST, false),
                                        Variant.variant().with(VariantProperties.MODEL, identifier4)
                                )
                                .with(
                                        Condition.condition().term(BlockStateProperties.NORTH, false).term(BlockStateProperties.EAST, false).term(BlockStateProperties.SOUTH, false).term(BlockStateProperties.WEST, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier4).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .with(Condition.condition().term(BlockStateProperties.NORTH, true), Variant.variant().with(VariantProperties.MODEL, identifier5))
                                .with(
                                        Condition.condition().term(BlockStateProperties.EAST, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier5).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                                .with(Condition.condition().term(BlockStateProperties.SOUTH, true), Variant.variant().with(VariantProperties.MODEL, identifier6))
                                .with(
                                        Condition.condition().term(BlockStateProperties.WEST, true),
                                        Variant.variant().with(VariantProperties.MODEL, identifier6).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
                                )
                );
        generator.createSimpleFlatItemModel(bars);
    }
}
