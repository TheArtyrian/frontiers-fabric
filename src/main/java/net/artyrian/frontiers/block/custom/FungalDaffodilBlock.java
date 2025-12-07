package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.MushroomBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;

public class FungalDaffodilBlock extends MushroomBlock
{
    public FungalDaffodilBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity)
    {
        if (entity.bypassesLandingEffects()) super.onEntityLand(world, entity);
        else this.bounce(entity);
    }

    private void bounce(Entity entity)
    {
        Vec3d vec3d = entity.getVelocity();
        if (vec3d.y < 0.0)
        {
            double d = ((entity instanceof LivingEntity) ? 0.5 : 0.4);
            entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
        }
    }
}
