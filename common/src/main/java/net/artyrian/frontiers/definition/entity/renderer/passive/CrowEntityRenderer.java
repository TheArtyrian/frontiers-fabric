package net.artyrian.frontiers.definition.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.passive.CrowEntity;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CrowEntityRenderer extends MobRenderer<CrowEntity, CrowModel>
{
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/entity/crow/crow.png");
    private static final ResourceLocation TEXTURE_DOOM = Frontiers.id("textures/entity/crow/crow_doom.png");

    public CrowEntityRenderer(EntityRendererProvider.Context context)
    {
        super(context, new CrowModel(context.bakeLayer(FRRegistries.ModelLayers.CROW)), 0.3F);
    }

    public ResourceLocation getTextureLocation(CrowEntity crow)
    {
        String name = ChatFormatting.stripFormatting(crow.getName().getString());
        if (name.equals("Doom")) return TEXTURE_DOOM;
        return TEXTURE;
    }

    public float getBob(CrowEntity crowCaKaw, float f)
    {
        float g = Mth.lerp(f, crowCaKaw.prevFlapProgress, crowCaKaw.flapProgress);
        float h = Mth.lerp(f, crowCaKaw.prevMaxWingDeviation, crowCaKaw.maxWingDeviation);
        return (Mth.sin(g) + 1.0F) * h;
    }
}
