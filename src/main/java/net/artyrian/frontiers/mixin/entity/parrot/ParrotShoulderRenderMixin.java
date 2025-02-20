package net.artyrian.frontiers.mixin.entity.parrot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.ParrotRenderMixInterface;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.ParrotEntityRenderer;
import net.minecraft.client.render.entity.feature.ShoulderParrotFeatureRenderer;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;

@Debug(export = true)
@Mixin(ShoulderParrotFeatureRenderer.class)
public abstract class ParrotShoulderRenderMixin
{
    @Shadow
    @Final
    private ParrotEntityModel model;

    @ModifyVariable(method = "method_17958", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer mixingIntoSynthesisWowHelp(
            VertexConsumer vertexConsumer,
            @Local NbtCompound nbtCompound,
            @Local ParrotEntity.Variant variant,
            @Local(argsOnly = true) VertexConsumerProvider vertexConsumers
    )
    {
        String name = nbtCompound.getString("CustomName");

        // I HATE HOW THIS WORKS JESUS AAAAAAAAAA
        if ("\"Kazooie\"".equals(name)) return vertexConsumers.getBuffer(this.model.getLayer(ParrotRenderMixInterface.KAZOOIE_TEXTURE));
        else if ("\"Lovebirb\"".equals(name)) return vertexConsumers.getBuffer(this.model.getLayer(ParrotRenderMixInterface.LOVEBIRB_TEXTURE));

        return vertexConsumer;
    }
}
