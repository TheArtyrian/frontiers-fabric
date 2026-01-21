package net.vertisoft.vectorlib.agnostic.registrars;

import net.minecraft.client.gui.components.SplashRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class VectorSplashReg
{
    private final Map<? extends SplashRenderer, Supplier<Boolean>> CONDITIONAL_SPLASHER = new HashMap<>();
}
