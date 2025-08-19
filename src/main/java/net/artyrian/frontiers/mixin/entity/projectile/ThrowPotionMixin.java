package net.artyrian.frontiers.mixin.entity.projectile;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.SpiritCandleBlock;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionEntity.class)
public abstract class ThrowPotionMixin extends ProjectileMixin
{
    @Inject(method = "extinguishFire", at = @At("TAIL"))
    private void addFrontiersChecks(BlockPos pos, CallbackInfo ci)
    {
        BlockState blockState = this.getWorld().getBlockState(pos);
        if (blockState.isOf(ModBlocks.SPIRIT_CANDLE) && SpiritCandleBlock.isLitCandle(blockState))
        {
            SpiritCandleBlock.extinguish(null, blockState, this.getWorld(), pos);
        }
    }
}
