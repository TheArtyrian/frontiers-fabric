package net.artyrian.frontiers.block.entity;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class PhantomBedBlockEntity extends BlockEntity
{
    public PhantomBedBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.PHANTOM_BED_BLOCKENTITY, pos, state);
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}

