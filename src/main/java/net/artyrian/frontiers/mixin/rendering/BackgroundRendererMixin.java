package net.artyrian.frontiers.mixin.rendering;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Debug(export = true)
@Mixin(BackgroundRenderer.class)
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
                                        @Local CameraSubmersionType cameraSubmersionType)
    {
        if (cameraSubmersionType == CameraSubmersionType.LAVA)
        {
            if (
                    entity instanceof LivingEntity &&
                    !entity.isSpectator() &&
                    ((LivingEntity)entity).hasStatusEffect(ModStatusEffects.MAGMA_VISION)
            )
            {
                return -2.0F;
            }
        }
        else
        {
            if (
                    entity instanceof LivingEntity &&
                    ((LivingEntity)entity).hasStatusEffect(ModStatusEffects.MAGMA_VISION)
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
                                      @Local CameraSubmersionType cameraSubmersionType)
    {
        if (cameraSubmersionType == CameraSubmersionType.LAVA)
        {
            if (
                    entity instanceof LivingEntity &&
                    !entity.isSpectator() &&
                    ((LivingEntity)entity).hasStatusEffect(ModStatusEffects.MAGMA_VISION)
            )
            {
                return viewDistance * 0.1F;
            }
        }
        else
        {
            if (
                    entity instanceof LivingEntity &&
                    ((LivingEntity)entity).hasStatusEffect(ModStatusEffects.MAGMA_VISION)
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
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        if (
                cameraSubmersionType != CameraSubmersionType.LAVA &&
                entity instanceof LivingEntity &&
                ((LivingEntity)entity).hasStatusEffect(ModStatusEffects.MAGMA_VISION)
        )
        {
            red = 0.1F;
            green = 0.0F;
            blue = 0.02F;
            original.call(0.05F, 0.0F, 0.01F, alpha);
        }
        else original.call(red_x, green_x, blue_x, alpha);
    }
}
