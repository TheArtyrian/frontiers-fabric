package net.artyrian.frontiers.mixin_interfaces;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.util.Identifier;

public interface ParrotRenderMixInterface
{
    public final Identifier KAZOOIE_TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/parrot/parrot_kazooie.png");
    public final Identifier LOVEBIRB_TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/parrot/parrot_lb.png");

    public abstract Identifier frontiers$getTextureFromName(Identifier og, String name);
}
