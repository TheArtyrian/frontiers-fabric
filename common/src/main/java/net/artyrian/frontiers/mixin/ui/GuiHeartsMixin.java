package net.artyrian.frontiers.mixin.ui;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.misc.ModHeartType;
import net.artyrian.frontiers.mixin_intf.PlayerMixInterface;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
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
import I;
import java.util.ArrayList;
import java.util.Arrays;

// Mixes in custom heart types from net.artyrian.frontiers.misc.ModHeartType.
// ID OF HEARTTYPE FIELD: field_33952
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
    Gui.HeartType[] field_33952;

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
        var i = 1;

        // Frontiers: PINK
        var frontiers_pink = newHeartType(
                "FRONTIERS_PINK",
                     last.ordinal() + i,
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_full"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_full_blinking"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_half"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_half_blinking"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_full"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_full_blinking"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_half"),
                            ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier1_hardcore_half_blinking")
                );
        ModHeartType.FRONTIERS_PINK = frontiers_pink;
        hearts.add(frontiers_pink);
        i++;

        // Frontiers: PURPLE
        var frontiers_purple = newHeartType(
                "FRONTIERS_PURPLE",
                last.ordinal() + i,
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_full"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_full_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_half"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_half_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_full"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_full_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_half"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/tier2_hardcore_half_blinking")
        );
        ModHeartType.FRONTIERS_PURPLE = frontiers_purple;
        hearts.add(frontiers_purple);
        i++;

        // Frontiers: ON FIRE
        var frontiers_onfire = newHeartType(
                "FRONTIERS_ONFIRE",
                last.ordinal() + i,
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_full"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_full_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_half"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_half_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_full"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_full_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_half"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/onfire_hardcore_half_blinking")
        );
        ModHeartType.FRONTIERS_ONFIRE = frontiers_onfire;
        hearts.add(frontiers_onfire);
        i++;

        // Frontiers: STORM
        var frontiers_storm = newHeartType(
                "FRONTIERS_STORM",
                last.ordinal() + i,
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_full"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_full_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_half"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_half_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_hardcore_full"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_hardcore_full_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_hardcore_half"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_hardcore_half_blinking")
        );
        ModHeartType.FRONTIERS_STORM = frontiers_storm;
        hearts.add(frontiers_storm);
        i++;

        // Frontiers: STORM (CONTAINER)
        var frontiers_container_storm = newHeartType(
                "FRONTIERS_CONTAINER_STORM",
                last.ordinal() + i,
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container_hardcore"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container_hardcore_blinking"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container_hardcore"),
                ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, "hud/heart/storm_container_hardcore_blinking")
        );
        ModHeartType.FRONTIERS_CONTAINER_STORM = frontiers_container_storm;
        hearts.add(frontiers_container_storm);
        i++;

        // Inject.
        field_33952 = hearts.toArray(new Gui.HeartType[0]);
    }

    @Inject(method = "fromPlayerState", at = @At("TAIL"), cancellable = true, order = 1100)
    private static void bleugh(Player player, CallbackInfoReturnable<Gui.HeartType> cir)
    {
        if (player.hasEffect(ModStatusEffects.STORM_POISONING)) cir.setReturnValue(ModHeartType.FRONTIERS_STORM);
        else if (player.isOnFire() && cir.getReturnValue() == Gui.HeartType.NORMAL) cir.setReturnValue(ModHeartType.FRONTIERS_ONFIRE);

        boolean isNormal = (cir.getReturnValue() == Gui.HeartType.NORMAL);
        if (isNormal)
        {
            if (((PlayerMixInterface)player).frontiers_1_21x$usedUpgradeApple())
            {
                cir.setReturnValue(ModHeartType.FRONTIERS_PINK);
            }
            if (false /*player.isClimbing()*/)
            {
                cir.setReturnValue(ModHeartType.FRONTIERS_PURPLE);
            }
        }
    }
}
