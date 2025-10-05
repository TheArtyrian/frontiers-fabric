package net.artyrian.frontiers.mixin.packet;

import net.artyrian.frontiers.data.ModNetworkConstants;
import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.data.packets.ManaOrbSpawnS2CPacket;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.state.PlayStateFactories;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(PlayStateFactories.class)
public abstract class PlayStateFactoriesMixin
{
    @Inject(method = "method_55958", at = @At(value = "TAIL"))
    private static void evilMixinThatWillGetMeBlacklistedFromTheIndustry(NetworkStateBuilder<ClientPlayPacketListener, RegistryByteBuf> builder, CallbackInfo ci)
    {
        builder
                .add(ModNetworkConstants.PICKUP_TO_BLOCK, ItemBlockPickupS2CPacket.CODEC)
                .add(ModNetworkConstants.SPAWN_MANA_ORB, ManaOrbSpawnS2CPacket.CODEC);
    }
}