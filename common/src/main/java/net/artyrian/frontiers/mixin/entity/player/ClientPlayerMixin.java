package net.artyrian.frontiers.mixin.entity.player;

import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.definition.menu.bottled_message.BottledMessageScreen;
import net.artyrian.frontiers.reg.misc.FRDataComponents;
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
    @Shadow @Final protected Minecraft minecraft;

    @Override
    public void frontiers$openBottleScreen(ItemStack stack, InteractionHand hand)
    {
        BottleContentComponent msgC = stack.getComponents().getOrDefault(FRDataComponents.BOTTLE_CONTENT.get(), BottleContentComponent.DEFAULT);
        String msg = msgC.getText();

        this.minecraft.setScreen(new BottledMessageScreen((LocalPlayer)(Object)this, stack, hand, msg));
    }
}
