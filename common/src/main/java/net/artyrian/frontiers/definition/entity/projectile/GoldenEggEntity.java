package net.artyrian.frontiers.definition.entity.projectile;

import net.artyrian.frontiers.definition.entity.passive.GoldenChickenEntity;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import java.util.List;

public class GoldenEggEntity extends ThrowableItemProjectile
{
    private static final EntityDimensions EMPTY_DIMENSIONS = EntityDimensions.fixed(0.0F, 0.0F);

    public GoldenEggEntity(EntityType<? extends GoldenEggEntity> entityType, Level world) {
        super(entityType, world);
    }
    public GoldenEggEntity(Level world, LivingEntity owner) { super(ModEntity.GOLDEN_EGG.get(), owner, world); }
    public GoldenEggEntity(Level world, double x, double y, double z) { super(ModEntity.GOLDEN_EGG.get(), x, y, z, world); }

    @Override
    public void handleEntityEvent(byte status)
    {
        if (status == EntityEvent.DEATH)
        {
            double times = 0.08;
            for (int i = 0; i < 8; i++)
            {
                this.level()
                        .addParticle(
                                new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times,
                                ((double)this.random.nextFloat() - 0.5) * times
                        );
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult hitResult)
    {
        super.onHit(hitResult);
        if (!this.level().isClientSide)
        {
            if (this.random.nextInt(8) == 0)
            {
                int i = 1;
                if (this.random.nextInt(32) == 0)
                {
                    i = 4;
                }

                for (int j = 0; j < i; j++)
                {
                    GoldenChickenEntity chicky = ModEntity.GOLDEN_CHICKEN.get().create(this.level());
                    if (chicky != null)
                    {
                        chicky.setAge(-24000);
                        chicky.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                        if (!chicky.fudgePositionAfterSizeChange(EMPTY_DIMENSIONS))
                        {
                            break;
                        }

                        this.level().addFreshEntity(chicky);
                    }
                }
            }

            AABB box = new AABB(this.blockPosition().above()).inflate(3, 1, 3);
            List<Player> list = this.level().getEntitiesOfClass(Player.class, box);

            for (Player playerEntity : list)
            {
                playerEntity.addEffect(new MobEffectInstance(
                        ModStatusEffects.ALLUREMENT,
                        1200,
                        0,
                        false,
                        true)
                );
            }

            this.level().broadcastEntityEvent(this, EntityEvent.DEATH);
            this.playSound(ModSounds.EGG_CRACK.get(),0.8F, 1.0F);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItem.GOLDEN_EGG.get();
    }
}