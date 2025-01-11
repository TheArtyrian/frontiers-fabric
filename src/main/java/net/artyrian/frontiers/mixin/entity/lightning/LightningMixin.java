package net.artyrian.frontiers.mixin.entity.lightning;

import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_interfaces.BrewMixInterface;
import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.IntProvider;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(LightningEntity.class)
public abstract class LightningMixin extends EntityMixin
{
    @Shadow protected abstract BlockPos getAffectedBlockPos();

    @Inject(method = "powerLightningRod", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/LightningRodBlock;setPowered(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"))
    private void brewCheck(CallbackInfo ci)
    {
        BlockPos blockBelow = this.getAffectedBlockPos().add(new Vec3i(0, -1, 0));
        BlockState blockState = this.getWorld().getBlockState(blockBelow);

        if (blockState.isOf(Blocks.BREWING_STAND) && blockState.hasBlockEntity())
        {
            BlockEntity entity = this.getWorld().getBlockEntity(blockBelow);

            if (entity != null)
            {
                ((BrewMixInterface)entity).craftLightning(entity.getWorld(), blockBelow, ((BrewingStandBlockEntity)entity).getHeldStacks());

                if (this.getWorld().isClient())
                {
                    ParticleUtil.spawnParticlesAround(this.getWorld(), blockBelow, 16, ParticleTypes.WHITE_SMOKE);
                    ParticleUtil.spawnParticlesAround(this.getWorld(), blockBelow, 16, ParticleTypes.ELECTRIC_SPARK);
                    ParticleUtil.spawnParticlesAround(this.getWorld(), blockBelow, 16, ParticleTypes.OMINOUS_SPAWNING);
                }
            }
        }
    }
}
