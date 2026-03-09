package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.CurseAltarBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CurseAltarBlock extends BaseEntityBlock
{
    public static final MapCodec<CurseAltarBlock> CODEC = simpleCodec(CurseAltarBlock::new);
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);

    public CurseAltarBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        if (!world.isClientSide) player.openMenu(state.getMenuProvider(world, pos));
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(type, ModBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(),
                world.isClientSide ? CurseAltarBlockEntity::clientTick : CurseAltarBlockEntity::serverTick);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        Containers.dropContentsOnDestroy(state, newState, level, pos);
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected MapCodec<CurseAltarBlock> codec() { return CODEC; }
    @Nullable @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new CurseAltarBlockEntity(pos, state); }
    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPE; }
    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
