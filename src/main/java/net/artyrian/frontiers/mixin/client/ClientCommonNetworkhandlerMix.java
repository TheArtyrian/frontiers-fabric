package net.artyrian.frontiers.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientCommonNetworkHandler.class)
public abstract class ClientCommonNetworkhandlerMix
{
    @Final @Shadow
    protected MinecraftClient client;
}
