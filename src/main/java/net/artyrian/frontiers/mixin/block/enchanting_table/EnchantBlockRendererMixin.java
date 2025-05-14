package net.artyrian.frontiers.mixin.block.enchanting_table;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.EnchantRenderMixInterface;
import net.artyrian.frontiers.mixin_interfaces.EnchantTableMixInterface;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(EnchantingTableBlockEntityRenderer.class)
public abstract class EnchantBlockRendererMixin implements EnchantRenderMixInterface
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
            Function<Identifier, RenderLayer> layerFactory, Operation<VertexConsumer> original)
    {
        return FRONTIERS_END_BOOK_TEXTURE.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
    }

    @Inject(
            method = "render(Lnet/minecraft/block/entity/EnchantingTableBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At("TAIL")
    )
    private void doCrystalBeamRenderpass(
            EnchantingTableBlockEntity enchantingTableBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci)
    {
        //matrixStack.push();
        //matrixStack.translate(0.5F, 0.0F, 0.5F);

        //matrixStack.pop();
    }
}
