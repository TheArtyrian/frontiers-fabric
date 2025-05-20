package net.artyrian.frontiers.entity.ai;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.OcelotMixIntf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.OcelotEntity;

import java.util.EnumSet;

public class OcelotSitGoal extends Goal
{
    private final OcelotEntity ocelot;
    private final OcelotMixIntf ocelot_interfaced;

    public OcelotSitGoal(OcelotEntity ocelot)
    {
        this.ocelot = ocelot;
        this.ocelot_interfaced = (OcelotMixIntf)(this.ocelot);
        this.setControls(EnumSet.of(Goal.Control.JUMP, Goal.Control.MOVE));
    }

    @Override
    public boolean shouldContinue()
    {
        //Frontiers.LOGGER.warn("not sitting!");
        return this.ocelot_interfaced.frontiers$isSitting();
    }

    @Override
    public boolean canStart()
    {
        if (!this.ocelot_interfaced.frontiers$isTamed())
        {
            return false;
        }
        else if (this.ocelot.isInsideWaterOrBubbleColumn())
        {
            return false;
        }
        else if (!this.ocelot.isOnGround())
        {
            return false;
        }
        else
        {
            LivingEntity livingEntity = this.ocelot_interfaced.frontiers$getOwner();
            if (livingEntity == null)
            {
                return true;
            }
            else
            {
                //Frontiers.LOGGER.warn("attempted sitgoal");
                return this.ocelot.squaredDistanceTo(livingEntity) < 144.0 && livingEntity.getAttacker() != null ? false : this.ocelot_interfaced.frontiers$isSitting();
            }
        }
    }

    @Override
    public void start()
    {
        //Frontiers.LOGGER.info("ticking!");
        this.ocelot.getNavigation().stop();
        this.ocelot_interfaced.frontiers$setInSittingPose(true);
    }

    @Override
    public void stop()
    {
        //Frontiers.LOGGER.info("stopped!");
        this.ocelot_interfaced.frontiers$setInSittingPose(false);
    }
}
