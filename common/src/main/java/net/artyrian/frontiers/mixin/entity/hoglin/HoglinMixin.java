package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Debug(export = true)
@Mixin(Hoglin.class)
public abstract class HoglinMixin extends EntityMixin implements HoglinIntf, VectorSyncable
{
    @Shadow protected abstract boolean isImmuneToZombification();
    @Shadow public abstract Brain<Hoglin> getBrain();
    @Shadow public abstract boolean canFallInLove();
    @Shadow public abstract void readAdditionalSaveData(CompoundTag compound);
    @Shadow public abstract void addAdditionalSaveData(CompoundTag compound);

    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((Hoglin)(Object)this, NBTSync.HOGLIN$ID, true, (nbt) -> {
        nbt.putBoolean(NBTSync.HOGLIN$TRUFFLE, false);
    });

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Override public boolean frontiers_1_21x$isTruffled() { return this.vectorLib$netSync.getBool(NBTSync.HOGLIN$TRUFFLE, false); }
    @Override public void frontiers_1_21x$setTruffled(boolean value) { this.vectorLib$netSync.syncBool(NBTSync.HOGLIN$TRUFFLE, value, false); }

    @Override
    public boolean frontiers$isImmuneToZombification() { return this.isImmuneToZombification(); }

    @Inject(method = "canFallInLove", at = @At("RETURN"), cancellable = true)
    public void frontiers$canEatTry(CallbackInfoReturnable<Boolean> cir)
    {
        boolean cirReturn = cir.getReturnValue();
        boolean isPacified = this.getBrain().hasMemoryValue(MemoryModuleType.PACIFIED) || frontiers_1_21x$isTruffled();

        Optional<BlockPos> optional = this.getBrain().getMemory(MemoryModuleType.NEAREST_REPELLENT);
        boolean near_fungi = optional.isPresent() && (optional.get()).closerToCenterThan(this.position(), 8.0);

        if (!cirReturn && isPacified) cir.setReturnValue(!near_fungi);
        else cir.setReturnValue(cirReturn);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.readNetSyncFromNBT(nbt); }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.saveToNBT(nbt); }

    @Inject(method = "getBreedOffspring", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void frontiers$createChild(ServerLevel world, AgeableMob entity, CallbackInfoReturnable<AgeableMob> cir, Hoglin hoglinEntity)
    {
        hoglinEntity.setImmuneToZombification(isImmuneToZombification());
        ((HoglinIntf)hoglinEntity).frontiers_1_21x$setTruffled(frontiers_1_21x$isTruffled());
    }
}
