package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.definition.block.entity.PhantomBedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PhantomBedBlock extends BedBlock
{
    public PhantomBedBlock(Properties settings)
    {
        super(DyeColor.PURPLE, settings);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return new PhantomBedBlockEntity(pos, state);
    }
}
