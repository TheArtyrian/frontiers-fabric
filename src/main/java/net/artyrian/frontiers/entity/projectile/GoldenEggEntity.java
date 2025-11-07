package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.passive.GoldenChickenEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class GoldenEggEntity extends ThrownItemEntity
{
    private static final EntityDimensions EMPTY_DIMENSIONS = EntityDimensions.fixed(0.0F, 0.0F);

    public GoldenEggEntity(EntityType<? extends GoldenEggEntity> entityType, World world) {
        super(entityType, world);
    }
    public GoldenEggEntity(World world, LivingEntity owner) { super(ModEntity.GOLDEN_EGG, owner, world); }
    public GoldenEggEntity(World world, double x, double y, double z) {
        super(ModEntity.GOLDEN_EGG, x, y, z, world);
    }

    @Override
    public void handleStatus(byte status)
    {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES)
        {
            double times = 0.08;
            for (int i = 0; i < 8; i++)
            {
                this.getWorld()
                        .addParticle(
                                new ItemStackParticleEffect(ParticleTypes.ITEM, this.getStack()),
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
    protected void onEntityHit(EntityHitResult entityHitResult)
    {
        super.onEntityHit(entityHitResult);
        entityHitResult.getEntity().damage(this.getDamageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient)
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
                    GoldenChickenEntity chicky = ModEntity.GOLDEN_CHICKEN.create(this.getWorld());
                    if (chicky != null)
                    {
                        chicky.setBreedingAge(-24000);
                        chicky.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                        if (!chicky.recalculateDimensions(EMPTY_DIMENSIONS))
                        {
                            break;
                        }

                        this.getWorld().spawnEntity(chicky);
                    }
                }
            }

            Box box = new Box(this.getBlockPos().up()).expand(3, 1, 3);
            List<PlayerEntity> list = this.getWorld().getNonSpectatingEntities(PlayerEntity.class, box);

            for (PlayerEntity playerEntity : list)
            {
                playerEntity.addStatusEffect(new StatusEffectInstance(
                        ModStatusEffects.ALLUREMENT,
                        1200,
                        0,
                        false,
                        true)
                );
            }

            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.playSound(ModSounds.EGG_CRACK,0.8F, 1.0F);
            this.discard();
        }
    }

    @Override
    protected Item getDefaultItem() {
        return ModItem.GOLDEN_EGG;
    }
}