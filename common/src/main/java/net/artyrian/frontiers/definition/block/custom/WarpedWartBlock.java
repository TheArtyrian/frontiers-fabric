package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WarpedWartBlock extends NetherWartBlock
{
    public WarpedWartBlock(Properties settings)
    {
        super(settings);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state)
    {
        return new ItemStack(ModItem.WARPED_WART.get());
    }
}
