package net.artyrian.frontiers.mixin_intf;

import net.minecraft.client.gui.GuiGraphics;

public interface GuiIntf
{
    void frontiersML$accessibleFromAllRenderSanity(GuiGraphics context);

    void frontiersML$flashMana(int data);
    boolean frontiersML$isDoingManaFlash();
}
