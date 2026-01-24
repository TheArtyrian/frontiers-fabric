package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.definition.data.nbt_sync.HoglinPersistentNBT;
import net.artyrian.frontiers.definition.networking.payload.attachment.HoglinPayload;
import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.vertisoft.vectorlib.VectorLib;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Debug(export = true)
@Mixin(Hoglin.class)
public abstract class HoglinMixin extends EntityMixin implements HoglinIntf
{
    @Shadow protected abstract boolean isImmuneToZombification();
    @Shadow public abstract Brain<Hoglin> getBrain();
    @Shadow public abstract boolean canFallInLove();

    @Unique private CompoundTag frontiers$persistentData;

    @Override public boolean frontiers_1_21x$isTruffled()
    {
        if (this.frontiers$persistentData != null && this.frontiers$persistentData.contains(HoglinPersistentNBT.TRUFFLE))
        {
            return this.frontiers$persistentData.getBoolean(HoglinPersistentNBT.TRUFFLE);
        }
        else return false;
    }
    @Override public void frontiers_1_21x$setTruffled(boolean value)
    {
        HoglinPersistentNBT.setTruffled(this, value);
        frontiers$sendToAllTracking();
    }

    @Unique private void frontiers$sendToAllTracking()
    {
        if (this.frontiers$persistentData != null)
        {
            VectorLib.NETWORK.sendToAllTrackingEntity((Hoglin)(Object)this, new HoglinPayload(this.getId(), this.frontiers$persistentData));
        }
    }

    @Override
    public CompoundTag frontiersArtyrian$getPersistentNbt()
    {
        if (this.frontiers$persistentData == null)
        {
            this.frontiers$persistentData = new CompoundTag();
            this.frontiers$persistentData.putBoolean(HoglinPersistentNBT.TRUFFLE, false);
        }
        return this.frontiers$persistentData;
    }

    @Override
    public void frontiersArtyrian$syncNbt(CompoundTag nbt)
    {
        this.frontiers$persistentData = nbt;
    }

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
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci)
    {
        if (nbt.contains("FrontiersPersistentUserdata", Tag.TAG_COMPOUND))
        {
            this.frontiers$persistentData = nbt.getCompound("FrontiersPersistentUserdata");
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci)
    {
        if (this.frontiers$persistentData != null)
        {
            nbt.put("FrontiersPersistentUserdata", frontiers$persistentData);
        }
    }

    @Inject(method = "getBreedOffspring", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void frontiers$createChild(ServerLevel world, AgeableMob entity, CallbackInfoReturnable<AgeableMob> cir, Hoglin hoglinEntity)
    {
        hoglinEntity.setImmuneToZombification(isImmuneToZombification());

        CompoundTag append = new CompoundTag();
        hoglinEntity.addAdditionalSaveData(append);
        append.putBoolean("BredWithTruffle", frontiers_1_21x$isTruffled());
        hoglinEntity.readAdditionalSaveData(append);
    }
}
