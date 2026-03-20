package net.artyrian.frontiers.mixin_intf;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.resources.ResourceLocation;

public interface ParrotRenderIntf
{
    public final ResourceLocation KAZOOIE_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/parrot/parrot_kazooie.png");
    public final ResourceLocation LOVEBIRB_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/parrot/parrot_lb.png");
    public final ResourceLocation KEYNIS_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/parrot/parrot_keynis.png");

    public abstract ResourceLocation frontiers$getTextureFromName(ResourceLocation og, String name);
}
