package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChestRenderer.class)
public abstract class ChestBlockRendererMixin<T extends BlockEntity & LidBlockEntity>
{
    @Unique
    private static final Material FRONTIERS_PERSONAL_DISABLED =
            new Material(Sheets.CHEST_SHEET, ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "entity/chest/personal_disabled"));

    @ModifyVariable(
            method = "render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V",
            at = @At(
                    value = "STORE",
                    target = "Lnet/minecraft/client/util/SpriteIdentifier;getVertexConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/util/function/Function;)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer frontiersModifyArgForLayer(
            VertexConsumer og,
            @Local Material spr,
            @Local(argsOnly = true) T entity,
            @Local(argsOnly = true) MultiBufferSource vx
    )
    {
        if (entity instanceof PersonalChestBlockEntity chest)
        {
            if (chest.getCooldown() > 0) return FRONTIERS_PERSONAL_DISABLED.buffer(vx, RenderType::entityTranslucentCull);
        }
        return og;
    }
}
