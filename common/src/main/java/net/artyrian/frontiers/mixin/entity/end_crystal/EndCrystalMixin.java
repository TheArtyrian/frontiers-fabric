package net.artyrian.frontiers.mixin.entity.end_crystal;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.data.nbt_sync.NBTSync;
import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.artyrian.frontiers.mixin_intf.EndCrystalIntf;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.artyrian.frontiers.reg.misc.ModBlockProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSyncClient;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorNetSync;
import net.vertisoft.vectorlib.agnostic.networking.netsync.VectorSyncable;
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
public abstract class EndCrystalMixin extends EntityMixin implements EndCrystalIntf, VectorSyncable
{
    @Shadow public abstract boolean showsBottom();
    @Shadow public int time;

    @Unique private CompoundTag frontiers$persistentData;
    @Unique private final VectorNetSync vectorLib$netSync = new VectorNetSync((EndCrystal)(Object)this, NBTSync.ENDCRYSTAL$ID, true, (nbt) -> {
        nbt.putBoolean(NBTSync.ENDCRYSTAL$FRIENDLY, false);
        nbt.putInt(NBTSync.ENDCRYSTAL$HITS, 0);

        CompoundTag pos = new CompoundTag();
        pos.putInt("x", this.getBlockX());
        pos.putInt("y", this.getBlockY());
        pos.putInt("z", this.getBlockZ());
        nbt.put(NBTSync.ENDCRYSTAL$BEAMPOS, pos);
    });


    @Unique public int crackTicks = 0;
    @Unique public float crackFloat = 1.0f;
    @Unique public float beamLen = 0.0f;
    @Unique public float rays = 0.0f;

    @Override public VectorNetSync getVectorLibNetsync() { return vectorLib$netSync; }

    @Override public boolean frontiers_1_21x$isFriendly() { return this.vectorLib$netSync.getBool(NBTSync.ENDCRYSTAL$FRIENDLY, false); }
    @Override public void frontiers_1_21x$setFriendly(boolean friend) { this.vectorLib$netSync.syncBool(NBTSync.ENDCRYSTAL$FRIENDLY, friend, false); }

    @Override public int frontiers_1_21x$getHitsTaken() { return this.vectorLib$netSync.getInt(NBTSync.ENDCRYSTAL$HITS, 0); }
    @Override public void frontiers_1_21x$setHitsTaken(int count) { this.vectorLib$netSync.syncInt(NBTSync.ENDCRYSTAL$HITS, count, false); }

    @Override public BlockPos frontiers$getGoodBeamPos() { return this.vectorLib$netSync.getBlockPos(NBTSync.ENDCRYSTAL$BEAMPOS, this.blockPosition()); }
    @Override public void frontiers$setGoodBeamPos(BlockPos pos) { this.vectorLib$netSync.syncBlockPos(NBTSync.ENDCRYSTAL$BEAMPOS, pos, false); }

