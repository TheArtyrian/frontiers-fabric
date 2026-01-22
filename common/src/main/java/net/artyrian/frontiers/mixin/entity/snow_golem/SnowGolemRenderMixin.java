package net.artyrian.frontiers.mixin.entity.snow_golem;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.SnowGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowGolemRenderer.class)
public abstract class SnowGolemRenderMixin
{
    @Unique private static final ResourceLocation ROMEO_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID,"textures/entity/snow_golem_romeo.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/passive/SnowGolemEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private ResourceLocation xrid(ResourceLocation original, @Local(argsOnly = true) SnowGolem snowy)
    {
        String name = ChatFormatting.stripFormatting(snowy.getName().getString());
        boolean isromeo = (name.equals("Romeo") || name.equals("Admin"));
        if (isromeo)
        {
            return ROMEO_TEXTURE;
        }
        return original;
    }
}
