package net.artyrian.frontiers.definition.entity.ai.creeper;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Ocelot;

public class CreeperNewRevengeGoal extends HurtByTargetGoal
{
    public CreeperNewRevengeGoal(PathfinderMob mob)
    {
        super(mob);
    }

    @Override
    public boolean canUse()
    {
        LivingEntity livingEntity = this.mob.getLastHurtByMob();
        if (livingEntity instanceof Ocelot ocelot)
        {
            return false;
        }
        return super.canUse();
    }
}
