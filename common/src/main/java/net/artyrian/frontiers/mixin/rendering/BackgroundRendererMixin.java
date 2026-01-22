package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Debug(export = true)
@Mixin(FogRenderer.class)
public class BackgroundRendererMixin
{
    @Shadow private static float red;
    @Shadow private static float green;
    @Shadow private static float blue;

    @ModifyArg(method = "applyFog", at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogStart(F)V"))
    private static float customFogStart(float original,
                                        @Local(argsOnly = true, ordinal = 0) float viewDistance,
                                        @Local Entity entity,
                                        @Local FogType cameraSubmersionType)
    {
        if (cameraSubmersionType == FogType.LAVA)
        {
            if (
                    entity instanceof LivingEntity &&
                    !entity.isSpectator() &&
                    ((LivingEntity)entity).hasEffect(ModStatusEffects.MAGMA_VISION)
            )
            {
                return -2.0F;
            }
        }
        else
        {
            if (
                    entity instanceof LivingEntity &&
                    ((LivingEntity)entity).hasEffect(ModStatusEffects.MAGMA_VISION)
            )
            {
                return 0.0F;
            }
        }
        return original;
    }

    @ModifyArg(method = "applyFog", at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderFogEnd(F)V"))
    private static float customFogEnd(float original,
                                      @Local(argsOnly = true, ordinal = 0) float viewDistance,
                                      @Local Entity entity,
                                      @Local FogType cameraSubmersionType)
    {
        if (cameraSubmersionType == FogType.LAVA)
        {
            if (
                    entity instanceof LivingEntity &&
                    !entity.isSpectator() &&
                    ((LivingEntity)entity).hasEffect(ModStatusEffects.MAGMA_VISION)
            )
            {
                return viewDistance * 0.1F;
            }
        }
        else
        {
            if (
                    entity instanceof LivingEntity &&
                    ((LivingEntity)entity).hasEffect(ModStatusEffects.MAGMA_VISION)
            )
            {
                return 2.0F;
            }
        }
        return original;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V", ordinal = 1))
    private static void customColorHandler(float red_x, float green_x, float blue_x, float alpha, Operation<Void> original,
                                           @Local(argsOnly = true) Camera camera)
    {
        FogType cameraSubmersionType = camera.getFluidInCamera();
        Entity entity = camera.getEntity();
        if (
                cameraSubmersionType != FogType.LAVA &&
                entity instanceof LivingEntity &&
                ((LivingEntity)entity).hasEffect(ModStatusEffects.MAGMA_VISION)
        )
        {
            red = 0.05F;
            green = 0.0F;
            blue = 0.01F;
            original.call(0.05F, 0.0F, 0.01F, alpha);
        }
        else original.call(red_x, green_x, blue_x, alpha);
    }
}
