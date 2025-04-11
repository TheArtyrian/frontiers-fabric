package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.dimension.ModDimension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.dimension.DimensionType;

public class UnbreakableInDimensionBlock extends Block
{
    private final RegistryKey<World> DIMENSION;

    public UnbreakableInDimensionBlock(RegistryKey<World> type, Settings settings)
    {
        super(settings);
        this.DIMENSION = type;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        RegistryKey<World> c = ((World)world).getRegistryKey();
        if (c.equals(DIMENSION))
        {
            return false;
        }
        return super.canPlaceAt(state, world, pos);
    }

    public RegistryKey<World> getUnbreakableDimension()
    {
        return DIMENSION;
    }
}
