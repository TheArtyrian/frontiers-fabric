package net.artyrian.frontiers.mixin.block.beacon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
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
    @Shadow private int level;

    @Shadow public static void playSound(Level world, BlockPos pos, SoundEvent sound)
    {

    }

    @Inject(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/advancement/criterion/ConstructBeaconCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;I)V",
            shift = At.Shift.AFTER)
    )
    private static void checkBrimtanBeaconCriterion(
            Level world,
            BlockPos pos,
            BlockState state,
            BeaconBlockEntity blockEntity,
            CallbackInfo ci,
            @Local ServerPlayer player
            )
    {
        if (blockEntity.levels >= 4 && hasBrimtanBlocks(world, pos.below()))
        {
            ModCriteria.BEACON_POWERED_WITH_BRIMTAN.trigger(player);
        }
    }

    @WrapOperation(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/entity/BeaconBlockEntity;playSound(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;)V",
            ordinal = 0)
    )
    private static void cancelSound(
            Level world,
            BlockPos pos,
            SoundEvent sound,
            Operation<Void> original,
            @Local(argsOnly = true) BeaconBlockEntity beacon)
    {
        if (beacon.levels >= 4 && hasBrimtanBlocks(world, pos.below()))
        {
            original.call(world, pos, ModSounds.BEACON_BRIMTAN);
        }
        else
        {
            original.call(world, pos, sound);
        }
    }

    @WrapOperation(method = "applyPlayerEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Box;expand(D)Lnet/minecraft/util/math/Box;"))
    private static AABB brimtanOverflow(
            AABB instance,
            double og_double,
            Operation<AABB> original,
            @Local(argsOnly = true) int level,
            @Local(argsOnly = true) Level world,
            @Local(argsOnly = true) BlockPos pos)
    {
        if (level >= 4 && hasBrimtanBlocks(world, pos.below()))
        {
            return original.call(instance, og_double * 2.0);
        }
        return original.call(instance, og_double);
    }

    @Unique
    private static boolean hasBrimtanBlocks(Level world, BlockPos pos)
    {
        return (
                world.getBlockState(pos).is(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.offset(1, 0, 0)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(-1, 0, 0)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(0, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(0, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.offset(1, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(-1, 0, 1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(1, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.offset(-1, 0, -1)).is(ModBlocks.BRIMTAN_BLOCK)
        );
    }
}
