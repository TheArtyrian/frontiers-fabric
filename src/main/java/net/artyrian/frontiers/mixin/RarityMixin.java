package net.artyrian.frontiers.mixin;

import net.artyrian.frontiers.misc.ModRarity;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
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

// Mixes in custom rarities from net.artyrian.frontiers.misc.ModRarity.
// ID OF RARITY FIELD: field_8905
@Mixin(Rarity.class)
public abstract class RarityMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Rarity newRarity(String internalName, int internalId, int index, String name, Formatting formatting) {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    Rarity[] field_8905;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/util/Rarity;field_8905:[Lnet/minecraft/util/Rarity;",
            shift = At.Shift.AFTER))
    private static void addCustomRarity(CallbackInfo ci)
    {
        // Get rarity list.
        var rarities = new ArrayList<>(Arrays.asList(field_8905));
        var last = rarities.get(rarities.size() - 1);

        // Frontiers: MYTHICAL
        var frontiers_mythical = newRarity("FRONTIERS_MYTHICAL", last.ordinal() + 1, 4, "frontiers_mythical", Formatting.GOLD);
        ModRarity.FRONTIERS_MYTHICAL = frontiers_mythical;
        rarities.add(frontiers_mythical);

        // Frontiers: LEGENDARY
        var frontiers_legendary = newRarity("FRONTIERS_LEGENDARY", last.ordinal() + 2, 5, "frontiers_legendary", Formatting.GREEN);
        ModRarity.FRONTIERS_LEGENDARY = frontiers_legendary;
        rarities.add(frontiers_legendary);

        // Frontiers: UNREAL
        var frontiers_unreal = newRarity("FRONTIERS_UNREAL", last.ordinal() + 3, 6, "frontiers_unreal", Formatting.BLUE);
        ModRarity.FRONTIERS_UNREAL = frontiers_unreal;
        rarities.add(frontiers_unreal);

        // Inject.
        field_8905 = rarities.toArray(new Rarity[0]);
    }
}
