package net.artyrian.frontiers.mixin.ui.death;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.misc.FrontiersRandomTextList;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.artyrian.frontiers.rendering.ModRenderLayers;
import net.artyrian.frontiers.util.MethodToolbox;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public abstract class DeathScreenMixin extends ScreenMixin
{
    @Shadow @Final private boolean isHardcore;
    @Unique private Component totem_text = Component.translatable("deathScreen.frontiers.consumedTotem").withStyle(ChatFormatting.AQUA);
    @Unique private Component annoying_text = Component.literal("\"" + "---" + "\"").withStyle(ChatFormatting.YELLOW);
    @Unique private ResourceLocation funnypic;

    @Inject(method = "init", at = @At("TAIL"))
    public void initAvariceChecker(CallbackInfo ci)
    {
        ChatFormatting color = ChatFormatting.YELLOW;
        String concatter = (this.isHardcore)
                ? Frontiers.HARDCORE_MSG.getRandomMessage(this.client.level.random)
                : Frontiers.DEATH_MSG.getRandomMessage(this.client.level.random);

        if (
            this.client.player != null &&
            this.client.level.dimension() == ModDimension.CRAGS_LEVEL_KEY &&
            ((PlayerMixInterface)this.client.player).frontiers_1_21x$killedByCragsMonster())
        {
            concatter = FrontiersRandomTextList.getRandomCragsMessage(this.client.level.random);
            color = ChatFormatting.RED;
        }

        concatter = concatter.replaceAll("PLAYERNAME", this.client.getUser().getName());

        this.annoying_text = Component.literal("\"" + concatter + "\"").withStyle(color);
        this.funnypic = MethodToolbox.funnyImageProvider(this.client.level.random);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void extraTextWriter(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doDeathScreenComment()) context.drawCenteredString(this.textRenderer, this.annoying_text, this.width / 2, 115, 16777215);

        if (this.client.player != null)
        {
            boolean used_totem = ((PlayerMixInterface) this.client.player).frontiers_1_21x$usedAvariceTotem();
            if (used_totem && !this.isHardcore)
            {
                context.drawCenteredString(this.textRenderer, this.totem_text, this.width / 2, this.height / 4 + 126, 16777215);
            }
        }
    }

    @WrapOperation(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V",
            ordinal = 0)
    )
    public void aprilFoolsOpText(GuiGraphics instance, Font textRenderer, Component text, int centerX, int y, int color, Operation<Void> original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            instance.blit(funnypic, centerX - 16, y - 20, 0.0F, 0.0F,32, 32, 32, 32);
        }
        else
        {
            original.call(instance, textRenderer, text, centerX, y, color);
        }
    }

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void doDifferentCragsDeathBG(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (this.client.player != null && ((PlayerMixInterface)this.client.player).frontiers_1_21x$killedByCragsMonster())
        {
            context.fillRenderType(ModRenderLayers.getCragsPortal(), 0, 0, this.width, this.height, 0);
            ci.cancel();
        }
    }
}
