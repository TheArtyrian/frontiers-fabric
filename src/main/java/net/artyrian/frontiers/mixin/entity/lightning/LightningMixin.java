package net.artyrian.frontiers.mixin.entity.lightning;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.misc.ModBlockProperties;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_interfaces.BrewMixInterface;
import net.artyrian.frontiers.mixin_interfaces.LightningMixInterface;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LightningRodBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrewingStandBlockEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(LightningEntity.class)
public abstract class LightningMixin extends EntityMixin implements LightningMixInterface
{
    @Shadow protected abstract BlockPos getAffectedBlockPos();

    @Shadow private @Nullable ServerPlayerEntity channeler;
    @Unique private final Boolean IS_CHANNELED = ((AttachmentTarget)this)
            .getAttachedOrCreate(ModAttachmentTypes.LIGHTNING_IS_CHANNELED, ModAttachmentTypes.LIGHTNING_IS_CHANNELED.initializer());

    @Override
    public boolean frontiers_1_21x$isChanneled()
    {
        return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.LIGHTNING_IS_CHANNELED, ModAttachmentTypes.LIGHTNING_IS_CHANNELED.initializer());
    }
    @Override
    public void frontiers_1_21x$setChanneled(boolean value)
    {
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.LIGHTNING_IS_CHANNELED, value);
    }

    @Inject(method = "powerLightningRod", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/LightningRodBlock;setPowered(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"))
    private void brewCheck(CallbackInfo ci)
    {
        World world = this.getWorld();
        Direction rod_direction = world.getBlockState(this.getAffectedBlockPos()).get(Properties.FACING);

        BlockPos blockBelow = this.getAffectedBlockPos().offset(rod_direction.getOpposite());
        BlockState blockState = world.getBlockState(blockBelow);
        boolean isRotatedUp = (rod_direction == Direction.UP);

        boolean isNotChanneled = (!this.frontiers_1_21x$isChanneled());

        for (int i = 0; i < 15; i++)
        {
            if (
                    blockState.isOf(Blocks.LIGHTNING_ROD)
                    && blockState.get(Properties.FACING) == rod_direction
                    && blockState.get(ModBlockProperties.ROD_CONNECTED)
            )
            {
                ((LightningRodBlock) blockState.getBlock()).setPowered(blockState, world, blockBelow);
                blockBelow = blockBelow.offset(rod_direction.getOpposite());
                blockState = world.getBlockState(blockBelow);
            }
            else break;
        }

        if (blockState.isOf(Blocks.BREWING_STAND) && blockState.hasBlockEntity() && isRotatedUp && isNotChanneled)
        {
            BlockEntity entity = world.getBlockEntity(blockBelow);

            if (entity != null)
            {
                ((BrewMixInterface)entity).frontiers_1_21x$craftLightning(entity.getWorld(), blockBelow, ((BrewingStandBlockEntity)entity).getHeldStacks());

                ServerWorld serverWorld = (ServerWorld)world;
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

    @Inject(method = "setChanneler", at = @At("TAIL"))
    private void lazyChannelerSet(ServerPlayerEntity channeler, CallbackInfo ci)
    {
        this.frontiers_1_21x$setChanneled((channeler != null));
    }
}
