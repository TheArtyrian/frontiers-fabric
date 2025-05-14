package net.artyrian.frontiers.mixin.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockEntity.class)
public abstract class BlockEntityMixin
{
    @Shadow public abstract @Nullable World getWorld();

    @Shadow public abstract void markDirty();

    @Shadow public abstract ComponentMap getComponents();

    @Shadow public abstract void setComponents(ComponentMap components);

    @Shadow public abstract BlockState getCachedState();

    @Shadow protected static void markDirty(World world, BlockPos pos, BlockState state)
    {

    }
}
