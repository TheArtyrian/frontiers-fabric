package net.artyrian.frontiers.event;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.OreWitherPayload;
import net.artyrian.frontiers.data.payloads.WitherHardmodePayload;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.sounds.ModSounds;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

public class PlayerBlockBreakEventReg
{
    public static void doReg()
    {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            if (state.isIn(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
            {
                MinecraftServer server = world.getServer();
                StateSaveLoad loader = StateSaveLoad.getServerState(server);
                boolean hard = loader.isInHardmode;

                if (!hard)
                {
                    for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) world, pos)) {
                        ServerPlayNetworking.send(targeter, new OreWitherPayload(pos));
                    }
                }
            }
            return true;
        });
    }
}
