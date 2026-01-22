package net.artyrian.frontiers.mixin.entity.player;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.client.screen.bottled_message.BottledMessageScreen;
import net.artyrian.frontiers.data.components.BottleContentComponent;
import net.artyrian.frontiers.data.components.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerMixin extends PlayerMixin
{
    @Shadow @Final protected Minecraft client;

    @Override
    public void frontiers$openBottleScreen(ItemStack stack, InteractionHand hand)
    {
        BottleContentComponent msgC = stack.getComponents().getOrDefault(ModDataComponents.BOTTLE_CONTENT, BottleContentComponent.DEFAULT);
        String msg = msgC.getText();

        this.client.setScreen(new BottledMessageScreen((LocalPlayer)(Object)this, stack, hand, msg));
    }
}
