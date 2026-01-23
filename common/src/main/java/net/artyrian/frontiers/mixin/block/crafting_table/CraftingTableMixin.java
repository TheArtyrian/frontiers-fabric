package net.artyrian.frontiers.mixin.block.crafting_table;

import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingTableBlock.class)
public abstract class CraftingTableMixin extends BlockMixin
{
    @Inject(method = "getMenuProvider", at = @At("TAIL"))
    public void frnt$screenInjector(BlockState state, Level world, BlockPos pos, CallbackInfoReturnable<MenuProvider> cir)
    {

    }
}
