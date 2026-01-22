package net.artyrian.frontiers.mixin;

import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Mob.class)
public abstract class MobEntityMixin extends LivingEntityMixin implements Leashable
{
    @Shadow @Final protected GoalSelector goalSelector;
    @Shadow protected PathNavigation navigation;
    @Shadow @Final protected GoalSelector targetSelector;

    @Shadow public abstract void setPersistenceRequired();

    @Shadow public abstract boolean isNoAi();
}
