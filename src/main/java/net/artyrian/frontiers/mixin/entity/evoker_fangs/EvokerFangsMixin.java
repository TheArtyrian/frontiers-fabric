package net.artyrian.frontiers.mixin.entity.evoker_fangs;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_interfaces.FangsMixInterface;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(EvokerFangsEntity.class)
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
            if (lazy_cap_idc != null && target instanceof TameableEntity && ((TameableEntity) target).isOwner(lazy_cap_idc))
            {
                return true;
            }
        }
        return original;
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void customNBTRead(NbtCompound nbt, CallbackInfo ci)
    {
        if (nbt.contains("IsFriendly", NbtElement.BYTE_TYPE))
        {
            this.frontiers_1_21x$setFriendly(nbt.getBoolean("IsFriendly"));
        }
        if (nbt.contains("UseGatorFrontiersTex", NbtElement.BYTE_TYPE))
        {
            this.frontiers_1_21x$setGator(nbt.getBoolean("UseGatorFrontiersTex"));
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void customNBTWrite(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("IsFriendly", this.frontiers_1_21x$isFriendly());
        nbt.putBoolean("UseGatorFrontiersTex", this.frontiers_1_21x$isGator());
    }
}
