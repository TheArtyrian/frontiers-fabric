package net.artyrian.frontiers.definition.block.custom;

import com.mojang.authlib.GameProfile;
import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.PersonalChestBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.ModStats;
import net.minecraft.core.BlockBox;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class PersonalChestBlock extends AbstractChestBlock<PersonalChestBlockEntity> implements SimpleWaterloggedBlock
{
    public static final MapCodec<PersonalChestBlock> CODEC = simpleCodec(settings -> new PersonalChestBlock(settings, () -> ModBlockEntities.PERSONAL_CHEST_BLOCKENTITY.get()));
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);

    public PersonalChestBlock(Properties settings, Supplier<BlockEntityType<? extends PersonalChestBlockEntity>> blockEntityTypeSupplier)
    {
        super(settings, blockEntityTypeSupplier);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override protected MapCodec<PersonalChestBlock> codec() { return CODEC; }
    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new PersonalChestBlockEntity(pos, state); }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) { builder.add(FACING, WATERLOGGED); }
    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
    @Override
    protected RenderShape getRenderShape(BlockState state) { return RenderShape.ENTITYBLOCK_ANIMATED; }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, this.getExpectedEntityType(), PersonalChestBlockEntity::clientTick) : createTickerHelper(type, this.getExpectedEntityType(), PersonalChestBlockEntity::serverTick);
    }

    @Override
    protected float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos)
    {
        if
        (
                world.getBlockEntity(pos) instanceof PersonalChestBlockEntity pchest
                && !pchest.playerOwnerMatches(player.getUUID())
        )
        {
            pchest.setCooldown(20);
            return 1.0F / 8000.0F;
        }
        return super.getDestroyProgress(state, player, world, pos);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
    {
        if (context instanceof EntityCollisionContext && world.getBlockEntity(pos) instanceof PersonalChestBlockEntity chest)
        {
            int time = chest.getCooldown();
            if (time > 0) { return Shapes.empty(); }
        }
        return SHAPE;
    }

    public BlockEntityType<? extends PersonalChestBlockEntity> getExpectedEntityType()
    {
        return this.blockEntityType.get();
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
    {
        super.setPlacedBy(world, pos, state, placer, itemStack);
        if (!world.isClientSide() && placer instanceof Player player && world.getBlockEntity(pos) instanceof PersonalChestBlockEntity chest && chest.getChestOwner() == null)
        {
            chest.setChestOwner(player.getUUID());
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit)
    {
        BlockEntity entity = world.getBlockEntity(pos);
        if (
                entity instanceof PersonalChestBlockEntity chest &&
                chest.playerOwnerMatches(player.getUUID()) &&
                stack.is(ModItem.CHEST_KEY.get()) &&
                stack.has(DataComponents.PROFILE)
        )
        {
            if (world.isClientSide)
            {
                return ItemInteractionResult.SUCCESS;
            }
            else
            {
                GameProfile userProf = stack.get(DataComponents.PROFILE).gameProfile();
                UUID uuidOwner = chest.getChestOwner();

                if (uuidOwner != null && userProf != null && !userProf.getId().equals(uuidOwner))
                {
                    UUID targetUUID = userProf.getId();
                    if (chest.canAddToAllowedList(targetUUID))
                    {
                        chest.addToAllowedList(targetUUID);

                        world.playSound(
                                null,
                                (double)pos.getX() + 0.5F,
                                (double)pos.getY() + 0.5F,
                                (double)pos.getZ() + 0.5F,
                                ModSounds.CHEST_KEY_USED.get(),
                                SoundSource.PLAYERS,
                                1.0F,
                                0.8F + (Math.clamp(world.getRandom().nextFloat(), 0.15F, 0.5F))
                        );
                    }
                }

                return ItemInteractionResult.CONSUME;
            }
        }
        return super.useItemOn(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        BlockPos above = pos.above();
        if (world.getBlockState(above).isRedstoneConductor(world, above))
        {
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        else if (world.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }
        else
        {
            UUID playerID = player.getUUID();
            if (
                    world.getBlockEntity(pos) instanceof PersonalChestBlockEntity entity &&
                    (entity.playerOwnerMatches(playerID) || entity.isUUIDOnAllowedList(playerID))
            )
            {
                MenuProvider namedScreenHandlerFactory = this.getMenuProvider(state, world, pos);
                if (namedScreenHandlerFactory != null)
                {
                    player.openMenu(namedScreenHandlerFactory);
                    player.awardStat(ModStats.getStat(ModStats.OPEN_PERSONALCHEST.get()));
                    PiglinAi.angerNearbyPiglins(player, true);
                }
            }
            else
            {
                world.playSound(
                        null,
                        (double)pos.getX() + 0.5F,
                        (double)pos.getY() + 0.5F,
                        (double)pos.getZ() + 0.5F,
                        ModSounds.PERSONAL_CHEST_LOCKED.get(),
                        SoundSource.BLOCKS,
                        0.5F,
                        0.8F + (Math.clamp(world.getRandom().nextFloat(), 0.15F, 0.5F))
                );
            }
            return InteractionResult.CONSUME;
        }
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
    protected FluidState getFluidState(BlockState state)
    {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx)
    {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params)
    {
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof PersonalChestBlockEntity pChest && pChest.getCooldown() > 0)
        {
            return List.of();
        }

        return super.getDrops(state, params);
    }

    @Override
    protected void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved)
    {
        if (!state.is(newState.getBlock()))
        {
            boolean dropContainer = true;
            BlockEntity entityAt = world.getBlockEntity(pos);
            if (entityAt instanceof PersonalChestBlockEntity chest1 && chest1.getCooldown() > 0)
            {
                for (BlockPos blockPos : BlockPos.randomBetweenClosed(world.random, 256, pos.getX() - 8, pos.getY(), pos.getZ() - 8, pos.getX() + 8, pos.getY() + 8, pos.getZ() + 8))
                {
                    if (world.getBlockState(blockPos).isAir())
                    {
                        world.setBlock(blockPos, state, Block.UPDATE_ALL_IMMEDIATE);
                        BlockEntity entity = world.getBlockEntity(blockPos);
                        if (entity instanceof PersonalChestBlockEntity chest2)
                        {
                            dropContainer = false;
                            PersonalChestBlockEntity.copyInFull(chest1, chest2);
                            break;
                        }
                    }
                }
            }

            if (dropContainer) Containers.dropContentsOnDestroy(state, newState, world, pos);
        }
        super.onRemove(state, world, pos, newState, moved);
    }

    @Override
    protected void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof PersonalChestBlockEntity personal)
        {
            personal.onScheduledTick();
        }
    }

    @Override
    public DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine(BlockState state, Level world, BlockPos pos, boolean ignoreBlocked)
    {
        return DoubleBlockCombiner.Combiner::acceptNone;
    }
}
