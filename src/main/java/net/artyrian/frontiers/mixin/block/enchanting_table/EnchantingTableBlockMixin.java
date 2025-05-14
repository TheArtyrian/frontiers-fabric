package net.artyrian.frontiers.mixin.block.enchanting_table;

import net.artyrian.frontiers.mixin.BlockWithEntityMixin;
import net.artyrian.frontiers.mixin.block.BlockMixin;
import net.artyrian.frontiers.mixin_interfaces.EnchantTableMixInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin extends BlockWithEntityMixin
{
    @Inject(method = "getTicker", at = @At("HEAD"), cancellable = true, order = 500)
    private <T extends BlockEntity> void changeTickerReturn(
            World world, BlockState state, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> cir)
    {
        if (!world.isClient)
        {
            cir.setReturnValue(validateTicker(type, BlockEntityType.ENCHANTING_TABLE, EnchantTableMixInterface::frontiers$frontiersServerTableTick));
        }
    }

}
