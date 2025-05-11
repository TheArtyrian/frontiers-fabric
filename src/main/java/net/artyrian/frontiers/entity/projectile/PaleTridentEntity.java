package net.artyrian.frontiers.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PaleTridentEntity extends TridentEntity
{
    public PaleTridentEntity(EntityType<? extends TridentEntity> entityType, World world)
    {
        super(entityType, world);
    }
}
