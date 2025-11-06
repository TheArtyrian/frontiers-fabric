package net.artyrian.frontiers.block.custom;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModStats;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Supplier;

public class PersonalChestBlock extends AbstractChestBlock<PersonalChestBlockEntity> implements Waterloggable
{
    public static final MapCodec<PersonalChestBlock> CODEC = createCodec(settings -> new PersonalChestBlock(settings, () -> ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY));
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);

    public PersonalChestBlock(Settings settings, Supplier<BlockEntityType<? extends PersonalChestBlockEntity>> blockEntityTypeSupplier)
    {
        super(settings, blockEntityTypeSupplier);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override protected MapCodec<PersonalChestBlock> getCodec() { return CODEC; }
    @Nullable @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new PersonalChestBlockEntity(pos, state); }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(FACING, WATERLOGGED); }
    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) { return BlockRenderType.ENTITYBLOCK_ANIMATED; }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient ? validateTicker(type, this.getExpectedEntityType(), PersonalChestBlockEntity::clientTick) : validateTicker(type, this.getExpectedEntityType(), PersonalChestBlockEntity::serverTick);
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player)
    {
        if (world.getBlockEntity(pos) instanceof PersonalChestBlockEntity chest)
        {
            if (!chest.playerOwnerMatches(player.getUuid()) && chest.getCooldown() <= 0)
            {
                chest.setCooldown(120);
            }
        }
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos)
    {
        if
        (
                world.getBlockEntity(pos) instanceof PersonalChestBlockEntity pchest &&
                !pchest.playerOwnerMatches(player.getUuid())
        )
        {
            return 1.0F / 100.0F / 100.0F;
        }
        return super.calcBlockBreakingDelta(state, player, world, pos);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (context instanceof EntityShapeContext entityShapeContext && world.getBlockEntity(pos) instanceof PersonalChestBlockEntity chest)
        {
            int time = chest.getCooldown();
            Entity entity = entityShapeContext.getEntity();
            if (time > 0)
            {
                return SHAPE;
                //if (entity instanceof PlayerEntity player && (chest.playerOwnerMatches(player.getUuid()) || chest.isUUIDOnAllowedList(player.getUuid()))) return SHAPE;
                //else return VoxelShapes.empty();
            }
        }
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        if (context instanceof EntityShapeContext entityShapeContext && world.getBlockEntity(pos) instanceof PersonalChestBlockEntity chest)
        {
            int time = chest.getCooldown();
            Entity entity = entityShapeContext.getEntity();
            if (time > 0)
            {
                return VoxelShapes.empty();
            }
        }
        return SHAPE;
    }

    public BlockEntityType<? extends PersonalChestBlockEntity> getExpectedEntityType()
    {
        return this.entityTypeRetriever.get();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient() && placer instanceof PlayerEntity player && world.getBlockEntity(pos) instanceof PersonalChestBlockEntity chest && chest.getChestOwner() == null)
        {
            chest.setChestOwner(player.getUuid());
        }
    }

    // TODO: Make it so after several hours of non interaction, the chest can be broken
    // Also this might not work atm
    //@Override
    //protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    //{
    //    if (context instanceof EntityShapeContext entityShapeContext && world.getBlockEntity(pos) instanceof PersonalChestBlockEntity blockentity)
    //    {
    //        Entity entity = entityShapeContext.getEntity();
    //        if (
    //                entity instanceof PlayerEntity player &&
    //                (!blockentity.playerOwnerMatches(player.getUuid()))
    //        )
    //        {
    //            return SHAPE_NONOWNER;
    //        }
    //    }
    //    return super.getCollisionShape(state, world, pos, context);
    //}

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        BlockEntity entity = world.getBlockEntity(pos);
        if (
                entity instanceof PersonalChestBlockEntity chest &&
                chest.playerOwnerMatches(player.getUuid()) &&
                stack.isOf(ModItem.CHEST_KEY) &&
                stack.contains(DataComponentTypes.PROFILE)
        )
        {
            if (world.isClient)
            {
                return ItemActionResult.SUCCESS;
            }
            else
            {
                GameProfile userProf = stack.get(DataComponentTypes.PROFILE).gameProfile();
                UUID uuidOwner = chest.getChestOwner();

                if (uuidOwner != null && userProf != null && !userProf.getId().equals(uuidOwner))
                {
                    UUID targetUUID = userProf.getId();
                    if (chest.canAddToAllowedList(targetUUID))
                    {
                        chest.addToAllowedList(targetUUID);

                        ((ServerWorld)world).playSound(
                                null,
                                (double)pos.getX() + 0.5F,
                                (double)pos.getY() + 0.5F,
                                (double)pos.getZ() + 0.5F,
                                ModSounds.CHEST_KEY_USED,
                                SoundCategory.PLAYERS,
                                1.0F,
                                0.8F + (Math.clamp(world.getRandom().nextFloat(), 0.15F, 0.5F))
                        );
                    }
                }

                return ItemActionResult.CONSUME;
            }
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        BlockPos above = pos.up();
        if (world.getBlockState(above).isSolidBlock(world, above))
        {
            return ActionResult.success(world.isClient);
        }
        else if (world.isClient)
        {
            return ActionResult.SUCCESS;
        }
        else
        {
            UUID playerID = player.getUuid();
            if (
                    world.getBlockEntity(pos) instanceof PersonalChestBlockEntity entity &&
                    (entity.playerOwnerMatches(playerID) || entity.isUUIDOnAllowedList(playerID))
            )
            {
                NamedScreenHandlerFactory namedScreenHandlerFactory = this.createScreenHandlerFactory(state, world, pos);
                if (namedScreenHandlerFactory != null)
                {
                    player.openHandledScreen(namedScreenHandlerFactory);
                    player.incrementStat(ModStats.OPEN_PERSONALCHEST);
                    PiglinBrain.onGuardedBlockInteracted(player, true);
                }
            }
            else
            {
                ((ServerWorld)world).playSound(
                        null,
                        (double)pos.getX() + 0.5F,
                        (double)pos.getY() + 0.5F,
                        (double)pos.getZ() + 0.5F,
                        ModSounds.PERSONAL_CHEST_LOCKED,
                        SoundCategory.BLOCKS,
                        0.5F,
                        0.8F + (Math.clamp(world.getRandom().nextFloat(), 0.15F, 0.5F))
                );
            }
            return ActionResult.CONSUME;
        }
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
    protected FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx)
    {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
    {
        ItemScatterer.onStateReplaced(state, newState, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PersonalChestBlockEntity) {
            ((PersonalChestBlockEntity)blockEntity).onScheduledTick();
        }
    }

    @Override
    public DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> getBlockEntitySource(BlockState state, World world, BlockPos pos, boolean ignoreBlocked)
    {
        return DoubleBlockProperties.PropertyRetriever::getFallback;
    }
}
