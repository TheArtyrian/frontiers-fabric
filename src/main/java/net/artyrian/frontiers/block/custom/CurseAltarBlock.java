package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.block.entity.CurseAltarBlockEntity;
import net.artyrian.frontiers.block.entity.ModBlockEntities;
import net.artyrian.frontiers.client.screen.curse.CurseAltarScreenHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Nameable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CurseAltarBlock extends BlockWithEntity
{
    public static final MapCodec<CurseAltarBlock> CODEC = createCodec(CurseAltarBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);

    public CurseAltarBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }

    @Nullable @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos)
    {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CurseAltarBlockEntity)
        {
            Text text = ((Nameable)blockEntity).getDisplayName();
            return new SimpleNamedScreenHandlerFactory(
                    (syncId, inventory, player) -> new CurseAltarScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text
            );
        }
        else
        {
            return null;
        }
    }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClient ? validateTicker(type, ModBlockEntities.CURSE_ALTAR_BLOCKENTITY, CurseAltarBlockEntity::tick) : null;
    }

    @Override
    protected MapCodec<CurseAltarBlock> getCodec() { return CODEC; }
    @Nullable @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new CurseAltarBlockEntity(pos, state); }
    @Override
    protected boolean hasSidedTransparency(BlockState state) {
        return true;
    }
    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return SHAPE; }
    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
