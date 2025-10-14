package net.artyrian.frontiers.entity.renderer.passive;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.passive.CrowEntity;
import net.artyrian.frontiers.rendering.ModModelLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class CrowEntityRenderer extends MobEntityRenderer<CrowEntity, CrowModel>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/crow/crow.png");
    private static final Identifier TEXTURE_DOOM = Identifier.of(Frontiers.MOD_ID, "textures/entity/crow/crow_doom.png");

    public CrowEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context, new CrowModel(context.getPart(ModModelLayers.CROW)), 0.3F);
    }

    public Identifier getTexture(CrowEntity crow)
    {
        String name = Formatting.strip(crow.getName().getString());
        if (name.equals("Doom")) return TEXTURE_DOOM;
        return TEXTURE;
    }

    public float getAnimationProgress(CrowEntity crowCaKaw, float f)
    {
        float g = MathHelper.lerp(f, crowCaKaw.prevFlapProgress, crowCaKaw.flapProgress);
        float h = MathHelper.lerp(f, crowCaKaw.prevMaxWingDeviation, crowCaKaw.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}
