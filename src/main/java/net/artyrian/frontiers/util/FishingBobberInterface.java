package net.artyrian.frontiers.util;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public interface FishingBobberInterface
{
    int getLineColor();
    Identifier getBobberTex();
    RenderLayer getBobberLayer();
}