    @Override public int frontiers_1_21x$getCrackSpin() { return crackTicks; }
    @Override public float frontiers_1_21x$getCrackFloat() { return crackFloat; }
    @Override public float frontiers_1_21x$getBeamLen() { return beamLen; }
    @Override public int frontiers_1_21x$getRays() { return Math.round(rays); }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTRead(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.readNetSyncFromNBT(nbt); }
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    protected void customNBTWrite(CompoundTag nbt, CallbackInfo ci) { this.vectorLib$netSync.saveToNBT(nbt); }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void tickAppend(CallbackInfo ci)
    {
        Level thisworld = this.level();
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
                        thisworld.setBlock(blockPosXR, thisworld.getBlockState(blockPosXR).setValue(ModBlockProperties.IS_CORRUPTED, true), Block.UPDATE_CLIENTS);
                    }
                }
            }
        }

        // Beam tick appender
        int hit_amnt = this.frontiers_1_21x$getHitsTaken();
        if (hit_amnt > 0)
        {
            float crack_val = (hit_amnt == 1) ? 0.6f : 0.3f;
            float beam_val = (hit_amnt == 1) ? 0.05f : 0.6f;
            if (crackFloat > crack_val) crackFloat -= 0.1f;
            if (beamLen < beam_val) beamLen += 0.05f;
            if (rays < 4.0f * (float)hit_amnt) rays += 0.5f;

            crackTicks ++;
            // Don't want any overflows now :3c
            if (crackTicks >= 720) crackTicks = 0;
        }
    }

    @ModifyExpressionValue(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;isRemoved()Z"))
    public boolean checkCracks(boolean original, @Local DamageSource source)
    {
        int hit_amnt = this.frontiers_1_21x$getHitsTaken();
        Level thisworld = this.level();

        if (!original)
        {
            if (this.frontiers_1_21x$isFriendly())
            {
                this.playSound(SoundEvents.GLASS_BREAK, 1.0f, 0.8f);
                this.remove(Entity.RemovalReason.KILLED);
                boolean is_creative = source.getEntity() instanceof Player && ((Player) source.getEntity()).isCreative();

                if (!thisworld.isClientSide())
                {
                    VectorEventSync.Local.fireEvent(thisworld, this.blockPosition(), FRLevelEvents.Local.END_CRYSTAL_HARM, 3);

                    thisworld.gameEvent(source.getEntity(), GameEvent.BLOCK_DESTROY, this.position());

                    boolean do_loot = thisworld.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
                    if (!is_creative && do_loot)
                    {
                        ItemEntity crystal = new ItemEntity(thisworld,
                                this.position().x(),
                                this.position().y() + 1.0,
                                this.position().z(),
                                new ItemStack(ModItem.PURIFIED_END_CRYSTAL.get(), 1)
                        );
                        crystal.setDeltaMovement(
                                .05d * (thisworld.getRandom().nextDouble() * 0.02d),
                                .1d,
                                .05d * (thisworld.getRandom().nextDouble() * 0.02D));
                        crystal.setDefaultPickUpDelay();
                        thisworld.addFreshEntity(crystal);
                    }
                }

                return true;
            }
            else
            {
                if (!thisworld.isClientSide())
                {
                    VectorEventSync.Local.fireEvent(thisworld, this.blockPosition(), FRLevelEvents.Local.END_CRYSTAL_HARM, hit_amnt);
                }

                thisworld.gameEvent(source.getEntity(), GameEvent.ENTITY_DAMAGE, this.position());

                boolean not_explode = (!source.is(DamageTypes.EXPLOSION) && !source.is(DamageTypes.PLAYER_EXPLOSION));
                if (hit_amnt < 2 && not_explode)
                {
                    this.frontiers_1_21x$setHitsTaken(hit_amnt + 1);

                    this.playSound(ModSounds.END_CRYSTAL_HIT.get(), 5.0f, 1.0f);
                    if (hit_amnt == 1) this.playSound(ModSounds.END_CRYSTAL_WAIL.get(), 5.0f, 1.0f);

                    return true;
                } else
                {
                    this.playSound(ModSounds.END_CRYSTAL_HIT.get(), 5.0f, 1.2f);
                    this.playSound(ModSounds.END_CRYSTAL_EXPLODE.get(), 5.0f, 1.0f);

                    return false;
                }
            }
        }
        else return original;
    }

    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EndCrystal;onDestroyedBy(Lnet/minecraft/world/damagesource/DamageSource;)V"))
    public void lmaoDropShards(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        // Defines if this is one from the Ender Dragon fight or not
        Level thisworld = this.level();
        if (!thisworld.isClientSide() && this.showsBottom())
        {
            int random = thisworld.getRandom().nextIntBetweenInclusive(2, 6);
            for (int i = 0; i < random; i++)
            {
                double _xx = (thisworld.getRandom().nextBoolean()) ? 0.5D : -0.5D;
                double _zz = (thisworld.getRandom().nextBoolean()) ? 0.5D : -0.5D;
                ItemEntity frags = new ItemEntity(thisworld,
                        this.position().x(),
                        this.position().y() + 1.5F,
                        this.position().z(),
                        new ItemStack(ModItem.END_CRYSTAL_SHARD.get(), 1)
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

    @Inject(method = "getPickResult", at = @At("RETURN"), cancellable = true)
    public void checkFriend(CallbackInfoReturnable<ItemStack> cir)
    {
        if (this.frontiers_1_21x$isFriendly())
        {
            cir.setReturnValue(new ItemStack(ModItem.PURIFIED_END_CRYSTAL.get()));
        }
    }
}
