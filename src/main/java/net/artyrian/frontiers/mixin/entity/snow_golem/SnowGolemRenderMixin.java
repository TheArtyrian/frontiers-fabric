package net.artyrian.frontiers.mixin.entity.snow_golem;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.render.entity.SnowGolemEntityRenderer;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowGolemEntityRenderer.class)
public abstract class SnowGolemRenderMixin
{
    @Unique private static final Identifier ROMEO_TEXTURE = Identifier.of(Frontiers.MOD_ID,"textures/entity/snow_golem_romeo.png");

    @ModifyReturnValue(method = "getTexture(Lnet/minecraft/entity/passive/SnowGolemEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"))
    private Identifier xrid(Identifier original, @Local(argsOnly = true) SnowGolemEntity snowy)
    {
        String name = Formatting.strip(snowy.getName().getString());
        boolean isromeo = (name.equals("Romeo") || name.equals("Admin"));
        if (isromeo)
        {
            return ROMEO_TEXTURE;
        }
        return original;
    }
}
