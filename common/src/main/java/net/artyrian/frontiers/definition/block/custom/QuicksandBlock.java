package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.reg.content.FRTags;
import net.artyrian.frontiers.reg.property.FRDamageType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class QuicksandBlock extends Block
{
    public static final MapCodec<QuicksandBlock> CODEC = simpleCodec(QuicksandBlock::new);
    private final BlockParticleOption SAND_PARTICLES = new BlockParticleOption(ParticleTypes.BLOCK, defaultBlockState());
    private static final Vec3 NO_MOVEMENT = new Vec3(0.0, 0.0, 0.0);

    @Override
    public MapCodec<QuicksandBlock> codec() {
        return CODEC;
    }

    public QuicksandBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (isValidEntity(entity))
        {
            Vec3 vec3d = new Vec3(0.25, 0.05F, 0.25);
            Vec3 vec3d_sneak = new Vec3(0.25, 0.0F, 0.25);

            if (entity.isShiftKeyDown()) entity.makeStuckInBlock(state, vec3d_sneak);
            else entity.makeStuckInBlock(state, vec3d);

            // Do stuff for server side.
            if (!world.isClientSide)
            {
                if (!entity.isShiftKeyDown()) ((ServerLevel)world).sendParticles(
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
                    entity.hurt(FRDamageType.of(world, FRDamageType.QUICKSAND), 1.0F);
                }
            }
        }
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityShapeContext)
        {
            Entity entity = entityShapeContext.getEntity();
            if (entity != null)
            {
                if (!isValidEntity(entity)) return Shapes.block();

                boolean bl = entity instanceof FallingBlockEntity;
                if (bl || entity.isShiftKeyDown() && context.isAbove(Shapes.block(), pos, false))
                {
                    return Shapes.block();
                }
            }
        }
        return Shapes.empty();
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter world, BlockPos pos) { return Shapes.block(); }

    // Determines whether the entity is in quicksand or not.
    // Used isInWall as reference
    private boolean canSuffocateLocal(LivingEntity entity)
    {
        if (!entity.noPhysics)
        {
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

            for (int i = 0; i < 8; i++) {
                double d = entity.getX() + (double)(((float)((i >> 0) % 2) - 0.5F) * entity.getBbWidth() * 0.8F);
                double e = entity.getEyeY() + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F * entity.getScale());
                double f = entity.getZ() + (double)(((float)((i >> 2) % 2) - 0.5F) * entity.getBbWidth() * 0.8F);
                mutable.set(d, e, f);
                BlockState blockState = entity.level().getBlockState(mutable);
                if (blockState.getRenderShape() != RenderShape.INVISIBLE && blockState.isViewBlocking(entity.level(), mutable)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.is(this) ? true : super.skipRendering(state, stateFrom, direction);
    }

    // Simple check for if this entity can be sunk in here. Quicksand-immune entites do not work
    private boolean isValidEntity(Entity entity)
    {
        return !entity.getType().is(FRTags.EntityTypes.QUICKSAND_IMMUNE);
    }

    // Chat i am NOT doing this :skull: :skull: :skull: :skull: :skull:
    private boolean entityNotMoving(Entity entity)
    {
        Vec3 velocity = entity.getDeltaMovement();
        if (velocity.equals(Vec3.ZERO)) return true;
        else Frontiers.LOGGER.info(velocity.toString());

        return false;
    }
}
