package net.artyrian.frontiers.mixin;

import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.minecraft.entity.Leashable;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntityMixin implements Leashable
{
    @Shadow public abstract void setPersistent();

    @Shadow public abstract boolean isAiDisabled();
}
