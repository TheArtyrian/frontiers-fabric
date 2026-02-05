package net.artyrian.frontiers.mixin.entity.ocelot;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.definition.entity.ai.ocelot.OcelotEscapeDangerGoal;
import net.artyrian.frontiers.definition.entity.ai.ocelot.OcelotFollowOwnerGoal;
import net.artyrian.frontiers.definition.entity.ai.ocelot.OcelotSitGoal;
import net.artyrian.frontiers.mixin.entity.AnimalEntityMixin;
import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
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
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

/** This mixin aims to make Ocelots tameable again, in an effort to restore them from simply ambient mobs to where they were before. <p>
 * Due to mixin limitations, it manually re-implements tamability in its own format. Spaghetti ahead. You have been warned.
 * <p>Also floppa mention */
@Debug(export = true)
@Mixin(Ocelot.class)
public abstract class OcelotEntityMixin extends AnimalEntityMixin implements OcelotMixIntf, VectorSyncable
{
    @Shadow public abstract boolean isFood(ItemStack stack);
    @Shadow protected abstract void setTrusting(boolean trusting);

    @Unique private boolean frontiersSitting;

    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((Ocelot)(Object)this, NBTSync.OCELOT$ID, true, (nbt) -> {
        nbt.putByte(NBTSync.OCELOT$TAME_FLAG, (byte)0);
        nbt.putByte(NBTSync.OCELOT$COLLAR, (byte)DyeColor.RED.getId());
    });

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Override public DyeColor frontiers$getCollarColor() { return DyeColor.byId(this.vectorLib$netSync.getByte(NBTSync.OCELOT$COLLAR, (byte)DyeColor.RED.getId())); }
    @Override public void frontiers$setCollarColor(DyeColor color) { this.vectorLib$netSync.syncByte(NBTSync.OCELOT$COLLAR, (byte)color.getId(), false); }

    @Nullable @Override public UUID frontiers$getOcelotOwnerID() { return this.vectorLib$netSync.getUUID(NBTSync.OCELOT$OWNER, true); }
    @Override public void frontiers$setOcelotOwnerID(@Nullable UUID uuid) { if (uuid != null) this.vectorLib$netSync.syncUUID(NBTSync.OCELOT$OWNER, uuid, true); }

    @Override public byte frontiers$getTameFlags() { return this.vectorLib$netSync.getByte(NBTSync.OCELOT$TAME_FLAG, (byte)0); }
    @Override public void frontiers$setTameFlags(byte flags) { this.vectorLib$netSync.syncByte(NBTSync.OCELOT$TAME_FLAG, flags, false); }

    @Override public LivingEntity frontiers$getOwner()
    {
        UUID uUID = this.frontiers$getOcelotOwnerID();
        return (uUID == null) ? null : this.level().getPlayerByUUID(uUID);
    }
    @Override public void frontiers$setOwner(Player player)
    {
        this.frontiers$setTamed(true, true);
        this.frontiers$setOcelotOwnerID(player.getUUID());
        if (player instanceof ServerPlayer serverPlayerEntity)
        {
            CriteriaTriggers.TAME_ANIMAL.trigger(serverPlayerEntity, (Ocelot)(Object)this);
        }
    }
    @Override public boolean frontiers$isOwner(LivingEntity player)
    {
        return player == this.frontiers$getOwner();
    }

    @Override public boolean frontiers$isTamed()
    {
        byte b = this.frontiers$getTameFlags();
        return (b & 4) != 0;
    }
    @Override public void frontiers$setTamed(boolean tamed, boolean updateAttributes)
    {
        byte b = this.frontiers$getTameFlags();

        if (tamed) this.frontiers$setTameFlags((byte)(b | 4));
        else this.frontiers$setTameFlags((byte)(b & -5));

        if (updateAttributes) this.frontiers$UpdateAttrb();
    }

    @Override public boolean frontiers$isSitting()
    {
        return this.frontiersSitting;
    }
    @Override public void frontiers$setSitting(boolean sitting)
    {
        this.frontiersSitting = sitting;
    }

