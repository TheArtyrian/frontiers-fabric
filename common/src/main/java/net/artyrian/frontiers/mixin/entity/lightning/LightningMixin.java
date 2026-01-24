package net.artyrian.frontiers.mixin.entity.lightning;

import net.artyrian.frontiers.definition.data.nbt_sync.LightningPersistentNBT;
import net.artyrian.frontiers.definition.networking.payload.attachment.LightningPayload;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.BrewMixInterface;
import net.artyrian.frontiers.mixin_intf.LightningIntf;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.vertisoft.vectorlib.VectorLib;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(LightningBolt.class)
public abstract class LightningMixin extends EntityMixin implements LightningIntf
{
    @Shadow protected abstract BlockPos getStrikePosition();
    @Shadow private @Nullable ServerPlayer cause;

    @Unique private CompoundTag frontiers$persistentData;

    @Override
    public boolean frontiers_1_21x$isChanneled()
    {
        if (this.frontiers$persistentData != null && this.frontiers$persistentData.contains(LightningPersistentNBT.CHANNELED))
        {
            return this.frontiers$persistentData.getBoolean(LightningPersistentNBT.CHANNELED);
        }
        else return false;
    }
    @Override
    public void frontiers_1_21x$setChanneled(boolean value)
    {
        LightningPersistentNBT.setChanneled(this, value);
        frontiers$sendToAllTracking();
    }

    @Unique private void frontiers$sendToAllTracking()
    {
        if (this.frontiers$persistentData != null)
        {
            VectorLib.NETWORK.sendToAllTrackingEntity((LightningBolt)(Object)this, new LightningPayload(this.getId(), this.frontiers$persistentData));
        }
    }

    @Override
    public CompoundTag frontiersArtyrian$getPersistentNbt()
    {
        if (this.frontiers$persistentData == null)
        {
            this.frontiers$persistentData = new CompoundTag();
            this.frontiers$persistentData.putBoolean(LightningPersistentNBT.CHANNELED, false);
        }
        return this.frontiers$persistentData;
    }

    @Override
    public void frontiersArtyrian$syncNbt(CompoundTag nbt)
    {
        this.frontiers$persistentData = nbt;
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void frnt$customNBTRead(CompoundTag nbt, CallbackInfo ci)
    {
        if (nbt.contains("FrontiersPersistentUserdata", Tag.TAG_COMPOUND))
        {
            this.frontiers$persistentData = nbt.getCompound("FrontiersPersistentUserdata");
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void frnt$customNBTWrite(CompoundTag nbt, CallbackInfo ci)
    {
        if (this.frontiers$persistentData != null)
        {
            nbt.put("FrontiersPersistentUserdata", frontiers$persistentData);
        }
    }

    @Inject(method = "powerLightningRod", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/LightningRodBlock;onLightningStrike(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"))
    private void brewCheck(CallbackInfo ci)
    {
        Level world = this.level();
        Direction rod_direction = world.getBlockState(this.getStrikePosition()).getValue(BlockStateProperties.FACING);

        BlockPos blockBelow = this.getStrikePosition().relative(rod_direction.getOpposite());
        BlockState blockState = world.getBlockState(blockBelow);
        boolean isRotatedUp = (rod_direction == Direction.UP);

        boolean isNotChanneled = (!this.frontiers_1_21x$isChanneled());

        for (int i = 0; i < 15; i++)
        {
            if (
                    blockState.is(Blocks.LIGHTNING_ROD)
                    && blockState.getValue(BlockStateProperties.FACING) == rod_direction
                    && blockState.getValue(ModBlockProperties.ROD_CONNECTED)
            )
            {
                ((LightningRodBlock) blockState.getBlock()).onLightningStrike(blockState, world, blockBelow);
                blockBelow = blockBelow.relative(rod_direction.getOpposite());
                blockState = world.getBlockState(blockBelow);
            }
            else break;
        }

        if (blockState.is(Blocks.BREWING_STAND) && blockState.hasBlockEntity() && isRotatedUp && isNotChanneled)
        {
            BlockEntity entity = world.getBlockEntity(blockBelow);

            if (entity != null)
            {
                ((BrewMixInterface)entity).frontiers_1_21x$craftLightning(entity.getLevel(), blockBelow, ((BrewingStandBlockEntity)entity).getItems());

                ServerLevel serverWorld = (ServerLevel)world;
                serverWorld.sendParticles(
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
                serverWorld.sendParticles(
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
                serverWorld.sendParticles(
                        ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS,
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

    @Inject(method = "setCause", at = @At("TAIL"))
    private void lazyChannelerSet(ServerPlayer channeler, CallbackInfo ci)
    {
        this.frontiers_1_21x$setChanneled((channeler != null));
    }
}
