package net.artyrian.frontiers.definition.menu.curse;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.renderer.CurseAltarBlockEntityRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class CurseAltarScreen extends AbstractContainerScreen<CurseAltarMenu>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/container/curse_altar.png");

    private static final ResourceLocation BAR = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/bar");

    static final ResourceLocation BUTTON_DISABLED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/button_disabled");
    static final ResourceLocation BUTTON_ENABLED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/button_enabled");
    static final ResourceLocation BUTTON_HOVER = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/button_hover");
    static final ResourceLocation ARROW_DENIED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/nocando");
    static final ResourceLocation ARROW_DONE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/done");

    static final ResourceLocation TABLET_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet.png");
    static final ResourceLocation TABLET_GLOW_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet_glow.png");

    private static final ResourceLocation SGA = ResourceLocation.withDefaultNamespace("alt");
    private static final Style SGA_STYLE = Style.EMPTY.withFont(SGA);

    private static final int BUT_W = 64;
    private static final int BUT_H = 16;
    private static final int EYE_W = 26;
    private static final int EYE_H = 11;

    public static final int REQUIRED_XP = 30;
    private final ModelPart tablet;
    private float glowAlpha = 0.0F;

    private final Component DISPLAY_TEXT;

    public CurseAltarScreen(CurseAltarMenu handler, Inventory inventory, Component title)
    {
        super(handler, inventory, title);
        this.DISPLAY_TEXT = Component.translatable("container.frontiers.curse_altar.uncurse").withStyle(SGA_STYLE);
        this.tablet = CurseAltarBlockEntityRenderer.getTexModel().bakeRoot();
    }

    @Override
    public void containerTick()
    {
        super.containerTick();
        this.doTick();
    }

    public void doTick()
    {
        this.menu.doEyeTick();

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        boolean toolPresent = (toolStack != null && this.menu.hasCurses(toolStack));

        if (toolPresent)
        {
            if (this.glowAlpha + 0.2F < 1.0F) { this.glowAlpha += 0.2F; }
            else { this.glowAlpha = 1.0F; }
        }
        else
        {
            if (this.glowAlpha - 0.2F > 0.0F) { this.glowAlpha -= 0.2F; }
            else { this.glowAlpha = 0.0F; }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        int xp = (this.minecraft != null && this.minecraft.player != null) ? this.minecraft.player.experienceLevel : 0;
        boolean is_creative = this.minecraft != null && this.minecraft.player != null && this.minecraft.player.getAbilities().instabuild;

        boolean toolPresent = (toolStack != null && this.menu.hasCurses(toolStack));

        if (toolPresent)
        {
            int drawx = x + 85;
            int drawy = y + 48;
            int butw = 66;
            int buth = 19;

            if (xp >= REQUIRED_XP || is_creative)
            {
                double xx = mouseX - (double)drawx;
                double yy = mouseY - (double)drawy;
                if (xx >= 0 && yy >= 0 && xx < butw && yy < buth && this.menu.clickMenuButton(this.minecraft.player, 0))
                {
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 0);
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);
        this.renderTooltip(context, mouseX, mouseY);

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        int xp = (this.minecraft != null && this.minecraft.player != null) ? this.minecraft.player.experienceLevel : 0;
        boolean is_creative = this.minecraft != null && this.minecraft.player != null && this.minecraft.player.getAbilities().instabuild;

        if (this.menu.getCharges() > 0)
        {
            float xri = (this.menu.getCharges() / 20.0F);
            int xr2 = (xri == 1) ? 121 : (int)(121.0F * xri);
            context.blitSprite(BAR, 121, 5, 0, 0, leftPos + 47, topPos + 6, xr2, 5);
        }

        boolean toolPresent = (toolStack != null && this.menu.hasCurses(toolStack));

        if (toolPresent)
        {
            int drawx = leftPos + 85;
            int drawy = topPos + 48;
            int butw = 66;
            int buth = 19;

            if (xp < REQUIRED_XP && !is_creative)
            {
                int xx = mouseX - drawx;
                int yy = mouseY - drawy;
                if (xx >= 0 && yy >= 0 && xx < butw && yy < buth)
                {
                    context.renderTooltip(this.font, Component.translatable("container.frontiers.curse_altar.levelcount", REQUIRED_XP).withStyle(ChatFormatting.RED), mouseX, mouseY);
                }
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY)
    {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        context.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.getCharges() > 0) this.drawTablet(context, x - 14, y, delta);

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        int xp = (this.minecraft != null && this.minecraft.player != null) ? this.minecraft.player.experienceLevel : 0;
        boolean is_creative = this.minecraft != null && this.minecraft.player != null && this.minecraft.player.getAbilities().instabuild;

        boolean toolPresent = (toolStack != null && this.menu.hasCurses(toolStack));
        boolean showX = false;

        RenderSystem.enableBlend();

        int basex = x + 98;
        int basey = y + 16;
        int drawy;
        for (int i = 0; i < 4; i++)
        {
            drawy = basey + (BUT_H * i);

            context.blitSprite(BUTTON_DISABLED, basex, drawy, BUT_W, BUT_H);
        }

        RenderSystem.disableBlend();

        if (toolPresent)
        {
            int textColor;

            RenderSystem.enableBlend();

            //if (xp >= REQUIRED_XP || is_creative)
            //{
            //    int xx = mouseX - drawx;
            //    int yy = mouseY - drawy;
            //    if (xx >= 0 && yy >= 0 && xx < butw && yy < buth)
            //    {
            //        context.blitSprite(BUTTON_HOVER, drawx, drawy, butw, buth);
            //        textColor = CommonColors.WHITE;
            //    }
            //    else
            //    {
            //        context.blitSprite(BUTTON_ENABLED, drawx, drawy, butw, buth);
            //        textColor = 0xFFC8FF8F;
            //    }
            //}
            //else
            //{
            //    context.blitSprite(BUTTON_DISABLED, drawx, drawy, butw, buth);
            //    textColor = 0xFF8C605D;
            //    showX = true;
            //}

            RenderSystem.disableBlend();

            //context.drawString(this.font, this.DISPLAY_TEXT, x + 91, y + 54, textColor);
        }

        boolean toolWithoutCurse = (!toolPresent && toolStack != null && !toolStack.isEmpty() && !this.menu.hasCurses(toolStack));

        if (toolWithoutCurse)
        {
            RenderSystem.enableBlend();
            context.blitSprite(ARROW_DONE, x + 107, y + 23, 22, 22);
            RenderSystem.disableBlend();
        }
        else if (showX)
        {
            RenderSystem.enableBlend();
            context.blitSprite(ARROW_DENIED, x + 107, y + 23, 22, 22);
            RenderSystem.disableBlend();
        }
    }

    private void drawTablet(GuiGraphics context, int x, int y, float delta)
    {
        Lighting.setupForEntityInInventory();
        context.pose().pushPose();

        context.pose().translate((float)x + 45.0F, (float)y + 46.0F, 100.0F);
        context.pose().scale(-55.0F, 55.0F, 55.0F);
        context.pose().mulPose(Axis.ZP.rotationDegrees(180.0F)); // Rotates L/R
        context.pose().mulPose(Axis.YP.rotationDegrees(-90.0F));
        context.pose().mulPose(Axis.ZP.rotationDegrees(30.0F));

        VertexConsumer vertexConsumer = context.bufferSource().getBuffer(RenderType.entityCutout(TABLET_TEX));
        this.tablet.render(context.pose(), vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY);

        vertexConsumer = context.bufferSource().getBuffer(RenderType.entityTranslucent(TABLET_GLOW_TEX));
        int color1 = FastColor.ARGB32.lerp(this.glowAlpha, FastColor.ARGB32.color(Mth.floor(0.0F), -1), -1);
        this.tablet.render(context.pose(), vertexConsumer, 15728880, OverlayTexture.NO_OVERLAY, color1);

        context.flush();
        context.pose().popPose();
        Lighting.setupFor3DItems();
    }
}
