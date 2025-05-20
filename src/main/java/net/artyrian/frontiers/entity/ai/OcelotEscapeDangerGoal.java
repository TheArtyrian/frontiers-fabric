package net.artyrian.frontiers.entity.ai;

import net.artyrian.frontiers.mixin_interfaces.OcelotMixIntf;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.registry.tag.TagKey;

public class OcelotEscapeDangerGoal extends EscapeDangerGoal
{
    private final OcelotEntity ocelot;

    public OcelotEscapeDangerGoal(OcelotEntity entity, final double speed, final TagKey<DamageType> dangerousDamageTypes)
    {
        super(entity, speed, dangerousDamageTypes);
        this.ocelot = entity;
    }

    public OcelotEscapeDangerGoal(OcelotEntity entity, final double speed)
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
