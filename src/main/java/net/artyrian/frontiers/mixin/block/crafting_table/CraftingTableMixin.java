package net.artyrian.frontiers.mixin.block.crafting_table;

import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingTableBlock.class)
public abstract class CraftingTableMixin extends BlockMixin
{
    @Inject(method = "createScreenHandlerFactory", at = @At("TAIL"), cancellable = true)
    public void screenInjector(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<NamedScreenHandlerFactory> cir)
    {

    }
}
