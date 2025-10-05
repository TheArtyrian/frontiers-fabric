package net.artyrian.frontiers.entity.renderer.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.misc.ManaOrbEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ManaOrbEntityRenderer extends EntityRenderer<ManaOrbEntity>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/mana_orb.png");
    private static final RenderLayer LAYER = RenderLayer.getItemEntityTranslucentCull(TEXTURE);

    public ManaOrbEntityRenderer(EntityRendererFactory.Context context)
    {
        super(context);
        this.shadowRadius = 0.15F;
        this.shadowOpacity = 0.75F;
    }

    protected int getBlockLight(ManaOrbEntity manaOrb, BlockPos blockPos)
    {
        return MathHelper.clamp(super.getBlockLight(manaOrb, blockPos) + 7, 0, 15);
    }

    public void render(ManaOrbEntity manaOrb, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i)
    {
        // What even goes on
        matrixStack.push();
        int j = manaOrb.getOrbSize();
        float h = (float)(j % 4 * 16) / 64.0F;
        float k = (float)(j % 4 * 16 + 16) / 64.0F;
        float l = (float)(j / 4 * 16) / 64.0F;
        float m = (float)(j / 4 * 16 + 16) / 64.0F;

        float r = (manaOrb.age + g) / 4.0F;
        int s = (int)((MathHelper.sin(r + 0.0F) + 1.0F) * 0.5F * 255.0F);

        matrixStack.translate(0.0F, 0.1F, 0.0F);
        matrixStack.multiply(this.dispatcher.getRotation());
        matrixStack.scale(0.3F, 0.3F, 0.3F);

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
        MatrixStack.Entry entry = matrixStack.peek();

        int red = Math.clamp(s - 64, 16, 255);
        int green = Math.clamp(s + 128, 16, 128);
        vertex(vertexConsumer, entry, -0.5F, -0.25F, red, green, 255, h, m, i);
        vertex(vertexConsumer, entry, 0.5F, -0.25F, green, red, 255, k, m, i);
        vertex(vertexConsumer, entry, 0.5F, 0.75F, red, green, 255, k, l, i);
        vertex(vertexConsumer, entry, -0.5F, 0.75F, green, red, 255, h, l, i);

        matrixStack.pop();
        super.render(manaOrb, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static void vertex(
            VertexConsumer vertexConsumer, MatrixStack.Entry matrix, float x, float y, int red, int green, int blue, float u, float v, int light
    )
    {
        vertexConsumer.vertex(matrix, x, y, 0.0F)
                .color(red, green, blue, 196)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(matrix, 0.0F, 1.0F, 0.0F);
    }

    public Identifier getTexture(ManaOrbEntity orb) {
        return TEXTURE;
    }
}