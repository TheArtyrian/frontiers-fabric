package net.artyrian.frontiers.mixin.entity.ocelot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.entity.ai.ocelot.OcelotEscapeDangerGoal;
import net.artyrian.frontiers.entity.ai.ocelot.OcelotFollowOwnerGoal;
import net.artyrian.frontiers.entity.ai.ocelot.OcelotSitGoal;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
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
@Mixin(Ocelot.class)
public abstract class OcelotEntityMixin extends AnimalEntityMixin implements OcelotMixIntf
{
    @Shadow public abstract boolean isFood(ItemStack stack);
    @Shadow protected abstract void setTrusting(boolean trusting);

    @Unique
    private CompoundTag frontiers$persistentData;

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
            int j = this.random.nextIntBetweenInclusive(-3, 3);
            int k = this.random.nextIntBetweenInclusive(-3, 3);
            if (Math.abs(j) >= 2 || Math.abs(k) >= 2)
            {
                int l = this.random.nextIntBetweenInclusive(-1, 1);
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
        PathType pathNodeType = WalkNodeEvaluator.getPathTypeStatic((Ocelot)(Object)this, pos);
        if (pathNodeType != PathType.WALKABLE)
        {
            return false;
        }
        else
        {
            BlockState blockState = this.getWorld().getBlockState(pos.below());
            if (!this.canTeleportOntoLeaves() && blockState.getBlock() instanceof LeavesBlock)
            {
                return false;
            }
            else
            {
                BlockPos blockPos = pos.subtract(this.getBlockPos());
                return this.getWorld().noCollision((Ocelot)(Object)this, this.getBoundingBox().move(blockPos));
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
        if (!this.getWorld().isClientSide && this.getWorld().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.frontiers$getOwner() instanceof ServerPlayer)
        {
            this.frontiers$getOwner().sendSystemMessage(this.getDamageTracker().getDeathMessage());
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
            this.frontiersTryTeleportNear(livingEntity.blockPosition());
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
        return (uUID == null) ? null : this.getWorld().getPlayerByUUID(uUID);
    }
    @Override
    public void frontiers$setOwner(Player player)
    {
        this.frontiers$setTamed(true, true);
        this.frontiers$setOcelotOwnerID(player.getUUID());
        if (player instanceof ServerPlayer serverPlayerEntity)
        {
            CriteriaTriggers.TAME_ANIMAL.trigger(serverPlayerEntity, (Ocelot)(Object)this);
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
        this.goalSelector.addGoal(2, new OcelotSitGoal((Ocelot)(Object)this));
        this.goalSelector.addGoal(1, new OcelotEscapeDangerGoal((Ocelot)(Object)this, 1.5));
        this.goalSelector.addGoal(6, new OcelotFollowOwnerGoal((Ocelot)(Object)this, 1.5, 10.0F, 5.0F));

        if (Frontiers.CONFIG.doOcelotsAttackCreepers())
        {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal((Ocelot)(Object)this, Creeper.class, false));
        }
    }

    @ModifyExpressionValue(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/OcelotEntity$OcelotTemptGoal;isActive()Z"))
    private boolean alsoCheckOnUserCreative(boolean original, @Local(argsOnly = true) Player player)
    {
        return original || player.isCreative();
    }

    @Inject(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/OcelotEntity;setTrusting(Z)V", shift = At.Shift.AFTER))
    private void setTameAsWell(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
    {
        this.frontiers$setOwner(player);
        this.frontiers$setSitting(true);
        this.setPersistent();
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void customReadNBTStuff(CompoundTag nbt, CallbackInfo ci)
    {
        UUID ownerID;
        if (nbt.hasUUID("Owner"))
        {
            ownerID = nbt.getUUID("Owner");
        }
        else
        {
            String string = nbt.getString("Owner");
            ownerID = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), string);
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
    private void customWriteNBTStuff(CompoundTag nbt, CallbackInfo ci)
    {
        if (this.frontiers$getOcelotOwnerID() != null)
        {
            nbt.putUUID("Owner", this.frontiers$getOcelotOwnerID());
        }

        nbt.putBoolean("Sitting", this.frontiersSitting);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void tameEventChecker(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        Item item = itemStack.getItem();
        if (this.frontiers$isTamed())
        {
            if (this.frontiers$isOwner(player))
            {
                if (item instanceof DyeItem dyeItem)
                {
                    DyeColor dyeColor = dyeItem.getDyeColor();
                    if (dyeColor != this.frontiers$getCollarColor())
                    {
                        if (!this.getWorld().isClientSide())
                        {
                            this.frontiers$setCollarColor(dyeColor);
                            itemStack.consume(1, player);
                            this.setPersistent();
                        }

                        cir.setReturnValue(InteractionResult.sidedSuccess(this.getWorld().isClientSide()));
                    }
                }
                else if (this.isFood(itemStack) && this.getHealth() < this.getMaxHealth())
                {
                    if (!this.getWorld().isClientSide())
                    {
                        this.eat(player, hand, itemStack);
                        FoodProperties foodComponent = itemStack.get(DataComponents.FOOD);
                        this.heal(foodComponent != null ? (float)foodComponent.nutrition() : 1.0F);
                    }

                    cir.setReturnValue(InteractionResult.sidedSuccess(this.getWorld().isClientSide()));
                }
                else
                {
                    InteractionResult actionResult = super.interactMob(player, hand);
                    if (!actionResult.consumesAction())
                    {
                        this.frontiers$setSitting(!this.frontiers$isSitting());
                        cir.setReturnValue(InteractionResult.sidedSuccess(this.getWorld().isClientSide()));
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
    private Ocelot createChildWithNewAttribs(Ocelot original, @Local(argsOnly = true) AgeableMob passiveEntity)
    {
        if (original != null && passiveEntity instanceof Ocelot ocelotEntity2)
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