package net.artyrian.frontiers.definition.entity.types.projectile;

import net.artyrian.frontiers.definition.entity.types.misc.ManaOrbEntity;
import net.artyrian.frontiers.reg.content.FREntity;
import net.artyrian.frontiers.reg.content.FRItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.phys.HitResult;

public class ManaBottleEntity extends ThrowableItemProjectile
{
    public ManaBottleEntity(EntityType<? extends ManaBottleEntity> entityType, Level world) { super(entityType, world); }

    public ManaBottleEntity(Level world, LivingEntity owner)
    {
        super(FREntity.MANA_BOTTLE.get(), owner, world);
    }

    public ManaBottleEntity(Level world, double x, double y, double z) { super(FREntity.MANA_BOTTLE.get(), x, y, z, world); }

    @Override
    protected Item getDefaultItem() {
        return FRItems.MANA_BOTTLE.get();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.07;
    }

    @Override
    protected void onHit(HitResult hitResult)
    {
        super.onHit(hitResult);
        if (this.level() instanceof ServerLevel)
        {
            this.level().levelEvent(LevelEvent.PARTICLES_SPELL_POTION_SPLASH, this.blockPosition(), PotionContents.getColor(Potions.LUCK));
            int i = 3 + this.level().random.nextInt(5) + this.level().random.nextInt(5);
            ManaOrbEntity.award((ServerLevel)this.level(), this.position(), i);
            this.discard();
        }
    }
}