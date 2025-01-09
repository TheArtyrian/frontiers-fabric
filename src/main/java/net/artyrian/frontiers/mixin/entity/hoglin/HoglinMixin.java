package net.artyrian.frontiers.mixin.entity.hoglin;

import net.artyrian.frontiers.mixin_interfaces.HoglinMixInterface;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
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
@Mixin(HoglinEntity.class)
public abstract class HoglinMixin extends EntityMixin implements HoglinMixInterface
{
    // Uniques & Shadows
    @Unique private static final TrackedData<Boolean> TRUFFLED = DataTracker.registerData(HoglinEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override public boolean isTruffled()
    {
        return this.getDataTracker().get(TRUFFLED);
    }
    @Override public void setTruffled(boolean value)
    {
        this.getDataTracker().set(TRUFFLED, value);
    }

    @Shadow protected abstract boolean isImmuneToZombification();
    @Shadow public abstract Brain<HoglinEntity> getBrain();
    @Shadow public abstract boolean canEat();

    // Injects
    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(TRUFFLED, false);
    }

    @Inject(method = "canEat", at = @At("RETURN"), cancellable = true)
    public void canEat(CallbackInfoReturnable<Boolean> cir)
    {
        boolean cirReturn = cir.getReturnValue();
        boolean isPacified = this.getBrain().hasMemoryModule(MemoryModuleType.PACIFIED) || isTruffled();

        Optional<BlockPos> optional = this.getBrain().getOptionalRegisteredMemory(MemoryModuleType.NEAREST_REPELLENT);
        boolean near_fungi = optional.isPresent() && ((BlockPos)optional.get()).isWithinDistance(this.getPos(), 8.0);

        if (!cirReturn && isPacified) cir.setReturnValue(!near_fungi);
        else cir.setReturnValue(cirReturn);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("BredWithTruffle", isTruffled());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci)
    {
        setTruffled(nbt.getBoolean("BredWithTruffle"));
    }

    @Inject(method = "createChild", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void createChild(ServerWorld world, PassiveEntity entity, CallbackInfoReturnable<PassiveEntity> cir, HoglinEntity hoglinEntity)
    {
        hoglinEntity.setImmuneToZombification(isImmuneToZombification());

        NbtCompound append = new NbtCompound();
        hoglinEntity.writeCustomDataToNbt(append);
        append.putBoolean("BredWithTruffle", isTruffled());
        hoglinEntity.readCustomDataFromNbt(append);
    }
}
