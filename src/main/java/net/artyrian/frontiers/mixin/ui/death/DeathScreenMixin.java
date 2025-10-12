package net.artyrian.frontiers.mixin.ui.death;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.misc.FrontiersRandomTextList;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.artyrian.frontiers.rendering.ModRenderLayers;
import net.artyrian.frontiers.util.MethodToolbox;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
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
    @Unique private Text totem_text = Text.translatable("deathScreen.frontiers.consumedTotem").formatted(Formatting.AQUA);
    @Unique private Text annoying_text = Text.literal("\"" + "---" + "\"").formatted(Formatting.YELLOW);
    @Unique private Identifier funnypic;

    @Inject(method = "init", at = @At("TAIL"))
    public void initAvariceChecker(CallbackInfo ci)
    {
        Formatting color = Formatting.YELLOW;
        String concatter = (this.isHardcore)
                ? Frontiers.HARDCORE_MSG.getRandomMessage(this.client.world.random)
                : Frontiers.DEATH_MSG.getRandomMessage(this.client.world.random);

        if (
            this.client.player != null &&
            this.client.world.getRegistryKey() == ModDimension.CRAGS_LEVEL_KEY &&
            ((PlayerMixInterface)this.client.player).frontiers_1_21x$killedByCragsMonster())
        {
            concatter = FrontiersRandomTextList.getRandomCragsMessage(this.client.world.random);
            color = Formatting.RED;
        }

        concatter = concatter.replaceAll("PLAYERNAME", this.client.getSession().getUsername());

        this.annoying_text = Text.literal("\"" + concatter + "\"").formatted(color);
        this.funnypic = MethodToolbox.funnyImageProvider(this.client.world.random);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void extraTextWriter(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doDeathScreenComment()) context.drawCenteredTextWithShadow(this.textRenderer, this.annoying_text, this.width / 2, 115, 16777215);

        if (this.client.player != null)
        {
            boolean used_totem = ((PlayerMixInterface) this.client.player).frontiers_1_21x$usedAvariceTotem();
            if (used_totem && !this.isHardcore)
            {
                context.drawCenteredTextWithShadow(this.textRenderer, this.totem_text, this.width / 2, this.height / 4 + 126, 16777215);
            }
        }
    }

    @WrapOperation(method = "render", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V",
            ordinal = 0)
    )
    public void aprilFoolsOpText(DrawContext instance, TextRenderer textRenderer, Text text, int centerX, int y, int color, Operation<Void> original)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            instance.drawTexture(funnypic, centerX - 16, y - 20, 0.0F, 0.0F,32, 32, 32, 32);
        }
        else
        {
            original.call(instance, textRenderer, text, centerX, y, color);
        }
    }

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void doDifferentCragsDeathBG(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (this.client.player != null && ((PlayerMixInterface)this.client.player).frontiers_1_21x$killedByCragsMonster())
        {
            context.fillWithLayer(ModRenderLayers.getCragsPortal(), 0, 0, this.width, this.height, 0);
            ci.cancel();
        }
    }
}
