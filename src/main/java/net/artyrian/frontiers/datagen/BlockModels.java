package net.artyrian.frontiers.datagen;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
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
     Registers a block using the Vivulite Anvil model.
     */
    public static void registerVivuliteAnvil(Block anvil, BlockStateModelGenerator modelGenerator) {
        Identifier identifier = BlockModels.TEMPLATE_VIV_ANVIL_FACTORY.upload(anvil, modelGenerator.modelCollector);
        modelGenerator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(anvil, identifier).coordinate(
                        BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates()));
    }
}
