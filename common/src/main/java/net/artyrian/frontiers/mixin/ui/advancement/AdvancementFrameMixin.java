package net.artyrian.frontiers.mixin.ui.advancement;

import net.artyrian.frontiers.reg.misc.ModAdvancementFrame;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.AdvancementType;
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

// ID OF RARITY FIELD: field_1253
@Mixin(AdvancementType.class)
public abstract class AdvancementFrameMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static AdvancementType newFrame(String internalName, int internalId, String id, ChatFormatting titleFormat) {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    AdvancementType[] $VALUES;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = VectorOpcode.PUTSTATIC,
            target = "Lnet/minecraft/advancement/AdvancementFrame;field_1253:[Lnet/minecraft/advancement/AdvancementFrame;",
            shift = At.Shift.AFTER))
    private static void addCustomRarity(CallbackInfo ci)
    {
        // Get rarity list.
        var frames = new ArrayList<>(Arrays.asList($VALUES));
        var last = frames.get(frames.size() - 1);

        // Frontiers: FRONTIER
        var frontiers_adv = newFrame("FRONTIERS_ADV", last.ordinal() + 1, "frontier_adv", ChatFormatting.BLUE);
        ModAdvancementFrame.FRONTIERS_ADV = frontiers_adv;
        frames.add(frontiers_adv);

        // Inject.
        $VALUES = frames.toArray(new AdvancementType[0]);
    }
}
