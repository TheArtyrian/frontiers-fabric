package net.artyrian.frontiers.mixin.entity.end_crystal;

import net.artyrian.frontiers.mixin.entity.EntityMixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalMixin extends EntityMixin
{
    @Unique private static final TrackedData<Integer> HITS_TAKEN = DataTracker.registerData(EndCrystalEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void dataAdd(DataTracker.Builder builder, CallbackInfo ci)
    {
        builder.add(HITS_TAKEN, 0);
    }

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/EndCrystalEntity;isRemoved()Z"))
    public boolean checkCracks(EndCrystalEntity instance)
    {
        int hit_amnt = this.getDataTracker().get(HITS_TAKEN);
        boolean current = this.isRemoved();
        if (!current)
        {
            if (hit_amnt < 2)
            {
                this.getDataTracker().set(HITS_TAKEN, hit_amnt + 1);
                this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 0.7f);

                if (!this.getWorld().isClient())
                {
                    this.getWorld().addBlockBreakParticles(this.getBlockPos(), Blocks.GLASS.getDefaultState());
                    this.getWorld().addBlockBreakParticles(this.getBlockPos().offset(Direction.UP, 1), Blocks.GLASS.getDefaultState());
                }

                return true;
            }
            else
            {
                this.playSound(SoundEvents.ENTITY_EVOKER_CAST_SPELL, 1.0f, 0.8f);
                this.playSound(SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 0.8f);
                return false;
            }
        }
        else return current;
    }

    @Inject(method = "kill", at = @At("HEAD"))
    public void theGlass(CallbackInfo ci)
    {
        this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 0.8f);
    }
}
