package net.artyrian.frontiers.mixin.block.beacon;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
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

    @Shadow public static void playSound(World world, BlockPos pos, SoundEvent sound)
    {

    }

    @Inject(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/advancement/criterion/ConstructBeaconCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;I)V",
            shift = At.Shift.AFTER)
    )
    private static void checkBrimtanBeaconCriterion(
            World world,
            BlockPos pos,
            BlockState state,
            BeaconBlockEntity blockEntity,
            CallbackInfo ci,
            @Local ServerPlayerEntity player
            )
    {
        if (blockEntity.level >= 4 && hasBrimtanBlocks(world, pos.down()))
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
            World world,
            BlockPos pos,
            SoundEvent sound,
            Operation<Void> original,
            @Local(argsOnly = true) BeaconBlockEntity beacon)
    {
        if (beacon.level >= 4 && hasBrimtanBlocks(world, pos.down()))
        {
            original.call(world, pos, ModSounds.BEACON_BRIMTAN);
        }
        else
        {
            original.call(world, pos, sound);
        }
    }

    @WrapOperation(method = "applyPlayerEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Box;expand(D)Lnet/minecraft/util/math/Box;"))
    private static Box brimtanOverflow(
            Box instance,
            double og_double,
            Operation<Box> original,
            @Local(argsOnly = true) int level,
            @Local(argsOnly = true) World world,
            @Local(argsOnly = true) BlockPos pos)
    {
        if (level >= 4 && hasBrimtanBlocks(world, pos.down()))
        {
            return original.call(instance, og_double * 2.0);
        }
        return original.call(instance, og_double);
    }

    @Unique
    private static boolean hasBrimtanBlocks(World world, BlockPos pos)
    {
        return (
                world.getBlockState(pos).isOf(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.add(1, 0, 0)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(-1, 0, 0)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(0, 0, 1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(0, 0, -1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&

                world.getBlockState(pos.add(1, 0, 1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(-1, 0, 1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(1, 0, -1)).isOf(ModBlocks.BRIMTAN_BLOCK) &&
                world.getBlockState(pos.add(-1, 0, -1)).isOf(ModBlocks.BRIMTAN_BLOCK)
        );
    }
}
