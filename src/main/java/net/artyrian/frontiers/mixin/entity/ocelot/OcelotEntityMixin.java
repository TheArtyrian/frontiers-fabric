package net.artyrian.frontiers.mixin.entity.ocelot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.entity.ai.OcelotEscapeDangerGoal;
import net.artyrian.frontiers.entity.ai.OcelotFollowOwnerGoal;
import net.artyrian.frontiers.entity.ai.OcelotSitGoal;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.OcelotMixIntf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.UUID;

/** This mixin aims to make Ocelots tameable again, in an effort to restore them from simply ambient mobs to where they were before. <p>
 * Due to mixin limitations, it manually re-implements tamability in its own format. Spaghetti ahead. You have been warned.
 * <p>Also floppa mention */
@Debug(export = true)
@Mixin(OcelotEntity.class)
public abstract class OcelotEntityMixin extends AnimalEntityMixin implements OcelotMixIntf
{
    @Shadow public abstract boolean isBreedingItem(ItemStack stack);
    @Shadow protected abstract void setTrusting(boolean trusting);

    @Unique protected final byte TAMEABLE_FLAGS =
            ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS.initializer());
    @Unique protected final Optional<UUID> OWNER_UUID =
            ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.OCELOT_OWNER_UUID, ModAttachmentTypes.OCELOT_OWNER_UUID.initializer());
    @Unique protected final int COLLAR_COLOR =
            ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.OCELOT_COLLAR_COLOR, ModAttachmentTypes.OCELOT_COLLAR_COLOR.initializer());

    @Unique private boolean frontiersSitting;

    @Unique
    protected void frontiersUpdateAttrb()
    {
        // unused atm
    }

    @Unique
    private void frontiersTryTeleportNear(BlockPos pos)
    {
        for (int i = 0; i < 10; i++)
        {
            int j = this.random.nextBetween(-3, 3);
            int k = this.random.nextBetween(-3, 3);
            if (Math.abs(j) >= 2 || Math.abs(k) >= 2)
            {
                int l = this.random.nextBetween(-1, 1);
                if (this.frontiersTryTeleportTo(pos.getX() + j, pos.getY() + l, pos.getZ() + k))
                {
                    return;
                }
            }
        }
    }
    @Unique
    private boolean frontiersTryTeleportTo(int x, int y, int z)
    {
        if (!this.frontiersCanTeleportTo(new BlockPos(x, y, z)))
        {
            return false;
        }
        else
        {
            this.refreshPositionAndAngles((double)x + 0.5, (double)y, (double)z + 0.5, this.getYaw(), this.getPitch());
            this.navigation.stop();
            return true;
        }
    }
    @Unique
    private boolean frontiersCanTeleportTo(BlockPos pos)
    {
        PathNodeType pathNodeType = LandPathNodeMaker.getLandNodeType((OcelotEntity)(Object)this, pos);
        if (pathNodeType != PathNodeType.WALKABLE)
        {
            return false;
        }
        else
        {
            BlockState blockState = this.getWorld().getBlockState(pos.down());
            if (!this.canTeleportOntoLeaves() && blockState.getBlock() instanceof LeavesBlock)
            {
                return false;
            }
            else
            {
                BlockPos blockPos = pos.subtract(this.getBlockPos());
                return this.getWorld().isSpaceEmpty((OcelotEntity)(Object)this, this.getBoundingBox().offset(blockPos));
            }
        }
    }
    @Unique
    protected boolean canTeleportOntoLeaves()
    {
        return false;
    }

    @Override
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {
        if (!this.getWorld().isClient && this.getWorld().getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES) && this.frontiers$getOwner() instanceof ServerPlayerEntity)
        {
            this.frontiers$getOwner().sendMessage(this.getDamageTracker().getDeathMessage());
        }
        super.onDeathHook(damageSource, ci);
    }

    @Override
    public final boolean frontiers$cannotFollowOwner()
    {
        return this.frontiers$isSitting() || this.hasVehicle() || this.getLeashData() != null || this.frontiers$getOwner() != null && this.frontiers$getOwner().isSpectator();
    }
    @Override
    public boolean frontiers$shouldTryTeleportToOwner()
    {
        LivingEntity livingEntity = this.frontiers$getOwner();
        return livingEntity != null && this.squaredDistanceTo(this.frontiers$getOwner()) >= 144.0;
    }
    @Override
    public void frontiers$tryTeleportToOwner()
    {
        LivingEntity livingEntity = this.frontiers$getOwner();
        if (livingEntity != null)
        {
            this.frontiersTryTeleportNear(livingEntity.getBlockPos());
        }
    }

    @Override
    public void frontiers$setOcelotOwnerID(@Nullable UUID uuid) { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.OCELOT_OWNER_UUID, Optional.ofNullable(uuid)); }
    @Nullable
    @Override
    public UUID frontiers$getOcelotOwnerID()
    {
        Optional<UUID> uuidOpt = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.OCELOT_OWNER_UUID, ModAttachmentTypes.OCELOT_OWNER_UUID.initializer());
        return uuidOpt.orElse(null);
    }

    @Override
    public LivingEntity frontiers$getOwner()
    {
        UUID uUID = this.frontiers$getOcelotOwnerID();
        return (uUID == null) ? null : this.getWorld().getPlayerByUuid(uUID);
    }
    @Override
    public void frontiers$setOwner(PlayerEntity player)
    {
        this.frontiers$setTamed(true, true);
        this.frontiers$setOcelotOwnerID(player.getUuid());
        if (player instanceof ServerPlayerEntity serverPlayerEntity)
        {
            Criteria.TAME_ANIMAL.trigger(serverPlayerEntity, (OcelotEntity)(Object)this);
        }
    }
    @Override
    public boolean frontiers$isOwner(LivingEntity player)
    {
        return player == this.frontiers$getOwner();
    }

    @Override
    public boolean frontiers$isTamed()
    {
        byte b = ((AttachmentTarget)this)
                .getAttachedOrCreate(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS.initializer());
        return (b & 4) != 0;
    }
    @Override
    public void frontiers$setTamed(boolean tamed, boolean updateAttributes)
    {
        byte b = ((AttachmentTarget)this)
                .getAttachedOrCreate(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS.initializer());

        if (tamed) ((AttachmentTarget)this).setAttached(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, (byte)(b | 4));
        else ((AttachmentTarget)this).setAttached(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, (byte)(b & -5));

        if (updateAttributes) this.frontiersUpdateAttrb();
    }

    @Override
    public DyeColor frontiers$getCollarColor()
    {
        int colorid = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.OCELOT_COLLAR_COLOR, ModAttachmentTypes.OCELOT_COLLAR_COLOR.initializer());
        return DyeColor.byId(colorid);
    }
    @Override
    public void frontiers$setCollarColor(DyeColor color)
    {
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.OCELOT_COLLAR_COLOR, color.getId());
    }

    @Override
    public boolean frontiers$isSitting()
    {
        return this.frontiersSitting;
    }
    @Override
    public void frontiers$setSitting(boolean sitting)
    {
        this.frontiersSitting = sitting;
    }

    @Override
    public boolean frontiers$isInSittingPose()
    {
        byte b = ((AttachmentTarget)this)
                .getAttachedOrCreate(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS.initializer());
        return (b & 1) != 0;
    }
    @Override
    public void frontiers$setInSittingPose(boolean sitting)
    {
        byte b = ((AttachmentTarget)this)
                .getAttachedOrCreate(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS.initializer());

        if (sitting) ((AttachmentTarget)this).setAttached(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, (byte)(b | 1));
        else ((AttachmentTarget)this).setAttached(ModAttachmentTypes.OCELOT_TAMEABLE_FLAGS, (byte)(b & -2));
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    private void appendFrontiersAIGoals(CallbackInfo ci)
    {
        this.goalSelector.add(2, new OcelotSitGoal((OcelotEntity)(Object)this));
        this.goalSelector.add(1, new OcelotEscapeDangerGoal((OcelotEntity)(Object)this, 1.5));
        this.goalSelector.add(6, new OcelotFollowOwnerGoal((OcelotEntity)(Object)this, 1.5, 10.0F, 5.0F));
    }

    @ModifyExpressionValue(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/OcelotEntity$OcelotTemptGoal;isActive()Z"))
    private boolean alsoCheckOnUserCreative(boolean original, @Local(argsOnly = true) PlayerEntity player)
    {
        return original || player.isCreative();
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/OcelotEntity;setTrusting(Z)V", shift = At.Shift.AFTER))
    private void setTameAsWell(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir)
    {
        this.frontiers$setOwner(player);
        this.frontiers$setSitting(true);
        this.setPersistent();
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void customReadNBTStuff(NbtCompound nbt, CallbackInfo ci)
    {
        UUID ownerID;
        if (nbt.containsUuid("Owner"))
        {
            ownerID = nbt.getUuid("Owner");
        }
        else
        {
            String string = nbt.getString("Owner");
            ownerID = ServerConfigHandler.getPlayerUuidByName(this.getServer(), string);
        }

        if (ownerID != null)
        {
            try
            {
                this.frontiers$setOcelotOwnerID(ownerID);
                this.frontiers$setTamed(true, false);
            }
            catch (Throwable thrower)
            {
                this.frontiers$setTamed(false, true);
            }
        }

        this.frontiersSitting = nbt.getBoolean("Sitting");
        this.frontiers$setInSittingPose(this.frontiersSitting);
    }
    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void customWriteNBTStuff(NbtCompound nbt, CallbackInfo ci)
    {
        if (this.frontiers$getOcelotOwnerID() != null)
        {
            nbt.putUuid("Owner", this.frontiers$getOcelotOwnerID());
        }

        nbt.putBoolean("Sitting", this.frontiersSitting);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void tameEventChecker(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir)
    {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (this.frontiers$isTamed())
        {
            if (this.frontiers$isOwner(player))
            {
                if (item instanceof DyeItem dyeItem)
                {
                    DyeColor dyeColor = dyeItem.getColor();
                    if (dyeColor != this.frontiers$getCollarColor())
                    {
                        if (!this.getWorld().isClient())
                        {
                            this.frontiers$setCollarColor(dyeColor);
                            itemStack.decrementUnlessCreative(1, player);
                            this.setPersistent();
                        }

                        cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
                    }
                }
                else if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth())
                {
                    if (!this.getWorld().isClient())
                    {
                        this.eat(player, hand, itemStack);
                        FoodComponent foodComponent = itemStack.get(DataComponentTypes.FOOD);
                        this.heal(foodComponent != null ? (float)foodComponent.nutrition() : 1.0F);
                    }

                    cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
                }
                else
                {
                    ActionResult actionResult = super.interactMob(player, hand);
                    if (!actionResult.isAccepted())
                    {
                        this.frontiers$setSitting(!this.frontiers$isSitting());
                        cir.setReturnValue(ActionResult.success(this.getWorld().isClient()));
                    }
                    else
                    {
                        cir.setReturnValue(actionResult);
                    }
                }
            }
        }
    }

    @ModifyReturnValue(
            method = "createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/PassiveEntity;)Lnet/minecraft/entity/passive/OcelotEntity;",
            at = @At("RETURN")
    )
    private OcelotEntity createChildWithNewAttribs(OcelotEntity original, @Local(argsOnly = true) PassiveEntity passiveEntity)
    {
        if (original != null && passiveEntity instanceof OcelotEntity ocelotEntity2)
        {
            if (this.frontiers$isTamed())
            {
                ((OcelotMixIntf)original).frontiers$setOcelotOwnerID(this.frontiers$getOcelotOwnerID());
                ((OcelotMixIntf)original).frontiers$setTamed(true, true);
                original.setTrusting(true);

                if (this.random.nextBoolean())
                {
                    ((OcelotMixIntf)original).frontiers$setCollarColor(this.frontiers$getCollarColor());
                }
                else
                {
                    ((OcelotMixIntf)original).frontiers$setCollarColor(((OcelotMixIntf)ocelotEntity2).frontiers$getCollarColor());
                }
            }
        }
        return original;
    }
}