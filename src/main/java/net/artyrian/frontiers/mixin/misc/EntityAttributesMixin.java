package net.artyrian.frontiers.mixin.misc;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Debug(export = true)
@Mixin(EntityAttributes.class)
public class EntityAttributesMixin
{
    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=generic.armor")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/attribute/ClampedEntityAttribute;<init>(Ljava/lang/String;DDD)V", ordinal = 0),
            index = 3
    )
    private static double buffArmor(double translationKey)
    {
        return 60.0;
    }

    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=generic.max_health")),
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/entity/attribute/ClampedEntityAttribute;<init>(Ljava/lang/String;DDD)V", ordinal = 0),
            index = 3
    )
    private static double buffHP(double translationKey)
    {
        return 2048.0;
    }
}
