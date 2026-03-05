package net.artyrian.frontiers.mixin.level;

import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin
{
    @Shadow @Final private List<ServerPlayer> players;

    @Inject(method = "wakeUpAllPlayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/players/SleepStatus;removeAllSleepers()V"))
    private void doPhantomBedHeal(CallbackInfo ci)
    {
        (this.players.stream().filter(LivingEntity::isSleeping).toList()).forEach(player ->
        {
            player.getSleepingPos().filter(player.level()::hasChunkAt).ifPresent(pos ->
            {
                BlockState blockState = player.level().getBlockState(pos);
                if (blockState.is(ModBlocks.PHANTOM_STITCH_BED.get()))
                {
                    ModCriteria.SLEPT_ON_PHANTOM_BED.get().trigger(player);
                    player.setHealth(player.getMaxHealth());

                    if (!player.hasEffect(ModStatusEffects.WELL_RESTED))
                    {
                        player.addEffect(new MobEffectInstance(ModStatusEffects.WELL_RESTED, 144000, 0, true, false));
                    }

                    if (!player.hasEffect(MobEffects.SLOW_FALLING))
                    {
                        player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, true, false));
                    }
                }
            });
        });
    }
}
