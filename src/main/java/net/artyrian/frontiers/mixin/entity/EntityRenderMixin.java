package net.artyrian.frontiers.mixin.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityRenderer.class)
public abstract class EntityRenderMixin
{
    @Shadow @Final
    protected EntityRenderDispatcher dispatcher;
}
