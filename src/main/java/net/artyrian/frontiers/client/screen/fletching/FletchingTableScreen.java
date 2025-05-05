package net.artyrian.frontiers.client.screen.fletching;

import com.mojang.blaze3d.systems.RenderSystem;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class FletchingTableScreen extends HandledScreen<FletchingTableScreenHandler>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/gui/container/fletching_table.png");
    private static final Identifier QUESTION_MARK_TEX = Identifier.of(Frontiers.MOD_ID, "container/fletching_table/missingno");

    private Identifier ARROW_TEXTURE = null;

    public FletchingTableScreen(FletchingTableScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
    }

    @Override
    public void handledScreenTick()
    {
        super.handledScreenTick();
        this.doTick();
    }

    public void doTick()
    {
        ARROW_TEXTURE = this.handler.getArrowTex();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);

        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);

        Identifier texture =
                (ARROW_TEXTURE != null) ? ARROW_TEXTURE : Identifier.ofVanilla("textures/entity/projectiles/arrow.png");
        int light = (ARROW_TEXTURE != null) ? 15728880 : 0;

        DiffuseLighting.method_34742();
        context.getMatrices().push();

        context.getMatrices().translate((float) x + 34.0F, (float) y + 52.0F, 100.0F);
        context.getMatrices().scale(-55.0F, 55.0F, 55.0F);
        context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45.0F));
        context.getMatrices().multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(40.0F));
        this.drawArrow(context.getMatrices(), context.getVertexConsumers(), light, texture);

        context.draw();
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();

        context.getMatrices().push();

        if (ARROW_TEXTURE == null)
        {
            RenderSystem.enableBlend();
            context.drawGuiTexture(QUESTION_MARK_TEX, x + 18, y + 20, 32, 32);
            RenderSystem.disableBlend();
        }

        context.getMatrices().pop();
    }

    /** Renders an arrow. Uses a modified version of ProjectileEntityRenderer's {@link
     * net.minecraft.client.render.entity.ProjectileEntityRenderer#render(PersistentProjectileEntity, float, float, MatrixStack, VertexConsumerProvider, int) render()}. */
    public void drawArrow(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int light,
            Identifier texture
    )
    {
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(45.0F));
        matrixStack.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutout(texture));

        MatrixStack.Entry entry = matrixStack.peek();
        this.arrowVertex(entry, vertexConsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, light);
        this.arrowVertex(entry, vertexConsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, light);

        for (int u = 0; u < 4; u++)
        {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
            this.arrowVertex(entry, vertexConsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, light);
            this.arrowVertex(entry, vertexConsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, light);
            this.arrowVertex(entry, vertexConsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, light);
            this.arrowVertex(entry, vertexConsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, light);
        }
    }

    /** Uses the exact same code as ProjectileEntityRenderer's {@link
     * net.minecraft.client.render.entity.ProjectileEntityRenderer#vertex(MatrixStack.Entry, VertexConsumer, int, int, int, float, float, int, int, int, int) vertex()}. */
    private void arrowVertex(
            MatrixStack.Entry matrix,
            VertexConsumer vertexConsumer,
            int x,
            int y,
            int z,
            float u,
            float v,
            int normalX,
            int normalZ,
            int normalY,
            int light
    )
    {
        vertexConsumer.vertex(matrix, (float)x, (float)y, (float)z)
                .color(Colors.WHITE)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(light)
                .normal(matrix, (float)normalX, (float)normalY, (float)normalZ);
    }
}
