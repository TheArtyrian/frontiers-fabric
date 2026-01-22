package net.artyrian.frontiers.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin
{
    @Shadow public abstract BlockPos getBlockPos();

    @Shadow public abstract @Nullable Level getLevel();

    @Shadow public abstract void setChanged();

    @Shadow public abstract DataComponentMap components();

    @Shadow public abstract void setComponents(DataComponentMap components);

    @Shadow public abstract BlockState getBlockState();

    @Shadow protected static void setChanged(Level world, BlockPos pos, BlockState state)
    {

    }
}
