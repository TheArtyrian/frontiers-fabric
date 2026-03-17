package net.artyrian.frontiers.mixin.ui;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.item.intf.Magic;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.mixin_intf.GuiIntf;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Gui.class)
public abstract class GuiMixin implements GuiIntf
{
    @Unique private static final Component FRONTIERS$ALPHA_TEXT = Component.literal("Minecraft Infdev (real)");
    @Unique private static final ResourceLocation FRONTIERS$EX_ARMOR_HALF_TEXTURE = Frontiers.id("hud/double_armor_half");
    @Unique private static final ResourceLocation FRONTIERS$EX_ARMOR_FULL_TEXTURE = Frontiers.id("hud/double_armor_full");
    @Unique private static final ResourceLocation FRONTIERS$SANITY_HALF_TEXTURE = Frontiers.id("hud/sanity_half");
    @Unique private static final ResourceLocation FRONTIERS$SANITY_FULL_TEXTURE = Frontiers.id("hud/sanity_whole");
    @Unique private static final ResourceLocation FRONTIERS$SANITY_CONTAINER_TEXTURE = Frontiers.id("hud/sanity_container");

    @Unique private static final ResourceLocation FRONTIERS$EXP_BG_SHORT = Frontiers.id("hud/experience_bar_bg_short");
    @Unique private static final ResourceLocation FRONTIERS$EXP_PROGRESS_SHORT = Frontiers.id("hud/experience_bar_progress_short");
    @Unique private static final ResourceLocation FRONTIERS$MANA_BG = Frontiers.id("hud/mana_bar_bg");
    @Unique private static final ResourceLocation FRONTIERS$MANA_PROGRESS = Frontiers.id("hud/mana_bar_progress");

    @Shadow protected abstract void renderHeart(GuiGraphics context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half);
    @Shadow @Nullable protected abstract Player getCameraPlayer();
    @Shadow @Final private static ResourceLocation AIR_BURSTING_SPRITE;
    @Shadow @Final private static ResourceLocation AIR_SPRITE;
    @Shadow protected abstract int getVisibleVehicleHeartRows(int heartCount);
    @Shadow protected abstract int getVehicleMaxHearts(@Nullable LivingEntity entity);
    @Shadow public abstract Font getFont();
    @Shadow protected abstract boolean isExperienceBarVisible();

    @Shadow @Final private RandomSource random;
    @Shadow @Final private Minecraft minecraft;


