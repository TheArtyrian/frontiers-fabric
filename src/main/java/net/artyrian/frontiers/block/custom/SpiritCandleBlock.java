package net.artyrian.frontiers.block.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.particle.ModParticle;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

public class SpiritCandleBlock extends Block implements Waterloggable
{
    public static final MapCodec<SpiritCandleBlock> CODEC = createCodec(SpiritCandleBlock::new);
    public static final BooleanProperty LIT = AbstractCandleBlock.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.get(LIT) ? 6 : 0;
    public static final int BOX_EFFECT_SIZE = 4;

    private static final VoxelShape SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 7.0, 10.0);
    private static final int TICK_TIME = 60;

    public SpiritCandleBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(
                this.stateManager.getDefaultState()
                        .with(LIT, false)
                        .with(WATERLOGGED, false)
        );
    }

    @Override
    protected MapCodec<SpiritCandleBlock> getCodec() { return CODEC; }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(LIT, WATERLOGGED); }
    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);}
    @Override
    protected FluidState getFluidState(BlockState state) { return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state); }
    protected boolean isNotLit(BlockState state) { return !state.get(WATERLOGGED) && !state.get(LIT); }

    @Override
    protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
    {
        //Frontiers.LOGGER.info("Scheduled a spirit candle");
        world.scheduleBlockTick(pos, this, 20);
    }
    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (state.get(LIT))
        {
            Box box = new Box(pos).expand(BOX_EFFECT_SIZE);
            List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, box);

            for (LivingEntity entity : list)
            {
                if (entity.getType() != null && entity.getType().isIn(EntityTypeTags.UNDEAD))
                {
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0, true, true));
                }
            }
        }
        world.scheduleBlockTick(pos, this, TICK_TIME);
    }
    @Override
    protected void onExploded(BlockState state, World world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger)
    {
        if (explosion.canTriggerBlocks() && state.get(LIT))
        {
            extinguish(null, state, world, pos);
        }

        super.onExploded(state, world, pos, explosion, stackMerger);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (stack.isEmpty() && player.getAbilities().allowModifyWorld && state.get(LIT))
        {
            extinguish(player, state, world, pos);
            return ItemActionResult.success(world.isClient);
        }
        else if ((stack.isOf(Items.FLINT_AND_STEEL) || stack.isOf(Items.FIRE_CHARGE)) && !canBeLit(state))
        {
            return ItemActionResult.FAIL;
        }
        else if (stack.isOf(Items.FLINT_AND_STEEL))
        {
            setLit(world, state, pos, true);
            world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            player.getStackInHand(hand).damage(1, player, LivingEntity.getSlotForHand(hand));
            return ItemActionResult.SUCCESS;
        }
        else if (stack.isOf(Items.FIRE_CHARGE))
        {
            setLit(world, state, pos, true);
            world.playSound(null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 1.0F);
            if (!player.isCreative())
            {
                stack.decrement(1);
            }
            return ItemActionResult.SUCCESS;
        }
        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static boolean isLitCandle(BlockState state) {
        return state.contains(LIT) && state.get(LIT);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
    )
    {
        if (state.get(WATERLOGGED))
        {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!(Boolean)state.get(WATERLOGGED) && fluidState.getFluid() == Fluids.WATER)
        {
            BlockState blockState = state.with(WATERLOGGED, Boolean.valueOf(true));
            if (state.get(LIT))
            {
                extinguish(null, blockState, world, pos);
            }
            else
            {
                world.setBlockState(pos, blockState, Block.NOTIFY_ALL);
            }

            world.scheduleFluidTick(pos, fluidState.getFluid(), fluidState.getFluid().getTickRate(world));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random)
    {
        if (state.get(LIT))
        {
            this.getParticleOffsets(state)
                    .forEach(offset -> spawnCandleParticles(world, offset.add(pos.getX(), pos.getY(), pos.getZ()), random));
        }
    }

    private static void spawnCandleParticles(World world, Vec3d vec3d, Random random) {
        float f = random.nextFloat();
        if (f < 0.3F)
        {
            world.addParticle(ParticleTypes.SMOKE, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
            if (f < 0.17F)
            {
                world.playSound(
                        vec3d.x + 0.5,
                        vec3d.y + 0.5,
                        vec3d.z + 0.5,
                        SoundEvents.BLOCK_CANDLE_AMBIENT,
                        SoundCategory.BLOCKS,
                        1.0F + random.nextFloat(),
                        random.nextFloat() * 0.7F + 0.3F,
                        false
                );
            }
        }
        world.addParticle(ModParticle.VEX_FLAME, vec3d.x, vec3d.y, vec3d.z, 0.0, 0.0, 0.0);
    }

    public static void spawnBlockingParticles(ServerWorld world, BlockPos pos) {
        Random random = world.getRandom();
        for (int i = 0; i < 20; i++)
        {
            double t = (double) pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 2.0;
            double u = (double) pos.getY() + 0.5 + (random.nextDouble() - 0.5) * 2.0;
            double v = (double) pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 2.0;

            world.spawnParticles(ParticleTypes.SMOKE, t, u, v, 1, 0.0, 0.0, 0.0, 0);
            world.spawnParticles(ModParticle.VEX_FLAME, t, u, v, 1, 0.0, 0.0, 0.0, 0);
        }
    }

    protected Iterable<Vec3d> getParticleOffsets(BlockState state)
    {
        return ImmutableList.of(new Vec3d(0.5, 0.56, 0.5));
    }
    public static boolean canBeLit(BlockState state)
    {
        return (
                state.getBlock() instanceof SpiritCandleBlock
                && state.contains(LIT)
                &&state.contains(WATERLOGGED)
                && !(Boolean)state.get(LIT)
                && !(Boolean)state.get(WATERLOGGED))
        ;
    }
    private static void setLit(WorldAccess world, BlockState state, BlockPos pos, boolean lit)
    {
        world.setBlockState(pos, state.with(LIT, lit), Block.NOTIFY_ALL_AND_REDRAW);
    }
    public static void extinguish(@Nullable PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos)
    {
        setLit(world, state, pos, false);
        if (state.getBlock() instanceof SpiritCandleBlock)
        {
            ((SpiritCandleBlock)state.getBlock())
                    .getParticleOffsets(state)
                    .forEach(
                            offset -> world.addParticle(
                                    ParticleTypes.SMOKE, (double)pos.getX() + offset.getX(), (double)pos.getY() + offset.getY(), (double)pos.getZ() + offset.getZ(), 0.0, 0.1F, 0.0
                            )
                    );
        }

        world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }
}
