package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.MonsterBakeryBlockEntity;
import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.artyrian.frontiers.reg.misc.FRStats;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MonsterBakeryBlock extends BaseEntityBlock
{
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public MonsterBakeryBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    public static final MapCodec<MonsterBakeryBlock> CODEC = simpleCodec(MonsterBakeryBlock::new);
    @Override
    protected MapCodec<MonsterBakeryBlock> codec() { return CODEC; }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new MonsterBakeryBlockEntity(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved)
    {
        if (!state.is(newState.getBlock()))
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MonsterBakeryBlockEntity)
            {
                if (world instanceof ServerLevel)
                {
                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), ((MonsterBakeryBlockEntity) blockEntity).getItem(0));
                    Containers.dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), ((MonsterBakeryBlockEntity) blockEntity).getItem(1));
                }

                super.onRemove(state, world, pos, newState, moved);
                world.updateNeighbourForOutputSignal(pos, this);
            }
            else
            {
                super.onRemove(state, world, pos, newState, moved);
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        if (world.isClientSide)
        {
            return InteractionResult.SUCCESS;
        }
        else
        {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MonsterBakeryBlockEntity)
            {
                player.openMenu((MenuProvider)blockEntity);
                player.awardStat(FRStats.getStat(FRStats.INTERACT_WITH_MONSTER_BAKERY.get()));
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, FRBlockEntities.MONSTER_BAKERY_BLOCKENTITY.get(),
                world.isClientSide ? MonsterBakeryBlockEntity::clientTick : MonsterBakeryBlockEntity::serverTick);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
