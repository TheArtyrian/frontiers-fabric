package net.artyrian.frontiers.mixin.world;

import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin
{
    @Shadow @Final private List<ServerPlayerEntity> players;

    @Inject(method = "wakeSleepingPlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/SleepManager;clearSleeping()V"))
    private void doPhantomBedHeal(CallbackInfo ci)
    {
        (this.players.stream().filter(LivingEntity::isSleeping).toList()).forEach(player ->
        {
            player.getSleepingPosition().filter(player.getWorld()::isChunkLoaded).ifPresent(pos ->
            {
                BlockState blockState = player.getWorld().getBlockState(pos);
                if (blockState.isOf(ModBlocks.PHANTOM_STITCH_BED))
                {
                    ModCriteria.SLEPT_ON_PHANTOM_BED.trigger(player);
                    player.setHealth(player.getMaxHealth());

                    if (!player.hasStatusEffect(ModStatusEffects.WELL_RESTED))
                    {
                        player.addStatusEffect(new StatusEffectInstance(ModStatusEffects.WELL_RESTED, 144000, 0, true, false));
                    }

                    if (!player.hasStatusEffect(StatusEffects.SLOW_FALLING))
                    {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0, true, false));
                    }
                }
            });
        });
    }
}
