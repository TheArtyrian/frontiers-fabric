package net.artyrian.frontiers.mixin.rendering;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.mob.CrawlerEntity;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.warden.Warden;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin
{
    @Shadow private @Nullable PostChain postEffect;
    @Shadow protected abstract void loadEffect(ResourceLocation id);

    @Inject(method = "checkEntityPostEffect", at = @At("TAIL"), cancellable = true)
    private void frontiersReplaceWithEntityShader(Entity entity, CallbackInfo ci)
    {
        if (this.postEffect == null)
        {
            if (entity instanceof CrawlerEntity)
            {
                this.loadEffect(Frontiers.id("shaders/post/crawler.json"));
                ci.cancel();
            }
            else if (entity instanceof Warden)
            {
                this.loadEffect(Frontiers.id("shaders/post/warden.json"));
                ci.cancel();
            }
        }
    }
}
