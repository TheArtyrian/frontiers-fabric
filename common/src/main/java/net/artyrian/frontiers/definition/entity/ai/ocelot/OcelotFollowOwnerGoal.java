package net.artyrian.frontiers.definition.entity.ai.ocelot;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.level.pathfinder.PathType;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class OcelotFollowOwnerGoal extends Goal
{
    private final Ocelot ocelot;
    private final OcelotMixIntf ocelot_interfaced;

    @Nullable
    private LivingEntity owner;
    private final double speed;
    private final PathNavigation navigation;
    private int updateCountdownTicks;
    private final float maxDistance;
    private final float minDistance;
    private float oldWaterPathfindingPenalty;

    public OcelotFollowOwnerGoal(Ocelot ocelot, double speed, float minDistance, float maxDistance)
    {
        this.ocelot = ocelot;
        this.ocelot_interfaced = (OcelotMixIntf)(this.ocelot);

        this.speed = speed;
        this.navigation = ocelot.getNavigation();
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse()
    {
        LivingEntity livingEntity = this.ocelot_interfaced.frontiers$getOwner();
        if (livingEntity == null)
        {
            return false;
        }
        else if (this.ocelot_interfaced.frontiers$cannotFollowOwner())
        {
            return false;
        }
        else if (this.ocelot.distanceToSqr(livingEntity) < (double)(this.minDistance * this.minDistance))
        {
            return false;
        }
        else
        {
            this.owner = livingEntity;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse()
    {
        if (this.navigation.isDone())
        {
            return false;
        }
        else
        {
            return this.ocelot_interfaced.frontiers$cannotFollowOwner() ? false : !(this.ocelot.distanceToSqr(this.owner) <= (double)(this.maxDistance * this.maxDistance));
        }
    }

    @Override
    public void start()
    {
        this.updateCountdownTicks = 0;
        this.oldWaterPathfindingPenalty = this.ocelot.getPathfindingMalus(PathType.WATER);
        this.ocelot.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop()
    {
        this.owner = null;
        this.navigation.stop();
        this.ocelot.setPathfindingMalus(PathType.WATER, this.oldWaterPathfindingPenalty);
    }

    @Override
    public void tick()
    {
        boolean cantry = this.ocelot_interfaced.frontiers$shouldTryTeleportToOwner();
        if (!cantry)
        {
            this.ocelot.getLookControl().setLookAt(this.owner, 10.0F, (float)this.ocelot.getMaxHeadXRot());
        }

        if (--this.updateCountdownTicks <= 0)
        {
            this.updateCountdownTicks = this.adjustedTickDelay(10);
            if (cantry)
            {
                this.ocelot_interfaced.frontiers$tryTeleportToOwner();
            }
            else
            {
                this.navigation.moveTo(this.owner, this.speed);
            }
        }
    }
}