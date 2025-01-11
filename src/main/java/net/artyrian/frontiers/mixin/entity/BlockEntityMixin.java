package net.artyrian.frontiers.mixin.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin
{
    @Shadow public abstract BlockState getCachedState();

    @Shadow protected static void markDirty(World world, BlockPos pos, BlockState state)
    {

    }
}
