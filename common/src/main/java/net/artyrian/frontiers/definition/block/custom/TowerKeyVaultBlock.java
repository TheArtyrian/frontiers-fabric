package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.block.entity.TowerKeyVaultBlockEntity;
import net.artyrian.frontiers.reg.content.FRBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TowerKeyVaultBlock extends BaseEntityBlock implements EntityBlock
{
    public static final MapCodec<TowerKeyVaultBlock> CODEC = TowerKeyVaultBlock.simpleCodec(TowerKeyVaultBlock::new);

    public TowerKeyVaultBlock(Properties settings)
    {
        super(settings);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide
                ? createTickerHelper(type, FRBlockEntities.TOWER_KEY_VAULT.get(), TowerKeyVaultBlockEntity::tickClient)
                : createTickerHelper(type, FRBlockEntities.TOWER_KEY_VAULT.get(), TowerKeyVaultBlockEntity::tickServer);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec()
    {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new TowerKeyVaultBlockEntity(pos, state); }

    @Override
    protected RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }
}
