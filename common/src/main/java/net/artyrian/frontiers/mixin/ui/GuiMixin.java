package net.artyrian.frontiers.mixin.ui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.misc.ModHeartType;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Gui.class)
public abstract class GuiMixin
{
    @Unique private static final Component ALPHA_TEXT = Component.literal("Minecraft Infdev (real)");
    @Unique private static final ResourceLocation EX_ARMOR_HALF_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/double_armor_half");
    @Unique private static final ResourceLocation EX_ARMOR_FULL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/double_armor_full");
    @Unique private static final ResourceLocation SANITY_HALF_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/sanity_half");
    @Unique private static final ResourceLocation SANITY_FULL_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/sanity_whole");
    @Unique private static final ResourceLocation SANITY_CONTAINER_TEXTURE = ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/sanity_container");

    @Shadow protected abstract void drawHeart(GuiGraphics context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half);

    @Shadow @Nullable protected abstract Player getCameraPlayer();

    @Shadow @Final private static ResourceLocation AIR_BURSTING_TEXTURE;

    @Shadow @Final private static ResourceLocation AIR_TEXTURE;

    @Shadow protected abstract int getHeartRows(int heartCount);

    @Shadow protected abstract int getHeartCount(@Nullable LivingEntity entity);

    @Shadow public abstract Font getTextRenderer();

    @Shadow @Final private RandomSource random;

    @WrapOperation(method = "renderHealthBar", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIZZZ)V",
            ordinal = 0)
    )
    private void renderChanger(
            Gui instance, GuiGraphics context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, Operation<Void> original, @Local(argsOnly = true) Player player)
    {
        if (player.hasEffect(ModStatusEffects.STORM_POISONING))
        {
            this.drawHeart(context, ModHeartType.FRONTIERS_CONTAINER_STORM, x, y, hardcore, blinking, half);
        }
        else original.call(instance, context, type, x, y, hardcore, blinking, half);
    }

    @Inject(method = "renderArmor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.BEFORE))
    private static void extraChest(GuiGraphics context, Player player, int i, int j, int k, int x, CallbackInfo ci)
    {
        int l = player.getArmorValue();
        if (l > 20)
        {
            int m = i - (j - 1) * k - 10;

            for (int n = 10; n < 20; n++)
            {
                int o = x + (n - 10) * 8;
                if (n * 2 + 1 < l)
                {
                    context.blitSprite(EX_ARMOR_FULL_TEXTURE, o, m, 9, 9);
                }

                if (n * 2 + 1 == l)
                {
                    context.blitSprite(EX_ARMOR_HALF_TEXTURE, o, m, 9, 9);
                }
            }
        }
    }

    @Inject(method = "renderStatusBars", at = @At("TAIL"))
    private void renderSanity(GuiGraphics context, CallbackInfo ci)
    {
        Player playerEntity = this.getCameraPlayer();

        int m = context.guiWidth() / 2 + 91;
        int n = context.guiHeight() - 49;

        int maxair = playerEntity.getMaxAirSupply();
        int air = Math.min(playerEntity.getAirSupply(), maxair);

        int sanity = ((PlayerMixInterface)playerEntity).frontiers_1_21x$getSanity();
        int sanityTick = ((PlayerMixInterface)playerEntity).frontiers_1_21x$getSanityTick();

        boolean doShake = (
                sanity < 5 || sanityTick >= 1190
        );
        boolean critical = (sanity == 0);

        //context.drawText(this.getTextRenderer(), String.valueOf(sanity), 20, 20, -1, true);
        //context.drawText(this.getTextRenderer(), String.valueOf(sanityTick), 20, 30, -1, true);

        if (playerEntity.level().dimension().equals(ModDimension.CRAGS_LEVEL_KEY) && maxair == air)
        {
            RenderSystem.enableBlend();

            int truen;
            for (int i = 0; i < 10; i++)
            {
                truen = n;
                if (doShake)
                {
                    int bounder = (critical) ? (this.random.nextInt(3) - 1) : this.random.nextInt(2);
                    truen += bounder;
                }

                context.blitSprite(SANITY_CONTAINER_TEXTURE, m - i * 8 - 9, truen, 9, 9);

                if (i * 2 + 1 < sanity)
                {
                    context.blitSprite(SANITY_FULL_TEXTURE, m - i * 8 - 9, truen, 9, 9);
                }
                else if (i * 2 + 1 == sanity)
                {
                    context.blitSprite(SANITY_HALF_TEXTURE, m - i * 8 - 9, truen, 9, 9);
                }
            }

            RenderSystem.disableBlend();
        }
    }

    @ModifyExpressionValue(method = "renderMiscOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean isOtherPumpkinLikes(boolean original, @Local ItemStack stack)
    {
        return original || stack.is(ModBlocks.CARVED_GLISTERING_MELON.asItem()) || stack.is(ModBlocks.CARVED_MELON.asItem());
    }

    @Inject(method = "renderMiscOverlays", at = @At("TAIL"))
    private void aprilFoolsText(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            int m = 2;
            int n = 2;
            context.drawString(this.getTextRenderer(), ALPHA_TEXT, m, n, CommonColors.WHITE, true);
        }
    }
}
