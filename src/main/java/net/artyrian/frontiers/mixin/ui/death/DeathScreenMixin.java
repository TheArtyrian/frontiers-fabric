package net.artyrian.frontiers.mixin.ui.death;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.ui.ScreenMixin;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInteface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
    @Unique private boolean used_avarice_totem;
    @Unique private Text totem_text = Text.translatable("deathScreen.frontiers.consumedTotem").formatted(Formatting.AQUA);
    @Unique private Text annoying_text = Text.literal("\"" + "---" + "\"").formatted(Formatting.YELLOW);

    @Inject(method = "init", at = @At("TAIL"))
    public void initAvariceChecker(CallbackInfo ci)
    {
        String concatter = (this.isHardcore)
                ? Frontiers.HARDCORE_MSG.getRandomMessage(this.client.world.random)
                : Frontiers.DEATH_MSG.getRandomMessage(this.client.world.random);
        concatter = concatter.replaceAll("PLAYERNAME", this.client.getSession().getUsername());

        this.used_avarice_totem = ((PlayerMixInteface)this.client.player).frontiers_1_21x$usedAvariceTotem();
        this.annoying_text = Text.literal("\"" + concatter + "\"").formatted(Formatting.YELLOW);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void extraTextWriter(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci)
    {
        if (Frontiers.CONFIG.doDeathScreenComment()) context.drawCenteredTextWithShadow(this.textRenderer, this.annoying_text, this.width / 2, 115, 16777215);
        if (this.used_avarice_totem) context.drawCenteredTextWithShadow(this.textRenderer, this.totem_text, this.width / 2, this.height / 4 + 126, 16777215);
    }
}
