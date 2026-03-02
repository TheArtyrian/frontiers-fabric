package net.artyrian.frontiers.mixin.packet;

import net.artyrian.frontiers.definition.event.MixinShortcuts;
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
public abstract class PlayStateFactoriesMixinFabric
{
    @Inject(method = "method_55958", at = @At(value = "TAIL"))
    private static void evilMixinThatWillGetMeBlacklistedFromTheIndustry(ProtocolInfoBuilder<ClientGamePacketListener, RegistryFriendlyByteBuf> builder, CallbackInfo ci)
    {
        MixinShortcuts.playStateBuilderAppend(builder);
    }
}