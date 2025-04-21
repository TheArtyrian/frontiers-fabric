package net.artyrian.frontiers.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileMixin extends EntityMixin
{
    @Shadow @Nullable public abstract Entity getOwner();
}
