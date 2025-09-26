package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.misc.ModDamageType;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Unique;

public class QuicksandBlock extends Block
{
    public static final MapCodec<QuicksandBlock> CODEC = createCodec(QuicksandBlock::new);
    private final BlockStateParticleEffect SAND_PARTICLES = new BlockStateParticleEffect(ParticleTypes.BLOCK, getDefaultState());
    private static final Vec3d NO_MOVEMENT = new Vec3d(0.0, 0.0, 0.0);

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
            Vec3d vec3d_sneak = new Vec3d(0.25, 0.0F, 0.25);

            if (entity.isSneaking()) entity.slowMovement(state, vec3d_sneak);
            else entity.slowMovement(state, vec3d);

            // Do stuff for server side.
            if (!world.isClient)
            {
                if (!entity.isSneaking()) ((ServerWorld)world).spawnParticles(
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

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entityShapeContext)
        {
            Entity entity = entityShapeContext.getEntity();
            if (entity != null)
            {
                if (!isValidEntity(entity)) return VoxelShapes.fullCube();

                boolean bl = entity instanceof FallingBlockEntity;
                if (bl || entity.isSneaking() && context.isAbove(VoxelShapes.fullCube(), pos, false))
                {
                    return VoxelShapes.fullCube();
                }
            }
        }
        return VoxelShapes.empty();
    }

    @Override
    protected VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) { return VoxelShapes.fullCube(); }

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

    // Simple check for if this entity can be sunk in here. Quicksand-immune entites do not work
    private boolean isValidEntity(Entity entity)
    {
        return !entity.getType().isIn(ModTags.EntityTypes.QUICKSAND_IMMUNE);
    }

    // Chat i am NOT doing this :skull: :skull: :skull: :skull: :skull:
    private boolean entityNotMoving(Entity entity)
    {
        Vec3d velocity = entity.getVelocity();
        if (velocity.equals(Vec3d.ZERO)) return true;
        else Frontiers.LOGGER.info(velocity.toString());

        return false;
    }
}
