package net.artyrian.frontiers.mixin.ui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.misc.ModHeartType;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInterface;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(InGameHud.class)
public abstract class GuiMixin
{
    @Unique private static final Text ALPHA_TEXT = Text.literal("Minecraft Infdev (real)");
    @Unique private static final Identifier EX_ARMOR_HALF_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/double_armor_half");
    @Unique private static final Identifier EX_ARMOR_FULL_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/double_armor_full");
    @Unique private static final Identifier SANITY_HALF_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/sanity_half");
    @Unique private static final Identifier SANITY_FULL_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/sanity_whole");
    @Unique private static final Identifier SANITY_CONTAINER_TEXTURE = Identifier.of(Frontiers.MOD_ID, "hud/sanity_container");

    @Shadow protected abstract void drawHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half);

    @Shadow @Nullable protected abstract PlayerEntity getCameraPlayer();

    @Shadow @Final private static Identifier AIR_BURSTING_TEXTURE;

    @Shadow @Final private static Identifier AIR_TEXTURE;

    @Shadow protected abstract int getHeartRows(int heartCount);

    @Shadow protected abstract int getHeartCount(@Nullable LivingEntity entity);

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow @Final private Random random;

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

    @Inject(method = "renderStatusBars", at = @At("TAIL"))
    private void renderSanity(DrawContext context, CallbackInfo ci)
    {
        PlayerEntity playerEntity = this.getCameraPlayer();

        int m = context.getScaledWindowWidth() / 2 + 91;
        int n = context.getScaledWindowHeight() - 49;

        int maxair = playerEntity.getMaxAir();
        int air = Math.min(playerEntity.getAir(), maxair);

        int sanity = ((PlayerMixInterface)playerEntity).frontiers_1_21x$getSanity();
        int sanityTick = ((PlayerMixInterface)playerEntity).frontiers_1_21x$getSanityTick();

        boolean doShake = (
                sanity <= 6 || sanityTick >= 1195
        );
        int shakePower = (sanity == 0) ? 3 : 1;

        if (playerEntity.getWorld().getRegistryKey().equals(ModDimension.CRAGS_LEVEL_KEY) && maxair == air)
        {
            RenderSystem.enableBlend();

            int truen;
            for (int i = 0; i < 10; i++)
            {
                // Makes the bar shake - use when empty
                truen = n;
                if (doShake) truen += (this.random.nextInt(shakePower) - 1);

                context.drawGuiTexture(SANITY_CONTAINER_TEXTURE, m - i * 8 - 9, truen, 9, 9);

                if (i * 2 + 1 < sanity)
                {
                    context.drawGuiTexture(SANITY_FULL_TEXTURE, m - i * 8 - 9, truen, 9, 9);
                }
                else if (i * 2 + 1 == sanity)
                {
                    context.drawGuiTexture(SANITY_HALF_TEXTURE, m - i * 8 - 9, truen, 9, 9);
                }
            }

            RenderSystem.disableBlend();
        }
    }

    @Inject(method = "renderMiscOverlays", at = @At("TAIL"))
    private void aprilFoolsText(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci)
    {
        if (Frontiers.IS_APRIL_FOOLS)
        {
            int m = 2;
            int n = 2;
            context.drawText(this.getTextRenderer(), ALPHA_TEXT, m, n, Colors.WHITE, true);
        }
    }
}
