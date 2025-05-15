package net.artyrian.frontiers.mixin.block.enchanting_table;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.EnchantTableMixInterface;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(EnchantingTableBlockEntityRenderer.class)
public abstract class EnchantBlockRendererMixin
{
    @Shadow @Final public static SpriteIdentifier BOOK_TEXTURE;

    @Unique
    private static final SpriteIdentifier FRONTIERS_END_BOOK_TEXTURE = new SpriteIdentifier(
            SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/end_book")
    );

    @WrapOperation(
            method = "render(Lnet/minecraft/block/entity/EnchantingTableBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;")
    )
    private VertexConsumer endBookConsumeFrontiers(
            SpriteIdentifier instance,
            VertexConsumerProvider vertexConsumers,
            Function<Identifier, RenderLayer> layerFactory, Operation<VertexConsumer> original,
            @Local(argsOnly = true) EnchantingTableBlockEntity enchantingTableBlockEntity)
    {
        if (((EnchantTableMixInterface)enchantingTableBlockEntity).frontiers$getCrystalCount() >= 4)
        {
            return FRONTIERS_END_BOOK_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        }
        else
        {
            return original.call(instance, vertexConsumers, layerFactory);
        }
    }
}
