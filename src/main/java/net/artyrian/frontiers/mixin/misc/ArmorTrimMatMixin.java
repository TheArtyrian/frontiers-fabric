package net.artyrian.frontiers.mixin.misc;

import net.artyrian.frontiers.item.data.ModArmorMaterials;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Debug(export = true)
@Mixin(ItemModelGenerator.class)
public class ArmorTrimMatMixin
{
    @Shadow @Final private static List<ItemModelGenerator.TrimMaterial> TRIM_MATERIALS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void newArmorTrimJumpscare(CallbackInfo ci)
    {
        //List<ItemModelGenerator.TrimMaterial> ADDER = List.of(
        //        new ItemModelGenerator.TrimMaterial("cobalt", 10.0F,
        //                Map.of(ModArmorMaterials.COBALT_ARMOR_MATERIAL, "cobalt_darker")),
        //        new ItemModelGenerator.TrimMaterial("verdinite", 10.1F,
        //                Map.of(ModArmorMaterials.VERDINITE_ARMOR_MATERIAL, "verdinite_darker")),
        //        new ItemModelGenerator.TrimMaterial("vivulite", 10.2F,
        //                Map.of(ModArmorMaterials.VIVULITE_ARMOR_MATERIAL, "vivulite_darker")),
        //        new ItemModelGenerator.TrimMaterial("frostite", 10.3F,
        //                Map.of(ModArmorMaterials.FROSTITE_ARMOR_MATERIAL, "frostite_darker")),
        //        new ItemModelGenerator.TrimMaterial("mourning_gold", 10.4F,
        //                Map.of(ModArmorMaterials.MOURNING_GOLD_ARMOR_MATERIAL, "mourning_gold_darker"))
        //);
//
        //TRIM_MATERIALS.addAll(ADDER);
    }
}
