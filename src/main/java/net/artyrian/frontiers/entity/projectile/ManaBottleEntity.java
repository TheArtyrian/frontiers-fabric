package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.misc.ManaOrbEntity;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.Potions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class ManaBottleEntity extends ThrownItemEntity
{
    public ManaBottleEntity(EntityType<? extends ManaBottleEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public ManaBottleEntity(World world, LivingEntity owner)
    {
        super(ModEntity.MANA_BOTTLE, owner, world);
    }

    public ManaBottleEntity(World world, double x, double y, double z)
    {
        super(ModEntity.MANA_BOTTLE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItem.MANA_BOTTLE;
    }

    @Override
    protected double getGravity() {
        return 0.07;
    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
        super.onCollision(hitResult);
        if (this.getWorld() instanceof ServerWorld)
        {
            this.getWorld().syncWorldEvent(WorldEvents.SPLASH_POTION_SPLASHED, this.getBlockPos(), PotionContentsComponent.getColor(Potions.LUCK));
            int i = 3 + this.getWorld().random.nextInt(5) + this.getWorld().random.nextInt(5);
            ManaOrbEntity.spawn((ServerWorld)this.getWorld(), this.getPos(), i);
            this.discard();
        }
    }
}