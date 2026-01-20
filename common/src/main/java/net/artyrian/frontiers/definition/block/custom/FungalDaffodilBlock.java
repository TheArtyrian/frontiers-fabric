package net.artyrian.frontiers.definition.block.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.phys.Vec3;

public class FungalDaffodilBlock extends HugeMushroomBlock
{
    public FungalDaffodilBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter world, Entity entity)
    {
        if (entity.isSuppressingBounce()) super.updateEntityAfterFallOn(world, entity);
        else this.bounce(entity);
    }

    private void bounce(Entity entity)
    {
        Vec3 vec3d = entity.getDeltaMovement();
        if (vec3d.y < 0.0)
        {
            double d = ((entity instanceof LivingEntity) ? 0.5 : 0.4);
            entity.setDeltaMovement(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }
}
