package net.artyrian.frontiers.mixin.packet;

import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.ProtocolInfoBuilder;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.GameProtocols;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(GameProtocols.class)
public abstract class PlayStateFactoriesMixin
{
    @Inject(method = "lambda$static$1", at = @At(value = "TAIL"))
    private static void evilMixinThatWillGetMeBlacklistedFromTheIndustry(ProtocolInfoBuilder<ClientGamePacketListener, RegistryFriendlyByteBuf> builder, CallbackInfo ci)
    {
        builder
                .addPacket(ModNetworkConstants.PICKUP_TO_BLOCK, ItemBlockPickupS2CPacket.CODEC)
                .addPacket(ModNetworkConstants.SPAWN_MANA_ORB, ManaOrbSpawnS2CPacket.CODEC)
                .addPacket(ModNetworkConstants.UPDATE_BOSSBAR_MUSIC, BossBarMusicS2CPacket.CODEC);
    }
}