package net.artyrian.frontiers.mixin.block.enchanting_table;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_intf.EnchantTableMixInterface;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.EnchantTableRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Function;

@Mixin(EnchantTableRenderer.class)
public abstract class EnchantBlockRendererMixin
{
    @Shadow @Final public static Material BOOK_TEXTURE;

    @Unique
    private static final Material FRONTIERS_END_BOOK_TEXTURE = new Material(
            TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "entity/end_book")
    );

    @WrapOperation(
            method = "render(Lnet/minecraft/block/entity/EnchantingTableBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;")
    )
    private VertexConsumer endBookConsumeFrontiers(
            Material instance,
            MultiBufferSource vertexConsumers,
            Function<ResourceLocation, RenderType> layerFactory, Operation<VertexConsumer> original,
            @Local(argsOnly = true) EnchantingTableBlockEntity enchantingTableBlockEntity)
    {
        if (((EnchantTableMixInterface)enchantingTableBlockEntity).frontiers$getCrystalCount() >= 4)
        {
            return FRONTIERS_END_BOOK_TEXTURE.buffer(vertexConsumers, RenderType::entitySolid);
        }
        else
        {
            return original.call(instance, vertexConsumers, layerFactory);
        }
    }
}
