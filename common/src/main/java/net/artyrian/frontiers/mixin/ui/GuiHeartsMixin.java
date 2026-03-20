package net.artyrian.frontiers.mixin.ui;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_intf.PlayerIntf;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.vertisoft.vectorlib.agnostic.util.VectorOpcode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.Arrays;

// Mixes in custom heart types from net.artyrian.frontiers.misc.ModHeartType.
@Mixin(Gui.HeartType.class)
public abstract class GuiHeartsMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Gui.HeartType newHeartType(String internalName,
                                                    int ordinal,
                                                    ResourceLocation fullTex,
                                                    ResourceLocation fullBlinkTex,
                                                    ResourceLocation halfTex,
                                                    ResourceLocation halfBlinkTex,
                                                    ResourceLocation hardcoreFullTex,
                                                    ResourceLocation hardcoreFullBlinkTex,
                                                    ResourceLocation hardcoreHalfTex,
                                                    ResourceLocation hardcoreHalfBlinkTex)
    {
        throw new AssertionError();
    }

    // Get hearts field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    Gui.HeartType[] $VALUES;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = VectorOpcode.PUTSTATIC,
            target = "Lnet/minecraft/client/gui/Gui$HeartType;$VALUES:[Lnet/minecraft/client/gui/Gui$HeartType;",
            shift = At.Shift.AFTER))
    private static void addCustomHearts(CallbackInfo ci)
    {
        // Get rarity list.
        var hearts = new ArrayList<>(Arrays.asList($VALUES));
        var last = hearts.get(hearts.size() - 1);
        var i = 1;

        // Frontiers: PINK
        var frontiers_pink = newHeartType(
                "FRONTIERS_PINK",
                     last.ordinal() + i,
                            Frontiers.id("hud/heart/tier1_full"),
                            Frontiers.id("hud/heart/tier1_full_blinking"),
                            Frontiers.id("hud/heart/tier1_half"),
                            Frontiers.id("hud/heart/tier1_half_blinking"),
                            Frontiers.id("hud/heart/tier1_hardcore_full"),
                            Frontiers.id("hud/heart/tier1_hardcore_full_blinking"),
                            Frontiers.id("hud/heart/tier1_hardcore_half"),
                            Frontiers.id("hud/heart/tier1_hardcore_half_blinking")
                );
        FRRegistries.HeartType.FRONTIERS_PINK = frontiers_pink;
        hearts.add(frontiers_pink);
        i++;

        // Frontiers: PURPLE
        var frontiers_purple = newHeartType(
                "FRONTIERS_PURPLE",
                last.ordinal() + i,
                Frontiers.id("hud/heart/tier2_full"),
                Frontiers.id("hud/heart/tier2_full_blinking"),
                Frontiers.id("hud/heart/tier2_half"),
                Frontiers.id("hud/heart/tier2_half_blinking"),
                Frontiers.id("hud/heart/tier2_hardcore_full"),
                Frontiers.id("hud/heart/tier2_hardcore_full_blinking"),
                Frontiers.id("hud/heart/tier2_hardcore_half"),
                Frontiers.id("hud/heart/tier2_hardcore_half_blinking")
        );
        FRRegistries.HeartType.FRONTIERS_PURPLE = frontiers_purple;
        hearts.add(frontiers_purple);
        i++;

        // Frontiers: ON FIRE
        var frontiers_onfire = newHeartType(
                "FRONTIERS_ONFIRE",
                last.ordinal() + i,
                Frontiers.id("hud/heart/onfire_full"),
                Frontiers.id("hud/heart/onfire_full_blinking"),
                Frontiers.id("hud/heart/onfire_half"),
                Frontiers.id("hud/heart/onfire_half_blinking"),
                Frontiers.id("hud/heart/onfire_hardcore_full"),
                Frontiers.id("hud/heart/onfire_hardcore_full_blinking"),
                Frontiers.id("hud/heart/onfire_hardcore_half"),
                Frontiers.id("hud/heart/onfire_hardcore_half_blinking")
        );
        FRRegistries.HeartType.FRONTIERS_ONFIRE = frontiers_onfire;
        hearts.add(frontiers_onfire);
        i++;

        // Frontiers: STORM
        var frontiers_storm = newHeartType(
                "FRONTIERS_STORM",
                last.ordinal() + i,
                Frontiers.id("hud/heart/storm_full"),
                Frontiers.id("hud/heart/storm_full_blinking"),
                Frontiers.id("hud/heart/storm_half"),
                Frontiers.id("hud/heart/storm_half_blinking"),
                Frontiers.id("hud/heart/storm_hardcore_full"),
                Frontiers.id("hud/heart/storm_hardcore_full_blinking"),
                Frontiers.id("hud/heart/storm_hardcore_half"),
                Frontiers.id("hud/heart/storm_hardcore_half_blinking")
        );
        FRRegistries.HeartType.FRONTIERS_STORM = frontiers_storm;
        hearts.add(frontiers_storm);
        i++;

        // Frontiers: STORM (CONTAINER)
        var frontiers_container_storm = newHeartType(
                "FRONTIERS_CONTAINER_STORM",
                last.ordinal() + i,
                Frontiers.id("hud/heart/storm_container"),
                Frontiers.id("hud/heart/storm_container_blinking"),
                Frontiers.id("hud/heart/storm_container"),
                Frontiers.id("hud/heart/storm_container_blinking"),
                Frontiers.id("hud/heart/storm_container_hardcore"),
                Frontiers.id("hud/heart/storm_container_hardcore_blinking"),
                Frontiers.id("hud/heart/storm_container_hardcore"),
                Frontiers.id("hud/heart/storm_container_hardcore_blinking")
        );
        FRRegistries.HeartType.FRONTIERS_CONTAINER_STORM = frontiers_container_storm;
        hearts.add(frontiers_container_storm);
        i++;

        // Inject.
        $VALUES = hearts.toArray(new Gui.HeartType[0]);
    }

    @Inject(method = "forPlayer", at = @At("TAIL"), cancellable = true)
    private static void bleugh(Player player, CallbackInfoReturnable<Gui.HeartType> cir)
    {
        if (player.hasEffect(ModStatusEffects.STORM_POISONING)) cir.setReturnValue(FRRegistries.HeartType.FRONTIERS_STORM);
        else if (player.isOnFire() && cir.getReturnValue().equals(Gui.HeartType.NORMAL)) cir.setReturnValue(FRRegistries.HeartType.FRONTIERS_ONFIRE);

        boolean isNormal = (cir.getReturnValue().equals(Gui.HeartType.NORMAL));
        if (isNormal && Frontiers.CONFIG.doBuffHearts())
        {
            if (((PlayerIntf)player).frontiers_1_21x$usedUpgradeApple()) { cir.setReturnValue(FRRegistries.HeartType.FRONTIERS_PINK); }
            if (false /*player.isClimbing()*/) { cir.setReturnValue(FRRegistries.HeartType.FRONTIERS_PURPLE); }
        }
    }
}