    @WrapOperation(method = "renderHearts", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/Gui;renderHeart(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Gui$HeartType;IIZZZ)V",
            ordinal = 0)
    )
    private void renderChanger(
            Gui instance, GuiGraphics context, Gui.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, Operation<Void> original, @Local(argsOnly = true) Player player)
    {
        if (player.hasEffect(ModStatusEffects.STORM_POISONING))
        {
            this.renderHeart(context, FRRegistries.HeartType.FRONTIERS_CONTAINER_STORM, x, y, hardcore, blinking, half);
        }
        else original.call(instance, context, type, x, y, hardcore, blinking, half);
    }

    @Inject(method = "renderArmor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.BEFORE))
    private static void frontiers$extraChest(GuiGraphics context, Player player, int i, int j, int k, int x, CallbackInfo ci)
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
                    context.blitSprite(FRONTIERS$EX_ARMOR_FULL_TEXTURE, o, m, 9, 9);
                }

                if (n * 2 + 1 == l)
                {
                    context.blitSprite(FRONTIERS$EX_ARMOR_HALF_TEXTURE, o, m, 9, 9);
                }
            }
        }
    }

    @Inject(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V", shift = At.Shift.AFTER), cancellable = true)
    private void frontiers$renderExperienceBar(GuiGraphics guiGraphics, int x, CallbackInfo ci)
    {
        Item mainItem = this.minecraft.player.getWeaponItem().getItem();
        Item offItem = this.minecraft.player.getOffhandItem().getItem();

        if (Frontiers.CONFIG.doesManaBarAlwaysShow() || mainItem instanceof Magic || offItem instanceof Magic)
        {
            int nextXP = this.minecraft.player.getXpNeededForNextLevel();
            if (nextXP > 0)
            {
                int x2 = (guiGraphics.guiWidth() / 2) + 1;
                int y = guiGraphics.guiHeight() - 32 + 3;

                RenderSystem.enableBlend();

                // Mana
                int progress1 = 0;

                guiGraphics.blitSprite(FRONTIERS$MANA_BG, x, y, 90, 5);
                if (progress1 > 0) guiGraphics.blitSprite(FRONTIERS$MANA_PROGRESS, 90, 5, 0, 0, x, y, progress1, 5);

                // EXP
                int progress2 = (int)(this.minecraft.player.experienceProgress * 91.0F);

                guiGraphics.blitSprite(FRONTIERS$EXP_BG_SHORT, x2, y, 90, 5);
                if (progress2 > 0) guiGraphics.blitSprite(FRONTIERS$EXP_PROGRESS_SHORT, 90, 5, 0, 0, x2, y, progress2, 5);

                RenderSystem.disableBlend();
            }

            this.minecraft.getProfiler().pop();
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void frontiers$renderExperienceAndMana(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci)
    {
        Item mainItem = this.minecraft.player.getWeaponItem().getItem();
        Item offItem = this.minecraft.player.getOffhandItem().getItem();

        if (Frontiers.CONFIG.doesManaBarAlwaysShow() || mainItem instanceof Magic || offItem instanceof Magic)
        {
            int expLvl = this.minecraft.player.experienceLevel;
            if (this.isExperienceBarVisible() && expLvl > 0)
            {
                this.minecraft.getProfiler().push("expLevelWithManaLevel");

                int halfway = (guiGraphics.guiWidth() / 2);
                int y = guiGraphics.guiHeight() - 31;

                String expStr = "--";
                int x1 = halfway - 94 - this.getFont().width(expStr);

                // Mana
                guiGraphics.drawString(this.getFont(), expStr, x1 + 1, y, 0, false);
                guiGraphics.drawString(this.getFont(), expStr, x1 - 1, y, 0, false);
                guiGraphics.drawString(this.getFont(), expStr, x1, y + 1, 0, false);
                guiGraphics.drawString(this.getFont(), expStr, x1, y - 1, 0, false);
                guiGraphics.drawString(this.getFont(), expStr, x1, y, 0x739FFA, false);

                String expStr2 = "" + expLvl;
                int x2 = halfway + 94;

                // EXP
                guiGraphics.drawString(this.getFont(), expStr2, x2 + 1, y, 0, false);
                guiGraphics.drawString(this.getFont(), expStr2, x2 - 1, y, 0, false);
                guiGraphics.drawString(this.getFont(), expStr2, x2, y + 1, 0, false);
                guiGraphics.drawString(this.getFont(), expStr2, x2, y - 1, 0, false);
                guiGraphics.drawString(this.getFont(), expStr2, x2, y, 0x80FF20, false);


                this.minecraft.getProfiler().pop();
                ci.cancel();
            }
        }
    }

    @ModifyExpressionValue(method = "renderCameraOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean isOtherPumpkinLikes(boolean original, @Local ItemStack stack)
    {
        return original || stack.is(ModBlocks.CARVED_GLISTERING_MELON.get().asItem()) || stack.is(ModBlocks.CARVED_MELON.get().asItem());
    }

    @Inject(method = "renderCameraOverlays", at = @At("TAIL"))
    private void aprilFoolsText(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci)
    {
        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
        {
            int m = 2;
            int n = 2;
            context.drawString(this.getFont(), FRONTIERS$ALPHA_TEXT, m, n, CommonColors.WHITE, true);
        }
    }

    @Override
    public void frontiersML$accessibleFromAllRenderSanity(GuiGraphics context)
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

                context.blitSprite(FRONTIERS$SANITY_CONTAINER_TEXTURE, m - i * 8 - 9, truen, 9, 9);

                if (i * 2 + 1 < sanity)
                {
                    context.blitSprite(FRONTIERS$SANITY_FULL_TEXTURE, m - i * 8 - 9, truen, 9, 9);
                }
                else if (i * 2 + 1 == sanity)
                {
                    context.blitSprite(FRONTIERS$SANITY_HALF_TEXTURE, m - i * 8 - 9, truen, 9, 9);
                }
            }

            RenderSystem.disableBlend();
        }
    }
}
