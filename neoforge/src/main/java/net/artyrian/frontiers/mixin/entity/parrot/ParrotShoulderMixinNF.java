package net.artyrian.frontiers.mixin.entity.parrot;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.mixin_intf.ParrotRenderMixInterface;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ParrotOnShoulderLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ParrotOnShoulderLayer.class, priority = 500)
public class ParrotShoulderMixinNF<T extends Player>
{
    @Shadow @Final private ParrotModel model;

    // ALSO HAS IMPL IN A FABRIC CLASS!
    @ModifyVariable(method = "lambda$render$1", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer mixingIntoSynthesisWowHelp(
            VertexConsumer vertexConsumer,
            @Local CompoundTag nbtCompound,
            @Local Parrot.Variant variant,
            @Local(argsOnly = true) MultiBufferSource vertexConsumers
    )
    {
        String name = nbtCompound.getString("CustomName");

        // I HATE HOW THIS WORKS JESUS AAAAAAAAAA
        if ("\"Kazooie\"".equals(name)) return vertexConsumers.getBuffer(this.model.renderType(ParrotRenderMixInterface.KAZOOIE_TEXTURE));
        else if ("\"Lovebirb\"".equals(name)) return vertexConsumers.getBuffer(this.model.renderType(ParrotRenderMixInterface.LOVEBIRB_TEXTURE));
        else if ("\"Keynis\"".equals(name)) return vertexConsumers.getBuffer(this.model.renderType(ParrotRenderMixInterface.KEYNIS_TEXTURE));

        return vertexConsumer;
    }
}
