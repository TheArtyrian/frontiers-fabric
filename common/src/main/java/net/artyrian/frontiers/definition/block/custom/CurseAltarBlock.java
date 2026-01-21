package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.CurseAltarBlockEntity;
import net.artyrian.frontiers.definition.menu.curse.CurseAltarScreenHandler;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
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
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(world, pos));
            return InteractionResult.CONSUME;
        }
    }

    @Nullable @Override
    protected MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos)
    {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CurseAltarBlockEntity)
        {
            Component text = ((Nameable)blockEntity).getDisplayName();
            return new SimpleMenuProvider(
                    (syncId, inventory, player) -> new CurseAltarScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos)), text
            );
        }
        else
        {
            return null;
        }
    }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(), CurseAltarBlockEntity::tick) : null;
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
