package net.artyrian.frontiers.mixin.entity;

import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HostileEntity.class)
public abstract class HostileEntityMixin extends MobEntityMixin
{
}
