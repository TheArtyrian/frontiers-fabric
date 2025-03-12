package net.artyrian.frontiers.mixin.entity.lightning;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.FrontiersConfig;
import net.artyrian.frontiers.mixin_interfaces.LightningMixInterface;
import net.minecraft.client.render.entity.LightningEntityRenderer;
import net.minecraft.entity.LightningEntity;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Debug(export = true)
@Mixin(LightningEntityRenderer.class)
public abstract class LightningRenderMixin
{
    @ModifyArgs(
            method = "render(Lnet/minecraft/entity/LightningEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LightningEntityRenderer;drawBranch(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;FFIFFFFFFFZZZZ)V")
    )
    private void branchColorChanger(Args args, @Local(argsOnly = true) LightningEntity lightningEntity)
    {
        boolean has_channeler = (
                ((LightningMixInterface)lightningEntity).frontiers_1_21x$isChanneled() && Frontiers.CONFIG.doChanneledLightningRecolor()
        );

        // Default RGB: 0.45, 0.45, 0.5
        if (has_channeler)
        {
            args.set(7, 0.28F);
            args.set(8, 0.48F);
            args.set(9, 0.92F);
        }
    }
}
