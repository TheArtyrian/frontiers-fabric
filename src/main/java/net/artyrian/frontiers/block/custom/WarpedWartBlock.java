package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class WarpedWartBlock extends NetherWartBlock
{
    public WarpedWartBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItem.WARPED_WART);
    }
}
