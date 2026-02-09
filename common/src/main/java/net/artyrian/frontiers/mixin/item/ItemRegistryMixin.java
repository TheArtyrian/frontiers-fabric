package net.artyrian.frontiers.mixin.item;

import net.artyrian.frontiers.reg.misc.ModFoodComponents;
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
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item;<init>(Lnet/minecraft/world/item/Item$Properties;)V", ordinal = 0)
    )
    private static Item.Properties frontiers$edibleGlisteringMelon(Item.Properties properties)
    {
        return new Item.Properties().food(ModFoodComponents.GLISTERING_MELON_REWORK);
    }

    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=ender_eye")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/EnderEyeItem;<init>(Lnet/minecraft/world/item/Item$Properties;)V", ordinal = 0)
    )
    private static Item.Properties frontiers$enderEyeRework(Item.Properties properties)
    {
        return new Item.Properties().stacksTo(16);
    }
}
