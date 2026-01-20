package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PhantomBedBlockEntity extends BlockEntity
{
    public PhantomBedBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.PHANTOM_BED_BLOCKENTITY.get(), pos, state);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}

