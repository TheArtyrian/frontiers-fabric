package net.artyrian.frontiers.mixin.ui.loading;

import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import net.vertisoft.vectorlib.agnostic.util.VectorOpcode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.ArrayList;
import java.util.Arrays;

@Mixin(ReceivingLevelScreen.Reason.class)
public abstract class WorldEntryReasonMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static ReceivingLevelScreen.Reason newReason(String internalName, int ordinal)
    {
        throw new AssertionError();
    }

    // Get field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    ReceivingLevelScreen.Reason[] $VALUES;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = VectorOpcode.PUTSTATIC,
            target = "Lnet/minecraft/client/gui/screens/ReceivingLevelScreen$Reason;$VALUES:[Lnet/minecraft/client/gui/screens/ReceivingLevelScreen$Reason;",
            shift = At.Shift.AFTER))
    private static void addCustomReasons(CallbackInfo ci)
    {
        // Get list.
        var reasons = new ArrayList<>(Arrays.asList($VALUES));
        var last = reasons.get(reasons.size() - 1);
        var i = 1;

        // Frontiers: CRAGS
        var frontiers_crags = newReason(
                "FRONTIERS_CRAGS",
                last.ordinal() + i
        );
        FRRegistries.WorldEntryReason.CRAGS = frontiers_crags;
        reasons.add(frontiers_crags);
        i++;

        // Inject.
        $VALUES = reasons.toArray(new ReceivingLevelScreen.Reason[0]);
    }
}
