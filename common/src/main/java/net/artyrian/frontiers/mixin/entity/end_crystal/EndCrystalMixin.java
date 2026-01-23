package net.artyrian.frontiers.mixin.entity.end_crystal;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.EndCrystalMixInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Debug(export = true)
@Mixin(EndCrystal.class)
public abstract class EndCrystalMixin extends EntityMixin implements EndCrystalMixInterface
{
    @Shadow public abstract boolean showsBottom();

    @Shadow public int time;
    //@Unique private static final TrackedData<Integer> HITS_TAKEN2 = DataTracker.registerData(EndCrystalEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique private final Integer HITS_TAKEN = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
    @Unique private final Boolean IS_FRIENDLY = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());
    @Unique private final BlockPos GOODBEAM_POS = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_GOODBEAM_POS, ModAttachmentTypes.ENDCRYSTAL_GOODBEAM_POS.initializer());
    @Unique private BlockParticleOption GLASS_PARTICLES = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.GLASS.defaultBlockState());
    @Unique public int crackTicks = 0;
    @Unique public float crackFloat = 1.0f;
    @Unique public float beamLen = 0.0f;
    @Unique public float rays = 0.0f;

    @Override
    public boolean frontiers_1_21x$isFriendly()
    {
        boolean returner = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());
        return returner;
    }
    @Override
    public void frontiers_1_21x$setFriendly(boolean friend) { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, friend); }
    @Override public int frontiers_1_21x$getCrackSpin() { return crackTicks; }
    @Override public float frontiers_1_21x$getCrackFloat() { return crackFloat; }
    @Override public float frontiers_1_21x$getBeamLen() { return beamLen; }
    @Override public int frontiers_1_21x$getRays() { return Math.round(rays); }

    @Override
    public BlockPos frontiers$getGoodBeamPos()
    {
        BlockPos returner = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_GOODBEAM_POS, ModAttachmentTypes.ENDCRYSTAL_GOODBEAM_POS.initializer());
        return returner;
    }
    @Override
    public void frontiers$setGoodBeamPos(BlockPos pos) { ((AttachmentTarget)this).setAttached(ModAttachmentTypes.ENDCRYSTAL_GOODBEAM_POS, pos); }

    //@Inject(method = "initDataTracker", at = @At("TAIL"))
    //protected void dataAdd(DataTracker.Builder builder, CallbackInfo ci)
    //{
    //    builder.add(HITS_TAKEN, 0);
    //}

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci)
    {
        if (nbt.contains("IsFriendly", Tag.TAG_BYTE))
        {
           this.frontiers_1_21x$setFriendly(nbt.getBoolean("IsFriendly"));
        }

        if (nbt.contains("GoodBeamPos", Tag.TAG_COMPOUND))
        {
            CompoundTag pound = nbt.getCompound("GoodBeamPos");
            BlockPos remap = new BlockPos(pound.getInt("x"), pound.getInt("y"), pound.getInt("z"));
            this.frontiers$setGoodBeamPos(remap);
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci)
    {
        nbt.putBoolean("IsFriendly", this.frontiers_1_21x$isFriendly());

        if (this.frontiers$getGoodBeamPos() != null)
        {
            BlockPos posReal = this.frontiers$getGoodBeamPos();
            CompoundTag pos = new CompoundTag();
            pos.putInt("x", posReal.getX());
            pos.putInt("y", posReal.getY());
            pos.putInt("z", posReal.getZ());

            nbt.put("GoodBeamPos", pos);
        }
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void tickAppend(CallbackInfo ci)
    {
        Level thisworld = this.getWorld();
        if (!thisworld.isClientSide && time % 100 == 0 && !this.frontiers_1_21x$isFriendly())
        {
            int spongetronX = this.getBlockX();
            int spongetronY = this.getBlockY();
            int spongetronZ = this.getBlockZ();
            for (BlockPos blockPosXR : BlockPos.betweenClosed(spongetronX - 8, spongetronY - 8, spongetronZ - 8, spongetronX + 8, spongetronY + 8, spongetronZ + 8))
            {
                if (thisworld.getBlockState(blockPosXR).is(Blocks.BUDDING_AMETHYST))
                {
                    Optional<Boolean> is_corrupted = thisworld.getBlockState(blockPosXR).getOptionalValue(ModBlockProperties.IS_CORRUPTED);
                    if (is_corrupted.isPresent())
                    {
                        thisworld.getBlockState(blockPosXR).setValue(ModBlockProperties.IS_CORRUPTED, true);

                        thisworld.setBlock(blockPosXR, thisworld
                                .getBlockState(blockPosXR)
                                .setValue(ModBlockProperties.IS_CORRUPTED, true),
                                Block.UPDATE_CLIENTS
                        );
                    }
                }
            }
        }

        // Beam tick appender
        int hit_amnt = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        if (hit_amnt > 0)
        {
            float crack_val = (hit_amnt == 1) ? 0.6f : 0.3f;
            float beam_val = (hit_amnt == 1) ? 0.05f : 0.6f;
            if (crackFloat > crack_val) crackFloat -= 0.1f;
            if (beamLen < beam_val) beamLen += 0.05f;
            if (rays < 4.0f * (float)hit_amnt) rays += 0.5f;

            crackTicks ++;
            // Don't want any overflows now :3c
            if (crackTicks > 2048) crackTicks = 0;
        }
    }

    @ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/EndCrystalEntity;isRemoved()Z"))
    public boolean checkCracks(boolean original, @Local DamageSource source)
    {
        int hit_amnt = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        Level thisworld = this.getWorld();

        if (!original)
        {
            if (this.frontiers_1_21x$isFriendly())
            {
                this.playSound(SoundEvents.GLASS_BREAK, 1.0f, 0.8f);
                this.remove(Entity.RemovalReason.KILLED);

                boolean is_creative = source.getEntity() instanceof Player && ((Player) source.getEntity()).isCreative();
                if (!thisworld.isClientSide())
                {
                    ((ServerLevel)thisworld).sendParticles(
                            GLASS_PARTICLES,
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            30,
                            0.4,
                            0.4,
                            0.4,
                            0.7
                            );

                    boolean do_loot = thisworld.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
                    if (!is_creative && do_loot)
                    {
                        ItemEntity crystal = new ItemEntity(thisworld,
                                this.getPos().x(),
                                this.getPos().y() + 1.0,
                                this.getPos().z(),
                                new ItemStack(ModItem.PURIFIED_END_CRYSTAL, 1)
                        );
                        crystal.setDeltaMovement(
                                .05d * (thisworld.getRandom().nextDouble() * 0.02d),
                                .1d,
                                .05d * (thisworld.getRandom().nextDouble() * 0.02D));
                        crystal.setDefaultPickUpDelay();
                        thisworld.addFreshEntity(crystal);
                    }

                    thisworld.gameEvent(source.getEntity(), GameEvent.BLOCK_DESTROY, this.getPos());
                }

                return true;
            }
            else
            {
                if (!thisworld.isClientSide())
                {
                    ((ServerLevel)thisworld).sendParticles(
                            GLASS_PARTICLES,
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            30,
                            0.4,
                            0.4,
                            0.4,
                            0.7
                    );

                    ((ServerLevel)thisworld).sendParticles(
                            ParticleTypes.EXPLOSION,
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            2,
                            0.6,
                            0.6,
                            0.6,
                            0.3
                    );

                    ((ServerLevel)thisworld).sendParticles(
                            ParticleTypes.WHITE_SMOKE,
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            20,
                            0.4,
                            0.4,
                            0.4,
                            0.4
                    );

                    ((ServerLevel)thisworld).sendParticles(
                            ParticleTypes.SMOKE,
                            this.getX(),
                            this.getY() + 1.0,
                            this.getZ(),
                            30,
                            0.4,
                            0.4,
                            0.4,
                            0.3
                    );
                }

                thisworld.gameEvent(source.getEntity(), GameEvent.ENTITY_DAMAGE, this.getPos());

                boolean not_explode = (!source.is(DamageTypes.EXPLOSION) && !source.is(DamageTypes.PLAYER_EXPLOSION));
                if (hit_amnt < 2 && not_explode)
                {
                    ((AttachmentTarget) this).setAttached(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, hit_amnt + 1);

                    this.playSound(ModSounds.END_CRYSTAL_HIT, 5.0f, 1.0f);
                    if (hit_amnt == 1) this.playSound(ModSounds.END_CRYSTAL_WAIL, 5.0f, 1.0f);

                    return true;
                } else
                {
                    this.playSound(ModSounds.END_CRYSTAL_HIT, 5.0f, 1.2f);
                    this.playSound(ModSounds.END_CRYSTAL_EXPLODE, 5.0f, 1.0f);

                    return false;
                }
            }
        }
        else return original;
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/EndCrystalEntity;crystalDestroyed(Lnet/minecraft/entity/damage/DamageSource;)V"))
    public void lmaoDropShards(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        // Defines if this is one from the Ender Dragon fight or not
        Level thisworld = this.getWorld();
        if (!thisworld.isClientSide() && this.showsBottom())
        {
            int random = thisworld.getRandom().nextIntBetweenInclusive(2, 6);
            for (int i = 0; i < random; i++)
            {
                double _xx = (thisworld.getRandom().nextBoolean()) ? 0.5D : -0.5D;
                double _zz = (thisworld.getRandom().nextBoolean()) ? 0.5D : -0.5D;
                ItemEntity frags = new ItemEntity(thisworld,
                        this.getPos().x(),
                        this.getPos().y() + 1.5F,
                        this.getPos().z(),
                        new ItemStack(ModItem.END_CRYSTAL_SHARD, 1)
                );
                frags.setDeltaMovement(
                        (thisworld.getRandom().nextDouble()) * _xx,
                        0.5D * (thisworld.getRandom().nextDouble()),
                        (thisworld.getRandom().nextDouble()) * _zz
                );
                frags.setDefaultPickUpDelay();

                thisworld.addFreshEntity(frags);
            }
        }
    }

    @Inject(method = "kill", at = @At("HEAD"))
    public void theGlass(CallbackInfo ci)
    {
        this.playSound(SoundEvents.GLASS_BREAK, 1.0f, 0.8f);
    }

    @Inject(method = "getPickBlockStack", at = @At("RETURN"), cancellable = true)
    public void checkFriend(CallbackInfoReturnable<ItemStack> cir)
    {
        if (this.frontiers_1_21x$isFriendly())
        {
            cir.setReturnValue(new ItemStack(ModItem.PURIFIED_END_CRYSTAL));
        }
    }
}
