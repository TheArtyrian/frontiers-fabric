package net.artyrian.frontiers.mixin.entity;

import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.minecraft.world.entity.monster.Monster;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Monster.class)
public abstract class HostileEntityMixin extends MobEntityMixin
{
}
