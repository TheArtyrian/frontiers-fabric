package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.block.entity.PhantomBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class PhantomBedBlock extends BedBlock
{
    public PhantomBedBlock(Settings settings)
    {
        super(DyeColor.PURPLE, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
    {
        return new PhantomBedBlockEntity(pos, state);
    }
}
