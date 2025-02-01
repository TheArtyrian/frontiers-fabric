package net.artyrian.frontiers.mixin.entity.end_crystal;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_interfaces.EndCrystalMixInterface;
import net.artyrian.frontiers.sounds.ModSounds;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalMixin extends EntityMixin implements EndCrystalMixInterface
{
    @Shadow public abstract boolean shouldShowBottom();

    //@Unique private static final TrackedData<Integer> HITS_TAKEN2 = DataTracker.registerData(EndCrystalEntity.class, TrackedDataHandlerRegistry.INTEGER);
    @Unique private final Integer HITS_TAKEN = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
    @Unique private final Boolean IS_FRIENDLY = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());
    @Unique public int crackTicks = 0;
    @Unique public float crackFloat = 1.0f;
    @Unique public float beamLen = 0.0f;
    @Unique public float rays = 0.0f;

    @Override
    public boolean isFriendly()
    {
        boolean returner = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());
        return returner;
    }
    @Override
    public void setFriendly(boolean friend)
    {
        ((AttachmentTarget)this).setAttached(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, friend);
    }

    @Override public int getCrackSpin() { return crackTicks; }
    @Override public float getCrackFloat() { return crackFloat; }
    @Override public float getBeamLen() { return beamLen; }
    @Override public int getRays() { return Math.round(rays); }

    //@Inject(method = "initDataTracker", at = @At("TAIL"))
    //protected void dataAdd(DataTracker.Builder builder, CallbackInfo ci)
    //{
    //    builder.add(HITS_TAKEN, 0);
    //}

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    protected void customNBTRead(NbtCompound nbt, CallbackInfo ci)
    {
        if (nbt.contains("IsFriendly", NbtElement.BYTE_TYPE))
        {
           this.setFriendly(nbt.getBoolean("IsFriendly"));
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    protected void customNBTWrite(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("IsFriendly", this.isFriendly());
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void tickAppend(CallbackInfo ci)
    {
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

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/EndCrystalEntity;isRemoved()Z"))
    public boolean checkCracks(EndCrystalEntity instance, @Local DamageSource source)
    {
        int hit_amnt = ((AttachmentTarget)this).getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        boolean current = this.isRemoved();
        World thisworld = this.getWorld();

        if (!current)
        {
            if (this.isFriendly())
            {
                thisworld.addBlockBreakParticles(this.getBlockPos(), Blocks.GLASS.getDefaultState());
                this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 0.8f);
                this.remove(Entity.RemovalReason.KILLED);

                boolean is_creative = source.getAttacker() instanceof PlayerEntity && ((PlayerEntity) source.getAttacker()).isCreative();
                if (!thisworld.isClient() && !is_creative)
                {
                    ItemEntity crystal = new ItemEntity(thisworld,
                            this.getPos().getX(),
                            this.getPos().getY() + 1.0,
                            this.getPos().getZ(),
                            new ItemStack(ModItem.PURIFIED_END_CRYSTAL, 1)
                    );
                    crystal.setVelocity(
                            .05d * (thisworld.getRandom().nextDouble() * 0.02d),
                            .1d,
                            .05d * (thisworld.getRandom().nextDouble() * 0.02D));
                    crystal.setToDefaultPickupDelay();
                    thisworld.spawnEntity(crystal);
                }

                return true;
            }
            else
            {
                boolean not_explode = (!source.isOf(DamageTypes.EXPLOSION) && !source.isOf(DamageTypes.PLAYER_EXPLOSION));
                if (hit_amnt < 2 && not_explode)
                {
                    ((AttachmentTarget) this).setAttached(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, hit_amnt + 1);

                    this.playSound(ModSounds.END_CRYSTAL_HIT, 5.0f, 1.0f);
                    if (hit_amnt == 1) this.playSound(ModSounds.END_CRYSTAL_WAIL, 5.0f, 1.0f);

                    if (!thisworld.isClient())
                    {
                        thisworld.addBlockBreakParticles(this.getBlockPos(), Blocks.GLASS.getDefaultState());
                        thisworld.addBlockBreakParticles(this.getBlockPos().offset(Direction.UP, 1), Blocks.GLASS.getDefaultState());
                    }

                    return true;
                } else
                {
                    this.playSound(ModSounds.END_CRYSTAL_HIT, 2.0f, 1.2f);
                    this.playSound(ModSounds.END_CRYSTAL_EXPLODE, 5.0f, 1.0f);

                    return false;
                }
            }
        }
        else return current;
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/EndCrystalEntity;crystalDestroyed(Lnet/minecraft/entity/damage/DamageSource;)V"))
    public void lmaoDropShards(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        // Defines if this is one from the Ender Dragon fight or not
        World thisworld = this.getWorld();
        if (!thisworld.isClient() && this.shouldShowBottom())
        {
            int random = thisworld.getRandom().nextBetween(1, 5);
            for (int i = 0; i < random; i++)
            {
                double _xx = (thisworld.getRandom().nextBoolean()) ? 0.5D : -0.5D;
                double _zz = (thisworld.getRandom().nextBoolean()) ? 0.5D : -0.5D;
                ItemEntity frags = new ItemEntity(thisworld,
                        this.getPos().getX(),
                        this.getPos().getY() + 1.5F,
                        this.getPos().getZ(),
                        new ItemStack(ModItem.END_CRYSTAL_SHARD, 1)
                );
                frags.setVelocity(
                        (thisworld.getRandom().nextDouble()) * _xx,
                        0.5D * (thisworld.getRandom().nextDouble()),
                        (thisworld.getRandom().nextDouble()) * _zz
                );
                frags.setToDefaultPickupDelay();

                thisworld.spawnEntity(frags);
            }
        }
    }

    @Inject(method = "kill", at = @At("HEAD"))
    public void theGlass(CallbackInfo ci)
    {
        this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 0.8f);
    }

    @Inject(method = "getPickBlockStack", at = @At("RETURN"), cancellable = true)
    public void checkFriend(CallbackInfoReturnable<ItemStack> cir)
    {
        if (this.isFriendly())
        {
            cir.setReturnValue(new ItemStack(ModItem.PURIFIED_END_CRYSTAL));
        }
    }
}
