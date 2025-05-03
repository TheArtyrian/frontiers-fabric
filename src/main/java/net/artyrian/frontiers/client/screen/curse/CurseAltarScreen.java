package net.artyrian.frontiers.client.screen.curse;

import com.mojang.blaze3d.systems.RenderSystem;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.entity.renderer.CurseAltarBlockEntityRenderer;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class CurseAltarScreen extends HandledScreen<CurseAltarScreenHandler>
{
    private static final Identifier TEXTURE = Identifier.of(Frontiers.MOD_ID, "textures/gui/container/curse_altar.png");

    static final Identifier BUTTON_DISABLED = Identifier.of(Frontiers.MOD_ID, "container/curse_altar/button_disabled");
    static final Identifier BUTTON_ENABLED = Identifier.of(Frontiers.MOD_ID, "container/curse_altar/button_enabled");
    static final Identifier BUTTON_HOVER = Identifier.of(Frontiers.MOD_ID, "container/curse_altar/button_hover");
    static final Identifier ARROW_DENIED = Identifier.of(Frontiers.MOD_ID, "container/curse_altar/nocando");

    static final Identifier TABLET_TEX = Identifier.of(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet.png");
    static final Identifier TABLET_GLOW_TEX = Identifier.of(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet_glow.png");
    static final Identifier TABLET_XP_TEX = Identifier.of(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet_xp.png");
    static final Identifier TABLET_XP_GLOW_TEX = Identifier.of(Frontiers.MOD_ID, "textures/entity/curse_altar_tablet_xp_glow.png");

    private static final Identifier SGA = Identifier.ofVanilla("alt");
    private static final Style SGA_STYLE = Style.EMPTY.withFont(SGA);

    public static final int REQUIRED_XP = 30;
    private final ModelPart tablet;
    public float glowAlpha = 0.0F;
    public float expAlpha = 0.0F;

    private final Text DISPLAY_TEXT;

    public CurseAltarScreen(CurseAltarScreenHandler handler, PlayerInventory inventory, Text title)
    {
        super(handler, inventory, title);
        this.DISPLAY_TEXT = Text.translatable("container.frontiers.curse_altar.uncurse").fillStyle(SGA_STYLE);
        this.tablet = CurseAltarBlockEntityRenderer.getTexModel().createModel();
    }

    @Override
    public void handledScreenTick() {
        super.handledScreenTick();
        this.doTick();
    }

    public void doTick()
    {
        ItemStack toolStack = this.handler.getSlot(0).getStack();
        ItemStack tabletStack = this.handler.getSlot(1).getStack();

        boolean toolPresent = (toolStack != null && this.handler.hasCurses(toolStack));
        boolean tabletPresent = (tabletStack != null && tabletStack.isOf(ModItem.CURSED_TABLET));
        int xp = (this.client != null && this.client.player != null) ? this.client.player.experienceLevel : 0;
        boolean is_creative = this.client != null && this.client.player != null && this.client.player.getAbilities().creativeMode;

        if (toolPresent && tabletPresent && (xp >= REQUIRED_XP || is_creative))
        {
            if (this.expAlpha + 0.2F < 1.0F) { this.expAlpha += 0.2F; }
            else { this.expAlpha = 1.0F; }
        }
        else
        {
            if (this.expAlpha - 0.2F > 0.0F) { this.expAlpha -= 0.2F; }
            else { this.expAlpha = 0.0F; }
        }


        if (tabletPresent)
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
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        ItemStack toolStack = this.handler.getSlot(0).getStack();
        ItemStack tabletStack = this.handler.getSlot(1).getStack();
        int xp = (this.client != null && this.client.player != null) ? this.client.player.experienceLevel : 0;
        boolean is_creative = this.client != null && this.client.player != null && this.client.player.getAbilities().creativeMode;

        boolean toolPresent = (toolStack != null && this.handler.hasCurses(toolStack));
        boolean tabletPresent = (tabletStack != null && tabletStack.isOf(ModItem.CURSED_TABLET));

        if (toolPresent && tabletPresent)
        {
            int drawx = x + 85;
            int drawy = y + 48;
            int butw = 66;
            int buth = 19;

            if (xp >= REQUIRED_XP || is_creative)
            {
                double xx = mouseX - (double)drawx;
                double yy = mouseY - (double)drawy;
                if (xx >= 0 && yy >= 0 && xx < butw && yy < buth && this.handler.onButtonClick(this.client.player, 0))
                {
                    this.client.interactionManager.clickButton(this.handler.syncId, 0);
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);

        ItemStack toolStack = this.handler.getSlot(0).getStack();
        ItemStack tabletStack = this.handler.getSlot(1).getStack();
        int xp = (this.client != null && this.client.player != null) ? this.client.player.experienceLevel : 0;
        boolean is_creative = this.client != null && this.client.player != null && this.client.player.getAbilities().creativeMode;

        boolean toolPresent = (toolStack != null && this.handler.hasCurses(toolStack));
        boolean tabletPresent = (tabletStack != null && tabletStack.isOf(ModItem.CURSED_TABLET));

        if (toolPresent && tabletPresent)
        {
            int drawx = x + 85;
            int drawy = y + 48;
            int butw = 66;
            int buth = 19;

            if (xp < REQUIRED_XP && !is_creative)
            {
                int xx = mouseX - drawx;
                int yy = mouseY - drawy;
                if (xx >= 0 && yy >= 0 && xx < butw && yy < buth)
                {
                    context.drawTooltip(this.textRenderer, Text.translatable("container.frontiers.curse_altar.levelcount", REQUIRED_XP).formatted(Formatting.RED), mouseX, mouseY);
                }
            }
        }
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY)
    {
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(TEXTURE, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.drawTablet(context, x, y, delta);

        ItemStack toolStack = this.handler.getSlot(0).getStack();
        ItemStack tabletStack = this.handler.getSlot(1).getStack();
        int xp = (this.client != null && this.client.player != null) ? this.client.player.experienceLevel : 0;
        boolean is_creative = this.client != null && this.client.player != null && this.client.player.getAbilities().creativeMode;

        boolean toolPresent = (toolStack != null && this.handler.hasCurses(toolStack));
        boolean tabletPresent = (tabletStack != null && tabletStack.isOf(ModItem.CURSED_TABLET));
        boolean showX = false;

        if (toolPresent && tabletPresent)
        {
            int textColor;

            RenderSystem.enableBlend();

            int drawx = x + 85;
            int drawy = y + 48;
            int butw = 66;
            int buth = 19;

            if (xp >= REQUIRED_XP || is_creative)
            {
                int xx = mouseX - drawx;
                int yy = mouseY - drawy;
                if (xx >= 0 && yy >= 0 && xx < butw && yy < buth)
                {
                    context.drawGuiTexture(BUTTON_HOVER, drawx, drawy, butw, buth);
                    textColor = Colors.WHITE;
                }
                else
                {
                    context.drawGuiTexture(BUTTON_ENABLED, drawx, drawy, butw, buth);
                    textColor = 0xFFC8FF8F;
                }
            }
            else
            {
                context.drawGuiTexture(BUTTON_DISABLED, drawx, drawy, butw, buth);
                textColor = 0xFF8C605D;
                showX = true;
            }

            RenderSystem.disableBlend();

            context.drawTextWithShadow(this.textRenderer, this.DISPLAY_TEXT, x + 91, y + 54, textColor);
        }
        else if (toolPresent || tabletPresent) showX = true;

        if (showX)
        {
            RenderSystem.enableBlend();
            context.drawGuiTexture(ARROW_DENIED, x + 107, y + 23, 22, 22);
            RenderSystem.disableBlend();
        }
    }

    private void drawTablet(DrawContext context, int x, int y, float delta)
    {
        DiffuseLighting.method_34742();
        context.getMatrices().push();

        context.getMatrices().translate((float)x + 45.0F, (float)y + 46.0F, 100.0F);
        context.getMatrices().scale(-55.0F, 55.0F, 55.0F);
        context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F)); // Rotates L/R
        context.getMatrices().multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
        context.getMatrices().multiply(RotationAxis.POSITIVE_Z.rotationDegrees(30.0F));

        VertexConsumer vertexConsumer = context.getVertexConsumers().getBuffer(RenderLayer.getEntityCutout(TABLET_TEX));
        this.tablet.render(context.getMatrices(), vertexConsumer, 15728880, OverlayTexture.DEFAULT_UV);

        vertexConsumer = context.getVertexConsumers().getBuffer(RenderLayer.getEntityTranslucent(TABLET_GLOW_TEX));
        int color1 = ColorHelper.Argb.lerp(this.glowAlpha, ColorHelper.Argb.withAlpha(MathHelper.floor(0.0F), -1), -1);
        this.tablet.render(context.getMatrices(), vertexConsumer, 15728880, OverlayTexture.DEFAULT_UV, color1);

        int color2 = ColorHelper.Argb.lerp(this.expAlpha, ColorHelper.Argb.withAlpha(MathHelper.floor(0.0F), -1), -1);
        vertexConsumer = context.getVertexConsumers().getBuffer(RenderLayer.getEntityTranslucent(TABLET_XP_TEX));
        this.tablet.render(context.getMatrices(), vertexConsumer, 15728880, OverlayTexture.DEFAULT_UV, color2);

        vertexConsumer = context.getVertexConsumers().getBuffer(RenderLayer.getEntityTranslucent(TABLET_XP_GLOW_TEX));
        this.tablet.render(context.getMatrices(), vertexConsumer, 15728880, OverlayTexture.DEFAULT_UV, color2);

        context.draw();
        context.getMatrices().pop();
        DiffuseLighting.enableGuiDepthLighting();
    }
}
