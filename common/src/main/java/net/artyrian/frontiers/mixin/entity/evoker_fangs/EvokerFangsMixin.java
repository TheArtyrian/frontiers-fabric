package net.artyrian.frontiers.mixin.entity.evoker_fangs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.data.nbt_sync.EvokerFangsPersistentNBT;
import net.artyrian.frontiers.definition.networking.payload.attachment.EvoFangsPayload;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.EvoFangsIntf;
import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.artyrian.frontiers.reg.misc.ModDamageType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.vertisoft.vectorlib.VectorLib;
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
public abstract class EvokerFangsMixin extends EntityMixin implements EvoFangsIntf
{
    @Shadow @Nullable public abstract LivingEntity getOwner();
    @Unique private CompoundTag frontiers$persistentData;

    @Override
    public boolean frontiers_1_21x$isFriendly()
    {
        if (this.frontiers$persistentData != null && this.frontiers$persistentData.contains(EvokerFangsPersistentNBT.FRIENDLY))
        {
            return this.frontiers$persistentData.getBoolean(EvokerFangsPersistentNBT.FRIENDLY);
        }
        else return false;
    }
    @Override
    public void frontiers_1_21x$setFriendly(boolean value)
    {
        EvokerFangsPersistentNBT.setFriendly(this, value);
        frontiers$sendToAllTracking();
    }

    @Override
    public boolean frontiers_1_21x$isGator()
    {
        if (this.frontiers$persistentData != null && this.frontiers$persistentData.contains(EvokerFangsPersistentNBT.GATOR))
        {
            return this.frontiers$persistentData.getBoolean(EvokerFangsPersistentNBT.GATOR);
        }
        else return false;
    }
    @Override
    public void frontiers_1_21x$setGator(boolean value)
    {
        EvokerFangsPersistentNBT.setGator(this, value);
        frontiers$sendToAllTracking();
    }

    @Unique private void frontiers$sendToAllTracking()
    {
        if (this.frontiers$persistentData != null)
        {
            VectorLib.NETWORK.sendToAllTrackingEntity((EvokerFangs)(Object)this, new EvoFangsPayload(this.getId(), this.frontiers$persistentData));
        }
    }

    @Override
    public CompoundTag frontiersArtyrian$getPersistentNbt()
    {
        if (this.frontiers$persistentData == null)
        {
            this.frontiers$persistentData = new CompoundTag();
            this.frontiers$persistentData.putBoolean(EvokerFangsPersistentNBT.FRIENDLY, false);
            this.frontiers$persistentData.putBoolean(EvokerFangsPersistentNBT.GATOR, false);
        }
        return this.frontiers$persistentData;
    }

    @Override
    public void frontiersArtyrian$syncNbt(CompoundTag nbt)
    {
        this.frontiers$persistentData = nbt;
    }

    @ModifyExpressionValue(method = "dealDamageTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isAlliedTo(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean alsoCheckPet(boolean original, @Local(argsOnly = true) LivingEntity target)
    {
        if (this.frontiers_1_21x$isFriendly())
        {
            LivingEntity lazy_cap_idc = this.getOwner();
            // Horrible implementation for capturing tameable ocelot, but like what else can you really do lol
            if (lazy_cap_idc != null &&
                    (target instanceof TamableAnimal && ((TamableAnimal) target).isOwnedBy(lazy_cap_idc)) ||
                    (target instanceof Ocelot ocelot && ((OcelotMixIntf)ocelot).frontiers$isOwner(lazy_cap_idc))
            )
            {
                return true;
            }
        }
        return original;
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

    @ModifyExpressionValue(method = "dealDamageTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSources;magic()Lnet/minecraft/world/damagesource/DamageSource;"))
    private DamageSource changeDmgTypeMagic(DamageSource value) { return ModDamageType.of(this.level(), ModDamageType.EVOKER_FANGS); }
    @ModifyVariable(method = "dealDamageTo", at = @At(value = "STORE"))
    private DamageSource changeDmgTypeIndirect(DamageSource value) { return ModDamageType.of(this.level(), ModDamageType.EVOKER_FANGS); }
}
