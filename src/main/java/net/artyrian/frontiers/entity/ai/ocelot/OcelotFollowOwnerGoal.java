package net.artyrian.frontiers.entity.ai.ocelot;

import net.artyrian.frontiers.mixin_interfaces.OcelotMixIntf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.passive.OcelotEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class OcelotFollowOwnerGoal extends Goal
{
    private final OcelotEntity ocelot;
    private final OcelotMixIntf ocelot_interfaced;

    @Nullable
    private LivingEntity owner;
    private final double speed;
    private final EntityNavigation navigation;
    private int updateCountdownTicks;
    private final float maxDistance;
    private final float minDistance;
    private float oldWaterPathfindingPenalty;

    public OcelotFollowOwnerGoal(OcelotEntity ocelot, double speed, float minDistance, float maxDistance)
    {
        this.ocelot = ocelot;
        this.ocelot_interfaced = (OcelotMixIntf)(this.ocelot);

        this.speed = speed;
        this.navigation = ocelot.getNavigation();
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart()
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
        else if (this.ocelot.squaredDistanceTo(livingEntity) < (double)(this.minDistance * this.minDistance))
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
    public boolean shouldContinue()
    {
        if (this.navigation.isIdle())
        {
            return false;
        }
        else
        {
            return this.ocelot_interfaced.frontiers$cannotFollowOwner() ? false : !(this.ocelot.squaredDistanceTo(this.owner) <= (double)(this.maxDistance * this.maxDistance));
        }
    }

    @Override
    public void start()
    {
        this.updateCountdownTicks = 0;
        this.oldWaterPathfindingPenalty = this.ocelot.getPathfindingPenalty(PathNodeType.WATER);
        this.ocelot.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    @Override
    public void stop()
    {
        this.owner = null;
        this.navigation.stop();
        this.ocelot.setPathfindingPenalty(PathNodeType.WATER, this.oldWaterPathfindingPenalty);
    }

    @Override
    public void tick()
    {
        boolean cantry = this.ocelot_interfaced.frontiers$shouldTryTeleportToOwner();
        if (!cantry)
        {
            this.ocelot.getLookControl().lookAt(this.owner, 10.0F, (float)this.ocelot.getMaxLookPitchChange());
        }

        if (--this.updateCountdownTicks <= 0)
        {
            this.updateCountdownTicks = this.getTickCount(10);
            if (cantry)
            {
                this.ocelot_interfaced.frontiers$tryTeleportToOwner();
            }
            else
            {
                this.navigation.startMovingTo(this.owner, this.speed);
            }
        }
    }
}