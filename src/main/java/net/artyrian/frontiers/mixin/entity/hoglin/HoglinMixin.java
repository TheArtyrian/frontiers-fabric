package net.artyrian.frontiers.mixin.entity.hoglin;

import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HoglinEntity.class)
public abstract class HoglinMixin
{
    // Uniques & Shadows
    @Unique private boolean truffled;

    @Unique public boolean isTruffled() { return truffled; }

    @Shadow protected abstract boolean isImmuneToZombification();
    @Shadow public abstract Brain<HoglinEntity> getBrain();

    @Shadow public abstract boolean canEat();

    // Injects
    @Inject(method = "canEat", at = @At("RETURN"), cancellable = true)
    public void canEat(CallbackInfoReturnable<Boolean> cir)
    {
        boolean cirReturn = cir.getReturnValue();
        boolean isPacified = this.getBrain().hasMemoryModule(MemoryModuleType.PACIFIED) || truffled;

        if (!cirReturn && isPacified) cir.setReturnValue(true);
        else cir.setReturnValue(cirReturn);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci)
    {
        if (this.truffled)
        {
            nbt.putBoolean("BredWithTruffle", true);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci)
    {
        this.truffled = nbt.getBoolean("BredWithTruffle");
    }

    @Inject(method = "createChild", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void createChild(ServerWorld world, PassiveEntity entity, CallbackInfoReturnable<PassiveEntity> cir, HoglinEntity hoglinEntity)
    {
        hoglinEntity.setImmuneToZombification(isImmuneToZombification());

        NbtCompound append = new NbtCompound();
        hoglinEntity.writeCustomDataToNbt(append);
        append.putBoolean("BredWithTruffle", truffled);
        hoglinEntity.readCustomDataFromNbt(append);
    }
}
