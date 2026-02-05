package net.artyrian.frontiers.mixin.entity.lightning;

import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.BrewMixInterface;
import net.artyrian.frontiers.mixin_intf.LightningIntf;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
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
public abstract class LightningMixin extends EntityMixin implements LightningIntf, VectorSyncable
{
    @Shadow protected abstract BlockPos getStrikePosition();
    @Shadow @Nullable private ServerPlayer cause;

    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((LightningBolt)(Object)this, NBTSync.LIGHTNING$ID, true, (nbt) -> {
        nbt.putBoolean(NBTSync.LIGHTNING$CHANNELED, false);
    });

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Override public boolean frontiers_1_21x$isChanneled() { return this.vectorLib$netSync.getBool(NBTSync.LIGHTNING$CHANNELED, false); }
    @Override public void frontiers_1_21x$setChanneled(boolean value) { this.vectorLib$netSync.syncBool(NBTSync.LIGHTNING$CHANNELED, value, false); }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void frnt$customNBTRead(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.readNetSyncFromNBT(nbt); }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void frnt$customNBTWrite(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.saveToNBT(nbt); }

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
