package net.artyrian.frontiers.mixin.entity.chicken;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.definition.entity.ai.chicken.ChickenMateGoal;
import net.artyrian.frontiers.definition.entity.passive.GoldenChickenEntity;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends AnimalEntityMixin implements VectorSyncable
{
    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((Chicken)(Object)this, NBTSync.CHICKEN$ID, false, (nbt) -> {
        nbt.putBoolean(NBTSync.CHICKEN$EGG, false);
    });

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Unique public boolean frontiers$getGoldenEgg() { return this.vectorLib$netSync.getBool(NBTSync.CHICKEN$EGG, false); }
    @Unique public void frontiers$setGoldenEgg(boolean bool) { this.vectorLib$netSync.syncBool(NBTSync.CHICKEN$EGG, bool, false); }

    @WrapOperation(method = "aiStep", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/animal/Chicken;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;")
    )
    public ItemEntity eggDropIntercept(Chicken chicken, ItemLike itemConvertible, Operation<ItemEntity> original)
    {
        if (frontiers$getGoldenEgg())
        {
            frontiers$setGoldenEgg(false);
            if (this.level().getRandom().nextIntBetweenInclusive(0, 3) == 0) return original.call(chicken, ModItem.GOLDEN_EGG.get());
        }
        return original.call(chicken, itemConvertible);
    }

    @Inject(method = "isFood", at = @At("HEAD"), cancellable = true)
    public void isAbleToMakeGoldenEgg(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        if (stack.is(ModTags.Items.GOLDEN_CHICKEN_FOOD))
        {
            Chicken self = ((Chicken)(Object)this);
            int i = self.getAge();
            boolean baby = self.isBaby();
            if (i == 0 && !baby) frontiers$setGoldenEgg(true);
            cir.setReturnValue(true);
        }
    }

    /** Makes chickens able to mate with Golden Chickens */
    @ModifyArgs(
            method = "registerGoals",
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/goal/BreedGoal;<init>(Lnet/minecraft/world/entity/animal/Animal;D)V")),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V",
                    ordinal = 0)
    )
    private void frontiersGoldChickenMateGoal(Args args)
    {
        args.set(1, new ChickenMateGoal((Chicken)(Object)this, GoldenChickenEntity.class, 1.0));
    }

    @Override
    public void frontiersCanBreedWithHook(Animal other, CallbackInfoReturnable<Boolean> cir)
    {
        Chicken self = (Chicken)(Object)this;
        boolean returnVal;
        if (other == self)
        {
            returnVal = false;
        }
        else if (!(other instanceof Chicken) && !(other instanceof GoldenChickenEntity))
        {
            returnVal = false;
        }
        else
        {
            returnVal = this.isInLove() && other.isInLove();
        }
        cir.setReturnValue(returnVal);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void frontiers$readAddSaveDat(CompoundTag compound, CallbackInfo ci) { this.vectorLib$netSync.readNetSyncFromNBT(compound); }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void frontiers$addAddSaveDat(CompoundTag compound, CallbackInfo ci) { this.vectorLib$netSync.saveToNBT(compound); }
}