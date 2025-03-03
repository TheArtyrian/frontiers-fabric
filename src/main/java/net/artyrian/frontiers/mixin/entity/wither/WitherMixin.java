package net.artyrian.frontiers.mixin.entity.wither;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.mixin.entity.LivingEntityMixin;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherEntity.class)
public abstract class WitherMixin extends LivingEntityMixin
{
    @Override
    public void onDeathHook(DamageSource damageSource, CallbackInfo ci)
    {
        if (!getWorld().isClient)
        {
            PlayerEntity player;

            if (damageSource.getAttacker() instanceof PlayerEntity) player = (PlayerEntity)damageSource.getAttacker();
            else if (damageSource.getAttacker() != null) player = getWorld().getClosestPlayer(damageSource.getAttacker(), 256.0);
            else player = null;

            if (player instanceof PlayerEntity)
            {
                // Get MC server
                MinecraftServer server = getWorld().getServer();

                // Do NBT test state.
                StateSaveLoad serverState = StateSaveLoad.getServerState(server);
                serverState.isInHardmode = true;

                // Send a packet to the server signifying Hardmode
                //PacketByteBuf data = PacketByteBufs.create();

                if (server != null)
                {
                    ServerPlayerEntity playerEntity = server.getPlayerManager().getPlayer(player.getUuid());
                    server.execute(() ->
                    {
                        ServerPlayNetworking.send(playerEntity, new WitherHardmodePayload(true));
                    });
                }
                else Frontiers.LOGGER.error("[Frontiers (FATAL)] Could not set hardmode! Server was not retraceable from ServerPlayer.");
            }
            else Frontiers.LOGGER.error("[Frontiers (FATAL)] Could not set hardmode! Player not found either as attacker or within range.");
        }
    }
}
