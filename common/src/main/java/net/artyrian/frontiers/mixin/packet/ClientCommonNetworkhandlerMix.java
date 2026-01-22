package net.artyrian.frontiers.mixin.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientCommonPacketListenerImpl;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientCommonPacketListenerImpl.class)
public abstract class ClientCommonNetworkhandlerMix
{
    @Final @Shadow
    protected Minecraft minecraft;
}
