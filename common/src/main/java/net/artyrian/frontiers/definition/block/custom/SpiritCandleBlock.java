package net.artyrian.frontiers.definition.block.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.reg.misc.ModParticle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

public class SpiritCandleBlock extends Block implements SimpleWaterloggedBlock
{
    public static final MapCodec<SpiritCandleBlock> CODEC = simpleCodec(SpiritCandleBlock::new);
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.getValue(LIT) ? 6 : 0;
    public static final int BOX_EFFECT_SIZE = 4;

    private static final VoxelShape SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 7.0, 10.0);
    private static final int TICK_TIME = 60;

    public SpiritCandleBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(LIT, false)
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected MapCodec<SpiritCandleBlock> codec() { return CODEC; }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPE; }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(LIT, WATERLOGGED); }
    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {return Block.canSupportCenter(world, pos.below(), Direction.UP);}
    @Override
    protected FluidState getFluidState(BlockState state) { return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state); }
    protected boolean isNotLit(BlockState state) { return !state.getValue(WATERLOGGED) && !state.getValue(LIT); }

    @Override
    protected void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify)
    {
        //Frontiers.LOGGER.info("Scheduled a spirit candle");
        world.scheduleTick(pos, this, 20);
    }
    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if (state.getValue(LIT))
        {
            AABB box = new AABB(pos).inflate(BOX_EFFECT_SIZE);
            List<LivingEntity> list = world.getEntitiesOfClass(LivingEntity.class, box);

            for (LivingEntity entity : list)
            {
                if (entity.getType() != null && entity.getType().is(EntityTypeTags.UNDEAD))
                {
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0, true, true));
                }
            }
        }
        world.scheduleTick(pos, this, TICK_TIME);
    }
    @Override
    protected void onExplosionHit(BlockState state, Level world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger)
    {
        if (explosion.canTriggerBlocks() && state.getValue(LIT))
        {
            extinguish(null, state, world, pos);
        }

        super.onExplosionHit(state, world, pos, explosion, stackMerger);
    }

    @Override
    protected void onProjectileHit(Level world, BlockState state, BlockHitResult hit, Projectile projectile)
    {
        if (!world.isClientSide && projectile.isOnFire() && this.isNotLit(state))
        {
            setLit(world, state, hit.getBlockPos(), true);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        if (stack.isEmpty() && player.getAbilities().mayBuild && state.getValue(LIT))
        {
            extinguish(player, state, world, pos);
            return ItemInteractionResult.sidedSuccess(world.isClientSide);
        }
        else if ((stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) && !canBeLit(state))
        {
            return ItemInteractionResult.FAIL;
        }
        else if (stack.is(Items.FLINT_AND_STEEL))
        {
            setLit(world, state, pos, true);
            world.playSound(player, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            player.getItemInHand(hand).hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
            return ItemInteractionResult.SUCCESS;
        }
        else if (stack.is(Items.FIRE_CHARGE))
        {
            setLit(world, state, pos, true);
            world.playSound(null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F);
            if (!player.isCreative())
            {
                stack.shrink(1);
            }
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static boolean isLitCandle(BlockState state) {
        return state.hasProperty(LIT) && state.getValue(LIT);
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    )
    {
        if (state.getValue(WATERLOGGED))
        {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!(Boolean)state.getValue(WATERLOGGED) && fluidState.getType() == Fluids.WATER)
        {
            BlockState blockState = state.setValue(WATERLOGGED, Boolean.valueOf(true));
            if (state.getValue(LIT))
            {
                extinguish(null, blockState, world, pos);
            }
            else
            {
                world.setBlock(pos, blockState, Block.UPDATE_ALL);
            }

            world.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(world));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random)
    {
        if (state.getValue(LIT))
        {
            this.getParticleOffsets(state)
                    .forEach(offset -> spawnCandleParticles(world, offset.add(pos.getX(), pos.getY(), pos.getZ()), random));
        }
    }

    private static void spawnCandleParticles(Level world, Vec3 vec3d, RandomSource random) {
        float f = random.nextFloat();
        if (f < 0.3F)
        {
            world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
            if (f < 0.17F)
            {
                world.playLocalSound(
                        vec3d.x + 0.5,
                        vec3d.y + 0.5,
                        vec3d.z + 0.5,
                        SoundEvents.CANDLE_AMBIENT,
                        SoundSource.BLOCKS,
                        1.0F + random.nextFloat(),
                        random.nextFloat() * 0.7F + 0.3F,
                        false
                );
            }
        }
        world.addParticle(ModParticle.VEX_FLAME.get(), vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
    }

    public static void spawnBlockingParticles(ServerLevel world, BlockPos pos) {

    }

    protected Iterable<Vec3> getParticleOffsets(BlockState state)
    {
        return ImmutableList.of(new Vec3(0.5, 0.56, 0.5));
    }
    public static boolean canBeLit(BlockState state)
    {
        return (
                state.getBlock() instanceof SpiritCandleBlock
                && state.hasProperty(LIT)
                &&state.hasProperty(WATERLOGGED)
                && !(Boolean)state.getValue(LIT)
                && !(Boolean)state.getValue(WATERLOGGED))
        ;
    }
    private static void setLit(LevelAccessor world, BlockState state, BlockPos pos, boolean lit)
    {
        world.setBlock(pos, state.setValue(LIT, lit), Block.UPDATE_ALL_IMMEDIATE);
    }
    public static void extinguish(@Nullable Player player, BlockState state, LevelAccessor world, BlockPos pos)
    {
        setLit(world, state, pos, false);
        if (state.getBlock() instanceof SpiritCandleBlock)
        {
            ((SpiritCandleBlock)state.getBlock())
                    .getParticleOffsets(state)
                    .forEach(
                            offset -> world.addParticle(
                                    ParticleTypes.SMOKE, (double)pos.getX() + offset.x(), (double)pos.getY() + offset.y(), (double)pos.getZ() + offset.z(), 0.0, 0.1F, 0.0
                            )
                    );
        }

        world.playSound(null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }
}
