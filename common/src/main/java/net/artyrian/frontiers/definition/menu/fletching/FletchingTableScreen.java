package net.artyrian.frontiers.definition.menu.fletching;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.projectile.AbstractArrow;

public class FletchingTableScreen extends AbstractContainerScreen<FletchingTableMenu>
{
    private static final ResourceLocation TEXTURE = Frontiers.id("textures/gui/container/fletching_table.png");
    private static final ResourceLocation QUESTION_MARK_TEX = Frontiers.id("container/fletching_table/missingno");

    private ResourceLocation ARROW_TEXTURE = null;

    public FletchingTableScreen(FletchingTableMenu handler, Inventory inventory, Component title)
    {
        super(handler, inventory, title);
    }

    @Override
    public void containerTick()
    {
        super.containerTick();
        this.doTick();
    }

    public void doTick()
    {
        ARROW_TEXTURE = this.menu.getArrowTex();
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);

        this.renderTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY)
    {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        context.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        ResourceLocation texture =
                (ARROW_TEXTURE != null) ? ARROW_TEXTURE : ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png");
        int light = (ARROW_TEXTURE != null) ? 15728880 : 0;

        Lighting.setupForEntityInInventory();
        context.pose().pushPose();

        context.pose().translate((float) x + 34.0F, (float) y + 52.0F, 100.0F);
        context.pose().scale(-55.0F, 55.0F, 55.0F);
        context.pose().mulPose(Axis.ZP.rotationDegrees(45.0F));
        context.pose().mulPose(Axis.YN.rotationDegrees(40.0F));
        this.drawArrow(context.pose(), context.bufferSource(), light, texture);

        context.flush();
        context.pose().popPose();
        Lighting.setupFor3DItems();

        context.pose().pushPose();

        if (ARROW_TEXTURE == null)
        {
            RenderSystem.enableBlend();
            context.blitSprite(QUESTION_MARK_TEX, x + 18, y + 20, 32, 32);
            RenderSystem.disableBlend();
        }

        context.pose().popPose();
    }

    /** Renders an arrow. Uses a modified version of ProjectileEntityRenderer's {@link
     * net.minecraft.client.renderer.entity.ArrowRenderer#render(AbstractArrow, float, float, PoseStack, MultiBufferSource, int) render()}. */
    public void drawArrow(
            PoseStack matrixStack,
            MultiBufferSource vertexConsumerProvider,
            int light,
            ResourceLocation texture
    )
    {
        matrixStack.mulPose(Axis.XP.rotationDegrees(45.0F));
        matrixStack.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStack.translate(-4.0F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutout(texture));

        PoseStack.Pose entry = matrixStack.last();
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
            matrixStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            this.arrowVertex(entry, vertexConsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, light);
            this.arrowVertex(entry, vertexConsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, light);
            this.arrowVertex(entry, vertexConsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, light);
            this.arrowVertex(entry, vertexConsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, light);
        }
    }

    /** Uses the exact same code as ProjectileEntityRenderer's {@link
     * net.minecraft.client.renderer.entity.ArrowRenderer#vertex(PoseStack.Pose, VertexConsumer, int, int, int, float, float, int, int, int, int) vertex()}. */
    private void arrowVertex(
            PoseStack.Pose matrix,
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
        vertexConsumer.addVertex(matrix, (float)x, (float)y, (float)z)
                .setColor(CommonColors.WHITE)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(matrix, (float)normalX, (float)normalY, (float)normalZ);
    }
}
