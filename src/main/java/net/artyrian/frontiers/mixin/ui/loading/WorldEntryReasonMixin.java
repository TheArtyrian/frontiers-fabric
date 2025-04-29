package net.artyrian.frontiers.mixin.ui.loading;

import net.artyrian.frontiers.misc.ModWorldEntryReason;
import net.minecraft.client.gui.screen.DownloadingTerrainScreen;
import org.objectweb.asm.Opcodes;
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

@Mixin(DownloadingTerrainScreen.WorldEntryReason.class)
public abstract class WorldEntryReasonMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static DownloadingTerrainScreen.WorldEntryReason newReason(String internalName, int ordinal)
    {
        throw new AssertionError();
    }

    // Get field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    DownloadingTerrainScreen.WorldEntryReason[] field_51490;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/client/gui/screen/DownloadingTerrainScreen$WorldEntryReason;field_51490:[Lnet/minecraft/client/gui/screen/DownloadingTerrainScreen$WorldEntryReason;",
            shift = At.Shift.AFTER))
    private static void addCustomReasons(CallbackInfo ci)
    {
        // Get list.
        var reasons = new ArrayList<>(Arrays.asList(field_51490));
        var last = reasons.get(reasons.size() - 1);
        var i = 1;

        // Frontiers: CRAGS
        var frontiers_crags = newReason(
                "FRONTIERS_CRAGS",
                last.ordinal() + i
        );
        ModWorldEntryReason.CRAGS = frontiers_crags;
        reasons.add(frontiers_crags);
        i++;

        // Inject.
        field_51490 = reasons.toArray(new DownloadingTerrainScreen.WorldEntryReason[0]);
    }
}
