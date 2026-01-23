package net.artyrian.frontiers.mixin.block.beacon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.definition.advancement.criterion.BeaconBrimtanCriterion;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin extends BlockEntityMixin
{
    @Shadow public int levels;
    @Shadow public static void playSound(Level world, BlockPos pos, SoundEvent sound) { }

    @Inject(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/advancements/critereon/ConstructBeaconTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;I)V",
            shift = At.Shift.AFTER)
    )
    private static void frontiers$checkBrimtanBeaconCriterion(
            Level world,
            BlockPos pos,
            BlockState state,
            BeaconBlockEntity blockEntity,
            CallbackInfo ci,
            @Local ServerPlayer player
            )
    {
        if (blockEntity.levels >= 4 && frontiers$hasBrimtanBlocks(world, pos.below()))
        {
            ((BeaconBrimtanCriterion)ModCriteria.BEACON_POWERED_WITH_BRIMTAN.get()).trigger(player);
        }
    }

    @WrapOperation(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/entity/BeaconBlockEntity;playSound(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;)V",
            ordinal = 0)
    )
    private static void frontiers$cancelSoundForBetterOne(
            Level world,
            BlockPos pos,
            SoundEvent sound,
            Operation<Void> original,
            @Local(argsOnly = true) BeaconBlockEntity beacon)
    {
        if (beacon.levels >= 4 && frontiers$hasBrimtanBlocks(world, pos.below()))
        {
            original.call(world, pos, ModSounds.BEACON_BRIMTAN.get());
        }
        else
        {
            original.call(world, pos, sound);
        }
    }

    @WrapOperation(method = "applyEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(D)Lnet/minecraft/world/phys/AABB;"))
    private static AABB frontiers$brimtanOverflow(
            AABB instance,
            double og_double,
            Operation<AABB> original,
            @Local(argsOnly = true) int level,
            @Local(argsOnly = true) Level world,
            @Local(argsOnly = true) BlockPos pos)
    {
        if (level >= 4 && frontiers$hasBrimtanBlocks(world, pos.below()))
        {
            return original.call(instance, og_double * 2.0);
        }
        return original.call(instance, og_double);
    }

    @Unique
    private static boolean frontiers$hasBrimtanBlocks(Level world, BlockPos pos)
    {
        return (
                world.getBlockState(pos).is(ModBlocks.BRIMTAN_BLOCK.get()) &&

                world.getBlockState(pos.offset(1, 0, 0)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(-1, 0, 0)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(0, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(0, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&

                world.getBlockState(pos.offset(1, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(-1, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(1, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK.get()) &&
                world.getBlockState(pos.offset(-1, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK.get())
        );
    }
}
