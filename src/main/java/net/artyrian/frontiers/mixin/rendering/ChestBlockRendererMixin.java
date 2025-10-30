package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.Function;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.PersonalChestBlock;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LidOpenable;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChestBlockEntityRenderer.class)
public abstract class ChestBlockRendererMixin<T extends BlockEntity & LidOpenable>
{
    @Unique
    private static final SpriteIdentifier FRONTIERS_PERSONAL_DISABLED =
            new SpriteIdentifier(TexturedRenderLayers.CHEST_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/chest/personal_disabled"));

    @ModifyVariable(
            method = "render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(
                    value = "STORE",
                    target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer frontiersModifyArgForLayer(
            VertexConsumer og,
            @Local SpriteIdentifier spr,
            @Local(argsOnly = true) T entity,
            @Local(argsOnly = true) VertexConsumerProvider vx
    )
    {
        if (entity instanceof PersonalChestBlockEntity chest)
        {
            if (chest.getCooldown() > 0) return FRONTIERS_PERSONAL_DISABLED.getVertexConsumer(vx, RenderLayer::getEntityTranslucentCull);
        }
        return og;
    }
}
