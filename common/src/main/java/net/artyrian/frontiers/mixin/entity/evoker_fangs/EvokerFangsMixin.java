package net.artyrian.frontiers.mixin.entity.evoker_fangs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.misc.ModDamageType;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.FangsMixInterface;
import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.projectile.EvokerFangs;
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
public abstract class EvokerFangsMixin extends EntityMixin implements FangsMixInterface
{
    @Shadow public abstract @Nullable LivingEntity getOwner();

    @Unique private final Boolean SUMMONED = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.EVOKERFANGS_IS_FRIENDLY, ModAttachmentTypes.EVOKERFANGS_IS_FRIENDLY.initializer());

    @Override
    public void frontiers_1_21x$setFriendly(boolean value) { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.EVOKERFANGS_IS_FRIENDLY, value); }
    @Override
    public boolean frontiers_1_21x$isFriendly() { return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.EVOKERFANGS_IS_FRIENDLY, ModAttachmentTypes.EVOKERFANGS_IS_FRIENDLY.initializer()); }

    @Override
    public void frontiers_1_21x$setGator(boolean value) { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.EVOKERFANGS_GATOR, value); }
    @Override
    public boolean frontiers_1_21x$isGator() { return ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.EVOKERFANGS_GATOR, ModAttachmentTypes.EVOKERFANGS_GATOR.initializer()); }

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isTeammate(Lnet/minecraft/entity/Entity;)Z"))
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

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void customNBTRead(CompoundTag nbt, CallbackInfo ci)
    {
        if (nbt.contains("IsFriendly", Tag.TAG_BYTE))
        {
            this.frontiers_1_21x$setFriendly(nbt.getBoolean("IsFriendly"));
        }
        if (nbt.contains("UseGatorFrontiersTex", Tag.TAG_BYTE))
        {
            this.frontiers_1_21x$setGator(nbt.getBoolean("UseGatorFrontiersTex"));
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void customNBTWrite(CompoundTag nbt, CallbackInfo ci)
    {
        nbt.putBoolean("IsFriendly", this.frontiers_1_21x$isFriendly());
        nbt.putBoolean("UseGatorFrontiersTex", this.frontiers_1_21x$isGator());
    }

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSources;magic()Lnet/minecraft/entity/damage/DamageSource;"))
    private DamageSource changeDmgTypeMagic(DamageSource value) { return ModDamageType.of(this.getWorld(), ModDamageType.EVOKER_FANGS); }
    @ModifyVariable(method = "damage", at = @At(value = "STORE"))
    private DamageSource changeDmgTypeIndirect(DamageSource value) { return ModDamageType.of(this.getWorld(), ModDamageType.EVOKER_FANGS); }
}
