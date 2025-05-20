package net.artyrian.frontiers.entity.ai.creeper;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.OcelotEntity;

public class CreeperNewRevengeGoal extends RevengeGoal
{
    public CreeperNewRevengeGoal(PathAwareEntity mob)
    {
        super(mob);
    }

    @Override
    public boolean canStart()
    {
        LivingEntity livingEntity = this.mob.getAttacker();
        if (livingEntity instanceof OcelotEntity ocelot)
        {
            return false;
        }
        return super.canStart();
    }
}
