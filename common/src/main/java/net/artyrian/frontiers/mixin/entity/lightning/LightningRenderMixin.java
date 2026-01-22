package net.artyrian.frontiers.mixin.entity.lightning;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_intf.LightningMixInterface;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Debug(export = true)
@Mixin(LightningBoltRenderer.class)
public abstract class LightningRenderMixin
{
    @ModifyArgs(
            method = "render(Lnet/minecraft/entity/LightningEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LightningEntityRenderer;drawBranch(Lorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumer;FFIFFFFFFFZZZZ)V")
    )
    private void branchColorChanger(Args args, @Local(argsOnly = true) LightningBolt lightningEntity)
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