    @Override public boolean frontiers$isInSittingPose()
    {
        byte b = this.frontiers$getTameFlags();
        return (b & 1) != 0;
    }
    @Override public void frontiers$setInSittingPose(boolean sitting)
    {
        byte b = this.frontiers$getTameFlags();

        if (sitting) this.frontiers$setTameFlags((byte)(b | 1));
        else this.frontiers$setTameFlags((byte)(b & -2));
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
            this.moveTo((double)x + 0.5, (double)y, (double)z + 0.5, this.getYRot(), this.getXRot());
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
            BlockState blockState = this.level().getBlockState(pos.below());
            if (!this.frnt$canTeleportOntoLeaves() && blockState.getBlock() instanceof LeavesBlock)
            {
                return false;
            }
            else
            {
                BlockPos blockPos = pos.subtract(this.blockPosition());
                return this.level().noCollision((Ocelot)(Object)this, this.getBoundingBox().move(blockPos));
            }
        }
    }

    @Unique protected void frontiers$UpdateAttrb() { /* unused atm*/ }
    @Unique protected boolean frnt$canTeleportOntoLeaves()
    {
        return false;
    }

    @Override
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {
        if (!this.level().isClientSide && this.level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && this.frontiers$getOwner() instanceof ServerPlayer)
        {
            this.frontiers$getOwner().sendSystemMessage(this.getCombatTracker().getDeathMessage());
        }
        super.onDeathHook(damageSource, ci);
    }

    @Override
    public final boolean frontiers$cannotFollowOwner()
    {
        return this.frontiers$isSitting() || this.isPassenger() || this.getLeashData() != null || this.frontiers$getOwner() != null && this.frontiers$getOwner().isSpectator();
    }
    @Override
    public boolean frontiers$shouldTryTeleportToOwner()
    {
        LivingEntity livingEntity = this.frontiers$getOwner();
        return livingEntity != null && this.distanceToSqr(this.frontiers$getOwner()) >= 144.0;
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

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void customReadNBTStuff(CompoundTag nbt, CallbackInfo ci)
    {
        this.vectorLib$netSync.readNetSyncFromNBT(nbt);

        this.frontiersSitting = this.frontiers$isInSittingPose();
        this.frontiers$setInSittingPose(this.frontiersSitting);
    }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void customWriteNBTStuff(CompoundTag nbt, CallbackInfo ci)
    {
        this.vectorLib$netSync.saveToNBT(nbt);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
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

    @ModifyExpressionValue(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Ocelot$OcelotTemptGoal;isRunning()Z"))
    private boolean alsoCheckOnUserCreative(boolean original, @Local(argsOnly = true) Player player)
    {
        return original || player.isCreative();
    }

    @Inject(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Ocelot;setTrusting(Z)V", shift = At.Shift.AFTER))
    private void setTameAsWell(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir)
    {
        this.frontiers$setOwner(player);
        this.frontiers$setSitting(true);
        //this.frontiers$setInSittingPose(true);
        this.setPersistenceRequired();
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
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
                        if (!this.level().isClientSide())
                        {
                            this.frontiers$setCollarColor(dyeColor);
                            itemStack.consume(1, player);
                            this.setPersistenceRequired();
                        }

                        cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide()));
                    }
                }
                else if (this.isFood(itemStack) && this.getHealth() < this.getMaxHealth())
                {
                    if (!this.level().isClientSide())
                    {
                        this.usePlayerItem(player, hand, itemStack);
                        FoodProperties foodComponent = itemStack.get(DataComponents.FOOD);
                        this.heal(foodComponent != null ? (float)foodComponent.nutrition() : 1.0F);
                    }

                    cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide()));
                }
                else
                {
                    InteractionResult actionResult = super.mobInteract(player, hand);
                    if (!actionResult.consumesAction())
                    {
                        this.frontiers$setSitting(!this.frontiers$isSitting());
                        //this.frontiers$setInSittingPose(!this.frontiers$isInSittingPose());
                        cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide()));
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
            method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Ocelot;",
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