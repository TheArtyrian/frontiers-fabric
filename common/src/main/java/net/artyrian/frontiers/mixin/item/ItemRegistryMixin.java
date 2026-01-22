package net.artyrian.frontiers.mixin.item;

import net.artyrian.frontiers.item.data.ModFoodComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Debug(export=true)
@Mixin(Items.class)
public class ItemRegistryMixin
{
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=glistering_melon_slice")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;<init>(Lnet/minecraft/item/Item$Settings;)V", ordinal = 0)
    )
    private static Item.Properties edibleGlisteringMelon(Item.Properties original)
    {
        return new Item.Properties().food(ModFoodComponents.GLISTERING_MELON_REWORK);
    }
}
