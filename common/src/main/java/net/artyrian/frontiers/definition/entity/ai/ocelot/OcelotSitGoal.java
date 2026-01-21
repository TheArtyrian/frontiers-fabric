package net.artyrian.frontiers.definition.entity.ai.ocelot;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Ocelot;
import java.util.EnumSet;

public class OcelotSitGoal extends Goal
{
    private final Ocelot ocelot;
    private final OcelotMixIntf ocelot_interfaced;

    public OcelotSitGoal(Ocelot ocelot)
    {
        this.ocelot = ocelot;
        this.ocelot_interfaced = (OcelotMixIntf)(this.ocelot);
        this.setFlags(EnumSet.of(Flag.JUMP, Flag.MOVE));
    }

    @Override
    public boolean canContinueToUse()
    {
        //Frontiers.LOGGER.warn("not sitting!");
        return this.ocelot_interfaced.frontiers$isSitting();
    }

    @Override
    public boolean canUse()
    {
        if (!this.ocelot_interfaced.frontiers$isTamed())
        {
            return false;
        }
        else if (this.ocelot.isInWaterOrBubble())
        {
            return false;
        }
        else if (!this.ocelot.onGround())
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
                return this.ocelot.distanceToSqr(livingEntity) < 144.0 && livingEntity.getLastHurtByMob() != null ? false : this.ocelot_interfaced.frontiers$isSitting();
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
