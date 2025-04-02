package net.artyrian.frontiers.mixin.ui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.misc.ModHeartType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(InGameHud.class)
public abstract class GuiMixin
{
    @Unique private static final Identifier EX_ARMOR_HALF_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/double_armor_half");
    @Unique private static final Identifier EX_ARMOR_FULL_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/double_armor_full");

    @Shadow protected abstract void drawHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half);

    @WrapOperation(method = "renderHealthBar", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIZZZ)V",
            ordinal = 0)
    )
    private void renderChanger(
            InGameHud instance, DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, Operation<Void> original, @Local(argsOnly = true) PlayerEntity player)
    {
        if (player.hasStatusEffect(ModStatusEffects.STORM_POISONING))
        {
            this.drawHeart(context, ModHeartType.FRONTIERS_CONTAINER_STORM, x, y, hardcore, blinking, half);
        }
        else original.call(instance, context, type, x, y, hardcore, blinking, half);
    }

    @Inject(method = "renderArmor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.BEFORE))
    private static void extraChest(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci)
    {
        int l = player.getArmor();
        if (l > 20)
        {
            int m = i - (j - 1) * k - 10;

            for (int n = 10; n < 20; n++)
            {
                int o = x + (n - 10) * 8;
                if (n * 2 + 1 < l)
                {
                    context.drawGuiTexture(EX_ARMOR_FULL_TEXTURE, o, m, 9, 9);
                }

                if (n * 2 + 1 == l)
                {
                    context.drawGuiTexture(EX_ARMOR_HALF_TEXTURE, o, m, 9, 9);
                }
            }
        }
    }
}
