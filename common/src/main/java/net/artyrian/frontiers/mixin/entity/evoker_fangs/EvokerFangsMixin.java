package net.artyrian.frontiers.mixin.entity.evoker_fangs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.EvoFangsIntf;
import net.artyrian.frontiers.reg.property.FRDamageType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(EvokerFangs.class)
public abstract class EvokerFangsMixin extends EntityMixin implements EvoFangsIntf, VectorSyncable
{
    @Shadow @Nullable public abstract LivingEntity getOwner();

    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((EvokerFangs)(Object)this, NBTSync.EVOFANGS$ID, true, (nbt) -> {
        nbt.putBoolean(NBTSync.EVOFANGS$FRIENDLY, false);
        nbt.putBoolean(NBTSync.EVOFANGS$GATOR, false);
    });

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Override public boolean frontiers_1_21x$isFriendly() { return this.vectorLib$netSync.getBool(NBTSync.EVOFANGS$FRIENDLY, false); }
    @Override public void frontiers_1_21x$setFriendly(boolean value) { this.vectorLib$netSync.syncBool(NBTSync.EVOFANGS$FRIENDLY, value, false); }

    @Override public boolean frontiers_1_21x$isGator() { return this.vectorLib$netSync.getBool(NBTSync.EVOFANGS$GATOR, false); }
    @Override public void frontiers_1_21x$setGator(boolean value) { this.vectorLib$netSync.syncBool(NBTSync.EVOFANGS$GATOR, value, false); }

    // As a part of a reassessment of what I want mana in Frontiers to be, this code is no longer necessary
    //@ModifyExpressionValue(method = "dealDamageTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isAlliedTo(Lnet/minecraft/world/entity/Entity;)Z"))
    //private boolean alsoCheckPet(boolean original, @Local(argsOnly = true) LivingEntity target)
    //{
    //    if (this.frontiers_1_21x$isFriendly())
    //    {
    //        LivingEntity lazy_cap_idc = this.getOwner();
    //        // Horrible implementation for capturing tameable ocelot, but like what else can you really do lol
    //        if (lazy_cap_idc != null &&
    //                (target instanceof TamableAnimal && ((TamableAnimal)target).isOwnedBy(lazy_cap_idc)) ||
    //                (target instanceof Ocelot ocelot && ((OcelotMixIntf)ocelot).frontiers$isOwner(lazy_cap_idc))
    //        )
    //        {
    //            return true;
    //        }
    //    }
    //    return original;
    //}

    @Inject(method = "dealDamageTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/EvokerFangs;damageSources()Lnet/minecraft/world/damagesource/DamageSources;", shift = At.Shift.AFTER))
    private void frontiers$setEntityDamagedByPlayer(LivingEntity target, CallbackInfo ci)
    {
        if (this.frontiers_1_21x$isFriendly() && this.getOwner() instanceof Player player) target.setLastHurtByPlayer(player);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.readNetSyncFromNBT(nbt); }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.saveToNBT(nbt); }

    @ModifyExpressionValue(method = "dealDamageTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;magic()Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource changeDmgTypeMagic(DamageSource value) { return FRDamageType.of(this.level(), FRDamageType.EVOKER_FANGS); }
    @ModifyVariable(method = "dealDamageTo", at = @At(value = "STORE"))
    private DamageSource changeDmgTypeIndirect(DamageSource value) { return FRDamageType.of(this.level(), FRDamageType.EVOKER_FANGS); }
}
