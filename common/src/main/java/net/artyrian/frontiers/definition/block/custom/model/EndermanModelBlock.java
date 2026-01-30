package net.artyrian.frontiers.definition.block.custom.model;

import net.artyrian.frontiers.definition.block.entity.model.BoggedModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.CreeperModelBlockEntity;
import net.artyrian.frontiers.definition.block.entity.model.EndermanModelBlockEntity;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EndermanModelBlock extends EntityModelBlock
{
    public EndermanModelBlock(Properties settings)
    {
        super(settings);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type)
    {
        return world.isClientSide ? createTickerHelper(type, ModBlockEntities.ENDERMAN_MODEL_BLOCKENTITY.get(), EndermanModelBlockEntity::tick) : null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new EndermanModelBlockEntity(pos, state); }
}
