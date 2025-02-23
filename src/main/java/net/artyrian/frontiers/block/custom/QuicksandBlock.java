package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.misc.ModDamageType;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Unique;

public class QuicksandBlock extends Block
{
    public static final MapCodec<QuicksandBlock> CODEC = createCodec(QuicksandBlock::new);
    @Unique
    private BlockStateParticleEffect SAND_PARTICLES = new BlockStateParticleEffect(ParticleTypes.BLOCK, getDefaultState());

    @Override
    public MapCodec<QuicksandBlock> getCodec() {
        return CODEC;
    }

    public QuicksandBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (isValidEntity(entity))
        {
            Vec3d vec3d = new Vec3d(0.25, 0.05F, 0.25);

            entity.slowMovement(state, vec3d);

            // Do stuff for server side.
            if (!world.isClient)
            {
                ((ServerWorld)world).spawnParticles(
                    SAND_PARTICLES,
                        entity.getX(),
                        pos.getY() + 1.0,
                        entity.getZ(),
                    2,
                    0.0,
                    0.0,
                    0.0,
                    0.6
                );

                // Suffocate if inside & applicable.
                if (entity instanceof LivingEntity && canSuffocateLocal((LivingEntity) entity))
                {
                    entity.damage(ModDamageType.of(world, ModDamageType.QUICKSAND), 1.0F);
                }
            }
        }
    }

    // Determines whether the entity is in quicksand or not.
    // Used isInWall as reference
    private boolean canSuffocateLocal(LivingEntity entity)
    {
        if (!entity.noClip)
        {
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int i = 0; i < 8; i++) {
                double d = entity.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * entity.getWidth() * 0.8F);
                double e = entity.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * entity.getScale());
                double f = entity.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * entity.getWidth() * 0.8F);
                mutable.set(d, e, f);
                BlockState blockState = entity.getWorld().getBlockState(mutable);
                if (blockState.getRenderType() != BlockRenderType.INVISIBLE && blockState.shouldBlockVision(entity.getWorld(), mutable)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Simple check for if this entity can be sunk in here.
    private boolean isValidEntity(Entity entity)
    {
        return (
                !(entity instanceof WardenEntity) &&
                !(entity instanceof EnderDragonEntity) &&
                !(entity instanceof WitherEntity)
        );
    }
}
