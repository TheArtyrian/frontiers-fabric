package net.artyrian.frontiers.mixin.entity.projectile;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.TridentEntityRenderer;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// UNUSED!
@Mixin(TridentEntityRenderer.class)
public abstract class TridentEntityRenderMixin
{
    @Unique
    private static final Identifier TEXTURE_PALE_FRONTIERS = Identifier.of(Frontiers.MOD_ID,"textures/entity/projectiles/pale_trident.png");

    //@Inject(method = "render(Lnet/minecraft/entity/projectile/TridentEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"))
    //private void renderChecker(TridentEntity tridentEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci)
    //{
    //    Frontiers.LOGGER.info(tridentEntity.getItemStack().toString());
    //}

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/projectile/TridentEntity;)Lnet/minecraft/util/Identifier;" , at = @At("RETURN"))
    private Identifier textureChangeForCustomFrontiers(Identifier original, @Local(argsOnly = true) TridentEntity entity)
    {
        return original;
        //if (entity.getItemStack().isOf(ModItem.PALE_TRIDENT))
        //{
        //    return TEXTURE_PALE_FRONTIERS;
        //}
        //else
        //{
        //    if (entity.getItemStack().getItem() != null) Frontiers.LOGGER.warn(entity.getItemStack().getItem().toString());
        //    return original;
        //}
    }
}
