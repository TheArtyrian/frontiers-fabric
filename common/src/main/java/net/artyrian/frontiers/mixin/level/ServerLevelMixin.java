package net.artyrian.frontiers.mixin.level;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.NotNull;
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
    @Shadow @NotNull public abstract MinecraftServer getServer();

    @WrapOperation(method = "tickPrecipitation", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/Biome;shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean frontiers$snowMelt(Biome instance, LevelReader levelReader, BlockPos pos, Operation<Boolean> original)
    {
        MinecraftServer server = this.getServer();
        if (server != null)
        {
            StateSaveLoad serverState = StateSaveLoad.getServerState(server);
            if (serverState.snowMeltPos.contains(pos.below()))
            {
                ServerLevel self = (ServerLevel)(Object)this;
                VectorEventSync.Local.fireEvent(self, pos.below(), FRLevelEvents.Local.SNOW_MELT_GLISTEN, self.random.nextIntBetweenInclusive(2, 5));
                return false;
            }
        }

        return original.call(instance, levelReader, pos);
    }

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
