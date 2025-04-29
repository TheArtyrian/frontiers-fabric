package net.artyrian.frontiers.mixin.ui.credits;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.FrontiersConfig;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import org.intellij.lang.annotations.JdkConstants;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreditsScreen.class)
public abstract class CreditsScreenMixin extends ScreenMixin
{
    @Shadow private float time;
    @Shadow @Final private float baseSpeed;
    @Shadow private int creditsHeight;
    @Unique @Final
    private final Identifier BG_TEX = Identifier.ofVanilla("textures/block/dirt.png");

    private final Identifier BG_TEX_ENDSTONE = Identifier.ofVanilla("textures/block/end_stone.png");
    private final Identifier BG_TEX_SKY = Identifier.ofVanilla("textures/environment/end_sky.png");
    private final Identifier BG_TEX_PORTAL = Identifier.ofVanilla("textures/entity/end_portal.png");

    @WrapOperation(method = "renderBackground", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;fillWithLayer(Lnet/minecraft/client/render/RenderLayer;IIIII)V")
    )
    private void renderBackgroundOldFashionedWay(DrawContext context, RenderLayer layer, int startX, int startY, int endX, int endY, int z, Operation<Void> original) {
        switch (Frontiers.CONFIG.creditsType())
        {
            // Original
            case 0:
            {
                int i = this.width;
                float f = this.time * 0.5F;
                //int j = true;
                float g = this.time / this.baseSpeed;
                float h = g * 0.02F;
                float k = (float)(this.creditsHeight + this.height + this.height + 24) / this.baseSpeed;
                float l = (k - 20.0F - g) * 0.005F;
                if (l < h) {
                    h = l;
                }

                if (h > 1.0F) {
                    h = 1.0F;
                }

                h *= h;
                h = h * 96.0F / 255.0F;
                context.setShaderColor(h, h, h, 1.0F);
                context.drawTexture(BG_TEX, 0, 0, 0, 0.0F, f, i, this.height, 64, 64);
                context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            break;

            // Frontiers BG
            case 1:
            {
                float f = this.time * 0.5F;
                float f2 = this.time * -0.3F;
                float g = this.time / this.baseSpeed;
                float h = g * 0.02F;
                float k = (float)(this.creditsHeight + this.height + this.height + 24) / this.baseSpeed;
                float l = (k - 20.0F - g) * 0.005F;
                if (l < h) {
                    h = l;
                }

                if (h > 1.0F) {
                    h = 1.0F;
                }

                float h_pre = h;

                h *= h;
                h = h * 96.0F / 255.0F;


                context.setShaderColor((h * 0.3F), 0.0F, (h * 0.4F), 1.0F);
                context.drawTexture(BG_TEX_PORTAL, 0, 0, 10, f2, f, this.width, this.height, 256, 256);

                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ONE_MINUS_DST_COLOR);

                context.setShaderColor((h * 0.05F), (h * 0.05F), (h * 0.05F), 1.0F);
                context.drawTexture(BG_TEX_SKY, 0, 0, 0, f, f2, this.width, this.height, 256, 256);

                context.setShaderColor(0.0F, 0.0F, (h * 0.6F), 1.0F);
                context.drawTexture(BG_TEX_PORTAL, 0, 0, 10, 0.0F, f2, this.width, this.height, 256, 256);

                context.setShaderColor((h * 0.5F), 0.0F, (h * 0.6F), 1.0F);
                context.drawTexture(BG_TEX_PORTAL, 0, 0, 10, f2, 0.0F, this.width, this.height, 256, 256);

                context.setShaderColor((h * 0.6F), (h * 0.1F), (h * 0.2F), 1.0F);
                context.drawTexture(BG_TEX_PORTAL, 0, 0, 10, f2, f, this.width, this.height, 256, 256);

                context.setShaderColor((h * 0.1F), (h * 0.4F), (h * 0.6F), 1.0F);
                context.drawTexture(BG_TEX_PORTAL, 0, 0, 10, 0.0F, f, this.width, this.height, 256, 256);

                context.setShaderColor((h * 0.3F), (h * 0.3F), (h * 0.3F), 1.0F);
                context.drawTexture(BG_TEX_PORTAL, 0, 0, 10, f, 0.0F, this.width, this.height, 256, 256);

                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();

                context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
            break;

            // Vanilla
            case 2:
            {
                original.call(context, layer, startX, startY, endX, endY, z);
            }
            break;
        }
    }
}
