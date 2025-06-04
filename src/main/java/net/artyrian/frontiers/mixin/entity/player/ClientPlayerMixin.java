package net.artyrian.frontiers.mixin.entity.player;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.client.screen.bottled_message.BottledMessageScreen;
import net.artyrian.frontiers.data.components.BottleContentComponent;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerMixin extends PlayerMixin
{
    @Shadow @Final protected MinecraftClient client;

    @Override
    public void frontiers$openBottleScreen(ItemStack stack, Hand hand)
    {
        BottleContentComponent msgC = stack.getComponents().getOrDefault(ModDataComponents.BOTTLE_CONTENT, BottleContentComponent.DEFAULT);
        String msg = msgC.getText();

        this.client.setScreen(new BottledMessageScreen((ClientPlayerEntity)(Object)this, stack, hand, msg));
    }
}
