package net.artyrian.frontiers.block.entity.renderer;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.CurseAltarBlockEntity;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.w3c.dom.css.RGBColor;

public class CurseAltarBlockEntityRenderer implements BlockEntityRenderer<CurseAltarBlockEntity>
{
    //public static final SpriteIdentifier TABLET_TEXTURE = new SpriteIdentifier(
    //        SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/curse_altar_tablet")
    //);
    //public static final SpriteIdentifier GLOW_TEXTURE = new SpriteIdentifier(
    //        SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(Frontiers.MOD_ID, "entity/curse_altar_tablet_glow")
    //);
    static final Identifier TABLET_TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet.png");
    static final Identifier GLOW_TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet_glow.png");
    private final ModelPart tablet;
    private final ModelPart overlay;

    public CurseAltarBlockEntityRenderer(BlockEntityRendererFactory.Context context)
    {
        this.tablet = getTexModel().createModel();
        this.overlay = this.tablet.getChild("tabmain").getChild("overlay");
    }

    @Override
    // Made with Blockbench 4.12.4 and hand written into java because lol lmao
    // https://www.youtube.com/watch?v=n_n8toR4Bxo - artyrian
    public void render(CurseAltarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
    {
        matrices.push();
        matrices.translate(0.5F, 0.75F, 0.5F);

        float g = (float)entity.ticks + tickDelta;
        matrices.translate(0.0F, 0.1F + MathHelper.sin(g * 0.1F) * 0.01F, 0.0F);
        float h = entity.tabletRotation - entity.lastTabletRotation;

        while (h >= (float) Math.PI) { h -= (float) (Math.PI * 2); }
        while (h < (float) -Math.PI) { h += (float) (Math.PI * 2); }

        float k = entity.lastTabletRotation + h * tickDelta;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation(-k));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(80.0F));

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(TABLET_TEXTURE));
        this.tablet.render(matrices, vertexConsumer, light, overlay, -1);
        vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(GLOW_TEXTURE));
        int colornew = ColorHelper.Argb.lerp(entity.tabletGlow, ColorHelper.Argb.withAlpha(MathHelper.floor(0.0F), -1), -1);
        this.tablet.render(matrices, vertexConsumer, 255, overlay, colornew);

        matrices.pop();
    }

    public static TexturedModelData getTexModel()
    {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData tabmain = modelPartData.addChild("tabmain", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData cube = tabmain
                .addChild("cube",
                        ModelPartBuilder.create()
                                .uv(0, 0)
                                .cuboid(-4.0F, -3.0F, -6.0F, 8.0F, 2.0F, 12.0F, new Dilation(0.0F)),
                        ModelTransform.rotation(0.0F, (float) (Math.PI / 2), 1.5F));
        ModelPartData overlay = tabmain
                .addChild("overlay",
                        ModelPartBuilder.create()
                                .uv(0, 18)
                                .cuboid(-4.0F, -3.0F, -6.0F, 8.0F, 2.0F, 12.0F, new Dilation(0.0F)),
                        ModelTransform.rotation(0.0F, (float) (Math.PI / 2), 1.5F));
        return TexturedModelData.of(modelData, 48, 48);
    }
}
