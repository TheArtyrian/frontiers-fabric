package net.artyrian.frontiers.mixin.entity.parrot;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.misc.ModParrotVariant;
import net.artyrian.frontiers.misc.ModRarity;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
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

// UNUSED!
// ID OF VARIANT FIELD: field_41559
@Mixin(ParrotEntity.Variant.class)
public abstract class ParrotVariantMixin
{
    // Allows new entries.
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static ParrotEntity.Variant newVariant(String internalName, int ordinal, int id, String name)
    {
        throw new AssertionError();
    }

    // Get rarity field.
    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    ParrotEntity.Variant[] field_41559;

    // Injects data.
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(
            value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/entity/passive/ParrotEntity$Variant;field_41559:[Lnet/minecraft/entity/passive/ParrotEntity$Variant;",
            shift = At.Shift.AFTER))
    private static void addCustomVariant(CallbackInfo ci)
    {
        // Get variant list.
        //var variants = new ArrayList<>(Arrays.asList(field_41559));
        //var last = variants.get(variants.size() - 1);
        //var i = 1;

        // Frontiers: Kazooie
        //var kazooie = newVariant(
        //        "FRONTIERS_KAZOOIE",
        //        last.ordinal() + i,
        //        last.ordinal() + i,
        //        "frontiers_kazooie"
        //);
        //ModParrotVariant.FRONTIERS_KAZOOIE = kazooie;
        //variants.add(kazooie);
        //i++;

        // Frontiers: Lovebirb
        //var lovebirb = newVariant(
        //        "FRONTIERS_LOVEBIRB",
        //        last.ordinal() + i,
        //        last.ordinal() + i,
        //        "frontiers_lovebirb"
        //);
        //ModParrotVariant.FRONTIERS_LOVEBIRB = lovebirb;
        //variants.add(lovebirb);
        //Frontiers.LOGGER.info(String.valueOf((last.ordinal() + i)));
        //i++;

        // Inject.
        //field_41559 = variants.toArray(new ParrotEntity.Variant[0]);
    }
}
