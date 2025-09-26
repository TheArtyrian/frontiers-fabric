package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.mob.CrawlerEntity;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
    @Shadow private @Nullable PostEffectProcessor postProcessor;

    @Shadow protected abstract void loadPostProcessor(Identifier id);

    @Inject(method = "onCameraEntitySet", at = @At("TAIL"), cancellable = true)
    private void frontiersReplaceWithEntityShader(Entity entity, CallbackInfo ci)
    {
        if (this.postProcessor == null)
        {
            if (entity instanceof CrawlerEntity)
            {
                this.loadPostProcessor(Identifier.of(Frontiers.MOD_ID, "shaders/post/crawler.json"));
                ci.cancel();
            }
            else if (entity instanceof WardenEntity)
            {
                this.loadPostProcessor(Identifier.of(Frontiers.MOD_ID, "shaders/post/warden.json"));
                ci.cancel();
            }
        }
    }
}
