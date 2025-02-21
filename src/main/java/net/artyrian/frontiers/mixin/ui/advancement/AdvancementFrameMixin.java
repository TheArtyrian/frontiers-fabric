package net.artyrian.frontiers.mixin.ui.advancement;

import net.artyrian.frontiers.misc.ModAdvancementFrame;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.util.Formatting;
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

// ID OF RARITY FIELD: field_1253
@Mixin(AdvancementFrame.class)
public abstract class AdvancementFrameMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static AdvancementFrame newFrame(String internalName, int internalId, String id, Formatting titleFormat) {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    AdvancementFrame[] field_1253;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/advancement/AdvancementFrame;field_1253:[Lnet/minecraft/advancement/AdvancementFrame;",
            shift = At.Shift.AFTER))
    private static void addCustomRarity(CallbackInfo ci)
    {
        // Get rarity list.
        var frames = new ArrayList<>(Arrays.asList(field_1253));
        var last = frames.get(frames.size() - 1);

        // Frontiers: FRONTIER
        var frontiers_adv = newFrame("FRONTIERS_ADV", last.ordinal() + 1, "frontier_adv", Formatting.BLUE);
        ModAdvancementFrame.FRONTIERS_ADV = frontiers_adv;
        frames.add(frontiers_adv);

        // Inject.
        field_1253 = frames.toArray(new AdvancementFrame[0]);
    }
}
