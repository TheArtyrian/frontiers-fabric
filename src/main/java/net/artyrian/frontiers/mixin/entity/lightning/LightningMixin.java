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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
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
        boolean isRotatedUp = (this.getWorld().getBlockState(this.getAffectedBlockPos()).get(Properties.FACING) == Direction.UP);

        if (blockState.isOf(Blocks.BREWING_STAND) && blockState.hasBlockEntity() && isRotatedUp)
        {
            BlockEntity entity = this.getWorld().getBlockEntity(blockBelow);

            if (entity != null)
            {
                ((BrewMixInterface)entity).craftLightning(entity.getWorld(), blockBelow, ((BrewingStandBlockEntity)entity).getHeldStacks());

                ServerWorld serverWorld = (ServerWorld)this.getWorld();
                serverWorld.spawnParticles(
                        ParticleTypes.WHITE_SMOKE,
                        blockBelow.getX() + 0.5,
                        blockBelow.getY() + 0.5,
                        blockBelow.getZ() + 0.5,
                        12,
                        0.0,
                        0.0,
                        0.0,
                        1.0
                );
                serverWorld.spawnParticles(
                        ParticleTypes.ELECTRIC_SPARK,
                        blockBelow.getX() + 0.5,
                        blockBelow.getY() + 0.5,
                        blockBelow.getZ() + 0.5,
                        12,
                        0.0,
                        0.0,
                        0.0,
                        1.0
                );
                serverWorld.spawnParticles(
                        ParticleTypes.TRIAL_SPAWNER_DETECTION_OMINOUS,
                        blockBelow.getX() + 0.5,
                        blockBelow.getY() + 0.5,
                        blockBelow.getZ() + 0.5,
                        12,
                        0.0,
                        0.01,
                        0.0,
                        1.0
                );

            }
        }
    }
}
