package net.artyrian.frontiers.mixin.misc;

import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
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

// Mixes in custom rarities from net.artyrian.frontiers.misc.ModRarity.
// ID OF RARITY FIELD: field_8905
@Mixin(Rarity.class)
public abstract class RarityMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Rarity newRarity(String internalName, int internalId, int index, String name, ChatFormatting formatting) {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    Rarity[] $VALUES;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = VectorOpcode.PUTSTATIC,
            target = "Lnet/minecraft/world/item/Rarity;$VALUES:[Lnet/minecraft/world/item/Rarity;",
            shift = At.Shift.AFTER))
    private static void addCustomRarity(CallbackInfo ci)
    {
        // Get rarity list.
        var rarities = new ArrayList<>(Arrays.asList($VALUES));
        var last = rarities.get(rarities.size() - 1);

        // Frontiers: MYTHICAL
        var frontiers_mythical = newRarity("FRONTIERS_MYTHICAL", last.ordinal() + 1, 4, "frontiers_mythical", ChatFormatting.GOLD);
        FRRegistries.Rarities.FRONTIERS_MYTHICAL = frontiers_mythical;
        rarities.add(frontiers_mythical);

        // Frontiers: LEGENDARY
        var frontiers_legendary = newRarity("FRONTIERS_LEGENDARY", last.ordinal() + 2, 5, "frontiers_legendary", ChatFormatting.GREEN);
        FRRegistries.Rarities.FRONTIERS_LEGENDARY = frontiers_legendary;
        rarities.add(frontiers_legendary);

        // Frontiers: UNREAL
        var frontiers_unreal = newRarity("FRONTIERS_UNREAL", last.ordinal() + 3, 6, "frontiers_unreal", ChatFormatting.BLUE);
        FRRegistries.Rarities.FRONTIERS_UNREAL = frontiers_unreal;
        rarities.add(frontiers_unreal);

        // Inject.
        $VALUES = rarities.toArray(new Rarity[0]);
    }
}
