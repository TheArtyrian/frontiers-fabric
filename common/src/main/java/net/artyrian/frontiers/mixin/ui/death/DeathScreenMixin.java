package net.artyrian.frontiers.mixin.ui.death;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.artyrian.frontiers.systems.FrontiersRandomTextList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
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
    @Shadow @Final private boolean hardcore;
    @Unique private Component totem_text = Component.translatable("deathScreen.frontiers.consumedTotem").withStyle(ChatFormatting.AQUA);
    @Unique private Component annoying_text = Component.literal("\"" + "---" + "\"").withStyle(ChatFormatting.YELLOW);
    @Unique private ResourceLocation funnypic;

    @Inject(method = "init", at = @At("TAIL"))
    public void initAvariceChecker(CallbackInfo ci)
    {
        Style style = Style.EMPTY.withColor(ChatFormatting.YELLOW);
        Style quoteStyle = Style.EMPTY.withColor(ChatFormatting.YELLOW);
        String concatter = (this.hardcore)
                ? Frontiers.HARDCORE_MSG.getRandomMessage(this.minecraft.level.random)
                : Frontiers.DEATH_MSG.getRandomMessage(this.minecraft.level.random);

        if (
            this.minecraft.player != null &&
            this.minecraft.level.dimension() == ModDimension.CRAGS_LEVEL_KEY &&
            ((PlayerMixInterface)this.minecraft.player).frontiers_1_21x$killedByCragsMonster())
        {
            concatter = FrontiersRandomTextList.getRandomCragsMessage(this.minecraft.level.random);
            style = Style.EMPTY.withColor(ChatFormatting.RED).withObfuscated(true).withBold(true);
            quoteStyle = Style.EMPTY.withColor(ChatFormatting.RED);
        }

        concatter = concatter.replaceAll("PLAYERNAME", this.minecraft.getUser().getName());

        Component pre = Component.literal(concatter).withStyle(style);
        Component quote = Component.literal("\"").withStyle(quoteStyle);

        this.annoying_text = Component.empty().append(quote).append(pre).append(quote);
        this.funnypic = MethodToolbox.funnyImageProvider(this.minecraft.level.random);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void extraTextWriter(GuiGraphics context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doDeathScreenComment()) context.drawCenteredString(this.font, this.annoying_text, this.width / 2, 115, 16777215);

        if (this.minecraft.player != null)
        {
            boolean used_totem = ((PlayerMixInterface) this.minecraft.player).frontiers_1_21x$usedAvariceTotem();
            if (used_totem && !this.hardcore)
            {
                context.drawCenteredString(this.font, this.totem_text, this.width / 2, this.height / 4 + 126, 16777215);
            }
        }
    }

    @WrapOperation(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;drawCenteredString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V",
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
        if (this.minecraft.player != null && ((PlayerMixInterface)this.minecraft.player).frontiers_1_21x$killedByCragsMonster())
        {
            context.fillRenderType(FRRegistries.RenderLayers.getCragsPortal(), 0, 0, this.width, this.height, 0);
            ci.cancel();
        }
    }
}
