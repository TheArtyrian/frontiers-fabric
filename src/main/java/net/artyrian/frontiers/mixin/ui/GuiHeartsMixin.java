package net.artyrian.frontiers.mixin.ui;

import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModHeartType;
import net.artyrian.frontiers.mixin_interfaces.PlayerMixInteface;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.objectweb.asm.Opcodes;
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
// ID OF HEARTTYPE FIELD: field_33952
@Mixin(InGameHud.HeartType.class)
public abstract class GuiHeartsMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static InGameHud.HeartType newHeartType(String internalName,
                                                    int ordinal,
                                                    Identifier fullTex,
                                                    Identifier fullBlinkTex,
                                                    Identifier halfTex,
                                                    Identifier halfBlinkTex,
                                                    Identifier hardcoreFullTex,
                                                    Identifier hardcoreFullBlinkTex,
                                                    Identifier hardcoreHalfTex,
                                                    Identifier hardcoreHalfBlinkTex)
    {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    InGameHud.HeartType[] field_33952;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "net/minecraft/client/gui/hud/InGameHud$HeartType.field_33952:[Lnet/minecraft/client/gui/hud/InGameHud$HeartType;",
            shift = At.Shift.AFTER))
    private static void addCustomHearts(CallbackInfo ci)
    {
        // Get rarity list.
        var hearts = new ArrayList<>(Arrays.asList(field_33952));
        var last = hearts.get(hearts.size() - 1);

        // Frontiers: PINK
        var frontiers_pink = newHeartType(
                "FRONTIERS_PINK",
                     last.ordinal() + 1,
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_full"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_full_blinking"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_half"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_half_blinking"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_full"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_full_blinking"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_half"),
                            Identifier.of(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_half_blinking")
                );
        ModHeartType.FRONTIERS_PINK = frontiers_pink;
        hearts.add(frontiers_pink);

        // Frontiers: PURPLE
        var frontiers_purple = newHeartType(
                "FRONTIERS_PURPLE",
                last.ordinal() + 2,
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_full"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_full_blinking"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_half"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_half_blinking"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_full"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_full_blinking"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_half"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_half_blinking")
        );
        ModHeartType.FRONTIERS_PURPLE = frontiers_purple;
        hearts.add(frontiers_purple);

        // Frontiers: ON FIRE
        var frontiers_onfire = newHeartType(
                "FRONTIERS_ONFIRE",
                last.ordinal() + 3,
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_full"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_full_blinking"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_half"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_half_blinking"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_full"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_full_blinking"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_half"),
                Identifier.of(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_half_blinking")
        );
        ModHeartType.FRONTIERS_ONFIRE = frontiers_onfire;
        hearts.add(frontiers_onfire);

        // Inject.
        field_33952 = hearts.toArray(new InGameHud.HeartType[0]);
    }

    @Inject(method = "fromPlayerState", at = @At("TAIL"), cancellable = true)
    private static void bleugh(PlayerEntity player, CallbackInfoReturnable<InGameHud.HeartType> cir)
    {
        if (player.isOnFire() && cir.getReturnValue() == InGameHud.HeartType.NORMAL) cir.setReturnValue(ModHeartType.FRONTIERS_ONFIRE);
        boolean isNormal = (cir.getReturnValue() == InGameHud.HeartType.NORMAL);
        if (isNormal)
        {
            if (((PlayerMixInteface)player).usedUpgradeApple())
            {
                cir.setReturnValue(ModHeartType.FRONTIERS_PINK);
            }
            if (player.isClimbing())
            {
                cir.setReturnValue(ModHeartType.FRONTIERS_PURPLE);
            }
        }
    }
}
