package net.artyrian.frontiers.definition.entity.ai.ocelot;

import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Ocelot;

public class OcelotEscapeDangerGoal extends PanicGoal
{
    private final Ocelot ocelot;

    public OcelotEscapeDangerGoal(Ocelot entity, final double speed, final TagKey<DamageType> dangerousDamageTypes)
    {
        super(entity, speed, dangerousDamageTypes);
        this.ocelot = entity;
    }

    public OcelotEscapeDangerGoal(Ocelot entity, final double speed)
    {
        super(entity, speed);
        this.ocelot = entity;
    }

    @Override
    public void tick()
    {
        if (!((OcelotMixIntf)this.ocelot).frontiers$cannotFollowOwner() && ((OcelotMixIntf)this.ocelot).frontiers$shouldTryTeleportToOwner())
        {
            ((OcelotMixIntf)this.ocelot).frontiers$tryTeleportToOwner();
        }

        super.tick();
    }
}
