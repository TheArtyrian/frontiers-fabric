package net.artyrian.frontiers.mixin.block.enchanting_table;

import net.artyrian.frontiers.mixin.BlockWithEntityMixin;
import net.artyrian.frontiers.mixin_intf.EnchantTableMixInterface;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin extends BlockWithEntityMixin
{
    @Inject(method = "getTicker", at = @At("HEAD"), cancellable = true)
    private <T extends BlockEntity> void frontiers$changeTickerReturn(
            Level world, BlockState state, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> cir)
    {
        if (!world.isClientSide)
        {
            cir.setReturnValue(createTickerHelper(type, BlockEntityType.ENCHANTING_TABLE, EnchantTableMixInterface::frontiers$frontiersServerTableTick));
        }
    }

}
