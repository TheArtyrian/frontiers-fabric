package net.artyrian.frontiers.mixin.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityRenderer.class)
public abstract class EntityRenderMixin<T extends Entity>
{
    @Shadow @Final
    protected EntityRenderDispatcher dispatcher;

    @Shadow protected abstract int getBlockLight(T entity, BlockPos blockPos);
}
