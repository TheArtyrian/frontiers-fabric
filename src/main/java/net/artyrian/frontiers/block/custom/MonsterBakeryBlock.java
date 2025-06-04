package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.misc.ModStats;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MonsterBakeryBlock extends BlockWithEntity
{
    public static final BooleanProperty LIT = Properties.LIT;

    public MonsterBakeryBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false));
    }

    public static final MapCodec<MonsterBakeryBlock> CODEC = createCodec(MonsterBakeryBlock::new);
    @Override
    protected MapCodec<MonsterBakeryBlock> getCodec() { return CODEC; }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new MonsterBakeryBlockEntity(pos, state);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
    {
        if (!state.isOf(newState.getBlock()))
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MonsterBakeryBlockEntity)
            {
                if (world instanceof ServerWorld)
                {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), ((MonsterBakeryBlockEntity) blockEntity).getStack(0));
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), ((MonsterBakeryBlockEntity) blockEntity).getStack(1));
                }

                super.onStateReplaced(state, world, pos, newState, moved);
                world.updateComparators(pos, this);
            }
            else
            {
                super.onStateReplaced(state, world, pos, newState, moved);
            }
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (world.isClient)
        {
            return ActionResult.SUCCESS;
        }
        else
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MonsterBakeryBlockEntity)
            {
                player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
                player.incrementStat(ModStats.INTERACT_WITH_MONSTER_BAKERY);
            }
            return ActionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return validateTicker(type, ModBlockEntities.MONSTER_BAKERY_BLOCKENTITY,
                world.isClient ? MonsterBakeryBlockEntity::clientTick : MonsterBakeryBlockEntity::serverTick);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
