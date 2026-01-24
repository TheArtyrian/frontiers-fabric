package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
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
    @Unique
    private CompoundTag frontiers$persistentData;

    @Override public boolean frontiers_1_21x$isTruffled()
    {
        return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.HOGLIN_IS_TRUFFLED, ModAttachmentTypes.HOGLIN_IS_TRUFFLED.initializer());
    }
    @Override public void frontiers_1_21x$setTruffled(boolean value)
    {
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.HOGLIN_IS_TRUFFLED, value);
    }

    @Shadow protected abstract boolean isImmuneToZombification();
    @Shadow public abstract Brain<Hoglin> getBrain();
    @Shadow public abstract boolean canEat();

    // Injects
    //@Inject(method = "initDataTracker", at = @At("TAIL"))
    //protected void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
    //    builder.add(TRUFFLED2, false);
    //}

    @Inject(method = "canEat", at = @At("RETURN"), cancellable = true)
    public void canEat(CallbackInfoReturnable<Boolean> cir)
    {
        boolean cirReturn = cir.getReturnValue();
        boolean isPacified = this.getBrain().hasMemoryValue(MemoryModuleType.PACIFIED) || frontiers_1_21x$isTruffled();

        Optional<BlockPos> optional = this.getBrain().getMemory(MemoryModuleType.NEAREST_REPELLENT);
        boolean near_fungi = optional.isPresent() && ((BlockPos)optional.get()).closerToCenterThan(this.getPos(), 8.0);

        if (!cirReturn && isPacified) cir.setReturnValue(!near_fungi);
        else cir.setReturnValue(cirReturn);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci)
    {
        nbt.putBoolean("BredWithTruffle", frontiers_1_21x$isTruffled());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci)
    {
        frontiers_1_21x$setTruffled(nbt.getBoolean("BredWithTruffle"));
    }

    @Inject(method = "createChild", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void createChild(ServerLevel world, AgeableMob entity, CallbackInfoReturnable<AgeableMob> cir, Hoglin hoglinEntity)
    {
        hoglinEntity.setImmuneToZombification(isImmuneToZombification());

        CompoundTag append = new CompoundTag();
        hoglinEntity.addAdditionalSaveData(append);
        append.putBoolean("BredWithTruffle", frontiers_1_21x$isTruffled());
        hoglinEntity.readAdditionalSaveData(append);
    }
}
