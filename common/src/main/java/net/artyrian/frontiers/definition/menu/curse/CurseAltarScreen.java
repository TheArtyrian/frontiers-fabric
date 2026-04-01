package net.artyrian.frontiers.definition.menu.curse;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.renderer.CurseAltarBlockEntityRenderer;
import net.artyrian.frontiers.definition.networking.packet.server.ServerboundCurseAltarPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.vertisoft.vectorlib.VectorLib;

import java.util.List;

public class CurseAltarScreen extends AbstractContainerScreen<CurseAltarMenu>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/gui/container/curse_altar.png");

    private static final ResourceLocation BAR = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/bar");

    private static final ResourceLocation ARROW_DISABLED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/arrow_disabled");
    private static final ResourceLocation ARROW_UP = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/up_arrow");
    private static final ResourceLocation ARROW_UP_ON = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/up_arrow_selected");
    private static final ResourceLocation ARROW_DOWN = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/down_arrow");
    private static final ResourceLocation ARROW_DOWN_ON = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/down_arrow_selected");

    static final ResourceLocation BUTTON_DISABLED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/button_disabled");
    static final ResourceLocation BUTTON_ENABLED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/button_enabled");
    static final ResourceLocation BUTTON_HOVER = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/button_hover");

    static final ResourceLocation EYE_OFF = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/eye_off");
    static final ResourceLocation EYE_REG = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/eye_reg");
    static final ResourceLocation EYE_LEFT = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/eye_left");
    static final ResourceLocation EYE_RIGHT = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/eye_right");
    static final ResourceLocation EYE_GLEE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/eye_gleeful");
    static final ResourceLocation EYE_SHOCKED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/eye_shocked");
    static final ResourceLocation EYE_DENIED = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/nocando");
    static final ResourceLocation EYE_DONE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/done");

    static final ResourceLocation CHARGE_COST = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/cost");
    static final ResourceLocation CHARGE_COST_OFF = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "container/curse_altar/cost_disabled");

    static final ResourceLocation TABLET_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet.png");
    static final ResourceLocation TABLET_GLOW_TEX = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet_glow.png");

    private static final int BUT_W = 67;
    private static final int BUT_H = 16;
    private static final int EYE_W = 26;
    private static final int EYE_H = 11;
    private static final int ARROW_W = 9;
    private static final int ARROW_H = 16;

    private static final int BUT_BASE_X = 91;
    private static final int BUT_BASE_Y = 16;
    private static final int ARROW_BASE_X = 159;
    private static final int ARROW_UP_BASE_Y = 16;
    private static final int ARROW_DOWN_BASE_Y = 64;

    private final ModelPart tablet;

    private float glowAlpha = 0.0F;
    private int eyeFinishTime = 0;
    private int cooldownTime = 0;

    public CurseAltarScreen(CurseAltarMenu handler, Inventory inventory, Component title)
    {
        super(handler, inventory, title);
        this.tablet = CurseAltarBlockEntityRenderer.getTexModel().bakeRoot();
    }

    @Override
    public void containerTick()
    {
        super.containerTick();

        if (this.eyeFinishTime > 0) this.eyeFinishTime--;
        if (this.cooldownTime > 0) this.cooldownTime--;

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        boolean toolPresent = (!toolStack.isEmpty() && this.menu.canBePurified(toolStack));

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
        if (this.minecraft == null || this.minecraft.player == null || this.minecraft.gameMode == null) return super.mouseClicked(mouseX, mouseY, button);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        boolean toolPresent = (!toolStack.isEmpty() && this.menu.canBePurified(toolStack) && !this.menu.arePagesEmpty());

        if (toolPresent)
        {
            // Buttons
            int basex = x + BUT_BASE_X;
            int basey = y + BUT_BASE_Y;
            double xx1;
            double yy1;
            int drawy1;

            for (int i = 0; i < 4; i++)
            {
                drawy1 = basey + (BUT_H * i);
                xx1 = mouseX - (double)basex;
                yy1 = mouseY - (double)drawy1;

                if (
                        xx1 >= 0 && yy1 >= 0 && xx1 < BUT_W && yy1 < BUT_H
                        && this.cooldownTime <= 0
                        && this.menu.playerCanEnchantCurrent(this.minecraft.player, i)
                )
                {
                    List<CurseEnchantInst> page = this.menu.getCurrentPage();
                    if (i < page.size())
                    {
                        CurseEnchantInst inst = page.get(i);
                        this.eyeFinishTime = 80;
                        this.cooldownTime = 30;
                        VectorLib.client().sendViaGamemode(this.minecraft.gameMode, new ServerboundCurseAltarPacket(inst));
                        this.menu.postPurify();
                        return true;
                    }
                    return false;
                }
            }

            int arrowX = x + ARROW_BASE_X;
            int uparrowY = y + ARROW_UP_BASE_Y;
            int downarrowY = y + ARROW_DOWN_BASE_Y;
            double xx2 = mouseX - (double)arrowX;
            double yy2 = mouseY - (double)uparrowY;
            double yy3 = mouseY - (double)downarrowY;

            // Up Arrow
            if (xx2 >= 0 && yy2 >= 0 && xx2 < ARROW_W && yy2 < ARROW_H && !this.menu.onFirstPage() && this.menu.clickMenuButton(this.minecraft.player, 4))
            {
                this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }

            // Down arrow
            if (xx2 >= 0 && yy3 >= 0 && xx2 < ARROW_W && yy3 < ARROW_H && !this.menu.onLastPage() && this.menu.clickMenuButton(this.minecraft.player, 5))
            {
                this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);

        // Charge bar
        if (this.menu.getCharges() > 0)
        {
            float xri = (this.menu.getCharges() / 20.0F);
            int xr2 = (xri == 1) ? 121 : (int)(121.0F * xri);
            context.blitSprite(BAR, 121, 5, 0, 0, leftPos + 47, topPos + 6, xr2, 5);
        }

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        boolean toolPresent = (!toolStack.isEmpty() && this.menu.canBePurified(toolStack));

        if (!this.menu.arePagesEmpty() && toolPresent)
        {
            int basex = this.leftPos + BUT_BASE_X;
            int basey = this.topPos + BUT_BASE_Y;
            int drawy;
            List<CurseEnchantInst> enchantInstances = this.menu.getCurrentPage();

            for (int i = 0; i < 4; i++)
            {
                drawy = basey + (BUT_H * i);
                double xx = mouseX - (double) basex;
                double yy = mouseY - (double) drawy;

                if (xx >= 0 && yy >= 0 && xx < BUT_W && yy < BUT_H && i < enchantInstances.size())
                {
                    List<Component> list = Lists.newArrayList();
                    if (this.cooldownTime > 0)
                    {
                        list.add(Component.translatable("container.frontiers.curse_altar.cooldown").withStyle(ChatFormatting.RED));
                    }
                    else
                    {
                        CurseEnchantInst inst = enchantInstances.get(i);
                        list.add(inst.getText());
                        if (this.minecraft != null && !this.menu.playerCanEnchantCurrent(this.minecraft.player, i))
                        {
                            list.add(CommonComponents.EMPTY);
                            list.add(Component.translatable("container.frontiers.curse_altar.level_cost", inst.cost())
                                    .withStyle(this.minecraft.player.experienceLevel >= inst.cost() ? ChatFormatting.GRAY : ChatFormatting.RED));
                            list.add(Component.translatable("container.frontiers.curse_altar.charge_cost", inst.chargeCost())
                                    .withStyle(this.menu.hasEnoughCharges(i) ? ChatFormatting.GRAY : ChatFormatting.RED));
                        }
                    }

                    context.renderComponentTooltip(this.font, list, mouseX, mouseY);
                }
            }
        }

        // Tooltips
        this.renderTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics context, float delta, int mouseX, int mouseY)
    {
        if (this.minecraft == null || this.minecraft.player == null) return;

        CursedNames.get().seedUp(this.menu.seed, this.menu.getScrollPage() * 4);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        boolean eyeGleeful = false;

        ItemStack toolStack = this.menu.getSlot(0).getItem();
        boolean toolPresent = (!toolStack.isEmpty()  && this.menu.canBePurified(toolStack));
        boolean hasCharges = (this.menu.getCharges() > 0);

        // BG
        context.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // Tablet
        if (hasCharges) this.drawTablet(context, x - 14, y, delta);

        RenderSystem.enableBlend();

        // Buttons
        int basex = x + BUT_BASE_X;
        int basey = y + BUT_BASE_Y;
        int drawy;
        boolean canEnchant;
        for (int i = 0; i < 4; i++)
        {
            drawy = basey + (BUT_H * i);

            if (!toolPresent || this.menu.arePagesEmpty())
            {
                context.blitSprite(BUTTON_DISABLED, basex, drawy, BUT_W, BUT_H);
            }
            else
            {
                canEnchant = this.menu.playerCanEnchantCurrent(this.minecraft.player, i);
                double xx = mouseX - (double)basex;
                double yy = mouseY - (double)drawy;

                int textColor = 0xFFE6331F;
                int outlineColor = 0xFF78001D;
                int xpCol = 8453920;
                boolean drawOutline = false;
                ResourceLocation button = BUTTON_ENABLED;
                ResourceLocation costSprite = CHARGE_COST;

                if (canEnchant && hasCharges && this.cooldownTime <= 0)
                {
                    if (xx >= 0 && yy >= 0 && xx < BUT_W && yy < BUT_H)
                    {
                        button = BUTTON_HOVER;
                        eyeGleeful = true;
                        textColor = 0xFFFCFC7E;
                        outlineColor = CommonColors.WHITE;
                        drawOutline = true;
                    }
                    else drawOutline = true;
                }
                else
                {
                    button = BUTTON_DISABLED;
                    costSprite = CHARGE_COST_OFF;
                    textColor = 0xFF332E25;
                    xpCol = 4226832;
                }

                context.blitSprite(button, basex, drawy, BUT_W, BUT_H);

                List<CurseEnchantInst> enchantInstances = this.menu.getCurrentPage();
                if (i < enchantInstances.size())
                {
                    CurseEnchantInst instance = enchantInstances.get(i);
                    MutableComponent component = CursedNames.get().goMyCurse();

                    int pad = 8;
                    if (drawOutline)
                    {
                        context.drawString(this.font, component, basex + pad - 1, drawy + 3, outlineColor, false);
                        context.drawString(this.font, component, basex + pad + 1, drawy + 3, outlineColor, false);
                        context.drawString(this.font, component, basex + pad, drawy + 3, outlineColor, false);
                        context.drawString(this.font, component, basex + pad - 1, drawy + 5, outlineColor, false);
                        context.drawString(this.font, component, basex + pad + 1, drawy + 5, outlineColor, false);
                        context.drawString(this.font, component, basex + pad, drawy + 5, outlineColor, false);
                        context.drawString(this.font, component, basex + pad - 1, drawy + 4, outlineColor, false);
                        context.drawString(this.font, component, basex + pad + 1, drawy + 4, outlineColor, false);
                    }
                    context.drawString(this.font, component, basex + pad, drawy + 4, textColor, false);

                    for (int j = 0; j < instance.chargeCost(); j++) context.blitSprite(costSprite, basex + 2, drawy + 2 + (3 * j), 2, 2);

                    String lvl = String.valueOf(instance.cost());
                    int xOffSet = this.font.width(lvl) + 2;
                    context.drawString(this.font, lvl, basex + BUT_W - xOffSet, drawy + 6, xpCol);
                }
            }
        }

        // Arrows
        int arrowX = x + ARROW_BASE_X;
        int uparrowY = y + ARROW_UP_BASE_Y;
        int downarrowY = y + ARROW_DOWN_BASE_Y;
        double xx = mouseX - (double)arrowX;
        ResourceLocation upArrow = ARROW_DISABLED;
        ResourceLocation downArrow = ARROW_DISABLED;

        if (toolPresent && !this.menu.arePagesEmpty())
        {
            double yy1 = mouseY - (double)uparrowY;
            double yy2 = mouseY - (double)downarrowY;

            // Up
            if (!this.menu.onFirstPage())
            {
                if (xx >= 0 && yy1 >= 0 && xx < ARROW_W && yy1 < ARROW_H) upArrow = ARROW_UP_ON;
                else upArrow = ARROW_UP;
            }

            // Down
            if (!this.menu.onLastPage())
            {
                if (xx >= 0 && yy2 >= 0 && xx < ARROW_W && yy2 < ARROW_H) downArrow = ARROW_DOWN_ON;
                else downArrow = ARROW_DOWN;
            }
        }

        context.blitSprite(upArrow, arrowX, uparrowY, ARROW_W, ARROW_H);
        context.blitSprite(downArrow, arrowX, downarrowY, ARROW_W, ARROW_H);

        // Eye
        ResourceLocation arrowToDraw = EYE_OFF;

        if (hasCharges)
        {
            if (this.eyeFinishTime > 0) arrowToDraw = EYE_DONE;
            else if (!toolStack.isEmpty())
            {
                if (toolStack.is(Items.END_CRYSTAL)) arrowToDraw = EYE_SHOCKED;
                else if (!this.menu.canBePurified(toolStack)) arrowToDraw = EYE_DENIED;
                else
                {
                    if (mouseX <= x + 48) arrowToDraw = EYE_LEFT;
                    else if (mouseX >= x + 97) arrowToDraw = (eyeGleeful) ? EYE_GLEE : EYE_RIGHT;
                    else arrowToDraw = EYE_REG;
                }
            }
            else arrowToDraw = EYE_DENIED;
        }

        context.blitSprite(arrowToDraw, x + 54, y + 42, EYE_W, EYE_H);

        RenderSystem.disableBlend();
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
