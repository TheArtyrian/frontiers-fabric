package net.artyrian.frontiers.mixin.entity.projectile;

import net.artyrian.frontiers.definition.block.custom.SpiritCandleBlock;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownPotion.class)
public abstract class ThrowPotionMixin extends ProjectileMixin
{
    @Inject(method = "dowseFire", at = @At("TAIL"))
    private void addFrontiersChecks(BlockPos pos, CallbackInfo ci)
    {
        BlockState blockState = this.level().getBlockState(pos);
        if (blockState.is(ModBlocks.SPIRIT_CANDLE.get()) && SpiritCandleBlock.isLitCandle(blockState))
        {
            SpiritCandleBlock.extinguish(null, blockState, this.level(), pos);
        }
    }
}
